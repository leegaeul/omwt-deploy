package com.olleh.webtoon.common.util;

import com.olleh.webtoon.http.HttpParameters;
import com.olleh.webtoon.http.HttpRequest;

public class HttpUtil {

	/**
	 * HTTP POST 방식으로 request
	 * @param String url
	 * @param HttpParameters httpParams
	 * @return 
	 */
	public static String sendPostUrl(String url) {
		
		String params[] = url.split("\\?");
		HttpParameters httpParams = new HttpParameters();
		httpParams.addParams(params.length > 1 ? params[1] : null);
		
		return sendUrl(params[0], false, httpParams);
	}
	
	/**
	 * HTTP GET 방식으로 request
	 * @param String url
	 * @param HttpParameters httpParams
	 * @return 
	 */
	public static String sendGetUrl(String url) {
		
		return sendUrl(url, true, null);
	}
	
	/**
	 * HTTP POST/GET 방식으로 request
	 * @param String url
	 * @param boolean isGetMethod
	 * @param HttpParameters httpParams
	 * @return 
	 */
	public static String sendUrl(String url, boolean isGetMethod, HttpParameters httpParams) {
		
		return new HttpRequest().call(url, isGetMethod, httpParams);
	}
}
