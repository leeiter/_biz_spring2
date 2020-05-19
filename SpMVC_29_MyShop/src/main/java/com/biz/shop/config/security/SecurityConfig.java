package com.biz.shop.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationProvider authProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http);
		
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/mypage").hasAnyRole("ADMIN", "USER")
		.antMatchers("/user/**").permitAll()
		 .antMatchers("/**").permitAll(); // 이 코드를 사용하게 되면 아래있는 코드는 의미가 없어짐
//		.anyRequest().authenticated(); // 위에 나열한 것 외에는 모두 인증 필요
		
		http.formLogin()
		
		// security에서 지원하는 login URL
		.loginProcessingUrl("/login")
		
				
		// login form
		.loginPage("/user/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.and().httpBasic();
		
		http.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// super.configure(auth);
		auth.authenticationProvider(authProvider);
	}

	/*
	 * security 필터를 거치지 않고 요청에 응답할 요소들
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// super.configure(web);
		
		// resources 폴더 안에 있는 파일은 확인하지 마라
		web.ignoring().antMatchers("/resources/**");
	}
	
	/*
	 * 암호화된 password를 비교(match)하기 위한 클래스 생성
	 * Authen..Provider에 주입되는 객체
	 * 
	 * 사용자 암호를 
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
