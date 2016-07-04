/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : AuthUtil.java
 * DESCRIPTION    : 회원 인증 관련 Util class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        kdh      2014-05-20      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import com.olleh.webtoon.common.constant.AppConstant;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;

public class AuthUtil {
	
	protected static Log logger = LogFactory.getLog(AuthUtil.class);

	/**
	 * 회원관련 쿠키를 확인하여 로그인여부를 체크한다.
	 * 
	 * @param HttpServletRequest request
	 * @return boolean   :  null이거나 공백 문자인지 여부
	 */
    public static boolean isLogin(HttpServletRequest request) throws Exception {
    	    	
    	// 웹툰 회원 정보
		String cookieName = MessageUtil.getSystemMessage("system.cookie.name");      //쿠키 변수명
		String webtoon_user = StringUtil.defaultStr(CookieUtil.getCookie(request, cookieName));
		
		// 올레 회원 ID
		String userid = StringUtil.defaultStr(CookieUtil.getCookie(request, "kt_userid"));
		
		// 회원 구분(1: 웹툰회원, 2: 작가 , 3: 약관 미동의 올레회원, 4: 약관 동의 올레회원, 5: 약관 미동의 웹툰회원  )
		String u_check_no = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_check_no"), "");	
		
		//회원구분 값은 필수로 있어야 하며  웹툰회원, 올레회원 중 하나라도 쿠키값이 있으면 로그인 상태
		if(!"".equals(u_check_no) && (!"".equals(webtoon_user) || !"".equals(userid))){
			return true;
		}
		
		return false;
    	    	
    }
    
    /**
	 * 회원관련 쿠키에서 회원정보를 추출하여 회원정보를 반환 한다.
	 * 
	 * @param HttpServletRequest request
	 * @return boolean   :  null이거나 공백 문자인지 여부
	 */
    public static UserDomain getCookieUserInfo(HttpServletRequest request) throws Exception {
    	UserDomain userDomain = new UserDomain();
    	
    	// 웹툰 회원 정보
		String cookieName = MessageUtil.getSystemMessage("system.cookie.name");      //쿠키 변수명
		String webtoon_user = StringUtil.defaultStr(CookieUtil.getCookie(request, cookieName));
		
		// 올레 회원 ID
		String userid = StringUtil.defaultStr(CookieUtil.getCookie(request, "kt_userid"));
		
		// 회원 구분(1: 웹툰회원, 2: 작가 , 3: 약관 미동의 올레회원, 4: 약관 동의 올레회원, 5: 약관 미동의 웹툰회원  )
		String u_check_no = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_check_no"), "");	
		userDomain.setCheckno(u_check_no);
		
		//회원 번호
		try{
			userDomain.setUserseq(Long.parseLong(CookieUtil.getCookie(request, "u_no")));
		}catch(Exception e){
			userDomain.setUserseq(0);
		}
		
		//회원구분 값은 필수로 있어야 하며  웹툰회원, 올레회원 중 하나라도 쿠키값이 있으면 로그인 상태
		if(("1".equals(u_check_no) || "5".equals(u_check_no)) && !"".equals(webtoon_user)){			
			//웹툰회원 일때
			userDomain.setNickname(CookieUtil.getCookie(request, "u_nickname"));	
			
			userDomain.setNameconurl(CookieUtil.getCookie(request, "u_nc_url"));			
			userDomain.setMnameconurl(CookieUtil.getCookie(request, "u_mnc_url"));
			userDomain.setIdfg("open");
			
		}else if(("2".equals(u_check_no) || "3".equals(u_check_no) || "4".equals(u_check_no)) && !"".equals(userid)){	
			//올레회원 일때			
			userDomain.setNameconurl(CookieUtil.getCookie(request, "u_nc_url"));			
			userDomain.setMnameconurl(CookieUtil.getCookie(request, "u_mnc_url"));
			userDomain.setNickname(CookieUtil.getCookie(request, "u_nickname"));	
			userDomain.setIdfg("olleh");
			
			// 작가일때
			if("2".equals(u_check_no)){
				userDomain.setNameconurl2(CookieUtil.getCookie(request, "u_nc2_url"));			
				userDomain.setMnameconurl2(CookieUtil.getCookie(request, "u_mnc2_url"));
			}			
		}
		
		return userDomain;    	    	
    }
    
