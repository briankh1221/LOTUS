package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalAnswerDTO {
	private int personal_idx;
	private	int applicant_idx;
	private String personalQuestion;
	private String personalMaxW;
	private String personalAnswer;
}
