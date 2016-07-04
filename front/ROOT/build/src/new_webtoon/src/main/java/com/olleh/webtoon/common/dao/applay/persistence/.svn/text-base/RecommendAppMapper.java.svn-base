package com.olleh.webtoon.common.dao.applay.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.applay.domain.HotAppDomain;
import com.olleh.webtoon.common.dao.applay.domain.LinkSmsDomain;
import com.olleh.webtoon.common.dao.applay.domain.RecommendAppDomain;

public interface RecommendAppMapper {

	/**
	 * 추천앱 리스트 총 갯수 조회 
	 * @return
	 */
	public int recommendAppSelectListCnt(RecommendAppDomain recommendAppDomain);
	
	
	/**
	 * 추천앱 리스트 조회 
	 * @return
	 */
	public List<RecommendAppDomain> recommendAppSelectList(RecommendAppDomain recommendAppDomain);
	
	/**
	 * 추천앱 상세 조회 
	 * @return
	 */
	public RecommendAppDomain recommendAppSelectDetail(RecommendAppDomain recommendAppDomain);
	
	/**
	 * RNB 추천앱 조회 
	 * @return
	 */
	public RecommendAppDomain rnbRecommendAppSelect();
	
	/**
	 * RNB 인기앱 조회 
	 * @return
	 */
	public RecommendAppDomain rnbPopularAppSelect();
	
	/**
	 * HotApp 정보
	 * @return
	 */
	public HotAppDomain mainHotappSelectDetail();
	
	/**
	 * HotApp 아이템 리스트 조회 
	 * @return
	 */
	public List<HotAppDomain> mainHotappItemSelectList();
	
	/**
	 * HotApp 아이템 랜덤 리스트 조회 
	 * @return
	 */
	public List<HotAppDomain> mainHotappItemRandomSelectList();
	
	/**
	 * SMS 보낸이력 정보
	 * @param LinkSmsDomain 보내는 사람 정보 및 추천앱 순번
	 * @return int 등록 갯수
	 */
	public int linkSmsInsert(LinkSmsDomain linkSmsDomain);

	/**
	 * 이번달 SMS 보낸 총 갯수 조회 
	 * @param LinkSmsDomain 보내는 사람 정보
	 * @return int 
	 */
	public int linkSmsSelectCnt(LinkSmsDomain linkSmsDomain);
}
