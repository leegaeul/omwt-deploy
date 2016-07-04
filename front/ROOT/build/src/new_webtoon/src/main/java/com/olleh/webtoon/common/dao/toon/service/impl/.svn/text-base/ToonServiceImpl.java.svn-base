/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ToonServiceImpl.java
 * DESCRIPTION    : 웹툰 Service 구현 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-23      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.toon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.toon.domain.BookmarkDomain;
import com.olleh.webtoon.common.dao.toon.domain.FavoritesDomain;
import com.olleh.webtoon.common.dao.toon.domain.GenreDomain;
import com.olleh.webtoon.common.dao.toon.domain.NovelFileDomain;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;
import com.olleh.webtoon.common.dao.toon.domain.TodayToonDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonImageDomain;
import com.olleh.webtoon.common.dao.toon.persistence.ToonMapper;
import com.olleh.webtoon.common.dao.toon.service.iface.ToonService;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.pc.cache.ToonCache;

@Service("ToonService")
@Repository
public class ToonServiceImpl implements ToonService{
	
	@Autowired
	private ToonMapper toonMapper;
	
	/**
	 * Toon 리스트 조회 
	 * @param 검색, 정렬 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<ToonDomain> toonList(Map<String,Object> param){
		List<ToonDomain> cachedList = null;         //HashMap에 캐시되어 있던 List<ToonDomain>		
		List<ToonDomain> toonList = null;
		
		String sort = "";
		if(param.get("sort") == null || "".equals((String)param.get("sort"))) sort = "update";
		else sort = (String)param.get("sort");
		
		//캐시 데이터의 키값으로 "toon|sort" 형태임
		String cacheKey = "toon|" + sort;
		ToonCache toonCache = ToonCache.getInstance();
		
		cachedList = toonCache.getCachedList(cacheKey);
		
		if(cachedList == null) { //캐시된 데이터가 없거나 만료된 경우
			toonList = toonMapper.toonSelectList(param);
			
			//목록이 있는 경우 DB에서 새로 조회한 데이터를 캐시 데이터에 추가한다.
			if(toonList != null && toonList.size() > 0) {
				toonCache.putCache(cacheKey, toonList);
			}			
		}else { //캐시된 데이터가 있는 경우
			toonList = cachedList;
		}
		
		//toonList = toonMapper.toonSelectList(param);
		
		//웹툰, 웹소설 분기처리
		String toonfg = param.get("toonfg") == null ? "all" : param.get("toonfg").toString();
		List<ToonDomain> resultToonList = null;
		
		if("all".equals(toonfg)){
			resultToonList = toonList;
		}else{
			resultToonList = new ArrayList<ToonDomain>();
			for(ToonDomain toon : toonList){
				if(toonfg.equals(toon.getToonfg())){
					resultToonList.add(toon);
				}
			}
		}
		
		return resultToonList;
	}
	
	/**
	 * Toon 상세정보 조회 
	 * @param 웹툰 번호 파라메터 정보 
	 * @return ToonDomain 웹툰 상세 정보
	 */
	@Transactional(readOnly=true)
	public ToonDomain toonDetail(Map<String,Object> param){
		return toonMapper.toonSelectDetail(param);
	}
	
	
	/**
	 * 장르 리스트 조회 
	 * @return List<GenreDomain> 장르 목록 정보
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<GenreDomain> genreList(){
		List<GenreDomain> cachedList = null;         //HashMap에 캐시되어 있던 List<GenreDomain>		
		List<GenreDomain> genreList = null;
		
		//캐시 데이터의 키값으로 "startRowNo|week|sort" 형태임
		String cacheKey = "genreList";
		ToonCache toonCache = ToonCache.getInstance();
		
		cachedList = toonCache.getCachedList(cacheKey);
		
		if(cachedList == null) { //캐시된 데이터가 없거나 만료된 경우
			genreList = toonMapper.genreSelectList();
			
			//목록이 있는 경우 DB에서 새로 조회한 데이터를 캐시 데이터에 추가한다.
			if(genreList != null && genreList.size() > 0) {
				toonCache.putCache(cacheKey, genreList);
			}			
		}else { //캐시된 데이터가 있는 경우
			genreList = cachedList;
		}
				
		return genreList;
	}	

	
	/**
	 * 웹툰 작화 리스트 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ToonDomain> timesList(Map<String,Object> param){
		return toonMapper.timesSelectList(param);
	}	

	
	/**
	 * 소설 작화 리스트 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ToonDomain> ntimesList(Map<String,Object> param){
		return toonMapper.ntimesSelectList(param);
	}
	
	/**
	 * 작품 구매 여부 조회 
	 * @param 유저 아이디, 웹툰 seq 
	 * @return 구매 여부
	 */
	@Transactional(readOnly=true)
	public String getToonPurchaseyn(Map<String,Object> param){
		return toonMapper.selectToonPurchaseyn(param);
	}

	
	/**
	 * 웹툰 작화 리스트 갯수 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	@Transactional(readOnly=true)
	public int timesListCount(Map<String,Object> param){
		return toonMapper.timesSelectListCount(param);
	}
	
	/**
	 * 웹툰 작화 정보 조회 
	 * @param 작화 회차 번호 파라메터 정보 
	 * @return ToonDomain 웹툰 작화 정보
	 */
	@Transactional(readOnly=true)
	public ToonDomain timesDetail(Map<String,Object> param){
		return toonMapper.timesSelectDetail(param);
	}
	
