/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자(모바일)
 * FILE NAME      : ToonController.java
 * DESCRIPTION    : 웹툰 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-18      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

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
import com.olleh.webtoon.common.dao.api.domain.DeviceDomain;
import com.olleh.webtoon.common.dao.api.service.iface.DeviceService;
import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductHistoryDomain;
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.dao.shop.service.iface.ShopService;
import com.olleh.webtoon.common.dao.ticker.domain.TickerDomain;
import com.olleh.webtoon.common.dao.ticker.service.iface.TickerService;
import com.olleh.webtoon.common.dao.toon.domain.GenreDomain;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonImageDomain;
import com.olleh.webtoon.common.dao.toon.service.iface.ToonService;
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

@Controller("MobileToonController")
public class ToonController {
	
	@Autowired
	ToonService toonService;
	
	@Autowired
	PremiumService premiumService;
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	OrderbuyService orderbuyService;
	
	@Autowired
	TickerService tickerService;
	
	@Autowired
	UserServiceIface userServiceIface;
	
	@Autowired
	AdvertisementService advertisementService;
	
	@Autowired
	DeviceService deviceService;
	
	protected static Log logger = LogFactory.getLog(ToonController.class);

	@RequestMapping(value = "/m/main.kt")
	public ModelAndView main(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();	
		
		String str_rturl = "";
		
		//앱 일경우 앱설정 호출 URL 리턴
		if(RequestUtil.getUuid(request) != null){
			String rturlparam = StringUtil.defaultStr(StringUtil.cleanXSS(request.getParameter("rturl")), "");
			
			if(!"".equals(rturlparam)) {
				str_rturl = "?rturl="+rturlparam;
			}
		}
		
		modelAndView.setViewName("redirect:/m/toon/weekList.kt"+str_rturl);
		
		return modelAndView;		
	}
		
