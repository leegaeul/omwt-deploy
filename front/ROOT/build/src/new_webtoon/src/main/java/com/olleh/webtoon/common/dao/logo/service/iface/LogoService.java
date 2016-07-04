package com.olleh.webtoon.common.dao.logo.service.iface;

import com.olleh.webtoon.common.dao.logo.domain.LogoDomain;

public interface LogoService
{
	
	/**
	 * logo 리스트 조회
	 * @return
	 */
	public LogoDomain getLogo(String param);

}
