package com.gyeonglodang.serviceimpl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.Companyinfo_boardDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.RecruitDTO;
import com.gyeonglodang.mybatis.mapper.RecruitMapper;
import com.gyeonglodang.service.IrecruitService;

@Service
public class RecruitServiceImpl implements IrecruitService {
	
	@Autowired
	RecruitMapper mapper;
	
	@Override
	public List<RecruitDTO> getConfirm(Map<String,Object> map) {
		
		return mapper.getConfirm(map);
	}
	@Override
	public String getCustomContext(Map<String,Object> map) {
		
		return mapper.getCustomContext(map);
	}
	@Override
	public List<Map<String, Object>> getDate(int gonggoidx) {
		
		return mapper.getDate(gonggoidx);
   }
   @Override
   public List<JobNotice_boardDTO> getJncList() {
	   
	   return mapper.getJncList();
   }
   @Override
   public List<Companyinfo_boardDTO> getCompanyInfo() {
	   
	   return mapper.getCompanyInfo();
   }
   @Override
   public List<CompanyAccount_boardDTO> getCompanyLogo() {
	   
	   return mapper.getCompanyLogo();
   }
   @Override
   public List<JobNotice_boardDTO> dDayCount() {
	   
	   return mapper.dDayCount();
   }
   @Override
   public List<Map<String, Object>> getGonggo(int companyIdx) {
      
	   return mapper.getGonggo(companyIdx);
   }
   @Override
   public int getGonggoidx(String title) {
      
	   return mapper.getGonggoidx(title);
   }
}
