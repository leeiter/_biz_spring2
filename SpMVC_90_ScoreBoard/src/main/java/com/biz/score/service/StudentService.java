package com.biz.score.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.score.domain.StudentVO;
import com.biz.score.persistence.StudentDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentService {
	
	private final StudentDao studentDao;
	
	public List<StudentVO> selectAll() {
		return studentDao.selectAll();
	}
	
	public StudentVO findByNum(String st_num) {
		return studentDao.findByNum(st_num);
	}
	
	public List<StudentVO> findByName(String st_name) {
		return studentDao.findByName(st_name);
	}
	
	public int save(StudentVO studentVO) {
		String st_num = studentVO.getSt_num();
		
		StudentVO sVO = studentDao.findByNum(st_num);
		
		if(sVO != null) {
			studentDao.update(studentVO);
		} else {
			studentDao.insert(studentVO);
		}
		return 0;
	}

	public int delete(String st_num) {
		return studentDao.delete(st_num);
	}

	public boolean isExistsNum(String st_num) {
		
		StudentVO studentVO = studentDao.findByNum(st_num);
		if(studentVO != null && studentVO.getSt_num().equalsIgnoreCase(st_num)) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}
	
}
