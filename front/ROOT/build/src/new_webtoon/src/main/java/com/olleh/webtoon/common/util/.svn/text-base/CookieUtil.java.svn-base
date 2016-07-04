/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : CookieUtil.java
 * DESCRIPTION    : Cookie 관련 Util class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-15      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CookieUtil {
	
	protected static Log logger = LogFactory.getLog(CookieUtil.class);

	/**
	 * 쿠키명을 전달받아 해당 쿠키값을 가져온다.
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @param String cookieName          : 쿠키명
	 * @return String cookieVal          : 쿠키값
	 * @throws Exception
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) throws Exception {
		
		Cookie[] cookies = null;
		String cookieVal = null;
		
		try	{
			
			cookies = request.getCookies();
			
			if(cookieName==null || cookies==null) {
				return null;
			}

			for(int i = 0; i < cookies.length; i++) {
				
				//쿠키값이 없거나 "null" 이라는 문자열로 들어왔는지 체크하여 쿠키값을 반환한다.
				if(cookieName.equals(cookies[i].getName())) {
					if(StringUtil.isEmptyOrWhitespace(cookies[i].getValue()) || (cookies[i].getValue()).equals("null")) {
                		return null;
                	} else {
                		cookieVal = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                		break;
                	}
				}
				
			}
			
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return cookieVal;
		
	}

	/**
	 * 쿠키를 세팅한다.
	 * 
	 * @param HttpServletResponse response : HttpServletResponse 객체
	 * @param String cookieName            : 쿠키명
	 * @param String cookieValue           : 쿠키값
	 * @param int age                      : 쿠키 유효시간 (초단위, -1을 넘기면 브라우져 종료시 쿠키가 종료된다.)
	 * @param String path                  : 쿠키경로
	 * @param String domain                : 쿠키 도메인
	 * @return void
	 * @throws Exception
	 */
	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int age, String path, String domain) throws Exception {
		
		StringBuilder cookieStr = new StringBuilder();
		Date expireDate = null;
		DateFormat dateFormat = null;
		
		//쿠키명은 필수항목이다.
		if(StringUtil.isEmptyOrWhitespace(cookieName)) {
			throw new NullPointerException();
		}
		
		try	{

			//쿠키명과 값을 세팅한다.
			cookieStr.append(cookieName + "=");
			cookieStr.append(URLEncoder.encode(StringUtil.changeNullToEmpty(cookieValue), "UTF-8") + "; ");

			//쿠키 만료일자를 세팅한다.
			if(age == 0) {
				cookieStr.append("expires=Thu, 01 Jan 1970 00:00:00 GMT; ");
			} else if (age > 0) {
				expireDate = new Date();
				expireDate.setTime(expireDate.getTime() + ((long)age * 1000));

				dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.ENGLISH);
				dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

				cookieStr.append("expires=" + dateFormat.format(expireDate) + " UTC; ");
			}

			//쿠키 경로를 세팅한다.
			if(!StringUtil.isEmptyOrWhitespace(path)) {
				cookieStr.append("path=").append(path).append("; ");
			}

			//쿠키 도메인을 세팅한다.
			if(!StringUtil.isEmptyOrWhitespace(domain)) {
				cookieStr.append("domain=").append(domain).append("; ");
			}

			//쿠키를 response 객체에 세팅한다.
			response.addHeader("Set-Cookie", cookieStr.toString());
			
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
	}

	/**
	 * 쿠키명을 전달받아 쿠키를 삭제한다.
	 * 
	 * @param HttpServletResponse response : HttpServletResponse 객체
	 * @param String cookieName            : 쿠키명
	 * @return void
	 */
	public static void deleteCookie(HttpServletResponse response, String cookieName) {
		
		try	{
			setCookie(response, cookieName, null, 0, "/", null);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
	
	/**
	 * 쿠키명과 도메인을 전달받아 쿠키를 삭제한다.
	 * 
	 * @param HttpServletResponse response : HttpServletResponse 객체
	 * @param String cookieName            : 쿠키명
	 * @param String domain                : 도메인
	 * @return void
	 */
	public static void deleteCookie(HttpServletResponse response, String cookieName, String domain) {
		
		try {
			setCookie(response, cookieName, null, 0, "/", domain);
		} catch (Exception e) {
			logger.error(e);
		}			
		
	}
	
	/**
	 * 쿠키를 전체를 삭제한다.
	 * 
	 * @param HttpServletResponse response : HttpServletResponse 객체
	 * @return void
	 */
	public static void deleteAllCookie(HttpServletRequest request, HttpServletResponse response, String cookieDomain) {
		try {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
		        for(int i = 0; i< cookies.length ; i++){
		        	//최근 본 툰 관련 cookie 제외
		        	//if(!((cookies[i].getName().indexOf("timesseq") > -1 || cookies[i].getName().indexOf("regdt") > -1) && cookies[i].getName().indexOf("list") > -1) && !(cookies[i].getName().indexOf("u_cc_") > -1)){
		        	if(!((cookies[i].getName().indexOf("timesseq") > -1 || cookies[i].getName().indexOf("regdt") > -1) && cookies[i].getName().indexOf("list") > -1)){
		        		if(!(cookies[i].getName().indexOf("u_pw_leg_chg") > -1) && !(cookies[i].getName().indexOf("ot_token") > -1))
		        			CookieUtil.deleteCookie(response, cookies[i].getName(), cookieDomain);
		        	}
		        }
			}
		} catch (Exception e) {
			logger.error(e);
		}			
		
	}
	
}