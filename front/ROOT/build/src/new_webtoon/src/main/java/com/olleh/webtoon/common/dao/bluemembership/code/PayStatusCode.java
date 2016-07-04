package com.olleh.webtoon.common.dao.bluemembership.code;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 블루멤버십 가입상태
 * 
 * @author bigmandoo
 *
 */
@JsonSerialize(using = EnumCodeSerializer.class)
public enum PayStatusCode implements CodeBase
{
	standby("standby", "결제등록대기"), 
	complete("complete", "결제등록완료"), 
	fail("fail", "결제등록실패"), 
	cancel("cancel", "취소");

	private String	code;
	private String	text;


	private PayStatusCode( String code, String text )
	{
		this.code = code;
		this.text = text;
	}


	public String getCode()
	{
		return code;
	}


	public String getText()
	{
		return text;
	}
}
