package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInfoValueDTO {
	private int idx;
	private int gonggoidx;
	private String email;
	private String name;
	private String birth_year;
	private String birth_month;
	private String birth_date;
	private String zipcode;
	private String roadname;
	private String detail_address;
	private String phone_frontnum;
	private String phone_middlenum;
	private String phone_backnum;
	private String emergency_frontnum;
	private String emergency_middlenum;
	private String emergency_backnum;
	private String lowerclass;
	private String disabled;
	private String army;
	private String youth;
}
