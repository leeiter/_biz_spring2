package com.biz.shop.persistence;

import java.util.List;

import com.biz.shop.domain.UserVO;

public interface UserDao {
	
	public List<UserVO> selectAll();
	
	public UserVO findById(long id);
	public UserVO findByUserName(String username);
	
	public int insert(UserVO userVO);
	public int update(UserVO userVO);
	
}
