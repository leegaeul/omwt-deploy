package com.olleh.webtoon.common.dao.dcb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.dcb.domain.DcbDomain;
import com.olleh.webtoon.common.dao.dcb.persistence.DcbMapper;
import com.olleh.webtoon.common.dao.dcb.service.iface.DcbService;
import com.olleh.webtoon.common.util.DateUtil;

@Service("dcbService")
@Repository
public  class DcbServiceImpl implements DcbService {
	
	@Autowired
	private DcbMapper dcbMapper;	
	
	/**
	 * 쿠폰발급 
	 * @param String  : deviceid 
	 * @return String : 쿠폰번호
	 */
	@Transactional(readOnly=false)
	public String getDcbCoupon(String deviceid) {
		
		if(deviceid == null || deviceid.length() < 1) {
			return null;
		}
		
		//deviceid로 쿠폰 발급 이력을 조회한다.
		String couponNumber = dcbMapper.selectDcbCoupon(deviceid);
		if(couponNumber != null && couponNumber.length() > 0) {
			return couponNumber;
		}
		
		//쿠폰 발급 이력이 없으면 쿠폰을 발급 받는다.
		DcbDomain dcbDomain = new DcbDomain();
		dcbDomain.setDeviceid(deviceid);
		dcbDomain.setIssuedt(DateUtil.getNowDate(1));
		
		dcbMapper.updateDcbCoupon(dcbDomain);
		
		return dcbMapper.selectDcbCoupon(deviceid);
	}

	/**
	 * 쿠폰번호로 쿠폰 정보 조회
	 * 
	 * @param String couponnum
	 * @return DcbDomain : 쿠폰 정보
	 */
	@Transactional(readOnly=true)
	public DcbDomain getCouponInfoByCouponnum(String couponnum) {
		return dcbMapper.selectCouponInfoByCouponnum(couponnum);
	}
	
	/**
	 * 아이디로 등록된 쿠폰정보 조회
	 * 
	 * @param DcbDomain dcbDomain
	 * @return DcbDomain : 쿠폰 정보
	 */
	@Transactional(readOnly=true)
	public DcbDomain getCouponInfoById(DcbDomain dcbDomain) {
		return dcbMapper.selectCouponInfoById(dcbDomain);
	}
	
	/**
	 * 100원앱 쿠폰 등록
	 * 
	 * @param DcbDomain dcbDomain
	 * @return void
	 */
	@Transactional(readOnly=false)
	public void couponRegistProc(DcbDomain dcbDomain) {
		dcbMapper.updateCouponEvent(dcbDomain);
	}
}