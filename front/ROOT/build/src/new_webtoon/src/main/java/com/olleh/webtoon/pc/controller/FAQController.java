/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자(PC버전)
 * FILE NAME      : FAQController.java
 * DESCRIPTION    : FAQ Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0         khb          2014-04-21      init
 *****************************************************************************/

package com.olleh.webtoon.pc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.support.domain.FaqDomain;
import com.olleh.webtoon.common.dao.support.service.iface.FAQService;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
public class FAQController {
	
	@Autowired
	FAQService faqService;
		
	/** 
	 * FAQ 리스트화면으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/support/faqList.kt")
	public ModelAndView faqList(@ModelAttribute("faqDomain") FaqDomain faqDomain) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();

		//페이지 사이즈 기본값
		if(faqDomain.getPageSize() == 0) faqDomain.setPageSize(10);
		
		//Like 검색, XSS 보안처리
		if(faqDomain.getKeyword() != null && !"".equals(faqDomain.getKeyword())){ 
			faqDomain.setKeyword("%"+StringUtil.cleanXSS(faqDomain.getKeyword())+"%");
		}
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = faqService.faqListCnt(faqDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//Faq 목록을 조회한다.
		List<FaqDomain>list = faqService.faqList(faqDomain);
		modelAndView.addObject("faqList", list);
			
		modelAndView.setViewName("pc/support/faqList");
		return modelAndView;
	}
		
	/**
	 * FAQ 상세화면으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/support/faqDetail.kt")
	public ModelAndView faqDetail(@ModelAttribute("faqDomain") FaqDomain faq) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		FaqDomain faqDomain = faqService.faqDetail(faq);
		
		//이전화, 다음화 Sequence 조회
		FaqDomain prevFaqDomain  = faqService.prevFaqSeq(faq);
		FaqDomain nextFaqDomain  = faqService.nextFaqSeq(faq);
		
		modelAndView.addObject("prevFaqDomain",prevFaqDomain);
		modelAndView.addObject("nextFaqDomain",nextFaqDomain);
		modelAndView.addObject("faqDomain",faqDomain);
		
		modelAndView.setViewName("pc/support/faqDetail");
		
		return modelAndView;
	}
}