package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/user/product")
@Controller
public class B2C_Controller {
	
	private final ProductService pService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ProductVO productVO, Model model) {
		
		
		productVO = new ProductVO();
		
		this.modelMapping(model);
		model.addAttribute("productVO", productVO);
		
		return "admin/main";
	}
		
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(ProductVO productVO, @PathVariable("id") String strId, Model model) {
		this.modelMapping(model);
		
		long id = Long.valueOf(strId);
		productVO = pService.findById(id);
		
		model.addAttribute("productVO", productVO);
		model.addAttribute("BODY", "B2C_DETAIL");
		return "admin/main";
		
	}
	
	private void modelMapping(Model model) {
		List<ProductVO> b2cList = pService.selectAll();
		model.addAttribute("B2C_LIST", b2cList);
		// model.addAttribute("BODY", "B2C");
	}
	
	/*
	 * 
	 * @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String strId, Model model) {
		this.modelMapping(model);
		
		long id = Long.valueOf(strId);
		productVO = proService.findById(id);
		
		model.addAttribute("productVO", productVO);
		return "admin/main";
	
	
		ReadBookVO rbVO = rbService.findBySeq(rb_seq);
		model.addAttribute("RBOOK", rbVO);
	
		String b_code = rbVO.getRb_bcode();
		BooksVO booksVO = bService.findByBCode(b_code);
		model.addAttribute("BOOK", booksVO);
	
		List<ReadBookVO> rbList = rbService.findByBCode(b_code);
		model.addAttribute("RBLIST", rbList);

		model.addAttribute("BODY", "RB_VIEW");
		return "rbook/list";
	}
	
		@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@ModelAttribute("productVO") ProductVO productVO, @PathVariable("id") String strId, Model model) {
		this.modelMapping(model);
		
		long id = Long.valueOf(strId);
		productVO = proService.findById(id);
		
		model.addAttribute("productVO", productVO);
		return "admin/main";
	}
	
	 * 
	 * 	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String product(@ModelAttribute("productVO") ProductVO productVO, Model model) {
		this.modelMapping(model);
		
		productVO = new ProductVO();
		model.addAttribute("productVO", productVO);
		return "admin/main";
	}
	 */
	
}
