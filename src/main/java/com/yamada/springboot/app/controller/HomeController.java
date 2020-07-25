package com.yamada.springboot.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
		
	@GetMapping("/home")
	public String getHome() {
		System.out.println("action");
		return "hello";
	}

}
