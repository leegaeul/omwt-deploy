package com.olleh.webtoon.common.dao.logo.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class LogoDomain
{
	
	private int logoseq;	      //로고순번
	private String title; 		  //제목
	private String displayfg;	//전시구분 (pc: PC버전 로고, mobile: 모바일버전 로고)
	private String displayyn;	//전시여부 (Y/N)
	private String startdt;		//전시시작일시 (YYYYMMDDHHMMSS)
	private String enddt;		//전시종료일시 (YYYYMMDDHHMMSS)
	private String imagepath; //이미지파일 풀경로
	private String imagefilenm;//이미지원본파일명
	private String regid;        //등록자ID
	private String regdt;		  //등록일시 (YYYYMMDDHHMMSS)
	private String modid;	 //수정자ID
	private String moddt;   //수정일시 (YYYYMMDDHHMMSS)
	public int getLogoseq()
	{
		return logoseq;
	}
	public void setLogoseq( int logoseq )
	{
		this.logoseq = logoseq;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle( String title )
	{
		this.title = title;
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
	public String getImagepath()
	{
		if(imagepath != null && imagepath.indexOf("http") < 0)
			imagepath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/logo"+imagepath;
		
		return imagepath;
	}
	public void setImagepath( String imagepath )
	{
		this.imagepath = imagepath;
	}
	public String getImagefilenm()
	{
		return imagefilenm;
	}
	public void setImagefilenm( String imagefilenm )
	{
		this.imagefilenm = imagefilenm;
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
