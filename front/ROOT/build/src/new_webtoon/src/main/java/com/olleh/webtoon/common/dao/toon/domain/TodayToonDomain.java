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

package com.olleh.webtoon.common.dao.toon.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class TodayToonDomain {
	private String timesseq = "";
	private String webtoonnm = "";
	private String locationfg = "";
	private String itemorder = "";
	private String thumbpath = "";
	private String timestitle = "";
	private String lastyn = "";
	private String totalstickercnt = "";
	private String webtoonseq = "";
	private String regdt = "";
	private String authornm1 = ""; 
	private String authornm2 = ""; 
	private String authornm3 = ""; 
	private String serialfg = ""; 
	private String agefg = "";        
	private String upyn = "";
	private String endyn = "";
	private String newyn = "";
	private String restyn = "";
	
	private String oniconurl;
	private String officonurl;
	private String listiconurl;
	private String defaultyn;
	private int weekorder;
	
	public String getTimesseq() {
		return timesseq;
	}
	public void setTimesseq(String timesseq) {
		this.timesseq = timesseq;
	}
	public String getLocationfg() {
		return locationfg;
	}
	public void setLocationfg(String locationfg) {
		this.locationfg = locationfg;
	}	
	public String getItemorder() {
		return itemorder;
	}
	public void setItemorder(String itemorder) {
		this.itemorder = itemorder;
	}
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0)
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+thumbpath;
		
		return thumbpath;
	}
	public void setThumbpath(String thumbpath) {
		this.thumbpath = thumbpath;
	}
	public String getTimestitle() {
		return timestitle;
	}
	public void setTimestitle(String timestitle) {
		this.timestitle = timestitle;
	}
	public String getLastyn() {
		return lastyn;
	}
	public void setLastyn(String lastyn) {
		this.lastyn = lastyn;
	}
	public String getTotalstickercnt() {
		return totalstickercnt;
	}
	public void setTotalstickercnt(String totalstickercnt) {
		this.totalstickercnt = totalstickercnt;
	}
	public String getWebtoonseq() {
		return webtoonseq;
	}
	public void setWebtoonseq(String webtoonseq) {
		this.webtoonseq = webtoonseq;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
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
	public String getSerialfg() {
		return serialfg;
	}
	public void setSerialfg(String serialfg) {
		this.serialfg = serialfg;
	}
	public String getAgefg() {
		return agefg;
	}
	public void setAgefg(String agefg) {
		this.agefg = agefg;
	}
	public String getUpyn() {
		return upyn;
	}
	public void setUpyn(String upyn) {
		this.upyn = upyn;
	}
	public String getNewyn() {
		return newyn;
	}
	public void setNewyn(String newyn) {
		this.newyn = newyn;
	}
	public String getRestyn() {
		return restyn;
	}
	public void setRestyn(String restyn) {
		this.restyn = restyn;
	}
	public String getOniconurl() {
		return oniconurl;
	}
	public void setOniconurl(String oniconurl) {
		this.oniconurl = oniconurl;
	}
	public String getOfficonurl() {
		return officonurl;
	}
	public void setOfficonurl(String officonurl) {
		this.officonurl = officonurl;
	}
	public String getListiconurl() {			
		if(listiconurl != null && listiconurl.indexOf("http") < 0 && defaultyn!= null && "N".equals(defaultyn))
			listiconurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/sticker"+listiconurl;
		
		return listiconurl;
	}
	public void setListiconurl(String listiconurl) {
		this.listiconurl = listiconurl;
	}
	public String getDefaultyn() {
		return defaultyn;
	}
	public void setDefaultyn(String defaultyn) {
		this.defaultyn = defaultyn;
	}
	public String getWebtoonnm() {
		return webtoonnm;
	}
	public void setWebtoonnm(String webtoonnm) {
		this.webtoonnm = webtoonnm;
	}
	public int getWeekorder() {
		return weekorder;
	}
	public void setWeekorder(int weekorder) {
		this.weekorder = weekorder;
	}
	public String getEndyn() {
		return endyn;
	}
	public void setEndyn(String endyn) {
		this.endyn = endyn;
	}
	
}