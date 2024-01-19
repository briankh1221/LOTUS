package com.gyeonglodang.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_boardDTO;
import com.gyeonglodang.dto.JobNoticeRecListDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.RecruitCateg_boardDTO;
import com.gyeonglodang.mybatis.mapper.JobNotice_boardMapper;
import com.gyeonglodang.service.JobNotice_boardService;


@Service
public class JobNotice_boardServiceImpl implements JobNotice_boardService {

	@Autowired
	JobNotice_boardMapper dao;
	@Override
	public List<JobNotice_boardDTO> ncSearchList(JobNotice_boardDTO jobDTO) {
		
		return dao.ncSearchList(jobDTO);
	}
	@Override
	public int insert(JobNoticeRecListDTO dto) {
		int jobRs = dao.jobInsert(dto);
		
		String noticeControl_idx = Integer.toString(dao.getJobIdx(dto)); 
		
		int recRs = 0;
		
		for(int i=0; i<dto.getRecDtoList().size(); i++) {
			JobNoticeRecListDTO forRecDto = new JobNoticeRecListDTO();
			forRecDto.setJobDto(new JobNotice_boardDTO());
			forRecDto.getJobDto().setNoticeControl_idx(noticeControl_idx);;
			forRecDto.getJobDto().setCompanyIdx(dto.getJobDto().getCompanyIdx());;
			forRecDto.setRecDto(dto.getRecDtoList().get(i));
			recRs += dao.recInsert(forRecDto);
		}
		return jobRs + recRs;
	}
	@Override
	public int update(JobNoticeRecListDTO dto) {
		int jobRs = dao.jobUpdate(dto);
		int recRs = 0;
		for(int i=0; i<dto.getRecDtoList().size(); i++) {
			String recruitCateg_idx = dto.getRecDtoList().get(i).getRecruitCateg_idx();
			
			if(dto.getRecDtoList().get(i).getRecruitCateg_show().equals("y")) {
				if(dto.getRecDtoList().get(i).getRecruitCateg_status_change().equals("update")) {
					JobNoticeRecListDTO recDto = new JobNoticeRecListDTO();
					recDto.setRecDto(dto.getRecDtoList().get(i));
					jobRs += dao.recUpdate(recDto);
				}
				else if(dto.getRecDtoList().get(i).getRecruitCateg_status_change().equals("c")) {
					JobNoticeRecListDTO recDto = new JobNoticeRecListDTO();
					recDto.setRecDto(dto.getRecDtoList().get(i));
					JobNotice_boardDTO jobDto = new JobNotice_boardDTO();
					jobDto.setNoticeControl_idx(dto.getJobDto().getNoticeControl_idx());
					jobDto.setCompanyIdx(dto.getJobDto().getCompanyIdx());
					recDto.setJobDto(jobDto);
					jobRs += dao.recInsert(recDto);
				}
			}
			else if(dto.getRecDtoList().get(i).getRecruitCateg_status_change().equals("delete")) {
				if(dto.getRecDtoList().get(i).getRecruitCateg_idx() != null) {
					JobNoticeRecListDTO recDto = new JobNoticeRecListDTO();
					recDto.setRecDto(dto.getRecDtoList().get(i));
					jobRs += dao.recDelete(recDto);
				}
			}
			
		}
		return jobRs + recRs;
	}
	@Override
	public int deleteNotice(String noticeControl_idx) {
		JobNoticeRecListDTO jobRecDto = new JobNoticeRecListDTO();
		jobRecDto.setJobDto(new JobNotice_boardDTO());
		jobRecDto.setRecDto(new RecruitCateg_boardDTO());
		jobRecDto.getJobDto().setNoticeControl_idx(noticeControl_idx);
		jobRecDto.getRecDto().setNoticeControl_idx(noticeControl_idx);
		
		dao.jobDelete(jobRecDto);
		dao.recDeleteAll(jobRecDto);
		
		JobNoticeCateg_boardDTO jobCategDTO = new JobNoticeCateg_boardDTO();
		jobCategDTO.setNoticeControl_idx(noticeControl_idx);
		dao.jobCatgDelete(jobCategDTO);
		
		return 1;
	}
	@Override
	public Map<String, Object> getContent(JobNoticeRecListDTO jobRecDTO) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("jobContent", dao.getJobContent(jobRecDTO));
		map.put("recContent", dao.getRecContent(jobRecDTO));
		
		return map;
	}
	@Override
	public int companyAccount_insert(CompanyAccount_boardDTO dto) {
		
		int rs = dao.companyAccount_insert(dto);
		
		return rs;
	}
	@Override
	public int companyAccount_update(CompanyAccount_boardDTO dto) {
		
		int rs = dao.companyAccount_update(dto);
		
		return rs;
	}
	@Override
	public CompanyAccount_boardDTO companyAccount_getContent(CompanyAccount_boardDTO dto) {
		CompanyAccount_boardDTO contentDto = dao.companyAccount_getContent(dto);
		
		return contentDto;
	}	
}
