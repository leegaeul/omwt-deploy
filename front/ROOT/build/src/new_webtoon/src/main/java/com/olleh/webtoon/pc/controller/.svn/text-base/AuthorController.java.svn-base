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

package com.olleh.webtoon.pc.controller;

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

@Controller
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
			
	protected static Log logger = LogFactory.getLog(AuthorController.class);
		
	/**
	 * 요일별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/toon/authorDetail.kt")
	public ModelAndView authorDetail(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("pc/toon/authorDetail");
		
		//작가 상세정보
		AuthorDomain author = authorService.authorDetail(param);
		
		//프로필 설정이 비공개일 경우 메인으로 이동 - 20150210
//		if(!"Y".equals(author.getProfileyn())){
//			modelAndView.setViewName("redirect:"+MessageUtil.getSystemMessage("system.pc.domain")+"/main.kt");
//			return modelAndView;
//		}
		
		modelAndView.addObject("author", author);
		
		//작가의 연재 작품 목록
		param.put("pcdisplayyn", "Y");
		List<ToonDomain> toonList = authorService.authorToonList(param);
		modelAndView.addObject("toonList", toonList);
		
		//작가 에피소드	목록	
		List<YoyozineDomain> yoyozineList = authorService.authorYoyozineList(param);
		modelAndView.addObject("yoyozineList", yoyozineList);
	
		param.put("itemstartRowNo", 0);
		param.put("itempageSize", 10);
		
		//연관 작품 아이템 목록(최대 10개만)
		List<ShopDomain> shopList = authorService.authorItemList(param);
		modelAndView.addObject("shopList", shopList);
		
		//연관 작품 아이템 갯수
		int shopListcnt = 0;
		shopListcnt = authorService.authorItemListCount(param);
		modelAndView.addObject( "shopListcnt", shopListcnt );
		
		
		return modelAndView;
		
	}
	
}