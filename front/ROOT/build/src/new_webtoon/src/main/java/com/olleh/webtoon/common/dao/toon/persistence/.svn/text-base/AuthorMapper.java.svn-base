/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : AuthorMapper.java
 * DESCRIPTION    : 웹툰 작가 DB 테이블 연동관련  Mapper class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-23      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.toon.persistence;

import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.shop.domain.ShopDomain;
import com.olleh.webtoon.common.dao.toon.domain.AuthorDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineDomain;

public interface AuthorMapper {
	
	/**
	 * 작가 상세정보 
	 * @param 작가 번호 파라메터 정보 
	 * @return AuthorDomain 웹툰 작가 상세정보
	 */
	public AuthorDomain authorSelectDetail(Map<String,Object> param);
	
	/**
	 * 프로필 공개여부 
	 * @param 작가 번호 파라메터 정보 
	 * @return String 공개여부
	 */
	public String selectAuthorProfileyn(Map<String,Object> param);
	
	/**
	 * 작가의 연재작품 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public int authorToonSelectListCount(Map<String,Object> param);
	
	/**
	 * 작가의 연재작품 목록 조회 
	 * @param 작가 번호 파라메터 정보 
	 * @return List<ToonDomain> 작가의 연재작품 목록 정보
	 */
	public List<ToonDomain> authorToonSelectList(Map<String,Object> param);
	
	/**
	 * 작가 에피소드 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public int authorYoyozineSelectListCount(Map<String,Object> param);
	
	/**
	 * 작가 에피소드 목록 조회 
	 * @param 작가번호 파라메터 정보 
	 * @return List<YoyozineDomain> 작가 에피소드 목록 정보
	 */
	public List<YoyozineDomain> authorYoyozineSelectList(Map<String,Object> param);
	
	/**
	 * 작가 관련 아이템 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ShopDomain> 웹툰 목록 정보
	 */
	public int shopAuthorItemCount(Map<String, Object> param);

	/**
	 * 작가 관련 아이템 목록 조회
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ShopDomain> 웹툰 목록 정보
	 */
	public List<ShopDomain> shopAuthorItemList(Map<String, Object> param);
}                                         