	/**
	 * 웹툰 작화 회차 리스트 조회 
	 * @param 작품 번호 파라메터 정보
	 * @return List<ToonDomain> 웹툰 작화 회차 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ToonDomain> tiemsnoList(Map<String,Object> param){
		return toonMapper.tiemsnoSelectList(param);
	}
	
	/**
	 * 웹툰 작화 이미지 리스트 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return List<ToonImageDomain> 웹툰 작화 이미지 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ToonImageDomain> toonImageList(Map<String,Object> param){
		return toonMapper.toonImageSelectList(param);
	}
	
	/**
	 * 웹소설 작화 파일 리스트 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return List<ToonImageDomain> 웹툰 작화 이미지 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<NovelFileDomain> novelFileList(Map<String,Object> param){
		return toonMapper.novelFileSelectList(param);
	}
	
	/**
	 * 추천 웹툰 리스트 조회 
	 * @param 요일, 장르, 작품 구분
	 * @return List<ToonDomain> 웹툰 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ToonDomain> topToonList(String param)
	{
		return toonMapper.topToonSelectList(param);
	}
	
	@Transactional(readOnly=true)
	public int stickerCount(int timesseq){
		return toonMapper.stickerSelectCount(timesseq);
	}
	
	@Transactional(readOnly=true)
	public int myStickerCount(StickerDomain sticker){		
		return toonMapper.myStickerSelectCount(sticker);
	}
	
	@Transactional(readOnly=false)
	public void stickerRegist(StickerDomain stickerDomain) {		
		toonMapper.stickerInsert(stickerDomain);		
		toonMapper.stickerUpdate(stickerDomain);		
	}
	
	@Transactional(readOnly=true)
	public int stickerCheck(StickerDomain stickerDomain){
		return toonMapper.stickerCheck(stickerDomain);
	}
	
	@Transactional(readOnly=false)
	public void bookmarkRegist(BookmarkDomain bookmarkDomain) {		
		toonMapper.bookmarkInsert(bookmarkDomain);		
	}
	
	@Transactional(readOnly=false)
	public int bookmarkDelete(BookmarkDomain bookmarkDomain) {		
		return toonMapper.bookmarkDelete(bookmarkDomain);		
	}
	
	@Transactional(readOnly=true)
	public int bookmarkCheck(BookmarkDomain bookmarkDomain){
		return toonMapper.bookmarkCheck(bookmarkDomain);
	}
	
	@Transactional(readOnly=true)
	public int bookmarkListCnt(BookmarkDomain bookmarkDomain){
		return toonMapper.bookmarkSelectListCnt(bookmarkDomain);
	}
	
	@Transactional(readOnly=true)
	public List<ToonDomain> bookmarkList(BookmarkDomain bookmarkDomain){
		return toonMapper.bookmarkSelectList(bookmarkDomain);
	}
	
	@Transactional(readOnly=false)
	public void favoritesRegist(FavoritesDomain favoritesDomain) {		
		toonMapper.favoritesInsert(favoritesDomain);		
	}
	
	@Transactional(readOnly=false)
	public int favoritesDelete(FavoritesDomain favoritesDomain) {		
		return toonMapper.favoritesDelete(favoritesDomain);		
	}
	
	@Transactional(readOnly=true)
	public int favoritesCheck(FavoritesDomain favoritesDomain){
		return toonMapper.favoritesCheck(favoritesDomain);
	}
	
	@Transactional(readOnly=true)
	public int favoritesListCnt(FavoritesDomain favoritesDomain){
		return toonMapper.favoritesSelectListCnt(favoritesDomain);
	}
	
	@Transactional(readOnly=true)
	public List<ToonDomain> favoritesList(FavoritesDomain favoritesDomain){
		return toonMapper.favoritesSelectList(favoritesDomain);
	}
	
	@Transactional(readOnly=true)
	public int recentListCnt(Map<String,Object> param){
		return toonMapper.recentSelectListCnt(param);
	}
	
	@Transactional(readOnly=false)
	public void favoritesPushRegist(FavoritesDomain favoritesDomain) {		
		toonMapper.favoritesPushUpdate(favoritesDomain);		
	}
	
	@Transactional(readOnly=true)
	public List<ToonDomain> recentList(Map<String,Object> param){
		return toonMapper.recentSelectList(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	public List mytoonDetail(Map<String,Object> param){
		return toonMapper.mytoonSelectDetail(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	public Map<String, Object> mainTodayWebtoonDateList(Map<String, Object> param){
		
		List<String> result = toonMapper.mainTodayWebtoonSelectDateList(param);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("monday",    result.get(DateUtil.MONDAY - 1));
		map.put("tuesday",   result.get(DateUtil.TUESDAY - 1));
		map.put("wednesday", result.get(DateUtil.WEDNESDAY - 1));
		map.put("thursday",  result.get(DateUtil.THURSDAY - 1));
		map.put("friday",    result.get(DateUtil.FRIDAY - 1));
		map.put("saturday",  result.get(DateUtil.SATURDAY - 1));
		map.put("sunday",    result.get(DateUtil.SUNDAY - 1));
		
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	public List mainTodayWebtoonTypeList(Map<String, Object> param){
		return toonMapper.mainTodayWebtoonTypeSelectList(param);
	}
	
	@Transactional(readOnly=true)
	public List<TodayToonDomain> mainTodayWebtoonItemList(Map<String, Object> param){
		return toonMapper.mainTodayWebtoonItemSelectList(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	public List mainRealTimeCommentList(Map<String,Object> param){
		return toonMapper.mainRealTimeCommentSelectList(param);
	}
	
	@Transactional(readOnly=true)
	public ToonDomain simpleTimesDetail(Map<String,Object> param){
		return toonMapper.simpleTimesSelectDetail(param);
	}
	
	@Transactional(readOnly=true)
	public ToonDomain simpleToonDetail(Map<String,Object> param){
		return toonMapper.simpleToonSelectDetail(param);
	}
	
	@Transactional(readOnly=true)
	public ToonDomain prdToonDetail(Map<String,Object> param){
		return toonMapper.prdToonSelectDetail(param);
	}
	
	@Transactional(readOnly=true)
	public int toonRefPrdCount(Map<String,Object> param){
		return toonMapper.toonRefPrdSelectCount(param);
	}

	@Transactional(readOnly=true)
	public int refItemCount(Map<String, Object> param) {
		return toonMapper.refItemCount(param);
	}

}