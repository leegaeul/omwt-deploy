/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : DcbController.java
 * DESCRIPTION    : DCB 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-05-28      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.dcb.service.iface.DcbService;
import com.olleh.webtoon.common.util.RequestUtil;

@Controller("mobileDcbController")
public class DcbController {
			
	protected static Log logger = LogFactory.getLog(DcbController.class);
			
	@Autowired
	DcbService dcbService;
	
	
	/**
	 * DCB List 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/dcb/webtoonList.kt")
	public ModelAndView moveToDcbList() throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/dcb/webtoonList");
		
		return modelAndView;
	}
	
	/**
	 * DCB View 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/dcb/dcbView.kt")
	public ModelAndView moveToDcbView() throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/dcb/dcbView");
		
		return modelAndView;
	}
	
	/**
	 * DCB CouponView 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/dcb/dcbCouponView.kt")
	public ModelAndView moveToDcbCouponView(HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		String uuid = RequestUtil.getUuid(request);
		
		modelAndView.addObject("couponNumber",dcbService.getDcbCoupon(uuid));
		
		modelAndView.setViewName("mobile/dcb/dcbCouponView");
		
		return modelAndView;
	}
}

