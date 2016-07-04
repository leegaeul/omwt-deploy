package com.olleh.webtoon.common.dao.applay.service.iface;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.applay.domain.HotAppDomain;
import com.olleh.webtoon.common.dao.applay.domain.LinkSmsDomain;
import com.olleh.webtoon.common.dao.applay.domain.RecommendAppDomain;

public interface RecommendAppService {

	/**
	 * 추천앱 리스트 총 갯수 조회 
	 * @return
	 */
	public int recommendAppListCnt(RecommendAppDomain recommendAppDomain);
	
	/**
	 * 추천앱 리스트 조회 
	 * @return
	 */
	public List<RecommendAppDomain> recommendAppList(RecommendAppDomain recommendAppDomain);
	
	/**
	 * 추천앱 상세 조회 
	 * @param appseq
	 * @return
	 */
	public RecommendAppDomain recommendAppDetail(RecommendAppDomain recommendAppDomain);
	
	/**
	 * RNB 추천앱 조회 
	 * @return
	 */
	public RecommendAppDomain rnbRecommendApp();
	
	/**
	 * RNB 인기앱 조회 
	 * @return
	 */
	public RecommendAppDomain rnbPopularApp();
	
	/**
	 * HotApp 정보
	 * @return
	 */
	public HotAppDomain mainHotappDetail();
	
	/**
	 * HotApp 아이템 리스트 조회 
	 * @return
	 */
	public List<HotAppDomain> mainHotappItemList();
	
	/**
	 * SMS 보낸이력 정보
	 * @param LinkSmsDomain 보내는 사람 정보 및 추천앱 순번
	 * @return int 등록 갯수
	 */
	public int linkSmsRegist(LinkSmsDomain linkSmsDomain);
	
	/**
	 * 이번달 SMS 보낸 총 갯수 조회 
	 * @param LinkSmsDomain 보내는 사람 정보
	 * @return int 
	 */
	public int linkSmsCnt(LinkSmsDomain linkSmsDomain);
}
