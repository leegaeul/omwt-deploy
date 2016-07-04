/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 운영자
 * FILE NAME      : RankingRefwebtoonDomain.java
 * DESCRIPTION    : YO랭킹 관련작품 테이블 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2013-06-03      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.ranking.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class RankingRefwebtoonDomain 
{
	private int    refseq;			// 관련순번  (PK. 일련번호)
	private int    rankingseq;		// YOYOZINE 순번 (FK)
	private int    authorseq1;		// 작가순번
	private int    authorseq2;		// 작가순번
	private int    authorseq3;		// 작가순번
	private String authornm1;		// 작가명
	private String authornm2;		// 작가명
	private String authornm3;		// 작가명
	private int    webtoonseq;		// 작품순번
	private String webtoonnm;		// 작품명
	private String sthumbfilenm;	// 웹툰 썸네일 파일명
	private String sthumbpath;		// 웹툰 PC 썸네일 경로
	private String thumbpath;		// 웹툰 모바일 썸네일 경로
	private String regid;			// 등록자ID
	private String regdt;			// 등록일시 (YYYYMMDDHHMMSS)
	
	public int getRefseq() { return refseq; }
	public void setRefseq(int refseq) { this.refseq = refseq; }
	
	public int getRankingseq() { return rankingseq; }
	public void setRankingseq(int rankingseq) { this.rankingseq = rankingseq; }
	
	public int getAuthorseq1() { return authorseq1; }
	public void setAuthorseq1(int authorseq1) { this.authorseq1 = authorseq1; }
	
	public int getAuthorseq2() { return authorseq2; }
	public void setAuthorseq2(int authorseq2) { this.authorseq2 = authorseq2; }
	
	public int getAuthorseq3() { return authorseq3; }
	public void setAuthorseq3(int authorseq3) { this.authorseq3 = authorseq3; }
	
	public String getAuthornm1() { return authornm1; }
	public void setAuthornm1(String authornm1) { this.authornm1 = authornm1; }
	
	public String getAuthornm2() { return authornm2; }
	public void setAuthornm2(String authornm2) { this.authornm2 = authornm2; }
	
	public String getAuthornm3() { return authornm3; }
	public void setAuthornm3(String authornm3) { this.authornm3 = authornm3; }
	
	public int getWebtoonseq() { return webtoonseq; }
	public void setWebtoonseq(int webtoonseq) { this.webtoonseq = webtoonseq; }
	
	public String getWebtoonnm() { return webtoonnm; }
	public void setWebtoonnm(String webtoonnm) { this.webtoonnm = webtoonnm; }
	
	public String getSthumbfilenm() { return sthumbfilenm; }
	public void setSthumbfilenm(String sthumbfilenm) { this.sthumbfilenm = sthumbfilenm; }
	
	public String getSthumbpath() {
		if(sthumbpath != null && sthumbpath.indexOf("http") < 0)
			sthumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+sthumbpath;
		
		return sthumbpath; 
	}
	public void setSthumbpath(String sthumbpath) { this.sthumbpath = sthumbpath; }
	
	public String getRegid() { return regid; }
	public void setRegid(String regid) { this.regid = regid; }
	
	public String getRegdt() { return regdt; }
	public void setRegdt(String regdt) { this.regdt = regdt; }
	
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0)
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+thumbpath;
		
		return thumbpath;
	}
	public void setThumbpath(String thumbpath) {
		this.thumbpath = thumbpath;
	}

}
