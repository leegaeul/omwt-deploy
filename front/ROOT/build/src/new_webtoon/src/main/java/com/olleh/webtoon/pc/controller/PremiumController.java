/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : PremiumController.java
 * DESCRIPTION    : 프리미엄 웹툰 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-21      init
 *****************************************************************************/

package com.olleh.webtoon.pc.controller;

import java.util.ArrayList;
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
import com.olleh.webtoon.common.dao.novel.service.iface.NovelService;
import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.premium.domain.CategoryDomain;
import com.olleh.webtoon.common.dao.premium.domain.PremiumDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductHistoryDomain;
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.dao.toon.service.iface.ToonService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
public class PremiumController {

	protected static Log logger = LogFactory.getLog(PremiumController.class);

	@Autowired
	PremiumService premiumService;
	
	@Autowired
	NovelService novelService;

	@Autowired
	BannerService bannerService;

	@Autowired
	ToonService toonService;

	@Autowired
	OrderbuyService orderbuyService;

	/**
	 * 프리미엄 메인 화면 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/premium/premiumMain.kt")
	public ModelAndView premiumMain(@ModelAttribute("premiumDomain") PremiumDomain premiumDomain) throws Exception {

		ModelAndView modelAndView = new ModelAndView();		
		
		//메인화면, PC버전
		premiumDomain.setMainyn("Y");
		premiumDomain.setMobileyn("N");
		
		//상단 배너
		List<BannerDomain> bannerList = bannerService.bannerList("premium");
		modelAndView.addObject("bannerList", bannerList);
		
		//카테고리 목록
		List<CategoryDomain> categoryList = premiumService.premiumCategoryList("premium");
		modelAndView.addObject("categoryList", categoryList);

		if(categoryList != null)
		{
			//카테고리별 리스트 목록
			ArrayList<ArrayList<PremiumDomain>> premiumList = new ArrayList<ArrayList<PremiumDomain>>();

			for(int i = 0; i < categoryList.size(); i++)
			{
				premiumDomain.setCategoryseq(categoryList.get(i).getCategoryseq());
				premiumList.add(premiumService.premiumList(premiumDomain));
			}

			modelAndView.addObject("premiumList", premiumList);
		}

		modelAndView.setViewName("pc/premium/premiumMain");
		
		return modelAndView;
	}


	/**
	 * 프리미엄 리스트 화면 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/premium/premiumList.kt")
	public ModelAndView premiumDetailList(@ModelAttribute("premiumDomain") PremiumDomain premiumDomain) throws Exception {

		ModelAndView modelAndView = new ModelAndView();		

		//PC버전
		premiumDomain.setMobileyn("N");
		
		//페이지 사이즈 기본값
		if(premiumDomain.getPageSize() == 0) premiumDomain.setPageSize(10);

		//총 갯수 조회
		int totalCnt = premiumService.totalListCnt(premiumDomain);
		modelAndView.addObject("totalCnt", totalCnt);

		//카테고리
		CategoryDomain category = premiumService.getCategoryBySeq(premiumDomain.getCategoryseq());
		modelAndView.addObject("category", category);
		
		//리스트 목록 조회
		List<PremiumDomain> list = null;
		if("novel".equals(premiumDomain.getToonfg())){
			list = novelService.novelList(premiumDomain);
		}else{
			list = premiumService.premiumList(premiumDomain);
		}
		 
		modelAndView.addObject("premiumList", list);

		modelAndView.setViewName("pc/premium/premiumList");

		return modelAndView;
	}

	/**
	 * 성인인증 페이지
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/premium/premiumAdultCert.kt")
	public ModelAndView premiumAdultCert(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pc/premium/premiumAdultCert");

		// 로그인 체크
		if (!AuthUtil.isLogin(request)) {
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}

		// 인증후 URL
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "/main.kt");
		urlcd = urlcd.replaceAll("urlcd=", "");
		modelAndView.addObject("urlcd", urlcd);
		
		return modelAndView;
	}

	/**
	 * 법적대리인인증 페이지
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/premium/premiumLawerCert.kt")
	public ModelAndView premiumLawerCert(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pc/premium/premiumLawerCert");

		// 로그인 체크
		if (!AuthUtil.isLogin(request)) {
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}
		
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		modelAndView.addObject("birthday", user.getBirthday());

		// 인증후 URL
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "/main.kt");
		urlcd = urlcd.replaceAll("urlcd=", "");
		modelAndView.addObject("urlcd", urlcd);
				
		return modelAndView;
	}

	/**
	 * 본인인증 페이지
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/premium/premiumMyCert.kt")
	public ModelAndView premiumMyCert(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pc/premium/premiumMyCert");

		// 로그인 체크
		if (!AuthUtil.isLogin(request)) {
			modelAndView.setViewName("redirect:/login.kt");
			return modelAndView;
		}

		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		modelAndView.addObject("nickname", user.getNickname());

		// 인증후 URL
		String urlcd = StringUtil.defaultStr(request.getQueryString(), "/main.kt");
		urlcd = urlcd.replaceAll("urlcd=", "");
		modelAndView.addObject("urlcd", urlcd);
		
		return modelAndView;
	}
	
	/**
	 * 복합결제 페이지
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/premium/mixPurchase.kt")
	public ModelAndView moveToMixPurchase(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pc/premium/mixPurchase");
		
		// 로그인 체크
		if (!AuthUtil.isLogin(request)) {
			modelAndView.setViewName("redirect:/m/login.kt");
			return modelAndView;
		}
		
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		
		//상품정보 조회
		String prdhistoryseq = StringUtil.defaultStr(request.getParameter("prdhistoryseq"));
		ProductHistoryDomain product = premiumService.productDetailBySeq(prdhistoryseq);
		
		//복합결제 상품이 아닌 상품으로 접근, 네임콘, 스티콘상품 접근시 예외처리
		if(!"mix".equals(product.getPurchasefg()) || "comment".equals(product.getPrdfg()) || "name".equals(product.getPrdfg())){
			throw new Exception();
		}
		
		modelAndView.addObject("product", product);
		
		//상품 제목
		String title = StringUtil.defaultStr(request.getParameter("title"));
		modelAndView.addObject("title", title);
		
		//보유베리
		int myBerry = orderbuyService.berryAvail(user.getEmail(), user.getIdfg());
		modelAndView.addObject("myBerry", myBerry);
		
		//보유블루베리
		int myBlueBerry = orderbuyService.blueBerryAvail(user.getEmail(), user.getIdfg());
		modelAndView.addObject("myBlueBerry", myBlueBerry);
		
		return modelAndView;
	}
}