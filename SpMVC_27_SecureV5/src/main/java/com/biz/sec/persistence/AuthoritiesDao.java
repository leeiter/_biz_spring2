package com.biz.sec.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.sec.domain.AuthorityVO;

public interface AuthoritiesDao {

	/*
	 * 사용자 이름으로 권한 테이블에서 권한 리스트를 SELECT
	 */
	@Select("SELECT * FROM authorities WHERE username = #{username}")
	List<AuthorityVO> findByUserName(String username);

	int insert(List<AuthorityVO> authCollection);

	@Delete("DELETE FROM authorities WHERE username = #{username}")
	int delete(String username);

}
