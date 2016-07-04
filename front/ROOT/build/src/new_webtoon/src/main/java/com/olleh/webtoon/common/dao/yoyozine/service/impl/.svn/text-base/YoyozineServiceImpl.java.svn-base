/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자 > 요요진
 * FILE NAME      : YoyozineServiceImpl.java
 * DESCRIPTION    : 요요진 정보 관련 Service 구현 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        					      2014-04-21      init
 *****************************************************************************/
package com.olleh.webtoon.common.dao.yoyozine.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineDomain;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineImageDomain;
import com.olleh.webtoon.common.dao.yoyozine.persistence.YoyozineMapper;
import com.olleh.webtoon.common.dao.yoyozine.service.iface.YoyozineService;

@Service( "yoyozineService" )
@Repository
public class YoyozineServiceImpl implements YoyozineService
{
	protected static Log	logger	= LogFactory.getLog( YoyozineServiceImpl.class );

	@Autowired
	private YoyozineMapper	yoyozineMapper;

	/**
	 * Yoyozine 리스트 총갯수 조회
	 */
	@Transactional(readOnly=true)
	public int yoyozineListCnt(YoyozineDomain yoyozineDomain)
	{
		return yoyozineMapper.yoyozineSelectListCnt(yoyozineDomain);
	}
	
	/**
	 * Yoyozine 리스트 조회
	 * @param yoyozineDomain 페이지 정보
	 * @return
	 */
	@Transactional(readOnly=true)
	public  List<YoyozineDomain> yoyozineList(YoyozineDomain yoyozineDomain)
	{
		return yoyozineMapper.yoyozineSelectList(yoyozineDomain);
	}
	
	/**
	 * Yoyozine 상세 조회
	 * @return
	 */
	@Transactional(readOnly=true)
	public YoyozineDomain yoyozineDetail(YoyozineDomain yoyozineDomain)
	{
		//조회수 업데이트
		//yoyozineMapper.readCntUpdate(yoyozineDomain.getYoyozineseq());
		
		return yoyozineMapper.yoyozineSelectDetail(yoyozineDomain);
	}
	
	/**
	 * RefYoyozine 목록 조회
	 * @param YoyozineDomain
	 * @return List<ToonDomain>
	 */
	@Transactional(readOnly=true)
	public List<ToonDomain> refwebtoonList(YoyozineDomain yoyozineDomain)
	{
		return yoyozineMapper.refwebtoonSelectList(yoyozineDomain);
	}
	
	/**
	 * 이전 Yoyozine Seq조회
	 * @param YoyozineDomain
	 * @return 이전 yoyozine Seq번호
	 */
	@Transactional(readOnly=true)
	public int getPrevYoyozine(int yoyozineseq)
	{
		return yoyozineMapper.getPrevYoyozine(yoyozineseq);
	}
	
	/**
	 * 다음 Yoyozine Seq조회
	 * @param YoyozineDomain
	 * @return 다음 Yoyozine Seq번호
	 */

	@Transactional(readOnly=true)
	public int getNextYoyozine(int yoyozineseq)
	{
		return yoyozineMapper.getNextYoyozine(yoyozineseq);
	}
	
	
	/**
	 *메인 요요진관련 카테고리별 게시글
	 * @return List<YoyozineDomain>
	 */
	@Transactional(readOnly=true)
	public List<YoyozineDomain> moYoyozineMainList()
	{
		return yoyozineMapper.moYoyozineMainList(); //서비스 Impl 단에 모바일 메인 게시글 관련 메소드 추가
	}
	
	/**
	 *  Image 조회
	 * @param int yoyozineseq : 요요진 순번
	 * @return List<YoyozineImageDomain> : 본문이미지
	 */
	@Transactional(readOnly=true)
	public  List<YoyozineImageDomain> yoyozineImageList(int yoyozineseq){
		return yoyozineMapper.yoyozineImageList(yoyozineseq);
	}
	
	@Transactional(readOnly=true)
	public YoyozineDomain simpleYoyozineDetail(YoyozineDomain yoyozineDomain){
		return yoyozineMapper.simpleYoyozineSelectDetail(yoyozineDomain);
	}
	
	/**
	 * yoyozine 리스트 상단 배너 조회
	  * @param YoyozineDomain
	 * @return List<YoyozineDomain>
	 *//*
	@Transactional(readOnly=true)
	public List<YoyozineDomain> bannerList(YoyozineDomain yoyozineDomain){
		return yoyozineMapper.yoyozineBannerList( yoyozineDomain );
		
	}*/
}