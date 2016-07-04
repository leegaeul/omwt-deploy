/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - CommentService.java
 * 
 * DESCRIPTION
 * -  댓글 처리 Service interface class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.comment.service.iface;

import java.util.List;

import com.olleh.webtoon.common.dao.comment.domain.CommentDomain;
import com.olleh.webtoon.common.dao.comment.domain.DeclarationDomain;
import com.olleh.webtoon.common.dao.comment.domain.SubCommentDomain;
import com.olleh.webtoon.common.dao.toon.domain.StickerIconDomain;
import com.olleh.webtoon.common.dao.user.domain.IconDomain;

public interface CommentService 
{	
	/**
	 * 댓글 수 조회 
	 * @param commentDomain commentDomain
	 */
	public int commentListCnt(CommentDomain commentDomain);
	
	/**
	 * 댓글 리스트 조회 
	 * @param commentDomain commentDomain
	 */
	public List<CommentDomain> commentList(CommentDomain commentDomain);
	
	/**
	 * 베스트 댓글 수 조회 
	 * @param commentDomain commentDomain
	 */
	public int bestCommentListCnt(CommentDomain commentDomain);
	
	/**
	 * 베스트 댓글 조회
	 * @param CommentDomain 회차Seq번호, startrow, pageSize 
	 * @return List<CommentDomain> 조회한 댓글 리스트 
	 */
	public List<CommentDomain> bestCommentList(CommentDomain commentDomain);
	
	/**
	 * 댓글 등록 
	 * @param commentDomain commentDomain
	 */
	public void commentRegistProc(CommentDomain commentDomain);
	
	/**
	 * 댓글 삭제 
	 * @param commentDomain 댓글Seq번호 
	 */
	public void commentDeleteProc(CommentDomain commentDomain);
	
	/**
	 * 대댓글 수 조회 
	 * @param SubCommentDomain subCommentDomain
	 */
	public int subCommentListCnt(SubCommentDomain subCommentDomain);
	
	/**
	 * 대댓글 리스트 조회 
	 * @param SubCommentDomain subCommentDomain
	 */
	public List<SubCommentDomain> subCommentList(SubCommentDomain subCommentDomain);
	
	/**
	 * 댓글 상세 조회 
	 * @param CommentDomain commentDomain
	 */
	public CommentDomain commentDetail(CommentDomain commentDomain);
	
	/**
	 * 대댓글 등록 
	 * @param SubCommentDomain subCommentDomain
	 */
	public void subCommentRegistProc(SubCommentDomain subCommentDomain);
	
	/**
	 * 대댓글 삭제 
	 * @param SubCommentDomain subCommentDomain
	 */
	public void subCommentDeleteProc(SubCommentDomain subCommentDomain);
		
	/**
	 * 좋아요/싫어요 등록 
	 * @param CommentDomain commentDomain 
	 */
	public void commentRecommend(CommentDomain commentDomain);
	
	/**
	 * 추천 중복 체크를 위한 좋아요/ 싫어요 seq 조회 
	 * @param CommentDomain commentDomain
	 */
	public String commentRecommendSeq(CommentDomain commentDomain);

	/**
	 * 본인 작성글 체크를 위한 댓글 regId 조회 
	 * @param CommentDomain commentDomain
	 */
	public String commentRegId(CommentDomain commentDomain);
	
	/**
	 * 본인 작성글 체크를 위한 대댓글 regId 조회 
	 * @param SubCommentDomain subCommentDomain
	 */
	public String subCommentRegId(SubCommentDomain subCommentDomain);
	
	/**
	 * 스티커콘 총 갯수 
	 * @param 
	 */
	public int stickerconListCnt();
	
	/**
	 * 스티커콘 조회 
	 * @param StickerDomain stickerDomain
	 */
	public List<StickerIconDomain> stickerconList(StickerIconDomain stickerIconDomain);
	
	/**
	 * 신고 하기
	 * @param DeclarationDomain declarationDomain
	 */
	public void commentDeclation(DeclarationDomain declarationDomain);
	
	/**
	 * 신고 5회 누적 여부 조회
	 * @param DeclarationDomain declarationDomain
	 */
	public String commentBlind(DeclarationDomain declarationDomain);
	
	/**
	 * 중복 신고 체크를 위한 seq 조회
	 * @param DeclarationDomain declarationDomain
	 */
	public String declarationSeq(DeclarationDomain declarationDomain);
	
	/**
	 * 본인 작품 여부 조회
	 * @param CommentDomain commentDomain
	 */
	public String selectMyWebtoon(CommentDomain commentDomain);
	
	/**
	 * 본인 작품 여부 조회
	 * @param SubCommentDomain subCommentDomain
	 */
	public String selectMyWebtoonByCommentseq(SubCommentDomain subCommentDomain);
	
	/**
	 * 댓글 중복 확인
	 * @param CommentDomain commentDomain
	 */
	public String commentDuplicateyn(CommentDomain commentDomain);
	
	/**
	 * 대댓글 중복 확인
	 * @param SubCommentDomain subCommentDomain
	 */
	public String subCommentDuplicateyn(SubCommentDomain subCommentDomain);
	
	/**
	 * 5회 이상 신고 접수된 댓글과 등록 댓글 내용 비교
	 * @param CommentDomain commentDomain
	 */
	public String chkDeclareComment(CommentDomain commentDomain);
	
	/**
	 * 5회 이상 신고 접수된 댓글과 등록 대댓글 내용 비교
	 * @param SubCommentDomain subCommentDomain
	 */
	public String chkDeclareSubComment(SubCommentDomain subCommentDomain);
	
	/**
	 * 최근 사용한 스티콘 목록 조회
	 * @param String regid
	 */
	public List<IconDomain> recentconList(String param);
	
}