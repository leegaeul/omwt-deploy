/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ServiceController.java
 * DESCRIPTION    : B2B 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2015-05-07      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.service.domain;

public class ApiResultDomain {
	
	private String app_service_media;	//접속 구분자(아이폰, 안드로이드, 기타(모바일웹))
	private String device_sn;			//기기 코드 값(성인인증 시 사용)
	private String account_id;			//사용자 계정
	private String class_status;		//유료 회원 여부(2:유료회원)
	private String phone_ncn;			//알짜팩이나 월정액 사용자의 경우 NCN 값을 함께 전달
	private String class_type;
	private String navi_bar_type;		//네이게이션 타입(2015/7/28)
	
	public String getApp_service_media() {
		return app_service_media;
	}
	public void setApp_service_media(String app_service_media) {
		this.app_service_media = app_service_media;
	}
	public String getDevice_sn() {
		return device_sn;
	}
	public void setDevice_sn(String device_sn) {
		this.device_sn = device_sn;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getClass_status() {
		return class_status;
	}
	public void setClass_status(String class_status) {
		this.class_status = class_status;
	}
	public String getPhone_ncn() {
		return phone_ncn;
	}
	public void setPhone_ncn(String phone_ncn) {
		this.phone_ncn = phone_ncn;
	}
	public String getClass_type() {
		return class_type;
	}
	public void setClass_type(String class_type) {
		this.class_type = class_type;
	}
	public String getNavi_bar_type() {
		return navi_bar_type;
	}
	public void setNavi_bar_type(String navi_bar_type) {
		this.navi_bar_type = navi_bar_type;
	}
	
}