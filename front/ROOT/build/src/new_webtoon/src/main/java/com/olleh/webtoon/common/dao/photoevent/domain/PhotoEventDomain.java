package com.olleh.webtoon.common.dao.photoevent.domain;

public class PhotoEventDomain
{
	private int eventseq; 		//이벤트순번 (PK, 자동증가)
	private String eventfg; 	//이벤트 구분
	private String userid; 		//유저아이디
	private String idfg; 		//아이디구분(open:오픈아이디 olleh:올레아이디)
	private String phonenum;	//전화번호
	private String regdt;		//등록일시 (YYYYMMDDHHMMSS)
	private String winyn;		//당첨 여부(Y/null)
	private int grade;			//당첨 등수
	private int degree;			//당첨 차수
	
	public int getEventseq() {
		return eventseq;
	}
	public void setEventseq(int eventseq) {
		this.eventseq = eventseq;
	}
	public String getEventfg() {
		return eventfg;
	}
	public void setEventfg(String eventfg) {
		this.eventfg = eventfg;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getIdfg() {
		return idfg;
	}
	public void setIdfg(String idfg) {
		this.idfg = idfg;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getWinyn() {
		return winyn;
	}
	public void setWinyn(String winyn) {
		this.winyn = winyn;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}

}