/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자 
 * FILE NAME      : YoyozineDomain.java
 * DESCRIPTION    : YOYOZINE 테이블 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        						      2014-04-21      init
 *****************************************************************************/
package com.olleh.webtoon.common.dao.yoyozine.domain;

import com.olleh.webtoon.common.util.MessageUtil;


public class YoyozineDomain
{
	private int	yoyozineseq;		// YOYOZINE 순번 (PK. 일련번호)
	private int startRowNo; 		// 해당페이지 시작 Row번호(페이지크기가 10일 경우 1페이지는 0, 2페이지는 10, 3페이지는 20)
	private int pageSize; 			// 페이지크기
	private int commentcnt; 		// 댓글 갯수
	private int nextyoyozineseq;    // 이전 순번
	private int prevyoyozineseq;    // 다음 순번
	private long readcnt;			// 조회수
	private String categoryfg;		// 카테고리구분 (interview:사심가득인터뷰, toon:추천툰, appzine:앱진, special:YOYO SPECIAL)
	private String displayyn;		// 전시여부 (Y/N)
	private String directdisplayyn;	// 즉시 노출 여부 (Y/N)
	private String startdt;			// 전시시작일시 (YYYYMMDDHHMMSS)
	private String subject;			// 말머리
	private String title;			// 제목
	private String subtitle;		// 설명
	private String storynum;		// 발행호수
	private String thumbpath;		// 썸네일 풀경로 (PC웹 용)
	private String thumbfilenm;		// 썸네일파일 원본파일명 (PC웹 용)
	private String mthumbpath;		// 모바일 썸네일파일 풀경로 (모바일웹 용)
	private String mthumbfilenm;	// 모바일 썸네일파일 원본파일명 (모바일웹 용)
	private String imageyn;			// 본문이 이미지인지 여부 (Y:이미지, N:URL)
	private String contentsurl;		// 컨텐츠URL
	private String publishdt;		// 발행일시 (YYYYMMDDHHMMSS)
	private String tempsaveyn;		// 임시저장여부 (Y/N)
	private String regid;			// 등록한 운영자ID
	private String regdt;			// 등록일시 (YYYYMMDDHHMMSS)
	private String modid;			// 수정한 운영자ID
	private String moddt;			// 수정일시 (YYYYMMDDHHMMSS)
	private String keyword; 		// 검색기능
	private String mobileyn = "N";  // 모바일 여부
	
	//yoranking 관련 추가 
	private String rankingseq;		// 랭킹 순번
	private String oniconurl; 		// 스티커 url
	private int totalstickercnt;    // 누적 스티커 갯수
	
	
	public int getYoyozineseq() {
		return yoyozineseq;
	}
	public void setYoyozineseq(int yoyozineseq) {
		this.yoyozineseq = yoyozineseq;
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
	public int getCommentcnt() {
		return commentcnt;
	}
	public void setCommentcnt(int commentcnt) {
		this.commentcnt = commentcnt;
	}
	public int getNextyoyozineseq() {
		return nextyoyozineseq;
	}
	public void setNextyoyozineseq(int nextyoyozineseq) {
		this.nextyoyozineseq = nextyoyozineseq;
	}
	public int getPrevyoyozineseq() {
		return prevyoyozineseq;
	}
	public void setPrevyoyozineseq(int prevyoyozineseq) {
		this.prevyoyozineseq = prevyoyozineseq;
	}
	public long getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(long readcnt) {
		this.readcnt = readcnt;
	}
	public String getCategoryfg() {
		return categoryfg;
	}
	public void setCategoryfg(String categoryfg) {
		this.categoryfg = categoryfg;
	}
	public String getDisplayyn() {
		return displayyn;
	}
	public void setDisplayyn(String displayyn) {
		this.displayyn = displayyn;
	}
	public String getDirectdisplayyn() {
		return directdisplayyn;
	}
	public void setDirectdisplayyn(String directdisplayyn) {
		this.directdisplayyn = directdisplayyn;
	}
	public String getStartdt() {
		return startdt;
	}
	public void setStartdt(String startdt) {
		this.startdt = startdt;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getStorynum() {
		return storynum;
	}
	public void setStorynum(String storynum) {
		this.storynum = storynum;
	}
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0)
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/zine"+thumbpath;
		
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
	public String getMthumbpath() {
		if(mthumbpath != null && mthumbpath.indexOf("http") < 0)
			mthumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/zine"+mthumbpath;
		
		return mthumbpath;
	}
	public void setMthumbpath(String mthumbpath) {
		this.mthumbpath = mthumbpath;
	}
	public String getMthumbfilenm() {
		return mthumbfilenm;
	}
	public void setMthumbfilenm(String mthumbfilenm) {
		this.mthumbfilenm = mthumbfilenm;
	}
	public String getImageyn() {
		return imageyn;
	}
	public void setImageyn(String imageyn) {
		this.imageyn = imageyn;
	}
	public String getContentsurl() {
		return contentsurl;
	}
	public void setContentsurl(String contentsurl) {
		this.contentsurl = contentsurl;
	}
	public String getPublishdt() {
		return publishdt;
	}
	public void setPublishdt(String publishdt) {
		this.publishdt = publishdt;
	}
	public String getTempsaveyn() {
		return tempsaveyn;
	}
	public void setTempsaveyn(String tempsaveyn) {
		this.tempsaveyn = tempsaveyn;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getMobileyn() {
		return mobileyn;
	}
	public void setMobileyn(String mobileyn) {
		this.mobileyn = mobileyn;
	}
	public String getRankingseq() {
		return rankingseq;
	}
	public void setRankingseq(String rankingseq) {
		this.rankingseq = rankingseq;
	}
	public String getOniconurl() {
		return oniconurl;
	}
	public void setOniconurl(String oniconurl) {
		this.oniconurl = oniconurl;
	}
	public int getTotalstickercnt() {
		return totalstickercnt;
	}
	public void setTotalstickercnt(int totalstickercnt) {
		this.totalstickercnt = totalstickercnt;
	}
}