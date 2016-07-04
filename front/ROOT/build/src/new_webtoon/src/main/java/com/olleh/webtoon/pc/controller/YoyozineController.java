/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰 
 * SUBSYSTEM NAME : 사용자 > YOYOZINE
 * FILE NAME      : YoyozineController.java
 * DESCRIPTION    : 요요진   Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0          				        2014-04-21      init
 *****************************************************************************/
package com.olleh.webtoon.pc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.user.domain.NameconDomain;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineDomain;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineImageDomain;
import com.olleh.webtoon.common.dao.yoyozine.service.iface.YoyozineService;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
public class YoyozineController {
	
	protected static Log logger = LogFactory.getLog(YoyozineController.class);
	
	@Autowired
	YoyozineService yoyozineService;
	
	@Autowired
	UserServiceIface userServiceIface;
	
	/**
	 * Yoyozine 리스트화면으로 이동 처리하는 RequestMapping메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/yoyozine/list.kt")
	public ModelAndView yoyozineList(HttpServletRequest req) {
//		
		ModelAndView modelAndView = new ModelAndView();		
		
		modelAndView.setViewName("redirect:/yoyozine/main.kt");
		
		return modelAndView;
		
//		int totalCount = 0;
//		List<YoyozineDomain> list = null;
//		
//		ModelAndView modelAndView = new ModelAndView();
//		YoyozineDomain yoyozineDomain = new YoyozineDomain();
//		
//		if(req.getParameter("startNo")==null || req.getParameter("startNo").equals(""))
//		{
//			yoyozineDomain.setStartRowNo(1); // jspPage로부터 받은 파라미터
//		}
//		else
//		{
//			yoyozineDomain.setStartRowNo(StringUtil.defaultInt( (String)req.getParameter("startNo"))); //jspPage로부터 받은 파라미터
//		}
//		
//		if(req.getParameter("keyword")==null || req.getParameter( "keyword").equals(""))
//		{
//			yoyozineDomain.setKeyword( null );
//		}
//		else
//		{
//			yoyozineDomain.setKeyword( "%"+req.getParameter( "keyword")+"%");
//		}
//		
//		if(req.getParameter("categoryfg")==null || req.getParameter( "categoryfg").equals(""))
//		{
//			yoyozineDomain.setCategoryfg( null );
//		}
//		else
//		{
//			yoyozineDomain.setCategoryfg( req.getParameter( "categoryfg") );
//		}
//		
//		//페이징 설정
//		int startRowNo = yoyozineDomain.getStartRowNo();
//		PagingUtil paging = new PagingUtil();
//		paging.setPAGESIZE(PAGESIZE);
//		yoyozineDomain.setStartRowNo(paging.getStartNo(startRowNo));
//		yoyozineDomain.setPageSize(PAGESIZE);
//
//		
//		//yoyozine 리스트총갯수, 리스트 조회 
//		totalCount = yoyozineService.yoyozineListCnt(yoyozineDomain);
//		list             = yoyozineService.yoyozineList(yoyozineDomain);
//		
//		modelAndView.addObject("totalCount", totalCount);
//		modelAndView.addObject("yoyozineList", list);
//		modelAndView.addObject( "startRowNo",startRowNo);
//		modelAndView.addObject("pageSize",PAGESIZE);
//		modelAndView.addObject("lastRowNo",paging.getEndNo( startRowNo, totalCount));
//		modelAndView.addObject( "totalPageSize",paging.getTotalPageSize(totalCount));
//		modelAndView.addObject( "maxBlockSize",MAX_BLOCK_SIZE);
//		
//		modelAndView.setViewName("pc/yoyozine/list");
//		return modelAndView;
	}
	
	/**
	 * Yoyozine 상세화면으로 이동 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView  ModelAndView 객체
	 */
	@RequestMapping(value="/yoyozine/detail.kt")
	public ModelAndView yoyozineDetail(@ModelAttribute("yoyozineDomain") YoyozineDomain yoyozine, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		//모바일 기기로 접속시 모바일 로그인 페이지로
		if(RequestUtil.isMobile(request)){
			String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/yoyozine/yoyozineDetail.kt" + urlcd);
			return modelAndView;
		}
				
		// 유저정보
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
				
		userDomain.setEmail(userDomain.getEmail());
		userDomain.setIdfg(userDomain.getIdfg());
		
		
		if(userDomain.getPageSize() == 0) {
			userDomain.setPageSize(999999);
		}
				
		//yoyozine 관련 작품 리스트
		List<ToonDomain> refwebtoonList = yoyozineService.refwebtoonList(yoyozine);
		modelAndView.addObject("refwebtoonList", refwebtoonList);
		
		//yoyozine 상세조회
		YoyozineDomain yoyozineDomain = yoyozineService.yoyozineDetail(yoyozine);
		modelAndView.addObject( "yoyozineDomain",yoyozineDomain);
		
		//yoyozine 이미지 리스트
		List<YoyozineImageDomain> imageList = yoyozineService.yoyozineImageList(yoyozine.getYoyozineseq());
		modelAndView.addObject("imageList", imageList);
		
		//네임콘 리스트
		List<NameconDomain> defualtNameconList = userServiceIface.defualtNameconList(userDomain);		
		modelAndView.addObject("defualtNameconList", defualtNameconList);
		
		List<NameconDomain> freeNameconList = userServiceIface.freeNameconList(userDomain);
		modelAndView.addObject("freeNameconList", freeNameconList);
		
		List<NameconDomain> payNameconList = userServiceIface.payNameconList(userDomain);
		modelAndView.addObject("payNameconList", payNameconList);
		
		List<NameconDomain> bmNameconList = userServiceIface.bmNameconList( userDomain );
		modelAndView.addObject( "bmNameconList", bmNameconList );

		modelAndView.setViewName("pc/yoyozine/detail");
		
		return modelAndView;
	}
	
	/**
	 * Yoyozine메인으로 이동 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView ModelAndView 객체
	 */
	
	@RequestMapping(value="/yoyozine/main.kt")
	public ModelAndView yoyozineMain(@ModelAttribute("yoyozineDomain") YoyozineDomain yoyozineDomain){
		
		ModelAndView modelAndView = new ModelAndView();
		
		//페이지 사이즈 기본값
		if(yoyozineDomain.getPageSize() == 0) yoyozineDomain.setPageSize(10);
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = yoyozineService.yoyozineListCnt(yoyozineDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//yoyozine 리스트총갯수, 리스트 조회
		List<YoyozineDomain> list = yoyozineService.yoyozineList(yoyozineDomain);
		modelAndView.addObject( "yoyozineList",list);		
		
		/*//yoyozine 상단 배너 갯수
		List<YoyozineDomain> bannerList =yoyozineService.bannerList(yoyozineDomain);
		modelAndView.addObject("bannerList",bannerList);*/

		modelAndView.setViewName( "pc/yoyozine/main");
		
		return modelAndView;		
	}
}