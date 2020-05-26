package com.biz.shop.service;

import java.util.List;

import com.biz.shop.domain.ProFileVO;

public interface FileUploadService {
	
	public List<ProFileVO> selectAll();
	
	public List<ProFileVO> findByPCode(String file_pcode);
	
	public ProFileVO findById(long id);
	
	public int insert(ProFileVO profileVO);
	
	// 1개씩 삭제하기
	public int delete(long id);
	
	//상품코드로 검색하여 여러개 삭제
	public int delete(String file_pcode);
	
	public int update(ProFileVO proFileVO);

}
