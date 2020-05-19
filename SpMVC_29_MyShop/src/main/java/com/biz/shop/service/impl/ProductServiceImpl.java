package com.biz.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.persistence.DDL_Dao;
import com.biz.shop.persistence.ProductDao;
import com.biz.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDao proDao;
	private final DDL_Dao ddl_dao;
	
	public ProductServiceImpl(ProductDao proDao, DDL_Dao ddl_dao) {
		this.proDao = proDao;
		this.ddl_dao = ddl_dao;
		
		String create_product_table
				= " CREATE TABLE IF NOT EXISTS tbl_product "
				+ " (p_code VARCHAR(6) PRIMARY KEY, "
				+ " p_name VARCHAR(125), "
				+ " p_bcode VARCHAR(6), "
				+ " p_dcode VARCHAR(6), "
				+ " p_iprice int, "
				+ " p_oprice int, "
				+ " p_vat boolean default true, "
				+ " p_file VARCHAR(255)) ";
		
		ddl_dao.create_table(create_product_table);
	}
	
	@Override
	public void insert(ProductVO productVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findByPCode(String p_code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductVO> findByPName(String p_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ProductVO productVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String p_code) {
		// TODO Auto-generated method stub
		
	}

}
