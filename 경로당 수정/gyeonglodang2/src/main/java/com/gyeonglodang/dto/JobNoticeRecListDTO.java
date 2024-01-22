package com.gyeonglodang.dto;

import java.util.List;

import lombok.Data;

@Data
public class JobNoticeRecListDTO {

	private JobNotice_boardDTO jobDto;
	private RecruitCateg_boardDTO recDto;
	private List<RecruitCateg_boardDTO> recDtoList;
}
