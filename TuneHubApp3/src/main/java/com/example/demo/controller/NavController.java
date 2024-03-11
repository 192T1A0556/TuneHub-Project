package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController 
{
	@GetMapping("/map-register")
	public String registerMapping() {
		return "register";//goes to register.html page
	}
	
	@GetMapping("/map-login")
	public String loginMapping() {
		return "login";//goes to login.html page
	}
	
	@GetMapping("/map-songs")
	public String songsMapping() {
		return "addsongs";
	}
	
	@GetMapping("/samplePayment")
	public String samplePayment() {
		return "samplePayment";
	}
}
