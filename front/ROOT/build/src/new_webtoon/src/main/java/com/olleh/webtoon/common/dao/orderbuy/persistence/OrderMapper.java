/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - OrderMapper.java
 * 
 * DESCRIPTION
 * - 주문내역 Mapper
 *****************************************************************************/

package com.olleh.webtoon.common.dao.orderbuy.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.orderbuy.domain.OrderDomain;

public interface OrderMapper {

	/**
	 * 잔여베리 조회
	 * @param 주문자ID
	 * @return 미자막 주문내역
	 */
	public Integer selectOrderSum(OrderDomain orderDomain);
	
	/**
	 * 잔여블루베리 조회
	 * @param 주문자ID
	 * @return 미자막 주문내역
	 */
	public Integer selectBlueOrderSum(OrderDomain orderDomain);
	
	/**
	 * 잔여라즈베리 조회
	 * @param ctn 사용자번호
	 * @return 미자막 주문내역
	 */
	public Integer selectRaspOrderSum(OrderDomain orderDomain);
	
	
	
	/**
	 * 블루베리 충전일 조회
	 * @param 주문자ID
	 * @return 블루베리 충전일(YY년-MM월-DD일)
	 */
	public String selectChargeDate(OrderDomain orderDomain);
	
	/**
	 * 블루멤버십 해지 여부 조회
	 * @param 주문자ID
	 * @return 해지여부
	 */
	public String selectBmCancelyn(OrderDomain orderDomain);
	
	/**
	 * 베리 충전/구매 추가
	 * @param orderDomain
	 * @return
	 */
	public int insertOrder(OrderDomain orderDomain);
	
	/**
	 * 블루 베리 충전/구매 추가
	 * @param orderDomain
	 * @return
	 */
	public int insertBlueOrder(OrderDomain orderDomain);
	
	/**
	 * 라즈 베리 충전/구매 추가
	 * @param orderDomain
	 * @return
	 */
	public int insertRaspOrder(OrderDomain orderDomain);
	

	/**
	 * 베리 충전 리스트 갯수 확인
	 * @param orderDomain
	 * @return 
	 */
	public int orderSelectListCnt(OrderDomain orderDomain);
	
	
	/**
	 * 베리 충전 리스트 조회
	 * @param orderDomain
	 * @return 
	 */
	public List<OrderDomain> berrychargeSelectList(OrderDomain orderDomain);
	
	/**
	 * 주문내역 취소처리(삭제)
	 * @param buyseq
	 * @return 
	 */
	public void deleteOrder(Integer buyseq);
}
