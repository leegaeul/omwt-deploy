/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : DcbController.java
 * DESCRIPTION    : Common Dcb Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2015-05-15      init
 *****************************************************************************/

package com.olleh.webtoon.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.api.sdp.SDPConstants;
import com.olleh.webtoon.common.dao.dcb.domain.DcbDomain;
import com.olleh.webtoon.common.dao.dcb.service.iface.DcbService;
import com.olleh.webtoon.common.dao.payment.domain.OrderDomain;
import com.olleh.webtoon.common.dao.payment.domain.PaymentDomain;
import com.olleh.webtoon.common.dao.payment.service.iface.PaymentServiceIface;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.RequestUtil;

@Controller("CommonDcvController")
public class DcbController {
			
	protected static Log logger = LogFactory.getLog(DcbController.class);
			
	@Autowired
	DcbService dcbService;
	
	@Autowired
	PaymentServiceIface paymentServiceIface;
	
	/**
	 * 100원앱 쿠폰발급 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/dcb/getDcbCoupon.kt")
	public ModelAndView getDcbCoupon(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		String uuid = RequestUtil.getUuid(request);
		
		if(uuid == null || uuid.length() < 1){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "deviceid값이 전달되지 않았습니다.");
			
			return modelAndView;
		}
		 
		modelAndView.addObject("erroryn", "N");
		
		//쿠폰 발급
		String couponNumber = dcbService.getDcbCoupon(uuid);
		
		modelAndView.addObject("couponNumber", couponNumber);
		
		return modelAndView;
		
	}

	/**
	 * 100원앱 쿠폰을 등록 및 베리지급 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/dcb/dcbCouponRegistProc.kt")
	public ModelAndView dcbCouponRegistProc(@ModelAttribute("dcbDomain") DcbDomain dcbDomain, HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("mappingJacksonJsonView");

		//로그인 체크
		if(!AuthUtil.isLogin(request)) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errorcode", "ERROR01");

			return modelAndView;
		}
		
		DcbDomain couponInfo = dcbService.getCouponInfoByCouponnum(dcbDomain.getCouponnum());
		
		//쿠폰이 이미 등록 되었을 경우
		if(couponInfo != null && couponInfo.getRegid() != null && couponInfo.getRegid().length() > 0) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errorcode", "ERROR02");

			return modelAndView;
		}

		//쿠폰 번호가 없을 경우
		if(couponInfo == null) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errorcode", "ERROR03");

			return modelAndView;
		}

		//쿠폰 등록
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);		
		dcbDomain.setRegid(user.getEmail());
		dcbDomain.setIdfg(user.getIdfg());
		dcbDomain.setRegdt(DateUtil.getNowDate(1));

		DcbDomain couponInfoById = dcbService.getCouponInfoById(dcbDomain);

		//아이디가 이미 등록 되었을 경우
		if(couponInfoById != null) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errorcode", "ERROR04");

			return modelAndView;
		}
		
		//에러여부를 세팅한다.
		modelAndView.addObject("erroryn", "N");
		
		// 1.쿠폰 등록 처리 한다.
		dcbService.couponRegistProc(dcbDomain);
		
		// 2.베리 지급 처리 한다.
		int orderamount = 100;
		String paymentid = user.getEmail();
		String idfg = user.getIdfg();
		String orderno = DateUtil.getNowDate(1) + Long.toString(System.nanoTime()).substring(5, 11);

		// 2-1.결제정보를 등록한다.					
		PaymentDomain paymentDomain = new PaymentDomain();
		paymentDomain.setResultcode(SDPConstants.RESULT_SUCCESS);
		paymentDomain.setResultmsg(SDPConstants.RESULT_SUCCESS_STRING);
		paymentDomain.setPaymentseq(0);
		paymentDomain.setPaymentstep("complete");
		paymentDomain.setPayname("관리자 무료 충전");
		paymentDomain.setPayno("");
		paymentDomain.setOrderno(orderno);
		paymentDomain.setFreeyn("Y");
		paymentDomain.setPaymentfg("order");
		paymentDomain.setPaymethod("NP");
		paymentDomain.setOrderamount(orderamount);
		paymentDomain.setPaymentid(paymentid);
		paymentDomain.setIdfg(idfg);
		paymentDomain.setOpcode("");
		paymentDomain.setPaymentdt(DateUtil.getNowDate(1));
		paymentDomain.setRegid(paymentid);
		paymentDomain.setRegdt(DateUtil.getNowDate(1));
		
		paymentServiceIface.registPayment(paymentDomain);

		// 2-2.주문정보를 등록한다.
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setPaymentseq(paymentDomain.getPaymentseq());
		orderDomain.setOrderno(paymentDomain.getOrderno());
		orderDomain.setUsefg("charge");
		orderDomain.setOrderfg("order");
		orderDomain.setOrderamount(paymentDomain.getOrderamount());
		orderDomain.setOrderid(paymentDomain.getPaymentid());
		orderDomain.setOrderdt(DateUtil.getNowDate(1));
		orderDomain.setIdfg(paymentDomain.getIdfg());
		orderDomain.setRegid(paymentid);
		orderDomain.setRegdt(DateUtil.getNowDate(1));

		paymentServiceIface.registOrder(orderDomain);

		return modelAndView;
	}
}