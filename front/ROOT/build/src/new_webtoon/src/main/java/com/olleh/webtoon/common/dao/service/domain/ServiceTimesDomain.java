/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ServiceController.java
 * DESCRIPTION    : B2B 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2015-05-07      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.service.domain;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;

import com.olleh.webtoon.common.util.MessageUtil;


public class ServiceTimesDomain {

	@Value( "${serverType}" )
	private String serverType;
	
	private int timesseq;
	private int webtoonseq;
	private String timestitle;
	private String thumbpath;
	private String linkurl;
	private String publishdt;
	private String sale_price;
	private String free;
	
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0){
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+thumbpath;
			
			/*try {
				thumbpath = URLEncoder.encode(thumbpath, "UTF-8");
				thumbpath = "http://content.contentsbox.net/display.php?file_position=" + thumbpath;
			} catch (Exception e) {thumbpath = null;}
			
			if(!"real".equals(serverType)){
				try {
					thumbpath = URLEncoder.encode(thumbpath, "UTF-8");
					thumbpath = "http://dev.content.contentsbox.net/display.php?file_position=" + thumbpath;
				} catch (Exception e) {thumbpath = null;}
			}*/
		}
		
		return thumbpath;
	}
	public void setThumbpath(String thumbpath) {
		this.thumbpath = thumbpath;
	}
	public int getTimesseq() {
		return timesseq;
	}
	public void setTimesseq(int timesseq) {
		this.timesseq = timesseq;
	}
	public String getTimestitle() {
		return timestitle;
	}
	public void setTimestitle(String timestitle) {
		this.timestitle = timestitle;
	}
	public String getPublishdt() {
		return publishdt;
	}
	public void setPublishdt(String publishdt) {
		this.publishdt = publishdt;
	}
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
	public int getWebtoonseq() {
		return webtoonseq;
	}
	public void setWebtoonseq(int webtoonseq) {
		this.webtoonseq = webtoonseq;
	}
	public String getSale_price() {
		return sale_price;
	}
	public void setSale_price(String sale_price) {
		this.sale_price = sale_price.replaceAll(",", "");
	}
	public String getFree() {
		return free;
	}
	public void setFree(String free) {
		this.free = free;
	}
}