package com.olleh.webtoon.common.dao.bluemembership.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inicis.inipay4.util.INIdata;
import com.mobilians.mc01_v0005.AckParam;
import com.olleh.webtoon.common.dao.bluemembership.code.BillMethodCode;
import com.olleh.webtoon.common.dao.bluemembership.code.ChangeRequestCode;
import com.olleh.webtoon.common.dao.bluemembership.code.JoinStatusCode;
import com.olleh.webtoon.common.dao.bluemembership.code.PayStatusCode;
import com.olleh.webtoon.common.dao.bluemembership.code.PrdCode;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinFailDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinTermDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMOrderDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPaymentDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdOptionDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.MobiBillkeyRegisterResultForm;
import com.olleh.webtoon.common.dao.bluemembership.persistence.BMJoinFailMapper;
import com.olleh.webtoon.common.dao.bluemembership.persistence.BMJoinMapper;
import com.olleh.webtoon.common.dao.bluemembership.persistence.BMPaymentMapper;
import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.TRDateUtils;
import com.olleh.webtoon.common.util.TRMiscUtils;
import com.olleh.webtoon.olltoon.common.util.OlltoonAuthUtil;

@Service
public class BMService
{
	private Log					logger	= LogFactory.getLog( BMService.class );

	@Autowired
	private HttpServletRequest	request;

	@Autowired
	private BMJoinMapper		joinMapper;

	@Autowired
	private BMPaymentMapper		paymentMapper;

	@Autowired
	private UserServiceIface	userService;

	@Autowired
	private OrderbuyService		orderbuyService;

	@Autowired
	private InicisService		inicisService;

	@Autowired
	private MobiService			mobiService;

	@Autowired
	private BMOrderService		orderService;

	@Autowired
	private BMPrdService		prdService;

	@Autowired
	private BMTermService		termService;
	
	@Autowired
	private BMJoinFailMapper	failMapper;

	private String				updatetoken;


	public int saveJoin( BMJoinDomain join )
	{
		join.setModdt( TRDateUtils.yyyyMMddhhmiss() );
		int updated = joinMapper.updateByJoinseq( join );

		if( updated > 0 )
			return updated;

		join.setChangereqfg( ChangeRequestCode.none );
		join.setChangereqdt( "" );
		join.setChangejoinseq( 0 );
		join.setUpdatetoken( "" );
		join.setRegdt( TRDateUtils.yyyyMMddhhmiss() );
		join.setModdt( null );

		return joinMapper.insert( join );
	}


	@Transactional
	public BMPaymentDomain registerPayment( BMPaymentDomain payment )
	{
		payment.setModdt( TRDateUtils.yyyyMMddhhmiss() );
		
		if( paymentMapper.updateByPaymentseq( payment ) == 0)
			paymentMapper.insert( payment );
		
		// 결제 완료이고 기존에 등록된 이용기간, 블루베리 충전 레코드가 없으면 지급함
		if( payment.getPaystatusfg().equals( PayStatusCode.complete ) )
		{
			BMJoinTermDomain term = termService.getLastTermByPaymentseq( payment.getPaymentseq() );
			BMOrderDomain order = orderService.getLastOrderByPaymentseq( payment.getPaymentseq() );

			if( term == null && order == null )
			{
				// 이용 기간 데이터 입력(ow_bm_join)
				term = termService.renewTermWithPayment( payment );

				// 블루 베리 충전
				orderService.chargeBlueBerryWithTerm( term );
				
				// 상품연장, 상품 변경에 대해서 부가 상품 지급 처리 - 20150209
				provideMembershipOptionProduct( getJoinByJoinseq(term.getJoinseq()) );
			}
		}
		// 결제 실패하면 멤버십을 중지상태로 바꿈
		else if( payment.getPaystatusfg() == PayStatusCode.fail )
		{
			//20150312 - 결제가 실패하면 지급된 블루멤버십 상품들을 회수 처리한다.
			BMJoinDomain join = new BMJoinDomain();
			join.setUserid(payment.getUserid());
			join.setIdfg(payment.getIdfg());
			join.setPrdcode(payment.getPrdcode());
			collectMembershipOptionProduct(join);
			
			processMembershipFailure( null, payment, null );
		}

		return payment;
	}


