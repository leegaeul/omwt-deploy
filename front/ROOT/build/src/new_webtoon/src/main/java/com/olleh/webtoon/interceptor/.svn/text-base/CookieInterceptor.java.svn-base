/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : CookieInterceptor.java
 * DESCRIPTION    : 웹툰 및 올레닷컴 쿠키가 있는지 확인하여 있을 경우 파싱해서 
 *                  Request 객체에 넣어주는 인터셉터 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-15      init
 *****************************************************************************/

package com.olleh.webtoon.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import JKTFCrypto.JKTFCrypto;

import com.olleh.webtoon.api.sdp.SDPApiService;
import com.olleh.webtoon.api.sdp.SDPConstants;
import com.olleh.webtoon.api.sdp.SDPResponse;
import com.olleh.webtoon.common.dao.logo.domain.LogoDomain;
import com.olleh.webtoon.common.dao.logo.service.iface.LogoService;
import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.premium.domain.ProductHistoryDomain;
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AesCipherUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Component
public class CookieInterceptor extends HandlerInterceptorAdapter {

	protected static Log logger = LogFactory.getLog(CookieInterceptor.class);

	@Autowired
	PremiumService premiumService;

	@Autowired
	OrderbuyService orderbuyService;
	
	@Autowired
	UserServiceIface userServiceIface;
	
	@Autowired
	LogoService logoService;
		
	/**
	 * 웹툰 쿠키와 올레닷컴 쿠키를 확인하여 로그인한 사용자인지 여부를 
	 * HttpServletRequest 객체에 세팅한다.
	 * 실제 ID는 성능을 위해 필요한 경우 해당 컨트롤러에서 복호화한다.
	 * 
	 * @param HttpServletRequest request   : HttpServletRequest 객체
     * @param HttpServletResponse response : HttpServletResponse 객체
     * @param Object handler               : controller 객체
     * @return boolean                     : 처리 성공 여부 (false를 반환하면 controller가 실행되지 않는다.)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		    	
		if( 
				request.getRequestURI().contains( "/test/" )
				|| (!request.getRequestURI().contains( "/old/api/" ) && request.getRequestURI().contains( "/api/" ))
				|| (!request.getRequestURI().contains( "/old/api/" ) && request.getRequestURI().contains( "/api/" ))
				|| (!request.getRequestURI().contains( "/old/api/" ) && request.getRequestURI().contains( "/web/" ))
				|| (!request.getRequestURI().contains( "/old/api/" ) && request.getRequestURI().contains( "/ot/" ))
		)
			return true;
		
		try {
			
			//모바일 로고
			LogoDomain mobileLogo = logoService.getLogo("MOBILE");
			request.setAttribute("mobileLogo", mobileLogo);
			
			//PC 로고
			LogoDomain pcLogo = logoService.getLogo("PC");
			request.setAttribute("pcLogo", pcLogo);

			String uri = RequestUtil.getRequestUri(request); //Request URI
			
			/*logger.debug("#### URL:" + request.getRequestURI());
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					logger.debug("#### Cookie:Domain=" + cookie.getDomain() + ","
							+ cookie.getName() + "=" + cookie.getValue());

				}
			} else {
				logger.debug("### Cookie is null");
			}

			Map<String, Object> reqParam = WebUtils.getParametersStartingWith(
					request, "");
			Iterator<String> it = reqParam.keySet().iterator();
			
			if( !request.getRequestURI().contains( "file.downloadImage" ) )
			{
				while (it.hasNext()) {
					String key = it.next();
					Object val = reqParam.get(key);
					if (val.getClass().isArray()) {
						String[] values = (String[]) val;
						logger.debug("##PARAM:" + key + "=["
								+ StringUtils.arrayToCommaDelimitedString(values) + "]");
					} else {
						logger.debug("##PARAM:" + key + "=[" + val + "]");
					}
				}
			}*/
						
