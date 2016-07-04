package com.olleh.webtoon.common.dao.bluemembership.code;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 블루베리 사용 구분
 * 
 * @author bigmandoo
 *
 */
@JsonSerialize(using = EnumCodeSerializer.class)
public enum OrderUseCode implements CodeBase
{
	charge("charge", "결제"), 
	buy("buy", "구매");

	private String	code;
	private String	text;


	private OrderUseCode( String code, String text )
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
