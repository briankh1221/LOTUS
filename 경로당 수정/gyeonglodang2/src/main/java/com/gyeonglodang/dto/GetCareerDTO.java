package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCareerDTO {
	private int idx;
	private int gongoidx;
	private String careerTitle;
	private int careerField;
	private String careerNotice;

}
