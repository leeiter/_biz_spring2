package com.biz.score.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.score.domain.ScoreVO;

public interface ScoreDao {
	
	@Select("SELECT * FROM tbl_score")
	public List<ScoreVO> selectAll();
	
	@Select("SELECT * FROM tbl_score WHERE s_num = #{s_num}")
	public ScoreVO findByNum(String s_num);
		
	public int insert(ScoreVO scoreVO);
	public int update(ScoreVO scoreVO);
	
	@Delete("DELETE FROM tbl_score WHERE s_num = #{s_num}")
	public int delete(String s_num);

}
