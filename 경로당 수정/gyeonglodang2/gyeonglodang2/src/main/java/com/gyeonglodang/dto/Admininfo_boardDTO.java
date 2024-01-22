package com.gyeonglodang.dto;

import lombok.Data;

@Data
public class Admininfo_boardDTO {
	private String idx; 
	private int companyIdx; 
	private String name; 
	private String id;
	private String pw;
	private String role; 
	private String auth;  
	private String email; 
	private String tel;
	private String dep; 
}
