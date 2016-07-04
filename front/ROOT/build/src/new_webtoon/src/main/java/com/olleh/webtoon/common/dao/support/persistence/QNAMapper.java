/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : QNAMapper.java
 * DESCRIPTION    : 1:1문의 연동관련  Mapper class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0          jss          	  2014-04-17	1:1문의      
 *****************************************************************************/

package com.olleh.webtoon.common.dao.support.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.support.domain.QnaDomain;

public interface QNAMapper
{
	
	/**
	 * 문의하기 리스트 총갯수 조회
	 * @param qnaDomain
	 * @return
	 */
	public int qnaSelectListCnt(QnaDomain qnaDomain);
	
	/**
	 * 문의하기 리스트 조회 
	 * @param qnaDomain 페이지 정보 
	 * @return
	 */
	public List<QnaDomain> qnaSelectList(QnaDomain qnaDomain);

	/**
	 * 문의하기 정보를 전달받아 저장한다.
	 * 
	 * @param QnaDomain qnaDomain : 문의하기 정보
	 * @return 
	 */
	public void qnaInsert( QnaDomain qnaDomain );

}
