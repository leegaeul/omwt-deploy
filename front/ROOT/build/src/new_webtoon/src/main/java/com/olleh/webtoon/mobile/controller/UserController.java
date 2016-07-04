/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : UserController.java
 * DESCRIPTION    : 웹툰 회원 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-21      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.user.domain.NameconDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AesCipherUtil;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller("MobileUserController")
public class UserController {
			
	protected static Log logger = LogFactory.getLog(UserController.class);
	
	@Autowired
	UserServiceIface userServiceIface;
		
	/**
	 * 로그인 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = {"/m/login.kt", "/m/appLogin.kt"})
	public ModelAndView login(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 상태
		if(AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/main.kt");
			
			return modelAndView;
		}
		
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
		
		//PC 기기로 접속시 PC 로그인 페이지로
		if("real".equals(System.getProperty("serverType")) && !RequestUtil.isMobile(request)){
			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain.ssl")+"/login.kt" + urlcd);		
			return modelAndView;
		}
		
		String u_cc_fc = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_cc_fc"));
		String u_cc_em = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_cc_em"));
		
		if(u_cc_fc != null && !"".equals(u_cc_fc)){
			//복호화 처리	
			AesCipherUtil aesCipherUtil = new AesCipherUtil();
			String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
			String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
			u_cc_em = aesCipherUtil.decrypt(key, initialVector, u_cc_em.getBytes());	
			
			modelAndView.addObject("u_cc_em", u_cc_em);	
		}
		
		//강제 로그인 페이지 이동
		//modelAndView.setViewName("redirect:https://login.olleh.com/wamui/AthMobile.do?mRt=http://webtoon.olleh.com/m/main.kt");
		//return modelAndView;
		
		urlcd = urlcd.replaceAll("urlcd=", "");
		modelAndView.addObject("urlcd", urlcd);
		modelAndView.setViewName("mobile/user/login");
		
		//앱 설정에서 로그인 페이지 호출시 로그인 후 설정 URl 호출
		if(request.getRequestURI().indexOf("/m/appLogin.kt") > -1){
			modelAndView.addObject("rturl", "/m/appConf.kt");
		}
		
		return modelAndView;
		
	}
	
	/**
	 * 로그아웃 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 * @throws Exception 
	 */
	@RequestMapping(value = {"/m/appLogout.kt"})
	public ModelAndView appLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/logout");
		
		return modelAndView;		
	}
	
	/**
	 * 로그아웃 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 * @throws Exception 
	 */
	@RequestMapping(value = {"/m/logout.kt"})
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
		
		//모바일 앱에서 로그인시 Device정보에 로그인 정보를 update
		 String uuid = RequestUtil.getUuid(request);
		 if(uuid != null){
			 UserDomain userDomain = new UserDomain();

			 userDomain.setUuid(uuid);
			 userDomain.setIdfg(null);
			 userDomain.setLoginyn("N");
			 
			 userServiceIface.updateEmailByUuid(userDomain);					 
		 }
		
		//올레닷컴 로그아웃 처리
		modelAndView.setViewName("redirect:https://login.olleh.com/wamui/ComSSOLogout.do?urlcd="+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/toon/weekList.kt");
		
		//쿠키 삭제
		CookieUtil.deleteAllCookie(request, response, cookieDomain);
		
		return modelAndView;		
	}
	
	/**
	 * 로그아웃 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 * @throws Exception 
	 */
	@RequestMapping(value = {"/m/logoutIfm.kt"})
	public ModelAndView logoutIfm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
		
		//모바일 앱에서 로그인시 Device정보에 로그인 정보를 update
		 String uuid = RequestUtil.getUuid(request);
		 if(uuid != null){
			 UserDomain userDomain = new UserDomain();
			 
			 userDomain.setUuid(uuid);
			 userDomain.setIdfg(null);
			 userDomain.setLoginyn("N");
			 
			 userServiceIface.updateEmailByUuid(userDomain);					 
		 }
		
		//올레닷컴 로그아웃 처리
		modelAndView.setViewName("redirect:https://login.olleh.com/wamui/ComSSOLogout.do?urlcd="+MessageUtil.getSystemMessage("system.mobile.domain")+"/logout.jsp?p=Y");
		
		//쿠키 삭제
		CookieUtil.deleteAllCookie(request, response, cookieDomain);
		
		//앱 설정에서 로그아웃 호출시 로그아웃 후 설정 URL 호출
		if(request.getRequestURI().indexOf("/m/appLogout.kt") > -1){			
			//modelAndView.setViewName("redirect:https://login.olleh.com/wamui/ComSSOLogout.do?urlcd="+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/toon/weekList.kt?rturl=/m/appConf.kt");
		}
		
		return modelAndView;		
	}
	
	/**
	 * 비밀번호 찾기 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/user/passwdSearch.kt")
	public ModelAndView passwdSearch(HttpServletRequest request) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 상태
		if(AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/main.kt");
			return modelAndView;
		}
		
		modelAndView.setViewName("mobile/user/passwdSearch");
		
		return modelAndView;
		
	}
	
	/**
	 * 회원가입 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/user/regist.kt")
	public ModelAndView regist(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 상태
		if(AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/main.kt");
			return modelAndView;
		}
		
		modelAndView.setViewName("mobile/user/regist");
		
		return modelAndView;
		
	}
	
	/**
	 * 회원가입 완료 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/user/registComplete.kt")
	public ModelAndView registComplete(HttpServletRequest request) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();	
		
		//로그인 상태
		if(AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		modelAndView.setViewName("mobile/user/registComplete");
		
		return modelAndView;
		
	}
	
	/**
	 * 인증이 완료되지 않았을때 처리 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/user/loginNotAuth.kt")
	public ModelAndView loginNotAuth(HttpServletRequest request) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();	
		
		modelAndView.setViewName("mobile/user/loginNotAuth");
		
		return modelAndView;
		
	}
	
	/**
	 * 올렛 ID 계정 약관 동의 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = {"/m/user/ollehUserAgreement.kt", "/m/user/webtoonUserAgreement.kt"})
	public ModelAndView ollehUserAgreement(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String u_check_no = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_check_no"), "");			
		
		//약관 미동의 올레회원 체크
		if("3".equals(u_check_no) || "5".equals(u_check_no)){
			modelAndView.setViewName("mobile/user/ollehUserAgreement");			
		}else{
			modelAndView.setViewName("redirect:/m/main.kt");
		}
		
		return modelAndView;		
	}
	
	/**
	 * 이용약관 내용 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/user/terms.kt")
	public ModelAndView terms() throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/user/terms");
		
		return modelAndView;
		
	}
	
	/**
	 * 네임콘 리스트 조회 후
	 * 결과 json을 보내주는 RequestMapping메소드
	 * 
	 * @return ModelAndView modelAndView = ModelAndView 객체
	 */
	@RequestMapping(value = "/m/mytoon/nameconJsonList.kt")
	public ModelAndView yoyozineJsonList(@ModelAttribute("userDomain") UserDomain userDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();

		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		
		String listtype = (String)request.getParameter("listtype");
		
		userDomain.setEmail(user.getEmail());
		userDomain.setIdfg(user.getIdfg());
		
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