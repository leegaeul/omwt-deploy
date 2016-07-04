package com.olleh.webtoon.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.applay.domain.EventDomain;
import com.olleh.webtoon.common.dao.applay.domain.EventImageDomain;
import com.olleh.webtoon.common.dao.applay.service.iface.EventService;

@Controller("MobileEventController")
public class EventController {
	
	@Autowired
	EventService eventService;
	
	/** 
	 * 이벤트 리스트 화면으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/applay/eventList.kt")
	public ModelAndView eventList() throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/applay/eventList");
		
		return modelAndView;
	}
	
	/**
	 * 이벤트 리스트 조회 후 그 결과를 json으로 보내주는 RequestMapping 메소드 
	 * @return
	 */
	@RequestMapping(value = "/m/applay/eventJsonList.kt")
	public ModelAndView eventJsonList(@ModelAttribute("eventDomain") EventDomain eventDomain){
		
		ModelAndView modelAndView = new ModelAndView();

		//페이지 사이즈 기본값
		if(eventDomain.getPageSize() == 0) eventDomain.setPageSize(10);
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = eventService.eventListCnt(eventDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//Event 목록을 조회한다.
		List<EventDomain>list = eventService.eventList(eventDomain);
		modelAndView.addObject("eventList", list);
			
		modelAndView.setViewName("mappingJacksonJsonView");
		return modelAndView;
	}
		
	/**
	 * 이벤트 상세페이지 결과를 json으로 보내주는 RequestMapping 메소드 
	 * @return
	 */
	@RequestMapping(value = "m/applay/eventDetailJson.kt")
	public ModelAndView eventDetailJson(@ModelAttribute("eventDomain") EventDomain event){
		
		ModelAndView modelAndView = new ModelAndView();
		
		//이벤트 이미지 리스트
		List<EventImageDomain> list = eventService.eventImageList(event.getEventseq());
		modelAndView.addObject("eventImageList",list);
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 이벤트 상세화면으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/applay/eventDetail.kt")
	public ModelAndView eventDetail(@ModelAttribute("eventDomain") EventDomain event) throws Exception{

		ModelAndView modelAndView = new ModelAndView();
		
		//이벤트 상세 조회
		EventDomain eventDomain = eventService.eventDetail(event);
		modelAndView.addObject("eventDomain",eventDomain);
		
		modelAndView.setViewName("mobile/applay/eventDetail");
		
		return modelAndView;
	}
	
	/**
	 * 이벤트 상세화면으로 redirect 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value="/m/event/newwebtoon/event.kt")
	public ModelAndView eventRedirect(HttpServletRequest request) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/applay/eventRedirect");

		return modelAndView;
	}
}