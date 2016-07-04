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

import com.olleh.webtoon.common.dao.comment.domain.CommentEventDomain;
import com.olleh.webtoon.common.dao.comment.persistence.CommentEventMapper;
import com.olleh.webtoon.common.dao.comment.service.iface.CommentEventService;

@Service("commentEventService")
@Repository
public  class CommmentEventServiceImpl implements CommentEventService
{
	@Autowired
	private CommentEventMapper commentEventMapper;
	
	/**
	 * 댓글 이벤트 약관 동의 체크를 위한 eventseq 조회
	 * @param CommentEventDomain commentEventDomain
	 */
	@Transactional(readOnly=true)
	public CommentEventDomain commentEventseq(CommentEventDomain commentEventDomain){
		return commentEventMapper.commentSelectEventseq(commentEventDomain);
	}
	
	/**
	 * 댓글 이벤트 등록
	 * @param CommentEventDomain commentEventDomain
	 */
	@Transactional(readOnly=false)
	public void commentEventRegistProc(CommentEventDomain commentEventDomain){
		
		if("Y".equals(commentEventDomain.getCommentyn())) {
			commentEventDomain.setCommentseq(commentEventMapper.commentseqSelect(commentEventDomain));
		}
		
		commentEventMapper.commentEventInsert(commentEventDomain);
	}
	
	/**
	 * 댓글 이벤트 당첨 여부 조회
	 * @param CommentEventDomain commentEventDomain
	 */
	@Transactional(readOnly=true)
	public String checkPrizeWinner(CommentEventDomain commentEventDomain){
		return commentEventMapper.selectPrizeWinner(commentEventDomain);
	}
	
	/**
	 * 댓글 이벤트 당첨 목록 조회
	 * @param CommentEventDomain commentEventDomain
	 */
	@Transactional(readOnly=true)
	public List<CommentEventDomain> prizeWinnerList(CommentEventDomain commentEventDomain){
		return commentEventMapper.prizeWinnerSelectList(commentEventDomain);
	}
}