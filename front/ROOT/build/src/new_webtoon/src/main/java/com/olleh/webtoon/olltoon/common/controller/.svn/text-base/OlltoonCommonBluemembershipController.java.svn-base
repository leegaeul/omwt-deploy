package com.olleh.webtoon.olltoon.common.controller;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.constant.AppConstant;
import com.olleh.webtoon.common.dao.bluemembership.code.BillMethodCode;
import com.olleh.webtoon.common.dao.bluemembership.code.ChangeRequestCode;
import com.olleh.webtoon.common.dao.bluemembership.code.JoinStatusCode;
import com.olleh.webtoon.common.dao.bluemembership.code.PrdCode;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinTermDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMOrderDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPaymentDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMSummaryModel;
import com.olleh.webtoon.common.dao.bluemembership.domain.INIBillKeyRegisterForm;
import com.olleh.webtoon.common.dao.bluemembership.domain.INIBillKeyRegisterResultForm;
import com.olleh.webtoon.common.dao.bluemembership.domain.MobiBillkeyRegisterForm;
import com.olleh.webtoon.common.dao.bluemembership.domain.MobiBillkeyRegisterResultForm;
import com.olleh.webtoon.common.dao.bluemembership.service.BMOrderService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMPrdService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMTermService;
import com.olleh.webtoon.common.dao.bluemembership.service.InicisService;
import com.olleh.webtoon.common.dao.bluemembership.service.MobiService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.PremiumUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.olltoon.common.util.OlltoonAuthUtil;

@Controller
public class OlltoonCommonBluemembershipController
{
	private Log							logger	= LogFactory.getLog( OlltoonCommonBluemembershipController.class );

	@Inject
	private Provider<BMSummaryModel>	summaryProvider;

	@Autowired
	private BMService					bmService;

	@Autowired
	private InicisService				inicisService;

	@Autowired
	private MobiService					mobiService;

	@Autowired
	private BMPrdService				prdService;

	@Autowired
	private BMTermService				termService;

	@Autowired
	private BMOrderService				orderService;

	@Autowired
	private HttpServletRequest			request;

	@Value( "${serverType}" )
	private String						serverType;

	@Value( "${system.pc.domain.ssl}" )
	private String						SYSTEM_PC_DOMAIN_SSL;

	@Value( "${system.pc.domain}" )
	private String						SYSTEM_PC_DOMAIN;

	@Value( "${system.mobile.domain.ssl}" )
	private String						SYSTEM_MOBILE_DOMAIN_SSL;

	@Value( "${system.mobile.domain}" )
	private String						SYSTEM_MOBILE_DOMAIN;


