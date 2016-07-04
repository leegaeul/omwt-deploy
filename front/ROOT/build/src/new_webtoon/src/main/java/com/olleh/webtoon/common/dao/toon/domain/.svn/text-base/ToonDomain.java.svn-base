/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : ToonDomain.java
 * DESCRIPTION    : 웹툰 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-23      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.toon.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class ToonDomain {
	private int webtoonseq;
	private String webtoonnm;
	private String subtitle;
	private String pcdisplayyn;
	private String mdisplayyn;
	private String toonyn;
	private String premiumyn;
	private String terminationyn;
	private String terminationdt;
	private String thumbpath;
	private String nthumbpath;
	private String sthumbpath;
	private String imagepath;
	private String thumbfilenm;
	private String nthumbfilenm;
	private String mthumbpath;
	private String mthumbfilenm;
	private String imagefilenm;
	private String mimagepath;
	private String mimagefilenm;
	
	private String authorseq1;
	private String authorseq2;
	private String authorseq3;
	
	private String authornm1;
	private String authornm2;
	private String authornm3;
	
	private int genreseq1;
	private int genreseq2;
	private int genreseq3;
	
	private String genrenm1;
	private String genrenm2;
	private String genrenm3;
	
	private String agefg;
	private String enddt;
	private String republishyn;
	private String mondayyn;
	private String tuesdayyn;
	private String wednesdayyn;
	private String thursdayyn;
	private String fridayyn;
	private String saturdayyn;
	private String sundayyn;
	private String serialfg;
	private String reststartdt;
	private String restenddt;
	private String iconseq;
	private String webtoondesc;
	private String regid;
	private String regdt;
	private String modid;
	private String moddt;
	private String maxtimesno;
	private String maxtimestitle;
	private String maxtimesthumbpath;
	private String totalstickercnt;
	private String sumtotalstickercnt;
	
	private String initialword;
	
	private String lastyn;
	private int timesseq;
	private String timesno;
	private String timestitle;
	private int prevtimesseq;
	private int nexttimesseq;
	private int firsttimesseq;
	private String prevtimestitle;
	private String nexttimestitle;
	private String comments;
	private String restcomment;
	
	private String newyn;
	private String upyn;
	private String endyn;
	private String restyn;
	private String pushyn;
	
	private String refseq1;
	private String refseq2;
	private String refseq3;
	
	private String refwebtoonseq1;
	private String refwebtoonseq2;
	private String refwebtoonseq3;
	
	private String reftimestitle1;
	private String reftimestitle2;
	private String reftimestitle3;
	
	private String oniconurl;
	private String officonurl;
	private String listiconurl;
	private String defaultyn;
	
	private String yoyozineseq;
	
	private String sellyn;
	private int prdorgprice;
	private String prdprice;
	private int prdterm;
	private int sumprice;
	private String purchaseyn;
	private String discountyn;
	private String prddiscountprice;
	private String discountpercent;     
	private String buyid;
	private int timesprdcnt;
	
	private String prdhistoryseq;
	private String comment;
	private int prevRanking;
	
	private String bgmdisplayyn;
	private String bgmpath1;
	private String bgmpath2;
	
	private String purchasefg;
	
	private String toonfg;
	private String illustauthorseq1;
	private String illustauthorseq2;
	private String illustauthorseq3;
	private String illustauthornm1;
	private String illustauthornm2;
	private String illustauthornm3;
	private String bgcolor;
	
	private String authorprofileyn1;
	private String authorprofileyn2;
	private String authorprofileyn3;
	private String illustprofileyn1;
	private String illustprofileyn2;
	private String illustprofileyn3;
	
	private String refkeyword;
	
	public int getWebtoonseq() {
		return webtoonseq;
	}
	public void setWebtoonseq(int webtoonseq) {
		this.webtoonseq = webtoonseq;
	}
	public String getWebtoonnm() {
		return webtoonnm;
	}
	public void setWebtoonnm(String webtoonnm) {
		this.webtoonnm = webtoonnm;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getPcdisplayyn() {
		return pcdisplayyn;
	}
	public void setPcdisplayyn(String pcdisplayyn) {
		this.pcdisplayyn = pcdisplayyn;
	}
	public String getMdisplayyn() {
		return mdisplayyn;
	}
	public void setMdisplayyn(String mdisplayyn) {
		this.mdisplayyn = mdisplayyn;
	}
	public String getToonyn() {
		return toonyn;
	}
	public void setToonyn(String toonyn) {
		this.toonyn = toonyn;
	}
	public String getPremiumyn() {
		return premiumyn;
	}
	public void setPremiumyn(String premiumyn) {
		this.premiumyn = premiumyn;
	}
	public String getTerminationyn() {
		return terminationyn;
	}
	public void setTerminationyn(String terminationyn) {
		this.terminationyn = terminationyn;
	}
	public String getTerminationdt() {
		return terminationdt;
	}
	public void setTerminationdt(String terminationdt) {
		this.terminationdt = terminationdt;
	}
	public String getThumbpath() {
		if(thumbpath != null && thumbpath.indexOf("http") < 0)
			thumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+thumbpath;
		
		return thumbpath;
	}
	public void setThumbpath(String thumbpath) {
		this.thumbpath = thumbpath;
	}	
	public String getSthumbpath() {
		return sthumbpath;
	}
	public void setSthumbpath(String sthumbpath) {
		if(sthumbpath != null && sthumbpath.indexOf("http") < 0)
			sthumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+sthumbpath;
		
		this.sthumbpath = sthumbpath;
	}
	public String getThumbfilenm() {
		return thumbfilenm;
	}
	public void setThumbfilenm(String thumbfilenm) {
		this.thumbfilenm = thumbfilenm;
	}
	public String getMthumbpath() {
		if(mthumbpath != null && mthumbpath.indexOf("http") < 0)
			mthumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+mthumbpath;
		
		return mthumbpath;
	}
	public void setMthumbpath(String mthumbpath) {
		this.mthumbpath = mthumbpath;
	}
	public String getMthumbfilenm() {
		return mthumbfilenm;
	}
	public void setMthumbfilenm(String mthumbfilenm) {
		this.mthumbfilenm = mthumbfilenm;
	}
	public String getImagepath() {
		if(imagepath != null && imagepath.indexOf("http") < 0)
			imagepath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+imagepath;
		
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getImagefilenm() {
		return imagefilenm;
	}
	public void setImagefilenm(String imagefilenm) {
		this.imagefilenm = imagefilenm;
	}
	public String getMimagepath() {
		return mimagepath;
	}
	public void setMimagepath(String mimagepath) {
		this.mimagepath = mimagepath;
	}
	public String getMimagefilenm() {
		return mimagefilenm;
	}
	public void setMimagefilenm(String mimagefilenm) {
		this.mimagefilenm = mimagefilenm;
	}
	public String getAuthorseq1() {
		return authorseq1;
	}
	public void setAuthorseq1(String authorseq1) {
		this.authorseq1 = authorseq1;
	}
	public String getAuthorseq2() {
		return authorseq2;
	}
	public void setAuthorseq2(String authorseq2) {
		this.authorseq2 = authorseq2;
	}
	public String getAuthorseq3() {
		return authorseq3;
	}
	public void setAuthorseq3(String authorseq3) {
		this.authorseq3 = authorseq3;
	}
	public int getGenreseq1() {
		return genreseq1;
	}
	public void setGenreseq1(int genreseq1) {
		this.genreseq1 = genreseq1;
	}
	public int getGenreseq2() {
		return genreseq2;
	}
	public void setGenreseq2(int genreseq2) {
		this.genreseq2 = genreseq2;
	}
	public int getGenreseq3() {
		return genreseq3;
	}
	public void setGenreseq3(int genreseq3) {
		this.genreseq3 = genreseq3;
	}
	public String getGenrenm1() {
		return genrenm1;
	}
	public void setGenrenm1(String genrenm1) {
		this.genrenm1 = genrenm1;
	}
	public String getGenrenm2() {
		return genrenm2;
	}
	public void setGenrenm2(String genrenm2) {
		this.genrenm2 = genrenm2;
	}
	public String getGenrenm3() {
		return genrenm3;
	}
	public void setGenrenm3(String genrenm3) {
		this.genrenm3 = genrenm3;
	}
	public String getAgefg() {
		return agefg;
	}
	public void setAgefg(String agefg) {
		this.agefg = agefg;
	}
	public String getEnddt() {
		return enddt;
	}
	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}
	public String getRepublishyn() {
		return republishyn;
	}
	public void setRepublishyn(String republishyn) {
		this.republishyn = republishyn;
	}
	public String getMondayyn() {
		return mondayyn;
	}
	public void setMondayyn(String mondayyn) {
		this.mondayyn = mondayyn;
	}	
	public String getTuesdayyn() {
		return tuesdayyn;
	}
	public void setTuesdayyn(String tuesdayyn) {
		this.tuesdayyn = tuesdayyn;
	}
	public String getWednesdayyn() {
		return wednesdayyn;
	}
	public void setWednesdayyn(String wednesdayyn) {
		this.wednesdayyn = wednesdayyn;
	}
	public String getThursdayyn() {
		return thursdayyn;
	}
	public void setThursdayyn(String thursdayyn) {
		this.thursdayyn = thursdayyn;
	}
	public String getFridayyn() {
		return fridayyn;
	}
	public void setFridayyn(String fridayyn) {
		this.fridayyn = fridayyn;
	}
	public String getSaturdayyn() {
		return saturdayyn;
	}
	public void setSaturdayyn(String saturdayyn) {
		this.saturdayyn = saturdayyn;
	}
	public String getSundayyn() {
		return sundayyn;
	}
	public void setSundayyn(String sundayyn) {
		this.sundayyn = sundayyn;
	}
	public String getSerialfg() {
		return serialfg;
	}
	public void setSerialfg(String serialfg) {
		this.serialfg = serialfg;
	}
	public String getReststartdt() {
		return reststartdt;
	}
	public void setReststartdt(String reststartdt) {
		this.reststartdt = reststartdt;
	}
	public String getRestenddt() {
		return restenddt;
	}
	public void setRestenddt(String restenddt) {
		this.restenddt = restenddt;
	}
	public String getIconseq() {
		return iconseq;
	}
	public void setIconseq(String iconseq) {
		this.iconseq = iconseq;
	}
	public String getWebtoondesc() {
		return webtoondesc;
	}
	public void setWebtoondesc(String webtoondesc) {
		this.webtoondesc = webtoondesc;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getModid() {
		return modid;
	}
	public void setModid(String modid) {
		this.modid = modid;
	}
	public String getModdt() {
		return moddt;
	}
	public void setModdt(String moddt) {
		this.moddt = moddt;
	}
	
	public String getAuthornm1() {
		return authornm1;
	}
	public void setAuthornm1(String authornm1) {
		this.authornm1 = authornm1;
	}
	public String getAuthornm2() {
		return authornm2;
	}
	public void setAuthornm2(String authornm2) {
		this.authornm2 = authornm2;
	}
	public String getAuthornm3() {
		return authornm3;
	}
	public void setAuthornm3(String authornm3) {
		this.authornm3 = authornm3;
	}
	public String getMaxtimesno() {
		return maxtimesno;
	}
	public void setMaxtimesno(String maxtimesno) {
		this.maxtimesno = maxtimesno;
	}
	public String getMaxtimestitle() {
		return maxtimestitle;
	}
	public void setMaxtimestitle(String maxtimestitle) {
		this.maxtimestitle = maxtimestitle;
	}
	public String getMaxtimesthumbpath() {
		if(maxtimesthumbpath != null && maxtimesthumbpath.indexOf("http") < 0)
			maxtimesthumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+maxtimesthumbpath;
		
		return maxtimesthumbpath;
	}
	public void setMaxtimesthumbpath(String maxtimesthumbpath) {
		this.maxtimesthumbpath = maxtimesthumbpath;
	}
	public String getTotalstickercnt() {
		return totalstickercnt;
	}
	public void setTotalstickercnt(String totalstickercnt) {
		this.totalstickercnt = totalstickercnt;
	}
	public String getSumtotalstickercnt() {
		return sumtotalstickercnt;
	}
	public void setSumtotalstickercnt(String sumtotalstickercnt) {
		this.sumtotalstickercnt = sumtotalstickercnt;
	}
	
	
	public String getInitialword() {
		return initialword;
	}
	public void setInitialword(String initialword) {
		this.initialword = initialword;
	}	
	
	public String getLastyn() {
		return lastyn;
	}
	public void setLastyn(String lastyn) {
		this.lastyn = lastyn;
	}
	public int getTimesseq() {
		return timesseq;
	}
	public void setTimesseq(int timesseq) {
		this.timesseq = timesseq;
	}
	public String getTimesno() {
		return timesno;
	}
	public void setTimesno(String timesno) {
		this.timesno = timesno;
	}
	public String getTimestitle() {
		return timestitle;
	}
	public void setTimestitle(String timestitle) {
		this.timestitle = timestitle;
	}
	public int getPrevtimesseq() {
		return prevtimesseq;
	}
	public void setPrevtimesseq(int prevtimesseq) {
		this.prevtimesseq = prevtimesseq;
	}
	public int getNexttimesseq() {
		return nexttimesseq;
	}
	public void setNexttimesseq(int nexttimesseq) {
		this.nexttimesseq = nexttimesseq;
	}
	public int getFirsttimesseq() {
		return firsttimesseq;
	}
	public void setFirsttimesseq(int firsttimesseq) {
		this.firsttimesseq = firsttimesseq;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}	
	public String getRestcomment() {
		return restcomment;
	}
	public void setRestcomment(String restcomment) {
		this.restcomment = restcomment;
	}
	public String getNewyn() {
		return newyn;
	}
	public void setNewyn(String newyn) {
		this.newyn = newyn;
	}
	public String getUpyn() {
		return upyn;
	}
	public void setUpyn(String upyn) {
		this.upyn = upyn;
	}
	public String getRestyn() {
		return restyn;
	}
	public void setRestyn(String restyn) {
		this.restyn = restyn;
	}
	public String getRefseq1() {
		return refseq1;
	}
	public void setRefseq1(String refseq1) {
		this.refseq1 = refseq1;
	}
	public String getRefseq2() {
		return refseq2;
	}
	public void setRefseq2(String refseq2) {
		this.refseq2 = refseq2;
	}
	public String getRefseq3() {
		return refseq3;
	}
	public void setRefseq3(String refseq3) {
		this.refseq3 = refseq3;
	}	
	public String getRefwebtoonseq1() {
		return refwebtoonseq1;
	}
	public void setRefwebtoonseq1(String refwebtoonseq1) {
		this.refwebtoonseq1 = refwebtoonseq1;
	}
	public String getRefwebtoonseq2() {
		return refwebtoonseq2;
	}
	public void setRefwebtoonseq2(String refwebtoonseq2) {
		this.refwebtoonseq2 = refwebtoonseq2;
	}
	public String getRefwebtoonseq3() {
		return refwebtoonseq3;
	}
	public void setRefwebtoonseq3(String refwebtoonseq3) {
		this.refwebtoonseq3 = refwebtoonseq3;
	}
	public String getReftimestitle1() {
		return reftimestitle1;
	}
	public void setReftimestitle1(String reftimestitle1) {
		this.reftimestitle1 = reftimestitle1;
	}
	public String getReftimestitle2() {
		return reftimestitle2;
	}
	public void setReftimestitle2(String reftimestitle2) {
		this.reftimestitle2 = reftimestitle2;
	}
	public String getReftimestitle3() {
		return reftimestitle3;
	}
	public void setReftimestitle3(String reftimestitle3) {
		this.reftimestitle3 = reftimestitle3;
	}
	public String getOniconurl() {	
		if(oniconurl != null && oniconurl.indexOf("http") < 0 && defaultyn!= null && "N".equals(defaultyn))
			oniconurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/sticker"+oniconurl;
		
		return oniconurl;
	}
	public void setOniconurl(String oniconurl) {
		this.oniconurl = oniconurl;
	}
	public String getOfficonurl() {		
		if(officonurl != null && officonurl.indexOf("http") < 0 && defaultyn!= null && "N".equals(defaultyn))
			officonurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/sticker"+officonurl;
		
		return officonurl;
	}
	public void setOfficonurl(String officonurl) {
		this.officonurl = officonurl;
	}
	public String getListiconurl() {	
		if(listiconurl != null && listiconurl.indexOf("http") < 0 && defaultyn!= null && "N".equals(defaultyn))
			listiconurl = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/sticker"+listiconurl;
		
		return listiconurl;
	}
	public void setListiconurl(String listiconurl) {
		this.listiconurl = listiconurl;
	}
	
	
	public String getDefaultyn() {
		return defaultyn;
	}
	public void setDefaultyn(String defaultyn) {
		this.defaultyn = defaultyn;
	}
	public String getYoyozineseq() {
		return yoyozineseq;
	}
	public void setYoyozineseq(String yoyozineseq) {
		this.yoyozineseq = yoyozineseq;
	}
	public String getSellyn() {
		return sellyn;
	}
	public void setSellyn(String sellyn) {
		this.sellyn = sellyn;
	}
	public int getPrdorgprice() {
		return prdorgprice;
	}
	public void setPrdorgprice(int prdorgprice) {
		this.prdorgprice = prdorgprice;
	}
	public String getPrdprice() {
		return prdprice;
	}
	public void setPrdprice(String prdprice) {
		this.prdprice = prdprice;
	}
	public int getPrdterm() {
		return prdterm;
	}
	public void setPrdterm(int prdterm) {
		this.prdterm = prdterm;
	}
	public String getPurchaseyn() {
		return purchaseyn;
	}
	public void setPurchaseyn(String purchaseyn) {
		this.purchaseyn = purchaseyn;
	}
	public int getSumprice() {
		return sumprice;
	}
	public void setSumprice(int sumprice) {
		this.sumprice = sumprice;
	}
	public String getDiscountyn() {
		return discountyn;
	}
	public void setDiscountyn(String discountyn) {
		this.discountyn = discountyn;
	}
	public String getPrddiscountprice() {
		return prddiscountprice;
	}
	public void setPrddiscountprice(String prddiscountprice) {
		this.prddiscountprice = prddiscountprice;
	}
	public String getBuyid() {
		return buyid;
	}
	public void setBuyid(String buyid) {
		this.buyid = buyid;
	}
	public String getPrdhistoryseq() {
		return prdhistoryseq;
	}
	public void setPrdhistoryseq(String prdhistoryseq) {
		this.prdhistoryseq = prdhistoryseq;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getPrevRanking() {
		return prevRanking;
	}
	public void setPrevRanking(int prevRanking) {
		this.prevRanking = prevRanking;
	}
	public String getPushyn() {
		return pushyn;
	}
	public void setPushyn(String pushyn) {
		this.pushyn = pushyn;
	}
	public String getDiscountpercent() {
		return discountpercent;
	}
	public void setDiscountpercent(String discountpercent) {
		this.discountpercent = discountpercent;
	}
	public int getTimesprdcnt() {
		return timesprdcnt;
	}
	public void setTimesprdcnt(int timesprdcnt) {
		this.timesprdcnt = timesprdcnt;
	}
	public String getBgmdisplayyn() {
		return bgmdisplayyn;
	}
	public void setBgmdisplayyn(String bgmdisplayyn) {
		this.bgmdisplayyn = bgmdisplayyn;
	}
	public String getBgmpath1() {
		if(bgmpath1 != null && bgmpath1.indexOf("http") < 0)
			bgmpath1 = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/webtoon"+bgmpath1;
		
		return bgmpath1;
	}
	public void setBgmpath1(String bgmpath1) {
		this.bgmpath1 = bgmpath1;
	}
	public String getBgmpath2() {
		if(bgmpath2 != null && bgmpath2.indexOf("http") < 0)
			bgmpath2 = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/webtoon"+bgmpath2;
		
		return bgmpath2;
	}
	public void setBgmpath2(String bgmpath2) {
		this.bgmpath2 = bgmpath2;
	}
	public String getPurchasefg() {
		return purchasefg;
	}
	public void setPurchasefg(String purchasefg) {
		this.purchasefg = purchasefg;
	}
	public String getNthumbpath() {
		if(nthumbpath != null && nthumbpath.indexOf("http") < 0)
			nthumbpath = MessageUtil.getSystemMessage("system.image.server.domain") + "/download/thumb"+nthumbpath;
		
		return nthumbpath;
	}
	public void setNthumbpath(String nthumbpath) {
		this.nthumbpath = nthumbpath;
	}
	public String getNthumbfilenm() {
		return nthumbfilenm;
	}
	public void setNthumbfilenm(String nthumbfilenm) {
		this.nthumbfilenm = nthumbfilenm;
	}
	public String getIllustauthorseq1() {
		return illustauthorseq1;
	}
	public void setIllustauthorseq1(String illustauthorseq1) {
		this.illustauthorseq1 = illustauthorseq1;
	}
	public String getIllustauthorseq2() {
		return illustauthorseq2;
	}
	public void setIllustauthorseq2(String illustauthorseq2) {
		this.illustauthorseq2 = illustauthorseq2;
	}
	public String getIllustauthorseq3() {
		return illustauthorseq3;
	}
	public void setIllustauthorseq3(String illustauthorseq3) {
		this.illustauthorseq3 = illustauthorseq3;
	}
	public String getIllustauthornm1() {
		return illustauthornm1;
	}
	public void setIllustauthornm1(String illustauthornm1) {
		this.illustauthornm1 = illustauthornm1;
	}
	public String getIllustauthornm2() {
		return illustauthornm2;
	}
	public void setIllustauthornm2(String illustauthornm2) {
		this.illustauthornm2 = illustauthornm2;
	}
	public String getIllustauthornm3() {
		return illustauthornm3;
	}
	public void setIllustauthornm3(String illustauthornm3) {
		this.illustauthornm3 = illustauthornm3;
	}
	public String getToonfg() {
		return toonfg;
	}
	public void setToonfg(String toonfg) {
		this.toonfg = toonfg;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getRefkeyword() {
		return refkeyword;
	}
	public void setRefkeyword(String refkeyword) {
		this.refkeyword = refkeyword;
	}
	public String getEndyn() {
		return endyn;
	}
	public void setEndyn(String endyn) {
		this.endyn = endyn;
	}
	public String getAuthorprofileyn1() {
		return authorprofileyn1;
	}
	public void setAuthorprofileyn1(String authorprofileyn1) {
		this.authorprofileyn1 = authorprofileyn1;
	}
	public String getAuthorprofileyn2() {
		return authorprofileyn2;
	}
	public void setAuthorprofileyn2(String authorprofileyn2) {
		this.authorprofileyn2 = authorprofileyn2;
	}
	public String getAuthorprofileyn3() {
		return authorprofileyn3;
	}
	public void setAuthorprofileyn3(String authorprofileyn3) {
		this.authorprofileyn3 = authorprofileyn3;
	}
	public String getIllustprofileyn1() {
		return illustprofileyn1;
	}
	public void setIllustprofileyn1(String illustprofileyn1) {
		this.illustprofileyn1 = illustprofileyn1;
	}
	public String getIllustprofileyn2() {
		return illustprofileyn2;
	}
	public void setIllustprofileyn2(String illustprofileyn2) {
		this.illustprofileyn2 = illustprofileyn2;
	}
	public String getIllustprofileyn3() {
		return illustprofileyn3;
	}
	public void setIllustprofileyn3(String illustprofileyn3) {
		this.illustprofileyn3 = illustprofileyn3;
	}
	public String getPrevtimestitle() {
		return prevtimestitle;
	}
	public void setPrevtimestitle(String prevtimestitle) {
		this.prevtimestitle = prevtimestitle;
	}
	public String getNexttimestitle() {
		return nexttimestitle;
	}
	public void setNexttimestitle(String nexttimestitle) {
		this.nexttimestitle = nexttimestitle;
	}
}