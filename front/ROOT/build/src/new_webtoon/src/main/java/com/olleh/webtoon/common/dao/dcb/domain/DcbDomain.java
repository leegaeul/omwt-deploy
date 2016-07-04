package com.olleh.webtoon.common.dao.dcb.domain;

public class DcbDomain {

	private String deviceid;		//단말아이디(UUID)
	private String regid;			//쿠폰 등록 아이디
	private String idfg;			//크폰 등록 아이디구분(open:오픈아이디 olleh:올레아이디)
	private String couponnum;		//쿠폰번호
	private String regdt;			//쿠폰 등록 날짜
	private String issuedt;			//쿠폰 발급 날짜
	
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getIdfg() {
		return idfg;
	}
	public void setIdfg(String idfg) {
		this.idfg = idfg;
	}
	public String getCouponnum() {
		return couponnum;
	}
	public void setCouponnum(String couponnum) {
		this.couponnum = couponnum;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getIssuedt() {
		return issuedt;
	}
	public void setIssuedt(String issuedt) {
		this.issuedt = issuedt;
	}	
}