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

import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.toon.domain.BookmarkDomain;
import com.olleh.webtoon.common.dao.toon.domain.FavoritesDomain;
import com.olleh.webtoon.common.dao.toon.domain.GenreDomain;
import com.olleh.webtoon.common.dao.toon.domain.NovelFileDomain;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;
import com.olleh.webtoon.common.dao.toon.domain.TodayToonDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonImageDomain;


public interface ToonService {
	
	/**
	 * Toon 리스트 조회 
	 * @param 검색, 정렬 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public List<ToonDomain> toonList(Map<String,Object> param);
	
	/**
	 * Toon 상세정보 조회 
	 * @param 웹툰 번호 파라메터 정보 
	 * @return ToonDomain 웹툰 상세 정보
	 */
	@Transactional(readOnly=true)
	public ToonDomain toonDetail(Map<String,Object> param);
	
	/**
	 * 장르 리스트 조회 
	 * @return List<GenreDomain> 장르 목록 정보
	 */
	public List<GenreDomain> genreList();
	
	/**
	 * 웹툰 작화 리스트 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ToonDomain> timesList(Map<String,Object> param);
	
	/**
	 * 소설 작화 리스트 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ToonDomain> ntimesList(Map<String,Object> param);
	
	/**
	 * 작품 구매 여부 조회 
	 * @param 유저 아이디, 웹툰 seq 
	 * @return 구매 여부
	 */
	@Transactional(readOnly=true)
	public String getToonPurchaseyn(Map<String,Object> param);
	
	/**
	 * 웹툰 작화 리스트 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public int timesListCount(Map<String,Object> param);
	
	/**
	 * 웹툰 작화 정보 조회 
	 * @param 작화 회차 번호 파라메터 정보 
	 * @return ToonDomain 웹툰 작화 정보
	 */
	public ToonDomain timesDetail(Map<String,Object> param);
	
	/**
	 * 웹툰 작화 회차 리스트 조회 
	 * @param 작품 번호 파라메터 정보
	 * @return List<ToonDomain> 웹툰 작화 회차 목록 정보
	 */
	public List<ToonDomain> tiemsnoList(Map<String,Object> param);
	
	/**
	 * 웹툰 작화 이미지 리스트 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return List<ToonImageDomain> 웹툰 작화 이미지 목록 정보
	 */
	public List<ToonImageDomain> toonImageList(Map<String,Object> param);
	
	/**
	 * 웹소설 작화 파일 리스트 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return List<ToonImageDomain> 웹툰 작화 이미지 목록 정보
	 */
	public List<NovelFileDomain> novelFileList(Map<String,Object> param);
	
	/**
	 * 추천 웹툰 리스트 조회 
	 * @param 요일, 장르, 작품 구분
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public List<ToonDomain> topToonList(String param);
	
	public int stickerCount(int timesseq);
	
	public void stickerRegist(StickerDomain stickerDomain);
	
	public int stickerCheck(StickerDomain stickerDomain);
	
	public void bookmarkRegist(BookmarkDomain bookmarkDomain);
	
	public int bookmarkDelete(BookmarkDomain bookmarkDomain);
	
	public int bookmarkCheck(BookmarkDomain bookmarkDomain);
	
	public int bookmarkListCnt(BookmarkDomain bookmarkDomain);
	
	public List<ToonDomain> bookmarkList(BookmarkDomain bookmarkDomain);
	
	public void favoritesRegist(FavoritesDomain favoritesDomain);
	
	public int favoritesDelete(FavoritesDomain favoritesDomain);
	
	public int favoritesCheck(FavoritesDomain favoritesDomain);
	
	public int favoritesListCnt(FavoritesDomain favoritesDomain);
	
	public void favoritesPushRegist(FavoritesDomain favoritesDomain);
	
	public List<ToonDomain> favoritesList(FavoritesDomain favoritesDomain);
	
	public int recentListCnt(Map<String,Object> param);
	
	public List<ToonDomain> recentList(Map<String,Object> param);
	
	@SuppressWarnings("rawtypes")
	public List mytoonDetail(Map<String,Object> param);
	
	@SuppressWarnings("rawtypes")
	public Map<String, Object> mainTodayWebtoonDateList(Map<String, Object> param);
	
	@SuppressWarnings("rawtypes")
	public List mainTodayWebtoonTypeList(Map<String, Object> param);
	
	public List<TodayToonDomain> mainTodayWebtoonItemList(Map<String, Object> param);
	
	@SuppressWarnings("rawtypes")
	public List mainRealTimeCommentList(Map<String,Object> param);

	public int myStickerCount(StickerDomain sticker);
	
	public ToonDomain simpleTimesDetail(Map<String,Object> param);
	
	public ToonDomain simpleToonDetail(Map<String,Object> param);
	
	public ToonDomain prdToonDetail(Map<String,Object> param);
	
	public int toonRefPrdCount(Map<String,Object> param);

	public int refItemCount(Map<String, Object> param);
}