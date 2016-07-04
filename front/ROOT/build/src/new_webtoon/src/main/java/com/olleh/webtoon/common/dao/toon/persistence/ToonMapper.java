/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ToonMapper.java
 * DESCRIPTION    : 웹툰 DB 테이블 연동관련  Mapper class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-23      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.toon.persistence;

import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.toon.domain.BookmarkDomain;
import com.olleh.webtoon.common.dao.toon.domain.FavoritesDomain;
import com.olleh.webtoon.common.dao.toon.domain.GenreDomain;
import com.olleh.webtoon.common.dao.toon.domain.NovelFileDomain;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;
import com.olleh.webtoon.common.dao.toon.domain.TodayToonDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonImageDomain;

public interface ToonMapper {
	
	/**
	 * Toon 리스트 조회 
	 * @param 검색, 정렬 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public List<ToonDomain> toonSelectList(Map<String,Object> param);
	
	/**
	 * Toon 상세정보 조회 
	 * @param 웹툰 번호 파라메터 정보 
	 * @return ToonDomain 웹툰 상세 정보
	 */
	public ToonDomain toonSelectDetail(Map<String,Object> param);
	
	/**
	 * 장르 리스트 조회 
	 * @return List<GenreDomain> 장르 목록 정보
	 */
	public List<GenreDomain> genreSelectList();
	
	/**
	 * 웹툰 작화 리스트 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public List<ToonDomain> timesSelectList(Map<String,Object> param);
	
	/**
	 * 소설 작화 리스트 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public List<ToonDomain> ntimesSelectList(Map<String,Object> param);
	
	/**
	 * 작품 구매 여부 조회 
	 * @param 유저 아이디, 웹툰 seq 
	 * @return 구매 여부
	 */
	public String selectToonPurchaseyn(Map<String,Object> param);
	
	/**
	 * 웹툰 작화 리스트 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public int timesSelectListCount(Map<String,Object> param);
	
	/**
	 * 웹툰 작화 정보 조회 
	 * @param 작화 회차 번호 파라메터 정보 
	 * @return ToonDomain 웹툰 작화 정보
	 */
	public ToonDomain timesSelectDetail(Map<String,Object> param);
	
	/**
	 * 웹툰 작화 회차 리스트 조회 
	 * @param 작품 번호 파라메터 정보
	 * @return List<ToonDomain> 웹툰 작화 회차 목록 정보
	 */
	public List<ToonDomain> tiemsnoSelectList(Map<String,Object> param);
	
	/**
	 * 웹툰 작화 이미지 리스트 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return List<ToonImageDomain> 웹툰 작화 이미지 목록 정보
	 */
	public List<ToonImageDomain> toonImageSelectList(Map<String,Object> param);
	
	/**
	 * 웹소설 작화 파일 리스트 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return List<ToonImageDomain> 웹툰 작화 이미지 목록 정보
	 */
	public List<NovelFileDomain> novelFileSelectList(Map<String,Object> param);
	
	/**
	 * 추천 웹툰 리스트 조회 
	 * @param 요일, 장르, 작품 구분
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	public List<ToonDomain> topToonSelectList(String param);
	
	public int stickerSelectCount(int timesseq);
	
	public void stickerInsert(StickerDomain stickerDomain);
	
	public void stickerUpdate(StickerDomain stickerDomain);
	
	public int stickerCheck(StickerDomain stickerDomain);
	
	public void bookmarkInsert(BookmarkDomain bookmarkDomain);
	
	public int bookmarkDelete(BookmarkDomain bookmarkDomain);
		
	public int bookmarkCheck(BookmarkDomain bookmarkDomain);
	
	public void favoritesInsert(FavoritesDomain favoritesDomain);
	
	public int favoritesDelete(FavoritesDomain favoritesDomain);
	
	public int favoritesCheck(FavoritesDomain favoritesDomain);
	
	public void favoritesPushUpdate(FavoritesDomain favoritesDomain);
	
	public int bookmarkSelectListCnt(BookmarkDomain bookmarkDomain);
	
	public List<ToonDomain> bookmarkSelectList(BookmarkDomain bookmarkDomain);
	
	public int favoritesSelectListCnt(FavoritesDomain favoritesDomain);
	
	public List<ToonDomain> favoritesSelectList(FavoritesDomain favoritesDomain);
	
	public int recentSelectListCnt(Map<String,Object> param);
	
	public List<ToonDomain> recentSelectList(Map<String,Object> param);
	
	@SuppressWarnings("rawtypes")
	public List mytoonSelectDetail(Map<String,Object> param);
	
	@SuppressWarnings("rawtypes")
	public List<String> mainTodayWebtoonSelectDateList(Map<String, Object> param);
	
	@SuppressWarnings("rawtypes")
	public List mainTodayWebtoonTypeSelectList(Map<String, Object> param);
	
	public List<TodayToonDomain> mainTodayWebtoonItemSelectList(Map<String, Object> param);
	
	@SuppressWarnings("rawtypes")
	public List mainRealTimeCommentSelectList(Map<String,Object> param);

	public int myStickerSelectCount(StickerDomain sticker);
	
	public ToonDomain simpleTimesSelectDetail(Map<String,Object> param);
	
	public ToonDomain simpleToonSelectDetail(Map<String,Object> param);
	
	public ToonDomain prdToonSelectDetail(Map<String,Object> param);
	
	public int toonRefPrdSelectCount(Map<String,Object> param);

	public int refItemCount(Map<String, Object> param);
}                                         