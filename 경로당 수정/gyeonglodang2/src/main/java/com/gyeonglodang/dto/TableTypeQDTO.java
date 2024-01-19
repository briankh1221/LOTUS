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
public class TableTypeQDTO {
	private int idx;
	private int tableTypeIdx;
	private String tableCategory;
	private String tableNotice;
}
