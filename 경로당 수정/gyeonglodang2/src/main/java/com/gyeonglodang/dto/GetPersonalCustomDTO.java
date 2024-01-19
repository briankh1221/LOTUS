package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPersonalCustomDTO {
	private int gongoidx;
	private String infoCustomCategory;
	private String infoCustomNotice;
	private String infoCustomField;
	private String infoCustomPilsu;
}
