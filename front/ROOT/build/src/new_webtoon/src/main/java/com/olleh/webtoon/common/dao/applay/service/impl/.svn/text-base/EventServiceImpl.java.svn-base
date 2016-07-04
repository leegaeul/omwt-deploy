package com.olleh.webtoon.common.dao.applay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.applay.domain.EventDomain;
import com.olleh.webtoon.common.dao.applay.domain.EventImageDomain;
import com.olleh.webtoon.common.dao.applay.domain.SnsDomain;
import com.olleh.webtoon.common.dao.applay.persistence.EventMapper;
import com.olleh.webtoon.common.dao.applay.service.iface.EventService;

@Service("eventService")
@Repository
public class EventServiceImpl implements EventService {

	@Autowired
	private EventMapper eventMapper;
	
	/**
	 * EVENT 리스트 총 갯수 조회 
	 * @return
	 */
	@Transactional(readOnly=true)
	public int eventListCnt(EventDomain eventDomain)
	{
		return eventMapper.eventSelectListCnt(eventDomain);
	}
	
	/**
	 * EVENT 리스트 조회 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<EventDomain> eventList(EventDomain eventDomain) {
		// TODO Auto-generated method stub
		return eventMapper.eventSelectList(eventDomain);
	}
	
	/**
	 * EVENT 상세 조회 
	 * @param eventseq
	 * @return
	 */
	@Transactional(readOnly=false)
	public EventDomain eventDetail(EventDomain eventDomain)
	{
		//조회수 업데이트
		eventMapper.readCntUpdate(eventDomain.getEventseq());
		
		return eventMapper.eventSelectDetail(eventDomain);
	}
	
	/**
	 * EVENT 이미지 리스트 조회  
	 * @param eventseq
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<EventImageDomain> eventImageList(int eventseq)
	{		
		return eventMapper.eventSelectImageList(eventseq);
	}
	
	/**
	 * RNB EVENT 조회 
	 * @return
	 */
	@Transactional(readOnly=true)
	public EventDomain rnbEvent()
	{
		return eventMapper.rnbEventSelect();
	}
	
	//temp
	/**
	 * SNS연동정보 등록
	 * @param SnsDomain 
	 */
	@Transactional(readOnly=false)
	public void insertSns(SnsDomain snsDomain) {
		eventMapper.insertSns(snsDomain);
	}
}