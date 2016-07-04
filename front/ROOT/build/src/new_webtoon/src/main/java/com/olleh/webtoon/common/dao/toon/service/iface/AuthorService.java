/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ToonService.java
 * DESCRIPTION    : 웹툰   Service interface class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-23      init
 *****************************************************************************/


package com.olleh.webtoon.common.dao.toon.service.iface;

import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.shop.domain.ShopDomain;
import com.olleh.webtoon.common.dao.toon.domain.AuthorDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineDomain;


public interface AuthorService {
	
	/**
	 * 작가 상세정보 
	 * @param 작가 번호 파라메터 정보 
	 * @return AuthorDomain 웹툰 작가 상세정보
	 */
	public AuthorDomain authorDetail(Map<String,Object> param);
	
	/**
	 * 프로필 공개여부 
	 * @param 작가 번호 파라메터 정보 
	 * @return String 공개여부
	 */
	public String getAuthorProfileyn(Map<String,Object> param);
	
	/**
	 * 작가의 연재작품 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public int authorToonListCount(Map<String,Object> param);
	
	/**
	 * 작가의 연재작품 목록 조회 
	 * @param 검색, 정렬 파라메터 정보 
	 * @return List<ToonDomain> 작가의 연재작품 목록 정보
	 */
	public List<ToonDomain> authorToonList(Map<String,Object> param);
	
	/**
	 * 작가 에피소드 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public int authorYoyozineListCount(Map<String,Object> param);
	
	/**
	 * 작가 에피소드 목록 조회 
	 * @param 작가번호 파라메터 정보 
	 * @return List<YoyozineDomain> 작가 에피소드 목록 정보
	 */
	public List<YoyozineDomain> authorYoyozineList(Map<String,Object> param);

	/**
	 * 작가 관련 아이템 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ShopDomain> 웹툰 목록 정보
	 */
	public int authorItemListCount(Map<String,Object> param);

	/**
	 * 작가 관련 아이템 목록 조회
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ShopDomain> 웹툰 목록 정보
	 */
	public List<ShopDomain> authorItemList(Map<String, Object> param);

	
}