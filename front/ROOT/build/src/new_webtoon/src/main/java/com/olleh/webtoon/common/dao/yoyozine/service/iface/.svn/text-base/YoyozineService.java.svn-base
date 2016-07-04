/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자 > 요요진
 * FILE NAME      : YoyozineServiceIface.java
 * DESCRIPTION    : 요요진 정보 관련 Service interface class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        						     2014-04-21     init
 *****************************************************************************/
package com.olleh.webtoon.common.dao.yoyozine.service.iface;

import java.util.List;

import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineDomain;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineImageDomain;

public interface YoyozineService {
	
	/**
	 * Yoyozine 리스트 총갯수 조회
	 * @return 
	 */
	
	public int yoyozineListCnt(YoyozineDomain yoyozineDomain);
	
	/**
	 * Yoyozine 목록 조회
	 * @param yoyozineDomain yoyozineDomain
	 * @return List<YoyozineDomain>
	 */
	public List<YoyozineDomain> yoyozineList(YoyozineDomain yoyozineDomain);
	
	/**
	 * RefYoyozine 목록 조회
	 * @param YoyozineDomain
	 * @return List<ToonDomain>
	 */
	public List<ToonDomain> refwebtoonList(YoyozineDomain yoyozineDomain);
	
	
	/**
	 * Yoyozine 상세정보 조회
	 * @return YoyozineDomain
	 */
	public YoyozineDomain yoyozineDetail(YoyozineDomain yoyozineDomain);
	
	/**
	 * 이전 Yoyozine Seq조회
	 * @param YoyozineDomain
	 * @return 이전 Yoyozine Seq
	 */
	public int getPrevYoyozine(int yoyozineseq);
	
	/**
	 * 다음 Yoyozine Seq조회
	 * @param YoyozineDomain
	 * @return 다음 Yoyozine Seq
	 */
	public int getNextYoyozine(int yoyozineseq);
	
	/**
	 * 메인 요요진 각카테고리별 게시글
	 * @return List<YoyozineDomain>
	 */
	public List<YoyozineDomain> moYoyozineMainList(); //서비스단에 모바일 메인 게시판 관련 메소드 추가
	
	/**
	 *  Image 조회
	 */
	public List<YoyozineImageDomain> yoyozineImageList(int yoyozineseq);
	
	public YoyozineDomain simpleYoyozineDetail(YoyozineDomain yoyozineDomain);
	
	
/*	*//**
	 * yoyozine list 상단 banner 조회
	 *//*
	public List<YoyozineDomain> bannerList(YoyozineDomain yoyozineDomain);*/
}