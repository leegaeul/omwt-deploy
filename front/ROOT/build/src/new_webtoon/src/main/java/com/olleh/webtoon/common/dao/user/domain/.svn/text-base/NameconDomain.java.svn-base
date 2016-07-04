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

public class NameconDomain {
	
	protected static Log logger = LogFactory.getLog(NameconDomain.class);
	
	private int nameconseq = 0;
	private int startRowNo;				//시작순번
	private int pageSize;				//페이지사이즈	
	private String defaultyn = "";
	private String authoryn = "";
	private String nameconnm = "";
	private String nameconurl = "";
	private String mnameconurl = "";
	
	
	public int getNameconseq() {
		return nameconseq;
	}
	public void setNameconseq(int nameconseq) {
		this.nameconseq = nameconseq;
	}
	public String getDefaultyn() {
		return defaultyn;
	}
	public void setDefaultyn(String defaultyn) {
		this.defaultyn = defaultyn;
	}
	public String getAuthoryn() {
		return authoryn;
	}
	public void setAuthoryn(String authoryn) {
		this.authoryn = authoryn;
	}
	public String getNameconnm() {
		return nameconnm;
	}
	public void setNameconnm(String nameconnm) {
		this.nameconnm = nameconnm;
	}
	public String getNameconurl() {
		return nameconurl;
	}
	public void setNameconurl(String nameconurl) {
		if(nameconurl != null && nameconurl.indexOf("/images/") < 0)
			nameconurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/namecon"+nameconurl;
		
		this.nameconurl = nameconurl;
	}
	public String getMnameconurl() {
		return mnameconurl;
	}
	public void setMnameconurl(String mnameconurl) {
		if(mnameconurl != null && mnameconurl.indexOf("/images/") < 0)
			mnameconurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/namecon"+mnameconurl;
		
		this.mnameconurl = mnameconurl;
	}
	public int getStartRowNo() {
		return startRowNo;
	}
	public void setStartRowNo(int startRowNo) {
		this.startRowNo = startRowNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
				
}