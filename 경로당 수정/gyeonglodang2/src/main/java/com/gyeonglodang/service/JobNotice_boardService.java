package com.gyeonglodang.service;

import java.util.List;
import java.util.Map;

import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.JobNoticeRecListDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;

public interface JobNotice_boardService {

	List<JobNotice_boardDTO> ncSearchList(JobNotice_boardDTO jobDTO); 	
	int insert(JobNoticeRecListDTO dto);
	int update(JobNoticeRecListDTO dto);
	int deleteNotice(String noticeControl_idx);
	Map<String, Object> getContent(JobNoticeRecListDTO jobRecDTO);
	int companyAccount_insert(CompanyAccount_boardDTO dto);
	int companyAccount_update(CompanyAccount_boardDTO dto);
	CompanyAccount_boardDTO companyAccount_getContent(CompanyAccount_boardDTO dto);
}