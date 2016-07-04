package com.olleh.webtoon.common.dao.cutplay.service.iface;

import java.util.List;

import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayCommentDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayEventDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayJoinDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayWinnerDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;

public interface CutPlayEventService {	
	
	/**
	 * 컷스프레이벤트 현재 차수 조회 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 차수 
	 */
	public CutPlayEventDomain getCutPlayEventCurTimes(String date);
	
	/**
	 * 컷스프레이벤트 차수 조회 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 차수 
	 */
	public CutPlayEventDomain getCutPlayEventTimes(int eventtimes);
	
	/**
	 * 컷스프레이벤트 참여횟수 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 참여 횟수 
	 */
	public int getCutPlayEventCnt(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 베스트 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return CutPlayEventDomain : 컷스프레이벤트 베스트 
	 */
	public List<CutPlayJoinDomain> getCutPlayEventBest(int eventtimes, String userid);
	
	/**
	 * 컷스프레이벤트 참여
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return void
	 */
	public void cutPlayEventRegistProc(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 목록 전체 수
	 */
	public int getImageListCnt(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 목록
	 */
	public List<CutPlayJoinDomain> getImageList(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 내 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 목록 전체 수
	 */
	public int getMyImageListCnt(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 내 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 목록
	 */
	public List<CutPlayJoinDomain> getMyImageList(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 내 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 목록
	 */
	public CutPlayJoinDomain getCutPlayJoinDetail(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레 도전하기 step01
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 WebToonImagePath,webtoonseq
	 */
	public List<CutPlayJoinDomain> getCutPlayWebToonImagePath();
	
	
	/**
	 * 컷 이벤트 댓글 등록
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return 
	 */
	public void cutPlayCommentProc(CutPlayCommentDomain cutPlayCommentDomain);
	
	/**
	 * 컷 이벤트 댓글 등록 아이디 조회
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return 
	 */
	public String getCommentRegId(CutPlayCommentDomain cutPlayCommentDomain);
	
	/**
	 * 컷 이벤트 댓글 삭제
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return 
	 */
	public void cutPlayCommentDeleteProc(CutPlayCommentDomain cutPlayCommentDomain);
	
	/**
	 * 컷이벤트 댓글 목록 갯수 조회
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return int
	 */
	public int getCutPlayCommentTotalCnt(CutPlayCommentDomain cutPlayCommentDomain);
	
	/**
	 * 컷이벤트 댓글 목록 조회
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return List<CutPlayCommentDomain> 
	 */
	public List<CutPlayCommentDomain> getCutPlayCommentList(CutPlayCommentDomain cutPlayCommentDomain);
	
	/**
	 * 좋아요 이력 조회
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public int getGoodcntHisCnt(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 작성자 조회
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public String getCutPlayRegid(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 삭제 처리
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public void cutPlayDeleteProc(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 좋아요 처리
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public void cutPlayGoodcntProc(CutPlayJoinDomain cutPlayJoinDomain);

	/**
	 * 신고 이력 조회
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public int getDeclarecntHisCnt(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 신고 처리
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public void cutPlayDeclarecntProc(CutPlayJoinDomain cutPlayJoinDomain);

	/**
	 * 공유 SNS 정보 저장 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public void cutPlaySnsProc(CutPlayJoinDomain cutPlayJoinDomain);
	
	
	
	/**
	 * 총 누적 베리 조회 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public int getFreeBerryAmount(UserDomain user);
	
	/**
	 * 일일 누적 베리 조회 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public int getTodayFreeBerryAmount(UserDomain user);
	
	/**
	 * 베리 지급 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public void chargeFreeBerry(UserDomain user, boolean isMobile);
	
	public int getMobileImageListCnt(CutPlayJoinDomain cutPlayJoinDomain);

	public List<CutPlayJoinDomain> getMobileImageList(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 당첨자 조회
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public CutPlayWinnerDomain getCutPlayPrizeWinner(CutPlayWinnerDomain cutPlayWinnerDomain);
	
	/**
	 * 당첨자 조회
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public List<CutPlayWinnerDomain> getCutPlayPrizeList(CutPlayWinnerDomain cutPlayWinnerDomain);
}