/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : CookieInterceptor.java
 * DESCRIPTION    : 웹툰 및 올레닷컴 쿠키가 있는지 확인하여 있을 경우 파싱해서 
 *                  Request 객체에 넣어주는 인터셉터 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-15      init
 *****************************************************************************/

package com.olleh.webtoon.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.olleh.webtoon.common.util.RequestUtil;

@Component
public class RenewalInterceptor extends HandlerInterceptorAdapter {

	protected static Log logger = LogFactory.getLog(RenewalInterceptor.class);
		
	/**
	 * 웹툰 쿠키와 올레닷컴 쿠키를 확인하여 로그인한 사용자인지 여부를 
	 * HttpServletRequest 객체에 세팅한다.
	 * 실제 ID는 성능을 위해 필요한 경우 해당 컨트롤러에서 복호화한다.
	 * 
	 * @param HttpServletRequest request   : HttpServletRequest 객체
     * @param HttpServletResponse response : HttpServletResponse 객체
     * @param Object handler               : controller 객체
     * @return boolean                     : 처리 성공 여부 (false를 반환하면 controller가 실행되지 않는다.)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = RequestUtil.getRequestUri(request); //Request URI
		if(uri.indexOf(".kt") > 0) 
		{
		
			if( 
					//개편 URL
					!request.getRequestURI().contains( "/web/" ) 
					&& !request.getRequestURI().contains( "/api/" )
					&& !request.getRequestURI().contains( "/ot/" )
					
					//개편 후 필요한 URL
					&& !request.getRequestURI().contains( "/old/api/" )
					&& !request.getRequestURI().contains( "/service/api/" )
					&& !request.getRequestURI().contains( "/service/timesDetail" )
					&& !request.getRequestURI().contains( "/service/timesJsonDetail" )
					
					
					//이벤트 관련
					&& !request.getRequestURI().contains( "/eventpromotion/" )
					&& !request.getRequestURI().contains( "/common/" )
					
					
					&& !request.getRequestURI().contains( "/m/main.kt" )
					
			){		

				String webtoonseq = RequestUtil.getParameter(request, "webtoonseq", true) ;
				String timesseq = RequestUtil.getParameter(request, "timesseq", true) ;
				String webtoonSeq = RequestUtil.getParameter(request, "webtoonSeq", true) ;
				String authorseq = RequestUtil.getParameter(request, "authorseq", true) ;
				
				
				//기존 회차 목록 Redirect				
				if(request.getRequestURI().contains( "/toon/timesList.kt" ))
					response.sendRedirect("/web/times_list.kt?webtoonseq="+ webtoonseq);
				
				//전전 기존 회차 목록 Redirect
				else if(request.getRequestURI().contains( "/main/times_list.kt" ))
					response.sendRedirect("/web/times_list.kt?webtoonseq="+ webtoonSeq);
					
				//기존 소설 회차목록 Redirect
				else if(request.getRequestURI().contains( "/novel/novelList.kt" ))
					response.sendRedirect("/web/times_nlist.kt?webtoonseq="+ webtoonseq);
				
				//기존 회차 상세
				else if(request.getRequestURI().contains( "/toon/timesDetail.kt" ) || request.getRequestURI().contains( "/main/times_detail.kt" ))
					response.sendRedirect("/web/times_view.kt?webtoonseq="+ webtoonseq +"&timesseq="+timesseq);
				
				//기존 소설 회차 상세
				else if(request.getRequestURI().contains( "/novel/novelDetail.kt" ))
					response.sendRedirect("/web/times_nview.kt?webtoonseq="+ webtoonseq +"&timesseq="+timesseq);
				
				//기존 작가 상세
				else if(request.getRequestURI().contains( "/toon/authorDetail.kt" ))
					response.sendRedirect("/web/author_view.kt?authorseq="+ authorseq);
				
				//이벤트 Redirect
				else if(request.getRequestURI().contains( "/applay/eventList.kt" ) || request.getRequestURI().contains( "/subcontents/yoyozine_list.kt" ))
					response.sendRedirect("/web/event_list.kt?eventseq="+ RequestUtil.getParameter(request, "eventseq", true) );
				
				//이벤트 Redirect
				else if(request.getRequestURI().contains( "/applay/eventDetail.kt" ))
					response.sendRedirect("/web/event_view.kt?eventseq="+ RequestUtil.getParameter(request, "eventseq", true) );
				
				//요요진 Redirect
				else if(request.getRequestURI().contains( "/yoyozine/list.kt" ) || request.getRequestURI().contains( "/subcontents/yoyozine_list.kt" ))
					response.sendRedirect("/web/yoyozine_list.kt?yoyozineseq"+ RequestUtil.getParameter(request, "yoyozineseq", true) );
				
				//요요진 Redirect
				else if(request.getRequestURI().contains( "/yoyozine/detail.kt" ) || request.getRequestURI().contains( "/subcontents/yoyozine_detail.kt" ))
					response.sendRedirect("/web/yoyozine_view.kt?yoyozineseq"+ RequestUtil.getParameter(request, "yoyozineseq", true) );
				
				//메인 Redirect
				else if(request.getRequestURI().contains( "/toon/weekList.kt" ) || request.getRequestURI().contains( "/main/week_list.kt" ))
					response.sendRedirect("/web/main.kt");
				
				//기타 메인 페이지
				else
					response.sendRedirect("/web/main.kt");
				
				
				return false;
			}
			
			
			

			return true;
		}
		
		return true;		
	}

}