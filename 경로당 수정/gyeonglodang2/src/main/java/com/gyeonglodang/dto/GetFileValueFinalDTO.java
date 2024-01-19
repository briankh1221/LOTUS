package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFileValueFinalDTO {
	private int idx;
	private int gonggoidx;
	private String scode;
	private String applicant_file_Original;
	private String applicant_file_filePath;
}
