package com.gyeonglodang.controller;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gyeonglodang.dto.ApplicantDTO;
import com.gyeonglodang.dto.GetCareerDTO;
import com.gyeonglodang.dto.GetCareerQDTO;
import com.gyeonglodang.dto.GetFileValueDTO;
import com.gyeonglodang.dto.GetFileValueFinalDTO;
import com.gyeonglodang.dto.GetInfoDTO;
import com.gyeonglodang.dto.GetInfoValueDTO;
import com.gyeonglodang.dto.GetInfoValueFinalDTO;
import com.gyeonglodang.dto.GetPersonalCustomDTO;
import com.gyeonglodang.dto.GetPersonalDTO;
import com.gyeonglodang.dto.GetPersonalQDTO;
import com.gyeonglodang.dto.GetResumeCustomDTO;
import com.gyeonglodang.dto.GetSentenceTypeDTO;
import com.gyeonglodang.dto.GetTableTypeDTO;
import com.gyeonglodang.dto.GetTableTypeQDTO;
import com.gyeonglodang.dto.InfoCustomDTO;
import com.gyeonglodang.dto.InfoCustomRadioDTO;
import com.gyeonglodang.dto.InfoCustomTextDTO;
import com.gyeonglodang.dto.JobNoticeDTO;
import com.gyeonglodang.dto.PersonalDTO;
import com.gyeonglodang.dto.ResumeFileUploadDTO;
import com.gyeonglodang.dto.ResumeFileUploadSetDTO;
import com.gyeonglodang.dto.RsmFileDTO;
import com.gyeonglodang.dto.PersonalAnswerDTO;
import com.gyeonglodang.service.RecruitResumeService;


@Controller
@RequestMapping("recruit_resume")
public class RecruitResumeContorller {
	@Autowired
	@Qualifier("recruitResumeServiceImpl")
	RecruitResumeService service;
	
	
	@RequestMapping("main")
	public String main(HttpServletRequest request,Model model) throws ParseException{
		HttpSession session = request.getSession();
		int companyIdx = (int)session.getAttribute("companyIdx");	//session의 companyIdx
		List<JobNoticeDTO> list = service.getTitle(companyIdx);
		
		Date now = new Date();
		
		List<JobNoticeDTO> rsList = new ArrayList<JobNoticeDTO>();
		
		
		for(JobNoticeDTO dto : list) {
			String postingDate = dto.getJobNotice_postingDate();	//postingDate 가져와서 담은 변수
			String postingStartLine = postingDate.split("~")[0];	//게시시작기간 가져와서 담은 변수
			String postingDeadLine = postingDate.split("~")[1];		//게시종료기간 가져와서 담은 변수
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	//날짜 변환하는 객체 
			
			Date formatDate = sdf.parse(postingDeadLine);	//String 타입 데이터를 Date 타입으로 변경
			Date formatDate1 = sdf.parse(postingStartLine);	//String 타입 데이터를 Date 타입으로 변경
			
			
			if(now.before(formatDate) && now.after(formatDate1) && dto.getJobNotice_operation().equals("y")) {
				rsList.add(dto);
			}
		}
		model.addAttribute("rsList", rsList);
		
		return "recruit_resume/main";
	}
	
	
	@RequestMapping("applicant")
	public String applicant(ApplicantDTO dto, HttpServletRequest request,Model model, HttpSession session) throws ParseException {
		String viewName = "";	//이동시킬 페이지를 담을 변수 viewName
		Map<String,Object> getemail = new HashMap<String, Object>();
		getemail.put("email", dto.getEmail());
		getemail.put("gonggoidx", dto.getGonggoidx());
		ApplicantDTO rs = service.getApplicant2(getemail);	//전달 받은 dto의 유무를 알아내는 변수 rs 
		session = request.getSession();
		session.setAttribute("gonggoidx", dto.getGonggoidx());
		model.addAttribute("dto",dto);
		int insertRs = 0;	//insert 유무를 확인할 수 있는 int형 데이터를 담는 변수 insertRs
		if(rs != null) {	//전달받은 dto가 있으면
			viewName = "redirect:applicationSearch";	//recruit_resume/applicationList로 이동
		} else if (rs == null){		//전달받은 dto가 null이면
			insertRs = service.insertApplicant(dto);	//전달받은 dto를 insert
			model.addAttribute("insertRs", insertRs);
			session.setAttribute("applicant_email", dto.getEmail());
			viewName = "redirect:applicationList";
		}
		
		return viewName;
	}
	
	
	@RequestMapping("applicationSearch")
	public String applicationSearch(HttpServletRequest request, Model model) throws ParseException {
		System.out.println("serch 실행");
		HttpSession session = request.getSession();
		int companyIdx = (int)session.getAttribute("companyIdx");	//session의 companyIdx
		List<JobNoticeDTO> list = service.getTitle(companyIdx);
		Date now = new Date();
		
		List<JobNoticeDTO> rsList = new ArrayList<JobNoticeDTO>();
		
		
		for(JobNoticeDTO jbdto : list) {
			String postingDate = jbdto.getJobNotice_postingDate();	//postingDate 가져와서 담은 변수
			String postingStartLine = postingDate.split("~")[0];	//게시시작기간 가져와서 담은 변수
			String postingDeadLine = postingDate.split("~")[1];		//게시종료기간 가져와서 담은 변수
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	//날짜 변환하는 객체 
			
			Date formatDate = sdf.parse(postingDeadLine);	//String 타입 데이터를 Date 타입으로 변경
			Date formatDate1 = sdf.parse(postingStartLine);	//String 타입 데이터를 Date 타입으로 변경
			
			if(now.before(formatDate) && now.after(formatDate1) && jbdto.getJobNotice_operation().equals("y")) {
				rsList.add(jbdto);
			}
		}
		model.addAttribute("rsList", rsList);
		
		
		
		return "recruit_resume/applicationSearch";
	}
	
	@RequestMapping("applicationSubmit")
	public String application_Submit(HttpServletRequest request, Model model, ApplicantDTO dto,HttpSession session) {
		String view="";
		String email=dto.getEmail();
		String password = dto.getPassword();
		int gonggoidx= dto.getGonggoidx();
		session.setAttribute("applicant_email", email);
		session.setAttribute("gonggoidx", gonggoidx);
		dto = service.getApplicant(email);
		if(dto != null) {
			if(password.equals(dto.getPassword())) {				
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("gonggoidx", gonggoidx);
				map.put("email", email);
				int scode=0;
				String field=null;
				Map<String,Object> categ_scode= service.getCategScode(map);
				if(categ_scode!=null) {
					if(categ_scode.get("scode")!=null) {
						scode = (int)categ_scode.get("scode");
						field = (String)categ_scode.get("field");
					}else if(categ_scode.get("field")!=null) {
						field = (String)categ_scode.get("field");
					}
				}
				ApplicantDTO apDto=service.getApplicant2(map);
				String getScode = Integer.toString(apDto.getScode());
				
				if(!getScode.equals("0")) {
					System.out.println("submitScode: "+scode);
					model.addAttribute("scode",scode);
					model.addAttribute("noticeControl_idx",gonggoidx);
					model.addAttribute("recruitCateg_categ",field);
					view = "redirect:application_final";
				}else {
					if(field!=null) {
						model.addAttribute("noticeControl_idx",gonggoidx);
						model.addAttribute("recruitCateg_categ",field);
						view = "redirect:application";
					}else {
						ApplicantDTO apdto =service.getApplicant2(map);
						model.addAttribute("dto",apdto);
						model.addAttribute("insertRs",1);
						view = "redirect:applicationList";
					}
				}
			} else {
				model.addAttribute("rs",0);
				return "redirect:applicationSearch";
			}
		 } else {
			model.addAttribute("rs",0);
			return "redirect:applicationSearch";
		 }
		
		return view;
	}
	
