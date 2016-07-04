package com.olleh.webtoon.common.dao.bluemembership.code;

import org.codehaus.jackson.map.annotate.JsonSerialize;



/**
 * 블루멤버십 가입중 결제 등록 상태
 * 
 * @author bigmandoo
 *
 */

@JsonSerialize(using = EnumCodeSerializer.class)
public enum JoinStatusCode implements CodeBase
{
	wating( "wating", "결제등록대기" ),
	fail("fail", "가입실패"),
	changeprdfail("chgprdfail", "상품변경실패"),
	changebillfail("chgbillfail", "결제수단변경실패"),
	suspend("suspend", "정지됨"),
	active( "active", "이용중" ),
	cancel( "cancel", "해지됨" ),
	change( "change", "변경됨" ),
	reserve( "reserve", "예약됨" );

	private String								code;
	private String								text;


	private JoinStatusCode( String code, String text )
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
