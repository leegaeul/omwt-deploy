package com.olleh.webtoon.common.dao.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olleh.webtoon.common.dao.api.domain.AppMetaDomain;
import com.olleh.webtoon.common.dao.api.persistence.AppMetaMapper;

@Service
public class AppService
{
	@Autowired
	private AppMetaMapper metaMapper;
	
	public AppMetaDomain getAppMeta( String os, String marketfg , String appversion )
	{
		AppMetaDomain meta = new AppMetaDomain();
		meta.setOs( os );
		meta.setMarketfg(marketfg);
		meta.setVersion(appversion);
		
		return metaMapper.selectAppMeta( meta );
	}
}
