package com.olleh.webtoon.common.dao.eventpromotion.domain;

public class EventCouponDomain
{
	private String couponnum;   //쿠폰 번호
	private String idfg;        //ID구분 (open:오픈ID, olleh:올레ID, 복합PK)
	private String regid;       //등록자ID
	private String regdt;       //등록일시 (YYYYMMDDHHMMSS)
	private String useyn;       //사용여부(Y/null)
	private String phonenum;	//휴대전화번호
	
	public String getCouponnum() {
		return couponnum;
	}
	public void setCouponnum(String couponnum) {
		this.couponnum = couponnum;
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
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	  
}