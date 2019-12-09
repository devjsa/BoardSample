package com.example.board.basic;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.board.vo.User;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebUtil {
	
	private WebUtil() {
	}
	
	public static void setCookie(HttpServletResponse response, String name, String value) {

		if(StringUtils.isEmpty(value)) {
			value = "";
		}
		Cookie cookie = new Cookie( name, value );
		cookie.setPath( "/" );
		if(StringUtils.isEmpty(value)) {
			cookie.setMaxAge( 0 ); 
		}
		
		response.addCookie( cookie );
	}
	
	public static void setCookie(HttpServletResponse response, String name, String value, int age) {
		if(StringUtils.isEmpty(value)) {
			value = "";
		}
		Cookie cookie = new Cookie( name, value );
		cookie.setPath( "/" );
		cookie.setMaxAge( age );
		response.addCookie( cookie );
	}
	
	public static boolean setCookie( HttpServletRequest request, 
			HttpServletResponse response, 
			String name,
			String value,
			String path,
			int age,		// in seconds
			String domain )
	{
		if(StringUtils.isEmpty(name)) {
			return false;
		}
		if(!StringUtils.isWhitespace(name)) {
			return false;
		}

		if(domain == null) {
			domain = request.getServerName();
		}
		if(path == null) {
			path = "/";
		}

		if(value == null || !StringUtils.isWhitespace(value)) {
			value = "";
		}

		Cookie cookie = new Cookie(name, value); 
		cookie.setDomain( domain ); 
		cookie.setPath(path);

		if(StringUtils.isEmpty(value)) {
			cookie.setMaxAge( 0 ); 
		} else if(age >= -1) {
			cookie.setMaxAge( age );
		}

		response.addCookie(cookie); //쿠키 생성

		return true;
	}
	
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies == null) {
			return null;
		}

		for( int i = 0; i < cookies.length; i++ ){ 
			Cookie cookie = cookies[i];

			if(cookie.getName().equals(name)){
				return cookie;
			}
		}
		return null;
	}
	
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		return (cookie != null) ? cookie.getValue() : null; 
	}
	
	public static String getCookieDomain(String serverName) {
		try {
			java.util.StringTokenizer st = new java.util.StringTokenizer(serverName, ".");
			int tok = st.countTokens();
			if(tok < 3) {
				return serverName;
			}
			
			for(int i = 0; i < tok-2; i++) {
				st.nextElement();
			}
			StringBuilder buf = new StringBuilder();
			while(st.hasMoreTokens()) {
				buf.append(st.nextToken()).append(".");
			}
			String domain = buf.toString();
			if(domain.endsWith(".")) {
				domain = domain.substring(0, domain.length()-1);
			}
			return domain;
		} catch(Exception e) {
			log.error("getCookieDomain error; errMsg: "+ e.getMessage(), e);
			return null;
		}
	}

	public static String getHomeUrl(HttpServletRequest request) {
		String url = "http://"+request.getServerName();
		if(request.getServerPort() == 443) {
			url = "https://"+request.getServerName();
		} else if(request.getServerPort() != 80) {
			url = "http://"+request.getServerName()+":"+request.getServerPort();
		}
		return url;
	}
	
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest hsr = sra.getRequest();
		return hsr;
	}
	
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	public static String getAccountLevel(){
		User user = (User) getSession().getAttribute(Constants.USER_SESSION_KEY);
		return user == null ? Constants.ROLE_GUEST : user.getAuthority();
	}
}
