package com.biz.shop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.biz.shop.dao.CartDao;
import com.biz.shop.domain.CartListVO;
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
	
	public int qty_update(long seq, int p_qty) {
		return cartDao.qty_update(seq, p_qty);
	}

	public void deleteOne(long longSeq) {
		cartRepository.deleteById(longSeq);
		// TODO Auto-generated method stub
		
	}

	public int cart_list_delete(List<String> strSeqList) {
		return cartDao.cart_list_delete(strSeqList);
		// TODO Auto-generated method stub

	}

	@Transactional
	public void cart_list_qty_update(CartListVO cartList) {
		int nSize = cartList.getP_qty().size();
		for (int i = 0 ; i < nSize ; i++) {
			cartDao.qty_update(cartList.getSeq().get(i), cartList.getP_qty().get(i));
		}
		// TODO Auto-generated method stub
		
	}

	public Integer cart_to_delivery(List<String> buyList) {
		
		
		// TODO Auto-generated method stub
		return cartDao.cart_to_delivery(buyList);
	}
	
	
	
	
}
