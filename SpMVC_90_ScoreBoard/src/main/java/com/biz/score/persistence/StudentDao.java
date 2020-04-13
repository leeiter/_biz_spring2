package com.biz.score.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.score.domain.StudentVO;

public interface StudentDao {
	
	@Select("SELECT * FROM tbl_student")
	public List<StudentVO> selectAll();
	
	@Select("SELECT * FROM tbl_student WHERE st_num = #{st_num}")
	public StudentVO findByNum(String st_num);
	public List<StudentVO> findByName(String st_name);
		
	public int insert(StudentVO studentVO);
	public int update(StudentVO studentVO);
	
	@Delete("DELETE FROM tbl_student WHERE st_num = #{st_num}")
	public int delete(String st_num);
	
}
