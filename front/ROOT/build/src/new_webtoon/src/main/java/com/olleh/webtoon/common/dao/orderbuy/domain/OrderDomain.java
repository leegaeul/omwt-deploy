package com.olleh.webtoon.common.dao.orderbuy.domain;

import java.text.NumberFormat;

// 주문내역 (ow_order)
public class OrderDomain {

	private int orderseq;		// 주문순번
	private int paymentseq;		// 결제순번
	private int buyseq;			// 구매순번
	private int payamount;		// 결제금액 (단위:원)
	private String paymethod;	// 결제수단 (DP:자사 휴대폰 폰빌, HP:휴대폰 소액결제, CD:신용카드, GP:문화상품권, NP:0원결제, P1:별포인트, OP:올레마켓포인트, OC:올레마켓쿠폰)
	private String paymethodnm;
	private String paymentdt;	// 결제일시 (YYYYMMDDHHMISS)
	private String orderno;		// 주문번호 (결제시생성된주문번호)
	private String usefg;		// 사용구분 (charge:충전, buy:구매)
	private String orderfg;		// 주문구분 (order:주문, refund:환불, cancel:취소)
	private String freeyn;		// 무료충전 여부	
	private int orderamount;	// 주문금액 (단위:베리)
	private String orderid;		// 주문자ID
	private String orderdt;		// 주문일시 (YYYYMMDDHHMISS) 
	private String idfg;		// 주문자ID구분 (open:오픈ID, olleh:올레ID)
	private String regid;		// 등록자ID
	private String regdt;		// 등록일시 (YYYYMMDDHHMISS)
	private String sort;		// 정렬기준 
	private int startRowNo;  	// 해당페이지 시작 Row 번호(페이지크기가 10일 경우 1페이지는 0, 2페이지는 10, 3페이지는 20)
	private int pageSize;    	// 페이지크기
	private String purchasefg;  // 구매방식 구분(berry:일반베리, blue:블루베리)
	private String ctnuserseq;  // ctn 사용자 번호
	
	public int getOrderseq() {
		return orderseq;
	}
	public void setOrderseq(int orderseq) {
		this.orderseq = orderseq;
	}
	public int getPaymentseq() {
		return paymentseq;
	}
	public void setPaymentseq(int paymentseq) {
		this.paymentseq = paymentseq;
	}
	public int getBuyseq() {
		return buyseq;
	}
	public void setBuyseq(int buyseq) {
		this.buyseq = buyseq;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getUsefg() {
		return usefg;
	}
	public void setUsefg(String usefg) {
		this.usefg = usefg;
	}
	public String getOrderfg() {
		return orderfg;
	}
	public void setOrderfg(String orderfg) {
		this.orderfg = orderfg;
	}
	public int getOrderamount() {
		return orderamount;
	}
	
	public String getStrOrderamount() 
	{
		NumberFormat nf = NumberFormat.getInstance();		  
		return nf.format(orderamount);
	}
	
	public void setOrderamount(int orderamount) {
		this.orderamount = orderamount;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderdt() {
		return orderdt;
	}
	public void setOrderdt(String orderdt) {
		this.orderdt = orderdt;
	}
	public String getIdfg() {
		return idfg;
	}
	public void setIdfg(String idfg) {
		this.idfg = idfg;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public int getStartRowNo() {
		return startRowNo;
	}
	public void setStartRowNo(int startRowNo) {
		this.startRowNo = startRowNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPayamount() {
		return payamount;
	}
	
	public String getStrPayamount() 
	{
		NumberFormat nf = NumberFormat.getInstance();		  
		return nf.format(payamount);
	}
	
	public void setPayamount(int payamount) {
		this.payamount = payamount;
	}
	public String getPaymethod() {			
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getPaymethodnm() {
		if( paymethodnm == null )
			return paymethodnm = "";
		return paymethodnm;
	}
	public void setPaymethodnm(String paymethodnm) {
		this.paymethodnm = paymethodnm;
	}
	public String getPaymentdt() {
		return paymentdt;
	}
	public void setPaymentdt(String paymentdt) {
		this.paymentdt = paymentdt;
	}
	public String getFreeyn() {
		return freeyn;
	}
	public void setFreeyn(String freeyn) {
		this.freeyn = freeyn;
	}
	public String getPurchasefg() {
		return purchasefg;
	}
	public void setPurchasefg(String purchasefg) {
		this.purchasefg = purchasefg;
	}
	public String getCtnuserseq() {
		return ctnuserseq;
	}
	public void setCtnuserseq(String ctnuserseq) {
		this.ctnuserseq = ctnuserseq;
	}	
}
