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
public class ProColorVO {
	
	private long c_seq;
	private long size_seq;
	private String c_color;
	private String o_name;
	private int c_qty;
	

}
