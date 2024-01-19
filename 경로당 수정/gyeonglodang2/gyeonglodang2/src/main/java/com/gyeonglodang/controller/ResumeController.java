package com.gyeonglodang.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gyeonglodang.dto.CareerDTO;
import com.gyeonglodang.dto.CareerFieldSettingDTO;
import com.gyeonglodang.dto.CareerQDTO;
import com.gyeonglodang.dto.CustomFieldSettingDTO;
import com.gyeonglodang.dto.GetCareerDTO;
import com.gyeonglodang.dto.GetCareerQDTO;
import com.gyeonglodang.dto.GetInfoDTO;
import com.gyeonglodang.dto.GetPersonalCustomDTO;
import com.gyeonglodang.dto.GetPersonalDTO;
import com.gyeonglodang.dto.GetPersonalQDTO;
import com.gyeonglodang.dto.GetResumeCustomDTO;
import com.gyeonglodang.dto.GetSentenceTypeDTO;
import com.gyeonglodang.dto.GetTableTypeDTO;
import com.gyeonglodang.dto.GetTableTypeQDTO;
import com.gyeonglodang.dto.InfoCustomDTO;
import com.gyeonglodang.dto.InfoCustomFieldSettingDTO;
import com.gyeonglodang.dto.InfoDTO;
import com.gyeonglodang.dto.PersonalDTO;
import com.gyeonglodang.dto.PersonalFieldSettingDTO;
import com.gyeonglodang.dto.PersonalQDTO;
import com.gyeonglodang.dto.RecruitCategBoardDTO;
import com.gyeonglodang.dto.RecruitCateg_boardDTO;
import com.gyeonglodang.dto.ResumeCustomDTO;
import com.gyeonglodang.dto.ResumeFileDTO;
import com.gyeonglodang.dto.ResumeFileUploadDTO;
import com.gyeonglodang.dto.ResumeFileUploadSetDTO;
import com.gyeonglodang.dto.RsmFileDTO;
import com.gyeonglodang.dto.RsmFileUploadFieldSettingDTO;
import com.gyeonglodang.dto.SentenceTypeDTO;
import com.gyeonglodang.dto.TableTypeDTO;
import com.gyeonglodang.dto.TableTypeQDTO;
import com.gyeonglodang.service.RecruitResumeService;
import com.gyeonglodang.service.ResumeService;

@Controller
@RequestMapping("resume")
public class ResumeController {
	
	String view;
	
	@Autowired
	@Qualifier("resumeServiceImpl")
	 ResumeService service;
	
	@Autowired
	@Qualifier("recruitResumeServiceImpl")
	RecruitResumeService service2;
	
