package com.olleh.webtoon.common.dao.payment.domain;

import java.io.Serializable;

/**
 * 결제 실패 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class PaymentFailDomain implements Serializable
{

	/** serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	private Integer				failseq;
	
	/** 아이디 */
	private String				regid;
	
	/** 아이디 구분 */
	private String				idfg;
	
	/** 서비스구분 메시지 */
	private String				servicefg;
	
	/** 오류 코드 */
	private String				errorcode;

	/** 오류 메시지 */
	private String				errormsg;

	/** 등록 일자 */
	private String				regdt;

	public Integer getFailseq() {
		return failseq;
	}

	public void setFailseq(Integer failseq) {
		this.failseq = failseq;
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

	public String getIdfg() {
		return idfg;
	}

	public void setIdfg(String idfg) {
		this.idfg = idfg;
	}	

	public String getServicefg() {
		return servicefg;
	}

	public void setServicefg(String servicefg) {
		this.servicefg = servicefg;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getRegdt() {
		return regdt;
	}

	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	
}
