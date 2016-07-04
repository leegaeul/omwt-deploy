/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - OrderbuyServiceImpl.java
 * 
 * DESCRIPTION
 * -  주문/구매 내역 Service implement class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.orderbuy.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdDomain;
import com.olleh.webtoon.common.dao.bluemembership.persistence.BMPrdMapper;
import com.olleh.webtoon.common.dao.bluemembership.service.BMService;
import com.olleh.webtoon.common.dao.orderbuy.domain.BuyDomain;
import com.olleh.webtoon.common.dao.orderbuy.domain.OrderDomain;
import com.olleh.webtoon.common.dao.orderbuy.persistence.BuyMapper;
import com.olleh.webtoon.common.dao.orderbuy.persistence.OrderMapper;
import com.olleh.webtoon.common.dao.orderbuy.service.iface.OrderbuyService;
import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductHistoryDomain;
import com.olleh.webtoon.common.dao.premium.persistence.ProductAvailMapper;
import com.olleh.webtoon.common.dao.premium.persistence.ProductHistoryMapper;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.PremiumUtil;

@Service("orderbuyService")
@Repository
public class OrderbuyServiceImpl implements OrderbuyService
{	
	private Log					logger	= LogFactory.getLog( OrderbuyService.class );
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private BuyMapper buyMapper;

	@Autowired
	private ProductAvailMapper productAvailMapper;
	
	@Autowired
	private ProductHistoryMapper productHistoryMapper;
	
	@Autowired
	private BMPrdMapper prdMapper;
	
	@Autowired
	private BMService bmService;

