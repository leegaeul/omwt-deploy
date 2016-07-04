/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : KeyUtil.java
 * DESCRIPTION    : Key 문자열 관련 util class
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0              InJae Yeo      2014-04-16      init
 *****************************************************************************/

package com.olleh.webtoon.olltoon.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import com.olleh.webtoon.common.util.AesCipherUtil;
import com.olleh.webtoon.common.util.MessageUtil;

public class KeyUtil {
	
	public static String stringToKey(String data) throws Exception{		
		//Null이거나 공백일경우
		if(data == null || "".equals(data)) return "";

		//데이터 암호화		
		AesCipherUtil aesCipherUtil = new AesCipherUtil();		
		String aeskey = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
		String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
		String encData = aesCipherUtil.encrypt(aeskey, initialVector, (URLEncoder.encode(data, "UTF-8")).getBytes());
		
		//암호화 데이터 HEX 변환
		byte[] byteData = encData.getBytes();		
		StringBuilder str = new StringBuilder();
		
	    for(int i = 0; i < byteData.length; i++)
	        str.append(String.format("%x", byteData[i]));	    
	    
	    String key = str.toString();
	    
	    return key;
	}

	public static String keyToString(String key) throws Exception {
		//Null이거나 공백일경우
		if(key == null || "".equals(key)) return "";
		
		//HEX 값을 암호화 데이터로 변환
		StringBuilder str = new StringBuilder();
	    for (int i = 0; i < key.length(); i+=2)
	        str.append((char) Integer.parseInt(key.substring(i, i + 2), 16));
	    
	    String data = str.toString();			

		//데이터 복호화	
		AesCipherUtil aesCipherUtil = new AesCipherUtil();		
		String aeskey = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
		String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
		String decData = aesCipherUtil.decrypt(aeskey, initialVector, data.getBytes());
		
		data = URLDecoder.decode(decData, "UTF-8");
		
	    return data;
	}
	
}