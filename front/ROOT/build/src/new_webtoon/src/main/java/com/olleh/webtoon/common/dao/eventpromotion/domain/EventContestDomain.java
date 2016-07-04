package com.olleh.webtoon.common.dao.eventpromotion.domain;

public class EventContestDomain
{
	private int contestseq;		// 이벤트순번 (PK, 자동증가)
	private String regdt;    	// 등록 날짜
	private String contestnm;   // 응모인명
	private String contestpnm;  // 응모인필명
	private String title;       // 응모작품제목
	private String email;       // 응모인 이메일
	private String phonenm;     // 응모인 핸드폰번호
	private String genretype1;  // 장르1 필수 (1:개그, 2:일상, 3:드라마, 4:액션, 5:판타지, 6:감성, 7:무제한)
	private String genretype2;  // 장르2 (1:개그, 2:일상, 3:드라마, 4:액션, 5:판타지, 6:감성, 7:무제한)
	private String synopsis;	// 응모작 시놉시스(최소50자 ~ 최대 1000자)    
	private String filepath; 	// 응모작품 파일경로
	private String filename; 	// 응모작품 파일이름
	private String statelevel;  // 응모작 상태(1D:접수,  2P:1차평가합격,  2N:1차평가불합격,  3P:2차평가합격,  3N:2차평가불합격)
	private int totalcount;		// 접수현황 카운트
	
	private int eventseq; 	   	//이벤트순번 (PK, 자동증가)
	private int commentseq; 	//댓글순번;
	private int timesseq;		//회차 순번
	private String eventfg;		//이벤트구분(comment:댓글, sticker:스티커)
	private String idfg;;	   	//ID구분 (open:오픈ID, olleh:올레ID)
	private String regid;	   	//등록자ID
	private String phonenum;   	//COMMENT
	private String winyn;      	//당첨 여부(y/n)
	
	public int getContestseq() {
		return contestseq;
	}
	public void setContestseq(int contestseq) {
		this.contestseq = contestseq;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getContestnm() {
		return contestnm;
	}
	public void setContestnm(String contestnm) {
		this.contestnm = contestnm;
	}
	public String getContestpnm() {
		return contestpnm;
	}
	public void setContestpnm(String contestpnm) {
		this.contestpnm = contestpnm;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenm() {
		return phonenm;
	}
	public void setPhonenm(String phonenm) {
		this.phonenm = phonenm;
	}
	public String getGenretype1() {
		return genretype1;
	}
	public void setGenretype1(String genretype1) {
		this.genretype1 = genretype1;
	}
	public String getGenretype2() {
		return genretype2;
	}
	public void setGenretype2(String genretype2) {
		this.genretype2 = genretype2;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getStatelevel() {
		return statelevel;
	}
	public void setStatelevel(String statelevel) {
		this.statelevel = statelevel;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getTimesseq() {
		return timesseq;
	}
	public void setTimesseq(int timesseq) {
		this.timesseq = timesseq;
	}
	public int getEventseq() {
		return eventseq;
	}
	public void setEventseq(int eventseq) {
		this.eventseq = eventseq;
	}
	public int getCommentseq() {
		return commentseq;
	}
	public void setCommentseq(int commentseq) {
		this.commentseq = commentseq;
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
	public String getEventfg() {
		return eventfg;
	}
	public void setEventfg(String eventfg) {
		this.eventfg = eventfg;
	}
	
}