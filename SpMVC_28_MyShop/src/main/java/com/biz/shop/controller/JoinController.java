package com.biz.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.shop.domain.UserVO;
import com.biz.shop.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SessionAttributes
@RequestMapping(value = "/join")
@Controller
public class JoinController {
	
	private final UserService userService;
	
	@ModelAttribute("userVO")
	public UserVO newUser() {
		return new UserVO();
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String join() {
		return "join/join";
	}
	
	@RequestMapping(value = "/joinok", method = RequestMethod.POST)
	public String joinOk(@ModelAttribute("userVO") UserVO userVO, Model model) {
		int ret = userService.insert(userVO);
		model.addAttribute("JOIN", "JOIN_OK");
		
		return "user/login";
		
	}

}
