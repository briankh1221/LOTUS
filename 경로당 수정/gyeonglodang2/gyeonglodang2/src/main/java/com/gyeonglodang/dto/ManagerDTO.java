package com.gyeonglodang.dto;

import lombok.Data;

@Data
public class ManagerDTO {
	
//	idx int AI PK 
//	part_list_title varchar(50) 
//	announcement_date varchar(30) 
//	announcement_setting varchar(20)
	
	private int rownum;
	private int idx;
	private int companyIdx;
	private int gonggoidx;
	private String part_list_title;
	private String announcement_date;
	private String announcement_setting;
	

}
