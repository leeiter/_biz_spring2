package com.biz.shop.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.shop.domain.Authorities;
import com.biz.shop.domain.Users;
import com.biz.shop.repository.AuthDao;
import com.biz.shop.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
	
	private final UserDao userDao;
	private final AuthDao authDao;
	
	private final BCryptPasswordEncoder passEncoder;
	
	@Transactional
	public void userSave(Users userVO) {
		
		String userRole = "ROLE_ADMIN";
		
		// 처음 등록 사용자에게는 Admin을 부여하고
		// 이후 사용자에게는 User를 부여하는 코드를 부착
		
		String planPass = userVO.getPassword();
		String cryptPass = passEncoder.encode(planPass);
		
		userVO.setPassword(cryptPass);
		userVO.setEnabled(true);
		
		Authorities auth = Authorities.builder()
				.username(userVO.getUsername())
				.authority(userRole)
				.build();
		
		userDao.save(userVO);
		authDao.save(auth);
		
		
	}
	
}
