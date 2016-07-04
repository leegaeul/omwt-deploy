/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ServiceController.java
 * DESCRIPTION    : B2B 처리 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2015-05-07      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonImageDomain;
import com.olleh.webtoon.common.dao.toon.service.iface.ToonService;

@Controller
public class AppApiController {
	
	protected static Log logger = LogFactory.getLog(AppApiController.class);
	
	@Autowired
	ToonService toonService;
	
	/**
	 * 웹툰 리스트 정보를 조회 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/app/api/webtoonList.kt")
	public ModelAndView toonList(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		param.put("toonfg", "toon");
		List<ToonDomain> toonList = toonService.toonList(param);
		
		modelAndView.addObject("toonList", toonList);
		
		modelAndView.setViewName("mappingJacksonJsonView");		
		
		return modelAndView;
	}
	
	/**
	 * 웹툰 리스트 정보를 조회 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/app/api/timesList.kt")
	public ModelAndView timesList(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		param.put("toonfg", "toon");
		List<ToonDomain> timesList = toonService.timesList(param);
		
		modelAndView.addObject("timesList", timesList);
		
		modelAndView.setViewName("mappingJacksonJsonView");		
		
		return modelAndView;
	}
	
	/**
	 * 웹툰 리스트 정보를 조회 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/app/api/timesDetail.kt")
	public ModelAndView timesDetail(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		// 작화 이미지 정보 가져오기
		List<ToonImageDomain> toonImageList = toonService.toonImageList(param);
		modelAndView.addObject("toonImageList", toonImageList);
		
		modelAndView.setViewName("mappingJacksonJsonView");		
		
		return modelAndView;
	}
	
	
}