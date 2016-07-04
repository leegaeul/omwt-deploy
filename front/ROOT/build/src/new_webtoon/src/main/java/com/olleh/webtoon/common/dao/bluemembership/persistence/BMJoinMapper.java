package com.olleh.webtoon.common.dao.bluemembership.persistence;

import java.util.List;
import java.util.Map;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;

public interface BMJoinMapper
{
	public int insert( BMJoinDomain join );


	public int updateByJoinseq( BMJoinDomain join );


	public int deleteByJoinseq( BMJoinDomain join );


	public BMJoinDomain selectByJoinseq( BMJoinDomain join );
	
	
	public BMJoinDomain selectLastByChangeJoinseq( BMJoinDomain join );


	public List<BMJoinDomain> selectAll();


	public BMJoinDomain selectLastCancelForUser( BMJoinDomain join );


	public List<BMJoinDomain> selectCancelListForUser( BMJoinDomain join );


	public BMJoinDomain selectLastForUserByJoinStatus( BMJoinDomain join );


	public BMJoinDomain selectLastForUser( BMJoinDomain join );


	public int updateTokenForRenewal( Map<String, Object> map );


	public BMJoinDomain selectLastByUpdatetoken( BMJoinDomain join );


	public int updateTokenForFinishRenewal( BMJoinDomain join );
}
