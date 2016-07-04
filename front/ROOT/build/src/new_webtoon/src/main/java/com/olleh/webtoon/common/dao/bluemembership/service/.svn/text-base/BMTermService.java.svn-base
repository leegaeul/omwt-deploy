package com.olleh.webtoon.common.dao.bluemembership.service;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olleh.webtoon.common.dao.bluemembership.code.PrdCode;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinTermDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPaymentDomain;
import com.olleh.webtoon.common.dao.bluemembership.persistence.BMJoinTermMapper;
import com.olleh.webtoon.common.util.TRDateUtils;

@Service
public class BMTermService
{
	@Autowired
	private BMJoinTermMapper	termMapper;

	@Autowired
	private BMPrdService		prdService;

	private Calendar			cal	= Calendar.getInstance();


	public BMJoinTermDomain getCurrentTermForUser( String idfg, String userid )
	{
		BMJoinTermDomain term = new BMJoinTermDomain();
		term.setIdfg( idfg );
		term.setUserid( userid );
		return termMapper.selectCurrentForUser( term );
	}
	
	
	public BMJoinTermDomain getLastTermForUser( String idfg, String userid )
	{
		BMJoinTermDomain term = new BMJoinTermDomain();
		term.setIdfg( idfg );
		term.setUserid( userid );
		return termMapper.selectLastTermForUser( term );
	}
	
	
	public BMJoinTermDomain getNextTermForUserAndPrd( String idfg, String userid, PrdCode prdcode )
	{
		BMJoinTermDomain lastTerm = getLastTermForUser( idfg, userid );
		BMJoinTermDomain term = new BMJoinTermDomain();
		term.setIdfg( idfg );
		term.setUserid( userid );
		term.setPrdcode( prdcode );
		
		//상품코드가 전달되지 않으면 이용기간을 30일로간주
		Integer useTerm = prdcode == null ? 30 : prdService.getPrdByPrdcode( prdcode ).getUseterm();
		

		Date now = new Date();
		Date yesterday = DateUtils.addDays( now, -1 );
		Date endDate;
		
		//신규 -> 현재시간을 시작시간으로, D일 23시 59분 59초를 종료시간으로
		if( lastTerm == null )
		{
			term.setStartdtDate( now );
			endDate = DateUtils.addDays( term.getStartdtDate(), useTerm + 1 );		//D+1일
			endDate = DateUtils.truncate( endDate, Calendar.DATE );	//D+1일 0시 0분 0초
			endDate = DateUtils.addSeconds( endDate, -1 );			//D일 23시 59분 59초
			term.setEnddtDate( endDate );
		}
		//연장 -> 오늘 0시를 시작시간으로, D-1일 23시 59분 59초를 종료시간으로 
		else if( DateUtils.isSameDay( lastTerm.getEnddtDate(), yesterday ) && lastTerm.getEnddt().endsWith( "235959" ) )
		{
			term.setStartdtDate( DateUtils.truncate( now, Calendar.DATE ) );
			endDate = DateUtils.addDays( term.getStartdtDate(), useTerm );			//D일
			endDate = DateUtils.truncate( endDate, Calendar.DATE );	//D일 0시 0분 0초
			endDate = DateUtils.addSeconds( endDate, -1 );			//D-1일 23시 59분 59초
			term.setEnddtDate( endDate );
		}
		//재가입 -> 현재시간을 시작시간으로, D일 23시 59분 59초를 종료시간으로
		else if( lastTerm.getEnddtDate().before( now ) )
		{
			term.setStartdtDate( now );
			endDate = DateUtils.addDays( term.getStartdtDate(), useTerm + 1 );		//D+1일
			endDate = DateUtils.truncate( endDate, Calendar.DATE );	//D+1일 0시 0분 0초
			endDate = DateUtils.addSeconds( endDate, -1 );			//D일 23시 59분 59초
			term.setEnddtDate( endDate );
		}
		//변경 -> 서비스 종료일 다음날 0시를 시작시간으로, D-1일 23시 59분 59초를 종료시간으로
		else
		{
			Date nextStart = DateUtils.addDays( lastTerm.getEnddtDate(), 1 );
			nextStart = DateUtils.truncate( nextStart, Calendar.DATE );
			term.setStartdtDate( nextStart );
			
			endDate = DateUtils.addDays( term.getStartdtDate(), useTerm );			//D일
			endDate = DateUtils.truncate( endDate, Calendar.DATE );	//D일 0시 0분 0초
			endDate = DateUtils.addSeconds( endDate, -1 );			//D-1일 23시 59분 59초
			term.setEnddtDate( endDate );
		}
		
		return term;
	}

	/**
	 * payment 데이터로 새로운 이용기간을 추가함
	 * 
	 * @param payment
	 * @return
	 */
	public BMJoinTermDomain renewTermWithPayment( BMPaymentDomain payment )
	{
		BMJoinTermDomain term = getNextTermForUserAndPrd( payment.getIdfg(), payment.getUserid(), payment.getPrdcode() );
		term.setJoinseq( payment.getJoinseq() );
		term.setPaymentseq( payment.getPaymentseq() );
		term.setRegdt( TRDateUtils.yyyyMMddhhmiss() );
		termMapper.insert( term );

		return term;
	}


	public BMJoinTermDomain getLastTermByPaymentseq( Integer paymentseq )
	{
		return termMapper.selectLastByPaymentseq( new BMPaymentDomain( paymentseq ) );
	}


	public BMJoinTermDomain getLastJoinTermByJoinseq( Integer joinseq )
	{
		BMJoinTermDomain term = new BMJoinTermDomain();
		term.setJoinseq( joinseq );
		return termMapper.selectLastByJoinseq( term );
	}
}
