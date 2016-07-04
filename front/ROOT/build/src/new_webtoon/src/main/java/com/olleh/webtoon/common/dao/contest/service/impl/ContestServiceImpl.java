/*****************************************************************************
 * PROJECT NAME   : 올레마켓 공모전
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ContestServiceImpl.java
 * DESCRIPTION    : 공모전 Service 구현 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-12-18      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.contest.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.contest.domain.ContestDomain;
import com.olleh.webtoon.common.dao.contest.domain.ContestImageDomain;
import com.olleh.webtoon.common.dao.contest.persistence.ContestMapper;
import com.olleh.webtoon.common.dao.contest.service.iface.ContestService;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;
import com.olleh.webtoon.pc.cache.ContestCache;

@Service("ContestService")
@Repository
public class ContestServiceImpl implements ContestService{
	
	@Autowired
	private ContestMapper contestMapper;
	
	/**
	 * 공모전 리스트 조회 
	 * @param 검색, 정렬 파라메터 정보
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<ContestDomain> contestList(Map<String,Object> param) {
		
		//HashMap에 캐시되어 있던 List<ContestDomain>
		List<ContestDomain> cachedList  = null;         		
		List<ContestDomain> contestList = null;
		
		String sort = "";
		if(param.get("sort") == null || "".equals((String)param.get("sort"))) sort = "update";
		else sort = (String)param.get("sort");
		
		//캐시 데이터의 키값으로 "contest|sort" 형태임
		String cacheKey = "contest|" + sort;
		ContestCache contestCache = ContestCache.getInstance();
		
		cachedList = contestCache.getCachedList(cacheKey);
		
		if(cachedList == null) { //캐시된 데이터가 없거나 만료된 경우
			contestList = contestMapper.contestSelectList(param);
			
			//목록이 있는 경우 DB에서 새로 조회한 데이터를 캐시 데이터에 추가한다.
			if(contestList != null && contestList.size() > 0) {
				contestCache.putCache(cacheKey, contestList);
			}	
		}
		//캐시된 데이터가 있는 경우
		else { 
			contestList = cachedList;
		}
		
		return contestList;
	}
	
	/**
	 * 추천 공모전 리스트 조회(3개)  
	 * 
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ContestDomain> topContestList() {
		return contestMapper.topContestSelectList();
	}
	
	/**
	 * 공모전 상세정보 조회 
	 * @param 공모전 번호 파라메터 정보 
	 * @return ContestDomain 공모전 상세 정보
	 */
	@Transactional(readOnly=true)
	public ContestDomain contestDetail(Map<String,Object> param){
		return contestMapper.contestSelectDetail(param);
	}

	/**
	 * 공모전 작화 리스트 갯수 조회 
	 * @param 페이징, 공모전 번호 파라메터 정보 
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	@Transactional(readOnly=true)
	public int timesListCount(Map<String,Object> param){
		return contestMapper.timesSelectListCount(param);
	}
	
	/**
	 * 공모전 작화 리스트 조회 
	 * @param 페이징, 공모전 번호 파라메터 정보 
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ContestDomain> timesList(Map<String,Object> param){
		return contestMapper.timesSelectList(param);
	}

	/**
	 * 공모전 작화 정보 조회 
	 * @param 작화 회차 번호 파라메터 정보 
	 * @return ContestDomain 공모전 작화 정보
	 */
	@Transactional(readOnly=true)
	public ContestDomain timesDetail(Map<String,Object> param){
		return contestMapper.timesSelectDetail(param);
	}
	
	/**
	 * 공모전 작화 회차 리스트 조회 
	 * @param 작품 번호 파라메터 정보
	 * @return List<ContestDomain> 공모전 작화 회차 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ContestDomain> tiemsnoList(Map<String,Object> param){
		return contestMapper.tiemsnoSelectList(param);
	}
	
	/**
	 * 공모전 작화 이미지 리스트 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return List<ContestImageDomain> 공모전 작화 이미지 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ContestImageDomain> contestImageList(Map<String,Object> param){
		return contestMapper.contestImageSelectList(param);
	}
	
	/**
	 * 공모전 작화의 스티커 갯수를 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return int 스티커 갯수
	 */
	@Transactional(readOnly=true)
	public int stickerCount(int timesseq){
		return contestMapper.stickerSelectCount(timesseq);
	}
	
	/**
	 * 공모전 작화에 사용자 스티커 갯수를 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return int 스티커 갯수
	 */
	@Transactional(readOnly=true)
	public int myStickerCount(StickerDomain sticker){		
		return contestMapper.myStickerSelectCount(sticker);
	}
	
	/**
	 * 공모전 작화에 스티커 제공 처리 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return int 스티커 갯수
	 */
	@Transactional(readOnly=false)
	public void stickerRegist(StickerDomain stickerDomain) {		
		contestMapper.stickerInsert(stickerDomain);		
		contestMapper.stickerUpdate(stickerDomain);		
	}
	
	/**
	 * 공모전 작화에 사용자가 스티커 입력 횟수 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return int 스티커 갯수
	 */
	@Transactional(readOnly=true)
	public int stickerCheck(StickerDomain stickerDomain){
		return contestMapper.stickerCheck(stickerDomain);
	}
	
	/**
	 * 모바일 og태그 셋팅을 위한 공모전 기본정보 조회 
	 * @param 공모전 번호 파라메터 정보 
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	@Transactional(readOnly=true)
	public ContestDomain simpleContestDetail(Map<String,Object> param){
		return contestMapper.simpleContestSelectDetail(param);
	}

	/**
	 * 모바일 og태그 셋팅을 위한 공모전 작화 기본정보 조회 
	 * @param 공모전 번호 파라메터 정보 
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	@Transactional(readOnly=true)
	public ContestDomain simpleTimesDetail(Map<String,Object> param){
		return contestMapper.simpleTimesSelectDetail(param);
	}
	
}