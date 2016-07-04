package com.olleh.webtoon.common.dao.applay.service.iface;

import java.util.List;

import com.olleh.webtoon.common.dao.applay.domain.EventDomain;
import com.olleh.webtoon.common.dao.applay.domain.EventImageDomain;
import com.olleh.webtoon.common.dao.applay.domain.SnsDomain;

public interface EventService {

	/**
	 * EVENT 리스트 총갯수 조회 
	 * @return
	 */
	public int eventListCnt(EventDomain eventDomain);
	
	/**
	 * EVENT 리스트 조회 
	 * @return
	 */
	public List<EventDomain> eventList(EventDomain eventDomain);
	
	/**
	 * EVENT 상세 조회 
	 * @param eventseq
	 * @return
	 */
	public EventDomain eventDetail(EventDomain eventDomain);
	
	/**
	 * EVENT 이미지 리스트 조회  
	 * @param eventseq
	 * @return
	 */
	public List<EventImageDomain> eventImageList(int eventseq);
	
	/**
	 * RNB EVENT 조회 
	 * @return
	 */
	public EventDomain rnbEvent();
	
	//temp
	/**
	 * SNS연동정보 등록
	 * @param SnsDomain 
	 */
	public void insertSns(SnsDomain snsDomain);
}
