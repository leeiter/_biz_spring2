package com.biz.models.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.biz.models.domain.UsersVO;

public interface UserDao {
	
	@Select("SELECT * FROM tbl_user WHERE userId = #{userId}")
	public UsersVO findByUserId(String userId);

	@Insert("INSERT INTO tbl_user(userId, password, userName, userRolle) VALUES(#{userId}, #{password}, #{userName}, #{userRolle})")
	public int insert(UsersVO usersVO);
	
	@Delete("DELETE FROM tbl_user WHERE userId = #{userId}")
	public int delete(String userId);
	
}
