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

package com.olleh.webtoon.common.dao.payment.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.api.sdp.SDPConstants;
import com.olleh.webtoon.common.dao.payment.domain.OrderDomain;
import com.olleh.webtoon.common.dao.payment.domain.PaymentDomain;
import com.olleh.webtoon.common.dao.payment.persistence.PaymentMapper;
import com.olleh.webtoon.common.dao.payment.service.iface.PaymentServiceIface;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.persistence.UserMapper;
import com.olleh.webtoon.common.util.AesCipherUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Service("paymentService")
@Repository
public class PaymentServiceImpl implements PaymentServiceIface {
	
	protected static Log logger = LogFactory.getLog(PaymentServiceImpl.class);
	
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
	@Transactional(readOnly=true)
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
	@Transactional(readOnly=true)
	public int getOrderSum(UserDomain userDomain) {
		int amount = paymentMapper.getOrderSum(userDomain);
		
		return amount;
	}
	
	/**
	 * 결제정보를 등록한다.
	 *
	 * @param PaymentDomain paymentDomain : 결제정보
	 */
	@Transactional(readOnly=false)
	public void registPayment(PaymentDomain paymentDomain) throws Exception {
		paymentMapper.registPayment(paymentDomain);
	}
	
	/**
	 * 결제정보를 수정한다.
	 *
	 * @param PaymentDomain paymentDomain : 결제정보
	 */
	@Transactional(readOnly=false)
	public void modifyPayment(PaymentDomain paymentDomain) throws Exception {
		paymentMapper.modifyPayment(paymentDomain);
	}
	
	/**
	 * 주문정보를 등록한다.
	 *
	 * @param OrderDomain orderDomain : 주문정보
	 */
	@Transactional(readOnly=false)
	public void registOrder(OrderDomain orderDomain) throws Exception {
		paymentMapper.registOrder(orderDomain);	
	}
	
	/**
	 * 사용자 정보를 조회한다.
	 *
	 * @param HttpServletRequest req : HTTP Request
	 * @return UserDomain userDomain: 사용자 정보
	 */
	@Transactional(readOnly=true)
	public UserDomain getUserInfo(HttpServletRequest req) throws Exception {
		// 웹툰 회원 정보
		String cookieName = MessageUtil.getSystemMessage("system.cookie.name");
		String webtoon_user = StringUtil.defaultStr(CookieUtil.getCookie(req, cookieName));
		
		// 올레 회원 ID
		String kt_userid = StringUtil.defaultStr(CookieUtil.getCookie(req, "kt_userid"));
		
		UserDomain userDomain = new UserDomain();
		
		// 암호화 모듈
		AesCipherUtil aesCipherUtil = new AesCipherUtil();		
		String key = MessageUtil.getSystemMessage("system.cookie.aes.key");
		String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv");
				
		if(!"".equals(webtoon_user)) {
			String decuserinfo = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, webtoon_user.getBytes()));
			UserDomain user = new UserDomain(decuserinfo);
			
			user = userMapper.selectUserInfoByEmail(user.getEmail());
			userDomain.setIdfg("open");
			userDomain.setUserid(user.getEmail());
			userDomain.setEmail(user.getEmail());
			userDomain.setCtype("");
			userDomain.setCtn("");
			
		} else if(!"".equals(kt_userid)) {
			String encuserid = StringUtil.defaultStr(CookieUtil.getCookie(req, "u_olleh_id"), "");
			String decuserid = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, encuserid.getBytes()));
			
			// 고객유형 체크
			String u_olleh_ctype = StringUtil.defaultStr(CookieUtil.getCookie(req, "u_olleh_ctype"));
			String olleh_ctype = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, u_olleh_ctype.getBytes()));
			
			// 대표 CTN 체크
			String u_olleh_ctn = StringUtil.defaultStr(CookieUtil.getCookie(req, "u_olleh_ctn"));
			String olleh_ctn = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, u_olleh_ctn.getBytes()));
			
			OllehUserDomain user = userMapper.ollehUserSelectDetail(decuserid);
			userDomain.setIdfg("olleh");
			userDomain.setUserid(user.getOllehid());
			userDomain.setEmail("");
			userDomain.setCtype(olleh_ctype);
			userDomain.setCtn(olleh_ctn);
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
	@Transactional(readOnly=true)
	public int getUserStatus(HttpServletRequest req) throws Exception {
		// 웹툰 회원 정보
		String cookieName = MessageUtil.getSystemMessage("system.cookie.name");
		String webtoon_user = StringUtil.defaultStr(CookieUtil.getCookie(req, cookieName));
		
		// 올레 회원 ID
		String kt_userid = StringUtil.defaultStr(CookieUtil.getCookie(req, "kt_userid"));
		
		// 1. 로그인 여부 체크
		if("".equals(webtoon_user) && "".equals(kt_userid)) {
			return SDPConstants.REQ_LOGIN;
		}
				
		// 2. 약관 동의 여부, 본인 인증 여부 체크
		AesCipherUtil aesCipherUtil = new AesCipherUtil();		
		String key = MessageUtil.getSystemMessage("system.cookie.aes.key");
		String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv");
		
		// 오픈아이디인 경우
		if(!"".equals(webtoon_user)) {
			String decuserinfo = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, webtoon_user.getBytes()));
			UserDomain userDomain = new UserDomain(decuserinfo);
			userDomain = userMapper.selectUserInfoByEmail(userDomain.getEmail());
			
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
		} else if(!"".equals(kt_userid)) {
			String u_olleh_id = StringUtil.defaultStr(CookieUtil.getCookie(req, "u_olleh_id"), "");
			String olleh_id = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, u_olleh_id.getBytes()));
			
			String u_olleh_ctype = StringUtil.defaultStr(CookieUtil.getCookie(req, "u_olleh_ctype"), "");
			String olleh_ctype = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, u_olleh_ctype.getBytes()));
			
			OllehUserDomain userDomain = userMapper.ollehUserSelectDetail(olleh_id);
			
			if(userDomain == null) {
				return SDPConstants.REQ_LOGIN;
			}
			
			if(!"Y".equals(userDomain.getTermsyn())) {
				return SDPConstants.REQ_AGREEMENT;
			}
		}
		
		return SDPConstants.REQ_NONE;		
	}
	
	/**
	 * 폰빌 제외 CTN 여부를 조회한다.
	 *
	 * @param String ctn : CTN
	 */
	@Transactional(readOnly=true)
	public boolean getExcCtn(String ctn) throws Exception {
		int cnt = paymentMapper.getExcCtn(ctn);
		return (cnt > 0);
	}
}