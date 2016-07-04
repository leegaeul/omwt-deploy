package com.olleh.webtoon.mobile.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.dao.advertisement.domain.AdvertisementDomain;
import com.olleh.webtoon.common.dao.advertisement.service.iface.AdvertisementService;

@Controller("MobileAdvertisementController")
public class AdvertisementController{
	
	protected static Log logger = LogFactory.getLog(AdvertisementController.class);
	
	@Autowired
	AdvertisementService advertisementService;

	/**
	 * 광고 영역 include 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/common/advertise.kt")
	public ModelAndView advertise(@ModelAttribute("advertisementDomain") AdvertisementDomain advertisementDomain) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/include/advertisement");		
		
		//광고정보 조회
		List<AdvertisementDomain> advertiseList = advertisementService.advertisementList(advertisementDomain);
		modelAndView.addObject("advertiseList", advertiseList);
		modelAndView.addObject("areafg", advertisementDomain.getAreafg());
	
		return modelAndView;
		
	}
	
}