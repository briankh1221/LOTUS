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
public class InfoCustomDTO {
	private int idx;
	private int gonggoIdx;
	private String infoCustomField;
	private String infoCustomCategory;
	private String infoCustomNotice;
	private String infoCustomPilsu;
}
