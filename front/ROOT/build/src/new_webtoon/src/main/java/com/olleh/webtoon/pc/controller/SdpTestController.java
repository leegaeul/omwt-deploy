package com.olleh.webtoon.pc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import JKTFCrypto.JKTFCrypto;

import com.olleh.webtoon.common.dao.advertisement.domain.AdvertisementDomain;
import com.olleh.webtoon.api.sdp.SDPApiService;
import com.olleh.webtoon.api.sdp.SDPConstants;
import com.olleh.webtoon.api.sdp.SDPResponse;
import com.olleh.webtoon.common.util.CookieUtil;

@Controller
public class SdpTestController{
	
	protected static Log logger = LogFactory.getLog(SdpTestController.class);

	@RequestMapping(value = "/sdp/test.kt")
	public ModelAndView advertise(@ModelAttribute("advertisementDomain") AdvertisementDomain advertisementDomain, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("pc/sdp/test");
		
		String olleh_id = "";
		String olleh_ctn = "";
		String olleh_ctype = "";
		String olleh_age = "";
		String userid = "";
		String encid = "";
		String kt_credtid = "";
		
		olleh_ctn = "01043716426";
		
		try{
			userid = CookieUtil.getCookie(request, "kt_userid");
		}catch(Exception e){
			logger.error("CookieUtil kt_userid ERROR : " + e.getMessage());
		}
		
		try{
			encid = CookieUtil.getCookie(request, "kt_encid");
		}catch(Exception e){
			logger.error("CookieUtil kt_encid ERROR : " + e.getMessage());
		}
		
			JKTFCrypto crypto = new JKTFCrypto();
			crypto.DecryptSessionKey(encid);
			byte[] decdata = crypto.DecryptData(userid);
                                                     
/*			if(crypto.getErrorCode() < 0 || decdata == null){ //쿠키 복호화 에러 발생
				logger.info("JKTFCrypto ERROR : " + crypto.getErrorCode());
			} else { //정상적으로 사용자ID를 복호화한 상태
				olleh_id = new String(decdata);
			}
			
			// 쿠키정보에서 크레딧 아이디 조회
			try{
				kt_credtid = CookieUtil.getCookie(request, "kt_credtid");
			}catch(Exception e){
				logger.error("CookieUtil kt_credtid ERROR : " + e.getMessage());
			}
			modelAndView.addObject("kt_credtid1", kt_credtid);
			
			decdata = crypto.DecryptData(kt_credtid);

			if(crypto.getErrorCode() < 0 || decdata == null) {
				logger.info("JKTFCrypto ERROR : " + crypto.getErrorCode());
			} else { //정상적으로 사용자 크레딧 ID를 복호화한 상태
				kt_credtid = new String(decdata);
			}
			
			// 크레딧 아이디로 대표 CTN 조회
			if(kt_credtid != null && !"".equals(kt_credtid)) {
				logger.debug("==================== SDP updateRetrieveRepPhoneNumber Start====================");
				SDPResponse sDPResponse = SDPApiService.updateRetrieveRepPhoneNumber(kt_credtid);
				if(sDPResponse != null) {
					if(SDPConstants.RESULT_SUCCESS.equals(sDPResponse.getResultCode())) {
						olleh_ctn = (String)sDPResponse.getParameter("REPRESENTATIVENUMBER");
					}
				}
				logger.debug("==================== SDP updateRetrieveRepPhoneNumber End====================");
			}
			
			// 대표CTN으로 고객유형, 나이 조회
			if(olleh_ctn != null && !"".equals(olleh_ctn)) {
				logger.debug("==================== SDP getUserInfoWithAge Start====================");
				SDPResponse sDPResponse = SDPApiService.getUserInfoWithAge(olleh_ctn);
				if(sDPResponse != null) {
					if(SDPConstants.RESULT_SUCCESS.equals(sDPResponse.getResultCode())) {
						olleh_ctype = (String)sDPResponse.getParameter("CUSTOMERTYPE");
						olleh_age = (String)sDPResponse.getParameter("USERAGE");

						logger.debug("SDP getUserInfoWithAge CONTRACT_NUM: " + (String)sDPResponse.getParameter("CONTRACT_NUM"));
						logger.debug("SDP getUserInfoWithAge CUSTOMER_ID: " + (String)sDPResponse.getParameter("CUSTOMER_ID"));
						logger.debug("SDP getUserInfoWithAge NS_CONTRACT_NUM: " + (String)sDPResponse.getParameter("NS_CONTRACT_NUM"));
						logger.debug("SDP getUserInfoWithAge NS_CUSTOMER_ID: " + (String)sDPResponse.getParameter("NS_CUSTOMER_ID"));
						logger.debug("SDP getUserInfoWithAge SOC: " + (String)sDPResponse.getParameter("SOC"));
						logger.debug("SDP getUserInfoWithAge PP: " + (String)sDPResponse.getParameter("PP"));
						logger.debug("SDP getUserInfoWithAge MODEL_NAME: " + (String)sDPResponse.getParameter("MODEL_NAME"));
						logger.debug("SDP getUserInfoWithAge CUSTOMER_TYPE: " + (String)sDPResponse.getParameter("CUSTOMER_TYPE"));
						logger.debug("SDP getUserInfoWithAge PREPAID: " + (String)sDPResponse.getParameter("PREPAID"));
						logger.debug("SDP getUserInfoWithAge AGE: " + (String)sDPResponse.getParameter("AGE"));
						logger.debug("SDP getUserInfoWithAge IMSI: " + (String)sDPResponse.getParameter("IMSI"));
						logger.debug("SDP getUserInfoWithAge G3_IND: " + (String)sDPResponse.getParameter("G3_IND"));
						logger.debug("SDP getUserInfoWithAge USER_AGE: " + (String)sDPResponse.getParameter("USER_AGE"));

					}
				}
				logger.debug("==================== SDP getUserInfoWithAge End====================");
			}
			
			
			// 대표CTN으로 가입 여부 확인
			if(olleh_ctn != null && !"".equals(olleh_ctn)) {
				logger.debug("==================== SDP getBasicUserInfo Start====================");
				SDPResponse sDPResponse = SDPApiService.getBasicUserInfo(olleh_ctn);
				if(sDPResponse != null) {
					if(SDPConstants.RESULT_SUCCESS.equals(sDPResponse.getResultCode())) {
						logger.debug("SDP getBasicUserInfo NS_CONTRACT_NUM: " + (String)sDPResponse.getParameter("NS_CONTRACT_NUM"));
						logger.debug("SDP getBasicUserInfo NS_CUSTOMER_ID: " + (String)sDPResponse.getParameter("NS_CUSTOMER_ID"));
						logger.debug("SDP getBasicUserInfo SOC: " + (String)sDPResponse.getParameter("SOC"));
						logger.debug("SDP getBasicUserInfo PP: " + (String)sDPResponse.getParameter("PP"));
						logger.debug("SDP getBasicUserInfo MODEL_NAME: " + (String)sDPResponse.getParameter("MODEL_NAME"));
						logger.debug("SDP getBasicUserInfo CUSTOMER_TYPE: " + (String)sDPResponse.getParameter("CUSTOMER_TYPE"));
						logger.debug("SDP getBasicUserInfo PREPAID: " + (String)sDPResponse.getParameter("PREPAID"));
						logger.debug("SDP getBasicUserInfo AGE: " + (String)sDPResponse.getParameter("MODEL_NAME"));
					}
				}
				logger.debug("==================== SDP getBasicUserInfo End ====================");
			}
			
			// 대표CTN으로 가입 여부 확인
			if(olleh_ctn != null && !"".equals(olleh_ctn)) {
				logger.debug("==================== SDP getBasicUserInfoByPhoneNumber Start====================");
				SDPResponse sDPResponse = SDPApiService.getBasicUserInfoByPhoneNumber(olleh_ctn);
				if(sDPResponse != null) {
					if(SDPConstants.RESULT_SUCCESS.equals(sDPResponse.getResultCode())) {
						logger.debug("SDP getBasicUserInfo SEX: " + (String)sDPResponse.getParameter("SEX"));
						logger.debug("SDP getBasicUserInfo ZIPCODE: " + (String)sDPResponse.getParameter("ZIPCODE"));
						logger.debug("SDP getBasicUserInfo AGE: " + (String)sDPResponse.getParameter("AGE"));
					}
				}
				logger.debug("==================== SDP getBasicUserInfoByPhoneNumber End ====================");
			}*/
			
			// 대표CTN으로 부가 서비스 확인
			if(olleh_ctn != null && !"".equals(olleh_ctn)) {
				logger.debug("==================== SDP checkAllSubscribeStatus Start====================");
				SDPResponse sDPResponse = SDPApiService.checkAllSubscribeStatus("KTFCP", "OMWTAU", olleh_ctn);
				if(sDPResponse != null) {
					if(SDPConstants.RESULT_SUCCESS.equals(sDPResponse.getResultCode())) {
						logger.debug("SDP getBasicUserInfo NS_CONTRACT_NUM: " + (String)sDPResponse.getParameter("NS_CONTRACT_NUM"));
						logger.debug("SDP getBasicUserInfo NS_CUSTOMER_ID: " + (String)sDPResponse.getParameter("NS_CUSTOMER_ID"));
						logger.debug("SDP getBasicUserInfo FEATURE_CODE: " + (String)sDPResponse.getParameter("FEATURE_CODE"));
						logger.debug("SDP getBasicUserInfo EFFECTIVE_DATE: " + (String)sDPResponse.getParameter("EFFECTIVE_DATE"));
						logger.debug("SDP getBasicUserInfo AGE: " + (String)sDPResponse.getParameter("AGE"));
					}
				}
				logger.debug("==================== SDP checkAllSubscribeStatus End ====================");
			}
			
/*			// id/pw로 인증처리
			if(olleh_ctn != null && !"".equals(olleh_ctn)) {
				logger.debug("==================== SDP authenticateUserByIdPwd Start====================");
				SDPResponse sDPResponse = SDPApiService.authenticateUserByIdPwd("dosoun2", "ineedbenz7373");
				if(sDPResponse != null) {
					if(SDPConstants.RESULT_SUCCESS.equals(sDPResponse.getResultCode())) {
						logger.debug("SDP getBasicUserInfo Realname_check_yn: " + (String)sDPResponse.getParameter("Realname_check_yn"));
						logger.debug("SDP getBasicUserInfo token_id: " + (String)sDPResponse.getParameter("token_id"));
						logger.debug("SDP getBasicUserInfo token_expiry: " + (String)sDPResponse.getParameter("token_expiry"));
						logger.debug("SDP getBasicUserInfo phone_number: " + (String)sDPResponse.getParameter("phone_number"));
					}
				}
				logger.debug("==================== SDP authenticateUserByIdPwd End ====================");
			}
			
			// id/pw로 인증처리
			if(olleh_ctn != null && !"".equals(olleh_ctn)) {
				logger.debug("==================== SDP authenticateUserByIdPwdForOlleh Start====================");
				SDPResponse sDPResponse = SDPApiService.authenticateUserByIdPwdForOlleh("dosoun2", "ineedbenz7373");
				if(sDPResponse != null) {
					if(SDPConstants.RESULT_SUCCESS.equals(sDPResponse.getResultCode())) {
						logger.debug("SDP getBasicUserInfo Realname_check_yn: " + (String)sDPResponse.getParameter("Realname_check_yn"));
						logger.debug("SDP getBasicUserInfo token_id: " + (String)sDPResponse.getParameter("token_id"));
						logger.debug("SDP getBasicUserInfo token_expiry: " + (String)sDPResponse.getParameter("token_expiry"));
						logger.debug("SDP getBasicUserInfo phone_number: " + (String)sDPResponse.getParameter("phone_number"));
					}
				}
				logger.debug("==================== SDP authenticateUserByIdPwdForOlleh End ====================");
			}*/
			
			modelAndView.addObject("olleh_id", olleh_id);
			modelAndView.addObject("kt_credtid2", kt_credtid);
			modelAndView.addObject("olleh_id", olleh_id);
			modelAndView.addObject("olleh_ctn", olleh_ctn);
			modelAndView.addObject("olleh_ctype", olleh_ctype);
			modelAndView.addObject("olleh_age", olleh_age);
		

	
		return modelAndView;
		
	}
	
}
