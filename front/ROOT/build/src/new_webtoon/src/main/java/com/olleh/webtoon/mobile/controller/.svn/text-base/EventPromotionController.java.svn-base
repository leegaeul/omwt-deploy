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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayEventDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayJoinDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayWinnerDomain;
import com.olleh.webtoon.common.dao.cutplay.service.iface.CutPlayEventService;
import com.olleh.webtoon.common.dao.cutplay.util.CutPlayEventUtil;
import com.olleh.webtoon.common.dao.eventpromotion.domain.EventPromotionDomain;
import com.olleh.webtoon.common.dao.eventpromotion.service.iface.EventPromotionService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;
import com.olleh.webtoon.olltoon.common.util.KeyUtil;

@Controller("mobileEventPromotionController")
public class EventPromotionController {
			
	protected static Log logger = LogFactory.getLog(EventPromotionController.class);
	
	private final String ICE_EVENT_STARTDT_TEST = "20140813100001";
	private final String ICE_EVENT_ENDTDT_TEST  = "20140902235959";

	private final String ICE_EVENT_STARTDT = "20140813140001";
	private final String ICE_EVENT_ENDTDT  = "20140902235959";
			
	@Autowired
	EventPromotionService eventPromotionService;

	@Autowired
	CutPlayEventService cutPlayEventService;
	
