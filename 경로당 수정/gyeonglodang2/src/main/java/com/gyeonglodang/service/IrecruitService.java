package com.gyeonglodang.service;

import java.util.List;
import java.util.Map;

import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.Companyinfo_boardDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.RecruitDTO;

public interface IrecruitService {
//	email, password, scode가 묶여있는 confirm 테이블에서 email을통해
//	값들을 가져옴
	List<RecruitDTO> getConfirm(Map<String,Object> map);
	
//	announcement_applicant테이블에 announcement_custom을 조인해서
//	조건으로 문구제목이 같은경우를 넣어서 두테이블을 연결
//	거기에 전형과 수험번호를 조건으로 넣어서 해당하는 커스텀 문구를 가져옴
	String getCustomContext(Map<String,Object> map);
	
//	공고번호로 모든 전형의 기록되있는 발표날짜 설정들을 가져옴
	List<Map<String,Object>> getDate(int gonggoidx);
	
	List<JobNotice_boardDTO> getJncList();
	   
	List<Companyinfo_boardDTO> getCompanyInfo();
	
	List<CompanyAccount_boardDTO> getCompanyLogo();
	
	List<JobNotice_boardDTO> dDayCount();
	
	List<Map<String,Object>> getGonggo(int companyIdx);
	   
	int getGonggoidx(String title);
}