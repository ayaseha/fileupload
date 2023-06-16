package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}
	
	@GetMapping("/signin")
	public String signin() {
		return "user/signin";
	}
}