	public BMJoinDomain getJoinByJoinseq( Integer joinseq )
	{
		return joinMapper.selectByJoinseq( new BMJoinDomain( joinseq ) );
	}


	/**
	 * 등록 대기 상태의 가입 레코드를 만듬
	 * 
	 * @param prdcode
	 * @param billmethodfg
	 * @param changereqfg
	 * @param buyertel
	 * @param buyeremail
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public BMJoinDomain initJoinDataBeforePayment( PrdCode prdcode, BillMethodCode billmethodfg, String buyertel, String buyeremail )
			throws Exception
	{
		UserDomain user = AuthUtil.getDecryptUserInfo( request );

		BMJoinDomain active = getActiveJoinForUser( user.getIdfg(), user.getEmail() );

		BMJoinDomain join = new BMJoinDomain();
		join.setJoinstatusfg( JoinStatusCode.wating );	// 예약 상태
		join.setPrdcode( prdcode );
		join.setIdfg( user.getIdfg() );
		join.setUserid( user.getEmail() );
		join.setBillmethodfg( billmethodfg );
		join.setChangereqfg( ChangeRequestCode.none );
		join.setChangejoinseq( 0 );
		join.setUpdatetoken( "" );

		if( active == null )
		{
			join.setBuyertel( buyertel );
			join.setBuyeremail( buyeremail );
		}
		// 멤버십 변경건 이라면 buyertel과 buyeremail을 기존 이용중인 멤버십 데이터에서 가져옴
		else
		{
			join.setBuyeremail( active.getBuyeremail() );
			join.setBuyertel( active.getBuyertel() );
		}

		saveJoin( join );

		return join;
	}


	/**
	 * 등록 대기 상태의 가입 레코드를 만듬
	 * 
	 * @param prdcode
	 * @param billmethodfg
	 * @param changereqfg
	 * @param buyertel
	 * @param buyeremail
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public BMJoinDomain otInitJoinDataBeforePayment( PrdCode prdcode, BillMethodCode billmethodfg, String buyertel, String buyeremail )
			throws Exception
	{
		UserDomain user = OlltoonAuthUtil.getDecryptUserInfo( request );

		BMJoinDomain active = getActiveJoinForUser( user.getIdfg(), user.getEmail() );

		BMJoinDomain join = new BMJoinDomain();
		join.setJoinstatusfg( JoinStatusCode.wating );	// 예약 상태
		join.setPrdcode( prdcode );
		join.setIdfg( user.getIdfg() );
		join.setUserid( user.getEmail() );
		join.setBillmethodfg( billmethodfg );
		join.setChangereqfg( ChangeRequestCode.none );
		join.setChangejoinseq( 0 );
		join.setUpdatetoken( "" );

		if( active == null )
		{
			join.setBuyertel( buyertel );
			join.setBuyeremail( buyeremail );
		}
		// 멤버십 변경건 이라면 buyertel과 buyeremail을 기존 이용중인 멤버십 데이터에서 가져옴
		else
		{
			join.setBuyeremail( active.getBuyeremail() );
			join.setBuyertel( active.getBuyertel() );
		}

		saveJoin( join );

		return join;
	}


	public BMJoinDomain getActiveJoinForUser( String idfg, String userid )
	{
		BMJoinDomain join = new BMJoinDomain();
		join.setIdfg( idfg );
		join.setUserid( userid );
		join.setJoinstatusfg( JoinStatusCode.active );
		return joinMapper.selectLastForUserByJoinStatus( join );
	}


	public BMJoinDomain getLastJoinForUser( String idfg, String userid )
	{
		BMJoinDomain join = new BMJoinDomain();
		join.setIdfg( idfg );
		join.setUserid( userid );
		return joinMapper.selectLastForUser( join );
	}

	
	public BMJoinDomain getLastJoinByChangeJoinseq( Integer changejoinseq )
	{
		BMJoinDomain join = new BMJoinDomain();
		join.setChangejoinseq( changejoinseq );
		return joinMapper.selectLastByChangeJoinseq( join );
	}


	/**
	 * 사용자의 마지막 해지이력
	 * 
	 * @param idfg
	 * @param userid
	 * @return
	 */
	public BMJoinDomain getLastCancelJoinForUser( String idfg, String userid )
	{
		BMJoinDomain join = new BMJoinDomain();
		join.setIdfg( idfg );
		join.setUserid( userid );
		return joinMapper.selectLastCancelForUser( join );
	}


