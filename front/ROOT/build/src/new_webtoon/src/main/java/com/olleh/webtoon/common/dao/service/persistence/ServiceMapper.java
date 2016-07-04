/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ServiceController.java
 * DESCRIPTION    : B2B DB 테이블 연동관련  Mapper class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2015-05-07      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.service.persistence;

import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.service.domain.ServiceDomain;
import com.olleh.webtoon.common.dao.service.domain.ServiceTimesDomain;
import com.olleh.webtoon.common.dao.service.domain.ServiceToonImageDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;

public interface ServiceMapper {
	
	/**
	 * 웹툰 리스트 정보 조회 
	 * @param ServiceDomain serviceDomain : 서비스 구분값, 연재정보(년/월)
	 * @return List<ServiceDomain>
	 */
	public List<ServiceDomain> selectToonList(ServiceDomain serviceDomain);
	
	/**
	 * 웹툰 정보 조회 
	 * @param ServiceDomain serviceDomain : 웹툰번호
	 * @return ServiceDomain
	 */
	public ServiceDomain selectToonDetail(ServiceDomain serviceDomain);
	
	/**
	 * 웹툰 리스트 정보 조회 
	 * @param ServiceDomain serviceDomain : 서비스 구분값, 연재정보(년/월)
	 * @return List<ServiceDomain>
	 */
	public List<ServiceTimesDomain> selectTimesList(ServiceTimesDomain serviceTimesDomain);
	
	/**
	 * 웹툰 작화 이미지 리스트 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return List<ToonImageDomain> 웹툰 작화 이미지 목록 정보
	 */
	public List<ServiceToonImageDomain> toonImageSelectList(Map<String,Object> param);
	
	/**
	 * 웹툰 작화 정보 조회 
	 * @param 작화 회차 번호 파라메터 정보 
	 * @return ToonDomain 웹툰 작화 정보
	 */
	public ToonDomain timesSelectDetail(Map<String,Object> param);
	
	/**
	 * 유료/무료 회차 여부 조회  
	 * @param String timesseq 
	 * @return String freeyn(Y/N)
	 */
	public String selectTimesFreeyn(ServiceTimesDomain serviceTimesDomain);
}