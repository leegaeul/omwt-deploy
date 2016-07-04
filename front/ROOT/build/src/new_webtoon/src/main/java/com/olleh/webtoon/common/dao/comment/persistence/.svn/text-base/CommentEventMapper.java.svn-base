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

import com.olleh.webtoon.common.dao.comment.domain.CommentEventDomain;

public interface CommentEventMapper 
{		
	/**
	 * 댓글 이벤트 약관 동의 체크를 위한 eventseq 조회
	 * @param CommentEventDomain commentEventDomain
	 */
	public CommentEventDomain commentSelectEventseq(CommentEventDomain commentEventDomain);
	
	/**
	 * 댓글 이벤트 등록
	 * @param CommentEventDomain commentEventDomain
	 */
	public void commentEventInsert(CommentEventDomain commentEventDomain);
	
	/**
	 * 유저의 가장 최신 댓글seq
	 * @param CommentEventDomain commentEventDomain
	 */
	public String commentseqSelect(CommentEventDomain commentEventDomain);
	
	/**
	 * 댓글 이벤트 당첨 여부 조회
	 * @param CommentEventDomain commentEventDomain
	 */
	public String selectPrizeWinner(CommentEventDomain commentEventDomain);
	
	/**
	 * 댓글 이벤트 당첨 목록 조회
	 * @param CommentEventDomain commentEventDomain
	 */
	public List<CommentEventDomain> prizeWinnerSelectList(CommentEventDomain commentEventDomain);
}