	/**
	 * 사용자의 해지이력 목록
	 * 
	 * @param idfg
	 * @param userid
	 * @return
	 */
	public List<BMJoinDomain> getCancelJoinListForUser( String idfg, String userid )
	{
		BMJoinDomain join = new BMJoinDomain();
		join.setIdfg( idfg );
		join.setUserid( userid );
		return joinMapper.selectCancelListForUser( join );
	}


	/**
	 * 멤버십 결제
	 * pg사와 연동하여 결제를 진항하고 결과를 BMPaymentDomain을 리턴
	 * 결제 결과를 디비에 저장하지는 않음
	 * 
	 * @param join
	 * @return
	 * @throws Exception
	 */
	public BMPaymentDomain payForMembership( BMJoinDomain join ) throws Exception
	{
		BMJoinTermDomain currentTerm = termService.getCurrentTermForUser( join.getIdfg(), join.getUserid() );

		// 현재 이용중인 상태이면 추가 결제를 진행하지 않는다
		if( currentTerm != null )
		{
			throw new Exception( String.format( "멤버십 이용기간이 만료되지 않았으므로 추가 결제가 불가 합니다:joinseq=%d, termsseq=%d", join.getJoinseq(),
					currentTerm.getTermseq() ) );
		}
		
		BMPaymentDomain payment = new BMPaymentDomain();
		payment.setJoinseq( join.getJoinseq() );
		payment.setPrdcode( join.getPrdcode() );
		payment.setIdfg( join.getIdfg() );
		payment.setUserid( join.getUserid() );
		payment.setRegdt( TRDateUtils.yyyyMMddhhmiss() );
		payment.setPaystatusfg( PayStatusCode.standby );
		payment.setAmount( 0 );

		// 이니시스 카드 결제
		if( join.getBillmethodfg() == BillMethodCode.card )
		{
			INIdata data = inicisService.payForMembership( join );

			payment.setBillmethodfg( BillMethodCode.card );
			payment.setPaystatusfg( "00".equals( data.getData( "ResultCode" ) ) ? PayStatusCode.complete : PayStatusCode.fail );
			payment.setAmount( Integer.parseInt( data.getData( "price" ) ) );
			payment.setPgtid( data.getData( "tid" ) );
			payment.setPgresultcode( data.getData( "ResultCode" ) );
			payment.setPgresultmsg( data.getData( "ResultMsg" ) );
			payment.setPgauthcode( data.getData( "CardAuthCode" ) );
			payment.setPgauthdt( data.getData( "PGauthdate" ) + data.getData( "PGauthtime" ) );
		}
		// 모빌리언스 폰빌
		else
		{
			//인서트 해서 키값을 가져옴
			paymentMapper.insert( payment );
			
			AckParam data = mobiService.payForMembership( join, payment.getPaymentseq() );

			payment.setBillmethodfg( BillMethodCode.phone );
			if( "0000".equals( data.getResultcd() ) && data.getMode().equals( "53" ) && data.getMobilid() != null )
				payment.setPaystatusfg( PayStatusCode.complete );
			else
				payment.setPaystatusfg( PayStatusCode.fail );
			payment.setAmount( Integer.parseInt( data.getPrdtprice() ) );
			payment.setPgtid( data.getMobilid() );
			payment.setPgresultcode( data.getResultcd() );
			payment.setPgresultmsg( data.getResultmsg() );
			payment.setPgauthcode( data.getMobilid() );
			payment.setPgauthdt( data.getActdate() );
		}

		return payment;
	}


	public BMPaymentDomain getLastPaymentByJoinseq( Integer joinseq )
	{
		BMPaymentDomain payment = new BMPaymentDomain();
		payment.setJoinseq( joinseq );

		return paymentMapper.selectLastByJoinseq( payment );
	}


	public Date getNextChargeDateForJoinseq( Integer joinseq )
	{
		BMJoinTermDomain lastTerm = termService.getLastJoinTermByJoinseq( joinseq );

		if( lastTerm == null )
			return null;

		Date endDate = lastTerm.getEnddtDate();

		Calendar cal = Calendar.getInstance();
		cal.setTime( endDate );
		cal.add( Calendar.SECOND, 1 );

		return cal.getTime();
	}


