/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자 
 * FILE NAME      : FAQServiceImpl.java
 * DESCRIPTION    : FAQ Service 구현 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0         khb          2014-04-21      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.support.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.support.domain.FaqDomain;
import com.olleh.webtoon.common.dao.support.persistence.FAQMapper;
import com.olleh.webtoon.common.dao.support.service.iface.FAQService;

@Service("faqService")
@Repository
public class FAQServiceImpl implements FAQService{
	
	@Autowired
	private FAQMapper faqMapper;
	
	/**
	 * FAQ 리스트 총갯수 조회 
	 * @param faqDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public int faqListCnt(FaqDomain faqDomain){
		return faqMapper.faqSelectListCnt(faqDomain);
	}
	
	/**
	 * FAQ 리스트 조회 
	 * @param faqDomain 페이지 정보 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<FaqDomain> faqList(FaqDomain faqDomain){
		return faqMapper.faqSelectList(faqDomain);
	}
	
	/**
	 * FAQ 상세 조회 
	 * @return faqDomain
	 */
	@Transactional(readOnly=true)
	public FaqDomain faqDetail(FaqDomain faqDomain)
	{
		return faqMapper.faqSelectDetail(faqDomain);
	}
	
	/**
	 * 이전 FAQ Seq 조회 
	 * @param  faqDomain 페이지 정보 
	 * @return faqDomain
	 */
	@Transactional(readOnly=true)
	public FaqDomain prevFaqSeq(FaqDomain faqDomain)
	{
		return faqMapper.prevFaqSelectSeq(faqDomain);
	}
	
	/**
	 * 다음 FAQ Seq 조회 
	 * @param  faqDomain 페이지 정보 
	 * @return faqDomain
	 */
	@Transactional(readOnly=true)
	public FaqDomain nextFaqSeq(FaqDomain faqDomain)
	{
		return faqMapper.nextFaqSelectSeq(faqDomain);
	}
}