	/**
	 * 멤버십 변경 취소
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/ot/bluemembership/cancelChange.kt" )
	public ModelAndView cancelChange( Integer joinseq ) throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName( AppConstant.JSON_VIEW_NAME );

		// 로그인 여부 체크
		if( OlltoonAuthUtil.ifNotLoginThenRedirect( request, mv, false ) )
			return mv;

		BMJoinDomain reserved = bmService.getJoinByJoinseq( joinseq );

		if( reserved == null || reserved.getJoinstatusfg() != JoinStatusCode.reserve )
			throw new Exception( "변경 예약된 이력이 없습니다" );

		try
		{
			bmService.cancelChangeReservation( reserved );
		} catch( Exception e )
		{
			logger.error( e.getMessage() );
			logger.error( ExceptionUtils.getStackTrace( e ) );
			
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", e.getMessage() );

			return mv;
		}

		mv.addObject( "erroryn", "N" );

		return mv;
	}


	/**
	 * 해지 신청
	 * 
	 * @param joinseq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/ot/bluemembership/reserveCancelJoin.kt" )
	public ModelAndView reserveCancelJoin( Integer joinseq ) throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName( AppConstant.JSON_VIEW_NAME );

		// 로그인 여부 체크
		if( OlltoonAuthUtil.ifNotLoginThenRedirect( request, mv, false ) )
			return mv;

		BMJoinDomain active = bmService.getJoinByJoinseq( joinseq );

		if( active == null || active.getJoinstatusfg() != JoinStatusCode.active )
			throw new Exception( "시스템 오류 입니다" );

		try
		{
			bmService.reserveCancelJoin( active );
		} catch( Exception e )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", e.getMessage() );

			return mv;
		}
		mv.addObject( "erroryn", "N" );

		return mv;
	}


	/**
	 * 해지 신청 취소
	 * 
	 * @param joinseq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/ot/bluemembership/cancelCancelJoinReservation.kt" )
	public ModelAndView cancelCancelJoinReservation( Integer joinseq ) throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName( AppConstant.JSON_VIEW_NAME );

		// 로그인 여부 체크
		if( OlltoonAuthUtil.ifNotLoginThenRedirect( request, mv, false ) )
			return mv;

		BMJoinDomain active = bmService.getJoinByJoinseq( joinseq );

		if( active == null || active.getJoinstatusfg() != JoinStatusCode.active )
			throw new Exception( "시스템 오류 입니다" );

		try
		{
			bmService.cancelCancelJoinReservation( active );
		} catch( Exception e )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", e.getMessage() );

			return mv;
		}
		mv.addObject( "erroryn", "N" );

		return mv;
	}


	/**
	 * 블루멤버십 가입 화면에서 pg사에 넘길 데이터를 생성하기 위해 호출하는 ajax api
	 * 
	 * @return
	 */
	@RequestMapping( value = "/ot/bluemembership/createOrder.kt" )
	public ModelAndView createOrder( 
			@RequestParam( value = "isMobile", required = true, defaultValue = "true" ) boolean isMobile,
			@RequestParam( value = "prdcode", required = true ) PrdCode prdcode,
			@RequestParam( value = "buyertel", required = false ) String buyertel,
			@RequestParam( value = "buyeremail", required = false ) String buyeremail,
			@RequestParam( value = "billmethodfg", required = true ) BillMethodCode billmethodfg ) throws Exception
	{
		ModelAndView mv = new ModelAndView();

		mv.setViewName( AppConstant.JSON_VIEW_NAME );
		mv.addObject( "erroryn", "N" );

		String fullUrl = RequestUtil.getFullURL( request );
		if( fullUrl.startsWith( "http:" ) && serverType.equals( "real" ) )
		{
			mv.setViewName( "redirect:" + fullUrl.replaceFirst( "http:", "https:" ) );
			return mv;
		}

		if( !OlltoonAuthUtil.isLogin( request ) )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "로그인이 필요한 요청입니다" );

