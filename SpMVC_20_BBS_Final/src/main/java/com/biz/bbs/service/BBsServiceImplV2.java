package com.biz.bbs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.repository.BBsDao;

import lombok.RequiredArgsConstructor;

@Transactional
@Service("bbsV2")
public class BBsServiceImplV2 extends BBsServiceImpl {

	public BBsServiceImplV2(BBsDao bbsDao) {
		super(bbsDao);
	}

	public List<BBsVO> selectAll() {
		List<BBsVO> retList = bbsDao.selectLevel();
		return retList;
	}

}
