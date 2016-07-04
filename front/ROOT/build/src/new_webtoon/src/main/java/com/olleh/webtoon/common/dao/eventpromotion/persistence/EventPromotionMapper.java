package com.olleh.webtoon.common.dao.eventpromotion.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.eventpromotion.domain.EventContestDomain;
import com.olleh.webtoon.common.dao.eventpromotion.domain.EventCouponDomain;
import com.olleh.webtoon.common.dao.eventpromotion.domain.EventPromotionDomain;


public interface EventPromotionMapper 
{		
	/**
	 * 아이스크림 이벤트 참여 여부 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return String : 참여여부(Y) 
	 */
	public String selectIceEventDuplicateyn(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * 아이스크림 이벤트 등록
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return void
	 */
	public void insertIceEvent(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * 이벤트 유효성 체크
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return String : 이벤트 유효성 여부(Y/N)
	 */
	public String selectEventJoinyn(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * 아이스크림 이벤트1 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return String : 당첨 날짜
	 */
	public String selectIceMemberEventPrizeWinner(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * 아이스크림 이벤트2 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	public List<String> selectIceSnsEventPrizeWinner(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * SNS 응원글 목록 총 갯수 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return int : 응원글 목록 총 갯수
	 */
	public int selectIceSnsCmtTotalCnt(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * SNS 응원글 목록 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<EventPromotionDomain> : 응원글 목록
	 */
	public List<EventPromotionDomain> selectIceSnsCmtList(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * SNS 응원글 등록
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return void
	 */
	public void insertIceSnsEvent(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * SNS 이벤트 중복 참여 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return int : 참여 횟수
	 */
	public int selectIceSnsEventHistory(EventPromotionDomain eventPromotionDomain);

	/**
	 * 공모전 이벤트 필명 중복 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return int : 등록필명수
	 */
	public int selectContestPnmCnt(EventContestDomain eventContestDomain);

	/**
	 * 공모전 이벤트 등록
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	public void insertContestEvent(EventContestDomain eventContestDomain);
	
	/**
	 * 쿠폰 이벤트 등록
	 * 
	 * @param EventCouponDomain eventCouponDomain
	 * @return void
	 */
	public void updateCouponEvent(EventCouponDomain eventCouponDomain);
	
	/**
	 * 쿠폰번호로 쿠폰 이벤트 목록 조회
	 * 
	 * @param String couponnum
	 * @return EventCouponDomain : 쿠폰 이벤트
	 */
	public EventCouponDomain selectCouponEventByCouponnum(String couponnum);
	
	/**
	 * 아이디로 쿠폰 이벤트 목록 조회
	 * 
	 * @param EventCouponDomain eventCouponDomain
	 * @return EventCouponDomain : 쿠폰 이벤트
	 */
	public EventCouponDomain selectCouponEventById(EventCouponDomain eventCouponDomain);
	
	/**
	 * 쿠폰 플러스이벤트 등록
	 * 
	 * @param EventCouponDomain eventCouponDomain
	 * @return void
	 */
	public void insertPlusCouponEvent(EventCouponDomain eventCouponDomain);
	
	/**
	 * 아이디로 쿠폰 플러스이벤트 목록 조회
	 * 
	 * @param EventCouponDomain eventCouponDomain
	 * @return EventCouponDomain : 쿠폰 이벤트
	 */
	public EventCouponDomain selectPlusCouponEventById(EventCouponDomain eventCouponDomain);

	/**
	 * 공모전 접수현황 조회
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return int : 접수명수
	 */	
	public int selectContestCnt(EventContestDomain eventContestDomain);
	
	/**
	 * 이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	public List<EventPromotionDomain> selectCouponEventPrizeWinner(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * 공모전 댓글 이벤트 등록
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	public void insertContestComment(EventContestDomain eventContestDomain);
	
	/**
	 * 공모전 스티커 이벤트 등록
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	public void insertContestSticker(EventContestDomain eventContestDomain);
	
	/**
	 * 공모전 이벤트 등록
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	public void insertContestDefault(EventContestDomain eventContestDomain);
	
	/**
	 * 공모전 이벤트 이력 조회
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	public int selectContestEventHisCnt(EventContestDomain eventContestDomain);
	
	/**
	 * 공모전 이벤트 참여자 전화번호 조회
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	public String selectContestPhoneNumber(EventContestDomain eventContestDomain);
	
	/**
	 * 최신 댓글 순번 가져오기
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	public int selectRecentCommentseq(EventContestDomain eventContestDomain);

	/**
	 * 냄보소 SNS이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	public List<EventPromotionDomain> selectNaembosoEventPrizeWinner(EventPromotionDomain eventPromotionDomain);

	/**
	 * 냄보소 Comment이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	public List<EventPromotionDomain> selectNaembosoCommentEventPrizeWinner(EventPromotionDomain eventPromotionDomain);

	/**
	 * 냄보소 Photo이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	public List<EventPromotionDomain> selectNaembosoPhotoEventPrizeWinner(EventPromotionDomain eventPromotionDomain);
	
	
	
	/**
	 * sns이벤트 참여 여부 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return void
	 */
	public int selectSnsEventJoinHisCnt(EventPromotionDomain eventPromotionDomain);
	
//	/**
//	 * 2주년 이벤트 참여 날짜 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public String selectMissionEventRegdt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 등록
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public void insertMissionEvent(EventMissionDomain eventMissionDomain);
//
//	/**
//	 * 2주년 이벤트 참여 내역 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public List<EventMissionDomain> selectMissionEventList(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 소문내기 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int selectRumorTotalCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 스티커 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int selectStickerTotalCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 댓글 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int selectCommentTotalCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 공유하기 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int selectSnsTotalCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 미션 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int selectMissionCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 미션 참여
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public void insertMission(EventMissionDomain eventMissionDomain);
//	
//	
//	/**
//	 * 2주년 이벤트 금일 출석체크 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int selectMissionTodayHisCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 미션처리할 소문내기 목록 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public List<EventMissionDomain> selectRumorList(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 미션처리할 스티커 목록 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public List<EventMissionDomain> selectStickerList(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 미션처리할 댓글 목록 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public List<EventMissionDomain> selectCommentList(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 미션처리할 공유하기 목록 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public List<EventMissionDomain> selectSnsList(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 최근 등록한 순번 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public String selectRecentMissionseq(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 미션 순번 목록 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public List<String> selectMissionseqList(EventMissionDomain eventMissionDomain);

}