/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : QNAServiceImpl.java
 * DESCRIPTION    : 1:1문의  Service interface class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0          jss          	  2014-04-17	1:1문의      
 *****************************************************************************/
package com.olleh.webtoon.common.dao.support.service.iface;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.support.domain.QnaDomain;

public interface QNAService
{
	/**
	 * 문의하기 리스트 총갯수 조회 
	 * @param qnaDomain
	 * @return
	 */
	public int qnaListCnt(QnaDomain qnaDomain);
	
	/**
	 * 문의하기 리스트 조회 
	 * @param qnaDomain 페이지 정보 
	 * @return
	 */
	public List<QnaDomain> qnaList(QnaDomain qnaDomain);

	/**
	 * 문의하기 정보를 전달받아 저장한다.
	 * 
	 * @param QnaDomain qnaDomain : 문의하기 정보
	 * @return 
	 */
	@Transactional(readOnly=false)
	public void registQnaProc(QnaDomain qnaDomain);



}
