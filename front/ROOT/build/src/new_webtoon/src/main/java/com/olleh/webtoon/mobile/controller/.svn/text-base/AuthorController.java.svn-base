/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : AuthorController.java
 * DESCRIPTION    : 작가 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-21      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

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
import com.olleh.webtoon.common.dao.toon.domain.AuthorDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.service.iface.AuthorService;
import com.olleh.webtoon.common.dao.yoyozine.domain.YoyozineDomain;
import com.olleh.webtoon.common.util.StringUtil;

@Controller("MobileAuthorController")
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
			
	protected static Log logger = LogFactory.getLog(AuthorController.class);
		
	/**
	 * 요일별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "m/toon/authorDetail.kt")
	public ModelAndView authorDetail(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//프로필 설정이 비공개일 경우 메인으로 이동 - 20150210
//		String profileyn = authorService.getAuthorProfileyn(param);
//		if(!"Y".equals(profileyn)){
//			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.mobile.domain")+"/m/main.kt");
//			return modelAndView;
//		}
		
		modelAndView.setViewName("mobile/toon/authorDetail");
		
		return modelAndView;
		
	}
	
	/**
	 * 요일별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/toon/authorJsonDetail.kt")
	public ModelAndView authorJsonDetail(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//페이징 처리
		String toonPageNoParam = (String)param.get("toonPageNo");
		String yoyozinePageNoParam = (String)param.get("yoyozinePageNo");
		String itemPageNoParm = (String)param.get("itemPageNo");
		
		int toonPageNo = 1;
		int yoyozinePageNo = 1;
		int itemPageNo = 1;
		
		toonPageNo =  StringUtil.defaultInt(toonPageNoParam,1);
		yoyozinePageNo =  StringUtil.defaultInt(yoyozinePageNoParam,1);
		itemPageNo = StringUtil.defaultInt(itemPageNoParm,1);
		String pageSizeParam = (String)param.get("pageSize");		
		int pageSize = 5;
		pageSize =  StringUtil.defaultInt(pageSizeParam,5);
		
		param.put("toonStartRowNo", (toonPageNo-1)*pageSize);
		param.put("yoyozineStartRowNo", (yoyozinePageNo-1)*pageSize);
		param.put("itemStartRowNo", (itemPageNo-1)*pageSize);
		param.put("pageSize", pageSize);
		
		// 작품 갯수
		int authorToonListCount = authorService.authorToonListCount(param);
		modelAndView.addObject("toonTotalCount", authorToonListCount);
		
		// 에페소드 갯수
		int authorYoyozineListCount = authorService.authorYoyozineListCount(param);
		modelAndView.addObject("yoyozineTotalCount", authorYoyozineListCount);
		
		// 작가관련 상품 갯수
		int authorItemListCount = authorService.authorItemListCount(param);
		modelAndView.addObject("itemTotalCount", authorItemListCount);
		
		//작가 상세정보
		AuthorDomain author = authorService.authorDetail(param);
		modelAndView.addObject("author", author);
		
		//작가의 연재 작품 목록
		param.put("mdisplayyn", "Y");
		List<ToonDomain> toonList = authorService.authorToonList(param);
		modelAndView.addObject("toonList", toonList);
		
		//작가 에피소드	목록	
		List<YoyozineDomain> yoyozineList = authorService.authorYoyozineList(param);
		modelAndView.addObject("yoyozineList", yoyozineList);
		
		param.put("itemstartRowNo", 0);
		param.put("itempageSize", 6);

		//작가관련 상품 목록	
		List<ShopDomain> authorItemList = authorService.authorItemList(param);
		modelAndView.addObject("authorItemList", authorItemList);
		
		return modelAndView;
		
	}
	
}