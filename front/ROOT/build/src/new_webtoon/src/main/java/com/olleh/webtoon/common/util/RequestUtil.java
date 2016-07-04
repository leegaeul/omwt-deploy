/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : RequestUtil.java
 * DESCRIPTION    : HttpServletRequest 관련 Util class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-15      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	/**
	 * HttpServletRequest 객체로부터 원하는 파라미터를 가져오는 메소드.
	 * 파라미터가 null일 경우 빈 문자열을 리턴한다.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @param String paramName           : 파라미터명
	 * @param boolean doTrim             : trim 할지의 여부(true일 경우 trim 처리)
	 * @return String paramValue         : 파라미터값
	 */
	public static String getParameter(HttpServletRequest request, String paramName, boolean doTrim) {
		
		String paramValue = changeNullToEmpty(request.getParameter(paramName));
		
		if(doTrim) {
			paramValue = paramValue.trim();
		}
		
		return paramValue;
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 원하는 int형태의 파라미터를 가져오는 메소드.
	 * 파라미터가 null이거나 숫자가 아닐 경우 0을 리턴한다.
	 * 
	 * @param request       : HttpServletRequest 객체
	 * @param paramName     : 파라미터명
	 * @return int intValue : int로 변형한 파라미터값
	 */
	public static int getIntParameter(HttpServletRequest request, String paramName) {
		
		String paramValue = changeNullToEmpty((String)request.getParameter(paramName)).trim();
		int intValue = 0;
		
		if(paramValue.length() > 0) {
			try {
				intValue = Integer.parseInt(paramValue);
			} catch(NumberFormatException nfe) {}
		}
		
		return intValue;
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 원하는 long 형태의 파라미터를 가져오는 메소드.
	 * 파라미터가 null이거나 숫자가 아닐 경우 0L을 리턴한다.
	 * 
	 * @param request         : HttpServletRequest 객체
	 * @param paramName       : 파라미터명
	 * @return long longValue : long으로 변형한 파라미터값
	 */
	public static long getLongParameter(HttpServletRequest request, String paramName) {
		
		String paramValue = changeNullToEmpty(request.getParameter(paramName)).trim();
		long longValue = 0L;
		
		if(paramValue.length() > 0) {
			try {
				longValue = Long.parseLong(paramValue);
			} catch(NumberFormatException nfe) {}
		}
		
		return longValue;
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 Request URI를 가져오는 메소드.
	 * Request URI가 null일 경우 빈 문자열을 반환한다.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return String                    : Request URI
	 */
	public static String getRequestUri(HttpServletRequest request) {
		
		return changeNullToEmpty(request.getRequestURI()).trim();
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 Request IP를 가져오는 메소드.
	 * Request IP가 null일 경우 빈 문자열을 반환한다.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return String                    : Request URI
	 */
	public static String getRequestIp(HttpServletRequest request) {
		
		//return changeNullToEmpty(request.getRemoteAddr());
		
		//L4서버 이용시
		String ip = request.getHeader("HTTP_X_FORWARDED_FOR");

		if(ip == null || ip.length() == 0 || ip.toLowerCase().equals("unknown"))
		    ip = request.getHeader("X-Forwarded-For");

		if(ip == null || ip.length() == 0 || ip.toLowerCase().equals("unknown"))
		    ip = request.getHeader("REMOTE_ADDR");

		if(ip == null || ip.length() == 0 || ip.toLowerCase().equals("unknown"))
		    ip = request.getRemoteAddr();
		
		return changeNullToEmpty(ip);
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 Referer를 가져오는 메소드.
	 * Request IP가 null일 경우 빈 문자열을 반환한다.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return String                    : Referer
	 */
	public static String getReferer(HttpServletRequest request) {
		
		return changeNullToEmpty(request.getHeader("referer"));
		
	}
 
	/**
	 * HttpServletRequest 객체로부터 요청 host정보를 가져오는 메소드.
	 * host정보가 null일 경우 빈 문자열을 반환한다.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return String                    : Referer
	 */
	public static String getHost(HttpServletRequest request) {
		
		return changeNullToEmpty(request.getHeader("Host"));
		
}
 
	/**
	 * HttpServletRequest 객체로부터 모바일 여부를 체크하는 메소드.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return boolean                    : 모바일 여부
	 */
	public static boolean isMobile(HttpServletRequest request) {
		//모바일 체크
		boolean isMobile = false;
		String userAgent = request.getHeader("User-Agent").toLowerCase();		
		if(userAgent.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")
				|| userAgent.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")) {
			isMobile = true;	
		}
		
		return isMobile;
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 인자값을 포함한 URL반환
	 * @param request
	 * @return
	 * @see http://stackoverflow.com/questions/2222238/httpservletrequest-to-complete-url
	 */
	public static String getFullURL(HttpServletRequest request) {
	    StringBuffer requestURL = request.getRequestURL();
	    String queryString = request.getQueryString();

	    if (queryString == null) {
	        return requestURL.toString();
	    } else {
	        return requestURL.append('?').append(queryString).toString();
	    }
	}
	
	/**
	 * HttpServletRequest 객체로부터 인자값을 포함한 URI반환
	 * @param request
	 * @return
	 * @see http://stackoverflow.com/questions/2222238/httpservletrequest-to-complete-url
	 */
	public static String getFullURI(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
	    String queryString = request.getQueryString();

	    if (queryString == null) {
	        return requestURI;
	    } else {
	    	return requestURI + "?" + queryString;
	    }
	}
	
	/**
	 * 문자열을 넘겨받아 null일 경우 빈 문자열을 반환하는 메소드.
	 * @param String src : null 체크할 문자열
	 * @return String    : null 체크한 문자열
	 */
    private static String changeNullToEmpty(String src) {
    	
    	if(src == null) {
    		return "";
    	} else {
    		return src;
    	}
    	
    }
    
    /**
	 * HttpServletRequest 객체로부터 UUID 반환
	 * @param request
	 * @return uuid
	 */
    public static String  getUuid(HttpServletRequest request) {
    	String ua = request.getHeader( "User-Agent" );
    	String uuid = null;
    	
		 //모바일 앱에서 로그인시 Device정보에 로그인 정보를 update
		 if(ua.indexOf("UUID") > -1){
			 uuid = ua.substring(ua.indexOf("UUID")+5);
		 }
		 
		 return uuid;
    }
    
   	/**
   	 * HttpServletRequest 객체로부터 iOS를 체크하는 메소드.
   	 * 
   	 * @param HttpServletRequest request : HttpServletRequest 객체
   	 * @return boolean                    : iOS 여부
   	 */
   	public static boolean isIos(HttpServletRequest request) {
   		//iOS 체크
   		boolean isIos = false;
   		String userAgent = request.getHeader("User-Agent").toLowerCase();		
   		if(
   			userAgent.matches(".*(ip(hone|od)).*")
   		) {
   			isIos = true;	
   		}
   		
   		return isIos;
   		
   	}
   	
   	
    
   	/**
   	 * HttpServletRequest 객체로부터 단말 유형 반환 메소드.
   	 * 
   	 * @param HttpServletRequest request : HttpServletRequest 객체
   	 * @return String                    : 단말유형(pc:PC, mobile:모바일웹, ios:IOS, android:안드로이드)
   	 */
   	public static String getClientDeviceInfo(HttpServletRequest request) {
   		
   		//단말유형(pc:PC, mobile:모바일웹, ios:IOS, android:안드로이드)
   		String devicefg = "";
   		
   		//모바일 여부 체크
   		if(isMobile(request)){
   			devicefg = "mobile";
   			
   			//웹툰 앱 체크
   			if(getUuid(request) != null){
   				//OS 체크
	   			if(isIos(request)) devicefg = "ios";
	   			else devicefg = "android";
   			}
   			
   		}else{
   			devicefg = "pc";
   		}
   		
   		return devicefg;
   	}
    
}