package com.olleh.webtoon.common.dao.bluemembership.domain;

public class MobiBillkeyRegisterResultForm
{
	public String	Resultcd;		// [ 4byte 고정] 결과코드
	public String	Resultmsg;		// [ 100byte 이하] 결과메세지
	public String	AutoBillKey;	// [ 15byte 이하] 자동결제 최초등록키
	public String	CASH_GB;		// [ 2byte 고정] 결제수단(MC)
	public String	Commid;		// [ 3byte 고정] 이통사
	public String	Mobilid;		// [ 15byte 이하] 모빌리언스 거래번호
	public String	Mrchid;		// [ 8byte 고정] 상점ID
	
	public String	MSTR;			// [2000byte 이하] 가맹점 전달 콜백변수
	public String	No;			// [ 11byte 이하] 폰번호
	public String	Payeremail;	// [ 30byte 이하] 결제자 이메일
	public String	Prdtnm;		// [ 50byte 이하] 상품명
	public String	Prdtprice;		// [ 10byte 이하] 상품가격
	public String	Signdate;		// [ 14byte 이하] 결제일자
	public String	Svcid;			// [ 12byte 고정] 서비스ID
	public String	Tradeid;		// [ 40byte 이하] 상점거래번호
	public String	Userid;		// [ 20byte 이하] 사용자ID
	public String	USERKEY;		// [ 15byte 이하] 휴대폰정보(이통사, 휴대폰번호, 주민번호) 대체용 USERKEY


	public void setResultcd( String resultcd )
	{
		Resultcd = resultcd;
	}


	public void setResultmsg( String resultmsg )
	{
		Resultmsg = resultmsg;
	}


	public void setAutoBillKey( String autoBillKey )
	{
		AutoBillKey = autoBillKey;
	}


	public void setCASH_GB( String cASH_GB )
	{
		CASH_GB = cASH_GB;
	}


	public void setCommid( String commid )
	{
		Commid = commid;
	}


	public void setMobilid( String mobilid )
	{
		Mobilid = mobilid;
	}


	public void setMrchid( String mrchid )
	{
		Mrchid = mrchid;
	}


	public void setMSTR( String mSTR )
	{
		MSTR = mSTR;
	}


	public void setNo( String no )
	{
		No = no;
	}


	public void setPayeremail( String payeremail )
	{
		Payeremail = payeremail;
	}


	public void setPrdtnm( String prdtnm )
	{
		Prdtnm = prdtnm;
	}


	public void setPrdtprice( String prdtprice )
	{
		Prdtprice = prdtprice;
	}


	public void setSigndate( String signdate )
	{
		Signdate = signdate;
	}


	public void setSvcid( String svcid )
	{
		Svcid = svcid;
	}


	public void setTradeid( String tradeid )
	{
		Tradeid = tradeid;
	}


	public void setUserid( String userid )
	{
		Userid = userid;
	}


	public void setUSERKEY( String uSERKEY )
	{
		USERKEY = uSERKEY;
	}
}
