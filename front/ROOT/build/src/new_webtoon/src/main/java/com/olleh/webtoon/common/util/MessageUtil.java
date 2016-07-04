/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : MessageUtil.java
 * DESCRIPTION    : Java 파일에서 ResourceBundle Message를 사용하기 위한 Util class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-15      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

public class MessageUtil implements MessageSourceAware {
		
	protected static Log logger = LogFactory.getLog(MessageUtil.class);
	
	private static MessageSource messageSource; //MessageSource 객체

	/**
	 * 시스템 property ResourceBundle message 키값을 넘겨받아 해당 message를 반환하는 메소드.
	 * 
	 * @param String key : ResourceBundle message 키값
	 * @return String    : ResourceBundle message
	 */
    public static String getSystemMessage(String key) {
    	
    	String message = null; //반환할 메시지
    	
    	if(key == null) {
    		return null;
    	}
    	
    	try {
    		message = messageSource.getMessage(key, null, Locale.getDefault());
    	} catch(Exception e) {
    		logger.error(e.getMessage());
    	}
    	
    	return message;
    	
    }
    
    /**
	 * ResourceBundle message 키값을 넘겨받아 해당 message를 반환하는 메소드.
	 * 
	 * @param String key                 : ResourceBundle message 키값
	 * @return String                    : ResourceBundle message
	 */
    public static String getMessage(String key) {
    	
    	String message = null; //반환할 메시지
    	
    	if(key == null) {
    		return null;
    	}
    	
    	try {
    		message = messageSource.getMessage(key, null, Locale.getDefault());
    	} catch(Exception e) {
    		logger.error(e.getMessage());
    	}
    	
    	return message;
    	
    }
    
    /**
	 * ResourceBundle message 키값과 message 내의 변수영역과 치환될 배열 객체를 넘겨받아 
	 * 해당 message를 반환하는 메소드.
	 * 
	 * @param String key                 : ResourceBundle message 키값
	 * @param Object[] args              : message 내의 변수영역과 치환될 배열 객체
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return String                    : ResourceBundle message
	 */
    public static String getMessage(String key, Object[] args) {
    	
    	String message = null; //반환할 메시지
    	
    	if(key == null) {
    		return null;
    	}
    	
    	try {
    		message = messageSource.getMessage(key, args, Locale.getDefault());
    	} catch(Exception e) {
    		logger.error(e.getMessage());
    	}
    	
    	return message;
    	
    }
        
    /**
     * MessageSourceAware의 setMessageSource 구현 메소드.
     * MessageSource 객체를 전달 받는다.
     * 
     * @param MessageSource messageSource : MessageSource 객체
     * @return void
     */
    @Override
    public void setMessageSource(MessageSource messageSource) {
    	
    	this.messageSource = messageSource;
    	
    }
    
}