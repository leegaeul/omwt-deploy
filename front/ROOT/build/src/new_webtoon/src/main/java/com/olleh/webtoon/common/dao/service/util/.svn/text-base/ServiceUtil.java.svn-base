package com.olleh.webtoon.common.dao.service.util;

import javax.servlet.http.HttpServletRequest;

public class ServiceUtil {

	/**
	 * HttpServletRequest 객체로부터 app 여부 반환
	 * @param request
	 * @return 
	 */
	public static boolean isAppService(HttpServletRequest request) {
		
		String ua = request.getHeader( "User-Agent" );
		return ua.toLowerCase().indexOf("anymobi/") > -1 ? true : false;
	}
}
