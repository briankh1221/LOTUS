package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RsmFileDTO {
	private int rsmfile_idx;
	private int idx;
	private String rsmfile_title;
	private String rsmfile_explain;
}
