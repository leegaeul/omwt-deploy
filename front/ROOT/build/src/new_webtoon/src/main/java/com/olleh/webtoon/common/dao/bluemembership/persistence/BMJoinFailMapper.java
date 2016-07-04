package com.olleh.webtoon.common.dao.bluemembership.persistence;

import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinFailDomain;

public interface BMJoinFailMapper
{
	public int insert( BMJoinFailDomain term );


	public int updateByFailseq( BMJoinFailDomain term );


	public int deleteByFailseq( BMJoinFailDomain term );


	public BMJoinFailDomain selectByFailseq( BMJoinFailDomain term );
}
