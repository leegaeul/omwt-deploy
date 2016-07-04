package com.olleh.webtoon.common.dao.applay.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.applay.domain.EventDomain;
import com.olleh.webtoon.common.dao.applay.domain.EventImageDomain;
import com.olleh.webtoon.common.dao.applay.domain.SnsDomain;

public interface EventMapper {

	/**
	 * EVENT 리스트 총 갯수 조회 
	 * @return
	 */
	public int eventSelectListCnt(EventDomain eventDomain);
	
	/**
	 * EVENT 리스트 조회 
	 * @return
	 */
	public List<EventDomain> eventSelectList(EventDomain eventDomain);
	
	/**
	 * EVENT 상세 조회 
	 * @return
	 */
	public EventDomain eventSelectDetail(EventDomain eventDomain);
	
	/**
	 * EVENT 이미지 리스트 조회  
	 * @param eventseq
	 * @return
	 */
	public List<EventImageDomain> eventSelectImageList(int eventseq);
	
	/**
	 * EVENT 조회수를 업데이트
	 * 
	 * @param EventDomain eventDomain : 업데이트할 이벤트
	 * @return void
	 */
	public void readCntUpdate(int eventseq);
	
	/**
	 * RNB EVENT 조회 
	 * @return
	 */
	public EventDomain rnbEventSelect();
	
	//temp
	/**
	 * SNS연동정보 등록
	 * @param SnsDomain 
	 */
	public void insertSns(SnsDomain snsDomain);
}