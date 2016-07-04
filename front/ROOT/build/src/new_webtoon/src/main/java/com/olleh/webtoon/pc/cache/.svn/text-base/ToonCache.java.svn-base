/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : MainWeekCache.java
 * DESCRIPTION    : 메인화면의 주별 목록 데이터를 캐싱하여 저장해두는 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-02-25      init
 *****************************************************************************/

package com.olleh.webtoon.pc.cache;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("rawtypes")
public class ToonCache {

	protected static Log logger = LogFactory.getLog(ToonCache.class);
	private static ToonCache toonCache = null;       //MainCache 인스턴스

	private static HashMap<String, List> cacheList = new HashMap<String, List>(); //메인화면에 뿌리는 주별 목록항목들을 캐싱한 객체
	private static HashMap<String, Long> expirationTimeList = new HashMap<String, Long>();        //캐시 만료 시간 cacheList의 키값과 매칭된다.
	private static final long cacheTerm = 60000;   //캐시 유지 시간 - 1분
	private static final int maxCacheSize = 10000; //캐시를 하는 최대 개수

	/**
	 * 생성자 메소드.
	 */
	private ToonCache() {}

	/**
     * MainCache 객체의 instance를 리턴한다.
     *
     * @return MainCache mainCache : MainCache mainCache
     */
    public static ToonCache getInstance() {

    	//MainCache 객체가 null일 경우만 생성하는 싱글톤 패턴.
    	if(toonCache == null) {
    		toonCache = new ToonCache();
    	}

        return toonCache;

    }

    /**
     * "startRowNo|week|sort"
	 * 형태로 된 키값을 전달받아 캐시해 놓은 List 객체를 반환한다.
     *
     * 단, 캐시 유지 시간이 지나면 null을 반환하여 다시 넣도록 유도하게 된다.
     *
     * @param String key    : "startRowNo|week|sort" 형태로 된 키값
     * @return List : 캐시된 메인화면 주별 리스트 객체
     */
	public List getCachedList(String key) {

		List toonCache = null;
		Long expirationTime = null;

		if(key!=null && key.trim().length()>0) {

			try {
				expirationTime = expirationTimeList.get(key);

				if(expirationTime!=null && System.currentTimeMillis() < expirationTime.longValue()) {
					toonCache = cacheList.get(key);
				}
			} catch(Exception e) {
				logger.error(e);
			}

		}

		return toonCache;

	}

	/**
	 * "startRowNo|week|sort" 형태로 된 키값과
	 * 캐싱할 객체를 전달 받아 캐시에 넣는다.
	 *
	 * @param String key   : "startRowNo|week|sort" 형태로 된 키
	 * @param List : 메인화면 항목 객체
	 * @return void
	 */
	public void putCache(String key, List toonCache) {

		if(key!=null && key.trim().length()>0 && toonCache!=null) {

			try {
				//HashMap이 무한정 늘어나는 경우를 대비해 1만개로 제한해 놓았음.
				if(cacheList.size() > maxCacheSize) {
					expireAllCache();
				}

				long expirationTime = System.currentTimeMillis() + cacheTerm;

				cacheList.put(key, toonCache);
				expirationTimeList.put(key, expirationTime);

			} catch(Exception e) {
				logger.error(e);
			}

		}

	}

	/**
	 * "startRowNo|week|sort" 형태로 된 키를
	 * 전달받아 해당 캐시를 만료시킨다.
	 *
	 * @return void
	 */
	public void expireCache(String key) {

		if(key!=null && key.trim().length()>0) {
			cacheList.remove(key);
			expirationTimeList.remove(key);
		}

	}

	/**
	 * 모든 캐시를 만료시킨다.
	 *
	 * @return void
	 */
	public void expireAllCache() {

		cacheList.clear();
		cacheList = new HashMap<String, List>();

		expirationTimeList.clear();
		expirationTimeList = new HashMap<String, Long>();

	}

}