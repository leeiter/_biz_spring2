package com.biz.shop.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class MySQLEnc {

	public static void main(String[] args) {
	
		StandardPBEStringEncryptor pbEnc = new StandardPBEStringEncryptor();
		
		Map<String, String> envList = System.getenv();
		System.out.println(envList.get("BIZ.COM"));
		
		Scanner scan = new Scanner(System.in);
		System.out.print("MySQL UserName >> ");
		String userName = scan.nextLine();
		
		System.out.print("MySQL Password >> ");
		String password = scan.nextLine();
		
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		
		// 환경변수에서 SALT 비번 가져오기
		pbEnc.setPassword(envList.get("BIZ.COM"));
		
		// BIZ.COM 문자열로 SALT 비번으로 하기
		// pbEnc.setPassword("BIZ.COM");
		
		String encUserName = pbEnc.encrypt(userName);
		String encPassword = pbEnc.encrypt(password);
		
		System.out.printf("username : %s \n", encUserName);
		System.out.printf("password : %s \n", encPassword);
		
		ResourceLoader resLoader = new DefaultResourceLoader();
		Resource res = resLoader.getResource("file:src/main/resources/db.connection.properties"); // 절대경로 지정
		
		String saveUserName = String.format("mysql.username=%s", encUserName);
		String savePassword = String.format("mysql.password=%s", encPassword);
		
		PrintWriter out;
		try {
			out = new PrintWriter(res.getFile());
			out.println(saveUserName);
			out.println(savePassword);
			
			out.flush();
			out.close();
			
			System.out.println(res.getFile().toString() + " 저장완료.");			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		scan.close();
		
	}
	
}
