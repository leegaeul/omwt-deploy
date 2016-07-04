/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰 
 * SUBSYSTEM NAME : 사용자(모바일)
 * FILE NAME      : YoyozineController.java
 * DESCRIPTION    : 요요진   Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0          				        2014-04-29      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

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

@Controller("MobileYoyozineController")
public class YoyozineController
{
	protected static Log logger	= LogFactory.getLog( YoyozineController.class );

	@Autowired
	YoyozineService	yoyozineService;
	
	@Autowired
	UserServiceIface userServiceIface;

	/**
	 * Yoyozine 리스트화면으로 이동 처리하는 RequestMapping메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView객체
	 */
	@RequestMapping( value = "/m/yoyozine/list.kt" )
	public ModelAndView yoyozineList( )
	{
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName( "mobile/yoyozine/list" );
		
		return modelAndView;
	}

	/**
	 * yoyozine 리스트 조회 후
	 * 결과 json을 보내주는 RequestMapping메소드
	 * 
	 * @return ModelAndView modelAndView = ModelAndView 객체
	 */
	@RequestMapping( value = "/m/yoyozine/yoyozineJsonList.kt" )
	public ModelAndView yoyozineJsonList(@ModelAttribute("yoyozineDomain") YoyozineDomain yoyozineDomain) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();

		int totalCnt = yoyozineService.yoyozineListCnt(yoyozineDomain);
		modelAndView.addObject( "totalCnt", totalCnt );
		
		//페이지 사이즈 기본값
		if(yoyozineDomain.getPageSize() == 0) yoyozineDomain.setPageSize(10);

		//요요진 리스트
		List<YoyozineDomain> list = yoyozineService.yoyozineList( yoyozineDomain );
		modelAndView.addObject( "yoyozineList", list );
		
		modelAndView.setViewName("mappingJacksonJsonView");
		return modelAndView;
	}
	
	/**
	 *  yoyozine  상세 화면으로 이동 처리하는 RequestMapping메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/yoyozine/yoyozineDetail.kt")
	public ModelAndView yoyozineDetail(@ModelAttribute("yoyozineDomain") YoyozineDomain yoyozine,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//모바일 기기가 아닐경우 pc페이지로
		if(!RequestUtil.isMobile(request)){
			String urlcd = StringUtil.defaultStr(request.getQueryString(), "");
			
			if(urlcd != null && !"".equals(urlcd)) urlcd = "?" + urlcd;
			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/yoyozine/detail.kt" + urlcd);
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
		
		//요요진 기본 정보 조회
		YoyozineDomain yoyozineDomain = yoyozineService.simpleYoyozineDetail(yoyozine);
		modelAndView.addObject( "yoyozineDomain",yoyozineDomain);
		
		modelAndView.setViewName("mobile/yoyozine/detail");  
		
		return modelAndView;
	}
	
	/**
	 *  yoyozine  리스트 조회 후 상세 image 보여줌
	 *  결과 json을 보내주는 RequestMapping메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/yoyozine/yoyozineJsonDetail.kt")
	public ModelAndView yoyozineJsonDetail(@ModelAttribute("yoyozineDomain") YoyozineDomain yoyozine) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//모바일 정보 셋팅
		yoyozine.setMobileyn("Y");
		
		//yoyozine 관련 작품 리스트
		List<ToonDomain> refwebtoonList = yoyozineService.refwebtoonList(yoyozine);
		modelAndView.addObject("refwebtoonList", refwebtoonList);
		
		//요요진 상세 조회
		YoyozineDomain yoyozineDomain = yoyozineService.yoyozineDetail(yoyozine);
		modelAndView.addObject( "yoyozineDomain",yoyozineDomain);
		
		//요요진 이미지 리스트
		List<YoyozineImageDomain> list = yoyozineService.yoyozineImageList(yoyozine.getYoyozineseq());
		modelAndView.addObject( "yoyozineImageList",list);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * yoyozine 메인 화면으로 이동 처리하는 RequestMapping메소드
	 * @ return ModelAndView modelAndView :  ModelAndView 객체
	 * 
	 */
	@RequestMapping(value = "/m/yoyozine/main.kt")
	public ModelAndView yoyozineMain(){
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/yoyozine/main");
		return modelAndView;
		
	}
	
	/**
	 * yoyozine 메인 리스트 조회 후
	 * 결과 json을 보내주는 RequestMapping
	 * @return ModelAndView modelAndView : ModelAndView객체
	 */
	
	@RequestMapping(value = "/m/yoyozine/mainJsonList.kt")
	public ModelAndView yoyozineMainJsonList( HttpServletRequest req )
	{
		ModelAndView modelAndView = new ModelAndView();

		try
		{
			modelAndView.addObject( "erroryn", "N" );

			YoyozineDomain yoyozineDomain = new YoyozineDomain();
			yoyozineDomain.setStartRowNo( StringUtil.defaultInt( req.getParameter( "startRowNo" ), 0 ) );
			yoyozineDomain.setPageSize( StringUtil.defaultInt( req.getParameter( "pageSize" ), 5 ) );
			
			int totalCount = yoyozineService.yoyozineListCnt(yoyozineDomain);
			modelAndView.addObject( "totalCount", totalCount );
			
			if( yoyozineDomain.getStartRowNo() == 0 )
			{
				yoyozineDomain.setPageSize(1);
			}

			modelAndView.addObject( "startRowNo", yoyozineDomain.getStartRowNo() );
			modelAndView.addObject( "pageSize", yoyozineDomain.getPageSize() );
			
			//모바일 메인 리스트 yoyo진 관련 리스트를 각 카테고리별 하나씩 뽑아옴
			List<YoyozineDomain> list = yoyozineService.moYoyozineMainList();//컨트롤단 메소드 수정, 파람값 삭제
			modelAndView.addObject( "yoyozineList", list );
		} catch( Exception e )
		{
			logger.error( e );

			modelAndView.clear();
			modelAndView.addObject( "erroryn", "Y" );
			modelAndView.addObject( "errormsg", MessageUtil.getSystemMessage( "message.error.select" ) );
		}

		modelAndView.setViewName("mappingJacksonJsonView");
		return modelAndView;
	}		
}