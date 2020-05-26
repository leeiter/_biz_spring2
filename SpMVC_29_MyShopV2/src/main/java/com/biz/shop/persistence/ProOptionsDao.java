package com.biz.shop.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.biz.shop.domain.ProColorVO;
import com.biz.shop.domain.ProOptionsVO;
import com.biz.shop.domain.ProSizeVO;

public interface ProOptionsDao {
	
	@Select(" SELECT * FROM tbl_options WHERE o_division = 'COLOR' ")
	public List<ProOptionsVO> getColorList();
	
	@Select(" SELECT * FROM tbl_options WHERE o_division = 'SIZE' ")
	public List<ProOptionsVO> getSizeList();
	
	@Insert("INSERT INTO tbl_pro_size(p_code, s_size) VALUES(#{p_code}, #{s_size})")
	public int insert_size(ProSizeVO proSizeVO);
	
	@Delete("DELETE FROM tbl_pro_size WHERE p_code = #{p_code} AND s_size = #{s_size}")
	public int delete_size(ProSizeVO proSizeVO);
	
	public int insert_color(ProColorVO proColorVO);
	
	@Select("SELECT COUNT(*) FROM tbl_pro_size WHERE p_code = #{p_code} AND s_size = #{s_size}")
	public int getProSize(ProSizeVO proSizeVO);

}
