package com.olleh.webtoon.mobile.controller;

import java.util.Date;
import java.util.List;
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
import com.inicis.inipay4.INIpay;
import com.olleh.webtoon.common.dao.bluemembership.code.BillMethodCode;
import com.olleh.webtoon.common.dao.bluemembership.code.ChangeRequestCode;
import com.olleh.webtoon.common.dao.bluemembership.code.JoinStatusCode;
import com.olleh.webtoon.common.dao.bluemembership.code.PrdCode;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinTermDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPaymentDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMSummaryModel;
import com.olleh.webtoon.common.dao.bluemembership.domain.INIBillKeyRegisterResultForm;
import com.olleh.webtoon.common.dao.bluemembership.service.BMOrderService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMPrdService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMTermService;
import com.olleh.webtoon.common.dao.bluemembership.service.InicisService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.PremiumUtil;
import com.olleh.webtoon.common.util.RequestUtil;

@Controller
public class MobileBlueMembershipController
{
	private Log					logger	= LogFactory.getLog( MobileBlueMembershipController.class );
	
	@Inject
	private Provider<BMSummaryModel>	summaryProvider;

	@Autowired
	private BMService			bmService;
	
	@Autowired
	private BMPrdService		prdService;

	@Autowired
	private BMTermService		termService;

	@Autowired
	private BMOrderService		orderService;
	
	@Autowired
	private InicisService		inicisService;

	@Autowired
	private INIpay				inipay;

	@Autowired
	private HttpServletRequest	request;

	@Value( "${serverType}" )
	private String				serverType;

	@Value( "${system.pc.domain.ssl}" )
	private String				SYSTEM_PC_DOMAIN_SSL;

	@Value( "${system.pc.domain}" )
	private String				SYSTEM_PC_DOMAIN;

	@Value( "${system.mobile.domain.ssl}" )
	private String				SYSTEM_MOBILE_DOMAIN_SSL;

	@Value( "${system.mobile.domain}" )
	private String				SYSTEM_MOBILE_DOMAIN;


	/**
	 * 블루멤버십 안내(상품선택)
	 * 
	 * @return
	 */
	@RequestMapping( value = "/m/bluemembership/introduction.kt" )
	public ModelAndView introduction() throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName( "mobile/bluemembership/introduction" );

		boolean isLogin = AuthUtil.isLogin( request );

		
		if( isLogin )
		{
			UserDomain user = AuthUtil.getDecryptUserInfo( request );
			mv.addObject( "userCert", PremiumUtil.myCert( request ) );
		}

		mv.addObject( "isLogin", isLogin );
		mv.addObject( "activeJoin", getActiveJoin() );
		mv.addObject( "usingPrd", getUsingPrd() );
		
		if( getUsingPrd() != null )
			mv.addObject( "alternativePrdCode", getUsingPrd().getPrdcode() == PrdCode.BM30 ? PrdCode.BM50 : PrdCode.BM30 );
		
		if( getActiveJoin() != null )
			mv.addObject( "reservedJoin", bmService.getJoinByJoinseq( getActiveJoin().getChangejoinseq() ) );

