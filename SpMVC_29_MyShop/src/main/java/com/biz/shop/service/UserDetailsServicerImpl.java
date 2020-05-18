package com.biz.shop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biz.shop.domain.UserDetailsVO;
import com.biz.shop.persistence.UserDao;

@Service
public class UserDetailsServicerImpl implements UserDetailsService {
	
	private final UserDao userDao;

	public UserDetailsServicerImpl(UserDao userDao) {
		this.userDao = userDao;	
		
		// 테이블 생성 부분을 코딩하기 위한 방법
		String create_user_table = " CREATE TABLE IF NOT EXISTS tbl_users( " + 
				" id BIGINT PRIMARY KEY AUTO_INCREMENT, " + 
				" user_name VARCHAR(50) UNIQUE, " + 
				" user_pass VARCHAR(125), " + 
				" enabled BOOLEAN default true, " +				
				" email VARCHAR(50), " + 
				" phone VARCHAR(20), " + 
				" address VARCHAR(125) " + 
				" ) ";
		
		String create_auth_table = " CREATE TABLE IF NOT EXISTS authorities( " + 
				" id BIGINT PRIMARY KEY AUTO_INCREMENT, " + 
				" username VARCHAR(50), " + 
				" authority VARCHAR(50) " + 
				" ) ";
		
		userDao.create_table(create_user_table);
		userDao.create_table(create_auth_table);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsVO userVO = userDao.findByUserName(username);
		if(userVO == null) {
			throw new UsernameNotFoundException(username + "정보를 찾을 수 없음");
		}
		return userVO;
	}

}
