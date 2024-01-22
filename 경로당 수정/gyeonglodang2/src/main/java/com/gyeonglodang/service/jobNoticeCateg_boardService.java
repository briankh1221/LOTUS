package com.gyeonglodang.service;

import java.util.List;
import java.util.Map;

import com.gyeonglodang.dto.Admininfo_boardDTO;
import com.gyeonglodang.dto.Announcement_boardDTO;
import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.Companyinfo_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_fileDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.RecruitCateg_boardDTO;

public interface jobNoticeCateg_boardService {
	Map<String, Object> boardList(JobNoticeCateg_boardDTO dto);
	Map<String, Object> operStatusList();
	JobNotice_boardDTO boardEditList(JobNotice_boardDTO jobNotice_boardDTO);
	JobNoticeCateg_boardDTO contAndOperAndPostDateView(JobNotice_boardDTO jobNotice_boardDTO);
	List<RecruitCateg_boardDTO> categoryView(JobNotice_boardDTO jobNotice_boardDTO);
	JobNoticeCateg_boardDTO previewList(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
	JobNotice_boardDTO titleView(JobNotice_boardDTO jobNotice_boardDTO);
	Companyinfo_boardDTO companyOneShow(Companyinfo_boardDTO companyinfo_boardDTO);	   
	List<Announcement_boardDTO> announcementBoardList(int companyIdx);
	Announcement_boardDTO announcementBoardEditList(Announcement_boardDTO announcement_boardDTO);
	int companyEdit(Companyinfo_boardDTO companyinfo_boardDTO);
	List<Admininfo_boardDTO> administratorAccountsList(Admininfo_boardDTO admininfo_boardDTO);
	List<Companyinfo_boardDTO> companyList();
	Admininfo_boardDTO adminAccountsEditShowOne(Admininfo_boardDTO admininfo_boardDTO);
	int fileUpload(Map<String, Object> fileUploadmap);
	int imageUpload(Map<String, Object> imageUploadmap);	   
	List<JobNoticeCateg_fileDTO> fileUploadList(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
	List<JobNoticeCateg_fileDTO> imageUploadList(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
	int fileDeleteList(JobNoticeCateg_fileDTO jobNoticeCateg_fileDTO);
	int companyInfoDelete(Companyinfo_boardDTO companyinfo_boardDTO);
	int imageDeleteList(JobNoticeCateg_fileDTO jobNoticeCateg_fileDTO);
	JobNoticeCateg_fileDTO downLoadFile(JobNoticeCateg_fileDTO jobNoticeCateg_fileDTO);
	int boardEditDelete(Announcement_boardDTO announcement_boardDTO); 
	int jobNoticeDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
	int jobNoticeCategDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
	int adminAccountDelete(Admininfo_boardDTO admininfo_boardDTO);
	int fileUploadDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
	int recruitCategDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
	int updateJobNotice_boardDTO(JobNotice_boardDTO jobNotice_boardDTO);
	int updateJobNoticeCateg_boardDTO(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
	int adminAccountUpdate(Admininfo_boardDTO admininfo_boardDTO);
	int saveJobNotice_boardDTO(JobNotice_boardDTO jobNotice_boardDTO);
	int saveJobNoticeCateg_boardDTO(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);   
	int savingBoardEdit(Announcement_boardDTO announcement_boardDTO);
	int companyRegister(Companyinfo_boardDTO companyinfo_boardDTO);
	int boardWrite(Announcement_boardDTO announcement_boardDTO);
	int adminAccountRegister(Admininfo_boardDTO admininfo_boardDTO);
	int getCompanyIdx();
	int jobInsert(JobNotice_boardDTO dto);
	CompanyAccount_boardDTO companyLogoView(CompanyAccount_boardDTO companyAccount_boardDTO);  
	List<CompanyAccount_boardDTO> companyLogoView2();
	CompanyAccount_boardDTO mainImageView(CompanyAccount_boardDTO companyAccount_boardDTO);
	CompanyAccount_boardDTO companyPage(CompanyAccount_boardDTO companyAccount_boardDTO);
	List<JobNotice_boardDTO> dDayCount();

}
