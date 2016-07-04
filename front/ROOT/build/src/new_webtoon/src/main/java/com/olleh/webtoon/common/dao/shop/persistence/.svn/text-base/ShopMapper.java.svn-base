package com.olleh.webtoon.common.dao.shop.persistence;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.shop.domain.ShopDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.user.domain.IconDomain;

public interface ShopMapper 
{		
	
	/**
	 * SHOP 리스트 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int shopSelectCnt(ShopDomain shopDomain);
	
	/**
	 * SHOP 메인 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public List<ShopDomain> shopSelectList(ShopDomain shopDomain);
	
	/**
	 * SHOP 연관작품, 연관작가 상품 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int shopSelectRefListCnt(ShopDomain shopDomain);
	
	/**
	 * SHOP 연관작품, 연관작가 상품 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public List<ShopDomain> shopSelectRefList(ShopDomain shopDomain);
	
	/**
	 * SHOP 상품 상세조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public ShopDomain shopSelectDetail(ShopDomain shopDomain);
	
	/**
	 * SHOP 네임콘 리스트 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int shopSelectNameconTotalCnt(ShopDomain shopDomain);
	
	/**
	 * SHOP 네임콘 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public List<IconDomain> shopSelectNameconList(ShopDomain shopDomain);
	
	/**
	 * SHOP 스티콘 리스트 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int shopSelectCommentconTotalCnt(ShopDomain shopDomain);
	
	/**
	 * SHOP 스티콘 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public List<IconDomain> shopSelectCommentconList(ShopDomain shopDomain);
	
	/**
	 * SHOP PRDFG 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public String selectPrdfg(ShopDomain shopDomain);
	
	/**
	 * SHOP 관련작품 리스트 갯수
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int shopSelectReftoonTotalCnt(ShopDomain shopDomain);
	
	/**
	 * SHOP 관련작품 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public List<ToonDomain> shopSelectRefToonList(ShopDomain shopDomain);
	
	/**
	 * SHOP 관련 작품, 작가 추천 아이템 리스트 갯수
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int shopSelectRefprdTotalCnt(ShopDomain shopDomain);
	
	/**
	 * SHOP 관련 작품, 작가 추천 아이템 리스트 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public List<ShopDomain> shopSelectRefprdList(ShopDomain shopDomain);
	
	
	/**
	 * 스티콘,네임콘 검색 리스트 갯수 조회
	 * @param ShopDomain shopDomain
	 * @return
	 */
	public int searchSelectListCnt(ShopDomain shopDomain);	
	

	/**
	 *스티콘,네임콘 검색 리스트 목록
	 * @param ShopDomain shopDomain
	 * @return  
	 */

	public List<ShopDomain> searchSelectList(ShopDomain shopDomain);
	
	/**
	 * 보유한 스티콘 상품 리스트 목록
	 * @param String param
	 * @return List<ShopDomain> : 스티콘 상품 목록  
	 */
	public List<ShopDomain> commentconSelectList(String param);
	
	/**
	 * 스티콘상품에 대한 이미지 리스트 목록
	 * @param String param
	 * @return List<IconDomain> : 이미지 리스트 목록  
	 */
	public List<IconDomain> iconImageSelectList(String param);
}