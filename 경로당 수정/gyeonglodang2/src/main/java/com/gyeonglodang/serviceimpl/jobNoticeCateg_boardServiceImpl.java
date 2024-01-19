package com.gyeonglodang.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyeonglodang.dto.Admininfo_boardDTO;
import com.gyeonglodang.dto.Announcement_boardDTO;
import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.Companyinfo_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_fileDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.RecruitCateg_boardDTO;
import com.gyeonglodang.mybatis.mapper.JobNoticeCateg_boardMapper;
import com.gyeonglodang.service.jobNoticeCateg_boardService;

@Service
public class jobNoticeCateg_boardServiceImpl implements jobNoticeCateg_boardService {

   @Autowired
   JobNoticeCateg_boardMapper dao;
   
   @Override
   public Map<String, Object> boardList(JobNoticeCateg_boardDTO dto) {   
	  // 채용공고 게시물 목록 불러와야 함 *231103 권지현
      List<JobNotice_boardDTO> boardList = dao.boardList(dto);
      // 채용공고 게시물 목록의 게시기간이 아니라 채용공고 분야 게시기간을 불러와야 함 *231103 권지현
      List<JobNoticeCateg_boardDTO> operationList = dao.operateView(dto); 
      
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("boardList", boardList);
      map.put("operationList", operationList);      
      
      return map;
   }
   @Override
   public Map<String, Object> operStatusList() {   
      
      List<JobNotice_boardDTO> jobNoticeList = dao.jobNoticeList();
      List<Integer> recruitCategList = dao.recruitCategList();
      List<Companyinfo_boardDTO> companyinfoList = dao.companyinfoList();
            
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("jobNoticeList", jobNoticeList);
      map.put("recruitCategList", recruitCategList);      
      map.put("companyinfoList", companyinfoList);      
      
      return map;
   }
   @Override
   public JobNotice_boardDTO boardEditList(JobNotice_boardDTO jobNotice_boardDTO) {
      
      return dao.boardEditList(jobNotice_boardDTO);
   }
   @Override
   public JobNoticeCateg_boardDTO contAndOperAndPostDateView(JobNotice_boardDTO jobNotice_boardDTO) {
      
      return dao.contAndOperAndPostDateView(jobNotice_boardDTO);
   }
   @Override
   public List<RecruitCateg_boardDTO> categoryView(JobNotice_boardDTO jobNotice_boardDTO) {
      
      return dao.categoryView(jobNotice_boardDTO);
   }
   @Override
   public JobNoticeCateg_boardDTO previewList(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
      
      return dao.previewList(jobNoticeCateg_boardDTO);
   }
   @Override
   public JobNotice_boardDTO titleView(JobNotice_boardDTO jobNotice_boardDTO) {
      
      return dao.titleView(jobNotice_boardDTO);
   }
   @Override
   public Companyinfo_boardDTO companyOneShow(Companyinfo_boardDTO companyinfo_boardDTO) {
      
      return dao.companyOneShow(companyinfo_boardDTO);
   }
   @Override
   public List<Announcement_boardDTO> announcementBoardList(int companyIdx) {
      
      return dao.announcementBoardList(companyIdx);
   }
   @Override
   public Announcement_boardDTO announcementBoardEditList(Announcement_boardDTO announcement_boardDTO) {
      
      return dao.announcementBoardEditList(announcement_boardDTO);
   }
   @Override
   public int companyEdit(Companyinfo_boardDTO companyinfo_boardDTO) {
      
      return dao.companyEdit(companyinfo_boardDTO);
   }
   @Override
   public List<Admininfo_boardDTO> administratorAccountsList(Admininfo_boardDTO admininfo_boardDTO) {
      
      return dao.administratorAccountsList(admininfo_boardDTO);
   }
   @Override
   public List<Companyinfo_boardDTO> companyList() {
      
      return dao.companyinfoList();
   }
   @Override
   public Admininfo_boardDTO adminAccountsEditShowOne(Admininfo_boardDTO admininfo_boardDTO) {
      
      return dao.adminAccountsEditShowOne(admininfo_boardDTO);
   }
   @Override
   public int fileUpload(Map<String, Object> fileUploadmap) {
      
      int rs = 0;
      
      for(int i = 0; i < ((List<String>) fileUploadmap.get("nameList")).size(); i++) {
         Map<String, Object> fileUploadmapdaoMap = new HashMap<>();
         fileUploadmapdaoMap.put("fileName", ((List<String>) fileUploadmap.get("nameList")).get(i));
         fileUploadmapdaoMap.put("filePath", (String) fileUploadmap.get("uploadPath"));
         fileUploadmapdaoMap.put("noticeControl_idx", (int) fileUploadmap.get("noticeControl_idx"));
         rs += dao.fileUploaddaoMap(fileUploadmapdaoMap);      
      }
      return rs;
   };
   @Override
   public int imageUpload(Map<String, Object> imageUploadmap) {
      
      int rs = 0;
      
      for(int i = 0; i < ((List<String>) imageUploadmap.get("nameList")).size(); i++) {
         Map<String, Object> imageUploaddaoMap = new HashMap<>();
         imageUploaddaoMap.put("fileName", ((List<String>) imageUploadmap.get("nameList")).get(i));
         imageUploaddaoMap.put("filePath", (String) imageUploadmap.get("uploadPath"));
         imageUploaddaoMap.put("noticeControl_idx", (int) imageUploadmap.get("noticeControl_idx"));
         rs += dao.imageUploaddaoMap(imageUploaddaoMap);
      }
      return rs;
   };
   @Override
   public List<JobNoticeCateg_fileDTO> fileUploadList(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
      
      return dao.fileUploadList(jobNoticeCateg_boardDTO);
   };
   @Override
   public List<JobNoticeCateg_fileDTO> imageUploadList(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
      
      return dao.imageUploadList(jobNoticeCateg_boardDTO);
   };
   @Override
   public int fileDeleteList(JobNoticeCateg_fileDTO jobNoticeCateg_fileDTO) {
      
      return dao.fileDeleteList(jobNoticeCateg_fileDTO);
   };
   @Override
   public int companyInfoDelete(Companyinfo_boardDTO companyinfo_boardDTO) {
      
      return dao.companyInfoDelete(companyinfo_boardDTO);
   };
   @Override
   public int imageDeleteList(JobNoticeCateg_fileDTO jobNoticeCateg_fileDTO) {
         
         return dao.imageDeleteList(jobNoticeCateg_fileDTO);
   };
   @Override
   public JobNoticeCateg_fileDTO downLoadFile(JobNoticeCateg_fileDTO jobNoticeCateg_fileDTO) {
      
      return dao.downLoadFile(jobNoticeCateg_fileDTO);
   };
   @Override
   public int boardEditDelete(Announcement_boardDTO announcement_boardDTO) {
      
      return dao.boardEditDelete(announcement_boardDTO);
   };
   @Override
   public int jobNoticeDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
      
