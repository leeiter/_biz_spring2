package com.biz.shop.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;import org.apache.ibatis.jdbc.SQL;

import com.biz.shop.domain.UserDetailsVO;
import com.biz.shop.persistence.sql.UserSQL;

public interface UserDao {
	
	/*
	 * SQL 클래스를 사용하여 작성된 동적쿼리를
	 * mapping하는 Annotation
	 * 
	 * type : SQL 클래스로 작성된 동적쿼리가 있는 클래스
	 * method : 동적쿼리 클래스 내의 실제 method 이름
	 */
	@SelectProvider(type = UserSQL.class, method = "select_user")
	public List<UserDetailsVO> selectAll();
	
	@SelectProvider(type = UserSQL.class, method = "select_user_one")
	public UserDetailsVO findByUserName(String username);
	
	// 동적 쿼리 문자열을 받아서 테이블을 생성하는 쿼리
	// DDL_Dao interface로 통합
	// ("${create_table_query}")
	// public void create_table(String create_table_query);
	
}
