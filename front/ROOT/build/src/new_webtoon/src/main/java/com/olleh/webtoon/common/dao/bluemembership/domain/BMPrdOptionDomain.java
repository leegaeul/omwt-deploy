package com.olleh.webtoon.common.dao.bluemembership.domain;

import com.olleh.webtoon.common.dao.bluemembership.code.PrdCode;

public class BMPrdOptionDomain
{
	private PrdCode prdcode;
	private Integer	prdseq;

	
	public BMPrdOptionDomain()
	{
		// TODO Auto-generated constructor stub
	}
	
	
	public BMPrdOptionDomain( PrdCode prdcode )
	{
		setPrdcode( prdcode );
	}

	
	public BMPrdOptionDomain( PrdCode prdcode, Integer optionprdseq )
	{
		setPrdcode( prdcode );
		setPrdseq( optionprdseq );
	}
	

	public PrdCode getPrdcode()
	{
		return prdcode;
	}


	public void setPrdcode( PrdCode prdcode )
	{
		this.prdcode = prdcode;
	}


	public Integer getPrdseq() {
		return prdseq;
	}


	public void setPrdseq(Integer prdseq) {
		this.prdseq = prdseq;
	}

}
