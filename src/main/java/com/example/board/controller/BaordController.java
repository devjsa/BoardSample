package com.example.board.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaordController {
	
	@RequestMapping("/")
	public String hello(Model model) {
		
		model.addAttribute("test", new Date());
		return "/main";
	}

}
