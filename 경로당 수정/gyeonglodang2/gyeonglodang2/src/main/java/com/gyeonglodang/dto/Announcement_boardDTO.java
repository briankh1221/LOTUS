package com.gyeonglodang.dto;

import lombok.Data;

@Data
public class Announcement_boardDTO {
	   private int companyIdx;
	   private int announcement_no;
	   private String announcement_category;
	   private String announcement_title;
	   private String announcement_postingDate;
	   private String announcement_operation;
	   private String announcement_content;
}


