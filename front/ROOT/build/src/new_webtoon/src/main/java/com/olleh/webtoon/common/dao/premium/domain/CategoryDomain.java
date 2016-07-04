package com.olleh.webtoon.common.dao.premium.domain;


public class CategoryDomain {

	private int categoryseq;	 // 카테고리순번 (PK, 자동증가)
	private String categorynm;   //카테고리명
	private String categorydesc; //카테고리설명
	private String categoryfg; 	 //카테고리구분(premium:프리미엄웹툰 novel:웹소설)
	private String displayyn; 	 //전시여부 (Y/N)
	private int displayorder; 	 //전시순서
	private int categorycnt;
	
	public int getCategoryseq() {
		return categoryseq;
	}
	public void setCategoryseq(int categoryseq) {
		this.categoryseq = categoryseq;
	}
	public String getCategorynm() {
		return categorynm;
	}
	public void setCategorynm(String categorynm) {
		this.categorynm = categorynm;
	}
	public String getCategorydesc() {
		return categorydesc;
	}
	public void setCategorydesc(String categorydesc) {
		this.categorydesc = categorydesc;
	}
	public String getCategoryfg() {
		return categoryfg;
	}
	public void setCategoryfg(String categoryfg) {
		this.categoryfg = categoryfg;
	}
	public String getDisplayyn() {
		return displayyn;
	}
	public void setDisplayyn(String displayyn) {
		this.displayyn = displayyn;
	}
	public int getDisplayorder() {
		return displayorder;
	}
	public void setDisplayorder(int displayorder) {
		this.displayorder = displayorder;
	}
	public int getCategorycnt() {
		return categorycnt;
	}
	public void setCategorycnt(int categorycnt) {
		this.categorycnt = categorycnt;
	}
	
}