package com.gyeonglodang.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.JobNoticeRecListDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.RecruitCateg_boardDTO;
import com.gyeonglodang.serviceimpl.JobNotice_boardServiceImpl;

@RestController
@RequestMapping("/notice")
public class NcJasonController {

	@Autowired
	JobNotice_boardServiceImpl service;

	// jobNotice_board.jsp에서 AJAX 실행시킴 *231103 권지현
	@RequestMapping(value = "/jobNotice_boardList")
	public List<JobNotice_boardDTO> ncSearchList(HttpSession session) {
		int companyIdx = (int)session.getAttribute("companyIdx");
		
		JobNotice_boardDTO jobDTO = new JobNotice_boardDTO();
		jobDTO.setCompanyIdx(companyIdx);
		List<JobNotice_boardDTO> list = service.ncSearchList(jobDTO);
		
		// gonggoheader.jsp에서 session에 noticeControl_idx 올리기 위해 필요함 *231110 권지현
		if(session.getAttribute("noticeControl_idx") == null) {
			if(list.size()==0) {
				session.setAttribute("noticeControl_idx", "1");
			}else {
				session.setAttribute("noticeControl_idx", list.get(0).getNoticeControl_idx());
			}
		}
		return list;
	}
	// jobNotice_board_new.jsp에서 AJAX 실행시킴 *231110 권지현
	@RequestMapping(value = "/getContent")
	public Map<String, Object> getContent(@RequestParam String noticeControl_idx, HttpSession session) {
		// JobNoticeRecListDTO에 DTO와 ArrayList 담음
		JobNoticeRecListDTO jobRecDTO = new JobNoticeRecListDTO();
		jobRecDTO.setJobDto(new JobNotice_boardDTO());
		jobRecDTO.setRecDto(new RecruitCateg_boardDTO());
		
		jobRecDTO.getJobDto().setNoticeControl_idx(noticeControl_idx);
		jobRecDTO.getRecDto().setNoticeControl_idx(noticeControl_idx);
		
		Map<String, Object> map = service.getContent(jobRecDTO);
		
		return map;
	}
	
	@RequestMapping(value = "/companyAccount_getContent")
	public CompanyAccount_boardDTO companyAccount_getContent(HttpSession session) {
		CompanyAccount_boardDTO dto = new CompanyAccount_boardDTO();
		
		dto.setCompanyIdx((int)session.getAttribute("companyIdx"));
		CompanyAccount_boardDTO contentDto = service.companyAccount_getContent(dto);
		
		return contentDto;
	}
	
	@RequestMapping(value = "/companyLogo_imgUpload")
	public Map<String, Object> companyLogo_imgUpload(
			@RequestPart(required = false) MultipartFile logoFile, 
			@RequestPart(required = false) MultipartFile regFile,  
			@RequestParam String imageSelect,
			HttpSession session
			) throws Exception {
		
		String companyIdx = Integer.toString((int)session.getAttribute("companyIdx"));
		String uploadPath = "/Users/dpqbw/Desktop/k-digital/spring2/springws/gyeonglodang/src/main/webapp/resources/uploadfile"; 
		String logoFileName = "";
		String regFileName = "";
		
		
		if(logoFile != null) {
			File saveFile = new File(uploadPath, logoFile.getOriginalFilename());
			logoFile.transferTo(saveFile);
			logoFileName = logoFile.getOriginalFilename();
		}
		if(regFile != null) {
			File saveFile = new File(uploadPath, regFile.getOriginalFilename());
			regFile.transferTo(saveFile);
			regFileName = regFile.getOriginalFilename();
		}
		
	
		Map<String, Object> map = new HashMap<>();
		
		if(!logoFileName.equals("")) {
			map.put("companyLogoImg", logoFileName);
		}
		if(!regFileName.equals("")) {
			map.put("companySiteImg", regFileName);
		}
		map.put("companyIdx", companyIdx);
					
		return map;
	}

	@RequestMapping(value = "/noticeControl_idx_to_session")
	public String noticeControl_idx_to_session(@RequestParam String selectedValue,  HttpSession session) {
		session.setAttribute("noticeControl_idx_selected", selectedValue);
		return selectedValue;
	}
	// gonggoheader.jsp에서 AJAX 실행시킴. Session에 올려진 noticeControl_idx_selected 받기 위해 실행시키는 메서드 *231107 권지현
	@RequestMapping(value = "/getSession")
	public Object getSession(HttpSession session) {
		Object noticeControl_idx_selected = session.getAttribute("noticeControl_idx_selected") == null ? "0" : session.getAttribute("noticeControl_idx_selected");
		
		return noticeControl_idx_selected;
	}
	// gonggoheader.jsp에서 AJAX 실행시킴. Session에 noticeControl_idx_selected 셋업 시키기 위해 실행시키는 메서드 *231107 권지현
	@RequestMapping(value = "/setSession")
	public Object setSession(HttpSession session, @RequestParam int noticeControl_idx_selected) {
		session.setAttribute("noticeControl_idx_selected", noticeControl_idx_selected);
		
		return (Object)session.getAttribute("noticeControl_idx_selected");
	}

}
