package com.olleh.webtoon.common.dao.bluemembership.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olleh.webtoon.common.dao.bluemembership.code.PrdCode;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdOptionDomain;
import com.olleh.webtoon.common.dao.bluemembership.persistence.BMPrdMapper;
import com.olleh.webtoon.common.dao.bluemembership.persistence.BMPrdOptionMapper;

@Service
public class BMPrdService
{
	private Log					logger	= LogFactory.getLog( BMPrdService.class );

	@Autowired
	private HttpServletRequest	request;

	@Autowired
	private BMPrdMapper			prdMapper;

	@Autowired
	private BMPrdOptionMapper	prdOptionMapper;


	public BMPrdDomain getPrdByPrdcode( PrdCode prdcode )
	{
		return prdMapper.selectByPrdcode( new BMPrdDomain( prdcode ) );
	}

	public List<BMPrdOptionDomain> getPrdOptionListByPrdcode( PrdCode prdcode )
	{
		return prdOptionMapper.selectListByPrdcode( new BMPrdOptionDomain( prdcode ) );
	}
	
	public List<BMPrdOptionDomain> getPrdOptionListByDate( PrdCode prdcode )
	{
		return prdOptionMapper.selectListByDate( new BMPrdOptionDomain( prdcode ) );
	}
	
	public List<BMPrdOptionDomain> getAllPrdOptionListByPrdcode( PrdCode prdcode )
	{
		return prdOptionMapper.selectAllListByPrdcode( new BMPrdOptionDomain( prdcode ) );
	}
}
