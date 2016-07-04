package com.olleh.webtoon.common.dao.novel.service.iface;

import java.util.ArrayList;

import com.olleh.webtoon.common.dao.premium.domain.PremiumDomain;

public interface NovelService 
{	
	/**
	 * 웹소설 작품 목록 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹소설 작품 목록 정보
	 */
	public ArrayList<PremiumDomain> novelList(PremiumDomain premiumDomain);
	
	/**
	 * 모든 웹소설 작품 목록 조회 
	 * @param 페이징, 웹툰 번호 파라메터 정보 
	 * @return List<ToonDomain> 웹소설 작품 목록 정보
	 */
	public ArrayList<PremiumDomain> totalNovelList(PremiumDomain premiumDomain);
}
