package com.biz.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.score.domain.ScoreVO;
import com.biz.score.service.ScoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/score")
@Controller
public class ScoreController {
	
	private final ScoreService scoreService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<ScoreVO> socreList = scoreService.selectAll();
		model.addAttribute("SCORE_LIST", socreList);
		return "score/s_list";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		return "score/s_input";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(ScoreVO scoreVO) {
		scoreService.save(scoreVO);
		return "redirect:/score/list";
	}
	
	@RequestMapping(value = "/view/{s_subject}", method = RequestMethod.GET)
	public String view(@PathVariable("s_subject") String s_subject, Model model) {
		ScoreVO scoreVO = scoreService.findByNum(s_subject);
		model.addAttribute("SCORE", scoreVO);
		return "score/s_view";
	}
	
	/*
	 * 테이블 선택시 num 여러개가 선택이 되다보니
	 * num 1개의 값만 가져오기가 힘들다.
	 * 
	 */
	@RequestMapping(value = "/update/{s_num}", method = RequestMethod.GET)
	public String update(ScoreVO scoreVO, @PathVariable("s_num") String s_num, Model model) {
		scoreVO = scoreService.findByNum(s_num);
		model.addAttribute("SCORE", scoreVO);
		return "score/s_input";
	}
	
	@RequestMapping(value = "/update/{s_num}", method = RequestMethod.POST)
	public String update(ScoreVO scoreVO) {
		scoreService.save(scoreVO);
		return "redirect:/score/list";
	}
	
	@RequestMapping(value = "/delete/{s_num}", method = RequestMethod.GET)
	public String delete(@PathVariable("s_num") String s_num) {
		int ret = scoreService.delete(s_num);
		return "redirect:/score/list";
	}
	
	
	
	/*

	numsubcheck


	
	@RequestMapping(value="/numcheck",method = RequestMethod.GET)
	public String numCheck(String st_num) {
		
		boolean ret = studentService.isExistsNum(st_num);
		if(ret) {
			return "Exists".toUpperCase(); // EXISTS
		}
		return "NonExists".toUpperCase(); // NONEXISTS
	}
	


	*/

}