    /**
	 * 회원관련 쿠키에서 암호화 되어 있는 회원정보를 복호화하여 복호화 된 회원정보를 반환 한다.
	 * 
	 * @param HttpServletRequest request
	 * @return UserDomain   :  회원정보
	 */
    public static UserDomain getDecryptUserInfo(HttpServletRequest request) throws Exception {
    	UserDomain userDomain = new UserDomain();
    	
    	// 웹툰 회원 정보
		String cookieName = MessageUtil.getSystemMessage("system.cookie.name");      //쿠키 변수명
		String webtoon_user = StringUtil.defaultStr(CookieUtil.getCookie(request, cookieName));
		
		// 올레 회원 ID
		String userid = StringUtil.defaultStr(CookieUtil.getCookie(request, "kt_userid"));
		
		// 회원 구분(1: 웹툰회원, 2: 작가 , 3: 약관 미동의 올레회원, 4: 약관 동의 올레회원, 5: 약관 미동의 웹툰회원  )
		String u_check_no = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_check_no"), "");	
		userDomain.setCheckno(u_check_no);
		
		//암복호화 모듈
		AesCipherUtil aesCipherUtil = new AesCipherUtil();		
		String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
		String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
		
		
		//회원구분 값은 필수로 있어야 하며  웹툰회원, 올레회원 중 하나라도 쿠키값이 있으면 로그인 상태
		if(("1".equals(u_check_no) || "5".equals(u_check_no)) && !"".equals(webtoon_user)){			
			//웹툰회원 일때			
			
			//복호화 처리			
			String webtoon_user_info = aesCipherUtil.decrypt(key, initialVector, webtoon_user.getBytes());
			
			//로그인할때 셋팅한 쿠키값으로 웹툰 정보 셋팅
			userDomain = new UserDomain(webtoon_user_info);
			
			userDomain.setCheckno(u_check_no);
			userDomain.setNickname(CookieUtil.getCookie(request, "u_nickname"));					
			userDomain.setNameconseq(StringUtil.defaultInt(CookieUtil.getCookie(request, "u_nc_seq"),1));			
			userDomain.setNameconurl(CookieUtil.getCookie(request, "u_nc_url"));			
			userDomain.setMnameconurl(CookieUtil.getCookie(request, "u_mnc_url"));
			userDomain.setIdfg("open");
			userDomain.setAuthoryn("N");
			
		}else if(("2".equals(u_check_no) || "3".equals(u_check_no) || "4".equals(u_check_no)) && !"".equals(userid)){	
			//올레회원 일때
			
			//올레ID 복호화
			String olleh_id = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_olleh_id"), "");
			olleh_id = aesCipherUtil.decrypt(key, initialVector, olleh_id.getBytes());			
			userDomain.setOllehid(olleh_id);
			
			//올레 계정 회원 타입 (I:개인, O:법인, N:준회원(KT비사용자))
			String olleh_ctype = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_olleh_ctype"), "I");
			olleh_ctype = aesCipherUtil.decrypt(key, initialVector, olleh_ctype.getBytes());			
			userDomain.setCtype(olleh_ctype);
			
			//올레계정 만나이
			String olleh_age = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_olleh_age"), "0");
			olleh_age = aesCipherUtil.decrypt(key, initialVector, olleh_age.getBytes());			
			userDomain.setAge(olleh_age);
			
			//기본으로 이메일에 올레 ID를 셋팅
			userDomain.setEmail(olleh_id);
			userDomain.setNickname(olleh_id);	
			userDomain.setNameconseq(StringUtil.defaultInt(CookieUtil.getCookie(request, "u_nc_seq"),1));
			userDomain.setNameconurl(CookieUtil.getCookie(request, "u_nc_url"));			
			userDomain.setMnameconurl(CookieUtil.getCookie(request, "u_mnc_url"));		
			userDomain.setIdfg("olleh");
			userDomain.setAuthoryn("N");
			
			// 작가일때
			if("2".equals(u_check_no)){
				//닉네임이 없을때는 올렛ID로 지정
				if(CookieUtil.getCookie(request, "u_nickname") != null)
					userDomain.setNickname(CookieUtil.getCookie(request, "u_nickname"));

				userDomain.setAuthorseq(CookieUtil.getCookie(request, "u_ahr_seq"));			
				userDomain.setNameconseq2(StringUtil.defaultInt(CookieUtil.getCookie(request, "u_nc2_seq"),1));
				userDomain.setNameconurl2(CookieUtil.getCookie(request, "u_nc2_url"));			
				userDomain.setMnameconurl2(CookieUtil.getCookie(request, "u_mnc2_url"));
				userDomain.setAuthoryn("Y");
			}
			
		}
		
		return userDomain;    	    	
    }
 
    /**
     * 
     * @param request
     * @param mv
     * @return 리디렉션 하면 true 아니면 false
     */
    public static boolean ifNotLoginThenRedirect( HttpServletRequest request, ModelAndView mv, boolean isMobile ) throws Exception
    {
    	if (!isLogin( request ) ) 
		{
    		if( StringUtils.isNotEmpty( mv.getViewName() ) && mv.getViewName().equals( AppConstant.JSON_VIEW_NAME ))
    		{
    			mv.addObject( "erroryn", "Y" );
    			mv.addObject( "errormsg", "로그인 후 이용해주세요." );
    		}
    		else
    		{
				String beforeURI = RequestUtil.getFullURI(request);
				beforeURI = StringUtils.isNotEmpty( beforeURI ) ? "?urlcd=" + beforeURI : "";
				if( isMobile )
					mv.setViewName("redirect:/m/login.kt" + beforeURI);
				else
					mv.setViewName("redirect:/login.kt" + beforeURI);
    		}
			return true;
		}
    	else
    		return false;
    }
}