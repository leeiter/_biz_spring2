package com.biz.shop.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.biz.shop.domain.UserDetailsVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * @Component :
 * @Controller, @Service 등과 같이
 * 자동으로 scan 되어 @Bean으로 등록할 클래스를 지정
 * Controller, Service, Repository 등과 같이 기능을 
 * 명시적으로 지정하기 애매한 클래스는 Component로 지정을 한다.
 * 
 * spring security에서 login을 직접 수행하는 클래스
 * login을 customizing하여
 * UserDetailsService로 부터 DB를 조회하여
 * 사용자 정보를 받아오고
 * 비빌번호 BCrypt..Encoder를 사용해서 암호화되어 저장된 값과 비교하고
 * 일치하면 로그인을 승인하는 열할을 수행하는 클래스
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationProviderImp implements AuthenticationProvider {
	
	// @Autowired를 대체하는 방식으로
	// 객체를 private final로 선언하고
	// @RequiredArgsContructor를 선언하여
	// 사용할 수 있도록 초기화를 수행한다.
	private final UserDetailsService userService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		UserDetailsVO userVO = (UserDetailsVO) userService.loadUserByUsername(username);
		
		passwordEncoder.matches(password, userVO.getPassword());
		
		if(!userVO.isEnabled()) {
			throw new BadCredentialsException(username + "접근금지!!");
		}
		
		return new UsernamePasswordAuthenticationToken(userVO, null, userVO.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
