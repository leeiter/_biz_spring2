package com.biz.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.shop.domain.DeptVO;
import com.biz.shop.domain.ProductVO;
import com.biz.shop.repository.DeptDao;
import com.biz.shop.repository.ProductDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeptService {
	
	private final DeptDao dDao;
	
	public void save(DeptVO deptVO) {
		DeptVO d = dDao.save(deptVO);
		log.debug("상품정보 : " + d.toString());
	}
	
	public List<DeptVO> selectAll() {
		List<DeptVO> deptList = dDao.findAll();
		return deptList;
	}

	public DeptVO findById(long id) {
		Optional<DeptVO> deptVO = dDao.findById(id);
		// TODO Auto-generated method stub
		return deptVO.get();
	}

	
	/*
	 * 


	
	public ProductVO findByPCode(String p_code) {
		// ProductVO proVO = pDao.find
		// TODO Auto-generated method stub
		return null;
	}


	 */


}
