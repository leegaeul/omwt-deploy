/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : UserMapper.java
 * DESCRIPTION    : 사용자 테이블 DB 연동 관련 Mapper class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-16      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.user.persistence;

import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.user.domain.NameconDomain;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;

public interface UserMapper {
	
	/**
	 * 이메일ID를 전달받아 해당되는 사용자 정보를 조회한다.
	 * 
	 * @param String email : 이메일ID
	 * @return UserDomain  : 사용자정보
	 */
	public UserDomain selectUserInfoByEmail(String email);
	
	public UserDomain selectUserInfoByCertificationkey(String certificationkey);
	
	public UserDomain selectUserUseTermsByEmail(String email);
	
	/**
	 * 회원순번을 전달받아 해당되는 사용자 정보를 조회한다.
	 * 
	 * @param long userseq : 회원순번
	 * @return UserDomain  : 사용자정보
	 */
	public UserDomain selectUserInfoBySeq(long userseq);
	
	/**
	 * 최종 로그인 시간을 업데이트한다.
	 * 
	 * @param UserDomain userDomain : 업데이트할 사용자 정보
	 * @return void
	 */
	public void updateLastlogindt(UserDomain userDomain);
	
	public int userCheckCount(UserDomain userDomain);
	
	public int cancelUserCheckCount(UserDomain userDomain);
	
	public void passwdUpdate(UserDomain userDomain);
	
	public void userInsert(UserDomain userDomain);
	
	public void reCertificationUpdate(UserDomain userDomain);
	
	public int certificationUpdate(UserDomain userDomain);
	
	public int adultUpdate(UserDomain userDomain);
	
	public int minorityUpdate(UserDomain userDomain);
	
	public void withdrawUpdate(UserDomain userDomain);
	
	public int passwdCheckCount(UserDomain userDomain);
	
	public void newPasswdUpdate(UserDomain userDomain);
	
	public List<NameconDomain> defualtNameconSelectList();
	
	public List<NameconDomain> availNameconSelectList(UserDomain userDomain);
	
	public List<NameconDomain> defualtNameconSelectList(UserDomain userDomain);
	
	public void defualtNameconUpdate(UserDomain userDomain);
	
	public OllehUserDomain ollehUserSelectDetail(String ollehid);
	
	public void ollehUserInsert(String ollehid);
	
	public void ollehUserAgreementUpdate(OllehUserDomain ollehUser);
	
	public void webtoonUserAgreementUpdate(UserDomain userDomain);
	
	/**
	 * 로그인 이력
	 * @param email, idfg, logindt 
	 * @return
	 */
	public void loginHisInsert(Map<String, Object> param);
	
	/**
	 * 로그인 이력 횟수 조회  
	 * @param email, idfg 
	 * @return int 로그인 횟수
	 */
	public int selectHistoryCnt(Map<String, Object> param);
	
	public void ollehUserUsetermsynUpdateByOllehId(OllehUserDomain ollehUser);
	
	public void usetermsynUpdateByEmail(OllehUserDomain ollehUser);
	
	public void ollehUserPushUsetermsynUpdateByOllehId(OllehUserDomain ollehUser);
	
	public void pushUsetermsynUpdateByEmail(OllehUserDomain ollehUser);
	
	public void updateEmailByUuid(UserDomain userDomain);

	public int defualtNameconSelectCnt(UserDomain userDomain);

	public List<NameconDomain> freeNameconSelectList(UserDomain userDomain);

	public List<NameconDomain> payNameconSelectList(UserDomain userDomain);
	
	public List<NameconDomain> bmNameconSelectList(UserDomain userDomain);
	
	public int selectFailHisCnt(String email);
	
	public String selectFailDtOveryn(String email);
	
	public void initLoginFailInfo(String email);
	
	public void updateLoginFailInfo(String email);
	
	public void updatePasswdChangeDate(String email);
	
	public int selectOpenUserNameconseq(UserDomain userDomain);
	
	public int selectOllehUserNameconseq(UserDomain userDomain);
	
	public void updateOpenUserNameconseq(UserDomain userDomain);
	
	public void updateOllehUserNameconseq(UserDomain userDomain);	

	public UserDomain selectNaverUserDetail(String email);

}