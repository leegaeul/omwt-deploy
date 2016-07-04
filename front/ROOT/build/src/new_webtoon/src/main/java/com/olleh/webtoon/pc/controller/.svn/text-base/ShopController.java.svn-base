/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ShopController.java
 * DESCRIPTION    : 스티콘, 네임콘 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0       			JSS      2014-07-14      init
 *****************************************************************************/
package com.olleh.webtoon.pc.controller;

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
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.dao.shop.domain.ShopDomain;
import com.olleh.webtoon.common.dao.shop.service.iface.ShopService;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.user.domain.IconDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;

@Controller
public class ShopController{
	
	protected static Log logger = LogFactory.getLog(ShopController.class);
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	PremiumService premiumService;
	
	@Autowired
	OrderbuyService orderbuyService;
	
	@Autowired
	BannerService bannerService;
	
	/**
	 * SHOP 메인 화면
	 * @return ModelAndView medelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/shop/shopMain.kt")
	public ModelAndView shopMain(@ModelAttribute("shopDomain") ShopDomain shopDomain) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();		
		
		//페이지 사이즈 기본값
		if(shopDomain.getPageSize() == 0) shopDomain.setPageSize(10);
		
		//시작번호 기본값 
		if(shopDomain.getStartRowNo() == 0) shopDomain.setStartRowNo(0);
		
		//상품 목록 갯수
		int totalCnt = shopService.shopListCnt(shopDomain);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//네임콘, 스티콘 페이지
		if("name".equals(shopDomain.getShopfg()) || "comment".equals(shopDomain.getShopfg()))
		{
			//페이지 사이즈 기본값 변경
			shopDomain.setPageSize(20);
			
			//상품 목록
			List<ShopDomain> shopList = shopService.shopList(shopDomain);
			modelAndView.addObject("shopList", shopList);
			
			modelAndView.setViewName("pc/shop/shopMain");
			return modelAndView;
		}
		
		//상단 배너
		List<BannerDomain> bannerList = bannerService.bannerList("shop");
		modelAndView.addObject("bannerList", bannerList);
		
		//신상 상품 목록 (등록일순)
		shopDomain.setOrder("regdt");
		List<ShopDomain> newList = shopService.shopList(shopDomain);
		modelAndView.addObject("newList", newList);
		
		//인기 상품 목록 (구매량순)
		shopDomain.setOrder("buycnt");
		List<ShopDomain> popularList = shopService.shopList(shopDomain);
		modelAndView.addObject("popularList", popularList);
		
		modelAndView.setViewName("pc/shop/shopRecomMain");
		
		return modelAndView;
		
	}
	
	/**
	 * SHOP 리스트 화면 ( 신상아이템, 인기아이템 )
	 *@return ModelAndView modelAndView : modelAndView 객체 
	 */
	@RequestMapping(value="/shop/shopList.kt")
	public ModelAndView shopList(@ModelAttribute("shopDomain") ShopDomain shopDomain) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//페이지 사이즈 기본값
		if(shopDomain.getPageSize() == 0) shopDomain.setPageSize(20);
		
		//시작 번호 기본값
		if(shopDomain.getPageSize() == 0) shopDomain.setPageSize(0);
		
		//상품 목록 갯수
		int totalCnt = shopService.shopListCnt(shopDomain);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//상품 목록
		List<ShopDomain> shopList = shopService.shopList(shopDomain);
		modelAndView.addObject("shopList", shopList);
		
		modelAndView.setViewName("pc/shop/shopList");
		
