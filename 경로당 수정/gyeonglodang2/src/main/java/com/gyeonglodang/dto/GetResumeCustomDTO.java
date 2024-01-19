package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetResumeCustomDTO {
	private int idx;
	private int gonggoidx;
	private String customTitle;
	private String customField;
	private String customNotice;
}
