package com.olleh.webtoon.common.dao.novel.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.novel.persistence.NovelMapper;
import com.olleh.webtoon.common.dao.novel.service.iface.NovelService;
import com.olleh.webtoon.common.dao.premium.domain.PremiumDomain;

@Service("novelService")
@Repository
public  class NovelServiceImpl implements NovelService
{
	@Autowired
	private NovelMapper novelMapper;

	/**
	 * 웹소설 작품 목록 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹소설 작품 목록 정보
	 */
	@Transactional(readOnly=true)
	public ArrayList<PremiumDomain> novelList(PremiumDomain premiumDomain){
		return novelMapper.selectNovelList(premiumDomain);
	}
	
	/**
	 * 모든 웹소설 작품 목록 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹소설 작품 목록 정보
	 */
	@Transactional(readOnly=true)
	public ArrayList<PremiumDomain> totalNovelList(PremiumDomain premiumDomain){
		return novelMapper.selectTotalNovelList(premiumDomain);
	}
}
