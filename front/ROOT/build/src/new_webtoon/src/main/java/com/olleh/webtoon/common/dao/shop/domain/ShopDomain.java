package com.olleh.webtoon.common.dao.shop.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class ShopDomain {
	
	private int prdseq; 				//상품순번
	private String prdhistoryseq;			//상품이력순번
	private int prdterm; 				//상품사용가능기간(단위:일)
	private int startRowNo;				//시작순번
	private int pageSize;				//페이지사이즈
	private String prdfg; 				//상품구분(webtoon:작품, times:회차, name:네임콘, comment:댓글콘, thema:테마)
	private String prdnm;				//상품명
	private String sellyn;				//판매여부(Y/N)
	private String sellfg;				//판매방식구분(default:기본, sale:할인, sell:일반, event:이벤트)
	private String purchasefg;	 		//구매방식구분(blue:블루베리 berry:일반베리 mix:복합)
	private String recommyn; 			//추천여부(Y/N)
	private String discountyn; 			//할인여부(Y/N)
	private String prdprice; 			//상품가격(단위:베리)
	private String prddiscountprice; 	//상품할인가격(단위:베리)
	private String sellstartdt; 		//판매시작일시(YYYYMMDDHHMISS)
	private String sellenddt; 			//판매종료일시(YYYYMMDDHHMISS)
	private String discountsellstartdt; //할인판매시작일시,프로모션판매시작일시(YYYYMMDDHHMISS)
	private String discountsellenddt;   //할인판매종료일시,프로모션판매종료일시(YYYYMMDDHHMISS)
	private String thumbpath; 			//썸네일경로
	private String thumbfilenm; 		//썸네일파일명
	private String thumbactpath; 		//활성썸네일경로
	private String thumbactfilenm;		//활성썸네일파일명
	private String thumbinactpath;		//비활성썸네일경로
	private String thumbinactfilenm;	//비활성썸네일파일명
	private int refwebtoonseq1;			//연관작품순번1
	private int refwebtoonseq2;			//연관작품순번2
	private int refwebtoonseq3;			//연관작품순번3
	private int refauthorseq1;			//연관작가순번1
	private int refauthorseq2;			//연관작가순번2
	private int refauthorseq3;			//연관작가순번3
	private String authornm1;	      	//연관작가이름1
	private String authornm2;   	   	//연관작가이름1
	private String authornm3;      		//연관작가이름1
	private String order;			    //정렬 구분값
	private String category;			//카테고리(연관작가:authory 연관작품:webtoon)
	private int categoryseq;            //연관작가, 연관작품 seq
	private String shopfg;              //추천, 네임콘, 스티콘 구분자
	private String discountpercent;     //할인율 
	private String purchaseyn;          //구매여부(Y/N)
	private String buyid;               //구매아이디
	private String refauthorseqs;       //연관작가seq 목록
	private String refwebtoonseqs;      //연관작품seq 목록
	private String mobileyn;            //모바일 여부
	private String query; 
	private String promotionyn;         //프로모션 여부
	private String categoryprdfg; 		//연관 아이템 리스트/메인 구분
	
	public int getPrdseq() {
		return prdseq;
	}
	public void setPrdseq(int prdseq) {
		this.prdseq = prdseq;
	}
	public String getPrdhistoryseq() {
		return prdhistoryseq;
	}
	public void setPrdhistoryseq(String prdhistoryseq) {
		this.prdhistoryseq = prdhistoryseq;
	}
	public int getPrdterm() {
		return prdterm;
	}
	public void setPrdterm(int prdterm) {
		this.prdterm = prdterm;
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
	public String getPrdnm() {
		return prdnm;
	}
	public void setPrdnm(String prdnm) {
		this.prdnm = prdnm;
	}
	public String getSellyn() {
		return sellyn;
	}
	public void setSellyn(String sellyn) {
		this.sellyn = sellyn;
	}
	public String getSellfg() {
		return sellfg;
	}
	public void setSellfg(String sellfg) {
		this.sellfg = sellfg;
	}
	public String getRecommyn() {
		return recommyn;
	}
	public void setRecommyn(String recommyn) {
		this.recommyn = recommyn;
	}
	public String getDiscountyn() {
		return discountyn;
	}
	public void setDiscountyn(String discountyn) {
		this.discountyn = discountyn;
	}
	public String getPrdprice() {
		return prdprice;
	}
	public void setPrdprice(String prdprice) {
		this.prdprice = prdprice;
	}
	public String getPrddiscountprice() {
		return prddiscountprice;
	}
	public void setPrddiscountprice(String prddiscountprice) {
		this.prddiscountprice = prddiscountprice;
	}
	public String getSellstartdt() {
		return sellstartdt;
	}
	public void setSellstartdt(String sellstartdt) {
		this.sellstartdt = sellstartdt;
	}
	public String getSellenddt() {
		return sellenddt;
	}
	public void setSellenddt(String sellenddt) {
		this.sellenddt = sellenddt;
	}
	public String getDiscountsellstartdt() {
		return discountsellstartdt;
	}
	public void setDiscountsellstartdt(String discountsellstartdt) {
		this.discountsellstartdt = discountsellstartdt;
	}
	public String getDiscountsellenddt() {
		return discountsellenddt;
	}
	public void setDiscountsellenddt(String discountsellenddt) {
		this.discountsellenddt = discountsellenddt;
	}
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0)
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+thumbpath;
		
		return thumbpath;
	}
	public void setThumbpath(String thumbpath) {
		this.thumbpath = thumbpath;
	}
	public String getThumbfilenm() {
		return thumbfilenm;
	}
	public void setThumbfilenm(String thumbfilenm) {
		this.thumbfilenm = thumbfilenm;
	}
	public String getThumbactpath() {
		if(thumbactpath != null && thumbactpath.indexOf("http") < 0)
			thumbactpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+thumbactpath;
		
		return thumbactpath;
	}
	public void setThumbactpath(String thumbactpath) {
		this.thumbactpath = thumbactpath;
	}
	public String getThumbactfilenm() {
		return thumbactfilenm;
	}
	public void setThumbactfilenm(String thumbactfilenm) {
		this.thumbactfilenm = thumbactfilenm;
	}
	public String getThumbinactpath() {
		if(thumbinactpath != null && thumbinactpath.indexOf("http") < 0)
			thumbinactpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+thumbinactpath;
		
		return thumbinactpath;
	}
	public void setThumbinactpath(String thumbinactpath) {
		this.thumbinactpath = thumbinactpath;
	}
	public String getThumbinactfilenm() {
		return thumbinactfilenm;
	}
	public void setThumbinactfilenm(String thumbinactfilenm) {
		this.thumbinactfilenm = thumbinactfilenm;
	}
	public int getRefwebtoonseq1() {
		return refwebtoonseq1;
	}
	public void setRefwebtoonseq1(int refwebtoonseq1) {
		this.refwebtoonseq1 = refwebtoonseq1;
	}
	public int getRefwebtoonseq2() {
		return refwebtoonseq2;
	}
	public void setRefwebtoonseq2(int refwebtoonseq2) {
		this.refwebtoonseq2 = refwebtoonseq2;
	}
	public int getRefwebtoonseq3() {
		return refwebtoonseq3;
	}
	public void setRefwebtoonseq3(int refwebtoonseq3) {
		this.refwebtoonseq3 = refwebtoonseq3;
	}
	public int getRefauthorseq1() {
		return refauthorseq1;
	}
	public void setRefauthorseq1(int refauthorseq1) {
		this.refauthorseq1 = refauthorseq1;
	}
	public int getRefauthorseq2() {
		return refauthorseq2;
	}
	public void setRefauthorseq2(int refauthorseq2) {
		this.refauthorseq2 = refauthorseq2;
	}
	public int getRefauthorseq3() {
		return refauthorseq3;
	}
	public void setRefauthorseq3(int refauthorseq3) {
		this.refauthorseq3 = refauthorseq3;
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
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getShopfg() {
		return shopfg;
	}
	public void setShopfg(String shopfg) {
		this.shopfg = shopfg;
	}
	public String getDiscountpercent() {
		return discountpercent;
	}
	public void setDiscountpercent(String discountpercent) {
		this.discountpercent = discountpercent;
	}
	public String getPurchaseyn() {
		return purchaseyn;
	}
	public void setPurchaseyn(String purchaseyn) {
		this.purchaseyn = purchaseyn;
	}
	public String getBuyid() {
		return buyid;
	}
	public void setBuyid(String buyid) {
		this.buyid = buyid;
	}
	public String getRefauthorseqs() {
		return refauthorseqs;
	}
	public void setRefauthorseqs(String refauthorseqs) {
		this.refauthorseqs = refauthorseqs;
	}
	public String getRefwebtoonseqs() {
		return refwebtoonseqs;
	}
	public void setRefwebtoonseqs(String refwebtoonseqs) {
		this.refwebtoonseqs = refwebtoonseqs;
	}
	public String getMobileyn() {
		return mobileyn;
	}
	public void setMobileyn(String mobileyn) {
		this.mobileyn = mobileyn;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public int getCategoryseq() {
		return categoryseq;
	}
	public void setCategoryseq(int categoryseq) {
		this.categoryseq = categoryseq;
	}
	public String getCategoryprdfg() {
		return categoryprdfg;
	}
	public void setCategoryprdfg(String categoryprdfg) {
		this.categoryprdfg = categoryprdfg;
	}
	public String getPromotionyn() {
		return promotionyn;
	}
	public void setPromotionyn(String promotionyn) {
		this.promotionyn = promotionyn;
	}
	public String getPurchasefg() {
		return purchasefg;
	}
	public void setPurchasefg(String purchasefg) {
		this.purchasefg = purchasefg;
	}
	
}