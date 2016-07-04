package com.olleh.webtoon.common.dao.bluemembership.domain;

public class MobiBillkeyRegisterForm
{
	/*****************************************************************************************
	 * - 필수 입력 항목
	 *****************************************************************************************/
	public String	CASH_GB				= "MC";		// [ 2byte 고정] 결제수단구분. "MC" 고정값. 수정불가!
	public String	MC_SVCID			= "";	// [ 12byte 고정] 모빌리언스에서 부여한 서비스ID (12byte 숫자 형식)
	public String	Prdtprice			= "";	// [ 10byte 이하] 결제요청금액 (암호화 사용 시 암호화 대상)
	public String	PAY_MODE			= "";	// [ 2byte 고정] 연동시 테스트/실결제 구분 (00: 테스트결제-비과금, 10: 실거래결제-과금)
	public String	Prdtnm				= "";	// [ 50byte 이하] 상품명
	public String	Siteurl				= "";	// [ 20byte 이하] 가맹점도메인 (예: www.mcash.co.kr)

	public String	Tradeid				= "";	// [4byte 이상, 40byte 이하] 가맹점거래번호. 결제 요청 시 마다 unique한 값을 세팅해야 함.
	public String	CALL_TYPE			= "";
	// 해당 샘플에는 테스트를 위해 {가맹점 서비스ID + 요청일시} 형식으로 세팅하였음.

	/*****************************************************************************************
	 * - 디자인 관련 필수항목
	 *****************************************************************************************/
	public String	LOGO_YN				= "N";	// [ 1byte 고정] 가맹점 로고 사용 여부 (N: 모빌리언스 로고-default, Y: 가맹점 로고 (사전에 모빌리언스에 가맹점 로고 이미지를 등록해야함))

	/*****************************************************************************************
	 * - 선택 입력 항목
	 *****************************************************************************************/
	public String	MC_AUTHPAY			= "";	// [ 1byte 고정] 하이브리드 방식 사용시 "Y" 로 설정 (휴대폰 SMS인증 후 일반 소켓모듈 결제 연동시 사용) (N: 미사용-default, Y: 사용)
	public String	MC_AUTOPAY			= "Y";	// [ 1byte 고정] 자동결제를 위한 최초 일반결제 시 "Y" 세팅. 결제 완료 후 휴대폰정보 대체용 USERKEY 발급 및 자동결제용 AutoBillKey 발급 (N: 미사용-default, Y: 사용)
	public String	MC_PARTPAY			= "";	// [ 1byte 고정] 부분취소를 위한 일반결제 시 "Y" 세팅. 결제 완료 후 자동결제 USERKEY 발급 (N: 미사용-default, Y: 사용)
	public String	MC_No				= "";	// [ 11byte 이하] 사용자 폰번호 (결제창 호출시 세팅할 폰번호)
	public String	MC_FIXNO			= "";	// [ 1byte 고정] 사용자 폰번호 수정불가 여부(N: 수정가능-default, Y: 수정불가)
	public String	MC_DEFAULTCOMMID	= "";	// [ 3byte 고정] 통신사 기본 선택 값. SKT, KTF, LGT 3개의 값 중 원하는 통신사 세팅 시 해당 통신사가 미리 선택되어짐.
	public String	MC_FIXCOMMID		= "";	// [ 1byte 고정] 통신사 고정 선택 값. SKT, KTF, LGT 3개의 값 중 원하는 통신사 세팅 시 해당 통신사만 결제창에 보여짐.
	public String	Payeremail			= "";	// [ 30byte 이하] 결제자 e-mail
	public String	Userid				= "";	// [ 20byte 이하] 가맹점 결제자ID
	public String	Item				= "";	// [ 8byte 이하] 아이템코드. 미사용 시 반드시 공백으로 세팅.
	public String	Prdtcd				= "";	// [ 40byte 이하] 상품코드. 자동결제인 경우 상품코드별 SMS문구를 별도 세팅할 때 사용하며 사전에 모빌리언스에 등록이 필요함.
	public String	MC_Cpcode			= "";	// [ 20byte 이하] 리셀러하위상점key. 리셀러 업체인 경우에만 세팅.
	public String	Notiemail			= "";	// [ 30byte 이하] 알림 e-mail: 결제 완료 후 당사와 가맹점간의 Noti 연동이 실패한 경우 알람 메일을 받을 가맹점 담당자 이메일주소
	public String	Okurl				= "";
	public String	Notiurl				= "";	// [ 128byte 이하] 결제 완료 후 가맹점 측 결제 처리를 담당하는 페이지. System back단으로 호출이 되며 사용자에게는 보여지지 않는다.
	public String	Failurl				= "";	// [ 128byte 이하] 결제 실패 시 사용자에게 보여질 가맹점 측 실패 페이지. 결제처리에 대한 실패처리 안내를 가맹점에서 제어해야 할 경우만 사용.
	public String	Closeurl			= "";
	// iframe 호출 시 필수! (예: http://www.mcash.co.kr/failurl.jsp)
	public String	MSTR				= "";	// [2000byte 이하] 가맹점 콜백 변수. 가맹점에서 추가적으로 파라미터가 필요한 경우 사용하며 &, % 는 사용불가 (예: MSTR="a=1|b=2|c=3")

