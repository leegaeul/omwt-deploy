/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : PaymentController.java
 * DESCRIPTION    : 결제 
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-23      init
 *****************************************************************************/

package com.olleh.webtoon.olltoon.payment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.api.sdp.SDPApiService;
import com.olleh.webtoon.api.sdp.SDPConstants;
import com.olleh.webtoon.api.sdp.SDPResponse;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;
import com.olleh.webtoon.common.dao.bluemembership.service.BMService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMTermService;
import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.payment.domain.IPGPaymentDomain;
import com.olleh.webtoon.common.dao.payment.domain.OrderDomain;
import com.olleh.webtoon.common.dao.payment.domain.PaymentDomain;
import com.olleh.webtoon.common.dao.payment.domain.PaymentFailDomain;
import com.olleh.webtoon.common.dao.payment.service.iface.PaymentFailService;
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AesCipherUtil;
import com.olleh.webtoon.common.util.AppInfoUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.SmsSendUtil;
import com.olleh.webtoon.common.util.StringUtil;
import com.olleh.webtoon.olltoon.common.util.KeyUtil;
import com.olleh.webtoon.olltoon.common.util.OlltoonRequestUtil;
import com.olleh.webtoon.olltoon.payment.service.iface.OlltoonPaymentServiceIface;

@Controller("OlltoonPaymentController")
public class OlltoonPaymentController {
	
	protected static Log logger = LogFactory.getLog(OlltoonPaymentController.class);
	private static final String DELIM = "; ";
		
	@Autowired
	OlltoonPaymentServiceIface olltoonPaymentService;

	@Autowired
	PaymentFailService paymentFailService;
	
	@Autowired
	OrderbuyService orderbuyService;
	
	@Autowired
	PremiumService premiumService;
	
	@Autowired
	UserServiceIface userServiceIface;
	
	@Autowired
	private BMService			bmService;
	
	@Autowired
	private BMTermService		termService;
	
