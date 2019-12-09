package com.example.board.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.vo.kakao.KakaoToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/kakao")
@Controller
public class KakaoController {

	@Value("${kakao.token.url}")
	public String tokenUrl;
	
	@Value("${kakao.user.info.url}")
	public String userInfoUrl;
	
	@Value("${kakao.rest.key}")
	public String restKey;
	
	@Value("${server.host}")
	public String serverHost;
	
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request, HttpServletResponse response, Model model,
		@RequestParam(name = "code", required = false) String code,
		@RequestParam(name = "error", required = false) String error) {
	
		try {
			
			if( code != null ) {
				URL url = new URL(tokenUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				String urlParameters = "grant_type=authorization_code"
						+ "&client_id="+restKey
						+ "&redirect_uri="+serverHost+"/kakao/test"
						+ "&code="+code;

		        conn.setRequestMethod(HttpMethod.POST.name());
		        // conn.setRequestProperty("Authorization", "Bearer " + code);
		        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		        conn.setDoOutput(true);              //항상 갱신된내용을 가져옴.
		        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		        wr.writeBytes(urlParameters);
		        wr.flush();
		        wr.close();

		        int responseCode = conn.getResponseCode();
		        log.info("getToken responseCode : " + responseCode);
		        
		        if( responseCode == 200 ) {
		        	ObjectMapper objectMapper = new ObjectMapper();
		        	KakaoToken tokenVO = objectMapper.readValue( getResponseString(conn), KakaoToken.class);
		        	
		        	String accessToken = tokenVO.getAccess_token();
					if( accessToken != null ) {
		        		log.info(accessToken);
		        	}
		        	
					model.addAttribute("code", responseCode);
			        model.addAttribute("result", getUserInfo(accessToken));
		        }
		        
			} else {
				model.addAttribute("error", error);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "test";
		
		
	}
	
	public String getUserInfo(String token) throws Exception {
		
		
		URL url = new URL(userInfoUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(HttpMethod.POST.name());
        conn.setRequestProperty("Authorization", "Bearer " + token);
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        int responseCode = conn.getResponseCode();
        log.info("getUserInfo responseCode : " + responseCode);
        
        if( responseCode == 200 ) {
        	return getResponseString(conn);
        }
		
		return null;
	}

	private String getResponseString(HttpURLConnection conn) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String line = "";
		String result = "";
		
		while ((line = br.readLine()) != null) {
		    result += line;
		}
		return result;
	}
	
	@RequestMapping("/callback")
	public String callback() {
		
		return "kakao/callback";
	}
	
}
