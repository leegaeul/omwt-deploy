package com.olleh.webtoon.common.dao.logo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.cache.LogoCache;
import com.olleh.webtoon.common.dao.logo.domain.LogoDomain;
import com.olleh.webtoon.common.dao.logo.service.iface.LogoService;
import com.olleh.webtoon.common.dao.logo.persistence.LogoMapper;


@Service("logoService")
@Repository
public class LogoServiceImpl implements LogoService {

	@Autowired
	private LogoMapper logoMapper;

	/**
	 *  logo 리스트 조회
	 * @return
	 */
	@Transactional(readOnly=true)
	public LogoDomain getLogo(String param){

		String cacheKey = param;
		LogoCache logoCache = LogoCache.getInstance();
		
		LogoDomain logoDomain = logoCache.getCachedLogo(cacheKey);
		
		if(logoDomain == null) { //캐시된 데이터가 없거나 만료된 경우
			logoDomain = logoMapper.logoSelectList(param);
			
			//목록이 있는 경우 DB에서 새로 조회한 데이터를 캐시 데이터에 추가한다.
			if(logoDomain != null && logoDomain.getImagepath() != null) {
				logoCache.putCache(cacheKey, logoDomain);
			}			
		}
		
		return logoDomain;
	}
}