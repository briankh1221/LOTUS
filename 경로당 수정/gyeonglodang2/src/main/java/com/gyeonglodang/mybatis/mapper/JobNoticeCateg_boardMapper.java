package com.gyeonglodang.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gyeonglodang.dto.Admininfo_boardDTO;
import com.gyeonglodang.dto.Announcement_boardDTO;
import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.Companyinfo_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_fileDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.RecruitCateg_boardDTO;

@Mapper
public interface JobNoticeCateg_boardMapper {

   public List<JobNotice_boardDTO> boardList(JobNoticeCateg_boardDTO dto);
   
   public List<JobNoticeCateg_boardDTO> operateView(JobNoticeCateg_boardDTO dto);
   
   public JobNotice_boardDTO boardEditList(JobNotice_boardDTO jobNotice_boardDTO);
   
   public JobNoticeCateg_boardDTO contAndOperAndPostDateView(JobNotice_boardDTO jobNotice_boardDTO);
   
   public List<RecruitCateg_boardDTO> categoryView(JobNotice_boardDTO jobNotice_boardDTO);
   
   public JobNoticeCateg_boardDTO previewList(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
   
   public JobNotice_boardDTO titleView(JobNotice_boardDTO jobNotice_boardDTO);
   
   public Companyinfo_boardDTO companyOneShow(Companyinfo_boardDTO companyinfo_boardDTO);
   
   public int companyEdit(Companyinfo_boardDTO companyinfo_boardDTO);
   
   public List<Announcement_boardDTO> announcementBoardList(int companyIdx);
   
   public Announcement_boardDTO announcementBoardEditList(Announcement_boardDTO announcement_boardDTO);
   
   public List<Admininfo_boardDTO> administratorAccountsList(Admininfo_boardDTO admininfo_boardDTO);
   
   public List<JobNotice_boardDTO> jobNoticeList();
   
   public List<Integer> recruitCategList();
   
   public List<Companyinfo_boardDTO> companyinfoList();
      
   public Admininfo_boardDTO adminAccountsEditShowOne(Admininfo_boardDTO admininfo_boardDTO);
   
   public int fileUploaddaoMap(Map<String, Object> fileUploadmapdaoMap);
   
   public int imageUploaddaoMap(Map<String, Object> imageUploaddaoMap);
   
   public List<JobNoticeCateg_fileDTO> fileUploadList(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
   
   public List<JobNoticeCateg_fileDTO> imageUploadList(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
   
   public int fileDeleteList(JobNoticeCateg_fileDTO jobNoticeCateg_fileDTO);
   
   public int imageDeleteList(JobNoticeCateg_fileDTO jobNoticeCateg_fileDTO);
   
   public int companyInfoDelete(Companyinfo_boardDTO companyinfo_boardDTO);
   
   public JobNoticeCateg_fileDTO downLoadFile(JobNoticeCateg_fileDTO jobNoticeCateg_fileDTO);
   
   public int jobNoticeDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
   
   public int jobNoticeCategDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
   
   public int boardEditDelete(Announcement_boardDTO announcement_boardDTO);
   
   public int fileUploadDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
   
   public int recruitCategDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
   
   public int adminAccountDelete(Admininfo_boardDTO admininfo_boardDTO);
   
   public int updateJobNotice_boardDTO(JobNotice_boardDTO jobNotice_boardDTO);
   
   public int updateJobNoticeCateg_boardDTO(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
   
   public int saveJobNotice_boardDTO(JobNotice_boardDTO jobNotice_boardDTO);
   
   public int saveJobNoticeCateg_boardDTO(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO);
   
   public int savingBoardEdit(Announcement_boardDTO announcement_boardDTO);
   
   public int adminAccountUpdate(Admininfo_boardDTO admininfo_boardDTO);
   
   public int boardWrite(Announcement_boardDTO announcement_boardDTO);
   
   public int adminAccountRegister(Admininfo_boardDTO admininfo_boardDTO);
   
   public int companyRegister(Companyinfo_boardDTO companyinfo_boardDT);

   public int getCompanyIdx();

   public int jobInsert(JobNotice_boardDTO dto);
   // 회사 로고 부르기 위한 메서드 *231107 권지현
   public CompanyAccount_boardDTO companyLogoView(CompanyAccount_boardDTO companyAccount_boardDTO);
   
   public List<CompanyAccount_boardDTO> companyLogoView2();
   
   public CompanyAccount_boardDTO mainImageView(CompanyAccount_boardDTO companyAccount_boardDTO);
   
   public CompanyAccount_boardDTO companyPage(CompanyAccount_boardDTO companyAccount_boardDTO);
   
   public List<JobNotice_boardDTO> dDayCount();
            
}


