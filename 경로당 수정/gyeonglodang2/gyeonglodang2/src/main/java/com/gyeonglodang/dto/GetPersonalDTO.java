package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPersonalDTO {
	private int idx;
	private int gonggoidx;
	private String personalTitle;
	private String personalField;
	private String personalNotice;

}
