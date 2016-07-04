package com.olleh.webtoon.common.dao.eventpromotion.service.iface;

import java.util.List;

import com.olleh.webtoon.common.dao.eventpromotion.domain.EventContestDomain;
import com.olleh.webtoon.common.dao.eventpromotion.domain.EventCouponDomain;
import com.olleh.webtoon.common.dao.eventpromotion.domain.EventPromotionDomain;


public interface EventPromotionService 
{	
	/**
	 * 아이스크림 이벤트 참여 여부 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return String : 참여여부(Y) 
	 */
	public String geIceEventDuplicateyn(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * 아이스크림 이벤트 등록
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return void
	 */
	public void iceEventRegistProc(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * 이벤트 유효성 체크
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return String : 이벤트 유효성 여부(Y/N)
	 */
	public String getEventJoinyn(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * 아이스크림 이벤트1 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return String : 당첨 날짜
	 */
	public String getIceMemberEventPrizeWinner(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * 아이스크림 이벤트2 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	public List<String> getIceSnsEventPrizeWinner(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * SNS 응원글 목록 총 갯수 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return int : 응원글 목록 총 갯수
	 */
	public int getIceSnsCmtTotalCnt(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * SNS 응원글 목록 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<EventPromotionDomain> : 응원글 목록
	 */
	public List<EventPromotionDomain> getIceSnsCmtList(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * SNS 응원글 등록
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return void
	 */
	public void iceSnsEventRegistProc(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * SNS 이벤트 중복 참여 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return int : 참여 횟수
	 */
	public int getIceSnsEventHistory(EventPromotionDomain eventPromotionDomain);

	/**
	 * 공모전 이벤트 필명 중복 조회
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return int : 등록필명수
	 */
	public int getContestPnmCheckCnt(EventContestDomain eventContestDomain);

	/**
	 * 공모전 이벤트 등록
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	public void contestRegistProc(EventContestDomain eventContestDomain);

	/**
	 * 쿠폰 이벤트 등록
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	public void couponRegistProc(EventCouponDomain eventCouponDomain);
	
	/**
	 * 쿠폰번호로 쿠폰 이벤트
	 * 
	 * @param String couponnum
	 * @return EventCouponDomain : 쿠폰 이벤트
	 */
	public EventCouponDomain getCouponEventByCouponnum(String couponnum);
	
	/**
	 * 아이디로 쿠폰 이벤트
	 * 
	 * @param EventCouponDomain eventCouponDomain
	 * @return EventCouponDomain : 쿠폰 이벤트
	 */
	public EventCouponDomain getCouponEventById(EventCouponDomain eventCouponDomain);
	
	/**
	 * 쿠폰 플러스 이벤트 등록
	 * 
	 * @param eventCouponDomain eventCouponDomain
	 * @return void
	 */
	public void couponPlusEventRegistProc(EventCouponDomain eventCouponDomain);

	/**
	 * 아이디로 쿠폰 플러스 이벤트
	 * 
	 * @param EventCouponDomain eventCouponDomain
	 * @return EventCouponDomain : 쿠폰 플러스 이벤트
	 */
	public EventCouponDomain getCouponPlusEventById(EventCouponDomain eventCouponDomain);

	/**
	 * 공모전 접수현황 조회
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return int : 접명수
	 */	
	public int getContestCnt(EventContestDomain eventContestDomain);
	

	/**
	 * 이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	public List<EventPromotionDomain> getCouponEventPrizeWinner(EventPromotionDomain eventPromotionDomain);
	
	
	/**
	 * 공모전 이벤트(댓글, 스티커) 등록
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	public void contestEventRegistProc(EventContestDomain eventContestDomain); 
	
	/**
	 * 공모전 이벤트 준비완료 여부 조회
	 * 
	 * @param userid, idfg
	 * @return int
	 */
	public int getContestEventHisCnt(String userid, String idfg);

	/**
	 * 냄보소 SNS이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	public List<EventPromotionDomain> getNaembosoSnsEventPrizeWinner(EventPromotionDomain eventPromotionDomain);

	/**
	 * 냄보소 Comment이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	public List<EventPromotionDomain> getNaembosoCommentEventPrizeWinner(EventPromotionDomain eventPromotionDomain);

	/**
	 * 냄보소 Photo이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	public List<EventPromotionDomain> getNaembosoPhotoEventPrizeWinner(EventPromotionDomain eventPromotionDomain);
	
	
	
	/**
	 * (공통)sns이벤트 참여 여부 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return void
	 */
	public int getSnsEventJoinHisCnt(EventPromotionDomain eventPromotionDomain);
	
	/**
	 * (공통)sns 이벤트 참여
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return void
	 */
	public void snsEventRegistProc(EventPromotionDomain eventPromotionDomain);
	
	
	
//	/**
//	 * 2주년 이벤트 참여 날짜 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public String getMissionEventRegdt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 참여
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public void missionEventRegistProc(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 참여 내역 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public List<EventMissionDomain> getMissionEventList(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 미션 참여
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public void missionRegistProc(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 로그인 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int getLoginTotalCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 소문내기 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int getRumorTotalCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 스티커 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int getStickerTotalCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 댓글 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int getCommentTotalCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 공유하기 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int getSnsTotalCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 미션 업데이트 여부 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public String getUpdateyn(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 금일 출석체크 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public int getMissionTodayHisCnt(EventMissionDomain eventMissionDomain);
//	
//	/**
//	 * 2주년 이벤트 미션처리
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public void insertMissionList(EventMissionDomain eventMissionDomain);
}