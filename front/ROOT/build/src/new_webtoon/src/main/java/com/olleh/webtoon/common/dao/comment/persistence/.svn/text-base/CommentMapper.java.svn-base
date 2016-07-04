/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - CommentMapper.java
 * 
 * DESCRIPTION
 * -  댓글 연동 class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.comment.persistence;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.comment.domain.CommentDomain;
import com.olleh.webtoon.common.dao.comment.domain.DeclarationDomain;
import com.olleh.webtoon.common.dao.comment.domain.SubCommentDomain;
import com.olleh.webtoon.common.dao.toon.domain.StickerIconDomain;
import com.olleh.webtoon.common.dao.user.domain.IconDomain;

public interface CommentMapper 
{		
	/**
	 * 댓글 수 조회 
	 * @param commentDomain commentDomain
	 */
	public int commentSelectListCnt(CommentDomain commentDomain);
	
	/**
	 * 댓글 조회 
	 * @param CommentDomain commentDomain 
	 * @return List<CommentDomain> 조회한 댓글 리스트 
	 */
	public List<CommentDomain> commentSelectList(CommentDomain commentDomain);
	
	/**
	 * 베스트 댓글 수 조회 
	 * @param commentDomain commentDomain
	 */
	public int bestCommentSelectListCnt(CommentDomain commentDomain);
	
	/**
	 * 베스트 댓글 조회
	 * @param CommentDomain commentDomain 
	 * @return List<CommentDomain> 조회한 댓글 리스트 
	 */
	public List<CommentDomain> bestCommentSelectList(CommentDomain commentDomain);
	
	/**
	 * 댓글 등록
	 * @param CommentDomain 회차Seq번호, 댓글내용 
	 * @return 
	 */
	public void commentInsert(CommentDomain commentDomain);
	
	/**
	 * 댓글 삭제 
	 * @param commentDomain  댓글 Sequence number 
	 */
	public void commentDelete(CommentDomain commentDomain);
	
	/**
	 * 대댓글 수 조회 
	 * @param commentDomain commentDomain
	 */
	public int subCommentSelectListCnt(SubCommentDomain subCommentDomain);
	
	/**
	 * 대댓글 리스트 조회 
	 * @param commentDomain commentDomain
	 */
	public List<SubCommentDomain> subCommentSelectList(SubCommentDomain subCommentDomain);
	
	/**
	 * 댓글 상세 조회 
	 * @param CommentDomain commentDomain
	 */
	public CommentDomain commentSelectDetail(CommentDomain commentDomain);
	
	/**
	 * 대댓글 등록 
	 * @param commentDomain commentDomain
	 */
	public void subCommentInsert(SubCommentDomain subCommentDomain);
	
	/**
	 * 좋아요 싫어요 정보 삭제
	 * @param commentDomain commentRecommend
	 */
	public void commentRecommendDelete(CommentDomain commentDomain);
	
	/**
	 * 대댓글 전부 삭제 
	 * @param commentDomain 댓글Seq번호 
	 */
	public void subCommentAllDelete(CommentDomain commentDomain);
	
	/**
	 * 대댓글 삭제 
	 * @param commentDomain 댓글Seq번호 
	 */
	public void subCommentDelete(SubCommentDomain subCommentDomain);
	
	/**
	 * 좋아요/싫어요 조회 
	 * @param commentDomain 댓글 Sequence number 
	 */
	public String getRecomm(CommentDomain commentDomain);
	
	/**
	 * 좋아요/싫어요 카운트 업데이트 
	 * @param commentDomain 댓글 Sequence number 
	 */
	public void commentRecommendUpdate(CommentDomain commentDomain);
	
	/**
	 * 좋아요/싫어요 등록
	 * @param commentDomain 댓글 Sequence number 
	 */
	public void commentRecommendInsert(CommentDomain commentDomain);
	
	/**
	 * 추천 중복 체크를 위한 좋아요/ 싫어요 seq 조회 
	 * @param CommentDomain commentDomain
	 */
	public String commentRecommendSelectSeq(CommentDomain commentDomain);

	/**
	 * 본인 작성글 체크를 위한 댓글 regId 조회 
	 * @param CommentDomain commentDomain
	 */
	public String commentSelectRegId(CommentDomain commentDomain);
	
	/**
	 * 본인 작성글 체크를 위한 대댓글 regId 조회 
	 * @param SubCommentDomain subCommentDomain
	 */
	public String subCommentSelectRegId(SubCommentDomain subCommentDomain);
	
	/**
	 * 스티커콘 총 갯수 
	 * @param 
	 */
	public int stickerconSelectListCnt();
	
	/**
	 * 스티커콘 조회 
	 * @param StickerDomain stickerDomain
	 */
	public List<StickerIconDomain> stickerconSelectList(StickerIconDomain stickerIconDomain);
	
	/**
	 * 신고 하기
	 * @param DeclarationDomain declarationDomain
	 */
	public void commentDeclationInsert(DeclarationDomain declarationDomain);
	
	/**
	 * 신고 5회 누적 여부 조회
	 * @param DeclarationDomain declarationDomain
	 */
	public String commentSelectBlind(DeclarationDomain declarationDomain);
	
	/**
	 * 중복 신고 체크를 위한 seq 조회
	 * @param DeclarationDomain declarationDomain
	 */
	public String declarationSelectSeq(DeclarationDomain declarationDomain);
	
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
	public String commentSelectDuplicateyn(CommentDomain commentDomain);
	
	/**
	 * 대댓글 중복 확인
	 * @param SubCommentDomain subcommentDomain
	 */
	public String subCommentSelectDuplicateyn(SubCommentDomain subcommentDomain);
	
	/**
	 * 5회 이상 신고 접수된 댓글과 등록 댓글 내용 비교
	 * @param CommentDomain commentDomain
	 */
	public String selectDeclareComment(CommentDomain commentDomain);
	
	/**
	 * 5회 이상 신고 접수된 댓글과 등록 대댓글 내용 비교
	 * @param SubCommentDomain subCommentDomain
	 */
	public String selectDeclareSubComment(SubCommentDomain subCommentDomain);
	
	/**
	 * 최근 사용한 스티콘 목록 조회
	 * @param String regid
	 */
	public List<IconDomain> selectRecentconList(String param);
}