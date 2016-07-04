/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - RankingServiceImpl.java
 * 
 * DESCRIPTION
 * -  Ranking Service implement class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.ranking.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.ranking.domain.YoRankingDomain;
import com.olleh.webtoon.common.dao.ranking.persistence.RankingMapper;
import com.olleh.webtoon.common.dao.ranking.service.iface.RankingService;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.ranking.domain.RankingRefwebtoonDomain;

@Service("rankingService")
@Repository
public  class RankingServiceImpl implements RankingService
{
	@Autowired
	private RankingMapper rankingMapper;
	
	/**
	 * 실시간 랭킹 목록 조회
	 * @param 
	 * @return  List<ToonDomain>
	 */
	@Transactional(readOnly=true)
	public List<ToonDomain> realtimeList(Map<String,Object> param){
		
		//비율 정보를 셋팅 한다.
		Map<String, Object> rate = rankingMapper.selectRankingRate();
		param.put("visitrate", rate.get("visitrate"));
		param.put("activerate", rate.get("activerate"));
		param.put("pvrate", rate.get("pvrate"));
		param.put("uvrate", rate.get("uvrate"));
		param.put("cmtrate", rate.get("cmtrate"));
		param.put("snsrate", rate.get("snsrate"));
		param.put("stickerrate", rate.get("stickerrate"));
		param.put("favorrate", rate.get("favorrate"));
		param.put("bookrate", rate.get("bookrate"));
		
		return rankingMapper.selectRealtimeList(param);
	}
	
	/**
	 * 월간 랭킹 년/월 목록 조회
	 * 
	 * @return List<String> 년/월
	 */
	@Transactional(readOnly=true)
	public List<String> rankingDate(){
		return rankingMapper.selectRankingDate();
	}
	
	/**
	 * 월간 랭킹 목록 조회
	 * @param 
	 * @return  List<ToonDomain>
	 */
	@Transactional(readOnly=true)
	public List<ToonDomain> monthList(Map<String,Object> param){
		return rankingMapper.selectMonthList(param);
	}
	
	/**
	 * yo 랭킹 갯수 조회
	 * @param 
	 * @return  int : 목록 갯수
	 */
	@Transactional(readOnly=true)
	public int yorangkingListCnt(Map<String,Object> param){
		return rankingMapper.selectYorankingListCnt(param);
	}
	
	/**
	 * yo 랭킹 목록 조회
	 * @param 
	 * @return  List<YoRankingDomain>
	 */
	@Transactional(readOnly=true)
	public List<YoRankingDomain> yorangkingList(Map<String,Object> param){
		return rankingMapper.selectYorankingList(param);
	}
	
	/**
	 * Yo랭킹 정보를 조회한다.
	 * @param rankingeseq
	 * @return YoRankingDomain
	 */
	@Transactional(readOnly=true)
	public YoRankingDomain getYoRankingInfo(int rankingseq)
	{
		return rankingMapper.getYoRankingInfo(rankingseq);
	}
	
	/**
	 * 관련작품을 조회한다.
	 * @param rankingseq
	 * @return List<RankingRefwebtoonDomain>
	 */
	@Transactional(readOnly=true)
	public List<RankingRefwebtoonDomain> getRefwebtoonInfo(int rankingseq)
	{
		return rankingMapper.getRefwebtoonInfo(rankingseq);
	}
	
	/**
	 * Yo랭킹 스티커 갯수를 조회한다.
	 * @param rankingseq
	 * @return totalstickercnt
	 */
	@Transactional(readOnly=true)
	public int stickerCount(int rankingseq)
	{
		return rankingMapper.stickerSelectCount(rankingseq);
	}
	
	/**
	 * Yo랭킹 나의 스티커 갯수를 조회한다.
	 * @param StickerDomain
	 * @return stickercount
	 */
	@Transactional(readOnly=true)
	public int myStickerCount(StickerDomain sticker)
	{	
		return rankingMapper.myYorankingStickerSelectCount(sticker);
	}
	
	/**
	 * Yo랭킹 스티커를 등록한다.
	 * @param stickerDomain
	 * @return
	 */
	@Transactional(readOnly=false)
	public void stickerRegist(StickerDomain stickerDomain) 
	{		
		rankingMapper.stickerInsert(stickerDomain);			
	}
	
	/**
	 * 사용자의 Yo랭킹 스티커를 등록상태를 확인한다.
	 * @param stickerDomain
	 * @return
	 */
	@Transactional(readOnly=true)
	public int stickerCheck(StickerDomain stickerDomain){
		return rankingMapper.stickerCheck(stickerDomain);
	}
}
