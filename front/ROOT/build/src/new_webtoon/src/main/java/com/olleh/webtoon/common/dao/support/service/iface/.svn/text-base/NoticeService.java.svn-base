/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - NoticeService.java
 * 
 * DESCRIPTION
 * -  공지사항 Service interface class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.support.service.iface;

import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.support.domain.NoticeDomain;

public interface NoticeService
{
	// Notice 리스트 총갯수 조회
	public int noticeListCount(NoticeDomain noticeDomain);
	
	// Notice 리스트 조회
	public List<NoticeDomain> noticeList(NoticeDomain NoticeDomain);

	// 필수 Notice 리스트 조회
	public List<NoticeDomain> essentialNoticeList(NoticeDomain noticeDomain);
	
	// Notice 상세 조회
	public NoticeDomain  noticeDetail(NoticeDomain notice);
	
	// 이전 Notice Seq 조회
	public NoticeDomain prevNoticeDomain(NoticeDomain notice);
	
	// 다음 Notice Seq 조회
	public NoticeDomain nextNoticeDomain(NoticeDomain notice);
	
	// 최신 Notice 리스트 조회
	public List<NoticeDomain> newestNoticeList(int size);
	
	//test Log 조회수 증가
	public void testLogCntModify(Map<String,Object> param);
}
