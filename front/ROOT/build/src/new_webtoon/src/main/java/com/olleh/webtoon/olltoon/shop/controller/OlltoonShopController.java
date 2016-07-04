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
package com.olleh.webtoon.olltoon.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductHistoryDomain;
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.PremiumUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;
import com.olleh.webtoon.olltoon.common.util.KeyUtil;

@Controller("OlltoonShopController")
public class OlltoonShopController {
		
	protected static Log logger = LogFactory.getLog(OlltoonShopController.class);
	
	@Autowired
	PremiumService premiumService;
	
	@Autowired
	OrderbuyService orderbuyService;
	
//	@Autowired
//	BMTermService bmTermService;
	
	/**
	 * 상품구매
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ot/shop/buyIcon.kt")
	public ModelAndView premiumBuy(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		// 로그인 상태가 아닌경우
		logger.debug("[shopDetail] 로그인상태인가? " + AuthUtil.isLogin(request));
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "로그인이 필요합니다.");
			modelAndView.addObject("errorcode", PremiumUtil.LOGIN_ERROR);
			return modelAndView;
		}
		
		// 상품정보 - prdfg, prdseq
		ProductHistoryDomain product = premiumService.productDetail(param);
		logger.debug("[shopDetail] 상품정보가 존재하는가? " + product);
		
		// 상품정보가 없음
		if (product == null) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "상품정보가 없습니다.");
			modelAndView.addObject("errorcode", PremiumUtil.NOT_EXIST_PRODUCT_ERROR);
			return modelAndView;
		}
		
		// 상품정보가 같지 않음
		if(param.get("prdhistoryseq") != null && !"".equals(param.get("prdhistoryseq")) 
				&& !param.get("prdhistoryseq").equals("" + product.getPrdhistoryseq())){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "상품정보가 없습니다.");
			modelAndView.addObject("errorcode", PremiumUtil.NOT_EXIST_PRODUCT_ERROR);
			return modelAndView;
		}
		
		// 판매상품이 아님
		if (("" + product.getSellyn()).equals("N")) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "판매중인 상품이 아닙니다.");
			modelAndView.addObject("errorcode", PremiumUtil.NOT_SELL_PRODUCT_ERROR);
			return modelAndView;
		}
		
		// IOS APP일 경우 분기처리
		if("ios".equals(RequestUtil.getClientDeviceInfo(request))){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errorcode", PremiumUtil.IOS_APP_ERROR);
			return modelAndView;
		}
		
		// 본인인증 체크
		int myCert = PremiumUtil.myCert(request);
		
		if (myCert == PremiumUtil.NOT_CERT_USER) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "본인인증이 필요합니다.");
			modelAndView.addObject("errorcode", PremiumUtil.SELF_CERT_ERROR);
			
			return modelAndView;
		}
		
		// 구매내역 체크(prdseq, prdfg, buyid)
		param.put("idfg", userMap.get("idfg"));
		ProductAvailDomain productAvail = premiumService.productAvail((String)userMap.get("userid"), param);
		logger.debug("[shopDetail] 구매내역 이용기간이 남은 아이콘인가? " + productAvail);
		if (productAvail != null) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "이미 구매한 상품입니다.");
			modelAndView.addObject("errorcode", PremiumUtil.ALREADY_PURCHASE_ERROR);
			return modelAndView;
		}
		
		String userid = (String)userMap.get("userid");
		String idfg   = (String)userMap.get("idfg");
		int prdprice  = product.getPrdprice();
		int ownBerry  = orderbuyService.berryAvail(userid, idfg);
		int ownBlue   = orderbuyService.blueBerryAvail(userid, idfg);
		int ownRasp   = orderbuyService.raspBerryAvail(userid, idfg);
		
		// 상품 판매 유형
		boolean isBerryProduct = "Y".equals(product.getBerryuseyn());
		boolean isBlueProduct  = "Y".equals(product.getBlueberryuseyn());
		boolean isRaspProduct  = "Y".equals(product.getRaspberryuseyn());

		if ("Y".equals(param.get("preview")))
		{
			modelAndView.addObject("erroryn", "N");
			modelAndView.addObject("availBerry", ownBerry);
			modelAndView.addObject("availBlueBerry", ownBlue);
			modelAndView.addObject("availRaspBerry", ownRasp);
			modelAndView.addObject("prdhistoryseq", product.getPrdhistoryseq());
			return modelAndView;
		}
		
		//구매 방법
		String purchasefg = (String)param.get("purchasefg");
		
		// 베리 체크
		if("berry".equals(purchasefg) && isBerryProduct) {
			if(ownBerry < prdprice) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", "보유 베리가 부족합니다.");
				modelAndView.addObject("errorcode", PremiumUtil.MBERRY_SHORTAGE_ERROR);
				return modelAndView;
			}
		}
		
		// 블루 베리 체크
		else if("blue".equals(purchasefg) && isBlueProduct) {
			if(ownBlue < prdprice) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", "보유 블루베리가 부족합니다.");
				modelAndView.addObject("errorcode", PremiumUtil.BBERRY_SHORTAGE_ERROR);
				return modelAndView;
			}
		}
		
		// 라즈 베리 체크
		else if("rasp".equals(purchasefg) && isRaspProduct) {
			if(ownRasp < prdprice) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", "보유 라즈베리가 부족합니다.");
				modelAndView.addObject("errorcode", PremiumUtil.RBERRY_SHORTAGE_ERROR);
				return modelAndView;
			}
		}
		
		// 구매방법이 없을 경우
		else {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "구매 방법이 없습니다.");
			modelAndView.addObject("errorcode", PremiumUtil.NOT_EXIST_PURCHAEFG_ERROR);
			return modelAndView;
		}
		
		// 구매처리
		try {
			// 보유 블루베리 셋팅 - 20150209
			product.setOwnblueberry(ownBlue);
			product.setPurchasefg(purchasefg);
			product.setCtnuserseq((String)userMap.get("ctnuserseq")); //라즈베리 관련 유저 순번
			orderbuyService.otProductBuy(userid, idfg, product);
			modelAndView.addObject("erroryn", "N");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "구매도중 오류가 발생했습니다.");
			modelAndView.addObject("errorcode", PremiumUtil.PURCHASE_ERROR);
		}

		return modelAndView;
	}
}