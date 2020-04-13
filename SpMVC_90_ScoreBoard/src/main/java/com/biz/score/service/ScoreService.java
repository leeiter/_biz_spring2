package com.biz.score.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.score.domain.ScoreVO;
import com.biz.score.persistence.ScoreDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScoreService {
	
	private final ScoreDao scoreDao;
	
	public List<ScoreVO> selectAll() {
		return scoreDao.selectAll();
	}
	
	public ScoreVO findByNum(String s_num) {
		return scoreDao.findByNum(s_num);
	}
	
	public int save(ScoreVO scoreVO) {
		String s_num = scoreVO.getS_num();
		
		ScoreVO sVO = scoreDao.findByNum(s_num);
		
		if(sVO != null) {
			scoreDao.update(scoreVO);
		} else {
			scoreDao.insert(scoreVO);
		}
		return 0;
	}

	public int delete(String s_num) {
		return scoreDao.delete(s_num);
	}

	public boolean isExistsNum(String s_num) {
		
		ScoreVO scoreVO = scoreDao.findByNum(s_num);
		if(scoreVO != null && scoreVO.getS_num().equalsIgnoreCase(s_num)) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}
	
}
