package com.gyeonglodang.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.JobNoticeRecListDTO;
import com.gyeonglodang.dto.RecruitCateg_boardDTO;
import com.gyeonglodang.serviceimpl.JobNotice_boardServiceImpl;

@Controller
@RequestMapping("/notice")
public class NcController {

	@Autowired
	JobNotice_boardServiceImpl service;
	
	@RequestMapping(value = "/jobNotice_board")
	public String jobNotice_board(
			@RequestParam(required = false) String noticeControl_idx, 
			@RequestParam(required = false) String noticeControl_idx_selected, 
			@RequestParam(required = false) String companyIdx, 
			HttpSession session,
			Model model) {
		if(companyIdx != null) {
			session.setAttribute("companyIdx", Integer.parseInt(companyIdx));
		}
		if(noticeControl_idx != null) {
			service.deleteNotice(noticeControl_idx);
		}
		return "notice/jobNotice_board";
	}
	
	@RequestMapping(value = "/jobNotice_board_new", method = RequestMethod.GET)
	public String jobNotice_board_new(HttpSession session, Model model) {
		
		model.addAttribute("noticeControl_idx_selected", session.getAttribute("noticeControl_idx_selected"));
		return "notice/jobNotice_board_new";
	}
	// jobNotice_board_new.jsp에서 기본정보 저장하기 위한 메서드 *231110 권지현
	@RequestMapping(value = "/jobNotice_board_new_save", method = RequestMethod.POST)
	public String jobNotice_board_new_save(JobNoticeRecListDTO dto, @RequestParam Map<String, Object> map, HttpSession session) {
		String jobNotice_postingDate = (String)map.get("jobDto.jobNotice_postingDateS") + "~" + (String)map.get("jobDto.jobNotice_postingDateE");
		dto.getJobDto().setJobNotice_postingDate(jobNotice_postingDate);
		int companyidx = (int)session.getAttribute("companyIdx");
		dto.getJobDto().setCompanyIdx(companyidx);
		
		for(RecruitCateg_boardDTO RecruitCateg_boardDTO : dto.getRecDtoList()) {
			RecruitCateg_boardDTO.setCompanyIdx(companyidx);
		}
		
		service.insert(dto);
		
		return "notice/jobNotice_board";
	}
	// jobNotice_board_new.jsp에서 기본정보 업데이트하기 위한 메서드 *231110 권지현
	@RequestMapping(value = "/jobNotice_board_new_update", method = RequestMethod.POST)
	public String jobNotice_board_new_update(JobNoticeRecListDTO dto, @RequestParam Map<String, Object> map, HttpSession session) {
		String noticeControl_idx = (String)map.get("noticeControl_idx");
		String jobNotice_postingDate = (String)map.get("jobDto.jobNotice_postingDateS") + "~" + (String)map.get("jobDto.jobNotice_postingDateE");
		int companyidx = (int)session.getAttribute("companyIdx");
		
		dto.getJobDto().setCompanyIdx(companyidx);
		dto.getJobDto().setNoticeControl_idx(noticeControl_idx);
		dto.getJobDto().setJobNotice_postingDate(jobNotice_postingDate);
		
		for(int i=0; i<dto.getRecDtoList().size(); i++) {
			dto.getRecDtoList().get(i).setNoticeControl_idx(noticeControl_idx);
			dto.getRecDtoList().get(i).setCompanyIdx(companyidx);
		}
		service.update(dto);
		
		return "notice/jobNotice_board";
	}
	
	@RequestMapping(value = "/companyAccount")
	public String companyAccount() {
		
		return "notice/companyAccount";
	}
	
	@RequestMapping(value = "/companyAccount_save")
	public String companyAccount_save(CompanyAccount_boardDTO dto, Map<String, Object> map) {
		service.companyAccount_insert(dto);
		
		return "notice/companyAccount";
	}
	
	@RequestMapping(value = "/announcemnet_boardPreview2")
	public String announcemnet_boardPreview2() {
		
		return "notice/announcemnet_boardPreview2";
	}
	
	@RequestMapping(value = "/companyAccount_update")
	   public String companyAccount_update(CompanyAccount_boardDTO dto, @RequestParam("imageSelect") String imageSelect) {
	      if(imageSelect.equals("1")) dto.setCompanySiteImg("1111.jpg");
	      if(imageSelect.equals("2")) dto.setCompanySiteImg("cat1.jpg");
	      if(imageSelect.equals("3")) dto.setCompanySiteImg("cat2.jpg");
	      
	      service.companyAccount_update(dto);
	      
	      return "notice/companyAccount";
	   }
}
