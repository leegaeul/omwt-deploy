/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - PremiumService.java
 * 
 * DESCRIPTION
 * -  프리미엄 Service interface class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.premium.service.iface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.premium.domain.CategoryDomain;
import com.olleh.webtoon.common.dao.premium.domain.PremiumDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductHistoryDomain;

public interface PremiumService 
{	
	/**
	 * 카테고리 리스트 조회
	 * @param String categoryfg
	 * @return  
	 */
	public List<CategoryDomain> premiumCategoryList(String categoryfg);

	/**
	 * 카테고리 프리미엄 리스트 갯수 조회
	 * @param PremiumDomain premiumDomain
	 * @return  
	 */
	public int totalListCnt(PremiumDomain premiumDomain);
	
	/**
	 * 카테고리 순번을 넘겨받아 해당 카테고리 정보 조회
	 * @param int categoryseq   : 카테고리 순번
	 * @return  
	 */
	public CategoryDomain getCategoryBySeq(int categoryseq);


	/**
	 * 프리미엄 상세 리스트 조회
	 * @param PremiumDomain premiumDomain
	 * @return
	 */
	public ArrayList<PremiumDomain> premiumList(PremiumDomain premiumDomain);

	/**
	 * 상품조회 (상품이력에서 가장 마지막 행을 가지고옴)
	 * @param webtoonseq
	 * @param timesseq
	 * @return
	 */
	public ProductHistoryDomain productDetail(Map<String,Object> param);

	/**
	 * 이용가능 상품조회
	 * @param param
	 * @return
	 */
	public ProductAvailDomain productAvail(String email, Map<String,Object> param);
	
	/**
	 * 회차 상품 존재 여부 조회
	 * @param webtoonseq
	 * @return String 존재여부(Y)
	 */
	public String getExistTimesprd(String param);
	
	/**
	 * 상품이력순번으로 상품정보 조회
	 * @param String : 상품이력순번
	 * @return ProductHistoryDomain : 상품정보
	 */
	public ProductHistoryDomain productDetailBySeq(String param);
	
	/**
	 * 블루멤버십 가입여부 체크
	 * @param email, idfg
	 * @return String 가입여부(Y)
	 */
	public String getBlueMembershipInfo(String email, String idfg);
}
