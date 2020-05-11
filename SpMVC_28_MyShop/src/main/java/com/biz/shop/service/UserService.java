package com.biz.shop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.shop.domain.UserVO;
import com.biz.shop.persistence.AuthDao;
import com.biz.shop.persistence.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final AuthDao authDao;
	private final UserDao userDao;

	@Transactional
	public int insert(UserVO userVO) {
		userVO.setEnabled(true);
		userVO.setAuthorities(null);
		
		String encPassword = passwordEncoder.encode(userVO.getPassword());
		userVO.setPassword(encPassword);
		
		int ret = userDao.insert(userVO);
		return ret;
	}
	
	public boolean isExistsUserName(String username) {
		UserVO userVO = userDao.findByUserName(username);
		
		if(userVO != null && userVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		
		return false;
	}
	
	public UserVO findById(long id) {
		UserVO userVO = userDao.findById(id);
		return userVO;
	}
	
	public boolean check_password(String password) {
		UserVO userVO = (UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return passwordEncoder.matches(password, userVO.getPassword());
	}
	
	@Transactional
	public int update(UserVO userVO) {
		Authentication oldAuth = SecurityContextHolder.getContext().getAuthentication();
		
		UserVO oldUserVO = (UserVO) oldAuth.getPrincipal();
		oldUserVO.setEmail(userVO.getEmail());
		oldUserVO.setPhone(userVO.getPhone());
		oldUserVO.setAddress(userVO.getAddress());
		
		int ret = userDao.update(oldUserVO);
		if(ret > 0) {
			Authentication newAuth = new UsernamePasswordAuthenticationToken(oldUserVO, oldAuth.getCredentials(), oldAuth.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		return ret;
		
	}
	
	private Collection<GrantedAuthority> getAuthorities(String[] authList) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(String auth : authList) {
			if(!auth.isEmpty()) {
				SimpleGrantedAuthority sAuth = new SimpleGrantedAuthority(auth);
				authorities.add(sAuth);
			}
		}
		
		return authorities;
	}
	
	@Transactional
	public List<UserVO> selectAll() {
		return userDao.selectAll();
	}
	
	public UserVO findByUserName(String username) {
		return userDao.findByUserName(username);
	}
	
	
	

}
