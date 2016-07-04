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

package com.olleh.webtoon.common.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AesCipherUtil;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.EncryptUtil;
import com.olleh.webtoon.common.util.MailSendUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RandomUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.SpamWordUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller("CommonUserController")
public class UserController {
			
	protected static Log logger = LogFactory.getLog(UserController.class);
	
	@Autowired
	UserServiceIface userServiceIface;

	
	/**
	 * 비밀번호 찾기 완료 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/passwdSearchProc.kt")
	public ModelAndView passwdSearchProc(UserDomain user, HttpServletRequest request,  HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView();
		String trimEmail = StringUtil.changeNullToEmpty(user.getEmail());   //trim() 처리한 사용자ID
		String trimNickname= StringUtil.changeNullToEmpty(user.getNickname()); //trim() 처리한 닉네임
		
		//JsonView를 View로 지정한다.
		modelAndView.setViewName("mappingJacksonJsonView");
		
		try {
			
			//1. 사용자 ID 파라미터가 들어왔는지 체크한다.
			if(trimEmail.length() == 0) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.none.userid.parameter"));
				
				return modelAndView;
			}
			
			//2. 닉네임 파라미터가 들어왔는지 체크한다.
			if(trimNickname.length() == 0) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.none.passwd.parameter"));
				
				return modelAndView;
			}
			
			//3. 해당 이메일 ID로 DB에서 회원정보를 조회한다.
			UserDomain userDomain = userServiceIface.getUserInfoByEmail(trimEmail);
			
			//4. 해당 ID, 닉네임 존재여부 확인
			if(userDomain == null) {
				
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.none.user"));
				
				return modelAndView;
				
			}else if(!userDomain.getNickname().equals(user.getNickname())) {
				
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.mismatch.serchpass"));
				
				return modelAndView;
				
			}else if(userDomain.getUserstatus().equals("cancel")) {
				
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.none.user"));
				
				return modelAndView;
				
			} else {
				
				modelAndView.addObject("erroryn", "N");
				
				//임시비밀번호 생성
				String tmpPasswd = RandomUtil.getPasswd();

				// 이메일 제목
				String mailSubject = MessageUtil.getMessage("message.email.passwdsearch.subject");				
				
				// 이메일 템플릿 가져오기
				String templatePath = request.getServletContext().getRealPath("/")+MessageUtil.getMessage("system.email.template.passwdsearch");
				BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(templatePath),"UTF8"));
				String line;
				StringBuffer emailTemplate = new StringBuffer();
				
				while ((line = bf.readLine()) != null) {
					emailTemplate.append(line);
				}
				bf.close();
				
				// 이메일 콘텐츠 : 이메일 템플릿에 임시비밀번호 삽입
				String mailContent = emailTemplate.toString().replaceAll("#change_passwd#", tmpPasswd).replaceAll("#subject#", mailSubject);
				mailContent = mailContent.replaceAll("#domain#", MessageUtil.getMessage("system.pc.domain"));
				
				//비밀번호 이메일 발송
				MailSendUtil.sendEmailResult(mailSubject, mailContent, trimEmail);	
				
				EncryptUtil encryptUtil = new EncryptUtil();
				String encTmpPasswd = encryptUtil.encryptSHA512(tmpPasswd);	        
				
				user.setPasswd(encTmpPasswd);
				userServiceIface.passwdUpdate(user);
				
				//임시 비번 발송 성공시 캡차코드 로그인 유무 체크 쿠키 삭제
				String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인	
				CookieUtil.deleteCookie(response, "u_cc_fc", cookieDomain);
				CookieUtil.deleteCookie(response, "u_cc_as", cookieDomain);
				
			}			
			
		} catch(Exception e) {
			
			logger.error(e);
			
			//예외가 발생하면 기존 데이터를 clear하고 에러여부를 세팅한다.
			modelAndView.clear();
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.server"));
			modelAndView.setViewName("mappingJacksonJsonView");
			
		}

		modelAndView.addObject("userDomain", null);
		
		return modelAndView;		
	}
	
	/**
	 * 회원가입 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/nicknameCheck.kt")
	public ModelAndView nicknameCheck(@RequestParam(value="nickname", required=true) String nickname) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		String trimNickname= StringUtil.changeNullToEmpty(nickname).trim(); //trim() 처리한 닉네임
		
//		String equalSpamword = SpamWordUtil.isEqual(trimNickname);
		String containSpamword = SpamWordUtil.isContain(trimNickname);
		
//		if(equalSpamword != null){	
//			modelAndView.addObject("useyn", "X");	
//			modelAndView.addObject("message", "'"+equalSpamword+"'" + MessageUtil.getMessage("message.error.spamword.equal.nickname"));	
//		}else if(containSpamword != null){	
		if(containSpamword != null){
			modelAndView.addObject("useyn", "X");	
			modelAndView.addObject("message", "'"+containSpamword+"'" + MessageUtil.getMessage("message.error.spamword.contain.nickname"));	
		}else{
			UserDomain tmpUser = new UserDomain();
			tmpUser.setNickname(nickname);
			int checkCount = userServiceIface.userCheckCount(tmpUser);
			
			if(checkCount > 0)
				modelAndView.addObject("useyn", "N");
			else 
				modelAndView.addObject("useyn", "Y");
		}
		
		return modelAndView;
		
	}
	
	/**
	 * 회원가입 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/emailCheck.kt")
	public ModelAndView emailCheck(@RequestParam(value="email", required=true) String email) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		UserDomain tmpUser = new UserDomain();
		tmpUser.setEmail(email);
		
		//1.탈퇴 아이디 체크
		int cancelCnt = userServiceIface.cancelUserCheckCount(tmpUser);
		if(cancelCnt > 0){
			modelAndView.addObject("cancelyn", "Y");
			return modelAndView;
		}
		
		//2.중복 아이디 체크
		int checkCount = userServiceIface.userCheckCount(tmpUser);
		if(checkCount > 0){
			modelAndView.addObject("duplicateyn", "Y");
			return modelAndView;
		}
		
		modelAndView.addObject("cancelyn", "N");
		modelAndView.addObject("duplicateyn", "N");
		
		return modelAndView;
		
	}
	
	/**
	 * 비밀번호 찾기 완료 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/registProc.kt")
	public ModelAndView registProc(UserDomain user, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		// 모바일여부
		boolean isMobile = com.olleh.webtoon.common.util.RequestUtil.isMobile(request);
		
		if(isMobile){
			String plusInfo = com.olleh.webtoon.common.util.RequestUtil.getParameter(request, "plusInfo", true);
			System.out.println(plusInfo);
			
			try{
				//암복호화 모듈
				com.olleh.webtoon.common.util.AesCipherUtil aesCipherUtil = new com.olleh.webtoon.common.util.AesCipherUtil();		
				String key = com.olleh.webtoon.common.util.MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
				String initialVector = com.olleh.webtoon.common.util.MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
				
				//파라메터 암호화 처리
				String decriptParamStr = aesCipherUtil.decryptNoEncoding(key, initialVector, plusInfo.getBytes());
				
				System.out.println(decriptParamStr);
				
				String paramStr[] = decriptParamStr.split("\\|");
				
				String nickname = java.net.URLDecoder.decode(paramStr[2], "UTF-8");
				
				user.setBirthday(paramStr[0]);
				user.setEmail(paramStr[1]);
				user.setNickname(nickname);
				user.setUsetermsyn(paramStr[3]);
				user.setMinorityyn(paramStr[4]);
				user.setPasswd(paramStr[5]);
				user.setGender(paramStr[6]);
				user.setCaptcha(paramStr[7]);
				
			}catch(Exception e){
				logger.error(e);
				
				//예외가 발생하면 기존 데이터를 clear하고 에러여부를 세팅한다.
				modelAndView.clear();
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.server"));
				modelAndView.setViewName("mappingJacksonJsonView");
			}
		}
		
		
		String trimEmail = StringUtil.changeNullToEmpty(user.getEmail());   //trim() 처리한 사용자ID
		String trimNickname= StringUtil.changeNullToEmpty(user.getNickname()); //trim() 처리한 닉네임
		String trimPasswd= StringUtil.changeNullToEmpty(user.getPasswd()); //trim() 처리한 비밀번호
		
		//JsonView를 View로 지정한다.
		modelAndView.setViewName("mappingJacksonJsonView");
		
		try {
			
			//1. 사용자 ID 파라미터가 들어왔는지 체크한다.
			if(trimEmail.length() == 0) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.none.userid.parameter"));
				
				modelAndView.addObject("userDomain", null);		
				
				return modelAndView;
			}
			
			//2. 닉네임 파라미터가 들어왔는지 체크한다.
			if(trimNickname.length() == 0) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.none.nickname.parameter"));
				
				modelAndView.addObject("userDomain", null);		
				
				return modelAndView;
			}
			
			//2-1. 닉네임 파라미터 금칙어와 같은지 체크한다.
//			String equalSpamword = SpamWordUtil.isEqual(trimNickname);
//			if(equalSpamword != null){
//				modelAndView.addObject("erroryn", "Y");
//				modelAndView.addObject("errormsg", "'"+equalSpamword+"'" + MessageUtil.getMessage("message.error.spamword.equal.nickname"));
//				
//				modelAndView.addObject("userDomain", null);		
//				
//				return modelAndView;
//			}
			
			//2-2. 닉네임 파라미터 금칙어를 포함하고 있는지 체크한다.
			String containSpamword = SpamWordUtil.isContain(trimNickname);
			if(containSpamword != null){
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", "'"+containSpamword+"'" + MessageUtil.getMessage("message.error.spamword.contain.nickname"));
				
				modelAndView.addObject("userDomain", null);		
				
				return modelAndView;
			}
			
			//3. 비밀번호 파라미터가 들어왔는지 체크한다.
			if(trimPasswd.length() == 0) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.none.passwd.parameter"));
				
				modelAndView.addObject("userDomain", null);		
				
				return modelAndView;
			}
			
			EncryptUtil encryptUtil = new EncryptUtil();
			String encCaptcha = encryptUtil.encryptSHA512(user.getCaptcha());
			
	        // 쿠키에 저장된 캡차코드
			String cookieEncryptCaptcha = CookieUtil.getCookie(request, "u_cc_as");
			
			// 쿠키와 파라메터 암호화된 캡차코드 비교
			if(!cookieEncryptCaptcha.equals(encCaptcha)){
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", "자동 입력 방지 문자 입력이 틀렸어요~");
				modelAndView.addObject("userDomain", null);		
				modelAndView.addObject("ccreload", "Y"); //캡차코드 새로고침
				
				return modelAndView;
			}
			
			//3. 해당 이메일 ID로 DB에서 회원정보가 있는지 체크.
			UserDomain tmpUser = new UserDomain();
			tmpUser.setEmail(user.getEmail());
			int checkCount = userServiceIface.userCheckCount(tmpUser);
			
			//4. 해당 ID 존재여부 확인
			if(checkCount > 0) {
				
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.none.userid"));
				
				modelAndView.addObject("userDomain", null);		
				
				return modelAndView;
				
			} else {
				
				modelAndView.addObject("erroryn", "N");
				
				//등록일
				String regdt = DateUtil.getNowDate(1);
				
				//3시간 후
			    Calendar calendar = Calendar.getInstance();
			    String pattern = "yyyy.MM.dd HH:mm";
			    calendar.add(Calendar.HOUR_OF_DAY, 3);			    
			    SimpleDateFormat format = new SimpleDateFormat(pattern);
		        String after3Hour = format.format(calendar.getTime());


				
				if(user.getTermsyn() == null || "".equals(user.getTermsyn())) user.setTermsyn("N");				
				user.setAgreementdt(regdt);
				user.setRegdt(regdt);
				
				//비밀번호 암호
				user.setPasswd(encryptUtil.encryptSHA512(user.getPasswd()));
				
				//인증키 발행
				String authkey = trimEmail+":"+trimNickname+":"+regdt;
				authkey = encryptUtil.encryptSHA512(authkey);
				user.setCertificationkey(authkey);			

				
				// 이메일 제목
				String mailSubject = MessageUtil.getMessage("message.email.certification.subject");	
				
				// 이메일 템플릿 가져오기
				String templatePath = request.getServletContext().getRealPath("/")+MessageUtil.getMessage("system.email.template.certification");
				BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(templatePath),"UTF8"));
				String line;
				StringBuffer emailTemplate = new StringBuffer();
				
				while ((line = bf.readLine()) != null) {
					emailTemplate.append(line);
				}
				bf.close();
				
				// 인증 콘텐츠 : 인증 템플릿에 인증키 삽입
				String mailContent = emailTemplate.toString().replaceAll("#change_authkey#", authkey).replaceAll("#subject#", mailSubject).replaceAll("#auth_time#", after3Hour);
				mailContent = mailContent.replaceAll("#domain#", MessageUtil.getMessage("system.pc.domain"));
				
				//비밀번호 이메일 발송
				MailSendUtil.sendEmailResult(mailSubject, mailContent, trimEmail);	
				
				userServiceIface.userRegist(user);
		
				modelAndView.addObject("certificationkey", authkey);
			}
			
		} catch(Exception e) {
			
			logger.error(e);
			
			//예외가 발생하면 기존 데이터를 clear하고 에러여부를 세팅한다.
			modelAndView.clear();
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.server"));
			modelAndView.setViewName("mappingJacksonJsonView");
			
		}

		modelAndView.addObject("userDomain", null);		
		
		return modelAndView;		
	}
	
	/**
	 * 비밀번호 찾기 완료 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/reCertificationProc.kt")
	public ModelAndView reCertification(UserDomain user, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		String trimEmail = StringUtil.changeNullToEmpty(user.getEmail());   //trim() 처리한 사용자ID
		String trimNickname= StringUtil.changeNullToEmpty(user.getNickname()); //trim() 처리한 닉네임
		String trimCertificationkey= StringUtil.changeNullToEmpty(user.getCertificationkey()); //trim() 처리한 닉네임
		
		//JsonView를 View로 지정한다.
		modelAndView.setViewName("mappingJacksonJsonView");
		
		try {
				
				modelAndView.addObject("erroryn", "N");
				
				//등록일
				String regdt = DateUtil.getNowDate(1);
				user.setRegdt(regdt);
				
				//3시간 후
			    Calendar calendar = Calendar.getInstance();
			    String pattern = "yyyy.MM.dd HH:mm";
			    calendar.add(Calendar.HOUR_OF_DAY, 3);			    
			    SimpleDateFormat format = new SimpleDateFormat(pattern);
		        String after3Hour = format.format(calendar.getTime());
				
				EncryptUtil encryptUtil = new EncryptUtil();				
				//인증키 재발행
				String authkey = trimEmail+":"+trimNickname+":"+regdt;
				authkey = encryptUtil.encryptSHA512(authkey);
				user.setRecertificationkey(authkey);
				
				//현재 인증키
				user.setCertificationkey(trimCertificationkey);
				
				// 이메일 제목
				String mailSubject = MessageUtil.getMessage("message.email.certification.subject");	
				
				// 이메일 템플릿 가져오기
				String templatePath = request.getServletContext().getRealPath("/")+MessageUtil.getMessage("system.email.template.certification");				
				BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(templatePath),"UTF8"));
				String line;
				StringBuffer emailTemplate = new StringBuffer();
				
				while ((line = bf.readLine()) != null) {
					emailTemplate.append(line);
				}
				bf.close();
				
				// 인증 콘텐츠 : 인증 템플릿에 인증키 삽입
				String mailContent = emailTemplate.toString().replaceAll("#change_authkey#", authkey).replaceAll("#subject#", mailSubject).replaceAll("#auth_time#", after3Hour);
				mailContent = mailContent.replaceAll("#domain#", MessageUtil.getMessage("system.pc.domain"));
				
				//비밀번호 이메일 발송
				MailSendUtil.sendEmailResult(mailSubject, mailContent, trimEmail);	
				
				userServiceIface.reCertification(user);

				modelAndView.addObject("certificationkey", authkey);
			
		} catch(Exception e) {
			
			logger.error(e);
			
			//예외가 발생하면 기존 데이터를 clear하고 에러여부를 세팅한다.
			modelAndView.clear();
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getMessage("message.error.server"));
			modelAndView.setViewName("mappingJacksonJsonView");
			
		}
		
		modelAndView.addObject("userDomain", null);
		
		return modelAndView;		
	}
	
	/**
	 * 회원가입 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/passwdCheck.kt")
	public ModelAndView passwdCheck(@RequestParam(value="passwd", required=true) String passwd, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//로그인 정보가 없을경우.
		if(!AuthUtil.isLogin(request)){
		
			modelAndView.addObject("useyn", "N");		
			return modelAndView;
		}
		
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		
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
	 * 새로운 비밀번호 유효성 검사 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/newPasswdCheck.kt")
	public ModelAndView newPasswdCheck(@RequestParam(value="passwd", required=true) String passwd, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//로그인 정보가 없을경우.
		if(!AuthUtil.isLogin(request)){
		
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "로그인 후 이용해주세요.");
			return modelAndView;
		}
		
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		
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
	 * 회원 비밀번호 변경
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/passwdChangeProc.kt")
	public ModelAndView passwdChangeProc(UserDomain userDomain, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("mappingJacksonJsonView");
		
		modelAndView.addObject("erroryn", "Y");		
		
		//로그인 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/main.kt");
			return modelAndView;
		}	
		
		EncryptUtil encryptUtil = new EncryptUtil();
		String encPasswd = encryptUtil.encryptSHA512(userDomain.getPasswd());
		String encNewpasswd = encryptUtil.encryptSHA512(userDomain.getNewpasswd());
		String encCaptcha = encryptUtil.encryptSHA512(userDomain.getCaptcha());
		
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
		
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		
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
	 * 회원 네임콘 변경
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/nameconChangeProc.kt")
	public ModelAndView nameconChangeProc(UserDomain userDomain, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("mappingJacksonJsonView");
		
		modelAndView.addObject("erroryn", "Y");		
		
		//로그인 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/main.kt");
			return modelAndView;
		}	
		
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		
		userDomain.setEmail(user.getEmail());
		userDomain.setIdfg(user.getIdfg());
		
		//네임콘 변경
		userServiceIface.defualtNamecon(userDomain);
		
		String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
		String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
		String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
		int intCookieAge = Integer.parseInt(cookieAge);
		
		//쿠키정보 변경		
		CookieUtil.setCookie(response, "u_nc_seq", String.valueOf(userDomain.getNameconseq()), intCookieAge, cookiePath, cookieDomain);
		CookieUtil.setCookie(response, "u_nc_url", userDomain.getNameconurl(), intCookieAge, cookiePath, cookieDomain);
		CookieUtil.setCookie(response, "u_mnc_url", userDomain.getMnameconurl(), intCookieAge, cookiePath, cookieDomain);
		
		
		//이전 페이지 url 가져오기
		String returnUrl = StringUtil.defaultStr(CookieUtil.getCookie(request, "nameconChangeReturnUrl"), "");
		modelAndView.addObject( "returnUrl", returnUrl );

		modelAndView.addObject( "userDomain", null );
		
		return modelAndView;
		
	}
	
	/**
	 * 비밀번호 찾기 완료 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/isAgreementProc.kt")
	public ModelAndView isAgreement(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//JsonView를 View로 지정한다.
		modelAndView.setViewName("mappingJacksonJsonView");
		modelAndView.addObject("isAgreement", "Y");
		
		String u_check_no = com.olleh.webtoon.common.util.StringUtil.defaultStr(
				com.olleh.webtoon.common.util.CookieUtil.getCookie(request, "u_check_no"), "");
		
		String u_agr_pop = com.olleh.webtoon.common.util.StringUtil.defaultStr(
				com.olleh.webtoon.common.util.CookieUtil.getCookie(request, "u_agr_pop"), "N");
		
		
		if("N".equals(u_agr_pop) && "3".equals(u_check_no)){
			modelAndView.addObject("isAgreement", "N");
		}
		
		return modelAndView;		
	}
	
	/**
	 * 회원 네임콘 변경
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/ollehUserAgreementProc.kt")
	public ModelAndView ollehUserAgreementProc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("mappingJacksonJsonView");
		
		modelAndView.addObject("erroryn", "Y");		
		
		//필수 약관동의 여부
		String isTerms = RequestUtil.getParameter(request, "terms", true);
		//선택 약관동의 여부
		String isUseterms = RequestUtil.getParameter(request, "useterms", true);
		
		//회원구분
		String u_check_no = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_check_no"), "");	
		//올레아이디
		String u_olleh_id = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_olleh_id"), "");	
		
		
		//약관 미동의 올레회원 체크
		if("3".equals(u_check_no) && !"".equals(u_olleh_id) && "Y".equals(isTerms)){
			AesCipherUtil aesCipherUtil = new AesCipherUtil();
			String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
			String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
			
			String olleh_id = aesCipherUtil.decrypt(key, initialVector, u_olleh_id.getBytes());
			
			OllehUserDomain ollehUser = new OllehUserDomain();
			
			ollehUser.setOllehid(olleh_id);
			ollehUser.setTermsyn(isTerms);
			ollehUser.setUsetermsyn(isUseterms);
			
			
			userServiceIface.ollehUserAgreement(ollehUser);
			
			//약관 동의 올레회원(4)로 쿠키 값 변경
			String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
			String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
			String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
			int intCookieAge = Integer.parseInt(cookieAge);
			
			CookieUtil.setCookie(response, "u_check_no", "4", intCookieAge, cookiePath, cookieDomain);
			
			modelAndView.addObject("erroryn", "N");
		}else if("5".equals(u_check_no) && "Y".equals(isTerms)){
			
			UserDomain user = AuthUtil.getDecryptUserInfo(request);
			
			user.setTermsyn(isTerms);
			user.setUsetermsyn(isUseterms);
			
			userServiceIface.webtoonUserAgreement(user);
			
			//약관 동의 올레회원(1)로 쿠키 값 변경
			String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
			String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
			String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
			int intCookieAge = Integer.parseInt(cookieAge);
			
			CookieUtil.setCookie(response, "u_check_no", "1", intCookieAge, cookiePath, cookieDomain);
			
			modelAndView.addObject("erroryn", "N");
		}
		
		return modelAndView;
		
	}
	
	/**
	 * 회원가입 인증처리 완료 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = {"/user/authComplete.kt", "/m/user/authComplete.kt"})
	public ModelAndView authComplete(@RequestParam(value="auth", required=true) String auth, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 상태
		if(AuthUtil.isLogin(request)){
			if(RequestUtil.isMobile(request))
				modelAndView.setViewName("redirect:/m/main.kt");
			else
				modelAndView.setViewName("redirect:/main.kt");
			return modelAndView;
		}
		
		UserDomain user = new UserDomain();
		
		// 처리한 인증키	
		String trimCertificationkey= StringUtil.changeNullToEmpty(auth); //trim() 처리한 인증키	
		user.setCertificationkey(trimCertificationkey);
		
		//가입일
		String joindt = DateUtil.getNowDate(1);
		user.setJoindt(joindt);		
		
		//메인 인증 처리
		if(userServiceIface.certification(user) > 0){
			user = userServiceIface.getUserInfoByCertificationkey(trimCertificationkey);
			
			/*
			// 이메일 제목
			String mailSubject = MessageUtil.getMessage("message.email.join.subject");	
			
			// 이메일 템플릿 가져오기
			String templatePath = request.getServletContext().getRealPath("/")+MessageUtil.getMessage("system.email.template.join");
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(templatePath),"UTF8"));
			String line;
			StringBuffer emailTemplate = new StringBuffer();
			
			while ((line = bf.readLine()) != null) {
				emailTemplate.append(line);
			}
			bf.close();
			
			// 인증 콘텐츠 : 인증 템플릿에 인증키 삽입
			String mailContent = emailTemplate.toString().replaceAll("#subject#", mailSubject);
			mailContent = mailContent.replaceAll("#domain#", MessageUtil.getMessage("system.pc.domain"));
			
			//회원가입 완료 이메일 발송
			MailSendUtil.sendEmailResult(mailSubject, mailContent, user.getEmail());	
			*/
			