	/**
	 * 사용가능한 베리조회
	 * @param email
	 * @return int berry: 사용가능한 베리
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int berryAvail(String email, String idfg) {
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setOrderid(email);
		orderDomain.setIdfg(idfg);

		Integer currentBerry = orderMapper.selectOrderSum(orderDomain);
		return (currentBerry == null) ? 0 : currentBerry;
	}
	
	/**
	 * 사용가능한 블루베리조회
	 * @param email
	 * @return int blueberry: 사용가능한 블루베리
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int blueBerryAvail(String email, String idfg){
		
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setOrderid(email);
		orderDomain.setIdfg(idfg);

		Integer currentBlueBerry = orderMapper.selectBlueOrderSum(orderDomain);
		return (currentBlueBerry == null) ? 0 : currentBlueBerry;
	}
	
	/**
	 * 사용가능한 랒베리조회
	 * @param email
	 * @return int blueberry: 사용가능한 블루베리
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int raspBerryAvail(String email, String idfg){
		
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setOrderid(email);
		orderDomain.setIdfg(idfg);

		Integer currentRaspBerry = orderMapper.selectRaspOrderSum(orderDomain);
		return (currentRaspBerry == null) ? 0 : currentRaspBerry;
	}

	/**
	 * 사용가능한 블루베리조회(DecimalFormat)
	 * @param email
	 * @return String berry: 사용가능한 블루베리
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public String strBerryAvail(String email, String idfg)
	{		
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setOrderid(email);
		orderDomain.setIdfg(idfg);

		Integer currentBerry = orderMapper.selectOrderSum(orderDomain);
		
		if( currentBerry == null )
			return "0";
		
		NumberFormat nf = NumberFormat.getInstance();		  
		return nf.format(currentBerry);
	}
	
	/**
	 * 사용가능한 블루베리조회(DecimalFormat)
	 * @param email
	 * @return String blueberry: 사용가능한 블루베리
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public String strBlueBerryAvail(String email, String idfg)
	{	
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setOrderid(email);
		orderDomain.setIdfg(idfg);

		Integer currentBlueBerry = orderMapper.selectBlueOrderSum(orderDomain);
		
		if( currentBlueBerry == null )
			return "0";
		
		NumberFormat nf = NumberFormat.getInstance();		  
		return nf.format(currentBlueBerry);
	}
	
	/**
	 * 사용가능한 라즈베리조회(DecimalFormat)
	 * @param email
	 * @return String raspberry: 사용가능한 블루베리
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public String strRaspBerryAvail(String email, String idfg)
	{	
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setOrderid(email);
		orderDomain.setIdfg(idfg);

		Integer currentRaspBerry = orderMapper.selectRaspOrderSum(orderDomain);
		
		if( currentRaspBerry == null )
			return "0";
		
		NumberFormat nf = NumberFormat.getInstance();		  
		return nf.format(currentRaspBerry);
	}
	
	/**
	 * 블루베리 충전일 조회
	 * @param email, idfg
	 * @return 블루베리 충전일(YY년-MM월-DD일)
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public String getChargeDate(String email, String idfg){
		
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setOrderid(email);
		orderDomain.setIdfg(idfg);
		
		return orderMapper.selectChargeDate(orderDomain);
	}
	
	/**
	 * 블루멤버십 해지 여부 조회
	 * @param email, idfg
	 * @return 해지여부
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public String getBmCancelyn(String email, String idfg){
		
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setOrderid(email);
		orderDomain.setIdfg(idfg);
		
		return orderMapper.selectBmCancelyn(orderDomain);
	}
	
	/**
	 * 회차 주문
	 * @param userDomain
	 * @param productHistoryDomain
	 * @return
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void productBuy(String email, String idfg, ProductHistoryDomain productHistoryDomain) {
		
		// 상품SEQ, 가격, 사용일자, 보유 블루베리(20150209)
		int prdhistoryseq = productHistoryDomain.getPrdhistoryseq();
		int prdprice = productHistoryDomain.getPrdprice();
		int prdterm = productHistoryDomain.getPrdterm();
		int berryamount = productHistoryDomain.getBerryamount();
		int blueberryamount = productHistoryDomain.getBlueberryamout(); 
		int ownblueberry = productHistoryDomain.getOwnblueberry();
		
		// 사용일자
		String availdt = DateUtil.getDateAfterDay(prdterm);
		if(prdterm == 0) {
			availdt = PremiumUtil.UNLIMITED_TERM;
		}
		
		// 현재시간
		String nowDate = DateUtil.getNowDate(1);
		
		// 1. 구매내역 추가
		BuyDomain buyDomain = new BuyDomain();
		buyDomain.setPrdhistoryseq(prdhistoryseq);
		buyDomain.setBuyfg("order");
		buyDomain.setBuyid(email);
		buyDomain.setIdfg(idfg);
		
		// 구매방식에 따른 가격정보 셋팅
		if(PremiumUtil.BERRY_PURCHASE_PRODUCT.equals(productHistoryDomain.getPurchasefg())){
			buyDomain.setBuyamount(String.valueOf(prdprice));
			buyDomain.setBuyblueamount("0");
		}
		else if(PremiumUtil.BBERRY_PURCHASE_PRODUCT.equals(productHistoryDomain.getPurchasefg())){
			buyDomain.setBuyamount("0");
			buyDomain.setBuyblueamount(String.valueOf(prdprice));
		}
		else if(PremiumUtil.MIX_PURCHASE_PRODUCT.equals(productHistoryDomain.getPurchasefg())){
			buyDomain.setBuyamount(String.valueOf(berryamount));
			buyDomain.setBuyblueamount(String.valueOf(blueberryamount));
		}
		
		//정산 관련 통계데이터 정보 셋팅 - 20150209
		String blueprdname = null;
		if(Integer.parseInt(buyDomain.getBuyblueamount()) > 0){
			
			//유료 블루베리 상품 && 블루멤버십 가입자
			int blueberrycnt = 0;
			BMPrdDomain prd = null;
			BMJoinDomain join = bmService.getActiveJoinForUser(idfg, email);

			if(join != null){
				blueprdname = join.getPrdcode().getCode();
			}
			
			if(join != null){
				prd = prdMapper.selectByPrdcode(new BMPrdDomain(join.getPrdcode()));
			}

			if(prd != null){
				blueberrycnt = prd.getBlueberrycnt();
			}
			
			int validblueberrycnt = blueberrycnt / 2;
			int buyblueamount = Integer.parseInt(buyDomain.getBuyblueamount());
			
			if(ownblueberry <= validblueberrycnt ){
				buyDomain.setValidblueamount(buyblueamount);
				buyDomain.setInvalidblueamount(0);
			}else if(ownblueberry - Integer.parseInt(buyDomain.getBuyblueamount()) <= validblueberrycnt){
				buyDomain.setValidblueamount(buyblueamount - (ownblueberry - validblueberrycnt));
				buyDomain.setInvalidblueamount(ownblueberry - validblueberrycnt);
			}else{
				buyDomain.setValidblueamount(0);
				buyDomain.setInvalidblueamount(buyblueamount);
			}
			
		}else{
			buyDomain.setValidblueamount(0);
			buyDomain.setInvalidblueamount(0);
		}
		
		// 블루베리 정산 데이터 셋팅 - 20150209
		buyDomain.setOwnblueberry(ownblueberry);
		buyDomain.setBlueprdname(blueprdname);
		
		buyDomain.setBuydt(nowDate);
		buyDomain.setRegid(email);
		buyDomain.setRegdt(nowDate);
		buyMapper.insertBuy(buyDomain);
		int buyseq = buyDomain.getBuyseq();
		
		// 2. 주문내역 추가
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setBuyseq(buyseq);
		orderDomain.setUsefg("buy");
		orderDomain.setOrderfg("order");
		orderDomain.setOrderamount(prdprice);
		orderDomain.setOrderid(email);
		orderDomain.setOrderdt(nowDate);
		orderDomain.setIdfg(idfg);
		orderDomain.setRegid(email);
		orderDomain.setRegdt(nowDate);
		
		// 구매 방식에 따른 주문테이블(ow_order, ow_bm_order) 분기처리
		if(PremiumUtil.BERRY_PURCHASE_PRODUCT.equals(productHistoryDomain.getPurchasefg())){
			orderMapper.insertOrder(orderDomain);
		}else if(PremiumUtil.BBERRY_PURCHASE_PRODUCT.equals(productHistoryDomain.getPurchasefg())){
			orderMapper.insertBlueOrder(orderDomain);
		}else if(PremiumUtil.MIX_PURCHASE_PRODUCT.equals(productHistoryDomain.getPurchasefg())){
			
			orderDomain.setOrderamount(berryamount);
			orderMapper.insertOrder(orderDomain);
			
			orderDomain.setOrderamount(blueberryamount);
			orderMapper.insertBlueOrder(orderDomain);
		}
		
		// 3. 이용가능상품
		ProductAvailDomain productAvailDomain = new ProductAvailDomain();
		productAvailDomain.setBuyseq(buyseq);
		productAvailDomain.setPrdhistoryseq(prdhistoryseq);
		productAvailDomain.setBuyid(email);
		productAvailDomain.setAvaildt(availdt);
		productAvailDomain.setIdfg(idfg);
		productAvailDomain.setRegid(email);
		productAvailDomain.setRegdt(nowDate);
		productAvailMapper.insertProductAvail(productAvailDomain);
	}
	
	/**
	 * 회차 주문
	 * @param userDomain
	 * @param productHistoryDomain
	 * @return
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void otProductBuy(String email, String idfg, ProductHistoryDomain productHistoryDomain) {
		
		// 상품SEQ, 가격, 사용일자, 보유 블루베리(20150209)
		int prdhistoryseq 	= productHistoryDomain.getPrdhistoryseq();
		int prdprice 	  	= productHistoryDomain.getPrdprice();
		int prdterm 	  	= productHistoryDomain.getPrdterm();
//		int berryamount 	= productHistoryDomain.getBerryamount();
//		int blueberryamount = productHistoryDomain.getBlueberryamout(); 
		int ownblueberry 	= productHistoryDomain.getOwnblueberry();
		String purchasefg	= productHistoryDomain.getPurchasefg();
		
		// 사용일자
		String availdt = DateUtil.getDateAfterDay(prdterm);
		if(prdterm == 0) {
			availdt = PremiumUtil.UNLIMITED_TERM;
		}
		
		// 현재시간
		String nowDate = DateUtil.getNowDate(1);
		
		// 1. 구매내역 추가
		BuyDomain buyDomain = new BuyDomain();
		buyDomain.setPrdhistoryseq(prdhistoryseq);
		buyDomain.setBuyfg("order");
		buyDomain.setBuyid(email);
		buyDomain.setIdfg(idfg);
		
		boolean isBerryProduct = "Y".equals(productHistoryDomain.getBerryuseyn());
		boolean isBlueProduct  = "Y".equals(productHistoryDomain.getBlueberryuseyn());
		boolean isRaspProduct  = "Y".equals(productHistoryDomain.getRaspberryuseyn());
		
		// 구매방식에 따른 가격정보 셋팅
		if("berry".equals(purchasefg) && isBerryProduct){
			buyDomain.setBuyamount(String.valueOf(prdprice));
			buyDomain.setBuyblueamount("0");
			buyDomain.setBuyraspamount("0");
		}
		else if("blue".equals(purchasefg) && isBlueProduct){
			buyDomain.setBuyamount("0");
			buyDomain.setBuyblueamount(String.valueOf(prdprice));
			buyDomain.setBuyraspamount("0");
		}
		else if("rasp".equals(purchasefg) && isRaspProduct){
			buyDomain.setBuyamount("0");
			buyDomain.setBuyblueamount("0");
			buyDomain.setBuyraspamount(String.valueOf(prdprice));
		}
		
		//정산 관련 통계데이터 정보 셋팅 - 20150209
		String blueprdname = null;
		if(Integer.parseInt(buyDomain.getBuyblueamount()) > 0){
			
			//유료 블루베리 상품 && 블루멤버십 가입자
			int blueberrycnt = 0;
			BMPrdDomain prd = null;
			BMJoinDomain join = bmService.getActiveJoinForUser(idfg, email);

			if(join != null){
				blueprdname = join.getPrdcode().getCode();
			}
			
			if(join != null){
				prd = prdMapper.selectByPrdcode(new BMPrdDomain(join.getPrdcode()));
			}

			if(prd != null){
				blueberrycnt = prd.getBlueberrycnt();
			}
			
			int validblueberrycnt = blueberrycnt / 2;
			int buyblueamount = Integer.parseInt(buyDomain.getBuyblueamount());
			
			if(ownblueberry <= validblueberrycnt ){
				buyDomain.setValidblueamount(buyblueamount);
				buyDomain.setInvalidblueamount(0);
			}else if(ownblueberry - Integer.parseInt(buyDomain.getBuyblueamount()) <= validblueberrycnt){
				buyDomain.setValidblueamount(buyblueamount - (ownblueberry - validblueberrycnt));
				buyDomain.setInvalidblueamount(ownblueberry - validblueberrycnt);
			}else{
				buyDomain.setValidblueamount(0);
				buyDomain.setInvalidblueamount(buyblueamount);
			}
			
		}else{
			buyDomain.setValidblueamount(0);
			buyDomain.setInvalidblueamount(0);
		}
		
		// 블루베리 정산 데이터 셋팅 - 20150209
		buyDomain.setOwnblueberry(ownblueberry);
		buyDomain.setBlueprdname(blueprdname);
		
		buyDomain.setBuydt(nowDate);
		buyDomain.setRegid(email);
		buyDomain.setRegdt(nowDate);
		buyMapper.insertOtBuy(buyDomain);
		int buyseq = buyDomain.getBuyseq();
		
		logger.debug("buyseq = " + buyseq);
		
		// 2. 주문내역 추가
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setBuyseq(buyseq);
		orderDomain.setUsefg("buy");
		orderDomain.setOrderfg("order");
		orderDomain.setOrderamount(prdprice);
		orderDomain.setCtnuserseq(productHistoryDomain.getCtnuserseq());
		orderDomain.setOrderid(email);
		orderDomain.setOrderdt(nowDate);
		orderDomain.setIdfg(idfg);
		orderDomain.setRegid(email);
		orderDomain.setRegdt(nowDate);
		
		logger.debug("purchasefg = " + purchasefg);
		logger.debug("isBerryProduct = " + isBerryProduct);
		logger.debug("isBlueProduct = " + isBlueProduct);
		logger.debug("isRaspProduct = " + isRaspProduct);
		
		// 구매 방식에 따른 주문테이블(ow_order, ow_bm_order) 분기처리
		if("berry".equals(purchasefg) && isBerryProduct){
			orderMapper.insertOrder(orderDomain);
		}else if("blue".equals(purchasefg) && isBlueProduct){
			orderMapper.insertBlueOrder(orderDomain);
		}else if("rasp".equals(purchasefg) && isRaspProduct){
			logger.debug("insertRaspOrder start");
			orderMapper.insertRaspOrder(orderDomain);
			logger.debug("insertRaspOrder end");
		}
	
		logger.debug("insert end");
		
		// 3. 이용가능상품
		ProductAvailDomain productAvailDomain = new ProductAvailDomain();
		productAvailDomain.setBuyseq(buyseq);
		productAvailDomain.setPrdhistoryseq(prdhistoryseq);
		productAvailDomain.setBuyid(email);
		productAvailDomain.setAvaildt(availdt);
		productAvailDomain.setIdfg(idfg);
		productAvailDomain.setRegid(email);
		productAvailDomain.setRegdt(nowDate);
		productAvailMapper.insertProductAvail(productAvailDomain);
	}
	
	/**
	 * 블루멤버십 아이템 무료 제공
	 * 
	 * @param idfg       : 아이디 구분
	 * @param userid     : 유저아이디
	 * @param prdseqList : 상품순번 목록
	 * @return List<ProductAvailDomain>
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public List<ProductAvailDomain> provideMembershipOptionProduct( String idfg, String userid, List<Integer> prdseqList ){
		
		if(prdseqList == null)
		{
			logger.info( "provideMembershipOptionProduct prdseqList is null" );
			return null;
		}

		if( prdseqList.size() > 0 )
			logger.info( String.format( "provideMembershipOptionProduct %s %s %d %d", idfg, userid, prdseqList.size(), prdseqList.get( 0 )) );
		
		//return value
		List<ProductAvailDomain> result = new ArrayList<ProductAvailDomain>();
		
		for(Integer prdseq : prdseqList)
		{
			if(prdseq == null || prdseq < 1) continue;
			
			//상품정보 조회
			ProductHistoryDomain product = productHistoryMapper.selectFreeProductDetailBySeq(prdseq);
			
			//상품정보 없음
			if(product == null) continue;
			
			ProductAvailDomain productAvailDomain = new ProductAvailDomain();
			productAvailDomain.setBuyid(userid);
			productAvailDomain.setPrdfg(product.getPrdfg());
			productAvailDomain.setPrdseq(product.getPrdseq());
			
			//구매 내역 조회
			productAvailDomain = productAvailMapper.selectProductAvail(productAvailDomain);
			
			//구매 내역이 없을시에 구매처리
			if(productAvailDomain == null)
			{
				// 상품SEQ
				int prdhistoryseq = product.getPrdhistoryseq();
				
				// 사용일자(무제한 처리)
				String availdt = PremiumUtil.UNLIMITED_TERM;
				
				// 현재시간
				String nowDate = DateUtil.getNowDate(1);
				
				// 1. 구매내역 추가
				BuyDomain buyDomain = new BuyDomain();
				buyDomain.setPrdhistoryseq(prdhistoryseq);
				buyDomain.setBuyfg("order");
				buyDomain.setBuyid(userid);
				buyDomain.setIdfg(idfg);
				buyDomain.setBuyamount("0");
				buyDomain.setBuyblueamount("0");
				buyDomain.setBuydt(nowDate);
				buyDomain.setRegid(userid);
				buyDomain.setRegdt(nowDate);
				
				buyMapper.insertBuy(buyDomain);
				int buyseq = buyDomain.getBuyseq();
				
				// 2. 주문내역 추가
				OrderDomain orderDomain = new OrderDomain();
				orderDomain.setBuyseq(buyseq);
				orderDomain.setUsefg("buy");
				orderDomain.setOrderfg("order");
				orderDomain.setOrderamount(0);
				orderDomain.setOrderid(userid);
				orderDomain.setOrderdt(nowDate);
				orderDomain.setIdfg(idfg);
				orderDomain.setRegid(userid);
				orderDomain.setRegdt(nowDate);
				
				orderMapper.insertOrder(orderDomain);

				// 3. 이용가능상품
				ProductAvailDomain productAvail = new ProductAvailDomain();
				productAvail.setBuyseq(buyseq);
				productAvail.setPrdhistoryseq(prdhistoryseq);
				productAvail.setBuyid(userid);
				productAvail.setAvaildt(availdt);
				productAvail.setIdfg(idfg);
				productAvail.setRegid(userid);
				productAvail.setRegdt(nowDate);
				
				productAvailMapper.insertProductAvail(productAvail);
				result.add(productAvail);
			}
			//구매 내역이 있을 경우 
			else
			{
				result.add(productAvailDomain);
			}
		}
		
		return result != null && result.size() > 0 ? result : null;
	}
	
	/**
	 * 블루멤버십 무료 제공 아이템 취소 처리
	 * 
	 * @param idfg       : 아이디 구분
	 * @param userid     : 유저아이디
	 * @param prdseqList : 상품순번 목록 
	 * @return List<ProductAvailDomain>
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public List<ProductAvailDomain> collectMembershipOptionProduct( String idfg, String userid, List<Integer> prdseqList ){

		if(prdseqList == null) return null;
		
		//return value
		List<ProductAvailDomain> result = new ArrayList<ProductAvailDomain>();
		
		for(Integer prdseq : prdseqList)
		{
			if(prdseq == null || prdseq < 1) continue;
			
			ProductAvailDomain productAvailDomain = new ProductAvailDomain();
			productAvailDomain.setBuyid(userid);
			productAvailDomain.setIdfg(idfg);
			productAvailDomain.setPrdseq(prdseq);
			
			//구매 내역 조회(해당 상품 순번을 가진 구매된 모든 이력들을 조회 후 취소처리한다.)
			List<ProductAvailDomain> availList = productAvailMapper.selectProductAvailByPrdSeq(productAvailDomain);
			
			//구매 내역이 있을 시에 취소 처리
			if(availList != null && availList.size() > 0)
			{
				for(ProductAvailDomain avail : availList)
				{
					int buyseq = buyMapper.selectBuyseq(avail);
					
					if(buyseq > 0){
						
						// 1. 이용가능 상품 취소처리
						productAvailMapper.deleteProductAvail(buyseq);
						
						// 2. 주문 내역 취소처리
						orderMapper.deleteOrder(buyseq);
						
						// 3. 구매내역 취소처리
						buyMapper.deleteBuy(buyseq);
						
						result.add(avail);
					}
				}
			}
		}
		
		return result != null && result.size() > 0 ? result : null;
	}
	
	/**
	 * 베리 충전 리스트 갯수 조회 
	 * @param orderDomain 
	 * @return
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int orderListCnt(OrderDomain orderDomain)
	{
		return orderMapper.orderSelectListCnt(orderDomain);
	}
	
	/**
	 * 베리 충전 리스트 조회 
	 * @param orderDomain
	 * @return List<OrderDomain> 베리 충전 목록 정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public List<OrderDomain> berrychargeList(OrderDomain orderDomain)
	{
		return orderMapper.berrychargeSelectList(orderDomain);
	}
	
	/**
	 * 구매 리스트 갯수 조회 
	 * @param buyDomain 
	 * @return
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int buyListCnt(BuyDomain buyDomain)
	{
		return buyMapper.buySelectListCnt(buyDomain);
	}
	
	/**
	 * 구매 리스트 조회 
	 * @param orderDomain
	 * @return List<OrderDomain> 베리 충전 목록 정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public List<BuyDomain> berryuseList(BuyDomain buyDomain)
	{
		return buyMapper.berryuseSelectList(buyDomain);
	}
}
