package com.olleh.webtoon.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import com.olleh.webtoon.common.code.ApiResultCode;
import com.olleh.webtoon.common.code.DomainNameCode;
import com.olleh.webtoon.common.constant.AppConstant;
import com.olleh.webtoon.common.dao.api.domain.ApiResultDomain;

public class ApiExceptionResolver implements HandlerExceptionResolver, AppConstant
{
	private Logger	logger	= Logger.getLogger( this.getClass() );
	
	public ModelAndView resolveException( HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception )
	{
		ModelAndView mv = new ModelAndView();
		request.setAttribute( "exception", exception );
		
		mv.setViewName( JSON_VIEW_NAME );
		
		if( exception instanceof ApiException )
		{
			ApiException appException = (ApiException)exception;
			mv.addObject( DomainNameCode.result, appException.getResult() );
			
			logger.error( appException.getResult().getCode() );
			logger.error( appException.getResult().getMessage() );
			logger.error( appException.getResult().getDebugMessage() );
			logger.error( ExceptionUtils.getStackTrace( exception ) );
		}
		else if( request.getRequestURI().contains( "/api/" ) )
		{
			mv.addObject( DomainNameCode.result, new ApiResultDomain( ApiResultCode.system_error ) );
			
			logger.error( ExceptionUtils.getStackTrace( exception ) );
		}
		else
			return new ModelAndView("redirect:/common/exception.kt?errorcd=500");
		
		return mv;
	}
}
