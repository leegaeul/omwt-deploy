/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : MytoonController.java
 * DESCRIPTION    : 나의 웹툰(y Toon) 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-21      init
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.api.domain.DeviceDomain;
import com.olleh.webtoon.common.dao.api.service.iface.DeviceService;
import com.olleh.webtoon.common.dao.orderbuy.domain.BuyDomain;
import com.olleh.webtoon.common.dao.orderbuy.domain.OrderDomain;
import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.dao.support.domain.QnaDomain;
import com.olleh.webtoon.common.dao.support.service.iface.QNAService;
import com.olleh.webtoon.common.dao.toon.domain.BookmarkDomain;
import com.olleh.webtoon.common.dao.toon.domain.FavoritesDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.service.iface.ToonService;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller("MobileMytoonController")
public class MytoonController {
			
	protected static Log logger = LogFactory.getLog(MytoonController.class);
	
	@Autowired
	ToonService toonService;
	
	@Autowired
	OrderbuyService orderbuyService;
	
	@Autowired
	PremiumService premiumService;
	
	@Autowired
	UserServiceIface userServiceIface;
	
	@Autowired
	QNAService qnaService;
	
	@Autowired
	DeviceService deviceService;
		
	/**
	 * 최근 본 Toon 작화 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/mytoon/recentToon.kt")
	public ModelAndView recentToon(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크		
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		//앱 로그인 동기화
		loginSync(request);
		
		//회원정보 복호화
				UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
				
				String email = userDomain.getEmail().substring(0, 2) + userDomain.getEmail().substring(userDomain.getEmail().length()-2, userDomain.getEmail().length());
				String cookieName = "timesseq"+email+"list";      //쿠키 변수명
				String regdtCookieName = "regdt"+email+"list";      //쿠키 등록일 변수명
				
				//쿠키에서 최근 본 회차 목록 가져온다.
				String timesseqList = StringUtil.defaultStr(CookieUtil.getCookie(request, cookieName), "");
				String regdtList = StringUtil.defaultStr(CookieUtil.getCookie(request, regdtCookieName), "");
				
				//20개가 넘을경우 
				int arraySize = 20;
				String[] timesseqArray = timesseqList.split(",");	
				String[] regdtArray = regdtList.split(",");
				String[] tmpTimesseqArray = new String[arraySize];
				String[] tmpRegdtArray = new String[arraySize];
				
				if(timesseqArray.length > arraySize){
					timesseqList = "";
					regdtList = "";
					int itv = timesseqArray.length -arraySize;
					for(int i=0 ; i < arraySize ; i++){
						tmpTimesseqArray[i] = timesseqArray[i+itv];		
						tmpRegdtArray[i] = regdtArray[i+itv];
						
						timesseqList = timesseqList + timesseqArray[i+itv];
						
						if(i != arraySize-1) timesseqList = timesseqList + ",";					
					}
				}else{
					tmpTimesseqArray = timesseqArray;
					tmpRegdtArray = regdtArray;
				}
				
				param.put("timesseqList", timesseqList);		
				List<ToonDomain> tmpList = new ArrayList<ToonDomain>();
				

				//웹툰 목록을 가져와서 최신순으로 정렬
				if(!"".equals(timesseqList)){			
					//웹툰 목록
					List<ToonDomain> list = toonService.recentList(param);				
					
					for(int i = (tmpTimesseqArray.length-1) ; i >= 0 ; i--){
						for(ToonDomain toon : list){
							if(tmpTimesseqArray[i].equals(""+toon.getTimesseq())){
								toon.setRegdt(tmpRegdtArray[i].substring(0, 4)+"."+tmpRegdtArray[i].substring(4, 6)+"."+tmpRegdtArray[i].substring(6, 8)+" "+tmpRegdtArray[i].substring(8, 10)+":"+tmpRegdtArray[i].substring(10, 12));
								
								tmpList.add(toon);
							}
						}
					}
					
				}
				
				modelAndView.addObject("recentList", tmpList);	
		
		modelAndView.setViewName("mobile/mytoon/recentToon");
		
		// 최근 본 Toon 작화 목록 Cookie에서 가져오기
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/m/mytoon/recentToonJson.kt")
	public ModelAndView recentToonJson(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크		
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		//회원정보 복호화
		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		String email = userDomain.getEmail().substring(0, 2) + userDomain.getEmail().substring(userDomain.getEmail().length()-2, userDomain.getEmail().length());
		String cookieName = "timesseq"+email+"list";      //쿠키 변수명
		String regdtCookieName = "regdt"+email+"list";      //쿠키 등록일 변수명
		
		//쿠키에서 최근 본 회차 목록 가져온다.
		String timesseqList = StringUtil.defaultStr(CookieUtil.getCookie(request, cookieName), "");
		String regdtList = StringUtil.defaultStr(CookieUtil.getCookie(request, regdtCookieName), "");
		
		//20개가 넘을경우 
		int arraySize = 20;
		String[] timesseqArray = timesseqList.split(",");	
		String[] regdtArray = regdtList.split(",");
		String[] tmpTimesseqArray = new String[arraySize];
		String[] tmpRegdtArray = new String[arraySize];
		
		if(timesseqArray.length > arraySize){
			timesseqList = "";
			regdtList = "";
			int itv = timesseqArray.length -arraySize;
			for(int i=0 ; i < arraySize ; i++){
				tmpTimesseqArray[i] = timesseqArray[i+itv];		
				tmpRegdtArray[i] = regdtArray[i+itv];
				
				timesseqList = timesseqList + timesseqArray[i+itv];
				
				if(i != arraySize-1) timesseqList = timesseqList + ",";					
			}
		}else{
			tmpTimesseqArray = timesseqArray;
			tmpRegdtArray = regdtArray;
		}
		
		param.put("timesseqList", timesseqList);		
		List<ToonDomain> tmpList = new ArrayList<ToonDomain>();
		

		//웹툰 목록을 가져와서 최신순으로 정렬
		if(!"".equals(timesseqList)){			
			//웹툰 목록
			List<ToonDomain> list = toonService.recentList(param);				
			
			for(int i = (tmpTimesseqArray.length-1) ; i >= 0 ; i--){
				for(ToonDomain toon : list){
					if(tmpTimesseqArray[i].equals(""+toon.getTimesseq())){
						toon.setRegdt(tmpRegdtArray[i].substring(0, 4)+"."+tmpRegdtArray[i].substring(4, 6)+"."+tmpRegdtArray[i].substring(6, 8));
						
						tmpList.add(toon);
					}
				}
			}
			
		}
				
		modelAndView.addObject("recentList", tmpList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
		
	}
	
	/**
	 * 즐겨찾기 Toon 작품 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/mytoon/favoriteList.kt")
	public ModelAndView favoriteList(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크		
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		//앱 로그인 동기화
		loginSync(request);
		
		modelAndView.setViewName("mobile/mytoon/favoriteList");
		
		// 즐겨찾기 Toon 작품 목록 가져오기
		
		return modelAndView;
		
	}
	
	/**
	 * 즐겨찾기 Toon 작품 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/mytoon/favoriteJsonList.kt")
	public ModelAndView favoriteJsonList(FavoritesDomain favoritesDomain, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}

		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		//로그인 유저 이메일 셋팅
		favoritesDomain.setRegid(userDomain.getEmail());

		//페이지 사이즈 기본값
		if(favoritesDomain.getPageSize() == 0) favoritesDomain.setPageSize(10);
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = toonService.favoritesListCnt(favoritesDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//웹툰 목록
		List<ToonDomain> list = toonService.favoritesList(favoritesDomain);
		modelAndView.addObject("favoritesList", list);

		modelAndView.addObject("favoritesDomain", null);
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 서비스 동의 여부를 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/mytoon/favoriteUseTermsJsonProc.kt")
	public ModelAndView favoriteUseTermsJsonProc(UserDomain userDomain, HttpServletRequest request) throws Exception 
	{

		ModelAndView modelAndView = new ModelAndView();
		
		// 로그인 여부 체크
		if(!AuthUtil.isLogin(request))
		{
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}
		
		String usetermsyn = userDomain.getUsetermsyn();
		userDomain = AuthUtil.getDecryptUserInfo(request);
		
		// 알림 받기 동의여부
		if(("1".equals(userDomain.getCheckno()) || "5".equals(userDomain.getCheckno())) && !"".equals(userDomain.getCheckno())){
			userDomain.setUsetermsyn(usetermsyn);
			
			userServiceIface.pushtermsynUpdate(userDomain);
		}else{
			OllehUserDomain ollehUserDomain = new OllehUserDomain();	
			ollehUserDomain.setOllehid(userDomain.getEmail());
			ollehUserDomain.setUsetermsyn(usetermsyn);
			
			userServiceIface.ollehUserUsetermsynUpdateByOllehId(ollehUserDomain);
		}
		
		modelAndView.addObject("userDomain", null);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;		
	}
	
	/**
	 * 알림 서비스 On/Off를 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/mytoon/favoritePushJsonProc.kt")
	public ModelAndView favoritePushJsonProc(FavoritesDomain favoritesDomain, HttpServletRequest request) throws Exception 
	{
		ModelAndView modelAndView = new ModelAndView();
		
		// 로그인 여부 체크
		if(!AuthUtil.isLogin(request))
		{
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}

		
		UserDomain userCheck = AuthUtil.getDecryptUserInfo(request);
		UserDomain userDomain = null;
		
		// 알림 받기 동의여부
		if(("1".equals(userCheck.getCheckno()) || "5".equals(userCheck.getCheckno())) && !"".equals(userCheck.getCheckno())){
			userDomain = userServiceIface.getUserUseTermsByEmail(userCheck.getEmail());
		}else{
			OllehUserDomain ollehUserDomain = userServiceIface.ollehUserDetail(userCheck.getEmail());
			
			userDomain = userCheck;
			userDomain.setUsetermsyn(ollehUserDomain.getUsetermsyn());
			userDomain.setUseagreementdt(ollehUserDomain.getUseagreementdt());
		}
		
		if(userDomain.getUseagreementdt() != null && !"".equals(userDomain.getUseagreementdt())){
			
			//정보동의여부가 미동의 이며 알림 수신이 Y이면 정보동의 여부도 동의로 업데이트
			logger.debug("favoritesDomain.getPushyn() =====>" + favoritesDomain.getPushyn());
			logger.debug("userDomain.getUsetermsyn() =====>" + userDomain.getUsetermsyn());
			if("Y".equals(favoritesDomain.getPushyn()) && "N".equals(userDomain.getUsetermsyn())){
				if(("1".equals(userCheck.getCheckno()) || "5".equals(userCheck.getCheckno())) && !"".equals(userCheck.getCheckno())){
					userCheck.setUsetermsyn(favoritesDomain.getPushyn());
					
					userServiceIface.pushtermsynUpdate(userCheck);
				}else{
					OllehUserDomain ollehUserDomain = new OllehUserDomain();	
					ollehUserDomain.setOllehid(userCheck.getEmail());
					ollehUserDomain.setUsetermsyn(favoritesDomain.getPushyn());
					
					userServiceIface.ollehUserUsetermsynUpdateByOllehId(ollehUserDomain);
				}
			}
			
			favoritesDomain.setIdfg(userCheck.getIdfg());
			favoritesDomain.setRegid(userCheck.getEmail());
			toonService.favoritesPushRegist(favoritesDomain);
			userDomain.setUsetermsyn("Y");
		}else{
			userDomain.setUsetermsyn("N");
		}
		
		modelAndView.addObject("usetermsyn", userDomain.getUsetermsyn());
		
		modelAndView.addObject("favoritesDomain", null);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;				
	}
	
	/**
	 * 책갈피 Toon 작품 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/mytoon/bookmarkList.kt")
	public ModelAndView toonmarkList(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		//앱 로그인 동기화
		loginSync(request);
		
		modelAndView.setViewName("mobile/mytoon/bookmarkList");
		
		// 책갈피 toon 작화 목록 가져오기
		
		return modelAndView;
		
	}
	
	/**
	 * 책갈피 Toon 작품 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/mytoon/bookmarkJsonList.kt")
	public ModelAndView bookmarkJsonList(BookmarkDomain bookmarkDomain, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}

		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		//로그인 유저 이메일 셋팅
		bookmarkDomain.setRegid(userDomain.getEmail());

		//페이지 사이즈 기본값
		if(bookmarkDomain.getPageSize() == 0) bookmarkDomain.setPageSize(10);
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = toonService.bookmarkListCnt(bookmarkDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//웹툰 목록
		List<ToonDomain> list = toonService.bookmarkList(bookmarkDomain);
		modelAndView.addObject("bookmarkList", list);

		modelAndView.addObject("bookmarkDomain", null);
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
		
	}
	
	/**
	 * 회원 상세정보 화면 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/mytoon/userDetail.kt")
	public ModelAndView userDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/mytoon/userDetail");
		
		//로그인 여부 체크		
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		//앱 로그인 동기화
		loginSync(request);
		
		// 회원 정보 가져오기
		
		// 쿠키에 저장되어있는 네임콘 정보와 DB에서 회원이 보유한 네임콘 정보가 다를경우 쿠키값에 다시 셋팅
		UserDomain oldUserDomain = AuthUtil.getDecryptUserInfo(request);
		//UserDomain newUserDomain = userServiceIface.getUserInfoByEmail(oldUserDomain.getEmail());
		OllehUserDomain newUserDomain = null;
		
		if("open".equals(oldUserDomain.getIdfg()))
			 newUserDomain = (UserDomain)userServiceIface.getUserInfoByEmail(oldUserDomain.getEmail());
		if("olleh".equals(oldUserDomain.getIdfg()))
			newUserDomain = userServiceIface.ollehUserDetail(oldUserDomain.getEmail());
		
		if(oldUserDomain.getNameconseq() != newUserDomain.getNameconseq()){
			String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
			String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
			String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
			int intCookieAge = Integer.parseInt(cookieAge);
			
			//쿠키정보 변경		
			CookieUtil.setCookie(response, "u_nc_seq", String.valueOf(newUserDomain.getNameconseq()), intCookieAge, cookiePath, cookieDomain);
			CookieUtil.setCookie(response, "u_nc_url", newUserDomain.getNameconurl(), intCookieAge, cookiePath, cookieDomain);
			CookieUtil.setCookie(response, "u_mnc_url", newUserDomain.getMnameconurl(), intCookieAge, cookiePath, cookieDomain);
		}
		
		return modelAndView;
	}
	
	/**
	 * 회원 상세정보 화면 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/mytoon/nameconChange.kt")
	public ModelAndView nameconChange(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam(value = "returnUrl", defaultValue = "") String returnUrl ) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크		
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		//앱 로그인 동기화
		loginSync(request);
		
		if( StringUtils.hasText( returnUrl ))
		{
			logger.info( "MytoonController.nameconChange.returnUrl=" + returnUrl );
			CookieUtil.setCookie(response, "nameconChangeReturnUrl", returnUrl, -1, 
					MessageUtil.getSystemMessage("system.cookie.path"), 
					MessageUtil.getSystemMessage("system.cookie.domain"));
		}

		//UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		/*List<NameconDomain> defualtNameconList = userServiceIface.defualtNameconList(userDomain);
		
		modelAndView.addObject("defualtNameconList", defualtNameconList);*/
		
