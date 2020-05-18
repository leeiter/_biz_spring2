package com.biz.shop.auth;

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
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationProviderImp implements AuthenticationProvider {
	
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
