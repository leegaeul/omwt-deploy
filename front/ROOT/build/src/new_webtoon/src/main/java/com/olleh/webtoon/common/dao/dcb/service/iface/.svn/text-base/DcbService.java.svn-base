package com.olleh.webtoon.common.dao.dcb.service.iface;

import com.olleh.webtoon.common.dao.dcb.domain.DcbDomain;


public interface DcbService {	
	
	/**
	 * 쿠폰발급 
	 * @param String  : deviceid 
	 * @return String : 쿠폰번호
	 */
	public String getDcbCoupon(String deviceid);
	
	/**
	 * 쿠폰번호로 쿠폰 정보 조회
	 * 
	 * @param String couponnum
	 * @return DcbDomain : 쿠폰 정보
	 */
	public DcbDomain getCouponInfoByCouponnum(String couponnum);
	
	/**
	 * 아이디로 등록된 쿠폰정보 조회
	 * 
	 * @param DcbDomain dcbDomain
	 * @return DcbDomain : 쿠폰 정보
	 */
	public DcbDomain getCouponInfoById(DcbDomain dcbDomain);
	
	/**
	 * 100원앱 쿠폰 등록
	 * 
	 * @param DcbDomain dcbDomain
	 * @return void
	 */
	public void couponRegistProc(DcbDomain dcbDomain);
	
}