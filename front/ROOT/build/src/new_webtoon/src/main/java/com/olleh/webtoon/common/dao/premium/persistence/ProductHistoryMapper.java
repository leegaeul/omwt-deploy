package com.olleh.webtoon.common.dao.premium.persistence;

import java.util.Map;

import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductHistoryDomain;

public interface ProductHistoryMapper {

	public ProductHistoryDomain selectProductDetail(Map<String,Object> param);
	
	public String selectExistTimesprd(String param);	

	public ProductHistoryDomain selectProductDetailBySeq(String param);
	
	public ProductHistoryDomain selectFreeProductDetailBySeq(Integer param);

	public ProductHistoryDomain deleteProductAvail(ProductAvailDomain productAvailDomain);
}