		return mv;
	}


	/**
	 * 블루멤버십 가입(결제수단선택, 자동결제 등록)
	 * 
	 * @return
	 */
	@RequestMapping( value = "/m/bluemembership/join.kt" )
	public ModelAndView join( @RequestParam( value = "prdcode", required = true ) PrdCode prdcode ) throws Exception
	{
		ModelAndView mv = new ModelAndView();

		String fullUrl = RequestUtil.getFullURL( request );
		if( fullUrl.startsWith( "http:" ) && serverType.equals( "real" ) )
		{
			mv.setViewName( "redirect:" + fullUrl.replaceFirst( "http:", "https:" ) );
			return mv;
		}

		// 로그인 여부 체크
		if( AuthUtil.ifNotLoginThenRedirect( request, mv, true ) )
			return mv;
		
		//IOS APP일 경우 분기처리
		if("ios".equals(RequestUtil.getClientDeviceInfo(request))){
			mv.setViewName("redirect:/m/premium/ios_payment.kt");
			return mv;
		}
		
		// 인증상태
		int myCert = PremiumUtil.myCert(request);
		
		// 인증후 이동할 URI
		String beforeURI = RequestUtil.getFullURI(request);
		beforeURI = (beforeURI != null && 0 < beforeURI.length()) ? "?urlcd=" + beforeURI : "";
		
		// 미인증
		if (myCert == PremiumUtil.NOT_CERT_USER) {
			mv.setViewName("redirect:/m/premium/premiumMyCert.kt" + beforeURI);
			return mv;
		}
		// 미성년자(법적대리인 미인증)
		else if (myCert == PremiumUtil.MINOR_NOT_CERT_USER) {
			mv.setViewName("redirect:/m/premium/premiumLawerCert.kt" + beforeURI);
			return mv;
		}
		// 생년월일없는 올레준회원
		else if (myCert == PremiumUtil.ASSOCIATE_USER) {
			mv.setViewName("redirect:/m/bluemembership/introduction.kt");
			return mv;
		}
		
		UserDomain user = AuthUtil.getDecryptUserInfo( request );

		mv.addObject( "user", user );
		mv.addObject( "prd", prdService.getPrdByPrdcode( prdcode ) );

		BMJoinTermDomain nextTerm = termService.getNextTermForUserAndPrd( user.getIdfg(), user.getEmail(), prdcode );

		mv.addObject( "nextTerm", nextTerm );
		mv.addObject( "iniOfferPeriod", inicisService.convertDatesToIniOfferPeriodFormat( nextTerm.getStartdtDate(), nextTerm.getEnddtDate() ) ); // 이니시스에 표시할 제공기간
		
		mv.addObject( "iniMid", inicisService.getIniMid() );
		mv.addObject( "SYSTEM_PC_DOMAIN_SSL", SYSTEM_PC_DOMAIN_SSL );
		mv.addObject( "SYSTEM_MOBILE_DOMAIN_SSL", SYSTEM_MOBILE_DOMAIN_SSL );
		mv.addObject( "SYSTEM_PC_DOMAIN", SYSTEM_PC_DOMAIN );
		mv.addObject( "SYSTEM_MOBILE_DOMAIN", SYSTEM_MOBILE_DOMAIN );

		mv.setViewName( "mobile/bluemembership/join" );
		return mv;
	}




	/**
	 * 마이툰 > 블루멤버십 > 이용내역
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/m/mytoon/bluemembership/joinStatus.kt" )
	public ModelAndView bmJoinStatus( HttpServletRequest request ) throws Exception
	{
		ModelAndView mv = new ModelAndView();

		// 로그인 여부 체크
		if( AuthUtil.ifNotLoginThenRedirect( request, mv, true ) )
			return mv;

		mv.setViewName( "mobile/mytoon/bluemembership/joinStatus" );
		mv.addObject( "SYSTEM_MOBILE_DOMAIN", SYSTEM_MOBILE_DOMAIN );
		mv.addObject( "SYSTEM_MOBILE_DOMAIN_SSL", SYSTEM_MOBILE_DOMAIN_SSL );
		
		// 현재 로그인 사용자
		mv.addObject( "user", AuthUtil.getCookieUserInfo( request ) );

		UserDomain user = AuthUtil.getDecryptUserInfo( request );

		BMJoinDomain active = bmService.getActiveJoinForUser( user.getIdfg(), user.getEmail() ); // 이용중인 멤버십

		// 이용중인 멤버십이 없다면 해지이력을 가져옴
		if( active == null )
		{
			List<BMJoinDomain> cancelList = bmService.getCancelJoinListForUser( user.getIdfg(), user.getEmail() ); // 해지이력
			mv.addObject( "cancelList", cancelList );
		}
		else
		{
			mv.addObject( "active", active );

			// 다음 결제일
//			mv.addObject( "nextChargeDate", bmService.getNextChargeDateForJoinseq( active.getJoinseq() ) );
			mv.addObject( "nextChargeDate", termService.getNextTermForUserAndPrd( active.getIdfg(), active.getUserid(), active.getPrdcode() ).getStartdtDate() );

			// 멤버십 변경 요청 상태면 변경 요청 이력을 가져옴
			if( active.getChangereqfg() == ChangeRequestCode.bill || active.getChangereqfg() == ChangeRequestCode.product )
			{
				mv.addObject( "reserved", bmService.getJoinByJoinseq( active.getChangejoinseq() ) );

			}
		}

		mv.addObject( "ownBlueBerry", orderService.getOwnBlueBerryForUser( user.getIdfg(), user.getEmail() ) );

		return mv;
	}


	/**
	 * 마이툰 > 블루멤버십 > 결제내역
	 * 
	 * @param request
	 * @param payment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/m/mytoon/bluemembership/paymentList.kt" )
	public ModelAndView bmPaymentList( @ModelAttribute BMPaymentDomain payment ) throws Exception
	{
		ModelAndView mv = new ModelAndView();

		// 로그인 여부 체크
		if( AuthUtil.ifNotLoginThenRedirect( request, mv, true ) )
			return mv;

		mv.setViewName( "mobile/mytoon/bluemembership/paymentList" );
		// 현재 로그인 사용자
		mv.addObject( "user", AuthUtil.getCookieUserInfo( request ) );
		UserDomain user = AuthUtil.getDecryptUserInfo( request );

		payment.setIdfg( user.getIdfg() );
		payment.setUserid( user.getEmail() );

		payment.setTotalCount( bmService.getPaymentCountForUser( payment.getIdfg(), payment.getUserid() ) );
		mv.addObject( "paging", payment );
		mv.addObject( "paymentList", bmService.getPaymentListForUserWithPaging( payment ) );

		BMJoinDomain activeJoin = bmService.getActiveJoinForUser( user.getIdfg(), user.getEmail() );
		mv.addObject( "activeJoin", activeJoin );

		// 다음 결제일
		if( activeJoin != null )
			mv.addObject( "nextChargeDate", termService.getNextTermForUserAndPrd( activeJoin.getIdfg(), activeJoin.getUserid(), activeJoin.getPrdcode() ).getStartdtDate() );
//			mv.addObject( "nextChargeDate", bmService.getNextChargeDateForJoinseq( activeJoin.getJoinseq() ) );

		return mv;
	}


	/**
	 * 블루멤버십 변경
	 * 
	 * @return
	 */
	@RequestMapping( value = "/m/bluemembership/change.kt" )
	public ModelAndView change( @RequestParam( value = "changereqfg", required = true ) ChangeRequestCode changereqfg ) throws Exception
	{
		ModelAndView mv = new ModelAndView();

		// 변경요청 코드 체크
		if( changereqfg != ChangeRequestCode.bill && changereqfg != ChangeRequestCode.product )
		{
			throw new Exception("비 정상적인 접근입니다");
		}

		// 로그인 여부 체크
		if( AuthUtil.ifNotLoginThenRedirect( request, mv, true ) )
			return mv;
		
		//IOS APP일 경우 분기처리
		if("ios".equals(RequestUtil.getClientDeviceInfo(request))){
			mv.setViewName("redirect:/m/premium/ios_payment.kt");
			return mv;
		}

		UserDomain user = AuthUtil.getDecryptUserInfo( request );
		mv.addObject( "user", user );

		BMJoinDomain join = getActiveJoin();

		// 가입 내역이 없으면 오류
		if( join == null )
			throw new Exception( "비 정상적인 접근 입니다" );
		
		// 변경신청내역 없는 가입자만 상품변경 가능
		if( changereqfg == ChangeRequestCode.product &&
			join.getChangereqfg() != ChangeRequestCode.none )
			throw new Exception( "비 정상적인 접근 입니다" );
		
		// 해지예약자를 제외하고 결제방법 변경 신청 가능
		if( changereqfg == ChangeRequestCode.bill &&
			join.getChangereqfg() == ChangeRequestCode.cancel )
			throw new Exception( "비 정상적인 접근 입니다" );

		mv.addObject( "active", join );

		BMJoinTermDomain term = getCurrentTerm();

		// 현재 이용기간이 없는경우( 있을 수 없는 케이스 )
//		if( term == null )
//			throw new Exception( "시스템 오류 입니다" );

		BMPrdDomain prd = null;

		// 현재 이용중인 상품
		BMPrdDomain currentPrd = getUsingPrd();
		mv.addObject( "currentPrd", currentPrd );

		// 상품변경요청 : 가입할 상품을 바꿈 BM30 <-> bM50
		if( changereqfg == ChangeRequestCode.product )
		{
			prd = prdService.getPrdByPrdcode( join.getPrdcode() == PrdCode.BM30 ? PrdCode.BM50 : PrdCode.BM30 );
			mv.setViewName( "mobile/bluemembership/changeProduct" );
		}
		// 결제수단변경요청 : 가입할 상품을 그대로 유지
		else if( changereqfg == ChangeRequestCode.bill )
		{
			prd = currentPrd;
			mv.setViewName( "mobile/bluemembership/changeBillmethod" );
		}

		mv.addObject( "prd", prd );

		// 변경시에 적용될 이용 기간
		BMJoinTermDomain nextTerm = termService.getNextTermForUserAndPrd( join.getIdfg(), join.getUserid(), join.getPrdcode() );

		mv.addObject( "nextTerm", nextTerm );
		mv.addObject( "iniOfferPeriod", inicisService.convertDatesToIniOfferPeriodFormat( nextTerm.getStartdtDate(), nextTerm.getEnddtDate() ) ); // 이니시스에 표시할 제공기간
		
		mv.addObject( "iniMid", inicisService.getIniMid() );
		mv.addObject( "SYSTEM_PC_DOMAIN_SSL", SYSTEM_PC_DOMAIN_SSL );
		mv.addObject( "SYSTEM_MOBILE_DOMAIN_SSL", SYSTEM_MOBILE_DOMAIN_SSL );
		mv.addObject( "SYSTEM_PC_DOMAIN", SYSTEM_PC_DOMAIN );
		mv.addObject( "SYSTEM_MOBILE_DOMAIN", SYSTEM_MOBILE_DOMAIN );

		return mv;
	}
	
	
	/**
	 * 결제 재시도
	 * 
	 * @param prdcode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/m/bluemembership/retryPayment.kt" )
	public ModelAndView retryPayment( @RequestParam( value = "joinseq" ) Integer joinseq ) throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject( "erroryn", "N" );
		
		BMJoinDomain join = bmService.getJoinByJoinseq( joinseq );
		
		//결제 요청건이 중지된 가입정보가 아닐경우 오류
		//카드 결제건이 아닐경우 별도 결제 요청이 불가함 ( 폰빌은 최초 결제가 한꺼번에 이루어지므로 )
		if( join == null || join.getJoinstatusfg() != JoinStatusCode.suspend || join.getBillmethodfg() != BillMethodCode.card )
		{
			throw new Exception("비 정상적인 접근 입니다");
		}
		
		//현재가 이용기간중이라면 중복결제 시도 임
		BMJoinTermDomain term = termService.getCurrentTermForUser( join.getIdfg(), join.getUserid() );
		if( term != null )
		{
			throw new Exception("중복결제 시도 입니다");
		}
		
		//현재 멤버십의 결제목록을 가져옴
		List<BMPaymentDomain> paymentList = bmService.getPaymentListByJoinSeq( join.getJoinseq() );
		
		//첫 결제가 아니면 컨트롤러를 통해 시도할 수 없음
		if( !paymentList.isEmpty() )
		{
			throw new Exception("비 정상적인 접근 입니다");
		}
		
		bmService.payForMembership( join );
		
		mv.setViewName( "redirect:/m/bluemembership/joinResult.kt" );
			
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
