package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeFileDTO {
	private int rsmfile_idx;
	private int gonggoIdx;
	private String rsmfile_title;
	private String rsmfile_explain;
}
