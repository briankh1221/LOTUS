package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CareerQDTO {
	private int idx;
	private int careerIdx;
	private String careerQuestion;
	private String careerMinW;
	private String careerMaxW;
	
	@Override
	public String toString() {
		return "CareerQDTO [idx=" + idx + ", careerIdx=" + careerIdx + ", careerQuestion=" + careerQuestion
				+ ", careerMinW=" + careerMinW + ", careerMaxW=" + careerMaxW + "]";
	}
}
