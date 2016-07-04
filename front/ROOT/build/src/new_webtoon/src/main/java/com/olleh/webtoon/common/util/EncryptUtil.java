/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : EncryptUtil.java
 * DESCRIPTION    : Sha512 암호화 관련 util class
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0              InJae Yeo      2014-04-16      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
	
	/**
	* 
	* 문자열를 받아서 SHA-512로 암호화 처리를 한다.
	* 패스워드 DB 저장 시 처리를 한다.
	* 사용예) StringUtil.EncryptSHA512(String);
	* @param String
	* @return String
	*/
	public String encryptSHA512(String src) throws Exception {
		
		if(src==null || src.equals("")) {
			return src;
		}
		
	    StringBuilder encryptStr = new StringBuilder();
	
	    MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

	    messageDigest.update(src.getBytes());
    	byte[] mb = messageDigest.digest();
    	
    	for (int i = 0; i < mb.length; i++) {
    		
    		byte temp = mb[i];
    		String s = Integer.toHexString(new Byte(temp));
    		while (s.length() < 2){
    			s = "0" + s;
		    }
    	
    		s = s.substring(s.length() - 2);
    		encryptStr.append(s);
    		
    	}
	    		    
	    return encryptStr.toString();
	    
	}
	
	/*public static void main(String[] args) {
		try {
			EncryptUtil encryptUtil = new EncryptUtil();
			System.out.println(encryptUtil.encryptSHA512("cadgsdfsfsfssdfsdfs"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	* 
	* 문자열를 받아서 SHA-256로 암호화 처리를 한다.
	* 이니시스 모바일 결제 모듈에 넘겨줄 hashdata를 생성할 때 사용
	* 사용예) StringUtil.EncryptSHA256(String);
	* @param String
	* @return String
	*/
	public static String encryptSHA256(String str)
	{
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}
	
}