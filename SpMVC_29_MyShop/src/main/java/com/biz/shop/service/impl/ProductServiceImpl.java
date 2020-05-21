package com.biz.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.persistence.DDL_Dao;
import com.biz.shop.persistence.ProductDao;
import com.biz.shop.persistence.sql.CreateTableSQL;
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
		ddl_dao.create_table(CreateTableSQL.create_pro_size_table);
		ddl_dao.create_table(CreateTableSQL.create_pro_color_table);
	}
	
	@Override
	public int insert(ProductVO productVO) {
		return proDao.insert(productVO);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductVO> selectAll() {
		// TODO Auto-generated method stub
		return proDao.selectAll();
	}

	@Override
	public ProductVO findByPCode(String p_code) {
		
		return proDao.findByPCode(p_code);
		
	}

	@Override
	public List<ProductVO> findByPName(String p_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ProductVO productVO) {
		return 0;
		
	}

	@Override
	public int delete(String p_code) {
		return 0;
		
	}

}
