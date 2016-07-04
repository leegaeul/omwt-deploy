/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰

 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : SDPApiService.java
 * DESCRIPTION    : SDP API
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-23      init
 *****************************************************************************/

package com.olleh.webtoon.api.sdp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.olleh.webtoon.common.dao.payment.domain.PaymentDomain;
import com.olleh.webtoon.common.util.MessageUtil;

public class SDPApiService {
	
	public static SDPResponse getUserInfoWithAge(String ctn) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("CALL_CTN", ctn);
		
		SDPClient client = new SDPClient();
		return client.call(new SDPRequest(SDPApiCode.getUserInfoWithAge, params)); 
	}
	
	public static SDPResponse getBasicUserInfo(String ctn) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("CALL_CTN", ctn);
		
		SDPClient client = new SDPClient();
		return client.call(new SDPRequest(SDPApiCode.getBasicUserInfoRequest, params)); 
	}
	
	public static SDPResponse getBasicUserInfoByPhoneNumber(String ctn) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("CALL_CTN", ctn);
		
		SDPClient client = new SDPClient();
		return client.call(new SDPRequest(SDPApiCode.getBasicUserInfoByPhoneNumber, params)); 
	}
	
	public static SDPResponse checkAllSubscribeStatus(String cpid, String sid, String ctn) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("CPID", cpid);
		params.put("SID", sid);
		params.put("CALL_CTN", ctn);
		
		SDPClient client = new SDPClient();
		return client.call(new SDPRequest(SDPApiCode.checkAllSubscribeStatus, params)); 
	}
	
	public static SDPResponse authenticateUserByIdPwd(String user_name, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "user_name", user_name );
		params.put( "password", password );
		params.put( "domain_name_cd", "1" );	// 도메인네임코드, 1 = 통합웹계약
		params.put( "Domain_name_Cd", "1" ); // 도메인네임코드, 1 = 통합웹계약
		
		SDPClient client = new SDPClient();
		return client.call(new SDPRequest(SDPApiCode.authenticateUserByIdPwd, params)); 
	}
	
	public static SDPResponse authenticateUserByIdPwdForOlleh(String user_name, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "user_name", user_name );
		params.put( "password", password );
		params.put( "domain_name_cd", "1" );	// 도메인네임코드, 1 = 통합웹계약
		params.put( "Domain_name_Cd", "1" ); // 도메인네임코드, 1 = 통합웹계약
		
		SDPClient client = new SDPClient();
		return client.call(new SDPRequest(SDPApiCode.authenticateUserByIdPwdForOlleh, params)); 
	}
	
	public static SDPResponse updateRetrieveRepPhoneNumber(String crdId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("credential_id", crdId);
		params.put("action_type", "1");
		
		SDPClient client = new SDPClient();
		return client.call(new SDPRequest(SDPApiCode.updateRetrieveRepPhoneNumber, params)); 
	}
	
	public static SDPResponse getOwnerContactInfoByCredentialId(String crdId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("credential_id", crdId);
		params.put("access_level", "3");
		
		SDPClient client = new SDPClient();
		return client.call(new SDPRequest(SDPApiCode.getOwnerContactInfoByCredentialId, params));
	}
	
	public static SDPResponse requestPayment(PaymentDomain paymentDomain) {
		// 주문정보
		Map<String, String> ordHist = new HashMap<String, String>();
		ordHist.put("ordNo", paymentDomain.getOrderno());
		ordHist.put("ordDt", paymentDomain.getPaymentdt());
		ordHist.put("prtlSvcType", MessageUtil.getSystemMessage("system.ipg.prtlsvctype"));
		ordHist.put("prtlSvcCd", MessageUtil.getSystemMessage("system.ipg.prtlsvccd"));
		ordHist.put("ttlBaseAmt", Integer.toString(paymentDomain.getPayamount()));
		ordHist.put("ttlBlngAmt", Integer.toString(paymentDomain.getPayamount()));
		ordHist.put("ttlDscnAmt", "0");
		ordHist.put("ttlEvntDscnAmt", "0");
		ordHist.put("ttlOthrBlngAmt", "0");
		ordHist.put("statCd", "AC");
		ordHist.put("goodsCnt", "1");
		
		// 구매정보
		Map<String, String> buyHist = new HashMap<String, String>();
		buyHist.put("buySrlNo", paymentDomain.getOrderno());
		buyHist.put("buyDt", paymentDomain.getPaymentdt());
		buyHist.put("sellerNo", MessageUtil.getSystemMessage("system.ipg.sellerno"));
		buyHist.put("goodsId", paymentDomain.getGoodsid());
		buyHist.put("goodsNm", paymentDomain.getPayname());
		buyHist.put("buyQty", "1");
		buyHist.put("buyUntval", Integer.toString(paymentDomain.getPayamount()));
		buyHist.put("goodAmt", Integer.toString(paymentDomain.getPayamount()));
		buyHist.put("blngAmt", Integer.toString(paymentDomain.getPayamount()));
		buyHist.put("dscnAmt", "0");
		buyHist.put("evntDscnAmt", "0");
		buyHist.put("othrBlngAmt", "0");
		buyHist.put("taxFlag", "Y");
		buyHist.put("sellerType", MessageUtil.getSystemMessage("system.ipg.sellerno"));
		buyHist.put("sellerType", MessageUtil.getSystemMessage("system.ipg.sellertype"));
		buyHist.put("sellerId", MessageUtil.getSystemMessage("system.ipg.sellerid"));
		buyHist.put("sellerNm", MessageUtil.getSystemMessage("system.ipg.sellernm"));
		buyHist.put("sellerRprsNm", MessageUtil.getSystemMessage("system.ipg.sellerrprsnm"));
		buyHist.put("sellerTlph", MessageUtil.getSystemMessage("system.ipg.sellertlph"));
		buyHist.put("sellerAdrs", MessageUtil.getSystemMessage("system.ipg.selleradrs"));
		
		Collection collection = new ArrayList();
		collection.add(buyHist);
		
		Map<String, Object> buyHists = new HashMap<String, Object>();
		buyHists.put("buyHist", collection);
		
		// 결제정보
		Map<String, Object> payment = new HashMap<String, Object>();
		payment.put("userid", paymentDomain.getUserid());
		payment.put("payStorId", MessageUtil.getSystemMessage("system.ipg.paystoreid"));
		payment.put("payMthdCd", paymentDomain.getPaymethod());	// 변경 필요
		payment.put("payAmt", paymentDomain.getPayamount());
		payment.put("payNo", paymentDomain.getPayno());
		payment.put("payId", paymentDomain.getPayid());
		payment.put("subscrId", paymentDomain.getSubscrid());
		payment.put("subscrIdType", paymentDomain.getSubscridtype());
		payment.put("payNm", paymentDomain.getPayname());
		payment.put("ordHist", ordHist);
		payment.put("buyHists", buyHists);		
		
		SDPClient client = new SDPClient();
		
		return client.call(new SDPRequest(SDPApiCode.requestPayment, payment));
	}
	
	public static SDPResponse cancelPayment(PaymentDomain paymentDomain) {
		// 결제정보
		Map<String, Object> payment = new HashMap<String, Object>();
		payment.put("payNo", paymentDomain.getPayno());
		payment.put("payNoType", "1");
		payment.put("cancelType", "1");
		
		SDPClient client = new SDPClient();
		
		return client.call(new SDPRequest(SDPApiCode.cancelPayment, payment));
	}
}