package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCareerQDTO {
	private int careeridx;
	private String careerQuestion;
	private int careerMinW;
	private String careerMaxW;

}