	@RequestMapping("main")
	public String main(Model model, HttpSession session, InfoDTO idto, InfoCustomDTO icdto, PersonalDTO pdto, CareerDTO cdto, ResumeCustomDTO rcdto, ResumeFileDTO rfdto) {
		
		// 세션 값 셋팅해주기 *231105 유준호
		int gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		
		// 인적사항 DTO 불러오기 *231105 유준호
		idto.setGonggoIdx(gonggoidx);
		idto = service.getInfo(idto);
		model.addAttribute("info_dto", idto);
		
		// 인적사항 custom List 불러오기 *231105 유준호
		icdto.setGonggoIdx(gonggoidx);
		List<InfoCustomDTO> icdtoList = service.getInfoCustom(icdto);
		model.addAttribute("infoCustom_list", icdtoList);
		
		// 자기소개 정보 DTO 불러오기 *231105 유준호
		pdto.setGonggoIdx(gonggoidx);
		pdto = service.getPersonal(pdto);
		model.addAttribute("personal_dto", pdto);
		
		// 자기소개 문항 List 불러오기 *231105 유준호
		List<PersonalQDTO> pqdtoList = service.getPersonalQ(pdto);
		model.addAttribute("personalQ_list", pqdtoList);
		
		// 경력기술서 정보 DTO 불러오기 *231105 유준호
		cdto.setGonggoIdx(gonggoidx);
		cdto = service.getCareer(cdto);
		model.addAttribute("career_dto", cdto);
		
		// 경력기술서 문항 List 불러오기 *231105 유준호
		List<CareerQDTO> cqdtoList = service.getCareerQ(cdto);
		model.addAttribute("careerQ_list", cqdtoList);
		
		// 맞춤형 문항 정보 List 불러오기 *231105 유준호
		rcdto.setGonggoIdx(gonggoidx);
		List<ResumeCustomDTO> rcdtoList = service.getResumeCustom(rcdto);
		model.addAttribute("resumeCustom_list", rcdtoList);
		
		// 맞춤형 문항 단문형 List 불러오기 *231105 유준호
		List<SentenceTypeDTO> stdtoList = service.getSentenceType(rcdto);
		model.addAttribute("sentenceType_list", stdtoList);
		
		// 맞춤형 문항 테이블형 정보 List 불러오기 *231105 유준호
		List<TableTypeDTO> ttdtoList = service.getTableType(rcdto);
		model.addAttribute("tableType_list", ttdtoList);
		
		// 맞춤형 문항 테이블형 문항 List 불러오기 *231105 유준호
		List<TableTypeQDTO> ttqdtoList = service.getTableTypeQ(rcdto);
		model.addAttribute("tableTypeQ_list", ttqdtoList);
		
		// rsmfile_dto *231105 유준호
		rfdto.setGonggoIdx(gonggoidx);
		rfdto = service.getResumeFile(rfdto);
		model.addAttribute("rsmfile_dto", rfdto);
		
		// rmsfileUpload_list *231105 유준호
		List<ResumeFileUploadSetDTO> rfusdtoList = service.getResumeFileUpload(rfdto);
		model.addAttribute("rsmfileUpload_list",rfusdtoList);
		
		view = "resume/main";
		
		return view;
	}
	
