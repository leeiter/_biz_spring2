package com.biz.sec.service;

import java.util.HashMap;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserVO;
import com.biz.sec.persistence.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	// @Autowired
	private final PasswordEncoder passwordEncoder;

	private final UserDao userDao;

	/**
	 * @since 2020-04-09
	 * @author leeiter
	 * 
	 * @param username
	 * @param password
	 * @return
	 * 
	 * 회원가입을 신청하면 비밀번호는 암호화하고
	 * 아이디와 비밀번호를 DB insert 수행
	 * 
	 * @update 2020-04-10
	 * Map<String, String> 구조의 VO 데이터를
	 * UserVO로 변경
	 */
	public int insert(String username, String password) {
		// 회원가입 form에서 전달 받은 password 값을 암호화 시키는 과정
		String encPassword = passwordEncoder.encode(password);
		UserVO userVO = new UserVO().builder()
				.username(username)
				.password(encPassword)
				.build();

		int ret = userDao.insert(userVO);
		return ret;
	}

	public boolean isExistsUserName(String username) {
		UserVO userVO = userDao.findByUserName(username);
		
		// 이미 DB에 회원정보(username)이 저장되어 있다.		
		if(userVO != null && userVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		return false;
	}

}
