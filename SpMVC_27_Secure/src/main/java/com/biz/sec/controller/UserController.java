package com.biz.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/user")
@Controller
public class UserController {

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String user() {
		return "user HOME";
	}
	
	@ResponseBody
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage() {
		return "user MyPage";
	}
	
}