	@RequestMapping("preview")
	public String preview(
			HttpServletRequest request, 
			// @RequestParam String recruitCateg_categ, 
			RecruitCateg_boardDTO categ,
			Model model, 
			GetInfoDTO infodto,
			HttpSession session
			) {
		
		// 세션 값 셋팅해주기 *231107 유준호
		int igonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		String gonggoidx = Integer.toString(igonggoidx);
		
		// 드랍박스만들기 *231107 유준호
		categ.setNoticeControl_idx(gonggoidx);
		List<RecruitCateg_boardDTO> categList = service2.getField(categ);
		model.addAttribute("categList",categList);
		
		String recruitCateg_categ=null;
		if(categ.getRecruitCateg_categ()==null) {
		recruitCateg_categ= categList.get(0).getRecruitCateg_categ();
		}else {
		recruitCateg_categ=categ.getRecruitCateg_categ();
		}
		
		String jobNotice_title = service2.getJBTittle(igonggoidx);
		Map<String,Object> application_info = new HashMap<String, Object>();
		application_info.put("recruitCateg_categ", recruitCateg_categ);
		application_info.put("gonggoidx", gonggoidx);
		
		// 각 파트의 항목생성에 필요한 리스트를 생성하기위해 필요한 idx를 가져오는 소스 *231107 유준호
		List<Integer> infocustomidx_list = service2.getInfoCustomIdxList(application_info);
		List<Integer> personalidx_list = service2.getPersonalIdxList(application_info);
		List<Integer> careeridx_list = service2.getCareerIdxList(application_info);
		List<Integer> resumecustomidx_list = service2.getResumeCustomIdxList(application_info);
		List<Integer> fileidx_list = service2.getFileIdxList(application_info);
		
		model.addAttribute("recruitCateg_categ", recruitCateg_categ);
		model.addAttribute("jobNotice_title", jobNotice_title);
		
		// 1. 자기소개 양식을 가져오는 소스 *231107 유준호
		List<GetPersonalDTO> personalList = new ArrayList<GetPersonalDTO>();
		
		
		if(personalidx_list.size()!=0) {
			for(int personalidx: personalidx_list) {
				GetPersonalDTO personalDto = service2.getPersonalList(personalidx);
				personalList.add(personalDto);
			}
		}
		
		List<GetPersonalQDTO> personalQList = service2.getPersonalQList();
		
		// 2. 경력기술서 양식을 가져오는 소스 *231107 유준호
		
		List<GetCareerDTO> careerList = new ArrayList<GetCareerDTO>();
		if(careeridx_list.size()!=0) {
			for(int careeridx: careeridx_list) {
				GetCareerDTO careerDto = service2.getCareerList(careeridx);
				careerList.add(careerDto);
			}
		}
		List<GetCareerQDTO> careerQList = service2.getCareerQList();
		
		// 3. 인적사항 커스텀 양식을 가져오는 소스 및 전송하는 소스 *231107 유준호
		
		List<GetPersonalCustomDTO> personalCustomList = new ArrayList<GetPersonalCustomDTO>();
		if(infocustomidx_list.size()!=0) {
			for(int infocustomidx: infocustomidx_list) {
				GetPersonalCustomDTO personalcustomDto = service2.getPersonalCustom(infocustomidx);
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
		model.addAttribute("noticeControl_idx", gonggoidx);
		
		
		//info를 가져오는 소스
		infodto = service2.getInfo(igonggoidx);
		model.addAttribute("info_dto", infodto);
		//========================================================================
		//====================양식을 모델에 담아 전송하는 소스====================
		//========================================================================
		
		//1. 자기소개서
		model.addAttribute("personalList", personalList);
		model.addAttribute("personalQList", personalQList);
//		model.addAttribute("personalTitle", personalTitle);
//		model.addAttribute("personalNotice", personalNotice);
		//2. 경력기술서
//		model.addAttribute("careerTitle", careerTitle);
//		model.addAttribute("careerNotice", careerNotice);
		model.addAttribute("careerList", careerList);
		model.addAttribute("careerQList", careerQList);
		//3. 인적사항 커스텀
		model.addAttribute("infoCustom_List",infocustom_list);
		
		//4. 맞춤형 문단
		List<GetResumeCustomDTO> resumeCustom = new ArrayList<GetResumeCustomDTO>();
		if(resumecustomidx_list.size()!=0) {
			for(int resumecustomidx: resumecustomidx_list) {
				GetResumeCustomDTO resumeCustomDto = service2.getResumeCustom(resumecustomidx);
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
			List<GetSentenceTypeDTO> sentenceTypeList =  service2.getSentenceType(resumeCustomIdx);
			List<Object> sentence_check_list =  new ArrayList<Object>();
			List<Object> sentence_notice_list = new ArrayList<Object>();
			count = 0;
			for(GetSentenceTypeDTO dto: sentenceTypeList) {
				System.out.println("여기: "+dto.getSentenceNotice());
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
			System.out.println(sentence_notice_list_list);
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
			List<GetTableTypeDTO> tableTypeList = service2.getTableTypeList(resumeCustomIdx);
			tabletype_list_list.add(tableTypeList);
			//tabletypeq 가져와서 model에 담아주기
		}
		List<GetTableTypeQDTO> tableTypeQList = service2.getTableTypeQListAll();
		model.addAttribute("tabletype_list_list", tabletype_list_list);
		model.addAttribute("tableTypeQ_list",tableTypeQList);
		
//		7. 파일문단
		List<ResumeFileUploadSetDTO> resumefile_list = new ArrayList<ResumeFileUploadSetDTO>();
		RsmFileDTO resumefileDTO = service2.getResumeFile(igonggoidx);
		if(fileidx_list.size()!=0) {
			for(int fileidx: fileidx_list) {
				ResumeFileUploadSetDTO rsmfilesetDTO = service2.getRsmFileUpload(fileidx);
				resumefile_list.add(rsmfilesetDTO);
			}
		}

		if(resumefileDTO!=null) {
			model.addAttribute("resumefileDTO",resumefileDTO);
		}
		model.addAttribute("resumefile_list",resumefile_list);

	
		view = "resume/resumePreview";
		return view;				
	}
	
	
	@PostMapping("save")
	public String save(
			HttpSession session, 
			InfoDTO idto, 
			InfoCustomDTO icdto, 
			PersonalDTO pdto, 
			PersonalQDTO pqdto, 
			CareerDTO cdto, 
			CareerQDTO cqdto, 
			ResumeCustomDTO rcdto, 
			SentenceTypeDTO stdto, 
			TableTypeDTO ttdto, 
			TableTypeQDTO ttqdto,
			ResumeFileDTO rfdto,
			ResumeFileUploadDTO rfudto,
			@RequestParam(name ="file", required = false) List<MultipartFile> files,
			HttpServletRequest request,
			@RequestParam Map<String, Object> map
			) throws Exception  {
//		System.out.println("resumeController: "+stdto.getSentenceNotice());
//		System.out.println("resumeController: "+ttdto.getTableTitle());
//		System.out.println("resumeController: "+ttqdto.getTableCategory());
//		System.out.println("resumeController: "+ttqdto.getTableNotice());
//		System.out.println("resumeController: "+cqdto.getCareerQuestion());
//		System.out.println("save");
//		if(files!=null) {
//			System.out.println(files.toString());
//		}
//		System.out.println("controller map path : "+map.get("filePath1"));
//		System.out.println("controller map name : "+map.get("fileName1"));
//		세션 값 셋팅해주기
		int gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
//		int gonggoidx = 1;
		
		// 인적사항 DTO 불러오기
		idto.setGonggoIdx(gonggoidx);
		icdto.setGonggoIdx(gonggoidx);
		pdto.setGonggoIdx(gonggoidx);
		cdto.setGonggoIdx(gonggoidx);
		rcdto.setGonggoIdx(gonggoidx);
		rfdto.setGonggoIdx(gonggoidx);
		
		int rs = service.insertResume(idto, icdto, pdto, pqdto, cdto, cqdto, rcdto, stdto, ttdto, ttqdto, rfdto, rfudto, request, files, map);

//		System.out.println("resusmeController: 성공하면 1? "+rs);
		view = "redirect:main";
		return view;
	}
	
	@RequestMapping("download")
	@ResponseBody
	public ResponseEntity<byte[]> downloadFile(@RequestParam String filePath, @RequestParam String OfileName) throws Exception{
		System.out.println("fileDownload");
		System.out.println(filePath);
		System.out.println(filePath.substring(filePath.lastIndexOf("/")));
		System.out.println(OfileName);
		// 파일을 읽어 바이트 배열로 변환
		File file = new File(filePath);
		byte[] fileContent = Files.readAllBytes(file.toPath());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
        // 파일 이름을 UTF-8로 인코딩하여 Content-Disposition 헤더에 추가
//        String encodedFileName = new String(filePath.substring(filePath.lastIndexOf("/")).getBytes("UTF-8"), "ISO-8859-1");
        String encodedFileName = new String(OfileName.getBytes("UTF-8"), "ISO-8859-1");
        headers.setContentDispositionFormData("attachment", encodedFileName);
        
        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
	}
	
	@RequestMapping("resumeFieldSetting")
	public String resumeFieldSetting(
			Model model, 
			HttpSession session, 
			RecruitCategBoardDTO rcbdto, 
			InfoCustomDTO icdto, 
			PersonalDTO pdto, 
			CareerDTO cdto, 
			ResumeCustomDTO rcdto, 
			ResumeFileDTO rfdto,
			InfoCustomFieldSettingDTO icfsdto,
			PersonalFieldSettingDTO pfsdto,
			CareerFieldSettingDTO cfsdto,
			CustomFieldSettingDTO customsdto,
			RsmFileUploadFieldSettingDTO rfsdto
			) {
		
//		세션 값 셋팅해주기
		int gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
//		int gonggoidx = 1;
		
		// 인적사항 custom List 불러오기
		icdto.setGonggoIdx(gonggoidx);
		List<InfoCustomDTO> icdtoList = service.getInfoCustomField(icdto);
		model.addAttribute("infoCustom_list", icdtoList);
		
		icfsdto.setNoticeControl_idx(gonggoidx);
		List<InfoCustomFieldSettingDTO> icfsdtoList = service.getInfoCustomFieldSetting(icfsdto);
		model.addAttribute("infoCustomField_list",icfsdtoList);
		
		// 맞춤형 문항 정보 List 불러오기
		rcdto.setGonggoIdx(gonggoidx);
		List<ResumeCustomDTO> rcdtoList = service.getResumeCustomField(rcdto);
		model.addAttribute("resumeCustom_list", rcdtoList);
		
		customsdto.setNoticeControl_idx(gonggoidx);
		List<CustomFieldSettingDTO> customsdtoList = service.getResumeCustomFieldSetting(customsdto);
		System.out.println(customsdtoList.toString());
		model.addAttribute("resumeCustomField_list",customsdtoList);
		
		// 자기소개 정보 DTO 불러오기
		pdto.setGonggoIdx(gonggoidx);
		pdto = service.getPersonalField(pdto);
		model.addAttribute("personal_dto",pdto);
		
		pfsdto.setNoticeControl_idx(gonggoidx);
		List<PersonalFieldSettingDTO> pfsdtoList = service.getPersonalFieldSetting(pfsdto);
		model.addAttribute("personalField_list",pfsdtoList);
		
		// 경력기술서 정보 DTO 불러오기
		cdto.setGonggoIdx(gonggoidx);
		cdto = service.getCareerField(cdto);
		model.addAttribute("career_dto", cdto);
		
		cfsdto.setNoticeControl_idx(gonggoidx);
		List<CareerFieldSettingDTO> cfsdtoList = service.getCareerFieldSetting(cfsdto);
		model.addAttribute("careerField_list",cfsdtoList);
		
		// rmsfileUpload_list
		rfdto.setGonggoIdx(gonggoidx);
		List<ResumeFileUploadSetDTO> rfusdtoList = service.getResumeFileUploadField(rfdto);
		model.addAttribute("rsmfileUpload_list",rfusdtoList);
		
		rfsdto.setNoticeControl_idx(gonggoidx);
		List<RsmFileUploadFieldSettingDTO> rfsdtoList= service.getResumeFileUploadFieldSetting(rfsdto);
		model.addAttribute("rsmfileUploadField_list",rfsdtoList);
		
		// recruitFieldList
		rcbdto.setGonggoIdx(gonggoidx);
		List<RecruitCategBoardDTO> fieldList = service.getRecruitField(rcbdto);
		model.addAttribute("fieldList", fieldList);
		
		view = "resume/resumeFieldSetting";
		return view;				
	}
	
	@RequestMapping("resumeFieldInsert")
	public String resumeFieldInsert(HttpSession session, CareerFieldSettingDTO cfsdto, CustomFieldSettingDTO customsdto, InfoCustomFieldSettingDTO icfsdto, PersonalFieldSettingDTO pfsdto, RsmFileUploadFieldSettingDTO rfsdto) {
//		세션 값 셋팅해주기
		int gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
//		int gonggoidx = 1;
		
		
		// infoCustomField
		icfsdto.setNoticeControl_idx(gonggoidx);
		customsdto.setNoticeControl_idx(gonggoidx);
		pfsdto.setNoticeControl_idx(gonggoidx);
		cfsdto.setNoticeControl_idx(gonggoidx);
		rfsdto.setNoticeControl_idx(gonggoidx);
		
		int rs =service.insertResumeField(icfsdto, customsdto, pfsdto, cfsdto, rfsdto);
		view= "redirect:resumeFieldSetting";
		
		return view;
	}

}
