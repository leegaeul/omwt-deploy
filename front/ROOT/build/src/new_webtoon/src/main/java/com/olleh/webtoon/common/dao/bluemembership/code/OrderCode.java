package com.olleh.webtoon.common.dao.bluemembership.code;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 블루베리 주문 구분
 * 
 * @author bigmandoo
 *
 */

@JsonSerialize(using = EnumCodeSerializer.class)
public enum OrderCode implements CodeBase
{
	order( "order", "주문" ),
	refund( "refund", "차감" ),
	cancel( "cancel", "취소" ),
	expire( "expire", "만료" );

	private String								code;
	private String								text;


	private OrderCode( String code, String text )
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
