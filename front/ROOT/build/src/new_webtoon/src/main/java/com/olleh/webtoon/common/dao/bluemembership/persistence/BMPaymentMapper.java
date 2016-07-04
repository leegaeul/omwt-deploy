package com.olleh.webtoon.common.dao.bluemembership.persistence;

import java.util.List;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPaymentDomain;

public interface BMPaymentMapper
{
	public int insert( BMPaymentDomain payment );


	public int updateByPaymentseq( BMPaymentDomain payment );


	public int deleteByPaymentseq( BMPaymentDomain payment );


	public BMPaymentDomain selectByPaymentseq( BMPaymentDomain payment );


	public List<BMPaymentDomain> selectAll();


	public BMPaymentDomain selectLastByJoinseq( BMPaymentDomain payment );


	public List<BMPaymentDomain> selectListForUserWithPaging( BMPaymentDomain payment );


	public Integer selectCountForUser( BMPaymentDomain payment );


	public List<BMPaymentDomain> selectListByJoinseq( BMPaymentDomain payment );
}
