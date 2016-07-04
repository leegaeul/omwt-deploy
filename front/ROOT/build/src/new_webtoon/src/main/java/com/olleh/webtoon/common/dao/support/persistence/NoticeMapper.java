/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - NoticeMapper.java
 * 
 * DESCRIPTION
 * -  공지사항 연동 class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.support.persistence;

import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.support.domain.NoticeDomain;

public interface NoticeMapper 
{
	//  공지사항 리스트 총갯수 조회 
	public int noticeSelectListCount(NoticeDomain noticeDomain);
	
	// 공지사항 리스트 조회
	public List<NoticeDomain> noticeSelectList(NoticeDomain noticeDomain);
	
	// 필수 공지사항 리스트 조회
	public List<NoticeDomain> essentialNoticeSelectList(NoticeDomain noticeDomain);
	
	// Notice 상세 죄회
	public NoticeDomain noticeSelectDetail(NoticeDomain notice);
	
	// 이전 Notice Seq 조회
	public NoticeDomain prevNoticeSelectDomain(NoticeDomain notice);
	
	// 다음 Notice Seq  조회
	public NoticeDomain nextNoticeSelectDomain(NoticeDomain notice);
	
	// 최신 Notice 리스트 조회
	public List<NoticeDomain> newestNoticeSelectList(int size);
	
	public void noticeUpdateReadCnt(int noticeseq);
	
	public void testlogUpdateCnt(Map<String,Object> param);	
	
}                                         
