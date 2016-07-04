package com.olleh.webtoon.common.dao.ticker.service.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.olleh.webtoon.common.dao.ticker.domain.TickerDomain;
import com.olleh.webtoon.common.dao.ticker.service.iface.TickerService;
import com.olleh.webtoon.common.dao.ticker.persistence.TickerMapper;
import com.olleh.webtoon.common.dao.ticker.service.impl.TickerServiceImpl;

@Service("tickerService")
@Repository
public class TickerServiceImpl implements TickerService
{
	protected static Log	logger	= LogFactory.getLog( TickerServiceImpl.class );

	@Autowired
	private TickerMapper tickerMapper;

	/**
	 * ticker 조회
	 * @param TickerDomain 
	 * @return
	 */
	
	@Transactional(readOnly=true)
	public  List<TickerDomain> tickerList(String displayfg)
	{
		return tickerMapper.tickerSelectList(displayfg);
	}
	
	
}
