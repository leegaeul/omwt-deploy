/*****************************************************************************
 * PROJECT NAME   : 올레마켓 공모전
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ContestController.java
 * DESCRIPTION    : 공모전 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-12-18      init
 *****************************************************************************/

package com.olleh.webtoon.pc.controller;

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
import com.olleh.webtoon.common.dao.user.domain.NameconDomain;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.PremiumUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
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
	@RequestMapping(value = "/contest/contestMain.kt")
	public ModelAndView moveToContestMain(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("pc/contest/contestMain");
		
		//상단 공모전 3개(운영자가 등록한 추천 공모전 3개 : Cache 처리)
		List<ContestDomain> topContestList = contestService.topContestList();
		modelAndView.addObject("topContestList", topContestList);
		
		//장르 정보 가져오기(Cache 처리)
		List<GenreDomain> genreList = toonService.genreList();
		modelAndView.addObject("genreList", genreList);
		
		//전체 장르별 공모전 목록(스티커순, 제목순, 작가순 : Cache 처리) -> 업데이트순 제외
		List<ContestDomain> contestList = contestService.contestList(param);
		modelAndView.addObject("contestList", contestList);
		
		for(GenreDomain genre : genreList){
			List<ContestDomain> tmpContestList = new ArrayList<ContestDomain>();
			for(ContestDomain contest : contestList){
				
				//PC전시 여부
				if("Y".equals(contest.getPcdisplayyn())){					
					if(genre.getGenreseq() == contest.getGenreseq1() || 
							genre.getGenreseq() == contest.getGenreseq2() || 
							genre.getGenreseq() == contest.getGenreseq3()){
						tmpContestList.add(contest);
					}
				}
			}
			
			modelAndView.addObject("genre"+genre.getGenreseq()+"ContestList", tmpContestList);	
		}

		return modelAndView;
	}
	
	/**
	 * 공모전 작화 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/contest/timesList.kt")
	public ModelAndView timesList(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		ContestDomain contestDetail = contestService.contestDetail(param);
		
		//공모전이 미전시일 경우 메인으로 이동
		if("N".equals(contestDetail.getPcdisplayyn())){
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/main.kt");
			return modelAndView;
		}
		
		modelAndView.addObject("contestDetail", contestDetail);
				
		//페이징 처리
		String startRowNoParam = (String)param.get("startRowNo");		
		int startRowNo = 1;
		startRowNo =  StringUtil.defaultInt(startRowNoParam,0);	
		
		String pageSizeParam = (String)param.get("pageSize");		
		int pageSize = 10;
		pageSize =  StringUtil.defaultInt(pageSizeParam,10);
		
		param.put("startRowNo", startRowNo);
		param.put("pageSize", pageSize);
		
		// 작품 회차별 목록 갯수
		int totalCnt = contestService.timesListCount(param);
		modelAndView.addObject("totalCnt", totalCnt);
		
		// 작품 회차별 목록	
		List<ContestDomain> timesList = contestService.timesList(param);
		modelAndView.addObject("timesList", timesList);
		
		modelAndView.setViewName("pc/contest/timesList");
		
		return modelAndView;
	}
	
	/**
	 * 공모전 작화 보기 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/contest/timesDetail.kt")
	public ModelAndView timesDetail(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pc/contest/timesDetail");
		
		//모바일 기기로 접속시 모바일 로그인 페이지로
//		if(RequestUtil.isMobile(request)){
//			String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
//			
//			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
//			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/contest/timesDetail.kt" + urlcd);
//			return modelAndView;
//		}
		
		// 작품의 상세정보 가져오기
		ContestDomain contest = contestService.timesDetail(param);
		
		//공모전이 미전시일 경우 메인으로 이동
		if(!"Y".equals(contest.getPcdisplayyn())){
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/main.kt");
			return modelAndView;
		}
		
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
				
		userDomain.setEmail(userDomain.getEmail());
		userDomain.setIdfg(userDomain.getIdfg());
		
		if(userDomain.getPageSize() == 0) {
			userDomain.setPageSize(999999);
		}
		
		// 인증상태
		int myCert = PremiumUtil.myCert(request);
		
		// 인증후 이동할 URI
		String beforeURI = RequestUtil.getFullURI(request);
		beforeURI = (beforeURI != null && 0 < beforeURI.length()) ? "?urlcd=" + beforeURI : "";
		
		modelAndView.addObject("contest", contest);
		
		// 성인물 체크
		if (("" + contest.getAgefg()).equals("adult")) {
			// 로그인 체크
			if (!isLogin) {
				modelAndView.addObject("webtoonseq", param.get("webtoonseq"));
				modelAndView.setViewName("redirect:/login.kt" + beforeURI);
				return modelAndView;
			}

			//약관 미동의
			if (myCert == PremiumUtil.DISAGREE_TERMS_USER) {
				modelAndView.setViewName("redirect:/user/ollehUserAgreement.kt" + beforeURI);
				return modelAndView;
			}
			// 미인증
			if (myCert == PremiumUtil.NOT_CERT_USER) {
				modelAndView.setViewName("redirect:/premium/premiumAdultCert.kt" + beforeURI);
				return modelAndView;
			}
			// 미성년자
			else if (myCert == PremiumUtil.MINOR_NOT_CERT_USER || myCert == PremiumUtil.MINOR_CERT_USER) {
				modelAndView.setViewName("pc/toon/minorDenied");
				return modelAndView;
			}
			// 생년월일없는 올레준회원
			else if (myCert == PremiumUtil.ASSOCIATE_USER) {
				modelAndView.setViewName("pc/toon/amDenied");
				return modelAndView;
			}
		}
		
		// 작화 회차 목록
		param.put("webtoonseq", contest.getWebtoonseq());
		List<ContestDomain> timesnoList = contestService.tiemsnoList(param);
		modelAndView.addObject("tiemsnoList", timesnoList);
		
		// 작화 이미지 정보 가져오기
		List<ContestImageDomain> contestImageList = contestService.contestImageList(param);
		modelAndView.addObject("contestImageList", contestImageList);
		
		//스티커 갯수
		String timesseq = (String)param.get("timesseq");
		modelAndView.addObject("stickerCnt", contestService.stickerCount(Integer.parseInt(timesseq)));
		
		//네임콘 목록
		List<NameconDomain> defualtNameconList = userServiceIface.defualtNameconList(userDomain);		
		modelAndView.addObject("defualtNameconList", defualtNameconList);
		
		List<NameconDomain> freeNameconList = userServiceIface.freeNameconList(userDomain);
		modelAndView.addObject("freeNameconList", freeNameconList);
		
		List<NameconDomain> payNameconList = userServiceIface.payNameconList(userDomain);
		modelAndView.addObject("payNameconList", payNameconList);
		
		List<NameconDomain> bmNameconList = userServiceIface.bmNameconList( userDomain );
		modelAndView.addObject( "bmNameconList", bmNameconList );

//		//광고 종류 선별
//		int adCnt = 0;
//		AdvertisementDomain advertisementDomain = new AdvertisementDomain();
//		advertisementDomain.setAreafg("sponsorT");
//		advertisementDomain.setDevicefg("pc");
//		advertisementDomain.setWebtoonseq(String.valueOf(contest.getWebtoonseq()));
//		advertisementDomain.setTimesseq(timesseq);
//
//		adCnt = advertisementService.advertisementListCount(advertisementDomain);
//		
//		//작품_회차 광고 여부
//		if(adCnt > 0){
//			modelAndView.addObject("adAreafg", "sponsorT");
//		}else{
//			advertisementDomain.setAreafg("sponsorW");
//			adCnt = advertisementService.advertisementListCount(advertisementDomain);
//			
//			//작품 광고 여부
//			if(adCnt > 0){
//				modelAndView.addObject("adAreafg", "sponsorW");
//			}else{
//				advertisementDomain.setAreafg("sponsor");
//				adCnt = advertisementService.advertisementListCount(advertisementDomain);
//				
//				//작품 전체 광고 여부
//				if(adCnt > 0)
//					modelAndView.addObject("adAreafg", "sponsor");
//				
//			}
//		}
//		
//		//광고 목록
//		advertisementDomain.setAreafg("textlinkA");
//		List<AdvertisementDomain> adListA = advertisementService.adList(advertisementDomain);
//		modelAndView.addObject("adListGetA", adListA);
//		
//		advertisementDomain.setAreafg("textlinkB");
//		List<AdvertisementDomain> adListB = advertisementService.adList(advertisementDomain);
//		modelAndView.addObject("adListGetB", adListB);
//		
//		advertisementDomain.setAreafg("textlinkC");
//		List<AdvertisementDomain> adListC = advertisementService.adList(advertisementDomain);
//		modelAndView.addObject("adListGetC", adListC);

		
		//로그인 여부 체크
		int myStickerCnt = 0;
		if(isLogin) {
			
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
	
	/**
	 * 공모전 회차에 스티커 입력 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/contest/stickerRegistJsonProc.kt")
	public ModelAndView stickerRegistJsonProc(StickerDomain stickerDomain, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//로그인 여부 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.addObject("loginyn", "N");
			return modelAndView;
		}

		//회원정보 복호화
		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		modelAndView.addObject("loginyn", "Y");
		
		//아이디 구분
		stickerDomain.setIdfg(userDomain.getIdfg());
		
		//이메일 아이디
		stickerDomain.setRegid(userDomain.getEmail());
		
		//현재날짜시간
		stickerDomain.setRegdt(DateUtil.getNowDate(1));
		
		//스티커 추가
		if(contestService.stickerCheck(stickerDomain) == 0)
			contestService.stickerRegist(stickerDomain);
		else
			modelAndView.addObject("duplicateSticker", "Y");
		
		//스티커 갯수
		modelAndView.addObject("stickerCnt", contestService.stickerCount(stickerDomain.getTimesseq()));
		
		modelAndView.addObject("stickerDomain", null);
		
		return modelAndView;		
	}
}