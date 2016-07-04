package com.olleh.webtoon.common.dao.photoevent.domain;

public class ImageDeclarationDomain {

	private int declarationseq;		// 신고순번 (PK, 자동증가)
	private int imageseq;			// 이미지순번
	private String idfg;			// ID구분 (open:오픈ID, olleh:올레ID)
	private String regid;			// 신고한 사용자ID
	private String regdt;			// 등록일시 (YYYYMMDDHHMMSS)
	
	private String musers;			//모니터링 유저
	
	public int getDeclarationseq() {
		return declarationseq;
	}
	public void setDeclarationseq(int declarationseq) {
		this.declarationseq = declarationseq;
	}
	public int getImageseq() {
		return imageseq;
	}
	public void setImageseq(int imageseq) {
		this.imageseq = imageseq;
	}
	public String getIdfg() {
		return idfg;
	}
	public void setIdfg(String idfg) {
		this.idfg = idfg;
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
	public String getMusers() {
		return musers;
	}
	public void setMusers(String musers) {
		this.musers = musers;
	}
	
}
