package com.olleh.webtoon.common.dao.bluemembership.persistence;

import java.util.List;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinTermDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPaymentDomain;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;

public interface BMJoinTermMapper
{
	public int insert( BMJoinTermDomain term );


	public int updateByTermseq( BMJoinTermDomain term );


	public int deleteByTermseq( BMJoinTermDomain term );


	public BMJoinTermDomain selectByTermseq( BMJoinTermDomain term );
	
	
	public BMJoinTermDomain selectLastTermForUser( BMJoinTermDomain term );


	public List<BMJoinTermDomain> selectAll();


	public BMJoinTermDomain selectLastByPaymentseq( BMPaymentDomain bmPaymentDomain );


	public BMJoinTermDomain selectLastByJoinseq( BMJoinTermDomain term );


	public BMJoinTermDomain selectCurrentForUser( BMJoinTermDomain term );
}
