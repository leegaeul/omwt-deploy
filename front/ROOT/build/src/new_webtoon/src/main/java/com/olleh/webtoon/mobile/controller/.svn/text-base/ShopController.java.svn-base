/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ShopController.java
 * DESCRIPTION    : 스티콘/네임콘 구매 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Hyung.Boo.Kim      2014-07-08      init
 *****************************************************************************/
package com.olleh.webtoon.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.banner.domain.BannerDomain;
import com.olleh.webtoon.common.dao.banner.service.iface.BannerService;
import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.shop.domain.ShopDomain;
import com.olleh.webtoon.common.dao.shop.service.iface.ShopService;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.user.domain.IconDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;

@Controller("MobileShopController")
public class ShopController {
		
	protected static Log logger = LogFactory.getLog(ShopController.class);
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	OrderbuyService orderbuyService;
	
	@Autowired
	BannerService bannerService;
	
	/**
	 * SHOP 메인 화면 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/shop/shopMain.kt")
	public ModelAndView shopMain(@ModelAttribute("shopDomain") ShopDomain shopDomain) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();		
		
		//네임콘, 스티콘 페이지
		if("name".equals(shopDomain.getShopfg()) || "comment".equals(shopDomain.getShopfg())){
			modelAndView.addObject("shopfg", shopDomain.getShopfg());
			modelAndView.setViewName("mobile/shop/shopMain");
			return modelAndView;
		}
			
		//추천 페이지
		modelAndView.setViewName("mobile/shop/shopRecomMain");
		
		return modelAndView;
	}
	
	/**
	 * SHOP 추천 아이템 메인 목록을 처리하는 RequestMapping 메소드 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/shop/shopJsonRecomMainList.kt")
	public ModelAndView shopJsonRecommendMain(@ModelAttribute("shopDomain") ShopDomain shopDomain) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//상단 배너
		List<BannerDomain> bannerList = bannerService.bannerList("mshop");
		modelAndView.addObject("bannerList", bannerList);
		
		//상품 목록 갯수
		int totalCnt = shopService.shopListCnt(shopDomain);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//신상 상품 목록 (등록일순)
		shopDomain.setOrder("regdt");
		List<ShopDomain> newList = shopService.shopList(shopDomain);
		modelAndView.addObject("newList", newList);
		
		//인기 상품 목록 (구매량순)
		shopDomain.setOrder("buycnt");
		List<ShopDomain> popularList = shopService.shopList(shopDomain);
		modelAndView.addObject("popularList", popularList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		modelAndView.addObject("shopDomain", null);
		
		return modelAndView;
	}
	
	/**
	 * SHOP 네임콘, 스티콘 메인 목록을 처리하는 RequestMapping 메소드 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/shop/shopJsonMain.kt")
	public ModelAndView shopJsonMain(@ModelAttribute("shopDomain") ShopDomain shopDomain) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//상품 목록 갯수
		int totalCnt = shopService.shopListCnt(shopDomain);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//상품 목록
		List<ShopDomain> shopList = shopService.shopList(shopDomain);
		modelAndView.addObject("shopList", shopList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * SHOP 리스트 화면 ( 신상아이템, 인기아이템, 연관작가리스트, 연관작품리스트 )
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/shop/shopList.kt")
	public ModelAndView shopList() throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();		
		
		modelAndView.setViewName("mobile/shop/shopList");
		
		return modelAndView;
	}
	
	/**
	 * SHOP 리스트 화면 목록을 처리하는 RequestMapping 메소드 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/shop/shopJsonList.kt")
	public ModelAndView shopJsonListMain(@ModelAttribute("shopDomain") ShopDomain shopDomain) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		int totalCnt = 0;
		List<ShopDomain> shopList = null;

		//연관 작가, 연관 작품 리스트
		if("webtoon".equals(shopDomain.getCategory()) || "authory".equals(shopDomain.getCategory()))
		{
			//상품 목록 갯수
			totalCnt = shopService.shopRefListCnt(shopDomain);
			
			//상품 목록
			shopList = shopService.shopRefList(shopDomain);
		}
		//인기아이템, 최신아이템 리스트
		else
		{
			//상품 목록 갯수
			totalCnt = shopService.shopListCnt(shopDomain);
			
			//상품 목록
			shopList = shopService.shopList(shopDomain);
		}
		
		modelAndView.addObject("totalCnt", totalCnt);
		modelAndView.addObject("shopList", shopList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * SHOP 상세 화면 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/shop/shopDetail.kt")
	public ModelAndView shopDetail(@ModelAttribute("shopDomain") ShopDomain shopDomain, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		UserDomain user = null;
		int availableBerry = 0;
		int availableBlueBerry = 0;
		
		if(AuthUtil.isLogin(request)){
			
			user = AuthUtil.getDecryptUserInfo(request);
			
			// 보유베리
			availableBerry = orderbuyService.berryAvail(user.getEmail(), user.getIdfg());
			
			// 보유블루베리
			availableBlueBerry = orderbuyService.blueBerryAvail(user.getEmail(), user.getIdfg());
		}
		
		modelAndView.addObject("availableBerry", availableBerry);
		modelAndView.addObject("availableBlueBerry", availableBlueBerry);
		modelAndView.addObject("prdseq", shopDomain.getPrdseq());
		
		modelAndView.setViewName("mobile/shop/shopDetail");
		
		return modelAndView;
	}
	
	/**
	 * SHOP 상세 화면 정보를 처리하는 RequestMapping 메소드 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/shop/shopJsonDetailt.kt")
	public ModelAndView shopJsonDetailt(@ModelAttribute("shopDomain") ShopDomain shop, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//로그인 상태
		if(AuthUtil.isLogin(request))
		{
			UserDomain user = AuthUtil.getDecryptUserInfo(request);
			if(user != null) shop.setBuyid(user.getEmail());
		}
		
		//상품 상세정보
		ShopDomain shopDomain = shopService.shopDetail(shop);
		
		if("N".equals(shopDomain.getSellyn()))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "판매중인 상품이 아닙니다.");
			
			return modelAndView; 
		}
		
		modelAndView.addObject("shopDomain", shopDomain);
		
		//아이콘 이미지 상세정보
		List<IconDomain> iconList = shopService.shopIconList(shop);
		modelAndView.addObject("iconList", iconList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * SHOP 상세화면 이미지 목록을 처리하는 RequestMapping 메소드 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/shop/shopJsonImage.kt")
	public ModelAndView shopJsonImage(@ModelAttribute("shopDomain") ShopDomain shop) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//아이콘 이미지 갯수
		int totalCnt = shopService.shopIconTotalCnt(shop);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//아이콘 이미지 상세정보
		List<IconDomain> iconList = shopService.shopIconList(shop);
		modelAndView.addObject("iconList", iconList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * SHOP 관련작품 목록을 처리하는 RequestMapping 메소드 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/shop/shopJsonReftoon.kt")
	public ModelAndView shopJsonReftoon(@ModelAttribute("shopDomain") ShopDomain shop) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//관련 작품 갯수
		int totalCnt = shopService.shopReftoonTotalCnt(shop);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//관련 작품 정보
		List<ToonDomain> reftoonList = shopService.shopReftoonList(shop);
		modelAndView.addObject("reftoonList", reftoonList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * SHOP 관련 작품, 작가 추천 아이템 처리하는 RequestMapping 메소드 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/m/shop/shopJsonRefprodcut.kt")
	public ModelAndView shopJsonRefprodcut(@ModelAttribute("shopDomain") ShopDomain shop) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//관련 작품 갯수
		int totalCnt = shopService.shopRefproductTotalCnt(shop);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//관련 작품 정보
		List<ShopDomain> refprdList = shopService.shopRefprdList(shop);
		modelAndView.addObject("refprdList", refprdList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
}