		modelAndView.addObject("returnUrl", returnUrl);
		modelAndView.setViewName("mobile/mytoon/nameconChange");
		
		// 회원 정보 가져오기
		
		// 회원이 보유한 네임콘 정보 가져오기
		
		return modelAndView;
		
	}
		
	/**
	 * 베리사용내역(구매내역) 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/mytoon/berryuseList.kt")
	public ModelAndView berryuseList(HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크		
		if(!AuthUtil.isLogin(request))
		{
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		//앱 로그인 동기화
		loginSync(request);
		
		modelAndView.setViewName("mobile/mytoon/berryuseList");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/m/mytoon/berryuseJsonList.kt")
	public ModelAndView berryuseJsonList(BuyDomain buyDomain, HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();
							
		//로그인 여부 체크		
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}

		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		//로그인 유저 이메일 셋팅
		buyDomain.setBuyid(userDomain.getEmail());
		
		if( userDomain.getIdfg().equals("open") ) 
			buyDomain.setIdfg("open");
		else
			buyDomain.setIdfg("olleh");

		//페이지 사이즈 기본값
		if(buyDomain.getPageSize() == 0) buyDomain.setPageSize(10);
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = orderbuyService.buyListCnt(buyDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//구매 목록
		List<BuyDomain> list = orderbuyService.berryuseList(buyDomain);
		modelAndView.addObject("berryuseList", list);
		
		// 사용자 총 베리 보유량
		String userBerry = orderbuyService.strBerryAvail(userDomain.getEmail(), userDomain.getIdfg());
		modelAndView.addObject("userBerry",userBerry);
		
		// 사용자 총 블루베리 보유량
		String userBlueBerry = orderbuyService.strBlueBerryAvail(userDomain.getEmail(), userDomain.getIdfg());
		modelAndView.addObject("userBlueBerry",userBlueBerry);
		
		// 블루멤버십 가입 여부 조회
		String blueMembershipyn = premiumService.getBlueMembershipInfo(userDomain.getEmail(), userDomain.getIdfg());
		modelAndView.addObject("blueMembershipyn", blueMembershipyn);

		modelAndView.addObject("buyDomain",null);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 베리충전내역(구매내역) 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */	
	@RequestMapping(value = "/m/mytoon/berrychargeList.kt")
	public ModelAndView berrychargeList(HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크		
		if(!AuthUtil.isLogin(request))
		{
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		//앱 로그인 동기화
		loginSync(request);
		
		modelAndView.setViewName("mobile/mytoon/berrychargeList");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/m/mytoon/berrychargeJsonList.kt")
	public ModelAndView berrychargeJsonList(OrderDomain orderDomain, HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크		
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}

		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		//로그인 유저 이메일 셋팅
		orderDomain.setOrderid(userDomain.getEmail());
		
		if( userDomain.getIdfg().equals("open") ) 
			orderDomain.setIdfg("open");
		else
			orderDomain.setIdfg("olleh");
		
		//페이지 사이즈 기본값
		if(orderDomain.getPageSize() == 0) orderDomain.setPageSize(10);
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = orderbuyService.orderListCnt(orderDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//베리 충전 목록
		List<OrderDomain> list = orderbuyService.berrychargeList(orderDomain);
		modelAndView.addObject("berrychargeList", list);
		
		// 사용자 총 베리 보유량
		String userBerry = orderbuyService.strBerryAvail(userDomain.getEmail(), userDomain.getIdfg());
		modelAndView.addObject("userBerry",userBerry);
		
		// 사용자 총 블루베리 보유량
		String userBlueBerry = orderbuyService.strBlueBerryAvail(userDomain.getEmail(), userDomain.getIdfg());
		modelAndView.addObject("userBlueBerry",userBlueBerry);
		
		// 블루멤버십 가입 여부 조회
		String blueMembershipyn = premiumService.getBlueMembershipInfo(userDomain.getEmail(), userDomain.getIdfg());
		modelAndView.addObject("blueMembershipyn", blueMembershipyn);

		modelAndView.addObject("orderDomain", null);
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * My toon 내문의 내역 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value="/m/mytoon/qnaList.kt")
	public ModelAndView qnaList(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크
		if(!AuthUtil.isLogin( request )){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		//앱 로그인 동기화
		loginSync(request);
		
		modelAndView.setViewName("mobile/mytoon/qnaList");
		
		return modelAndView;
	}
	
	/**
	 * My toon 내문의 내역 처리하는 RequestMapping메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value="/m/mytoon/qnaJsonList.kt")
	public ModelAndView qnaJsonList(QnaDomain qnaDomain, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 여부 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		//로그인 유저 정보
		qnaDomain.setRegid(userDomain.getEmail());
		qnaDomain.setIdfg( userDomain.getIdfg());
		
		//페이지 사이즈 기본값
		if(qnaDomain.getPageSize() == 0) qnaDomain.setPageSize(5);
		
		//ModelAndView 객체에 조회한 전체 건수
		int totalCnt = qnaService.qnaListCnt(qnaDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//QNA 목록 조회
		List<QnaDomain>list = qnaService.qnaList(qnaDomain);
		modelAndView.addObject("qnaList",list);

		modelAndView.addObject("qnaDomain",null);
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