	public List<BMPaymentDomain> getPaymentListForUserWithPaging( BMPaymentDomain payment )
	{
		return paymentMapper.selectListForUserWithPaging( payment );
	}


	public Integer getPaymentCountForUser( String idfg, String userid )
	{
		BMPaymentDomain payment = new BMPaymentDomain();
		payment.setIdfg( idfg );
		payment.setUserid( userid );
		return paymentMapper.selectCountForUser( payment );
	}


	public BMPaymentDomain getPaymentByPaymentseq( Integer paymentseq )
	{
		return paymentMapper.selectByPaymentseq( new BMPaymentDomain( paymentseq ) );
	}


	public BMJoinDomain getReservedJoinForUser( String idfg, String userid )
	{
		BMJoinDomain join = new BMJoinDomain();
		join.setIdfg( idfg );
		join.setUserid( userid );
		join.setJoinstatusfg( JoinStatusCode.reserve );
		return joinMapper.selectLastForUserByJoinStatus( join );
	}


	@Transactional
	public void cancelChangeReservation( BMJoinDomain reserved ) throws Exception
	{
		if( reserved.getJoinstatusfg() != JoinStatusCode.reserve )
			throw new Exception( String.format("예약된 가입정보가 아닙니다:joinseq=%d", reserved.getJoinseq() ) );

		BMJoinDomain active = getActiveJoinForUser( reserved.getIdfg(), reserved.getUserid() );

		if( active == null )
		{
			throw new Exception( "현재 활성화된 가입 이력이 없습니다" );
		}
		
		if( active.getChangejoinseq().intValue() != reserved.getJoinseq().intValue() )
		{
			throw new Exception( String.format("현재 활성화된 가입이력의 변경가입순번과 변경 취소 요청된 가입순번이 일치하지 않습니다:active.changereqjoinseq=%d, reserved.joinseq=%d", active.getChangejoinseq(), reserved.getJoinseq() ) );
		}

		joinMapper.deleteByJoinseq( reserved );

		active.setChangejoinseq( 0 );
		active.setChangereqdt( "" );
		active.setChangereqfg( ChangeRequestCode.none );

		joinMapper.updateByJoinseq( active );
	}


	/**
	 * 해지예약
	 * 
	 * @param join
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public BMJoinDomain reserveCancelJoin( BMJoinDomain join ) throws Exception
	{
		if( join.getChangereqfg() == ChangeRequestCode.cancel )
			throw new Exception( "이미 " + join.getChangereqfg().getText() + "중 입니다" );

		if( join.getChangereqfg() == ChangeRequestCode.bill || 
			join.getChangereqfg() == ChangeRequestCode.product )
		{
			BMJoinDomain changeReqJoin = getJoinByJoinseq( join.getChangejoinseq() );
			joinMapper.deleteByJoinseq( changeReqJoin );
		}
		
		join.setChangejoinseq( 0 );
		join.setChangereqdt( TRDateUtils.yyyyMMddhhmiss() );
		join.setChangereqfg( ChangeRequestCode.cancel );

		joinMapper.updateByJoinseq( join );
	

		return join;
	}


	/**
	 * 해지예약
	 * 
	 * @param join
	 * @return
	 * @throws Exception
	 */
	public BMJoinDomain cancelCancelJoinReservation( BMJoinDomain join ) throws Exception
	{
		if( join.getChangereqfg() != ChangeRequestCode.cancel )
			throw new Exception( "해지 예약된 가입정보가 아닙니다" );

		join.setChangejoinseq( 0 );
		join.setChangereqdt( "" );
		join.setChangereqfg( ChangeRequestCode.none );

		joinMapper.updateByJoinseq( join );

		return join;
	}


