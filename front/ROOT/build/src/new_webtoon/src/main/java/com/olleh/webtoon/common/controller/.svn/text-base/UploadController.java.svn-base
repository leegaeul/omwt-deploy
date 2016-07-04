/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 운영자
 * FILE NAME      : UploadController.java
 * DESCRIPTION    : 파일 업로드 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-29      init
 *****************************************************************************/

package com.olleh.webtoon.common.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.constant.AppConstant;
import com.olleh.webtoon.common.util.FileUtil;
import com.olleh.webtoon.common.util.MessageUtil;

@Controller("CommonUploadController")
public class UploadController
{
	protected static Log logger	= LogFactory.getLog( UploadController.class );
	
	/**
	 * ajax 방식으로 파일 업로드 시키는 메소드
	 * 
	 * @param MultipartFile
	 *            업로드된 파일
	 */
	@RequestMapping( value = "/file/ajaxUpload.kt" )
	public ModelAndView uploadFile( MultipartHttpServletRequest request )
	{
		MultipartFile mfile = request.getFileMap().values().iterator().next();

		ModelAndView mv = new ModelAndView();
		mv.setViewName( AppConstant.JSON_VIEW_NAME );

		try {
			String originFileName = mfile.getOriginalFilename();  	  // 원본 파일명
			String fieldName = mfile.getName();
			String fileExt = FileUtil.getExtension( originFileName ); // 확장자

			// exe, eml 파일처럼 업로드가 허용되지 않는 파일을 필터링한다.
			if( !FileUtil.isAllowedFile( fileExt ) )
			{
				throw new Exception();
			}

			// 파일을 저장할 경로를 가져온다. /YYYY/MM/DD
			String datePath = FileUtil.getDatePath();
			String fileName = String.valueOf( System.currentTimeMillis() ) + "." + fileExt;
			String tempPath = MessageUtil.getMessage( "system.data.temp.image.path" );
			//String tempPath = MessageUtil.getMessage( "D:/data/temp" );

			// 저장할 폴더가 있는지 확인한다.
			File saveFolder = new File( tempPath + datePath );
			if( !saveFolder.exists() )
			{
				saveFolder.mkdirs();
			}

			// 물리파일을 저장한다.
			File file = new File( tempPath + datePath + "/" + fileName );
			mfile.transferTo( file );

			// 저장하나 파일 크기를 가져온다
			mv.addObject( "fileSize"		, file.length() );
			mv.addObject( "erroryn"			, "N" );
			mv.addObject( "filePath"		, datePath + "/" + fileName );
			mv.addObject( "fileName"		, fileName );
			mv.addObject( "fieldName"		, fieldName );
			mv.addObject( "originFileName"	, originFileName );

		} catch( Exception e ) {
			logger.error( e.getMessage() );
			logger.error( ExceptionUtils.getStackTrace( e ) );
			mv.addObject( "erroryn", "Y" );
		}
		
		return mv;
	}
	
	/**
	 * 물리 파일(IFRAME방식)을 업로드하는 RequestMapping 메소드
	 * 
	 * @param MultipartHttpServletRequest
	 *            request : MultipartHttpServletRequest 객체
	 * @param HttpServletResponse
	 *            response : HttpServletResponse 객체
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/file/Upload.kt" )
	public ModelAndView uploaFrameFile( MultipartHttpServletRequest request, HttpServletResponse response)
	{
		MultipartFile mfile = request.getFileMap().values().iterator().next();
		ModelAndView mv = new ModelAndView();
		mv.setViewName( AppConstant.JSON_VIEW_NAME );

		try {
			String originFileName = mfile.getOriginalFilename();  	  // 원본 파일명
			String fieldName = mfile.getName();
			String fileExt = FileUtil.getExtension( originFileName ); // 확장자

			// exe, eml 파일처럼 업로드가 허용되지 않는 파일을 필터링한다.
			if( !FileUtil.isAllowedFile( fileExt ) )
			{
				throw new Exception();
			}

			// 파일을 저장할 경로를 가져온다. /YYYY/MM/DD
			String datePath = FileUtil.getDatePath();
			String fileName = String.valueOf( System.currentTimeMillis() ) + "." + fileExt;
			String tempPath = MessageUtil.getMessage( "system.data.temp.image.path" );
			//String tempPath = MessageUtil.getMessage( "D:/data/temp" );

			// 저장할 폴더가 있는지 확인한다.
			File saveFolder = new File( tempPath + datePath );
			if( !saveFolder.exists() )
			{
				saveFolder.mkdirs();
			}

			// 물리파일을 저장한다.
			File file = new File( tempPath + datePath + "/" + fileName );
			mfile.transferTo( file );
			
			// orientation 체크
//			if("jpeg".equals(fileExt.toLowerCase()) || "jpg".equals(fileExt.toLowerCase())) {
//				
//				int width = 0;
//				int height = 0;
//				int radiands = 0;
//				
//				int orientation = FileUtil.getImageOrientation(file);
//				if(orientation > -1) {
//					
//					/*
//					orientation 1 : 0도 회전
//	 		        orientation 3 : 180도 회전(뒤집어진)
//	 		        orientation 6 : 시계방향으로 270도 회전
//	 		        orientation 8 : 시계방향으로 90도 회전된 사진
//	 		        */
//					BufferedImage oldImage = ImageIO.read(new FileInputStream(file));
//					
//					switch (orientation) {
//					case FileUtil.ROTATED_NONE: radiands = 0;   width = oldImage.getHeight(); height = oldImage.getWidth();  break;
//					case FileUtil.ROTATED_180:  radiands = 180; width = oldImage.getHeight(); height = oldImage.getWidth();  break;
//					case FileUtil.ROTATED_270:  radiands = 90;  width = oldImage.getWidth();  height = oldImage.getHeight(); break;
//					case FileUtil.ROTATED_90:   radiands = 270; width = oldImage.getWidth();  height = oldImage.getHeight(); break;
//					default: 
//						radiands = 0;  width = oldImage.getWidth();  height = oldImage.getHeight(); break;
//					}
//					
//					BufferedImage newImage = new BufferedImage(height,width, oldImage.getType());
//					Graphics2D graphics = (Graphics2D) newImage.getGraphics();
//					
//					graphics.rotate(Math.toRadians(radiands), newImage.getWidth() / 2, newImage.getHeight() / 2);
//					graphics.translate((newImage.getWidth() - oldImage.getWidth()) / 2, (newImage.getHeight() - oldImage.getHeight()) / 2);
//					graphics.drawImage(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(), null);
//					ImageIO.write(newImage, fileExt, new FileOutputStream(file));
//				}
//			}

			// 저장하나 파일 크기를 가져온다
			mv.addObject( "fileSize"		, file.length() );
			mv.addObject( "erroryn"			, "N" );
			mv.addObject( "filePath"		, datePath + "/" + fileName );
			mv.addObject( "fileName"		, fileName );
			mv.addObject( "fieldName"		, fieldName );
			mv.addObject( "originFileName"	, originFileName );
			
		} catch( Exception e ) {
			logger.error( e.getMessage() );
			logger.error( ExceptionUtils.getStackTrace( e ) );
			mv.addObject( "erroryn", "Y" );
		}
		
		mv.setViewName( "common/upload_result" );
		return mv;
	}
}
