package com.biz.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.score.domain.StudentVO;
import com.biz.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/student")
@Controller
public class StudentController {
	
	private final StudentService studentService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<StudentVO> studentList = studentService.selectAll();
		model.addAttribute("STUDENT_LIST", studentList);
		return "student/st_list";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		return "student/st_input";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(StudentVO studentVO) {
		studentService.save(studentVO);
		return "redirect:/student/list";
	}
	
	@RequestMapping(value="/numcheck",method = RequestMethod.GET)
	public String numCheck(String st_num) {
		
		boolean ret = studentService.isExistsNum(st_num);
		if(ret) {
			return "Exists".toUpperCase(); // EXISTS
		}
		return "NonExists".toUpperCase(); // NONEXISTS
	}
	
	@RequestMapping(value = "/view/{st_num}", method = RequestMethod.GET)
	public String view(@PathVariable("st_num") String st_num, Model model) {
		StudentVO studentVO = studentService.findByNum(st_num);
		model.addAttribute("STUDNET", studentVO);
		return "student/st_view";
	}
	
	
	@RequestMapping(value = "/update/{st_num}", method = RequestMethod.GET)
	public String update(StudentVO studentVO, @PathVariable("st_num") String st_num, Model model) {
		studentVO = studentService.findByNum(st_num);
		model.addAttribute("STUDENT", studentVO);
		return "student/st_input";
	}
	
	@RequestMapping(value = "/update/{st_num}", method = RequestMethod.POST)
	public String update(StudentVO studentVO) {
		studentService.save(studentVO);
		return "redirect:/student/list";
	}
	
	@RequestMapping(value = "/delete/{st_num}", method = RequestMethod.GET)
	public String delete(@PathVariable("st_num") String st_num) {
		int ret = studentService.delete(st_num);
		return "redirect:/student/list";
	}

	

}
