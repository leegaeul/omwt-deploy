package com.olleh.webtoon.common.dao.payment.service.iface;

import com.olleh.webtoon.common.dao.payment.domain.PaymentFailDomain;

public interface PaymentFailService {
	
	/**
	 * 결제 실패 저장
	 * @return
	 */
	public int insertPaymentFail(PaymentFailDomain paymentFail);
}