			modelAndView.addObject("nickname", user.getNickname());
			
			//메일인증 성공
			if(RequestUtil.isMobile(request))
				modelAndView.setViewName("mobile/user/authComplete");
			else 
				modelAndView.setViewName("pc/user/authComplete");
		}else{
			//메일인증 실패
			if(RequestUtil.isMobile(request))
				modelAndView.setViewName("mobile/user/authFail");
			else 
				modelAndView.setViewName("pc/user/authFail");
		}
		
		return modelAndView;		
	}
	
	@RequestMapping(value = "/user/myCertComplete.kt")
	public ModelAndView myCertComplete(@RequestParam(value="adultno", required=true) String adultno
			, @RequestParam(value="adultdt", required=true) String adultdt
			, @RequestParam(value="birthday", required=true) String birthday
			, HttpServletResponse response, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		// 로그인 상태가 아닌경우
		if (!AuthUtil.isLogin(request))
		{
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}
				
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		
		// 올레ID일 경우 본인인증 처리 제외
		// 필요시 추가 처리 필요.
		if("olleh".equals(user.getIdfg())){
			modelAndView.addObject("erroryn", "N");
			
			return modelAndView;
		}else{		
			user.setAdultyn("Y");
			user.setAdultdt(adultdt);
			user.setAdultno(adultno);
			user.setBirthday(birthday);
			
			AesCipherUtil aesCipherUtil = new AesCipherUtil();
			String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
			String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
			String cookieName = MessageUtil.getSystemMessage("system.cookie.name");      //쿠키 변수명
			String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
			String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
			String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
			
			//쿠키에 넣을 정보를 조합하여 암호화한다.
			String cookieValue = aesCipherUtil.encrypt(key, initialVector, user.getCookieValue().getBytes());
	
			int intCookieAge = Integer.parseInt(cookieAge);
			
			//쿠키를 생성한다.
			CookieUtil.setCookie(response, cookieName, cookieValue, intCookieAge, cookiePath, cookieDomain);
			
			if (0 < userServiceIface.adultUpdate(user))
				modelAndView.addObject("erroryn", "N");
			else
				modelAndView.addObject("erroryn", "Y");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/user/minorityCertComplete.kt")
	public ModelAndView minorityCertComplete(@RequestParam(value="minoritycertnum", required=true) String minoritycertnum
			, @RequestParam(value="minoritycertdt", required=true) String minoritycertdt
			, HttpServletResponse response, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		// 로그인 상태가 아닌경우
		if (!AuthUtil.isLogin(request))
		{
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}
				
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		user.setMinorityyn("Y");
		user.setMinoritycertdt(minoritycertdt);
		user.setMinoritycertnum(minoritycertnum);
		
		AesCipherUtil aesCipherUtil = new AesCipherUtil();
		String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
		String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
		String cookieName = MessageUtil.getSystemMessage("system.cookie.name");      //쿠키 변수명
		String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
		String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
		String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
		
		//쿠키에 넣을 정보를 조합하여 암호화한다.
		String cookieValue = aesCipherUtil.encrypt(key, initialVector, user.getCookieValue().getBytes());

		int intCookieAge = Integer.parseInt(cookieAge);
		
		//쿠키를 생성한다.
		CookieUtil.setCookie(response, cookieName, cookieValue, intCookieAge, cookiePath, cookieDomain);
		
		if (0 < userServiceIface.minorityUpdate(user))
			modelAndView.addObject("erroryn", "N");
		else
			modelAndView.addObject("erroryn", "Y");
		
		return modelAndView;
	}
	
	/**
	 * 비번 변경일자 변경처리
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/modifyPasswdChangeDate.kt")
	public ModelAndView modifyPasswdChangeDate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//JsonView를 View로 지정한다.
		modelAndView.setViewName("mappingJacksonJsonView");
		
		modelAndView.addObject("erroryn", "N");
		
		// 로그인 상태가 아닌경우
		if (!AuthUtil.isLogin(request))
		{
			modelAndView.addObject("erroryn", "Y");
			return modelAndView;
		}
		
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		
		//2주뒤 비번 팝업창 뜨게 처리
		userServiceIface.modifyPasswdChangeDate(user.getEmail());
		
		//쿠키 삭제
		String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
		CookieUtil.deleteCookie(response, "u_pw_chg", cookieDomain);
		
		return modelAndView;		
	}
	
	/**
	 * 비밀번호 자릿수 변경하기 팝업 쿠키 셋팅
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/exitPwChange.kt")
	public ModelAndView exitPwChange(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//JsonView를 View로 지정한다.
		modelAndView.setViewName("mappingJacksonJsonView");
		
		modelAndView.addObject("erroryn", "N");
		
		// 로그인 상태가 아닌경우
		if (!AuthUtil.isLogin(request))
		{
			modelAndView.addObject("erroryn", "Y");
			return modelAndView;
		}

		int age = 35 * 86400; //35일
		String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
		String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
		CookieUtil.setCookie(response, "u_pw_leg_chg", "N", age, cookiePath, cookieDomain);
		
		return modelAndView;		
	}
	
	/**
	 * 캡차코드 일치여부 체크
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/user/captchaCheck.kt")
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
	
}