package com.biz.shop.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.biz.shop.domain.ProFileVO;
import com.biz.shop.service.FileUploadService;

public interface FileUpDao extends FileUploadService {

	@Select("SELECT * FROM tbl_profile")
	public List<ProFileVO> selectAll();
	
	@Select("SELECT * FROM tbl_profile WHERE file_pcode = #{file_pcode}")
	public List<ProFileVO> findByPCode(String file_pcode);
	
	@Select("SELECT * FROM tbl_profile WHERE id = #{id}")
	public ProFileVO findById(long id);
	
	@Insert(" INSERT INTO tbl_profile (file_pcode, file_origin_name, file_upload_name) "
			+ " VALUES (#{file_pcode}, #{file_origin_name}, #{file_upload_name}) ")
	public int insert(ProFileVO profileVO);

	@Delete("DELETE FROM tbl_profile WHERE id = #{id}")
	public int findByIdDelete(long id);

	@Delete("DELETE FROM tbl_profile WHERE file_pcode = #{file_pcode}")
	public int findByPCodeDelete(String file_pcode);
	
	public int update(ProFileVO proFileVO);

}
