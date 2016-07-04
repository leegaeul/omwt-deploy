/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : UserDomain.java
 * DESCRIPTION    : 회원 테이블 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-16      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.user.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.olleh.webtoon.common.util.MessageUtil;

public class IconDomain {
	
	protected static Log logger = LogFactory.getLog(IconDomain.class);
	
	private int iconseq = 0;
	private String defaultyn = "";
	private String iconnm = "";
	private String miconurl = "";
	private String iconurl = "";
	private String delyn = "";
	
	
	public int getIconseq() {
		return iconseq;
	}
	public void setIconseq(int iconseq) {
		this.iconseq = iconseq;
	}
	public String getDefaultyn() {
		return defaultyn;
	}
	public void setDefaultyn(String defaultyn) {
		this.defaultyn = defaultyn;
	}
	public String getIconnm() {
		return iconnm;
	}
	public void setIconnm(String iconnm) {
		this.iconnm = iconnm;
	}
	public String getIconurl() {
		if(iconurl != null && iconurl.indexOf("http") < 0)
			iconurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/namecon"+iconurl;
		
		return iconurl;
	}
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}
	public String getMiconurl() {
		if(miconurl != null && miconurl.indexOf("http") < 0)
			miconurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/namecon"+miconurl;
		
		return miconurl;
	}
	public void setMiconurl(String miconurl) {
		this.miconurl = miconurl;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}

}