	/**
	 * 요일별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/toon/weekList.kt")
	public ModelAndView weekList(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();		
		
		modelAndView.setViewName("mobile/toon/weekList");
		
		//비밀번호 변경기간이 3개월 이상이면 변경팝업 노출
		if (AuthUtil.isLogin(request))
		{
			String u_pw_chg = com.olleh.webtoon.common.util.StringUtil.defaultStr(
					com.olleh.webtoon.common.util.CookieUtil.getCookie(request, "u_pw_chg"), "");
			
			if("".equals(u_pw_chg)){
				UserDomain user = AuthUtil.getDecryptUserInfo(request);
				
				if("open".equals(user.getIdfg())){
					UserDomain userDomain = userServiceIface.getUserInfoByEmail(user.getEmail());
					
					//비밀번호 변경기간이 3개월 이상이면 변경팝업 노출 위한 쿠키
					String pwchange_monthdiff = userDomain.getPwchange_monthdiff();
					int monthdiff = new Integer(pwchange_monthdiff);
					
					if(monthdiff >= 3){
						String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
						String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
						
						CookieUtil.setCookie(response, "u_pw_chg", "Y", -1, cookiePath, cookieDomain);
						CookieUtil.setCookie(response, "u_pw_chg_pg", "main", -1, cookiePath, cookieDomain);
						
						modelAndView.setViewName("pc/main_redirect");
						return modelAndView;
					}
				}
			}
			
			//비밀번호 8자리로 변경하기 팝업 노출을 위한 쿠키 - 20150303
			String u_pw_leg_chg = com.olleh.webtoon.common.util.StringUtil.defaultStr(
					com.olleh.webtoon.common.util.CookieUtil.getCookie(request, "u_pw_leg_chg"), "");
			
			String u_pw_chg_pg = com.olleh.webtoon.common.util.StringUtil.defaultStr(
					com.olleh.webtoon.common.util.CookieUtil.getCookie(request, "u_pw_chg_pg"), "");
			
			if("".equals(u_pw_leg_chg)){
				UserDomain user = AuthUtil.getDecryptUserInfo(request);
				
				if("open".equals(user.getIdfg())){
					
					String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
					String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
					
					CookieUtil.setCookie(response, "u_pw_leg_chg", "Y", -1, cookiePath, cookieDomain);
					
					modelAndView.setViewName("pc/main_redirect");
					return modelAndView;
				}
			}
			
			if(!"main".equals(u_pw_chg_pg)){
				UserDomain user = AuthUtil.getDecryptUserInfo(request);
				
				if("open".equals(user.getIdfg())){
					
					String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
					String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
					
					CookieUtil.setCookie(response, "u_pw_chg_pg", "main", -1, cookiePath, cookieDomain);
					
					modelAndView.setViewName("pc/main_redirect");
					return modelAndView;
				}
			}
		}
		
		//앱 로그인 동기화
		loginSync(request);
		
		return modelAndView;		
	}
	
	/**
	 * 요일별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/toon/weekJsonList.kt")
	public ModelAndView weekJsonList(@RequestParam Map<String,Object> param) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");	
		
		String pageNoParam = (String)param.get("pageNo");		
		int pageNo = 1;
		pageNo =  StringUtil.defaultInt(pageNoParam,1);		
		
		String pageSizeParam = (String)param.get("pageSize");		
		int pageSize = 10;
		pageSize =  StringUtil.defaultInt(pageSizeParam,10);	
		
		String weekParam = (String)param.get("week");		
		int week = 0;
		week =  StringUtil.defaultInt(weekParam,DateUtil.getDayOfWeek());	
		
		//전체 요일별 웹툰 목록(업데이트순, 스티커순, 제목순, 작가순 : Cache 처리)		
		//각 요일별 List 추출
		param.put("toonfg", "all");
		List<ToonDomain> toonList = toonService.toonList(param);
		
		if(week > 0){			
			List<ToonDomain> tmpToonList = new ArrayList<ToonDomain>();
			for(ToonDomain toon : toonList){
				
				//모바일 전시 여부 및 toon 영역 전시 여부
				if("Y".equals(toon.getMdisplayyn()) && "Y".equals(toon.getToonyn())){
					if(week == 1 && "Y".equals(toon.getMondayyn())) tmpToonList.add(toon);
					if(week == 2 && "Y".equals(toon.getTuesdayyn())) tmpToonList.add(toon);
					if(week == 3 && "Y".equals(toon.getWednesdayyn())) tmpToonList.add(toon);
					if(week == 4 && "Y".equals(toon.getThursdayyn())) tmpToonList.add(toon);
					if(week == 5 && "Y".equals(toon.getFridayyn())) tmpToonList.add(toon);
					if(week == 6 && "Y".equals(toon.getSaturdayyn())) tmpToonList.add(toon);
					if(week == 7 && "Y".equals(toon.getSundayyn())) tmpToonList.add(toon);
					if(week == 8 && "end".equals(toon.getSerialfg())) tmpToonList.add(toon);
				}
			}
			
			toonList = tmpToonList;
		}
		
		int toIndex = 0;
		toIndex = pageNo*pageSize;	
		if(toIndex > toonList.size()) toIndex = toonList.size();
		
		//완결 일경우만 페이징 처리
		if(week < 8) toIndex = toonList.size();
		
		int fromIndex = ((pageNo-1)*pageSize);
		
		modelAndView.addObject("toonList", toonList.subList(fromIndex, toIndex));	
		modelAndView.addObject("totalCount", toonList.size());
		modelAndView.addObject("week", week);
		
		return modelAndView;
		
	}
	
	
	/**
	 * 작화 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/toon/timesList.kt")
	public ModelAndView timesList(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//모바일 기기가 아닐경우 
		if("real".equals(System.getProperty("serverType")) && !RequestUtil.isMobile(request)){
			String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/toon/timesList.kt" + urlcd);
			return modelAndView;
		}
		
		//작품 기본 상세설명 정보 가져오기
		ToonDomain toonInfo = toonService.simpleToonDetail(param);
		
		//웹소설
		if("novel".equals(toonInfo.getToonfg())){
			modelAndView.addObject("webtoonseq", toonInfo.getWebtoonseq());
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/novel/novelList.kt");
			
			return modelAndView;
		}
				
		int availableBerry = 0;
		int availableBlueBerry = 0;
		if(AuthUtil.isLogin(request)){
			
			//회원정보 복호화
			UserDomain user = AuthUtil.getDecryptUserInfo(request);
			
			// 보유베리
			availableBerry = orderbuyService.berryAvail(user.getEmail(), user.getIdfg());
			
			// 보유블루베리
			availableBlueBerry = orderbuyService.blueBerryAvail(user.getEmail(), user.getIdfg());
		}
		
		modelAndView.addObject("toonDetail", toonInfo);
		modelAndView.addObject("availableBerry", availableBerry);
		modelAndView.addObject("availableBlueBerry", availableBlueBerry);
		
		modelAndView.setViewName("mobile/toon/timesList");
		
		return modelAndView;		
	}
	
	/**
	 * 작화 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/toon/timesJsonList.kt")
	public ModelAndView timesJsonList(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		String mode = (String)param.get("mode");		
		mode =  StringUtil.defaultStr(mode,"");	
				
		//회원정보 복호화
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		if(user != null) param.put("buyid", user.getEmail());
		
		//페이징 처리
		String pageNoParam = (String)param.get("pageNo");		
		int pageNo = 1;
		pageNo =  StringUtil.defaultInt(pageNoParam,1);	
		
		String pageSizeParam = (String)param.get("pageSize");		
		int pageSize = 10;
		pageSize =  StringUtil.defaultInt(pageSizeParam,10);
		
		param.put("startRowNo", (pageNo-1)*pageSize);
		param.put("pageSize", pageSize);
		
		//더보기 클릭시 웹툰 상세정보 및 회차목록 갯수는 전달하지 않음.
		if(!mode.equals("list")){
			// 작품 상세설명 정보 가져오기
			ToonDomain toonDetail = toonService.toonDetail(param);
			modelAndView.addObject("toonDetail", toonDetail);
			
			//작품 연재 상태
			param.put("serialfg", toonDetail.getSerialfg());	
		
			// 작품 회차별 목록 갯수
			int timesListCount = toonService.timesListCount(param);
			modelAndView.addObject("totalCount", timesListCount);

		}
		
		// 작품 연관 아이템 갯수
		int refItemCount = toonService.refItemCount(param);
		modelAndView.addObject("refItemCount", refItemCount);
		
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
	@RequestMapping(value = "/m/toon/timesDetail.kt")
	public ModelAndView timesDetail(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//모바일 기기가 아닐경우 
		if("real".equals(System.getProperty("serverType")) && !RequestUtil.isMobile(request)){
			String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/toon/timesDetail.kt" + urlcd);
			return modelAndView;
		}
				
		//웹소설 여부 체크
		ToonDomain toonInfo = toonService.simpleTimesDetail(param);
		if("novel".equals(toonInfo.getToonfg())){
			modelAndView.addObject("timesseq", toonInfo.getTimesseq());
			modelAndView.addObject("webtoonseq", toonInfo.getWebtoonseq());
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/novel/novelDetail.kt");
			
			return modelAndView;
		}
		
		modelAndView.setViewName("mobile/toon/timesDetail");
		
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
		
		// 인증상태
		int myCert = PremiumUtil.myCert(request);
		
		// 인증후 이동할 URI
		String beforeURI = RequestUtil.getFullURI(request);
		beforeURI = (beforeURI != null && 0 < beforeURI.length()) ? "?urlcd=" + beforeURI : "";
		
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
		boolean existTimesprd = existTimesprdyn != null && "Y".equals(existTimesprdyn);											      //작품에 회차 상품 존재 여부
		
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
				
		//유료 회차 상품이 존재하고 선택 회차가 유료일 경우, 작품 상품만 존재할 경우 -> 유료
		if((existTimesprd && curTimesPrd) || (existToonPrd && !existTimesprd)){
			// 로그인 체크
			if (!isLogin) {
				modelAndView.setViewName("redirect:/m/login.kt" + beforeURI);
				return modelAndView;
			}
			
			//약관 미동의
			if (myCert == PremiumUtil.DISAGREE_TERMS_USER) {
				modelAndView.setViewName("redirect:/m/user/ollehUserAgreement.kt" + beforeURI);
				return modelAndView;
			}
			
			//사용자가 미인증 상태이면서 IOS APP일 경우 분기처리
			if((myCert == PremiumUtil.NOT_CERT_USER || myCert == PremiumUtil.MINOR_NOT_CERT_USER) 
					&& "ios".equals(RequestUtil.getClientDeviceInfo(request))){
				modelAndView.setViewName("redirect:/m/premium/ios_payment.kt" + beforeURI);
				return modelAndView;
			}
			
			// 미인증
			if (myCert == PremiumUtil.NOT_CERT_USER) {
				modelAndView.setViewName("redirect:/m/premium/premiumMyCert.kt" + beforeURI);
				return modelAndView;
			}
			
			// 미성년자(법적대리인 미인증)
			else if (myCert == PremiumUtil.MINOR_NOT_CERT_USER) {
				modelAndView.setViewName("redirect:/m/premium/premiumLawerCert.kt" + beforeURI);
				return modelAndView;
			}
			
			// 구매내역 이용기간이 남은 동일 회차가 있는가?(작품, 회차)
			if (productAvail == null) {
				
				//IOS APP일 경우 분기처리
				if("ios".equals(RequestUtil.getClientDeviceInfo(request))){
					modelAndView.setViewName("redirect:/m/premium/ios_payment.kt" + beforeURI);
					return modelAndView;
				}
				
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
					modelAndView.setViewName("redirect:/m/premium/mixPurchase.kt");
					return modelAndView;
				}
				
				if(uniqueTimes && timesProduct != null && "mix".equals(timesProduct.getPurchasefg())){
					String title = toon.getTimestitle() + " - " + toonDetail.getWebtoonnm();
					modelAndView.addObject("title", title);
					modelAndView.addObject("prdhistoryseq", timesProduct.getPrdhistoryseq());
					modelAndView.setViewName("redirect:/m/premium/mixPurchase.kt");
					return modelAndView;
				}
				
				// 회차 상세정보
				modelAndView.addObject("timesDetail", toon);
				
				// 작품 상세정보
				modelAndView.addObject("toonDetail", toonDetail);
				
				// 회차 총 갯수
				modelAndView.addObject("totalCnt", totalCnt);
				
				// 회차 상품정보
				modelAndView.addObject("timesProduct", timesProduct);
				
				// 작품 상품정보
				modelAndView.addObject("webtoonProduct", webtoonProduct);
					 
				// 회차구매 알림창
				modelAndView.setViewName("mobile/toon/purchaseTimes");
				
				return modelAndView;
			}
		}

		//광고 종류 선별
		int adCnt = 0;
		AdvertisementDomain advertisementDomain = new AdvertisementDomain();
		advertisementDomain.setAreafg("msponsorT");
		advertisementDomain.setDevicefg("mobile");
		advertisementDomain.setWebtoonseq(String.valueOf(toon.getWebtoonseq()));
		advertisementDomain.setTimesseq(String.valueOf(toon.getTimesseq()));
		
		adCnt = advertisementService.advertisementListCount(advertisementDomain);
		
		//작품_회차 광고 여부
		if(adCnt > 0){
			modelAndView.addObject("adAreafg", "msponsorT");
		}else{
			advertisementDomain.setAreafg("msponsorW");
			adCnt = advertisementService.advertisementListCount(advertisementDomain);
			
			//작품 광고 여부
			if(adCnt > 0){
				modelAndView.addObject("adAreafg", "msponsorW");
			}else{
				advertisementDomain.setAreafg("msponsor");
				adCnt = advertisementService.advertisementListCount(advertisementDomain);
				
				//작품 전체 광고 여부
				if(adCnt > 0)
					modelAndView.addObject("adAreafg", "msponsor");
				
			}
		}
		 
		advertisementDomain.setAreafg("mtextlinkA");
		List<AdvertisementDomain> adListA = advertisementService.adList(advertisementDomain);
		modelAndView.addObject("adListGetA", adListA);
		
		advertisementDomain.setAreafg("mtextlinkB");
		List<AdvertisementDomain> adListB = advertisementService.adList(advertisementDomain);
		modelAndView.addObject("adListGetB", adListB);
		
		advertisementDomain.setAreafg("mtextlinkC");
		List<AdvertisementDomain> adListC = advertisementService.adList(advertisementDomain);
		modelAndView.addObject("adListGetC", adListC);
		
		return modelAndView;
		
	}
	
	/**
	 * 작화 보기 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/toon/timesJsonDetail.kt")
	public ModelAndView timesJsonDetail(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");	
		
		// 작화 이미지 정보 가져오기
		List<ToonImageDomain> toonImageList = toonService.toonImageList(param);
		modelAndView.addObject("toonImageList", toonImageList);
		
		//스티커 갯수
		String timesseq = (String)param.get("timesseq");
		modelAndView.addObject("stickerCnt", toonService.stickerCount(Integer.parseInt(timesseq)));		

		//로그인 여부 체크
		int myStickerCnt = 0;
		if(!AuthUtil.isLogin(request)){
			
			// 작품의 상세정보 가져오기
			ToonDomain toon = toonService.timesDetail(param);
			modelAndView.addObject("toon", toon);
		}
		else{
			
			//회원정보 복호화
			UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
			
			ProductAvailDomain productAvail = premiumService.productAvail(userDomain.getEmail(), param);
			
			if(productAvail != null)
			{
				param.put("myuseyn", "Y");
			}
			
			// 작품의 상세정보 가져오기
			ToonDomain toon = toonService.timesDetail(param);
			modelAndView.addObject("toon", toon);
			
			if(userDomain != null && !"".equals(userDomain.getEmail())){
				StickerDomain sticker = new StickerDomain();
				
				sticker.setIdfg(userDomain.getIdfg());
				sticker.setRegid(userDomain.getEmail());
				sticker.setTimesseq(Integer.parseInt(timesseq));

				myStickerCnt = toonService.myStickerCount(sticker);
			}
			
			//최근 본 툰 셋팅
			String email = userDomain.getEmail().substring(0, 2) + userDomain.getEmail().substring(userDomain.getEmail().length()-2, userDomain.getEmail().length());
			String cookieName = "timesseq"+email+"list";      //쿠키 변수명
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
	@RequestMapping(value = "/m/toon/genreList.kt")
	public ModelAndView genreList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/toon/genreList");
		
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
	@RequestMapping(value = "/m/toon/genreJsonList.kt")
	public ModelAndView genreJsonList(@RequestParam Map<String,Object> param) throws Exception {
		
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
		
		//전체 장르별 웹툰 목록(업데이트순, 스티커순, 제목순, 작가순 : Cache 처리)
		param.put("toonfg",   "toon");
		param.put("toonType", "toon");
		List<ToonDomain> tmpToonList = toonService.toonList(param);
		
		List<ToonDomain> toonList = new ArrayList<ToonDomain>(); 
		
		if(genreNo != 0){
			for(ToonDomain toon : tmpToonList){
				
				//모바일 전시 여부 및 toon 영역 전시 여부
				if("Y".equals(toon.getMdisplayyn()) && "Y".equals(toon.getToonyn())){
					if(genreNo == toon.getGenreseq1() || 
							genreNo == toon.getGenreseq2() || 
									genreNo == toon.getGenreseq3()){
						//장르별 웹툰 목록
						toonList.add(toon);
					}else if(genreNo == 999 && "end".equals(toon.getSerialfg())){
						//완결 웹툰 목록
						toonList.add(toon);
					}
				}
			}
		}else{
			//전체 웹툰 목록
			toonList = tmpToonList;
		}
		
		
		
		int toIndex = 0;
		toIndex = pageNo*pageSize;			
		if(toIndex > toonList.size()) toIndex = toonList.size();
		
		int fromIndex = ((pageNo-1)*pageSize);
		
		modelAndView.addObject("toonList", toonList.subList(fromIndex, toIndex));	
		modelAndView.addObject("totalCount", toonList.size());
		
		return modelAndView;
		
	}
	
	/**
	 * 작품별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/toon/toonList.kt")
	public ModelAndView toonList(@RequestParam Map<String,Object> param) throws Exception {
		
		//초성 검색 조건이 없을때 default값 지정
		if(param.get("initial") == null) param.put("initial", "h1");
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/toon/toonList");
		
		//상단 웹툰 3개(운영자가 등록한 추천 웹툰 3개 : Cache 처리)
		
		//전체 초성 웹툰 목록(업데이트순, 스티커순, 제목순, 작가순 : Cache 처리)		
		List<ToonDomain> korean1ToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> korean2ToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> korean3ToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> korean4ToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> korean5ToonList = new ArrayList<ToonDomain>();
		List<ToonDomain> english1ToonList = new ArrayList<ToonDomain>();
		
		param.put("toonfg", "toon");
		List<ToonDomain> toonList = toonService.toonList(param); 
		for(ToonDomain toon : toonList){
			
			//모바일 전시 여부 및 toon 영역 전시 여부
			if("Y".equals(toon.getMdisplayyn()) && "Y".equals(toon.getToonyn())){
				if("h1".equals(toon.getInitialword())) korean1ToonList.add(toon);
				if("h2".equals(toon.getInitialword())) korean2ToonList.add(toon);
				if("h3".equals(toon.getInitialword())) korean3ToonList.add(toon);
				if("h4".equals(toon.getInitialword())) korean4ToonList.add(toon);
				if("h5".equals(toon.getInitialword())) korean5ToonList.add(toon);
				if("e1".equals(toon.getInitialword())) english1ToonList.add(toon);				
			}
		}
		
		modelAndView.addObject("korean1ToonList", korean1ToonList);
		modelAndView.addObject("korean2ToonList", korean2ToonList);
		modelAndView.addObject("korean3ToonList", korean3ToonList);
		modelAndView.addObject("korean4ToonList", korean4ToonList);
		modelAndView.addObject("korean5ToonList", korean5ToonList);
		modelAndView.addObject("english1ToonList", english1ToonList);
		
		return modelAndView;
		
	}
	
	/**
	 * ticker 리스트 조회 후
	 * 결과 json을 보내주는 RequestMapping메소드
	 * @return ModelAndView modelAndView = ModelAndView 객체
	 */

