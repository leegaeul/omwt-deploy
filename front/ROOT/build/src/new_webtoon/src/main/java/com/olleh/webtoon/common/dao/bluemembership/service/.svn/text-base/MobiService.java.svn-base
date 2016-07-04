package com.olleh.webtoon.common.dao.bluemembership.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mobilians.mc01_v0005.AckParam;
import com.mobilians.mc01_v0005.McashManager;
import com.olleh.webtoon.common.dao.bluemembership.code.BillMethodCode;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.MobiBillkeyRegisterForm;
import com.olleh.webtoon.common.dao.bluemembership.domain.MobiBillkeyRegisterResultForm;

@Service
public class MobiService
{
	private Log					logger	= LogFactory.getLog( InicisService.class );

	@Autowired
	private HttpServletRequest	request;

	@Autowired
	private BMService			bmService;

	@Autowired
	private BMPrdService		prdService;

	@Autowired
	private BMTermService		termService;

	@Value( "${system.mobi.svcid.auto}" )
	private String				svcidAuto;

	@Value( "${system.mobi.svcid.manual}" )
	private String				svcidManual;

	@Value( "${system.mobi.paymode}" )
	private String				paymode;

	@Value( "${system.mobi.siteurl}" )
	private String				siteurl;

	@Value( "${system.mobi.notiemail}" )
	private String				notiemail;

	@Value( "${system.mobi.mrchid}" )
	private String				mrchid;

	@Value( "${system.mobi.emailflag}" )
	private String				emailflag;

	@Value( "${system.mobi.logdir}" )
	private String				logdir;

	@Value( "${system.mobi.loglevel}" )
	private String				loglevel;

	@Value( "${system.mobi.server.ip}" )
	private String				serverIp;

	@Value( "${system.mobi.server.port}" )
	private Integer				serverPort;

	@Value( "${system.mobi.switch.ip}" )
	private String				switchIp;

	@Value( "${system.mobi.ver}" )
	private String				ver;

	@Value( "${system.mobi.curkey}" )
	private String				curkey;

	@Value( "${system.mobi.key1}" )
	private String				key1;

	@Value( "${system.mobi.key2}" )
	private String				key2;
	
	@Value( "${system.mobile.domain.ssl}")
	private String				mobileDomainSsl;

	@Value( "${system.pc.domain.ssl}")
	private String				pcDomainSsl;

	@Value( "${system.mobi.userencode}" )
	private String				userEncode;
	
	private final String		joinTradeIdPrefix = "bmjoin_";
	private final String		paymentTradeIdPrefix = "bmpayment_";


	public MobiBillkeyRegisterForm createBillKeyRegisterForm( BMJoinDomain join, boolean isMobile ) throws Exception
	{
		BMPrdDomain prd = prdService.getPrdByPrdcode( join.getPrdcode() );

		MobiBillkeyRegisterForm form = new MobiBillkeyRegisterForm();
		form.CASH_GB = "MC";
		form.MC_SVCID = svcidManual;
		form.MC_AUTOPAY = "Y";
		form.Prdtprice = prd.getPrice() + "";
		form.PAY_MODE = paymode;
		form.Prdtnm = prd.getPrdname();
		form.Siteurl = siteurl;
		form.Tradeid = joinTradeIdPrefix + join.getJoinseq();
		
		
		if( isMobile )
		{
//			if(!join.getOtcheck().equals(null)||join.getOtcheck().equals("ot")){
			if(join.getOtcheck() != null || "ot".equals(join.getOtcheck())){
				form.Okurl = mobileDomainSsl +"/ot/bluemembership/registerMobi.kt?isMobile=true";
				form.Closeurl = mobileDomainSsl +"/ot/bluemembership/registerMobi.kt?isMobile=true&isPopup=false&isUserCancel=true&Tradeid=" + form.Tradeid;
				form.CALL_TYPE = "SELF";
			}else{
				form.Okurl = mobileDomainSsl + "/bluemembership/registerMobi.kt?isMobile=true";
				form.Closeurl = mobileDomainSsl + "/bluemembership/registerMobi.kt?isMobile=true&isPopup=false&isUserCancel=true&Tradeid=" + form.Tradeid;
				form.CALL_TYPE = "SELF";	
			}
		}
		else
		{	
			if(join.getOtcheck() != null || "ot".equals(join.getOtcheck())){
				form.Okurl = pcDomainSsl + "/ot/bluemembership/registerMobi.kt?isMobile=false";
				form.Closeurl = pcDomainSsl + "/ot/bluemembership/registerMobi.kt?isMobile=false&isPopup=false&isUserCancel=true&Tradeid=" + form.Tradeid;
				form.CALL_TYPE = "P";
			}else{
				form.Okurl = pcDomainSsl + "/bluemembership/registerMobi.kt?isMobile=false";
				form.Closeurl = pcDomainSsl + "/bluemembership/registerMobi.kt?isMobile=false&isPopup=false&isUserCancel=true&Tradeid=" + form.Tradeid;
				form.CALL_TYPE = "P";
			}
		}

		return form;
	}


