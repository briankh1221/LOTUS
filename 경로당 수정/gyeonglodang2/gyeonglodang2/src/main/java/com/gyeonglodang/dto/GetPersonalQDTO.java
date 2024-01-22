package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPersonalQDTO {
	private int personalidx;
	private String personalQuestion;
	private String personalMinW;
	private String personalMaxW;

}
