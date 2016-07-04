package com.olleh.webtoon.common.dao.ranking.domain;


public class RankingDomain {

	private int startRowNo;	     //시작 순번
	private int pageSize;        //페이지 사이즈
	private String mobileyn;     //모바일 여부
	
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
	public String getMobileyn() {
		return mobileyn;
	}
	public void setMobileyn(String mobileyn) {
		this.mobileyn = mobileyn;
	}
}