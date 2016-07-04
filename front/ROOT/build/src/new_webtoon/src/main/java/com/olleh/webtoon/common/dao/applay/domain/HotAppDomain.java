package com.olleh.webtoon.common.dao.applay.domain;

import com.olleh.webtoon.common.util.MessageUtil;

/**
 * @author romis
 *
 */
public class HotAppDomain {	
	private String appseq = ""; 
	private String apptitle = "";
	private String price = "";
	private String freeyn = "";
	private String thumbpath = "";
	private String titlethumbpath = "";
	
	public String getAppseq() {
		return appseq;
	}
	public void setAppseq(String appseq) {
		this.appseq = appseq;
	}
	public String getApptitle() {
		return apptitle;
	}
	public void setApptitle(String apptitle) {
		this.apptitle = apptitle;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getFreeyn() {
		return freeyn;
	}
	public void setFreeyn(String freeyn) {
		this.freeyn = freeyn;
	}
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0)
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/rcdapp"+thumbpath;
		
		return thumbpath;
	}
	public void setThumbpath(String thumbpath) {
		this.thumbpath = thumbpath;
	}
	public String getTitlethumbpath() {
		if(titlethumbpath != null && titlethumbpath.indexOf("http") < 0)
			titlethumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/rcdapp"+titlethumbpath;
		
		return titlethumbpath;
	}
	public void setTitlethumbpath(String titlethumbpath) {
		this.titlethumbpath = titlethumbpath;
	}
	
}