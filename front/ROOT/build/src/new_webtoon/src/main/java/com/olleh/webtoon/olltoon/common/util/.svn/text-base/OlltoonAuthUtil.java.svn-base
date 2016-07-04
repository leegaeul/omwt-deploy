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

package com.olleh.webtoon.olltoon.common.util;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.constant.AppConstant;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AesCipherUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

public class OlltoonAuthUtil {
	
	protected static Log logger = LogFactory.getLog(OlltoonAuthUtil.class);

	/**
	 * 회원관련 쿠키를 확인하여 로그인여부를 체크한다.
	 * 
	 * @param HttpServletRequest request
	 * @return boolean   :  null이거나 공백 문자인지 여부
	 */
    public static boolean isLogin(HttpServletRequest request) throws Exception {

		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token != null && !"".equals(ot_token)) {
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
    	
    	return getDecryptUserInfo(request);  	    	
    }
    
    /**
	 * 회원관련 쿠키에서 암호화 되어 있는 회원정보를 복호화하여 복호화 된 회원정보를 반환 한다.
	 * 
	 * @param HttpServletRequest request
	 * @return UserDomain   :  회원정보
	 */
    public static UserDomain getDecryptUserInfo(HttpServletRequest request) throws Exception {
    	// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
    			
    	UserDomain userDomain = new UserDomain();
		
		//회원 번호
		try{
			userDomain.setUserseq(Long.parseLong((String)userMap.get("userseq")));
		}catch(Exception e){
			userDomain.setUserseq(0);
		}
		
		String idfg = (String)userMap.get("idfg");
		String termsyn = (String)userMap.get("termsyn");
		String authoryn = (String)userMap.get("authoryn");
		String u_check_no = "1";
		if("olleh".equals(idfg)) {
			u_check_no = "4";
			
			//올레회원 미동의
			if(!"Y".equals(termsyn)) 
				u_check_no = "3";
			
			//작가일 경우
			if("Y".equals(authoryn)) 
				u_check_no = "2";
			
			userDomain.setIdfg("olleh");
			userDomain.setNickname((String)userMap.get("userid"));	
		}else{
			u_check_no = "1";
			
			//웹툰 회원 미동의
			if(!"Y".equals(termsyn)) 
				u_check_no = "5";
			
			userDomain.setIdfg("open");
			userDomain.setNickname((String)userMap.get("nickname"));
			userDomain.setAdultyn((String)userMap.get("adultyn"));
			userDomain.setBirthday((String)userMap.get("birthday"));
		}

		userDomain.setUserid((String)userMap.get("userid"));	
		userDomain.setEmail((String)userMap.get("userid"));	
		userDomain.setAuthoryn((String)userMap.get("authoryn"));	
		userDomain.setCheckno(u_check_no);
		
		userDomain.setNameconseq((Integer)userMap.get("nameconseq"));	
		userDomain.setNameconurl((String)userMap.get("nameconurl"));			
		userDomain.setMnameconurl((String)userMap.get("mnameconurl"));

		// 작가일때
		if("2".equals(u_check_no)){
			userDomain.setAuthorseq((String)userMap.get("authorseq"));		
			userDomain.setNameconseq2((Integer)userMap.get("nameconseq2"));	
			userDomain.setNameconurl2((String)userMap.get("nameconurl2"));			
			userDomain.setMnameconurl2((String)userMap.get("mnameconurl2"));
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
				mv.setViewName("redirect:/web/login.kt" + beforeURI);
    		}
			return true;
		}
    	else
    		return false;
    }
}