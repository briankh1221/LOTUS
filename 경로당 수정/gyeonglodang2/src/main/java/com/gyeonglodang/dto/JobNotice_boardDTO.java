package com.gyeonglodang.dto;

import lombok.Data;

@Data
public class JobNotice_boardDTO {
	
	private String noticeControl_idx;
	private int companyIdx;
	private String jobNotice_idx;
	private String jobNotice_title;
	private String jobNotice_regidate;
	private String jobNotice_operation;
	private String jobNotice_postingDate;
}
