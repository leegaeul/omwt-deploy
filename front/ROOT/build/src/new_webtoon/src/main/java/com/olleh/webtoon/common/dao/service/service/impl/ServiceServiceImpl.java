/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ServiceController.java
 * DESCRIPTION    : B2B 처리 관련 Service 구현 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2015-05-07      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.service.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.service.domain.ApiResultDomain;
import com.olleh.webtoon.common.dao.service.domain.ServiceDomain;
import com.olleh.webtoon.common.dao.service.domain.ServiceTimesDomain;
import com.olleh.webtoon.common.dao.service.domain.ServiceToonImageDomain;
import com.olleh.webtoon.common.dao.service.persistence.ServiceMapper;
import com.olleh.webtoon.common.dao.service.service.iface.ServiceService;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.util.HttpUtil;
import com.olleh.webtoon.common.util.StringUtil;
import com.olleh.webtoon.mobile.controller.ServiceController;

@Service("serviceService")
@Repository
public  class ServiceServiceImpl implements ServiceService {

	protected static Log logger = LogFactory.getLog(ServiceController.class);
	
	@Autowired
	private ServiceMapper serviceMapper;
	
	/**
	 * 웹툰 리스트 정보 조회 
	 * @param String year  : 연재날짜(년)
	 * @param String month : 연재날짜(월)
	 * @return List<ServiceDomain>
	 */
	@Transactional(readOnly=true)
	public List<ServiceDomain> getToonList(String year, String month) {
		
		if(year != null && year.length() != 4) {
			year = "0000";
		}
		
		if(month != null && month.length() == 1) {
			month = "0" + month;
		}else if(month != null && month.length() != 2) {
			month = "00";
		}
		
		ServiceDomain serviceDomain = new ServiceDomain();
		serviceDomain.setPublishdt(year + month);
		
		return serviceMapper.selectToonList(serviceDomain);
	}
	
	/**
	 * 웹툰 정보 조회 
	 * @param String webtoonseq  : 웹툰 번호
	 * @return ServiceDomain
	 */
	@Transactional(readOnly=true)
	public ServiceDomain getToonDetail(String year, String month, int webtoonseq) {
		
		if(year != null && year.length() != 4) {
			year = "0000";
		}
		
		if(month != null && month.length() == 1) {
			month = "0" + month;
		}else if(month != null && month.length() != 2) {
			month = "00";
		}
		
		ServiceDomain serviceDomain = new ServiceDomain();
		serviceDomain.setPublishdt(year + month);
		serviceDomain.setWebtoonseq(webtoonseq);
		
		return serviceMapper.selectToonDetail(serviceDomain);
	}
	
	/**
	 * 웹툰 리스트 정보 조회 
	 * @param String year  : 연재날짜(년)
	 * @param String month : 연재날짜(월)
	 * @param String webtoonseq : 웹툰번호
	 * @return List<ServiceTimesDomain>
	 */
	@Transactional(readOnly=true)
	public List<ServiceTimesDomain> getTimesList(String year, String month, int webtoonseq) {
		
		if(year != null && year.length() != 4) {
			year = "0000";
		}
		
		if(month != null && month.length() == 1) {
			month = "0" + month;
		}else if(month != null && month.length() != 2) {
			month = "00";
		}
		
		ServiceTimesDomain serviceTimesDomain = new ServiceTimesDomain();
		serviceTimesDomain.setPublishdt(year + month);
		serviceTimesDomain.setWebtoonseq(webtoonseq);
		
		return serviceMapper.selectTimesList(serviceTimesDomain);
	}
	
	/**
	 * 웹툰 작화 이미지 리스트 조회 
	 * @param 작화 회차 번호 파라메터 정보
	 * @return List<ToonImageDomain> 웹툰 작화 이미지 목록 정보
	 */
	@Transactional(readOnly=true)
	public List<ServiceToonImageDomain> toonImageList(Map<String,Object> param){
		return serviceMapper.toonImageSelectList(param);
	}
	
	/**
	 * 웹툰 작화 정보 조회 
	 * @param 작화 회차 번호 파라메터 정보 
	 * @return ToonDomain 웹툰 작화 정보
	 */
	@Transactional(readOnly=true)
	public ToonDomain timesDetail(Map<String,Object> param){
		return serviceMapper.timesSelectDetail(param);
	}
	
	
	/**
	 * 컨텐츠박스 웹툰 감상 권한 조회
	 * @param String webtoonseq
	 * @param String timesseq
	 * @param String token
	 * @param boolean isAppService
	 * @param String isDev
	 * @return ApiResultDomain
	 */
	@Transactional(readOnly=true)
	public ApiResultDomain getAuthority(String webtoonseq, String timesseq, String token, boolean isAppService, String isDev){
		
		ApiResultDomain apiResult = null;
		
		String param = null;
		String domain = "alzza.contentsbox.net";
		if("1".equals(isDev)) domain = "dev.alzza.contentsbox.net";
		
		String host = "http://"+domain+"/m/1.0/grp_webtoon/webtoon_auth.php";
		
		if(isAppService) {
			param = "?webtoonseq={webtoonseq}&timesseq={timesseq}&app_user_agent={token}";
		}else{
			param = "?webtoonseq={webtoonseq}&timesseq={timesseq}&device_sn={token}";
		}
		
		param = param.replace("{webtoonseq}", webtoonseq);
		param = param.replace("{timesseq}",   timesseq);
		param = param.replace("{token}",  token);
		
		String message = HttpUtil.sendGetUrl(host + param);
		
		logger.debug("[Service timesDetail] url = " + host + param);
		logger.debug("[Service timesDetail] message = " + message);
		
		try {
			JSONObject response = new JSONObject(message);
			
			apiResult = new ApiResultDomain();
			apiResult.setClass_status(response.getString("class_status"));
			apiResult.setDevice_sn(response.getString("device_sn"));
			apiResult.setAccount_id(response.getString("account_id"));
			apiResult.setApp_service_media(response.getString("app_service_media"));
			apiResult.setNavi_bar_type(response.getString("navi_bar_type"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return apiResult;
	}
	
	/**
	 * 유료/무료 회차 여부 조회  
	 * @param String timesseq 
	 * @return String freeyn(Y/N)
	 */
	@Transactional(readOnly=true)
	public String getTimesFreeyn(String timesseq) {
		
		ServiceTimesDomain serviceTimesDomain = new ServiceTimesDomain();
		serviceTimesDomain.setTimesseq(StringUtil.defaultInt(timesseq, 0));
		
		return serviceMapper.selectTimesFreeyn(serviceTimesDomain);
	}
}