package com.gyeonglodang.dto;

import lombok.Data;

@Data
public class PassFailDTO {
	private int idx,companyIdx,gonggoidx;
	private String part,fieldidx,pass_text,fail_text;	
}
