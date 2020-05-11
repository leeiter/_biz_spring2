package com.biz.shop.controller;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.shop.domain.UserVO;
import com.biz.shop.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/user")
@Controller
public class UserController {
	
	private final UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/idcheck", method = RequestMethod.GET)
	public String idCheck(String username) {
		boolean ret = userService.isExistsUserName(username);
		if(ret) {
			return "Exists".toUpperCase(); // EXISTS
		}
		return "NonExists".toUpperCase(); // NONEXISTS
	}
	
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public String password(String password) {
		boolean ret = userService.check_password(password);
		if(ret) return "PASS_OK";
		return "PASS_FAIL";
	}
	
	
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(Principal principal, Model model) {
		UsernamePasswordAuthenticationToken upa = (UsernamePasswordAuthenticationToken) principal;
		
		UserVO userVO = (UserVO) upa.getPrincipal();
		userVO.setAuthorities(upa.getAuthorities());
		
		model.addAttribute("userVO", userVO);
		return "user/mypage";
		
	}
	
	@RequestMapping(value = "/mypage", method = RequestMethod.POST)
	public String mypage(UserVO userVO, Principal principal, Model model) {
		int ret = userService.update(userVO);
		return "redirect:/user/mypage";		
	}
	
}
