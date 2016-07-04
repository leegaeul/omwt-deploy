package com.olleh.webtoon.common.dao.photoevent.service.iface;

import java.util.List;

import com.olleh.webtoon.common.dao.photoevent.domain.ImageDeclarationDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.ImageRecommendDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.PhotoEventDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.PhotoEventImageDomain;

public interface PhotoEventService {	
	
	/**
	 * 포토이벤트 참여
	 * @param PhotoEventDomain photoEventDomain
	 * @return void
	 */
	public void photoEventRegistProc(PhotoEventDomain photoEventDomain);
	
	/**
	 * 포토이벤트 등록정보
	 * @param PhotoEventDomain photoEventDomain
	 * @return PhotoEventDomain : 포토이벤트 정보
	 */
	public PhotoEventDomain getPhotoEventRegist(PhotoEventDomain photoEventDomain);

	/**
	 * 포토이벤트 이미지 등록
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return void
	 */
	public void photoEventImageRegistProc(PhotoEventImageDomain photoEventImageDomain);
	
	/**
	 * 포토이벤트 등록 이미지 목록 갯수 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return int
	 */
	public int getImageListCnt(PhotoEventImageDomain photoEventImageDomain);
	
	/**
	 * 포토이벤트 등록 이미지 목록 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return List<PhotoEventImageDomain>
	 */
	public List<PhotoEventImageDomain> getImageList(PhotoEventImageDomain photoEventImageDomain);
	
	/**
	 * 포토이벤트 등록 이미지 목록 갯수 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return int
	 */
	public int getMyImageListCnt(PhotoEventImageDomain photoEventImageDomain);
	
	/**
	 * 포토이벤트 등록 이미지 목록 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return List<PhotoEventImageDomain>
	 */
	public List<PhotoEventImageDomain> getMyImageList(PhotoEventImageDomain photoEventImageDomain);
	
	/**
	 * 신고 하기
	 * @param dcbDomain imageDeclarationDomain
	 * @return void
	 */
	public void imageDeclationProc(ImageDeclarationDomain imageDeclarationDomain);
	
	/**
	 * 신고 횟수 조회 
	 * @param dcbDomain imageDeclarationDomain
	 * @return int : 신고횟수 
	 */
	public int getDeclareCntById(ImageDeclarationDomain imageDeclarationDomain);
	
	/**
	 * 신고 횟수 조회 
	 * @param dcbDomain imageDeclarationDomain
	 * @return int : 신고횟수 
	 */
	public int getDeclareCntByImgseq(ImageDeclarationDomain imageDeclarationDomain);
	
	/**
	 * 좋아요/싫어요 이력 조회 
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return int : 좋아요/싫어요 이력 
	 */
	public int getRecomCntById(ImageRecommendDomain imageRecommendDomain);
	
	/**
	 * 좋아요/싫어요 이력 조회 
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return int : 좋아요/싫어요 이력 
	 */
	public int getRecomCntByImgseq(ImageRecommendDomain imageRecommendDomain);
	
	/**
	 * 이미지 등록 아이디 조회 
	 * @param int imageseq : 이미지 순번
	 * @return String : 이미지 등록 아이디 
	 */
	public String getImageRegId(int imageseq);
	
	/**
	 * 좋아요/싫어요 등록
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return void 
	 */
	public void imageRecommendProc(ImageRecommendDomain imageRecommendDomain);
	
	/**
	 * totalgoodcnt를 update한다.
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return void 
	 */
	public void modifyImageInfo(ImageRecommendDomain imageRecommendDomain);

	/**
	 * 등록이미지 상태(Delete)를 update한다.
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return void 
	 */
	public void photoEventImageDeleteProc(PhotoEventImageDomain photoEventImageDomain);
}