			if(uri.indexOf(".kt") > 0 && !uri.contains( "/ot/" ) && !uri.contains( "/test/" ) && !uri.contains( "/web/" ) && !uri.contains( "/api/" )) 
			{
				
				// 웹툰 회원 정보
				String cookieName = MessageUtil.getSystemMessage("system.cookie.name");      //쿠키 변수명
				String webtoon_user = CookieUtil.getCookie(request, cookieName);
				
				// 올레 회원 ID
				String userid = CookieUtil.getCookie(request, "kt_userid");
				
				String u_at_lo = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_at_lo"),"N");
				
				// 회원 구분(1: 웹툰회원, 2: 작가 , 3: 약관 미동의 올레회원, 4: 약관 동의 올레회원, 5: 약관 미동의 웹툰회원  )
				String u_check_no = StringUtil.defaultStr(CookieUtil.getCookie(request, "u_check_no"), "");
				
				//모바일에서 웹툰 회원만 쿠키 10분 뒤 만료처리
				if(RequestUtil.isMobile(request) && u_at_lo.equals("Y") && u_check_no != null && (u_check_no.equals("1") || u_check_no.equals("5"))){
					String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
					String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
					
					String[] loginCookies = {"NWC","u_check_no","u_nickname","u_nc_seq","u_nc_url","u_mnc_url","u_no"}; //쿠키 유효시간 업데이트 할 쿠키 목록
					
					String cookieValue = "";					
					for(int i=0 ; i < loginCookies.length ; i++){
						cookieValue = CookieUtil.getCookie(request, loginCookies[i]);
						
						//만료시간 10분 뒤 셋팅
						if(cookieValue != null){
							CookieUtil.setCookie(response, loginCookies[i], cookieValue, 60*10, cookiePath, cookieDomain);
						}
					}
				}
				
				if(webtoon_user == null && userid != null) {
					// Olleh ID로 로그인한 상태
					
					// 복호화 및 약관 동의 체크
					if(u_check_no == null || "".equals(u_check_no)){
						
						String olleh_id = ""; 			// 복호화 된 kt_userid
						String olleh_ctn = ""; 			// 대표 CTN
						String olleh_ctype = "N"; 	// 고객유형(I:개인, B:법인사업자, O:개인사업자, G:공공기간, E:불명수납고객, N:준회원-KT비고객)
						String olleh_age = ""; 		// 고객나이
						
						/*
						 * KT 올레닷컴 쿠키 복호화
						 */
						String encid = CookieUtil.getCookie(request, "kt_encid");
						
						if(!"local".equals(System.getProperty("serverType"))){
							JKTFCrypto crypto = new JKTFCrypto();
							crypto.DecryptSessionKey(encid);
							byte[] decdata = crypto.DecryptData(userid);
				                                                     
							if(crypto.getErrorCode() < 0 || decdata == null){ //쿠키 복호화 에러 발생
								logger.info("JKTFCrypto ERROR : " + crypto.getErrorCode());
							} else { //정상적으로 사용자ID를 복호화한 상태
								olleh_id = new String(decdata);
							}
							
							// 쿠키정보에서 크레딧 아이디 조회
							String kt_credtid = CookieUtil.getCookie(request, "kt_credtid");
							decdata = crypto.DecryptData(kt_credtid);

							if(crypto.getErrorCode() < 0 || decdata == null) {
								logger.info("JKTFCrypto ERROR : " + crypto.getErrorCode());
							} else { //정상적으로 사용자 크레딧 ID를 복호화한 상태
								kt_credtid = new String(decdata);
							}
							
							// 크레딧 아이디로 대표 CTN 조회
							if(kt_credtid != null && !"".equals(kt_credtid)) {
								SDPResponse sDPResponse = SDPApiService.updateRetrieveRepPhoneNumber(kt_credtid);
								if(sDPResponse != null) {
									if(SDPConstants.RESULT_SUCCESS.equals(sDPResponse.getResultCode())) {
										olleh_ctn = (String)sDPResponse.getParameter("REPRESENTATIVENUMBER");
									}
								}
							}
							
							// 대표CTN으로 고객유형, 나이 조회
							if(olleh_ctn != null && !"".equals(olleh_ctn)) {
								SDPResponse sDPResponse = SDPApiService.getUserInfoWithAge(olleh_ctn);
								if(sDPResponse != null) {
									if(SDPConstants.RESULT_SUCCESS.equals(sDPResponse.getResultCode())) {
										olleh_ctype = (String)sDPResponse.getParameter("CUSTOMERTYPE");
										olleh_age = (String)sDPResponse.getParameter("USERAGE");
									}
								}
							}
						} else { // 로컬환경 테스트 데이터
							olleh_id = "ollehtest";
							olleh_ctn = "01011111111";
							olleh_ctype = "I";
							olleh_age = "20";
						}
						
						request.setAttribute("olleh_id", olleh_id);
						

						logger.debug("olleh_ctype : " +  olleh_ctype);
						
						//복호화 처리가 성공적으로 끝난경우
						if(!"".equals(StringUtil.defaultStr(olleh_id))){
							// 회원 구분 쿠키 생성
							String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
							String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
							String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
							int intCookieAge = Integer.parseInt(cookieAge);
							
							//최초 Olleh 로그인 여부 및 약관 동의 여부 체크
							OllehUserDomain ollehUser = null;
							ollehUser = userServiceIface.ollehUserDetail(olleh_id);							
							
							if(ollehUser != null && "Y".equals(ollehUser.getTermsyn())){
								
								//약관 동의 한 회원으로 쿠키 생성
								CookieUtil.setCookie(response, "u_check_no", "4", intCookieAge, cookiePath, cookieDomain);
								
							}else if(ollehUser != null && "N".equals(ollehUser.getTermsyn())){
								
								//약관 동의 안 한 경우
								CookieUtil.setCookie(response, "u_check_no", "3", intCookieAge, cookiePath, cookieDomain);
								
							}else{
								//올레ID로 처음 로그인 한경우 : 약관 미동의 올레 회원은 DB insert 후 네임콘 정보를 가져온다.
								
								//올레회원 DB 등록
								userServiceIface.ollehUserRegist(olleh_id);
								
								//다시 올레 회원정보를 가져옴
								ollehUser = userServiceIface.ollehUserDetail(olleh_id);
								
								//약관 동의 안 한 회원으로 쿠키 생성
								CookieUtil.setCookie(response, "u_check_no", "3", intCookieAge, cookiePath, cookieDomain);
							}
							
							String key = MessageUtil.getSystemMessage("system.cookie.aes.key");          //쿠키 암호화 키
							String initialVector = MessageUtil.getSystemMessage("system.cookie.aes.iv"); //쿠키 암호화 초기화벡터
							
							AesCipherUtil aesCipherUtil = new AesCipherUtil();
							
							//올레ID를 웹툰 암호화 모듈로 암호화 처리
							String u_olleh_id = aesCipherUtil.encrypt(key, initialVector, olleh_id.getBytes());
							CookieUtil.setCookie(response, "u_olleh_id", u_olleh_id, intCookieAge, cookiePath, cookieDomain);
							
							//올레 대표 CTN을 웹툰 암호화 모듈로 암호화 처리
							String u_olleh_ctn = aesCipherUtil.encrypt(key, initialVector, olleh_ctn.getBytes());
							CookieUtil.setCookie(response, "u_olleh_ctn", u_olleh_ctn, intCookieAge, cookiePath, cookieDomain);
							
							//올레 고객유형을 웹툰 암호화 모듈로 암호화 처리
							String u_olleh_ctype = aesCipherUtil.encrypt(key, initialVector, olleh_ctype.getBytes());
							
							//작가계정일때 올레계정 유형을 정회원(개인)으로 설정
							if("Y".equals(ollehUser.getAuthoryn()) && "Y".equals(ollehUser.getTermsyn())){
								olleh_ctype = "O";
								olleh_age = "20";
							}
							
							CookieUtil.setCookie(response, "u_olleh_ctype", u_olleh_ctype, intCookieAge, cookiePath, cookieDomain);
							//CookieUtil.setCookie(response, "u_olleh_ctype_d", olleh_ctype, intCookieAge, cookiePath, cookieDomain);
							
							//올레 나이정보를 웹툰 암호화 모듈로 암호화 처리
							String u_olleh_age = aesCipherUtil.encrypt(key, initialVector, olleh_age.getBytes());
							CookieUtil.setCookie(response, "u_olleh_age", u_olleh_age, intCookieAge, cookiePath, cookieDomain);
							//CookieUtil.setCookie(response, "u_olleh_age_d", olleh_age, intCookieAge, cookiePath, cookieDomain);
							
							//닉네임을 아이디 뒤에서 세자리를 * 처리하여 셋팅	
							String nickname = "";
							if(olleh_id.indexOf("@") > -1){
								if(olleh_id.indexOf('@') > 3){							
									nickname += olleh_id.substring(0, 3);
									
									for(int i=0 ; i < olleh_id.substring(3, olleh_id.indexOf('@')).length() ; i++){
										nickname += "*";
									}
									
									nickname += olleh_id.substring(olleh_id.indexOf('@'));
								}else{
									nickname = olleh_id;
								}
							}else{			
								nickname += olleh_id.substring(0, 3);
								
								for(int i=0 ; i < (olleh_id.length()-3) ; i++){
									nickname += "*";
								}
							}
							
							CookieUtil.setCookie(response, "u_nickname", nickname, intCookieAge, cookiePath, cookieDomain);
							
							// 네임콘 URL, 모바일네임콘 URL
							CookieUtil.setCookie(response, "u_nc_seq", String.valueOf(ollehUser.getNameconseq()), intCookieAge, cookiePath, cookieDomain);
							CookieUtil.setCookie(response, "u_nc_url", ollehUser.getNameconurl(), intCookieAge, cookiePath, cookieDomain);
							CookieUtil.setCookie(response, "u_mnc_url", ollehUser.getMnameconurl(), intCookieAge, cookiePath, cookieDomain);
							CookieUtil.setCookie(response, "u_no", String.valueOf(ollehUser.getUserseq()), intCookieAge, cookiePath, cookieDomain);	
							
							// 약관동의에 동의한 작가일때 작가 닉네임, 작가 네임콘 URL, 작가 모바일네임콘 URL
							// 약관 동의 안하면 작가가 아님.
							if("Y".equals(ollehUser.getAuthoryn()) && "Y".equals(ollehUser.getTermsyn())){
								CookieUtil.setCookie(response, "u_check_no", "2", intCookieAge, cookiePath, cookieDomain);
								
								// 작가 닉네임
								if(ollehUser.getNickname() != null && ollehUser.getNickname().length() > 0)
									nickname = ollehUser.getNickname();
								else
									nickname = "작가";
									
								CookieUtil.setCookie(response, "u_nickname", nickname, intCookieAge, cookiePath, cookieDomain);
								
								// 작가 네임콘 URL, 작가 모바일네임콘 URL, 작가번호
								CookieUtil.setCookie(response, "u_ahr_seq", ollehUser.getAuthorseq(), intCookieAge, cookiePath, cookieDomain);
								CookieUtil.setCookie(response, "u_nc2_seq", String.valueOf(ollehUser.getNameconseq2()), intCookieAge, cookiePath, cookieDomain);
								CookieUtil.setCookie(response, "u_nc2_url", ollehUser.getNameconurl2(), intCookieAge, cookiePath, cookieDomain);
								CookieUtil.setCookie(response, "u_mnc2_url", ollehUser.getMnameconurl2(), intCookieAge, cookiePath, cookieDomain);
							}
							

							
							//모바일 앱에서 로그인시 Device정보에 로그인 정보를 update
							 String uuid = RequestUtil.getUuid(request);
							 if(uuid != null){
								 UserDomain userDomain = new UserDomain();
								 
								 userDomain.setUuid(uuid);
								 userDomain.setIdfg("olleh");
								 userDomain.setEmail(olleh_id);
								 userDomain.setLoginyn("Y");
								 
								 userServiceIface.updateEmailByUuid(userDomain);					 
							 }							
							
							
														
							//네임콘 로그인 이벤트
							// ~ 20140731까지
							int today = Integer.parseInt(DateUtil.getNowDate(0));		
							if(today <= 20140731){							
								//로그인 히스토리
								Map<String, Object> param = new HashMap<String, Object>();
								param.put("loginid", olleh_id);
								param.put("idfg", "olleh");
								param.put("logindt", DateUtil.getNowDate(1));
								
								int historyCnt = userServiceIface.loginHisRegistProc(param) + 1;
								
								if(historyCnt > 0 && historyCnt <= 10 ){
									CookieUtil.setCookie(response, "u_namecon_event", Integer.toString(historyCnt), intCookieAge, cookiePath, cookieDomain);
								}
								
								//네임콘 구매 처리
								//출석횟수가 1,3,5,7,10 번째 일때 네임콘 상품을 등록
								if(historyCnt == 1 || historyCnt == 3 || historyCnt == 5 || historyCnt == 7 || historyCnt == 10){
									// 상품정보
									param.put("prdfg", "name");
									param.put("sellfg", "event");
									
									//상품번호 지정
									if(!"real".equals(System.getProperty("serverType"))){										
										//개발
										if(historyCnt == 1) param.put("prdseq", "1137");
										else if(historyCnt == 3) param.put("prdseq", "1138");
										else if(historyCnt == 5) param.put("prdseq", "1139");
										else if(historyCnt == 7) param.put("prdseq", "1140");
										else if(historyCnt == 10) param.put("prdseq", "1141");
									}else{
										//운영
										if(historyCnt == 1) param.put("prdseq", "1039");
										else if(historyCnt == 3) param.put("prdseq", "1040");
										else if(historyCnt == 5) param.put("prdseq", "1041");
										else if(historyCnt == 7) param.put("prdseq", "1042");
										else if(historyCnt == 10) param.put("prdseq", "1043");
									}
									
									
									ProductHistoryDomain product = premiumService.productDetail(param);
									//logger.debug("[premiumDetail] 상품정보가 존재하는가? " + product);
									
									if (product != null) {
										// 구매처리
										try {
											orderbuyService.productBuy(olleh_id, "olleh", product);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}									
								}
							}
						}
					}
				}else if(webtoon_user == null && userid == null){
					String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
					
					//로그인 상태가 아닌경우 강제로 쿠키 삭제
					if((uri.indexOf("/user/captchaCheck.kt") <= -1 && uri.indexOf("/login.kt") <= -1 && uri.indexOf("/user/") <= -1)
							|| uri.indexOf("/user/passwdSearch.kt") > -1
							|| uri.indexOf("/user/regist.kt") > -1){
						//CookieUtil.deleteAllCookie(request, response, cookieDomain);
					}
				}
				
			}
		
		} catch(Exception e) {
			logger.error(e);
		}
		
		return true;
		
	}

}