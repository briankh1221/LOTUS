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
public class PersonalQDTO {
	private int idx;
	private int personalIdx;
	private String personalQuestion;
	private String personalMinW;
	private String personalMaxW;
}