	/**
	 * @param join
	 *            등록 신청 이력
	 * @param success
	 *            등록 성공 여부
	 * @return
	 */
	@Transactional
	public BMJoinDomain registerJoin( BMJoinDomain join, boolean success )
	{
		BMJoinDomain activeJoin = getActiveJoinForUser( join.getIdfg(), join.getUserid() );
		BMJoinTermDomain currentTerm = termService.getCurrentTermForUser( join.getIdfg(), join.getUserid() );

		// 등록 성공
		if( success )
		{
			// 활성 상태의 가입이력이 있다면
			if( activeJoin != null && currentTerm != null )
			{
				//이미 변경 요청 중이라면 예약된 가입건을 삭제함
				if( 	activeJoin.getChangereqfg() == ChangeRequestCode.bill || 
						activeJoin.getChangereqfg() == ChangeRequestCode.product )
				{
					BMJoinDomain reserved = getJoinByJoinseq( activeJoin.getChangejoinseq() );
					if( reserved != null )
						joinMapper.deleteByJoinseq( reserved );
				}
				
				join.setJoinstatusfg( JoinStatusCode.reserve );					// 예약 상태로 바꿈
				join.setJoindt( termService.getNextTermForUserAndPrd( join.getIdfg(), join.getUserid(), join.getPrdcode() ).getStartdt() );	// 변경된 가입정보가 반영되는 날을 가입일로 함
				saveJoin( join );

				if( join.getPrdcode().equals( activeJoin.getPrdcode() ) )		// 변경 전후 상품코드가 같다면
					activeJoin.setChangereqfg( ChangeRequestCode.bill );			// 결제수단변경으로
				else
					activeJoin.setChangereqfg( ChangeRequestCode.product );	// 다르면 상품변경으로 함

				activeJoin.setChangereqdt( TRDateUtils.yyyyMMddhhmiss() );		// 변경 요청일
				activeJoin.setChangejoinseq( join.getJoinseq() );				// 변경 후 가입데이터 참조

				saveJoin( activeJoin );
			}
			else
			{
				join.setJoindt( TRDateUtils.yyyyMMddhhmiss() );					// 신규 가입이므로 현재시간이 가입일
				join.setJoinstatusfg( JoinStatusCode.active );					// 활성 상태
				join.setChangejoinseq( 0 );
				saveJoin( join );

				// 부가 상품 지급
				provideMembershipOptionProduct( join );
			}

		}
		// 등록 실패
		else
		{
			if( activeJoin == null || activeJoin.getJoinseq().intValue() != join.getJoinseq().intValue() )
				processMembershipFailure( join, null, null );
		}

		return join;
	}


	/**
	 * 휴대폰 결제 등록일 때만 첫 결제 처리를 동시에 함
	 * 
	 * @param join
	 * @param success
	 * @param form
	 * @return
	 */
	@Transactional
	public BMJoinDomain registerJoin( BMJoinDomain join, boolean success, MobiBillkeyRegisterResultForm form )
	{
		join = registerJoin( join, success );

		if( join.getJoinstatusfg() != JoinStatusCode.active || !success || join.getBillmethodfg() != BillMethodCode.phone )
			return join;

		BMPaymentDomain payment = new BMPaymentDomain();
		payment.setJoinseq( join.getJoinseq() );
		payment.setPrdcode( join.getPrdcode() );
		payment.setBillmethodfg( BillMethodCode.phone );
		payment.setIdfg( join.getIdfg() );
		payment.setUserid( join.getUserid() );
		payment.setRegdt( TRDateUtils.yyyyMMddhhmiss() );
		payment.setPaystatusfg( PayStatusCode.complete );
		payment.setAmount( Integer.parseInt( form.Prdtprice ) );
		payment.setPgtid( form.Mobilid );
		payment.setPgresultcode( form.Resultcd );
		payment.setPgresultmsg( form.Resultmsg );
		payment.setPgauthcode( form.Mobilid );
		payment.setPgauthdt( join.getJoindt() );

		registerPayment( payment );

		return join;
	}


	public List<BMPaymentDomain> getPaymentListByJoinSeq( Integer joinseq )
	{
		BMPaymentDomain payment = new BMPaymentDomain();
		payment.setJoinseq( joinseq );

		return paymentMapper.selectListByJoinseq( payment );
	}


	public void removeWatingJoinByJoinseq( Integer joinseq )
	{
		BMJoinDomain join = getJoinByJoinseq( joinseq );

		if( join != null & join.getJoinstatusfg() == JoinStatusCode.wating )
			joinMapper.deleteByJoinseq( join );
	}


