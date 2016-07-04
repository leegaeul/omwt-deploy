/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ToonDomain.java
 * DESCRIPTION    : 웹툰 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-23      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.service.domain;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;

import com.olleh.webtoon.common.util.MessageUtil;

public class ServiceToonImageDomain {
	
	@Value( "${serverType}" )
	private String serverType;
	
	private int imageseq;
	private int timesseq;
	private int displayorder;
	private String imagepath;
	private String imagefilenm;
	private String imagesize;
	private int width;
	private int height;
	private String regid;
	private String regdt;
	
	public int getImageseq() {
		return imageseq;
	}
	public void setImageseq(int imageseq) {
		this.imageseq = imageseq;
	}
	public int getTimesseq() {
		return timesseq;
	}
	public void setTimesseq(int timesseq) {
		this.timesseq = timesseq;
	}
	public int getDisplayorder() {
		return displayorder;
	}
	public void setDisplayorder(int displayorder) {
		this.displayorder = displayorder;
	}
	public String getImagepath() {
		if(imagepath != null && imagepath.indexOf("http") < 0){
			imagepath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/webtoon"+imagepath;
			
			/*try {
				imagepath = URLEncoder.encode(imagepath, "UTF-8");
				imagepath = "http://content.contentsbox.net/display.php?file_position=" + imagepath;
			} catch (Exception e) {imagepath = null;}
			

			if(!"real".equals(System.getProperty("serverType"))){	
				try {
					imagepath = URLEncoder.encode(imagepath, "UTF-8");
					imagepath = "http://dev.content.contentsbox.net/display.php?file_position=" + imagepath;
				} catch (Exception e) {imagepath = null;}
				
			}*/
		}
		
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
	public String getImagesize() {
		return imagesize;
	}
	public void setImagesize(String imagesize) {
		this.imagesize = imagesize;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
}