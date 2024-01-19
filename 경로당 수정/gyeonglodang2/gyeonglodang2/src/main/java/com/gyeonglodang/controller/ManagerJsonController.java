package com.gyeonglodang.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gyeonglodang.dto.ManagerDTO;
import com.gyeonglodang.service.ImanagerService;

@RestController
@RequestMapping("/manager")
public class ManagerJsonController {
	int gonggoidx;
	int companyIdx;
	
	@Autowired	
	ImanagerService service;
	
	@RequestMapping(value = "/getManager", method = RequestMethod.GET)
	public ManagerDTO getManager(@RequestParam int idx, HttpSession session) {
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyIdx", companyIdx);
		map.put("gonggoidx", gonggoidx);
		map.put("idx", idx);
		ManagerDTO dto = service.getManager(map);
		
		return dto;
		
	}
	
}
