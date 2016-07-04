/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : SDPClient.java
 * DESCRIPTION    : SDP API
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-26      init
 *****************************************************************************/

package com.olleh.webtoon.api.sdp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.olleh.webtoon.common.util.MessageUtil;

public class SDPClient {
	private final static Log logger = LogFactory.getLog(SDPClient.class);
	
	private HttpClient httpClient;
	private Map<String, String> headers;
	
	private static final int HTTP_TIME_OUT = 30000;
	
	public SDPClient() {
    	init();
    }
    
    private void init() {
    	httpClient = new HttpClient();
    }
	
	public SDPResponse call(SDPRequest request) {
		if("http".equals(request.getApiCode().getProtocol())) {
			httpClient.getHostConfiguration().setHost(MessageUtil.getSystemMessage("system.sdp.host"), 80, "http");
	        
	        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
	    	params.setConnectionTimeout(HTTP_TIME_OUT);
	        params.setSoTimeout(HTTP_TIME_OUT);
	        
	        httpClient.getHttpConnectionManager().setParams(params);
		} else {
			httpClient.getHostConfiguration().setHost(MessageUtil.getSystemMessage("system.sdp.host"), 443, "https");
	        
	        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
	    	params.setConnectionTimeout(HTTP_TIME_OUT);
	        params.setSoTimeout(HTTP_TIME_OUT);
	        
	        httpClient.getHttpConnectionManager().setParams(params);
		}
        
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		SDPResponse response = new SDPResponse(request.getApiCode());

		try {
	        PostMethod method = new PostMethod(request.getApiCode().getUrl());
	        
	        method.setRequestHeader("Authorization", "Basic " + getAuthorization());
	        
	        if(this.headers != null) {
	        	Iterator<Entry<String, String>> list = this.headers.entrySet().iterator();
        		while (list.hasNext()){
					Entry<String, String> entry = list.next();
					method.setRequestHeader(entry.getKey(), entry.getValue());
        		}
	        }
	        
	        method.setRequestEntity(request.getRequestBody());
	        
	        int status = httpClient.executeMethod(method);
	        response.setStatus(status);
	        
	        // SDP 요청에 대한 HttpStatus가 200, 201 정상인 경우와 500 server error 인 경우 응답 xml이 존재한다. 
	        if(status == HttpStatus.SC_OK
	        		|| status == HttpStatus.SC_CREATED 
	        		|| status == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
	        	is = method.getResponseBodyAsStream();
	        	
	        	if(is != null) {
					isr = new InputStreamReader(is,"UTF-8");
					br = new BufferedReader(isr);

					StringBuffer sb = new StringBuffer();
					String line = null;
					while( (line = br.readLine()) != null ) {
						sb.append(line);
					}
					
					response.setBody(sb.toString());
				}
	        } else {
	        	response.setResultCode(String.valueOf(response.getStatus()));
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			if(is != null) try { is.close(); } catch(Exception e) { }
			if(isr != null) try { isr.close(); } catch(Exception e) { }
			if(br != null) try { br.close(); } catch(Exception e) { }
			
			if(httpClient.getHttpConnectionManager() instanceof SimpleHttpConnectionManager) {
				((SimpleHttpConnectionManager)httpClient.getHttpConnectionManager()).shutdown();
			}
		}

        return response;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeader(Map<String, String> headers) {
		this.headers = headers;
	}
	
	private String getAuthorization() {
		String s = MessageUtil.getSystemMessage("system.sdp.username") + ":" + MessageUtil.getSystemMessage("system.sdp.password");
		byte[] b = Base64.encodeBase64(s.getBytes());
		return new String(b);
	}
}
