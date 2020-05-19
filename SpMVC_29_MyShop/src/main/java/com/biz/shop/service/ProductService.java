package com.biz.shop.service;

import java.util.List;

import com.biz.shop.domain.ProductVO;

/*
 * 상품관리 CRUD 수행할 Service
 */
public interface ProductService {
	
	public void insert(ProductVO productVO);
	
	public List<ProductVO> selectAll();
		
	public void findByPCode(String p_code);
	
	public List<ProductVO> findByPName(String p_name);
	
	public void update(ProductVO productVO);
	public void delete(String p_code);
	
}
