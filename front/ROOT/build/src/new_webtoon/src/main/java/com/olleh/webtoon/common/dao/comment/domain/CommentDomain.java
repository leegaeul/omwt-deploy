/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - CommentDomain.java
 * 
 * DESCRIPTION
 * - 댓글 Table Info를 전달하기 위한 Domain Model class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.comment.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class CommentDomain
{
	private int commentseq;		// 댓글순번 (PK, 자동증가)
	private String articlefg; 	// 컨텐츠구분
	private int articleseq;		// 컨텐츠 번호 (작품 : webtoonseq,  회차 : timesseq, 요요진 : yoyozineseq)
	private int nameconseq;		// 네임콘 순번
	private String defaultyn;	// 네임콘 기본적으로 제공되는 네임콘인지 여부 (Y/N)
	private String nameconnm;   // 네임콘 이름
	private String nameconurl;	// 네임콘 url
	private String mnameconurl;	// 모바일 네임콘 url
	private String comment;		// 댓글 내용
	private String delyn;		// 삭제여부
	private String deldt;		// 삭제일자
	private String delreason;	// 삭제사유
	private String recommfg;	// 추천 구분 ( 좋아요: G, 싫어요: B )	
	private int totalgoodcnt;	// 누적 좋아요 수
	private int totalbadcnt;	// 누적 싫어요 수
	private String blindyn;		// 블라인드 여부
	private String autoblindyn;	// 자동블라인드 여부
	private String blindreason;	// 블라인드 사유
	private String blinddt;		// 블라인드 일시
	private String adminreply;	// 관리자 댓글
	private String adminid;		// 관리자 ID	
	private String adminreplydt;// 관리자 댓글 일시
	private String idfg;		// ID 구분
	private String authoryn;	// 작가여부
	private String regid;		// 등록자 ID
	private String nickname;	// 닉네임
	private String regdate;		// 등록일시
	private String recomyn;     // 추천 여부
	private String musers;	    // 모니터링 유저
	private int startRowNo;		// Page Block 시작번호
	private int pageSize;		// Page 게시글 갯수
	private int subcommentcnt;  // 대댓글 수
	private String mobileyn = "N"; // 모바일 여부
	private String commentconseq; //스티콘 번호
	private String commentconurl; //스티콘 파일 경로
	private String regyn;		//본인글 여부
	
	public int getCommentseq() {
		return commentseq;
	}
	public void setCommentseq(int commentseq) {
		this.commentseq = commentseq;
	}
	public String getArticlefg() {
		return articlefg;
	}
	public void setArticlefg(String articlefg) {
		this.articlefg = articlefg;
	}
	public int getArticleseq() {
		return articleseq;
	}
	public void setArticleseq(int articleseq) {
		this.articleseq = articleseq;
	}
	public int getNameconseq() {
		return nameconseq;
	}
	public void setNameconseq(int nameconseq) {
		this.nameconseq = nameconseq;
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
	public String getRecommfg() {
		return recommfg;
	}
	public void setRecommfg(String recommfg) {
		this.recommfg = recommfg;
	}
	public int getTotalgoodcnt() {
		return totalgoodcnt;
	}
	public void setTotalgoodcnt(int totalgoodcnt) {
		this.totalgoodcnt = totalgoodcnt;
	}
	public int getTotalbadcnt() {
		return totalbadcnt;
	}
	public void setTotalbadcnt(int totalbadcnt) {
		this.totalbadcnt = totalbadcnt;
	}
	public String getBlindyn() {
		return blindyn;
	}
	public void setBlindyn(String blindyn) {
		this.blindyn = blindyn;
	}
	public String getAutoblindyn() {
		return autoblindyn;
	}
	public void setAutoblindyn(String autoblindyn) {
		this.autoblindyn = autoblindyn;
	}
	public String getBlindreason() {
		return blindreason;
	}
	public void setBlindreason(String blindreason) {
		this.blindreason = blindreason;
	}
	public String getBlinddt() {
		return blinddt;
	}
	public void setBlinddt(String blinddt) {
		this.blinddt = blinddt;
	}
	public String getAdminreply() {
		return adminreply;
	}
	public void setAdminreply(String adminreply) {
		this.adminreply = adminreply;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getAdminreplydt() {
		return adminreplydt;
	}
	public void setAdminreplydt(String adminreplydt) {
		this.adminreplydt = adminreplydt;
	}
	public String getIdfg() {
		return idfg;
	}
	public void setIdfg(String idfg) {
		this.idfg = idfg;
	}
	public String getAuthoryn() {
		return authoryn;
	}
	public void setAuthoryn(String authoryn) {
		this.authoryn = authoryn;
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
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
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
	public int getSubcommentcnt() {
		return subcommentcnt;
	}
	public void setSubcommentcnt(int subcommentcnt) {
		this.subcommentcnt = subcommentcnt;
	}	
	public String getDefaultyn() {
		return defaultyn;
	}
	public void setDefaultyn(String defaultyn) {
		this.defaultyn = defaultyn;
	}
	public String getNameconnm() {
		return nameconnm;
	}
	public void setNameconnm(String nameconnm) {
		this.nameconnm = nameconnm;
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
	public String getMobileyn() {
		return mobileyn;
	}
	public void setMobileyn(String mobileyn) {
		this.mobileyn = mobileyn;
	}
	public String getRecomyn() {
		return recomyn;
	}
	public void setRecomyn(String recomyn) {
		this.recomyn = recomyn;
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
	/**
	 * @return the deldt
	 */
	public String getDeldt() {
		return deldt;
	}
	/**
	 * @param deldt the deldt to set
	 */
	public void setDeldt(String deldt) {
		this.deldt = deldt;
	}
	/**
	 * @return the delreason
	 */
	public String getDelreason() {
		return delreason;
	}
	/**
	 * @param delreason the delreason to set
	 */
	public void setDelreason(String delreason) {
		this.delreason = delreason;
	}	
}