		return modelAndView;
		
	}
	
	/**
	 * SHOP 연관작품, 연관작가 메인 화면
	 *@return ModelAndView modelAndView : modelAndView 객체 
	 */
	@RequestMapping(value="/shop/shopRefMain.kt")
	public ModelAndView shopRefMain(@ModelAttribute("shopDomain") ShopDomain shopDomain) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();		
		
		//연관 상품 스티콘 목록(최대 10개만)
		shopDomain.setPrdfg("comment");
		List<ShopDomain> refcommentList = shopService.shopRefList(shopDomain);
		modelAndView.addObject("refcommentList", refcommentList);
		
		//연관 상품 스티콘 갯수(작품, 작가)
		int refcommentListCnt = shopService.shopRefListCnt(shopDomain);
		modelAndView.addObject("refcommentListCnt", refcommentListCnt);
		
		
		//연관 작품 네임콘 목록(최대 10개만)
		shopDomain.setPrdfg("name");
		List<ShopDomain> refnameconList = shopService.shopRefList(shopDomain);
		modelAndView.addObject("refnameconList", refnameconList);
		
		//연관 작품 네임콘 갯수(작품, 작가)
		int refnameconListcnt = shopService.shopRefListCnt(shopDomain);
		modelAndView.addObject("refnameconListcnt", refnameconListcnt);
				
		modelAndView.setViewName("pc/shop/shopRefMain");
		
		return modelAndView;
		
	}
	
	/**
	 * SHOP 연관작품, 연관작가 리스트 화면
	 *@return ModelAndView modelAndView : modelAndView 객체 
	 */
	@RequestMapping(value="/shop/shopRefList.kt")
	public ModelAndView shopRefList(@ModelAttribute("shopDomain") ShopDomain shopDomain) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//페이지 사이즈 기본값
		if(shopDomain.getPageSize() == 0) shopDomain.setPageSize(20);
		
		//시작 번호 기본값
		if(shopDomain.getPageSize() == 0) shopDomain.setPageSize(0);
		
		shopDomain.setPrdfg(shopDomain.getCategoryprdfg());
		
		//연관 상품 목록 갯수(작품, 작가)
		int totalCnt = shopService.shopRefListCnt(shopDomain);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//연관 상품 목록(작품, 작가)
		List<ShopDomain> shopList = shopService.shopRefList(shopDomain);
		modelAndView.addObject("shopList", shopList);
		
		modelAndView.setViewName("pc/shop/shopRefList");
		
		return modelAndView;
		
	}
	
	/**
	 * SHOP 네임콘, 스티콘 조회후 상세 image보여줌
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value="/shop/shopDetail.kt")
	public ModelAndView shopDetail(@ModelAttribute("shopDomain") ShopDomain shop, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		UserDomain user = null;
		int availableBerry = 0;
		int availableBlueBerry = 0;
		String blueMembershipyn = "N";

		if(AuthUtil.isLogin(request)){
			
			user = AuthUtil.getDecryptUserInfo(request);
			
			// 블루멤버십 회원여부 체크
			blueMembershipyn = premiumService.getBlueMembershipInfo(user.getEmail(), user.getIdfg());
			
			// 보유베리
			availableBerry = orderbuyService.berryAvail(user.getEmail(), user.getIdfg());
			
			if("Y".equals(blueMembershipyn)){
				
				// 보유블루베리
				availableBlueBerry = orderbuyService.blueBerryAvail(user.getEmail(), user.getIdfg());
			}
			
		}
		
		if(user != null) shop.setBuyid(user.getEmail());
		
		modelAndView.addObject("blueMembershipyn", blueMembershipyn);
		modelAndView.addObject("availableBerry", availableBerry);
		modelAndView.addObject("availableBlueBerry", availableBlueBerry);
		
		//상품 상세정보
		ShopDomain shopDomain = shopService.shopDetail(shop);
		
		if("N".equals(shopDomain.getSellyn()))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "판매중인 상품이 아닙니다.");
			
			return modelAndView; 
		}
		
		modelAndView.addObject("shopDomain", shopDomain);
		
		//아이콘 이미지 갯수
		List<IconDomain> iconList = shopService.shopIconList(shop);
		modelAndView.addObject("iconList", iconList);
		
		//아이콘 이미지 상세정보
		int iconCnt = shopService.shopIconTotalCnt(shop);
		modelAndView.addObject("iconCnt", iconCnt);
		
		//관련 작품 갯수
		int reftoonCnt = shopService.shopReftoonTotalCnt(shop);
		modelAndView.addObject("reftoonCnt", reftoonCnt);
		
		//관련 작품 정보
		List<ToonDomain> reftoonList = shopService.shopReftoonList(shop);
		modelAndView.addObject("reftoonList", reftoonList);
		
		//작품, 작가 관련 상품 갯수
		int refprdCnt = shopService.shopRefproductTotalCnt(shop);
		modelAndView.addObject("refprdCnt", refprdCnt);
		
		//작품, 작가 관련 상품 리스트 정보
		List<ShopDomain> refprdList = shopService.shopRefprdList(shop);
		modelAndView.addObject("refprdList", refprdList);
		
		modelAndView.setViewName("pc/shop/shopDetail");
		
		return modelAndView;
	}

}
