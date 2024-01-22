package com.gyeonglodang.dto;

import lombok.Data;

@Data
public class AnnouncementCustomDTO {
	int idx, gonggoidx;
	int companyIdx;
	String announcement_custom_title,context,name,part,fieldidx,scode;
}
