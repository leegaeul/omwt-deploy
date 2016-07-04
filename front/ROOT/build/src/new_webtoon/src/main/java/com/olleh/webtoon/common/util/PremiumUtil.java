package com.olleh.webtoon.common.util;

import javax.servlet.http.HttpServletRequest;

import com.olleh.webtoon.common.dao.user.domain.UserDomain;

public class PremiumUtil {
	
	public static final int NOT_CERT_USER       =  0;
	public static final int ADULT_CERT_USER     =  1;
	public static final int MINOR_NOT_CERT_USER =  2;
	public static final int MINOR_CERT_USER     =  3;
	public static final int ASSOCIATE_USER 	    =  4;
	public static final int DISAGREE_TERMS_USER =  5;
	public static final int LOGOUT 	            = -1;
	
	public static final int LOGIN_ERROR             	= 1;
	public static final int NOT_EXIST_PRODUCT_ERROR 	= 2;
	public static final int NOT_SELL_PRODUCT_ERROR  	= 3;
	public static final int ADULT_CERT_ERROR 	    	= 4;
	public static final int SELF_CERT_ERROR 	    	= 5;
	public static final int LAWER_CERT_ERROR 	    	= 6;
	public static final int MINOR_ERROR 		    	= 7;
	public static final int ALREADY_PURCHASE_ERROR  	= 8;
	public static final int BERRY_SHORTAGE_ERROR    	= 9;
	public static final int PURCHASE_ERROR 		    	= 10;
	public static final int ASSOCIATE_MEMBER_ERROR  	= 11;
	public static final int IOS_APP_ERROR  	        	= 12;
	public static final int BBERRY_SHORTAGE_ERROR   	= 13;
	public static final int MBERRY_SHORTAGE_ERROR   	= 14;
	public static final int BLUE_MEMBERSHIP_ERROR   	= 15;
	public static final int RBERRY_SHORTAGE_ERROR     	= 16;
	public static final int NOT_EXIST_PURCHAEFG_ERROR 	= 17;
	
	public static final String UNLIMITED_TERM = "99991231235959";
	public static final String BERRY_PURCHASE_PRODUCT  = "berry";
	public static final String BBERRY_PURCHASE_PRODUCT = "blue";
	public static final String MIX_PURCHASE_PRODUCT    = "mix";
	
	/**
	 * 유저의 본인인증 상태값 조회
	 * 
	 * @param request
	 * @return int
	 * 미인증: 0
	 * 성인: 1
	 * 미성년자(법적대리인 미인증): 2
	 * 미성년자(법적대리인 인증): 3
	 * 생년월일없는 올레준회원: 4
	 * 로그아웃: -1
	 * @throws Exception 
	 */
	public static int myCert(HttpServletRequest request) throws Exception {
		// 로그아웃 상태
		if (!AuthUtil.isLogin(request)) {
			return LOGOUT;
		}
		// 로그인 상태
		else {
			UserDomain user = AuthUtil.getDecryptUserInfo(request);
			
			//약관 미동의(공통)
			if("3".equals(user.getCheckno()) || "5".equals(user.getCheckno())) {
				return DISAGREE_TERMS_USER;
			}
			// 올레아이디
			else if (("" + user.getIdfg()).equals("olleh")) {
				// 생년월일없는 올레준회원
				if (("" + user.getCtype()).equals("N") && (user.getAge() == null || ("" + user.getAge()).length() == 0))
					return ASSOCIATE_USER;
				// 법인사업자
				else if (("" + user.getCtype()).equals("O"))
					return ADULT_CERT_USER;
				// 성인
				else if (19 <= user.getAgeToInteger())
					return ADULT_CERT_USER;
				// 미성년
				else
					return MINOR_CERT_USER;
			}
			// 오픈아이디
			else {
				// 미인증 체크
				if (!("" + user.getAdultyn()).equals("Y"))
					return NOT_CERT_USER;
				// 만 19세 이상인가?
				else if (19 <= DateUtil.getManAge(user.getBirthday())) 
					return ADULT_CERT_USER;
				// 미성년자(법적대리인 인증)
				else if (user.getMinoritycertnum() != null && 10 < user.getMinoritycertnum().length())
					return MINOR_CERT_USER;
				// 미성년자(법적대리인 미인증)
				else
					return MINOR_NOT_CERT_USER;
			}	
		}
	}

}
