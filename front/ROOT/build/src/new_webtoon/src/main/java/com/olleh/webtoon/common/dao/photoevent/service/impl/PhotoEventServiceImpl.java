package com.olleh.webtoon.common.dao.photoevent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.photoevent.domain.ImageDeclarationDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.ImageRecommendDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.PhotoEventDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.PhotoEventImageDomain;
import com.olleh.webtoon.common.dao.photoevent.persistence.PhotoEventMapper;
import com.olleh.webtoon.common.dao.photoevent.service.iface.PhotoEventService;
import com.olleh.webtoon.common.util.FileUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Service("photoEventService")
@Repository
public  class PhotoEventServiceImpl implements PhotoEventService {
	
	@Autowired
	private PhotoEventMapper photoEventMapper;	
	
	/**
	 * 포토이벤트 참여
	 * @param PhotoEventDomain photoEventDomain
	 * @return void
	 */
	@Transactional(readOnly=false)
	public void photoEventRegistProc(PhotoEventDomain photoEventDomain) {
		photoEventMapper.insertPhotoEvent(photoEventDomain);
	}
	
	/**
	 * 포토이벤트 등록정보
	 * @param PhotoEventDomain photoEventDomain
	 * @return PhotoEventDomain : 포토이벤트 정보
	 */
	@Transactional(readOnly=true)
	public PhotoEventDomain getPhotoEventRegist(PhotoEventDomain photoEventDomain) {
		return photoEventMapper.selectPhotoEventRegist(photoEventDomain);
	}

	/**
	 * 포토이벤트 이미지 등록
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return void
	 */
	public void photoEventImageRegistProc(PhotoEventImageDomain photoEventImageDomain){
		
		//이미지 사이즈 조절
		String thumFilePath = FileUtil.resize(StringUtil.defaultStr(photoEventImageDomain.getImagepath()), "_thum", 275, 275);
		String detailFilePath = FileUtil.resize(StringUtil.defaultStr(photoEventImageDomain.getImagepath()), "_detail", 770, 577);

		//임시 파일 복사 처리
		FileUtil.tempFileCopy("event", StringUtil.defaultStr(photoEventImageDomain.getImagepath(), ""));
		FileUtil.tempFileCopy("event", StringUtil.defaultStr(thumFilePath, ""));
		FileUtil.tempFileCopy("event", StringUtil.defaultStr(detailFilePath, ""));
		
		//이미지 저장
		photoEventImageDomain.setThumbpath(thumFilePath);
		photoEventImageDomain.setMthumbpath(thumFilePath);
		photoEventImageDomain.setDetailimagepath(detailFilePath);
		photoEventImageDomain.setMdetailimagepath(detailFilePath);
		
		photoEventMapper.insertPhotoEventImage(photoEventImageDomain);
	}
	
	/**
	 * 포토이벤트 등록 이미지 목록 갯수 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return int
	 */
	public int getImageListCnt(PhotoEventImageDomain photoEventImageDomain) {
		return photoEventMapper.selectImageListCnt(photoEventImageDomain);
	}
	
	/**
	 * 포토이벤트 등록 이미지 목록 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return List<PhotoEventImageDomain>
	 */
	@Transactional(readOnly=true)
	public List<PhotoEventImageDomain> getImageList(PhotoEventImageDomain photoEventImageDomain) {
		return photoEventMapper.selectImageList(photoEventImageDomain);
	}
	
	/**
	 * 포토이벤트 등록 이미지 목록 갯수 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return int
	 */
	public int getMyImageListCnt(PhotoEventImageDomain photoEventImageDomain) {
		return photoEventMapper.selectMyImageListCnt(photoEventImageDomain);
	}
	
	/**
	 * 포토이벤트 등록 이미지 목록 조회
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return List<PhotoEventImageDomain>
	 */
	@Transactional(readOnly=true)
	public List<PhotoEventImageDomain> getMyImageList(PhotoEventImageDomain photoEventImageDomain) {
		return photoEventMapper.selectMyImageList(photoEventImageDomain);
	}
	
	/**
	 * 신고 하기
	 * @param dcbDomain imageDeclarationDomain
	 * @return void
	 */
	@Transactional(readOnly=false)
	public void imageDeclationProc(ImageDeclarationDomain imageDeclarationDomain) {
		photoEventMapper.insertImageDeclation(imageDeclarationDomain);
	}
	
	/**
	 * 신고 횟수 조회 
	 * @param dcbDomain imageDeclarationDomain
	 * @return int : 신고횟수 
	 */
	@Transactional(readOnly=true)
	public int getDeclareCntById(ImageDeclarationDomain imageDeclarationDomain) {
		return photoEventMapper.selectDeclareCntById(imageDeclarationDomain);
	}
	
	/**
	 * 신고 횟수 조회 
	 * @param dcbDomain imageDeclarationDomain
	 * @return int : 신고횟수 
	 */
	@Transactional(readOnly=true)
	public int getDeclareCntByImgseq(ImageDeclarationDomain imageDeclarationDomain) {
		return photoEventMapper.selectDeclareCntByImgseq(imageDeclarationDomain);
	}
	
	/**
	 * 좋아요/싫어요 이력 조회 
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return int : 좋아요/싫어요 이력 
	 */
	@Transactional(readOnly=true)
	public int getRecomCntById(ImageRecommendDomain imageRecommendDomain) {
		return photoEventMapper.selectRecomCntById(imageRecommendDomain);
	}
	
	/**
	 * 좋아요/싫어요 이력 조회 
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return int : 좋아요/싫어요 이력 
	 */
	@Transactional(readOnly=true)
	public int getRecomCntByImgseq(ImageRecommendDomain imageRecommendDomain) {
		return photoEventMapper.selectRecomCntByImgseq(imageRecommendDomain);
	}
	
	/**
	 * 이미지 등록 아이디 조회 
	 * @param int imageseq : 이미지 순번
	 * @return String : 이미지 등록 아이디 
	 */
	@Transactional(readOnly=true)
	public String getImageRegId(int imageseq) {
		PhotoEventImageDomain imageDomain = new PhotoEventImageDomain();
		imageDomain.setImageseq(imageseq);
		
		return photoEventMapper.selectImageRegId(imageDomain);
	}
	
	/**
	 * 좋아요/싫어요 등록
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return void 
	 */
	@Transactional(readOnly=false)
	public void imageRecommendProc(ImageRecommendDomain imageRecommendDomain) {
		photoEventMapper.insertImageRecommend(imageRecommendDomain);
	}
	
	/**
	 * totalgoodcnt를 update한다.
	 * @param ImageRecommendDomain imageRecommendDomain
	 * @return void 
	 */
	@Transactional(readOnly=false)
	public void modifyImageInfo(ImageRecommendDomain imageRecommendDomain) {
		photoEventMapper.updateImageInfo(imageRecommendDomain);
	}

	/**
	 * 등록이미지 상태(Delete)를 update한다.
	 * @param PhotoEventImageDomain photoEventImageDomain
	 * @return void 
	 */
	@Transactional(readOnly=false)
	public void photoEventImageDeleteProc(PhotoEventImageDomain photoEventImageDomain) {
		photoEventMapper.updateImageDelete(photoEventImageDomain);
	}
}