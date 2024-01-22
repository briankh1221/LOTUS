package com.gyeonglodang.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gyeonglodang.dto.Admininfo_boardDTO;
import com.gyeonglodang.dto.Announcement_boardDTO;
import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.Companyinfo_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_fileDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.RecruitCateg_boardDTO;
import com.gyeonglodang.serviceimpl.jobNoticeCateg_boardServiceImpl;

@RestController
@RequestMapping("/notice")
public class JobNoticeCategJasonController {
   
   @Autowired
   jobNoticeCateg_boardServiceImpl service;
   
   // jobNoticeCateg_bored.jsp에서 AJAX 실행시킴. 채용 공고 게시물 목록을 부르기 위한 메서드 *231111 권지현
   @RequestMapping(value = "/jobNotice_boardList2") 
   public Map<String, Object> jobNotice_boardList (JobNoticeCateg_boardDTO dto, HttpSession session) {
      
      int companyIdx = (int)session.getAttribute("companyIdx");
      dto.setCompanyIdx(companyIdx);
      
      Map<String, Object> map = new HashMap<String, Object>();
      map = service.boardList(dto);
      
      return map;
   }
   
   @RequestMapping(value = "/homePageMove") 
   public String homePageMove (HttpSession session) {
      
      String companyIdx = (Integer.toString((int)session.getAttribute("companyIdx")));
      
      return companyIdx;
   }
   
   @RequestMapping(value = "/companyPage") 
      public CompanyAccount_boardDTO companyPage (CompanyAccount_boardDTO companyAccount_boardDTO, HttpSession session) {
         
         
         int companyIdx = (int)session.getAttribute("companyIdx");
         
         companyAccount_boardDTO.setCompanyIdx(companyIdx);
         
         CompanyAccount_boardDTO boardDTO = service.companyPage(companyAccount_boardDTO);
         String companyUrl = boardDTO.getCompanyUrl();
         if(!(companyUrl.substring(0, 4).equals("http"))) {
            companyUrl = "http://" + companyUrl;
            boardDTO.setCompanyUrl(companyUrl);
         }
         
         return boardDTO;
      }
   
