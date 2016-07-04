/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - OrderMapper.java
 * 
 * DESCRIPTION
 * - 주문내역 Mapper
 *****************************************************************************/

package com.olleh.webtoon.common.dao.orderbuy.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.orderbuy.domain.BuyDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;

public interface BuyMapper {

	/**
	 * 상품구매
	 * @param 
	 * @return
	 */
	public int insertBuy(BuyDomain buyDomain);
	
	/**
	 * 상품구매
	 * @param 
	 * @return
	 */
	public int insertOtBuy(BuyDomain buyDomain);

	/**
	 * 구매 리스트 갯수 확인
	 * @param buyDomain
	 * @return 
	 */
	public int buySelectListCnt(BuyDomain buyDomain);
	
	
	/**
	 * 구매 리스트 조회
	 * @param buyDomain
	 * @return 
	 */
	public List<BuyDomain> berryuseSelectList(BuyDomain buyDomain);
	
	/**
	 * 구매 순번 조회
	 * @param prdhistoryseq : 상품이력순번
	 * @return 
	 */
	public Integer selectBuyseq(ProductAvailDomain productAvailDomain);
	
	/**
	 * 구매 취소 처리(삭제)
	 * @param buyseq
	 * @return 
	 */
	public void deleteBuy(Integer buyseq);
	
}
