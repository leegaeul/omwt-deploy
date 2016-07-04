package com.olleh.webtoon.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.applay.domain.RecommendAppDomain;
import com.olleh.webtoon.common.dao.applay.service.iface.RecommendAppService;

@Controller("MobileRecommendAppController")
public class RecommendAppController {
	
	@Autowired
	RecommendAppService recommendAppService;
	
	/** 
	 * 추천앱 리스트 화면으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/applay/recommendAppList.kt")
	public ModelAndView recommendAppList() throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/applay/recommendAppList");
		
		return modelAndView;
	}
	
	/**
	 * 추천앱 리스트 조회 후 그 결과를 json으로 보내주는 RequestMapping 메소드 
	 * @return
	 */
	@RequestMapping(value = "/m/applay/recommendAppJsonList.kt")
	public ModelAndView recommendAppJsonList(@ModelAttribute("recommendAppDomain") RecommendAppDomain recommendAppDomain) throws Exception{

		ModelAndView modelAndView = new ModelAndView();
		
		//페이지 사이즈 기본값
		if(recommendAppDomain.getPageSize() == 0) recommendAppDomain.setPageSize(10);
		
		//모바일 여부 처리
		recommendAppDomain.setMobileyn("Y");
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = recommendAppService.recommendAppListCnt(recommendAppDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//추천앱 목록을 조회한다.
		List<RecommendAppDomain> recommendAppList = recommendAppService.recommendAppList(recommendAppDomain);
		modelAndView.addObject("recommendAppList", recommendAppList);
			
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 추천앱 상세화면으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/applay/recommendAppDetail.kt")
	public ModelAndView recommendAppDetail(@ModelAttribute("recommendAppDomain") RecommendAppDomain recommendApp) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		
		//추천앱 상세 조회
		RecommendAppDomain recommendAppDomain = recommendAppService.recommendAppDetail(recommendApp);
		modelAndView.addObject("recommendAppDomain",recommendAppDomain);
		
		modelAndView.setViewName("mobile/applay/recommendAppDetail");
		
		return modelAndView;
	}
	
//	/**
//	 * 추천앱 상세페이지 결과를 json으로 보내주는 RequestMapping 메소드
//	 * @return
//	 */
//	@RequestMapping(value = "m/applay/recommendAppDetailJson.kt")
//	public ModelAndView recommendAppDetailJson(@ModelAttribute("recommendAppDomain") RecommendAppDomain recommendApp){
//
//		ModelAndView modelAndView = new ModelAndView();
//		
//		//추천앱 상세 조회
//		RecommendAppDomain recommendAppDomain = recommendAppService.recommendAppDetail(recommendApp);
//		modelAndView.addObject("recommendAppDomain",recommendAppDomain);
//		
//		modelAndView.setViewName("mappingJacksonJsonView");
//		
//		return modelAndView;
//	}
}