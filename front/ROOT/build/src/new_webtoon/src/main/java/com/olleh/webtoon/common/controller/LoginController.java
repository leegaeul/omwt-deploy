/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : LoginController.java
 * DESCRIPTION    : 로그인 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-16      init
 *****************************************************************************/

package com.olleh.webtoon.common.controller;

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
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.EncryptUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
public class LoginController {
			
	protected static Log logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	UserServiceIface userServiceIface;
		
	/**
	 * ID, Password를 받아 로그인 처리하는 RequestMapping 메소드
	 * 클라이언트와 JSON 규격으로 통신하기 위해(에러가 난 경우도) 
	 * 파라미터를 필수로 처리하지 않음. 
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/login.kt")
	public ModelAndView login(@RequestParam(value="email", required=false) String email, 
			                  @RequestParam(value="passwd", required=false) String passwd, 
			                  @RequestParam(value="loginSave", required=false) String loginSave, 
			                  @RequestParam(value="captcha", required=false) String captcha,
			                  HttpServletRequest request, 
			                  HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView();
		String trimEmail = StringUtil.changeNullToEmpty(email);   //trim() 처리한 사용자ID
		String trimPasswd = StringUtil.changeNullToEmpty(passwd); //trim() 처리한 비밀번호
		String trimCaptcha = StringUtil.changeNullToEmpty(captcha); //trim() 처리한 비밀번호
		String trimLoginSave = StringUtil.changeNullToEmpty(loginSave); //trim() 로그인 유지 여부
		EncryptUtil encryptUtil = new EncryptUtil();
		
		//JsonView를 View로 지정한다.
		modelAndView.setViewName("mappingJacksonJsonView");
		
		try {
			
			//1. 사용자 ID 파라미터가 들어왔는지 체크한다.
			if(trimEmail.length() == 0) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.none.userid.parameter"));
				
				return modelAndView;
			}
			
			//2. 비밀번호 파라미터가 들어왔는지 체크한다.
			if(trimPasswd.length() == 0) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.none.passwd.parameter"));
				
				return modelAndView;
			}
			
			//3. 해당 이메일 ID로 DB에서 회원정보를 조회한다.
			UserDomain userDomain = userServiceIface.getUserInfoByEmail(trimEmail);
			
			//4. 해당 ID 존재여부와 비밀번호 일치여부를 확인하여 에러처리하거나 인증 정보 쿠키를 생성한다.
			if(userDomain == null) {
				
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.mismatch.all"));
				
				return modelAndView;
				
			}
			
			//5. 탈퇴한 회원을 체크한다.
			if("cancel".equals(userDomain.getUserstatus())){
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.cancel.userid"));
				
				return modelAndView;
			}
			
			// 대기, 정지 회원 체크
			if(userDomain.getUserstatus().equals("standby") || userDomain.getUserstatus().equals("stop")) {
				
				modelAndView.addObject("erroryn", "Y");
				
				if(userDomain.getUserstatus().equals("standby")) { //이메일 인증이 완료되지 않은 경우

					//현재 날짜시간
					String now = DateUtil.getNowDate(1);
					long nowDate = Long.parseLong(now);
					
					//등록 날짜시간
					String regdt = userDomain.getRegdt();					
					long regDate = Long.parseLong(regdt);
					
					//인증시간 3시간 초과 여부
					if(nowDate - regDate > 30000){
						modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.timeover.certification"));
					}else{
						modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.not.certification"));
						modelAndView.addObject("errormsgid", "message.error.not.certification");
						modelAndView.addObject("errormsgtype", "layerpop");
						modelAndView.addObject("certificationkey", userDomain.getCertificationkey());
						modelAndView.addObject("nickname", userDomain.getNickname());
					}
						
					
				} else { //정지 처리된 회원인 경우
					modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.mismatch.all"));
				}
				
				return modelAndView;
			} 
			
			//6. 로그인 실패 횟수 5이상일 경우 캡차코드 체크
			int failHisCnt = userServiceIface.getFailHisCnt(trimEmail);
			if(failHisCnt >= 5 && trimCaptcha.length() > 0) {
				
				// 쿠키와 파라메터 암호화된 캡차코드 비교
				if(trimCaptcha.length() <= 0){					
					modelAndView.addObject("erroryn", "Y");
					modelAndView.addObject("errormsg", "자동입력방지문자를 빠뜨리셨네요.");	
					
					return modelAndView;
				}
				
				// 캡차코드 암호화
		        String paramEncryptCaptcha = encryptUtil.encryptSHA512(trimCaptcha);
				
		        // 쿠키에 저장된 캡차코드
				String cookieEncryptCaptcha = CookieUtil.getCookie(request, "u_cc_as");
				
				// 쿠키와 파라메터 암호화된 캡차코드 비교
				if(!cookieEncryptCaptcha.equals(paramEncryptCaptcha)){					
					modelAndView.addObject("erroryn", "Y");
					modelAndView.addObject("errormsg", "자동입력 방지 문자가 틀렸어요~");
					modelAndView.addObject("ccreload", "Y"); //캡차코드 새로고침
					
					return modelAndView;
				}
			}
			

			//6. 5회이상 비밀번호 오류 체크 - 20141006
			//int failHisCnt = userServiceIface.getFailHisCnt(trimEmail);
			
			/*if(failHisCnt >= 5){
				
				//최종 로그인 실패 날짜 시간을 체크 한다.(10분 초과 여부)
				String tenOveryn = userServiceIface.getFailDtOveryn(trimEmail);
				
				//10분 이내일 경우
				if("N".equals(tenOveryn)){
					
					modelAndView.addObject("erroryn", "Y");
					modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.mismatch.over"));
					
					return modelAndView;
				}
				//10분 초과일 경우 실패 횟수 실패 시간을 초기화 한다.
				else{
					
					userServiceIface.initLoginFailInfo(trimEmail);
				}
			}*/
				
