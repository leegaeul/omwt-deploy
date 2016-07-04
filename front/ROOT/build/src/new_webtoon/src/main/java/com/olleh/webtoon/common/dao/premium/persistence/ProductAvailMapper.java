package com.olleh.webtoon.common.dao.premium.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;

public interface ProductAvailMapper {

	public int insertProductAvail(ProductAvailDomain productAvailDomain);
	
	public ProductAvailDomain selectProductAvail(ProductAvailDomain productAvailDomain);
	
	public List<ProductAvailDomain> selectProductAvailByPrdSeq(ProductAvailDomain productAvailDomain);
	
	public void deleteProductAvail(Integer buyseq);
	
}
