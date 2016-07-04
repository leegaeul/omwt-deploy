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

public class SmsSendUtil {
	

    private static final String API_URL			= MessageUtil.getSystemMessage("system.sms.apiurl");
    private static final String API_KEY			= MessageUtil.getSystemMessage("system.sms.apikey");
    private static final String API_CLIENT_ID	= MessageUtil.getSystemMessage("system.sms.apiclientid");
    private static final String SEND_NAME		= MessageUtil.getSystemMessage("system.sms.sendname");
    private static final String SEND_PHONE		= MessageUtil.getSystemMessage("system.sms.sendphone");
    
    /*private static final String API_URL			= "http://api.openapi.io/ppurio_test/1/message_test/sms/";
    private static final String API_KEY			= "MS0xMzY1NjY2MTAyNDk0LTA2MWE4ZDgyLTZhZmMtNGU5OS05YThkLTgyNmFmYzVlOTkzZQ==";
    private static final String API_CLIENT_ID	= "omwebtoon";
    private static final String SEND_NAME		= "올레마켓웹툰";
    private static final String SEND_PHONE		= "100";*/

	
	public static String sendSms(String dest_phone, String dest_name, String msg_body) {
		HttpURLConnection httpcon = null;
		BufferedReader in = null;
	    StringBuilder reMsg = new StringBuilder(256);
	    StringBuffer outputBytes = new StringBuffer();
	    
	    try {
		    httpcon = (HttpURLConnection) ((new URL(API_URL+API_CLIENT_ID).openConnection()));
			httpcon.setDoOutput(true);
			httpcon.setRequestProperty("x-waple-authorization", API_KEY);
			httpcon.setRequestMethod("POST");
			httpcon.connect();
			
			//공백 및 (-) 제거
			dest_phone = dest_phone.replaceAll(" ", "").replaceAll("-", "");
		
			outputBytes.append("dest_phone="+dest_phone);
			outputBytes.append("&dest_name="+URLEncoder.encode(dest_name, "utf-8"));
			outputBytes.append("&send_phone="+SEND_PHONE);
			outputBytes.append("&send_name="+URLEncoder.encode(SEND_NAME, "utf-8"));
			outputBytes.append("&msg_body="+URLEncoder.encode(msg_body, "utf-8"));
			
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
			e.printStackTrace();
	       reMsg.append(e.getMessage() + "\n");
		} finally {
			try{if(in != null) in.close();}catch(Exception e){}
			try{if(httpcon != null) httpcon.disconnect();}catch(Exception e){}
		}
	    
	    return reMsg.toString();
	}
	  
	  public static boolean sendSmsResult(String dest_phone, String dest_name, String msg_body) {
	        String regMsg = sendSms(dest_phone, dest_name, msg_body);
	        //System.out.println(regMsg);
	        
	        //전달 완료
	        if(regMsg.indexOf("200") > -1) return true;
	        
	        return false;
	  }
	  
	  public static void main(String args[]){		  
		  SmsSendUtil.sendSmsResult("01012345678", "테스트", "테스트입니다.");
	  }
}