/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : UserDomain.java
 * DESCRIPTION    : 회원 테이블 정보를 전달하기 위한 도메인 모델 class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-16      init
 *****************************************************************************/

package com.olleh.webtoon.common.dao.user.domain;

import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.annotate.JsonIgnore;
import com.olleh.webtoon.common.util.StringUtil;

public class UserDomain extends OllehUserDomain {
	
	protected static Log logger = LogFactory.getLog(UserDomain.class);
	
	private static final char DELIM = 0x16; //쿠키에 정보들을 붙여서 암호화할때 각 필드 사이의 구분자
	
	private String checkno;          //회원 구분(1: 웹툰회원, 2: 작가 , 3: 약관 미동의 올레회원, 4: 약관 동의 올레회원  )
	private String email;            //이메일 (사용자ID)
	private int startRowNo; 		// 해당페이지 시작 Row번호
	private int pageSize; 			// 페이지크기
	private String userstatus;       //회원상태 (standby:메일인증대기, use:사용, stop:정지, cancel:탈퇴)
	private String passwd;           //비밀번호 (Sha512 암호화)
	private String newpasswd;           //비밀번호 (Sha512 암호화)
	private String certificationkey; //이메일인증키 (인증메일 발송 시 생성한 복잡한 문자열)
	private String lastlogindt;      //최종로그인일시 (YYYYMMDDHHMMSS)
	private int logincnt;            //로그인회수
	private String joindt;           //가입일시 (YYYYMMDDHHMMSS), 이메일 인증 일시임
	private String recertificationkey; //이메일인증키 (인증메일 발송 시 생성한 복잡한 문자열)
	private String gender;             //성별
	private String faildt;			   //로그인 실패 일시 (YYYYMMDDHHMMSS) 
	
	private String minorityyn;        //14세미만인지 여부 (Y/N)
	private String minoritycertnum;   //부모동의를 위해 한국모바일인증 호출시 넘긴 키
	private String minoritycertdt;    //부모동의일시 (YYYYMMDDHHMMSS)
	
	private String ctn;			//대표CTN(01012341234)
	private String ctype;		//고객유형(I:개인, B:법인사업자, O:개인사업자, G:공공기간, E:불명수납고객, X:KT고객아님)
	private String age;		//고객나이(만)
	private String nameconlisttype;   // 네임콘 리스트 표시타입.
	private String pwchangedt; //비밀번호 변경일자
	private String pwchange_monthdiff; //비밀번호 변경 기간
	private String captcha; //캡차코드
	private String loginip;		//로그인시 IP
	
	/**
	 * 생성자 메소드
	 * 비어있는 UserDomain 객체를 생성한다.
	 */
	public UserDomain() { }
	
	/**
	 * 생성자 메소드
	 * 복호화된 쿠키 문자열을 받아 해당 정보를 저장하고 있는 UserDomain 객체를 생성한다.
	 * 
	 * 쿠키 문자열은 0x16를 구분자로 해서 userseq, email, nickname, adultyn 값이 순서대로 들어있다.
	 * 
	 * @param String cookieValue : 복호화된 쿠키 문자열
	 */
	public UserDomain(String cookieValue) {
		
		String[] cookieValues = cookieValue.split(String.valueOf(DELIM));
		
		try {
			setUserseq(Integer.parseInt(cookieValues[0]));
			this.email = cookieValues[1];
			setNickname(URLDecoder.decode(cookieValues[2], "UTF-8"));
			setAdultyn(cookieValues[3]);
			this.userstatus = cookieValues[4];
			setBirthday(cookieValues[5]);
			
			if(cookieValues.length > 6)
				this.minoritycertnum = cookieValues[6];
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
	}
	
	/**
	 * 쿠키에 저장할 필드들을 0x16 구분자로 연결하여 반환하는 메소드
	 * 이 문자열을 AES암호화하여 쿠키에 저장한다.
	 * 
	 * @return String : 쿠키에 저장할 필드들을 0x16 구분자로 연결한 문자열
	 */
	@JsonIgnore
	public String getCookieValue() {
		
		StringBuffer stringBuffer = new StringBuffer();
		
		try {
			stringBuffer.append(getUserseq());
			stringBuffer.append(DELIM);
			stringBuffer.append(email);
			stringBuffer.append(DELIM);
			stringBuffer.append(URLEncoder.encode(StringUtil.changeNullToEmpty(getNickname()), "UTF-8"));
			stringBuffer.append(DELIM);
			stringBuffer.append(getAdultyn());
			stringBuffer.append(DELIM);
			stringBuffer.append(userstatus);
			stringBuffer.append(DELIM);
			stringBuffer.append(getBirthday());
			stringBuffer.append(DELIM);
			stringBuffer.append(getMinoritycertnum());
		} catch(Exception e) {
			logger.error(e);
		}
		
		return stringBuffer.toString();
		
	}	
	
	public String getCheckno() {
		return checkno;
	}

	public void setCheckno(String checkno) {
		this.checkno = checkno;
	}

	@JsonIgnore
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}
	@JsonIgnore
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNewpasswd() {
		return newpasswd;
	}
	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}
	@JsonIgnore
	public String getCertificationkey() {
		return certificationkey;
	}
	public void setCertificationkey(String certificationkey) {
		this.certificationkey = certificationkey;
	}
	public String getLastlogindt() {
		return lastlogindt;
	}
	public void setLastlogindt(String lastlogindt) {
		this.lastlogindt = lastlogindt;
	}
	public int getLogincnt() {
		return logincnt;
	}
	public void setLogincnt(int logincnt) {
		this.logincnt = logincnt;
	}
	public String getJoindt() {
		return joindt;
	}
	public void setJoindt(String joindt) {
		this.joindt = joindt;
	}
	public String getRecertificationkey() {
		return recertificationkey;
	}
	public void setRecertificationkey(String recertificationkey) {
		this.recertificationkey = recertificationkey;
	}

	public String getMinorityyn() {
		return minorityyn;
	}

	public void setMinorityyn(String minorityyn) {
		this.minorityyn = minorityyn;
	}

	public String getMinoritycertnum() {
		return minoritycertnum;
	}

	public void setMinoritycertnum(String minoritycertnum) {
		this.minoritycertnum = minoritycertnum;
	}

	public String getMinoritycertdt() {
		return minoritycertdt;
	}

	public void setMinoritycertdt(String minoritycertdt) {
		this.minoritycertdt = minoritycertdt;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@JsonIgnore
	public String getCtn() {
		return ctn;
	}

	public void setCtn(String ctn) {
		this.ctn = ctn;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getAge() {
		return age;
	}

	public int getAgeToInteger() {
		try {
			return Integer.parseInt(age);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public void setAge(String age) {
		this.age = age;
	}

	public int getStartRowNo() {
		return startRowNo;
	}

	public void setStartRowNo(int startRowNo) {
		this.startRowNo = startRowNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getNameconlisttype() {
		return nameconlisttype;
	}

	public void setNameconlisttype(String nameconlisttype) {
		this.nameconlisttype = nameconlisttype;
	}

	public String getFaildt() {
		return faildt;
	}

	public void setFaildt(String faildt) {
		this.faildt = faildt;
	}
	
	
	public String getPwchangedt() {
		return pwchangedt;
	}

	public void setPwchangedt(String pwchangedt) {
		this.pwchangedt = pwchangedt;
	}

	public String getPwchange_monthdiff() {
		return pwchange_monthdiff;
	}

	public void setPwchange_monthdiff(String pwchange_monthdiff) {
		this.pwchange_monthdiff = pwchange_monthdiff;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}	
	
}