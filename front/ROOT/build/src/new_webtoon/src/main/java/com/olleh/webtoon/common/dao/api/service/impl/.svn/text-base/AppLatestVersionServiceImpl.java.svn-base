package com.olleh.webtoon.common.dao.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.api.domain.AppLatestVersionDomain;
import com.olleh.webtoon.common.dao.api.persistence.AppLatestVersionMapper;
import com.olleh.webtoon.common.dao.api.service.iface.AppLatestVersionService;

@Service
public class AppLatestVersionServiceImpl implements AppLatestVersionService
{
	@Autowired
	private AppLatestVersionMapper versionMapper;
	
	@Transactional(readOnly=true)
	public AppLatestVersionDomain getAppLatestVersion()
	{
		return versionMapper.select();
	}
}
