/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : DateUtil.java
 * DESCRIPTION    : 날짜 관련 util class
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0              InJae Yeo      2014-04-15      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppVersionUtil {

	private static final Logger LOG = LoggerFactory.getLogger(AppVersionUtil.class);
	
	/**
	 * 앱 마켓정보
	 */
	public static final String ANDROID_FULL	    = "android";
	public static final String ANDROID_ONESTORE	= "onestore";
	public static final String ANDROID_JUNIOR	= "junior";
	
	/**
	 * 마켓별 버전 정보
	 */
	public static final String ANDROID_FULL_VERSION     =  "01.10.04";
	public static final String ANDROID_ONESTORE_VERSION =  "01.10.07";
	public static final String ANDROID_JUNIOR_VERSION 	=  "01.00.08";
	
	/**
	 * CTN버전 정보 체크
	 * @param request
	 * @return boolean
	 */
	public static boolean isNewApi(HttpServletRequest request) throws Exception {
		
		String channelfg  = StringUtil.changeNullToEmpty(AppInfoUtil.getChannelfg(request)).trim();
		String appversion = StringUtil.changeNullToEmpty(AppInfoUtil.getAppversion(request)).trim();
		
		//안드로이드 앱 체크
		if(ANDROID_FULL.equals(channelfg) || ANDROID_ONESTORE.equals(channelfg) || ANDROID_JUNIOR.equals(channelfg)) {
			
			//앱버전 체크
			if(appversion != null && appversion.length() > 0) {
				
				if(ANDROID_FULL.equals(channelfg) && appversion.compareTo(ANDROID_FULL_VERSION) > 0) {
					return true;
				}else if(ANDROID_ONESTORE.equals(channelfg) && appversion.compareTo(ANDROID_ONESTORE_VERSION) > 0) {
					return true;
				}else if(ANDROID_JUNIOR.equals(channelfg) && appversion.compareTo(ANDROID_JUNIOR_VERSION) > 0) {
					return true;
				}
			}
		}else if(RequestUtil.isMobile(request)) {
			return true;
		}
		
		return false;
	}
}