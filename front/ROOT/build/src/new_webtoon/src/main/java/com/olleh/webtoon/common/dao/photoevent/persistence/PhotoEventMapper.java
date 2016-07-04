package com.olleh.webtoon.common.dao.photoevent.persistence;

import java.util.List;

import com.olleh.webtoon.common.dao.photoevent.domain.ImageDeclarationDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.ImageRecommendDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.PhotoEventDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.PhotoEventImageDomain;

public interface PhotoEventMapper {		

	/**
	 * 포토이벤트 참여
	 * @param PhotoEventDomain photoEventDomain
	 * @return void
	 */
	public void insertPhotoEvent(PhotoEventDomain photoEventDomain);
	
	/**
	 * 포토이벤트 등록정보 
	 * @param PhotoEventDomain photoEventDomain
	 * @return PhotoEventDomain : 포토이벤트 정보 
	 */
	public PhotoEventDomain selectPhotoEventRegist(PhotoEventDomain photoEventDomain);	

	/**
	 * 포토이벤트 이미지 등록
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return void
	 */
	public void insertPhotoEventImage(PhotoEventImageDomain photoEventImageDomain);
	
	/**
	 * 포토이벤트 등록 이미지 목록 갯수 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return int
	 */
	public int selectImageListCnt(PhotoEventImageDomain photoEventImageDomain);
	
	/**
	 * 포토이벤트 등록 이미지 목록 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return List<PhotoEventImageDomain>
	 */
	public List<PhotoEventImageDomain> selectImageList(PhotoEventImageDomain photoEventImageDomain);
	
	/**
	 * 포토이벤트 등록 이미지 목록 갯수 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return int
	 */
	public int selectMyImageListCnt(PhotoEventImageDomain photoEventImageDomain);
	
	/**
	 * 포토이벤트 등록 이미지 목록 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return List<PhotoEventImageDomain>
	 */
	public List<PhotoEventImageDomain> selectMyImageList(PhotoEventImageDomain photoEventImageDomain);
	
	/**
	 * 신고 하기
	 * @param dcbDomain imageDeclarationDomain
	 * @return void
	 */
	public void insertImageDeclation(ImageDeclarationDomain imageDeclarationDomain);
	
	/**
	 * 신고 횟수 조회 
	 * @param dcbDomain imageDeclarationDomain
	 * @return int : 신고횟수 
	 */
	public int selectDeclareCntById(ImageDeclarationDomain imageDeclarationDomain);
	
	/**
	 * 신고 횟수 조회 
	 * @param dcbDomain imageDeclarationDomain
	 * @return int : 신고횟수 
	 */
	public int selectDeclareCntByImgseq(ImageDeclarationDomain imageDeclarationDomain);
	
	/**
	 * 좋아요/싫어요 이력 조회 
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return int : 좋아요/싫어요 이력 
	 */
	public int selectRecomCntById(ImageRecommendDomain imageRecommendDomain);
	
	/**
	 * 좋아요/싫어요 이력 조회 
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return int : 좋아요/싫어요 이력 
	 */
	public int selectRecomCntByImgseq(ImageRecommendDomain imageRecommendDomain);
	
	/**
	 * 이미지 등록 아이디 조회 
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return String : 이미지 등록 아이디 
	 */
	public String selectImageRegId(PhotoEventImageDomain photoEventImageDomain);
	
	/**
	 * 좋아요/싫어요 등록
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return void 
	 */
	public void insertImageRecommend(ImageRecommendDomain imageRecommendDomain);

	/**
	 * totalgoodcnt를 update한다.
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return void 
	 */
	public void updateImageInfo(ImageRecommendDomain imageRecommendDomain);

	/**
	 * 등록이미지 상태(Delete)를 update한다.
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return void 
	 */
	public void updateImageDelete(PhotoEventImageDomain photoEventImageDomain);
}