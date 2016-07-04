package com.olleh.webtoon.common.dao.bluemembership.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.inicis.inipay4.util.INIdata;
import com.olleh.webtoon.pc.controller.PCBlueMembershipController;

public class INIBillKeyRegisterResultForm
{
	private static Log	logger	= LogFactory.getLog( PCBlueMembershipController.class );

	public String		resultcode;
	public String		resultmsg;
	public String		orderid;
	public String		billkey;
	public String		mid;
	public String		tid;
	public String		authkey;
	public String		cardcd;
	public String		p_noti;
	public String		merchantreserved;


	public INIBillKeyRegisterResultForm()
	{
		// TODO Auto-generated constructor stub
	}


	public INIBillKeyRegisterResultForm( INIdata data ) throws Exception
	{
		// logger.info("ResultMsg==========================");
		// logger.info( data.getData( "ResultMsg" ) );
		// logger.info( URLDecoder.decode( data.getData( "ResultMsg" ), "euc-kr" ) );
		// logger.info( URLDecoder.decode( data.getData( "ResultMsg" ), "utf-8" ) );
		// logger.info( StringUtil.euckrToUtf8( data.getData( "ResultMsg" )) );
		// logger.info( StringUtil.utf8ToEuckr( data.getData( "ResultMsg" )) );
		//
		setResultcode( data.getData( "ResultCode" ) );
		setResultmsg( data.getData( "ResultMsg" ) );
		setBillkey( data.getData( "BillKey" ) );
		setCardcd( data.getData( "CardResultCode" ) );
		setBillkey( data.getData( "BillKey" ) );
		setTid( data.getData( "tid" ) );
	}


	public void setResultcode( String resultcode )
	{
		this.resultcode = resultcode;
	}


	public void setResultmsg( String resultmsg )
	{
		this.resultmsg = resultmsg;
	}


	public void setOrderid( String orderid )
	{
		this.orderid = orderid;
	}


	public void setBillkey( String billkey )
	{
		this.billkey = billkey;
	}


	public void setMid( String mid )
	{
		this.mid = mid;
	}


	public void setTid( String tid )
	{
		this.tid = tid;
	}


	public void setAuthkey( String authkey )
	{
		this.authkey = authkey;
	}


	public void setCardcd( String cardcd )
	{
		this.cardcd = cardcd;
	}


	public void setP_noti( String p_noti )
	{
		this.p_noti = p_noti;
	}


	public void setMerchantreserved( String merchantreserved )
	{
		this.merchantreserved = merchantreserved;
	}

}
