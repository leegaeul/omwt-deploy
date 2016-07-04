/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : QNAController.java
 * DESCRIPTION    : 1:1문의  Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0          jss          	  2014-04-17	1:1문의      
 *****************************************************************************/

package com.olleh.webtoon.pc.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.pc.controller.QNAController;
import com.olleh.webtoon.common.dao.support.domain.QnaDomain;
import com.olleh.webtoon.common.dao.support.service.iface.QNAService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
public class QNAController
{
	protected static Log	logger	= LogFactory.getLog( QNAController.class );

	@Autowired
	QNAService qnaService;


	/**
	 * 문의하기 등록 화면으로 이동 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/support/qnaRegist.kt")
	public ModelAndView registQnaForm(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		
		//모바일 기기로 접속시 모바일 로그인 페이지로
		if(RequestUtil.isMobile(request)){
			modelAndView.setViewName("redirect:/m/support/qnaRegist.kt");
			return modelAndView;
		}
		
		modelAndView.setViewName( "/pc/support/qnaRegist" );
		
		return modelAndView;

	}


	/**
	 * 문의하기 등록 처리
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping( value = "/support/qnaRegistJsonProc.kt" )
	public ModelAndView registQnaProc( HttpServletRequest req )
	{
		ModelAndView modelAndView = new ModelAndView();
		
		try
		{
			QnaDomain qnaDomain = new QnaDomain();
			
			qnaDomain.setQnafg( StringUtil.cleanXSS( RequestUtil.getParameter( req, "qnafg", true ) ) );
			qnaDomain.setTitle( StringUtil.cleanXSS( RequestUtil.getParameter( req, "title", false ) ) );
			qnaDomain.setContents( StringUtil.cleanXSS( RequestUtil.getParameter( req, "contents", false ) ) );
			qnaDomain.setEmail( StringUtil.cleanXSS( RequestUtil.getParameter( req, "email", true ) ) );
			qnaDomain.setPhone1( StringUtil.cleanXSS( RequestUtil.getParameter( req, "phone1", true ) ) );
			qnaDomain.setRegdt( DateUtil.getNowDate( 1 ) );
			
			//ip, 접속채널 추가 - 20150119
			qnaDomain.setServicefg(RequestUtil.getClientDeviceInfo(req));
			qnaDomain.setIp(RequestUtil.getRequestIp(req));
			
			//로그인 정보
			if(AuthUtil.isLogin(req)){				
				//회원정보 복호화
				UserDomain userDomain = AuthUtil.getDecryptUserInfo(req);
				
				if(userDomain != null && !"".equals(userDomain.getEmail())){
					
					qnaDomain.setIdfg(userDomain.getIdfg());
					qnaDomain.setRegid(userDomain.getEmail());
					
					if(qnaDomain.getEmail() == null || "".equals(qnaDomain.getEmail())){
						qnaDomain.setEmail(userDomain.getEmail());
					}
				}
			}

			// QNA등록
			qnaService.registQnaProc( qnaDomain );

		} catch( Exception e ){
			logger.error( e );
		}

		modelAndView.setViewName( "mappingJacksonJsonView" );
		
		return modelAndView;
	}

	
}
