package com.olleh.webtoon.common.dao.orderbuy.domain;

import com.olleh.webtoon.common.util.MessageUtil;

// 구매내역 (ow_buy)
public class BuyDomain {

	private int buyseq;			  	// 구매순서 (PK, 자동증가)
	private int prdhistoryseq;	  	// 상품이력순서
	private int orgprdbuyseq;	  	// 원구매순번
	private String toonfg;		  	// 작품구분 (toon:웹툰, novel:소설)
	private String agefg;
	private String buyfg;		  	// 구매구분 (order:구매, refund:환불, cancel:취소)
	private String buyid;		  	// 구매자ID
	private String idfg;		  	// 구매자ID구분 (open:오픈ID, olleh:올레ID)
	private String buyamount;	  	// 베리구매금액
	private String buyblueamount; 	// 블루베리구매금액
	private String buyraspamount; 	// 라즈베리구매금액
	private String buydt;		  	// 구매일시 (YYYYMMDDHHMISS)
	private String substrbuydt;	  	// 구매일시 (YYYYMMDD)
	private String regid;		  	// 등록자ID
	private String regdt;		  	// 등록일시 (YYYYMMDDHHMISS)
	
	private String prdfg;		  	// 상품구분(all:전체, times:회차)
	private String prdtypefg;		// 상품구분(all:전체, times:회차)
	private int prdterm;		  	// 상품사용기간
	private long expired;		  	// 상품사용만료여부
	private String expiredt;	  	// 상품사용만료일 (YYYYMMDD)
	private int timesseq;		  	// 구매금액
	private String timestitle;	  	// 회차 제목
	private String tthumbpath;	  	// 회차 썸네일 경로
	private String tthumbpath1;	  	// 신규회차 썸네일 경로
	private int webtoonseq;		  	// 구매금액
	private String webtoonnm;	  	// 웹툰 제목
	private String wthumbpath;	  	// 웹툰 썸네일 경로
	private String initialword;	  	// 정렬대상
	private String sort;		  	// 정렬기준
	private String thumbpath;	  	// 상품썸네일
	private String prdnm;		  	// 상품명
	private int prdseq;			  	// 상품번호
	private String purchasefg;    	// 상품 구배방식 구분자
	private String berryuseyn;    	// 베리상품 여부 구분자
	private String blueberryuseyn;  // 블루베리상품 여부 구분자
	private String raspberryuseyn;  // 라즈베리상품 여부 구분자
	
	private int startRowNo;  	  	// 해당페이지 시작 Row 번호(페이지크기가 10일 경우 1페이지는 0, 2페이지는 10, 3페이지는 20)
	private int pageSize;    	  	// 페이지크기
	
	private String blueprdname;	  	//블루멤버십 상품 이름
	private int ownblueberry;	  	//보유 블루베리
	private int validblueamount;  	//정산블루베리
	private int invalidblueamount; 	//비정산 블루베리
	
