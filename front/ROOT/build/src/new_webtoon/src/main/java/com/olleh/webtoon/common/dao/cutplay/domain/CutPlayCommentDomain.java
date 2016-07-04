package com.olleh.webtoon.common.dao.cutplay.domain;

public class CutPlayCommentDomain
{
	private int startRowNo;
	private int pageSize;
	private int commentseq;
	private int eventseq;
	private int nameconseq;
	private String comment;
	private String delyn;
	private String idfg;
	private String regid;
	private String regyn;
	private String nickname;
	private String regdt;
	private String nameconurl;
	private String mnameconurl;
	
	public int getCommentseq() {
		return commentseq;
	}
	public void setCommentseq(int commentseq) {
		this.commentseq = commentseq;
	}
	public int getEventseq() {
		return eventseq;
	}
	public void setEventseq(int eventseq) {
		this.eventseq = eventseq;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public int getNameconseq() {
		return nameconseq;
	}
	public void setNameconseq(int nameconseq) {
		this.nameconseq = nameconseq;
	}
	public String getNameconurl() {
		return nameconurl;
	}
	public void setNameconurl(String nameconurl) {
		this.nameconurl = nameconurl;
	}
	public String getMnameconurl() {
		return mnameconurl;
	}
	public void setMnameconurl(String mnameconurl) {
		this.mnameconurl = mnameconurl;
	}
	public String getRegyn() {
		return regyn;
	}
	public void setRegyn(String regyn) {
		this.regyn = regyn;
	}
	
}