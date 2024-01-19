package com.gyeonglodang.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.Companyinfo_boardDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.RecruitDTO;

@Mapper
public interface RecruitMapper {
	public List<RecruitDTO> getConfirm(Map<String,Object> map);
	
	public String getCustomContext(Map<String,Object> map);
	
	public List<Map<String, Object>> getDate(int gonggoidx);
	
	public List<JobNotice_boardDTO> getJncList();
   
	public List<Companyinfo_boardDTO> getCompanyInfo();
   
	public List<JobNotice_boardDTO> dDayCount();
	
	public List<CompanyAccount_boardDTO> getCompanyLogo();
	
	public List<Map<String, Object>> getGonggo(int companyIdx);
	   
	public int getGonggoidx(String title);
}
