package com.olleh.webtoon.common.dao.payment.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.payment.domain.PaymentFailDomain;
import com.olleh.webtoon.common.dao.payment.persistence.PaymentFailMapper;
import com.olleh.webtoon.common.dao.payment.service.iface.PaymentFailService;
import com.olleh.webtoon.common.util.DateUtil;

@Service("paymentFailService")
@Repository
public class PaymentFailServiceImpl implements PaymentFailService {

	@Autowired
	private PaymentFailMapper paymentFailMapper;

	/**
	 * 결제 실패 저장
	 * @return
	 */
	@Transactional(readOnly=false)
	public int insertPaymentFail(PaymentFailDomain paymentFail)
	{
		paymentFail.setRegdt(DateUtil.getNowDate(1));
		return paymentFailMapper.insertPaymentFail(paymentFail);
	}
}