package com.biz.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.sec.service.MailSendService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/mail")
@Controller
public class MailController {
	
	private final MailSendService mService;
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String sendMail() {
		mService.sendMail();
		return "SEND OK";
	}

}
