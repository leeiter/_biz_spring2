package com.biz.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 상품정보의 이미지들을 관리할 table
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProFileVO {
	
	private long id;
	private String file_pcode; // 상품테이블과 join 하기 위한 key
	private String file_origin_name; // 실제이미지
	private String file_upload_name; // 업로드된 변경된 이름

}
