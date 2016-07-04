package com.olleh.webtoon.common.dao.ticker.service.iface;

import java.util.List;

import com.olleh.webtoon.common.dao.ticker.domain.TickerDomain;


public interface TickerService
{
	/**
	 * ticker 조회
	 * @param TickerDomain tickerDomain
	 * @return List<tickerDomain>
	 */
	
public List<TickerDomain> tickerList(String displayfg);

}
