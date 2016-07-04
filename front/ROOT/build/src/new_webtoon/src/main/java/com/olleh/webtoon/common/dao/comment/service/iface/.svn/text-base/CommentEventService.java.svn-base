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

import com.olleh.webtoon.common.dao.comment.domain.CommentEventDomain;

public interface CommentEventService 
{	
	/**
	 * 댓글 이벤트 약관 동의 체크를 위한 eventseq 조회
	 * @param CommentEventDomain commentEventDomain
	 */
	public CommentEventDomain commentEventseq(CommentEventDomain commentEventDomain);
	
	/**
	 * 댓글 이벤트 등록
	 * @param CommentEventDomain commentEventDomain
	 */
	public void commentEventRegistProc(CommentEventDomain commentEventDomain);
	
	/**
	 * 댓글 이벤트 당첨 여부 조회
	 * @param CommentEventDomain commentEventDomain
	 */
	public String checkPrizeWinner(CommentEventDomain commentEventDomain);
	
	/**
	 * 댓글 이벤트 당첨 목록 조회
	 * @param CommentEventDomain commentEventDomain
	 */
	public List<CommentEventDomain> prizeWinnerList(CommentEventDomain commentEventDomain);
}