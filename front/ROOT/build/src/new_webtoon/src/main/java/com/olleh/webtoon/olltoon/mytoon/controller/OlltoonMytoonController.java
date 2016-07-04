package com.olleh.webtoon.olltoon.mytoon.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.orderbuy.domain.BuyDomain;
import com.olleh.webtoon.common.dao.orderbuy.domain.OrderDomain;
import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.dao.user.domain.NameconDomain;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AppInfoUtil;
import com.olleh.webtoon.common.util.AppVersionUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.EncryptUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.StringUtil;
import com.olleh.webtoon.mobile.controller.MytoonController;
import com.olleh.webtoon.olltoon.common.util.KeyUtil;

@Controller
public class OlltoonMytoonController {
	
	@Autowired
	UserServiceIface userServiceIface;
	
	@Autowired
	OrderbuyService orderbuyService;
	
	@Autowired
	PremiumService premiumService;
	
	@RequestMapping(value = "/ot/mytoon/userDetail.kt")
	public ModelAndView otUserDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("olltoon/"+(AppInfoUtil.isMobile(request) ? "mobile" : "pc" )+"/mytoon/userDetail");

		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.setViewName("redirect:/web/login.kt?urlcd="+ MessageUtil.getSystemMessage("system.mobile.domain")+"/ot/mytoon/berryuseList.kt");
			return modelAndView;
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		request.setAttribute("userMap", userMap); 
		
		OllehUserDomain newUserDomain = null;				
		if(userMap != null && "open".equals(userMap.get("idfg"))){ 
			UserDomain tmpUserDomain = (UserDomain)userServiceIface.getUserInfoByEmail((String)userMap.get("userid"));
			tmpUserDomain.setIdfg("open");
			tmpUserDomain.setUserid(tmpUserDomain.getEmail());
			newUserDomain = (OllehUserDomain)tmpUserDomain;
		}else if(userMap != null && "olleh".equals(userMap.get("idfg"))){
			newUserDomain = userServiceIface.ollehUserDetail((String)userMap.get("userid"));
			newUserDomain.setIdfg("olleh");
			newUserDomain.setUserid(newUserDomain.getOllehid());
		}else if(userMap != null && ("naver".equals(userMap.get("idfg")) || "ctn".equals(userMap.get("idfg")))){
			UserDomain tmpUserDomain = new UserDomain();
			
			tmpUserDomain.setIdfg((String)userMap.get("idfg"));			
			tmpUserDomain.setUserid((String)userMap.get("userid"));
			tmpUserDomain.setEmail((String)userMap.get("userid"));
			tmpUserDomain.setNickname((String)userMap.get("nickname"));
			tmpUserDomain.setTermsyn((String)userMap.get("termsyn"));
			tmpUserDomain.setAdultyn((String)userMap.get("adultyn"));
			tmpUserDomain.setBirthday((String)userMap.get("birthday"));
			tmpUserDomain.setNameconseq(new Integer(String.valueOf(userMap.get("nameconseq"))));
			tmpUserDomain.setNameconurl((String)userMap.get("nameconurl"));
			tmpUserDomain.setMnameconurl((String)userMap.get("mnameconurl"));
			
			newUserDomain = (OllehUserDomain)tmpUserDomain;
		}
			
		request.setAttribute("ot_newyn", AppVersionUtil.isNewApi(request) ? "Y" : "N");
		request.setAttribute("userDomain", newUserDomain); 
		
		int nameconseq = StringUtil.defaultInt(String.valueOf(userMap.get("nameconseq")), 5);
		
		if(nameconseq != newUserDomain.getNameconseq()){
			String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
			String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
			String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
			int intCookieAge = Integer.parseInt(cookieAge);
			
			//쿠키정보 변경		
			CookieUtil.setCookie(response, "u_nc_seq", String.valueOf(newUserDomain.getNameconseq()), intCookieAge, cookiePath, cookieDomain);
			CookieUtil.setCookie(response, "u_nc_url", newUserDomain.getNameconurl(), intCookieAge, cookiePath, cookieDomain);
			CookieUtil.setCookie(response, "u_mnc_url", newUserDomain.getMnameconurl(), intCookieAge, cookiePath, cookieDomain);
		}
				
