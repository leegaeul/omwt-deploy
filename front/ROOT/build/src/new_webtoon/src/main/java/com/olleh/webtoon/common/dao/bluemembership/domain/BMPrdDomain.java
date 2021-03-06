package com.olleh.webtoon.common.dao.bluemembership.domain;

import java.io.Serializable;
import com.olleh.webtoon.common.dao.bluemembership.code.PrdCode;

/**
 * 블루멤버십 상품 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class BMPrdDomain implements Serializable
{

	/** serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** 상품순번. */
	private PrdCode				prdcode;

	/** 상품명 */
	private String				prdname;

	/** 가격. */
	private Integer				price;

	/** 처음결제가격. */
	private Integer				firstprice;

	/** 이용기간. */
	private Integer				useterm;

	/** 블루베리갯수 */
	private Integer				blueberrycnt;

	/** 가입유지기간. */
	private Integer				maintainperiod;

	/** 등록자ID. */
	private String				regid;

	/** 등록일시. */
	private String				regdt;

	/** 수정자ID. */
	private String				modid;

	/** 수정일시. */
	private String				moddt;


	/**
	 * 생성자.
	 */
	public BMPrdDomain()
	{
	}


	public BMPrdDomain( PrdCode prdcode )
	{
		setPrdcode( prdcode );
	}


	/**
	 * 가격을 설정합니다..
	 * 
	 * @param price
	 *            가격
	 */
	public void setPrice( Integer price )
	{
		this.price = price;
	}


	/**
	 * 가격을 가져옵니다..
	 * 
	 * @return 가격
	 */
	public Integer getPrice()
	{
		return this.price;
	}


	/**
	 * 처음결제가격을 설정합니다..
	 * 
	 * @param firstprice
	 *            처음결제가격
	 */
	public void setFirstprice( Integer firstprice )
	{
		this.firstprice = firstprice;
	}


	/**
	 * 처음결제가격을 가져옵니다..
	 * 
	 * @return 처음결제가격
	 */
	public Integer getFirstprice()
	{
		return this.firstprice;
	}


	/**
	 * 가입유지기간을 설정합니다..
	 * 
	 * @param maintainperiod
	 *            가입유지기간
	 */
	public void setMaintainperiod( Integer maintainperiod )
	{
		this.maintainperiod = maintainperiod;
	}


	/**
	 * 가입유지기간을 가져옵니다..
	 * 
	 * @return 가입유지기간
	 */
	public Integer getMaintainperiod()
	{
		return this.maintainperiod;
	}


	/**
	 * 등록자ID을 설정합니다..
	 * 
	 * @param regid
	 *            등록자ID
	 */
	public void setRegid( String regid )
	{
		this.regid = regid;
	}


	/**
	 * 등록자ID을 가져옵니다..
	 * 
	 * @return 등록자ID
	 */
	public String getRegid()
	{
		return this.regid;
	}


	/**
	 * 등록일시을 설정합니다..
	 * 
	 * @param regdt
	 *            등록일시
	 */
	public void setRegdt( String regdt )
	{
		this.regdt = regdt;
	}


	/**
	 * 등록일시을 가져옵니다..
	 * 
	 * @return 등록일시
	 */
	public String getRegdt()
	{
		return this.regdt;
	}


	/**
	 * 수정자ID을 설정합니다..
	 * 
	 * @param modid
	 *            수정자ID
	 */
	public void setModid( String modid )
	{
		this.modid = modid;
	}


	/**
	 * 수정자ID을 가져옵니다..
	 * 
	 * @return 수정자ID
	 */
	public String getModid()
	{
		return this.modid;
	}


	/**
	 * 수정일시을 설정합니다..
	 * 
	 * @param moddt
	 *            수정일시
	 */
	public void setModdt( String moddt )
	{
		this.moddt = moddt;
	}


	/**
	 * 수정일시을 가져옵니다..
	 * 
	 * @return 수정일시
	 */
	public String getModdt()
	{
		return this.moddt;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals( Object obj )
	{
		if( this == obj )
		{
			return true;
		}
		if( obj == null )
		{
			return false;
		}
		if( getClass() != obj.getClass() )
		{
			return false;
		}
		BMPrdDomain other = (BMPrdDomain)obj;
		if( prdcode == null )
		{
			if( other.prdcode != null )
			{
				return false;
			}
		}
		else if( !prdcode.equals( other.prdcode ) )
		{
			return false;
		}
		return true;
	}


	public String getPrdname()
	{
		return prdname;
	}


	public void setPrdname( String prdname )
	{
		this.prdname = prdname;
	}


	public Integer getUseterm()
	{
		return useterm;
	}


	public void setUseterm( Integer useterm )
	{
		this.useterm = useterm;
	}


	public Integer getBlueberrycnt()
	{
		return blueberrycnt;
	}


	public void setBlueberrycnt( Integer blueberrycnt )
	{
		this.blueberrycnt = blueberrycnt;
	}


	public PrdCode getPrdcode()
	{
		return prdcode;
	}


	public void setPrdcode( PrdCode prdcode )
	{
		this.prdcode = prdcode;
	}

}
