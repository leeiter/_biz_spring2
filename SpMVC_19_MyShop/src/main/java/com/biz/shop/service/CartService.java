package com.biz.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.shop.dao.CartDao;
import com.biz.shop.domain.CartVO;
import com.biz.shop.persistence.CartRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {

	private final CartDao cartDao;
	private final CartRepository cartRepository;
	
	public int countCart() {
		return cartDao.countCart();
	}
	
	public int countDelivery() {
		return cartDao.countDelivery();
	}
	
	public List<CartVO> selectCart(String username) {
		return cartDao.selectCart(username);
	}
	
	public List<CartVO> selectDelivery(String username) {
		return cartDao.selectDelivery(username);
	}

	public CartVO save(CartVO cartVO) {
		cartVO.setP_status("CART");
		return cartRepository.save(cartVO);
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