   @RequestMapping(value = "/operStatusList") 
   public Map<String, Object> operStatusList() {      
      
      Map<String, Object> map = new HashMap<String, Object>();
      map = service.operStatusList();
      
      return map;
   }   
   // jobNoticeCateg_boardEdit.jsp에서 AJAX 실행시킴. 해당 공고 게시물을 부르기 위한 메서드 *231110 권지현   
   @RequestMapping(value = "/jobNoticeCateg_boardEditList") 
   public JobNotice_boardDTO jobNoticeCateg_boardEditList (JobNotice_boardDTO jobNotice_boardDTO) {   
      
      JobNotice_boardDTO boardDTO = service.boardEditList(jobNotice_boardDTO);
      
      return boardDTO;
   }
   // jobNoticeCateg_boardEdit.jsp에서 AJAX 실행시킴. 공고 게시물 게시기간, 게시여부, 내용을 부르기 위한 메서드 *231110 권지현
   @RequestMapping(value = "/jobNoticeCateg_contAndOperAndPostDateView") 
   public JobNoticeCateg_boardDTO jobNoticeCateg_contAndOperAndPostDateView (JobNotice_boardDTO jobNotice_boardDTO) {   
      
      JobNoticeCateg_boardDTO boardDTO = service.contAndOperAndPostDateView(jobNotice_boardDTO);
            
      return boardDTO;
   }
   // jobNoticeCateg_boardEdit.jsp에서 AJAX 실행시킴. 공고 게시물 채용분야를 부르기 위한 메서드 *231110 권지현
   @RequestMapping(value = "/jobNoticeCateg_categoryView") 
   public List<RecruitCateg_boardDTO> jobNoticeCateg_categoryView(JobNotice_boardDTO jobNotice_boardDTO) {   
      
      List<RecruitCateg_boardDTO> boardDTO = service.categoryView(jobNotice_boardDTO);
      
      return boardDTO;
   }
   // jobNoticeCateg_boardEdit_preview.jsp에서 AJAX 실행시킴. 채용공고 게시물 접수기간, 내용 미리보기 부르기 위한 메서드 *231110 권지현
   @RequestMapping(value = "/jobNoticeCateg_boardEdit_previewList") 
   public JobNoticeCateg_boardDTO jobNoticeCateg_boardEdit_previewList(JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {   
      
      JobNoticeCateg_boardDTO boardDTO = service.previewList(jobNoticeCateg_boardDTO);  
      
      return boardDTO;
   }
   // jobNoticeCateg_boardEdit_preview.jsp에서 AJAX 실행시킴. 채용공고 게시물 제목 부르기 위한 메서드 *231110 권지현
   @RequestMapping(value = "/jobNoticeCateg_titleView") 
   public JobNotice_boardDTO jobNoticeCateg_titleView(JobNotice_boardDTO jobNotice_boardDTO) {   
      
      JobNotice_boardDTO boardDTO = service.titleView(jobNotice_boardDTO);
      
      return boardDTO;
   }
   
   @RequestMapping(value = "/announcementBoardList") 
   public List<Announcement_boardDTO> announcementBoardList(HttpSession session) {   
      
      int companyIdx =(int)session.getAttribute("companyIdx");
      
      List<Announcement_boardDTO> boardDTO = service.announcementBoardList(companyIdx);
      
      return boardDTO;
   }
   
   @RequestMapping(value = "/announcement_boardEditList") 
   public Announcement_boardDTO announcement_boardEditList(Announcement_boardDTO announcement_boardDTO) {   
      
      Announcement_boardDTO boardDTO = service.announcementBoardEditList(announcement_boardDTO);
      
      return boardDTO;
   }
   // administratorAccounts.jsp에서 AJAX 실행시킴. 운영자 목록을 부르기 위한 메서드 *231110 권지현
   @RequestMapping(value = "/administratorAccountsList") 
   public List<Admininfo_boardDTO> administratorAccountsList(HttpSession session) {   
      Admininfo_boardDTO admininfo_boardDTO = new Admininfo_boardDTO();
      admininfo_boardDTO.setCompanyIdx((int)session.getAttribute("companyIdx"));
      List<Admininfo_boardDTO> boardDTO = service.administratorAccountsList(admininfo_boardDTO);

      return boardDTO;
   }
   // administratorAccounts.jsp에서 AJAX 실행시킴. 운영자 목록 수정하기 위한 메서드 *231110 권지현
   @RequestMapping(value = "/adminAccountsEditShowOne") 
   public Admininfo_boardDTO adminAccountsEditShowOne(Admininfo_boardDTO admininfo_boardDTO) {   
      
      Admininfo_boardDTO boardDTO = service.adminAccountsEditShowOne(admininfo_boardDTO);
   
      return boardDTO;
   }   
   
   @RequestMapping(value = "/companyOneShow") 
   public Companyinfo_boardDTO companyOneShow(Companyinfo_boardDTO companyinfo_boardDTO) {   
      
      Companyinfo_boardDTO boardDTO = service.companyOneShow(companyinfo_boardDTO);
      
      return boardDTO;
   }   
   
   @RequestMapping(value = "/companyList") 
   public List<Companyinfo_boardDTO> companyList( ) {   
      
      List<Companyinfo_boardDTO> boardDTO = service.companyList();
      
      return boardDTO;
   }   
   // jobNoticeCateg_boardEdit.jsp에서 AJAX 실행시킴. 첨부파일 업로드 하기 위한 메서드 *231110 권지현  
   @RequestMapping(value = "/jobNoticeCateg_fileUpload") 
   public int fileUpload (@RequestParam ArrayList<MultipartFile> files, @RequestParam("noticeControl_idx") int noticeControl_idx) throws Exception{
      
      String uploadPath = "/Users/dpqbw/Desktop/k-digital/spring2/springws/gyeonglodang/src/main/webapp/resources/uploadfile";  
      
      for(MultipartFile mutipartFile : files) {
         File saveFile = new File(uploadPath, mutipartFile.getOriginalFilename());
         mutipartFile.transferTo(saveFile);
      }
      
      Map<String, Object> fileUploadmap = new HashMap<>();
      List<String> nameList = new ArrayList<>();
      
      for(int i=0; i<files.size(); i++) {
         nameList.add(files.get(i).getOriginalFilename());
      }
      
      fileUploadmap.put("uploadPath", uploadPath);
      fileUploadmap.put("nameList", nameList);
      fileUploadmap.put("noticeControl_idx", noticeControl_idx);
      
      int rs = service.fileUpload(fileUploadmap);
         
      return rs;
   }
   // jobNoticeCateg_boardEdit.jsp에서 AJAX 실행시킴. 이미지파일 업로드 하기 위한 메서드 *231110 권지현
   @RequestMapping(value = "/jobNoticeCateg_imageUpload") 
   public int imageUpload (@RequestParam ArrayList<MultipartFile> files, @RequestParam("noticeControl_idx") int noticeControl_idx) throws Exception{
      
      String uploadPath = "/Users/dpqbw/Desktop/k-digital/spring2/springws/gyeonglodang/src/main/webapp/resources/uploadfile";  
      
      for(MultipartFile mutipartFile : files) {
         File saveFile = new File(uploadPath, mutipartFile.getOriginalFilename());
         mutipartFile.transferTo(saveFile);
      }
      
      Map<String, Object> imageUploadmap = new HashMap<>();
      List<String> nameList = new ArrayList<>();
      
      for(int i=0; i<files.size(); i++) {
         nameList.add(files.get(i).getOriginalFilename());
      }
      
      imageUploadmap.put("uploadPath", uploadPath);
      imageUploadmap.put("nameList", nameList);
      imageUploadmap.put("noticeControl_idx", noticeControl_idx);
      
      int rs = service.imageUpload(imageUploadmap);
               
      return rs;
   }
   /* jobNoticeCateg_boardEdit_preview.jsp에서 AJAX 실행시킴
    * jobNoticeCateg_boardEdit.jsp에서 AJAX 실행시킴. 첨부파일 업로드 리스트 부르기 위한 메서드 *231110 권지현
    */
   @RequestMapping(value = "/jobNoticeCateg_fileUploadList") 
   public List<JobNoticeCateg_fileDTO> fileUploadList (JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
         
      List<JobNoticeCateg_fileDTO> fileDTO = service.fileUploadList(jobNoticeCateg_boardDTO);
      
      return fileDTO;
   }
   /* jobNoticeCateg_boardEdit_preview.jsp에서 AJAX 실행시킴	
    * jobNoticeCateg_boardEdit.jsp에서 AJAX 실행시킴. 이미지파일 업로드 리스트 부르기 위한 메서드 *231110 권지현
    */
   @RequestMapping(value = "/jobNoticeCateg_imageUploadList") 
   public List<JobNoticeCateg_fileDTO> imageUploadList (JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
      
      List<JobNoticeCateg_fileDTO> fileDTO = service.imageUploadList(jobNoticeCateg_boardDTO);
      
      return fileDTO;
   }
   // jobNoticeCateg_boardEdit.jsp에서 AJAX 실행시킴. 첨부 파일 삭제하기 위한 메서드 *231110 권지현
   @RequestMapping(value = "/jobNoticeCateg_fileDeleteList") 
   public int fileDeleteList (JobNoticeCateg_fileDTO jobNoticeCateg_fileDTO) {
         
      int fileDTO = service.fileDeleteList(jobNoticeCateg_fileDTO);
      
      return fileDTO;
   }
   // jobNoticeCateg_boardEdit.jsp에서 AJAX 실행시킴. 이미지 파일 삭제하기 위한 메서드 *231110 권지현
   @RequestMapping(value = "/jobNoticeCateg_imageDeleteList") 
   public int imageDeleteList (JobNoticeCateg_fileDTO jobNoticeCateg_fileDTO) {
         
      int fileDTO = service.imageDeleteList(jobNoticeCateg_fileDTO);
      
      return fileDTO;
   }
   
   @RequestMapping(value = "/downloadFile") 
   @ResponseBody
   public ResponseEntity<byte[]> downloadFile(@RequestParam String fileName) throws IOException {
      
      String filePath = "D:\\kdigital2307\\spring\\springws\\gyeonglodang\\src\\main\\webapp\\resources\\";
      
      File file = new File(filePath + fileName);
       byte[] fileContent = Files.readAllBytes(file.toPath());
      
      
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

       String encodedFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
       headers.setContentDispositionFormData("attachment", encodedFileName);

       return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
      
   }
   
   @RequestMapping(value = "/jobNoticeCateg_boardDelete") 
   public int boardDelete (@ModelAttribute  JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO) {
         
      int rs1 = service.jobNoticeCategDelete(jobNoticeCateg_boardDTO);
      int rs2 = service.fileUploadDelete(jobNoticeCateg_boardDTO);
      int rs3 = service.recruitCategDelete(jobNoticeCateg_boardDTO);
      int rs4 = service.jobNoticeDelete(jobNoticeCateg_boardDTO);
      
      return rs1+rs2+rs3+rs4;
   }
   // administratorAccounts.jsp에서 AJAX 실행시킴. 운여자 목록 삭제하기 위한 메서드 *231110 권지현
   @RequestMapping(value = "/announcement_boardEditDelete") 
   public int boardEditDelete (Announcement_boardDTO announcement_boardDTO) {
      
      int rs = service.boardEditDelete(announcement_boardDTO);
      
      return rs;
   }
   
   @RequestMapping(value = "/adminAccountDelete") 
   public int adminAccountDelete (Admininfo_boardDTO admininfo_boardDTO) {
      
      int rs = service.adminAccountDelete(admininfo_boardDTO);
      
      return rs;
   }
   
   @RequestMapping(value = "/companyInfoDelete") 
   public int companyInfoDelete (Companyinfo_boardDTO companyinfo_boardDTO) {
      
      int rs = service.companyInfoDelete(companyinfo_boardDTO);
      
      return 1;
   }
   // gonggoheader.jsp���� AJAX �����Ŵ *231111 ������
   @RequestMapping(value = "/companyLogoView") 
   public CompanyAccount_boardDTO companyLogoView (CompanyAccount_boardDTO companyAccount_boardDTO, HttpSession session) {
      
      int companyIdx = (int)session.getAttribute("companyIdx");
      companyAccount_boardDTO.setCompanyIdx(companyIdx);
      
      CompanyAccount_boardDTO boardDTO = service.companyLogoView(companyAccount_boardDTO);
      
      return boardDTO;
   }
   
   @RequestMapping(value = "/mainImageView") 
   public CompanyAccount_boardDTO mainImageView (CompanyAccount_boardDTO companyAccount_boardDTO, HttpSession session) {
      
      CompanyAccount_boardDTO boardDTO = service.mainImageView(companyAccount_boardDTO);
      
      return boardDTO;
   }
   
   @RequestMapping(value = "/dDayCount") 
   public List<JobNotice_boardDTO> dDayCount () {
      
      List<JobNotice_boardDTO> boardDTO = service.dDayCount();
      
      return boardDTO;
   }
}