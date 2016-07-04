/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - CommentServiceImpl.java
 * 
 * DESCRIPTION
 * -  댓글 처리 Service implement class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.comment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.comment.domain.CommentDomain;
import com.olleh.webtoon.common.dao.comment.domain.DeclarationDomain;
import com.olleh.webtoon.common.dao.comment.domain.SubCommentDomain;
import com.olleh.webtoon.common.dao.comment.persistence.CommentMapper;
import com.olleh.webtoon.common.dao.comment.service.iface.CommentService;
import com.olleh.webtoon.common.dao.toon.domain.StickerIconDomain;
import com.olleh.webtoon.common.dao.user.domain.IconDomain;

@Service("commentService")
@Repository
public  class CommmentServiceImpl implements CommentService
{
	@Autowired
	private CommentMapper commentMapper;
	
	/**
	 * 댓글 수 조회 
	 * @param commentDomain commentDomain
	 */
	@Transactional(readOnly=true)
	public int commentListCnt(CommentDomain commentDomain)  { return commentMapper.commentSelectListCnt(commentDomain); }
	
	/**
	 * 댓글 조회 
	 * @param commentDomain 댓글 Sequence number 
	 */
	@Transactional(readOnly=true)
	public List<CommentDomain> commentList(CommentDomain commentDomain) { return commentMapper.commentSelectList(commentDomain); }
	
	/**
	 * 베스트 댓글 수 조회 
	 * @param commentDomain commentDomain
	 */
	@Transactional(readOnly=true)
	public int bestCommentListCnt(CommentDomain commentDomain)  { return commentMapper.bestCommentSelectListCnt(commentDomain); }
	
	/**
	 * 베스트 댓글 조회
	 * @param CommentDomain 회차Seq번호, startrow, pageSize 
	 * @return List<CommentDomain> 조회한 댓글 리스트 
	 */
	@Transactional(readOnly=true)
	public List<CommentDomain> bestCommentList(CommentDomain commentDomain) { return commentMapper.bestCommentSelectList(commentDomain); }
	
	/**
	 * 댓글 등록 
	 * @param commentDomain 댓글 Sequence number 
	 */
	@Transactional(readOnly=false)
	public void commentRegistProc(CommentDomain commentDomain) { commentMapper.commentInsert(commentDomain); }
	
	
	/**
	 * 댓글 삭제 
	 * @param commentDomain 댓글 Sequence number 
	 */
	@Transactional(readOnly=false)
	public void commentDeleteProc(CommentDomain commentDomain) {
		
		//좋아요, 싫어요 정보 삭제
		commentMapper.commentRecommendDelete(commentDomain);
		
		//대댓글 삭제
		commentMapper.subCommentAllDelete(commentDomain);
		
		//댓글 삭제
		commentMapper.commentDelete(commentDomain); 
	}
	
	/**
	 * 대댓글 수 조회 
	 * @param SubCommentDomain subCommentDomain
	 */
	@Transactional(readOnly=true)
	public int subCommentListCnt(SubCommentDomain subCommentDomain)
	{
		return commentMapper.subCommentSelectListCnt(subCommentDomain);
	}
	
	/**
	 * 대댓글 리스트 조회 
	 * @param SubCommentDomain subCommentDomain
	 */
	@Transactional(readOnly=true)
	public List<SubCommentDomain> subCommentList(SubCommentDomain subCommentDomain)
	{
		return commentMapper.subCommentSelectList(subCommentDomain);
	}
	
	/**
	 * 댓글 상세 조회 
	 * @param CommentDomain commentDomain
	 */
	@Transactional(readOnly=true)
	public CommentDomain commentDetail(CommentDomain commentDomain)
	{
		return commentMapper.commentSelectDetail(commentDomain);
	}
	
	/**
	 * 대댓글 등록 
	 * @param SubCommentDomain subCommentDomain
	 */
	@Transactional(readOnly=false)
	public void subCommentRegistProc(SubCommentDomain subCommentDomain)
	{
		commentMapper.subCommentInsert(subCommentDomain);
	}

	/**
	 * 대댓글 삭제 
	 * @param SubCommentDomain subCommentDomain
	 */
	@Transactional(readOnly=false)
	public void subCommentDeleteProc(SubCommentDomain subCommentDomain)
	{
		commentMapper.subCommentDelete(subCommentDomain);
	}
	
	/**
	 * 좋아요/싫어요 등록 
	 * @param CommentDomain commentDomain 
	 */
	@Transactional(readOnly=false)
	public void commentRecommend(CommentDomain commentDomain) {
		commentMapper.commentRecommendInsert(commentDomain);
		commentMapper.commentRecommendUpdate(commentDomain); 
	}	
	
