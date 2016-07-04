package com.olleh.webtoon.exception;

import com.olleh.webtoon.common.dao.api.domain.ApiResultDomain;

@SuppressWarnings( "serial" )
public class ApiException extends RuntimeException
{
	private ApiResultDomain	result;


	public ApiException( ApiResultDomain result )
	{
		super();
		this.result = result;
	}


	public ApiResultDomain getResult()
	{
		return result;
	}
}
