/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - PremiumServiceImpl.java
 * 
 * DESCRIPTION
 * -  프리미엄 Service implement class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.premium.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.orderbuy.domain.OrderDomain;
import com.olleh.webtoon.common.dao.premium.domain.CategoryDomain;
import com.olleh.webtoon.common.dao.premium.domain.PremiumDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductHistoryDomain;
import com.olleh.webtoon.common.dao.premium.persistence.PremiumMapper;
import com.olleh.webtoon.common.dao.premium.persistence.ProductAvailMapper;
import com.olleh.webtoon.common.dao.premium.persistence.ProductHistoryMapper;
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.util.StringUtil;

@Service("premiumService")
@Repository
public  class PremiumServiceImpl implements PremiumService
{
	@Autowired
	private PremiumMapper premiumMapper;

	@Autowired
	private ProductAvailMapper productAvailMapper;

	@Autowired
	private ProductHistoryMapper productHistoryMapper;

	/**
	 * 카테고리 리스트 조회  
	 * @param String categoryfg
	 * @return 
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public List<CategoryDomain> premiumCategoryList(String categoryfg){
		return premiumMapper.premiumSelectCategoryList(categoryfg);
	}

	/**
	 * 카테고리 프리미엄 리스트 갯수 조회
	 * @param PremiumDomain premiumDomain
	 * @return  
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int totalListCnt(PremiumDomain premiumDomain){
		return premiumMapper.totalSelectListCnt(premiumDomain);
	}
	
	/**
	 * 카테고리 순번을 넘겨받아 해당 카테고리 정보 조회
	 * @param int categoryseq   : 카테고리 순번
	 * @return  
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public CategoryDomain getCategoryBySeq(int categoryseq){
		return premiumMapper.selectCategoryBySeq(categoryseq);
	}

	/**
	 * 프리미엄 상세 리스트 조회
	 * @param PremiumDomain premiumDomain
	 * @return
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public ArrayList<PremiumDomain> premiumList(PremiumDomain premiumDomain){
		return premiumMapper.premiumSelectList(premiumDomain);
	}	

	/**
	 * 상품조회 (상품이력에서 가장 마지막 행을 가지고옴)
	 * @param webtoonseq
	 * @param timesseq
	 * @return
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public ProductHistoryDomain productDetail(Map<String,Object> param) {
		return productHistoryMapper.selectProductDetail(param);
	}

	/**
	 * 이용가능 상품조회
	 * @param param
	 * @return
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public ProductAvailDomain productAvail(String email, Map<String, Object> param) {
		ProductAvailDomain productAvailDomain = new ProductAvailDomain();
		
		productAvailDomain.setBuyid(email);
		productAvailDomain.setIdfg(StringUtil.defaultStr((String)param.get("idfg")));
		productAvailDomain.setPrdfg(StringUtil.defaultStr((String)param.get("prdfg"), "times"));
		
		//회차구매, 전체구매
		if(!"name".equals(productAvailDomain.getPrdfg()) && !"comment".equals(productAvailDomain.getPrdfg()))
		{
			if (param.get("webtoonseq") != null)
				productAvailDomain.setWebtoonseq(Integer.parseInt((String)param.get("webtoonseq")));
			
			//회차 구매
			if (param.get("timesseq") != null)
				productAvailDomain.setTimesseq(Integer.parseInt((String) param.get("timesseq")));
		}
		//네임콘, 스티콘 구매
		else
		{
			if((String)param.get("prdseq") != null)
				productAvailDomain.setPrdseq(Integer.parseInt((String)param.get("prdseq")));
		}
		
		return productAvailMapper.selectProductAvail(productAvailDomain);
	}
	
	/**
	 * 회차 상품 존재 여부 조회
	 * @param webtoonseq
	 * @return String 존재여부(Y)
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public String getExistTimesprd(String param){
		
		return productHistoryMapper.selectExistTimesprd(param);
	}
	
	/**
	 * 상품이력순번으로 상품정보 조회
	 * @param String : 상품이력순번
	 * @return ProductHistoryDomain : 상품정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public ProductHistoryDomain productDetailBySeq(String param){
		
		return productHistoryMapper.selectProductDetailBySeq(param);
	}
	
	/**
	 * 블루멤버십 가입여부 체크
	 * @param email, idfg
	 * @return String 가입여부(Y)
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public String getBlueMembershipInfo(String email, String idfg){
		
		OrderDomain order = new OrderDomain();
		order.setOrderid(email);
		order.setIdfg(idfg);
		
		return premiumMapper.selectBlueMembershipInfo(order);
	}

}
