package com.biz.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {
	
	@RequestMapping(value = "")
	public String admin() {
		return "admin";
	}

}
