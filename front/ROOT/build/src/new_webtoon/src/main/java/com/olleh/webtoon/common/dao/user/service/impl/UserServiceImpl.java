/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : UserServiceImpl.java
 * DESCRIPTION    : 사용자 정보 관련 Service 구현 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-16      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.user.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.olleh.webtoon.common.code.ApiResultCode;
import com.olleh.webtoon.common.dao.api.domain.ApiResultDomain;
import com.olleh.webtoon.common.dao.user.domain.NameconDomain;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.persistence.UserMapper;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.exception.ApiException;

@Service("userService")
@Repository
public class UserServiceImpl implements UserServiceIface {
	
	protected static Log logger = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	
	/**
	 * 이메일ID를 전달받아 해당되는 사용자 정보를 조회한다.
	 * 
	 * @param String email : 이메일ID
	 * @return UserDomain  : 사용자정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public UserDomain getUserInfoByEmail(String email) {
		
		UserDomain userDomain = userMapper.selectUserInfoByEmail(email);
		
		return userDomain;
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public UserDomain getUserInfoByCertificationkey(String certificationkey) {
		
		UserDomain userDomain = userMapper.selectUserInfoByCertificationkey(certificationkey);
		
		return userDomain;
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public UserDomain getUserUseTermsByEmail(String email)
	{
		return userMapper.selectUserUseTermsByEmail(email);
	}
	
	/**
	 * 회원순번을 전달받아 해당되는 사용자 정보를 조회한다.
	 * 
	 * @param long userseq : 회원순번
	 * @return UserDomain  : 사용자정보
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public UserDomain getUserInfoBySeq(long userseq) {
		
		UserDomain userDomain = userMapper.selectUserInfoBySeq(userseq);
		
		return userDomain;
		
	}
	
	/**
	 * 최종 로그인 시간을 업데이트한다.
	 * 
	 * @param UserDomain userDomain : 업데이트할 사용자 정보
	 * @return void
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void updateLastlogindt(UserDomain userDomain) {
		
		userMapper.updateLastlogindt(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int userCheckCount(UserDomain userDomain) {
		
		return userMapper.userCheckCount(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int cancelUserCheckCount(UserDomain userDomain) {
		
		return userMapper.cancelUserCheckCount(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void passwdUpdate(UserDomain userDomain) {
		
		userMapper.passwdUpdate(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void userRegist(UserDomain userDomain) {
		
		userMapper.userInsert(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void reCertification(UserDomain userDomain) {
		
		userMapper.reCertificationUpdate(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public int certification(UserDomain userDomain) {
		
		return userMapper.certificationUpdate(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void withdraw(UserDomain userDomain) {
		
		userMapper.withdrawUpdate(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int passwdCheckCount(UserDomain userDomain) {
		
		return userMapper.passwdCheckCount(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void newPasswd(UserDomain userDomain) {
		
		userMapper.newPasswdUpdate(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public List<NameconDomain> defualtNameconList() {
		
		return userMapper.defualtNameconSelectList();
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public List<NameconDomain> availNameconList(UserDomain userDomain) {
		
		return userMapper.availNameconSelectList(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public List<NameconDomain> defualtNameconList(UserDomain userDomain) {
		
		return userMapper.defualtNameconSelectList(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void defualtNamecon(UserDomain userDomain) {
		
		userMapper.defualtNameconUpdate(userDomain);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public OllehUserDomain ollehUserDetail(String ollehid) {
		
		return userMapper.ollehUserSelectDetail(ollehid);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void ollehUserRegist(String ollehid) {
		
		userMapper.ollehUserInsert(ollehid);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void ollehUserAgreement(OllehUserDomain ollehUser) {
		
		userMapper.ollehUserAgreementUpdate(ollehUser);
		
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void webtoonUserAgreement(UserDomain userDomain) {
		
		userMapper.webtoonUserAgreementUpdate(userDomain);
		
	}

	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public int adultUpdate(UserDomain userDomain) {
		return userMapper.adultUpdate(userDomain);
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public int minorityUpdate(UserDomain userDomain) {
		return userMapper.minorityUpdate(userDomain);
	}
	
	/**
	 * 로그인 이력 
	 * @param email, idfg, logindt 
	 * @return loginHistoryCnt 로그인 이력 횟수 조회
	 */
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public int loginHisRegistProc(Map<String, Object> param){
		userMapper.loginHisInsert(param);
		
		 return userMapper.selectHistoryCnt(param);
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void ollehUserUsetermsynUpdateByOllehId(OllehUserDomain ollehUser)
	{
			userMapper.ollehUserUsetermsynUpdateByOllehId( ollehUser );
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void usetermsynUpdate(OllehUserDomain ollehUser)
	{
		if( !StringUtils.hasText( ollehUser.getUsetermsyn() ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "usetermsyn 가 전달되지 않았습니다" ) );
		
		ollehUser.setUsetermsyn( ollehUser.getUsetermsyn().toUpperCase() ); 
		
		if( !ollehUser.getUsetermsyn().equals( "Y" ) && !ollehUser.getUsetermsyn().equals( "N" ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "usetermsyn 은 Y이거 N이어야 합니다. usetermsyn=" + ollehUser.getUsetermsyn() ) );
		
		if( ollehUser instanceof UserDomain )
			userMapper.usetermsynUpdateByEmail( (UserDomain)ollehUser );
		else
			userMapper.ollehUserUsetermsynUpdateByOllehId( ollehUser );
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void pushtermsynUpdate(OllehUserDomain ollehUser)
	{
		if( !StringUtils.hasText( ollehUser.getUsetermsyn() ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "usetermsyn 가 전달되지 않았습니다" ) );
		
		ollehUser.setUsetermsyn( ollehUser.getUsetermsyn().toUpperCase() ); 
		
		if( !ollehUser.getUsetermsyn().equals( "Y" ) && !ollehUser.getUsetermsyn().equals( "N" ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "usetermsyn 은 Y이거 N이어야 합니다. usetermsyn=" + ollehUser.getUsetermsyn() ) );
		
		if( ollehUser instanceof UserDomain )
			userMapper.pushUsetermsynUpdateByEmail( (UserDomain)ollehUser );
		else
			userMapper.ollehUserPushUsetermsynUpdateByOllehId( ollehUser );
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void updateEmailByUuid(UserDomain userDomain) {
		
		userMapper.updateEmailByUuid(userDomain);
		
	}

	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int defualtNameconCnt(UserDomain userDomain) {
		
		return userMapper.defualtNameconSelectCnt(userDomain);
		
	}

	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public List<NameconDomain> freeNameconList(UserDomain userDomain) {
		
		return userMapper.freeNameconSelectList(userDomain);
	}

	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public List<NameconDomain> payNameconList(UserDomain userDomain) {
		
		return userMapper.payNameconSelectList(userDomain);
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public List<NameconDomain> bmNameconList(UserDomain userDomain) {
		
		return userMapper.bmNameconSelectList(userDomain);
	}

	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public int getFailHisCnt(String email){
		return userMapper.selectFailHisCnt(email);
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public String getFailDtOveryn(String email){
		return userMapper.selectFailDtOveryn(email);
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void initLoginFailInfo(String email){
		userMapper.initLoginFailInfo(email);
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void modifyLoginFailInfo(String email){
		
		userMapper.updateLoginFailInfo(email);
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void modifyPasswdChangeDate(String email){
		
		userMapper.updatePasswdChangeDate(email);
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=false)
	public void updateFromOptionToDefaultNamecon(String email, String idfg){
		
		UserDomain userDomain = new UserDomain();
		userDomain.setEmail(email);
		userDomain.setIdfg(idfg);
		userDomain.setNameconseq(5);
		userDomain.setStartRowNo(0);
		userDomain.setPageSize(9999999);
		
		//지급받은 블루멤버십 전용 네임콘 리스트
		List<NameconDomain> bmNameconList = userMapper.bmNameconSelectList(userDomain);
		if("open".equals(userDomain.getIdfg())){
			int useNameconseq = userMapper.selectOpenUserNameconseq(userDomain);
			if(bmNameconList != null && bmNameconList.size() > 0){
				for(int i = 0; i < bmNameconList.size(); i++){
					if(useNameconseq == bmNameconList.get(i).getNameconseq()){
						
						//default 네임콘으로 변경
						userMapper.updateOpenUserNameconseq(userDomain);
						break;
					}
				}
			}
		}else{
			int useNameconseq = userMapper.selectOllehUserNameconseq(userDomain);
			if(bmNameconList != null && bmNameconList.size() > 0){
				for(int i = 0; i < bmNameconList.size(); i++){
					if(useNameconseq == bmNameconList.get(i).getNameconseq()){
						
						//default 네임콘으로 변경
						userMapper.updateOllehUserNameconseq(userDomain);
						break;
					}
				}
			}
		}
	}
	
	@Transactional(value="masterdbTransactionManager", readOnly=true)
	public UserDomain getNaverUsertDetail(String ollehid) {
		
		return userMapper.selectNaverUserDetail(ollehid);
		
	}
}