/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 랭킹관리 > YO랭킹
 * FILE NAME      : YoRankingDomain.java
 * DESCRIPTION    : YO랭킹을 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0               joo         2014-06-24      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.ranking.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class YoRankingDomain 
{
	private int	   rankingseq;	 		// 랭킹순번
	private int    prevrankingseq;      // 이전 랭킹순번
	private int    nextrankingseq;		// 다음 랭킹순번
	private String displayyn;			// 전시여부 
	private String startdt;				// 예약전시 시작일시
	private String enddt;				// 에약전시 종료일시
	private String title;				// 제목
	private String contents;			// 목록용 내용
	private String comment;				// 운영자의 말
	private String contenturl;			// 컨텐츠 URL(==랜딩 URL)
	private int	   iconseq;				// 스티커아이콘순번
	private String thumbpath;			// 썸네일파일 풀경로(PC)
	private String thumbfilenm;			// 썸네일파일 원본파일명(PC)
	private String mthumbpath;			// 썸네일파일 풀경로(모바일)
	private String mthumbfilenm;		// 썸네일파일 원본파일명(모바일)
	private String regid;				// 등록한 운영자 ID
	private String regdt;				// 등록일시(YYYYMMDDHHMMSS)
	private String refauthorseqstr; 	// 관련작품 작가정보
	private String ref2authorseqstr; 	// 관련작품 작가정보
	private String ref3authorseqstr; 	// 관련작품 작가정보
	private String refwebtoonseqstr; 	// 관련작품 정보
	private String onstickerpath;    	// On 스티커 아이콘 경로
	private String offstickerpath;   	// Off 스티커 아이콘 경로
	private String liststickerpath;  	// 목록용 스티커 아이콘 경로
	private String keyword;             // 검색단어
	private String newyn;				// New 아이콘 노출 여부
	private int    startRowNo;          // 해당페이지 시작 Row 번호(페이지크기가 10일 경우 1페이지는 0, 2페이지는 10, 3페이지는 20)
	private int    pageSize;            // 페이지크기
	
	public int getRankingseq() { return rankingseq; }
	public void setRankingseq(int rankingseq) { this.rankingseq = rankingseq;}
	
	public int getPrevrankingseq() { return prevrankingseq; }
	public void setPrevrankingseq(int prevrankingseq) { this.prevrankingseq = prevrankingseq; }
	
	public int getNextrankingseq() { return nextrankingseq; }
	public void setNextrankingseq(int nextrankingseq) { this.nextrankingseq = nextrankingseq; }
	
	public String getDisplayyn() { return displayyn; }
	public void setDisplayyn(String displayyn) { this.displayyn = displayyn; }
	
	public String getStartdt() { return startdt; }
	public void setStartdt(String startdt) { this.startdt = startdt; }
	
	public String getEnddt() { return enddt; }
	public void setEnddt(String enddt) { this.enddt = enddt; }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getContents() { return contents; }
	public void setContents(String contents) { this.contents = contents; }
	
	public String getComment() { return comment; }	
	public void setComment(String comment) { this.comment = comment; }
	
	public String getContenturl() { return contenturl; }	
	public void setContenturl(String contenturl) { this.contenturl = contenturl; }
	
	public int getIconseq() { return iconseq; }	
	public void setIconseq(int iconseq) { this.iconseq = iconseq; }
	
	public String getThumbpath() { 
		if(thumbpath != null && thumbpath.indexOf("http") < 0)
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/zine"+thumbpath;
		
		return thumbpath; 
	}	
	public void setThumbpath(String thumbpath) { this.thumbpath = thumbpath; }
	
	public String getThumbfilenm() { return thumbfilenm; }	
	public void setThumbfilenm(String thumbfilenm) { this.thumbfilenm = thumbfilenm; }
	
	public String getMthumbpath() { 
		if(mthumbpath != null && mthumbpath.indexOf("http") < 0)
			mthumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/zine"+mthumbpath;
		
		return mthumbpath; 
	}	
	public void setMthumbpath(String mthumbpath) { this.mthumbpath = mthumbpath; }
	
	public String getMthumbfilenm() { return mthumbfilenm; }	
	public void setMthumbfilenm(String mthumbfilenm) { this.mthumbfilenm = mthumbfilenm; }
	
	public String getRegid() { return regid; }	
	public void setRegid(String regid) { this.regid = regid; }
	
	public String getRegdt() { return regdt; }	
	public void setRegdt(String regdt) { this.regdt = regdt;}
	
	public String getRefauthorseqstr() { return refauthorseqstr; }
	public void setRefauthorseqstr(String refauthorseqstr) { this.refauthorseqstr = refauthorseqstr; }
	
	public String getRef2authorseqstr() { return ref2authorseqstr; }
	public void setRef2authorseqstr(String ref2authorseqstr) { this.ref2authorseqstr = ref2authorseqstr; }
	
	public String getRef3authorseqstr() { return ref3authorseqstr; }
	public void setRef3authorseqstr(String ref3authorseqstr) { this.ref3authorseqstr = ref3authorseqstr;}
	
	public String getRefwebtoonseqstr() { return refwebtoonseqstr; }
	public void setRefwebtoonseqstr(String refwebtoonseqstr) { this.refwebtoonseqstr = refwebtoonseqstr; }

	public String getOnstickerpath() { 

		if(onstickerpath != null && onstickerpath.indexOf("http") < 0)
			onstickerpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/sticker"+onstickerpath;
		
		return onstickerpath; 
	}
	public void setOnstickerpath(String onstickerpath) { 		
		this.onstickerpath = onstickerpath; 
	}

	public String getOffstickerpath() { 
		if(offstickerpath != null && offstickerpath.indexOf("http") < 0)
			offstickerpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/sticker"+offstickerpath;
		
		return offstickerpath; 
	}
	public void setOffstickerpath(String offstickerpath) { 		
		this.offstickerpath = offstickerpath; 
	}
	
	public String getListstickerpath() { 
		if(offstickerpath != null && offstickerpath.indexOf("http") < 0)
			offstickerpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/sticker"+offstickerpath;
		
		return liststickerpath; 
	}
	public void setListstickerpath(String liststickerpath) { this.liststickerpath = liststickerpath; }
	
	public String getKeyword() { return keyword;}
	public void setKeyword(String keyword) { this.keyword = keyword; }
	
	public String getNewyn() { return newyn; }
	public void setNewyn(String newyn) { this.newyn = newyn; }
	
	public int getStartRowNo() { return startRowNo; }
	public void setStartRowNo(int startRowNo) { this.startRowNo = startRowNo; }

	public int getPageSize() { return pageSize; }
	public void setPageSize(int pageSize) { this.pageSize = pageSize; }
}
