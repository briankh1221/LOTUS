
package com.gyeonglodang.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gyeonglodang.dto.Admininfo_boardDTO;
import com.gyeonglodang.dto.Announcement_boardDTO;
import com.gyeonglodang.dto.Companyinfo_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_boardDTO;
import com.gyeonglodang.dto.JobNoticeCateg_fileDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.serviceimpl.jobNoticeCateg_boardServiceImpl;

@Controller
@RequestMapping("/notice")
public class JobNoticeCategController {

   @Autowired
   jobNoticeCateg_boardServiceImpl service;


   @RequestMapping(value = "/jobNoticeCateg_board", method = RequestMethod.GET)
   public String jobNoticeCateg_board(HttpSession session, Model model) {
        
      String companyIdx = Integer.toString((int)session.getAttribute("companyIdx"));
      
      model.addAttribute("companyIdx", companyIdx);
         
      return "notice/jobNoticeCateg_board";
   }
   
   @RequestMapping(value = "/jobNoticeCateg_board2", method = RequestMethod.GET)
   public String jobNoticeCateg_board2(HttpSession session, Model model) {
      
      int companyIdx = (int)session.getAttribute("companyIdx");
      
      model.addAttribute("companyIdx", companyIdx);
      
      return "notice/jobNoticeCateg_board2";
   }

   @RequestMapping(value = "/jobNoticeCateg_boardEdit", method = RequestMethod.GET)
   public String jobNotice_boardEdit(HttpSession session, Model model) {
      
      
      int companyIdx = (int)session.getAttribute("companyIdx");
      
      model.addAttribute("companyIdx", companyIdx);

      return "notice/jobNoticeCateg_boardEdit";
   }
   
   @RequestMapping(value = "/jobNotice_operationStatus", method = RequestMethod.GET)
   public String jobNotice_operationStatus() {
            
      return "notice/jobNotice_operationStatus";
   }
   
   @RequestMapping(value = "/companyAccounts", method = RequestMethod.GET)
   public String companyAccounts() {
      
      return "notice/companyAccounts";
   }
   
   @RequestMapping(value = "/updateAll") 
   public String updateAll (@RequestParam Map<String , Object> map) {
      
      JobNotice_boardDTO jobNotice_boardDTO= new JobNotice_boardDTO();
      jobNotice_boardDTO.setJobNotice_title((String)map.get("jobNotice_boardDTO.jobNotice_title"));
      jobNotice_boardDTO.setNoticeControl_idx((String)map.get("noticeControl_idx"));

      int rs = service.updateJobNotice_boardDTO(jobNotice_boardDTO);

      JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO = new JobNoticeCateg_boardDTO();
      jobNoticeCateg_boardDTO.setJobNoticeCateg_postingDate((String)map.get("jobNoticeCateg_boardDTO.jobNoticeCateg_postingDate")+"~"+(String)map.get("jobNoticeCateg_boardDTO.jobNoticeCateg_postingDate2"));
      String jobNoticeCategOperation = (String) map.get("jobNoticeCateg_boardDTO.jobNoticeCateg_operation");   
      
      if (jobNoticeCategOperation == null || jobNoticeCategOperation.isEmpty()) {
         jobNoticeCateg_boardDTO.setJobNoticeCateg_operation("n");
      } else if (jobNoticeCategOperation.equals("on")) {
         jobNoticeCateg_boardDTO.setJobNoticeCateg_operation("y");
      } 
         
      jobNoticeCateg_boardDTO.setJobNoticeCateg_content((String)map.get("jobNoticeCateg_boardDTO.jobNoticeCateg_content"));
      jobNoticeCateg_boardDTO.setNoticeControl_idx((String)map.get("noticeControl_idx"));

      int rs2 = service.updateJobNoticeCateg_boardDTO(jobNoticeCateg_boardDTO);

      return "notice/jobNoticeCateg_board";
   }
   
   @RequestMapping(value = "/companyEdit") 
   public String companyEdit (Companyinfo_boardDTO companyinfo_boardDTO) {
      
      if (companyinfo_boardDTO.getCompany_operation() == null || companyinfo_boardDTO.getCompany_operation().isEmpty()) {
         companyinfo_boardDTO.setCompany_operation("n");
         
      } else if (companyinfo_boardDTO.getCompany_operation().equals("on")) {
         companyinfo_boardDTO.setCompany_operation("y");
      } 
      
      int rs2 = service.companyEdit(companyinfo_boardDTO);
      
      return "notice/companyAccounts";
   }

