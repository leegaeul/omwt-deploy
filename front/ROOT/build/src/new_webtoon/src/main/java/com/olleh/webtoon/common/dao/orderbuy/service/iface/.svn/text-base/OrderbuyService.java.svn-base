/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - OrderbuyService.java
 * 
 * DESCRIPTION
 * -  주무/구매 내역 Service interface class.
 *****************************************************************************/

package com.olleh.webtoon.common.dao.orderbuy.service.iface;

import java.util.List;

import com.olleh.webtoon.common.dao.orderbuy.domain.BuyDomain;
import com.olleh.webtoon.common.dao.orderbuy.domain.OrderDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductAvailDomain;
import com.olleh.webtoon.common.dao.premium.domain.ProductHistoryDomain;

public interface OrderbuyService 
{

	/**
	 * 사용가능한 베리조회
	 * @param email
	 * @return int berry: 사용가능한 베리
	 */
	public int berryAvail(String email, String idfg);
	
	/**
	 * 사용가능한 블루베리조회
	 * @param email
	 * @return int blueberry: 사용가능한 블루베리
	 */
	public int blueBerryAvail(String email, String idfg);
	
	/**
	 * 사용가능한 블루베리조회
	 * @param email
	 * @return int blueberry: 사용가능한 블루베리
	 */
	public int raspBerryAvail(String email, String idfg);
	
	/**
	 * 사용가능한 블루베리조회(DecimalFormat)
	 * @param email
	 * @return String berry: 사용가능한 블루베리
	 */
	public String strBerryAvail(String email, String idfg);
	
	/**
	 * 사용가능한 블루베리조회(DecimalFormat)
	 * @param email
	 * @return String blueberry: 사용가능한 블루베리
	 */
	public String strBlueBerryAvail(String email, String idfg);
	
	/**
	 * 사용가능한 라즈베리조회(DecimalFormat)
	 * @param email
	 * @return String raspberry: 사용가능한 블루베리
	 */
	public String strRaspBerryAvail(String email, String idfg);
	
	/**
	 * 블루베리 충전일 조회
	 * @param email, idfg
	 * @return 블루베리 충전일(YY년-MM월-DD일)
	 */
	public String getChargeDate(String email, String idfg);
	
	/**
	 * 블루멤버십 해지 여부 조회
	 * @param email, idfg
	 * @return 해지여부
	 */
	public String getBmCancelyn(String email, String idfg);
	
	/**
	 * 회차 주문
	 * @param userDomain
	 * @param productHistoryDomain
	 * @return
	 */
	public void productBuy(String email, String idfg, ProductHistoryDomain productHistoryDomain);
	
	/**
	 * 회차 주문
	 * @param userDomain
	 * @param productHistoryDomain
	 * @return
	 */
	public void otProductBuy(String email, String idfg, ProductHistoryDomain productHistoryDomain);
	
	/**
	 * 베리 충전 리스트 갯수 조회 
	 * @param orderDomain 
	 * @return
	 */
	public int orderListCnt(OrderDomain orderDomain);
	
	/**
	 * 베리 충전 리스트 조회 
	 * @param orderDomain
	 * @return List<OrderDomain> 베리 충전 목록 정보
	 */
	public List<OrderDomain> berrychargeList(OrderDomain orderDomain);
	
	/**
	 * 구매 리스트 갯수 조회 
	 * @param buyDomain 
	 * @return
	 */
	public int buyListCnt(BuyDomain buyDomain);
	
	/**
	 * 구매 리스트 조회 
	 * @param buyDomain
	 * @return List<buyDomain> 베리 충전 목록 정보
	 */
	public List<BuyDomain> berryuseList(BuyDomain buyDomain);
	
	/**
	 * 블루멤버십 아이템 무료 제공
	 * 
	 * @param idfg       : 아이디 구분
	 * @param userid     : 유저아이디
	 * @param prdseqList : 상품순번 목록
	 * @return List<ProductAvailDomain>
	 */
	public List<ProductAvailDomain> provideMembershipOptionProduct( String idfg, String userid, List<Integer> prdseqList );
	
	/**
	 * 블루멤버십 무료 제공 아이템 취소 처리
	 * 
	 * @param idfg       : 아이디 구분
	 * @param userid     : 유저아이디
	 * @param prdseqList : 상품순번 목록 
	 * @return List<ProductAvailDomain>
	 */
	public List<ProductAvailDomain> collectMembershipOptionProduct( String idfg, String userid, List<Integer> prdseqList );
}
