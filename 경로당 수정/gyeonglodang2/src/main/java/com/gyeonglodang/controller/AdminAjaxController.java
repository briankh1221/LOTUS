package com.gyeonglodang.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyeonglodang.dto.Admininfo_boardDTO;
import com.gyeonglodang.service.AdminService;

// AdminAjaxController.java: ID & Password 확인 하기 위한 Controller 페이지 *231103 권지현

@RestController
@RequestMapping("/api")
public class AdminAjaxController {
	// view페이지를 담을 변수 *231101 허재영
	String view;	
	
	@Autowired
	@Qualifier("adminServiceImpl")
	AdminService service;

	// 로그인 기능을 처리해주는 메서드 *231101 허재영
	@PostMapping("/loginAction")
	public Map<String, Object> loginAction(Admininfo_boardDTO dto, HttpSession session) {	
		
		// home.jsp에서 가져온 ID, PW *231101 허재영
		String id = dto.getId();	
		String pw = dto.getPw();	
		
		// id가 DB에 있는지 찾아보는 메서드 *231101 허재영
		dto = service.getAdminDTO(dto);	
		Map<String, Object> map = new HashMap<String, Object>();
		if(dto != null) {
			if(pw.equals(dto.getPw())) {				
				map.put("rs", dto);
				// 로그인 동시에 session에 채용 담당자 CompanyIdx, CompanyName 띄움 *231101 권지현  
				session.setAttribute("companyIdx", dto.getCompanyIdx());
				session.setAttribute("company", dto.getName());
			} else {
				map.put("rs", "0");
			}
		} else {
			map.put("rs", "0");
		}		
		map.put("role", dto.getRole());
		return map;
	}
}
