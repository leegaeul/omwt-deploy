package com.olleh.webtoon.common.dao.bluemembership.code;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 블루멤버십 결제 수단
 * 
 * @author bigmandoo
 *
 */
@JsonSerialize(using = EnumCodeSerializer.class)
public enum BillMethodCode implements CodeBase
{
	card( "card", "신용카드" ),
	phone( "phone", "휴대전화" ),
	giftcard( "giftcard", "상품권" ),
	admin( "admin", "관리자가입" );

	private String								code;
	private String								text;


	private BillMethodCode( String code, String text )
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
