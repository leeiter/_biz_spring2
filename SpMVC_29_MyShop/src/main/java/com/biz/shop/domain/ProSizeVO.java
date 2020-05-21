package com.biz.shop.domain;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProSizeVO {
	
	private long s_seq;
	private String p_code;
	private String s_size;

}
