/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : UserDomain.java
 * DESCRIPTION    : 회원 테이블 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-16      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.user.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class OllehUserDomain {
	
	private long userseq;            	//회원순번 (PK, 자동증가)
	private String nickname;         //닉네임
	
	private String userid;				//사용자아이디(올레닷컴ID 또는 오픈ID)
	private String ollehid;				//올레닷컴ID
	private String authoryn; 			//작가여부
	private String termsyn;				//약관동의여부 (Y/N)
	private String agreementdt;			//약관동의일시 (YYYYMMDDHHMMSS)
	private String adultyn;				//성인인증여부 (Y/N)
	private String birthday;			//생년월일 (YYYYMMDD)
	private String adultdt;				//성인인증일시 (YYYYMMDDHHMMSS)
	private String adultno;				//성인인증번호
	private String regdt;				//등록일시 (YYYYMMDDHHMMSS)
	private String idfg; 				//ID구분 (open:오픈ID, olleh:올레ID)
	
	private String usetermsyn;			//정보활용약관동의여부 (Y/N)
	private String useagreementdt;		//정보활용약관동의일시 (YYYYMMDDHHMMSS)
	
	private int nameconseq;				//네임콘 번호	
	private String nameconnm;			//네임콘 명
	private String nameconurl;			//PC 네임콘URL (웹 루트로부터의 경로 URL, 작가인경우 NAS 경로)
	private String mnameconurl;			//모바일 네임콘URL (웹 루트로부터의 경로 URL, 작가인경우 NAS 경로)
	
	private String authorseq;
	private int nameconseq2;			//작가 네임콘 번호	
	private String nameconnm2;			//작가 네임콘 명
	private String nameconurl2;			//작가 PC 네임콘URL (웹 루트로부터의 경로 URL, 작가인경우 NAS 경로)
	private String mnameconurl2;		//작가 모바일 네임콘URL (웹 루트로부터의 경로 URL, 작가인경우 NAS 경로)
	
	private String uuid;		//디바이스 기기
	private String loginyn;		//디바이스 로그인여부 체크
	
	public long getUserseq() {
		return userseq;
	}
	public void setUserseq(long userseq) {
		this.userseq = userseq;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOllehid() {
		return ollehid;
	}
	public void setOllehid(String ollehid) {
		this.ollehid = ollehid;
	}
	public String getAuthoryn() {
		return authoryn;
	}
	public void setAuthoryn(String authoryn) {
		this.authoryn = authoryn;
	}
	public String getTermsyn() {
		return termsyn;
	}
	public void setTermsyn(String termsyn) {
		this.termsyn = termsyn;
	}
	public String getAgreementdt() {
		return agreementdt;
	}
	public void setAgreementdt(String agreementdt) {
		this.agreementdt = agreementdt;
	}
	public String getAdultyn() {
		return adultyn;
	}
	public void setAdultyn(String adultyn) {
		this.adultyn = adultyn;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAdultdt() {
		return adultdt;
	}
	public void setAdultdt(String adultdt) {
		this.adultdt = adultdt;
	}
	public String getAdultno() {
		return adultno;
	}
	public void setAdultno(String adultno) {
		this.adultno = adultno;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getIdfg() {
		return idfg;
	}
	public void setIdfg(String idfg) {
		this.idfg = idfg;
	}
	public int getNameconseq() {
		return nameconseq;
	}
	public void setNameconseq(int nameconseq) {
		this.nameconseq = nameconseq;
	}
	public String getNameconnm() {
		return nameconnm;
	}
	public void setNameconnm(String nameconnm) {
		this.nameconnm = nameconnm;
	}
	public String getNameconurl() {
		if(nameconurl == null || "".equals(nameconurl)) 
			nameconurl = "/images/pc/namecon/pc_namecon_05.png";
		
		return nameconurl;
	}
	public void setNameconurl(String nameconurl) {
		if(nameconurl != null && (nameconurl.indexOf("/images/") < 0 && nameconurl.indexOf("http") < 0))
			nameconurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/namecon"+nameconurl;
		
		this.nameconurl = nameconurl;
	}
	public String getMnameconurl() {
		if(mnameconurl == null || "".equals(mnameconurl)) 
			mnameconurl = "/images/mobile/namecon/pc_namecon_05.png";
		
		return mnameconurl;
	}
	public void setMnameconurl(String mnameconurl) {
		if(mnameconurl != null && (mnameconurl.indexOf("/images/") < 0 && mnameconurl.indexOf("http") < 0))
			mnameconurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/namecon"+mnameconurl;
		
		this.mnameconurl = mnameconurl;
	}	
	public String getAuthorseq() {
		return authorseq;
	}
	public void setAuthorseq(String authorseq) {
		this.authorseq = authorseq;
	}
	public int getNameconseq2() {
		return nameconseq2;
	}
	public void setNameconseq2(int nameconseq2) {
		this.nameconseq2 = nameconseq2;
	}
	public String getNameconnm2() {
		return nameconnm2;
	}
	public void setNameconnm2(String nameconnm2) {
		this.nameconnm2 = nameconnm2;
	}
	public String getNameconurl2() {
		if(nameconurl2 == null || "".equals(nameconurl2)) 
			nameconurl2 = "/images/pc/namecon/pc_namecon_05.png";
		
		return nameconurl2;
	}
	public void setNameconurl2(String nameconurl2) {
		if(nameconurl2 != null && (nameconurl2.indexOf("/images/") < 0 && nameconurl2.indexOf("http") < 0))
			nameconurl2 = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/namecon"+nameconurl2;
		
		this.nameconurl2 = nameconurl2;
	}
	public String getMnameconurl2() {
		if(mnameconurl2 == null || "".equals(mnameconurl2)) 
			mnameconurl2 = "/images/mobile/namecon/pc_namecon_05.png";
		
		return mnameconurl2;
	}
	public void setMnameconurl2(String mnameconurl2) {
		if(mnameconurl2 != null && (mnameconurl2.indexOf("/images/") < 0 && mnameconurl2.indexOf("http") < 0))
			mnameconurl2 = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/namecon"+mnameconurl2;
		
		this.mnameconurl2 = mnameconurl2;
	}
	public String getUsetermsyn() {
		return usetermsyn;
	}
	public void setUsetermsyn(String usetermsyn) {
		this.usetermsyn = usetermsyn;
	}
	public String getUseagreementdt() {
		return useagreementdt;
	}
	public void setUseagreementdt(String useagreementdt) {
		this.useagreementdt = useagreementdt;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getLoginyn() {
		return loginyn;
	}
	public void setLoginyn(String loginyn) {
		this.loginyn = loginyn;
	}
	
	
				
}