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

import com.olleh.webtoon.common.dao.applay.domain.LinkSmsDomain;
import com.olleh.webtoon.common.dao.applay.service.iface.RecommendAppService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMService;
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
import com.olleh.webtoon.common.dao.user.domain.NameconDomain;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.SmsSendUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
public class MytoonController
{
	protected static Log	logger	= LogFactory.getLog( MytoonController.class );

	@Autowired
	ToonService				toonService;

	@Autowired
	QNAService				qnaService;

	@Autowired
	PremiumService			premiumService;

	@Autowired
	OrderbuyService			orderbuyService;

	@Autowired
	UserServiceIface		userServiceIface;

	@Autowired
	RecommendAppService		recommendAppService;

	@Autowired
	BMService				bmService;
	


	/**
	 * 최근 본 Toon 작화 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/recentToon.kt" )
	public ModelAndView recentToon( @RequestParam Map<String, Object> param, HttpServletRequest request ) throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		// 회원정보 복호화
		UserDomain userDomain = AuthUtil.getDecryptUserInfo( request );

		String email = userDomain.getEmail().substring( 0, 2 )
				+ userDomain.getEmail().substring( userDomain.getEmail().length() - 2, userDomain.getEmail().length() );
		String cookieName = "timesseq" + email + "list";      // 쿠키 변수명
		String regdtCookieName = "regdt" + email + "list";      // 쿠키 등록일 변수명

		// 쿠키에서 최근 본 회차 목록 가져온다.
		String timesseqList = StringUtil.defaultStr( CookieUtil.getCookie( request, cookieName ), "" );
		String regdtList = StringUtil.defaultStr( CookieUtil.getCookie( request, regdtCookieName ), "" );

		// 20개가 넘을경우
		int arraySize = 20;
		String[] timesseqArray = timesseqList.split( "," );
		String[] regdtArray = regdtList.split( "," );
		String[] tmpTimesseqArray = new String[arraySize];
		String[] tmpRegdtArray = new String[arraySize];

		if( timesseqArray.length > arraySize )
		{
			timesseqList = "";
			regdtList = "";
			int itv = timesseqArray.length - arraySize;
			for( int i = 0; i < arraySize; i++ )
			{
				tmpTimesseqArray[i] = timesseqArray[i + itv];
				tmpRegdtArray[i] = regdtArray[i + itv];

				timesseqList = timesseqList + timesseqArray[i + itv];

				if( i != arraySize - 1 ) timesseqList = timesseqList + ",";
			}
		}
		else
		{
			tmpTimesseqArray = timesseqArray;
			tmpRegdtArray = regdtArray;
		}

		param.put( "timesseqList", timesseqList );
		List<ToonDomain> tmpList = new ArrayList<ToonDomain>();

		// 웹툰 목록을 가져와서 최신순으로 정렬
		if( !"".equals( timesseqList ) )
		{
			// 웹툰 목록
			List<ToonDomain> list = toonService.recentList( param );

			for( int i = ( tmpTimesseqArray.length - 1 ); i >= 0; i-- )
			{
				for( ToonDomain toon : list )
				{
					if( tmpTimesseqArray[i].equals( "" + toon.getTimesseq() ) )
					{
						toon.setRegdt( tmpRegdtArray[i].substring( 0, 4 ) + "." + tmpRegdtArray[i].substring( 4, 6 ) + "."
								+ tmpRegdtArray[i].substring( 6, 8 ) + " " + tmpRegdtArray[i].substring( 8, 10 ) + ":"
								+ tmpRegdtArray[i].substring( 10, 12 ) );

						tmpList.add( toon );
					}
				}
			}

		}

		modelAndView.addObject( "recentList", tmpList );

		modelAndView.setViewName( "pc/mytoon/recentToon" );

		return modelAndView;

	}


	/**
	 * 작품별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/recentToonDeleteJsonProc.kt" )
	public ModelAndView recentToonDeleteJsonProc( HttpServletRequest request, HttpServletResponse response ) throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName( "mappingJacksonJsonView" );

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		// 회원정보 복호화
		UserDomain userDomain = AuthUtil.getDecryptUserInfo( request );

		String email = userDomain.getEmail().substring( 0, 2 )
				+ userDomain.getEmail().substring( userDomain.getEmail().length() - 2, userDomain.getEmail().length() );
		String cookieName = "timesseq" + email + "list";      // 쿠키 변수명
		String regdtCookieName = "regdt" + email + "list";      // 쿠키 등록일 변수명

		String cookiePath = MessageUtil.getSystemMessage( "system.cookie.path" );      // 쿠키경로
		String cookieDomain = MessageUtil.getSystemMessage( "system.cookie.domain" );  // 쿠키 도메인
		int intCookieAge = 60 * 60 * 24 * 365;

		// 쿠키에서 최근 본 회차 목록 가져온다.
		String timesseqList = StringUtil.defaultStr( CookieUtil.getCookie( request, cookieName ), "" );
		List<String> timesseqListArrayList = new ArrayList<String>( Arrays.asList( timesseqList.split( "," ) ) );

		String regdtList = StringUtil.defaultStr( CookieUtil.getCookie( request, regdtCookieName ), "" );
		List<String> regdtListArrayList = new ArrayList<String>( Arrays.asList( regdtList.split( "," ) ) );

		// 파라메터 삭제 회차번호 목록
		String timesseqListParam = StringUtil.defaultStr( request.getParameter( "timesseqlist" ), "" );
		List<String> timesseqListParamArrayList = new ArrayList<String>( Arrays.asList( timesseqListParam.split( "," ) ) );

		// 쿠키 삭제 처리
		for( int i = 0; i < timesseqListParamArrayList.size(); i++ )
		{
			int index = timesseqListArrayList.indexOf( timesseqListParamArrayList.get( i ) );
			if( index > -1 )
			{
				timesseqListArrayList.remove( index );
				regdtListArrayList.remove( index );
			}
		}

		String saveTimesseqList = "";
		String saveRegdtList = "";
		for( int i = 0; i < timesseqListArrayList.size(); i++ )
		{
			if( i != ( timesseqListArrayList.size() - 1 ) )
			{
				saveTimesseqList += timesseqListArrayList.get( i ) + ",";
				saveRegdtList += regdtListArrayList.get( i ) + ",";
			}
			else
			{
				saveTimesseqList += timesseqListArrayList.get( i );
				saveRegdtList += regdtListArrayList.get( i );
			}
		}

		logger.debug( "saveTimesseqList ========>" + saveTimesseqList );
		logger.debug( "saveRegdtList ========>" + saveRegdtList );

		CookieUtil.setCookie( response, cookieName, saveTimesseqList, intCookieAge, cookiePath, cookieDomain );
		CookieUtil.setCookie( response, regdtCookieName, saveRegdtList, intCookieAge, cookiePath, cookieDomain );

		return modelAndView;
	}


	/**
	 * 즐겨찾기 Toon 작품 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/favoriteList.kt" )
	public ModelAndView favoriteList( FavoritesDomain favoritesDomain, HttpServletRequest request ) throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		UserDomain userDomain = AuthUtil.getDecryptUserInfo( request );

		// 로그인 유저 이메일 셋팅
		favoritesDomain.setRegid( userDomain.getEmail() );

		// 페이지 사이즈 기본값
		if( favoritesDomain.getPageSize() == 0 ) favoritesDomain.setPageSize( 10 );

		// ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = toonService.favoritesListCnt( favoritesDomain );
		modelAndView.addObject( "totalCnt", totalCnt );

		// 웹툰 목록
		List<ToonDomain> list = toonService.favoritesList( favoritesDomain );
		modelAndView.addObject( "favoritesList", list );

		modelAndView.addObject( "favoritesDomain", null );
		modelAndView.setViewName( "pc/mytoon/favoriteList" );

		return modelAndView;
	}


	/**
	 * 작품별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/favoriteDeleteJsonProc.kt" )
	public ModelAndView favoriteDeleteJsonProc( FavoritesDomain favoriteDomain, HttpServletRequest request ) throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName( "mappingJacksonJsonView" );

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		UserDomain userDomain = AuthUtil.getDecryptUserInfo( request );

		// 이메일 아이디
		favoriteDomain.setRegid( userDomain.getEmail() );

		String webtoonseqlist = favoriteDomain.getWebtoonseqlist();
		webtoonseqlist = "'" + webtoonseqlist.replaceAll( ",", "','" ) + "'";
		favoriteDomain.setWebtoonseqlist( webtoonseqlist );

		// 책갈피 삭제
		int deleteCnt = toonService.favoritesDelete( favoriteDomain );

		if( deleteCnt > 0 ) modelAndView.addObject( "erroryn", "N" );
		else modelAndView.addObject( "erroryn", "Y" );

		// ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = toonService.favoritesListCnt( favoriteDomain );
		modelAndView.addObject( "totalCnt", totalCnt );

		modelAndView.addObject( "favoriteDomain", null );

		return modelAndView;
	}


	/**
	 * 서비스 동의 여부를 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/favoriteUseTermsJsonProc.kt" )
	public ModelAndView favoriteUseTermsJsonProc( UserDomain userDomain, HttpServletRequest request ) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		String usetermsyn = userDomain.getUsetermsyn();
		userDomain = AuthUtil.getDecryptUserInfo( request );

		// 알림 받기 동의여부
		if( ( "1".equals( userDomain.getCheckno() ) || "5".equals( userDomain.getCheckno() ) ) && !"".equals( userDomain.getCheckno() ) )
		{
			userDomain.setUsetermsyn( usetermsyn );

			userServiceIface.pushtermsynUpdate( userDomain );
		}
		else
		{
			OllehUserDomain ollehUserDomain = new OllehUserDomain();
			ollehUserDomain.setOllehid( userDomain.getEmail() );
			ollehUserDomain.setUsetermsyn( usetermsyn );

			userServiceIface.ollehUserUsetermsynUpdateByOllehId( ollehUserDomain );
		}

		modelAndView.setViewName( "mappingJacksonJsonView" );

		modelAndView.addObject( "userDomain", null );

		return modelAndView;
	}


	/**
	 * 서비스 동의 여부를 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/favoritePushJsonProc.kt" )
	public ModelAndView favoritePushJsonProc( FavoritesDomain favoritesDomain, HttpServletRequest request ) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		UserDomain userCheck = AuthUtil.getDecryptUserInfo( request );
		UserDomain userDomain = null;

		// 알림 받기 동의여부
		if( ( "1".equals( userCheck.getCheckno() ) || "5".equals( userCheck.getCheckno() ) ) && !"".equals( userCheck.getCheckno() ) )
		{
			userDomain = userServiceIface.getUserUseTermsByEmail( userCheck.getEmail() );
		}
		else
		{
			OllehUserDomain ollehUserDomain = userServiceIface.ollehUserDetail( userCheck.getEmail() );

			userDomain = userCheck;
			userDomain.setUsetermsyn( ollehUserDomain.getUsetermsyn() );
			userDomain.setUseagreementdt( ollehUserDomain.getUseagreementdt() );
		}

		if( userDomain.getUseagreementdt() != null && !"".equals( userDomain.getUseagreementdt() ) )
		{
			favoritesDomain.setIdfg( userCheck.getIdfg() );
			favoritesDomain.setRegid( userCheck.getEmail() );

			toonService.favoritesPushRegist( favoritesDomain );
			userDomain.setUsetermsyn( "Y" );
		}
		else
		{
			userDomain.setUsetermsyn( "N" );
		}

		modelAndView.addObject( "usetermsyn", userDomain.getUsetermsyn() );

		modelAndView.setViewName( "mappingJacksonJsonView" );

		modelAndView.addObject( "favoritesDomain", null );

		return modelAndView;
	}


	/**
	 * 책갈피 Toon 작품 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/bookmarkList.kt" )
	public ModelAndView bookmarkList( BookmarkDomain bookmarkDomain, HttpServletRequest request ) throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		UserDomain userDomain = AuthUtil.getDecryptUserInfo( request );

		// 로그인 유저 이메일 셋팅
		bookmarkDomain.setRegid( userDomain.getEmail() );

		// 페이지 사이즈 기본값
		if( bookmarkDomain.getPageSize() == 0 ) bookmarkDomain.setPageSize( 10 );

		// ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = toonService.bookmarkListCnt( bookmarkDomain );
		modelAndView.addObject( "totalCnt", totalCnt );

		// 웹툰 목록
		List<ToonDomain> list = toonService.bookmarkList( bookmarkDomain );
		modelAndView.addObject( "bookmarkList", list );

		modelAndView.addObject( "bookmarkDomain", null );
		modelAndView.setViewName( "pc/mytoon/bookmarkList" );

		return modelAndView;

	}


	/**
	 * 작품별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/bookmarkDeleteJsonProc.kt" )
	public ModelAndView bookmarkDeleteJsonProc( BookmarkDomain bookmarkDomain, HttpServletRequest request ) throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName( "mappingJacksonJsonView" );

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		UserDomain userDomain = AuthUtil.getDecryptUserInfo( request );

		// 이메일 아이디
		bookmarkDomain.setRegid( userDomain.getEmail() );

		String timesseqlist = bookmarkDomain.getTimesseqlist();
		timesseqlist = "'" + timesseqlist.replaceAll( ",", "','" ) + "'";
		bookmarkDomain.setTimesseqlist( timesseqlist );

		// 책갈피 삭제
		int deleteCnt = toonService.bookmarkDelete( bookmarkDomain );

		if( deleteCnt > 0 ) modelAndView.addObject( "erroryn", "N" );
		else modelAndView.addObject( "erroryn", "Y" );

		// ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = toonService.bookmarkListCnt( bookmarkDomain );
		modelAndView.addObject( "totalCnt", totalCnt );

		modelAndView.addObject( "bookmarkDomain", null );

		return modelAndView;
	}


	/**
	 * 회원 상세정보 화면 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/userDetail.kt" )
	public ModelAndView userDetail( HttpServletRequest request, HttpServletResponse response ) throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();
		// 유저정보
		UserDomain oldUserDomain = AuthUtil.getDecryptUserInfo( request );
		
		oldUserDomain.setEmail( oldUserDomain.getEmail() );
		oldUserDomain.setIdfg( oldUserDomain.getIdfg() );

		if( oldUserDomain.getPageSize() == 0 )
		{
			oldUserDomain.setPageSize( 999999 );
		}

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		List<NameconDomain> defualtNameconList = userServiceIface.defualtNameconList( oldUserDomain );
		modelAndView.addObject( "defualtNameconList", defualtNameconList );
		
		List<NameconDomain> freeNameconList = userServiceIface.freeNameconList( oldUserDomain );
		modelAndView.addObject( "freeNameconList", freeNameconList );

		List<NameconDomain> payNameconList = userServiceIface.payNameconList( oldUserDomain );
		modelAndView.addObject( "payNameconList", payNameconList );
		
		List<NameconDomain> bmNameconList = userServiceIface.bmNameconList( oldUserDomain );
		modelAndView.addObject( "bmNameconList", bmNameconList );
		
		modelAndView.setViewName( "pc/mytoon/userDetail" );

		// 회원 정보 가져오기

		// 쿠키에 저장되어있는 네임콘 정보와 DB에서 회원이 보유한 네임콘 정보가 다를경우 쿠키값에 다시 셋팅
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
	 * 내문의 내역 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/qnaList.kt" )
	public ModelAndView qnaList( @ModelAttribute( "qnaDomain" ) QnaDomain qnaDomain, HttpServletRequest request ) throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		UserDomain userDomain = AuthUtil.getDecryptUserInfo( request );

		// 로그인 유저 정보
		qnaDomain.setRegid( userDomain.getEmail() );
		qnaDomain.setIdfg( userDomain.getIdfg() );

		// 페이지 사이즈 기본값
		if( qnaDomain.getPageSize() == 0 ) qnaDomain.setPageSize( 10 );

		// ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = qnaService.qnaListCnt( qnaDomain );
		modelAndView.addObject( "totalCnt", totalCnt );

		// Q&A 목록을 조회한다.
		List<QnaDomain> list = qnaService.qnaList( qnaDomain );
		modelAndView.addObject( "qnaList", list );

		modelAndView.addObject( "qnaDomain", null );
		modelAndView.setViewName( "pc/mytoon/qnaList" );

		return modelAndView;
	}


	/**
	 * 베리사용내역(구매내역) 목록 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/berryuseList.kt" )
	public ModelAndView berryuseList( @RequestParam Map<String, Object> param, BuyDomain buyDomain, HttpServletRequest request ) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();

		// 정렬
		String initialword = (String)param.get( "initialWord" );
		if( initialword == null || initialword == "" ) initialword = "date";
		buyDomain.setInitialword( initialword );

		String sortfg = (String)param.get( "sort" );
		if( sortfg == null || sortfg == "" ) sortfg = "desc";
		buyDomain.setSort( sortfg );

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		UserDomain userDomain = AuthUtil.getDecryptUserInfo( request );

		// 로그인 유저 이메일 셋팅
		buyDomain.setBuyid( userDomain.getEmail() );

		if( userDomain.getIdfg().equals( "open" ) ) buyDomain.setIdfg( "open" );
		else buyDomain.setIdfg( "olleh" );

		// 페이지 사이즈 기본값
		if( buyDomain.getPageSize() == 0 ) buyDomain.setPageSize( 10 );

		// ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = orderbuyService.buyListCnt( buyDomain );
		modelAndView.addObject( "totalCnt", totalCnt );

		// 사용자 총 베리 보유량
		String userBerry = orderbuyService.strBerryAvail( userDomain.getEmail(), userDomain.getIdfg() );
		modelAndView.addObject( "userBerry", userBerry );

		// 사용자 총 블루베리 보유량
		String userBlueBerry = orderbuyService.strBlueBerryAvail( userDomain.getEmail(), userDomain.getIdfg() );
		modelAndView.addObject( "userBlueBerry", userBlueBerry );

		// 블루멤버십 가입 여부 조회
		String blueMembershipyn = premiumService.getBlueMembershipInfo( userDomain.getEmail(), userDomain.getIdfg() );
		modelAndView.addObject( "blueMembershipyn", blueMembershipyn );

		// 구매 목록
		List<BuyDomain> list = orderbuyService.berryuseList( buyDomain );
		modelAndView.addObject( "berryuseList", list );

		modelAndView.addObject( "buyDomain", null );
		modelAndView.setViewName( "pc/mytoon/berryuseList" );

		return modelAndView;
	}


	/**
	 * 베리충전내역(구매내역) 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/berrychargeList.kt" )
	public ModelAndView berrychargeList( @RequestParam Map<String, Object> param, OrderDomain orderDomain, HttpServletRequest request )
			throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();

		// 정렬
		String sortfg = (String)param.get( "sort" );
		if( sortfg == null || sortfg == "" ) sortfg = "desc";
		orderDomain.setSort( sortfg );

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.setViewName( "redirect:/login.kt" );
			return modelAndView;
		}

		UserDomain userDomain = AuthUtil.getDecryptUserInfo( request );

		// 로그인 유저 이메일 셋팅
		orderDomain.setOrderid( userDomain.getEmail() );

		if( userDomain.getIdfg().equals( "open" ) ) orderDomain.setIdfg( "open" );
		else orderDomain.setIdfg( "olleh" );

		// 페이지 사이즈 기본값
		if( orderDomain.getPageSize() == 0 ) orderDomain.setPageSize( 10 );

		// ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = orderbuyService.orderListCnt( orderDomain );
		modelAndView.addObject( "totalCnt", totalCnt );

		// 사용자 총 베리 보유량
		String userBerry = orderbuyService.strBerryAvail( userDomain.getEmail(), userDomain.getIdfg() );
		modelAndView.addObject( "userBerry", userBerry );

		// 사용자 총 블루베리 보유량
		String userBlueBerry = orderbuyService.strBlueBerryAvail( userDomain.getEmail(), userDomain.getIdfg() );
		modelAndView.addObject( "userBlueBerry", userBlueBerry );

		// 블루멤버십 가입 여부 조회
		// String blueMembershipyn = premiumService.getBlueMembershipInfo(userDomain.getEmail(), userDomain.getIdfg());
		// modelAndView.addObject("blueMembershipyn", blueMembershipyn);

		// 베리 충전 목록
		List<OrderDomain> list = orderbuyService.berrychargeList( orderDomain );
		modelAndView.addObject( "berrychargeList", list );

		modelAndView.addObject( "orderDomain", null );
		modelAndView.setViewName( "pc/mytoon/berrychargeList" );

		return modelAndView;
	}


	/**
	 * 올레APP 설치 URL을 SMS로 보내주는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/mytoon/sendLinkSms.kt" )
	public ModelAndView sendLinkSms( LinkSmsDomain linkSmsDomain, HttpServletRequest request ) throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName( "mappingJacksonJsonView" );

		// 로그인 여부 체크
		if( !AuthUtil.isLogin( request ) )
		{
			modelAndView.addObject( "erroryn", "Y" );
			modelAndView.addObject( "errormsg", "로그인 후 이용해주세요." );
			return modelAndView;
		}

		// 회원정보 복호화
		UserDomain userDomain = AuthUtil.getDecryptUserInfo( request );

		modelAndView.addObject( "erroryn", "N" );

		// 아이디 구분
		linkSmsDomain.setIdfg( userDomain.getIdfg() );
		// 이메일 아이디
		linkSmsDomain.setRegid( userDomain.getEmail() );
		// 현재날짜시간
		linkSmsDomain.setRegdt( DateUtil.getNowDate( 1 ) );
		// 마켓url받기(구분자 = 0)
		linkSmsDomain.setAppseq( 0 );
		linkSmsDomain.setMarketyn( "Y" );

		// 월별 10건 체크 후 저장
		int linkSmsCnt = recommendAppService.linkSmsCnt( linkSmsDomain );

		if( linkSmsCnt < 10 )
		{
			// SMS 전송
			if( SmsSendUtil.sendSmsResult( linkSmsDomain.getDestphone(), "", "[올레마켓]" + linkSmsDomain.getMsg() ) )
			{

				// SMS 전송 성공 시 DB에 저장
				recommendAppService.linkSmsRegist( linkSmsDomain );
			}
		}
		else
		{
			modelAndView.addObject( "linkSmsCnt", linkSmsCnt );
		}

		modelAndView.addObject( "linkSmsDomain", null );

		return modelAndView;
	}
	
}







