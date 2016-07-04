/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : GenreDomain.java
 * DESCRIPTION    : 웹툰 장르 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-23      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.toon.domain;

public class GenreDomain {
	private int genreseq  ;
	private String genrenm   ;
	private String genredesc ;
	private String displayyn ;
	private String genreorder;
	private String regid     ;
	private String regdt     ;
	private String modid     ;
	private String moddt     ;
	
	public int getGenreseq() {
		return genreseq;
	}
	public void setGenreseq(int genreseq) {
		this.genreseq = genreseq;
	}
	public String getGenrenm() {
		return genrenm;
	}
	public void setGenrenm(String genrenm) {
		this.genrenm = genrenm;
	}
	public String getGenredesc() {
		return genredesc;
	}
	public void setGenredesc(String genredesc) {
		this.genredesc = genredesc;
	}
	public String getDisplayyn() {
		return displayyn;
	}
	public void setDisplayyn(String displayyn) {
		this.displayyn = displayyn;
	}
	public String getGenreorder() {
		return genreorder;
	}
	public void setGenreorder(String genreorder) {
		this.genreorder = genreorder;
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
	public String getModid() {
		return modid;
	}
	public void setModid(String modid) {
		this.modid = modid;
	}
	public String getModdt() {
		return moddt;
	}
	public void setModdt(String moddt) {
		this.moddt = moddt;
	}
}