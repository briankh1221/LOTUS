package com.gyeonglodang.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyeonglodang.dto.ApplicantDTO;
import com.gyeonglodang.service.RecruitResumeService;

@RestController
@RequestMapping("/applicantapi")
public class ApplicantAjaxController {
	
	String view;	//view페이지를 담을 변수
	
	@Autowired
	@Qualifier("recruitResumeServiceImpl")
	RecruitResumeService service;

	
	@PostMapping("/applicantAction")
	public Map<String, Object> loginAction(ApplicantDTO dto, HttpSession session) {	//
		
		String email = dto.getEmail();	//applicationSearch.jsp에서 가져온 ID
		String password = dto.getPassword();	//applicationSearch.jsp에서 가져온 PW
		
		dto = service.getApplicant(email);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(dto != null) {
			if(password.equals(dto.getPassword())) {				
				map.put("rs", dto);	
			} else {
				map.put("rs", "0");
			}
		 } else {
			map.put("rs", "0");
		 }
		
		
		return map;
	}
}
