package com.biz.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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

/*
 * servlet Config에서 scan 하는 shop.controller, shop.service 패키지의
 * 클래스들에게 Transaction을 적용시키려면
 * servletConfing의 ComponentScan이 설정된 부분에
 * @EnableTransactionManagement 를 설정해 두어야 한다.
 */
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.biz.shop.controller", "com.biz.shop.service"})
public class ServletConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {		
		// static 폴더로 외부에 보여준다.
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/files/**").addResourceLocations("/files/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///bizwork/upload/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	@Bean(name = "filePath")
	public String filePath() {
		return "c:/bizwork/upload";
	}
	
	/*
	 * fileUpload를 하기 위한 설정
	 * method 이름 같게 하고 다르면
	 * @Bean에 이름 설정을 해주어야 한다. 
	 */
	@Bean
	public MultipartResolver multipartResolver() {
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
