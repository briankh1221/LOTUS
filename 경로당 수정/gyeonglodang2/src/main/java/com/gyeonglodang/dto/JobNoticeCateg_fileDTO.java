package com.gyeonglodang.dto;

import lombok.Data;

@Data
public class JobNoticeCateg_fileDTO {
	
	private int idx;
	private int fileUpload_idx;	
	private int noticeControl_idx;
	private String fileName;
	private String filePath;

}
