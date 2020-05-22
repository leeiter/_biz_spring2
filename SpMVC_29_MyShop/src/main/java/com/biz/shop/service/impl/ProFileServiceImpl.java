package com.biz.shop.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/*
 * 상품정보의 대표 이미지 업로드 서비스
 */
@Slf4j
@Service
public class ProFileServiceImpl {
	
	@Autowired
	private String filePath;
	
	/*
	 * singleFile Upload method
	 */
	public String fileUpLoad(MultipartFile file) {
		log.debug("파일저장경로 : " + filePath);
		
		// 실제 업로드할 파일 물리적 경로(폴더)가 있는지 확인하고
		// 없으면 생성하는 절차 수행
		File dir = new File(filePath);
		if(!dir.exists()) {
			// dir.mkdir(); // 경로가 마지막 값만 만들고자 할 때
			dir.mkdirs(); // prents
		}
		
		/*
		 * Upload할 Original 파일이름을 file 객체로 부터 추출하여
		 * UUID 값과 연결한 유일한 파일 이름으로 변경하고
		 * file 객체의 transferTo() method를 사용하여 Upload 수행
		 */
		String originName = file.getOriginalFilename();
		String uuID = UUID.randomUUID().toString();
		String saveName = uuID + "_" + originName;
		
		File uploadFile = new File(filePath, originName);
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saveName;
	}

}
