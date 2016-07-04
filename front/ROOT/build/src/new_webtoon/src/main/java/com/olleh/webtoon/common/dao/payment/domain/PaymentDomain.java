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

public class PaymentDomain {
	private int paymentseq;
	private int orgpaymentseq;
	private int webtoonseq;
	private int timesseq;
	private String paymentstep;
	private String freeyn;
	private String paymentfg;
	private String resultcode;
	private String resultmsg;
	private String opcode;
	private String orderno;
	private String payid;
	private String payno;
	private String sessionid;
	private String paymethod;
	private String paymethodnm;
	private int payamount;
	private int orderamount;
	private String payname;
	private String kttype;
	private String displayversion;
	private String clienttype;
	private String paymentid;
	private String paymentdt;
	private String idfg;
	private String regid;
	private String regdt;
	
	private String custname;
	private String custphone;
	private String custemail;
	private String userid;
	private String subscrid;
	private String subscridtype;
	private String goodsid;
	
	private String ktyn;
	
	private String partialyn;
	
	public int getPaymentseq() {
		return paymentseq;
	}
	public void setPaymentseq(int paymentseq) {
		this.paymentseq = paymentseq;
	}
	public int getOrgpaymentseq() {
		return orgpaymentseq;
	}
	public void setOrgpaymentseq(int orgpaymentseq) {
		this.orgpaymentseq = orgpaymentseq;
	}
	public int getWebtoonseq() {
		return webtoonseq;
	}
	public void setWebtoonseq(int webtoonseq) {
		this.webtoonseq = webtoonseq;
	}
	public int getTimesseq() {
		return timesseq;
	}
	public void setTimesseq(int timesseq) {
		this.timesseq = timesseq;
	}
	public String getPaymentstep() {
		return paymentstep;
	}
	public void setPaymentstep(String paymentstep) {
		this.paymentstep = paymentstep;
	}
	public String getFreeyn() {
		return freeyn;
	}
	public void setFreeyn(String freeyn) {
		this.freeyn = freeyn;
	}
	public String getPaymentfg() {
		return paymentfg;
	}
	public void setPaymentfg(String paymentfg) {
		this.paymentfg = paymentfg;
	}
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getResultmsg() {
		return resultmsg;
	}
	public void setResultmsg(String resultmsg) {
		this.resultmsg = resultmsg;
	}
	public String getOpcode() {
		return opcode;
	}
	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	public String getPayno() {
		return payno;
	}
	public void setPayno(String payno) {
		this.payno = payno;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getPaymethodnm() {
		return paymethodnm;
	}
	public void setPaymethodnm(String paymethodnm) {
		this.paymethodnm = paymethodnm;
	}
	public int getPayamount() {
		return payamount;
	}
	public void setPayamount(int payamount) {
		this.payamount = payamount;
	}
	public int getOrderamount() {
		return orderamount;
	}
	public void setOrderamount(int orderamount) {
		this.orderamount = orderamount;
	}
	public String getPayname() {
		return payname;
	}
	public void setPayname(String payname) {
		this.payname = payname;
	}
	public String getKttype() {
		return kttype;
	}
	public void setKttype(String kttype) {
		this.kttype = kttype;
	}
	public String getDisplayversion() {
		return displayversion;
	}
	public void setDisplayversion(String displayversion) {
		this.displayversion = displayversion;
	}
	public String getClienttype() {
		return clienttype;
	}
	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}
	public String getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	public String getPaymentdt() {
		return paymentdt;
	}
	public void setPaymentdt(String paymentdt) {
		this.paymentdt = paymentdt;
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
	public String getIdfg() {
		return idfg;
	}
	public void setIdfg(String idfg) {
		this.idfg = idfg;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getCustphone() {
		return custphone;
	}
	public void setCustphone(String custphone) {
		this.custphone = custphone;
	}
	public String getCustemail() {
		return custemail;
	}
	public void setCustemail(String custemail) {
		this.custemail = custemail;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSubscrid() {
		return subscrid;
	}
	public void setSubscrid(String subscrid) {
		this.subscrid = subscrid;
	}
	public String getSubscridtype() {
		return subscridtype;
	}
	public void setSubscridtype(String subscridtype) {
		this.subscridtype = subscridtype;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getKtyn() {
		return ktyn;
	}
	public void setKtyn(String ktyn) {
		this.ktyn = ktyn;
	}
	public String getPartialyn() {
		return partialyn;
	}
	public void setPartialyn(String partialyn) {
		this.partialyn = partialyn;
	}
}