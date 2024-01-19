package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerAnswerDTO {
	private int career_idx;
	private	int applicant_idx;
	private String careerQuestion;
	private String careerMaxW;
	private String careerAnswer;
}