	@RequestMapping(value = "/m/ticker/tickerJsonList.kt")
	public ModelAndView tickerJsonList() throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		String displayfg = "mobile";
		
		//ticker 조회
		List<TickerDomain> tickerList = tickerService.tickerList(displayfg);
		modelAndView.addObject( "tickerList", tickerList );
		
		modelAndView.setViewName("mappingJacksonJsonView");
		return modelAndView;
	}
	
	/**
	 * 앱, 웹뷰의 로그인 동기화 메소드
	 * @return 
	 */
	public void loginSync(HttpServletRequest request) throws Exception {
		try{
		//모바일 앱에서 로그인, 로그아웃 부분 처리
		 String uuid = RequestUtil.getUuid(request);		 
		 
		 if(uuid != null){			
			 DeviceDomain deviceDomain = deviceService.getDeviceByDeviceid(uuid);			 
	
			 UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
			 
			 if(AuthUtil.isLogin(request)){
				 
				 //로그인 시
				 //클라이언트 로그인정보 와 서버 로그인 정보가 다를경우
				 if(!userDomain.getEmail().equals(deviceDomain.getUserid()) || !userDomain.getIdfg().equals(deviceDomain.getIdfg()) || "N".equals(deviceDomain.getLoginyn())){					 
					 userDomain.setUuid(uuid);
					 userDomain.setLoginyn("Y");
					 
					 userServiceIface.updateEmailByUuid(userDomain);
				 }
			 }else{
				 if(deviceDomain.getUserid() != null && !"".equals(deviceDomain.getUserid()) || "Y".equals(deviceDomain.getLoginyn())){
					 //로그아웃 시
					 //클라이언트 로그인정보 와 서버 로그인 정보가 다를경우				 
					 userDomain = new UserDomain();				 
					 userDomain.setUuid(uuid);
					 userDomain.setLoginyn("N");
					 
					 userServiceIface.updateEmailByUuid(userDomain);
				 }
			 }				 
		 }
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
}