package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTableTypeQDTO {
	private int idx;
	private int tableTypeidx;
	private String tableCategory;
	private String tableNotice;
}
