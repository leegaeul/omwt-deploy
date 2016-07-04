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

public class BookmarkDomain {
	private int timesseq = 0;
	private String timesseqlist = "";
	private String idfg = "";
	private String regid = "";
	private String regdt = "";
	private int startRowNo;  	   //해당페이지 시작 Row 번호(페이지크기가 10일 경우 1페이지는 0, 2페이지는 10, 3페이지는 20)
	private int pageSize;    	   //페이지크기	
	
	
	public int getTimesseq() {
		return timesseq;
	}
	public void setTimesseq(int timesseq) {
		this.timesseq = timesseq;
	}
	public String getTimesseqlist() {
		return timesseqlist;
	}
	public void setTimesseqlist(String timesseqlist) {
		this.timesseqlist = timesseqlist;
	}
	public String getIdfg() {
		return idfg;
	}
	public void setIdfg(String idfg) {
		this.idfg = idfg;
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