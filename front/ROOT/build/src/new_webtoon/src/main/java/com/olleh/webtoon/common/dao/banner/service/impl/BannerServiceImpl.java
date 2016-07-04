package com.olleh.webtoon.common.dao.banner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.banner.domain.BannerDomain;
import com.olleh.webtoon.common.dao.banner.persistence.BannerMapper;
import com.olleh.webtoon.common.dao.banner.service.iface.BannerService;

@Service("bannerService")
@Repository
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerMapper bannerMapper;

	/**
	 * BANNER 리스트 조회 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<BannerDomain> bannerList(String param)
	{
		return bannerMapper.bannerSelectList(param);
	}
}