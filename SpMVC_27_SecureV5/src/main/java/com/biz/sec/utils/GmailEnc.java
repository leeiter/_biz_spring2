package com.biz.sec.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/*
 * 키보드에서 문자열을 입력받아서
 * 암호화된 문자열을 생성
 */
public class GmailEnc {

	public static void main(String[] args) {
		
		StandardPBEStringEncryptor pbEnc = new StandardPBEStringEncryptor();
		
		/*
		 * 로컬컴퓨터에 설정된 환경변수들 목록을 가져와서
		 * 그중에 BIZ.COM으로 등록된 값을 보여라
		 */
		Map<String, String> envList = System.getenv();
		System.out.println(envList.get("BIZ.COM"));
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Gmail UserName >> ");
		String userName = scanner.nextLine();
		
		System.out.print("Gmail Password >> ");
		String password = scanner.nextLine();
		
		/*
		 * 암호화를 하기 위한 설정
		 * 알고리즘과 Salt password 설정
		 */
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(envList.get("BIZ.COM"));
		
		String encUserName = pbEnc.encrypt(userName);
		String encPassword = pbEnc.encrypt(password);
		
		System.out.printf("username : %s \n", encUserName);
		System.out.printf("password : %s \n", encPassword);
		
		String saveFile = "./src/main/webapp/WEB-INF/spring/properties/gmail.connection.properties";
		
		String saveUserName = String.format("gmail.username=ENC(%s)", encUserName);
		String savePassword = String.format("gmail.password=ENC(%s)", encPassword);
		
		try {
			PrintWriter out = new PrintWriter(saveFile);
			out.println(saveUserName);
			out.println(savePassword);
			
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
		System.out.println("gmail.connection.properties 저장완료.");
		
	}

}
