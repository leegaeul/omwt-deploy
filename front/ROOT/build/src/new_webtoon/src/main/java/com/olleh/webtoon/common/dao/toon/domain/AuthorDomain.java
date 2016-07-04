/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : AuthorDomain.java
 * DESCRIPTION    : 웹툰 작가 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-23      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.toon.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class AuthorDomain {
	private String authorseq    ;
	private String authornm     ;
	private String nickname     ;
	private String authordesc   ;
	private String blogurl      ;
	private String facebookurl  ;
	private String twitterurl   ;
	private String kakaourl     ;
	private String imagepath    ;
	private String imagefilenm  ;
	private String mimagepath   ;
	private String mimagefilenm ;
	private String displayyn    ;
	private String regid        ;
	private String regdt        ;
	private String modid        ;
	private String moddt        ;
	private String nameconurl   ;
	private String mnameconurl  ;
	private int startRowNo;
	private int pageSize;			
	private String profileyn;
	private String areafg;
	
	public String getAuthorseq() {
		return authorseq;
	}
	public void setAuthorseq(String authorseq) {
		this.authorseq = authorseq;
	}
	public String getAuthornm() {
		return authornm;
	}
	public void setAuthornm(String authornm) {
		this.authornm = authornm;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAuthordesc() {
		return authordesc;
	}
	public void setAuthordesc(String authordesc) {
		this.authordesc = authordesc;
	}
	public String getBlogurl() {
		return blogurl;
	}
	public void setBlogurl(String blogurl) {
		this.blogurl = blogurl;
	}
	public String getFacebookurl() {
		return facebookurl;
	}
	public void setFacebookurl(String facebookurl) {
		this.facebookurl = facebookurl;
	}
	public String getTwitterurl() {
		return twitterurl;
	}
	public void setTwitterurl(String twitterurl) {
		this.twitterurl = twitterurl;
	}
	public String getKakaourl() {
		return kakaourl;
	}
	public void setKakaourl(String kakaourl) {
		this.kakaourl = kakaourl;
	}
	public String getImagepath() {
		if(imagepath != null && imagepath.indexOf("http") < 0)
			imagepath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/author"+imagepath;
		
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getImagefilenm() {
		return imagefilenm;
	}
	public void setImagefilenm(String imagefilenm) {
		this.imagefilenm = imagefilenm;
	}
	public String getMimagepath() {
		return mimagepath;
	}
	public void setMimagepath(String mimagepath) {
		this.mimagepath = mimagepath;
	}
	public String getMimagefilenm() {
		return mimagefilenm;
	}
	public void setMimagefilenm(String mimagefilenm) {
		this.mimagefilenm = mimagefilenm;
	}
	public String getDisplayyn() {
		return displayyn;
	}
	public void setDisplayyn(String displayyn) {
		this.displayyn = displayyn;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getModid() {
		return modid;
	}
	public void setModid(String modid) {
		this.modid = modid;
	}
	public String getModdt() {
		return moddt;
	}
	public void setModdt(String moddt) {
		this.moddt = moddt;
	}
	public String getNameconurl() {
		return nameconurl;
	}
	public void setNameconurl(String nameconurl) {
		this.nameconurl = nameconurl;
	}
	public String getMnameconurl() {
		return mnameconurl;
	}
	public void setMnameconurl(String mnameconurl) {
		this.mnameconurl = mnameconurl;
	}
	public int getStartRowNo() {
		return startRowNo;
	}
	public void setStartRowNo(int startRowNo) {
		this.startRowNo = startRowNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getProfileyn() {
		return profileyn;
	}
	public void setProfileyn(String profileyn) {
		this.profileyn = profileyn;
	}
	public String getAreafg() {
		return areafg;
	}
	public void setAreafg(String areafg) {
		this.areafg = areafg;
	}
	
}