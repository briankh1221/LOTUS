package com.gyeonglodang.dto;

import lombok.Data;

@Data
public class SuccessfulCandidateListDTO {
	int idx,companyIdx,gonggoidx;
	String name,pass_fail,fieldidx,scode; 
}
