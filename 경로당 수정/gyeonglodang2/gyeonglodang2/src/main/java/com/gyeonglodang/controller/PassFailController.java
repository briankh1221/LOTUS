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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gyeonglodang.dao.FileUtil;
import com.gyeonglodang.dao.PassFailDAO;
import com.gyeonglodang.dto.AnnouncementCustomDTO;
import com.gyeonglodang.dto.CustomizeDTO;
import com.gyeonglodang.dto.ManagerDTO;
import com.gyeonglodang.dto.PassFailDTO;
import com.gyeonglodang.dto.SuccessfulCandidateListDTO;
import com.gyeonglodang.service.ImanagerService;

@Controller
@RequestMapping("passfail")
public class PassFailController {
	String view;
	int gonggoidx;
	int companyIdx;

	@Autowired
	@Qualifier("passFailDAOimpl")
	PassFailDAO dao;
	
	@Autowired	
	@Qualifier("managerServiceImpl")
	ImanagerService service;

	@Autowired
	FileUtil fileUtil;

	// 합격발표 설정으로 이동시 *231205 정민석
	@RequestMapping(value = "write", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeList(Model model, PassFailDTO dto, Map<String, Object> map, HttpSession session) {
		Map<String, Object> company_part = new HashMap<String, Object>();
		Map<String, Object> company_part_name = new HashMap<String, Object>();
		// session에 담겨있을 값들을 가져옴 *231207 정민석
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		// sql문 조건에 사용될 값들을 dto에 담아둠 *231207 정민석
		dto.setCompanyIdx(companyIdx);
		dto.setGonggoidx(gonggoidx);
		// sql문 조건에 사용될 값들을 map에 담아둠 *231207 정민석
		// 조건에 필요한 값에따라 사용할 map과 sql문이 달라짐 *231207 정민석
		company_part.put("companyIdx", companyIdx); 
		company_part.put("gonggoidx", gonggoidx);
		// 전형드롭박스에 들어갈 값 *231207 정민석
		List<ManagerDTO> part_title_list = dao.getPartTitle(company_part); 
		if(dto.getPart()==null || dto.getPart().equals("blank")) {
			model.addAttribute("part_title_list",part_title_list);
			view = "passfail/write";
			return view;
		}
		String part = dto.getPart();
		company_part.put("part", part);
		// 표시할 문구를 위해 날짜문자열과 세팅문자열을 합침 *231210 정민석
		// 공고가 바뀔때 만약 전형이 비어있을때 분기해야함 *231210 정민석
		map = dao.getDateSetting(company_part);
		if(map==null) {
			model.addAttribute("part_title_list",part_title_list);
			view = "passfail/write";
			return view;
		}
		// 시작날짜 값과 종료날짜값을 따로 전달하기위해 분리 *231210 정민석
		String date = (String) map.get("announcement_date");
		String[] dates = date.split("~");
		String Setting = (String) map.get("announcement_setting");
		Setting = Setting.replace("-", "");
		String startdate = dates[0];
		String enddate = dates[1];
		String dateSetting = date + " " + "(" + Setting + ")";
		// 전형과 연결된 분야에 등록될 문구를 수정 혹은 등록하기 위해 연결된 분야를 리스트로 받아옴 *231210 정민석
		List<PassFailDTO> list = dao.getTextList(company_part);
		List<AnnouncementCustomDTO> custom_title = dao.getCustomTitle(company_part);
		company_part_name.put("companyIdx", companyIdx);
		company_part_name.put("gonggoidx", gonggoidx);
		company_part_name.put("part", part);
		company_part_name.put("name", "");
		List<CustomizeDTO> customize_list = dao.getCustomizeList(company_part_name);
		model.addAttribute("part", part);
		model.addAttribute("gonggoidx", gonggoidx);
		model.addAttribute("startdate", startdate.trim());
		model.addAttribute("enddate", enddate.trim());
		model.addAttribute("Setting", Setting);
		model.addAttribute("list", list);
		model.addAttribute("dateSetting", dateSetting);
		model.addAttribute("custom_title", custom_title);
		model.addAttribute("customize_list", customize_list);
		model.addAttribute("part_title_list", part_title_list);
		view = "passfail/write";
		return view;
	}

	@GetMapping("passfailWrite")
	public String goPassFailWrite(Model model, PassFailDTO dto, @RequestParam String pass_fail
		, HttpSession session, Map<String, Object> company_part) {
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		dto.setCompanyIdx(companyIdx);
		dto.setGonggoidx(gonggoidx);
		String part = dto.getPart();
		company_part.put("companyIdx", companyIdx);
		company_part.put("gonggoidx", gonggoidx);
		company_part.put("part", part);
		// 드롭박스에 값으로 들어갈 전형과 연결된 분야값들을 list로 받아옴 *231210 정민석
		List<PassFailDTO> list = dao.getFieldidxs(company_part);
		String text = "";
		// 합격, 불합격 문구를 수정할시 이전값을 input에 담아주기위해 db에서 문구를 가져옴 *231210 정민석
		if (pass_fail.equals("pass")) {
			text = dao.getPassText(dto);
		} else if (pass_fail.equals("fail")) {
			text = dao.getFailText(dto);
		}
		model.addAttribute("dto", dto);
		model.addAttribute("list", list);
		model.addAttribute("text", text);
		model.addAttribute("pass_fail", pass_fail);
		model.addAttribute("gonggoidx", gonggoidx);
		view = "passfail/passfailWrite";
		return view;
	}

	// 전형생성시 전형-분야가 연결된 합격,불합격 문구가 담길 db가 insert *231211 정민석
    // insert 당시엔 합격, 불합격 문구를 null로 설정 *231211 정민석
	// 이곳에서 새로 등록 혹은 수정 두가지 경우에 모두 update문을 사용하기 위함 *231211 정민석
	@PostMapping("passfailWrite")
	public String failWrite(Model model, PassFailDTO pfdto, @RequestParam String pass_fail,
			@RequestParam String pass_fail_text, HttpSession session) {
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		pfdto.setCompanyIdx(companyIdx);
		pfdto.setGonggoidx(gonggoidx);
		if (pass_fail.equals("pass")) {
			pfdto.setPass_text(pass_fail_text);
			dao.updatePass(pfdto);
		} else if (pass_fail.equals("fail")) {
			pfdto.setFail_text(pass_fail_text);
			dao.updateFail(pfdto);
		}
		model.addAttribute("part", pfdto.getPart());
		view = "redirect:write";
		return view;
	}

	//	사용될 sql문의 조건이 달라질때마다 sql문에 전달할 map과 사용될 sql이 달라짐에 따라 map 변수를 여러개 선언함 *231211 정민석
	//	커스텀 문구 등록페이지로 이동시 *231211 정민석
	@GetMapping("customWrite")
	public String goCustomWrite(Model model, @RequestParam String title, AnnouncementCustomDTO acdto,
			@RequestParam String part, HttpSession session) {
		companyIdx = (int)session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		acdto.setCompanyIdx(companyIdx);
		acdto.setGonggoidx(gonggoidx);
		Map<String, Object> company_part = new HashMap<String, Object>();
		company_part.put("companyIdx", companyIdx);
		company_part.put("gonggoidx", gonggoidx);
		company_part.put("part", part);
		// 합격자발표(커스텀)에 저장된 값이 없을 경우에 *231211 정민석
		// 혹은 선택된 제목값이 새로추가 인경우 title 과 context를 빈값으로 설정 *231211 정민석
		if (title.equals("new")) {
			model.addAttribute("title", "");
			model.addAttribute("context", "");
		  // 합격발표 설정에서 합격자발표(커스텀으로) 이동시 첫번째 제목을 기준으로 설정 *231211 정민석
		} else if (title.equals("change")) {
			Map<String, Object> company_gonggo = new HashMap<String, Object>();
			company_gonggo.put("companyIdx", companyIdx);
			company_gonggo.put("gonggoidx", gonggoidx);
			Map<String, Object> company_title = new HashMap<String, Object>();
			acdto = dao.getMainCustom(company_gonggo);
			String announcetitle = acdto.getAnnouncement_custom_title();
			company_title.put("companyIdx", companyIdx);
			company_title.put("gonggoidx", gonggoidx);
			company_title.put("title", announcetitle);
			List<AnnouncementCustomDTO> applicant_list = dao.getApplicantList(company_title);
			model.addAttribute("applicant_list", applicant_list);
			model.addAttribute("title", announcetitle);
			model.addAttribute("context", acdto.getContext());
			// 제목 드롭박스의 값 변경시 그 값을 기준으로 설정 *231211 정민석
		} else {
			Map<String, Object> company_title = new HashMap<String, Object>();
			company_title.put("companyIdx", companyIdx);
			company_title.put("gonggoidx", gonggoidx);
			company_title.put("title", title);
			acdto = dao.getCustom(company_title);
			String announcetitle = acdto.getAnnouncement_custom_title();
			List<AnnouncementCustomDTO> applicant_list = dao.getApplicantList(company_title);
			model.addAttribute("applicant_list", applicant_list);
			model.addAttribute("title", announcetitle);
			model.addAttribute("context", acdto.getContext());
		}
		model.addAttribute("part", part);
		List<AnnouncementCustomDTO> custom_title = dao.getCustomTitle(company_part);
		model.addAttribute("custom_title", custom_title);
		model.addAttribute("gonggoidx", gonggoidx);
		view = "passfail/customWrite";
		return view;
	}

	// 커스텀 문구 등록시 *231211 정민석
	@PostMapping("customWrite")
	public String customWrite(AnnouncementCustomDTO acdto, @RequestParam String past_title, Map<String, Object> map,
			Model model, HttpSession session, Map<String, Object> company_part, Map<String, Object> company_custom,
			Map<String, Object> company_title) {
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		acdto.setCompanyIdx(companyIdx);
		acdto.setGonggoidx(gonggoidx);
		String title = acdto.getAnnouncement_custom_title();
		String part = acdto.getPart();
		company_part.put("companyIdx", companyIdx);
		company_part.put("gonggoidx", gonggoidx);
		company_part.put("part", part);
		// 커스텀 문구를 새로 등록시 *231211 정민석
		if (past_title.equals("")) {
			map.put("part", acdto.getPart());
			map.put("title", acdto.getAnnouncement_custom_title());
			map.put("context", acdto.getContext());
			map.put("companyIdx", companyIdx);
			map.put("gonggoidx", gonggoidx);
			dao.insertCustom(map);
		// 커스텀 문구를 수정시 *231211 정민석
		} else {
			map.put("past_title", past_title);
			map.put("context", acdto.getContext());
			map.put("title", title);
			map.put("companyIdx", companyIdx);
			map.put("gonggoidx", gonggoidx);
			dao.updateCustom(map);
		}
		// url을 리다이렉트시 필요한 값들을 설정 *231213 정민석
		company_custom.put("companyIdx", companyIdx);
		company_custom.put("gonggoidx", gonggoidx);
		company_custom.put("title", title);
		// 리다이렉트에 필요한 등록 혹은 수정된 제목에 연결된 제목과 내용을 가져옴 *231213 정민석
		company_title.put("companyIdx", companyIdx);
		company_title.put("gonggoidx", gonggoidx);
		company_title.put("announcetitle", title);
		List<AnnouncementCustomDTO> applicant_list = dao.getApplicantList(company_title);
		model.addAttribute("applicant_list", applicant_list);
		acdto = dao.getCustom(company_custom);
		model.addAttribute("title", acdto.getAnnouncement_custom_title());
		model.addAttribute("context", acdto.getContext());
		List<AnnouncementCustomDTO> custom_title = dao.getCustomTitle(company_part);
		model.addAttribute("custom_title", custom_title);
		model.addAttribute("part", part);
		model.addAttribute("gonggoidx", gonggoidx);
		view = "passfail/customWrite";
		return view;
	}

	// 합격자목록 페이지에 이동시 *231213 정민석
	@GetMapping("successfulcandidateList")
	public String goSuccessfulCandidateList(Model model, Map<String, Object> company_pass_fail, HttpSession session,
			Map<String, Object> company_part) {
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		String page = "";
		company_pass_fail.put("companyIdx", companyIdx);
		company_pass_fail.put("gonggoidx", gonggoidx);
		company_pass_fail.put("pass_fail", "");
		company_part.put("companyIdx", companyIdx);
		company_part.put("gonggoidx", gonggoidx);
		// 공고와 연결되어있는 필드목록을 가져옴 *231213 정민석
		List<String> field_list = service.getCategory(company_part);
		
		// 분야와 합격여부 조건없이 successful_candidate_list 테이블에 값을 전부 가져옴 *231213 정민석
		// 처음 이동시 전체를 가져오게 설계함 *231213 정민석
		// 여기서 tbody에 들어갈 값을 설정해서 보냄 *231215 정민석
		List<SuccessfulCandidateListDTO> successful_candidate_list = dao.getSuccessfulCandidateList(company_pass_fail);
		for (SuccessfulCandidateListDTO dto : successful_candidate_list) {
			page += "<tr>";
			page += "<td>" + dto.getScode() + "</td>";
			page += "<td>" + dto.getName() + "</td>";
			page += "<td><input type='button' onclick='gofinal("+dto.getScode()+")' value='지원서'></td>";
			page += "<td>" + dto.getFieldidx() + "</td>";
			page += "<td>" + dto.getPass_fail() + "</td>";
			page += "</tr>";
		}
		model.addAttribute("field_list", field_list);
		model.addAttribute("gonggoidx", gonggoidx);
		model.addAttribute("page", page);
		view = "passfail/successfulcandidateList";
		return view;
	}

	//	업로드페이지로 이동 *231213 정민석
	@GetMapping("upload")
	public String goUpload(Model model, @RequestParam Map<String, Object> map
			, HttpSession session) {
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		model.addAttribute("check", map.get("check"));
		model.addAttribute("title", map.get("title"));
		model.addAttribute("part", map.get("part"));
		model.addAttribute("gonggoidx", gonggoidx);
		view = "passfail/upload";
		return view;
	}

	//	CommmnosMultipartResolver을 사용하기위해 pom에 메이븐 두개 추가 *231215 정민석
	//	log4j2사용을 위해 xml파일 추가 *231215 정민석
	//	successfulcandidateList의 리스트를 excel파일로 업로드하는 공간 *231215 정민석
	@PostMapping("SCupload")
	public String doUpload(SuccessfulCandidateListDTO scldto, Model model,
			final MultipartHttpServletRequest multiRequest, HttpSession session) {
		// 원래 존재하던 db내용을 삭제해서 등록 혹은 수정할때 분기하지 않도록 설계함 *231215 정민석
		dao.deleteSCList();
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		// 업로드한 파일을 맵에 담음 *231215 정민석
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		List<HashMap<String, String>> apply = null;
		// 파일 유틸에 메소드를 사용해서 엑셀에 내용을 리스트 변수에 담음 *231215 정민석
		// 이 리스트엔 맵이 담겨있고 그 맵엔 엑셀의 cell각각의 값이 담겨있음 *231215 정민석
		apply = fileUtil.parseExcelSpringMultiPart(files, "applicant", 0, "reserve");
		// 리스트에서 맵을꺼내고 그 맵에서 값을꺼냄 *231215 정민석
		// 리스트에 담겨있기에 맵에 키가 중복되어도 그 값은 중복되지않고 유지됨 *231215 정민석 
		for (int i = 0; i < apply.size(); i++) {
			scldto.setScode(apply.get(i).get("cell_0"));
			scldto.setName(apply.get(i).get("cell_1"));
			scldto.setFieldidx(apply.get(i).get("cell_2"));
			scldto.setPass_fail(apply.get(i).get("cell_3"));
			scldto.setCompanyIdx(companyIdx);
			scldto.setGonggoidx(gonggoidx);
			dao.insertSuccessfulList(scldto);
		}
		view = "redirect:successfulcandidateList";
		return view;
	}
	//	커스텀 발표에 등록될 지원자 목록 리스트 업로드 공간 위에 업로드와 동일하지만 사용하는 열의 수가 다름 *231215 정민석
	@PostMapping("customUpload")
	public String doCustomUpload(Model model, final MultipartHttpServletRequest multiRequest, HttpSession session,
			@RequestParam String title, @RequestParam String part) {
		Map<String, Object> company_gonggo = new HashMap<String, Object>();
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		company_gonggo.put("companyIdx", companyIdx);
		company_gonggo.put("gonggoidx", gonggoidx);
		company_gonggo.put("title", title);
		dao.deleteCustomList(company_gonggo);
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		List<HashMap<String, String>> apply = null;

		apply = fileUtil.parseExcelSpringMultiPart(files, "applicant", 0, "reserve");
		for (int i = 0; i < apply.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("companyIdx", companyIdx);
			map.put("gonggoidx", gonggoidx);
			map.put("scode", apply.get(i).get("cell_0"));
			map.put("name", apply.get(i).get("cell_1"));
			map.put("fieldidx", apply.get(i).get("cell_2"));
			map.put("title", title);
			dao.insertAA(map);
		}
		model.addAttribute("title", title);
		model.addAttribute("part", part);
		view = "redirect:customWrite";
		return view;
	}
	
	// 맞춤설정 등록및 변경시 회사별, 전형별로 따로 설정하도록 설계 핵심부분은 위에 successfulcandidateList부분과 동일 *231215 정민석
	@GetMapping("customizeList")
	public String goCustomizeList(Model model, @RequestParam String part, HttpSession session) {
		Map<String, Object> company_part_name = new HashMap<String, Object>();
		List<CustomizeDTO> customize_list = null;
		String page = "";
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		company_part_name.put("companyIdx", companyIdx);
		company_part_name.put("gonggoidx", gonggoidx);
		company_part_name.put("part", part);
		company_part_name.put("name", "");
		customize_list = dao.getCustomizeList(company_part_name);
		for (CustomizeDTO dto : customize_list) {
			page += "<tr>";
			page += "<td>" + dto.getScode() + "</td>";
			page += "<td>" + dto.getName() + "</td>";
			page += "<td>" + dto.getFieldidx() + "</td>";
			page += "<td>" + dto.getColumn1() + "</td>";
			page += "<td>" + dto.getColumn2() + "</td>";
			page += "<td>" + dto.getColumn3() + "</td>";
			page += "</tr>";
		}
		model.addAttribute("part", part);
		model.addAttribute("page", page);
		model.addAttribute("gonggoidx", gonggoidx);
		view = "passfail/customizeList";
		return view;
	}

//	맞춤설정에 등록될 설정 리스트 업로드 공간 위에 업로드와 동일하지만 사용하는 열의 수가 다름 *231215 정민석
	@PostMapping("customizeUpload")
	public String doCustomizeUpload(Model model, final MultipartHttpServletRequest multiRequest, HttpSession session,
			@RequestParam String part) {
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		Map<String, Object> company_part = new HashMap<String, Object>();
		company_part.put("part", part);
		company_part.put("companyIdx", companyIdx);
		company_part.put("gonggoidx", gonggoidx);
		dao.deleteCustomizeList(company_part);
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		List<HashMap<String, String>> apply = null;

		apply = fileUtil.parseExcelSpringMultiPart(files, "applicant", 0, "reserve");
		for (int i = 0; i < apply.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("companyIdx", companyIdx);
			map.put("gonggoidx", gonggoidx);
			map.put("scode", apply.get(i).get("cell_0"));
			map.put("name", apply.get(i).get("cell_1"));
			map.put("fieldidx", apply.get(i).get("cell_2"));
			map.put("column1", apply.get(i).get("cell_3"));
			map.put("column2", apply.get(i).get("cell_4"));
			map.put("column3", apply.get(i).get("cell_5"));
			map.put("part", part);
			dao.insertCS(map);
		}
		model.addAttribute("part", part);
		view = "redirect:customizeList";
		return view;
	}



}
