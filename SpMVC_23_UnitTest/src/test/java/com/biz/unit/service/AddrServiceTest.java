package com.biz.unit.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * spring framework 환경에서 test를 수행하기 위한
 * 초기화 구분
 * 
 * spring 프로젝트가 bean 등 설정파일을 불러와서
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/*-context.xml"})
public class AddrServiceTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
