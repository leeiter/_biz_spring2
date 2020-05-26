package com.biz.shop.config.security;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;


@Slf4j
// project 시작할 때 처음으로 확인
@Configuration
public class JasyptConfig {
	
	/*
	 * 환경변수를 읽어서
	 * jasypt 암호화, 복호화 코드에 주입을 하는 역할
	 */
	@Bean
	public EnvironmentStringPBEConfig envConfig() {
		EnvironmentStringPBEConfig envConfig = new EnvironmentStringPBEConfig();
		envConfig.setAlgorithm("PBEWithMD5AndDES");
		envConfig.setPasswordEnvName("BIZ.COM");
		log.debug("JAST SALT: " + envConfig.getPassword());
		return envConfig;
	}

	// 실질적으로 일을 하는 클래스
	@Bean
	public StringEncryptor stringEncryptor() {
		StandardPBEStringEncryptor stringEnc = new StandardPBEStringEncryptor();
		stringEnc.setConfig(envConfig());
		return stringEnc;
	}
	
}
