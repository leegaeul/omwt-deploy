/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : PaymentServiceImpl.java
 * DESCRIPTION    : 결제
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-23      init
 *****************************************************************************/

package com.olleh.webtoon.olltoon.payment.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.api.sdp.SDPConstants;
import com.olleh.webtoon.common.dao.orderbuy.domain.BuyDomain;
import com.olleh.webtoon.common.dao.payment.domain.OrderDomain;
import com.olleh.webtoon.common.dao.payment.domain.PaymentDomain;
import com.olleh.webtoon.common.dao.payment.persistence.PaymentMapper;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.persistence.UserMapper;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.StringUtil;
import com.olleh.webtoon.olltoon.common.util.KeyUtil;
import com.olleh.webtoon.olltoon.payment.service.iface.OlltoonPaymentServiceIface;

@Service("olltoonPaymentService")
@Repository
public class OlltoonPaymentServiceImpl implements OlltoonPaymentServiceIface {
	
	protected static Log logger = LogFactory.getLog(OlltoonPaymentServiceImpl.class);
	
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * 주문번호로 결제정보를 조회한다.
	 *
	 * @param String orderNo : 주문번호
	 * @return PaymentDomain params : 조회한 결제정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public PaymentDomain getPayment(PaymentDomain params) {
		PaymentDomain paymentDomain = paymentMapper.getPayment(params);
		
		return paymentDomain;	
	}
	
