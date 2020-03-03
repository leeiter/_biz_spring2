package com.biz.shop.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.biz.shop.dao.DeptDao;
import com.biz.shop.domain.DeptVO;
import com.biz.shop.domain.ProductVO;
import com.biz.shop.persistence.DeptRepository;
import com.biz.shop.persistence.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeptService {
	
	private final DeptRepository deptRepo;
	private final DeptDao deptDao;
	
	public List<DeptVO> selectAll() {
		List<DeptVO> deptList = deptRepo.findAll();
		return deptList;
	}

	public DeptVO save(DeptVO deptVO) {
		DeptVO ret = deptRepo.save(deptVO);
		return ret;
	}

	public List<DeptVO> findByDName(String search) {
		return deptDao.findByDName(search);
	}
	
	
	
	
	/*


	public DeptVO findById(long id) {
		Optional<DeptVO> deptVO = dDao.findById(id);
		// TODO Auto-generated method stub
		return deptVO.get();
	}


	 * 


	
	public ProductVO findByPCode(String p_code) {
		// ProductVO proVO = pDao.find
		// TODO Auto-generated method stub
		return null;
	}


	 */


}