   @RequestMapping(value = "/saveAll") 
   public String saveAll (@RequestParam Map<String , Object> map) {
      
      JobNotice_boardDTO jobNotice_boardDTO= new JobNotice_boardDTO();
      jobNotice_boardDTO.setJobNotice_title((String)map.get("jobNotice_boardDTO.jobNotice_title"));
      jobNotice_boardDTO.setNoticeControl_idx((String) map.get("noticeControl_idx"));
       String scompnayIdx=(String) map.get("companyIdx");
       int companyIdx=Integer.parseInt(scompnayIdx);
       jobNotice_boardDTO.setCompanyIdx(companyIdx);
      
      int rs = service.saveJobNotice_boardDTO(jobNotice_boardDTO);

      JobNoticeCateg_boardDTO jobNoticeCateg_boardDTO = new JobNoticeCateg_boardDTO();
      jobNoticeCateg_boardDTO.setJobNoticeCateg_postingDate((String)map.get("jobNoticeCateg_boardDTO.jobNoticeCateg_postingDate")+"~"+(String)map.get("jobNoticeCateg_boardDTO.jobNoticeCateg_postingDate2"));
      String jobNoticeCategOperation = (String) map.get("jobNoticeCateg_boardDTO.jobNoticeCateg_operation");

      if (jobNoticeCategOperation == null || jobNoticeCategOperation.isEmpty()) {
         jobNoticeCateg_boardDTO.setJobNoticeCateg_operation("n");
      } else if (jobNoticeCategOperation.equals("on")) {
         jobNoticeCateg_boardDTO.setJobNoticeCateg_operation("y");
      } 

      jobNoticeCateg_boardDTO.setJobNoticeCateg_content((String)map.get("jobNoticeCateg_boardDTO.jobNoticeCateg_content"));   
      jobNoticeCateg_boardDTO.setNoticeControl_idx((String) map.get("noticeControl_idx"));      
      jobNoticeCateg_boardDTO.setCompanyIdx(companyIdx);

      int rs2 = service.saveJobNoticeCateg_boardDTO(jobNoticeCateg_boardDTO);

      return "notice/jobNoticeCateg_board";
   }
   
   @RequestMapping(value = "/savingBoardEdit") 
   public String savingBoardEdit (@RequestParam Map<String , Object> map) {
      
      Announcement_boardDTO announcement_boardDTO = new Announcement_boardDTO();
      announcement_boardDTO.setAnnouncement_no(Integer.parseInt((String) map.get("announcement_no")));
      announcement_boardDTO.setAnnouncement_category((String)map.get("announcement_category"));
      announcement_boardDTO.setAnnouncement_title((String)map.get("announcement_title"));
      announcement_boardDTO.setAnnouncement_postingDate((String)map.get("announcement_postingDate1")+"~"+(String)map.get("announcement_postingDate2"));
      String announcement_operation = (String) map.get("announcement_operation");
      
      if (announcement_operation == null || announcement_operation.isEmpty()) {
         announcement_boardDTO.setAnnouncement_operation("n");
      } else if (announcement_operation.equals("on")) {
         announcement_boardDTO.setAnnouncement_operation("y");
      } 
      announcement_boardDTO.setAnnouncement_content((String)map.get("announcement_content"));
      
      int rs = service.savingBoardEdit(announcement_boardDTO);
      
      return "notice/announcement_board";
   }
   
      @RequestMapping(value = "/adminAccountRegister") 
      public String adminAccountRegister (@RequestParam Map<String , Object> map, HttpSession session) {
         
         int companyIdx = (int)session.getAttribute("companyIdx");
         Admininfo_boardDTO admininfo_boardDTO = new Admininfo_boardDTO();
         admininfo_boardDTO.setName((String)map.get("name"));
         admininfo_boardDTO.setId((String)map.get("id"));
         admininfo_boardDTO.setPw((String)map.get("pw"));
         admininfo_boardDTO.setAuth((String)map.get("auth"));
         admininfo_boardDTO.setEmail((String)map.get("email"));
         admininfo_boardDTO.setTel((String)map.get("tel"));
         admininfo_boardDTO.setDep((String)map.get("dep"));
         admininfo_boardDTO.setCompanyIdx(companyIdx);
               
         int rs = service.adminAccountRegister(admininfo_boardDTO);
               
         return "notice/administratorAccounts";
      }
   
