/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자(모바일)
 * FILE NAME      : TodayController.java
 * DESCRIPTION    : 웹툰 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-18      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.toon.domain.TodayToonDomain;
import com.olleh.webtoon.common.dao.toon.service.iface.ToonService;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.StringUtil;
import com.olleh.webtoon.mobile.cache.TodayCache;

@Controller("MobileTodayController")
public class TodayController {
	
	@Autowired
	ToonService toonService;
	
	protected static Log logger = LogFactory.getLog(TodayController.class);
	private final String[] dayOfWeek = new String[]{"monday", "tuesday", "wendesday", "thursday", "friday", "saturday", "sunday"};

	@RequestMapping(value = "/m/service/ktwifi.kt")
	public ModelAndView ktwifi(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();		
		
		modelAndView.setViewName("mobile/service/ktwifi");
			
		//ModelAndView객체를 캐시에서 가져오고 없으면 구성해서 넣는다.
		TodayCache todayCache = TodayCache.getInstance();
		ModelAndView cachedModelAndView = todayCache.getCachedModelAndView();
		
		if(cachedModelAndView != null) {
			modelAndView = cachedModelAndView;
		} else {
			
			//오늘의 웹툰 오늘 날짜 기준 요일(월~일) 목록 가져오기
			Map<String, Object> week = toonService.mainTodayWebtoonDateList(DateUtil.getDayOfWeekList());
							
			//요일별 메인 오늘의 웹툰 가져오기(Cache 처리)
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> webtoonTypeList = toonService.mainTodayWebtoonTypeList(week);
			List<TodayToonDomain> webtoonItemList = toonService.mainTodayWebtoonItemList(week);
			
			int typeIdx = 0;
			int itemIdx = 0;
			
			for(int i = 0; i < week.size(); i++)
			{
				List<Map<String,Object>> weekWebtoonTypeList = new ArrayList<Map<String,Object>>(); 
				List<TodayToonDomain> weekWebtoonItemList = new ArrayList<TodayToonDomain>();
				
				//요일별 템플릿 정보 가져오기
				for(int j = typeIdx; j < webtoonTypeList.size(); j++)
				{
					Map<String, Object> type = (Map<String, Object>)webtoonTypeList.get(j);
					
					if(StringUtil.defaultInt(type.get("weekorder").toString()) == i + 1)
						weekWebtoonTypeList.add(webtoonTypeList.get(j));
					else{
						typeIdx = j;
						break;
					}
				}
				
				//요일별 웹툰 정보 가져오기
				for(int z = itemIdx; z < webtoonItemList.size(); z++)
				{
					TodayToonDomain item = (TodayToonDomain)webtoonItemList.get(z);
					
					if(item.getWeekorder() == i + 1)
						weekWebtoonItemList.add(webtoonItemList.get(z));
					else{
						itemIdx = z;
						break;
					}
				}
				
				modelAndView.addObject(dayOfWeek[i] + "WebtoonTypeList", weekWebtoonTypeList);
				modelAndView.addObject(dayOfWeek[i] + "WebtoonItemList", weekWebtoonItemList);
			}
			
			//정보를 모두 취합한 후 캐시에 넣는다.
			todayCache.putCache(modelAndView);
		}
		
		return modelAndView;		
	}
}