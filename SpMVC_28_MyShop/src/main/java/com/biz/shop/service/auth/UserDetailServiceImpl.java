package com.biz.shop.service.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biz.shop.domain.AuthVO;
import com.biz.shop.domain.UserVO;
import com.biz.shop.persistence.AuthDao;
import com.biz.shop.persistence.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
	
	private final AuthDao authDao;
	private final UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO userVO = userDao.findByUserName(username);
		
		if(userVO == null) {
			throw new UsernameNotFoundException("USERNAME이 없습니다.");
		}
		
		userVO.setAccountNonExpired(true);
		userVO.setAccountNonLocked(true);
		userVO.setCredentialsNonExpired(true);
		userVO.setAuthorities(this.getAuthorities(username));
		
		return userVO;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String username) {
		List<AuthVO> authList = authDao.findByUserName(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(AuthVO vo : authList) {
			SimpleGrantedAuthority sAuth = new SimpleGrantedAuthority(vo.getAuthority());
			authorities.add(sAuth);
		}
	
		return authorities;
	}

}
