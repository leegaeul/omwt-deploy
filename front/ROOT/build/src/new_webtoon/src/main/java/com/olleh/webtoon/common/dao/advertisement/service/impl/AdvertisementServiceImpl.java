package com.olleh.webtoon.common.dao.advertisement.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.olleh.webtoon.common.dao.advertisement.domain.AdvertisementDomain;
import com.olleh.webtoon.common.dao.advertisement.persistence.AdvertisementMapper;
import com.olleh.webtoon.common.dao.advertisement.service.iface.AdvertisementService;

@Service("advertisementService")
@Repository

public class AdvertisementServiceImpl implements AdvertisementService {
	
	@Autowired
	private AdvertisementMapper advertisementMapper;
	
	/**
	 * Advertisement 리스트 조회
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<AdvertisementDomain> advertisementList(AdvertisementDomain advertisementDomain)
	{
		return advertisementMapper.advertiseSelectList(advertisementDomain);
	}
	
	/**
	 * Advertisement 리스트 조회수
	 * @return
	 */
	@Transactional(readOnly=true)
	public int advertisementListCount(AdvertisementDomain advertisementDomain)
	{
		return advertisementMapper.advertiseSelectListCount(advertisementDomain);
	}

	/**
	 * Advertisement(회차상세 광고) 리스트 조회
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<AdvertisementDomain> adList(AdvertisementDomain advertisementDomain) {
		
		return advertisementMapper.adTextSelectList(advertisementDomain);
	}

}
