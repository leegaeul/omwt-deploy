package com.olleh.webtoon.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.kmc.domain.KmcLogDomain;
import com.olleh.webtoon.common.dao.kmc.service.iface.KmcLogService;

@Controller
public class KmcLogController 
{	
	protected static Log logger = LogFactory.getLog(KmcLogController.class);
	
	@Autowired
	KmcLogService kmcLogService;
	
	
	/**
	 * kmc 로그 등록 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/kmc/kmcLogRegistProc.kt")
	public ModelAndView kmcLogRegistProc(@ModelAttribute("kmcLogDomain") KmcLogDomain kmcLogDomain, HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		com.olleh.webtoon.common.dao.user.domain.UserDomain userDomain = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		if(userDomain != null  && userDomain.getEmail() != null && !"".equals(userDomain.getEmail())){
			kmcLogDomain.setIdfg(userDomain.getIdfg());
			kmcLogDomain.setRegid(userDomain.getEmail());
			kmcLogDomain.setResult("N");
		}				
		
		kmcLogService.insertKmcLog(kmcLogDomain);
		modelAndView.addObject("kmcLogDomain", null);
		
		return modelAndView;	
	}
	
	/**
	 * kmc 로그 업데이트 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/kmc/kmcLogModifyProc.kt")
	public ModelAndView kmcLogModifyProc(@ModelAttribute("kmcLogDomain") KmcLogDomain kmcLogDomain, HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.setViewName("mappingJacksonJsonView");				
		
		kmcLogService.modifyKmcLog(kmcLogDomain);
		modelAndView.addObject("kmcLogDomain", null);
		
		return modelAndView;	
	}
}