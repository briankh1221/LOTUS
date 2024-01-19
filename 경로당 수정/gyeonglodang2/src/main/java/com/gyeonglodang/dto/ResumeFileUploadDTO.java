package com.gyeonglodang.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeFileUploadDTO {
	private int rsmfileupload_idx;
	private int rsmfile_idx;
	private String rsmfileupload_field;
	private String rsmfileupload_fileName;
	private String original_fileName;
	private String rsmfileupload_pilsu;
	private List<MultipartFile> file;
	private String filePath;
	private String fileName;
	@Override
	public String toString() {
		return "ResumeFileUploadDTO [rsmfileupload_idx=" + rsmfileupload_idx + ", rsmfile_idx=" + rsmfile_idx
				+ ", rsmfileupload_field=" + rsmfileupload_field + ", rsmfileupload_fileName=" + rsmfileupload_fileName
				+ ", original_fileName=" + original_fileName + ", rsmfileupload_pilsu=" + rsmfileupload_pilsu
				+ ", file=" + file + "]";
	}

	
}