	/*****************************************************************************************
	 * - 오픈마켓의 경우 아래의 정보를 입력해야 합니다.
	 * 장바구니 결제의 경우 대표 판매자 외 n명, 대표 판매자 연락처를 입력하세요.
	 * 예) Sellernm = "홍길동외 2명";
	 * Sellertel = "0212345678";
	 *****************************************************************************************/
	public String	Sellernm			= "";	// [ 50byte 이하] 실판매자 이름 (오픈마켓의 경우 실 판매자 정보 필수)
	public String	Sellertel			= "";	// [ 15byte 이하] 실판매자 전화번호 (오픈마켓의 경우 실 판매자 정보 필수)

	/*****************************************************************************************
	 * - 디자인 관련 선택항목 (향후 변경될 수 있습니다.)
	 *****************************************************************************************/
	public String	IFRAME_NAME			= "";	// [ 1byte 고정] 결제창을 iframe으로 호출 할 경우 iframe 명칭 세팅
	public String	INFOAREA_YN			= "";	// [ 1byte 고정] 결제창 안내문 표시여부 (Y: 표시-default, N: 미표시)
	public String	FOOTER_YN			= "";	// [ 1byte 고정] 결제창 하단 안내 표시여부 (Y: 표시-default, N: 미표시)
	public String	HEIGHT				= "";	// [ 4byte 이하] 결제창 높이 (px단위: iframe 등 사용시 결제창 높이 조절, 팝업창 등 호출시 "" 로 세팅)
	public String	PRDT_HIDDEN			= "";	// [ 1byte 고정] iframe 사용시 상품명 숨김 여부 (가맹점 디자인 결제창으로 결제 입력 사항만 iframe에서 사용시)
	public String	EMAIL_HIDDEN		= "";	// [ 1byte 고정] 결제자 e-mail 입력창 숨김 여부 (N: 표시-default, Y: 미표시)
	public String	CONTRACT_HIDDEN		= "";	// [ 1byte 고정] 이용약관 숨김 여부 (Y: 표시-default, N: 미표시)

	/*****************************************************************************************
	 * - 암호화 처리 (암호화 사용 시)
	 * Cryptstring 항목은 금액변조에 대한 확인용으로 반드시 아래와 같이 문자열을 생성하여야 합니다.
	 * 
	 * 주) 암호화 스트링은 가맹점에서 전달하는 거래번호로 부터 추출되어 사용되므로
	 * 암호화에 이용한 거래번호가 변조되어 전달될 경우 복호화 실패로 결제 진행 불가
	 *****************************************************************************************/
	public String	Cryptyn				= "N";	// Y: 암호화 사용, N: 암호화 미사용
	public String	Cryptstring			= "";	// 암호화 사용 시 암호화된 스트링


	public MobiBillkeyRegisterForm()
	{
		// TODO Auto-generated constructor stub
	}


