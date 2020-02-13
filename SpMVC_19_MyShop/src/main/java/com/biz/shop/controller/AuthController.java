package com.biz.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.shop.domain.CustomUserDetails;
import com.biz.shop.domain.Users;
import com.biz.shop.repository.UserDao;
import com.biz.shop.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Controller
public class AuthController {
	
	private final AuthService aService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/auth/test", method = RequestMethod.GET)
	public String test() {
		return "auth/test";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@ResponseBody
	@RequestMapping(value = "/login_ok", method = RequestMethod.GET)
	public CustomUserDetails login_ok() {
		CustomUserDetails cUserDetails = (CustomUserDetails) SecurityContextHolder.getContext()
				.getAuthentication()
				.getDetails();
		
		return cUserDetails;
	}
	
	
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Users userVO) {
		aService.userSave(userVO);
		return "redirect:/";
	}
}
