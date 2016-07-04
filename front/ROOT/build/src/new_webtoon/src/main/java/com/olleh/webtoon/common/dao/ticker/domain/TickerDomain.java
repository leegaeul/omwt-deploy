package com.olleh.webtoon.common.dao.ticker.domain;

public class TickerDomain
{
	
	private int tickerseq;	      //티커순번
	private String displayfg;	//전시구분 (pc: PC버전 티커, mobile: 모바일버전 티커)
	private String displayyn;	//전시여부 (Y/N)
	private String startdt;		//전시시작일시 (YYYYMMDDHHMMSS)
	private String enddt;		//전시종료일시 (YYYYMMDDHHMMSS)
	private String contents; 	//티커내용
	private String newwindowyn; // 새창으로 띄울지 여부
	private String linkurl; //링크 URL
	private String regid;        //등록자ID
	private String regdt;		  //등록일시 (YYYYMMDDHHMMSS)
	private String modid;	 //수정자ID
	private String moddt;   //수정일시 (YYYYMMDDHHMMSS)
	public int getTickerseq()
	{
		return tickerseq;
	}
	public void setTickerseq( int tickerseq )
	{
		this.tickerseq = tickerseq;
	}
	public String getDisplayfg()
	{
		return displayfg;
	}
	public void setDisplayfg( String displayfg )
	{
		this.displayfg = displayfg;
	}
	public String getDisplayyn()
	{
		return displayyn;
	}
	public void setDisplayyn( String displayyn )
	{
		this.displayyn = displayyn;
	}
	public String getStartdt()
	{
		return startdt;
	}
	public void setStartdt( String startdt )
	{
		this.startdt = startdt;
	}
	public String getEnddt()
	{
		return enddt;
	}
	public void setEnddt( String enddt )
	{
		this.enddt = enddt;
	}
	public String getContents()
	{
		return contents;
	}
	public void setContents( String contents )
	{
		this.contents = contents;
	}
	public String getNewwindowyn()
	{
		return newwindowyn;
	}
	public void setNewwindowyn( String newwindowyn )
	{
		this.newwindowyn = newwindowyn;
	}
	public String getLinkurl()
	{
		return linkurl;
	}
	public void setLinkurl( String linkurl )
	{
		this.linkurl = linkurl;
	}
	public String getRegid()
	{
		return regid;
	}
	public void setRegid( String regid )
	{
		this.regid = regid;
	}
	public String getRegdt()
	{
		return regdt;
	}
	public void setRegdt( String regdt )
	{
		this.regdt = regdt;
	}
	public String getModid()
	{
		return modid;
	}
	public void setModid( String modid )
	{
		this.modid = modid;
	}
	public String getModdt()
	{
		return moddt;
	}
	public void setModdt( String moddt )
	{
		this.moddt = moddt;
	}
	
}