	public void setCASH_GB( String cASH_GB )
	{
		CASH_GB = cASH_GB;
	}


	public void setMC_SVCID( String mC_SVCID )
	{
		MC_SVCID = mC_SVCID;
	}


	public void setPrdtprice( String prdtprice )
	{
		Prdtprice = prdtprice;
	}


	public void setPAY_MODE( String pAY_MODE )
	{
		PAY_MODE = pAY_MODE;
	}


	public void setPrdtnm( String prdtnm )
	{
		Prdtnm = prdtnm;
	}


	public void setSiteurl( String siteurl )
	{
		Siteurl = siteurl;
	}


	public void setTradeid( String tradeid )
	{
		Tradeid = tradeid;
	}


	public void setLOGO_YN( String lOGO_YN )
	{
		LOGO_YN = lOGO_YN;
	}


	public void setMC_AUTHPAY( String mC_AUTHPAY )
	{
		MC_AUTHPAY = mC_AUTHPAY;
	}


	public void setMC_AUTOPAY( String mC_AUTOPAY )
	{
		MC_AUTOPAY = mC_AUTOPAY;
	}


	public void setMC_PARTPAY( String mC_PARTPAY )
	{
		MC_PARTPAY = mC_PARTPAY;
	}


	public void setMC_No( String mC_No )
	{
		MC_No = mC_No;
	}


	public void setMC_FIXNO( String mC_FIXNO )
	{
		MC_FIXNO = mC_FIXNO;
	}


	public void setMC_DEFAULTCOMMID( String mC_DEFAULTCOMMID )
	{
		MC_DEFAULTCOMMID = mC_DEFAULTCOMMID;
	}


	public void setMC_FIXCOMMID( String mC_FIXCOMMID )
	{
		MC_FIXCOMMID = mC_FIXCOMMID;
	}


	public void setPayeremail( String payeremail )
	{
		Payeremail = payeremail;
	}


	public void setUserid( String userid )
	{
		Userid = userid;
	}


	public void setItem( String item )
	{
		Item = item;
	}


	public void setPrdtcd( String prdtcd )
	{
		Prdtcd = prdtcd;
	}


	public void setMC_Cpcode( String mC_Cpcode )
	{
		MC_Cpcode = mC_Cpcode;
	}


	public void setNotiemail( String notiemail )
	{
		Notiemail = notiemail;
	}


	public void setNotiurl( String notiurl )
	{
		Notiurl = notiurl;
	}


	public void setFailurl( String failurl )
	{
		Failurl = failurl;
	}


	public void setMSTR( String mSTR )
	{
		MSTR = mSTR;
	}


	public void setSellernm( String sellernm )
	{
		Sellernm = sellernm;
	}


	public void setSellertel( String sellertel )
	{
		Sellertel = sellertel;
	}


	public void setIFRAME_NAME( String iFRAME_NAME )
	{
		IFRAME_NAME = iFRAME_NAME;
	}


	public void setINFOAREA_YN( String iNFOAREA_YN )
	{
		INFOAREA_YN = iNFOAREA_YN;
	}


	public void setFOOTER_YN( String fOOTER_YN )
	{
		FOOTER_YN = fOOTER_YN;
	}


	public void setHEIGHT( String hEIGHT )
	{
		HEIGHT = hEIGHT;
	}


	public void setPRDT_HIDDEN( String pRDT_HIDDEN )
	{
		PRDT_HIDDEN = pRDT_HIDDEN;
	}


	public void setEMAIL_HIDDEN( String eMAIL_HIDDEN )
	{
		EMAIL_HIDDEN = eMAIL_HIDDEN;
	}


	public void setCONTRACT_HIDDEN( String cONTRACT_HIDDEN )
	{
		CONTRACT_HIDDEN = cONTRACT_HIDDEN;
	}


	public void setCryptyn( String cryptyn )
	{
		Cryptyn = cryptyn;
	}


	public void setCryptstring( String cryptstring )
	{
		Cryptstring = cryptstring;
	}
}
