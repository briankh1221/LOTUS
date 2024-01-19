package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeFileUploadSetDTO {
	private int rsmfileupload_idx;
	private int rsmfile_idx;
	private String rsmfileupload_field;
	private String rsmfileupload_fileName;
	private String rsmfileupload_original;
	private String rsmfileupload_pilsu;
	private String rsmfileupload_filePath;
	
	@Override
	public String toString() {
		return "ResumeFileUploadSetDTO [rsmfileupload_idx=" + rsmfileupload_idx + ", rsmfile_idx=" + rsmfile_idx
				+ ", rsmfileupload_field=" + rsmfileupload_field + ", rsmfileupload_fileName=" + rsmfileupload_fileName
				+ ", original_fileName=" + rsmfileupload_original + ", rsmfileipload_pilsu=" + rsmfileupload_pilsu
				+ ", filePath=" + rsmfileupload_filePath + "]";
	}



}
