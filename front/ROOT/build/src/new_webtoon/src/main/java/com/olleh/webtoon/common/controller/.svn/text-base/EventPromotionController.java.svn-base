/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ExceptionController.java
 * DESCRIPTION    : 에러처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-05-28      init
 *****************************************************************************/

package com.olleh.webtoon.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.api.sdp.SDPConstants;
import com.olleh.webtoon.common.dao.applay.domain.LinkSmsDomain;
import com.olleh.webtoon.common.dao.eventpromotion.domain.EventContestDomain;
import com.olleh.webtoon.common.dao.eventpromotion.domain.EventCouponDomain;
import com.olleh.webtoon.common.dao.eventpromotion.domain.EventPromotionDomain;
import com.olleh.webtoon.common.dao.eventpromotion.service.iface.EventPromotionService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AesCipherUtil;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.SmsSendUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller("CommonEventPromotionController")
public class EventPromotionController {
			
	protected static Log logger = LogFactory.getLog(EventPromotionController.class);
	private static final String DELIM = "; ";
			
	@Autowired
	EventPromotionService eventPromotionService;
	
	
	/**
	 * 아이스크림 이벤트 응모를 요청하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/icecreamEventRegistProc.kt")
	public ModelAndView icecreamEventRegistProc(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain, 
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		//신규 가입자 및 오픈아이디 여부 체크
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		
		eventPromotionDomain.setRegid(user.getEmail());
		eventPromotionDomain.setRegdt(DateUtil.getNowDate(1));
		
		String joinyn = eventPromotionService.getEventJoinyn(eventPromotionDomain);
		
		if(!"open".equals(user.getIdfg()) || !"Y".equals(joinyn))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "아깝~ T.T '지금당장' 이벤트는\n웹툰 ID 신규 가입자만 가능합니다.\n'소문내기' 이벤트 도전!");
			return modelAndView;
		}
		
		try {
			String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
			String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
			String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
				
			AesCipherUtil aesCipherUtil = new AesCipherUtil();
			
			String u_otp_sc = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_cert_num"));
			String [] otp_sc = u_otp_sc.split(DELIM);
				
			// 저장된 인증번호
			String otp = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, otp_sc[0].getBytes()));
			
			String otp_time = StringUtil.defaultStr(aesCipherUtil.decrypt(key, initialVector, otp_sc[1].getBytes()));
			
			// 인증시간 체크
			if(Long.parseLong(DateUtil.getNowDate(1)) > Long.parseLong(otp_time) + (60 * 10)) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errorcode", SDPConstants.ERROR_SMS_TIMEOUT);
				modelAndView.addObject("errormsg", SDPConstants.ERROR_SMS_TIMEOUT_STRING);
				
				return modelAndView;
			}
			
			// 인증번호 체크
			if(!otp.equals(eventPromotionDomain.getCertNum())) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errorcode", SDPConstants.ERROR_SMS_INVALID);
				modelAndView.addObject("errormsg", SDPConstants.ERROR_SMS_INVALID_STRING);
				
				return modelAndView;
			}
			
			modelAndView.addObject("erroryn", "N");
			
			//쿠키 삭제
			CookieUtil.deleteCookie(response, "u_cert_num", cookieDomain);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errorcode", SDPConstants.ERROR_SYSTEM);
			modelAndView.addObject("errormsg", SDPConstants.ERROR_SYSTEM_STRING);
			
			return modelAndView;
		}
		
		//참여 여부 체크
		String duplicateyn = eventPromotionService.geIceEventDuplicateyn(eventPromotionDomain);
		if("Y".equals(duplicateyn))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "아잉~ 욕심쟁이~! 이미 참여하셨네요!\n'소문내기' 이벤트에 도전~");
			return modelAndView;
		}
		
		//에러 여부 셋팅
		modelAndView.addObject("erroryn", "N");
		
		eventPromotionService.iceEventRegistProc(eventPromotionDomain);
		
		return modelAndView;
	}
	
	/**
	 * 휴대폰으로 인증번호를 발송하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/requestCertNum.kt")
	public ModelAndView requestCertNum(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain, 
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		
		eventPromotionDomain.setRegid(user.getEmail());
		
		//신규 가입자 및 오픈아이디 여부 체크
		String joinyn = eventPromotionService.getEventJoinyn(eventPromotionDomain);
		
		if(!"open".equals(user.getIdfg()) || !"Y".equals(joinyn))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "아깝~ T.T '지금당장' 이벤트는\n웹툰 ID 신규 가입자만 가능합니다.\n'소문내기' 이벤트 도전!");
			return modelAndView;
		}
		
		//참여 여부 체크
		String duplicateyn = eventPromotionService.geIceEventDuplicateyn(eventPromotionDomain);
		if("Y".equals(duplicateyn))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "아잉~ 욕심쟁이~! 이미 참여하셨네요!\n'소문내기' 이벤트에 도전~");
			return modelAndView;
		}
		
		try {
			String otp = Integer.toString((int)(Math.random() * 1000000));
			for(int i = 0; i < (6 - otp.length()); i++) {
				otp = "0" + otp;
			}
			
			String msg = "올레마켓웹툰 인증번호 [" + otp + "]";
			boolean result = SmsSendUtil.sendSmsResult(eventPromotionDomain.getPhonenum(), "", msg);
			
			if(result) {
				String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
				String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
				String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
				String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
				
				AesCipherUtil aesCipherUtil = new AesCipherUtil();
				
				// 인증정보 쿠키 저장
				String u_otp = aesCipherUtil.encrypt(key, initialVector, otp.getBytes());
				String u_otp_time = aesCipherUtil.encrypt(key, initialVector, DateUtil.getNowDate(1).getBytes());
				String u_otp_ctn = aesCipherUtil.encrypt(key, initialVector, eventPromotionDomain.getPhonenum().getBytes());
				
				String otp_s = u_otp + DELIM + u_otp_time + DELIM + u_otp_ctn + DELIM + "" + DELIM + "";
				CookieUtil.setCookie(response, "u_cert_num", otp_s, (60 * 10), cookiePath, cookieDomain);
				
				modelAndView.addObject("erroryn", "N");
			} else {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errorcode", SDPConstants.ERROR_SMS_SERVICE);
				modelAndView.addObject("errormsg", SDPConstants.ERROR_SMS_SERVICE_STRING);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errorcode", SDPConstants.ERROR_SYSTEM);
			modelAndView.addObject("errormsg", SDPConstants.ERROR_SYSTEM_STRING);
		}
		
		return modelAndView;
	}
	
	/**
	 * 아이스크림이벤트 SNS 응원글 목록을 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getIceSnsComment.kt")
	public ModelAndView getIceSnsComment(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		//테이블 변경으로 인한 구분자 추가 - 20141022
		eventPromotionDomain.setEventfg("ice");
		
		//SNS 응원글 목록 총 갯수 조회
		int totalCnt = eventPromotionService.getIceSnsCmtTotalCnt(eventPromotionDomain);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//SNS 응원글 목록 조회
		List<EventPromotionDomain> snsCommentList = eventPromotionService.getIceSnsCmtList(eventPromotionDomain);
		modelAndView.addObject("snsCommentList", snsCommentList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 아이스크림이벤트 SNS 응원글을 등록하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/iceSnsEventRegistProc.kt")
	public ModelAndView iceSnsEventRegistProc(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain, HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		eventPromotionDomain.setRegid(user.getEmail());
		eventPromotionDomain.setIdfg(user.getIdfg());
		eventPromotionDomain.setRegdt(DateUtil.getNowDate(1));

		eventPromotionService.iceSnsEventRegistProc(eventPromotionDomain);
		
		return modelAndView;
	}
	
	
	/**
	 * 아이스크림이벤트 당첨자를 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/checkIceEventPrizeWinner.kt")
	public ModelAndView checkIceEventPrizeWinner(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//flat추가
		eventPromotionDomain.setEventfg("ice");
		
		//이벤트1 당첨여부 조회
		String memberWindate = eventPromotionService.getIceMemberEventPrizeWinner(eventPromotionDomain);
		
		//이벤트2 당첨여부 조회
		List<String> snsWindate = eventPromotionService.getIceSnsEventPrizeWinner(eventPromotionDomain);
		
		if((memberWindate == null || "".equals(memberWindate)) && snsWindate.size() == 0)
		{
			modelAndView.addObject("winyn", "N");
			return modelAndView;
		}
		
		modelAndView.addObject("memberWindate", memberWindate);
		modelAndView.addObject("snsWindate", snsWindate);
		modelAndView.addObject("winyn", "Y");
		
		return modelAndView;
	}	
	
	/**
	 * 필명을 파라미터로 받아 
	 * 중복여부를 json을 보내주는 RequestMapping 메소드
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/existContestpnm.kt")
	public ModelAndView existContestPnm(@ModelAttribute("eventContestDomain") EventContestDomain eventContestDomain) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		try {
			
			//에러여부를 세팅한다.
			modelAndView.addObject("erroryn", "N");
			
			int getpnmCount = eventPromotionService.getContestPnmCheckCnt(eventContestDomain);
			
			if(eventContestDomain != null) {
				
				if(getpnmCount > 0) {
					modelAndView.addObject("existYn", "Y");
				} else {
					modelAndView.addObject("existYn", "N");	
				}
				
			} else {
				modelAndView.addObject("existYn", "Y");
			}
						
		} catch(Exception e) {			
			//예외가 발생하면 기존 데이터를 clear하고 에러여부를 Y으로 세팅한다.
			modelAndView.clear();
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.select"));
		}
		
		//JsonView를 View로 지정한다.
		modelAndView.setViewName("mappingJacksonJsonView");		
		
		return modelAndView;
	}
	
	/**
	 * 접수현황을 json을 보내주는 RequestMapping 메소드
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getContestCount.kt")
	public ModelAndView getContestCount(@ModelAttribute("eventContestDomain") EventContestDomain eventContestDomain) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		try {
			
			//에러여부를 세팅한다.
			modelAndView.addObject("erroryn", "N");
			
			int totalcount = eventPromotionService.getContestCnt(eventContestDomain);
			modelAndView.addObject("totalcount", totalcount);
			
		} catch(Exception e) {			
			//예외가 발생하면 기존 데이터를 clear하고 에러여부를 Y으로 세팅한다.
			modelAndView.clear();
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.select"));
		}
		
		//JsonView를 View로 지정한다.
		modelAndView.setViewName("mappingJacksonJsonView");		
		
		return modelAndView;
	}
	
	
	/**
	 * 공모전이벤트 지원내용을 등록하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/contestRegistProc.kt")
	public ModelAndView contestRegistProc(@ModelAttribute("eventContestDomain") EventContestDomain eventContestDomain, HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		eventContestDomain.setRegdt(DateUtil.getNowDate(1));

		eventPromotionService.contestRegistProc(eventContestDomain);
		
		return modelAndView;
	}
	
	/**
	 * SNS 이벤트 응원글 목록을 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getSnsComment.kt")
	public ModelAndView getSnsComment(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		//SNS 응원글 목록 총 갯수 조회
		int totalCnt = eventPromotionService.getIceSnsCmtTotalCnt(eventPromotionDomain);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//SNS 응원글 목록 조회
		List<EventPromotionDomain> snsCommentList = eventPromotionService.getIceSnsCmtList(eventPromotionDomain);
		modelAndView.addObject("snsCommentList", snsCommentList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * SNS이벤트 당첨자를 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getSnsEventPrizeWinner.kt")
	public ModelAndView getSnsEventPrizeWinner(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//SNS이벤트 당첨여부 조회
		List<String> snsWindate = eventPromotionService.getIceSnsEventPrizeWinner(eventPromotionDomain);
		
		if(snsWindate == null || snsWindate.size() == 0)
		{
			modelAndView.addObject("winyn", "N");
			return modelAndView;
		}
		
		modelAndView.addObject("snsWindate", snsWindate);
		modelAndView.addObject("winyn", "Y");
		
		return modelAndView;
	}	
	
	
	/**
	 * 쿠폰이벤트 쿠폰을 등록하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/couponEventRegistProc.kt")
	public ModelAndView couponEventRegistProc(@ModelAttribute("eventCouponDomain") EventCouponDomain eventCouponDomain, HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//에러여부를 세팅한다.
		modelAndView.addObject("erroryn", "N");
		
		//로그인 체크
		if(!AuthUtil.isLogin(request))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "ERROR01");
			
			return modelAndView;
		}
		
		EventCouponDomain eventCoupon = eventPromotionService.getCouponEventByCouponnum(eventCouponDomain.getCouponnum());
		
		//쿠폰이 이미 등록 되었을 경우
		if(eventCoupon != null && "Y".equals(eventCoupon.getUseyn())){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "ERROR02");
			
			return modelAndView;
		}
		
		//쿠폰 번호가 없을 경우
		if(eventCoupon == null){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "ERROR03");
			
			return modelAndView;
		}
		
		//쿠폰 등록
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);		
		eventCouponDomain.setRegid(user.getEmail());
		eventCouponDomain.setIdfg(user.getIdfg());
		eventCouponDomain.setRegdt(DateUtil.getNowDate(1));
		
		EventCouponDomain eventCouponById = eventPromotionService.getCouponEventById(eventCouponDomain);
		
		//아이디가 이미 등록 되었을 경우
		if(eventCouponById != null && "Y".equals(eventCouponById.getUseyn())){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "ERROR04");
			
			return modelAndView;
		}

		eventPromotionService.couponRegistProc(eventCouponDomain);
		
		return modelAndView;
	}
	
	
	/**
	 * Plus쿠폰이벤트 등록하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/couponPlusEventRegistProc.kt")
	public ModelAndView couponPlusEventRegistProc(@ModelAttribute("eventCouponDomain") EventCouponDomain eventCouponDomain, HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//에러여부를 세팅한다.
		modelAndView.addObject("erroryn", "N");
		
		//로그인 체크
		if(!AuthUtil.isLogin(request))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "ERROR01");
			
			return modelAndView;
		}
		
		//쿠폰 등록
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);		
		eventCouponDomain.setRegid(user.getEmail());
		eventCouponDomain.setIdfg(user.getIdfg());
		eventCouponDomain.setRegdt(DateUtil.getNowDate(1));
		
		EventCouponDomain eventCouponById = eventPromotionService.getCouponEventById(eventCouponDomain);
		
		//쿠폰 번호가 없는 경우
		if(eventCouponById == null){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "ERROR03");
			
			return modelAndView;
		}
		
		//이미 응모 한 경우		
		EventCouponDomain eventCoupon = eventPromotionService.getCouponPlusEventById(eventCouponDomain);
		
		//이미 등록 되었을 경우
		if(eventCoupon != null && !"".equals(eventCoupon.getPhonenum())){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "ERROR02");
			
			return modelAndView;
		}

		eventPromotionService.couponPlusEventRegistProc(eventCouponDomain);
		
		return modelAndView;
	}
	
	/**
	 * 공모전 응모 완료SMS를 보내주는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/eventpromotion/sendSms.kt")
	public ModelAndView sendLinkSms(LinkSmsDomain linkSmsDomain, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//로그인 여부 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "로그인 후 이용해주세요.");
			return modelAndView;
		}
		
		//회원정보 복호화
		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		modelAndView.addObject("erroryn", "N");
		
		//아이디 구분
		linkSmsDomain.setIdfg(userDomain.getIdfg());
		//이메일 아이디
		linkSmsDomain.setRegid(userDomain.getEmail());
		//현재날짜시간
		linkSmsDomain.setRegdt(DateUtil.getNowDate(1));
		//마켓url받기(구분자 = 0) 
		linkSmsDomain.setAppseq(0);
		linkSmsDomain.setMarketyn("Y");

			//SMS 전송
			if(SmsSendUtil.sendSmsResult(linkSmsDomain.getDestphone(), "", linkSmsDomain.getMsg())){
				
				//SMS 전송 성공 시 DB에 저장

			}

		
		modelAndView.addObject("linkSmsDomain", null);
		
		return modelAndView;		
	}
	
	/**
	 * SNS이벤트 당첨자를 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getCouponEventPrizeWinner.kt")
	public ModelAndView getCouponEventPrizeWinner(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//이벤트 당첨여부 조회
		List<EventPromotionDomain> winList = eventPromotionService.getCouponEventPrizeWinner(eventPromotionDomain);
		
		if(winList == null || winList.size() == 0)
		{
			modelAndView.addObject("winyn", "N");
			return modelAndView;
		}
		
		modelAndView.addObject("winList", winList);
		modelAndView.addObject("winyn", "Y");
		
		return modelAndView;
	}
	
	/**
	 * 공모전이벤트(댓글, 스티커)를 등록하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/contestEventRegistProc.kt")
	public ModelAndView contestEventRegistProc(@ModelAttribute("eventContestDomain") EventContestDomain eventContestDomain, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		try {
		
			if(!AuthUtil.isLogin(request)){
				throw new Exception();
			}
			
			UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
			eventContestDomain.setIdfg(user.getIdfg());
			eventContestDomain.setRegid(user.getEmail());
			
			eventPromotionService.contestEventRegistProc(eventContestDomain);
		
		} catch (Exception e) {
			logger.debug(e);
			// TODO: handle exception
		}
		
		return modelAndView;
	}
	
	/**
	 * (공통)SNS 응원글을 등록하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/commonSnsEventRegistProc.kt")
	public ModelAndView commonSnsEventRegistProc(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain, HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		eventPromotionDomain.setRegid(user.getEmail());
		eventPromotionDomain.setIdfg(user.getIdfg());
		eventPromotionDomain.setRegdt(DateUtil.getNowDate(1));

		eventPromotionService.iceSnsEventRegistProc(eventPromotionDomain);
		
		return modelAndView;
	}
	
	/**
	 * (공통)SNS 응원글 목록을 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getCommonSnsComment.kt")
	public ModelAndView getCommonSnsComment(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		//총 갯수 조회
		int totalCnt = eventPromotionService.getIceSnsCmtTotalCnt(eventPromotionDomain);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//SNS 응원글 목록 조회
		List<EventPromotionDomain> snsCommentList = eventPromotionService.getIceSnsCmtList(eventPromotionDomain);
		modelAndView.addObject("snsCommentList", snsCommentList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 냄보소이벤트 당첨자를 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/checkNaembosoEventWinner.kt")
	public ModelAndView checkNaembosoEventWinner(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//이벤트1 당첨여부 조회
		List<EventPromotionDomain> snsWindate = eventPromotionService.getNaembosoSnsEventPrizeWinner(eventPromotionDomain);
		
		//이벤트2 당첨여부 조회
		List<EventPromotionDomain> commentWindate = eventPromotionService.getNaembosoCommentEventPrizeWinner(eventPromotionDomain);
		
		//이벤트3 당첨여부 조회
		List<EventPromotionDomain> photoWindate = eventPromotionService.getNaembosoPhotoEventPrizeWinner(eventPromotionDomain);
		
		if(snsWindate.size() == 0)
		{
			modelAndView.addObject("winyn", "N");
		}else{
			modelAndView.addObject("snsWindate", snsWindate);
			modelAndView.addObject("winyn", "Y");
		}
		
		if(commentWindate.size() == 0)
		{
			modelAndView.addObject("wincommentyn", "N");
		}else{
			modelAndView.addObject("commentWindate", commentWindate);
			modelAndView.addObject("wincommentyn", "Y");
		}
		
		if(photoWindate.size() == 0)
		{
			modelAndView.addObject("winphotoyn", "N");
		}else{
			modelAndView.addObject("photoWindate", photoWindate);
			modelAndView.addObject("winphotoyn", "Y");
		}
		
		return modelAndView;
	}
	
	
	
	
	//공유하기 공통
	/**
	 * (공통)SNS 이벤트 참여여부를 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getSnsEventJoinyn.kt")
	public ModelAndView getSnsEventJoinyn(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain, HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		eventPromotionDomain.setRegid(user.getEmail());
		eventPromotionDomain.setIdfg(user.getIdfg());
		eventPromotionDomain.setRegdt(DateUtil.getNowDate(1));

		int joinHisCnt = eventPromotionService.getSnsEventJoinHisCnt(eventPromotionDomain);
		modelAndView.addObject("joinyn", joinHisCnt > 0 ? "Y" : "N");
		
		return modelAndView;
	}
	
	/**
	 * SNS에 이벤트 참여하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/snsEventRegistProc.kt")
	public ModelAndView snsEventRegistProc(@ModelAttribute("eventPromotionDomain") EventPromotionDomain eventPromotionDomain, HttpServletRequest request) throws Exception
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}

		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		eventPromotionDomain.setRegid(user.getEmail());
		eventPromotionDomain.setIdfg(user.getIdfg());
		eventPromotionDomain.setRegdt(DateUtil.getNowDate(1));
		
		//preview(Y/N) 추가
		eventPromotionService.snsEventRegistProc(eventPromotionDomain);
		
		return modelAndView;
	}
	
//	/**
//	 * 로그인 정보를 리턴하는 RequestMapping 메소드
//	 * @return ModelAndView modelAndView : ModelAndView 객체
//	 */
//	@RequestMapping(value = "/common/getLoginInfo.kt")
//	public ModelAndView getLoginInfo(HttpServletRequest request) {
//		
//		ModelAndView modelAndView = new ModelAndView();
//		
//		modelAndView.setViewName("mappingJacksonJsonView");
//		
//		try {
//			
//			if(!AuthUtil.isLogin(request)){
//				modelAndView.addObject("isLogin", false);
//			}else {
//				modelAndView.addObject("isLogin", true);
//			}
//		
//		} catch (Exception e) {
//			logger.debug(e);
//			// TODO: handle exception
//		}
//		
//		return modelAndView;
//	}
//	
//	/**
//	 * 2주년 이벤트 참여 여부를 조회하는 RequestMapping 메소드
//	 * @return ModelAndView modelAndView : ModelAndView 객체
//	 */
//	@RequestMapping(value = "/common/getMissionEventJoinyn.kt")
//	public ModelAndView getMissionEventJoinyn(@ModelAttribute("eventMissionDomain") EventMissionDomain eventMissionDomain, HttpServletRequest request) {
//		
//		ModelAndView modelAndView = new ModelAndView();
//		
//		modelAndView.setViewName("mappingJacksonJsonView");
//		
//		try {
//			
//			if(!AuthUtil.isLogin(request)){
//				throw new Exception();
//			}
//			
//			UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
//			eventMissionDomain.setIdfg(user.getIdfg());
//			eventMissionDomain.setRegid(user.getEmail());
//			
//			//이벤트 참여 여부 조회
//			String regdt = eventPromotionService.getMissionEventRegdt(eventMissionDomain);
//			if(regdt == null || regdt.length() < 1) {
//				modelAndView.addObject("joinyn", "N");
//				return modelAndView;
//			}
//			
//			//참여 내역 조회
//			List<EventMissionDomain> missionList = eventPromotionService.getMissionEventList(eventMissionDomain);
//			modelAndView.addObject("joinyn", "Y");
//			modelAndView.addObject("missionList", missionList);
//		
//		} catch (Exception e) {
//			logger.debug(e);
//			// TODO: handle exception
//		}
//		
//		return modelAndView;
//	}
//	
//	/**
//	 * 2주년 이벤트 참여 처리하는 RequestMapping 메소드
//	 * @return ModelAndView modelAndView : ModelAndView 객체
//	 */
//	@RequestMapping(value = "/common/missionEventRegistProc.kt")
//	public ModelAndView missionEventRegistProc(@ModelAttribute("eventMissionDomain") EventMissionDomain eventMissionDomain, HttpServletRequest request) {
//		
//		ModelAndView modelAndView = new ModelAndView();
//		
//		modelAndView.setViewName("mappingJacksonJsonView");
//		
//		try {
//			
//			if(!AuthUtil.isLogin(request)){
//				throw new Exception();
//			}
//			
//			UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
//			eventMissionDomain.setIdfg(user.getIdfg());
//			eventMissionDomain.setRegid(user.getEmail());
//			eventMissionDomain.setRegdt(DateUtil.getNowDate(1));
//			
//			//이벤트 참여 여부 조회
//			String regdt = eventPromotionService.getMissionEventRegdt(eventMissionDomain);
//			if(regdt != null && regdt.length() > 0) {
//				modelAndView.addObject("erroryn", "Y");
//				modelAndView.addObject("errormsg", "이미 참여 하였습니다.");
//				return modelAndView;
//			}
//			
//			modelAndView.addObject("erroryn", "N");
//			eventPromotionService.missionEventRegistProc(eventMissionDomain);
//		
//		} catch (Exception e) {
//			logger.debug(e);
//			// TODO: handle exception
//		}
//		
//		return modelAndView;
//	}
//	
//	/**
//	 * 2주년 이벤트 미션 현황을 조회하는 RequestMapping 메소드
//	 * @return ModelAndView modelAndView : ModelAndView 객체
//	 */
//	@RequestMapping(value = "/common/getMyMission.kt")
//	public ModelAndView getMyMission(@ModelAttribute("eventMissionDomain") EventMissionDomain eventMissionDomain, HttpServletRequest request) {
//		
//		ModelAndView modelAndView = new ModelAndView();
//		
//		modelAndView.setViewName("mappingJacksonJsonView");
//		
//		try {
//			
//			if(!AuthUtil.isLogin(request)){
//				throw new Exception();
//			}
//			
//			UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
//			eventMissionDomain.setIdfg(user.getIdfg());
//			eventMissionDomain.setRegid(user.getEmail());
//			
//			//이벤트 참여 여부 조회
//			String regdt = eventPromotionService.getMissionEventRegdt(eventMissionDomain);
//			if(regdt == null || regdt.length() < 1) {
//				modelAndView.addObject("erroryn", "Y");
//				modelAndView.addObject("errormsg", "이벤트 참여 후 이용해주세요.");
//				return modelAndView;
//			}
//			
//			modelAndView.addObject("erroryn", "N");
//			eventMissionDomain.setRegdt(regdt);
//			
//			eventMissionDomain.setLogincnt(eventPromotionService.getLoginTotalCnt(eventMissionDomain));
//			eventMissionDomain.setRumorcnt(eventPromotionService.getRumorTotalCnt(eventMissionDomain));
//			eventMissionDomain.setStickercnt(eventPromotionService.getStickerTotalCnt(eventMissionDomain));
//			eventMissionDomain.setCommentcnt(eventPromotionService.getCommentTotalCnt(eventMissionDomain));
//			eventMissionDomain.setSnscnt(eventPromotionService.getSnsTotalCnt(eventMissionDomain));
//						
//			String updateyn = eventPromotionService.getUpdateyn(eventMissionDomain);
//			
//			modelAndView.addObject("loginTotalCnt", eventMissionDomain.getLogincnt());
//			modelAndView.addObject("rumorTotalCnt", eventMissionDomain.getRumorcnt());
//			modelAndView.addObject("stickerTotalCnt", eventMissionDomain.getStickercnt());
//			modelAndView.addObject("commentTotalCnt", eventMissionDomain.getCommentcnt());
//			modelAndView.addObject("snsTotalCnt", eventMissionDomain.getSnscnt());
//			modelAndView.addObject("updateyn", updateyn);
//			
//		} catch (Exception e) {
//			logger.debug(e);
//			// TODO: handle exception
//		}
//		
//		return modelAndView;
//	}
//	
//	/**
//	 * 2주년 이벤트 미션 참여 처리하는 RequestMapping 메소드
//	 * @return ModelAndView modelAndView : ModelAndView 객체
//	 */
//	@RequestMapping(value = "/common/missionRegistProc.kt")
//	public ModelAndView missionRegistProc(@ModelAttribute("eventMissionDomain") EventMissionDomain eventMissionDomain, HttpServletRequest request) {
//		
//		ModelAndView modelAndView = new ModelAndView();
//		
//		modelAndView.setViewName("mappingJacksonJsonView");
//		
//		try {
//			
//			if(!AuthUtil.isLogin(request)){
//				throw new Exception();
//			}
//			
//			UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
//			eventMissionDomain.setIdfg(user.getIdfg());
//			eventMissionDomain.setRegid(user.getEmail());
//			eventMissionDomain.setRegdt(DateUtil.getNowDate(1));
//			
//			//1.이벤트 참여 여부 조회
//			String regdt = eventPromotionService.getMissionEventRegdt(eventMissionDomain);
//			if(regdt == null || regdt.length() < 1) {
//				modelAndView.addObject("erroryn", "Y");
//				modelAndView.addObject("errormsg", "이벤트 참여 후 이용해주세요.");
//				return modelAndView;
//			}
//			
//			//2.출석체크
//			int todayHisCnt = eventPromotionService.getMissionTodayHisCnt(eventMissionDomain);
//			if(todayHisCnt < 1) {
//				eventMissionDomain.setMissionfg("login");
//				eventPromotionService.missionRegistProc(eventMissionDomain);
//			}
//			
//			//2.미션처리
//			eventMissionDomain.setRegdt(regdt);
//			eventPromotionService.insertMissionList(eventMissionDomain);
//			
//		} catch (Exception e) {
//			logger.debug(e);
//			// TODO: handle exception
//		}
//		
//		return modelAndView;
//	}
}