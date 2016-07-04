/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : SearchController.java
 * DESCRIPTION    : 웹툰 검색 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-05-13      init
 *****************************************************************************/

package com.olleh.webtoon.pc.controller;

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

@Controller
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
	@RequestMapping(value = "/search.kt")
	public ModelAndView search(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("pc/search");
		
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
				
				//PC전시, toon 전시 또는 premium 전시 또는 웹소설
				if("Y".equals(toon.getPcdisplayyn()) && ("Y".equals(toon.getToonyn()) || "Y".equals(toon.getPremiumyn()) || "novel".equals(toon.getToonfg()))){
					if((toon.getWebtoonnm() != null && (tempWebtoonnm).indexOf(query.replaceAll(" ", "")) > -1) ||
							(toon.getRefkeyword() != null && (tempRefkeyword).indexOf(query.replaceAll(" ", "")) > -1) ||
							//(toon.getTimestitle() != null && (toon.getTimestitle().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm1() != null && (tempAuthornm1).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm2() != null && (tempAuthornm2).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm3() != null && (tempAuthornm3).indexOf(query.replaceAll(" ", "")) > -1)) {
						
						if(!"novel".equals(toon.getToonfg())){
							resultToonList.add(toon);
						}else{
							resultNovelList.add(toon);
						}
					}
				}
			}
		}
		
		int fromIndex = 0;
		int toIndex = 5;
		if(resultToonList.size() < toIndex) toIndex = resultToonList.size();
		
		//웹툰
		modelAndView.addObject("resultToonList", resultToonList.subList(fromIndex, toIndex));
		modelAndView.addObject("resultToonListCnt", resultToonList.size());
		
		//웹소설
		modelAndView.addObject("resultNovelList", resultNovelList);
		modelAndView.addObject("resultNovelListCnt", resultNovelList.size());
		
		// 아이템 검색		
		ShopDomain shopDomain = new ShopDomain();
		shopDomain.setStartRowNo(0);
		shopDomain.setPageSize(10);
		
		if(query != null && !"".equals(query)){ 
			shopDomain.setQuery("%"+query+"%");
		}
		
		modelAndView.addObject("resultItemList", shopService.searchList(shopDomain));
		modelAndView.addObject("resultItemListCnt", shopService.searchListCnt(shopDomain));
		
		return modelAndView;
		
	}
	
	/**
	 * 검색 결과 더보기 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/searchWebtoonList.kt")
	public ModelAndView searchWebtoonList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("pc/searchWebtoonList");
		
		String query  = StringUtil.cleanXSS(StringUtil.defaultStr((String)param.get("query"), ""));
		String toonfg = StringUtil.cleanXSS(StringUtil.defaultStr((String)param.get("toonfg"), ""));
		
		String startRowNoParam = (String)param.get("startRowNo");		
		int startRowNo = 0;
		startRowNo =  StringUtil.defaultInt(startRowNoParam,0);		
		
		String pageSizeParam = (String)param.get("pageSize");		
		int pageSize = 10;
		pageSize =  StringUtil.defaultInt(pageSizeParam,10);	
		
		//작품 전체 목록 초기화
		List<ToonDomain> toonList = toonService.toonList(param);		
	
		//작품 검색 결과 목록
		List<ToonDomain> resultToonList = new ArrayList<ToonDomain>();		
	
		// 작품명, 회차명, 작가명 검색 결과 목록
		if(!query.equals("")){
			for(ToonDomain toon : toonList){
				
				//PC전시, toon 전시 또는 premium 전시
				if("Y".equals(toon.getPcdisplayyn()) && ("Y".equals(toon.getToonyn()) || "Y".equals(toon.getPremiumyn()) || "novel".equals(toon.getToonfg()))){
					if((toon.getWebtoonnm() != null && (toon.getWebtoonnm().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1) || 
							//(toon.getTimestitle() != null && (toon.getTimestitle().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm1() != null && (toon.getAuthornm1().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm2() != null && (toon.getAuthornm2().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1) || 
							(toon.getAuthornm3() != null && (toon.getAuthornm3().replaceAll(" ", "")).indexOf(query.replaceAll(" ", "")) > -1)){
						
						if("toon".equals(toonfg) && "toon".equals(toon.getToonfg())){
							resultToonList.add(toon);
						}else if("novel".equals(toonfg) && "novel".equals(toon.getToonfg())){
							resultToonList.add(toon);
						}
					}
						
				}
			}
		}		
		
		int toIndex = (startRowNo + pageSize);
		if(toIndex > resultToonList.size() ) toIndex = resultToonList.size();
		
		modelAndView.addObject("resultToonList", resultToonList.subList(startRowNo, toIndex));
		modelAndView.addObject("resultToonListCnt", resultToonList.size());		
		
		return modelAndView;
		
	}
	
	/**
	 * 검색 결과 더보기 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/searchItemList.kt")
	public ModelAndView searchItemList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("pc/searchItemList");
		
		String query = StringUtil.cleanXSS(StringUtil.defaultStr((String)param.get("query"), ""));
		
		String startRowNoParam = (String)param.get("startRowNo");		
		int startRowNo = 0;
		startRowNo =  StringUtil.defaultInt(startRowNoParam,0);		
		
		String pageSizeParam = (String)param.get("pageSize");		
		int pageSize = 20;
		pageSize =  StringUtil.defaultInt(pageSizeParam,20);	
		
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