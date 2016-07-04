/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰

 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : SDPResponse.java
 * DESCRIPTION    : SDP API
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-26      init
 *****************************************************************************/

package com.olleh.webtoon.api.sdp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SDPResponse {
	
	private final static Log logger = LogFactory.getLog(SDPResponse.class);
	
	private SDPApiCode apiCode;
	private int status;
	private String body;
	private String resultCode;
	private String resultMsg;
	private Map<String, Object> resultMap;
	
	public SDPResponse(SDPApiCode apiCode) {
		this.apiCode = apiCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
		this.parseResponse();
	}
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getParameter(String key) {
		return resultMap.get(key);
	}

	private void parseResponse() {
		if("soap".equals(this.apiCode.getType())) {
			resultMap = SDPUtil.parseXML(this.body);
			
			// 결과코드, 메세지 설정
			if(resultMap.get("RETURNCODE") != null) { // 성공
				this.setResultCode(resultMap.get("RETURNCODE").toString());
				this.setResultMsg(resultMap.get("RETURNDESC").toString());
				
			} else if(resultMap.get("FAULTCODE") != null) { // 실패
				if(resultMap.get("DETAIL") instanceof Map) { // SDP서버 에러
					Map detailMap = (Map)((Map)resultMap.get("DETAIL")).get("WAMEXCEPTION");
					
					this.setResultCode(detailMap.get("ERRORCODE").toString());	
					this.setResultMsg(detailMap.get("ERRORDESCRIPTION").toString());
					
				} else { // SDP클라이언트 에러
					this.setResultCode(resultMap.get("FAULTCODE").toString());
					this.setResultMsg(resultMap.get("FAULTSTRING").toString());
				}
			}
			
		} else if("json".equals(this.apiCode.getType())) {
			resultMap = SDPUtil.parseJSON(this.body);
			
			if("1".equals(resultMap.get("returncode"))) { // 성공
				try {
					this.setResultCode(resultMap.get("returncode").toString());	
					this.setResultMsg(URLDecoder.decode(resultMap.get("returndescription").toString(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					logger.error(e);
				}
			} else { // 실패
				try {
					if(resultMap.get("errorcode") == null) {
						this.setResultCode(SDPConstants.RESULT_FAIL);
						this.setResultMsg(SDPConstants.RESULT_FAIL_STRING);
					} else {
						this.setResultCode(resultMap.get("errorcode").toString());
						this.setResultMsg(URLDecoder.decode(resultMap.get("errordescription").toString(), "UTF-8"));
					}
				} catch (UnsupportedEncodingException e) {
					logger.error(e);
				}
			}
		}
	}
}
