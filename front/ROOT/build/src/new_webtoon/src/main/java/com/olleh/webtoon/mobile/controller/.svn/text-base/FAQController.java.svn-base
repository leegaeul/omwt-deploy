/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자(모바일 버전)
 * FILE NAME      : FAQController.java
 * DESCRIPTION    : FAQ Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0         khb          2014-04-21      init
 *****************************************************************************/
package com.olleh.webtoon.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.support.domain.FaqDomain;
import com.olleh.webtoon.common.dao.support.service.iface.FAQService;

@Controller("MobileFaqController")
public class FAQController {
	
	@Autowired
	FAQService faqService;
		
	/** 
	 * FAQ 리스트 화면으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/support/faqList.kt")
	public ModelAndView faqList() throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/support/faqList");
		
		return modelAndView;
	}
		
	/**
	 * FAQ 리스트 조회 후 그 결과를 json으로 보내주는 RequestMapping 메소드 
	 * @return
	 */
	@RequestMapping(value = "m/support/faqJsonList.kt")
	public ModelAndView faqJsonList(@ModelAttribute("faqDomain") FaqDomain faqDomain){
		
		ModelAndView modelAndView = new ModelAndView();
		
		//페이지 사이즈 기본값
		if(faqDomain.getPageSize() == 0) faqDomain.setPageSize(10);
		
		//검색어 빈값 처리
		faqDomain.setKeyword("");
		
		//모바일 여부 처리
		faqDomain.setMobileyn("Y");
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = faqService.faqListCnt(faqDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//Faq 목록을 조회한다.
		List<FaqDomain>list = faqService.faqList(faqDomain);
		modelAndView.addObject("faqList", list);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		return modelAndView;
	}
}