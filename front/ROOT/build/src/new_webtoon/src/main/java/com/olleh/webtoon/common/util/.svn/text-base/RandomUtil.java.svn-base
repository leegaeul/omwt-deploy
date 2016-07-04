/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : RandomUtil.java
 * DESCRIPTION    : 랜덤 관련 Util class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        kdh      2014-05-14      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RandomUtil {
	
	protected static Log logger = LogFactory.getLog(RandomUtil.class);
        
	/**
     * 랜덤 비밀번호를 반환한다.
     * 
     * @return String       : 랜덤 비밀번호
    */
    public static String getPasswd() {
        
    	String  result = "";
    	StringBuffer sb = new StringBuffer();
    	StringBuffer sc = new StringBuffer("!@#$%^&*-=?~");  // 특수문자 모음

    	// 소문자 4개를 임의발생
    	for( int i = 0; i<4; i++) {
    	    sb.append((char)((Math.random() * 26)+97)); // 아스키번호 97(a) 부터 26글자 중에서 택일
    	}  


    	// 숫자 5개를 임의 발생
    	for( int i = 0; i<5; i++) {
    	    sb.append((char)((Math.random() * 10)+48)); //아스키번호 48(1) 부터 10글자 중에서 택일
    	}
    	
    	//특수문자 1개 임의발생
    	sb.append(sc.charAt((int)(Math.random()*sc.length()-1)));
    	
    	result = sb.toString();
        
        return result;
        
    }
}