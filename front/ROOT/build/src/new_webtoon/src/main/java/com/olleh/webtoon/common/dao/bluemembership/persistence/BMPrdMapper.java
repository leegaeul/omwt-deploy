package com.olleh.webtoon.common.dao.bluemembership.persistence;

import java.util.List;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdDomain;

public interface BMPrdMapper
{
	public int insert( BMPrdDomain prd );


	public int updateByPrdcode( BMPrdDomain prd );


	public int deleteByPrdcode( BMPrdDomain prd );


	public BMPrdDomain selectByPrdcode( BMPrdDomain prd );


	public List<BMPrdDomain> selectAll();
}
