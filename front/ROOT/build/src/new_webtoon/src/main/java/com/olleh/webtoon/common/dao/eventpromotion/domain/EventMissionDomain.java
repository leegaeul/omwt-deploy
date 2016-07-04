package com.olleh.webtoon.common.dao.eventpromotion.domain;

public class EventMissionDomain
{
	private int eventseq; 	   // 이벤트순번 (PK, 자동증가)
	private String idfg;       // 아이디 구분
	private String regid;	   // 등록자ID
	private String regdt;      // 등록 날짜
	private String phonenum;   // 전화번호
	private String winyn;      // 당첨 여부(y/n)
	private String missionfg;  // 미션 구분값(login:출석, rumor:소문내기 sticker:스티커, comment:댓글, sns:공유하기)
	private String missionseq; // 등록 순번(snsseq, stickerseq, commentseq)
	private int grade;		   // 이벤트 등수
	private int degree;		   // 이벤트당첨 회차
	
	private int logincnt;
	private int rumorcnt;
	private int commentcnt;
	private int stickercnt;
	private int snscnt;
	private String missionseqList;
	
	public int getEventseq() {
		return eventseq;
	}
	public void setEventseq(int eventseq) {
		this.eventseq = eventseq;
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
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getWinyn() {
		return winyn;
	}
	public void setWinyn(String winyn) {
		this.winyn = winyn;
	}
	public String getMissionfg() {
		return missionfg;
	}
	public void setMissionfg(String missionfg) {
		this.missionfg = missionfg;
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
	public String getMissionseq() {
		return missionseq;
	}
	public void setMissionseq(String missionseq) {
		this.missionseq = missionseq;
	}
	public int getRumorcnt() {
		return rumorcnt;
	}
	public void setRumorcnt(int rumorcnt) {
		this.rumorcnt = rumorcnt;
	}
	public int getCommentcnt() {
		return commentcnt;
	}
	public void setCommentcnt(int commentcnt) {
		this.commentcnt = commentcnt;
	}
	public int getStickercnt() {
		return stickercnt;
	}
	public void setStickercnt(int stickercnt) {
		this.stickercnt = stickercnt;
	}
	public int getSnscnt() {
		return snscnt;
	}
	public void setSnscnt(int snscnt) {
		this.snscnt = snscnt;
	}
	public String getMissionseqList() {
		return missionseqList;
	}
	public void setMissionseqList(String missionseqList) {
		this.missionseqList = missionseqList;
	}
	public int getLogincnt() {
		return logincnt;
	}
	public void setLogincnt(int logincnt) {
		this.logincnt = logincnt;
	}
	
}