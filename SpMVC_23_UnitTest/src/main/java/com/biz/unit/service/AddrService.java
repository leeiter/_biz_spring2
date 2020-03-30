package com.biz.unit.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AddrService {
	
	public Map<String, String> getAddr() {
		Map<String, String> addr = new HashMap<String, String>();
		addr.put("name", "홍길동");
		addr.put("addr", "서울특별시");
		addr.put("tel", "010-1111-1111");
		
		return addr;
	}
	
	public String getName()
	{
		
	}
}
