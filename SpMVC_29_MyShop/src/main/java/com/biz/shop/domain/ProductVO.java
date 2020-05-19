package com.biz.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductVO {
	
	private String p_code;
	private String p_name;
	
	private String p_bcode; // 품목코드
	private String p_dcode; // 주매입처 코드

	private int p_iprice; // 매입가격
	private int p_oprice; // 판매가격
	
	private boolean p_vat; // 과세여부 true : 과세, false : 면세
	
	private String p_file; // 상품의 대표 이미지
}
