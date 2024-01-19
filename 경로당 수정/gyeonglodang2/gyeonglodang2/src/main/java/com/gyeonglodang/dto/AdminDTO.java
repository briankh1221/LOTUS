package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
	private int idx;	//idx(기준값)
	private String company;	//회사명
	private String id;	//admin ID
	private String pw;	//admin PW
	private int role;	//admin Role
}
