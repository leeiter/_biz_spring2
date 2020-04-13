package com.biz.score.domain;

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
public class StudentVO {

    private String st_num; 	// varchar(5) UNIQUE
    private String st_name; // varchar(20)
    private int st_class; 	// int
    private int  st_group; 	// int

}
