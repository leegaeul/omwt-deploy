package com.olleh.webtoon.pc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.advertisement.domain.AdvertisementDomain;
import com.olleh.webtoon.common.dao.advertisement.service.iface.AdvertisementService;
import com.olleh.webtoon.common.dao.banner.domain.BannerDomain;
import com.olleh.webtoon.common.dao.banner.service.iface.BannerService;
import com.olleh.webtoon.common.dao.novel.service.iface.NovelService;
import com.olleh.webtoon.common.dao.premium.domain.CategoryDomain;
import com.olleh.webtoon.common.dao.premium.domain.PremiumDomain;
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.dao.toon.domain.NovelFileDomain;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
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
public class NovelController {
	
	protected static Log logger = LogFactory.getLog(NovelController.class);
	
	@Autowired
	NovelService novelService;
	
	@Autowired
	ToonService toonService;
	
	@Autowired
	PremiumService premiumService;

	@Autowired
	BannerService bannerService;
	
	@Autowired
	AdvertisementService advertisementService;
	
	@Autowired
	UserServiceIface userServiceIface;
	
	/**
	 * 웹소설 메인 화면 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/novel/novelMain.kt")
	public ModelAndView moveToNovelMain(@ModelAttribute("premiumDomain") PremiumDomain premiumDomain) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();		
		
		//메인화면, PC버전
		premiumDomain.setMainyn("Y");
		premiumDomain.setMobileyn("N");
				
		// 상단 배너 리스트
		List<BannerDomain> bannerList = bannerService.bannerList("novel");
		modelAndView.addObject("bannerList", bannerList);
		
		//카테고리 목록
		List<CategoryDomain> categoryList = premiumService.premiumCategoryList("novel");
		modelAndView.addObject("categoryList", categoryList);
				
		// 웹소설 리스트
		if(categoryList != null)
		{
			//카테고리별 리스트 목록
			ArrayList<ArrayList<PremiumDomain>> novelList = new ArrayList<ArrayList<PremiumDomain>>();

			//카테고리 분리일 경우
//			for(int i = 0; i < categoryList.size(); i++)
//			{
//				premiumDomain.setCategoryseq(categoryList.get(i).getCategoryseq());
//				novelList.add(novelService.novelList(premiumDomain));
//			}

			//카테고리 1개일경우
			novelList.add(novelService.totalNovelList(premiumDomain));
			
			modelAndView.addObject("novelList", novelList);
		}
		
		modelAndView.setViewName("pc/novel/novelMain");

		return modelAndView;
	}
	
	/**
	 * 웹소설 리스트 화면 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/novel/novelList.kt")
	public ModelAndView moveToNovelList(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {

		ModelAndView modelAndView = new ModelAndView();		

		modelAndView.setViewName("pc/novel/novelList");
		
		//모바일 기기로 접속시 모바일 로그인 페이지로
		if(RequestUtil.isMobile(request)){
			String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/novel/novelList.kt" + urlcd);
			return modelAndView;
		}
		
		// 작품 상세설명 정보 가져오기 (작품 구매여부 구분자 - purchaseyn 추가)
		ToonDomain toonDetail = toonService.toonDetail(param);
		
		//웹툰
		if("toon".equals(toonDetail.getToonfg())){
			modelAndView.addObject("webtoonseq", toonDetail.getWebtoonseq());
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/toon/toonList.kt");
			
			return modelAndView;
		}
		
		modelAndView.addObject("toonDetail", toonDetail);
		
		//작품 연재 상태
		param.put("serialfg", toonDetail.getSerialfg());
		
		// 관련 상품 갯수
//		int refPrdCount = toonService.toonRefPrdCount(param);
//		modelAndView.addObject("refPrdCount", refPrdCount);
		
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
		int totalCnt = toonService.timesListCount(param);
		modelAndView.addObject("totalCnt", totalCnt);
		
		// 작품 회차별 목록	
		List<ToonDomain> timesList = toonService.ntimesList(param);
		modelAndView.addObject("timesList", timesList);
		
		return modelAndView;
	}
	
	/**
	 * 웹소설 상세 화면 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/novel/novelDetail.kt")
	public ModelAndView moveToNovelDetail(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("pc/novel/novelDetail");
		
		//모바일 기기로 접속시 모바일 로그인 페이지로
		if(RequestUtil.isMobile(request)){
			String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/novel/novelDetail.kt" + urlcd);
			return modelAndView;
		}
		
		// 작품의 상세정보 가져오기
		ToonDomain toon = toonService.timesDetail(param);
		
		//웹툰
		if("toon".equals(toon.getToonfg())){
			modelAndView.addObject("timesseq", toon.getTimesseq());
			modelAndView.addObject("webtoonseq", toon.getWebtoonseq());
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/toon/toonDetail.kt");
			
			return modelAndView;
		}
		
		//로그인 여부
		boolean isLogin = AuthUtil.isLogin(request);
		
		// 유저정보
		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
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
		
		modelAndView.addObject("toon", toon);
		
		// 성인물 체크
		if (("" + toon.getAgefg()).equals("adult")) {
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
				modelAndView.addObject("toonfg", "novel");
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
		param.put("webtoonseq", toon.getWebtoonseq());
		List<ToonDomain> timesnoList = toonService.tiemsnoList(param);
		modelAndView.addObject("tiemsnoList", timesnoList);
		
		// 관련 상품 갯수
		param.put("webtoonseq", toon.getWebtoonseq());
		int refPrdCount = toonService.toonRefPrdCount(param);
		modelAndView.addObject("refPrdCount", refPrdCount);
		
		// 웹소설 파일 정보 가져오기
		List<NovelFileDomain> novelFileList = toonService.novelFileList(param);
		modelAndView.addObject("novelFileList", novelFileList);
		
		//스티커 갯수
		String timesseq = (String)param.get("timesseq");
		modelAndView.addObject("stickerCnt", toonService.stickerCount(Integer.parseInt(timesseq)));
		
		//네임콘 목록
		List<NameconDomain> defualtNameconList = userServiceIface.defualtNameconList(userDomain);		
		modelAndView.addObject("defualtNameconList", defualtNameconList);
		
		List<NameconDomain> freeNameconList = userServiceIface.freeNameconList(userDomain);
		modelAndView.addObject("freeNameconList", freeNameconList);
		
		List<NameconDomain> payNameconList = userServiceIface.payNameconList(userDomain);
		modelAndView.addObject("payNameconList", payNameconList);
		
		List<NameconDomain> bmNameconList = userServiceIface.bmNameconList( userDomain );
		modelAndView.addObject( "bmNameconList", bmNameconList );

		//광고 종류 선별
		int adCnt = 0;
		AdvertisementDomain advertisementDomain = new AdvertisementDomain();
		advertisementDomain.setAreafg("sponsorT");
		advertisementDomain.setDevicefg("pc");
		advertisementDomain.setWebtoonseq(String.valueOf(toon.getWebtoonseq()));
		advertisementDomain.setTimesseq(timesseq);

		adCnt = advertisementService.advertisementListCount(advertisementDomain);
		
		//작품_회차 광고 여부
		if(adCnt > 0){
			modelAndView.addObject("adAreafg", "sponsorT");
		}else{
			advertisementDomain.setAreafg("sponsorW");
			adCnt = advertisementService.advertisementListCount(advertisementDomain);
			
			//작품 광고 여부
			if(adCnt > 0){
				modelAndView.addObject("adAreafg", "sponsorW");
			}else{
				advertisementDomain.setAreafg("sponsor");
				adCnt = advertisementService.advertisementListCount(advertisementDomain);
				
				//작품 전체 광고 여부
				if(adCnt > 0)
					modelAndView.addObject("adAreafg", "sponsor");
			}
		}
		
		//광고 목록
		advertisementDomain.setAreafg("textlinkA");
		List<AdvertisementDomain> adListA = advertisementService.adList(advertisementDomain);
		modelAndView.addObject("adListGetA", adListA);
		
		advertisementDomain.setAreafg("textlinkB");
		List<AdvertisementDomain> adListB = advertisementService.adList(advertisementDomain);
		modelAndView.addObject("adListGetB", adListB);
		
		advertisementDomain.setAreafg("textlinkC");
		List<AdvertisementDomain> adListC = advertisementService.adList(advertisementDomain);
		modelAndView.addObject("adListGetC", adListC);

		//스티커
		int myStickerCnt = 0;
		if(isLogin) {
			
			if(userDomain != null && !"".equals(userDomain.getEmail())){
				StickerDomain sticker = new StickerDomain();
				
				sticker.setIdfg(userDomain.getIdfg());
				sticker.setRegid(userDomain.getEmail());
				sticker.setTimesseq(Integer.parseInt(timesseq));

				myStickerCnt = toonService.myStickerCount(sticker);
			}
			
			//최근 본 툰 셋팅
			String email = userDomain.getEmail().substring(0, 2) + userDomain.getEmail().substring(userDomain.getEmail().length()-2, userDomain.getEmail().length());
			String cookieName = "timesseq"+email+"list";      //쿠키 회차번호 변수명
			String regdtCookieName = "regdt"+email+"list";      //쿠키 등록일 변수명
			String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
			String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
			int intCookieAge = 60*60*24*365;
			
			//쿠키를 생성한다.
			String tempTimesseq = StringUtil.defaultStr(CookieUtil.getCookie(request, cookieName), "");
			String tempRegdt = StringUtil.defaultStr(CookieUtil.getCookie(request, regdtCookieName), "");
			
			List<String> timesseqArrayList = new ArrayList<String>();
			List<String> regdtArrayList = new ArrayList<String>();
			
			if(!"".equals(tempTimesseq)){
				timesseqArrayList = new ArrayList<String>(Arrays.asList(tempTimesseq.split(",")));
				regdtArrayList = new ArrayList<String>(Arrays.asList(tempRegdt.split(",")));
			}
			
			//기존 최근 본 툰에 있는 확인
			for(int i=0 ; i < timesseqArrayList.size() ; i++){
				int index = timesseqArrayList.indexOf(timesseq);
				if(index > -1){
					timesseqArrayList.remove(index);
					regdtArrayList.remove(index);
				}
			}
			
			//최대 20개까지만 저장 가능
			int lastIndexNum = 20;
			if(timesseqArrayList.size() == lastIndexNum){
				timesseqArrayList.remove(0);
				regdtArrayList.remove(0);
			}
			
			//회차번호 및 현재 시간 저장
			timesseqArrayList.add(timesseq);
			regdtArrayList.add(DateUtil.getNowDate(1));
			
			//쿠키에 저장할 List 객체를 String으로 변환
			String saveTimesseqList = "";
			String saveRegdtList = "";
			for(int i=0 ; i < timesseqArrayList.size() ; i++){
				if(i != (timesseqArrayList.size()-1)){
					saveTimesseqList += timesseqArrayList.get(i) + ",";
					saveRegdtList += regdtArrayList.get(i) + ",";
				}else{
					saveTimesseqList += timesseqArrayList.get(i);
					saveRegdtList += regdtArrayList.get(i);
				}
			}
			
			CookieUtil.setCookie(response, cookieName, saveTimesseqList, intCookieAge, cookiePath, cookieDomain);
			CookieUtil.setCookie(response, regdtCookieName, saveRegdtList, intCookieAge, cookiePath, cookieDomain);
		}
		
		modelAndView.addObject("myStickerCnt", myStickerCnt);

		return modelAndView;
	}

}