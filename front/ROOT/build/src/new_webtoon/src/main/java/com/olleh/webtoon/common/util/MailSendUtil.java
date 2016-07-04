/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : AesCipherUtil.java
 * DESCRIPTION    : AES 암호화 관련 Util class.
 *                  AES transformation을 "AES/CBC/PKCS5Padding"
 *                  (CBC 모드와 PKCS5Padding 패딩 기법을 이용한 암호화)로 암호화한다.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-16      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MailSendUtil {
    private static final String EMAIL_URL                = MessageUtil.getSystemMessage("system.email.url");
    private static final String AUTHORIZATION     = MessageUtil.getSystemMessage("system.email.authorization");
    private static final String EMAIL_FROM               = MessageUtil.getSystemMessage("system.email.frommail");

	
	public static String sendEmail(String subject, String content, String tomail) {
		HttpURLConnection httpcon = null;
		BufferedReader in = null;
	    StringBuilder reMsg = new StringBuilder(256);
	    StringBuffer outputBytes = new StringBuffer();
	    
	    try {
	           
		    httpcon = (HttpURLConnection) ((new URL(EMAIL_URL).openConnection()));
			httpcon.setDoOutput(true);
			httpcon.setRequestProperty("Authorization", "Basic "+AUTHORIZATION);
			httpcon.setRequestMethod("POST");
			httpcon.connect();
		
			outputBytes.append("from="+EMAIL_FROM);
			outputBytes.append("&to="+tomail);
			outputBytes.append("&Subject="+URLEncoder.encode(subject, "utf-8"));
			outputBytes.append("&Contents="+URLEncoder.encode(content, "utf-8"));
			
			DataOutputStream os = new DataOutputStream(httpcon.getOutputStream());
		
			os.writeBytes(outputBytes.toString());
		
			os.close();
			
			in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
		    String line = "";
		    while((line=in.readLine())!=null) {
		    	reMsg.append(line + "\n");
		    }
		    
		    in.close();
		    httpcon.disconnect();
		} catch(Exception e) {
	       reMsg.append(e.getMessage() + "\n");
		} finally {
			try{if(in != null) in.close();}catch(Exception e){}
			try{if(httpcon != null) httpcon.disconnect();}catch(Exception e){}
		}
	    
	    return reMsg.toString();
	}
	  
	  public static boolean sendEmailResult(String subject, String content, String to) {
	        String reMsg = sendEmail(subject, content, to);
	        
	        if(reMsg.indexOf("Exception") != -1) return false;
	        if(reMsg.indexOf("requestId") == -1) return false;
	        
	        return true;
	  }
	
}