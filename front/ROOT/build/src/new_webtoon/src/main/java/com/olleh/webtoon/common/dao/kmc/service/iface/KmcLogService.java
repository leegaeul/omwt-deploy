package com.olleh.webtoon.common.dao.kmc.service.iface;

import com.olleh.webtoon.common.dao.kmc.domain.KmcLogDomain;

public interface KmcLogService {
	
	/**
	 * KMC Log 저장
	 * @return
	 */
	public int insertKmcLog(KmcLogDomain kmcLogDomain);

	/**
	 * KMC Log 수정
	 * @return
	 */
	public void modifyKmcLog(KmcLogDomain kmcLogDomain);
}
