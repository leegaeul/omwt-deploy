package com.olleh.webtoon.common.dao.bluemembership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.olleh.webtoon.common.dao.bluemembership.code.OrderCode;
import com.olleh.webtoon.common.dao.bluemembership.code.OrderUseCode;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinTermDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMOrderDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdDomain;
import com.olleh.webtoon.common.dao.bluemembership.persistence.BMOrderMapper;
import com.olleh.webtoon.common.util.TRDateUtils;

@Service
public class BMOrderService
{
	@Autowired
	private BMOrderMapper	orderMapper;

	@Autowired
	private BMPrdService	prdService;


	public BMOrderDomain chargeBlueBerryWithTerm( BMJoinTermDomain term )
	{
		BMPrdDomain prd = prdService.getPrdByPrdcode( term.getPrdcode() );

		BMOrderDomain order = new BMOrderDomain();
		order.setPaymentseq( term.getPaymentseq() );
		order.setBuyseq( null );
		order.setTermseq( term.getTermseq() );
		order.setIdfg( term.getIdfg() );
		order.setOrderid( term.getUserid() );
		order.setUsefg( OrderUseCode.charge );
		order.setOrderfg( OrderCode.order );
		order.setOrderamount( prd.getBlueberrycnt() );
		order.setOrderdt( TRDateUtils.yyyyMMddhhmiss() );
		order.setRegdt( TRDateUtils.yyyyMMddhhmiss() );

		orderMapper.insert( order );

		return order;
	}


	public BMOrderDomain getLastOrderByPaymentseq( Integer paymentseq )
	{
		BMOrderDomain order = new BMOrderDomain();
		order.setPaymentseq( paymentseq );
		return orderMapper.selectLastByPaymentseq( order );
	}


	public Integer getOwnBlueBerryForUser( String idfg, String userid )
	{
		BMOrderDomain order = new BMOrderDomain();
		order.setIdfg( idfg );
		order.setOrderid( userid );

		return orderMapper.selectOwnBlueBerryForUser( order );
	}
	
	
	public BMOrderDomain makeExpireFor( BMOrderDomain order ) throws Exception
	{
		if( order.getOrderfg() != OrderCode.order )
			throw new Exception(String.format("주문건이 아니어서 만료처리할 수 없습니다:orderseq=%d", order.getOrderseq()));
		
		if( order.getUsefg() != OrderUseCode.charge )
			throw new Exception(String.format("주문건이 아니어서 만료처리할 수 없습니다:orderseq=%d", order.getOrderseq()));
		
		int expireAmount = Math.min( 
				getOwnBlueBerryForUser( order.getIdfg(), order.getOrderid() ).intValue(),
				order.getOrderamount().intValue() );
		
		BMOrderDomain expire = new BMOrderDomain();
		expire.setIdfg( order.getIdfg() );
		expire.setOrderid( order.getOrderid() );
		expire.setUsefg( OrderUseCode.charge );
		expire.setOrderfg( OrderCode.expire );
		expire.setOriginorderseq( order.getOrderseq() );
		expire.setOrderamount( expireAmount );
		expire.setOrderdt( TRDateUtils.yyyyMMddhhmiss() );
		expire.setRegdt( TRDateUtils.yyyyMMddhhmiss() );
		
		orderMapper.insert( expire );
		
		return expire;
	}


	public BMOrderDomain getChargeOrderByPaymentseq( Integer paymentseq )
	{
		BMOrderDomain order = new BMOrderDomain();
		order.setPaymentseq( paymentseq );
		return orderMapper.selectChargeOrderByPaymentseq( order );
	}
}
