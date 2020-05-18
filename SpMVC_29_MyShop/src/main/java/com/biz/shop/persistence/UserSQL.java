package com.biz.shop.persistence;

import org.apache.ibatis.jdbc.SQL;

/*
 * SQL class
 * Mybatis 3.5 이상에서 사용하는 동적쿼리 작성 클래스
 */
public class UserSQL {
	
	public String select_user() {
		SQL sql = new SQL();
		sql.FROM("TBL_USERS")
		.SELECT("user_name as username")
		.SELECT("user_pass as password")
		.SELECT("ENABLED")
		.SELECT("email")
		.SELECT("phone")
		.SELECT("address")
		// .SELECT("nickname")
		.SELECT("ID");
		
		return sql.toString();
		
		
	}
	
	public String select_user_one() {
		SQL sql = new SQL();
		sql.FROM("TBL_USERS")
		.WHERE("user_name = #{username}")
		.SELECT("user_name as username")
		.SELECT("user_pass as password")
		.SELECT("ENABLED")
		.SELECT("email")
		.SELECT("phone")
		.SELECT("address")
		// .SELECT("nickname")
		.SELECT("ID");
		
		return sql.toString();
		
		
	}

}
