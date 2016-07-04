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

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.service.domain.ApiResultDomain;
import com.olleh.webtoon.common.dao.service.domain.ServiceDomain;
import com.olleh.webtoon.common.dao.service.domain.ServiceTimesDomain;
import com.olleh.webtoon.common.dao.service.domain.ServiceToonImageDomain;
import com.olleh.webtoon.common.dao.service.service.iface.ServiceService;
import com.olleh.webtoon.common.dao.service.util.ServiceUtil;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
public class ServiceController {
	
	protected static Log logger = LogFactory.getLog(ServiceController.class);
	
	@Autowired
	ServiceService serviceService;
	
	/**
	 * 웹툰 리스트 정보를 조회 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/service/api/toonList.kt")
	public ModelAndView toonList(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String year  = StringUtil.cleanXSS(StringUtil.defaultStr(request.getParameter("year"), "0000"));
		String month = StringUtil.cleanXSS(StringUtil.defaultStr(request.getParameter("month"), "00"));
		
		List<ServiceDomain> toonList = serviceService.getToonList(year, month);
		
		modelAndView.addObject("toonList", toonList);
		
		modelAndView.setViewName("mappingJacksonJsonView");		
		
		return modelAndView;
	}
	
	/**
	 * 회차 리스트 정보를 조회 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/service/api/timesList.kt")
	public ModelAndView timesList(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		int webtoonseq = StringUtil.defaultInt(request.getParameter("webtoonseq"), 0);
		String year    = StringUtil.cleanXSS(StringUtil.defaultStr(request.getParameter("year"), "0000"));
		String month   = StringUtil.cleanXSS(StringUtil.defaultStr(request.getParameter("month"), "00"));
		
		//웹툰 정보
		ServiceDomain toonInfo = serviceService.getToonDetail(year, month, webtoonseq);
		
		//회차 목록
		List<ServiceTimesDomain> timesList = serviceService.getTimesList(year, month, webtoonseq);
		
		modelAndView.addObject("toonInfo", toonInfo);
		modelAndView.addObject("timesList", timesList);
		
		modelAndView.setViewName("mappingJacksonJsonView");	
		
		return modelAndView;
	}
	
	/**
	 * 작화 보기 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/service/timesDetail.kt")
	public ModelAndView timesDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		ApiResultDomain apiResultDomain = null;
		String webtoonseq = StringUtil.defaultStr(request.getParameter("webtoonseq"), "0");
		String timesseq   = StringUtil.defaultStr(request.getParameter("timesseq"), "0");
		String isDev   = StringUtil.defaultStr(request.getParameter("dev"), "0");
		
		String freeyn = serviceService.getTimesFreeyn(timesseq);
			
		//앱서비스일 경우
		if(ServiceUtil.isAppService(request)) {
			logger.debug("[Service timesDetail] isAppService");
			String userAgent  = URLEncoder.encode(request.getHeader("User-Agent"), "UTF-8");
			apiResultDomain = serviceService.getAuthority(webtoonseq, timesseq, userAgent, true, isDev);
			modelAndView.addObject("devicefg", "appservice");
			modelAndView.addObject("navi_bar_type", apiResultDomain.getNavi_bar_type());
		}
		//웹서비스일 경우
		else{
			logger.debug("[Service timesDetail] isWebService");
			String device_sn = "";
			String device_sn_cookie = CookieUtil.getCookie(request, "device_sn");
			
			if(device_sn_cookie !=  null) {
				device_sn = device_sn_cookie;
			}else{
				String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
				String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인	
				device_sn = URLEncoder.encode(StringUtil.defaultStr(request.getParameter("device_sn")), "UTF-8");
				CookieUtil.setCookie(response, "device_sn", device_sn, -1, cookiePath, cookieDomain);
			}
			
			apiResultDomain = serviceService.getAuthority(webtoonseq, timesseq, device_sn, false, isDev);
			modelAndView.addObject("devicefg", "webservice");
		}
		
		//에러일 경우(실패)
		if(apiResultDomain == null) {
			logger.debug("[Service timesDetail] apiResultDomain is null");
			modelAndView.addObject("msg", "apierror");
			modelAndView.setViewName("mobile/service/error");
			return modelAndView;
		}

		//유료 회차이면서 유료권한이 없을 경우
		if(!"Y".equals(freeyn) && !"2".equals(apiResultDomain.getClass_status())){
			logger.debug("[Service timesDetail] class_status is not 2");
			modelAndView.addObject("msg", "autherror");
			modelAndView.setViewName("mobile/service/error");
			return modelAndView;
		}
		
		if("Y".equals(freeyn) && !"2".equals(apiResultDomain.getClass_status()))
			modelAndView.addObject("class_status", apiResultDomain.getClass_status());
		
		modelAndView.setViewName("mobile/service/timesDetail");
		
		return modelAndView;
	}
	
	/**
	 * 작화 보기 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/service/timesJsonDetail.kt")
	public ModelAndView timesJsonDetail(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");	

		// 작품의 상세정보 가져오기
		ToonDomain toon = serviceService.timesDetail(param);
		modelAndView.addObject("toon", toon);
		
		// 작화 이미지 정보 가져오기
		List<ServiceToonImageDomain> toonImageList = serviceService.toonImageList(param);
		modelAndView.addObject("toonImageList", toonImageList);
		
		return modelAndView;		
	}
}