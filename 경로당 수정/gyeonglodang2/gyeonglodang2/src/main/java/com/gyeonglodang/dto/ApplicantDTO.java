package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDTO {
	private int idx;
	private String email;
	private String password;
	private String field;
	private int gonggoidx;
	private int scode;
}
