package com.olleh.webtoon.common.dao.banner.service.iface;

import java.util.List;

import com.olleh.webtoon.common.dao.banner.domain.BannerDomain;

public interface BannerService {
	
	/**
	 * BANNER 리스트 조회 
	 * @return
	 */
	public List<BannerDomain> bannerList(String param);
}
