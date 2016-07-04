/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : AuthorServiceImpl.java
 * DESCRIPTION    : 웹툰 작가 Service 구현 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-23      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.toon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.shop.domain.ShopDomain;
import com.olleh.webtoon.common.dao.toon.domain.AuthorDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.persistence.AuthorMapper;
import com.olleh.webtoon.common.dao.toon.service.iface.AuthorService;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineDomain;

@Service("AuthorService")
@Repository
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	private AuthorMapper authorMapper;	
	
	/**
	 * 작가 상세정보 
	 * @param 작가 번호 파라메터 정보 
	 * @return AuthorDomain 웹툰 작가 상세정보
	 */
	@Transactional(readOnly=true)
	public AuthorDomain authorDetail(Map<String,Object> param){
		return authorMapper.authorSelectDetail(param);
	}
	
	/**
	 * 프로필 공개여부
	 * @param 작가 번호 파라메터 정보 
	 * @return 공개여부
	 */
	@Transactional(readOnly=true)
	public String getAuthorProfileyn(Map<String,Object> param){
		return authorMapper.selectAuthorProfileyn(param);
	}
	
	/**
	 * 작가의 연재작품 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	@Transactional(readOnly=true)
	public int authorToonListCount(Map<String,Object> param){
		return authorMapper.authorToonSelectListCount(param);
	}
	
	/**
	 * 작가의 연재작품 목록 조회 
	 * @param 검색, 정렬 파라메터 정보 
	 * @return List<ToonDomain> 작가의 연재작품 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ToonDomain> authorToonList(Map<String,Object> param){
		return authorMapper.authorToonSelectList(param);
	}
	
	/**
	 * 작가 에피소드 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	@Transactional(readOnly=true)
	public int authorYoyozineListCount(Map<String,Object> param){
		return authorMapper.authorYoyozineSelectListCount(param);
	}
	
	/**
	 * 작가 에피소드 목록 조회 
	 * @param 작가번호 파라메터 정보 
	 * @return List<YoyozineDomain> 작가 에피소드 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<YoyozineDomain> authorYoyozineList(Map<String,Object> param){
		return authorMapper.authorYoyozineSelectList(param);
	}

	/**
	 * 작가 관련 아이템 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ShopDomain> 웹툰 목록 정보
	 */
	@Transactional(readOnly=true)
	public int authorItemListCount(Map<String, Object> param) {
		return authorMapper.shopAuthorItemCount(param);
	}

	/**
	 * 작가 관련 아이템 목록 조회
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ShopDomain> 웹툰 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ShopDomain> authorItemList(Map<String, Object> param) {
		return authorMapper.shopAuthorItemList(param);
	}

}