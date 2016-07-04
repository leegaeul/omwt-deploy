package com.olleh.webtoon.common.dao.api.domain;

import org.springframework.util.StringUtils;
import com.olleh.webtoon.common.code.ApiResultCode;

public class ApiResultDomain
{
	private ApiResultCode	code;			// 코드
	private String			debugMessage;	// 디버그 메세지
	private String			message;		// 메세지


	public ApiResultDomain( ApiResultCode code )
	{
		super();

		this.code = code;
	}


	public ApiResultDomain( ApiResultCode code, String debugMessage )
	{

		super();

		this.code = code;
		this.debugMessage = debugMessage;
	}


	public ApiResultDomain( ApiResultCode code, String debugMessage, String message )
	{

		super();

		this.code = code;
		this.debugMessage = debugMessage;
		this.message = message;
	}


	public ApiResultCode getCode()
	{
		return code;
	}


	public void setCode( ApiResultCode code )
	{
		this.code = code;
	}


	public String getMessage()
	{
		if( StringUtils.hasText( message ) )
			return message;

		switch( code )
		{
			case success				: return "성공";
			case system_error			: return "시스템 장애 입니다";
			case parameter_error		: return "파라미터 오류 입니다";
			case device_not_found		: return "등록된 단말이 아닙니다";
			case user_not_found			: return "등록된 사용자가 없습니다";
			case login_required			: return "로그인이 필요한 서비스 입니다";
			case useagreement_required 	: return "정보/광고 수신약관에 동의하셔야 합니다";
			default						: return "알 수 없는 오류 입니다";
		}
	}


	public String getDebugMessage()
	{
		if( StringUtils.hasText( debugMessage ) )
			return debugMessage;
		else
			return "";
	}


	public void setDebugMessage( String debugMessage )
	{
		this.debugMessage = debugMessage;
	}


	public void setMessage( String message )
	{
		this.message = message;
	}
}
