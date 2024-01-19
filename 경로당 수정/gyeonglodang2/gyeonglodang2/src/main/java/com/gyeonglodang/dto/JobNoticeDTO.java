package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobNoticeDTO {
	private int noticeControl_idx;
	private int companyidx;
	private String jobNotice_title;
	private String jobNotice_regidate;
	private String jobNotice_operation;
	private String jobNotice_postingDate;
	private String recruitCateg_categ;
}
