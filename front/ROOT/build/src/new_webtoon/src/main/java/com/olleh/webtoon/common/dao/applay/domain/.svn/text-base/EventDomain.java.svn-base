package com.olleh.webtoon.common.dao.applay.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class EventDomain {
	
	private int eventseq;				//이벤트 순번
	private int readcnt;				//조회수
	private int startRowNo;           	//해당페이지 시작 Row 번호(페이지크기가 10일 경우 1페이지는 0, 2페이지는 10, 3페이지는 20)
	private int pageSize;       	    //페이지크기
	private String title;				//제목
	private String subtitle;			//설명
	private String eventyn;				//이벤트여부(Y:이벤트, N:당첨자발표)
	private String eventstartdt;		//이벤트시작일자
	private String eventenddt;			//이벤트종료일자
	private String mthumbpath;          //모바일썸네일풀경로
	private String thumbpath;			//썸네일풀경로
	private String regfg;				//본문등록방식(url:iframe에 HTML URL 호출, self:이미지와 텍스트로 직접구성)
	private String displayfg;           //전시방법구분(banner:배너형, list:목록형)
	private String popyn;               //PC컨텐츠URL새창여부(Y/N)
	private String mpopyn;              //모바일컨텐츠URL새창여부(Y/N)
	private String urllink;             //본문URL링크
	private String contentsurl;			//PC컨텐츠URL(regfg가 url일 경우 PC버전 iframe에 보여줄 컨텐츠 URL)
	private String mcontentsurl;        //모바일컨텐츠URL(regfg가 url일 경우 모바일버전 iframe에 보여줄 컨텐츠 URL)
	private String contents;		    //본문컨텐츠(regfg가 self일 경우 이미지 하단텍스트)
	private String eventendyn;			//이벤트종료여부
	private String mobileyn = "N";      //모바일여부
//	private String displayyn;			//PC전시여부
//	private String directdisplayyn;		//PC즉시전시여부
//	private String directdisplayenddt;	//PC즉시전시종료일시
//	private String startdt;				//PC예약전시시작일시
//	private String enddt;				//PC예약전시종료일시
//	private String mdirectdisplayenddt; //모바일즉시전시종료일시
//	private String mstartdt;		    //모바일예약전시시작일시
//	private String menddt;			    //모바일예약전시종료일시
	

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
	public int getEventseq() {
		return eventseq;
	}
	public void setEventseq(int eventseq) {
		this.eventseq = eventseq;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public String getEventyn() {
		return eventyn;
	}
	public void setEventyn(String eventyn) {
		this.eventyn = eventyn;
	}
	public String getEventstartdt() {
		return eventstartdt;
	}
	public void setEventstartdt(String eventstartdt) {
		this.eventstartdt = eventstartdt;
	}
	public String getEventenddt() {
		return eventenddt;
	}
	public void setEventenddt(String eventenddt) {
		this.eventenddt = eventenddt;
	}
	public String getMthumbpath() {
		if(mthumbpath != null && mthumbpath.indexOf("http") < 0)
			mthumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/event"+mthumbpath;
		
		return mthumbpath;
	}
	public void setMthumbpath(String mthumbpath) {
		this.mthumbpath = mthumbpath;
	}
	public String getDisplayfg() {
		return displayfg;
	}
	public void setDisplayfg(String displayfg) {
		this.displayfg = displayfg;
	}
	public String getPopyn() {
		return popyn;
	}
	public void setPopyn(String popyn) {
		this.popyn = popyn;
	}
	public String getMpopyn() {
		return mpopyn;
	}
	public void setMpopyn(String mpopyn) {
		this.mpopyn = mpopyn;
	}
	public String getUrllink() {
		return urllink;
	}
	public void setUrllink(String urllink) {
		this.urllink = urllink;
	}
	public String getMcontentsurl() {
		return mcontentsurl;
	}
	public void setMcontentsurl(String mcontentsurl) {
		this.mcontentsurl = mcontentsurl;
	}
	public String getRegfg() {
		return regfg;
	}
	public void setRegfg(String regfg) {
		this.regfg = regfg;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0)
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/event"+thumbpath;
		
		return thumbpath;
	}
	public void setThumbpath(String thumbpath) {
		this.thumbpath = thumbpath;
	}
	public String getContentsurl() {
		return contentsurl;
	}
	public void setContentsurl(String contentsurl) {
		this.contentsurl = contentsurl;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getEventendyn() {
		return eventendyn;
	}
	public void setEventendyn(String eventendyn) {
		this.eventendyn = eventendyn;
	}
	public String getMobileyn() {
		return mobileyn;
	}
	public void setMobileyn(String mobileyn) {
		this.mobileyn = mobileyn;
	}
}