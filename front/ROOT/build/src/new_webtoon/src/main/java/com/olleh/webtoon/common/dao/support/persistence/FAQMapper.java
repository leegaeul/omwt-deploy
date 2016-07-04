/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자 
 * FILE NAME      : FAQMapper.java
 * DESCRIPTION    : FAQ 연동관련  Mapper class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0         khb          2014-04-21      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.support.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.support.domain.FaqDomain;

public interface FAQMapper {
	
	/**
	 * FAQ 리스트 총갯수 조회
	 * @param faqDomain
	 * @return
	 */
	public int faqSelectListCnt(FaqDomain faqDomain);
	
	/**
	 * FAQ 리스트 조회 
	 * @param faqDomain 페이지 정보 
	 * @return
	 */
	public List<FaqDomain> faqSelectList(FaqDomain faqDomain);
	
	/**
	 * FAQ 상세 조회 
	 * @param faqDomain 페이지 정보 
	 * @return faqDomain
	 */
	public FaqDomain faqSelectDetail(FaqDomain faqDomain);
	
	/**
	 * 이전 FAQ Seq 조회 
	 * @param  faqDomain 페이지 정보 
	 * @return faqDomain
	 */
	public FaqDomain prevFaqSelectSeq(FaqDomain faqDomain);
	
	/**
	 * 다음 FAQ Seq 조회 
	 * @param  faqDomain 페이지 정보 
	 * @return faqDomain
	 */
	public FaqDomain nextFaqSelectSeq(FaqDomain faqDomain);
}                                         
