package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.shop.domain.CartListVO;
import com.biz.shop.domain.CartVO;
import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.CartService;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/user/product")
@Controller
public class B2C_Controller {
	
	private final ProductService pService;
	private final CartService cartSerivce;

	/*
	 * 
	 * 2용
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
	
	*/
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ProductVO productVO, Model model) {
		List<ProductVO> proList = pService.selectAll();

		model.addAttribute("B2C_LIST", proList);
		
		return "users/user_main";
	}
	
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") String strId, Model model) {
		long id = Long.valueOf(strId);
		ProductVO proVO = pService.findById(id);
		
		model.addAttribute("pVO", proVO);
		model.addAttribute("BODY", "DETAIL");
		return "users/user_main";
		
	}
	
	/*
	 * Authentication : 스프링 시큐리티 로그인된 사용자 정보 추출을 위한 인터페이스
	 */
	@ResponseBody
	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public String cart(CartVO cartVO, Authentication authen) {
		try {
			// 스프링 시큐리티로 로그인한 사용자 username 추출
			cartVO.setUsername(authen.getPrincipal().toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "LOGIN_FAIL";
		}
		
		cartSerivce.save(cartVO);
		
		
		//return "LOGIN USER" + authen.getPrincipal();
		return "OK";
		
	}
	
	@RequestMapping(value = "/cart_view", method = RequestMethod.GET)
	public String cart_view(Authentication authen, Model model) {
		model.addAttribute("BODY", "CART_VIEW");
		try {
			String username = authen.getPrincipal().toString();
			List<CartVO> cartList = cartSerivce.selectCart(username);
			model.addAttribute("CART_LIST", cartList);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "users/user_main";
		
	}
	
	@ResponseBody
	// @RequestMapping(value = "/qty_update/{seq}/{p_qty}")
	@RequestMapping(value = "/qty_update")
	public String qty_update(@RequestParam("seq") String seq, @RequestParam("p_qty") String p_qty
			// @PathVariable("seq") String seq, @PathVariable("p_qty") String p_qty
			) {
		long longSeq = Long.valueOf(seq);
		int intQty = Integer.valueOf(p_qty);
		
		int ret = cartSerivce.qty_update(longSeq, intQty);
		return ret + "";
	}
	
	@RequestMapping(value = "/cart_one_delete/{seq}")
	public String cart_one_delete(@PathVariable("seq") String seq) {
		long longSeq = Long.valueOf(seq);
		cartSerivce.deleteOne(longSeq);
		return "redirect:/user/product/cart_view";
	}

	@ResponseBody
	@RequestMapping(value = "/cart_list_delete", method = RequestMethod.POST)
	public Integer cart_list_delete(@RequestParam("delList[]") List<String> strSeqList
			//@PathVariable("seq") String seq
			) {
		
		
		log.debug("SEQ LIST : " + strSeqList.toString());
		
		Integer ret = cartSerivce.cart_list_delete(strSeqList);

		return ret;
	}
	
	@RequestMapping(value = "/cart_list_qty_update", method = RequestMethod.POST)
	public String cart_list_qty_update(CartListVO cartList) {
		
		log.debug("카트 수정 : " + cartList);
		cartSerivce.cart_list_qty_update(cartList);
		return "redirect:/user/product/cart_view";
	}
	
	@ResponseBody
	@RequestMapping(value = "/cart_list_buy", method = RequestMethod.POST)
	public Integer cart_list_buy(@RequestParam("buyList[]") List<String> buyList) {
		
		Integer ret = cartSerivce.cart_to_delivery(buyList);
		return ret;
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
