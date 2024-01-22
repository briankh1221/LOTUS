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
public class PersonalDTO {
	private int idx;
	private int gonggoIdx;
	private String personalTitle;
	private int personalField;
	private String personalNotice;
}
