package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/fancyLoginPage")
	public String fancyLoginPage() {		
		return "fancy-login";		
	}
	
	@GetMapping("/fancyCSRFLoginPage")
	public String fancyCSRFLoginPage() {		
		return "fancy-login-csrf";		
	}
	
	@GetMapping("/plainLoginPage")
	public String plainLoginPage() {		
		return "plain-login";
	}
}
