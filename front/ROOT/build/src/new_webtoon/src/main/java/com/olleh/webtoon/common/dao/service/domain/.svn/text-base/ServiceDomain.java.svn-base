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

public class ServiceDomain {

	@Value( "${serverType}" )
	private String serverType;
	
	private int webtoonseq;
	private String publishdt;
	private String thumbpath;
	private String webtoonnm;
	private String authornm1;
	private String authornm2;
	private String authornm3;
	private String authorurl1;
	private String authorurl2;
	private String authorurl3;
	private String genrenm1;
	private String genrenm2;
	private String genrenm3;
	private String webtoondesc;  
	private String episodeurl;
	private String adult_content;
	private String series_completed;
	private String sale_price;
	
	public int getWebtoonseq() {
		return webtoonseq;
	}
	public void setWebtoonseq(int webtoonseq) {
		this.webtoonseq = webtoonseq;
	}
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0){
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+thumbpath;
			
			/*try {
				thumbpath = URLEncoder.encode(thumbpath, "UTF-8");
				thumbpath = "http://content.contentsbox.net/display.php?file_position=" + thumbpath;
			} catch (Exception e) {thumbpath = null;}
			
			if(!"real".equals(System.getProperty("serverType"))){
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
	public String getWebtoonnm() {
		return webtoonnm;
	}
	public void setWebtoonnm(String webtoonnm) {
		this.webtoonnm = webtoonnm;
	}
	public String getAuthornm1() {
		return authornm1;
	}
	public void setAuthornm1(String authornm1) {
		this.authornm1 = authornm1;
	}
	public String getAuthornm2() {
		return authornm2;
	}
	public void setAuthornm2(String authornm2) {
		this.authornm2 = authornm2;
	}
	public String getAuthornm3() {
		return authornm3;
	}
	public void setAuthornm3(String authornm3) {
		this.authornm3 = authornm3;
	}
	public String getGenrenm1() {
		return genrenm1;
	}
	public void setGenrenm1(String genrenm1) {
		this.genrenm1 = genrenm1;
	}
	public String getGenrenm2() {
		return genrenm2;
	}
	public void setGenrenm2(String genrenm2) {
		this.genrenm2 = genrenm2;
	}
	public String getGenrenm3() {
		return genrenm3;
	}
	public void setGenrenm3(String genrenm3) {
		this.genrenm3 = genrenm3;
	}
	public String getPublishdt() {
		return publishdt;
	}
	public void setPublishdt(String publishdt) {
		this.publishdt = publishdt;
	}
	public String getAuthorurl1() {
		return authorurl1 == null ? "" : authorurl1;
	}
	public void setAuthorurl1(String authorurl1) {
		this.authorurl1 = authorurl1;
	}
	public String getAuthorurl2() {
		return authorurl2 == null ? "" : authorurl2;
	}
	public void setAuthorurl2(String authorurl2) {
		this.authorurl2 = authorurl2;
	}
	public String getAuthorurl3() {
		return authorurl3 == null ? "" : authorurl3;
	}
	public void setAuthorurl3(String authorurl3) {
		this.authorurl3 = authorurl3;
	}
	public String getWebtoondesc() {
		return webtoondesc == null ? "" : webtoondesc;
	}
	public void setWebtoondesc(String webtoondesc) {
		this.webtoondesc = webtoondesc;
	}
	public String getEpisodeurl() {
		return episodeurl == null ? "" : episodeurl;
	}
	public void setEpisodeurl(String episodeurl) {
		this.episodeurl = episodeurl;
	}
	public String getAdult_content() {
		return adult_content;
	}
	public void setAdult_content(String adult_content) {
		this.adult_content = adult_content;
	}
	public String getSeries_completed() {
		return series_completed;
	}
	public void setSeries_completed(String series_completed) {
		this.series_completed = series_completed;
	}
	public String getSale_price() {
		return sale_price;
	}
	public void setSale_price(String sale_price) {
		this.sale_price = sale_price.replaceAll(",", "");
	}
}