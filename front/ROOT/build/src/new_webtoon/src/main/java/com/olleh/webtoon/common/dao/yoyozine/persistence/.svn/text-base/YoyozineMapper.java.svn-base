/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자 > 요요진
 * FILE NAME      : YoyozineMapper.java
 * DESCRIPTION    : 요요진 테이블 DB 연동 관련 Mapper class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        					      2014-04-21      init
 *****************************************************************************/
package com.olleh.webtoon.common.dao.yoyozine.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineDomain;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineImageDomain;

public interface YoyozineMapper {
	
	/**
	 * Yoyozine 리스트 총갯수 조회
	 * @return
	 */
	public int yoyozineSelectListCnt(YoyozineDomain yoyozineDomain);
	
	/**
	 * Yoyozine 리스트 조회
	 * @param yoyozineDomain
	 * @return
	 */
	
	public List<YoyozineDomain> yoyozineSelectList(YoyozineDomain yoyoDomain);
	
	/**
	 * Yoyozine 상세 조회
	 * @param yoyozineDomain
	 * @return
	 */
	public YoyozineDomain yoyozineSelectDetail(YoyozineDomain yoyozineDomain);
	
	/**
	 * RefYoyozine 목록 조회
	 * @param YoyozineDomain
	 * @return List<ToonDomain>
	 */
	public List<ToonDomain> refwebtoonSelectList(YoyozineDomain yoyozineDomain);
	
	/**
	 * Yoyozine 조회수를 업데이트
	 * 
	 * @param YoyozineDomain : 업데이트할 요요진
	 * @return void
	 */
	public void readCntUpdate(int yoyozineseq);
	
	/**
	 * 이전 Yoyozine Seq 조회
	 * @param YoyozineDomain
	 * @return 이전 Yoyozine Seq번호
	 */
	public int getPrevYoyozine(int yoyozineseq);
	
	/**
	 * 다음 Yoyozine Seq조회
	 * @param YoyozineDomain
	 * @return 다음 Yoyozine Seq번호
	 */
	public int getNextYoyozine(int yoyozineseq);
	
	/**
	 * yoyozine(mobile) main list조회
	 */
	public List<YoyozineDomain> moYoyozineMainList();//mapper단에 메소드 추가
	
	/**
	 * Image조회
	 * @param int yoyozineseq : 요요진 순번
	 * @return List<YoyozineImageDomain> : 본문이미지
	 */
	public  List<YoyozineImageDomain> yoyozineImageList(int yoyozineseq);
	
	public YoyozineDomain simpleYoyozineSelectDetail(YoyozineDomain yoyozineDomain);

	
	/**
	 * yoyozine 리스트 상단 banner조회
	 * @param YoyozineDomain
	 * @return List<YoyozineDomain>
	*//*
	public List<YoyozineDomain> yoyozineBannerList(YoyozineDomain yoyozineDomain);*/
	
	
}