package com.biz.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Servlet-context.xml을 대신할 클래스
 * @author leeiter
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.biz.shop.controller", "com.biz.shop.service"})
public class ServletConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {		
		// static 폴더로 외부에 보여준다.
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/files/**").addResourceLocations("/files/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	/*
	 * fileUpload를 하기 위한 설정 
	 */
	@Bean
	public MultipartResolver multiResolver() {
		MultipartResolver mr = new CommonsMultipartResolver();
		((CommonsMultipartResolver)mr).setMaxUploadSize(1000 * 1000 * 2); // 1개당 2MB
		((CommonsMultipartResolver)mr).setMaxUploadSizePerFile(1000 * 1000 * 20); // 전체 20MB
		
		// SQL에서는 불가. java에서만 가능
		// ((CommonsMultipartResolver)mr).setMaxUploadSize(20_000_000); // 1개당 2MB
		// ((CommonsMultipartResolver)mr).setMaxUploadSizePerFile(20_000_000); // 전체 20MB
		return mr;
	}

	/*
	 * jsp rendering을 위한 설정
	 */
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

}
