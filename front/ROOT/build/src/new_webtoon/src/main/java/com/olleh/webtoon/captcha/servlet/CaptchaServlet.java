package com.olleh.webtoon.captcha.servlet;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.FlatColorBackgroundProducer;
import nl.captcha.noise.StraightLineNoiseProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

import com.olleh.webtoon.captcha.text.producer.WebtoonTextProducer;
import com.olleh.webtoon.captcha.util.CaptchaUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.EncryptUtil;
import com.olleh.webtoon.common.util.MessageUtil;
 
@SuppressWarnings("serial")
public class CaptchaServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	
 
        try {
        	
        	int width = 170;
        	int height = 50;

        	//이미지 사이즈 지정
        	if(request.getParameter("width") != null && !"".equals(request.getParameter("width"))){
        		try{
        			width = Integer.parseInt(request.getParameter("width"));
        		}catch(Exception e){}
        	}

        	if(request.getParameter("height") != null && !"".equals(request.getParameter("height"))){
        		try{
        			height = Integer.parseInt(request.getParameter("height"));
        		}catch(Exception e){
        		}
        	}
        	
			// 폰트 색깔 및 스타일 을 지정
        	Color color = new Color(241,75,89); 	
        	List<java.awt.Font> fonts = Arrays.asList(
        			new Font("Arial", Font.BOLD, 40),
        			new Font("Courier", Font.BOLD, 40),
        			new Font("Arial", Font.BOLD + Font.ITALIC, 40),
        			new Font("Courier", Font.BOLD + Font.ITALIC, 40));
        		
            // 기본 200 * 50 에해당하는 이미지 사이즈를 지정하고, 자동가입방지 문자 길이를 설정한다.
            Captcha captcha = new Captcha.Builder(width, height)
                                    .addText(new WebtoonTextProducer(), new DefaultWordRenderer(Arrays.asList(color), fonts))
                                    .addNoise(new StraightLineNoiseProducer(color, 4))
                                    .build();
            
            // 캡차코드 암호화
            EncryptUtil encryptUtil = new EncryptUtil();
            String encryptCaptcha = encryptUtil.encryptSHA512(captcha.getAnswer());
            
            // 쿠키에 자동가입방지 문자를 저장한다.            
            String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
			String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
			String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인			
			int intCookieAge = Integer.parseInt(cookieAge);			
            CookieUtil.setCookie(response, "u_cc_as", encryptCaptcha, intCookieAge, cookiePath, cookieDomain);
 
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            // 캐쉬를 지우기 위해 헤더값을 설정
            response.setDateHeader("Expires", 0);
            // 리턴값을 image형태로 설정
            response.setContentType("image/jpeg");
            // Image를 write 한다
            CaptchaUtil.writeImage(response, captcha.getImage());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
 
    }
 
}