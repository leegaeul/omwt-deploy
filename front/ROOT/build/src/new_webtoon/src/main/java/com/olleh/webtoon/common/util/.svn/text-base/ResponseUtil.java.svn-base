/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ResponseUtil.java
 * DESCRIPTION    : HttpServletResponse 관련 Util class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-15      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResponseUtil {
	
	protected static Log logger = LogFactory.getLog(ResponseUtil.class);
	
	/**
	 * HttpServletResponse 객체와 출력할 문자열, contentType을 전달받아 client로 결과 response를 보내는 메소드.
	 * 
	 * @param HttpServletResponse response : HttpServletResponse 객체
	 * @param String output                : 사용자에 출력할 문자열
	 * @param String contentType           : Content Type
	 * @return void 
	 */
	public static void print(HttpServletResponse response, String output, String contentType) {
		
		PrintWriter lm_oPrintWriter = null;
		
		try {
			response.setContentType(contentType + ";charset=UTF-8");
			
			lm_oPrintWriter = response.getWriter();
			lm_oPrintWriter.print(output);
			
		} catch (Exception e) {
			logger.error(e);
		} finally {
			lm_oPrintWriter.close();
		}
		
	}
	
	/**
	 * HttpServletResponse 객체와 출력할 메시지, 에러코드를 전달받아 client로 결과 Error를 보내는 메소드.
	 * 
	 * @param HttpServletResponse response : HttpServletResponse 객체
	 * @param String output                : 사용자에 출력할 메시지
	 * @param String statusCode            : 에러코드
	 * @return void 
	 */
	public static void printError(HttpServletResponse response, String msg, int errorCode) {
				
		try {
			response.sendError(errorCode, msg);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
	
}