	public BMJoinDomain applyRegisterResultToJoin( MobiBillkeyRegisterResultForm result, BMJoinDomain join ) throws Exception
	{
		if( join == null || join.getJoinseq() != Integer.parseInt( result.Tradeid.replace( joinTradeIdPrefix, "" ) ) )
			throw new Exception( "비 정상적인 접근 입니다" );

		if( join.getBillmethodfg() != BillMethodCode.phone )
			throw new Exception( "휴대폰 결제 요청이 아닙니다" );

		join.setPgresultcode( result.Resultcd );
		join.setPgresultmsg( result.Resultmsg );
		join.setPgbillkey( result.AutoBillKey );
		join.setPgtid( result.Mobilid );
		join.setPguserkey( result.USERKEY );
		join.setBuyertel( result.No );

		return join;

	}
	

	public AckParam payForMembership( BMJoinDomain join, Integer paymentseq ) throws Exception
	{
		BMPrdDomain prd = prdService.getPrdByPrdcode( join.getPrdcode() );

		String Mode = "43";										// [  2byte 고정] 휴대폰 자동결제 요청을 위한 고정값. 43 수정불가!!!
		String Phoneid = join.getPguserkey();					// [ 15byte 이하] 휴대폰정보(이통사, 휴대폰번호, 주민번호) 대체용 USERKEY
		String Svcid = svcidAuto;								// [ 12byte 고정] 모빌리언스에서 부여한 서비스ID (12byte 숫자 형식)
		String Mrchid = mrchid;									// [  8byte 고정] 모빌리언스에서 부여한 상점ID. 서비스ID 앞 8자리. (8byte 숫자 형식)
		String Tradeid = paymentTradeIdPrefix + paymentseq;		// [  4byte 이상, 40byte 이하] 가맹점거래번호. 결제 요청 시 마다 unique한 값을 세팅해야 함.
		String Prdtnm = prd.getPrdname();						// [ 50byte 이하] 상품명
		String Prdtprice = prd.getPrice() + "";					// [ 10byte 이하] 결제요청금액
		String Email = join.getBuyeremail();					// [ 30byte 이하] 결제자 e-mail
		String Emailflag = emailflag;							// [  1byte 고정] 결제 통보 이메일 보내기 여부
		String Recordkey = siteurl;								// [ 20byte 이하] 가맹점도메인 (예: www.mcash.co.kr)
		String ReqOpt = "";										// [ 30byte 이하] USERKEY 요청구분
		String AutoBillFlag = "2";								// [  1byte 고정] 자동결제구분. 자동결제 시 "2" 세팅. (0: 해당없음, 1: 최초등록, 2: 2회차이상 거래)
		String AutoBillKey = join.getPgbillkey();				// [ 15byte 이하] 최초 일반결제 시 발급받은 자동결제용 key
		String AutoBillDate = join.getJoindt().substring( 0, 8 );//[  8byte 고정] 자동결제 최초일자. (자동결제 1회차 일반결제일)
		String Commid = "";										// [  3byte 고정] 이통사. USERKEY 사용시에는 세팅할 필요없음
		String No = "";											// [ 11byte 이하] 휴대폰번호. USERKEY 사용시에는 세팅할 필요없음
		String Socialno = "";									// [ 13byte 이하] 주민번호. USERKEY 사용시에는 세팅할 필요없음
		String Userid = "";										// [ 20byte 이하] 가맹점 결제자ID
		String Prdtcd = "";										// [ 40byte 이하] 상품코드. 자동결제인 경우 상품코드별 SMS문구를 별도 세팅할 때 사용하며 사전에 모빌리언스에 등록이 필요함.
		String Item = "";										// [  8byte 이하] 아이템코드. 미사용 시 반드시 공백으로 세팅.
		String Cpcd = "";										// [ 20byte 이하] 리셀러하위상점key. 리셀러 업체인 경우에만 세팅.
		String Sellernm = "";									// [ 50byte 이하] 실판매자 이름 (오픈마켓의 경우 실 판매자 정보 필수)
		String Sellertel = "";									// [ 15byte 이하] 실판매자 전화번호 (오픈마켓의 경우 실 판매자 정보 필수)
		String Userip = "";
		
		try
		{
			Userip = request.getRemoteAddr();				// [ 15byte 이하] 사용자IP
		}
		catch( Exception e)
		{
			
		}
		
		logger.debug( "Mode=" + Mode );
		logger.debug( "Phoneid=" + Phoneid );
		logger.debug( "Svcid=" + svcidAuto );
		logger.debug( "Mrchid=" + mrchid );
		logger.debug( "Tradeid=" + Tradeid );
		logger.debug( "Prdtnm=" + Prdtnm );
		logger.debug( "Prdtprice=" + Prdtprice );
		logger.debug( "Email=" + Email );
		logger.debug( "Emailflag=" + Emailflag );
		logger.debug( "Recordkey=" + Recordkey );
		logger.debug( "AutoBillFlag=" + AutoBillFlag );
		logger.debug( "AutoBillKey=" + AutoBillKey );
		logger.debug( "AutoBillDate=" + AutoBillDate );
		logger.debug( "Userip=" + Userip );
		

		// 아래는 한글이 입력될 수 있는 파라미터들로 만약 한글이 깨진다면 CommonUtil.Decode_1을 사용한다.
		// Prdtnm = CommonUtil.Decode_1(Prdtnm);
		// Userid = CommonUtil.Decode_1(Userid);
		// Sellernm = CommonUtil.Decode_1(Sellernm);

		/*********************************************************************************
		 * 모빌리언스와 결제 통신 처리하는 프로세스
		 *********************************************************************************/
		McashManager mm = new McashManager();

		/*********************************************************************************
		 * Mcash.properties 파일을 가맹점이 지정한 경로에 설정시 아래처럼 세팅하세요.
		 * 미설정시는 기본 {class root}/Mcash.properties 파일을 이용하도록 처리됩니다.
		 *********************************************************************************/
		// mm.setConfigFileDir("/user1/mcash/Mcash.properties"); //가맹점에서 별도 경로에 Mcash.properties 파일을 설정하는 경우 사용

		/*********************************************************************************
		 * Mcash.properties 파일에 설정된 환경 변수를 properties 파일이 아닌
		 * 현재 페이지에서 직접 세팅을 원할 경우 아래 method 사용
		 *********************************************************************************/

		mm.setServerInfo( serverIp, // 서버 IP
				switchIp, // 스위치 IP
				serverPort, // 서버 PORT
				logdir, // 로그 디렉토리 경로
				curkey, // 가맹점 KEY 구분(0: 상점ID, 1: 사용자정의KEY1, 2: 사용자정의KEY2)
				key1, // 사용자정의KEY 사용 시 가맹점 KEY 값
				userEncode, // 가맹점측 서버 인코딩셋. "" or null 인경우 EUC-KR
				loglevel // 로그레벨. "" or null 가능
		);
		
		
		logger.debug( "serverIp=" + serverIp );
		logger.debug( "switchIp=" + switchIp );
		logger.debug( "serverPort=" + serverPort );
		logger.debug( "logdir=" + logdir );
		logger.debug( "curkey=" + curkey );
		logger.debug( "key1=" + key1 );
		logger.debug( "userEncode=" + userEncode );
		logger.debug( "loglevel=" + loglevel );


		/*********************************************************************************
		 * 결제 통신
		 *********************************************************************************/
		AckParam ap = mm.McashApprv( Mode,			// 01. 결제모드
				Recordkey,		// 02. 사이트URL
				Mrchid,			// 03. 상점ID
				Svcid,			// 04. 서비스ID
				No,				// 05. 핸드폰 번호
				Socialno,		// 06. 주민번호
				"",				// 07. 가맹점 사용자 주민번호 (미사용)
				Userid,			// 08. 사용자ID
				ReqOpt,			// 09. 사용자KEY 요청구분
				Email,			// 10. 사용자 email
				Phoneid,		// 11. USERKEY (기존 Phoneid 파라미터)
				Tradeid,		// 12. 가맹점 거래번호
				Prdtcd,			// 13. 가맹점 상품코드
				Prdtnm,			// 14. 가맹점 상품명
				Prdtprice,		// 15. 가맹점 상품 가격
				"",				// 16. SMS 인증번호
				Commid,			// 17. 이통사
				Emailflag,		// 18. 결제 통보 이메일 보내기 여부
				Item,			// 19. 사이트URL
				"",				// 20. SMS, ARS 구분
				Userip,			// 21. 사용자 IP
				Cpcd,			// 22. 가맹점 코드
				"",				// 23. 모빌리언스 거래번호
				"",				// 24. 안심결제 비밀번호
				Sellernm,		// 25. 판매자명
				Sellertel,		// 26. 판매자 연락처
				"",				// 27. SKT 캐리어핀 사용여부
				"",				// 28. SKT 캐리어핀 비밀번호
				AutoBillFlag,	// 29. 자동결제구분 (0: 해당없음, 1: 최초등록, 2: 2회차이상 거래)
				AutoBillDate,	// 30. 자동결제 최초일자 (2 회차이상 거래시 최초자동결제 거래일자)
				AutoBillKey		// 31. 자동결제 KEY (발급받은 자동결제 key)
				);

		return ap;
	}


	public String getJoinTradeIdPrefix()
	{
		return joinTradeIdPrefix;
	}


	public String getPaymentTradeIdPrefix()
	{
		return paymentTradeIdPrefix;
	}
}