	@RequestMapping("applicationList")
	public String applicationList(HttpServletRequest request, Model model, ApplicantDTO dto, @RequestParam int insertRs) throws ParseException {
		String viewName = "";
		HttpSession session = request.getSession();
		int companyIdx = (int)session.getAttribute("companyIdx");
		int gonggoidx = (int)session.getAttribute("gonggoidx");
		List<JobNoticeDTO> rsList = new ArrayList<JobNoticeDTO>();
		
		Date now = new Date();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("gonggoidx", gonggoidx);
		map.put("companyIdx", companyIdx);
		if(insertRs == 1) {		//insert 성공시
			List<JobNoticeDTO> list = service.getJobNoticeList(map);
			System.out.println("채용분야리스트 : "+list);
			for(JobNoticeDTO jbdto : list) {
				String postingDate = jbdto.getJobNotice_postingDate();	//postingDate 가져와서 담은 변수
				String postingStartLine = postingDate.split("~")[0];	//게시시작기간 가져와서 담은 변수
				String postingDeadLine = postingDate.split("~")[1];		//게시종료기간 가져와서 담은 변수
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	//날짜 변환하는 객체 
				
				Date formatDate = sdf.parse(postingDeadLine);	//String 타입 데이터를 Date 타입으로 변경
				Date formatDate1 = sdf.parse(postingStartLine);	//String 타입 데이터를 Date 타입으로 변경
				
				if(now.before(formatDate) && now.after(formatDate1) && jbdto.getJobNotice_operation().equals("y")) {
					rsList.add(jbdto);
				}
			}
			
			model.addAttribute("jobnoticelist", rsList);
			
			
			viewName =  "recruit_resume/applicationList";	//recruit_resume/application으로 이동
			
		} else {
			
			System.out.println("Insert is Fail");
			
		}
		
		return viewName;
	}
	@GetMapping("/application")
	public String application(HttpServletRequest request, @RequestParam int noticeControl_idx, @RequestParam String recruitCateg_categ, Model model, GetInfoDTO infodto) {
		
		//noticeControl_idx를 가져와서 jobNotice_title을 가져오는 소스
		HttpSession session = request.getSession();
		session.setAttribute("gonggoidx", noticeControl_idx);
		String jobNotice_title = service.getJBTittle(noticeControl_idx);
		String applicant_email = (String)session.getAttribute("applicant_email");
		Map<String,Object> applicant_info = new HashMap<String, Object>();
		applicant_info.put("email", applicant_email);
		applicant_info.put("gonggoidx", noticeControl_idx);
		Map<String,Object> application_info = new HashMap<String, Object>();
		application_info.put("gonggoidx", noticeControl_idx);
		application_info.put("recruitCateg_categ", recruitCateg_categ);
		
		
//		각 파트의 항목생성에 필요한 리스트를 생성하기위해 필요한 idx를 가져오는 소스
		List<Integer> infocustomidx_list = service.getInfoCustomIdxList(application_info);
		List<Integer> personalidx_list = service.getPersonalIdxList(application_info);
		List<Integer> careeridx_list = service.getCareerIdxList(application_info);
		List<Integer> resumecustomidx_list = service.getResumeCustomIdxList(application_info);
		List<Integer> fileidx_list = service.getFileIdxList(application_info);
		application_info.put("email", applicant_email);
		service.updateField(application_info);
		
		//===============================================
		//==========지원자 저장값 확인하는 소스==========
		//===============================================
		//1. 자기소개서 저장값 확인
		List<String> personalValueList = service.getPersonalValueList(applicant_info);
		model.addAttribute("personalValueList",personalValueList);
		//2. 경력기술서 저장값 확인
		List<String> careerValueList = service.getCareerValueList(applicant_info);
		model.addAttribute("careerValueList",careerValueList);
		//3. 인적사항 커스텀 저장값 확인 
		List<String> infocustomValueList = service.getInfoCustomValueList(applicant_info);
		model.addAttribute("infocustomValueList",infocustomValueList);
		//4. 단문형 저장값 확인
		List<String> sentenceValueList = service.getSentenceValueList(applicant_info);
		model.addAttribute("sentenceValueList",sentenceValueList);
		//5. 테이블 저장값 확인
		List<String> tableValueList = service.gettableValueList(applicant_info);
		model.addAttribute("tableValueList",tableValueList);
		//6. 인적사항 저장값 확인
		GetInfoValueDTO infoValueDTO = service.getinfoValue(applicant_info);
		if(infoValueDTO!=null) {
			model.addAttribute("infoValueDTO",infoValueDTO);
		}
		//7. 파일 저장값 확인
		List<GetFileValueDTO> fileValueList = service.getFileValueList(applicant_info);
		model.addAttribute("fileValueList",fileValueList);
		//===============================================
		//==========양식을 가져오는 소스==========
		//===============================================
		model.addAttribute("recruitCateg_categ", recruitCateg_categ);
		model.addAttribute("jobNotice_title", jobNotice_title);
		
		//1. 자기소개 양식을 가져오는 소스
//		List<GetPersonalDTO> personalList = service.getPersonalList(noticeControl_idx);
		List<GetPersonalDTO> personalList = new ArrayList<GetPersonalDTO>();
		
		
		if(personalidx_list.size()!=0) {
			for(int personalidx: personalidx_list) {
				GetPersonalDTO personalDto = service.getPersonalList(personalidx);
				personalList.add(personalDto);
			}
		}
		
		
		List<GetPersonalQDTO> personalQList = service.getPersonalQList();
		
		
		
		//2. 경력기술서 양식을 가져오는 소스
//		List<GetCareerDTO> careerList = service.getCareerList(noticeControl_idx);
		
		List<GetCareerDTO> careerList = new ArrayList<GetCareerDTO>();
		if(careeridx_list.size()!=0) {
			for(int careeridx: careeridx_list) {
				GetCareerDTO careerDto = service.getCareerList(careeridx);
				careerList.add(careerDto);
			}
		}
		List<GetCareerQDTO> careerQList = service.getCareerQList();
		
		//3. 인적사항 커스텀 양식을 가져오는 소스 및 전송하는 소스
		
//		personalCustomList= service.getPersonalCustom(noticeControl_idx);
		List<GetPersonalCustomDTO> personalCustomList = new ArrayList<GetPersonalCustomDTO>();
		if(infocustomidx_list.size()!=0) {
			for(int infocustomidx: infocustomidx_list) {
				GetPersonalCustomDTO personalcustomDto = service.getPersonalCustom(infocustomidx);
				personalCustomList.add(personalcustomDto);
			}
		}
		
		int count=1;
		List<Object> infocustom_list = new ArrayList<Object>();
		List<Object> infocustom_check_list = new ArrayList<Object>();
		List<Object> infocustom_category_list = new ArrayList<Object>();
		List<Object> infocustom_pilsu_list = new ArrayList<Object>();
		
		for(GetPersonalCustomDTO dto:  personalCustomList) {
			List<Object> infonotice_list = new ArrayList<Object>();
			if(dto.getInfoCustomNotice().indexOf("$")!=-1) {
				String notices[] = dto.getInfoCustomNotice().split("\\$");
				for(String notice: notices) {
					infonotice_list.add(notice);
				}
				infocustom_list.add(infonotice_list);
				infocustom_check_list.add("List");
				
			}else {
				infocustom_list.add(dto.getInfoCustomNotice());
				
				infocustom_check_list.add("String");
			}
			infocustom_category_list.add(dto.getInfoCustomCategory());
			infocustom_pilsu_list.add(dto.getInfoCustomPilsu());
			
		}
		model.addAttribute("infocustom_pilsu_list",infocustom_pilsu_list);
		model.addAttribute("infocustom_category_list",infocustom_category_list);
		model.addAttribute("infocustom_check_list", infocustom_check_list);
		model.addAttribute("infoCustom_List", infocustom_list);
		model.addAttribute("noticeControl_idx", noticeControl_idx);
		
		
		//info를 가져오는 소스
		infodto = service.getInfo(noticeControl_idx);
		model.addAttribute("info_dto", infodto);
		//========================================================================
		//====================양식을 모델에 담아 전송하는 소스====================
		//========================================================================
		
		//1. 자기소개서
		model.addAttribute("personalList", personalList);
		model.addAttribute("personalQList", personalQList);
		//2. 경력기술서
		model.addAttribute("careerList", careerList);
		model.addAttribute("careerQList", careerQList);
		//3. 인적사항 커스텀
		model.addAttribute("infoCustom_List",infocustom_list);
		
		//4. 맞춤형 문단
		List<GetResumeCustomDTO> resumeCustom = new ArrayList<GetResumeCustomDTO>();
		if(resumecustomidx_list.size()!=0) {
			for(int resumecustomidx: resumecustomidx_list) {
				GetResumeCustomDTO resumeCustomDto = service.getResumeCustom(resumecustomidx);
				resumeCustom.add(resumeCustomDto);
			}
		}
		
		model.addAttribute("resumecustom_list", resumeCustom);
		
		//5. 단문형 문단
		//맞춤형 문단의 개수대로 for문을 돌려야함
		List<Object> sentence_check_list_list = new ArrayList<Object>();
		List<Object> sentence_notice_list_list = new ArrayList<Object>();
		List<Object> sentence_type_list_list = new ArrayList<Object>();
		
		for(int i=0; i<resumeCustom.size(); i++) {
			//resumeCustom의 idx 가져오기
			int resumeCustomIdx = resumeCustom.get(i).getIdx();
			//list를 사용해서 각각의 resumeCustomIdx의 데이터들을 model에 저장하여 뷰단으로 전송
			List<GetSentenceTypeDTO> sentenceTypeList =  service.getSentenceType(resumeCustomIdx);
			List<Object> sentence_check_list =  new ArrayList<Object>();
			List<Object> sentence_notice_list = new ArrayList<Object>();
			count = 0;
			for(GetSentenceTypeDTO dto: sentenceTypeList) {
				List<Object> sentencenotice_list = new ArrayList<Object>();
				if(dto.getSentenceNotice().indexOf("$")!=-1) {	//sentenceNotice에 $문자가 포함이 되어있으면(input type="radio" 일때)
					String notices[] = dto.getSentenceNotice().split("\\$");	//$을 기준으로 나눠 배열에 담아줌
					for(String notice: notices) {	//배열에 담은 것들을 map에 넣어줌
						sentencenotice_list.add(notice);
					}
					sentence_notice_list.add(sentencenotice_list);
					sentence_check_list.add("List");
				}else {
					sentence_notice_list.add(dto.getSentenceNotice());
					sentence_check_list.add("String");
				}
				count++;
			}
			sentence_check_list_list.add(sentence_check_list);
			sentence_notice_list_list.add(sentence_notice_list);
			sentence_type_list_list.add(sentenceTypeList);
		}
		model.addAttribute("sentence_type_list_list", sentence_type_list_list);
		model.addAttribute("sentence_notice_list_list", sentence_notice_list_list);
		model.addAttribute("sentence_check_list_list", sentence_check_list_list);
		
		//6. 테이블형 문단
		//테이블은 tableType과 tableTypeq로 구성되어있기 때문에 두개를 한번에 보내줘야함
		List<Object> tabletype_list_list = new ArrayList<Object>();
		for(int i=0; i<resumeCustom.size(); i++) {
			//resumeCustom의 idx 가져오기
			int resumeCustomIdx = resumeCustom.get(i).getIdx();
			//tabletype 가져와서 모델에 담아주기
			List<GetTableTypeDTO> tableTypeList = service.getTableTypeList(resumeCustomIdx);
			tabletype_list_list.add(tableTypeList);
			//tabletypeq 가져와서 model에 담아주기
		}
		List<GetTableTypeQDTO> tableTypeQList = service.getTableTypeQListAll();
		model.addAttribute("tabletype_list_list", tabletype_list_list);
		model.addAttribute("tableTypeQ_list",tableTypeQList);
		
//		7. 파일문단
		List<ResumeFileUploadSetDTO> resumefile_list = new ArrayList<ResumeFileUploadSetDTO>();
		RsmFileDTO resumefileDTO = service.getResumeFile(noticeControl_idx);
		if(fileidx_list.size()!=0) {
			for(int fileidx: fileidx_list) {
				ResumeFileUploadSetDTO rsmfilesetDTO = service.getRsmFileUpload(fileidx);
				resumefile_list.add(rsmfilesetDTO);
			}
		}
		if(resumefileDTO!=null) {
			model.addAttribute("resumefileDTO",resumefileDTO);
		}
		model.addAttribute("resumefile_list",resumefile_list);
		
		return "recruit_resume/application";
	}
	
	


