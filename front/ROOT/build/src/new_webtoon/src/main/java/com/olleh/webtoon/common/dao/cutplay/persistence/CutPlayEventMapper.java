package com.olleh.webtoon.common.dao.cutplay.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayCommentDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayEventDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayJoinDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayWinnerDomain;
import com.olleh.webtoon.common.dao.orderbuy.domain.OrderDomain;
import com.olleh.webtoon.common.dao.payment.domain.PaymentDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;

public interface CutPlayEventMapper {		

	/**
	 * 컷스프레이벤트 현재 차수조회 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 차수 
	 */
	public CutPlayEventDomain selectCutPlayEventCurTimes(String date);
	
	/**
	 * 컷스프레이벤트 차수조회 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 차수 
	 */
	public CutPlayEventDomain selectCutPlayEventTimes(int eventtimes);
	
	/**
	 * 컷스프레이벤트 참여횟수 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 참여 횟수 
	 */
	public int selectCutPlayEventCnt(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 베스트 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 베스트 
	 */
	public List<CutPlayJoinDomain> selectCutPlayEventBest(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 참여
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return void
	 */
	public void insertCutPlayEvent(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 목록 전체 수
	 */
	public int selectImageListCnt(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 참여자 정보 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return CutPlayJoinDomain : 참여정보
	 */
	public CutPlayJoinDomain selectRegUser(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 목록
	 */
	public List<CutPlayJoinDomain> selectImageList(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 내 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 목록 전체 수
	 */
	public int selectMyImageListCnt(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 내 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 목록
	 */
	public List<CutPlayJoinDomain> selectMyImageList(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레이벤트 내 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 목록
	 */
	public CutPlayJoinDomain selectCutPlayJoinDetail(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 컷스프레 도전하기 step01
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 WebToonImagePath,webtoonseq
	 */
	public List<CutPlayJoinDomain> selectWebToonImagePath();
	
	/**
	 * 컷 이벤트 댓글 등록
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return 
	 */
	public void insertCutPlayComment(CutPlayCommentDomain cutPlayCommentDomain);
	
	/**
	 * 컷 이벤트 댓글 등록 아이디 조회
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return 
	 */
	public String selectCommentRegId(CutPlayCommentDomain cutPlayCommentDomain);
	
	/**
	 * 컷 이벤트 댓글 삭제
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return 
	 */
	public void deleteCutPlayComment(CutPlayCommentDomain cutPlayCommentDomain);
	
	/**
	 * 컷이벤트 댓글 목록 갯수 조회
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return int
	 */
	public int selectCutPlayCommentTotalCnt(CutPlayCommentDomain cutPlayCommentDomain);
	
	/**
	 * 컷이벤트 댓글 목록 조회
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return List<CutPlayCommentDomain> 
	 */
	public List<CutPlayCommentDomain> selectCutPlayCommentList(CutPlayCommentDomain cutPlayCommentDomain);
	
	/**
	 * 좋아요 이력 조회
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public int selectGoodcntHisCnt(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 작성자 조회
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public String selectCutPlayRegid(CutPlayJoinDomain cutPlayJoinDomain);
	
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
	public void insertCutPlayGoodcnt(CutPlayJoinDomain cutPlayJoinDomain);
	
	/**
	 * 좋아요 처리
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public void updateCutPlayGoodcnt(CutPlayJoinDomain cutPlayJoinDomain);

	/**
	 * 신고 이력 조회
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public int selectDeclarecntHisCnt(CutPlayJoinDomain cutPlayJoinDomain);

	/**
	 * 신고 처리
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public void insertCutPlayDeclarecnt(CutPlayJoinDomain cutPlayJoinDomain);

	/**
	 * 신고 처리
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public void updateCutPlayDeclarecnt(CutPlayJoinDomain cutPlayJoinDomain);

	/**
	 * 공유 SNS 정보 저장 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public void insertCutPlaySnsInfo(CutPlayJoinDomain cutPlayJoinDomain);
	
	
	
	
	
	/**
	 * 총 누적 베리 조회 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public int selectFreeBerryAmount(UserDomain user);
	
	/**
	 * 일일 누적 베리 조회 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public int selectTodayFreeBerryAmount(UserDomain user);
	
	/**
	 * 베리 지급 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public void registFreePayment(PaymentDomain payment);
	
	/**
	 * 베리 지급 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public void registFreeOrder(OrderDomain order);
	
	public int selectMobileImageListCnt(CutPlayJoinDomain cutPlayJoinDomain);

	public List<CutPlayJoinDomain> selectMobileImageList(CutPlayJoinDomain cutPlayJoinDomain);
	
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