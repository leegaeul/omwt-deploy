/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - DeclarationDomain.java
 * 
 * DESCRIPTION
 * - 댓글 Table Info를 전달하기 위한 Domain Model class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.comment.domain;

public class DeclarationDomain
{
	private int declarationseq;		// 신고순번 (PK, 자동증가)
	private String commentfg;		// 댓글구분 (main:댓글, sub:대댓글)
	private String commentseq;		// 댓글순번 (댓글은 commentseq, 대댓글은 subcommentseq)
	private String declarationfg;	// 신고종류 (0:기타, 1:욕설/인신공격 ...)
	private String contents;		// 신고내용
	private String idfg;			// ID구분 (open:오픈ID, olleh:올레ID)
	private String regid;			// 신고한 사용자ID
	private String regdt;			// 등록일시 (YYYYMMDDHHMMSS)
	private String musers;			// 모니터링 유저
	
	
	/* Getter & Setter Area */
	
	public int getDeclarationseq() { return declarationseq; }
	public void setDeclarationseq(int declarationseq) { this.declarationseq = declarationseq; }
	
	public String getCommentfg() { return commentfg; }
	public void setCommentfg(String commentfg) { this.commentfg = commentfg; }
	
	public String getCommentseq() { return commentseq; }
	public void setCommentseq(String commentseq) { this.commentseq = commentseq; }
	
	public String getDeclarationfg() { return declarationfg; }
	public void setDeclarationfg(String declarationfg) { this.declarationfg = declarationfg; }
	
	public String getContents() { return contents; }
	public void setContents(String contents) { this.contents = contents; }
	
	public String getIdfg() { return idfg; }
	public void setIdfg(String idfg) { this.idfg = idfg; }
	
	public String getRegid() { return regid; }
	public void setRegid(String regid) { this.regid = regid; }
	
	public String getRegdt() { return regdt; }
	public void setRegdt(String regdt) { this.regdt = regdt; }
	
	public String getMusers() { return musers;}
	public void setMusers(String musers) { this.musers = musers;}
}