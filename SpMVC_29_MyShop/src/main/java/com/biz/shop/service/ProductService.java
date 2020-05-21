package com.biz.shop.service;

import java.util.List;

import com.biz.shop.domain.ProductVO;

/*
 * 상품관리 CRUD 수행할 Service
 */
public interface ProductService {
	
	public int insert(ProductVO productVO);
	
	public List<ProductVO> selectAll();
		
	public ProductVO findByPCode(String p_code);
	
	public List<ProductVO> findByPName(String p_name);
	
	public int update(ProductVO productVO);
	public int delete(String p_code);
	
}
