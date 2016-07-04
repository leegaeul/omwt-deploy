package com.olleh.webtoon.common.dao.payment.persistence;

import com.olleh.webtoon.common.dao.payment.domain.PaymentFailDomain;

public interface PaymentFailMapper
{
	public int insertPaymentFail( PaymentFailDomain paymentFailDomain );
}
