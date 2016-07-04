package com.olleh.webtoon.common.dao.bluemembership.service;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.inicis.inipay4.INIpay;
import com.inicis.inipay4.util.INIdata;
import com.olleh.webtoon.common.dao.bluemembership.code.BillMethodCode;
import com.olleh.webtoon.common.dao.bluemembership.code.CardCode;
import com.olleh.webtoon.common.dao.bluemembership.code.PrdCode;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinTermDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.INIBillKeyRegisterForm;
import com.olleh.webtoon.common.dao.bluemembership.domain.INIBillKeyRegisterResultForm;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.EncryptUtil;
import com.olleh.webtoon.common.util.StringUtil;
import com.olleh.webtoon.common.util.TRDateUtils;

@Service
public class InicisService
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

	@Autowired
	private INIpay				inipay;

	@Value( "${system.inipay.home}" )
	private String				iniHome;

	@Value( "${system.inipay.logmode}" )
	private String				iniLogMode;

	@Value( "${system.inipay.key}" )
	private String				iniKey;

	@Value( "${system.inipay.mid}" )
	private String				iniMid;

	@Value( "${system.inipay.url}" )
	private String				iniUrl;
	
	@Value( "${system.inipay.merchantkey}" )
	private String				iniMerchantkey;
	
	@Value( "${system.mobile.domain.ssl}")
	private String				mobileDomainSsl;

	@Value( "${system.pc.domain.ssl}")
	private String				pcDomainSsl;


	public INIdata requestIniRegularPayRegister()
	{
		INIdata iniData = new INIdata();
		iniData.setData( "goodname", getParamAsEucKr( "goodname" ) );      	// 상품명 (최대 40자)
		iniData.setData( "buyername", getParamAsEucKr( "buyername" ) );    		// 구매자 (최대 15자)
		iniData.setData( "buyertel", request.getParameter( "buyertel" ) );     // 구매자이동전화
		iniData.setData( "buyeremail", request.getParameter( "buyeremail" ) );  	// 구매자이메일
		iniData.setData( "uip", request.getRemoteAddr() );                // IP Addr
		iniData.setData( "encrypted", request.getParameter( "encrypted" ) );	// 플러그인 자동 입력
		iniData.setData( "sessionkey", request.getParameter( "sessionkey" ) );	// 플러그인 자동 입력
		iniData.setData( "type", "authbill" );                             // 결제 type, 고정
		iniData.setData( "inipayHome", iniHome );                       			// 이니페이가 설치된 절대경로
		iniData.setData( "logMode", iniLogMode );                           	// logMode
		iniData.setData( "keyPW", iniKey );                                	// 키패스워드
		iniData.setData( "subPgip", "203.238.37.3" );                      	// Sub PG IP (고정)
		iniData.setData( "mid", iniMid );               					// 상점아이디
		iniData.setData( "paymethod", "Card" );									// 지불방법, 빌링등록
		iniData.setData( "billtype", "Card" );                             	// 빌링유형 고정
		iniData.setData( "price", "1000" );                                	// 가격, 결제되지 않는 고정설정값
		iniData.setData( "currency", "WON" );                              	// 화폐단위
		iniData.setData( "url", iniUrl );       							// 홈페이지 주소(URL)
		iniData.setData( "crypto", "execure" );                            	// Extrus 암호화 모듈 적용(고정)

		// 결제등록요청
		return inipay.payRequest( iniData );
	}


	public INIdata payForMembership( BMJoinDomain join ) throws Exception
	{
		INIdata data;
		int retryCnt = 0;
		
		while( true )
		{
			data = _payForMembership(join);
			
			//결제 완료
			if( data.getData("ResultCode").equals( "00" ) )
				break;
			
			if( data.getData( "ResultCode" ).equals( "01" ) )
			{
				//[1195][실시간빌링실패|빌링 미등록 거래] 오류, 다시한번시도해보기
				if( data.getData( "ResultMsg" ).contains( "1195" ) && retryCnt < 3 )
				{
					retryCnt++;
					Thread.sleep( 1000 );
					data = _payForMembership(join);
				}
				else
					break;
			}
		}
		
		return data;
	}
	
	private INIdata _payForMembership( BMJoinDomain join ) throws Exception
	{
		BMPrdDomain prd = prdService.getPrdByPrdcode( join.getPrdcode() );

		INIdata data = new INIdata();

		data.setData( "type", "reqrealbill" );                           	// 결제 type, 고정
		data.setData( "inipayHome", iniHome );                           	// 이니페이가 설치된 절대경로
		data.setData( "logMode", iniLogMode );                              // logMode
		data.setData( "keyPW", iniKey );                                    // 키패스워드
		data.setData( "subPgip", "203.238.37.3" );                          // Sub PG IP (고정)
		data.setData( "mid", iniMid );                 						// 상점아이디
		data.setData( "uid", iniMid );                						// INIpay User ID
		data.setData( "goodname", prd.getPrdname() );       				// 상품명 (최대 40자)
		data.setData( "currency", "WON" );       							// 화폐단위
		data.setData( "price", prd.getPrice() + "" );             			// 가격
		data.setData( "buyername", join.getUserid() );    				// 구매자 (최대 15자) : 구매자명은 아이디나 이메일로 설정함
		data.setData( "buyeremail", join.getBuyeremail() );
		data.setData( "paymethod", "Card" );                                // 지불방법, 카드빌링
		data.setData( "billkey", join.getPgbillkey() );         			// 빌링등록 키(빌키)
		data.setData( "buyertel", join.getBuyertel() );							//
		data.setData( "url", iniUrl ); // 홈페이지 주소(URL)
		try
		{
			data.setData( "uip", request.getRemoteAddr() );                     // IP Addr
		}
		catch(Exception e)
		{
			
		}
		data.setData( "cardquota", "00" );									// 할부 개월수, 00:일시불
		data.setData( "authentification", "01" );							// 본인인증, 01:안함
		data.setData( "crypto", "execure" );                                // Extrus 암호화 모듈 적용(고정)

		logger.info( "payWithInicis.INIdata" );
		logger.info( new ObjectMapper().writeValueAsString( data ) );

		// # 3. 지불 요청 #
		return inipay.payRequest( data );
	}


	/**
	 * 이니시스 등록 결과를 가입 신청 이력에 반영
	 * 
	 * @param iniData
	 * @return
	 * @throws Exception
	 */
	public BMJoinDomain applyIniRegisterResultToJoin( INIBillKeyRegisterResultForm iniData, BMJoinDomain join ) throws Exception
	{
		if( join == null || join.getJoinseq() != Integer.parseInt( iniData.orderid ) )
			throw new Exception( "비 정상적인 접근 입니다" );

		if( join.getBillmethodfg() != BillMethodCode.card )
			throw new Exception( "카드결제 요청이 아닙니다" );

		join.setPgbillkey( iniData.billkey );
		join.setPgcardcode( CardCode.toCode( iniData.cardcd ) );
		join.setPgresultcode( iniData.resultcode );
		join.setPgresultmsg( iniData.resultmsg );
		join.setPgtid( iniData.tid );

		return join;
	}


	public String convertDatesToIniOfferPeriodFormat( Date startDate, Date endDate )
	{
		return TRDateUtils.yyyymmddhhmiFromDate( startDate ) + TRDateUtils.yyyymmddhhmiFromDate( endDate );
	}


	private String getParamAsEucKr( String paramName )
	{
		return StringUtil.utf8ToEuckr( request.getParameter( paramName ) );
	}
	
	/**
	 * 이니시스 빌키 등록 시 전달할 파라미터 생성
	 * @param join
	 * @return
	 */
	public INIBillKeyRegisterForm createBillKeyRegisterForm( BMJoinDomain join, boolean isMobile )
	{
		BMPrdDomain prd = prdService.getPrdByPrdcode( join.getPrdcode() );
		
		INIBillKeyRegisterForm form = new INIBillKeyRegisterForm();
		
		BMJoinTermDomain nextTerm = termService.getNextTermForUserAndPrd( join.getIdfg(), join.getUserid(), join.getPrdcode() );
		String period = convertDatesToIniOfferPeriodFormat( nextTerm.getStartdtDate(), nextTerm.getEnddtDate() );
		
		String timestamp = Long.toString( System.currentTimeMillis() );
		String orderid = Integer.toString( join.getJoinseq() );
		String hashdata = EncryptUtil.encryptSHA256( iniMid + orderid + timestamp + iniMerchantkey );
		
		form.mid = iniMid;
		form.orderid = orderid;
		form.buyername = join.getUserid();
		form.buyertel = join.getBuyertel();
		form.buyeremail = join.getBuyeremail();
		form.goodname = join.getPrdcode().getText();
		form.price = prd.getPrice() + "";
		form.period =  period;
		form.timestamp = timestamp;
		form.hashdata = hashdata;
		if( isMobile )
//			if(!join.getOtcheck().equals(null)||join.getOtcheck().equals("ot")){
			if(join.getOtcheck() != null || "ot".equals(join.getOtcheck())){
				form.returnurl = mobileDomainSsl + "/ot/bluemembership/registerInicis.kt?isMobile=true&isPopup=false";
			}else{
				form.returnurl = mobileDomainSsl + "/bluemembership/registerInicis.kt?isMobile=true&isPopup=false";	
			}
		else
			if(join.getOtcheck() != null || "ot".equals(join.getOtcheck())){
				form.returnurl = pcDomainSsl + "/ot/bluemembership/registerInicis.kt?isMobile=false&isPopup=true";
			}else{
				form.returnurl = pcDomainSsl + "/bluemembership/registerInicis.kt?isMobile=false&isPopup=true";	
			}
			
		
		return form;
	}
	
	
	public INIBillKeyRegisterForm createBillKeyRegisterFormIE(PrdCode prdcode, String buyertel , String buyeremail ) throws Exception
	{
		BMPrdDomain prd = prdService.getPrdByPrdcode( prdcode );
		UserDomain user = AuthUtil.getDecryptUserInfo( request );
		BMJoinTermDomain nextTerm = termService.getNextTermForUserAndPrd( user.getIdfg(), user.getEmail(), prdcode );
		
		INIBillKeyRegisterForm form = new INIBillKeyRegisterForm();
		form.mid = iniMid;
		form.buyername = user.getEmail();
		form.buyertel = buyertel;
		form.buyeremail = buyeremail;
		form.goodname = prd.getPrdname();
		form.acceptmethod = "BILLAUTH:FULLVERIFY";
		form.ini_offer_period = convertDatesToIniOfferPeriodFormat( nextTerm.getStartdtDate(), nextTerm.getEnddtDate() );
		form.print_msg = prd.getUseterm() + "일마다 자동결제되는 상품 입니다";
		form.version = "4000";
		form.price = prd.getPrice() + "";
		form.prdcode = prdcode.getCode();

		return form;
	}
	
	
	


	public String getIniMid()
	{
		return iniMid;
	}
}
