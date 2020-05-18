package com.biz.shop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Root-context.xml을 대신할 클래스
 * @author leeiter
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.biz.shop.auth", "com.biz.shop.service"})
public class RootConfig {

}
