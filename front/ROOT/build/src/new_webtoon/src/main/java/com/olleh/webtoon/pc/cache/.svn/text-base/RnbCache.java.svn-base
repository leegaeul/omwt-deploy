/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : RankingCache.java
 * DESCRIPTION    : 실시간 랭킹 캐싱 처리 관련 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-07-30      init
 *****************************************************************************/

package com.olleh.webtoon.pc.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

public class RnbCache {

	protected static Log logger = LogFactory.getLog(RnbCache.class);
	private static RnbCache rnbCache = null;       //RankingCache.java 인스턴스
	private static ModelAndView cachedModelAndView = null; //랭킹의 캐싱된 ModelAndView 객체
	private static long cacheTimeOut = 0;                  //캐시 만료 시스템 시간
	private static final long cacheTerm = 300000;          //캐시 유지 시간 - 5분

	/**
	 * 생성자 메소드.
	 */
	private RnbCache() {}

	/**
     * RankingCache 객체의 instance를 리턴한다.
     *
     * @return RankingCache rnbCache : RankingCache rnbCache
     */
    public static RnbCache getInstance() {

    	//MainCache 객체가 null일 경우만 생성하는 싱글톤 패턴.
    	if(rnbCache == null) {
    		rnbCache = new RnbCache();
    	}

        return rnbCache;

    }

    /**
     * 캐시된 ModelAndView 객체를 반환하는 메소드
     * 단, 캐시 유지 시간이 지나면 null을 반환하여 다시 넣도록 유도하게 된다.
     *
     * @return ModelAndView : 캐시된 메인화면 ModelAndView 객체
     */
	public ModelAndView getCachedModelAndView() {
		
		ModelAndView modelAndView = null;
		
		try {
			if(cachedModelAndView != null && System.currentTimeMillis() <= cacheTimeOut) {
				modelAndView = cachedModelAndView;
			}
		} catch(Exception e) {
			logger.error(e);
		}
		
		return modelAndView;

	}

	/**
	 * 캐싱할 객체를 전달 받아 캐시에 넣는다.
	 *
	 * @param ModelAndView modelAndView : 메인화면 ModelAndView 객체
	 * @return void
	 */
	public void putCache(ModelAndView modelAndView) {
		
		if(modelAndView != null) {

			try {
				cachedModelAndView = modelAndView;
				cacheTimeOut = System.currentTimeMillis() + cacheTerm;
			} catch(Exception e) {
				logger.error(e);
			}

		}

	}
	
	/**
	 * 캐시를 만료시킨다.
	 *
	 * @return void
	 */
	public void expireCache() {

		cachedModelAndView = null;

	}

}