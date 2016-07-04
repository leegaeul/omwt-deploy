package com.olleh.webtoon.common.dao.applay.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class EventImageDomain {
	
	private int eventseq;			   //이벤트 순번
	private int imagesize;			   //이미지 파일 사이즈
	private int displayorder;          //전시 순서
	private String imagepath;          //이미지 파일 풀 경로
	private String imagefilenm;        //이미지 원본 파일명
	
	
	public int getEventseq() {
		return eventseq;
	}
	public void setEventseq(int eventseq) {
		this.eventseq = eventseq;
	}
	public int getImagesize() {
		return imagesize;
	}
	public void setImagesize(int imagesize) {
		this.imagesize = imagesize;
	}
	public int getDisplayorder() {
		return displayorder;
	}
	public void setDisplayorder(int displayorder) {
		this.displayorder = displayorder;
	}
	public String getImagepath() {
		if(imagepath != null && imagepath.indexOf("http") < 0)
			imagepath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/event"+imagepath;
		
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getImagefilenm() {
		return imagefilenm;
	}
	public void setImagefilenm(String imagefilenm) {
		this.imagefilenm = imagefilenm;
	}
}