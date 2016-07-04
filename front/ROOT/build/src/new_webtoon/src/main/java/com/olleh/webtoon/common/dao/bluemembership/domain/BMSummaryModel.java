package com.olleh.webtoon.common.dao.bluemembership.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope( "request" )
public class BMSummaryModel
{
	private BMJoinDomain		activeJoin;
	private BMPaymentDomain		lastPayment;
	private BMPrdDomain			usingPrd;
	private BMJoinTermDomain	currentTerm;
	private Integer				ownBlueBerryCount;


	public BMJoinDomain getActiveJoin()
	{
		return activeJoin;
	}


	public void setActiveJoin( BMJoinDomain activeJoin )
	{
		this.activeJoin = activeJoin;
	}


	public BMPaymentDomain getLastPayment()
	{
		return lastPayment;
	}


	public void setLastPayment( BMPaymentDomain lastPayment )
	{
		this.lastPayment = lastPayment;
	}


	public BMPrdDomain getUsingPrd()
	{
		return usingPrd;
	}


	public void setUsingPrd( BMPrdDomain usingPrd )
	{
		this.usingPrd = usingPrd;
	}


	public BMJoinTermDomain getCurrentTerm()
	{
		return currentTerm;
	}


	public void setCurrentTerm( BMJoinTermDomain currentTerm )
	{
		this.currentTerm = currentTerm;
	}


	public Integer getOwnBlueBerryCount()
	{
		return ownBlueBerryCount;
	}


	public void setOwnBlueBerryCount( Integer ownBlueBerryCount )
	{
		this.ownBlueBerryCount = ownBlueBerryCount;
	}

}
