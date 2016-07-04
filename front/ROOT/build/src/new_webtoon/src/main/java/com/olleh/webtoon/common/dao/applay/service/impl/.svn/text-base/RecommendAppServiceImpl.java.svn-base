package com.olleh.webtoon.common.dao.applay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.applay.domain.HotAppDomain;
import com.olleh.webtoon.common.dao.applay.domain.LinkSmsDomain;
import com.olleh.webtoon.common.dao.applay.domain.RecommendAppDomain;
import com.olleh.webtoon.common.dao.applay.persistence.RecommendAppMapper;
import com.olleh.webtoon.common.dao.applay.service.iface.RecommendAppService;

@Service("recommendAppService")
@Repository
public class RecommendAppServiceImpl implements RecommendAppService {

	@Autowired
	private RecommendAppMapper recommendAppMapper;
	
	/**
	 * 추천앱 리스트 총 갯수 조회 
	 * @return
	 */
	@Transactional(readOnly=true)
	public int recommendAppListCnt(RecommendAppDomain recommendAppDomain)
	{
		return recommendAppMapper.recommendAppSelectListCnt(recommendAppDomain);
	}
	
	/**
	 * 추천앱 리스트 조회 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<RecommendAppDomain> recommendAppList(RecommendAppDomain recommendAppDomain) {
		// TODO Auto-generated method stub
		return recommendAppMapper.recommendAppSelectList(recommendAppDomain);
	}
	
	/**
	 * 추천앱 상세 조회 
	 * @param appseq
	 * @return
	 */
	@Transactional(readOnly=true)
	public RecommendAppDomain recommendAppDetail(RecommendAppDomain recommendAppDomain)
	{
		return recommendAppMapper.recommendAppSelectDetail(recommendAppDomain);
	}
	
	/**
	 * RNB 추천앱 조회 
	 * @return
	 */
	@Transactional(readOnly=true)
	public RecommendAppDomain rnbRecommendApp()
	{
		return recommendAppMapper.rnbRecommendAppSelect();
	}
	
	/**
	 * RNB 인기앱 조회 
	 * @return
	 */
	@Transactional(readOnly=true)
	public RecommendAppDomain rnbPopularApp()
	{
		return recommendAppMapper.rnbPopularAppSelect();
	}
	
	/**
	 * HotApp 정보
	 * @return
	 */
	@Transactional(readOnly=true)
	public HotAppDomain mainHotappDetail()
	{
		return recommendAppMapper.mainHotappSelectDetail();
	}
	
	/**
	 * HotApp 아이템 리스트 조회 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<HotAppDomain> mainHotappItemList() {
		List<HotAppDomain> hotapplist = recommendAppMapper.mainHotappItemSelectList();
		List<HotAppDomain> hotapprandomlist = recommendAppMapper.mainHotappItemRandomSelectList();
		
		hotapplist.addAll(hotapprandomlist);
		
		return hotapplist;
	}
	
	/**
	 * SMS 보낸이력 정보
	 * @param LinkSmsDomain 보내는 사람 정보 및 추천앱 순번
	 * @return int 등록 갯수
	 */
	@Transactional(readOnly=false)
	public int linkSmsRegist(LinkSmsDomain linkSmsDomain)
	{
		return recommendAppMapper.linkSmsInsert(linkSmsDomain);
	}
	
	/**
	 * 이번달 SMS 보낸 총 갯수 조회 
	 * @param LinkSmsDomain 보내는 사람 정보
	 * @return int 
	 */
	@Transactional(readOnly=true)
	public int linkSmsCnt(LinkSmsDomain linkSmsDomain)
	{
		return recommendAppMapper.linkSmsSelectCnt(linkSmsDomain);
	}
}