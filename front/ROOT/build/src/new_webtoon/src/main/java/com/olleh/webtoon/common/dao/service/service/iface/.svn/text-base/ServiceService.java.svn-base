/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ServiceController.java
 * DESCRIPTION    : B2B 처리 관련 Service interface class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2015-05-07      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.service.service.iface;

import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.service.domain.ApiResultDomain;
import com.olleh.webtoon.common.dao.service.domain.ServiceDomain;
import com.olleh.webtoon.common.dao.service.domain.ServiceTimesDomain;
import com.olleh.webtoon.common.dao.service.domain.ServiceToonImageDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;

public interface ServiceService {
	
	/**
	 * 웹툰 리스트 정보 조회 
	 * @param String year  : 연재날짜(년)
	 * @param String month : 연재날짜(월)
	 * @return List<ServiceDomain>
	 */
	public List<ServiceDomain> getToonList(String year, String month);
	
	/**
	 * 웹툰 정보 조회 
	 * @param String webtoonseq  : 웹툰 번호
	 * @return ServiceDomain
	 */
	public ServiceDomain getToonDetail(String year, String month, int webtoonseq);
	
	/**
	 * 웹툰 리스트 정보 조회 
	 * @param String year  : 연재날짜(년)
	 * @param String month : 연재날짜(월)
	 * @param String webtoonseq : 웹툰번호
	 * @return List<ServiceTimesDomain>
	 */
	public List<ServiceTimesDomain> getTimesList(String year, String month, int webtoonseq);
	
	/**
	 * 웹툰 작화 이미지 리스트 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return List<ToonImageDomain> 웹툰 작화 이미지 목록 정보
	 */
	public List<ServiceToonImageDomain> toonImageList(Map<String,Object> param);
	
	/**
	 * 웹툰 작화 정보 조회 
	 * @param 작화 회차 번호 파라메터 정보 
	 * @return ToonDomain 웹툰 작화 정보
	 */
	public ToonDomain timesDetail(Map<String,Object> param);
	
	/**
	 * 컨텐츠박스 웹툰 감상 권한 조회
	 * @param String webtoonseq
	 * @param String timesseq
	 * @param String token
	 * @param boolean isAppService
	 * @param String isDev
	 * @return ApiResultDomain
	 */
	public ApiResultDomain getAuthority(String webtoonseq, String timesseq, String token, boolean isAppService, String isDev);

	/**
	 * 유료/무료 회차 여부 조회  
	 * @param String timesseq 
	 * @return String freeyn(Y/N)
	 */
	public String getTimesFreeyn(String timesseq);
	
}