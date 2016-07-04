/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : PagingUtil.java
 * DESCRIPTION    : 페이징 처리를 위한 Util class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0               Byun        2013-06-20      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;


public class PagingUtil {
	/**
	 * 페이지당 글수(Default로 10개씩)
	 */
	private int PAGESIZE = 10;          
	
	/**
	 * 총 페이지 수를 반환하는 메소드 
	 */
	public int getTotalPageSize(int totalCount) {
		// TODO Auto-generated constructor stub
		return totalCount % PAGESIZE == 0 ? totalCount / PAGESIZE : totalCount / PAGESIZE + 1; 
	}
	
	/**
	 * 페이지 수 반환하는 메소드 
	 * @return
	 */
	public int getPAGESIZE() {
		return PAGESIZE;
	}
	
	/**
	 * 페이지 수 설정하는 메소드(작품리스트는 10개씩, 댓글은 5개씩 보여주는 갯수가 다름)
	 * @param PAGESIZE
	 */
	public void setPAGESIZE(int PAGESIZE) {
		this.PAGESIZE = PAGESIZE;
	}

	/**
	 * 시작하는 Row를 반환하는 메소드
	 * @param startRowNo
	 * @return
	 */
	public  int getStartNo(int startRowNo) {
		return (startRowNo - 1)  * PAGESIZE;
	}
	
	/**
	 * 마지막 Row를 반환하는 메소드
	 * @param startRowNo
	 * @param totalCount
	 * @return
	 */
	public  int getEndNo(int startRowNo, int totalCount){
		int lastRowNo = 0;
		
		if(totalCount < (startRowNo  * PAGESIZE)){
			lastRowNo = totalCount;
		}else{
			lastRowNo = startRowNo  * PAGESIZE;
		}
		
		return lastRowNo;
	}
}