package com.olleh.webtoon.common.dao.bluemembership.code;

import java.util.HashMap;
import java.util.Map;

public enum CardCode implements CodeBase
{
	keb( "01", "외환카드" ),
	lotte( "03", "롯데카드" ),
	hyundai( "04", "현대카드" ),
	kb("06", "국민카드"),
	bc( "11", "BC카드" ),
	samsung( "12", "삼성카드" ),
	lg( "13", "LG카드" ),
	shinhan( "14", "신한카드" ),
	hanask( "17", "하나SK" ),
	visa( "21", "해외비자카드" ),
	master( "22", "해외마스타카드" ),
	jcb( "23", "해외JCB카드" ),
	amex( "24", "해외아멕스카드" ),
	etc( "99", "기타카드" );
	
	private static Map<String, CardCode>	codeMap	= new HashMap<String, CardCode>();

	static
	{
		for( CardCode code : CardCode.values() )
			codeMap.put( code.getCode(), code );
	}

	private String							code;
	private String							text;


	private CardCode( String code, String text )
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

	public static CardCode toCode( String code )
	{
		return codeMap.get( code );
	}
}