	@RequestMapping("insertApplication")
	public String insertApplication(
			HttpServletRequest request,
			HttpSession session,
			Model model,
			@RequestParam String recruitCateg_categ,
			@RequestParam Map<String, Object> map,
			@RequestParam(name="applicant_file",required=false) List<MultipartFile> file
			) throws Exception{
		session = request.getSession();
		String applicant_email = (String)session.getAttribute("applicant_email");
//		gonggoidx
		int noticeControl_idx = (int)session.getAttribute("gonggoidx");
//		원래있던 데이터 삭제하는소스
		Map<String, Object> applicant_info = new HashMap<String, Object>();
		applicant_info.put("email",applicant_email);
		applicant_info.put("gonggoidx",noticeControl_idx);
		service.deletePersonalValue(applicant_info);
		service.deleteCareerValue(applicant_info);
		service.deleteInfoCustomValue(applicant_info);
		service.deleteSentenceValue(applicant_info);
		service.deleteTableValue(applicant_info);
		service.deleteInfoValue(applicant_info);
		service.deleteResumeFile(applicant_info);
//		list들 가져오는 소스
		Map<String,Object> application_info = new HashMap<String, Object>();
		application_info.put("gonggoidx", noticeControl_idx);
		application_info.put("recruitCateg_categ", recruitCateg_categ);
		List<Integer> infocustomidx_list = service.getInfoCustomIdxList(application_info);
		List<Integer> personalidx_list = service.getPersonalIdxList(application_info);
		List<Integer> careeridx_list = service.getCareerIdxList(application_info);
		List<Integer> resumecustomidx_list = service.getResumeCustomIdxList(application_info);
		List<Integer> fileidx_list = service.getFileIdxList(application_info);
		
		List<GetPersonalDTO> personalList = new ArrayList<GetPersonalDTO>();
		
		if(personalidx_list.size()!=0) {
			for(int personalidx: personalidx_list) {
				GetPersonalDTO personalDto = service.getPersonalList(personalidx);
				personalList.add(personalDto);
			}
		}
		List<GetCareerDTO> careerList = new ArrayList<GetCareerDTO>();
		
		if(careeridx_list.size()!=0) {
			for(int careeridx: careeridx_list) {
				GetCareerDTO careerDto = service.getCareerList(careeridx);
				careerList.add(careerDto);
			}
		}
		List<GetPersonalCustomDTO> personalCustomList = new ArrayList<GetPersonalCustomDTO>();
		
		if(infocustomidx_list.size()!=0) {
			for(int infocustomidx: infocustomidx_list) {
				GetPersonalCustomDTO personalcustomDto = service.getPersonalCustom(infocustomidx);
				personalCustomList.add(personalcustomDto);
			}
		}
		List<GetResumeCustomDTO> resumeCustom = new ArrayList<GetResumeCustomDTO>();
		
		if(resumecustomidx_list.size()!=0) {
			for(int resumecustomidx: resumecustomidx_list) {
				GetResumeCustomDTO resumeCustomDto = service.getResumeCustom(resumecustomidx);
				resumeCustom.add(resumeCustomDto);
			}
		}
		
		// 1. 자기소개서 입력
//		List<GetPersonalDTO> personalList = service.getPersonalList(noticeControl_idx);
		List<GetPersonalQDTO> personalQList = service.getPersonalQList();
		int count1 = 1;
		int count2 = 1;
		for(GetPersonalDTO pDto : personalList) {
			for(GetPersonalQDTO pqDto : personalQList) {
				if(pqDto.getPersonalidx() == pDto.getIdx()) {
					String personal_value = (String)map.get("personalValue"+(count1)+""+(count2));
					Map<String, Object> insertMap = new HashMap<String, Object>();
					insertMap.put("email", applicant_email);
					insertMap.put("gonggoidx", noticeControl_idx);
					insertMap.put("value", personal_value);
					service.insertPersonalValue(insertMap);
				}
				count2++;
			}
			count1++;
		}
		// 2. 경력기술서 입력
//		service.insertCareer(careerQuestion, careerMaxW, careerAnswer);
//		List<GetCareerDTO> careerList = service.getCareerList(noticeControl_idx);
		List<GetCareerQDTO> careerQList = service.getCareerQList();
		count1 = 1;
		count2 = 1;
		for(GetCareerDTO cDto : careerList) {
			for(GetCareerQDTO cqDto : careerQList) {
				if(cqDto.getCareeridx() == cDto.getIdx()) {
					String career_value = (String)map.get("careerValue"+(count1)+""+(count2));
					Map<String, Object> insertMap = new HashMap<String, Object>();
					insertMap.put("email", applicant_email);
					insertMap.put("gonggoidx", noticeControl_idx);
					insertMap.put("value", career_value);
					service.insertCareerValue(insertMap);
				}
				count2++;
			}
			count1++;
		}
		// 3. 인적사항 항목 입력
//		List<GetPersonalCustomDTO> personalCustomList= service.getPersonalCustom(noticeControl_idx);
		for(int i=0; i< personalCustomList.size();i++){
		   String infocustom_value = (String)map.get("custom"+(i+1));
		   if(infocustom_value != null) {	//null인 값을 디비에 넣지 않기 위해서
			   Map<String, Object> insertMap = new HashMap<String, Object>();
			   insertMap.put("gonggoidx", noticeControl_idx);
			   insertMap.put("email", applicant_email);
			   insertMap.put("value", infocustom_value);
			   service.insert_infocustom(insertMap);
		   }
		}
		
		//4. 단문형 항목 입력
//		List<GetResumeCustomDTO> resumeCustom = service.getResumeCustom(noticeControl_idx);
		for(int i=0; i<resumeCustom.size(); i++) {
			int resumeCustomIdx = resumeCustom.get(i).getIdx(); 
			List<GetSentenceTypeDTO> sentenceTypeList =  service.getSentenceType(resumeCustomIdx);
			for(int j=0; j<sentenceTypeList.size(); j++) {
				Map<String, Object> insertMap = new HashMap<String, Object>();
				String value = (String)map.get("sentenceNotice"+(i+1)+"-"+(j+1));
				if(value!=null) {
					insertMap.put("gonggoidx", noticeControl_idx);
					insertMap.put("email", applicant_email);
					insertMap.put("value", value);
					
					service.insert_sentence(insertMap);
				}
			}
		}
		
		//5. 테이블형 항목 입력
		//--------------------------------------------
		//---------실질적으로 insert되는 부분---------
		//--------------------------------------------
		count1 = 0;
		for(GetResumeCustomDTO rcDto : resumeCustom) {
			System.out.println("resumeCustom.size(): "+count1);
			int resumeCustomIdx = resumeCustom.get(count1).getIdx();
			List<GetTableTypeDTO> tableTypeList = service.getTableTypeList(resumeCustomIdx);
			for(int j=0; j<tableTypeList.size(); j++) {
				//System.out.println("tableTypeList.size(): "+j);
				//System.out.println("j: "+j);
				List<GetTableTypeQDTO> tableTypeQList = service.getTableTypeQListAll();
				for(int k=0; k<tableTypeQList.size(); k++) {
					String value = (String)map.get("tableText"+(count1+1)+"-"+(k));
					if(value!=null) {
						System.out.println("value: "+value);
						Map<String, Object> insertMap = new HashMap<String, Object>();
						insertMap.put("gonggoidx", noticeControl_idx);
						insertMap.put("email", applicant_email);
						insertMap.put("value", value);
						service.insert_table(insertMap);
					}
					//System.out.println("k: "+k);
					//System.out.println("insert가 되는 value: "+value);
					//System.out.println("j-k: "+(j+1)+"-"+(k+1));
				}
				
			}
			count1++;
		}
		
		//6. 인적사항(기본정보) 입력
		Map<String, Object> infoMap = new HashMap<String, Object>();
		
		String name = (String)map.get("name");
		if(name == null) {
			name="";
		}
		
		infoMap.put("name", name);
		
		//birth 부분
		String year = (String)map.get("year");
		String month = (String)map.get("month");
		String date = (String)map.get("date");
		
		if(year == null) {
			year = "";
			month = "";
			date = "";
		}
		
		infoMap.put("year", year);
		infoMap.put("month", month);
		infoMap.put("date", date);
		
		//address 부분
		String zipcode = (String)map.get("zipcode");
		String roadname = (String)map.get("roadname");
		String detailaddress = (String)map.get("detailaddress");
		if(zipcode == null) {
			zipcode = "";
			roadname = "";
			detailaddress = "";
		}
		infoMap.put("zipcode", zipcode);
		infoMap.put("roadname", roadname);
		infoMap.put("detail_address", detailaddress);
		
		
		//phonenumber 부분
		String frontnum = (String)map.get("frontnum");
		String middlenum = (String)map.get("middlenum");
		String backnum = (String)map.get("backnum");
		
		String em_frontnum = (String)map.get("em_frontnum");
		String em_middlenum = (String)map.get("em_middlenum");
		String em_backnum = (String)map.get("em_backnum");
		
		if(frontnum == null) {
			frontnum="";
			middlenum="";
			backnum="";
			em_frontnum="";
			em_middlenum="";
			em_backnum="";
		}
		
		infoMap.put("phone_frontnum", frontnum);
		infoMap.put("phone_middelnum", middlenum);
		infoMap.put("phone_backnum", backnum);
		infoMap.put("emergency_frontnum", em_frontnum);
		infoMap.put("emergency_middlenum", em_middlenum);
		infoMap.put("emergency_backnum", em_backnum);
		
		String lowerclass = (String)map.get("lowerclass");
		if(lowerclass==null) {
			lowerclass="";
		}
		
		infoMap.put("lowerclass", lowerclass);
		
		String disabled = (String)map.get("disabled");
		if(disabled==null) {
			disabled="";
		}
		
		infoMap.put("disabled", disabled);
		
		String army = (String)map.get("army");
		if(army==null) {
			army="";
		}
		
		infoMap.put("army", army);
		
		String youth = (String)map.get("youth");
		if(youth==null) {
			youth="";
		
		}
		infoMap.put("youth", youth);
		
		infoMap.put("gonggoidx", noticeControl_idx);
		infoMap.put("email", applicant_email);
		service.insert_info(infoMap);
		
		
		if(file!=null) {
//		경로수정예정
			String uploadPath = "/Users/dpqbw/Desktop/k-digital/spring2/springws/gyeonglodang/src/main/webapp/resources/uploadfile";
			
			
			
			
			Map<String, Object>fileUploadmap = new HashMap<>();
			List<String> nameList = new ArrayList<>();
			for(int i=0; i<file.size(); i++) {   //반복문을 이용하여 nameList에 파일이름 추가하는 소스 
				nameList.add(file.get(i).getOriginalFilename());
			}
			
			List<String> sNList = new ArrayList<String>();
			String originFile = "";
			String ext = "";
			String now = "";
			String systemName = "";
			File oldFile = null;
			File newFile = null;
			File saveFile = null;
			fileUploadmap.put("email", applicant_email);
			fileUploadmap.put("gonggoidx", noticeControl_idx);
			fileUploadmap.put("uploadPath", uploadPath);
			for(int i = 0; i < nameList.size(); i++) {
				originFile = nameList.get(i);
				if(!originFile.equals("")) {
					ext = originFile.substring(originFile.lastIndexOf("."));
					now = new SimpleDateFormat("yyyMMdd_HmsS").format(new Date());
					systemName = now+"_"+i+ ext;
					sNList.add(systemName);
					oldFile = new File(uploadPath + File.separator + originFile);
					newFile = new File(uploadPath + File.separator + systemName);
					oldFile.renameTo(newFile);
					fileUploadmap.put("Original_Name", originFile);
					service.insertResumeFile(fileUploadmap);
				}
			}
			for(int i = 0; i < file.size(); i++) {
				if(sNList.size()!=0) {
					saveFile = new File(uploadPath, sNList.get(i));
					file.get(i).transferTo(saveFile);
				}
			}
		}
		
	    
		
		
		
		model.addAttribute("noticeControl_idx", noticeControl_idx);
		model.addAttribute("recruitCateg_categ", recruitCateg_categ);
		
		
		
		return "redirect: application";
	}
	
	
	@GetMapping("/application_final")
	public String application_final(HttpServletRequest request, @RequestParam int noticeControl_idx, @RequestParam String recruitCateg_categ, Model model, GetInfoDTO infodto
			, @RequestParam int scode) {
		//noticeControl_idx를 가져와서 jobNotice_title을 가져오는 소스
				HttpSession session = request.getSession();
				session.setAttribute("gonggoidx", noticeControl_idx);
				String jobNotice_title = service.getJBTittle(noticeControl_idx);
//				수험번호 (수정예정)
				Map<String,Object> applicant_info = new HashMap<String, Object>();
				applicant_info.put("scode", scode);
				applicant_info.put("gonggoidx", noticeControl_idx);
				Map<String,Object> application_info = new HashMap<String, Object>();
				application_info.put("gonggoidx", noticeControl_idx);
				application_info.put("recruitCateg_categ", recruitCateg_categ);
				
				
				
//				각 파트의 항목생성에 필요한 리스트를 생성하기위해 필요한 idx를 가져오는 소스
				List<Integer> infocustomidx_list = service.getInfoCustomIdxList(application_info);
				List<Integer> personalidx_list = service.getPersonalIdxList(application_info);
				List<Integer> careeridx_list = service.getCareerIdxList(application_info);
				List<Integer> resumecustomidx_list = service.getResumeCustomIdxList(application_info);
				List<Integer> fileidx_list = service.getFileIdxList(application_info);
				//===============================================
				//==========지원자 저장값 확인하는 소스==========
				//===============================================
				//1. 자기소개서 저장값 확인
				List<String> personalValueList = service.getPersonalValueList_final(applicant_info);
				model.addAttribute("personalValueList",personalValueList);
				//2. 경력기술서 저장값 확인
				List<String> careerValueList = service.getCareerValueList_final(applicant_info);
				model.addAttribute("careerValueList",careerValueList);
				//3. 인적사항 커스텀 저장값 확인 
				List<String> infocustomValueList = service.getInfoCustomValueList_final(applicant_info);
				model.addAttribute("infocustomValueList",infocustomValueList);
				//4. 단문형 저장값 확인
				List<String> sentenceValueList = service.getSentenceValueList_final(applicant_info);
				model.addAttribute("sentenceValueList",sentenceValueList);
				//5. 테이블 저장값 확인
				List<String> tableValueList = service.gettableValueList_final(applicant_info);
				System.out.println("applicant_info: "+applicant_info);
				model.addAttribute("tableValueList",tableValueList);
				System.out.println("hh: "+tableValueList);
				//6. 인적사항 저장값 확인
				GetInfoValueFinalDTO infoValueDTO = service.getinfoValue_final(applicant_info);
				
				if(infoValueDTO!=null) {
					model.addAttribute("infoValueDTO",infoValueDTO);
				}
				System.out.println("hh:"+infoValueDTO);
				//7. 파일 저장값 확인
				List<GetFileValueFinalDTO> fileValueList = service.getFileValueList_final(applicant_info);
				model.addAttribute("fileValueList",fileValueList);
				System.out.println(fileValueList);
				
				//===============================================
				//==========양식을 가져오는 소스==========
				//===============================================
				model.addAttribute("recruitCateg_categ", recruitCateg_categ);
				model.addAttribute("jobNotice_title", jobNotice_title);
				
				//1. 자기소개 양식을 가져오는 소스
				
//				List<GetPersonalDTO> personalList = service.getPersonalList(noticeControl_idx);
				List<GetPersonalDTO> personalList = new ArrayList<GetPersonalDTO>();
				
				if(personalidx_list.size()!=0) {
					for(int personalidx: personalidx_list) {
						GetPersonalDTO personalDto = service.getPersonalList(personalidx);
						personalList.add(personalDto);
					}
				}
				
				
				List<GetPersonalQDTO> personalQList = service.getPersonalQList();
				String personalTitle = personalList.get(0).getPersonalTitle();
				String personalNotice = personalList.get(0).getPersonalNotice();
				
				
				
				//2. 경력기술서 양식을 가져오는 소스
//				List<GetCareerDTO> careerList = service.getCareerList(noticeControl_idx);
				
				List<GetCareerDTO> careerList = new ArrayList<GetCareerDTO>();
				
				if(careeridx_list.size()!=0) {
					for(int careeridx: careeridx_list) {
						GetCareerDTO careerDto = service.getCareerList(careeridx);
						careerList.add(careerDto);
					}
				}
				List<GetCareerQDTO> careerQList = service.getCareerQList();
				
				String careerTitle = careerList.get(0).getCareerTitle();
				String careerNotice = careerList.get(0).getCareerNotice();
				
				//3. 인적사항 커스텀 양식을 가져오는 소스 및 전송하는 소스
				
//				personalCustomList= service.getPersonalCustom(noticeControl_idx);
				List<GetPersonalCustomDTO> personalCustomList = new ArrayList<GetPersonalCustomDTO>();
				
				if(infocustomidx_list.size()!=0) {
					for(int infocustomidx: infocustomidx_list) {
						GetPersonalCustomDTO personalcustomDto = service.getPersonalCustom(infocustomidx);
						personalCustomList.add(personalcustomDto);
					}
				}
				
				
				int count=1;
				List<Object> infocustom_list = new ArrayList<Object>();
				List<Object> infocustom_check_list = new ArrayList<Object>();
				List<Object> infocustom_category_list = new ArrayList<Object>();
				List<Object> infocustom_pilsu_list = new ArrayList<Object>();
				
				for(GetPersonalCustomDTO dto:  personalCustomList) {
					List<Object> infonotice_list = new ArrayList<Object>();
					if(dto.getInfoCustomNotice().indexOf("$")!=-1) {
						String notices[] = dto.getInfoCustomNotice().split("\\$");
						for(String notice: notices) {
							infonotice_list.add(notice);
						}
						infocustom_list.add(infonotice_list);
						infocustom_check_list.add("List");
						
					}else {
						infocustom_list.add(dto.getInfoCustomNotice());
						
						infocustom_check_list.add("String");
					}
					infocustom_category_list.add(dto.getInfoCustomCategory());
					infocustom_pilsu_list.add(dto.getInfoCustomPilsu());
					
				}
				model.addAttribute("infocustom_pilsu_list",infocustom_pilsu_list);
				model.addAttribute("infocustom_category_list",infocustom_category_list);
				model.addAttribute("infocustom_check_list", infocustom_check_list);
				model.addAttribute("infoCustom_List", infocustom_list);
				model.addAttribute("noticeControl_idx", noticeControl_idx);
				
				
				//info를 가져오는 소스
				infodto = service.getInfo(noticeControl_idx);
				model.addAttribute("info_dto", infodto);
				//========================================================================
				//====================양식을 모델에 담아 전송하는 소스====================
				//========================================================================
				
				//1. 자기소개서
				model.addAttribute("personalList", personalList);
				model.addAttribute("personalQList", personalQList);
				model.addAttribute("personalTitle", personalTitle);
				model.addAttribute("personalNotice", personalNotice);
				//2. 경력기술서
				model.addAttribute("careerTitle", careerTitle);
				model.addAttribute("careerNotice", careerNotice);
				model.addAttribute("careerList", careerList);
				model.addAttribute("careerQList", careerQList);
				//3. 인적사항 커스텀
				model.addAttribute("infoCustom_List",infocustom_list);
				
				//4. 맞춤형 문단
				
				
//				List<GetResumeCustomDTO> resumeCustom = service.getResumeCustom(noticeControl_idx);
				List<GetResumeCustomDTO> resumeCustom = new ArrayList<GetResumeCustomDTO>();
				
				if(resumecustomidx_list.size()!=0) {
					for(int resumecustomidx: resumecustomidx_list) {
						GetResumeCustomDTO resumeCustomDto = service.getResumeCustom(resumecustomidx);
						resumeCustom.add(resumeCustomDto);
					}
				}
				
				model.addAttribute("resumecustom_list", resumeCustom);
				
				//5. 단문형 문단
				//맞춤형 문단의 개수대로 for문을 돌려야함
				List<Object> sentence_check_list_list = new ArrayList<Object>();
				List<Object> sentence_notice_list_list = new ArrayList<Object>();
				List<Object> sentence_type_list_list = new ArrayList<Object>();
				
				for(int i=0; i<resumeCustom.size(); i++) {
					//resumeCustom의 idx 가져오기
					int resumeCustomIdx = resumeCustom.get(i).getIdx();
					//list를 사용해서 각각의 resumeCustomIdx의 데이터들을 model에 저장하여 뷰단으로 전송
					List<GetSentenceTypeDTO> sentenceTypeList =  service.getSentenceType(resumeCustomIdx);
					List<Object> sentence_check_list =  new ArrayList<Object>();
					List<Object> sentence_notice_list = new ArrayList<Object>();
					count = 0;
					for(GetSentenceTypeDTO dto: sentenceTypeList) {
						List<Object> sentencenotice_list = new ArrayList<Object>();
						if(dto.getSentenceNotice().indexOf("$")!=-1) {	//sentenceNotice에 $문자가 포함이 되어있으면(input type="radio" 일때)
							String notices[] = dto.getSentenceNotice().split("\\$");	//$을 기준으로 나눠 배열에 담아줌
							for(String notice: notices) {	//배열에 담은 것들을 map에 넣어줌
								sentencenotice_list.add(notice);
							}
							sentence_notice_list.add(sentencenotice_list);
							sentence_check_list.add("List");
						}else {
							sentence_notice_list.add(dto.getSentenceNotice());
							sentence_check_list.add("String");
						}
						count++;
					}
					sentence_check_list_list.add(sentence_check_list);
					sentence_notice_list_list.add(sentence_notice_list);
					sentence_type_list_list.add(sentenceTypeList);
				}
				model.addAttribute("sentence_type_list_list", sentence_type_list_list);
				model.addAttribute("sentence_notice_list_list", sentence_notice_list_list);
				model.addAttribute("sentence_check_list_list", sentence_check_list_list);
				
				//6. 테이블형 문단
				//테이블은 tableType과 tableTypeq로 구성되어있기 때문에 두개를 한번에 보내줘야함
				List<Object> tabletype_list_list = new ArrayList<Object>();
				for(int i=0; i<resumeCustom.size(); i++) {
					//resumeCustom의 idx 가져오기
					int resumeCustomIdx = resumeCustom.get(i).getIdx();
					//tabletype 가져와서 모델에 담아주기
					List<GetTableTypeDTO> tableTypeList = service.getTableTypeList(resumeCustomIdx);
					tabletype_list_list.add(tableTypeList);
					//tabletypeq 가져와서 model에 담아주기
				}
				List<GetTableTypeQDTO> tableTypeQList = service.getTableTypeQListAll();
				model.addAttribute("tabletype_list_list", tabletype_list_list);
				model.addAttribute("tableTypeQ_list",tableTypeQList);
				
//				7. 파일문단
				List<ResumeFileUploadSetDTO> resumefile_list = new ArrayList<ResumeFileUploadSetDTO>();
				RsmFileDTO resumefileDTO = service.getResumeFile(noticeControl_idx);
				
				if(fileidx_list.size()!=0) {
					for(int fileidx: fileidx_list) {
						ResumeFileUploadSetDTO rsmfilesetDTO = service.getRsmFileUpload(fileidx);
						resumefile_list.add(rsmfilesetDTO);
					}
				}
				if(resumefileDTO!=null) {
					model.addAttribute("resumefileDTO",resumefileDTO);
				}
				model.addAttribute("resumefile_list",resumefile_list);
		
		return "recruit_resume/application_final";
	}
	
