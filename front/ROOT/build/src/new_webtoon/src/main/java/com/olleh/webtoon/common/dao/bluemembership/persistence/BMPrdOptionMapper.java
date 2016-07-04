package com.olleh.webtoon.common.dao.bluemembership.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdOptionDomain;

public interface BMPrdOptionMapper
{
	public int insert( BMPrdOptionDomain option );


	public int deleteByPK( BMPrdOptionDomain option );


	public BMPrdOptionDomain selectByPK( BMPrdOptionDomain option );
	
	
	public List<BMPrdOptionDomain> selectListByPrdcode( BMPrdOptionDomain option );
	
	
	public List<BMPrdOptionDomain> selectAll();
	
	
	public List<BMPrdOptionDomain> selectListByDate( BMPrdOptionDomain option );
	
	
	public List<BMPrdOptionDomain> selectAllListByPrdcode( BMPrdOptionDomain option );
}
