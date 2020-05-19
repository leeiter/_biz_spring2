package com.biz.shop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Root-context.xml을 대신할 클래스
 * @author leeiter
 *
 */
/*
 * 현재 AuthenticationProvider에서
 * PasswordEncoder와 UserDetailsService 객체를 주입받아
 * 로그인 과정을 수행하고 있는 관계로
 * com.biz.shop.auth와 com.biz.shop.service 패키지를 스캔하여
 * @Bean으로 설정된 항목을 미리 객체로 주입할 준비를 하도록
 * 설정해 두어야 한다.
 */
@Configuration
@ComponentScan(basePackages = {"com.biz.shop.auth", "com.biz.shop.service"})
public class RootConfig {

}
