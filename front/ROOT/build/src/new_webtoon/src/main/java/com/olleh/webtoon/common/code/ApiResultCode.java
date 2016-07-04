package com.olleh.webtoon.common.code;

public enum ApiResultCode
{
	success("success"),
	system_error("system_error"),
	user_not_found("user_not_found"),
	device_not_found("device_not_found"),
	parameter_error("parameter_error"),
	login_required("login_required"),
	invalid_pushkey("invalid_pushkey"),
	useagreement_required("useagreement_required");

	private String	code;


	private ApiResultCode( String code )
	{
		this.code = code;
	}


	public String getCode()
	{
		return code;
	}


	public void setCode( String code )
	{
		this.code = code;
	}


	@Override
	public String toString()
	{
		return getCode();
	}
}
