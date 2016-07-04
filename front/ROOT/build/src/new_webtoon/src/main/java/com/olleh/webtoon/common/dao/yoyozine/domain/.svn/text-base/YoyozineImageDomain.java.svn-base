/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 운영자
 * FILE NAME      : YoyozineImageDomain.java
 * DESCRIPTION    : 요요진이미지 테이블 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        						     2014-04-21      init
 *****************************************************************************/
package com.olleh.webtoon.common.dao.yoyozine.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class YoyozineImageDomain
{

	private int		imageseq;		// 이미지순번 (PK, 자동증가)
	private int		yoyozineseq;	// YOYOZINE순번 (FK)
	private int		displayorder;	// 전시순서 
	private String   imagepath; //이미지파일풀경로
	private String	imagefilename;	// 이미지 원본 파일명
	private int		imagesize;		//이미지파일 사이즈 
	private String	regid;		// 등록한 운영자ID
	private String	regdt;		// 등록일시 (YYYYMMDDHHMMSS)
	public int getImageseq()
	{
		return imageseq;
	}
	public void setImageseq( int imageseq )
	{
		this.imageseq = imageseq;
	}
	public int getYoyozineseq()
	{
		return yoyozineseq;
	}
	public void setYoyozineseq( int yoyozineseq )
	{
		this.yoyozineseq = yoyozineseq;
	}
	public int getDisplayorder()
	{
		return displayorder;
	}
	public void setDisplayorder( int displayorder )
	{
		this.displayorder = displayorder;
	}
	public String getImagepath()
	{
		if(imagepath != null && imagepath.indexOf("http") < 0)
			imagepath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/zine"+imagepath;
		
		return imagepath;
	}
	public void setImagepath( String imagepath )
	{
		this.imagepath = imagepath;
	}
	public String getImagefilename()
	{
		return imagefilename;
	}
	public void setImagefilename( String imagefilename )
	{
		this.imagefilename = imagefilename;
	}
	public int getImagesize()
	{
		return imagesize;
	}
	public void setImagesize( int imagesize )
	{
		this.imagesize = imagesize;
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
	
	

}