	/**
	 * 멤버십 자동결제 배치 시
	 * 동일한 가입이력에 대해 다른 서버와 중복으로 자동결제처리를 하지 않도록
	 * 이 서버의 토큰을 표시 해 두고 표시된 레코드를 리턴
	 * 
	 * @return
	 */
	public int markUpdatetokenForRenewal() throws Exception
	{
		Map<String, Object> param = new HashMap<String, Object>();

		updatetoken = TRMiscUtils.getServerTokenValue();
		param.put( "updatetoken", updatetoken );

		Calendar cal = Calendar.getInstance();
		Date now = new Date();

		cal.setTime( now );
		cal.add( Calendar.DATE, -1 );

		param.put( "startdt", TRDateUtils.yyyymmddhhmissFromDate( cal.getTime() ) );
		param.put( "enddt", TRDateUtils.yyyymmddhhmissFromDate( now ) );

		return joinMapper.updateTokenForRenewal( param );
	}


	public int unMarkUpdatetoken()
	{
		BMJoinDomain join = new BMJoinDomain();
		join.setUpdatetoken( updatetoken );
		return joinMapper.updateTokenForFinishRenewal( join );
	}


	public BMJoinDomain getLastJoinByUpdatetoken()
	{
		BMJoinDomain join = new BMJoinDomain();
		join.setUpdatetoken( updatetoken );
		return joinMapper.selectLastByUpdatetoken( join );
	}


	/**
	 * 월별 멤버십 갱신(연장,해지,변경 처리)
	 * 
	 * @param join
	 * @throws Exception
	 */
	@Transactional
	public void membershipDailyProcess( BMJoinDomain join ) throws Exception
	{
		if( join.getJoinstatusfg() != JoinStatusCode.active )
			throw new Exception( String.format("활성상태의 가입이력이 아닙니다:joisneq=%d", join.getJoinseq()) );

		// 블루베리 회수
		BMOrderDomain order = orderService.getChargeOrderByPaymentseq( getLastPaymentByJoinseq( join.getJoinseq() ).getPaymentseq() );
		
		if( order != null )
			orderService.makeExpireFor( order );

		// 해지요청건이 아니면 연장처리
		if( join.getChangereqfg() != ChangeRequestCode.cancel )
		{
			/* 2015.10.31 블루멤버쉽 서비스 중단 : 자동연장 중단 처리
			// 변경요청인가?
			if( join.getChangereqfg() == ChangeRequestCode.bill || join.getChangereqfg() == ChangeRequestCode.product )
			{
				// 현재 가입레코드를 변경상태로 업데이트
				join.setJoinstatusfg( JoinStatusCode.change );
				joinMapper.updateByJoinseq( join );

				// 상품변경이면 부가상품에 대한 변경 처리를 함
				if( join.getChangereqfg() == ChangeRequestCode.product )
				{
					// 현재 가입된 멤버십으로 제공된 부가상품 회수
					collectMembershipOptionProduct( join );
						
					// 변경 요청 접수된 레코드 가져옴
					join = getJoinByJoinseq( join.getChangejoinseq() );
					
					// 변경 부가 상품 지급 -> 수정 : registerPayment에서 상품변경, 상품연장건에 대해서 모두 부가상품 지급 처리를 한다. - 20150209
//					provideMembershipOptionProduct( join );
				}
				else
				{
					// 변경 요청 접수된 레코드 가져옴
					join = getJoinByJoinseq( join.getChangejoinseq() );
				}
					
				// 변경된 레코드를 활성상태로
				join.setJoinstatusfg( JoinStatusCode.active );
				joinMapper.updateByJoinseq( join );
			}

			// 연장을 위한 결제 처리
			BMPaymentDomain payment = payForMembership( join );

			// 결제 결과를 디비에 반영하고 블루베리 지급
			registerPayment( payment );
			*/
		}
		else
		{
			// 해지처리
			join.setJoinstatusfg( JoinStatusCode.cancel );
			join.setCanceldt( TRDateUtils.yyyyMMddhhmiss() );
			joinMapper.updateByJoinseq( join );

			// 현재 가입된 멤버십으로 제공된 부가상품 회수
			collectMembershipOptionProduct( join );
		}
	}


