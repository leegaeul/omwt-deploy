/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : SDPConstants.java
 * DESCRIPTION    : SDP API
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-26      init
 *****************************************************************************/

package com.olleh.webtoon.api.sdp;

public class SDPConstants {
	
	// 결제 인증, 승인 시 에러 정의
	public static final String RESULT_SUCCESS = "1"; 	// 결제 인증, 승인 성공
	public static final String RESULT_FAIL = "0";			// 결제 승인 실패
	public static final String RESULT_SUCCESS_STRING = "";
	public static final String RESULT_FAIL_STRING = "결제 시스템 접속이 원할하지 않습니다.";
	
	// 웹툰서비스 자체 에러 정의
	public static final String ERROR_SYSTEM = "9999"; 
	public static final String ERROR_SMS_SERVICE = "1001";
	public static final String ERROR_SMS_INVALID = "1002";	
	public static final String ERROR_SMS_APPROVE = "1003";
	public static final String ERROR_SMS_TIMEOUT = "2001";	
	public static final String ERROR_INVALID_REQUEST = "4001";
	public static final String ERROR_INVALID_PARAMETER = "4002";
	public static final String ERROR_CANCEL_REQUEST = "4003";
	public static final String ERROR_INVALID_AGENT = "4004";
	public static final String ERROR_NONE = "0";
	
	public static final String ERROR_SYSTEM_STRING = "시스템 접속이 원할하지 않습니다.";
	public static final String ERROR_SMS_SERVICE_STRING = "SMS 시스템 접속이 원할하지 않습니다.";
	public static final String ERROR_SMS_INVALID_STRING = "인증번호가 일치하지 않습니다.";	
	public static final String ERROR_SMS_APPROVE_STRING = "휴대폰 결제인 경우 휴대폰 승인이 필요합니다.";
	public static final String ERROR_SMS_TIMEOUT_STRING = "인증시간이 만료되었습니다.";	
	public static final String ERROR_INVALID_REQUEST_STRING = "잘못된 요청입니다.";
	public static final String ERROR_INVALID_PARAMETER_STRING = "잘못된 요청입니다.";
	public static final String ERROR_CANCEL_REQUEST_STRING = "결제 요청을 취소하였습니다.";
	public static final String ERROR_INVALID_AGENT_STRING = "휴대폰 결제는 IE(인터넷 익스플로러), 파이어폭스, 크롬, 오페라 브라우저에서만 사용 가능합니다.";
	
	// 올레회원 고객구분코드
	public static final String CUSTOMER_TYPE_I = "I";		// 개인 고객
	public static final String CUSTOMER_TYPE_B = "O";	// 법인 고객
	public static final String CUSTOMER_TYPE_N = "N";	// 준회원(KT비회원)
	
	// 결제 승인 사용자 타입
	public static final String SUBSCR_CTN = "2";				// 대표 CTN 
	public static final String SUBSCR_OLLEHID = "5";		// 올레 아이디
	public static final String SUBSCR_OPENID = "6";		// 웹툰 오픈 아이디
	
	// 결제를 위해 필요한 사용자 상태 정의
	public static final int REQ_NONE  = 0;
	public static final int REQ_LOGIN  = 1;
	public static final int REQ_AGREEMENT  = 2;
	public static final int REQ_SELF_CERT  = 3;
	public static final int REQ_AGENT_CERT  = 4;
	public static final int REQ_ADULT_CERT  = 5;

	
	public static final String LINE_DELEMETER = "\n";
	
	public static final String SOAP_REQUEST_FORMAT = 
		  "<soapenv:Envelope "
		+ "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
		+ "xmlns:oas='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd' "
		+ "xmlns:sdp=\"http://kt.com/sdp\">" + LINE_DELEMETER 
		+ "	<soapenv:Header>" + LINE_DELEMETER 
		+ "		<oas:Security>" + LINE_DELEMETER 
		+ "			<oas:UsernameToken>" + LINE_DELEMETER 
		+ "				<oas:Username>%s</oas:Username>" + LINE_DELEMETER 
		+ "				<oas:Password>%s</oas:Password>" + LINE_DELEMETER 
		+ "			</oas:UsernameToken>" + LINE_DELEMETER 
		+ "		</oas:Security>" + LINE_DELEMETER 
		+ "	</soapenv:Header>" + LINE_DELEMETER 
		+ "	<soapenv:Body>" + LINE_DELEMETER 
		+ "		<sdp:%s>" + LINE_DELEMETER
		+ "%s"
		+ "		</sdp:%s>" + LINE_DELEMETER
		+ "	</soapenv:Body>" + LINE_DELEMETER 
		+ "</soapenv:Envelope>";	

	public static final String SOAP_PARAM_FORMAT = "			<sdp:%s>%s</sdp:%s>" + LINE_DELEMETER;
}
