package com.example.board.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.example.board.basic.Constants;
import com.example.board.basic.WebUtil;
import com.example.board.vo.User;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		log.info(model.containsAttribute("test")+"");
		return "login";
	}
	
	@SuppressWarnings("unused")
	private void checkSession (HttpServletRequest request, HttpServletResponse response) 
			throws ModelAndViewDefiningException {
		User user  = (User) WebUtils.getSessionAttribute(request, Constants.USER_SESSION_KEY);
		if(user != null) {
			if (Constants.LOGIN_SESSION_STATUS_LOGIN.equals(user.getSessionState())) {	
				throw new ModelAndViewDefiningException(new ModelAndView("redirect:/"));
			} else {	
				HttpSession session = request.getSession();
				WebUtil.setCookie(response, "AUTH-TOKEN", null);
				session.removeAttribute(Constants.USER_SESSION_KEY);
				session.invalidate(); 
			}
		}
	}
	
	
}