      return dao.jobNoticeDelete(jobNoticeCateg_boardDTO);
   };
   @Override
   public int jobNoticeCategDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
      
      return dao.jobNoticeCategDelete(jobNoticeCateg_boardDTO);
   };
   @Override
   public int adminAccountDelete(Admininfo_boardDTO admininfo_boardDTO) {
      
      return dao.adminAccountDelete(admininfo_boardDTO);
   };
   @Override
   public int fileUploadDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
      
      return dao.jobNoticeCategDelete(jobNoticeCateg_boardDTO);
   };
   @Override
   public int recruitCategDelete(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
      
      return dao.recruitCategDelete(jobNoticeCateg_boardDTO);
   };
   
   public int updateJobNotice_boardDTO(JobNotice_boardDTO jobNotice_boardDTO) {
      
      return dao.updateJobNotice_boardDTO(jobNotice_boardDTO);
   };
   @Override
   public int updateJobNoticeCateg_boardDTO(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
      
      return dao.updateJobNoticeCateg_boardDTO(jobNoticeCateg_boardDTO);
   };
   @Override
   public int adminAccountUpdate(Admininfo_boardDTO admininfo_boardDTO) {
      
      return dao.adminAccountUpdate(admininfo_boardDTO);
   };
   @Override
   public int saveJobNotice_boardDTO(JobNotice_boardDTO jobNotice_boardDTO) {
      
      return dao.saveJobNotice_boardDTO(jobNotice_boardDTO);
   };
   @Override
   public int saveJobNoticeCateg_boardDTO(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
      
      return dao.saveJobNoticeCateg_boardDTO(jobNoticeCateg_boardDTO);
   };   
   @Override
   public int savingBoardEdit(Announcement_boardDTO announcement_boardDTO) {
      
      return dao.savingBoardEdit(announcement_boardDTO);
   };
   @Override
   public int companyRegister(Companyinfo_boardDTO companyinfo_boardDTO) {
      
      return dao.companyRegister(companyinfo_boardDTO);
   };
   @Override
   public int boardWrite(Announcement_boardDTO announcement_boardDTO) {
      
      return dao.boardWrite(announcement_boardDTO);
   };
   @Override
   public int adminAccountRegister(Admininfo_boardDTO admininfo_boardDTO) {
      
      return dao.adminAccountRegister(admininfo_boardDTO);
   };
   @Override
   public int getCompanyIdx() {
      
      return dao.getCompanyIdx();
   };
   @Override
   public int jobInsert(JobNotice_boardDTO dto) {
      
      return dao.jobInsert(dto);
   };
   
   @Override
   public CompanyAccount_boardDTO companyLogoView(CompanyAccount_boardDTO companyAccount_boardDTO) {
      
      return dao.companyLogoView(companyAccount_boardDTO);
   }
   @Override
   public List<CompanyAccount_boardDTO> companyLogoView2() {
      
      return dao.companyLogoView2();
   }
   @Override
   public CompanyAccount_boardDTO mainImageView(CompanyAccount_boardDTO companyAccount_boardDTO) {
      
      return dao.mainImageView(companyAccount_boardDTO);
   }
   @Override
   public CompanyAccount_boardDTO companyPage(CompanyAccount_boardDTO companyAccount_boardDTO) {
      
      return dao.companyPage(companyAccount_boardDTO);
   }
   @Override
   public List<JobNotice_boardDTO> dDayCount() {
      
      return dao.dDayCount();
   }
}