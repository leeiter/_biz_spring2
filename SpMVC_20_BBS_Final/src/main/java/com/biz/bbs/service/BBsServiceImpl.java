package com.biz.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.dao.BBsDao;
import com.biz.bbs.domain.BBsVO;

@Service
public class BBsServiceImpl implements BBsService {

	protected final BBsDao bbsDao;
	
	public BBsServiceImpl(BBsDao bbsDao) {
		this.bbsDao = bbsDao;
	}
	
	@Override
	public List<BBsVO> selectAll() {
		return bbsDao.selectAll();
	}

	@Override
	public BBsVO findById(long b_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BBsVO> findBySubject(String b_subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BBsVO> findByWriter(String b_writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BBsVO bbsVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BBsVO bbsVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long b_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