	/**
	 * 멤버십 부가상품 지급
	 * 
	 * @param join
	 * @return
	 */
	public List<ProductAvailDomain> provideMembershipOptionProduct( BMJoinDomain join )
	{
		// 부가 상품 지급 - 20150209 지급상품 수정(날짜별 지급상품 구분)
//		List<BMPrdOptionDomain> optionList = prdService.getPrdOptionListByPrdcode( join.getPrdcode() );
		List<BMPrdOptionDomain> optionList = prdService.getPrdOptionListByDate( join.getPrdcode() );
		List<Integer> optionseqList = new ArrayList<Integer>();
		
		if(optionList != null){
			for( BMPrdOptionDomain option : optionList )
				optionseqList.add( option.getPrdseq() );
		}
		
		return orderbuyService.provideMembershipOptionProduct( join.getIdfg(), join.getUserid(), optionseqList );
	}


	/**
	 * 멤버십 부가상품 회수
	 * 
	 * @param join
	 * @return
	 */
	public List<ProductAvailDomain> collectMembershipOptionProduct( BMJoinDomain join )
	{
		//사용자가 회수할 멤버십 네임콘을 선택 상태일 경우 default네임콘으로 변경한다. - 20150211
		userService.updateFromOptionToDefaultNamecon(join.getUserid(), join.getIdfg());
		
		// 부가 상품 회수 - 20150209 회수상품 수정
//		List<BMPrdOptionDomain> optionList = prdService.getPrdOptionListByPrdcode( join.getPrdcode() );
		List<BMPrdOptionDomain> optionList = prdService.getAllPrdOptionListByPrdcode( join.getPrdcode() );
		List<Integer> optionseqList = new ArrayList<Integer>();
		
		if(optionList != null){
			for( BMPrdOptionDomain option : optionList )
				optionseqList.add( option.getPrdseq() );
		}

		return orderbuyService.collectMembershipOptionProduct( join.getIdfg(), join.getUserid(), optionseqList );
	}

	/**
	 * 가입실패나 결제실패 시 처리
	 * @param joinOrPayment
	 * @param e
	 * @return
	 */
	@Transactional
	public BMJoinFailDomain processMembershipFailure( BMJoinDomain join, BMPaymentDomain payment, Exception e  )
	{
		if( join == null && payment == null )
			throw new RuntimeException( "가입이력이나 결제이력 중 하나는 필수 입니다" );
		
		if( join == null )
		{
			join = getJoinByJoinseq( payment.getJoinseq() );
		}
		
		//이미 가입된 이력일 경우 멤버십 연장이 실패한 것이므로 suspend로 처리
		if( join.getJoinstatusfg() == JoinStatusCode.active )
		{
			join.setJoinstatusfg( JoinStatusCode.suspend );
		}
		//그렇지 않을 경우 멤버십 상태에 따라 처리
		else
		{
			BMJoinDomain activeJoin = getActiveJoinForUser( join.getIdfg(), join.getUserid() );
			
			//기 가입 이력이 있을경우 변경 신청이 실패한것
			if( activeJoin != null )
			{
				if( activeJoin.getPrdcode() != join.getPrdcode() )
					join.setJoinstatusfg( JoinStatusCode.changeprdfail );
				else
					join.setJoinstatusfg( JoinStatusCode.changebillfail );
			}
			//그렇지 않을 경우 신규 가입이 실패한 것
			else
				join.setJoinstatusfg( JoinStatusCode.fail );
			join.setChangejoinseq( 0 );
		}
		saveJoin( join );
		
		BMJoinFailDomain fail = new BMJoinFailDomain();
		fail.setJoinseq( join.getJoinseq() );
		if( payment != null )
			fail.setPaymentseq( payment.getPaymentseq() );
		
		if( e != null )
		{
			fail.setErrormsg( e.getMessage() );
			fail.setErrorlog( ExceptionUtils.getStackTrace( e ) );
		}
		else
		{
			if( payment == null )
			{
				fail.setErrormsg( join.getPgresultmsg() );
				fail.setErrorlog( join.getPgresultcode() );
			}
			else
			{
				fail.setErrormsg( payment.getPgresultmsg() );
				fail.setErrorlog( payment.getPgresultcode() );
			}
		}
		fail.setRegdt( TRDateUtils.yyyyMMddhhmiss() );
		failMapper.insert( fail );
		
		return fail;
	}
}