	/**
	 * 사용자별 주문내역(베리) 합계 정보를 조회한다.
	 *
	 * @param UserDomain userDomain : 사용자정보
	 * @return int        : 조회한 주문내역 합계 정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int getOrderSum(UserDomain userDomain) {
		int amount = paymentMapper.getOrderSum(userDomain);
		
		return amount;
	}
	
	/**
	 * 결제정보를 등록한다.
	 *
	 * @param PaymentDomain paymentDomain : 결제정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void registPayment(PaymentDomain paymentDomain) throws Exception {
		paymentMapper.registPayment(paymentDomain);
	}
	
	/**
	 * 결제정보를 수정한다.
	 *
	 * @param PaymentDomain paymentDomain : 결제정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void modifyPayment(PaymentDomain paymentDomain) throws Exception {
		paymentMapper.modifyPayment(paymentDomain);
	}
	
	/**
	 * 주문정보를 등록한다.
	 *
	 * @param OrderDomain orderDomain : 주문정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void registOrder(OrderDomain orderDomain) throws Exception {
		paymentMapper.registOrder(orderDomain);	
	}
	
	/**
	 * 사용자 정보를 조회한다.
	 *
	 * @param HttpServletRequest req : HTTP Request
	 * @return UserDomain userDomain: 사용자 정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public UserDomain getUserInfo(HttpServletRequest req) throws Exception {
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(req, "ot_token"));
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = 
				mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		UserDomain userDomain = new UserDomain();				
		if(userMap != null && "open".equals(userMap.get("idfg"))) {
			UserDomain user = userMapper.selectUserInfoByEmail((String)userMap.get("userid"));
			
			user = userMapper.selectUserInfoByEmail(user.getEmail());
			userDomain.setIdfg("open");
			userDomain.setUserid(user.getEmail());
			userDomain.setEmail(user.getEmail());
			userDomain.setNickname(user.getNickname());
			userDomain.setCtype("");
			userDomain.setCtn("");
			
		} else if(userMap != null && "olleh".equals(userMap.get("idfg"))) {
			String decuserid = StringUtil.defaultStr((String)userMap.get("userid"));
						
			OllehUserDomain user = userMapper.ollehUserSelectDetail(decuserid);
			userDomain.setIdfg("olleh");
			userDomain.setUserid(user.getOllehid());
			userDomain.setEmail("");
			userDomain.setCtype((String)userMap.get("olleh_ctype"));
			userDomain.setCtn((String)userMap.get("olleh_ctn"));
			
		}else if(userMap != null && "naver".equals(userMap.get("idfg"))) {
			String decuserid = StringUtil.defaultStr((String)userMap.get("userid"));
						
			userDomain.setIdfg("naver");
			userDomain.setUserid(decuserid);
			userDomain.setEmail(decuserid);
			userDomain.setCtype("");
			userDomain.setCtn("");
		}
		
		return userDomain;		
	}
	
	/**
	 * 사용자 정보를 조회한다.
	 *
	 * @param HttpServletRequest req : HTTP Request
	 * @return REQ_LOGIN : 로그인 필요
	 * 				  REQ_AGREEMENT : 약관동의 필요
	 * 				  REQ_SELF_CERT : 본인인증 필요
	 * 				  REQ_AGENT_CERT : 대리인 인증 필요
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int getUserStatus(HttpServletRequest req) throws Exception {
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(req, "ot_token"));
		
		// 1. 로그인 여부 체크
		if(ot_token == null || "".equals(ot_token)) {
			return SDPConstants.REQ_LOGIN;
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = 
				mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		req.setAttribute("ot_user", userMap);
				
		// 2. 약관 동의 여부, 본인 인증 여부 체크
		
		// 오픈아이디인 경우
		if(userMap != null && "open".equals(userMap.get("idfg"))) {
			UserDomain userDomain = userMapper.selectUserInfoByEmail((String)userMap.get("userid"));
			
			if(userDomain == null) {
				return SDPConstants.REQ_LOGIN;
			}
			
			if(!"Y".equals(userDomain.getTermsyn())) {
				return SDPConstants.REQ_AGREEMENT;	
			}
			
			if(!"Y".equals(userDomain.getAdultyn())) {
				return SDPConstants.REQ_SELF_CERT;
			} else {
				if(DateUtil.getAge(userDomain.getBirthday()) < 19 
						&& (userDomain.getMinoritycertnum() == null || "".equals(userDomain.getMinoritycertnum()))) {
					return SDPConstants.REQ_AGENT_CERT;
				}
			}
			
		// 올레회원인 경우
		} else if(userMap != null && "olleh".equals(userMap.get("idfg"))) {
			String olleh_id = StringUtil.defaultStr((String)userMap.get("userid"));			
			
			OllehUserDomain userDomain = userMapper.ollehUserSelectDetail(olleh_id);
			
			if(userDomain == null) {
				return SDPConstants.REQ_LOGIN;
			}
			
			if(!"Y".equals(userDomain.getTermsyn())) {
				return SDPConstants.REQ_AGREEMENT;
			}
		} else if(userMap != null && "naver".equals(userMap.get("idfg"))) {
			String naver_id = StringUtil.defaultStr((String)userMap.get("userid"));			
			
			
			if(naver_id == null || "".equals(naver_id)) {
				return SDPConstants.REQ_LOGIN;
			}
			
			if(!"Y".equals((String)userMap.get("termsyn"))) {
				return SDPConstants.REQ_AGREEMENT;
			}
			
			if(!"Y".equals((String)userMap.get("adultyn"))) {
				return SDPConstants.REQ_SELF_CERT;
			} else {
				if(DateUtil.getAge((String)userMap.get("birthday")) < 19 
						&& (((String)userMap.get("minoritycertnum")) == null || "".equals(((String)userMap.get("minoritycertnum"))))) {
					return SDPConstants.REQ_AGENT_CERT;
				}
			}
		}
		
		return SDPConstants.REQ_NONE;		
	}
	
	/**
	 * 폰빌 제외 CTN 여부를 조회한다.
	 *
	 * @param String ctn : CTN
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public boolean getExcCtn(String ctn) throws Exception {
		int cnt = paymentMapper.getExcCtn(ctn);
		return (cnt > 0);
	}
	
	/**
	 * 이용가능 상품 내역을 수정한다.
	 * 
	 * @param BuyDomain buyDomain     : 삭제할 구매내역 정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public void deletePrdAvail(BuyDomain buyDomain) throws Exception {
		paymentMapper.deletePrdAvail(buyDomain);
	}
}