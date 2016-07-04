/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : QNAServiceImpl.java
 * DESCRIPTION    : 1:1문의 상세 Service 구현 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0          jss          	  2014-04-17	1:1문의      
 *****************************************************************************/

package com.olleh.webtoon.common.dao.support.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.support.domain.QnaDomain;
import com.olleh.webtoon.common.dao.support.persistence.QNAMapper;
import com.olleh.webtoon.common.dao.support.service.iface.QNAService;

@Service( "qnaService" )
@Repository
public class QNAServiceImpl implements QNAService
{
	protected static Log	logger	= LogFactory.getLog( QNAServiceImpl.class );

	@Autowired
	private QNAMapper	qnaMapper;
	
	/**
	 * 문의하기 리스트 총갯수 조회 
	 * @param qnaDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public int qnaListCnt(QnaDomain qnaDomain){
		return qnaMapper.qnaSelectListCnt(qnaDomain);
	}
	
	/**
	 * 문의하기 리스트 조회 
	 * @param qnaDomain 페이지 정보 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<QnaDomain> qnaList(QnaDomain qnaDomain){
		return qnaMapper.qnaSelectList(qnaDomain);
	}


	/**
	 * 문의하기 정보를 전달받아 저장한다.
	 * 
	 * @param QnaDomain qnaDomain : 문의하기 정보
	 * @return 
	 */
	@Transactional(readOnly=false)
	public void registQnaProc(QnaDomain qnaDomain) {		
		qnaMapper.qnaInsert(qnaDomain);
	}
	
	
}

