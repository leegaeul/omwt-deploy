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
package com.olleh.webtoon.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinTermDomain;
import com.olleh.webtoon.common.dao.bluemembership.service.BMTermService;
import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductHistoryDomain;
import com.olleh.webtoon.common.dao.premium.service.iface.PremiumService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.PremiumUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller("CommonShopController")
public class ShopController {
		
	protected static Log logger = LogFactory.getLog(ShopController.class);
	
	@Autowired
	PremiumService premiumService;
	
	@Autowired
	OrderbuyService orderbuyService;
	
	@Autowired
	BMTermService bmTermService;
	
	/**
	 * 상품구매
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shop/buyIcon.kt")
	public ModelAndView premiumBuy(@RequestParam Map<String,Object> param, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");
		
		// 로그인 상태가 아닌경우
		logger.debug("[shopDetail] 로그인상태인가? " + AuthUtil.isLogin(request));
		if (!AuthUtil.isLogin(request)) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "로그인이 필요합니다.");
			modelAndView.addObject("errorcode", PremiumUtil.LOGIN_ERROR);
			return modelAndView;
		}
		
		// 상품정보 (prdfg, prdseq)
		ProductHistoryDomain product = premiumService.productDetail(param);
		logger.debug("[shopDetail] 상품정보가 존재하는가? " + product);
		
		// 상품정보가 없음
		if (product == null) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "상품정보가 없습니다.1");
			modelAndView.addObject("errorcode", PremiumUtil.NOT_EXIST_PRODUCT_ERROR);
			return modelAndView;
		}
		
		// 상품정보가 같지 않음
		if(param.get("prdhistoryseq") != null && !"".equals(param.get("prdhistoryseq")) 
				&& !param.get("prdhistoryseq").equals("" + product.getPrdhistoryseq())){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "상품정보가 없습니다.2");
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
		
		// 상품 판매 유형
		boolean isBerryProduct = PremiumUtil.BERRY_PURCHASE_PRODUCT.equals(product.getPurchasefg());
		boolean isBlueBerryProduct = PremiumUtil.BBERRY_PURCHASE_PRODUCT.equals(product.getPurchasefg());
		boolean isMixBerryProduct = PremiumUtil.MIX_PURCHASE_PRODUCT.equals(product.getPurchasefg());
		
		// 회원정보 복호화
		UserDomain user = AuthUtil.getDecryptUserInfo(request);
		
		// 구매내역 체크(prdseq, prdfg, buyid)
		ProductAvailDomain productAvail = premiumService.productAvail(user.getEmail(), param);
		logger.debug("[shopDetail] 구매내역 이용기간이 남은 아이콘인가? " + productAvail);
		if (productAvail != null) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "이미 구매한 상품입니다.");
			modelAndView.addObject("errorcode", PremiumUtil.ALREADY_PURCHASE_ERROR);
			return modelAndView;
		}
		
		// 블루멤버십 회원여부 체크
		String blueMembershipyn = premiumService.getBlueMembershipInfo(user.getEmail(), user.getIdfg());
		if(isBlueBerryProduct && !"Y".equals(blueMembershipyn)){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.private.blue.memebership"));
			modelAndView.addObject("errorcode", PremiumUtil.BLUE_MEMBERSHIP_ERROR);
			return modelAndView;
		}
		
		// 실제 보유베리
		int availableBerry = orderbuyService.berryAvail(user.getEmail(), user.getIdfg());
		
		// 실제 보유블루베리
		int availableBlueBerry = orderbuyService.blueBerryAvail(user.getEmail(), user.getIdfg());
		
		// 마이너스 베리 분기처리
		int ownBerry = availableBerry < 0 ? 0 : availableBerry;
		int ownBlueBerry = availableBlueBerry < 0 ? 0 : availableBlueBerry; 
		
		logger.debug("[shopDetail] 필요베리: " + product.getPrdprice() + " 구매방식: " + product.getPurchasefg());
		logger.debug("[shopDetail] 보유베리: " + availableBerry + " 보유블루베리: " + availableBlueBerry);
		
		// 베리 체크
		if(isBerryProduct && ownBerry < product.getPrdprice())
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.shop.charge.berry"));
			modelAndView.addObject("errorcode", PremiumUtil.BERRY_SHORTAGE_ERROR);
			modelAndView.addObject("myBerry", availableBerry);
			return modelAndView;
		}
		
		// 블루베리 체크
		if(isBlueBerryProduct && ownBlueBerry < product.getPrdprice())
		{	
			modelAndView.addObject("erroryn", "Y");
			
			//블루멤버십 해지 여부 조회
			String cancelyn = orderbuyService.getBmCancelyn(user.getEmail(), user.getIdfg());
			modelAndView.addObject("cancelyn", cancelyn);
			
			if(!"Y".equals(cancelyn)){
				//블루베리 충전 날짜 조회
				BMJoinTermDomain domain =  bmTermService.getNextTermForUserAndPrd(user.getIdfg(), user.getEmail(), null);
				modelAndView.addObject("chargeDate", domain.getStartdt());
			}

			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.premium.charge.blueberry"));
			modelAndView.addObject("errorcode", PremiumUtil.BBERRY_SHORTAGE_ERROR);
			return modelAndView;
		}
		
		// 복합결제 체크
		if(isMixBerryProduct && ownBerry + ownBlueBerry < product.getPrdprice())
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("membershipyn", StringUtil.defaultStr(blueMembershipyn, "N"));
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.shop.charge.berry"));
			modelAndView.addObject("errorcode", PremiumUtil.BERRY_SHORTAGE_ERROR);
			modelAndView.addObject("myBerry", availableBerry);
			return modelAndView;
		}

		if ("Y".equals(param.get("preview")))
		{
			modelAndView.addObject("erroryn", "N");
			modelAndView.addObject("prdhistoryseq", product.getPrdhistoryseq());
			return modelAndView;
		}
		
		if(isMixBerryProduct)
		{
			// 소진할 베리
			int berryamount = StringUtil.defaultInt(param.get("berryamount").toString());
			int blueberryamount = StringUtil.defaultInt(param.get("blueberryamount").toString());
			
			if(berryamount > ownBerry){
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", "보유베리가 부족합니다.");
				modelAndView.addObject("errorcode", PremiumUtil.MBERRY_SHORTAGE_ERROR);
				return modelAndView;
			}
			
			if(blueberryamount > ownBlueBerry){
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", "보유블루베리가 부족합니다.");
				modelAndView.addObject("errorcode", PremiumUtil.MBERRY_SHORTAGE_ERROR);
				return modelAndView;
			}
			
			if(berryamount + blueberryamount != product.getPrdprice()){
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", "가격정보가 일치하지 않습니다.(복합결제)");
				modelAndView.addObject("errorcode", PremiumUtil.MBERRY_SHORTAGE_ERROR);
				return modelAndView;
			}
			
			// 소진할 베리 셋팅
			product.setBerryamount(berryamount);
			product.setBlueberryamout(blueberryamount);
		}
		
		// 구매처리
		try {
			// 보유 블루베리 셋팅 - 20150209
			product.setOwnblueberry(ownBlueBerry);
			orderbuyService.productBuy(user.getEmail(), user.getIdfg(), product);
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