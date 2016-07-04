/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : UserServiceIface.java
 * DESCRIPTION    : 사용자 정보 관련 Service interface class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-16      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.user.service.iface;

import java.util.List;
import java.util.Map;

import com.olleh.webtoon.common.dao.user.domain.NameconDomain;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;

public interface UserServiceIface {

	/**
	 * 이메일ID를 전달받아 해당되는 사용자 정보를 조회한다.
	 * 
	 * @param String email : 이메일ID
	 * @return UserDomain  : 사용자정보
	 */
	public UserDomain getUserInfoByEmail(String email);
	
	public UserDomain getUserInfoByCertificationkey(String certificationkey);
	
	public UserDomain getUserUseTermsByEmail(String email);
	
	/**
	 * 회원순번을 전달받아 해당되는 사용자 정보를 조회한다.
	 * 
	 * @param long userseq : 회원순번
	 * @return UserDomain  : 사용자정보
	 */
	public UserDomain getUserInfoBySeq(long userseq);
	
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
	
	public void userRegist(UserDomain userDomain);
	
	public void reCertification(UserDomain userDomain);
	
	public int certification(UserDomain userDomain);
	
	public int adultUpdate(UserDomain userDomain);
	
	public int minorityUpdate(UserDomain userDomain);
	
	public void withdraw(UserDomain userDomain);
	
	public int passwdCheckCount(UserDomain userDomain);
	
	public void newPasswd(UserDomain userDomain);
	
	public List<NameconDomain> defualtNameconList();
	
	public List<NameconDomain> availNameconList(UserDomain userDomain);
	
	public List<NameconDomain> defualtNameconList(UserDomain userDomain);
	
	public void defualtNamecon(UserDomain userDomain);
	
	public OllehUserDomain ollehUserDetail(String ollehid);
	
	public void ollehUserRegist(String ollehid);
	
	public void ollehUserAgreement(OllehUserDomain ollehUser);
	
	public void webtoonUserAgreement(UserDomain userDomain);
	
	/**
	 * 로그인 이력 
	 * @param email, idfg, logindt 
	 * @return loginHistoryCnt
	 */
	public int loginHisRegistProc(Map<String, Object> param);
	
	public void ollehUserUsetermsynUpdateByOllehId(OllehUserDomain ollehUser);
	
	public void pushtermsynUpdate(OllehUserDomain ollehUser);
	
	public void usetermsynUpdate(OllehUserDomain ollehUser);			
	
	public void updateEmailByUuid(UserDomain userDomain);

	public int defualtNameconCnt(UserDomain userDomain);

	public List<NameconDomain> freeNameconList(UserDomain userDomain);

	public List<NameconDomain> payNameconList(UserDomain userDomain);
	
	public List<NameconDomain> bmNameconList(UserDomain userDomain);
	
	public int getFailHisCnt(String email);
	
	public String getFailDtOveryn(String email);
	
	public void initLoginFailInfo(String email);
	
	public void modifyLoginFailInfo(String email);;
	
	public void modifyPasswdChangeDate(String email);
	
	public void updateFromOptionToDefaultNamecon(String email, String idfg);

	public UserDomain getNaverUsertDetail(String string);
}