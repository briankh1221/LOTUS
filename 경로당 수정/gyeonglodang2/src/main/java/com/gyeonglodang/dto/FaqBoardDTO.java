package com.gyeonglodang.dto;

import lombok.Data;

@Data
public class FaqBoardDTO {
	
	private Integer faq_idx;
	private Integer companyIdx;
	private String faq_board_title;
	private String faq_board_context;
	
}
