package com.biz.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/file")
@Controller
public class FileController {

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String fileUp(MultipartFile file) {
		return file.getOriginalFilename();
	}
}
