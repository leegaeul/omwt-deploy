/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : SnsDomain.java
 * DESCRIPTION    : sns연동정보  전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0               Hyunmi Kim         2013-07-01      init
 *****************************************************************************/
package com.olleh.webtoon.common.dao.applay.domain;

public class SnsDomain {
	private int snsseq;			//SNS 순번
	private int articleseq;		//작품,회차 순번
	private String articlefg;	//작품,회차 구분(ex>1:작품, 2:회차, 3:요요진) -> 파악 후 적용
	private String idfg;        //ID구분 (open:오픈ID, olleh:올레ID)
	private String regid;		//등록ID
	private String regdt;		//등록일
	private String snsfg;		//SNS구분 (1:facebook, 2:twitter, 3:URL복사)
	
	
	public int getSnsseq() {
		return snsseq;
	}
	public void setSnsseq(int snsseq) {
		this.snsseq = snsseq;
	}
	public int getArticleseq() {
		return articleseq;
	}
	public void setArticleseq(int articleseq) {
		this.articleseq = articleseq;
	}
	public String getArticlefg() {
		return articlefg;
	}
	public void setArticlefg(String articlefg) {
		this.articlefg = articlefg;
	}
	public String getIdfg() {
		return idfg;
	}
	public void setIdfg(String idfg) {
		this.idfg = idfg;
	}
	public String getSnsfg() {
		return snsfg;
	}
	public void setSnsfg(String snsfg) {
		this.snsfg = snsfg;
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