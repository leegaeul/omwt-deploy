package com.olleh.webtoon.common.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.olleh.webtoon.common.constant.AppConstant;
import com.olleh.webtoon.common.util.TRMiscUtils;
import com.olleh.webtoon.task.BlueMembershipTask;

@Controller
public class RedirectController
{
	protected Log logger = LogFactory.getLog(RedirectController.class);
	
	@Autowired
	private BlueMembershipTask bmTask;
	
	
	@RequestMapping(value = "/test/mac.kt")
	public ModelAndView mac()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName( AppConstant.JSON_VIEW_NAME );
		
		try
		{
			mv.addObject( "mac", TRMiscUtils.getServerTokenValue() );
		}
		catch( Exception e)
		{
			logger.error( e.getMessage() );
			logger.error( ExceptionUtils.getStackTrace( e ) );
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/test/task.kt")
	public ModelAndView task()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName( AppConstant.JSON_VIEW_NAME );
		
		bmTask.dailyTask();
		
		return mv;
	}
}
