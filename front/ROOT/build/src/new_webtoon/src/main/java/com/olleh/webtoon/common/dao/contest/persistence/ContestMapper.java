/*****************************************************************************
 * PROJECT NAME   : 올레마켓 공모전
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ContestMapper.java
 * DESCRIPTION    : 공모전 DB 테이블 연동관련  Mapper class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-12-18      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.contest.persistence;

import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.contest.domain.ContestDomain;
import com.olleh.webtoon.common.dao.contest.domain.ContestImageDomain;
import com.olleh.webtoon.common.dao.toon.domain.StickerDomain;

public interface ContestMapper {
	
	/**
	 * 공모전 리스트 조회 
	 * @param 검색, 정렬 파라메터 정보
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	public List<ContestDomain> contestSelectList(Map<String,Object> param);
	
	/**
	 * 추천 공모전 리스트 조회(3개) 
	 * 
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	public List<ContestDomain> topContestSelectList();
	
	/**
	 * 공모전 상세정보 조회 
	 * @param 공모전 번호 파라메터 정보 
	 * @return ContestDomain 공모전 상세 정보
	 */
	public ContestDomain contestSelectDetail(Map<String,Object> param);
	
	/**
	 * 공모전 작화 리스트 갯수 조회 
	 * @param 페이징, 공모전 번호 파라메터 정보 
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	public int timesSelectListCount(Map<String,Object> param);
	
	/**
	 * 공모전 작화 리스트 조회 
	 * @param 페이징, 공모전 번호 파라메터 정보 
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	public List<ContestDomain> timesSelectList(Map<String,Object> param);
	
	/**
	 * 공모전 작화 정보 조회 
	 * @param 작화 회차 번호 파라메터 정보 
	 * @return ContestDomain 공모전 작화 정보
	 */
	public ContestDomain timesSelectDetail(Map<String,Object> param);
	
	/**
	 * 공모전 작화 회차 리스트 조회 
	 * @param 작품 번호 파라메터 정보
	 * @return List<ContestDomain> 공모전 작화 회차 목록 정보
	 */
	public List<ContestDomain> tiemsnoSelectList(Map<String,Object> param);
	
	/**
	 * 공모전 작화 이미지 리스트 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return List<ContestImageDomain> 공모전 작화 이미지 목록 정보
	 */
	public List<ContestImageDomain> contestImageSelectList(Map<String,Object> param);
	
	/**
	 * 공모전 작화의 스티커 갯수를 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return int 스티커 갯수
	 */
	public int stickerSelectCount(int timesseq);
	
	/**
	 * 공모전 작화에 사용자 스티커 갯수를 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return int 스티커 갯수
	 */
	public int myStickerSelectCount(StickerDomain sticker);
	
	/**
	 * 공모전 작화에 스티커 정보 입력 처리(스티커 테이블) 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return int 스티커 갯수
	 */
	public void stickerInsert(StickerDomain stickerDomain);
	
	/**
	 * 공모전 작화에 스티커 정보 갱신 처리(작화 테이블)
	 * @param 작화 회차 번호 파라메터 정보
	 * @return int 스티커 갯수
	 */
	public void stickerUpdate(StickerDomain stickerDomain);
	
	/**
	 * 공모전 작화에 사용자가 스티커 입력 횟수 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return int 스티커 갯수
	 */
	public int stickerCheck(StickerDomain stickerDomain);

	/**
	 * 모바일 og태그 셋팅을 위한 공모전 기본정보 조회 
	 * @param 공모전 번호 파라메터 정보 
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	public ContestDomain simpleContestSelectDetail(Map<String,Object> param);
	
	/**
	 * 모바일 og태그 셋팅을 위한 공모전 작화 기본정보 조회 
	 * @param 공모전 번호 파라메터 정보 
	 * @return List<ContestDomain> 공모전 목록 정보
	 */
	public ContestDomain simpleTimesSelectDetail(Map<String,Object> param);
}                                         