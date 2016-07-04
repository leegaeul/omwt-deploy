package com.olleh.webtoon.common.dao.bluemembership.code;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 블루멤버십 변경
 * 
 * @author bigmandoo
 *
 */
@JsonSerialize(using = EnumCodeSerializer.class)
public enum ChangeRequestCode implements CodeBase
{
	none("none", "이용중"),
	product("product", "상품변경예약"), 
	bill("bill", "결제수단변경예약"), 
	cancel("cancel", "해지예약");

	private String	code;
	private String	text;


	private ChangeRequestCode( String code, String text )
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