	/**
	 * 모바일 아이스크림 신규 가입 이벤트 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/iceEventIframe.kt")
	public ModelAndView moveToIceEventIframe() throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/eventpromotion/iceEventIframe");
		
		return modelAndView;
	}
	
	/**
	 * 모바일 아이스크림 신규 가입 이벤트 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/iceMemberEvent.kt")
	public ModelAndView moveToIceMemberEvent(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain, 
			HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
		
		//PC 기기로 접속시 PC 로그인 페이지로
		if(!RequestUtil.isMobile(request)){			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain.ssl")+"/eventpromotion/iceMemberEvent.kt" + urlcd);		
			return modelAndView;
		}
		
		//로그인 시 이벤트 참여여부 체크
		if(AuthUtil.isLogin(request))
		{
			UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
			eventPromotionDomain.setRegid(user.getEmail());
			
			if(!"real".equals(System.getProperty("serverType"))){
				eventPromotionDomain.setStartdt(ICE_EVENT_STARTDT_TEST);
				eventPromotionDomain.setEnddt(ICE_EVENT_ENDTDT_TEST);				
			}else{
				eventPromotionDomain.setStartdt(ICE_EVENT_STARTDT);
				eventPromotionDomain.setEnddt(ICE_EVENT_ENDTDT);	
			}
			
			//이벤트 참여 가능 여부 체크
			if("open".equals(user.getIdfg()))
			{
				String joinyn = eventPromotionService.getEventJoinyn(eventPromotionDomain);
				modelAndView.addObject("joinyn", joinyn);
			}
			
			//참여 여부 체크
			String duplicateyn = eventPromotionService.geIceEventDuplicateyn(eventPromotionDomain);
			modelAndView.addObject("duplicateyn", duplicateyn);
		}
		
		modelAndView.setViewName("mobile/eventpromotion/iceMemberEvent");
		
		return modelAndView;
	}
	
	/**
	 * 모바일 아이스크림 Sns 이벤트 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/iceSnsEvent.kt")
	public ModelAndView moveToIceSnsEvent(HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
		
		//PC 기기로 접속시 PC 로그인 페이지로
		if(!RequestUtil.isMobile(request)){			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain.ssl")+"/eventpromotion/iceSnsEvent.kt" + urlcd);		
			return modelAndView;
		}
		
		modelAndView.setViewName("mobile/eventpromotion/iceSnsEvent");
		
		return modelAndView;
	}
	
	/**
	 * 모바일 아이스크림 이벤트 당첨자 발표 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/iceEventResult.kt")
	public ModelAndView moveToIceEventResult(HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
		
		//PC 기기로 접속시 PC 로그인 페이지로
		if(!RequestUtil.isMobile(request)){			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain.ssl")+"/eventpromotion/iceEventResult.kt" + urlcd);		
			return modelAndView;
		}
		
		modelAndView.setViewName("mobile/eventpromotion/iceEventResult");
		
		return modelAndView;
	}
	
	/**
	 * 공모전 이벤트 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/contestEvent.kt")
	public ModelAndView moveToContestEvent(HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
		
		//PC 기기로 접속시 PC 페이지로
		if(!RequestUtil.isMobile(request)){			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain.ssl")+"/eventpromotion/contestEvent.kt" + urlcd);		
			return modelAndView;
		}
		
		modelAndView.setViewName("mobile/eventpromotion/contestEvent");
		
		return modelAndView;
	}
	
	/**
	 * 모카 쿠폰 이벤트 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/couponEvent.kt")
	public ModelAndView moveToCouponEvent() throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/eventpromotion/couponEvent");
		
		return modelAndView;
	}
	
	/**
	 * 공모전 이벤트 당첨자 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/contestEventResult.kt")
	public ModelAndView moveToContestEventResult() throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/eventpromotion/contestEventResult");
		
		return modelAndView;
	}
	
	/**
	 * 공모전 스티커,댓글 이벤트 당첨자 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/contestUserEventResult.kt")
	public ModelAndView moveToContestUserEventResult() throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/eventpromotion/contestUserEventResult");
		
		return modelAndView;
	}
	
	/**
	 * 모카 쿠폰 이벤트 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/couponEventResult.kt")
	public ModelAndView moveToCouponEventResult() throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/eventpromotion/couponEventResult");
		
		return modelAndView;
	}
	
	/**
	 * 모바일 냄보소 이벤트 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/naembosoEvent.kt")
	public ModelAndView moveToNaembosoEvent(HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
		
		//PC 기기로 접속시 PC 페이지로
		if(!RequestUtil.isMobile(request) && "real".equals(System.getProperty("serverType"))){			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain.ssl")+"/eventpromotion/naembosoEvent.kt" + urlcd);		
			return modelAndView;
		}
		
		modelAndView.setViewName("mobile/eventpromotion/naembosoEvent");
		
		return modelAndView;
	}
	
	/**
	 * 모바일 냄보소 이벤트 당첨자 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/naembosoEventResult.kt")
	public ModelAndView moveToNaembosoEventResult() throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/eventpromotion/naembosoEventResult");
		
		return modelAndView;
	}
	
	/**
	 * 2주년 이벤트 이벤트 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/2yearsEvent.kt")
	public ModelAndView moveTo2yearsEvent(HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
		
		//PC 기기로 접속시 PC 페이지로
		if(!RequestUtil.isMobile(request) && "real".equals(System.getProperty("serverType"))){			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain.ssl")+"/eventpromotion/2yearsEvent.kt" + urlcd);		
			return modelAndView;
		}
		
		modelAndView.setViewName("mobile/eventpromotion/2yearsEvent");
		
		return modelAndView;
	}
	
	/**
	 * 컷스프레 이벤트 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/cutplayEvent.kt")
	public ModelAndView moveToCutPlayEvent(HttpServletRequest request , @ModelAttribute("cutPlayJoinDomain") CutPlayJoinDomain cutPlayJoinDomain) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mobile/eventpromotion/cutplay_main");
		
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
		
		//PC 기기로 접속시 PC 페이지로
		if(!RequestUtil.isMobile(request) && "real".equals(System.getProperty("serverType"))){			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain.ssl")+"/eventpromotion/cutplayEvent.kt" + urlcd);
			return modelAndView;
		}
		
		String userid = null;
		String idfg = null;
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token != null && !"".equals(ot_token)) {
			
			//유저정보 가져오기
			String tokenJson = KeyUtil.keyToString(ot_token);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
			
			userid = (String)userMap.get("userid");
			idfg = (String)userMap.get("idfg");
		}
		
		int mBestIdx = 0;
		//현재 이벤트 차수 정보 조회
		CutPlayEventDomain curEventDomain = cutPlayEventService.getCutPlayEventCurTimes(DateUtil.getNowDate(1));
		mBestIdx = curEventDomain.getEventtimes();

		//차수별 베스트 컷스프레 조회
		Map<Integer, List<CutPlayJoinDomain>> map = new HashMap<Integer, List<CutPlayJoinDomain>>();
		for(int i = 0; i < curEventDomain.getEventtimes(); i++) {
			List<CutPlayJoinDomain> list = cutPlayEventService.getCutPlayEventBest((mBestIdx), userid);
			mBestIdx = mBestIdx - 1;
			String startdt = null;
			String enddt = null;
			int eventtimes = 0;
			
			if(list != null && list.size() > 0) {
				startdt = list.get(0).getStartdt();
				enddt = list.get(0).getEnddt();
				eventtimes = list.get(0).getEventtimes();
			} else {
				list =  new ArrayList<CutPlayJoinDomain>();
				
				CutPlayEventDomain cutPlayEventDomain = cutPlayEventService.getCutPlayEventTimes((i+1));
				
				startdt = cutPlayEventDomain.getStartdt();
				enddt = cutPlayEventDomain.getEnddt();
				eventtimes = cutPlayEventDomain.getEventtimes();
			}
			
			//이미지 도메인 설정
			CutPlayEventUtil.imageValidate(list);
			
			// 1위 없는 경우
			if(list.size() == 0) {
				CutPlayJoinDomain domain = new CutPlayJoinDomain();
				domain.setBestorder(1);
				domain.setWebtoonimagepath("/images/pc/event/cutplay/main/@cutspress_slide_top_first01.jpg");
				domain.setImagepath("/images/pc/event/cutplay/main/@cutspress_slide_btm_first01.jpg");
				domain.setEventtimes(eventtimes);
				domain.setStartdt(startdt);
				domain.setEnddt(enddt);
				domain.setDefafultyn("Y");
				
				list.add(domain);
			}
			
			// 2위 없는 경우
			if(list.size() == 1) {				
				CutPlayJoinDomain domain = new CutPlayJoinDomain();
				domain.setBestorder(2);
				domain.setWebtoonimagepath("/images/pc/event/cutplay/main/@cutspress_slide_top_first02.jpg");
				domain.setImagepath("/images/pc/event/cutplay/main/@cutspress_slide_btm_first02.jpg");
				domain.setEventtimes(eventtimes);
				domain.setStartdt(startdt);
				domain.setEnddt(enddt);
				domain.setDefafultyn("Y");
				
				list.add(domain);
			} 
			
			// 3위 없는 경우
			if(list.size() == 2) {
				CutPlayJoinDomain domain = new CutPlayJoinDomain();
				domain.setBestorder(3);
				domain.setWebtoonimagepath("/images/pc/event/cutplay/main/@cutspress_slide_top_first03.jpg");
				domain.setImagepath("/images/pc/event/cutplay/main/@cutspress_slide_btm_first03.jpg");
				domain.setEventtimes(eventtimes);
				domain.setStartdt(startdt);
				domain.setEnddt(enddt);
				domain.setDefafultyn("Y");
				
				list.add(domain);
			}
			
			map.put((i+1), list);
		}
		
		List<CutPlayJoinDomain> detailImageList = null;
		
		//상세 링크 조회
		if(cutPlayJoinDomain.getEventseq() > 0) {
			if(ot_token != null && !"".equals(ot_token)) {
				cutPlayJoinDomain.setUserid(userid);
				cutPlayJoinDomain.setIdfg(idfg);
			}
			
			CutPlayJoinDomain detail = cutPlayEventService.getCutPlayJoinDetail(cutPlayJoinDomain);
			CutPlayEventUtil.imageValidate(detail);
			modelAndView.addObject("detail", detail);
			
			detailImageList = cutPlayEventService.getMobileImageList(cutPlayJoinDomain);
			CutPlayEventUtil.imageValidate(detailImageList);
		}
		
		//약관 동의 여부
		
		int cnt = 0 ;
		
		if(ot_token != null && !"".equals(ot_token)) {
			cutPlayJoinDomain.setUserid(userid);
			cutPlayJoinDomain.setIdfg(idfg);
			cutPlayJoinDomain.setEventfg("join");
			cnt = cutPlayEventService.getCutPlayEventCnt(cutPlayJoinDomain);
		}
		modelAndView.addObject("agreeyn", cnt > 0 ? "Y" : "N");
				
		modelAndView.addObject("detailImageList", detailImageList);
		modelAndView.addObject("event", curEventDomain);
		modelAndView.addObject("best", map);
		
		return modelAndView;
	}
	
	/**
	 * 컷스프레 이벤트 당첨자 페이지로 이동하는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/eventpromotion/cutplayEventWinner.kt")
	public ModelAndView cutplayEventWinner(HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
	
		//PC 기기로 접속시 PC 페이지로
		if(!RequestUtil.isMobile(request) && "real".equals(System.getProperty("serverType"))){			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain.ssl")+"/eventpromotion/cutplayEventWinner.kt" + urlcd);
			return modelAndView;
		}
		
		//로그인 체크
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token == null || "".equals(ot_token)) {
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain.ssl")+"/web/login.kt" + urlcd);
			return modelAndView;
		}
		
		//유저정보 가져오기
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		CutPlayWinnerDomain cutPlayWinnerDomain = new CutPlayWinnerDomain();
		cutPlayWinnerDomain.setUserid((String)userMap.get("userid"));
		cutPlayWinnerDomain.setIdfg((String)userMap.get("idfg"));
		cutPlayWinnerDomain.setRegdt(DateUtil.getNowDate(1));
		
		List<CutPlayWinnerDomain> winnerList = cutPlayEventService.getCutPlayPrizeList(cutPlayWinnerDomain);
		
		if(winnerList == null || winnerList.size() < 1) {
			modelAndView.addObject("winyn", "N");
		}else {
			modelAndView.addObject("winyn", "Y");
		}
		
		modelAndView.addObject("winnerList", winnerList);
		
		modelAndView.setViewName("mobile/eventpromotion/cutplay_winner");
		
		return modelAndView;
	}
}