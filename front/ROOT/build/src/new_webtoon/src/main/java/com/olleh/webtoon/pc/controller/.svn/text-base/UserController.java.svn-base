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

package com.olleh.webtoon.pc.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AesCipherUtil;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.EncryptUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
public class UserController {
			
	protected static Log logger = LogFactory.getLog(UserController.class);
	
	@Autowired
	UserServiceIface userServiceIface;
		
	/**
	 * 로그인 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/login.kt")
	public ModelAndView login(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 상태
		if(AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/main.kt"); 
			return modelAndView;
		}
		
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
		
		//모바일 기기로 접속시 모바일 로그인 페이지로
		if(RequestUtil.isMobile(request)){
			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain.ssl")+"/m/login.kt" + urlcd);
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
		//modelAndView.setViewName("redirect:https://login.olleh.com/wamui/AthWeb.do?urlcd=http://webtoon.olleh.com/");
		//return modelAndView;
		
		urlcd = urlcd.replaceAll("urlcd=", "");
		modelAndView.addObject("urlcd", urlcd);
		modelAndView.setViewName("pc/user/login");
		
		return modelAndView;
		
	}
	
	/**
	 * 로그아웃 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/logout.kt")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
		
		//올레닷컴 로그아웃 처리
		modelAndView.setViewName("redirect:https://login.olleh.com/wamui/ComSSOLogout.do?urlcd="+MessageUtil.getSystemMessage("system.pc.domain")+"/main.kt");
		
		//쿠키 삭제
		CookieUtil.deleteAllCookie(request, response, cookieDomain);

		return modelAndView;		
	}
	
	/**
	 * 비밀번호 찾기 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/passwdSearch.kt")
	public ModelAndView passwdSearch(HttpServletRequest request) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();	
		
		//로그인 상태
		if(AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/main.kt");
			return modelAndView;
		}	
		
		modelAndView.setViewName("pc/user/passwdSearch");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/user/passwdSearchComplete.kt")
	public ModelAndView passwdSearchComplete(HttpServletRequest request) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();	
		
		//로그인 상태
		if(AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/main.kt");
			return modelAndView;
		}
		
		modelAndView.setViewName("pc/user/passwdSearchComplete");
		
		return modelAndView;
		
	}
	
	/**
	 * 회원가입 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/regist.kt")
	public ModelAndView regist(HttpServletRequest request) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();	
		
		//로그인 상태
		if(AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/main.kt");
			return modelAndView;
		}
		
		modelAndView.setViewName("pc/user/regist");
		
		return modelAndView;
		
	}
	
	/**
	 * 회원가입 완료 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/registComplete.kt")
	public ModelAndView registComplete(HttpServletRequest request) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();	
		
		//로그인 상태
		if(AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}
		
		modelAndView.setViewName("pc/user/registComplete");
		
		return modelAndView;
		
	}
	
	/**
	 * 인증이 완료되지 않았을때 처리 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/loginNotAuth.kt")
	public ModelAndView loginNotAuth(HttpServletRequest request) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();	
		
		modelAndView.setViewName("pc/user/loginNotAuth");
		
		return modelAndView;
		
	}
	
	/**
	 * 회원탈퇴 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/withdrawInfo.kt")
	public ModelAndView withdrawInfo(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 상태
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}
		
		modelAndView.setViewName("pc/user/withdrawInfo");
		
		return modelAndView;
		
	}
	
	/**
	 * 회원탈퇴 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/withdraw.kt")
	public ModelAndView withdraw(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();	
		
		//로그인 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}
		
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		
		modelAndView.addObject("user", user);
		
		modelAndView.setViewName("pc/user/withdraw");
		
		return modelAndView;
		
	}
	
	/**
	 * 회원탈퇴 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/withdrawProc.kt")
	public ModelAndView withdrawProc(@RequestParam(value="passwd", required=true) String passwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//로그인 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}
		
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		
		
		String trimPasswd = StringUtil.changeNullToEmpty(passwd); //trim() 처리한 비밀번호		
		
		//해당 이메일 ID로 DB에서 회원정보를 조회한다.
		UserDomain userDomain = userServiceIface.getUserInfoByEmail(user.getEmail());
		
		EncryptUtil encryptUtil = new EncryptUtil();
		String encPasswd = encryptUtil.encryptSHA512(trimPasswd);
		
		if(encPasswd.equals(userDomain.getPasswd())) {			
			user.setPasswd(encPasswd);
			
			userServiceIface.withdraw(user);
		}else{
			//비밀번호가 맞지 않을경우
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.mismatch.passwd"));
			
			return modelAndView;
		}
		
		//로그아웃 처리
		String cookieName = MessageUtil.getSystemMessage("system.cookie.name");      //쿠키 변수명
		String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
		
		CookieUtil.deleteCookie(response, "kt_userid");		

		CookieUtil.deleteCookie(response, cookieName, cookieDomain);		
		CookieUtil.deleteCookie(response, "u_check_no", cookieDomain);
		CookieUtil.deleteCookie(response, "u_nickname", cookieDomain);
		CookieUtil.deleteCookie(response, "u_nc_seq", cookieDomain);
		CookieUtil.deleteCookie(response, "u_nc_url", cookieDomain);
		CookieUtil.deleteCookie(response, "u_mnc_url", cookieDomain);
		CookieUtil.deleteCookie(response, "u_nc2_seq", cookieDomain);
		CookieUtil.deleteCookie(response, "u_nc2_url", cookieDomain);
		CookieUtil.deleteCookie(response, "u_mnc2_url", cookieDomain);
		CookieUtil.deleteCookie(response, "u_olleh_id", cookieDomain);
		CookieUtil.deleteCookie(response, "u_olleh_ctype", cookieDomain);
		CookieUtil.deleteCookie(response, "u_olleh_ctn", cookieDomain);
		CookieUtil.deleteCookie(response, "u_olleh_age", cookieDomain);
		CookieUtil.deleteCookie(response, "u_agr_pop", cookieDomain);
		
		return modelAndView;
		
	}
	
	/**
	 * 회원탈퇴 완료화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/withdrawComplete.kt")
	public ModelAndView withdrawComplete(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();	
		
		//로그인 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}
		
		modelAndView.setViewName("pc/user/withdrawComplete");
		
		return modelAndView;
		
	}
	
	/**
	 * 올렛 ID 계정 약관 동의 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = {"/user/ollehUserAgreement.kt", "/user/webtoonUserAgreement.kt"})
	public ModelAndView ollehUserAgreement(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String u_check_no = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_check_no"), "");			
		
		//약관 미동의 올레회원 체크
		if("3".equals(u_check_no) || "5".equals(u_check_no)){
			modelAndView.setViewName("pc/user/ollehUserAgreement");			
		}else{
			modelAndView.setViewName("redirect:/main.kt");
		}
		
		return modelAndView;		
	}
	
}