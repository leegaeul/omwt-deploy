package com.olleh.webtoon.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.kmc.domain.KmcFailDomain;
import com.olleh.webtoon.common.dao.kmc.service.iface.KmcFailService;

@Controller
public class KmcFailController 
{	
	protected static Log logger = LogFactory.getLog(KmcFailController.class);
	
	@Autowired
	KmcFailService kmcFailService;
	
	
	/**
	 * kmc 인증 실패 로그 등록 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/kmc/kmcFailRegistProc.kt")
	public ModelAndView commentRegistProc(@ModelAttribute("kmcFailDomain") KmcFailDomain kmcFailDomain, HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();		
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		com.olleh.webtoon.common.dao.user.domain.UserDomain userDomain = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		if(userDomain != null  && userDomain.getEmail() != null && !"".equals(userDomain.getEmail())){
			kmcFailDomain.setIdfg(userDomain.getIdfg());
			kmcFailDomain.setRegid(userDomain.getEmail());
		}				
		
		kmcFailService.insertKmcFail(kmcFailDomain);

		modelAndView.addObject("kmcFailDomain", null);
		
		return modelAndView;	
	}
}