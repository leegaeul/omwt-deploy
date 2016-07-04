/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : RankingController.java
 * DESCRIPTION    : 랭킹 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Kim.Hyung.Boo      2014-07-30      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.ranking.domain.RankingRefwebtoonDomain;
import com.olleh.webtoon.common.dao.ranking.domain.YoRankingDomain;
import com.olleh.webtoon.common.dao.ranking.service.iface.RankingService;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.service.iface.ToonService;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller("MobileRankingController")
public class RankingController {
			
	protected static Log logger = LogFactory.getLog(RankingController.class);
	
	@Autowired
	RankingService rankingService;
	
	@Autowired
	UserServiceIface userServiceIface;  
	
	@Autowired
	ToonService toonService;
	
	/**
	 * 실시간 웹툰 랭킹 화면
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/ranking/realtimeList.kt")
	public ModelAndView realtimeList() throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("redirect:/m/ranking/monthList.kt");
		
		//modelAndView.setViewName("mobile/ranking/realtimeList");
		
		return modelAndView;
	}
	
	
	/**
	 * 실시간 웹툰 랭킹 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/ranking/realtimeJsonList.kt")
	public ModelAndView realtimeJsonList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		/*
		//실시간 랭킹 화면의 ModelAndView객체를 캐시에서 가져오고 없으면 구성해서 넣는다.
		RankingCache rankingCache = RankingCache.getInstance();
		ModelAndView cachedModelAndView = rankingCache.getCachedModelAndView();
		
		if(cachedModelAndView != null) 
		{
			modelAndView = cachedModelAndView;
		}
		else
		{
			// 실시간 랭킹 웹툰 목록
			List<ToonDomain> realtimeList = null; //rankingService.realtimeList(param);
			modelAndView.addObject("realtimeList", realtimeList);
			
			//정보를 모두 취합한 후 캐시에 넣는다.
			rankingCache.putCache(modelAndView);
		}
		*/		
		
