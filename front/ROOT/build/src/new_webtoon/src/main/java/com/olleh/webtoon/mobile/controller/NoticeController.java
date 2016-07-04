/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - NoticeController.java
 * 
 * DESCRIPTION
 * - Home > 고객센터 > 공지사항 Controller class.
 *****************************************************************************/
package com.olleh.webtoon.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.support.domain.NoticeDomain;
import com.olleh.webtoon.common.dao.support.service.iface.NoticeService;

@Controller("MobileNoticeController")
public class NoticeController 
{
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping(value = "/m/support/noticeList.kt")
	public ModelAndView noticeList() throws Exception
	{		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/support/noticeList");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/m/support/noticeJsonList.kt")
	public ModelAndView noticeJsonList(@ModelAttribute("noticeDomain") NoticeDomain noticeDomain) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
//		//페이지 사이즈 기본값
//		if(noticeDomain.getPageSize() == 0) noticeDomain.setPageSize(10);
		
		//검색어 빈값 처리
		noticeDomain.setKeyword("");
		
		//모바일 여부 처리
		noticeDomain.setMobileyn("Y");
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = noticeService.noticeListCount(noticeDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//공지사항 목록을 조회한다.
		List<NoticeDomain> noticeList = noticeService.noticeList(noticeDomain);
		modelAndView.addObject("noticeList", noticeList);
		
		//필수 공지사항 목록을 조회한다.
		List<NoticeDomain> essentialNoticeList = noticeService.essentialNoticeList(noticeDomain);
		modelAndView.addObject("essentialNoticeList", essentialNoticeList);
			
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
}