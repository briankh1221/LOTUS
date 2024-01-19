package com.gyeonglodang.mybatis.mapper;

import java.util.List;

import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_boardDTO;
import com.gyeonglodang.dto.JobNoticeRecListDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.RecruitCateg_boardDTO;


public interface JobNotice_boardMapper {

	public List<JobNotice_boardDTO> ncSearchList(JobNotice_boardDTO jobDTO);
	
	public int jobInsert(JobNoticeRecListDTO dto);

	public int jobUpdate(JobNoticeRecListDTO dto);
	
	public int jobDelete(JobNoticeRecListDTO dto);
	
	public int getJobIdx(JobNoticeRecListDTO dto);
	
	public int recInsert(JobNoticeRecListDTO dto);
	
	public int recUpdate(JobNoticeRecListDTO dto);
	
	public int recDelete(JobNoticeRecListDTO dto);
	
	public int recDeleteAll(JobNoticeRecListDTO dto);
	
	public int jobCatgDelete(JobNoticeCateg_boardDTO jobCategDTO);      //JobNoticeCateg_boardDTO 삭제
	
	public JobNotice_boardDTO getJobContent(JobNoticeRecListDTO jobRecDTO);
	
	public List<RecruitCateg_boardDTO> getRecContent(JobNoticeRecListDTO jobRecDTO);
	
	public int companyAccount_insert(CompanyAccount_boardDTO dto);
	
	public int companyAccount_update(CompanyAccount_boardDTO dto);
	
	public CompanyAccount_boardDTO companyAccount_getContent(CompanyAccount_boardDTO dto);
}

