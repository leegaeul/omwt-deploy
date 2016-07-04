/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰 
 * SUBSYSTEM NAME : 사용자 
 * FILE NAME      : EtcController.java
 * DESCRIPTION    : 기타   Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0          kdh				        2014-04-21      init
 *****************************************************************************/
package com.olleh.webtoon.mobile.controller;


import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("MobileRuleController")
public class RuleController {
	
	protected static Log logger = LogFactory.getLog(RuleController.class);
	
	/**
	 * 이용약관 화면으로 이동 처리하는 RequestMapping메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/support/rule.kt")
	public ModelAndView yoyozineList(HttpServletRequest req) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/support/rule");
		
		return modelAndView;
	}
	
}