		return modelAndView;
	}
	
	@RequestMapping(value = "/ot/mytoon/nameconChange.kt")
	public ModelAndView otNameconChange(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam(value = "returnUrl", defaultValue = "") String returnUrl ) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		MytoonController mytoon = new MytoonController();
		modelAndView = mytoon.nameconChange(request, response, returnUrl );
		modelAndView.setViewName("olltoon/"+(AppInfoUtil.isMobile(request) ? "mobile" : "pc" )+"/mytoon/nameconChange");
		
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.setViewName("redirect:/web/login.kt");
			return modelAndView;
		}
		
		request.setAttribute("ot_token", ot_token);

		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		OllehUserDomain newUserDomain = null;				
		if(userMap != null && "open".equals(userMap.get("idfg"))){ 
			UserDomain tmpUserDomain = (UserDomain)userServiceIface.getUserInfoByEmail((String)userMap.get("userid"));
			tmpUserDomain.setIdfg("open");
			tmpUserDomain.setUserid(tmpUserDomain.getEmail());
			newUserDomain = (OllehUserDomain)tmpUserDomain;
		}else if(userMap != null && "olleh".equals(userMap.get("idfg"))){
			newUserDomain = userServiceIface.ollehUserDetail((String)userMap.get("userid"));
			newUserDomain.setIdfg("olleh");
			newUserDomain.setUserid(newUserDomain.getOllehid());
		}
		
		request.setAttribute("userDomain", newUserDomain); 

		return modelAndView;
	}
	
	@RequestMapping(value = "/ot/mytoon/berryuseList.kt")
	public ModelAndView otBerryuseList(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.setViewName("redirect:/web/login.kt?urlcd="+ MessageUtil.getSystemMessage("system.mobile.domain")+"/ot/mytoon/berryuseList.kt");
			return modelAndView;
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		OllehUserDomain newUserDomain = null;				
		if(userMap != null && "open".equals(userMap.get("idfg"))){ 
			UserDomain tmpUserDomain = (UserDomain)userServiceIface.getUserInfoByEmail((String)userMap.get("userid"));
			tmpUserDomain.setIdfg("open");
			tmpUserDomain.setUserid(tmpUserDomain.getEmail());
			newUserDomain = (OllehUserDomain)tmpUserDomain;
		}else if(userMap != null && "olleh".equals(userMap.get("idfg"))){
			newUserDomain = userServiceIface.ollehUserDetail((String)userMap.get("userid"));
			newUserDomain.setIdfg("olleh");
			newUserDomain.setUserid(newUserDomain.getOllehid());
		}else if(userMap != null && ("naver".equals(userMap.get("idfg")) || "ctn".equals(userMap.get("idfg")))){
			UserDomain tmpUserDomain = new UserDomain();
			
			tmpUserDomain.setIdfg((String)userMap.get("idfg"));			
			tmpUserDomain.setUserid((String)userMap.get("userid"));
			tmpUserDomain.setEmail((String)userMap.get("userid"));
			tmpUserDomain.setNickname((String)userMap.get("nickname"));
			tmpUserDomain.setTermsyn((String)userMap.get("termsyn"));
			tmpUserDomain.setAdultyn((String)userMap.get("adultyn"));
			tmpUserDomain.setBirthday((String)userMap.get("birthday"));			
			tmpUserDomain.setNameconseq(new Integer(String.valueOf(userMap.get("nameconseq"))));
			tmpUserDomain.setNameconurl((String)userMap.get("nameconurl"));
			tmpUserDomain.setMnameconurl((String)userMap.get("mnameconurl"));
			
			newUserDomain = (OllehUserDomain)tmpUserDomain;
		}
		
		request.setAttribute("ot_newyn", AppVersionUtil.isNewApi(request) ? "Y" : "N");
		request.setAttribute("userDomain", newUserDomain); 
		
		MytoonController mytoon = new MytoonController();
		modelAndView = mytoon.berryuseList(request);
		
		modelAndView.setViewName("olltoon/"+(AppInfoUtil.isMobile(request) ? "mobile" : "pc" )+"/mytoon/berryuseList");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/ot/mytoon/berrychargeList.kt")
	public ModelAndView otBerrychargeList(HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.setViewName("redirect:/web/login.kt");
			return modelAndView;
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		OllehUserDomain newUserDomain = null;				
		if(userMap != null && "open".equals(userMap.get("idfg"))){ 
			UserDomain tmpUserDomain = (UserDomain)userServiceIface.getUserInfoByEmail((String)userMap.get("userid"));
			tmpUserDomain.setIdfg("open");
			tmpUserDomain.setUserid(tmpUserDomain.getEmail());
			newUserDomain = (OllehUserDomain)tmpUserDomain;
		}else if(userMap != null && "olleh".equals(userMap.get("idfg"))){
			newUserDomain = userServiceIface.ollehUserDetail((String)userMap.get("userid"));
			newUserDomain.setIdfg("olleh");
			newUserDomain.setUserid(newUserDomain.getOllehid());
		}else if(userMap != null && ("naver".equals(userMap.get("idfg")) || "ctn".equals(userMap.get("idfg")))){
			UserDomain tmpUserDomain = new UserDomain();
			
			tmpUserDomain.setIdfg((String)userMap.get("idfg"));			
			tmpUserDomain.setUserid((String)userMap.get("userid"));
			tmpUserDomain.setEmail((String)userMap.get("userid"));
			tmpUserDomain.setNickname((String)userMap.get("nickname"));
			tmpUserDomain.setTermsyn((String)userMap.get("termsyn"));
			tmpUserDomain.setAdultyn((String)userMap.get("adultyn"));
			tmpUserDomain.setBirthday((String)userMap.get("birthday"));			
			tmpUserDomain.setNameconseq(new Integer(String.valueOf(userMap.get("nameconseq"))));
			tmpUserDomain.setNameconurl((String)userMap.get("nameconurl"));
			tmpUserDomain.setMnameconurl((String)userMap.get("mnameconurl"));
			
			newUserDomain = (OllehUserDomain)tmpUserDomain;
		}
		
		request.setAttribute("userDomain", newUserDomain);
		request.setAttribute("ot_newyn", AppVersionUtil.isNewApi(request) ? "Y" : "N");
		
		MytoonController mytoon = new MytoonController();
		modelAndView = mytoon.berrychargeList(request);
		
		modelAndView.setViewName("olltoon/"+(AppInfoUtil.isMobile(request) ? "mobile" : "pc" )+"/mytoon/berrychargeList");
		
		return modelAndView;
	}
	
	/**
	 * 새로운 비밀번호 유효성 검사 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/user/newPasswdCheck.kt")
	public ModelAndView newPasswdCheck(@RequestParam(value="passwd", required=true) String passwd, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.setViewName("redirect:/web/login.kt");
			return modelAndView;
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
					
		UserDomain user = (UserDomain)userServiceIface.getUserInfoByEmail((String)userMap.get("userid"));
		
		String email = user.getEmail();
		if(user.getEmail().indexOf("@") > -1)
			email = user.getEmail().substring(0, user.getEmail().indexOf("@")); 
		
		if(passwd.indexOf(email) > - 1){
			modelAndView.addObject("erroryn", "Y");
//			modelAndView.addObject("errormsg", "로그인 후 이용해주세요.");
			return modelAndView;
		}
		
		modelAndView.addObject("erroryn", "N");
		
		return modelAndView;
		
	}
	
	/**
	 * 회원가입 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/user/passwdCheck.kt")
	public ModelAndView passwdCheck(@RequestParam(value="passwd", required=true) String passwd, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.setViewName("redirect:/web/login.kt");
			return modelAndView;
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
					
		UserDomain user = (UserDomain)userServiceIface.getUserInfoByEmail((String)userMap.get("userid"));
		
		//현재 비밀번호 정보 셋팅
		EncryptUtil encryptUtil = new EncryptUtil();     
		user.setPasswd(encryptUtil.encryptSHA512(passwd));
		
		//비밀번호 체크
		int checkCount = userServiceIface.passwdCheckCount(user);
		
		if(checkCount > 0)
			modelAndView.addObject("useyn", "Y");
		else 
			modelAndView.addObject("useyn", "N");
		
		return modelAndView;
		
	}
	
	/**
	 * 회원 비밀번호 변경
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/user/passwdChangeProc.kt")
	public ModelAndView passwdChangeProc(UserDomain userDomain, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("mappingJacksonJsonView");
		
		modelAndView.addObject("erroryn", "Y");		
		
		EncryptUtil encryptUtil = new EncryptUtil();
		String encPasswd = encryptUtil.encryptSHA512(StringUtil.changeNullToEmpty(userDomain.getPasswd()));
		String encNewpasswd = encryptUtil.encryptSHA512(StringUtil.changeNullToEmpty(userDomain.getNewpasswd()));
		String encCaptcha = encryptUtil.encryptSHA512(StringUtil.changeNullToEmpty(userDomain.getCaptcha()));
		
		//현재비밀번호와 새로운 비밀번호 일치 여부 확인
		if(userDomain.getPasswd().equals(userDomain.getNewpasswd())){
			modelAndView.addObject("errormsg", "현재비밀번호와 새로운 비밀번호가 같습니다.");
			modelAndView.addObject("userDomain", null);		
			return modelAndView;
		}
		
        // 쿠키에 저장된 캡차코드
		String cookieEncryptCaptcha = CookieUtil.getCookie(request, "u_cc_as");
		
		// 쿠키와 파라메터 암호화된 캡차코드 비교
		if(!cookieEncryptCaptcha.equals(encCaptcha)){
			modelAndView.addObject("errormsg", "자동 입력 방지 문자 입력이 틀렸어요~");
			modelAndView.addObject("ccreload", "Y"); //캡차코드 새로고침
			modelAndView.addObject("userDomain", null);		
			return modelAndView;
		}
		
		
		
		
		
		
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.setViewName("redirect:/web/login.kt");
			return modelAndView;
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
					
		UserDomain user = (UserDomain)userServiceIface.getUserInfoByEmail((String)userMap.get("userid"));
		
		
		
		
		
		//비밀번호에 생년월일 정보 포함여부 확인
		if(user.getBirthday() != null && user.getBirthday().length() == 8){
			if(userDomain.getNewpasswd().indexOf(user.getBirthday().substring(4, 8)) > -1){
				modelAndView.addObject("errormsg", "정보 보호를 위해, 생년월일 입력은 앙대요~!");
				modelAndView.addObject("userDomain", null);		
				return modelAndView;
			}
		}
		
		userDomain.setEmail(user.getEmail());
		userDomain.setPasswd(encPasswd);
		userDomain.setNewpasswd(encNewpasswd);
		
		String pwchangedt = DateUtil.getNowDate(1);
		userDomain.setPwchangedt(pwchangedt);		
		
		//회원 확인
		if(userServiceIface.passwdCheckCount(userDomain) > 0) {	
			modelAndView.addObject("erroryn", "N");
			userServiceIface.newPasswd(userDomain);
		}
		
		return modelAndView;
		
	}
	
	/**
	 * 캡차코드 일치여부 체크
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/user/captchaCheck.kt")
	public ModelAndView captchaCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//JsonView를 View로 지정한다.
		modelAndView.setViewName("mappingJacksonJsonView");		
		modelAndView.addObject("erroryn", "N");
		
		//캡차 파라메터
        String paramCaptcha = request.getParameter("captcha");
        
        if(paramCaptcha == null || "".equals(paramCaptcha)){
			modelAndView.addObject("erroryn", "Y");
			return modelAndView;
        }

        //기본 불일치
        modelAndView.addObject("isEquals", "N");
        
		// 캡차코드 암호화
        EncryptUtil encryptUtil = new EncryptUtil();
        String paramEncryptCaptcha = encryptUtil.encryptSHA512(paramCaptcha);
		
        // 쿠키에 저장된 캡차코드
		String cookieEncryptCaptcha = CookieUtil.getCookie(request, "u_cc_as");
		
		// 쿠키와 파라메터 암호화된 캡차코드 비교
		if(cookieEncryptCaptcha.equals(paramEncryptCaptcha)){
			//일치로 변경처리
			modelAndView.addObject("isEquals", "Y");
		}
		
		return modelAndView;		
	}
	
	/**
	 * 회원 네임콘 변경
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/user/nameconChangeProc.kt")
	public ModelAndView nameconChangeProc(UserDomain userDomain, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("mappingJacksonJsonView");
		
		modelAndView.addObject("erroryn", "Y");		
		
		
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.setViewName("redirect:/web/login.kt");
			return modelAndView;
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		OllehUserDomain user = null;				
		if(userMap != null && "open".equals(userMap.get("idfg"))){
			UserDomain tmpUserDomain = (UserDomain)userServiceIface.getUserInfoByEmail((String)userMap.get("userid"));
			tmpUserDomain.setIdfg("open");
			tmpUserDomain.setUserid(tmpUserDomain.getEmail());
			user = (OllehUserDomain)tmpUserDomain;
		}else if(userMap != null && "olleh".equals(userMap.get("idfg"))){
			user = userServiceIface.ollehUserDetail((String)userMap.get("userid"));
			user.setIdfg("olleh");
			user.setUserid(user.getOllehid());
		}
		
		//네이버, CTN 계정추가
		if(userMap != null && ("naver".equals(userMap.get("idfg")) || "ctn".equals(userMap.get("idfg")))){
			userDomain.setIdfg((String)userMap.get("idfg"));
			userDomain.setEmail((String)userMap.get("userid"));
		}else{
			userDomain.setEmail(user.getUserid());
			userDomain.setIdfg(user.getIdfg());
		}
		
		//네임콘 변경
		userServiceIface.defualtNamecon(userDomain);		
		
		modelAndView.addObject( "userDomain", null );
		
		return modelAndView;
		
	}
	
	
	@RequestMapping(value = "/ot/mytoon/berryuseJsonList.kt")
	public ModelAndView berryuseJsonList(BuyDomain buyDomain, HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.setViewName("redirect:/web/login.kt");
			return modelAndView;
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		OllehUserDomain userDomain = new OllehUserDomain();				
		if(userMap != null && "open".equals(userMap.get("idfg"))){ 
			UserDomain tmpUserDomain = (UserDomain)userServiceIface.getUserInfoByEmail((String)userMap.get("userid"));
			tmpUserDomain.setIdfg("open");
			tmpUserDomain.setUserid(tmpUserDomain.getEmail());
			userDomain = (OllehUserDomain)tmpUserDomain;
		}else if(userMap != null && "olleh".equals(userMap.get("idfg"))){
			userDomain = userServiceIface.ollehUserDetail((String)userMap.get("userid"));
			userDomain.setIdfg("olleh");
			userDomain.setUserid(userDomain.getOllehid());
		} 
		
		
		//네이버, CTN 계정추가
		if(userMap != null && ("naver".equals(userMap.get("idfg")) || "ctn".equals(userMap.get("idfg")))){
			userDomain.setIdfg((String)userMap.get("idfg"));		
			userDomain.setUserid((String)userMap.get("userid"));
			
			buyDomain.setIdfg((String)userMap.get("idfg"));		
			buyDomain.setBuyid((String)userMap.get("userid"));
		}else{
			//로그인 유저 이메일 셋팅
			buyDomain.setBuyid(userDomain.getUserid());
			
			if( userDomain.getIdfg().equals("open") ) 
				buyDomain.setIdfg("open");
			else
				buyDomain.setIdfg("olleh");
		}

		//페이지 사이즈 기본값
		if(buyDomain.getPageSize() == 0) buyDomain.setPageSize(10);
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = orderbuyService.buyListCnt(buyDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//구매 목록
		List<BuyDomain> list = orderbuyService.berryuseList(buyDomain);
		modelAndView.addObject("berryuseList", list);
		
		// 사용자 총 베리 보유량
		String userBerry = orderbuyService.strBerryAvail(userDomain.getUserid(), userDomain.getIdfg());
		modelAndView.addObject("userBerry",userBerry);
		
		// 사용자 총 블루베리 보유량
//		String userBlueBerry = orderbuyService.strBlueBerryAvail(userDomain.getUserid(), userDomain.getIdfg());
//		modelAndView.addObject("userBlueBerry",userBlueBerry);
//		
//		// 사용자 총 라즈베리 보유량
//		String userRaspBerry = orderbuyService.strRaspBerryAvail(userDomain.getUserid(), userDomain.getIdfg());
//		modelAndView.addObject("userRaspBerry", StringUtil.defaultStr(userRaspBerry, "0"));
//				
//		// 블루멤버십 가입 여부 조회
//		String blueMembershipyn = premiumService.getBlueMembershipInfo(userDomain.getUserid(), userDomain.getIdfg());
//		modelAndView.addObject("blueMembershipyn", blueMembershipyn);

		modelAndView.addObject("buyDomain",null);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	
	

	
	@RequestMapping(value = "/ot/mytoon/berrychargeJsonList.kt")
	public ModelAndView berrychargeJsonList(OrderDomain orderDomain, HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.setViewName("redirect:/web/login.kt");
			return modelAndView;
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		OllehUserDomain userDomain = new OllehUserDomain();			
		if(userMap != null && "open".equals(userMap.get("idfg"))){ 
			UserDomain tmpUserDomain = (UserDomain)userServiceIface.getUserInfoByEmail((String)userMap.get("userid"));
			tmpUserDomain.setIdfg("open");
			tmpUserDomain.setUserid(tmpUserDomain.getEmail());
			userDomain = (OllehUserDomain)tmpUserDomain;
		}else if(userMap != null && "olleh".equals(userMap.get("idfg"))){
			userDomain = userServiceIface.ollehUserDetail((String)userMap.get("userid"));
			userDomain.setIdfg("olleh");
			userDomain.setUserid(userDomain.getOllehid());
		}else if(userMap != null && ("naver".equals(userMap.get("idfg")) || "ctn".equals(userMap.get("idfg")))){
			userDomain.setIdfg((String)userMap.get("idfg"));		
			userDomain.setUserid((String)userMap.get("userid"));
		}	
		
		if(userMap != null && userMap.get("ctnuserseq") != null){ 
			orderDomain.setCtnuserseq((userMap.get("ctnuserseq").toString()));
		}
		
		//네이버, CTN 계정추가
		if(userMap != null && ("naver".equals(userMap.get("idfg")) || "ctn".equals(userMap.get("idfg")))){
			userDomain.setIdfg((String)userMap.get("idfg"));		
			userDomain.setUserid((String)userMap.get("userid"));
			
			orderDomain.setIdfg((String)userMap.get("idfg"));		
			orderDomain.setOrderid((String)userMap.get("userid"));
		} else{
			
			//로그인 유저 이메일 셋팅
			orderDomain.setOrderid(userDomain.getUserid());
			
			if( userDomain.getIdfg().equals("open") ) 
				orderDomain.setIdfg("open");
			else
				orderDomain.setIdfg("olleh");
			
		}
		
		//페이지 사이즈 기본값
		if(orderDomain.getPageSize() == 0) orderDomain.setPageSize(10);
		
		if("ctn".equals(userMap.get("idfg"))){
			
			//ModelAndView 객체에 조회한 전체 건수를 넣는다.
			modelAndView.addObject("totalCnt",0);
			modelAndView.addObject("berrychargeList", null);
			modelAndView.addObject("userBerry",null);
			
		}else{
			//ModelAndView 객체에 조회한 전체 건수를 넣는다.
			int totalCnt = orderbuyService.orderListCnt(orderDomain);
			modelAndView.addObject("totalCnt",totalCnt);
			
			//베리 충전 목록
			List<OrderDomain> list = orderbuyService.berrychargeList(orderDomain);
			modelAndView.addObject("berrychargeList", list);
			
			// 사용자 총 베리 보유량
			String userBerry = orderbuyService.strBerryAvail(userDomain.getUserid(), userDomain.getIdfg());
			modelAndView.addObject("userBerry",userBerry);
			
			// 사용자 총 블루베리 보유량
//			String userBlueBerry = orderbuyService.strBlueBerryAvail(userDomain.getUserid(), userDomain.getIdfg());
//			modelAndView.addObject("userBlueBerry",userBlueBerry);

			// 사용자 총 라즈베리 보유량
//			String userRaspBerry = orderbuyService.strRaspBerryAvail(userDomain.getUserid(), userDomain.getIdfg());
//			modelAndView.addObject("userRaspBerry", StringUtil.defaultStr(userRaspBerry, "0"));
			
			// 블루멤버십 가입 여부 조회
//			String blueMembershipyn = premiumService.getBlueMembershipInfo(userDomain.getUserid(), userDomain.getIdfg());
//			modelAndView.addObject("blueMembershipyn", blueMembershipyn);
		}

		modelAndView.addObject("orderDomain", null);
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	
	
	

	
	/**
	 * 네임콘 리스트 조회 후
	 * 결과 json을 보내주는 RequestMapping메소드
	 * 
	 * @return ModelAndView modelAndView = ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/mytoon/nameconJsonList.kt")
	public ModelAndView yoyozineJsonList(@ModelAttribute("userDomain") UserDomain userDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();

		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.setViewName("redirect:/web/login.kt");
			return modelAndView;
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		
		
		String listtype = (String)request.getParameter("listtype");
		
		userDomain.setEmail((String)userMap.get("userid"));
		userDomain.setIdfg((String)userMap.get("idfg"));
		
		int getNameconCnt = 0;

		if (listtype.equals("free")){
			userDomain.setNameconlisttype("free");
			//네임콘 리스트 갯수
			getNameconCnt = userServiceIface.defualtNameconCnt(userDomain);
			modelAndView.addObject( "totalCnt", getNameconCnt );
			
			//네임콘 리스트
			List<NameconDomain> getNameconlist = userServiceIface.freeNameconList( userDomain );
			modelAndView.addObject( "defualtNameconList", getNameconlist );
			
		}else if(listtype.equals("pay")){
			userDomain.setNameconlisttype("pay");
			//네임콘 리스트 갯수
			getNameconCnt = userServiceIface.defualtNameconCnt(userDomain);
			modelAndView.addObject( "totalCnt", getNameconCnt );
			
			//네임콘 리스트
			List<NameconDomain> getNameconlist = userServiceIface.payNameconList( userDomain );
			modelAndView.addObject( "defualtNameconList", getNameconlist );
			
		}else if(listtype.equals("BM50")){
			userDomain.setNameconlisttype("BM50");
			//네임콘 리스트 갯수
			getNameconCnt = userServiceIface.defualtNameconCnt(userDomain);
			modelAndView.addObject( "totalCnt", getNameconCnt );
			
			//네임콘 리스트
			List<NameconDomain> getNameconlist = userServiceIface.bmNameconList( userDomain );
			modelAndView.addObject( "defualtNameconList", getNameconlist );
			
		}else {
			userDomain.setNameconlisttype("default");
			//네임콘 리스트 갯수
			getNameconCnt = userServiceIface.defualtNameconCnt(userDomain);
			modelAndView.addObject( "totalCnt", getNameconCnt );
			
			//네임콘 리스트
			List<NameconDomain> getNameconlist = userServiceIface.defualtNameconList( userDomain );
			modelAndView.addObject( "defualtNameconList", getNameconlist );
			
		}
		
		modelAndView.addObject( "userDomain", null );
		modelAndView.setViewName("mappingJacksonJsonView");
		return modelAndView;
	}

}