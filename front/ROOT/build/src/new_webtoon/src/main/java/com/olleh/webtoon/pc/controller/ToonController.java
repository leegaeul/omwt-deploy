/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ToonController.java
 * DESCRIPTION    : 웹툰 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-18      init
 *****************************************************************************/

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.advertisement.domain.AdvertisementDomain;
import com.olleh.webtoon.common.dao.advertisement.service.iface.AdvertisementService;
import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductHistoryDomain;
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.dao.toon.domain.BookmarkDomain;
import com.olleh.webtoon.common.dao.toon.domain.FavoritesDomain;
import com.olleh.webtoon.common.dao.toon.domain.GenreDomain;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonImageDomain;
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
public class ToonController {
	
	@Autowired
	ToonService toonService;
	
	@Autowired
	PremiumService premiumService;
	
	@Autowired
	OrderbuyService orderbuyService;
	
	@Autowired
	UserServiceIface userServiceIface;
	
	@Autowired
	AdvertisementService advertisementService;
			
	protected static Log logger = LogFactory.getLog(ToonController.class);
		
	/**
	 * 요일별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/toon/weekList.kt")
	public ModelAndView weekList(@RequestParam Map<String,Object> param) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();		
		
		modelAndView.setViewName("pc/toon/weekList");
		
		//상단 웹툰 3개(해당 요일의 추천 상품 3개 : Cache 처리)
		List<ToonDomain> topToonList = toonService.topToonList("day"); 
		
		//전체 요일별 웹툰 목록(업데이트순, 스티커순, 제목순, 작가순 : Cache 처리)		
		//각 요일별 List 추출
		List<ToonDomain> mondayToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> tuesedayToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> wednesdayToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> thursdayToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> fridayToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> saturdayToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> sundayToonList = new ArrayList<ToonDomain>();
		
		param.put("toonfg", "all");
		List<ToonDomain> toonList = toonService.toonList(param);
		for(ToonDomain toon : toonList){
			
			//PC전시 여부 및 toon 영역 전시 여부
			if("Y".equals(toon.getPcdisplayyn()) && ("Y".equals(toon.getToonyn()))){
				if("Y".equals(toon.getMondayyn())) mondayToonList.add(toon);
				if("Y".equals(toon.getTuesdayyn())) tuesedayToonList.add(toon);
				if("Y".equals(toon.getWednesdayyn())) wednesdayToonList.add(toon);
				if("Y".equals(toon.getThursdayyn())) thursdayToonList.add(toon);
				if("Y".equals(toon.getFridayyn())) fridayToonList.add(toon);
				if("Y".equals(toon.getSaturdayyn())) saturdayToonList.add(toon);
				if("Y".equals(toon.getSundayyn())) sundayToonList.add(toon);
			}
		}
		
		modelAndView.addObject("topToonList", topToonList);
		modelAndView.addObject("mondayToonList", mondayToonList);
		modelAndView.addObject("tuesedayToonList", tuesedayToonList);
		modelAndView.addObject("wednesdayToonList", wednesdayToonList);
		modelAndView.addObject("thursdayToonList", thursdayToonList);
		modelAndView.addObject("fridayToonList", fridayToonList);
		modelAndView.addObject("saturdayToonList", saturdayToonList);
		modelAndView.addObject("sundayToonList", sundayToonList);
		
		return modelAndView;
	}
	
	/**
	 * 작화 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/toon/timesList.kt")
	public ModelAndView timesList(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//모바일 기기로 접속시 모바일 로그인 페이지로
		if(RequestUtil.isMobile(request)){
			String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/toon/timesList.kt" + urlcd);
			return modelAndView;
		}
		
		//웹소설 여부 체크
		ToonDomain toonInfo = toonService.simpleToonDetail(param);
		if("novel".equals(toonInfo.getToonfg())){
			modelAndView.addObject("webtoonseq", toonInfo.getWebtoonseq());
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/novel/novelList.kt");
			
			return modelAndView;
		}
		
		modelAndView.setViewName("pc/toon/timesList");
		
		UserDomain user = null;
		int availableBerry = 0;
		int availableBlueBerry = 0;
		String blueMembershipyn = "N";
		
		if(AuthUtil.isLogin(request)){
			user = AuthUtil.getDecryptUserInfo(request);
			
			// 블루멤버십 회원여부 체크
			blueMembershipyn = premiumService.getBlueMembershipInfo(user.getEmail(), user.getIdfg());
			
			// 보유베리
			availableBerry = orderbuyService.berryAvail(user.getEmail(), user.getIdfg());
			
			if("Y".equals(blueMembershipyn)){
				// 보유블루베리
				availableBlueBerry = orderbuyService.blueBerryAvail(user.getEmail(), user.getIdfg());
			}
		}
		
		if(user != null) param.put("buyid", user.getEmail());
		
		modelAndView.addObject("blueMembershipyn", blueMembershipyn);
		modelAndView.addObject("availableBerry", availableBerry);
		modelAndView.addObject("availableBlueBerry", availableBlueBerry);
		
		//작품 상세 정보
		ToonDomain toonDetail = toonService.toonDetail(param);
		modelAndView.addObject("toonDetail", toonDetail);
		
		//작품 연재 상태
		param.put("serialfg", toonDetail.getSerialfg());
		
		// 관련 상품 갯수
		int refPrdCount = toonService.refItemCount(param);
		modelAndView.addObject("refPrdCount", refPrdCount);
		
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
		List<ToonDomain> timesList = toonService.timesList(param);
		modelAndView.addObject("timesList", timesList);
		
		// 작품에 유료 회차 상품 존재 여부
		String existTimesprdyn = premiumService.getExistTimesprd(param.get("webtoonseq").toString());
		modelAndView.addObject("existTimesprdyn", existTimesprdyn);
		
		return modelAndView;
		
	}
	
	/**
	 * 작화 보기 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/toon/timesDetail.kt")
	public ModelAndView timesDetail(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pc/toon/timesDetail");
		
		//모바일 기기로 접속시 모바일 로그인 페이지로
		if(RequestUtil.isMobile(request)){
			String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/toon/timesDetail.kt" + urlcd);
			return modelAndView;
		}
		
		//웹소설 여부 체크
		ToonDomain toonInfo = toonService.simpleTimesDetail(param);
		if("novel".equals(toonInfo.getToonfg())){
			modelAndView.addObject("timesseq", toonInfo.getTimesseq());
			modelAndView.addObject("webtoonseq", toonInfo.getWebtoonseq());
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/novel/novelDetail.kt");
			
			return modelAndView;
		}
		
		//로그인 여부
		boolean isLogin = AuthUtil.isLogin(request);
		
		// 유저정보
		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		// 로그인일 경우 쿠키에 저장되어있는 네임콘 정보와 DB에서 회원이 보유한 네임콘 정보가 다를경우 쿠키값에 다시 셋팅
		if(isLogin){
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
		
		// 인증후 결제페이지 URI
//		String paymentURI = "?urlcd=/payment/payment.kt?timesseq=" + param.get("timesseq");
				
		// 로그인 시 해당 회차 구매여부 체크 (구매한 상품일 경우 해당 회차의 노출여부에 상관 없이 감상 가능)
		ProductAvailDomain productAvail = null;
		if (isLogin) 
		{
			productAvail = premiumService.productAvail(userDomain.getEmail(), param);
			if(productAvail != null) param.put("myuseyn", "Y");
		}

		// 회차 상세정보 가져오기
		ToonDomain toon = toonService.timesDetail(param);
		modelAndView.addObject("toon", toon);
		
		// 유료상품 체크 - 선택 회차(단일)
		param.put("prdfg", "times");
		ProductHistoryDomain timesProduct = premiumService.productDetail(param);
		
		// 유료상품 체크 - 작품
		param.put("prdfg", "webtoon");
		ProductHistoryDomain webtoonProduct = premiumService.productDetail(param);

		// 유료상품 체크 - 작품에 유료 회차 상품이 존재 하는지 체크
		String existTimesprdyn =  premiumService.getExistTimesprd(param.get("webtoonseq").toString());
	
		boolean curTimesPrd   = timesProduct != null && 0 < timesProduct.getPrdprice() && "Y".equals(timesProduct.getSellyn());       //선택 회차 상품이 유료인지 여부 
		boolean existToonPrd  = webtoonProduct != null && 0 < webtoonProduct.getPrdprice() && "Y".equals(webtoonProduct.getSellyn()); //작품 상품 존재 여부
		boolean existTimesprd = existTimesprdyn != null && "Y".equals(existTimesprdyn);												  //작품에 회차 상품 존재 여부
		
		// 올레아이디 로그인 시 셋팅된 올레 아이디 쿠키값 로드를 위해 페이지 리로드
		String u_check_no = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_check_no"), "");
		String kt_userid  = StringUtil.defaultStr(CookieUtil.getCookie(request, "kt_userid"), ""); 
		
		if ("".equals(u_check_no) && !"".equals(kt_userid) && (("" + toon.getAgefg()).equals("adult") || curTimesPrd || existToonPrd)) 
		{
			modelAndView.addObject("cookiesetreload", "Y");
			return modelAndView;
		}
		else
		{
			modelAndView.addObject("cookiesetreload", "N");
		}
		
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
				modelAndView.setViewName("pc/toon/minorDenied");
				return modelAndView;
			}
			// 생년월일없는 올레준회원
			else if (myCert == PremiumUtil.ASSOCIATE_USER) {
				modelAndView.setViewName("pc/toon/amDenied");
				return modelAndView;
			}
		}
		
		//유료 회차 상품이 존재하고 선택 회차가 유료일 경우, 작품 상품만 존재할 경우 -> 유료
		if((existTimesprd && curTimesPrd) || (existToonPrd && !existTimesprd)){
			// 로그인 체크
			if (!isLogin) {
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
				modelAndView.setViewName("redirect:/premium/premiumMyCert.kt" + beforeURI);
				return modelAndView;
			}
			// 미성년자(법적대리인 미인증)
			else if (myCert == PremiumUtil.MINOR_NOT_CERT_USER) {
				modelAndView.setViewName("redirect:/premium/premiumLawerCert.kt" + beforeURI);
				return modelAndView;
			}
			
			// 구매내역 이용기간이 남은 동일 회차가 있는가?
			if (productAvail == null) {
				
				//회원정보 복호화
				if(userDomain != null) param.put("buyid", userDomain.getEmail());
				
				// 작품 상세설명 정보 가져오기
				ToonDomain toonDetail = toonService.toonDetail(param);
				
				// 회차 총 갯수 가져오기
				int totalCnt = toonService.timesListCount(param);
				
				boolean uniqueTimes = timesProduct != null && webtoonProduct == null;
				boolean uniqueToon  = timesProduct == null && webtoonProduct != null;
				
				if(uniqueToon && webtoonProduct != null && "mix".equals(webtoonProduct.getPurchasefg())){
					String title = "“" + toonDetail.getWebtoonnm() + "” 전회차 구매(총" + (toonDetail.getTimesprdcnt() == 0 ? totalCnt : toonDetail.getTimesprdcnt()) + "화)";
					modelAndView.addObject("title", title);
					modelAndView.addObject("prdhistoryseq", webtoonProduct.getPrdhistoryseq());
					modelAndView.setViewName("redirect:/premium/mixPurchase.kt");
					return modelAndView;
				}
				
				if(uniqueTimes && timesProduct != null && "mix".equals(timesProduct.getPurchasefg())){
					String title = toon.getTimestitle() + " - " + toonDetail.getWebtoonnm();
					modelAndView.addObject("title", title);
					modelAndView.addObject("prdhistoryseq", timesProduct.getPrdhistoryseq());
					modelAndView.setViewName("redirect:/premium/mixPurchase.kt");
					return modelAndView;
				}
				
				// 회차 상세정보
				modelAndView.addObject("timesDetail", toon);
				
				// 작품 상세정보
				modelAndView.addObject("toonDetail", toonDetail);
				
				// 회차 상품정보
				modelAndView.addObject("timesProduct", timesProduct);
				
				// 작품 상품정보
				modelAndView.addObject("webtoonProduct", webtoonProduct);
				
				// 회차 총 갯수
				modelAndView.addObject("totalCnt", totalCnt);
				
				// 회차구매 알림창
				modelAndView.setViewName("pc/toon/purchaseTimes");
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
		
		// 작화 이미지 정보 가져오기
		List<ToonImageDomain> toonImageList = toonService.toonImageList(param);
		modelAndView.addObject("toonImageList", toonImageList);
		
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

		
		//로그인 여부 체크
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
	
	/**
	 * 장르별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/toon/genreList.kt")
	public ModelAndView genreList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("pc/toon/genreList");
		
		//상단 웹툰 3개(운영자가 등록한 추천 웹툰 3개 : Cache 처리)
		List<ToonDomain> topToonList = toonService.topToonList("genre");
		
		//장르 정보 가져오기(Cache 처리)
		List<GenreDomain> genreList = toonService.genreList();
		modelAndView.addObject("genreList", genreList);
		
		//전체 장르별 웹툰 목록(업데이트순, 스티커순, 제목순, 작가순 : Cache 처리)
		param.put("toonfg",   "toon");
		param.put("toonType", "toon");
		List<ToonDomain> toonList = toonService.toonList(param);
		
		for(GenreDomain genre : genreList){
			List<ToonDomain> tmpToonList = new ArrayList<ToonDomain>();
			for(ToonDomain toon : toonList){
				
				//PC전시 여부 및 toon 영역 전시 여부
				if("Y".equals(toon.getPcdisplayyn()) && "Y".equals(toon.getToonyn())){					
					if(genre.getGenreseq() == toon.getGenreseq1() || 
							genre.getGenreseq() == toon.getGenreseq2() || 
							genre.getGenreseq() == toon.getGenreseq3()){
						tmpToonList.add(toon);
					}
				}
			}
			modelAndView.addObject("genre"+genre.getGenreseq()+"ToonList", tmpToonList);	
		}

		//완결 웹툰 목록
		List<ToonDomain> lastToonList = new ArrayList<ToonDomain>();
		for(ToonDomain toon : toonList){
			if("end".equals(toon.getSerialfg())){
				lastToonList.add(toon);
			}
		}
		modelAndView.addObject("topToonList", topToonList);
		modelAndView.addObject("lastToonList", lastToonList);	
		
		return modelAndView;
	}
	
	/**
	 * 작품별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/toon/toonList.kt")
	public ModelAndView toonList(@RequestParam Map<String,Object> param) throws Exception {
		
		//초성 검색 조건이 없을때 default값 지정
		if(param.get("initial") == null) param.put("initial", "h1");
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("pc/toon/toonList");
		
		//상단 웹툰 3개(운영자가 등록한 추천 웹툰 3개 : Cache 처리)
		List<ToonDomain> topToonList = toonService.topToonList("webtoon");
		
		//전체 초성 웹툰 목록(업데이트순, 스티커순, 제목순, 작가순 : Cache 처리)		
		List<ToonDomain> korean1ToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> korean2ToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> korean3ToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> korean4ToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> korean5ToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> english1ToonList = new ArrayList<ToonDomain>();

		param.put("toonfg",   "toon");
		param.put("toonType", "toon");
		List<ToonDomain> toonList = toonService.toonList(param);
		for(ToonDomain toon : toonList){
			
			//PC전시 여부 및 toon 영역 전시 여부
			if("Y".equals(toon.getPcdisplayyn()) && "Y".equals(toon.getToonyn())){	
				if("h1".equals(toon.getInitialword())) korean1ToonList.add(toon);
				if("h2".equals(toon.getInitialword())) korean2ToonList.add(toon);
				if("h3".equals(toon.getInitialword())) korean3ToonList.add(toon);
				if("h4".equals(toon.getInitialword())) korean4ToonList.add(toon);
				if("h5".equals(toon.getInitialword())) korean5ToonList.add(toon);
				if("e1".equals(toon.getInitialword())) english1ToonList.add(toon);
			}
		}
		
		modelAndView.addObject("topToonList", topToonList);
		modelAndView.addObject("korean1ToonList", korean1ToonList);
		modelAndView.addObject("korean2ToonList", korean2ToonList);
		modelAndView.addObject("korean3ToonList", korean3ToonList);
		modelAndView.addObject("korean4ToonList", korean4ToonList);
		modelAndView.addObject("korean5ToonList", korean5ToonList);
		modelAndView.addObject("english1ToonList", english1ToonList);
		
		return modelAndView;
		
	}
	
	/**
	 * 작품별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/toon/favoriteJsonProc.kt")
	public ModelAndView favoriteJsonProc() throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//로그인 여부 체크
		
		//즐겨찾기 추가 처리
		
		return modelAndView;
		
	}
	
	/**
	 * 작품별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/toon/stickerRegistJsonProc.kt")
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
		if(toonService.stickerCheck(stickerDomain) == 0)
			toonService.stickerRegist(stickerDomain);
		else
			modelAndView.addObject("duplicateSticker", "Y");
		
		//스티커 갯수
		modelAndView.addObject("stickerCnt", toonService.stickerCount(stickerDomain.getTimesseq()));
		
		modelAndView.addObject("stickerDomain", null);
		
		return modelAndView;		
	}
	
	/**
	 * 작품별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/toon/bookmarkRegistJsonProc.kt")
	public ModelAndView bookmarkRegistJsonProc(BookmarkDomain bookmarkDomain, HttpServletRequest request) throws Exception {
		
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
		bookmarkDomain.setIdfg(userDomain.getIdfg());
		//이메일 아이디
		bookmarkDomain.setRegid(userDomain.getEmail());
		//현재날짜시간
		bookmarkDomain.setRegdt(DateUtil.getNowDate(1));
		
		//파라미터 분리
		String timesseqListParam = StringUtil.defaultStr(bookmarkDomain.getTimesseqlist(), "");
		List<String> timesseqListParamArrayList = new ArrayList<String>(Arrays.asList(timesseqListParam.split(",")));
		
		//책갈피 추가
		boolean duplicateBookmark = true;
		for(int i = 0; i < timesseqListParamArrayList.size(); i++)
		{
			bookmarkDomain.setTimesseq(StringUtil.defaultInt(timesseqListParamArrayList.get(i)));
			if(toonService.bookmarkCheck(bookmarkDomain) == 0)
			{
				duplicateBookmark = false;
				toonService.bookmarkRegist(bookmarkDomain);
			}
		}
		
		if(duplicateBookmark)
			modelAndView.addObject("duplicateBookmark", "Y");
		
		modelAndView.addObject("bookmarkDomain", null);
		
		return modelAndView;		
	}
	
	
	
	@RequestMapping(value = "/toon/favoritesRegistJsonProc.kt")
	public ModelAndView favoritesRegistJsonProc(FavoritesDomain favoritesDomain, HttpServletRequest request) throws Exception {
		
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
		favoritesDomain.setIdfg(userDomain.getIdfg());
		//이메일 아이디
		favoritesDomain.setRegid(userDomain.getEmail());
		//현재날짜시간
		favoritesDomain.setRegdt(DateUtil.getNowDate(1));
		//푸쉬 알림 여부
		favoritesDomain.setPushyn("N");
		
		//푸쉬 알림 여부
		if(("1".equals(userDomain.getCheckno()) || "5".equals(userDomain.getCheckno())) && !"".equals(userDomain.getCheckno())){
			userDomain = userServiceIface.getUserUseTermsByEmail(userDomain.getEmail());
		}else{
			OllehUserDomain ollehUserDomain = userServiceIface.ollehUserDetail(userDomain.getEmail());
			
			userDomain.setUsetermsyn(ollehUserDomain.getUsetermsyn());
			userDomain.setUseagreementdt(ollehUserDomain.getUseagreementdt());
		}
		
		if(userDomain.getUseagreementdt() != null && !"".equals(userDomain.getUseagreementdt()))
			favoritesDomain.setPushyn("Y");
		
		//파라미터 분리
		String webtoonseqListParam = StringUtil.defaultStr(favoritesDomain.getWebtoonseqlist(), "");
		List<String> webtoonseqListParamArrayList = new ArrayList<String>(Arrays.asList(webtoonseqListParam.split(",")));
		
		//즐겨찾기 추가
		boolean duplicateFavorites = true;
		for(int i = 0; i < webtoonseqListParamArrayList.size(); i++)
		{
			favoritesDomain.setWebtoonseq(StringUtil.defaultInt(webtoonseqListParamArrayList.get(i)));
			if(toonService.favoritesCheck(favoritesDomain) == 0)
			{
				duplicateFavorites = false;
				toonService.favoritesRegist(favoritesDomain);
			}
		}
		
		if(duplicateFavorites)
			modelAndView.addObject("duplicateFavorites", "Y");

		modelAndView.addObject("favoritesDomain", null);		
		
		return modelAndView;		
	}
	
}