			if(userDomain.getUserstatus().equals("use") && !encryptUtil.encryptSHA512(trimPasswd).equals(userDomain.getPasswd())) {
				
				//로그인 실패 횟수, 실패날짜를 update한다.
				userServiceIface.modifyLoginFailInfo(trimEmail);
				
				//로그인 실패 횟수 다시 가져오기
				failHisCnt = userServiceIface.getFailHisCnt(trimEmail);
				
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("failCnt", failHisCnt);
				
				//로그인 실패 10회 이상일경우
				if(failHisCnt >= 10 && trimCaptcha.length() > 0) {
					modelAndView.addObject("errormsg", "에구구.. 로그인에 실패하셨네요...\n혹시~ 비밀번호를 잊어버리셨다면\n임시 비밀번호를 발급 받은 후 로그인 해 보세요~");
				}else{
					modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.mismatch.passwd"));
				}

				String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
				String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인	
				
				
				//로그인 실패 횟수가 5번 이상일때
				if(failHisCnt >= 5){	
					AesCipherUtil aesCipherUtil = new AesCipherUtil();
					String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
					String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터

					//쿠키에 넣을 정보를 조합하여 암호화한다.
					String encryptEmail = aesCipherUtil.encrypt(key, initialVector, userDomain.getEmail().getBytes());
					
					//캡차코드 로그인 화면 체크 값 셋팅
					CookieUtil.setCookie(response, "u_cc_fc", "1", -1, cookiePath, cookieDomain);
					CookieUtil.setCookie(response, "u_cc_em", encryptEmail, -1, cookiePath, cookieDomain);
				}else{
					//캡차코드 로그인 유무 체크 쿠키 삭제
					CookieUtil.deleteCookie(response, "u_cc_fc", cookieDomain);
					CookieUtil.deleteCookie(response, "u_cc_as", cookieDomain);

				}
					
				
				return modelAndView;
				
			} else { //비밀번호가 맞을 경우
				
				
				modelAndView.addObject("erroryn", "N");
				
				AesCipherUtil aesCipherUtil = new AesCipherUtil();
				String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
				String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
				String cookieName = MessageUtil.getSystemMessage("system.cookie.name");      //쿠키 변수명
				String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
				String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
				String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
				
				//쿠키에 넣을 정보를 조합하여 암호화한다.
				String cookieValue = aesCipherUtil.encrypt(key, initialVector, userDomain.getCookieValue().getBytes());

				int intCookieAge = Integer.parseInt(cookieAge);
				
				//자동로그아웃 유무
				String autoLogout = "N";
				
				//로그인 유지기능
				if(trimLoginSave.equals("Y")){
					intCookieAge = 14 * 86400; 			//365일
				}else{
					//모바일일 경우 로그인 유지를 안할시 10분뒤 자동 로그아웃 처리
					if(RequestUtil.isMobile(request)){
						intCookieAge = 60*10; 			//10분
						autoLogout = "Y";
					}
				}
				CookieUtil.setCookie(response, "u_at_lo", autoLogout, 14 * 86400, cookiePath, cookieDomain);
				
				//쿠키를 생성한다.
				CookieUtil.setCookie(response, cookieName, cookieValue, intCookieAge, cookiePath, cookieDomain);
				

				//로그인 성공시 캡차코드 로그인 유무 체크 쿠키 삭제
				CookieUtil.deleteCookie(response, "u_cc_fc", cookieDomain);
				CookieUtil.deleteCookie(response, "u_cc_as", cookieDomain);

				// 회원 구분(1: 웹툰회원, 2: 작가 , 3: 약관 미동의 올레회원, 4: 약관 동의 올레회원, 5: 약관 미동의 웹툰회원  )
				if("Y".equals(userDomain.getTermsyn()))
					CookieUtil.setCookie(response, "u_check_no", "1", intCookieAge, cookiePath, cookieDomain);
				else if("N".equals(userDomain.getTermsyn()))
					CookieUtil.setCookie(response, "u_check_no", "5", intCookieAge, cookiePath, cookieDomain);
				
				// 작가 닉네임
				CookieUtil.setCookie(response, "u_nickname", userDomain.getNickname(), intCookieAge, cookiePath, cookieDomain);
				
				// 네임콘 URL, 모바일네임콘 번호, 모바일네임콘 URL
				CookieUtil.setCookie(response, "u_nc_seq", String.valueOf(userDomain.getNameconseq()), intCookieAge, cookiePath, cookieDomain);
				CookieUtil.setCookie(response, "u_nc_url", userDomain.getNameconurl(), intCookieAge, cookiePath, cookieDomain);
				CookieUtil.setCookie(response, "u_mnc_url", userDomain.getMnameconurl(), intCookieAge, cookiePath, cookieDomain);
				CookieUtil.setCookie(response, "u_no", String.valueOf(userDomain.getUserseq()), intCookieAge, cookiePath, cookieDomain);
				
				//토큰 생성
				String webtoon_token = encryptUtil.encryptSHA512("open/"+trimEmail + "/" + trimPasswd + "/" +  DateUtil.getNowDate(1));
				CookieUtil.setCookie(response, "u_enctoken", webtoon_token, intCookieAge, cookiePath, cookieDomain);
				
				//최종 로그인 시간을 업데이트한다.
				UserDomain loginDomain = new UserDomain();
				loginDomain.setUserseq(userDomain.getUserseq());
				loginDomain.setLastlogindt(DateUtil.getNowDate(1));
				loginDomain.setLoginip(RequestUtil.getRequestIp(request));
				userServiceIface.updateLastlogindt(loginDomain);
				
				//모바일 앱에서 로그인시 Device정보에 로그인 정보를 update
				 String uuid = RequestUtil.getUuid(request);
				 if(uuid != null){
					 userDomain.setUuid(uuid);
					 userDomain.setIdfg("open");
					 userDomain.setLoginyn("Y");
					 
					 userServiceIface.updateEmailByUuid(userDomain);					 
				 }
				
				//블랙리스트 회원인 경우
				if(userDomain.getUserstatus().equals("stop")) { 
					modelAndView.addObject("erroryn", "Y");
					modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.account.stop"));
					modelAndView.addObject("errormsgid", "message.error.account.stop");
					modelAndView.addObject("errormsgtype", "layerpop");
					modelAndView.addObject("nickname", userDomain.getNickname());
				}
				
				//비밀번호 변경기간이 3개월 이상이면 변경팝업 노출 위한 쿠키
				String pwchange_monthdiff = userDomain.getPwchange_monthdiff();
				if(userDomain != null && pwchange_monthdiff != null){
					int monthdiff = new Integer(pwchange_monthdiff);
					
					if(monthdiff >= 3){
						CookieUtil.setCookie(response, "u_pw_chg", "Y", -1, cookiePath, cookieDomain);
					}
				}
				
				//비밀번호 8자리로 변경하기 팝업 노출을 위한 쿠키 - 20150303
				String u_pw_leg_chg = com.olleh.webtoon.common.util.StringUtil.defaultStr(
						com.olleh.webtoon.common.util.CookieUtil.getCookie(request, "u_pw_leg_chg"), "");
				
				if("".equals(u_pw_leg_chg)){
					CookieUtil.setCookie(response, "u_pw_leg_chg", "Y", -1, cookiePath, cookieDomain);
				}
			}
			
		} catch(Exception e) {
			
			logger.error(e);
			
			//예외가 발생하면 기존 데이터를 clear하고 에러여부를 세팅한다.
			modelAndView.clear();
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.server"));
			modelAndView.setViewName("mappingJacksonJsonView");
			
		}
		
		return modelAndView;
		
	}
	
}