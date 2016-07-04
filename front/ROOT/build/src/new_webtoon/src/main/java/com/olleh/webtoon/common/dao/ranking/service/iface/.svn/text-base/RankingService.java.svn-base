/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market Webtoon
 *  
 * FILE NAME
 * - RankingService.java
 * 
 * DESCRIPTION
 * -  Ranking Service interface class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.ranking.service.iface;

import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.ranking.domain.YoRankingDomain;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.ranking.domain.RankingRefwebtoonDomain;

public interface RankingService 
{	
	/**
	 * 실시간 랭킹 목록 조회
	 * @param 
	 * @return List<ToonDomain> 
	 */
	public List<ToonDomain> realtimeList(Map<String,Object> param);
	
	/**
	 * 월간 랭킹 년/월 목록 조회
	 * 
	 * @return List<String> 년/월
	 */
	public List<String> rankingDate();
	
	/**
	 * 월간 랭킹 목록 조회
	 * @param 
	 * @return List<ToonDomain>
	 */
	public List<ToonDomain> monthList(Map<String,Object> param);
	
	/**
	 * yo 랭킹 갯수 조회
	 * @param 
	 * @return int : 목록 갯수 
	 */
	public int yorangkingListCnt(Map<String,Object> param);
	
	/**
	 * yo 랭킹 목록 조회
	 * @param 
	 * @return List<YoRankingDomain> 
	 */
	public List<YoRankingDomain> yorangkingList(Map<String,Object> param);
	
	/**
	 * Yo랭킹 정보를 조회한다.
	 * @param rankingeseq
	 * @return YoRankingDomain
	 */
	public YoRankingDomain getYoRankingInfo(int rankingseq);
	
	/**
	 * 관련작품을 조회한다.
	 * @param rankingseq
	 * @return List<RankingRefwebtoonDomain>
	 */
	public List<RankingRefwebtoonDomain> getRefwebtoonInfo(int rankingseq);
	
	/**
	 * Yo랭킹 스티커 갯수를 조회한다.
	 * @param rankingseq
	 * @return totalstickercnt
	 */
	public int stickerCount(int rankingseq);
	
	/**
	 * Yo랭킹 나의 스티커 갯수를 조회한다.
	 * @param StickerDomain
	 * @return stickercount
	 */
	public int myStickerCount(StickerDomain sticker);
	
	/**
	 * Yo랭킹 스티커를 등록한다.
	 * @param stickerDomain
	 * @return
	 */
	public void stickerRegist(StickerDomain stickerDomain);
	
	/**
	 * 사용자의 Yo랭킹 스티커를 등록상태를 확인한다.
	 * @param stickerDomain
	 * @return
	 */
	public int stickerCheck(StickerDomain stickerDomain);
}
