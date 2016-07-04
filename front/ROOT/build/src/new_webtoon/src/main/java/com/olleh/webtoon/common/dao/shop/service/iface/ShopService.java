/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - ShopService.java
 * 
 * DESCRIPTION
 * -  Shop Service interface class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.shop.service.iface;

import java.util.List;

import com.olleh.webtoon.common.dao.shop.domain.ShopDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.user.domain.IconDomain;


public interface ShopService 
{	
	/**
	 * SHOP 리스트 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int shopListCnt(ShopDomain shopDomain);
	
	/**
	 * SHOP 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public List<ShopDomain> shopList(ShopDomain shopDomain);
	
	/**
	 * SHOP 연관작품, 연관작가 상품 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int shopRefListCnt(ShopDomain shopDomain);
	
	/**
	 * SHOP 연관작품, 연관작가 상품 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public List<ShopDomain> shopRefList(ShopDomain shopDomain);
	
	/**
	 * SHOP 상품 상세조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public ShopDomain shopDetail(ShopDomain shopDomain);
	
	/**
	 * SHOP 상품 아이콘 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int shopIconTotalCnt(ShopDomain shopDomain);
	
	/**
	 * SHOP 상품 아이콘 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public List<IconDomain> shopIconList(ShopDomain shopDomain);
	
	/**
	 * SHOP 관련작품 리스트 갯수
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int shopReftoonTotalCnt(ShopDomain shopDomain);
	
	/**
	 * SHOP 관련작품 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public List<ToonDomain> shopReftoonList(ShopDomain shopDomain);
	
	/**
	 * SHOP 관련 작품, 작가 추천 아이템 리스트 갯수
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int shopRefproductTotalCnt(ShopDomain shopDomain);
	
	/**
	 * SHOP 관련 작품, 작가 추천 아이템 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public List<ShopDomain> shopRefprdList(ShopDomain shopDomain);
	
	/**
	 * 스티콘,네임콘 검색 리스트 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int searchListCnt(ShopDomain shopDomain);
	
	/**
	 * 스티콘,네임콘 검색 리스트 목록
	 * @param ShopDomain shopDomain
	 * @return  
	 */
	public List<ShopDomain> searchList(ShopDomain shopDomain);
	
	/**
	 * 보유한 스티콘 상품 리스트 목록
	 * @param String param
	 * @return List<ShopDomain> : 스티콘 상품 목록  
	 */
	public List<ShopDomain> commentconList(String param);
	
	/**
	 * 스티콘상품에 대한 이미지 리스트 목록
	 * @param String param
	 * @return List<IconDomain> : 이미지 리스트 목록  
	 */
	public List<IconDomain> iconImageList(String param);
}
