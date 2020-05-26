package com.biz.shop.persistence.sql;

import org.apache.ibatis.jdbc.SQL;

public class ProductSQL {
	
	public String pro_insert() {
		SQL sql = new SQL();
		
		sql.INSERT_INTO("tbl_product")
		.INTO_COLUMNS("p_code, p_name")
		.INTO_VALUES("#{p_code}, #{p_name}")

		.INTO_COLUMNS("p_bcode, p_dcode")
		.INTO_VALUES("#{p_bcode}, #{p_dcode}")

		.INTO_COLUMNS("p_iprice, p_oprice")
		.INTO_VALUES("#{p_iprice}, #{p_oprice}")

		.INTO_COLUMNS("p_vat")
		.INTO_VALUES("#{p_vat}")

		.INTO_COLUMNS("p_file")
		.INTO_VALUES("#{p_file}");
		
		return sql.toString();
		
	}
	
	public String pro_update() {
		SQL sql = new SQL();
		
		sql.UPDATE("tbl_product")
		.SET("p_name = #{p_name}")
		.SET("p_bcode = #{p_bcode}")
		.SET("p_dcode = #{p_dcode}")
		.SET("p_iprice = #{p_iprice}")
		.SET("p_oprice = #{p_oprice}")
		.SET("p_vat = #{p_vat}")
		.SET("p_file = #{p_file}")
		.WHERE("p_code = #{p_code}");
		
		return sql.toString();
	}

}
