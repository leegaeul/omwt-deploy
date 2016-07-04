/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : AesCipherUtil.java
 * DESCRIPTION    : AES 암호화 관련 Util class.
 *                  AES transformation을 "AES/CBC/PKCS5Padding"
 *                  (CBC 모드와 PKCS5Padding 패딩 기법을 이용한 암호화)로 암호화한다.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-16      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AesCipherUtil {
	
	protected static Log logger = LogFactory.getLog(AesCipherUtil.class);
	
	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding"; //AES transformation
	
	/**
	 * 컨텐츠를 AES 암호화하는 메소드.
	 * CBC 모드와 PKCS5Padding 패딩 기법을 이용한 암호화
	 * 
	 * @param String key           : AES 키
	 * @param String initialVector : AES 초기화벡터
	 * @param byte[] content       : 암호화할 컨텐츠
	 * @return String encryptStr   : Cypher 암호화된 문자열을 Base64로 인코딩한 문자열
	 * @throws Exception
	 */
	public String encrypt(String key, String initialVector, byte[] content) throws Exception {
		
		byte[] cipherText = null; //Cypher 암호화된 문자열
		String encryptStr = null; //Cypher 암호화된 문자열을 Base64로 인코딩한 문자열
		
		if(content==null || StringUtil.isEmptyOrWhitespace(key) || StringUtil.isEmptyOrWhitespace(initialVector)) {
			throw new NullPointerException();
		}
		
		try {
			
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			
			SecretKeySpec secretKeySpec = new SecretKeySpec(hexToBytes(key), "AES");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(hexToBytes(initialVector));
			
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			
			cipherText = cipher.doFinal(content);
			encryptStr = new String(Base64.encodeBase64(cipherText));
			
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return encryptStr;

	}

	/**
	 * 암호화된 문자열을 원본 문자열로 복호화하는 메소드.
	 * CBC 모드와 PKCS5Padding 패딩 기법을 이용한 암호화
	 * 
	 * @param String key           : AES 키
	 * @param String initialVector : AES 초기화벡터
	 * @param byte[] content       : 복호화할 컨텐츠 (Cypher 암호화된 문자열을 Base64로 인코딩한 문자열)
	 * @return String decryptStr   : 복호화된 문자열
	 * @throws Exception
	 */
	public String decrypt(String key, String initialVector, byte[] content) throws Exception {
		
		byte[] plainText = null;
		String decryptStr = null;
		
		if(content==null || StringUtil.isEmptyOrWhitespace(key) || StringUtil.isEmptyOrWhitespace(initialVector)) {
			throw new NullPointerException();
		}
				
		try {
			
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			
			SecretKeySpec secretKeySpec = new SecretKeySpec(hexToBytes(key), "AES");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(hexToBytes(initialVector));
			
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			
			plainText = cipher.doFinal(Base64.decodeBase64(content));
			decryptStr = new String(plainText, "UTF-8");
			
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return decryptStr;
		
	}

	/**
	 * 암호화된 문자열을 원본 문자열로 복호화하는 메소드.
	 * CBC 모드와 PKCS5Padding 패딩 기법을 이용한 암호화
	 * 
	 * @param String key           : AES 키
	 * @param String initialVector : AES 초기화벡터
	 * @param byte[] content       : 복호화할 컨텐츠 (Cypher 암호화된 문자열을 Base64로 인코딩한 문자열)
	 * @return String decryptStr   : 복호화된 문자열
	 * @throws Exception
	 */
	public String decryptNoEncoding(String key, String initialVector, byte[] content) throws Exception {
		
		byte[] plainText = null;
		String decryptStr = null;
		
		if(content==null || StringUtil.isEmptyOrWhitespace(key) || StringUtil.isEmptyOrWhitespace(initialVector)) {
			throw new NullPointerException();
		}
				
		try {
			
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			
			SecretKeySpec secretKeySpec = new SecretKeySpec(hexToBytes(key), "AES");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(hexToBytes(initialVector));
			
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			
			plainText = cipher.doFinal(Base64.decodeBase64(content));
			decryptStr = new String(plainText);
			
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return decryptStr;
		
	}

	/**
	 * 16진수 문자열을 바이트 배열로 변환하는 메소드
	 * 
	 * @param String hex : 16진수 문자열
	 * @return byte[] b : 바이트 배열
	 */
	private byte[] hexToBytes(String hex) {
		
		byte[] b = null;
		int len = hex.length();
		
		try {
						
			if(len%2 == 1) {
				return null;
			}

			b = new byte[len / 2];
			
			for(int i = 0; i < len; i += 2) {
				b[i >> 1] = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
			}
			
		} catch (Exception e) {
			logger.error(e);
		}
		
		return b;
		
	}
		
	/*public static void main(String[] args) throws Exception	{
		
		try {
			
			AesCipherUtil aesCipherUtil = new AesCipherUtil();
			
			String encode = URLEncoder.encode(aesCipherUtil.encrypt("2c1b213af63e5a7d2a01d3599eba2c4b", "1a10c43d9a4506a12d6316a25d2b3c54", "01012345678^20130211".getBytes()), "UTF-8");
			System.out.println(encode);
			
			String plain = aesCipherUtil.decrypt("2c1b213af63e5a7d2a01d3599eba2c4b", "1a10c43d9a4506a12d6316a25d2b3c54", URLDecoder.decode(encode, "UTF-8").getBytes());
			System.out.println(plain);
			
		} catch (Exception e) {
			logger.error(e);
		}
	}*/
	
}