	@RequestMapping("insertFinal")
	public String insertFinal(
			HttpServletRequest request,
			HttpSession session,
			Model model,
			@RequestParam String recruitCateg_categ,
			@RequestParam Map<String, Object> map,
			@RequestParam(name="applicant_file",required=false) List<MultipartFile> file
	) throws Exception{
		session = request.getSession();
		String applicant_email = (String)session.getAttribute("applicant_email");
		System.out.println("sessionApplicant: "+applicant_email);                 
//		gonggoidx,companyidx
		int noticeControl_idx = (int)session.getAttribute("gonggoidx");
		System.out.println("noticeControl_idx: "+noticeControl_idx);
		int companyIdx = (int)session.getAttribute("companyIdx");
		System.out.println("companyIdx: "+companyIdx);
//		원래있던 데이터 삭제하는소스
		Map<String, Object> applicant_info = new HashMap<String, Object>();
		applicant_info.put("email",applicant_email);
		applicant_info.put("gonggoidx",noticeControl_idx);
//		service.deletePersonalValue(applicant_info);
//		service.deleteCareerValue(applicant_info);
//		service.deleteInfoCustomValue(applicant_info);
//		service.deleteSentenceValue(applicant_info);
//		service.deleteTableValue(applicant_info);
//		service.deleteInfoValue(applicant_info);
//		service.deleteResumeFile(applicant_info);
		
//		scode 생성하는소스 (추가예정)
		Map<String,Object> getfieldcode = new HashMap<String, Object>();
		getfieldcode.put("gonggoidx",noticeControl_idx);
		getfieldcode.put("recruitCateg_categ",recruitCateg_categ);
		
		//채용분야코드
		String fieldcode=service.getFieldCode(getfieldcode);
		System.out.println("fieldcode: "+fieldcode);
		String countScode = service.getCountScode(noticeControl_idx);
		System.out.println(countScode);
		int icountScode=Integer.parseInt(countScode)+1;
		System.out.println("icountScode: "+icountScode);
		countScode = String.format("%06d", icountScode);
		System.out.println("countScode: "+countScode);
		String scode=fieldcode+countScode;
		System.out.println("scode: "+scode);
		
		applicant_info.put("scode", scode);
		service.updateScode(applicant_info);
		System.out.println("service.updateScode(applicant_info): "+service.updateScode(applicant_info));
		
//		list들 가져오는 소스
		Map<String,Object> application_info = new HashMap<String, Object>();
		application_info.put("gonggoidx", noticeControl_idx);
		application_info.put("recruitCateg_categ", recruitCateg_categ);
		List<Integer> infocustomidx_list = service.getInfoCustomIdxList(application_info);
		List<Integer> personalidx_list = service.getPersonalIdxList(application_info);
		List<Integer> careeridx_list = service.getCareerIdxList(application_info);
		List<Integer> resumecustomidx_list = service.getResumeCustomIdxList(application_info);
		List<Integer> fileidx_list = service.getFileIdxList(application_info);
		
		List<GetPersonalDTO> personalList = new ArrayList<GetPersonalDTO>();
		
		if(personalidx_list.size()!=0) {
			for(int personalidx: personalidx_list) {
				GetPersonalDTO personalDto = service.getPersonalList(personalidx);
				personalList.add(personalDto);
			}
		}
		List<GetCareerDTO> careerList = new ArrayList<GetCareerDTO>();
		
		if(careeridx_list.size()!=0) {
			for(int careeridx: careeridx_list) {
				GetCareerDTO careerDto = service.getCareerList(careeridx);
				careerList.add(careerDto);
			}
		}
		List<GetPersonalCustomDTO> personalCustomList = new ArrayList<GetPersonalCustomDTO>();
		
		if(infocustomidx_list.size()!=0) {
			for(int infocustomidx: infocustomidx_list) {
				GetPersonalCustomDTO personalcustomDto = service.getPersonalCustom(infocustomidx);
				personalCustomList.add(personalcustomDto);
			}
		}
		List<GetResumeCustomDTO> resumeCustom = new ArrayList<GetResumeCustomDTO>();
		
		if(resumecustomidx_list.size()!=0) {
			for(int resumecustomidx: resumecustomidx_list) {
				GetResumeCustomDTO resumeCustomDto = service.getResumeCustom(resumecustomidx);
				resumeCustom.add(resumeCustomDto);
			}
		}
		
		// 1. 자기소개서 입력
//		List<GetPersonalDTO> personalList = service.getPersonalList(noticeControl_idx);
		List<GetPersonalQDTO> personalQList = service.getPersonalQList();
		int count1 = 1;
		int count2 = 1;
		for(GetPersonalDTO pDto : personalList) {
			for(GetPersonalQDTO pqDto : personalQList) {
				if(pqDto.getPersonalidx() == pDto.getIdx()) {
					String personal_value = (String)map.get("personalValue"+(count1)+""+(count2));
					Map<String, Object> insertMap = new HashMap<String, Object>();
					insertMap.put("scode", scode);
					insertMap.put("gonggoidx", noticeControl_idx);
					insertMap.put("value", personal_value);
					service.insertPersonalValue_final(insertMap);
				}
				count2++;
			}
			count1++;
		}
		// 2. 경력기술서 입력
//		service.insertCareer(careerQuestion, careerMaxW, careerAnswer);
//		List<GetCareerDTO> careerList = service.getCareerList(noticeControl_idx);
		List<GetCareerQDTO> careerQList = service.getCareerQList();
		count1 = 1;
		count2 = 1;
		for(GetCareerDTO cDto : careerList) {
			for(GetCareerQDTO cqDto : careerQList) {
				if(cqDto.getCareeridx() == cDto.getIdx()) {
					String career_value = (String)map.get("careerValue"+(count1)+""+(count2));
					Map<String, Object> insertMap = new HashMap<String, Object>();
					insertMap.put("scode", scode);
					insertMap.put("gonggoidx", noticeControl_idx);
					insertMap.put("value", career_value);
					service.insertCareerValue_final(insertMap);
				}
				count2++;
			}
			count1++;
		}
		// 3. 인적사항 항목 입력
//		List<GetPersonalCustomDTO> personalCustomList= service.getPersonalCustom(noticeControl_idx);
		for(int i=0; i< personalCustomList.size();i++){
		   String infocustom_value = (String)map.get("custom"+(i+1));
		   if(infocustom_value != null) {	//null인 값을 디비에 넣지 않기 위해서
			   Map<String, Object> insertMap = new HashMap<String, Object>();
			   insertMap.put("gonggoidx", noticeControl_idx);
			   insertMap.put("scode", scode);
			   insertMap.put("value", infocustom_value);
			   service.insert_final_infocustom(insertMap);
		   }
		}
		
		//4. 단문형 항목 입력
//		List<GetResumeCustomDTO> resumeCustom = service.getResumeCustom(noticeControl_idx);
		for(int i=0; i<resumeCustom.size(); i++) {
			int resumeCustomIdx = resumeCustom.get(i).getIdx(); 
			List<GetSentenceTypeDTO> sentenceTypeList =  service.getSentenceType(resumeCustomIdx);
			for(int j=0; j<sentenceTypeList.size(); j++) {
				Map<String, Object> insertMap = new HashMap<String, Object>();
				String value = (String)map.get("sentenceNotice"+(i+1)+"-"+(j+1));
				if(value!=null) {
					insertMap.put("gonggoidx", noticeControl_idx);
					insertMap.put("scode", scode);
					insertMap.put("value", value);
					
					service.insert_final_sentence(insertMap);
				}
			}
		}
		
		//5. 테이블형 항목 입력
		count1 = 0;
		for(GetResumeCustomDTO rcDto : resumeCustom) {
			int resumeCustomIdx = resumeCustom.get(count1).getIdx();
			List<GetTableTypeDTO> tableTypeList = service.getTableTypeList(resumeCustomIdx);
			for(int j=0; j<tableTypeList.size(); j++) {
				//System.out.println("tableTypeList.size(): "+j);
				//System.out.println("j: "+j);
				List<GetTableTypeQDTO> tableTypeQList = service.getTableTypeQListAll();
				for(int k=0; k<tableTypeQList.size(); k++) {
					String value = (String)map.get("tableText"+(count1+1)+"-"+(k));
					if(value!=null) {
						System.out.println("value: "+value);
						Map<String, Object> insertMap = new HashMap<String, Object>();
						insertMap.put("gonggoidx", noticeControl_idx);
						insertMap.put("scode", scode);
						insertMap.put("value", value);
						service.insert_final_table(insertMap);
					}
					//System.out.println("k: "+k);
					//System.out.println("insert가 되는 value: "+value);
					//System.out.println("j-k: "+(j+1)+"-"+(k+1));
				}
				
			}
			count1++;
		}
		
		//6. 인적사항(기본정보) 입력
		Map<String, Object> infoMap = new HashMap<String, Object>();
		
		String name = (String)map.get("name");
		if(name == null) {
			name="";
		}
		
		infoMap.put("name", name);
		
		//birth 부분
		String year = (String)map.get("year");
		String month = (String)map.get("month");
		String date = (String)map.get("date");
		
		if(year == null) {
			year = "";
			month = "";
			date = "";
		}
		
		infoMap.put("year", year);
		infoMap.put("month", month);
		infoMap.put("date", date);
		
		//address 부분
		String zipcode = (String)map.get("zipcode");
		String roadname = (String)map.get("roadname");
		String detailaddress = (String)map.get("detailaddress");
		if(zipcode == null) {
			zipcode = "";
			roadname = "";
			detailaddress = "";
		}
		infoMap.put("zipcode", zipcode);
		infoMap.put("roadname", roadname);
		infoMap.put("detail_address", detailaddress);
		
		
		//phonenumber 부분
		String frontnum = (String)map.get("frontnum");
		String middlenum = (String)map.get("middlenum");
		String backnum = (String)map.get("backnum");
		
		String em_frontnum = (String)map.get("em_frontnum");
		String em_middlenum = (String)map.get("em_middlenum");
		String em_backnum = (String)map.get("em_backnum");
		
		if(frontnum == null) {
			frontnum="";
			middlenum="";
			backnum="";
			em_frontnum="";
			em_middlenum="";
			em_backnum="";
		}
		
		infoMap.put("phone_frontnum", frontnum);
		infoMap.put("phone_middelnum", middlenum);
		infoMap.put("phone_backnum", backnum);
		infoMap.put("emergency_frontnum", em_frontnum);
		infoMap.put("emergency_middlenum", em_middlenum);
		infoMap.put("emergency_backnum", em_backnum);
		
		String lowerclass = (String)map.get("lowerclass");
		if(lowerclass==null) {
			lowerclass="";
		}
		
		infoMap.put("lowerclass", lowerclass);
		
		String disabled = (String)map.get("disabled");
		if(disabled==null) {
			disabled="";
		}
		
		infoMap.put("disabled", disabled);
		
		String army = (String)map.get("army");
		if(army==null) {
			army="";
		}
		
		infoMap.put("army", army);
		
		String youth = (String)map.get("youth");
		if(youth==null) {
			youth="";
		
		}
		infoMap.put("youth", youth);
		
		infoMap.put("gonggoidx", noticeControl_idx);
		infoMap.put("scode", scode);
		service.insert_final_info(infoMap);
		
		if(file!=null) {
//			경로수정예정
				String uploadPath = "/Users/dpqbw/Desktop/k-digital/spring2/springws/gyeonglodang/src/main/webapp/resources/uploadfile";
				
				
				
				
				Map<String, Object>fileUploadmap = new HashMap<>();
				List<String> nameList = new ArrayList<>();
				for(int i=0; i<file.size(); i++) {   //반복문을 이용하여 nameList에 파일이름 추가하는 소스 
					nameList.add(file.get(i).getOriginalFilename());
				}
				
				List<String> sNList = new ArrayList<String>();
				String originFile = "";
				String ext = "";
				String now = "";
				String systemName = "";
				File oldFile = null;
				File newFile = null;
				File saveFile = null;
				fileUploadmap.put("scode", scode);
				fileUploadmap.put("gonggoidx", noticeControl_idx);
				fileUploadmap.put("uploadPath", uploadPath);
				for(int i = 0; i < nameList.size(); i++) {
					originFile = nameList.get(i);
					if(!originFile.equals("")) {
						ext = originFile.substring(originFile.lastIndexOf("."));
						now = new SimpleDateFormat("yyyMMdd_HmsS").format(new Date());
						systemName = now+"_"+i+ ext;
						sNList.add(systemName);
						oldFile = new File(uploadPath + File.separator + originFile);
						newFile = new File(uploadPath + File.separator + systemName);
						oldFile.renameTo(newFile);
						fileUploadmap.put("Original_Name", originFile);
						service.insertResumeFile_final(fileUploadmap);
					}
				}
				for(int i = 0; i < file.size(); i++) {
					if(sNList.size()!=0) {
						saveFile = new File(uploadPath, sNList.get(i));
						file.get(i).transferTo(saveFile);
					}
				}
			}
//		나중엔 지워야할부분(test용 소스)
		model.addAttribute("noticeControl_idx", noticeControl_idx);
		model.addAttribute("recruitCateg_categ", recruitCateg_categ);
		model.addAttribute("scode",scode);
		return "redirect:application_final";
	}
}              