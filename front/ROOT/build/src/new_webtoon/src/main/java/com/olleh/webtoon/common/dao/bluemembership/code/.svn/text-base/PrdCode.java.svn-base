package com.olleh.webtoon.common.dao.bluemembership.code;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 블루멤버십 가입상태
 * 
 * @author bigmandoo
 *
 */
@JsonSerialize(using = EnumCodeSerializer.class)
public enum PrdCode implements CodeBase
{
	BM30("BM30", "블루멤버십 30"), BM50("BM50", "블루멤버십 50"), BMADMIN("BMADMIN", "관리자 가입상품");

	private String	code;
	private String	text;


	private PrdCode( String code, String text )
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
