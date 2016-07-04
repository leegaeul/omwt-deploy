/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : SDPRequest.java
 * DESCRIPTION    : SDP API
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-26      init
 *****************************************************************************/

package com.olleh.webtoon.api.sdp;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.olleh.webtoon.common.util.MessageUtil;

public class SDPRequest {
	private static Log logger = LogFactory.getLog(SDPRequest.class);

	private SDPApiCode apiCode;
	private Map<String, Object>	params;

	public SDPRequest(SDPApiCode apiCode, Map<String, Object> payment) {
		this.apiCode = apiCode;
		this.params = payment;
	}

	public RequestEntity getRequestBody() {
		RequestEntity requestEntity = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			if("soap".equals(this.apiCode.getType())) {
				for(String key : params.keySet()) {
					if(params.get(key) != null) {
						sb.append(String.format(SDPConstants.SOAP_PARAM_FORMAT, key, params.get(key), key));
					}
				}
				
				String contents = String.format(SDPConstants.SOAP_REQUEST_FORMAT, 
						MessageUtil.getSystemMessage("system.sdp.username"), 
						MessageUtil.getSystemMessage("system.sdp.password"), 
						apiCode.getName(), 
						sb.toString(),
						apiCode.getName());
				
				requestEntity = new StringRequestEntity(contents, "application/soap+xml", "utf-8");
				
			} else if("json".equals(this.apiCode.getType())) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
		        String contents = gson.toJson(params);
		        
				requestEntity = new StringRequestEntity(contents, "application/json", "utf-8");
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		
		return requestEntity;
	}

	public SDPApiCode getApiCode() {
		return apiCode;
	}

	public void setApiCode(SDPApiCode apiCode) {
		this.apiCode = apiCode;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
