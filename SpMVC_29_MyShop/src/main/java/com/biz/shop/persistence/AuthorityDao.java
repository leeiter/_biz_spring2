package com.biz.shop.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.shop.domain.AuthorityVO;

public interface AuthorityDao {
	
	@Select("SELECT * FROM authorities WHERE username = #{username}")
	public List<AuthorityVO> findByUserName(String username);

}
