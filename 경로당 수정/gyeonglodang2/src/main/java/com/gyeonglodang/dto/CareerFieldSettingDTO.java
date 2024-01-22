package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CareerFieldSettingDTO {
	private int idx;
	private int noticeControl_idx;
	private String careerField;
	private String careerField_idx;
}