	/**
	 * 추천 중복 체크를 위한 좋아요/ 싫어요 seq 조회 
	 * @param CommentDomain commentDomain
	 */
	@Transactional(readOnly=true)
	public String commentRecommendSeq(CommentDomain commentDomain){
		return commentMapper.commentRecommendSelectSeq(commentDomain);
	}

	/**
	 * 본인 작성글 체크를 위한 댓글 regId 조회 
	 * @param CommentDomain commentDomain
	 */
	@Transactional(readOnly=true)
	public String commentRegId(CommentDomain commentDomain){
		return commentMapper.commentSelectRegId(commentDomain); 
	}
	
	/**
	 * 본인 작성글 체크를 위한 대댓글 regId 조회 
	 * @param SubCommentDomain subCommentDomain
	 */
	@Transactional(readOnly=true)
	public String subCommentRegId(SubCommentDomain subCommentDomain){
		return commentMapper.subCommentSelectRegId(subCommentDomain); 
	}
	
	/**
	 * 스티커콘 총 갯수 
	 * @param 
	 */
	@Transactional(readOnly=true)
	public int stickerconListCnt(){
		return commentMapper.stickerconSelectListCnt();
	}
	
	/**
	 * 스티커콘 조회 
	 * @param StickerDomain stickerDomain
	 */
	@Transactional(readOnly=true)
	public List<StickerIconDomain> stickerconList(StickerIconDomain stickerIconDomain){
		return commentMapper.stickerconSelectList(stickerIconDomain);
	}
	
	/**
	 * 신고 하기
	 * @param DeclarationDomain declarationDomain
	 */
	@Transactional(readOnly=false)
	public void commentDeclation(DeclarationDomain declarationDomain){
		commentMapper.commentDeclationInsert(declarationDomain);
	}
	
	/**
	 * 신고 5회 누적 여부 조회
	 * @param DeclarationDomain declarationDomain
	 */
	@Transactional(readOnly=true)
	public String commentBlind(DeclarationDomain declarationDomain){
		return commentMapper.commentSelectBlind(declarationDomain);
	}
	
	/**
	 * 중복 신고 체크를 위한 seq 조회
	 * @param DeclarationDomain declarationDomain
	 */
	@Transactional(readOnly=true)
	public String declarationSeq(DeclarationDomain declarationDomain)
	{
		return commentMapper.declarationSelectSeq(declarationDomain);
	}
	
	/**
	 * 본인 작품 여부 조회
	 * @param CommentDomain commentDomain
	 */
	@Transactional(readOnly=true)
	public String selectMyWebtoon(CommentDomain commentDomain) {
		return commentMapper.selectMyWebtoon(commentDomain);
	}
	
	/**
	 * 본인 작품 여부 조회
	 * @param SubCommentDomain subCommentDomain
	 */
	@Transactional(readOnly=true)
	public String selectMyWebtoonByCommentseq(SubCommentDomain subCommentDomain) {
		return commentMapper.selectMyWebtoonByCommentseq(subCommentDomain);
	}
	
	/**
	 * 댓글 중복 확인
	 * @param CommentDomain commentDomain
	 */
	@Transactional(readOnly=true)
	public String commentDuplicateyn(CommentDomain commentDomain){
		return commentMapper.commentSelectDuplicateyn(commentDomain);
	}
	
	/**
	 * 대댓글 중복 확인
	 * @param SubCommentDomain subCommentDomain
	 */
	@Transactional(readOnly=true)
	public String subCommentDuplicateyn(SubCommentDomain subCommentDomain){
		return commentMapper.subCommentSelectDuplicateyn(subCommentDomain);
	}
	
	/**
	 * 5회 이상 신고 접수된 댓글과 등록 댓글 내용 비교
	 * @param CommentDomain commentDomain
	 */
	@Transactional(readOnly=true)
	public String chkDeclareComment(CommentDomain commentDomain){
		return commentMapper.selectDeclareComment(commentDomain);
	}
	
	/**
	 * 5회 이상 신고 접수된 댓글과 등록 대댓글 내용 비교
	 * @param SubCommentDomain subCommentDomain
	 */
	@Transactional(readOnly=true)
	public String chkDeclareSubComment(SubCommentDomain subCommentDomain){
		return commentMapper.selectDeclareSubComment(subCommentDomain);
	}
	
	/**
	 * 최근 사용한 스티콘 목록 조회
	 * @param String regid
	 */
	@Transactional(readOnly=true)
	public List<IconDomain> recentconList(String param){
		return commentMapper.selectRecentconList(param);
	}
}