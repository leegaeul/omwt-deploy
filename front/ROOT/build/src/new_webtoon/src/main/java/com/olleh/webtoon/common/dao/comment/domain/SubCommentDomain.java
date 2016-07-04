/*****************************************************************************
 * PROJECT NAME : 올레마켓 웹툰
 * SUBSYSTEM NAME : 웹툰 대댓글 
 * FILE NAME : SubCommentDomain.java
 * DESCRIPTION : 대댓글을  전달하기 위한 도메인 모델 class.
 * 
 * VERSION   NO     author                         date                   content  -> info
 * ----------------------------------------------------------------------------
      1.0                         Sung-Ho.Yeom         2014-05-14      init
 *****************************************************************************/
package com.olleh.webtoon.common.dao.comment.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class SubCommentDomain
{
	// 대댓글 순번 (PK, 자동증가)
	private int subcommentseq;
	
	// 네임콘 순번
	private int nameconseq;
	
	// 댓글 순번 (PK, 일련번호)
	private int commentseq;
	
	// 대댓글 내용
	private String subcomment;
	
	// 삭제여부
	private String delyn;
	
	// 삭제 ID
	private String delid;
	
	// 삭제 사유
	private String delreason;
	
	// 삭제 일시
	private String deldt;
	
	// 블라인드 여부
	private String blindyn;
	
	// 자동 블라인드 여부
	private String autoblindyn;
	
	// 블라인드 사유
	private String blindreason;
	
	// 블라인드 일시
	private String blinddt;
	
	// ID 구분
	private String idfg;
	
	// 작가여부
	private String authoryn;
	
	// 등록자 ID
	private String regid;
	
	// 닉네임
	private String nickname;
	
	// 등록일시
	private String regdate;
	
	//모니터링 유저
	private String musers;
	
	private int pageSize;
	
	private int startRowNo;
	
	private String nameconurl;
	
	private String defaultyn;	// 네임콘 기본적으로 제공되는 네임콘인지 여부 (Y/N)
	
	private String mnameconurl;	// 모바일 네임콘 url
	
	private String nameconnm;
	
	private String commentconseq;
	
	private String commentconurl;
	
	private String mobileyn = "N";
	
	private String regyn;
	
	/* * * * * * * * * *  GET & SET Area * * * * * * * * * */

	// 대댓글 순번 (PK, 자동증가)
	public int getSubcommentseq() { return subcommentseq; }
	public void setSubcommentseq(int subcommentseq) { this.subcommentseq = subcommentseq; }
	
	// 네임콘 순번
	public int getNameconseq() { return nameconseq; }
	public void setNameconseq(int nameconseq) { this.nameconseq = nameconseq; }

	// 댓글 순번 (PK, 일련번호)
	public int getCommentseq() { return commentseq; }
	public void setCommentseq(int commentseq) { this.commentseq = commentseq; }

	// 대댓글 내용
	public String getSubcomment() { return subcomment; }
	public void setSubcomment(String subcomment) { this.subcomment = subcomment; }

	// 삭제여부
	public String getDelyn() { return delyn; }
	public void setDelyn(String delyn) { this.delyn = delyn; }

	// 삭제 ID
	public String getDelid() { return delid; }
	public void setDelid(String delid) { this.delid = delid; }

	// 삭제 사유
	public String getDelreason() { return delreason; }
	public void setDelreason(String delreason) { this.delreason = delreason; }

	// 삭제 일시
	public String getDeldt() { return deldt; }
	public void setDeldt(String deldt) { this.deldt = deldt; }

	// 블라인드 여부
	public String getBlindyn() { return blindyn; }
	public void setBlindyn(String blindyn) { this.blindyn = blindyn; }

	// 자동 블라인드 여부
	public String getAutoblindyn() { return autoblindyn; }
	public void setAutoblindyn(String autoblindyn) { this.autoblindyn = autoblindyn; }

	// 블라인드 사유
	public String getBlindreason() { return blindreason; }
	public void setBlindreason(String blindreason) { this.blindreason = blindreason; }

	// 블라인드 일시
	public String getBlinddt() { return blinddt; }
	public void setBlinddt(String blinddt) { this.blinddt = blinddt; }

	// ID 구분
	public String getIdfg() { return idfg; }
	public void setIdfg(String idfg) { this.idfg = idfg; }

	// 작가여부
	public String getAuthoryn() { return authoryn; }
	public void setAuthoryn(String authoryn) { this.authoryn = authoryn; }
	
	// 등록자 ID
	public String getRegid() { return regid; }
	public void setRegid(String regid) { this.regid = regid; }
	
	// 닉네임
	public String getNickname() { return nickname;}
	public void setNickname(String nickname) { this.nickname = nickname; }

	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartRowNo() {
		return startRowNo;
	}
	public void setStartRowNo(int startRowNo) {
		this.startRowNo = startRowNo;
	}
	public String getNameconurl() {
		return nameconurl;
	}
	public void setNameconurl(String nameconurl) {
		this.nameconurl = nameconurl;
	}
	public String getNameconnm() {
		return nameconnm;
	}
	public void setNameconnm(String nameconnm) {
		this.nameconnm = nameconnm;
	}
	public String getMobileyn() {
		return mobileyn;
	}
	public void setMobileyn(String mobileyn) {
		this.mobileyn = mobileyn;
	}
	public String getDefaultyn() {
		return defaultyn;
	}
	public void setDefaultyn(String defaultyn) {
		this.defaultyn = defaultyn;
	}
	public String getMnameconurl() {
		return mnameconurl;
	}
	public void setMnameconurl(String mnameconurl) {
		this.mnameconurl = mnameconurl;
	}
	public String getMusers() {
		return musers;
	}
	public void setMusers(String musers) {
		this.musers = musers;
	}
	public String getCommentconseq() {
		return commentconseq;
	}
	public void setCommentconseq(String commentconseq) {
		this.commentconseq = commentconseq;
	}
	public String getCommentconurl() {
		if(commentconurl != null && commentconurl.indexOf("http") < 0)
			commentconurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/namecon"+commentconurl;
		
		return commentconurl;
	}
	public void setCommentconurl(String commentconurl) {
		this.commentconurl = commentconurl;
	}
	public String getRegyn() {
		return regyn;
	}
	public void setRegyn(String regyn) {
		this.regyn = regyn;
	}
	
	
}