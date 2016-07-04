package com.olleh.webtoon.common.dao.bluemembership.persistence;

import java.util.List;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMOrderDomain;

public interface BMOrderMapper
{
	public int insert( BMOrderDomain order );


	public int updateByOrderseq( BMOrderDomain order );


	public int deleteByOrderseq( BMOrderDomain order );


	public BMOrderDomain selectByOrderseq( BMOrderDomain order );


	public List<BMOrderDomain> selectAll();


	public BMOrderDomain selectLastByPaymentseq( BMOrderDomain order );


	public Integer selectOwnBlueBerryForUser( BMOrderDomain order );


	public BMOrderDomain selectChargeOrderByPaymentseq( BMOrderDomain order );
}