		modelAndView.setViewName("mappingJacksonJsonView");
		return modelAndView;
	}
	
	
	/**
	 * 월간 랭킹 목록 화면
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/ranking/monthList.kt")
	public ModelAndView monthList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		// 월간 랭킹 날짜(년/월) 목록 조회
		List<String> dateList = rankingService.rankingDate();
		modelAndView.addObject("dateList", dateList);
				
		// 월간 랭킹 웹툰 목록
		if((param.get("rankingmt") == null || "".equals(param.get("rankingmt"))) && dateList != null)
		{
			param.put("rankingmt", dateList.get(0));
		}
		
		// 날짜 정보
		modelAndView.addObject("rankingmt", param.get("rankingmt"));
		
		modelAndView.setViewName("mobile/ranking/monthList");
		
		return modelAndView;
	}
	
	
	/**
	 * 월간 랭킹 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/ranking/monthJsonList.kt")
	public ModelAndView monthJsonList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//페이징 처리
		String pageSizeParam = (String)param.get("pageSize");		
		int pageSize = StringUtil.defaultInt(pageSizeParam, 5);
		param.put("pageSize", pageSize);
		
		String startRowParam = (String)param.get("startRowNo");		
		int startRowNo = StringUtil.defaultInt(startRowParam, 0);
		param.put("startRowNo", startRowNo);
		
		List<ToonDomain> monthList = rankingService.monthList(param);
		modelAndView.addObject("monthList", monthList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	
	/**
	 * Yo 랭킹 목록 화면
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/ranking/yorankingList.kt")
	public ModelAndView yorankingList(@RequestParam Map<String,Object> param) throws Exception 
	{
		ModelAndView modelAndView = new ModelAndView();
		
		String mode = StringUtil.defaultStr((String)param.get("mode"));
		
		int totalCnt = rankingService.yorangkingListCnt(param);
						
		//yo 랭킹이 하나일 경우
		if(totalCnt == 1 && !"list".equals(mode)){
			param.put("pageSize", 10);
			param.put("startRowNo", 0);
			List<YoRankingDomain> yorankingList = rankingService.yorangkingList(param);			
			YoRankingDomain yoRanking = yorankingList.get(0);
			
			modelAndView.setViewName("redirect:/m/ranking/yorankingDetail.kt?rankingseq="+yoRanking.getRankingseq());
			return modelAndView;
		}
		
		modelAndView.setViewName("mobile/ranking/yorankingList");
		
		return modelAndView;
	}
	
	
	/**
	 * Yo 랭킹 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/ranking/yorankingJsonList.kt")
	public ModelAndView yorankingJsonList(@RequestParam Map<String,Object> param) throws Exception 
	{
		ModelAndView modelAndView = new ModelAndView();
		
		//페이징 처리
		String pageSizeParam = (String)param.get("pageSize");		
		int pageSize = StringUtil.defaultInt(pageSizeParam, 5);
		param.put("pageSize", pageSize);
		
		String startRowParam = (String)param.get("startRowNo");		
		int startRowNo = StringUtil.defaultInt(startRowParam, 0);
		param.put("startRowNo", startRowNo);
				
		// yo 랭킹 웹툰 목록 갯수
		int totalCnt = rankingService.yorangkingListCnt(param);
		modelAndView.addObject("totalCnt", totalCnt);
		
		// yo 랭킹 웹툰 목록
		List<YoRankingDomain> yorankingList = rankingService.yorangkingList(param);
		modelAndView.addObject("yorankingList", yorankingList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	
	/**
	 * Yo 랭킹 상세 화면
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/ranking/yorankingDetail.kt")
	public ModelAndView yorankingDetail(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ModelAndView modelAndView = new ModelAndView();
		
		int rankingseq = StringUtil.defaultInt((String)param.get("rankingseq"));		
		modelAndView.addObject("rankingseq", rankingseq);
		
		//모바일 기기가 아닐경우 pc페이지로
//		if(!RequestUtil.isMobile(request))
//		{
//			String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
//			
//			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
//			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/ranking/yorankingDetail.kt" + urlcd);
//			return modelAndView;
//		}
		
		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		// 로그인일 경우 쿠키에 저장되어있는 네임콘 정보와 DB에서 회원이 보유한 네임콘 정보가 다를경우 쿠키값에 다시 셋팅
		if(AuthUtil.isLogin(request)){
			//UserDomain newUserDomain = userServiceIface.getUserInfoByEmail(userDomain.getEmail());
			OllehUserDomain newUserDomain = null;
			
			if("open".equals(userDomain.getIdfg()))
				 newUserDomain = (UserDomain)userServiceIface.getUserInfoByEmail(userDomain.getEmail());
			if("olleh".equals(userDomain.getIdfg()))
				newUserDomain = userServiceIface.ollehUserDetail(userDomain.getEmail());
			
			if(userDomain.getNameconseq() != newUserDomain.getNameconseq()){
				String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
				String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
				String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
				int intCookieAge = Integer.parseInt(cookieAge);
				
				//쿠키정보 변경		
				CookieUtil.setCookie(response, "u_nc_seq", String.valueOf(newUserDomain.getNameconseq()), intCookieAge, cookiePath, cookieDomain);
				CookieUtil.setCookie(response, "u_nc_url", newUserDomain.getNameconurl(), intCookieAge, cookiePath, cookieDomain);
				CookieUtil.setCookie(response, "u_mnc_url", newUserDomain.getMnameconurl(), intCookieAge, cookiePath, cookieDomain);
			}
		}
		
		// 로그인 여부 체크
		int myStickerCnt = 0;
		if(AuthUtil.isLogin(request)) 
		{
			if(userDomain != null && !"".equals(userDomain.getEmail()))
			{
				StickerDomain sticker = new StickerDomain();
				
				sticker.setIdfg(userDomain.getIdfg());
				sticker.setRegid(userDomain.getEmail());
				sticker.setRankingseq(rankingseq);			
				
				myStickerCnt = rankingService.myStickerCount(sticker);
			}
		}
		
		// 사용자 스티커 등록여부 및 갯수
		modelAndView.addObject("myStickerCnt", myStickerCnt);
		
		// 스티커 갯수
		modelAndView.addObject("stickerCnt", rankingService.stickerCount(rankingseq));
		
		// 관련 작품 리스트
		List<RankingRefwebtoonDomain> refwebtoonList = rankingService.getRefwebtoonInfo(rankingseq);
		modelAndView.addObject("refwebtoonList", refwebtoonList);
		
		// yo 랭킹 상세 정보
		YoRankingDomain yorankingDomain = rankingService.getYoRankingInfo(rankingseq);
		modelAndView.addObject("yorankingDomain", yorankingDomain);
		
		modelAndView.setViewName("mobile/ranking/yorankingDetail");
		
		return modelAndView;
	}
	
	
	/**
	 * Yo 랭킹 목록 화면
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/ranking/yorankingJsonDetail.kt")
	public ModelAndView yorankingJsonDetail(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception
	{		
		ModelAndView modelAndView = new ModelAndView();
		
		int rankingseq = StringUtil.defaultInt((String)param.get("rankingseq"));
		
		// 유저정보
//		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
//		
//		//스티커 갯수
//		modelAndView.addObject("stickerCnt", toonService.stickerCount(Integer.parseInt(timesseq)));
//					
//		//로그인 여부 체크
//		int myStickerCnt = 0;
//		if(AuthUtil.isLogin(request)) {
//			
//			if(userDomain != null && !"".equals(userDomain.getEmail())){
//				StickerDomain sticker = new StickerDomain();
//				
//				sticker.setIdfg(userDomain.getIdfg());
//				sticker.setRegid(userDomain.getEmail());
//				sticker.setTimesseq(Integer.parseInt(timesseq));
//				
//				myStickerCnt = toonService.myStickerCount(sticker);
//			}
//		}
//		
//		// 사용자 스티커 등록여부 및 갯수
//		modelAndView.addObject("myStickerCnt", myStickerCnt);
//		
//		// 스티커 갯수
//		modelAndView.addObject("stickerCnt", rankingService.stickerCount(rankingseq));
//		

		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
}