package com.olleh.webtoon.common.dao.premium.persistence;

import java.util.ArrayList;
import java.util.List;

import com.olleh.webtoon.common.dao.orderbuy.domain.OrderDomain;
import com.olleh.webtoon.common.dao.premium.domain.CategoryDomain;
import com.olleh.webtoon.common.dao.premium.domain.PremiumDomain;


public interface PremiumMapper 
{		
	/**
	 * 프리미엄 카테고리 리스트 조회  
	 * @param String categoryfg
	 * @return  
	 */
	public List<CategoryDomain> premiumSelectCategoryList(String categoryfg);
	
	/**
	 * 카테고리 프리미엄 리스트 갯수 조회
	 * @param PremiumDomain premiumDomain
	 * @return  
	 */
	public int totalSelectListCnt(PremiumDomain premiumDomain);
	
	/**
	 * 카테고리 순번을 넘겨받아 해당 카테고리 정보 조회
	 * @param int categoryseq : 카테고리 순번
	 * @return  
	 */
	public CategoryDomain selectCategoryBySeq(int categoryseq);
	
	/**
	 * 프리미엄 상세 리스트 조회
	 * @param PremiumDomain premiumDomain
	 * @return
	 */
	public ArrayList<PremiumDomain> premiumSelectList(PremiumDomain premiumDomain);
	
	/**
	 * 블루멤버십 가입여부 체크
	 * @param OrderDomain order
	 * @return String 가입여부(Y)
	 */
	public String selectBlueMembershipInfo(OrderDomain order);
}