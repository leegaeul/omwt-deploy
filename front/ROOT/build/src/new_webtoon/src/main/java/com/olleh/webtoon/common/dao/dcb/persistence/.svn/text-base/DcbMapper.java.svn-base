package com.olleh.webtoon.common.dao.dcb.persistence;

import com.olleh.webtoon.common.dao.dcb.domain.DcbDomain;

public interface DcbMapper {		

	/**
	 * 쿠폰조회 
	 * @param String  : deviceid 
	 * @return String : 쿠폰번호
	 */
	public String selectDcbCoupon(String deviceid);

	/**
	 * 쿠폰발급 
	 * @param DcbDomain dcbDomain : deviceid 
	 * @return
	 */
	public void updateDcbCoupon(DcbDomain dcbDomain);
	
	/**
	 * 쿠폰번호로 쿠폰 정보 조회
	 * 
	 * @param String couponnum
	 * @return DcbDomain : 쿠폰 정보
	 */
	public DcbDomain selectCouponInfoByCouponnum(String couponnum);
	
	/**
	 * 아이디로 등록된 쿠폰정보 조회
	 * 
	 * @param DcbDomain dcbDomain
	 * @return DcbDomain : 쿠폰 정보
	 */
	public DcbDomain selectCouponInfoById(DcbDomain dcbDomain);
	
	/**
	 * 100원앱 쿠폰 등록
	 * 
	 * @param DcbDomain dcbDomain
	 * @return void
	 */
	public void updateCouponEvent(DcbDomain dcbDomain);
}