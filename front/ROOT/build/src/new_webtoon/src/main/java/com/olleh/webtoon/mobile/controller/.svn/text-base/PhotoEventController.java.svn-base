/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ExceptionController.java
 * DESCRIPTION    : 에러처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-05-28      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.photoevent.service.iface.PhotoEventService;

@Controller("mobilePhotoEventController")
public class PhotoEventController {
			
	protected static Log logger = LogFactory.getLog(PhotoEventController.class);
			
	@Autowired
	PhotoEventService photoEventService;
	
	
	/**
	 * 포토이벤트 PC 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/photoEvent.kt")
	public ModelAndView moveToPhotoEvent() throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/eventpromotion/photoEvent");
		
		return modelAndView;
	}
}

