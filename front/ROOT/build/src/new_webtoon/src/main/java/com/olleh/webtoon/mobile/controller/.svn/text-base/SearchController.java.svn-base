/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자(모바일)
 * FILE NAME      : SearchController.java
 * DESCRIPTION    : 웹툰 검색 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-05-13      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.shop.domain.ShopDomain;
import com.olleh.webtoon.common.dao.shop.service.iface.ShopService;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.service.iface.ToonService;
import com.olleh.webtoon.common.util.StringUtil;

@Controller("MobileSearchController")
public class SearchController {
			
	protected static Log logger = LogFactory.getLog(SearchController.class);
	
	@Autowired
	ToonService toonService;
	
	@Autowired
	ShopService shopService;
		
	/**
	 * 검색 결과 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/search.kt")
	public ModelAndView search(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/search");
		
		return modelAndView;
		
	}
	
	/**
	 * 검색 결과 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/searchJsonList.kt")
	public ModelAndView searchJsonList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		String query = StringUtil.cleanXSS(StringUtil.defaultStr(((String)param.get("query")).toUpperCase(), ""));
		
		//작품 전체 목록 초기화
		List<ToonDomain> toonList = toonService.toonList(param);		
	
		//웹툰 검색 결과 목록
		List<ToonDomain> resultToonList = new ArrayList<ToonDomain>();
		
		//웹소설 검색 결과 목록
		List<ToonDomain> resultNovelList = new ArrayList<ToonDomain>();
		
		// 작품명, 회차명, 작가명 검색 결과 목록
		if(!query.equals("")){
			for(ToonDomain toon : toonList){
				
				String tempWebtoonnm  = toon.getWebtoonnm() != null ? toon.getWebtoonnm().toUpperCase().replaceAll(" ", "") : toon.getWebtoonnm();
				String tempRefkeyword = toon.getRefkeyword() != null ? toon.getRefkeyword().toUpperCase().replaceAll(" ", "") : toon.getRefkeyword();
				String tempAuthornm1  = toon.getAuthornm1() != null ? toon.getAuthornm1().toUpperCase().replaceAll(" ", "") : toon.getAuthornm1();
				String tempAuthornm2  = toon.getAuthornm2() != null ? toon.getAuthornm2().toUpperCase().replaceAll(" ", "") : toon.getAuthornm2();
				String tempAuthornm3  = toon.getAuthornm3() != null ? toon.getAuthornm3().toUpperCase().replaceAll(" ", "") : toon.getAuthornm3();
				
				///모바일전시, toon 전시 또는 premium 전시 또는 웹소설
				if("Y".equals(toon.getMdisplayyn()) && ("Y".equals(toon.getToonyn()) || "Y".equals(toon.getPremiumyn()) || "novel".equals(toon.getToonfg()))){
					if((toon.getWebtoonnm() != null && (tempWebtoonnm).indexOf(query.replaceAll(" ", "")) > -1) ||
							(toon.getRefkeyword() != null && (tempRefkeyword).indexOf(query.replaceAll(" ", "")) > -1) ||
							//(toon.getTimestitle() != null && (toon.getTimestitle().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm1() != null && (tempAuthornm1).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm2() != null && (tempAuthornm2).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm3() != null && (tempAuthornm3).indexOf(query.replaceAll(" ", "")) > -1)){
						
						if(!"novel".equals(toon.getToonfg())){
							resultToonList.add(toon);
						}else{
							resultNovelList.add(toon);
						}	
					}
						
				}
			}
		}
		
//		int fromIndex = 0;
//		int toIndex = 5;
//		if(resultToonList.size() < toIndex) toIndex = resultToonList.size();
		
		//웹툰
//		modelAndView.addObject("resultToonList", resultToonList.subList(fromIndex, toIndex));
		modelAndView.addObject("resultToonList", resultToonList);
		modelAndView.addObject("resultToonListCnt", resultToonList.size());
		
		//웹소설
		modelAndView.addObject("resultNovelList", resultNovelList);
		modelAndView.addObject("resultNovelListCnt", resultNovelList.size());
		
		// 아이템 검색		
		ShopDomain shopDomain = new ShopDomain();
		shopDomain.setStartRowNo(0);
		shopDomain.setPageSize(9999999);
		
		if(query != null && !"".equals(query)){ 
			shopDomain.setQuery("%"+query+"%");
		}
		
		modelAndView.addObject("resultItemList", shopService.searchList(shopDomain));
		modelAndView.addObject("resultItemListCnt", shopService.searchListCnt(shopDomain));
		
		return modelAndView;
		
	}
	
	

	
	/**
	 * 검색 결과 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/searchWebtoonList.kt")
	public ModelAndView searchWebtoonList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/searchWebtoonList");
		
		return modelAndView;
		
	}
	
	/**
	 * 검색 결과 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/searchWebtoonJsonList.kt")
	public ModelAndView searchWebtoonJsonList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		String startRowNoParam = (String)param.get("startRowNo");		
		int startRowNo = 1;
		startRowNo =  StringUtil.defaultInt(startRowNoParam,1);	
		
		//목록 갯수
		String pageListCountParam = (String)param.get("pageListCount");		
		int pageListCount = 10;
		pageListCount =  StringUtil.defaultInt(pageListCountParam,10);		
		
		String query = StringUtil.cleanXSS(StringUtil.defaultStr((String)param.get("query"), ""));
		
		//작품 전체 목록 초기화
		List<ToonDomain> toonList = toonService.toonList(param);		
	
		//작품 검색 결과 목록
		List<ToonDomain> resultToonList = new ArrayList<ToonDomain>();		
	
		// 작품명, 회차명, 작가명 검색 결과 목록
		if(!query.equals("")){
			for(ToonDomain toon : toonList){
				
				///PC전시, toon 전시 또는 premium 전시
				if("Y".equals(toon.getMdisplayyn()) && ("Y".equals(toon.getToonyn()) || "Y".equals(toon.getPremiumyn()))){
					if((toon.getWebtoonnm() != null && (toon.getWebtoonnm().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1) || 
							//(toon.getTimestitle() != null && (toon.getTimestitle().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm1() != null && (toon.getAuthornm1().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm2() != null && (toon.getAuthornm2().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm3() != null && (toon.getAuthornm3().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1)) 
						resultToonList.add(toon);
				}
			}
		}
		
		int toIndex = 0;
		toIndex = startRowNo + pageListCount;
		if(toIndex > resultToonList.size()) toIndex = resultToonList.size();
		
		int fromIndex = startRowNo;
		
		modelAndView.addObject("resultToonList", resultToonList.subList(fromIndex, toIndex));
		
		modelAndView.addObject("resultToonListCnt",resultToonList.size());
		
		// 키워드와 연관있는 올레마켓 서비스
		
		return modelAndView;
		
	}
	
	

	
	/**
	 * 검색 결과 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/searchItemList.kt")
	public ModelAndView searchItemList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mobile/searchItemList");
		
		return modelAndView;
		
	}
	
	/**
	 * 검색 결과 더보기 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/searchItemJsonList.kt")
	public ModelAndView searchItemJsonList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		String query = StringUtil.cleanXSS(StringUtil.defaultStr((String)param.get("query"), ""));
		
		String startRowNoParam = (String)param.get("startRowNo");		
		int startRowNo = 0;
		startRowNo =  StringUtil.defaultInt(startRowNoParam,0);		
		
		String pageSizeParam = (String)param.get("pageSize");		
		int pageSize = 6;
		pageSize =  StringUtil.defaultInt(pageSizeParam,6);	
		
		// 아이템 검색		
		ShopDomain shopDomain = new ShopDomain();
		shopDomain.setStartRowNo(startRowNo);
		shopDomain.setPageSize(pageSize);
		
		if(query != null && !"".equals(query)){ 
			shopDomain.setQuery("%"+query+"%");
		}
		shopService.searchList(shopDomain);
		
		modelAndView.addObject("resultItemList", shopService.searchList(shopDomain));
		modelAndView.addObject("resultItemListCnt", shopService.searchListCnt(shopDomain));
				
		return modelAndView;
		
	}
	
}