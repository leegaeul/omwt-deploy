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

package com.olleh.webtoon.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.comment.domain.CommentEventDomain;
import com.olleh.webtoon.common.dao.comment.service.iface.CommentEventService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.DateUtil;

@Controller
public class CommentEventController {
			
	protected static Log logger = LogFactory.getLog(CommentEventController.class);
			
	@Autowired
	CommentEventService commentEventService;
	
	/**
	 * 댓글 이벤트 참여 약관 동의 여부를 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getCommentEventJoinyn.kt")
	public ModelAndView getCommentEventJoinyn(@ModelAttribute("commentEventDomain") CommentEventDomain commentEvent, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		commentEvent.setRegid(user.getEmail());
		commentEvent.setIdfg(user.getIdfg());
		
		// 댓글 이벤트 약관 동의 여부 확인
		CommentEventDomain commentEventDomain = commentEventService.commentEventseq(commentEvent); 
		
		if(commentEventDomain == null){
			modelAndView.addObject("joinyn", "N");
		}else{
			modelAndView.addObject("joinyn", "Y");
			modelAndView.addObject("phoneNumber", commentEventDomain.getPhonenum());
		}
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 댓글 이벤트 등록 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/commentEventRegistProc.kt")
	public ModelAndView commentEventRegistProc(@ModelAttribute("commentEventDomain") CommentEventDomain commentEventDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		
		commentEventDomain.setRegid(user.getEmail());
		commentEventDomain.setIdfg(user.getIdfg());
		commentEventDomain.setRegdt(DateUtil.getNowDate(1));
		
		//이벤트 참여
		commentEventService.commentEventRegistProc(commentEventDomain);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 댓글 이벤트 당첨여부 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/checkPrizeWinner.kt")
	public ModelAndView checkPrizeWinner(@ModelAttribute("commentEventDomain") CommentEventDomain commentEvent) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		//이벤트 당첨여부 조회
		String winyn = commentEventService.checkPrizeWinner(commentEvent);
		
		if("Y".equals(winyn))
		{
			List<CommentEventDomain> prizeWinnerList = commentEventService.prizeWinnerList(commentEvent);
			modelAndView.addObject("prizeWinnerList", prizeWinnerList);
			
			modelAndView.addObject("winyn", "Y");
		}
		else
		{
			modelAndView.addObject("winyn", "N");
		}
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
}