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
public class CareerDTO {
	private int idx;
	private int gonggoIdx;
	private String careerTitle;
	private int careerField;
	private String careerNotice;
	private int activate;
}