	/**
	 * 베리 결제 금액, 결제 수단 선택 페이지로 이동
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/payment/payment.kt")
	public ModelAndView moveToPayment(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		String channelfg = OlltoonRequestUtil.getChannelfg(request);
		
		//App에 따라 결제 모듈 변경 onestore : inapp , google :  gaia
//		if("onestore".equals(channelfg))
//			modelAndView.setViewName("olltoon/payment/payment_inapp");
//		else
			modelAndView.setViewName("olltoon/"+(AppInfoUtil.isMobile(request) ? "mobile" : "pc" )+"/payment/payment");
		
		try {
			int status = olltoonPaymentService.getUserStatus(request);
			
			String domain = RequestUtil.isMobile(request) ? MessageUtil.getSystemMessage("system.mobile.domain") : MessageUtil.getSystemMessage("system.pc.domain");
			
			// 1. 로그인 여부 체크
			if(status == SDPConstants.REQ_LOGIN) {
				modelAndView.setViewName("redirect:/web/login.kt?urlcd="+domain +"/ot/payment/payment.kt");
				return modelAndView;

			// 2. 약관 동의 여부 체크
			} else if(status == SDPConstants.REQ_AGREEMENT) {
				//modelAndView.setViewName("olltoon/user/ollehUserAgreement");
				modelAndView.setViewName("redirect:/ot/user/ollehUserAgreement.kt?urlcd="+domain +"/ot/payment/payment.kt");
				return modelAndView;
				
			// 3. 본인 인증 여부 체크
			} else if(status == SDPConstants.REQ_SELF_CERT) {
				
				//modelAndView.setViewName("olltoon/premium/premiumMyCert");
				modelAndView.setViewName("redirect:/web/cert_my.kt?urlcd="+domain +"/ot/payment/payment.kt");
				return modelAndView;

			// 4. 19세미만 법정 대리인 인증 여부 체크
			} else if(status == SDPConstants.REQ_AGENT_CERT) {
				//modelAndView.setViewName("olltoon/premium/premiumMyCert");
				modelAndView.setViewName("redirect:/web/cert_lawer.kt?urlcd="+domain +"/ot/payment/payment.kt");
				return modelAndView;
			}

			UserDomain userDomain = olltoonPaymentService.getUserInfo(request);
			
			modelAndView.addObject("userDomain", userDomain);
			
			String dpyn = "N";
			if("olleh".equals(userDomain.getIdfg()) && !SDPConstants.CUSTOMER_TYPE_N.equals(userDomain.getCtype())) {
				dpyn = olltoonPaymentService.getExcCtn(userDomain.getCtn()) ? "Y" : "N";	
			}
			modelAndView.addObject("dpyn", dpyn);
			
			// 보유한 베리 조회
			int sum = olltoonPaymentService.getOrderSum(userDomain);
			modelAndView.addObject("sum", sum);
			
			// 보유한 블루 베리 조회
			String bluesum = orderbuyService.strBlueBerryAvail( userDomain.getUserid(), userDomain.getIdfg() );
			modelAndView.addObject("bluesum", bluesum);
			modelAndView.addObject("sum", sum);
			
			
			// 웹툰 회원 정보
//			String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));			
//			String tokenJson = KeyUtil.keyToString(ot_token);
//			ObjectMapper mapper = new ObjectMapper();
//			Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
//			
//			OllehUserDomain newUserDomain = null;				
//			if(userMap != null && "open".equals(userMap.get("idfg"))){ 
//				UserDomain tmpUserDomain = (UserDomain)userServiceIface.getUserInfoByEmail((String)userMap.get("userid"));
//				tmpUserDomain.setIdfg("open");
//				tmpUserDomain.setUserid(tmpUserDomain.getEmail());
//				newUserDomain = (OllehUserDomain)tmpUserDomain;
//			}else if(userMap != null && "olleh".equals(userMap.get("idfg"))){
//				newUserDomain = userServiceIface.ollehUserDetail((String)userMap.get("userid"));
//				newUserDomain.setIdfg("olleh");
//				newUserDomain.setUserid(newUserDomain.getOllehid());
//			}
//			
//			request.setAttribute("userInfo", newUserDomain); 
			
			// 보유한 라즈 베리 조회
			String raspsum = orderbuyService.strRaspBerryAvail( userDomain.getUserid(), userDomain.getIdfg() );
			modelAndView.addObject("raspsum",raspsum);

			BMJoinDomain active = bmService.getActiveJoinForUser( userDomain.getIdfg(), userDomain.getEmail() ); // 이용중인 멤버십
			
			// 이용중인 멤버십이 없다면 해지이력을 가져옴
			if( active != null )
			{
				modelAndView.addObject( "nextChargeDate", termService.getNextTermForUserAndPrd( active.getIdfg(), active.getUserid(), active.getPrdcode() ).getStartdtDate() );
				modelAndView.addObject( "active", active );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
			modelAndView.setViewName("redirect:/web/common/error/exception.jsp");
		}
		
		return modelAndView;
	}
	
	/**
	 * 결제 요청 이후 응답처리 
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/payment/paymentProc.kt")
	public ModelAndView moveToPaymentProc(@ModelAttribute("command") PaymentDomain paymentDomain, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("olltoon/"+(AppInfoUtil.isMobile(request) ? "mobile" : "pc" )+"/payment/paymentProc");
		modelAndView.addObject("errorcode", SDPConstants.ERROR_NONE);
		
		
		try {
			// 1. 결제 데이터 유효성 체크
			if(paymentDomain == null) {
				modelAndView.addObject("errorcode", SDPConstants.ERROR_INVALID_REQUEST);
				paymentFail("payment", SDPConstants.ERROR_INVALID_REQUEST, SDPConstants.ERROR_INVALID_PARAMETER_STRING);
				return modelAndView;
			}
			
			// 결제 가격 유효성 체크
			// 면세 처리때문에 금액 조정
			if(paymentDomain.getPayamount() < 2700 || paymentDomain.getPayamount() > 95000) {
				modelAndView.addObject("errorcode", SDPConstants.ERROR_INVALID_PARAMETER);
				paymentFail("payment", SDPConstants.ERROR_INVALID_PARAMETER, SDPConstants.ERROR_INVALID_PARAMETER_STRING);
				return modelAndView;
			}
			
			// 주문 베리 유효성 체크
			if(paymentDomain.getOrderamount() < 240 || paymentDomain.getOrderamount() > 9000) {
				modelAndView.addObject("errorcode", SDPConstants.ERROR_INVALID_PARAMETER);
				paymentFail("payment", SDPConstants.ERROR_INVALID_PARAMETER, SDPConstants.ERROR_INVALID_PARAMETER_STRING);
				return modelAndView;
			}
			
			// 2. 사용자 상태에 따른 결제 정보 설정
			UserDomain userDomain = olltoonPaymentService.getUserInfo(request);
			
			if("olleh".equals(userDomain.getIdfg())) {
				paymentDomain.setSubscrid(userDomain.getUserid());
				paymentDomain.setSubscridtype(SDPConstants.SUBSCR_OLLEHID);
				paymentDomain.setCustphone(userDomain.getCtn());
			} else {
				paymentDomain.setSubscrid(userDomain.getUserid());
				paymentDomain.setSubscridtype(SDPConstants.SUBSCR_OPENID);
			}
			
			if("olleh".equals(userDomain.getIdfg()) && !SDPConstants.CUSTOMER_TYPE_N.equals(userDomain.getCtype())) {
				paymentDomain.setKttype("10");
			} else {
				paymentDomain.setKttype("20");
			}
			
			// 휴대폰 결제인 경우 사용자 상태에 따라 폰빌, 핸드폰 소액결제 분기 처리
			if("HP".equals(paymentDomain.getPaymethod())) {
				boolean isDP = "olleh".equals(userDomain.getIdfg()) && !SDPConstants.CUSTOMER_TYPE_N.equals(userDomain.getCtype());
				
				// 올레회원이고 KT사용자인 경우 폰빌 결제로 처리 (폰빌 불가 대상자 제외)
				if(isDP && !olltoonPaymentService.getExcCtn(userDomain.getCtn())) {
					paymentDomain.setPaymethod("DP");
					paymentDomain.setCustphone(userDomain.getCtn());
					
				// 올레준회원, 오픈아이디인 경우
				} else {
					if("Y".equals(paymentDomain.getKtyn())) { // KT사용자 체크 상태
						// 휴대폰 인증여부 체크
						String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
						String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
							
						AesCipherUtil aesCipherUtil = new AesCipherUtil();
						
						String u_otp_s = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_otp_s"));
						String [] otp_s = u_otp_s.split(DELIM);
						
						if("".equals(u_otp_s) || otp_s.length < 5) {
							modelAndView.addObject("errorcode", SDPConstants.ERROR_SMS_APPROVE);
							modelAndView.addObject("errormsg", SDPConstants.ERROR_SMS_APPROVE_STRING);
							paymentFail("payment", SDPConstants.ERROR_SMS_APPROVE, SDPConstants.ERROR_SMS_APPROVE_STRING);
							return modelAndView;
						}
							
						// 휴대폰 인증 여부
						String otp_approveyn = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, otp_s[4].getBytes()));
						
						// 인증된 휴대폰 번호
						String otp_ctn= StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, otp_s[2].getBytes()));
						
						// 인증된 휴대폰 번호 KT 사용자 여부
						String otp_dpyn= StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, otp_s[3].getBytes()));
						
						if("".equals(otp_ctn)) {
							modelAndView.addObject("errorcode", SDPConstants.ERROR_SMS_APPROVE);
							modelAndView.addObject("errormsg", SDPConstants.ERROR_SMS_APPROVE_STRING);
							paymentFail("payment", SDPConstants.ERROR_SMS_APPROVE, SDPConstants.ERROR_SMS_APPROVE_STRING);
							return modelAndView;
						}
						
						if(!"Y".equals(otp_approveyn)) {
							modelAndView.addObject("errorcode", SDPConstants.ERROR_SMS_APPROVE);
							modelAndView.addObject("errormsg", SDPConstants.ERROR_SMS_APPROVE_STRING);
							paymentFail("payment", SDPConstants.ERROR_SMS_APPROVE, SDPConstants.ERROR_SMS_APPROVE_STRING);
							return modelAndView;
						}
						
						// KT사용자 여부 조회
						if("Y".equals(otp_dpyn)) {
							paymentDomain.setPaymethod("DP");
							paymentDomain.setCustphone(otp_ctn);
							paymentDomain.setSubscrid(otp_ctn);
							paymentDomain.setSubscridtype(SDPConstants.SUBSCR_CTN);
						}
					}
				}
			}
			
			// 3. 주문번호 생성
			//String orderno = DateUtil.getNowDate(1) + Long.toString(System.nanoTime()).substring(8, 14);
			String nanotime = Long.toString(System.nanoTime());
			String orderno = DateUtil.getNowDate(1) + nanotime.substring(nanotime.length()-6, nanotime.length());		
			
			// 4. 가주문 데이터 생성
			paymentDomain.setPaymentstep("req");
			paymentDomain.setFreeyn("N");
			paymentDomain.setPaymentfg("order");
			paymentDomain.setOpcode(MessageUtil.getSystemMessage("system.ipg.opcode"));
			paymentDomain.setOrderno(orderno); 
			paymentDomain.setSessionid(request.getSession().getId());
			paymentDomain.setCustname(userDomain.getUserid());
			paymentDomain.setCustemail(userDomain.getEmail());
			paymentDomain.setDisplayversion("2.0");
			paymentDomain.setClienttype(RequestUtil.getClientDeviceInfo(request));
			paymentDomain.setPaymentid(userDomain.getUserid());
			paymentDomain.setPaymentdt(DateUtil.getNowDate(1));
			paymentDomain.setIdfg(userDomain.getIdfg());
			paymentDomain.setRegid(userDomain.getUserid());
			paymentDomain.setRegdt(DateUtil.getNowDate(1));
			
			olltoonPaymentService.registPayment(paymentDomain);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
			modelAndView.addObject("errorcode", SDPConstants.ERROR_SYSTEM);
			modelAndView.addObject("errormsg", SDPConstants.ERROR_SYSTEM_STRING);
			
			paymentFail("payment", SDPConstants.ERROR_SYSTEM, SDPConstants.ERROR_INVALID_AGENT_STRING);
		}
		
		modelAndView.addObject("paymentDomain", paymentDomain);
		
		return modelAndView;
	}
	
	/**
	 * 결제 요청 이후 응답처리 
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/payment/resultProc.kt")
	public ModelAndView moveToResultProc(@ModelAttribute("command") IPGPaymentDomain iPGPaymentDomain, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("olltoon/"+(AppInfoUtil.isMobile(request) ? "mobile" : "pc" )+"/payment/resultProc");
		modelAndView.addObject("errorcode", SDPConstants.ERROR_NONE);
		
		PaymentDomain paymentDomain = new PaymentDomain();

		try {
			// 1. 결제 데이터 유효성 체크
			if(iPGPaymentDomain == null || iPGPaymentDomain.getCode() == null || "".equals(iPGPaymentDomain.getCode())) {

				modelAndView.addObject("errorcode", SDPConstants.ERROR_CANCEL_REQUEST);
				modelAndView.addObject("errormsg", SDPConstants.ERROR_CANCEL_REQUEST_STRING);
				modelAndView.addObject("paymentDomain", paymentDomain);
				paymentFail("result", SDPConstants.ERROR_CANCEL_REQUEST, SDPConstants.ERROR_CANCEL_REQUEST_STRING);
				
				return modelAndView;
			}
			
			if("GAIA.200_0000".equals(iPGPaymentDomain.getCode())) { // IPG 결제인증 성공

				// 2. 가주문데이터 조회
				UserDomain userDomain = olltoonPaymentService.getUserInfo(request);
				
				PaymentDomain params = new PaymentDomain();
				params.setOrderno(iPGPaymentDomain.getOrderNo());
				params.setPaymentfg("order");
				params.setPaymentstep("req");
				params.setPaymentid(userDomain.getUserid());
				params.setIdfg(userDomain.getIdfg());
				
				paymentDomain = olltoonPaymentService.getPayment(params);
				
				// 3. 데이터 검증
				if(paymentDomain == null || !"req".equals(paymentDomain.getPaymentstep())) {

					modelAndView.addObject("errorcode", SDPConstants.ERROR_INVALID_REQUEST);
					modelAndView.addObject("errormsg", SDPConstants.ERROR_INVALID_REQUEST_STRING);
					modelAndView.addObject("paymentDomain", paymentDomain);
					paymentFail("result", SDPConstants.ERROR_INVALID_REQUEST, SDPConstants.ERROR_INVALID_REQUEST_STRING);
					
					return modelAndView;
				}
				
				paymentDomain.setPayno(iPGPaymentDomain.getPayNo());
				if(!"DP".equals(paymentDomain.getPaymethod())) {
					paymentDomain.setPaymethod("NN");
				}
				
				// 4. 결제승인 요청
				SDPResponse sDPResponse = SDPApiService.requestPayment(paymentDomain);

				// 5. 결제완료 처리
				if(sDPResponse != null && SDPConstants.RESULT_SUCCESS.equals(sDPResponse.getResultCode())) { // SDP 결제승인 성공

					paymentDomain.setPaymentstep("complete");
					paymentDomain.setResultcode(sDPResponse.getResultCode());
					paymentDomain.setResultmsg(sDPResponse.getResultMsg());
					
					olltoonPaymentService.modifyPayment(paymentDomain);

					// 베리 주문 완료 처리
					OrderDomain orderDomain = new OrderDomain();
					orderDomain.setPaymentseq(paymentDomain.getPaymentseq());
					orderDomain.setOrderno(paymentDomain.getOrderno());
					orderDomain.setUsefg("charge");
					orderDomain.setOrderfg("order");
					orderDomain.setOrderamount(paymentDomain.getOrderamount());
					orderDomain.setOrderid(paymentDomain.getPaymentid());
					orderDomain.setOrderdt(DateUtil.getNowDate(1));
					orderDomain.setIdfg(paymentDomain.getIdfg());
					orderDomain.setRegid(paymentDomain.getRegid());
					orderDomain.setRegdt(DateUtil.getNowDate(1));
					
					olltoonPaymentService.registOrder(orderDomain);
					
				} else { // SDP 결제승인 실패

					paymentDomain.setPaymentstep("fail");
					paymentDomain.setResultcode(sDPResponse.getResultCode());
					paymentDomain.setResultmsg(sDPResponse.getResultMsg());
					
					olltoonPaymentService.modifyPayment(paymentDomain);
				}
				
			} else { // IPG 결제인증 실패

				paymentDomain.setPaymentstep("fail");
				paymentDomain.setResultcode(iPGPaymentDomain.getCode());
				paymentDomain.setResultmsg(iPGPaymentDomain.getMessage());
				
				olltoonPaymentService.modifyPayment(paymentDomain);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
			modelAndView.addObject("errorcode", SDPConstants.ERROR_SYSTEM);
			modelAndView.addObject("errormsg", SDPConstants.ERROR_SYSTEM_STRING);
			
			paymentFail("result", SDPConstants.ERROR_SYSTEM, SDPConstants.ERROR_SYSTEM_STRING);
			
		}
		
		modelAndView.addObject("paymentDomain", paymentDomain);
		
		return modelAndView;		
	}
	
	/**
	 * 결제 완료 페이지로 이동
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/payment/result.kt")
	public ModelAndView moveToResult(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("olltoon/"+(AppInfoUtil.isMobile(request) ? "mobile" : "pc" )+"/payment/result");
		
		try {
			int status = olltoonPaymentService.getUserStatus(request);
			
			String domain = RequestUtil.isMobile(request) ? MessageUtil.getSystemMessage("system.mobile.domain") : MessageUtil.getSystemMessage("system.pc.domain");

			// 1. 로그인 여부 체크
			if(status == SDPConstants.REQ_LOGIN) {
				modelAndView.setViewName("olltoon/user/login");

			// 2. 약관 동의 여부 체크
			} else if(status == SDPConstants.REQ_AGREEMENT) {
				//modelAndView.setViewName("olltoon/user/ollehUserAgreement");
				modelAndView.setViewName("redirect:/ot/user/ollehUserAgreement.kt?urlcd="+domain +"/ot/payment/result.kt");
				return modelAndView;
				
			// 3. 본인 인증 여부 체크
			} else if(status == SDPConstants.REQ_SELF_CERT) {
				//modelAndView.setViewName("olltoon/premium/premiumMyCert");
				modelAndView.setViewName("redirect:/ot/premium/premiumMyCert.kt?urlcd="+domain +"/ot/payment/result.kt");
				return modelAndView;
				
			// 4. 19세미만 법정 대리인 인증 여부 체크
			} else if(status == SDPConstants.REQ_AGENT_CERT) {
				//modelAndView.setViewName("olltoon/premium/premiumMyCert");
				modelAndView.setViewName("redirect:/ot/premium/premiumLawerCert.kt?urlcd="+domain +"/ot/payment/payment.kt");
				return modelAndView;
			}

			UserDomain userDomain = olltoonPaymentService.getUserInfo(request);
			modelAndView.addObject("userDomain", userDomain);

			PaymentDomain params = new PaymentDomain();
			params.setOrderno(request.getParameter("orderno"));
			params.setPaymentfg("order");
			params.setPaymentstep("complete");
			params.setPaymentid(userDomain.getUserid());
			params.setIdfg(userDomain.getIdfg());
			

			PaymentDomain paymentDomain = olltoonPaymentService.getPayment(params);
			int sum = olltoonPaymentService.getOrderSum(userDomain);

			if(paymentDomain == null) {
				modelAndView.addObject("errorcode", SDPConstants.ERROR_INVALID_REQUEST);
				modelAndView.addObject("errormsg", SDPConstants.ERROR_INVALID_REQUEST_STRING);
				paymentFail("result", SDPConstants.ERROR_INVALID_REQUEST, SDPConstants.ERROR_INVALID_REQUEST_STRING);
			}else{
				modelAndView.addObject("errorcode", SDPConstants.ERROR_NONE);
			}
	
			modelAndView.addObject("sum", sum);
			modelAndView.addObject("paymentDomain", paymentDomain);
			
			// SMS otp키 삭제
			String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
			CookieUtil.deleteCookie(response, "u_otp_s", cookieDomain);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		return modelAndView;
	}
	
	/**
	 * sms otp키 생성
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/payment/requestSmsOtp.kt")
	public ModelAndView requestSmsOtp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		try {
			String otp = Integer.toString((int)(Math.random() * 1000000));
			for(int i = 0; i < (6 - otp.length()); i++) {
				otp = "0" + otp;
			}
			
			String msg = "올레마켓웹툰 인증번호 [" + otp + "]";
			boolean result = SmsSendUtil.sendSmsResult(request.getParameter("custphone"), "", msg);
			
			if(result) {
				String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
				String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
				String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
				String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
				
				AesCipherUtil aesCipherUtil = new AesCipherUtil();
				
				// 인증정보 쿠키 저장
				String u_otp = aesCipherUtil.encrypt(key, initialVector, otp.getBytes());
				String u_otp_time = aesCipherUtil.encrypt(key, initialVector, DateUtil.getNowDate(1).getBytes());
				String u_otp_ctn = aesCipherUtil.encrypt(key, initialVector, request.getParameter("custphone").getBytes());
				
				String otp_s = u_otp + DELIM + u_otp_time + DELIM + u_otp_ctn + DELIM + "" + DELIM + "";
				CookieUtil.setCookie(response, "u_otp_s", otp_s, (60 * 10), cookiePath, cookieDomain);
				
				modelAndView.addObject("erroryn", "N");
			} else {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errorcode", SDPConstants.ERROR_SMS_SERVICE);
				modelAndView.addObject("errormsg", SDPConstants.ERROR_SMS_SERVICE_STRING);
				
				paymentFail("smsOtp", SDPConstants.ERROR_SMS_SERVICE, SDPConstants.ERROR_INVALID_REQUEST_STRING);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errorcode", SDPConstants.ERROR_SYSTEM);
			modelAndView.addObject("errormsg", SDPConstants.ERROR_SYSTEM_STRING);
			
			paymentFail("smsOtp", SDPConstants.ERROR_SYSTEM, SDPConstants.ERROR_SYSTEM_STRING);
		}
		
		return modelAndView;
	}
	
	/**
	 * sms otp키 승인
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/ot/payment/approveSmsOtp.kt")
	public ModelAndView approveSmsOtp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		try {
			String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
			String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
			String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
			String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
				
			AesCipherUtil aesCipherUtil = new AesCipherUtil();
			
			String u_otp_sc = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_otp_s"));
			String [] otp_sc = u_otp_sc.split(DELIM);
				
			// 저장된 인증번호
			String otp = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, otp_sc[0].getBytes()));			
			String otp_time = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, otp_sc[1].getBytes()));
			
			// 인증시간 체크
			if(Long.parseLong(DateUtil.getNowDate(1)) > Long.parseLong(otp_time) + (60 * 10)) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errorcode", SDPConstants.ERROR_SMS_TIMEOUT);
				modelAndView.addObject("errormsg", SDPConstants.ERROR_SMS_TIMEOUT_STRING);
				
				paymentFail("approveSmsOtp", SDPConstants.ERROR_SMS_TIMEOUT, SDPConstants.ERROR_SMS_TIMEOUT_STRING);
				
				return modelAndView;
			}
			
			// 인증번호 체크
			if(!otp.equals(request.getParameter("otp"))) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errorcode", SDPConstants.ERROR_SMS_INVALID);
				modelAndView.addObject("errormsg", SDPConstants.ERROR_SMS_INVALID_STRING);
				
				paymentFail("approveSmsOtp", SDPConstants.ERROR_SMS_INVALID, SDPConstants.ERROR_SMS_INVALID_STRING);
				
				return modelAndView;
			}
			
			// KT사용자 여부 조회
			String u_otp_dpyn = ""; 
			String otp_ctn= StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, otp_sc[2].getBytes()));
			
			SDPResponse sDPResponse = SDPApiService.getUserInfoWithAge(otp_ctn);
			
			if(SDPConstants.RESULT_SUCCESS.equals(sDPResponse.getResultCode()) && !olltoonPaymentService.getExcCtn(otp_ctn)) {
				modelAndView.addObject("dpyn", "Y");
				u_otp_dpyn = aesCipherUtil.encrypt(key, initialVector, "Y".getBytes());
				
			} else {
				modelAndView.addObject("dpyn", "N");
				u_otp_dpyn = aesCipherUtil.encrypt(key, initialVector, "N".getBytes());
			}
			
			String u_otp_approveyn = aesCipherUtil.encrypt(key, initialVector, "Y".getBytes());
			
			String u_otp_s = otp_sc[0] + DELIM + otp_sc[1] + DELIM + otp_sc[2] + DELIM + u_otp_dpyn + DELIM + u_otp_approveyn;
			CookieUtil.setCookie(response, "u_otp_s", u_otp_s, (60 * 10), cookiePath, cookieDomain);
			
			modelAndView.addObject("erroryn", "N");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errorcode", SDPConstants.ERROR_SYSTEM);
			modelAndView.addObject("errormsg", SDPConstants.ERROR_SYSTEM_STRING);
			
			paymentFail("approveSmsOtp", SDPConstants.ERROR_SYSTEM, SDPConstants.ERROR_SYSTEM_STRING);
		}
		
		return modelAndView;
	}
	
	public void paymentFail(String servicefg, String errorcode, String errormsg){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();	
		PaymentFailDomain paymentFail = new PaymentFailDomain();
		
		UserDomain userDomain;
		try {
			userDomain = olltoonPaymentService.getUserInfo(request);
			paymentFail.setRegdt(DateUtil.getNowDate(1));
			paymentFail.setRegid(userDomain.getEmail());
			paymentFail.setIdfg(userDomain.getIdfg());
			
			paymentFail.setServicefg(servicefg);
			paymentFail.setErrorcode(errorcode);
			paymentFail.setErrormsg(errormsg);
			
			paymentFailService.insertPaymentFail(paymentFail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}