   @RequestMapping(value = "/adminAccountUpdate") 
   public String adminAccountUpdate (@RequestParam Map<String , Object> map) {
      
      Admininfo_boardDTO admininfo_boardDTO = new Admininfo_boardDTO();
      admininfo_boardDTO.setIdx((String) map.get("idx"));      
      admininfo_boardDTO.setName((String)map.get("name"));
      admininfo_boardDTO.setId((String)map.get("id"));
      admininfo_boardDTO.setPw((String)map.get("pw"));
      admininfo_boardDTO.setAuth((String)map.get("auth"));
      admininfo_boardDTO.setEmail((String)map.get("email"));
      admininfo_boardDTO.setTel((String)map.get("tel"));
      admininfo_boardDTO.setDep((String)map.get("dep"));
      
      int rs = service.adminAccountUpdate(admininfo_boardDTO);
      
      return "notice/administratorAccounts";
   }
   @RequestMapping(value = "/companyRegister") 
   public String companyRegister (Companyinfo_boardDTO companyinfo_boardDTO) {
            
      String company_operation = companyinfo_boardDTO.getCompany_operation();
      
      if (company_operation == null || company_operation.isEmpty()) {
         companyinfo_boardDTO.setCompany_operation("n");
      } else if (company_operation.equals("on")) {
         companyinfo_boardDTO.setCompany_operation("y");
      } 
      
      
      int rs = service.companyRegister(companyinfo_boardDTO);
      int companyIdx = service.getCompanyIdx();

      JobNotice_boardDTO dto = new JobNotice_boardDTO();
      dto.setCompanyIdx(companyIdx);
      dto.setJobNotice_title("임시공고입니다");
      dto.setJobNotice_operation("y");
      dto.setJobNotice_postingDate("1900-01-01~1900-01-01");
      service.jobInsert(dto);
      
      return "notice/companyAccounts";
   }
   
   @RequestMapping(value = "/jobNoticeCateg_boardEdit_preview")
   public String jobNoticeCateg_boardEdit_preview(JobNoticeCateg_fileDTO dto, Model model) {
      
      model.addAttribute("noticeControl_idx", dto.getNoticeControl_idx());
      
      return "notice/jobNoticeCateg_boardEdit_preview";
   }
   
   @RequestMapping(value = "/announcement_boardPreview")
   public String announcement_boardPreview(Announcement_boardDTO dto, Model model) {
      
      model.addAttribute("announcement_no", dto.getAnnouncement_no());
      
      return "notice/announcement_boardPreview";
   }
   
   @RequestMapping(value = "/announcement_board")
   public String announcement_board() {
            
      return "notice/announcement_board";
   }
   
   @RequestMapping(value = "/announcement_boardRegister")
   public String announcement_boardRegister() {
      
      return "notice/announcement_boardRegister";
   }
   
   @RequestMapping(value = "/announcement_boardWrite")
   public String announcement_boardWrite(@RequestParam Map<String , Object> map, HttpSession session) {
            
      Announcement_boardDTO announcement_boardDTO = new Announcement_boardDTO();
      announcement_boardDTO.setCompanyIdx((int)session.getAttribute("companyIdx"));
      announcement_boardDTO.setAnnouncement_category((String)map.get("announcement_category"));
      announcement_boardDTO.setAnnouncement_title((String)map.get("announcement_title"));
      announcement_boardDTO.setAnnouncement_postingDate((String)map.get("announcement_postingDate1")+"~"+(String)map.get("announcement_postingDate2"));
      String announcement_operation = (String) map.get("announcement_operation");
      
      if (announcement_operation == null || announcement_operation.isEmpty()) {
         announcement_boardDTO.setAnnouncement_operation("n");
      } else if (announcement_operation.equals("on")) {
         announcement_boardDTO.setAnnouncement_operation("y");
      } 
      announcement_boardDTO.setAnnouncement_content((String)map.get("announcement_content"));
      
      int rs = service.boardWrite(announcement_boardDTO);
      
      return "notice/announcement_board";
   }
   
   @RequestMapping(value = "/announcement_boardEdit")
   public String announcement_boardEdit() {
      
      return "notice/announcement_boardEdit";
   }
   
   @RequestMapping(value = "/administratorAccounts")
   public String adminstratorAccounts() {
      
      return "notice/administratorAccounts";
   }
   
   @RequestMapping(value = "/jobNoticeCateg_boardEdit_preview2")
   public String jobNoticeCateg_boardEdit_preview2(JobNoticeCateg_fileDTO dto, Model model) {
      
      model.addAttribute("noticeControl_idx", dto.getNoticeControl_idx());
      
      return "notice/jobNoticeCateg_boardEdit_preview2";
   }
   
   @RequestMapping(value = "/main")
   public String main(Model model, HttpSession session, @RequestParam(name = "companyIdx") String companyIdx ) {
      System.out.println("session : " + companyIdx);
      
      int icompanyIdx = Integer.parseInt(companyIdx);
      
      session.setAttribute("companyIdx", icompanyIdx);
      model.addAttribute(icompanyIdx);
      
      return "notice/main";
   }
}   