/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 운영자
 * FILE NAME      : FileUtil.java
 * DESCRIPTION    : 파일 관련 Util class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2013-06-05      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;


public class FileUtil {
	
	protected static Log logger = LogFactory.getLog(FileUtil.class);
	
	/**
	 * 파일명을 전달받아 확장자를 반환한다.
	 *
	 * @param String filename   : 파일명
	 * @return String extension : 확장자
	*/
	public static String getExtension(String filename) {

		String extension = "";

		if(filename != null) {
			int lm_iIndex = filename.lastIndexOf('.');

			if(lm_iIndex > -1) {
				extension = filename.substring(lm_iIndex + 1);
			}
		}

		return extension;

	}
	
	/**
	 * 현재시간 기준으로 파일을 저장할
	 * /YYYY/MM/DD 형태의 경로를 가져온다.
	 *
	 * @return String datePath : /YYYY/MM/DD 형태의 경로
	 */
	public static String getDatePath() {
		
		String datePath = "";       //반환할 경로
		
		//현재 날짜를 세팅한다.
		Date date = new Date();
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
				
		String year = Integer.toString(gregorianCalendar.get(Calendar.YEAR));        //년
		String month = Integer.toString(gregorianCalendar.get(Calendar.MONTH) + 1);  //월
		String day = Integer.toString(gregorianCalendar.get(Calendar.DATE));         //일

		//월일시분초가 1자리로 나올 경우 2자리로 맞춘다.
		if(month.length() == 1) {
			month = "0" + month;
		}
		
		if(day.length() == 1) {
			day = "0" + day;
		}
		
		datePath = "/" + year + "/" + month + "/" + day;

		return datePath;
		
	}
	
	/**
	 * 확장자를 전달받아 업로드 가능한 파일인지 여부를 반환한다.
	 * 
	 * @param String fileExt : 확장자
	 * @return boolean       : 업로드 가능한 파일인지 여부
	*/
	public static boolean isAllowedFile(String fileExt) {
		
		boolean isAllowed = true;
		
		if(fileExt == null) {
			return false;
		}
		
		if(fileExt.length() == 0) {
			return true;
		}
		
		String lowerFileExt = fileExt.toLowerCase().trim();
				
		String extList = "cab, exe, asp, jsp, aspx, php, cgi, js, eml, wap, ocp, dll";
		
		if(extList.indexOf(lowerFileExt) != -1) {
			isAllowed = false;
		}
		
		return isAllowed;
				
	}
	
	/**
	 * 원본 파일풀경로와 이동될 파일풀경로를 전달받아 해당 파일을 이동시킨다.
	 * 
	 * @param String src         : 원본 파일풀경로
	 * @param String des         : 이동될 파일풀경로
	 * @return boolean isSuccess : 결과
	*/
	public boolean move(String src, String des) {
    	
		boolean isSuccess = false;  //복사 성공여부
		int bufferSize = 32768;     //Buffer Size
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		
		try {
			
			File file = new File(src);
			
			if(!file.exists()) {
				return isSuccess;
			}
			
			File saveFolder = new File(des.substring(0, des.lastIndexOf("/")));
			
			if(!saveFolder.exists()) {
				saveFolder.mkdirs();
			}
			
			bufferedInputStream = new BufferedInputStream(new FileInputStream(src));
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(des));
			            
			byte[] buffer = new byte[bufferSize];
            
            int i = 0;
            
            while( (i = bufferedInputStream.read(buffer)) != -1) {
            	bufferedOutputStream.write(buffer, 0, i);
            	bufferedOutputStream.flush();
			}
            
            //복사가 끝나면 원본을 삭제한다.
            if(file.exists()) {
            	file.delete();
			}
            
            isSuccess = true;
			
		} catch(Exception e) {
			logger.error(e);
		} finally {
			if(bufferedInputStream != null) try{ bufferedInputStream.close(); } catch (Exception e) { }
            if(bufferedOutputStream != null) try{ bufferedOutputStream.close(); } catch(Exception e) { }
		}
		
