package com.olleh.webtoon.common.dao.banner.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class BannerDomain {
	
	private int bannerorder;	//배너순서
	private String title; 		//배너 제목
	private String imagepath;	//이미지파일 풀경로
	private String bannerfg;	//배너구분 (pcmain:PC메인상단, event:이벤트상단)
	private String startdt;		//전시시작일시 (YYYYMMDDHHMMSS)
	private String enddt;		//전시종료일시 (YYYYMMDDHHMMSS)
	private String newwindowyn;	//배너 클릭시 새창으로 띄울지 여부 (Y/N)
	private String linkfg;;		//링크구분 (web:웹페이지, webtoon:작품메인, times:작품회차, app:앱상세)
	private String linkurl;		//배너 링크 URL
	
	
	public int getBannerorder() {
		return bannerorder;
	}
	public void setBannerorder(int bannerorder) {
		this.bannerorder = bannerorder;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImagepath() {
		if(imagepath != null && imagepath.indexOf("http") < 0)
			imagepath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/banner"+imagepath;
		
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getBannerfg() {
		return bannerfg;
	}
	public void setBannerfg(String bannerfg) {
		this.bannerfg = bannerfg;
	}
	public String getStartdt() {
		return startdt;
	}
	public void setStartdt(String startdt) {
		this.startdt = startdt;
	}
	public String getEnddt() {
		return enddt;
	}
	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}
	public String getNewwindowyn() {
		return newwindowyn;
	}
	public void setNewwindowyn(String newwindowyn) {
		this.newwindowyn = newwindowyn;
	}
	public String getLinkfg() {
		return linkfg;
	}
	public void setLinkfg(String linkfg) {
		this.linkfg = linkfg;
	}
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
}