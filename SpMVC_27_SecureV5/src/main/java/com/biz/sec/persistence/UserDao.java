package com.biz.sec.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.sec.domain.UserDetailsVO;

public interface UserDao {
	
	public List<UserDetailsVO> selectAll();
	
	public void create_table(String create_table);
	
	public UserDetailsVO findByUserName(String username);
	
	// vo를 만들지 않고 Map을 이용해 insert 수행해보기
	public int insert(UserDetailsVO userVO);

	public UserDetailsVO findById(long id);

	public int update(UserDetailsVO userVO);

}
