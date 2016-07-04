package com.olleh.webtoon.common.dao.advertisement.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class AdvertisementDomain{
	
	private int advertisementseq;		//광고순번
	private String title; 						//제목
	private String advertisementfg;		//광고구분(advertisement:광고,  banner:단순배너)
	private String devicefg;					//디바이스 구분(pc/mobile)
	private String areafg;					//영역구분  (mainM:PC메인중단, listR:PC목록우측, listM:PC목록중단, detailM:PC상세중단, detailB:PC상세하단, login:PC로그인, passwd:PC비밀번호찾기, mdaylist:모바일요일목록, mgenrelist:모바일장르목록, mdetailM:모바일상세중단, mdetailMB:모바일상세중하단, mdetailB:모바일상세하단)
	private String displayyn;				//전시여부(Y/N)
	private String startdt;					//전시시작일시 (YYYYMMDDHHMMSS)
	private String enddt;					//전시종료일시 (YYYYMMDDHHMMSS)
	private String imagepath;				//이미지파일 풀경로
	private String imagefilenm;			//이미지원본파일명
	private String regid;        				//등록자ID
	private String regdt;		  				//등록일시 (YYYYMMDDHHMMSS)
	private String modid;	 				//수정자ID
	private String moddt;   				//수정일시 (YYYYMMDDHHMMSS)
	

	private String newwindowyn;
	private String mnewwindowyn;
	private String imnewwindowyn;
	private String iosnewwindowyn;
	private String androidnewwindowyn;
	private String linkurl;
	private String mlinkurl;
	private String imlinkurl;
	private String ioslinkurl;
	private String androidlinkurl;
	

	private String webtoonseq;
	private String timesseq;
	private String bgcolor;
	private String comment;
	
	
	public int getAdvertisementseq()
	{
		return advertisementseq;
	}
	public void setAdvertisementseq( int advertisementseq )
	{
		this.advertisementseq = advertisementseq;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle( String title )
	{
		this.title = title;
	}
	public String getAdvertisementfg()
	{
		return advertisementfg;
	}
	public void setAdvertisementfg( String advertisementfg )
	{
		this.advertisementfg = advertisementfg;
	}
	public String getDevicefg()
	{
		return devicefg;
	}
	public void setDevicefg( String devicefg )
	{
		this.devicefg = devicefg;
	}
	public String getAreafg()
	{
		return areafg;
	}
	public void setAreafg( String areafg )
	{
		this.areafg = areafg;
	}
	public String getDisplayyn()
	{
		return displayyn;
	}
	public void setDisplayyn( String displayyn )
	{
		this.displayyn = displayyn;
	}
	public String getStartdt()
	{
		return startdt;
	}
	public void setStartdt( String startdt )
	{
		this.startdt = startdt;
	}
	public String getEnddt()
	{
		return enddt;
	}
	public void setEnddt( String enddt )
	{
		this.enddt = enddt;
	}
	public String getNewwindowyn()
	{
		return newwindowyn;
	}
	public void setNewwindowyn( String newwindowyn )
	{
		this.newwindowyn = newwindowyn;
	}
	public String getLinkurl()
	{
		return linkurl;
	}
	public void setLinkurl( String linkurl )
	{
		this.linkurl = linkurl;
	}
	public String getImagepath()
	{
		if(imagepath != null && imagepath.indexOf("http") < 0 && imagepath.indexOf("https") < 0) {
			if("real".equals(System.getProperty("serverType"))){
				imagepath = MessageUtil.getSystemMessage("system.image.server.domain.ssl") + "/download/ad"+imagepath;
			} else {
				imagepath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/ad"+imagepath;
			}
		}
		
		return imagepath;
	}
	public void setImagepath( String imagepath )
	{
		this.imagepath = imagepath;
	}
	public String getImagefilenm()
	{
		
		return imagefilenm;
	}
	public void setImagefilenm( String imagefilenm )
	{
		this.imagefilenm = imagefilenm;
	}
	public String getRegid()
	{
		return regid;
	}
	public void setRegid( String regid )
	{
		this.regid = regid;
	}
	public String getRegdt()
	{
		return regdt;
	}
	public void setRegdt( String regdt )
	{
		this.regdt = regdt;
	}
	public String getModid()
	{
		return modid;
	}
	public void setModid( String modid )
	{
		this.modid = modid;
	}
	public String getModdt()
	{
		return moddt;
	}
	public void setModdt( String moddt )
	{
		this.moddt = moddt;
	}
	public String getMnewwindowyn() {
		return mnewwindowyn;
	}
	public void setMnewwindowyn(String mnewwindowyn) {
		this.mnewwindowyn = mnewwindowyn;
	}
	public String getIosnewwindowyn() {
		return iosnewwindowyn;
	}
	public void setIosnewwindowyn(String iosnewwindowyn) {
		this.iosnewwindowyn = iosnewwindowyn;
	}
	public String getAndroidnewwindowyn() {
		return androidnewwindowyn;
	}
	public void setAndroidnewwindowyn(String androidnewwindowyn) {
		this.androidnewwindowyn = androidnewwindowyn;
	}
	public String getMlinkurl() {
		return mlinkurl;
	}
	public void setMlinkurl(String mlinkurl) {
		this.mlinkurl = mlinkurl;
	}
	public String getIoslinkurl() {
		return ioslinkurl;
	}
	public void setIoslinkurl(String ioslinkurl) {
		this.ioslinkurl = ioslinkurl;
	}
	public String getAndroidlinkurl() {
		return androidlinkurl;
	}
	public void setAndroidlinkurl(String androidlinkurl) {
		this.androidlinkurl = androidlinkurl;
	}
	public String getWebtoonseq() {
		return webtoonseq;
	}
	public void setWebtoonseq(String webtoonseq) {
		this.webtoonseq = webtoonseq;
	}
	public String getTimesseq() {
		return timesseq;
	}
	public void setTimesseq(String timesseq) {
		this.timesseq = timesseq;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getImnewwindowyn() {
		return imnewwindowyn;
	}
	public void setImnewwindowyn(String imnewwindowyn) {
		this.imnewwindowyn = imnewwindowyn;
	}
	public String getImlinkurl() {
		return imlinkurl;
	}
	public void setImlinkurl(String imlinkurl) {
		this.imlinkurl = imlinkurl;
	}
}

