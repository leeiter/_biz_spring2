package com.biz.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		return "admin/main";
		
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String admin(Model model) {
		model.addAttribute("BODY", "PRODUCT");
		return "admin/main";
	}
	
}