	public int getBuyseq() {
		return buyseq;
	}
	public void setBuyseq(int buyseq) {
		this.buyseq = buyseq;
	}
	public int getPrdhistoryseq() {
		return prdhistoryseq;
	}
	public void setPrdhistoryseq(int prdhistoryseq) {
		this.prdhistoryseq = prdhistoryseq;
	}
	public int getOrgprdbuyseq() {
		return orgprdbuyseq;
	}
	public void setOrgprdbuyseq(int orgprdbuyseq) {
		this.orgprdbuyseq = orgprdbuyseq;
	}	
	public String getToonfg() {
		return toonfg;
	}
	public void setToonfg(String toonfg) {
		this.toonfg = toonfg;
	}
	public String getBuyfg() {
		return buyfg;
	}
	public void setBuyfg(String buyfg) {
		this.buyfg = buyfg;
	}
	public String getBuyid() {
		return buyid;
	}
	public void setBuyid(String buyid) {
		this.buyid = buyid;
	}
	public String getIdfg() {
		return idfg;
	}
	public void setIdfg(String idfg) {
		this.idfg = idfg;
	}
	public String getBuyamount() {
		return buyamount;
	}
	public void setBuyamount(String buyamount) {
		this.buyamount = buyamount;
	}
	public String getBuydt() {
		return buydt;
	}
	public void setBuydt(String buydt) {
		this.buydt = buydt;
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
	public String getPrdfg() {
		return prdfg;
	}
	public void setPrdfg(String prdfg) {
		this.prdfg = prdfg;
	}
	public int getPrdterm() {
		return prdterm;
	}
	public void setPrdterm(int prdterm) {
		this.prdterm = prdterm;
	}
	public String getTimestitle() {
		return timestitle;
	}
	public void setTimestitle(String timestitle) {
		this.timestitle = timestitle;
	}
	public String getTthumbpath() 
	{
		if(tthumbpath != null && tthumbpath.indexOf("http") < 0)
			tthumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+tthumbpath;
		
		return tthumbpath;
	}
	public void setTthumbpath(String tthumbpath) {
		this.tthumbpath = tthumbpath;
	}	
	public String getTthumbpath1() {
		if(tthumbpath1 != null && tthumbpath1.indexOf("http") < 0)
			tthumbpath1 = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+tthumbpath1;
		
		return tthumbpath1;
	}
	public void setTthumbpath1(String tthumbpath1) {
		this.tthumbpath1 = tthumbpath1;
	}
	public String getWebtoonnm() {
		return webtoonnm;
	}
	public void setWebtoonnm(String webtoonnm) {
		this.webtoonnm = webtoonnm;
	}
	public String getWthumbpath() 
	{
		if(wthumbpath != null && wthumbpath.indexOf("http") < 0)
			wthumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+wthumbpath;
		
		return wthumbpath;
	}
	public void setWthumbpath(String wthumbpath) {
		this.wthumbpath = wthumbpath;
	}
	public String getExpiredt() {
		return expiredt;
	}
	public void setExpiredt(String expiredt) {
		this.expiredt = expiredt;
	}	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getInitialword() {
		return initialword;
	}
	public void setInitialword(String initialword) {
		this.initialword = initialword;
	}
	public int getTimesseq() {
		return timesseq;
	}
	public void setTimesseq(int timesseq) {
		this.timesseq = timesseq;
	}
	public int getWebtoonseq() {
		return webtoonseq;
	}
	public void setWebtoonseq(int webtoonseq) {
		this.webtoonseq = webtoonseq;
	}
	public long getExpired() 
	{
//		if( Integer.valueOf(expired) < 0 )
//			expired = "Y";
//		else
//			expired = "N";
		
		return expired;
	}
	public void setExpired(long expired) {
		this.expired = expired;
	}	
	public String getSubstrbuydt() {
		return substrbuydt;
	}
	public void setSubstrbuydt(String substrbuydt) {
		this.substrbuydt = substrbuydt;
	}
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0)
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+thumbpath;
		
		return thumbpath;
	}
	public void setThumbpath(String thumbpath) {
		this.thumbpath = thumbpath;
	}
	public String getPrdnm() {
		return prdnm;
	}
	public void setPrdnm(String prdnm) {
		this.prdnm = prdnm;
	}
	public int getPrdseq() {
		return prdseq;
	}
	public void setPrdseq(int prdseq) {
		this.prdseq = prdseq;
	}
	public String getBuyblueamount() {
		return buyblueamount;
	}
	public void setBuyblueamount(String buyblueamount) {
		this.buyblueamount = buyblueamount;
	}
	public String getPurchasefg() {
		return purchasefg;
	}
	public void setPurchasefg(String purchasefg) {
		this.purchasefg = purchasefg;
	}
	public String getBlueprdname() {
		return blueprdname;
	}
	public void setBlueprdname(String blueprdname) {
		this.blueprdname = blueprdname;
	}
	public int getOwnblueberry() {
		return ownblueberry;
	}
	public void setOwnblueberry(int ownblueberry) {
		this.ownblueberry = ownblueberry;
	}
	public int getValidblueamount() {
		return validblueamount;
	}
	public void setValidblueamount(int validblueamount) {
		this.validblueamount = validblueamount;
	}
	public int getInvalidblueamount() {
		return invalidblueamount;
	}
	public void setInvalidblueamount(int invalidblueamount) {
		this.invalidblueamount = invalidblueamount;
	}
	public String getBuyraspamount() {
		return buyraspamount;
	}
	public void setBuyraspamount(String buyraspamount) {
		this.buyraspamount = buyraspamount;
	}
	public String getBerryuseyn() {
		return berryuseyn;
	}
	public void setBerryuseyn(String berryuseyn) {
		this.berryuseyn = berryuseyn;
	}
	public String getBlueberryuseyn() {
		return blueberryuseyn;
	}
	public void setBlueberryuseyn(String blueberryuseyn) {
		this.blueberryuseyn = blueberryuseyn;
	}
	public String getRaspberryuseyn() {
		return raspberryuseyn;
	}
	public void setRaspberryuseyn(String raspberryuseyn) {
		this.raspberryuseyn = raspberryuseyn;
	}
	/**
	 * @return the prdtypefg
	 */
	public String getPrdtypefg() {
		return prdtypefg;
	}
	/**
	 * @param prdtypefg the prdtypefg to set
	 */
	public void setPrdtypefg(String prdtypefg) {
		this.prdtypefg = prdtypefg;
	}
	public String getAgefg() {
		return agefg;
	}
	public void setAgefg(String agefg) {
		this.agefg = agefg;
	}
	
}	
