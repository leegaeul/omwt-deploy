/*****************************************************************************
 * PROJECT NAME   : 올레마켓 공모전
 * SUBSYSTEM NAME : 사용자(모바일)
 * FILE NAME      : ContestController.java
 * DESCRIPTION    : 공모전 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-12-18      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

import java.util.ArrayList;
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

import com.olleh.webtoon.common.dao.contest.domain.ContestDomain;
import com.olleh.webtoon.common.dao.contest.domain.ContestImageDomain;
import com.olleh.webtoon.common.dao.contest.service.iface.ContestService;
import com.olleh.webtoon.common.dao.eventpromotion.service.iface.EventPromotionService;
import com.olleh.webtoon.common.dao.toon.domain.GenreDomain;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;
import com.olleh.webtoon.common.dao.toon.service.iface.ToonService;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.PremiumUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller("MobileContestController")
public class ContestController {
	
	@Autowired
	ContestService contestService;
	
	@Autowired
	ToonService toonService;
	
	@Autowired
	UserServiceIface userServiceIface;
	
	@Autowired
	EventPromotionService eventPromotionService;
	
//	@Autowired
//	AdvertisementService advertisementService;
	
	protected static Log logger = LogFactory.getLog(ContestController.class);
	
	/**
	 * 공모전 메인 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/contest/contestMain.kt")
	public ModelAndView moveToContestMain(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/contest/contestMain");
		
		//장르 정보 가져오기(Cache 처리)
		List<GenreDomain> genreList = toonService.genreList();
		modelAndView.addObject("genreList", genreList);
		
		return modelAndView;
	}
	
	/**
	 * 장르별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/contest/contestJsonList.kt")
	public ModelAndView contestJsonList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");	
		
		String pageNoParam = (String)param.get("pageNo");		
		int pageNo = 1;
		pageNo =  StringUtil.defaultInt(pageNoParam,1);
		
		String pageSizeParam = (String)param.get("pageSize");		
		int pageSize = 10;
		pageSize =  StringUtil.defaultInt(pageSizeParam,10);
		
		String genreNoParam = (String)param.get("genreNo");		
		int genreNo = 1;
		genreNo =  StringUtil.defaultInt(genreNoParam,0);
		
		//전체 장르별 공모전 목록(스티커순, 제목순, 작가순 : Cache 처리) -> 업데이트순 제외
		List<ContestDomain> tmpContestList = contestService.contestList(param);
		
		List<ContestDomain> contestList = new ArrayList<ContestDomain>(); 
		
		if(genreNo != 0){
			for(ContestDomain contest : tmpContestList){
				
				//모바일 전시 여부
				if("Y".equals(contest.getMdisplayyn())){
					if(genreNo == contest.getGenreseq1() || 
							genreNo == contest.getGenreseq2() || 
									genreNo == contest.getGenreseq3()){
						//장르별 공모전 목록
						contestList.add(contest);
					}
				}
			}
		}
		//전체 공모전 목록
		else{
			contestList = tmpContestList;
		}
		
		int toIndex = 0;
		toIndex = pageNo*pageSize;			
		if(toIndex > contestList.size()) toIndex = contestList.size();
		
		int fromIndex = ((pageNo-1)*pageSize);
		
		modelAndView.addObject("contestList", contestList.subList(fromIndex, toIndex));	
		modelAndView.addObject("totalCount", contestList.size());
		
		return modelAndView;
		
	}
	
	/**
	 * 작화 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/contest/timesList.kt")
	public ModelAndView timesList(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//공모전 기본 상세설명 정보 가져오기
		ContestDomain contestDetail = contestService.simpleContestDetail(param);
		
		//공모전이 미전시일 경우 메인으로 이동
		if("N".equals(contestDetail.getMdisplayyn())){
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/main.kt");
			return modelAndView;
		}
		
		modelAndView.addObject("contestDetail", contestDetail);
		
		modelAndView.setViewName("mobile/contest/timesList");
		
		return modelAndView;		
	}
	
	/**
	 * 작화 목록을 요청하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/contest/timesJsonList.kt")
	public ModelAndView timesJsonList(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		String mode = (String)param.get("mode");		
		mode =  StringUtil.defaultStr(mode,"");	
		
		//페이징 처리
		String pageNoParam = (String)param.get("pageNo");		
		int pageNo = 1;
		pageNo =  StringUtil.defaultInt(pageNoParam,1);	
		
		String pageSizeParam = (String)param.get("pageSize");		
		int pageSize = 10;
		pageSize =  StringUtil.defaultInt(pageSizeParam,10);
		
		param.put("startRowNo", (pageNo-1)*pageSize);
		param.put("pageSize", pageSize);
		
		//더보기 클릭시 공모전 상세정보 및 회차목록 갯수는 전달하지 않음.
		if(!mode.equals("list")){
			// 작품 상세설명 정보 가져오기
			ContestDomain contestDetail = contestService.contestDetail(param);
			modelAndView.addObject("toonDetail", contestDetail);
		
			// 작품 회차별 목록 갯수
			int timesListCount = contestService.timesListCount(param);
			modelAndView.addObject("totalCount", timesListCount);
		}
		
		// 작품 회차별 목록	
		List<ContestDomain> timesList = contestService.timesList(param);
		modelAndView.addObject("timesList", timesList);
				
		return modelAndView;		
	}
	
	/**
	 * 공모전 작화 보기 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/contest/timesDetail.kt")
	public ModelAndView timesDetail(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//모바일 기기가 아닐경우 
//		if("real".equals(System.getProperty("serverType")) && !RequestUtil.isMobile(request)){
//			String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
//			
//			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
//			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/contest/timesDetail.kt" + urlcd);
//			return modelAndView;
//		}
		
		//공모전 기본 상세설명 정보 가져오기
		ContestDomain timesDetail = contestService.simpleTimesDetail(param);
				
		//공모전이 미전시일 경우 메인으로 이동
		if(!"Y".equals(timesDetail.getMdisplayyn())){
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/main.kt");
			return modelAndView;
		}
		
		modelAndView.addObject("timesDetail", timesDetail);
		modelAndView.setViewName("mobile/contest/timesDetail");
		
		//로그인 여부
		boolean isLogin = AuthUtil.isLogin(request);
		
		// 유저정보
		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		//공모전 이벤트 참여여부
		if(isLogin){
			int hisCnt = eventPromotionService.getContestEventHisCnt(userDomain.getEmail(), userDomain.getIdfg());
			if(hisCnt > 0){
				modelAndView.addObject("duplicateyn", "Y");
			}
		}
				
		// 로그인일 경우 쿠키에 저장되어있는 네임콘 정보와 DB에서 회원이 보유한 네임콘 정보가 다를경우 쿠키값에 다시 셋팅
		if(isLogin){
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
		
		// 인증상태
		int myCert = PremiumUtil.myCert(request);
		
		// 인증후 이동할 URI
		String beforeURI = RequestUtil.getFullURI(request);
		beforeURI = (beforeURI != null && 0 < beforeURI.length()) ? "?urlcd=" + beforeURI : "";
		
		// 성인물 체크
		if (("" + timesDetail.getAgefg()).equals("adult")) {
			// 로그인 체크
			if (!isLogin) {
				modelAndView.addObject("webtoonseq", param.get("webtoonseq"));
				modelAndView.setViewName("redirect:/m/login.kt" + beforeURI);
				return modelAndView;
			}

			//약관 미동의
			if (myCert == PremiumUtil.DISAGREE_TERMS_USER) {
				modelAndView.setViewName("redirect:/m/user/ollehUserAgreement.kt" + beforeURI);
				return modelAndView;
			}
			// 미인증
			else if (myCert == PremiumUtil.NOT_CERT_USER) {
				modelAndView.setViewName("redirect:/m/premium/premiumAdultCert.kt" + beforeURI);
				return modelAndView;
			}
			// 미성년자
			else if (myCert == PremiumUtil.MINOR_NOT_CERT_USER || myCert == PremiumUtil.MINOR_CERT_USER) {
				modelAndView.setViewName("mobile/toon/minorDenied");
				return modelAndView;
			}
			// 생년월일없는 올레준회원
			else if (myCert == PremiumUtil.ASSOCIATE_USER) {
				modelAndView.setViewName("mobile/toon/amDenied");
				return modelAndView;
			}
		}

		//광고 종류 선별
//		int adCnt = 0;
//		AdvertisementDomain advertisementDomain = new AdvertisementDomain();
//		advertisementDomain.setAreafg("msponsorT");
//		advertisementDomain.setDevicefg("mobile");
//		advertisementDomain.setWebcontestseq(String.valueOf(contest.getWebcontestseq()));
//		advertisementDomain.setTimesseq(String.valueOf(contest.getTimesseq()));
//		
//		adCnt = advertisementService.advertisementListCount(advertisementDomain);
//		
//		//작품_회차 광고 여부
//		if(adCnt > 0){
//			modelAndView.addObject("adAreafg", "msponsorT");
//		}else{
//			advertisementDomain.setAreafg("msponsorW");
//			adCnt = advertisementService.advertisementListCount(advertisementDomain);
//			
//			//작품 광고 여부
//			if(adCnt > 0){
//				modelAndView.addObject("adAreafg", "msponsorW");
//			}else{
//				advertisementDomain.setAreafg("msponsor");
//				adCnt = advertisementService.advertisementListCount(advertisementDomain);
//				
//				//작품 전체 광고 여부
//				if(adCnt > 0)
//					modelAndView.addObject("adAreafg", "msponsor");
//				
//			}
//		}
//		 
//		advertisementDomain.setAreafg("mtextlinkA");
//		List<AdvertisementDomain> adListA = advertisementService.adList(advertisementDomain);
//		modelAndView.addObject("adListGetA", adListA);
//		
//		advertisementDomain.setAreafg("mtextlinkB");
//		List<AdvertisementDomain> adListB = advertisementService.adList(advertisementDomain);
//		modelAndView.addObject("adListGetB", adListB);
//		
//		advertisementDomain.setAreafg("mtextlinkC");
//		List<AdvertisementDomain> adListC = advertisementService.adList(advertisementDomain);
//		modelAndView.addObject("adListGetC", adListC);
		
		return modelAndView;
		
	}
	
	/**
	 * 공모전 작화 보기 화면을 요청하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/contest/timesJsonDetail.kt")
	public ModelAndView timesJsonDetail(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");	
		
		// 작화 이미지 정보 가져오기
		List<ContestImageDomain> contestImageList = contestService.contestImageList(param);
		modelAndView.addObject("toonImageList", contestImageList);
		
		//스티커 갯수
		String timesseq = (String)param.get("timesseq");
		modelAndView.addObject("stickerCnt", contestService.stickerCount(Integer.parseInt(timesseq)));		

		//로그인 여부 체크
		int myStickerCnt = 0;
		if(!AuthUtil.isLogin(request)){
			
			// 작품의 상세정보 가져오기
			ContestDomain contest = contestService.timesDetail(param);
			modelAndView.addObject("toon", contest);
		}
		else{
			
			//회원정보 복호화
			UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
			
			// 작품의 상세정보 가져오기
			ContestDomain contest = contestService.timesDetail(param);
			modelAndView.addObject("toon", contest);
			
			if(userDomain != null && !"".equals(userDomain.getEmail())){
				StickerDomain sticker = new StickerDomain();
				
				sticker.setIdfg(userDomain.getIdfg());
				sticker.setRegid(userDomain.getEmail());
				sticker.setTimesseq(Integer.parseInt(timesseq));

				myStickerCnt = contestService.myStickerCount(sticker);
			}
		}
		
		modelAndView.addObject("myStickerCnt", myStickerCnt);
		
		return modelAndView;
	}
}