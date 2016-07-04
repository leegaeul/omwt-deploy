/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - ShopServiceImpl.java
 * 
 * DESCRIPTION
 * -  Shop Service implement class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.shop.domain.ShopDomain;
import com.olleh.webtoon.common.dao.shop.persistence.ShopMapper;
import com.olleh.webtoon.common.dao.shop.service.iface.ShopService;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.user.domain.IconDomain;

@Service("shopService")
@Repository
public  class ShopServiceImpl implements ShopService
{	
	@Autowired
	private ShopMapper shopMapper;
	
	/**
	 * SHOP 리스트 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public int shopListCnt(ShopDomain shopDomain){
		return shopMapper.shopSelectCnt(shopDomain);
	}
	
	/**
	 * SHOP 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ShopDomain> shopList(ShopDomain shopDomain){
		return shopMapper.shopSelectList(shopDomain);
	}
	
	/**
	 * SHOP 연관작품, 연관작가 상품 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public int shopRefListCnt(ShopDomain shopDomain){
		return shopMapper.shopSelectRefListCnt(shopDomain);
	}
	
	/**
	 * SHOP 연관작품, 연관작가 상품 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ShopDomain> shopRefList(ShopDomain shopDomain){
		return shopMapper.shopSelectRefList(shopDomain);
	}
	
	/**
	 * SHOP 상품 상세조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public ShopDomain shopDetail(ShopDomain shopDomain){
		
		return shopMapper.shopSelectDetail(shopDomain);
	}
	
	/**
	 * SHOP 상품 아이콘 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public int shopIconTotalCnt(ShopDomain shopDomain){
		
		//네임콘
		if("name".equals(shopDomain.getPrdfg()))
			return shopMapper.shopSelectNameconTotalCnt(shopDomain);
		
		//스티콘
		return shopMapper.shopSelectCommentconTotalCnt(shopDomain);
	}
	
	/**
	 * SHOP 상품 아이콘 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<IconDomain> shopIconList(ShopDomain shopDomain){
		
		String prdfg = shopMapper.selectPrdfg(shopDomain);
		
		//네임콘
		if("name".equals(prdfg))
			return shopMapper.shopSelectNameconList(shopDomain);
		
		//스티콘
		return shopMapper.shopSelectCommentconList(shopDomain);
	}
	
	/**
	 * SHOP 관련작품 리스트 갯수
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public int shopReftoonTotalCnt(ShopDomain shopDomain){
		return shopMapper.shopSelectReftoonTotalCnt(shopDomain);
	}
	
	/**
	 * SHOP 관련작품 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ToonDomain> shopReftoonList(ShopDomain shopDomain){
		return shopMapper.shopSelectRefToonList(shopDomain);
	}
	
	/**
	 * SHOP 관련 작품, 작가 추천 아이템 리스트 갯수
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public int shopRefproductTotalCnt(ShopDomain shopDomain){
		return shopMapper.shopSelectRefprdTotalCnt(shopDomain);
	}
	
	/**
	 * SHOP 관련 작품, 작가 추천 아이템 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ShopDomain> shopRefprdList(ShopDomain shopDomain){
		return shopMapper.shopSelectRefprdList(shopDomain);
	}
	
	/**
	 * 스티콘,네임콘 검색 리스트 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public int searchListCnt(ShopDomain shopDomain){
		return shopMapper.searchSelectListCnt(shopDomain);
	}
	
	/**
	 *스티콘,네임콘 검색 리스트 목록
	 * @param ShopDomain shopDomain
	 * @return  
	 */
	@Transactional(readOnly=true)
	public List<ShopDomain> searchList(ShopDomain shopDomain){
		return shopMapper.searchSelectList(shopDomain);
	}
	
	/**
	 * 보유한 스티콘 상품 리스트 목록
	 * @param String param
	 * @return List<ShopDomain> : 스티콘 상품 목록  
	 */
	@Transactional(readOnly=true)
	public List<ShopDomain> commentconList(String param){
		return shopMapper.commentconSelectList(param);
	}
	
	/**
	 * 스티콘상품에 대한 이미지 리스트 목록
	 * @param String param
	 * @return List<IconDomain> : 이미지 리스트 목록  
	 */
	@Transactional(readOnly=true)
	public List<IconDomain> iconImageList(String param){
		return shopMapper.iconImageSelectList(param);
	}
}