/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰

 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : SDPApiCode.java
 * DESCRIPTION    : SDP API
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-26      init
 *****************************************************************************/

package com.olleh.webtoon.api.sdp;

public enum SDPApiCode {
	getUserInfoWithAge (
			"getUserInfoWithAgeRequest",
			"soap",
			"https",
			"/bssCapri/BssCapriManager"
		), updateRetrieveRepPhoneNumber (
			"updateRetrieveRepPhoneNumber",
			"soap",
			"http",
			"/userProfile/UserProfileManager"
		), getOwnerContactInfoByCredentialId (
			"getOwnerContactInfoByCredentialId",
			"soap",
			"https",
			"/UUMDataRetrieval/UUMDataRetrievalSSL?wsdl"
		), requestPayment (
			"requestPayment",
			"json",
			"https",
			"/rest/ipg/requestPayment"
		), cancelPayment (
			"cancelPayment",
			"json",
			"https",
			"/rest/ipg/cancelPayment"
		), getBasicUserInfoRequest (
			"getBasicUserInfoRequest",
			"soap",
			"https",
			"/bssCapri/BssCapriManager"
		), getBasicUserInfoByPhoneNumber (
			"getBasicUserInfoByPhoneNumberRequest",
			"soap",
			"https",
			"/nonBssCapri/NonBssCapriManagerSSL"
		), checkAllSubscribeStatus (
			"checkAllSubscribeStatusRequest",
			"soap",
			"https",
			"/bssCapri/BssCapriManager"
		), authenticateUserByIdPwd (
			"authenticateUserByIdPwd",
			"soap",
			"https",
			"/authentication/AuthenticationManagerSSL"
		), authenticateUserByIdPwdForOlleh (
			"authenticateUserByIdPwdForOlleh",
			"soap",
			"https",
			"/authentication/AuthenticationManagerSSL"
		);
		
	
	private String name;
	private String type;
	private String protocol;
	private String url;

	private SDPApiCode(String name, String type, String protocol, String url) {
		this.name = name;
		this.type = type;
		this.protocol = protocol;
		this.url = url;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
