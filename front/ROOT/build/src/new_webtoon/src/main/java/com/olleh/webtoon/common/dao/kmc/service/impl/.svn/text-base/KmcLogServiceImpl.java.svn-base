package com.olleh.webtoon.common.dao.kmc.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.kmc.domain.KmcLogDomain;
import com.olleh.webtoon.common.dao.kmc.persistence.KmcLogMapper;
import com.olleh.webtoon.common.dao.kmc.service.iface.KmcLogService;
import com.olleh.webtoon.common.util.DateUtil;

@Service("kmcLogService")
@Repository
public class KmcLogServiceImpl implements KmcLogService {

	@Autowired
	private KmcLogMapper kmcLogMapper;

	/**
	 * KMC Log 저장
	 * @return
	 */
	@Transactional(readOnly=false)
	public int insertKmcLog(KmcLogDomain kmcLogDomain)
	{
		kmcLogDomain.setRegdt(DateUtil.getNowDate(1));
		return kmcLogMapper.insertKmcLog(kmcLogDomain);
	}

	
	/**
	 * KMC Log 수정
	 * @return
	 */
	@Transactional(readOnly=false)
	public void modifyKmcLog(KmcLogDomain kmcLogDomain) {
		
		kmcLogDomain.setRegdt(DateUtil.getNowDate(1));
		kmcLogMapper.modifyKmcLog(kmcLogDomain);
	}
}