package com.olleh.webtoon.common.dao.applay.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class RecommendAppDomain {
	
	private int startRowNo;         //해당페이지 시작 Row 번호(페이지크기가 10일 경우 1페이지는 0, 2페이지는 10, 3페이지는 20)
	private int pageSize;       	//페이지크기
	private int appseq;         	//추천앱 순번
	private int rdisplayorder;  	//최신앱전시순서
	private String apptitle;    	//앱 제목
	private String category1;   	//1차 카테고리
	private String category2;   	//2차 카테고리
	private String sellernm;   		//판매사명
	private String price;   		//가격
	private String thumbpath;   	//썸네일 파일경로
	private String thumbfilenm; 	//썸네일 파일명
	private String linkurl;   		//올레마PC링크
	private String mlinkurl;		//모바일 링크
	private String installurl;		//설치 링크
	private String rdisplayyn;   	//최신앱전시여부
	private String rdirectdisplayyn;//최신앱즉시전시여부
	private String rstartdt;   		//최신앱전시시작일
	private String pdisplayyn;     	//인기앱전시시작일
	private int popularityorder;   	//인기앱즉시전시여부
	private String pstartdt;   		//인기앱전시시작일시
	private String contents;		//상세설명
	private String screenshotpath01;//스크린샷 경로01
	private String screenshotpath02;//스크린샷 경로02
	private String screenshotpath03;//스크린샷 경로03
	private String screenshotpath04;//스크린샷 경로04
	private String screenshotpath05;//스크린샷 경로05
	private String grade;           //등급 
	private String filesize;        //파일용량
	private String freeyn;          //무료여부
	private String mobileyn = "N";  //모바일 여부
	
	
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
	public int getAppseq() {
		return appseq;
	}
	public void setAppseq(int appseq) {
		this.appseq = appseq;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getScreenshotpath01() {
		if(screenshotpath01 != null && !"".equals(screenshotpath01) &&screenshotpath01.indexOf("http") < 0)
			screenshotpath01 = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/rcdapp"+screenshotpath01;
		
		return screenshotpath01;
	}
	public void setScreenshotpath01(String screenshotpath01) {
		this.screenshotpath01 = screenshotpath01;
	}
	public String getScreenshotpath02() {
		if(screenshotpath02 != null && !"".equals(screenshotpath02) && screenshotpath02.indexOf("http") < 0)
			screenshotpath02 = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/rcdapp"+screenshotpath02;
		
		return screenshotpath02;
	}
	public void setScreenshotpath02(String screenshotpath02) {
		this.screenshotpath02 = screenshotpath02;
	}
	public String getScreenshotpath03() {
		if(screenshotpath03 != null && !"".equals(screenshotpath03) && screenshotpath03.indexOf("http") < 0)
			screenshotpath03 = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/rcdapp"+screenshotpath03;
		
		return screenshotpath03;
	}
	public void setScreenshotpath03(String screenshotpath03) {
		this.screenshotpath03 = screenshotpath03;
	}
	public String getScreenshotpath04() {
		if(screenshotpath04 != null && !"".equals(screenshotpath04) && screenshotpath04.indexOf("http") < 0)
			screenshotpath04 = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/rcdapp"+screenshotpath04;
		
		return screenshotpath04;
	}
	public void setScreenshotpath04(String screenshotpath04) {
		this.screenshotpath04 = screenshotpath04;
	}
	public String getScreenshotpath05() {
		if(screenshotpath05 != null && !"".equals(screenshotpath05) && screenshotpath05.indexOf("http") < 0)
			screenshotpath05 = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/rcdapp"+screenshotpath05;
		
		return screenshotpath05;
	}
	public void setScreenshotpath05(String screenshotpath05) {
		this.screenshotpath05 = screenshotpath05;
	}
	public String getApptitle() {
		return apptitle;
	}
	public void setApptitle(String apptitle) {
		this.apptitle = apptitle;
	}
	public String getCategory1() {
		return category1;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public String getCategory2() {
		return category2;
	}
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	public String getSellernm() {
		return sellernm;
	}
	public void setSellernm(String sellernm) {
		this.sellernm = sellernm;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0)
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/rcdapp"+thumbpath;
		
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
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
	public String getMlinkurl() {
		return mlinkurl;
	}
	public void setMlinkurl(String mlinkurl) {
		this.mlinkurl = mlinkurl;
	}
	public String getInstallurl() {
		return installurl;
	}
	public void setInstallurl(String installurl) {
		this.installurl = installurl;
	}
	public int getRdisplayorder() {
		return rdisplayorder;
	}
	public void setRdisplayorder(int rdisplayorder) {
		this.rdisplayorder = rdisplayorder;
	}
	public String getRdisplayyn() {
		return rdisplayyn;
	}
	public void setRdisplayyn(String rdisplayyn) {
		this.rdisplayyn = rdisplayyn;
	}
	public String getRdirectdisplayyn() {
		return rdirectdisplayyn;
	}
	public void setRdirectdisplayyn(String rdirectdisplayyn) {
		this.rdirectdisplayyn = rdirectdisplayyn;
	}
	public String getRstartdt() {
		return rstartdt;
	}
	public void setRstartdt(String rstartdt) {
		this.rstartdt = rstartdt;
	}
	public String getPdisplayyn() {
		return pdisplayyn;
	}
	public void setPdisplayyn(String pdisplayyn) {
		this.pdisplayyn = pdisplayyn;
	}
	public int getPopularityorder() {
		return popularityorder;
	}
	public void setPopularityorder(int popularityorder) {
		this.popularityorder = popularityorder;
	}
	public String getPstartdt() {
		return pstartdt;
	}
	public void setPstartdt(String pstartdt) {
		this.pstartdt = pstartdt;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public String getFreeyn() {
		return freeyn;
	}
	public void setFreeyn(String freeyn) {
		this.freeyn = freeyn;
	}
	public String getMobileyn() {
		return mobileyn;
	}
	public void setMobileyn(String mobileyn) {
		this.mobileyn = mobileyn;
	}
}