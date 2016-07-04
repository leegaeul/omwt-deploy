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
package com.olleh.webtoon.pc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.support.domain.NoticeDomain;
import com.olleh.webtoon.common.dao.support.service.iface.NoticeService;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
public class NoticeController 
{
	protected static Log logger = LogFactory.getLog(NoticeController.class);
	
	private final int NEWEST_SIZE = 5;
	
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping(value = "/support/noticeList.kt")
	public ModelAndView noticeList(@ModelAttribute("noticeDomain") NoticeDomain noticeDomain) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		//페이지 사이즈 기본값
		if(noticeDomain.getPageSize() == 0) noticeDomain.setPageSize(10);
		
		//Like 검색, XSS 보안처리
		if(noticeDomain.getKeyword() != null && !"".equals(noticeDomain.getKeyword())){ 
			noticeDomain.setKeyword("%"+StringUtil.cleanXSS(noticeDomain.getKeyword())+"%");
		}
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = noticeService.noticeListCount(noticeDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//공지사항 목록을 조회한다.
		List<NoticeDomain> noticeList = noticeService.noticeList(noticeDomain);
		modelAndView.addObject("noticeList", noticeList);
		
		//필수 공지사항 목록을 조회한다.
		List<NoticeDomain> essentialNoticeList = noticeService.essentialNoticeList(noticeDomain);
		modelAndView.addObject("essentialNoticeList", essentialNoticeList);
			
		modelAndView.setViewName("pc/support/noticeList");
		
		return modelAndView;
	}

	@RequestMapping(value = "/support/noticeDetail.kt")
	public ModelAndView noticeDetail(@ModelAttribute("notice") NoticeDomain notice) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		NoticeDomain noticeDomain = noticeService.noticeDetail(notice);
		
		//이전화, 다음화 Sequence 조회
		NoticeDomain prevNoticeDomain  = noticeService.prevNoticeDomain(notice);
		NoticeDomain nextNoticeDomain  = noticeService.nextNoticeDomain(notice);
		
		modelAndView.addObject("prevNoticeDomain",prevNoticeDomain);
		modelAndView.addObject("nextNoticeDomain",nextNoticeDomain);
		modelAndView.addObject("noticeDomain",noticeDomain);
		
		modelAndView.setViewName("pc/support/noticeDetail");
		
		return modelAndView;
	}
	
	/**
	 * 최신 공지 리스트 조회 후 그 결과를 json으로 보내주는 RequestMapping 메소드 
	 * @return
	 */
	@RequestMapping(value = "/support/rnbNoticeJsonList.kt")
	public ModelAndView rnbNoticeJsonList(HttpServletRequest req) throws Exception
	{
		List<NoticeDomain> noticeList = null;
		ModelAndView modelAndView	  = new ModelAndView();
		
		//최신 notice조회 
		noticeList = noticeService.newestNoticeList(NEWEST_SIZE);
		modelAndView.addObject("noticeList", noticeList);
			
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * test Log 조회수 증가 후 그 결과를 json으로 보내주는 RequestMapping 메소드 
	 * @return
	 */
	@RequestMapping(value = "/webtoonlog.kt")
	public ModelAndView testLogCntModify( @RequestParam Map<String,Object> param ) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		//test Log 조회수 증가
		logger.debug("==== Test Log DB Count Start ====");
		try{
			noticeService.testLogCntModify(param);
			modelAndView.addObject("result", "1");
		}catch(Exception e){
			modelAndView.addObject("result", "0");
		}	
		logger.debug("==== Test Log DB Count End ====");
		
		
		

		//test Log Server Url 호출
		logger.debug("==== Test Log Server Url Call Start ====");
		String testLogUrl = "http://172.27.0.46/commonlog.js";	
		RestTemplate restTemplate = new RestTemplate();
		String content = restTemplate.getForObject(testLogUrl, String.class);	// GET Method

		logger.debug("content : " + content);
		logger.debug("==== Test Log Server Url Call End ====");
			
		modelAndView.setViewName("mappingJacksonJsonView");
		
		
		

		//test Log L4 Server Url 호출
		logger.debug("==== Test Log Server Url Call Start ====");
		String testLogUrl2 = "http://210.114.40.53/commonlog.js";	
		RestTemplate restTemplate2 = new RestTemplate();
		String content2 = restTemplate2.getForObject(testLogUrl2, String.class);	// GET Method

		logger.debug("content : " + content2);
		logger.debug("==== Test Log L4 Server Url Call End ====");
			
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
}