package com.biz.shop.utils;

import java.util.Map;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class PbeEncryptor {
	
	/*
	private static StandardPBEStringEncryptor pbEnc;
	
	static {
		pbEnc = new StandardPBEStringEncryptor();
		
		Map<String, String> envList= System.getenv();
		String strSalt = envList.get("BIZ.COM");
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(strSalt);
	}
	
	public static String getEncrypt(String planText) {
		return pbEnc.encrypt(planText);
	}
	
	public static String getDecrypt(String encText) {
		return pbEnc.decrypt(encText);
	}

*/
}
