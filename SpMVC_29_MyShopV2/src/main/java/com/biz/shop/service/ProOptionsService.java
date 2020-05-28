package com.biz.shop.service;

import java.util.List;

import com.biz.shop.domain.ProColorVO;
import com.biz.shop.domain.ProOptionsVO;
import com.biz.shop.domain.ProSizeVO;

public interface ProOptionsService {
	
	public List<ProOptionsVO> getColorList();
	public List<ProOptionsVO> getSizeList();
	
	public int insert_size(ProSizeVO proSizeVO);
	
	// tbl_pro_table에 상품코드가 같고 사이즈가 같은 레코드가
	// 이미 등록(저장)되어 있는지 판단하기 위한 method
	public int getProSize(ProSizeVO proSizeVO);
	public int delete_size(ProSizeVO proSizeVO);
		
	public Object insert_color(ProColorVO proColorVO);
	// public int getProColor(ProColorVO proColorVO);
	
	public List<ProColorVO> getColorListBySize(String s_seq);
	
	

}
