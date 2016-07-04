package com.olleh.webtoon.common.dao.kmc.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.kmc.domain.KmcFailDomain;
import com.olleh.webtoon.common.dao.kmc.persistence.KmcFailMapper;
import com.olleh.webtoon.common.dao.kmc.service.iface.KmcFailService;
import com.olleh.webtoon.common.util.DateUtil;

@Service("kmcFailService")
@Repository
public class KmcFailServiceImpl implements KmcFailService {

	@Autowired
	private KmcFailMapper kmcFailMapper;

	/**
	 * KMC 실패 저장
	 * @return
	 */
	@Transactional(readOnly=false)
	public int insertKmcFail(KmcFailDomain kmcFail)
	{
		kmcFail.setRegdt(DateUtil.getNowDate(1));
		return kmcFailMapper.insertKmcFail(kmcFail);
	}
}