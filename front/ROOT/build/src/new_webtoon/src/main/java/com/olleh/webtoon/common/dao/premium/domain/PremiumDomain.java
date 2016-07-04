package com.olleh.webtoon.common.dao.premium.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class PremiumDomain {

	private int startRowNo;	     //시작 순번
	private int pageSize;        //페이지 사이즈
	private int categoryseq;	 //카테고리순번 (PK, 자동증가)
	private int categorycnt;     //해당 카테고리 프리미엄 갯수
	private String categorynm;	 //카테고리명
	private String categorydesc; //카테고리설명
	private String displayyn;	 //전시여부 (Y/N)
	private String displayorder; //전시순서
	private String regid;	     //등록한 운영자ID
	private String regdt;		 //등록일시 (YYYYMMDDHHMMSS)
	private String thumbpath;    //썸네일 경로
	private String webtoonnm;    //웹툰 제목
	private String authornm1;    //작가1
	private String authornm2;    //작가2
	private String authornm3;    //작가3
	private String webtoonseq;   //작품 순번
	private String upyn;         //업데이트 여부
	private String endyn;		 //완결 여부
	private String serialfg;     //완결 여부
	private String restyn;       //휴재 여부
	private String mainyn;       //메인 여부
	private String mondayyn;
	private String tuesdayyn;
	private String wednesdayyn;
	private String thursdayyn;
	private String fridayyn;
	private String saturdayyn;
	private String sundayyn;
	private String oniconurl;
	private String officonurl;
	private String listiconurl;
	private String defaultyn;
	private String agefg;
	private String newyn;
	private String sumtotalstickercnt;
	private String mobileyn;

	private String sellyn;
	private String discountyn;
	private String discountpercent;
	private String toonfg;
	private String genrenm1;
	private String genrenm2;
	private String genrenm3;
	private String nthumbpath;
	
	public int getCategoryseq() {
		return categoryseq;
	}
	public void setCategoryseq(int categoryseq) {
		this.categoryseq = categoryseq;
	}
	public String getCategorynm() {
		return categorynm;
	}
	public void setCategorynm(String categorynm) {
		this.categorynm = categorynm;
	}
	public String getCategorydesc() {
		return categorydesc;
	}
	public void setCategorydesc(String categorydesc) {
		this.categorydesc = categorydesc;
	}
	public String getDisplayyn() {
		return displayyn;
	}
	public void setDisplayyn(String displayyn) {
		this.displayyn = displayyn;
	}
	public String getDisplayorder() {
		return displayorder;
	}
	public void setDisplayorder(String displayorder) {
		this.displayorder = displayorder;
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
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0)
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+thumbpath;
		
		return thumbpath;
	}
	public void setThumbpath(String thumbpath) {
		this.thumbpath = thumbpath;
	}
	public String getWebtoonnm() {
		return webtoonnm;
	}
	public void setWebtoonnm(String webtoonnm) {
		this.webtoonnm = webtoonnm;
	}
	public String getAuthornm1() {
		return authornm1;
	}
	public void setAuthornm1(String authornm1) {
		this.authornm1 = authornm1;
	}
	public String getAuthornm2() {
		return authornm2;
	}
	public void setAuthornm2(String authornm2) {
		this.authornm2 = authornm2;
	}
	public String getAuthornm3() {
		return authornm3;
	}
	public void setAuthornm3(String authornm3) {
		this.authornm3 = authornm3;
	}
	public String getSumtotalstickercnt() {
		return sumtotalstickercnt;
	}
	public void setSumtotalstickercnt(String sumtotalstickercnt) {
		this.sumtotalstickercnt = sumtotalstickercnt;
	}
	public String getOniconurl() {
		return oniconurl;
	}
	public void setOniconurl(String oniconurl) {
		this.oniconurl = oniconurl;
	}
	public String getOfficonurl() {
		return officonurl;
	}
	public void setOfficonurl(String officonurl) {
		this.officonurl = officonurl;
	}
	public String getListiconurl() {	
		if(listiconurl != null && listiconurl.indexOf("http") < 0 && defaultyn!= null && "N".equals(defaultyn))
			listiconurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/sticker"+listiconurl;
		
		return listiconurl;
	}
	public void setListiconurl(String listiconurl) {
		this.listiconurl = listiconurl;
	}
	public String getDefaultyn() {
		return defaultyn;
	}
	public void setDefaultyn(String defaultyn) {
		this.defaultyn = defaultyn;
	}
	public String getAgefg() {
		return agefg;
	}
	public void setAgefg(String agefg) {
		this.agefg = agefg;
	}
	public String getNewyn() {
		return newyn;
	}
	public void setNewyn(String newyn) {
		this.newyn = newyn;
	}
	public String getWebtoonseq() {
		return webtoonseq;
	}
	public void setWebtoonseq(String webtoonseq) {
		this.webtoonseq = webtoonseq;
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
	public int getCategorycnt() {
		return categorycnt;
	}
	public void setCategorycnt(int categorycnt) {
		this.categorycnt = categorycnt;
	}
	public String getUpyn() {
		return upyn;
	}
	public void setUpyn(String upyn) {
		this.upyn = upyn;
	}
	public String getMondayyn() {
		return mondayyn;
	}
	public void setMondayyn(String mondayyn) {
		this.mondayyn = mondayyn;
	}
	public String getTuesdayyn() {
		return tuesdayyn;
	}
	public void setTuesdayyn(String tuesdayyn) {
		this.tuesdayyn = tuesdayyn;
	}
	public String getWednesdayyn() {
		return wednesdayyn;
	}
	public void setWednesdayyn(String wednesdayyn) {
		this.wednesdayyn = wednesdayyn;
	}
	public String getThursdayyn() {
		return thursdayyn;
	}
	public void setThursdayyn(String thursdayyn) {
		this.thursdayyn = thursdayyn;
	}
	public String getFridayyn() {
		return fridayyn;
	}
	public void setFridayyn(String fridayyn) {
		this.fridayyn = fridayyn;
	}
	public String getSaturdayyn() {
		return saturdayyn;
	}
	public void setSaturdayyn(String saturdayyn) {
		this.saturdayyn = saturdayyn;
	}
	public String getSundayyn() {
		return sundayyn;
	}
	public void setSundayyn(String sundayyn) {
		this.sundayyn = sundayyn;
	}
	public String getSerialfg() {
		return serialfg;
	}
	public void setSerialfg(String serialfg) {
		this.serialfg = serialfg;
	}
	public String getRestyn() {
		return restyn;
	}
	public void setRestyn(String restyn) {
		this.restyn = restyn;
	}
	public String getMainyn() {
		return mainyn;
	}
	public void setMainyn(String mainyn) {
		this.mainyn = mainyn;
	}
	public String getMobileyn() {
		return mobileyn;
	}
	public void setMobileyn(String mobileyn) {
		this.mobileyn = mobileyn;
	}
	public String getSellyn() {
		return sellyn;
	}
	public void setSellyn(String sellyn) {
		this.sellyn = sellyn;
	}
	public String getDiscountyn() {
		return discountyn;
	}
	public void setDiscountyn(String discountyn) {
		this.discountyn = discountyn;
	}
	public String getDiscountpercent() {
		return discountpercent;
	}
	public void setDiscountpercent(String discountpercent) {
		this.discountpercent = discountpercent;
	}
	public String getToonfg() {
		return toonfg;
	}
	public void setToonfg(String toonfg) {
		this.toonfg = toonfg;
	}
	public String getGenrenm1() {
		return genrenm1;
	}
	public void setGenrenm1(String genrenm1) {
		this.genrenm1 = genrenm1;
	}
	public String getGenrenm2() {
		return genrenm2;
	}
	public void setGenrenm2(String genrenm2) {
		this.genrenm2 = genrenm2;
	}
	public String getGenrenm3() {
		return genrenm3;
	}
	public void setGenrenm3(String genrenm3) {
		this.genrenm3 = genrenm3;
	}
	public String getNthumbpath() {
		if(nthumbpath != null && nthumbpath.indexOf("http") < 0)
			nthumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+nthumbpath;
		return nthumbpath;
	}
	public void setNthumbpath(String nthumbpath) {
		this.nthumbpath = nthumbpath;
	}
	public String getEndyn() {
		return endyn;
	}
	public void setEndyn(String endyn) {
		this.endyn = endyn;
	}
}