			return mv;
		}

		UserDomain user = OlltoonAuthUtil.getDecryptUserInfo( request );

		BMJoinDomain active = bmService.getActiveJoinForUser( user.getIdfg(), user.getEmail() );

		// 휴대폰 결제로 결제수단을 변경하는것은 불가
		if( active != null && billmethodfg == BillMethodCode.phone )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "결제수단변경은 카드결제로만 가능합니다" );

			return mv;
		}

		if( active == null && ( StringUtils.isEmpty( buyertel ) || StringUtils.isEmpty( buyeremail ) ) )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "구매자 전화번호 혹은 구매자 이메일이 입력되지 않았습니다" );

			return mv;
		}

		// 결제 대기 상태의 가입 데이터를 만든다
		BMJoinDomain join = bmService.otInitJoinDataBeforePayment( prdcode, billmethodfg, buyertel, buyeremail );
		
		join.setOtcheck("ot");
		
		// 카드결제
		if( billmethodfg == BillMethodCode.card )
		{
			INIBillKeyRegisterForm form = inicisService.createBillKeyRegisterForm( join, isMobile );
			mv.addObject( "form", form );
		}
		// 휴대폰 결제
		else if( billmethodfg == BillMethodCode.phone )
		{
			MobiBillkeyRegisterForm form = mobiService.createBillKeyRegisterForm( join, isMobile );
			mv.addObject( "form", form );
		}
		else
		{
			throw new Exception( "지원되지 않는 결제수단입니다" );
		}

		mv.addObject( "SYSTEM_PC_DOMAIN_SSL", SYSTEM_PC_DOMAIN_SSL );
		mv.addObject( "SYSTEM_MOBILE_DOMAIN_SSL", SYSTEM_MOBILE_DOMAIN_SSL );
		mv.addObject( "SYSTEM_PC_DOMAIN", SYSTEM_PC_DOMAIN );
		mv.addObject( "SYSTEM_MOBILE_DOMAIN", SYSTEM_MOBILE_DOMAIN );

		return mv;
	}
	
	
	/**
	 * 블루멤버십 가입 화면에서 pg사에 넘길 데이터를 생성하기 위해 호출하는 ajax api
	 * 
	 * @return
	 */
	@RequestMapping( value = "/ot/bluemembership/createInicisIEParam.kt" )
	public ModelAndView createInicisIEParam( 
			@RequestParam( value = "prdcode", required = true ) PrdCode prdcode,
			@RequestParam( value = "buyertel", required = false ) String buyertel,
			@RequestParam( value = "buyeremail", required = false ) String buyeremail ) throws Exception
	{
		ModelAndView mv = new ModelAndView();

		mv.setViewName( AppConstant.JSON_VIEW_NAME );
		mv.addObject( "erroryn", "N" );

		String fullUrl = RequestUtil.getFullURL( request );
		if( fullUrl.startsWith( "http:" ) && serverType.equals( "real" ) )
		{
			mv.setViewName( "redirect:" + fullUrl.replaceFirst( "http:", "https:" ) );
			return mv;
		}

		if( !OlltoonAuthUtil.isLogin( request ) )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "로그인이 필요한 요청입니다" );

			return mv;
		}

		UserDomain user = OlltoonAuthUtil.getDecryptUserInfo( request );

		BMJoinDomain active = bmService.getActiveJoinForUser( user.getIdfg(), user.getEmail() );

		if( active == null && ( StringUtils.isEmpty( buyertel ) || StringUtils.isEmpty( buyeremail ) ) )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "구매자 전화번호 혹은 구매자 이메일이 입력되지 않았습니다" );

			return mv;
		}

		INIBillKeyRegisterForm form = inicisService.createBillKeyRegisterFormIE( prdcode, buyertel, buyeremail );
		
		mv.addObject( "form", form ); 

		mv.addObject( "SYSTEM_PC_DOMAIN_SSL", SYSTEM_PC_DOMAIN_SSL );
		mv.addObject( "SYSTEM_MOBILE_DOMAIN_SSL", SYSTEM_MOBILE_DOMAIN_SSL );
		mv.addObject( "SYSTEM_PC_DOMAIN", SYSTEM_PC_DOMAIN );
		mv.addObject( "SYSTEM_MOBILE_DOMAIN", SYSTEM_MOBILE_DOMAIN );

		return mv;
	}


	/**
	 * 자동결제 테스트
	 * 
	 * @return
	 */
	@RequestMapping( value = "/ot/testbill.kt" )
	public ModelAndView testphonebill( Integer joinseq ) throws Exception
	{
		ModelAndView mv = new ModelAndView();

		mv.addObject( "payment", bmService.payForMembership( bmService.getJoinByJoinseq( joinseq ) ) );
		mv.setViewName( AppConstant.JSON_VIEW_NAME );

		return mv;
	}

	
	
	/**
	 * 블루멤버십 가입 완료 - 이니시스
	 * 
	 * @param prdcode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/ot/bluemembership/registerInicis.kt" )
	public ModelAndView registerInicis( 
			@ModelAttribute INIBillKeyRegisterResultForm iniData,
			@RequestParam( value = "isMobile", required = true, defaultValue = "false" ) boolean isMobile,
			@RequestParam( value = "isPopup", required = false ) Boolean isPopup) throws Exception
	{
		if( isPopup == null )
			isPopup = !isMobile;
		
		logger.info( String.format("registerInicis %s %s", iniData.resultcode, iniData.resultmsg) );
		boolean isUserCancel = iniData.resultcode.equals( "01" ) && iniData.resultmsg.equals( "사용자에 의한 취소입니다." );
		
		ModelAndView mv = new ModelAndView();
		
		Integer joinseq = Integer.parseInt( iniData.orderid );
		
		BMJoinDomain join = bmService.getJoinByJoinseq( joinseq );
		
		//대기 상태의 데이터가 아니라면 컨트롤러 중복 호출로 보고 종료
		if( join.getJoinstatusfg() != JoinStatusCode.wating )
		{
			logger.error( String.format( "결제결과등록 api 중복 호출, registerMobi, joinseq=", join.getJoinseq() ) );
			if( isMobile )
				mv.setViewName( "redirect:/ot/mytoon/bluemembership/joinStatus.kt" );
			else
				mv.setViewName( "redirect:/mytoon/bluemembership/joinStatus.kt" );
			return mv;
		}

		// 이니시스 등록 결과가 반영된 가입 신청 데이터
		join = inicisService.applyIniRegisterResultToJoin( iniData, join );
		
		// 가입 처리
		join = bmService.registerJoin( join, join.getPgresultcode().equals( "00" ) );
		
		Integer paymentseq = 0;

		// 신규가입
		if( join.getJoinstatusfg() == JoinStatusCode.active )
		{
			// 첫 결제 처리
			try
			{
				BMPaymentDomain payment = bmService.payForMembership( join );		//결제 처리
				
				if( StringUtils.isNotEmpty( payment.getPgresultcode() ) )
					payment 			= bmService.registerPayment( payment );		//디비 처리
				
				paymentseq 				= payment == null ? 0 : payment.getPaymentseq();
			} 
			catch( Exception e )
			{
				logger.debug( e.getMessage() );
				logger.debug( ExceptionUtils.getStackTrace( e ) );
				paymentseq = 0;
			}
		}

		//CommonBluemembershipController
		mv.setViewName( "redirect:/ot/bluemembership/joinResult.kt?joinseq=" + join.getJoinseq() + "&paymentseq=" + paymentseq + "&isMobile=" + isMobile + "&isPopup=" + isPopup + "&isUserCancel=" + isUserCancel );

		return mv;
	}


	/**
	 * 블루멤버십 가입 완료(모빌리언스에서 post로 호출하는 콜백 url)
	 * 
	 * @param prdcode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/ot/bluemembership/registerMobi.kt" )
	public ModelAndView registerMobi( @ModelAttribute MobiBillkeyRegisterResultForm form,
			@RequestParam( value = "isUserCancel", required = true, defaultValue = "false" ) boolean isUserCancel,
			@RequestParam( value = "isMobile", required = true, defaultValue = "false" ) boolean isMobile,
			@RequestParam( value = "isPopup", required = false ) Boolean isPopup) throws Exception
	{
		
		if( isPopup == null )
			isPopup = !isMobile;
		
		logger.info( String.format( "registerMobi.form.No=%s", form.No ) );
		ModelAndView mv = new ModelAndView();

		BMJoinDomain join = bmService.getJoinByJoinseq( Integer.parseInt( form.Tradeid.replace( mobiService.getJoinTradeIdPrefix(), "" ) ) );
		
		//대기 상태의 데이터가 아니라면 컨트롤러 중복 호출로 보고 종료
		if( join.getJoinstatusfg() != JoinStatusCode.wating )
		{
			logger.error( String.format( "결제결과등록 api 중복 호출, registerMobi, joinseq=", join.getJoinseq() ) );
			
			if( isMobile )
				mv.setViewName( "redirect:/ot/mytoon/bluemembership/joinStatus.kt" );
			else
				mv.setViewName( "redirect:/mytoon/bluemembership/joinStatus.kt" );
			
			return mv;
		}

		// 모빌리언스 등록 결과를 가입 이력에 반영
		join = mobiService.applyRegisterResultToJoin( form, join );

		// 가입, 결제 처리
		join = bmService.registerJoin( 
				join, 
				StringUtils.isNotEmpty( join.getPgresultcode() ) && join.getPgresultcode().equals( "0000" ), 
				form );

		// 결제 레코드
		BMPaymentDomain payment = bmService.getLastPaymentByJoinseq( join.getJoinseq() );

		Integer paymentseq = 0;
		// 방금 처리된 결제 레코드가 맞는지 확인
		if( payment != null && payment.getPgauthdt().equals( join.getJoindt() ) )
		{
			paymentseq = payment.getPaymentseq();
		}

		
		mv.setViewName( "redirect:/ot/bluemembership/joinResult.kt?" + "joinseq=" + join.getJoinseq() + "&paymentseq=" + paymentseq + "&isMobile="
				+ isMobile + "&isPopup=" + isPopup + "&isUserCancel=" + isUserCancel );

		return mv;
	}


	/**
	 * 가입 결과
	 * 
	 * @param joinseq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/ot/bluemembership/joinResult.kt" )
	public ModelAndView joinResult( @RequestParam( value = "joinseq", required = true ) Integer joinseq,
			@RequestParam( value = "paymentseq", required = true, defaultValue = "0" ) Integer paymentseq,
			@RequestParam( value = "isMobile", required = true, defaultValue = "false" ) boolean isMobile,
			@RequestParam( value = "isUserCancel", required = true, defaultValue = "false" ) boolean isUserCancel,
			@RequestParam( value = "isPopup", required = true, defaultValue = "false" ) boolean isPopup) throws Exception
	{
		ModelAndView mv = new ModelAndView();
		
		// 팝업이면 창을 닫고 부모창을 다시 common/olltoonbmJoinResultPopup.kt 컨트롤러로 이동시킴
		if( isPopup )
		{
			mv.addObject( "joinseq", joinseq );
			mv.addObject( "paymentseq", paymentseq );
			mv.addObject( "isMobile", isMobile );
			mv.addObject( "isUserCancel", isUserCancel );
			mv.setViewName( "common/olltoonbmJoinResultPopup" );
			return mv;
		}

		// 가입 내역
		BMJoinDomain join = bmService.getJoinByJoinseq( joinseq );
		mv.addObject( "join", join );

		// 신규 가입이 아닐 때 변경 신청인지 구분
		if( join.getJoinstatusfg() != JoinStatusCode.active )
		{
			BMJoinDomain active = getActiveJoin();
			if( active != null )
			{
				if( join.getPrdcode() == active.getPrdcode() )
					mv.addObject( "changereqfg", ChangeRequestCode.bill.getCode() );
				else
					mv.addObject( "changereqfg", ChangeRequestCode.product.getCode() );
				
				mv.addObject( "originJoin", bmService.getLastJoinByChangeJoinseq( join.getJoinseq() ) );
			}
			else
				mv.addObject( "changereqfg", ChangeRequestCode.none.getCode() );
		}
		else
			mv.addObject( "changereqfg", ChangeRequestCode.none.getCode() );

		// 가입한 상품
		BMPrdDomain prd = prdService.getPrdByPrdcode( join.getPrdcode() );
		mv.addObject( "prd", prd );

		// 결제 이력
		BMPaymentDomain payment = bmService.getPaymentByPaymentseq( paymentseq );
		mv.addObject( "payment", payment );
		
		// 현재 이용기간
		mv.addObject( "currentTerm", getCurrentTerm() );
		
		// 다음 이용기간
		if( getCurrentTerm() != null )
			mv.addObject( "nextTerm", termService.getNextTermForUserAndPrd( join.getIdfg(), join.getUserid(), prd.getPrdcode() ) );


		String viewPathPrefix = isMobile ? "olltoon/mobile" : "olltoon/pc";
		String urlPathPrefix = isMobile ? "/ot" : "/ot";

		//사용자 취소일 경우 가입/변경 신청화면으로 돌아간다
		if( isUserCancel )
		{
			if( join.getJoinstatusfg() == JoinStatusCode.fail )
				mv.setViewName( "redirect:" + urlPathPrefix + "/bluemembership/join.kt?prdcode=" + prd.getPrdcode() );
			else if( join.getJoinstatusfg() == JoinStatusCode.changebillfail )
				mv.setViewName( "redirect:" + urlPathPrefix + "/bluemembership/change.kt" );
			else if( join.getJoinstatusfg() == JoinStatusCode.changeprdfail )
				mv.setViewName( "redirect:" + urlPathPrefix + "/bluemembership/change.kt" );
		}
		// 신규가입
		else if( join.getJoinstatusfg() == JoinStatusCode.active )
		{
			// 결제 결과 연장된 기간
			BMJoinTermDomain term = termService.getLastTermByPaymentseq( payment.getPaymentseq() );
			mv.addObject( "term", term );

			// 결제 결과 충전된 블루베리
			BMOrderDomain order = orderService.getLastOrderByPaymentseq( payment.getPaymentseq() );
			mv.addObject( "order", order );

			mv.setViewName( viewPathPrefix + "/bluemembership/joinComplete" );
			
		}
		// 멤버십변경
		else if( join.getJoinstatusfg() == JoinStatusCode.reserve )
		{
			mv.setViewName( viewPathPrefix + "/bluemembership/changeComplete" );
		}
		// 가입 성공하였으나 결제 실패
		else if( join.getJoinstatusfg() == JoinStatusCode.suspend )
		{
			mv.setViewName( viewPathPrefix + "/bluemembership/membershipSuspend" );
		}
		// 가입 실패
		else if( join.getJoinstatusfg() == JoinStatusCode.fail ||
				join.getJoinstatusfg() == JoinStatusCode.changebillfail ||
				join.getJoinstatusfg() == JoinStatusCode.changeprdfail)
		{
			mv.setViewName( viewPathPrefix + "/bluemembership/joinFail" );
		}
		logger.info( "viewname:" + mv.getViewName() );

		return mv;
	}
	
	@RequestMapping( value = "/ot/bluemembership/getBlueMembershipInfo.kt" )
	public ModelAndView getBlueMembershipInfo() throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName( AppConstant.JSON_VIEW_NAME );
		
		if( !OlltoonAuthUtil.isLogin( request ) )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "로그인이 필요한 서비스 입니다.<br />로그인 하시겠습니까?" );
			return mv;
		}
		
		mv.addObject( "user", OlltoonAuthUtil.getDecryptUserInfo( request ) );
		mv.addObject( "userCert", PremiumUtil.myCert( request ) );
		mv.addObject( "devicefg", RequestUtil.getClientDeviceInfo(request) );
		
		if( getActiveJoin() == null )
		{
			return mv;
		}
		
		mv.addObject( "activeJoin", getActiveJoin() );
		mv.addObject( "alternativePrdCode", getUsingPrd().getPrdcode() == PrdCode.BM30 ? PrdCode.BM50 : PrdCode.BM30 );
		
		if( getActiveJoin().getChangejoinseq() == null || 
			getActiveJoin().getChangejoinseq() == 0 )
		{
			return mv;
		}
		
		BMJoinDomain reservedJoin = bmService.getJoinByJoinseq( getActiveJoin().getChangejoinseq() );
		
		if( reservedJoin == null )
		{
			return mv;
		}
		
		mv.addObject( "erroryn", "N" );
		mv.addObject( "reservedJoin", reservedJoin );
		
		return mv;
	}
	
	@RequestMapping( value = "/ot/bluemembership/getActiveJoin.kt" )
	public ModelAndView getActiveJoinJson() throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName( AppConstant.JSON_VIEW_NAME );
	
		if( !OlltoonAuthUtil.isLogin( request ) )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "로그인이 필요한 서비스 입니다" );
			return mv;
		}
		
		if( getActiveJoin() == null )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "가입된 이력이 없습니다" );
			return mv;
		}
		
		mv.addObject( "erroryn", "N" );
		mv.addObject( "user", OlltoonAuthUtil.getDecryptUserInfo( request ) );
		mv.addObject( "userCert", PremiumUtil.myCert( request ) );
		mv.addObject( "activeJoin", getActiveJoin() );
		
		if( getActiveJoin().getChangereqfg() == ChangeRequestCode.bill || 
			getActiveJoin().getChangereqfg() == ChangeRequestCode.product )
			mv.addObject( "changeJoin", bmService.getJoinByJoinseq( getActiveJoin().getChangejoinseq() ) );
		
		mv.addObject( "nextTerm", termService.getNextTermForUserAndPrd( getActiveJoin().getIdfg(), getActiveJoin().getUserid(), getActiveJoin().getPrdcode() ));
		mv.addObject( "devicefg", RequestUtil.getClientDeviceInfo(request) );
		
		return mv;
	}
	
	@RequestMapping( value = "/ot/bluemembership/getReservedJoin.kt" )
	public ModelAndView getReservedJoinJson() throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName( AppConstant.JSON_VIEW_NAME );
	
		if( !OlltoonAuthUtil.isLogin( request ) )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "로그인이 필요한 서비스 입니다" );
			return mv;
		}
		
		if( getActiveJoin() == null )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "가입된 이력이 없습니다" );
			return mv;
		}
		
		if( getActiveJoin().getChangejoinseq() == null || 
			getActiveJoin().getChangejoinseq() == 0 )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "변경예약된 이력이 없습니다" );
			return mv;
		}
		
		BMJoinDomain reservedJoin = bmService.getJoinByJoinseq( getActiveJoin().getChangejoinseq() );
		
		if( reservedJoin == null )
		{
			mv.addObject( "erroryn", "Y" );
			mv.addObject( "errormsg", "변경예약된 이력이 없습니다" );
			return mv;
		}
		
		mv.addObject( "erroryn", "N" );
		mv.addObject( "user", OlltoonAuthUtil.getDecryptUserInfo( request ) );
		mv.addObject( "userCert", PremiumUtil.myCert( request ) );
		mv.addObject( "activeJoin", getActiveJoin() );
		mv.addObject( "reservedJoin", reservedJoin );
		
		return mv;
	}

	private BMJoinDomain getActiveJoin()
	{
		return summaryProvider.get().getActiveJoin();
	}
	
	private BMPaymentDomain getLastPayment()
	{
		return summaryProvider.get().getLastPayment();
	}
	
	private BMPrdDomain getUsingPrd()
	{
		return summaryProvider.get().getUsingPrd();
	}
	
	private BMJoinTermDomain getCurrentTerm()
	{
		return summaryProvider.get().getCurrentTerm();
	}
	
	private Integer getOwnBlueBerryCount()
	{
		return summaryProvider.get().getOwnBlueBerryCount();
	}
}
