package com.biz.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.repository.ProductDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductDao pDao;
	
	public void save(ProductVO productVO) {
		ProductVO p = pDao.save(productVO);
		log.debug("상품정보 : " + p.toString());
	}
	
	public List<ProductVO> selectAll() {
		List<ProductVO> proList = pDao.findAll();
		return proList;
	}
	
	
	
}
