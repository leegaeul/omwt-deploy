/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : PaymentServiceIface.java
 * DESCRIPTION    : 결제
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-23      init
 *****************************************************************************/

package com.olleh.webtoon.olltoon.payment.service.iface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.orderbuy.domain.BuyDomain;
import com.olleh.webtoon.common.dao.payment.domain.OrderDomain;
import com.olleh.webtoon.common.dao.payment.domain.PaymentDomain;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;

public interface OlltoonPaymentServiceIface {
	
	/**
	 * 주문번호로 결제정보를 조회한다.
	 *
	 * @param String orderNo : 주문번호
	 * @return PaymentDomain params : 조회한 결제정보
	 */
	public PaymentDomain getPayment(PaymentDomain params);
	
	/**
	 * 사용자별 주문내역(베리) 합계 정보를 조회한다.
	 *
	 * @param UserDomain userDomain : 사용자 정보
	 * @return int        : 조회한 주문내역 합계 정보
	 */
	public int getOrderSum(UserDomain userDomain);
	
	/**
	 * 결제정보를 등록한다.
	 *
	 * @param PaymentDomain paymentDomain : 결제정보
	 */
	public void registPayment(PaymentDomain paymentDomain) throws Exception;
	
	/**
	 * 결제정보를 수정한다.
	 *
	 * @param PaymentDomain paymentDomain : 결제정보
	 */
	public void modifyPayment(PaymentDomain paymentDomain) throws Exception;
	
	/**
	 * 주문정보를 등록한다.
	 *
	 * @param OrderDomain orderDomain : 주문정보
	 */
	public void registOrder(OrderDomain orderDomain) throws Exception;
	
	/**
	 * 사용자 정보를 조회한다.
	 *
	 * @param HttpServletRequest req: request 정보
	 */
	public UserDomain getUserInfo(HttpServletRequest req) throws Exception;
	
	/**
	 * 사용자 상태를 조회한다.
	 *
	 * @param HttpServletRequest req: request 정보
	 */
	public int getUserStatus(HttpServletRequest req) throws Exception;
	
	/**
	 * 폰빌 제외 CTN 여부를 조회한다.
	 *
	 * @param String ctn : CTN
	 */
	public boolean getExcCtn(String ctn) throws Exception;
	
	
	/**
	 * 이용가능 상품 내역을 수정한다.
	 * 
	 * @param BuyDomain buyDomain     : 삭제할 구매내역 정보
	 */
	public void deletePrdAvail(BuyDomain buyDomain) throws Exception ;
	
}