		return isSuccess;
		
	}

	public static String tempFileCopy(String menufg, String filePath) {
		String tempRootPath = MessageUtil.getSystemMessage("system.data.temp.image.path");
		String rootPath = ""; 
				
		if(menufg.equals("thumb")) { //웹툰 썸네일 이미지
			rootPath = MessageUtil.getSystemMessage("system.data.webtoon.thumb.path");
		} else if(menufg.equals("webtoon")) { //회차 이미지
			rootPath = MessageUtil.getSystemMessage("system.data.webtoon.image.path");
		} else if(menufg.equals("banner")) { //배너 이미지
			rootPath = MessageUtil.getSystemMessage("system.data.banner.image.path");
		} else if(menufg.equals("rcdapp")) { //추천앱 이미지 
			rootPath = MessageUtil.getSystemMessage("system.data.rcdapp.image.path");
		} else if(menufg.equals("zine")) { //요요진 이미지
			rootPath = MessageUtil.getSystemMessage("system.data.zine.thumb.path");
		} else if(menufg.equals("author")) { //작가 프로필 이미지 파일 경로
			rootPath = MessageUtil.getSystemMessage("system.data.author.image.path");
		} else if(menufg.equals("temp")) { //임시 저장 파일 경로
			rootPath = MessageUtil.getSystemMessage("system.data.temp.image.path");
		} else if(menufg.equals("logo")) { //로고 파일 경로
			rootPath = MessageUtil.getSystemMessage("system.data.logo.image.path");
		} else if(menufg.equals("popup")) { //팝업 이미지 파일 경로
			rootPath = MessageUtil.getSystemMessage("system.data.popup.image.path");
		} else if(menufg.equals("webtoonbanner")) { //웹툰배너 파일 경로
			rootPath = MessageUtil.getSystemMessage("system.data.webtoonbanner.image.path");
		} else if(menufg.equals("namecon")) { //네임콘, 스티콘 파일 경로
			rootPath = MessageUtil.getSystemMessage("system.data.namecon.image.path");
		} else if(menufg.equals("event")) { //이벤트
			rootPath = MessageUtil.getSystemMessage("system.data.event.image.path");
		}
		
		//DB에 들어갈 경로 셋팅
		String newImagePath = filePath.replace("/download/temp", "/download/"+menufg);
		//원본, 타겟 경로 생성
		String tempImagePath = filePath.replace("/download/temp", "");
		String sourcePath = tempRootPath + tempImagePath;
		String targetPath = rootPath + tempImagePath;
		// 타켓 디렉토리 생성
		File dir = new File(targetPath);
		String parentPath = dir.getParent();
		File parentDir = new File(parentPath);
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		// 파일 복사
		try {
				
			fis = new FileInputStream(sourcePath);
			fos = new FileOutputStream(targetPath);
			
			int readInt = 0;
			byte[] buffer = new byte[1024];
			
			while ((readInt = fis.read(buffer, 0, 1024)) != -1) {
				fos.write(buffer, 0, (char)readInt);	
			}
		} catch(IOException e) {	
			logger.error("$$$$$$$$$$$$$$$$"+e);		
		} finally {
			if(fis != null) try{ fis.close(); } catch (Exception e) { }
            if(fos != null) try{ fos.close(); } catch(Exception e) { }
		}
		
		// DB경로 리턴
		return newImagePath;
	}
	
	
	public String getDownloadPath( String domain, String savedPath )
	{
		if( StringUtils.isEmpty( savedPath ) ) return "";
		
		return "http://webtoonimg.kthcorp.com/download/" + domain + savedPath; 
	}
	
	
	 public static final int RATIO = 0;
     public static final int SAME = -1;
     
     public static String resize(String srcPath, String div, int destWidth, int destHeight)  {
    	 String tempRootPath = MessageUtil.getSystemMessage("system.data.temp.image.path");
    	 
    	 String srcFilePath = tempRootPath+srcPath;
    	 File src = new File(srcFilePath);
    	 
    	 String desFilePath = srcFilePath.substring(0, srcFilePath.lastIndexOf(".")) + div + srcFilePath.substring(srcFilePath.lastIndexOf("."));
    	 File dest = new File(desFilePath);
    	 String destPath = srcPath.replaceAll(src.getName(), "");
    	 
    	 try{	 		
	         Image srcImg = null;
	         String suffix = src.getName().substring(src.getName().lastIndexOf('.')+1).toLowerCase();
	         if (suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif")) {
	             srcImg = ImageIO.read(src);
	         } else {
	             // BMP가 아닌 경우 ImageIcon을 활용해서 Image 생성
	             // 이렇게 하는 이유는 getScaledInstance를 통해 구한 이미지를
	             // PixelGrabber.grabPixels로 리사이즈 할때
	             // 빠르게 처리하기 위함이다.
	             srcImg = new ImageIcon(src.toURL()).getImage();
	         }
	         
	         int srcWidth = srcImg.getWidth(null);
	         int srcHeight = srcImg.getHeight(null);	         

             double ratio = ((double)destWidth) / ((double)srcWidth);             
             int tmpDestHeight = (int)((double)srcHeight * ratio);
             
             //만약 Height가 비율값으로 적용된 Height값보다 크면 비율을 다시 조정
             if(tmpDestHeight <= destHeight){
            	 destHeight = tmpDestHeight;
             }else{
            	 ratio = ((double)destHeight) / ((double)srcHeight);
            	 destWidth = (int)((double)srcWidth * ratio);
             }
	         
	         Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH); 
	         int pixels[] = new int[destWidth * destHeight]; 
	         PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth, destHeight, pixels, 0, destWidth); 
	         try {
	             pg.grabPixels();
	         } catch (InterruptedException e) {
	             throw new IOException(e.getMessage());
	         } 
	         BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB); 
	         destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth); 
	         
	         ImageIO.write(destImg, suffix, dest);
    	 }catch(Exception e){
    		 logger.error(e);
    	 }
         
         return destPath+dest.getName();
     }
     
    public static final int ROTATED_NONE = 1;
    public static final int ROTATED_180  = 3;
    public static final int ROTATED_270  = 6;
    public static final int ROTATED_90   = 8;
    
    public static int getImageOrientation(File file){
 		
 		int orientation = -1;
 		try {

 		    Metadata metadata = ImageMetadataReader.readMetadata(file);

 		    // Read Exif Data
 		    Directory directory = metadata.getDirectory( ExifIFD0Directory.class );
 		            
 		    if( directory != null )
 		    {
 		        // Read Orientation
 		        orientation = directory.getInt( ExifIFD0Directory.TAG_ORIENTATION );
 		        /*
 		        orientation 1 : 0도 회전
 		        orientation 3 : 180도 회전(뒤집어진)
 		        orientation 6 : 시계방향으로 270도 회전
 		        orientation 8 : 시계방향으로 90도 회전된 사진
 		         */ 

 		        // Read the datetime
// 		        directory = metadata.getDirectory(ExifSubIFDDirectory.class);
// 		        // 촬영일 정보는 ExifSubIFDDirectory에서 읽음
// 		        Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
// 		        if (date == null) {
// 		            // 촬영일이 없는경우 파일생성일로 읽음
// 		            directory = metadata.getDirectory( ExifIFD0Directory.class );
// 		            date = directory.getDate( ExifIFD0Directory.TAG_DATETIME );
// 		        }

 		    }
 		} catch (ImageProcessingException e1) {
 		    e1.printStackTrace();
 		} catch (IOException e1) {
 		    e1.printStackTrace();
 		} catch (MetadataException e) {
 		    e.printStackTrace();
 		}
 		
 		return orientation;
 	}
}