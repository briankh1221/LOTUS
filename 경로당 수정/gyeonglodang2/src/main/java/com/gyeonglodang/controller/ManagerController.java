package com.gyeonglodang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gyeonglodang.dto.ManagerDTO;
import com.gyeonglodang.service.ImanagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	String view;
	int gonggoidx;
	int companyIdx;

	@Autowired	
	@Qualifier("managerServiceImpl")
	ImanagerService service;
	
	
	@RequestMapping("/part_list")
	public String part_list(Model model, HttpSession session) {
//		회사번호와 공고번호를 조건으로 전형 목록을 가져옴
		System.out.println("part_list");
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		System.out.println(gonggoidx);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyIdx", companyIdx);
		map.put("gonggoidx", gonggoidx);
		List<ManagerDTO> list = service.getGonggoList(map);
		model.addAttribute("list", list);
		model.addAttribute("gonggoidx", gonggoidx);
//		System.out.println("controller" + list);
		return "manager/part_list";
	}
	
	@PostMapping("/part_list_newreg")
	public String part_list_newreg(Model model, HttpSession session) {
		System.out.println("/part_list_newreg");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		model.addAttribute("gonggoidx", gonggoidx);
		view = "manager/part_list_newreg";
		
		return view;
	}
	// 전형 insert
	@PostMapping("/insert")
	public String insert(ManagerDTO mDto, RedirectAttributes ra, HttpSession session) {
		System.out.println("Controller - insert - post");
		Map<String, Object> company_gonggo = new HashMap<String, Object>();
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		company_gonggo.put("companyIdx", companyIdx);
		company_gonggo.put("gonggoidx", gonggoidx);
		mDto.setCompanyIdx(companyIdx);
		mDto.setGonggoidx(gonggoidx);
		int rs = service.insert(mDto);
		String part = mDto.getPart_list_title();
//		공고와 연결되어있는 필드목록을 가져옴
		List<String> field_list = service.getCategory(company_gonggo);
		int count=1;
		for(String field : field_list) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("part", part);
			map.put("companyIdx", companyIdx);
			map.put("gonggoidx", gonggoidx);
			if(count==1) {
				map.put("fieldidx", "공통");
				service.insertPassFailText(map);
			}
			map.put("fieldidx", field);
//			전형 생성시 전형관리 관련해서 필요한 db에 추가insert
			service.insertPartField(map);
			service.insertPassFailText(map);
			count++;
		}
		ra.addFlashAttribute("rs", rs);
		ra.addFlashAttribute("gonggoidx", gonggoidx);
		
		view = "redirect:part_list";
		return view;
	}
	// 글 상세 페이지
	@GetMapping("/detailForm")
	public String detailForm(ManagerDTO mDto, Model model, HttpSession session) {
		System.out.println("/part_list_detailForm 도착");
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyIdx", companyIdx);
		map.put("gonggoidx", gonggoidx);
		map.put("idx", mDto.getIdx());
		List<ManagerDTO> list = service.part_list_detailForm(map);
//		
		model.addAttribute("list", list);
		model.addAttribute("idx", mDto.getIdx());
		model.addAttribute("gonggoidx", gonggoidx);
		return "manager/part_list_detailForm"; 
	}
	
	@PostMapping("/update")
	public String update(ManagerDTO mDto, HttpSession session, RedirectAttributes ra
			, @RequestParam String startDate, @RequestParam String endDate) {
		System.out.println("controller - update - post");
//		
		mDto.setAnnouncement_date(startDate+"~"+endDate);
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		int idx = mDto.getIdx();
		mDto.setIdx(idx);
		mDto.setCompanyIdx(companyIdx);
		mDto.setGonggoidx(gonggoidx);
		System.out.println(mDto);
		service.update(mDto);
		
//		session.setAttribute("idx", mDto.getIdx());
		ra.addFlashAttribute("gonggoidx", gonggoidx);
		view = "redirect:part_list";
		return view;
		
	}
//	전형 삭제시 db내에 전형으로 연결되어있는 값들을 연계해서 삭제하도록
//	foreign key에 delete cascade설정을 넣었음
	@PostMapping("/delete")
	public String delete(ManagerDTO mDto, HttpSession session, RedirectAttributes ra) {
		System.out.println("controller - delete - post");
		
		
		System.out.println(mDto);
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		ra.addFlashAttribute("gonggoidx", gonggoidx);
		if(mDto != null) {
			mDto.setCompanyIdx(companyIdx);
			mDto.setGonggoidx(gonggoidx);
			service.delete(mDto);
			view = "redirect:part_list";
		}
		
		return view;
	}
}