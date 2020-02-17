package com.biz.shop.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.shop.domain.DeptVO;
import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.DeptService;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@SessionAttributes("deptVO")
@RequiredArgsConstructor
@RequestMapping(value = "/admin/dept")
@Controller
public class DeptController {
	
	private final DeptService dService;

	@ModelAttribute("deptVO")
	public DeptVO newDept() {
		return new DeptVO();
	}

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String product(@ModelAttribute("deptVO") DeptVO deptVO, Model model) {
		
		deptVO = new DeptVO();
		List<DeptVO> deptList = dService.selectAll();

		model.addAttribute("DEPT_LIST", deptList);
		model.addAttribute("deptVO", deptVO);		

		model.addAttribute("BODY", "DEPT");
		return "admin/main";
	}
	
	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public String dept(@Valid @ModelAttribute("deptVO") DeptVO deptVO, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("BODY", "DEPT");
			return "admin/main";
		}
		
	
		dService.save(deptVO);
		status.setComplete();
		return "redirect:/admin/dept/";

	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@ModelAttribute("deptVO") DeptVO deptVO, @PathVariable("id") String strId, Model model) {
		List<DeptVO> deptList = dService.selectAll();

		model.addAttribute("DEPT_LIST", deptList);
		
		long id = Long.valueOf(strId);
		
		deptVO = dService.findById(id);
		
		model.addAttribute("deptVO", deptVO);
		model.addAttribute("BODY", "DEPT");
		return "admin/main";
		
	}
	
	
	
	/*
	 * 	
	}



	
	

	 */
	
}
