/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - NoticeServiceImpl.java
 * 
 * DESCRIPTION
 * - 공지사항 Service implement class. 
 *****************************************************************************/

package com.olleh.webtoon.common.dao.support.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.support.domain.NoticeDomain;
import com.olleh.webtoon.common.dao.support.persistence.NoticeMapper;
import com.olleh.webtoon.common.dao.support.service.iface.NoticeService;

@Service("noticeService")
@Repository
public class NoticeServiceImpl implements NoticeService
{
	@Autowired
	private NoticeMapper noticeMapper;
	
	// Notice 리스트 총갯수 조회
	@Transactional(readOnly=true)
	public int noticeListCount(NoticeDomain noticeDomain) { return noticeMapper.noticeSelectListCount(noticeDomain); }
	
	// Notice 리스트 조회
	@Transactional(readOnly=true)
	public List<NoticeDomain> noticeList(NoticeDomain noticeDomain) { return noticeMapper.noticeSelectList(noticeDomain); }
	
	// 필수 Notice 리스트 조회
	@Transactional(readOnly=true)
	public List<NoticeDomain> essentialNoticeList(NoticeDomain noticeDomain) { return noticeMapper.essentialNoticeSelectList(noticeDomain); }
	
	// Notice 상세 조회
	@Transactional(readOnly=false)
	public NoticeDomain noticeDetail(NoticeDomain notice) {
		// Notice 상세 조회
		NoticeDomain noticeDomain = noticeMapper.noticeSelectDetail(notice);
		
		//Notice 조회수 증가
		noticeMapper.noticeUpdateReadCnt(notice.getNoticeseq());
		
		return  noticeDomain;
	}
	
	
	// 이전 Notice Seq 조회
	@Transactional(readOnly=true)
	public NoticeDomain prevNoticeDomain(NoticeDomain notice) { return noticeMapper.prevNoticeSelectDomain(notice); }

	// 다음 Notice Seq 조회
	@Transactional(readOnly=true)
	public NoticeDomain nextNoticeDomain(NoticeDomain notice) { return noticeMapper.nextNoticeSelectDomain(notice); }
	
	// 최신 Notice 리스트 조회
	@Transactional(readOnly=true)
	public List<NoticeDomain> newestNoticeList(int size) { return noticeMapper.newestNoticeSelectList(size); }
	
	//test Log 조회수 증가
	@Transactional(readOnly=false)
	public void testLogCntModify(Map<String,Object> param){		
		//test Log 조회수 증가
		noticeMapper.testlogUpdateCnt(param);
	}
}
