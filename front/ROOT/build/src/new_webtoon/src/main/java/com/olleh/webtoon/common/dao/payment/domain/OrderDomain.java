/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : PaymentDomain.java
 * DESCRIPTION    : 결제
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-23      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.payment.domain;

public class OrderDomain {
	private int orderseq;
	private int paymentseq;
	private int buyseq;
	private String orderno;
	private String usefg;
	private String orderfg;
	private int orderamount;
	private String orderid;
	private String orderdt;
	private String idfg;
	private String regid;
	private String regdt;
	
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
}