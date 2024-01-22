package com.gyeonglodang.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gyeonglodang.dto.CareerDTO;
import com.gyeonglodang.dto.CareerFieldSettingDTO;
import com.gyeonglodang.dto.CareerQDTO;
import com.gyeonglodang.dto.CustomFieldSettingDTO;
import com.gyeonglodang.dto.InfoCustomDTO;
import com.gyeonglodang.dto.InfoCustomFieldSettingDTO;
import com.gyeonglodang.dto.InfoDTO;
import com.gyeonglodang.dto.PersonalDTO;
import com.gyeonglodang.dto.PersonalFieldSettingDTO;
import com.gyeonglodang.dto.PersonalQDTO;
import com.gyeonglodang.dto.RecruitCategBoardDTO;
import com.gyeonglodang.dto.ResumeCustomDTO;
import com.gyeonglodang.dto.ResumeFileDTO;
import com.gyeonglodang.dto.ResumeFileUploadDTO;
import com.gyeonglodang.dto.ResumeFileUploadSetDTO;
import com.gyeonglodang.dto.RsmFileUploadFieldSettingDTO;
import com.gyeonglodang.dto.SentenceTypeDTO;
import com.gyeonglodang.dto.TableTypeDTO;
import com.gyeonglodang.dto.TableTypeQDTO;
import com.gyeonglodang.mybatis.mapper.ResumeMapper;
import com.gyeonglodang.service.ResumeService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

@Service
public class ResumeServiceImpl implements ResumeService{

	@Autowired
	ResumeMapper dao;
	
	
	// 채용분야별 지원서 문항 저장하기
	
	@Override
	public int insertResumeField(InfoCustomFieldSettingDTO icfsdto, CustomFieldSettingDTO customsdto, PersonalFieldSettingDTO pfsdto, CareerFieldSettingDTO cfsdto, RsmFileUploadFieldSettingDTO rfsdto) {
		int rs =0;
		int icfsdtoInsert =0;
		int icfsdtoDelete =0;
		int customsdtoInsert=0;
		int customsdtoDelete=0;
		int pfsdtoInsert=0;
		int pfsdtoDelete=0;
		int cfsdtoInsert=0;
		int cfsdtoDelete=0;
		int rfsdtoInsert=0;
		int rfsdtoDelete=0;
		
		if(icfsdto.getInfoCustomField()!=null) {
			icfsdtoDelete = deleteInfoCustomField(icfsdto);
			icfsdtoInsert = insertInfoCustomField(icfsdto);
		}
		
		if(customsdto.getCustomField()!=null) {
			customsdtoDelete = deleteCustomField(customsdto);
			customsdtoInsert = insertCustomField(customsdto);
		}
		
		if(pfsdto.getPersonalField()!=null) {
			pfsdtoDelete = deletePersonalField(pfsdto);
			pfsdtoInsert = insertPersonalField(pfsdto);
		}
		
		if(cfsdto.getCareerField()!=null) {
			cfsdtoDelete = deleteCareerField(cfsdto);
			cfsdtoInsert = insertCareerField(cfsdto);
		}
		
		if(rfsdto.getFileField()!=null) {
			rfsdtoDelete = deleteRsmFileUploadField(rfsdto);
			rfsdtoInsert = insertRsmFileUploadField(rfsdto);
		}
		
		if(icfsdtoInsert ==1 || customsdtoInsert==1 || pfsdtoInsert==1 || cfsdtoInsert==1 || rfsdtoInsert==1) {
			rs=1;
		}
		
		return rs;
	}
	
	//채용분야별 지원서 문항 불러오기
	@Override
	public List<InfoCustomDTO> getInfoCustomField(InfoCustomDTO icdto) {
		return dao.getInfoCustomField(icdto);
	}
	
	@Override
	public List<InfoCustomFieldSettingDTO> getInfoCustomFieldSetting(InfoCustomFieldSettingDTO icfsdto){
		return dao.getInfoCustomFieldSetting(icfsdto);
	}

	@Override
	public PersonalDTO getPersonalField(PersonalDTO pdto) {
		return dao.getPersonalField(pdto);
	}
	
	@Override
	public List<PersonalFieldSettingDTO> getPersonalFieldSetting(PersonalFieldSettingDTO pfsdto){
		return dao.getPersonalFieldSetting(pfsdto);
	}
	
	@Override
	public CareerDTO getCareerField(CareerDTO cdto) {
		return dao.getCareerField(cdto);
	}
	
	@Override
	public List<CareerFieldSettingDTO> getCareerFieldSetting(CareerFieldSettingDTO cfsdto){
		return dao.getCareerFieldSetting(cfsdto);
	}
	
	@Override
	public List<ResumeCustomDTO> getResumeCustomField(ResumeCustomDTO rcdto){
		return dao.getResumeCustomField(rcdto);
	}
	
	@Override
	public List<CustomFieldSettingDTO> getResumeCustomFieldSetting(CustomFieldSettingDTO customsdto) {
		return dao.getResumeCustomFieldSetting(customsdto);
	}
	
	@Override
	public List<ResumeFileUploadSetDTO> getResumeFileUploadField(ResumeFileDTO rfdto){
		return dao.getResumeFileUploadField(rfdto);
	}
	
	@Override
	public List<RsmFileUploadFieldSettingDTO> getResumeFileUploadFieldSetting(RsmFileUploadFieldSettingDTO rfsdto){
		return dao.getResumeFileUploadFieldSetting(rfsdto);
	}
	
	@Override
	public List<RecruitCategBoardDTO> getRecruitField(RecruitCategBoardDTO rcbdto){
		return dao.getRecruitField(rcbdto);
	}
	

	//	get 메소드
	@Override
	public InfoDTO getInfo(InfoDTO idto) {
		return dao.getInfo(idto);
	}

	@Override
	public List<InfoCustomDTO> getInfoCustom(InfoCustomDTO icdto) {
		return dao.getInfoCustom(icdto);
	}

	@Override
	public PersonalDTO getPersonal(PersonalDTO pdto) {
		return dao.getPersonal(pdto);
	}

	@Override
	public List<PersonalQDTO> getPersonalQ(PersonalDTO pdto) {
		return dao.getPersonalQ(pdto);
	}

	@Override
	public CareerDTO getCareer(CareerDTO cdto) {
		return dao.getCareer(cdto);
	}

	@Override
	public List<CareerQDTO> getCareerQ(CareerDTO cdto){
		return dao.getCareerQ(cdto);
	}

	@Override
	public List<ResumeCustomDTO> getResumeCustom(ResumeCustomDTO rcdto){
		return dao.getResumeCustom(rcdto);
	}

	@Override
	public List<SentenceTypeDTO> getSentenceType(ResumeCustomDTO rcdto){
		return dao.getSentenceType(rcdto);
	}

	@Override
	public List<TableTypeDTO> getTableType(ResumeCustomDTO rcdto){
		return dao.getTableType(rcdto);
	}

	@Override
	public List<TableTypeQDTO> getTableTypeQ(ResumeCustomDTO rcdto){
		return dao.getTableTypeQ(rcdto);
	}

	@Override
	public ResumeFileDTO getResumeFile(ResumeFileDTO rfdto) {
		
		return dao.getResumeFile(rfdto);
	}

	@Override
	public List<ResumeFileUploadSetDTO> getResumeFileUpload(ResumeFileDTO rfdto){
		return dao.getResumeFileUpload(rfdto);
	}


	// insert 메소드
	@Override
	public int insertResume(InfoDTO idto, InfoCustomDTO icdto, PersonalDTO pdto, PersonalQDTO pqdto, CareerDTO cdto, CareerQDTO cqdto, ResumeCustomDTO rcdto, SentenceTypeDTO stdto, TableTypeDTO ttdto, TableTypeQDTO ttqdto, ResumeFileDTO rfdto, ResumeFileUploadDTO rfudto, HttpServletRequest request, @RequestParam("file") List<MultipartFile> file, Map<String,Object> map) throws Exception{
		System.out.println("service.insertResume : success");
		// 변수 정리
		int rs = 0; // 최종 결과 값
		int ii = 0; // infoInsert
		int id = 0; // infoDelete
		int ici= 0; // infoCustomInsert
		int icd= 0; // infoCustomDelete
		int pi = 0; // personalInsert
		int pd = 0; // personalDelete
		int pqi= 0; // personalQInsert
		int pqd= 0; // personalQDelete
		int ci = 0; // careerInsert
		int cd = 0; // careerDelete
		int cqi= 0; // careerQInsert
		int cqd= 0; // careerQDelete
		int rci= 0; // resumeCustomInsert
		int rcd= 0; // resumeCustomDelete
		int rfrs = 0;
		int rfurs =0;

		// 각 기능별 메소드 실행
		//인적사항 입력
		if(idto.getInfoTitle()!=null) {
			id = deleteInfo(idto);
			System.out.println("id = "+id);
			ii = insertInfo(idto);
			System.out.println("ii = "+ii);
		}

		//인적사항 커스텀 입력
		if(icdto.getInfoCustomCategory()!=null) {
			icd = deleteInfoCustom(icdto);
			System.out.println("icd = "+icd);
			ici = insertInfoCustom(icdto);
			System.out.println("ici = "+ici);
		}

		//자기소개서 입력
		pd = deletePersonal(pdto);
		System.out.println("pd = "+pd);
		pi = insertPersonal(pdto);
		System.out.println("pi = "+pi);

		//자기소개서 문항 입력
		if(pqdto.getPersonalQuestion()!=null) {
			pqd = deletePersonalQ(pdto);
			System.out.println("pqd = "+pqd);
			pdto = getPersonalIdx(pdto);
			pqi = insertPersonalQ(pdto, pqdto);
			System.out.println("pqi = "+pqi);
		}

		// 경력기술서 입력
		cd = deleteCareer(cdto);
		System.out.println("cd = "+cd);
		ci = insertCareer(cdto);
		System.out.println("ci = "+ci);

		// 경력기술서 문항 입력
		if(cqdto.getCareerQuestion()!=null) {
			cqd = deleteCareerQ(cdto);
			System.out.println("cqd = "+cqd);
			cdto = getCareerIdx(cdto);
			cqi = insertCareerQ(cdto, cqdto);
			System.out.println("cqi = "+cqi);
		}

		// 맞춤형 문항 정보
		rcd = deleteResumeCustom(rcdto);
		System.out.println("rcd ="+rcd);
		if(rcdto.getCustomTitle()!=null) {
			rci = insertResumeCustom(rcdto, stdto, ttdto, ttqdto);
			System.out.println("rci = "+rci);
		}

		// 파일 업로드 
		//if(rfudto.getRsmfileupload_field() != null) {
		System.out.println("serviceImplFileUpload start");
		//			deleteFileUpload(rfdto);
		insertFileUpload(rfdto, rfudto, file, request, map);
		//}

		// 내용이 없어서 추가가 안됐을때 문제 여부 확인
		if(id==0)ii=1;
		if(icd==0)ici=1;
		if(pqd==0)pqi=1;
		if(cqd==0)cqi=1;
		if(rcd==0)rci=1;


		// insert 잘되면 최종 결과 1
		if(ii==1&&ici==1&&pi==1&&pqi==1&&ci==1&&cqi==1&&rci==1) { 
			rs=1;
		}

		return rs;
	}



	// ==========================================================================================================================


	public void deleteFileUpload(ResumeFileDTO rfdto) {

	}

	public void insertFileUpload(ResumeFileDTO rfdto, ResumeFileUploadDTO rfudto, List<MultipartFile> file, HttpServletRequest request, Map<String,Object> map) throws Exception {
		//		System.out.println("insertFileUpload :"+rfudto);


		//여기에 dao 만들어야함
		dao.deleteResumeFile(rfdto);
		dao.insertResumeFile(rfdto);
		System.out.println("Tlqkf2");
		int idx = dao.getResumeFileIdx(rfdto);

		//==================== field 추출 ====================
		if(rfudto.getRsmfileupload_field() !=null) {


			String[] field = rfudto.getRsmfileupload_field().split(",");

			List<String> fileUploadFieldList = new ArrayList();
			for(String s : field) {
				fileUploadFieldList.add(s);
			}

			for(int i=0; i<fileUploadFieldList.size(); i++) {
				if(fileUploadFieldList.get(i)!=null && fileUploadFieldList.get(i+1).equals("1")) {
					fileUploadFieldList.remove(i+1);
				}
			}

			//		System.out.println(fileUploadFieldList);


			//==================== pilsu 추출 ====================
			String[] pilsu = rfudto.getRsmfileupload_pilsu().split(",");

			List<String> pilsuList = new ArrayList();
			for(String s : pilsu) {
				pilsuList.add(s);
			}

			for(int i=0; i<pilsuList.size(); i++) {
				if(pilsuList.get(i)!=null && pilsuList.get(i+1).equals("1")) {
					pilsuList.remove(i+1);
				}
			}

			//		System.out.println(pilsuList);






			//==================== fileName 추출 ====================
			String[] fileName = rfudto.getRsmfileupload_fileName().split(",");

			List<String> fileNameList = new ArrayList();
			for(String s : fileName) {
				fileNameList.add(s);
			}

			for(int i=0; i<fileNameList.size(); i++) {
				if(fileNameList.get(i)!=null && fileNameList.get(i+1).equals("1")) {
					fileNameList.remove(i+1);
				}
			}

			//		System.out.println("?"+fileNameList);




			//==================== filePath ====================
			String uploadPath = "/Users/dpqbw/Desktop/k-digital/spring2/springws/gyeonglodang/src/main/webapp/resources/uploadfile";

			String[] filePath = rfudto.getFilePath().split(",");
			List<String> filePathList = new ArrayList<String>();
			for(String s : filePath) {
				filePathList.add(s);
			}

			for(int i =0; i<filePathList.size(); i++) {
				if(filePathList.get(i).contains(uploadPath) && filePathList.get(i+1).equals("$")) {
					filePathList.remove(i+1);
				}else if(filePathList.get(i).equals("$")) {
					filePathList.set(i, uploadPath);
				}
			}

			String[] fileNames = rfudto.getFileName().split(",");
			List<String> fileNamesList = new ArrayList<String>();
			for(String s : fileNames) {
				fileNamesList.add(s);
			}
			for(int i =0; i<fileNamesList.size(); i++) {
				if(fileNamesList.get(i).equals("$")){
					fileNamesList.set(i, "");
				}else if(!fileNamesList.get(i).equals("$") && !fileNamesList.get(i).equals(null) && fileNamesList.get(i+1).equals("$")) {
					fileNamesList.remove(i+1);
				}
			}


			//upload할 위치
			int count1 = 1;

			//			Map<String, Object>fileUploadmap = new HashMap<String, Object>();
			List<String> nameList = new ArrayList<>();
			for(int i=0; i<file.size(); i++) {	//반복문을 이용하여 nameList에 파일이름 추가하는 소스 
				if(file.get(i).getOriginalFilename().equals("")) {
					if(!fileNamesList.get(i).equals(null)) {
						String pastFileName = fileNamesList.get(i);
						nameList.add(pastFileName);
					}else {
						nameList.add(file.get(i).getOriginalFilename());
					}
				}else {
					nameList.add(file.get(i).getOriginalFilename());
				}
				count1++;
			}


			System.out.println("??"+nameList.toString());
			System.out.println("tlqkfPath : "+map.get("filePath1"));
			System.out.println("tlqkfName : "+map.get("fileName1"));
			int count2 =1;
			// 시스템 filename 제작 및 저장
			List<String> sNList = new ArrayList<String>();
			String originFile = "";
			String ext = "";
			String now = "";
			String systemName = "";
			File oldFile = null;
			File newFile = null;
			File saveFile = null;

			List<String> filesPathList = new ArrayList<String>();
			for(int i = 0; i < nameList.size(); i++) {
				originFile = nameList.get(i);
				System.out.println("여기요"+originFile);
				if(originFile.contains(".")&&file.get(i).getSize()!=0) {
					System.out.println("여기요2"+originFile);
					ext = originFile.substring(originFile.lastIndexOf("."));
					now = new SimpleDateFormat("yyyMMdd_HmsS").format(new Date());
					systemName = now + "_" + i + ext;
					sNList.add(systemName);
					oldFile = new File(uploadPath + "/"+ originFile);
					newFile = new File(uploadPath + "/" + systemName);
					//				newFile = new File(uploadPath + File.separator + systemName);
					oldFile.renameTo(newFile);
					filesPathList.add(systemName);
				}else {
					if(!filePathList.get(i).equals(null)) {
						String pastFilePath = filePathList.get(i);
						System.out.println("service pastFilePath : "+pastFilePath.substring(pastFilePath.lastIndexOf("/")).replace("/", ""));
						filesPathList.add(pastFilePath.substring(pastFilePath.lastIndexOf("/")).replace("/", ""));
						sNList.add("");
					}else {
						filesPathList.add("");
						sNList.add("");
					}
				}
				count2++;
			}

			System.out.println("sNList : "+ sNList.toString());

			//			System.out.println("serviceImple tlqkf"+file.toString());
			//			System.out.println("serviceImple tlqkf"+file.size());
			for(int i = 0; i < file.size(); i++) {
				//				System.out.println("fileGet1 : " + file.get(i).toString());
				System.out.println("4이상한 곳은 혹시 여기?");
				if(file.get(i).getSize()!=0) {
					//					System.out.println("fileGet2-1 : " + file.get(i).toString());
					System.out.println("5이상한 곳은 여기?");
					saveFile = new File(uploadPath, sNList.get(i));
					System.out.println("6이상한 곳은 아님여기?");
					file.get(i).transferTo(saveFile);
					System.out.println("7이상한 곳은 아님 진짜 여기?");
					//					System.out.println("fileGet2-2 : " + file.get(i).toString());
				}else{

					System.out.println("8이상한 곳은 아님 마지막으로 여기?");
					//					System.out.println("fileGet3 : " + file.get(i).toString());
					continue;
				}
			}




			//			// fileUploadmap을 이용하여 
			//			fileUploadmap.put("uploadPath", uploadPath);
			//			fileUploadmap.put("systemName", systemName);
			//			System.out.println("tlqkf4");


			ResumeFileUploadSetDTO rfusDTO = new ResumeFileUploadSetDTO(); 
			dao.deleteResumeFileUpload(rfdto);
			//db에 들어갈 것들이 title, explain 들어가야하고 
			//rfuDTO 전부 들어가야함
			for(int i = 0; i < fileNameList.size(); i++) {
				System.out.println("임상희 삼겹살 1개 먹음");
				rfusDTO.setRsmfile_idx(idx);
				System.out.println("임상희 삼겹살 2개 먹음");
				rfusDTO.setRsmfileupload_field(fileUploadFieldList.get(i));
				System.out.println("임상희 삼겹살 3개 먹음");
				rfusDTO.setRsmfileupload_fileName(fileNameList.get(i));
				System.out.println("임상희 삼겹살 4개 먹음");
				rfusDTO.setRsmfileupload_pilsu(pilsuList.get(i));
				System.out.println("임상희 삼겹살 5개 먹음");
				rfusDTO.setRsmfileupload_original(nameList.get(i));
				System.out.println("임상희 삼겹살 6개 먹음");
				rfusDTO.setRsmfileupload_filePath(uploadPath + "/" + filesPathList.get(i));
				System.out.println("임상희 삼겹살 7개 먹음");
				dao.insertResumeFileUpload(rfusDTO);
				System.out.println("임상희 삼겹살 다 먹음");
			}
		}
	}


	// ---------------------------------------------------------------------------------

	// 인적사항 문항 입력
	public int insertInfo(InfoDTO idto) {
		System.out.println("service.insertInfo :"+ idto.getInfo());
		String[] info = idto.getInfo().split(",");
		for(String s : info) {
			switch (s) {
			case "1" : idto.setInfoName("1");break;
			case "2" : idto.setInfoBirth("1");break;
			case "3" : idto.setInfoAddress("1");break;
			case "4" : idto.setInfoPhone("1");break;
			case "5" : idto.setInfoArmy("1");break;
			case "6" : idto.setInfoVulnerable("1");break;
			case "7" : idto.setInfoYouth("1");break;
			case "8" : idto.setInfoDisabled("1");break;
			}
		}
		return dao.insertInfo(idto);
	}

	//기존 인적사항 문항 삭제
	public int deleteInfo(InfoDTO idto) {
		return dao.deleteInfo(idto);
	}

	// 인적사항 커스텀 문항 입력
	public int insertInfoCustom(InfoCustomDTO icdto) {
		//		System.out.println("service.insertInfoCustom");// 서비스 호출

		int rs=0; // 결과 값 초기화

		String[] infoCustomCategory = icdto.getInfoCustomCategory().split(","); 
		System.out.println(Arrays.toString(infoCustomCategory));
		List<String>infoCustomCategoryList = new ArrayList<String>(); // 카테고리 배열->리스트
		for(String s : infoCustomCategory) {
			infoCustomCategoryList.add(s);
		}

		String[] infoCustomNotice = icdto.getInfoCustomNotice().split(",");

		List<String>infoCustomNoticeList = new ArrayList<String>(); // 안내사항 배열->리스트
		for(String s : infoCustomNotice) {
			infoCustomNoticeList.add(s);
		}


		System.out.println(infoCustomNoticeList.toString());


		for(int i=0; i<infoCustomNoticeList.size(); i++) {
			if(!infoCustomNoticeList.get(i).equals(null)&&infoCustomNoticeList.get(i+1).equals("0")) { 
				infoCustomNoticeList.remove(i+1);
			}
		}

		String[] infoCustomField = icdto.getInfoCustomField().split(",");

		List<String>infoCustomFieldList = new ArrayList<String>(); // 분야 배열 -> 리스트
		for(String s : infoCustomField) {
			infoCustomFieldList.add(s);
		}

		String[] infoCustomPilsu = icdto.getInfoCustomPilsu().split(",");
		List<String> infoCustomPilsuList = new ArrayList<String>(); // 필수 배열->리스트

		// 체크하면 1 안하면 0으로 리스트 변경
		for(String s : infoCustomPilsu) {
			infoCustomPilsuList.add(s);
		}
		System.out.println(infoCustomPilsuList.toString());

		for(int i=0; i<infoCustomPilsuList.size(); i++) {
			if(infoCustomPilsuList.get(i).equals("1")&&infoCustomPilsuList.get(i+1).equals("0")) { 
				infoCustomPilsuList.remove(i+1);
			}
		}
		System.out.println("pilsu : "+infoCustomPilsuList.toString());

		// DTO에 담아서 작성
		for(int i=0; i<infoCustomCategoryList.size(); i++) {
			icdto.setInfoCustomCategory(infoCustomCategoryList.get(i));
			icdto.setInfoCustomNotice(infoCustomNoticeList.get(i));
			icdto.setInfoCustomField(infoCustomFieldList.get(i));
			icdto.setInfoCustomPilsu(infoCustomPilsuList.get(i));
			rs += dao.insertInfoCustom(icdto);
		}

		// 전부 다 잘 됐다면 1
		if(rs==infoCustomCategoryList.size()) rs = 1; 
		System.out.println(rs);
		return rs;
	}

	// 기존 인적사항 커스텀 문항 데이터 삭제
	public int deleteInfoCustom(InfoCustomDTO icdto) {
		return dao.deleteInfoCustom(icdto);
	}

	// ---------------------------------------------------------------------------------

	// 자기소개서 정보 저장
	public int insertPersonal(PersonalDTO pdto) {
		System.out.println("service.insertPersonal: " + pdto.getPersonalTitle());
		System.out.println("service.insertPersonal: " + pdto.getPersonalNotice());

		return dao.insertPersonal(pdto);
	}
	// 자기소개서 정보 삭제
	public int deletePersonal(PersonalDTO pdto) {
		return dao.deletePersonal(pdto);
	}

	// 자기소개서 저장 idx 가져오기
	public PersonalDTO getPersonalIdx (PersonalDTO pdto) {

		return dao.getPersonalIdx(pdto);
	}

	// 자기소개서 문항 저장
	public int insertPersonalQ(PersonalDTO pdto, PersonalQDTO pqdto) {
		System.out.println("service.insertPersonal : " + pqdto.getPersonalQuestion());
		System.out.println("service.insertPersonal : " + pqdto.getPersonalMinW());
		System.out.println("service.insertPersonal : " + pqdto.getPersonalMaxW());

		int rs=0;

		String[] PersonalQuestion = pqdto.getPersonalQuestion().split(",");
		String[] PersonalMinW = pqdto.getPersonalMinW().split(",");
		String[] PersonalMaxW = pqdto.getPersonalMaxW().split(",");

		for(int i=0; i<PersonalQuestion.length; i++) {
			pqdto.setPersonalIdx(pdto.getIdx());
			pqdto.setPersonalQuestion(PersonalQuestion[i]);
			pqdto.setPersonalMinW(PersonalMinW[i]);
			pqdto.setPersonalMaxW(PersonalMaxW[i]);
			rs += dao.insertPersonalQ(pqdto);
		}

		if(rs==PersonalQuestion.length) rs = 1; 
		//		System.out.println(rs);
		return rs;
	}
	// 자기소개서 문항 삭제
	public int deletePersonalQ(PersonalDTO pdto) {
		return dao.deletePersonalQ(pdto);
	}


	// ---------------------------------------------------------------------------------

	// 경력기술서 정보 저장
	public int insertCareer(CareerDTO cdto) {
		System.out.println("service.insertPersonal: " + cdto.getCareerTitle());
		System.out.println("service.insertPersonal: " + cdto.getCareerNotice());

		return dao.insertCareer(cdto);
	}
	// 경력기술서 정보 삭제
	public int deleteCareer(CareerDTO cdto) {
		return dao.deleteCareer(cdto);
	}

	// 경력기술서 저장 idx 가져오기
	public CareerDTO getCareerIdx (CareerDTO cdto) {

		return dao.getCareerIdx(cdto);
	}

	// 경력기술서 문항 저장
	public int insertCareerQ(CareerDTO cdto, CareerQDTO cqdto) {
		System.out.println("service.insertPersonal : " + cqdto.getCareerQuestion());
		System.out.println("service.insertPersonal : " + cqdto.getCareerMinW());
		System.out.println("service.insertPersonal : " + cqdto.getCareerMaxW());

		int rs=0;

		String[] careerQuestion = cqdto.getCareerQuestion().split(",");
		String[] careerMinW = cqdto.getCareerMinW().split(",");
		String[] careerMaxW = cqdto.getCareerMaxW().split(",");

		for(int i=0; i<careerQuestion.length; i++) {
			cqdto.setCareerIdx(cdto.getIdx());
			cqdto.setCareerQuestion(careerQuestion[i]);
			cqdto.setCareerMinW(careerMinW[i]);
			cqdto.setCareerMaxW(careerMaxW[i]);
			rs += dao.insertCareerQ(cqdto);
		}

		if(rs==careerQuestion.length) rs = 1; 
		//		System.out.println(rs);
		return rs;
	}

	// 경력기술서 문항 삭제
	public int deleteCareerQ(CareerDTO cdto) {
		return dao.deleteCareerQ(cdto);
	}

	// ---------------------------------------------------------------------------------	

	// 맞춤형문항 정보 저장
	public int insertResumeCustom(ResumeCustomDTO rcdto, SentenceTypeDTO stdto, TableTypeDTO ttdto, TableTypeQDTO ttqdto) {
		int rs = 0;
		// 맞춤형 문항 정보 정리
		String[] customTitle = rcdto.getCustomTitle().split(",");
		String[] customField = rcdto.getCustomField().split(",");
		String[] customNotice = rcdto.getCustomNotice().split(",");

		System.out.println("insertResumeCustom");
		// 맞춤형 문항_단문형 문항 정보 정리

		String[] sentenceQuestion = new String[0];
		List<String>sentenceQuestionList = new ArrayList<String>();

		if(stdto.getSentenceNotice()!=null) {
			sentenceQuestion = stdto.getSentenceQuestion().split(",");
			for(String s : sentenceQuestion) {
				sentenceQuestionList.add(s);
			}
			System.out.println(Arrays.toString(sentenceQuestion));
			for(int i=0; i<sentenceQuestionList.size(); i++) {
				if(sentenceQuestionList.get(i).equals("1")) {
					continue;
				}else if(!sentenceQuestionList.get(i).equals(null)&&sentenceQuestionList.get(i+1).equals("0")) { 
					sentenceQuestionList.remove(i+1);
				}
			}
			sentenceQuestionList.add("1");
		}
		System.out.println("sentenceQuestionList :" +sentenceQuestionList.toString());

		String[] sentenceNotice = new String[0];
		List<String>sentenceNoticeList = new ArrayList<String>();
		if(stdto.getSentenceNotice()!=null) {
			sentenceNotice = stdto.getSentenceNotice().split(",");
			for(String s : sentenceNotice) {
				sentenceNoticeList.add(s);
			}
			for(int i=0; i<sentenceNoticeList.size(); i++) {
				if(!sentenceNoticeList.get(i).equals(null)&&sentenceNoticeList.get(i+1).equals("0")) { 
					sentenceNoticeList.remove(i+1);
				}
			}
		}
		System.out.println("sentenceNoticeList :" +sentenceNoticeList.toString());

		String[] sentencePilsu = new String[0];
		List<String>sentencePilsuList = new ArrayList<String>();

		if(stdto.getSentencePilsu()!=null) {
			sentencePilsu = stdto.getSentencePilsu().split(",");
			for(String s : sentencePilsu) {
				sentencePilsuList.add(s);
			}
			for(int i=0; i<sentencePilsuList.size(); i++) {
				if(sentencePilsuList.get(i).equals("1")&&sentencePilsuList.get(i+1).equals("0")) { 
					sentencePilsuList.remove(i+1);
				}
			}
		}
		System.out.println("sentencePilsuList :" +sentencePilsuList.toString());
		System.out.println("단문형 정리 완료");
		// 맞춤형 문항_테이블형 정보 정리

		String[] tableTitle = ttdto.getTableTitle().split(",");
		System.out.println(Arrays.toString(tableTitle));
		List<String> tableTitleList = new ArrayList<String>();
		for(String s : tableTitle) {
			tableTitleList.add(s);
		}
		for(int i=0; i<tableTitleList.size(); i++) {
			if(tableTitleList.get(i).equals("1")) {
				continue;
			}else if(!tableTitleList.get(i).equals(null)&&tableTitleList.get(i+1).equals("0")) { 
				tableTitleList.remove(i+1);
			}
		}
		tableTitleList.add("1");
		System.out.println(tableTitleList.toString());
		
		if(ttdto.getTableMinRow()==null) {
			ttdto.setTableMinRow("0");
		}
		
		String[] tableMinRow = ttdto.getTableMinRow().split(",");
		List<String> tableMinRowList = new ArrayList<String>();
		for(String s : tableMinRow) {
			tableMinRowList.add(s);
		}
		System.out.println("serviceImpl : "+ tableMinRowList.toString());
		
		if(ttdto.getTableMaxRow()==null) {
			ttdto.setTableMaxRow("0");
		}

		String[] tableMaxRow = ttdto.getTableMaxRow().split(",");
		List<String> tableMaxRowList = new ArrayList<String>();
		for(String s : tableMaxRow) {
			tableMaxRowList.add(s);
		}
		System.out.println("serviceImpl : "+ tableMaxRowList.toString());
		
		if(ttdto.getTableMaxCol()==null) {
			ttdto.setTableMaxCol("0");
		}
		
		String[] tableMaxCol = ttdto.getTableMaxCol().split(",");
		List<String> tableMaxColList = new ArrayList<String>();
		for(String s : tableMaxCol) {
			tableMaxColList.add(s);
		}
		
		if(ttqdto.getTableCategory()==null) {
			ttqdto.setTableCategory("1");
		}
		// 맞춤형 문항_테이블형 문항 정리
		String[] tableCategory = ttqdto.getTableCategory().split(",");
		List<String> tableCategoryList = new ArrayList<String>();
		for(String s : tableCategory) {
			tableCategoryList.add(s);
		}
		
		for(int i=0; i<tableCategoryList.size(); i++) {
			if(tableCategoryList.get(i).equals("1")) {
				continue;
			}else if(!tableCategoryList.get(i).equals(null)&&tableCategoryList.get(i+1).equals("0")) { 
				tableCategoryList.remove(i+1);
			}
		}
		tableCategoryList.add("1");
		System.out.println("카테고리 리스트 정리(1포함) : "+ tableCategoryList.toString() );

		if(ttqdto.getTableNotice()==null) {
			ttqdto.setTableNotice("0");
		}
		String[] tableNotice = ttqdto.getTableNotice().split(",");
		List<String> tableNoticeList = new ArrayList<String>();
		for(String s : tableNotice) {
			tableNoticeList.add(s);
		}
		for(int i=0; i<tableNoticeList.size(); i++) {
			if(tableNoticeList.size()>1) {
				if(!tableNoticeList.get(i).equals(null)&&tableNoticeList.get(i+1).equals("0")) { 
					tableNoticeList.remove(i+1);
				}	
			}
		}
		System.out.println("노티스 리스트 정리 : "+ tableNoticeList.toString() );


		// 맞춤형 문항 구분자 인덱스
		int repeatSentenceNum = 0; // 단문형
		int repeatTableNum = 0; // 테이블형
		int repeatTableQNum = 0; // 테이블형

		// 맞춤형 문항 입력 반복 
		for(int i =0; i<customTitle.length; i++) {
			rcdto.setCustomTitle(customTitle[i]);
			rcdto.setCustomField(customField[i]);
			rcdto.setCustomNotice(customNotice[i]);
			rs += dao.insertResumeCustom(rcdto);

//			int idx = dao.getResumeCustomIdx(rcdto).getIdx(); // 저장된 맞춤형 문항 idx불러오기
			int idx = rcdto.getIdx(); // 저장된 맞춤형 문항 idx불러오기
			System.out.println("resumeCustomIdx : "+idx);

			// 맞춤형 문항 내 단문형 문항 입력 반복			
			for(int j = repeatSentenceNum; j<sentenceQuestionList.size(); j++) {
				System.out.println("최초 사이즈 : " +sentenceQuestionList.size());
				if(sentenceQuestionList.get(j).equals("1")&&!sentenceQuestionList.get(j+1).equals("1")) {
					sentenceQuestionList.remove(j);
					j--;
					System.out.println(sentenceQuestionList.toString());
					System.out.println("변경 사이즈 : " +sentenceQuestionList.size());
					continue;
				}else if(sentenceQuestionList.get(j).equals("1")&&sentenceQuestionList.get(j+1).equals("1")) {
					sentenceQuestionList.remove(j);
					j--;
					System.out.println(sentenceQuestionList.toString());
					System.out.println("변경 사이즈 : " +sentenceQuestionList.size());
					break;
				}else if(sentenceQuestionList.get(j+1).equals("1")){
					stdto.setResumeCustomIdx(idx);
					stdto.setSentenceQuestion(sentenceQuestionList.get(j));
					stdto.setSentenceNotice(sentenceNoticeList.get(j));
					stdto.setSentencePilsu(sentencePilsuList.get(j));
					dao.insertSentenceType(stdto);
					repeatSentenceNum = j+1;
					break;
				}else {
					stdto.setResumeCustomIdx(idx);
					stdto.setSentenceQuestion(sentenceQuestionList.get(j));
					stdto.setSentenceNotice(sentenceNoticeList.get(j));
					stdto.setSentencePilsu(sentencePilsuList.get(j));
					dao.insertSentenceType(stdto);
				}
			}


			// 맞춤형 문항 내 테이블형 정보 입력 반복	
			table: for(int j = repeatTableNum; j<tableTitleList.size(); j++) {
				System.out.println("최초 사이즈 : " +tableTitleList.size());
				if(tableTitleList.get(j).equals("1")) {
					tableTitleList.remove(j);
					j--;
					System.out.println(tableTitleList.toString());
					System.out.println("변경 사이즈 : " +tableTitleList.size());
					continue;
				}else if(tableTitleList.get(j+1).equals("1")){
					ttdto.setResumeCustomIdx(idx);
					ttdto.setTableTitle(tableTitleList.get(j));
					ttdto.setTableMinRow(tableMinRowList.get(j));
					System.out.println("ttdto setTableMinRow :" + ttdto.getTableMinRow());
					ttdto.setTableMaxRow(tableMaxRowList.get(j));
					System.out.println("ttdto setTableMaxRow :" + ttdto.getTableMaxRow());
					ttdto.setTableMaxCol(tableMaxColList.get(j));
					dao.insertTableType(ttdto);
					repeatTableNum = j+1;
					int tidx = ttdto.getIdx();
//					int tidx = dao.getTableTypeIdx(ttdto).getIdx();
					System.out.println("tidx1: "+tidx);
					System.out.println("tableMaxCol1: "+tableMaxColList.get(j));


					int p = tableCategoryList.size() > (Integer.parseInt(tableMaxColList.get(j))+repeatTableQNum) ? (Integer.parseInt(tableMaxColList.get(j))+repeatTableQNum) : tableCategoryList.size(); 

					// 맞춤형 문항 내 테이블형 문항 입력 반복	
					for(int k =repeatTableQNum; k<p; k++) {
						System.out.println("최초 카테고리 사이즈1 : " +Integer.parseInt(tableMaxColList.get(j)));
						if(tableCategoryList.get(k).equals("1")) {
							tableCategoryList.remove(k);
							k--;
							System.out.println(tableCategoryList.toString());
							System.out.println("변경 카테고리 사이즈1 : " +tableCategoryList.size());
							continue;
						}else if(tableCategoryList.get(k+1).equals("1")){
							System.out.println("다음이 1 일때");
							ttqdto.setTableTypeIdx(tidx);
							ttqdto.setTableCategory(tableCategoryList.get(k));
							ttqdto.setTableNotice(tableNoticeList.get(k));
							dao.insertTableTypeQ(ttqdto);
							repeatTableQNum = k + 1;
							break;
						}else {
							System.out.println("다음이 1 아닐때");
							ttqdto.setTableTypeIdx(tidx);
							ttqdto.setTableCategory(tableCategoryList.get(k));
							ttqdto.setTableNotice(tableNoticeList.get(k));
							dao.insertTableTypeQ(ttqdto);
							repeatTableQNum = k + 1;
						}
					}

					break table;
				}else {
					ttdto.setResumeCustomIdx(idx);
					ttdto.setTableTitle(tableTitleList.get(j));
					ttdto.setTableMinRow(tableMinRowList.get(j));
					ttdto.setTableMaxRow(tableMaxRowList.get(j));
					ttdto.setTableMaxCol(tableMaxColList.get(j));
					dao.insertTableType(ttdto);

					int tidx = ttdto.getIdx();;
//					int tidx = dao.getTableTypeIdx(ttdto).getIdx();
					
					System.out.println("tidx2: "+tidx);
					System.out.println("tableMaxCol2: "+tableMaxColList.get(j));

					int p = tableCategoryList.size() > (Integer.parseInt(tableMaxColList.get(j))+repeatTableQNum) ? (Integer.parseInt(tableMaxColList.get(j))+repeatTableQNum) : tableCategoryList.size();
					// 맞춤형 문항 내 테이블형 문항 입력 반복
					for(int k =repeatTableQNum; k<p; k++) {
						System.out.println("최초 카테고리 사이즈2 : " +Integer.parseInt(tableMaxColList.get(j)));
						if(tableCategoryList.get(k).equals("1")) {
							tableCategoryList.remove(k);
							k--;
							System.out.println(tableCategoryList.toString());
							System.out.println("변경 카테고리 사이즈2 : " +tableCategoryList.size());
							continue;
						}else if(tableCategoryList.get(k+1).equals("1")){
							System.out.println("다음이 1(2) 일때");
							ttqdto.setTableTypeIdx(tidx);
							ttqdto.setTableCategory(tableCategoryList.get(k));
							ttqdto.setTableNotice(tableNoticeList.get(k));
							dao.insertTableTypeQ(ttqdto);
							repeatTableQNum = k + 1;
							break;
						}else {
							System.out.println("다음이 1(2) 아닐때");
							ttqdto.setTableTypeIdx(tidx);
							ttqdto.setTableCategory(tableCategoryList.get(k));
							ttqdto.setTableNotice(tableNoticeList.get(k));
							dao.insertTableTypeQ(ttqdto);
							repeatTableQNum = k + 1;
						}
					}
				}
			}
		}

		if(rs==customTitle.length) rs = 1;
		System.out.println("rs ="+rs);
		return rs;
	}

	// 맞춤형 문항 삭제
	public int deleteResumeCustom(ResumeCustomDTO rcdto) {
		return dao.deleteResumeCustom(rcdto);
	}
	
//	=========================================채용분야별 지원서 문항 저장 메서드================================================
	// 인적사항
	public int insertInfoCustomField(InfoCustomFieldSettingDTO icfsdto) {
		int rs= 0;
		String[] infoCustomField_idx= icfsdto.getInfoCustomField_idx().split(",");
		List<String> infoCustomField_idxList = new ArrayList<String>();
		for(String s : infoCustomField_idx) {
			infoCustomField_idxList.add(s);
		}
		System.out.println("serviceimpl : "+ infoCustomField_idxList.toString());
		
		
		String[] infoCustomField=icfsdto.getInfoCustomField().split(",");
		List<String> infoCustomFieldList = new ArrayList<String>();
		for(String s : infoCustomField) {
			infoCustomFieldList.add(s);
		}
		System.out.println("serviceimpl : "+ infoCustomFieldList.toString());
		
		int repeatInfoFieldNum =0;
		
		for(int i =0; i<infoCustomField_idxList.size(); i++) {
			icfsdto.setInfoCustomField_idx(infoCustomField_idxList.get(i));
			for(int j =repeatInfoFieldNum; j<infoCustomFieldList.size(); j++) {
				if(infoCustomFieldList.get(j).equals("0")&&!infoCustomFieldList.get(j+1).equals(null)) {
					System.out.println("삽입1 : "+ infoCustomFieldList.toString());
					icfsdto.setInfoCustomField(infoCustomFieldList.get(j));
					rs += dao.insertInfoCustomField(icfsdto);
				}else if(!infoCustomFieldList.get(j).equals("1")&&!infoCustomFieldList.get(j).equals("0")) {
					infoCustomFieldList.remove(j+1);
					System.out.println("삽입2 : "+ infoCustomFieldList.toString());
					icfsdto.setInfoCustomField(infoCustomFieldList.get(j));
					rs += dao.insertInfoCustomField(icfsdto);
				}else if(infoCustomFieldList.get(j).equals("1")){
					infoCustomFieldList.remove(j);
					System.out.println("제거 : "+infoCustomFieldList.toString());
					repeatInfoFieldNum = j;
					break;
				}
			}
		}
		
		rs = (rs==infoCustomField_idxList.size()) ? 1 : 0;
		
		return rs;
	}
	
	public int deleteInfoCustomField(InfoCustomFieldSettingDTO icfsdto) {
		return dao.deleteInfoCustomField(icfsdto);
	}
	
	// 커스텀
	public int insertCustomField(CustomFieldSettingDTO customsdto) {
		int rs= 0;
		String[] customField_idx= customsdto.getCustomField_idx().split(",");
		List<String> customField_idxList = new ArrayList<String>();
		for(String s : customField_idx) {
			customField_idxList.add(s);
		}
		System.out.println("serviceimpl : "+ customField_idxList.toString());
		
		
		String[] customField= customsdto.getCustomField().split(",");
		List<String> customFieldList = new ArrayList<String>();
		for(String s : customField) {
			customFieldList.add(s);
		}
		System.out.println("serviceimpl : "+ customFieldList.toString());
		
		int repeatCustomFieldNum =0;
		
		for(int i =0; i<customField_idxList.size(); i++) {
			customsdto.setCustomField_idx(customField_idxList.get(i));
			for(int j =repeatCustomFieldNum; j<customFieldList.size(); j++) {
				if(customFieldList.get(j).equals("0")&&!customFieldList.get(j+1).equals(null)) {
					System.out.println("삽입1 : "+ customFieldList.toString());
					customsdto.setCustomField(customFieldList.get(j));
					rs += dao.insertCustomField(customsdto);
				}else if(!customFieldList.get(j).equals("1")&&!customFieldList.get(j).equals("0")) {
					customFieldList.remove(j+1);
					System.out.println("삽입2 : "+ customFieldList.toString());
					customsdto.setCustomField(customFieldList.get(j));
					rs += dao.insertCustomField(customsdto);
				}else if(customFieldList.get(j).equals("1")){
					customFieldList.remove(j);
					System.out.println("제거 : "+customFieldList.toString());
					repeatCustomFieldNum = j;
					break;
				}
			}
		}
		
		rs = (rs==customField_idxList.size()) ? 1 : 0;
		
		return rs;
	}
	
	public int deleteCustomField(CustomFieldSettingDTO customsdto) {
		return dao.deleteCustomField(customsdto);
	}
	
	// 자기소개서
	public int insertPersonalField(PersonalFieldSettingDTO pfsdto) {
		int rs= 0;
		String[] personalField_idx= pfsdto.getPersonalField_idx().split(",");
		List<String> personalField_idxList = new ArrayList<String>();
		for(String s : personalField_idx) {
			personalField_idxList.add(s);
		}
		System.out.println("serviceimpl : "+ personalField_idxList.toString());
		
		
		String[] personalField= pfsdto.getPersonalField().split(",");
		List<String> personalFieldList = new ArrayList<String>();
		for(String s : personalField) {
			personalFieldList.add(s);
		}
		System.out.println("serviceimpl : "+ personalFieldList.toString());
		
		int repeatPersonalFieldNum =0;
		
		for(int i =0; i<personalField_idxList.size(); i++) {
			pfsdto.setPersonalField_idx(personalField_idxList.get(i));
			for(int j =repeatPersonalFieldNum; j<personalFieldList.size(); j++) {
				if(personalFieldList.get(j).equals("0")&&!personalFieldList.get(j+1).equals(null)) {
					System.out.println("삽입1 : "+ personalFieldList.toString());
					pfsdto.setPersonalField(personalFieldList.get(j));
					rs += dao.insertPersonalField(pfsdto);
				}else if(!personalFieldList.get(j).equals("1")&&!personalFieldList.get(j).equals("0")) {
					personalFieldList.remove(j+1);
					System.out.println("삽입2 : "+ personalFieldList.toString());
					pfsdto.setPersonalField(personalFieldList.get(j));
					rs += dao.insertPersonalField(pfsdto);
				}else if(personalFieldList.get(j).equals("1")){
					personalFieldList.remove(j);
					System.out.println("제거 : "+personalFieldList.toString());
					repeatPersonalFieldNum = j;
					break;
				}
			}
		}
		
		rs = (rs==personalField_idxList.size()) ? 1 : 0;
		
		return rs;
	}
	
	public int deletePersonalField(PersonalFieldSettingDTO pfsdto) {
		return dao.deletePersonalField(pfsdto);
	}
	
	// 경력기술서
		public int insertCareerField(CareerFieldSettingDTO cfsdto) {
			int rs= 0;
			String[] careerField_idx= cfsdto.getCareerField_idx().split(",");
			List<String> careerField_idxList = new ArrayList<String>();
			for(String s : careerField_idx) {
				careerField_idxList.add(s);
			}
			System.out.println("serviceimpl : "+ careerField_idxList.toString());
			
			
			String[] careerField= cfsdto.getCareerField().split(",");
			List<String> careerFieldList = new ArrayList<String>();
			for(String s : careerField) {
				careerFieldList.add(s);
			}
			System.out.println("serviceimpl : "+ careerFieldList.toString());
			
			int repeatCareerFieldNum =0;
			
			for(int i =0; i<careerField_idxList.size(); i++) {
				cfsdto.setCareerField_idx(careerField_idxList.get(i));
				for(int j =repeatCareerFieldNum; j<careerFieldList.size(); j++) {
					if(careerFieldList.get(j).equals("0")&&!careerFieldList.get(j+1).equals(null)) {
						System.out.println("삽입1 : "+ careerFieldList.toString());
						cfsdto.setCareerField(careerFieldList.get(j));
						rs += dao.insertCareerField(cfsdto);
					}else if(!careerFieldList.get(j).equals("1")&&!careerFieldList.get(j).equals("0")) {
						careerFieldList.remove(j+1);
						System.out.println("삽입2 : "+ careerFieldList.toString());
						cfsdto.setCareerField(careerFieldList.get(j));
						rs += dao.insertCareerField(cfsdto);
					}else if(careerFieldList.get(j).equals("1")){
						careerFieldList.remove(j);
						System.out.println("제거 : "+careerFieldList.toString());
						repeatCareerFieldNum = j;
						break;
					}
				}
			}
			
			rs = (rs==careerField_idxList.size()) ? 1 : 0;
			
			return rs;
		}
		
		public int deleteCareerField(CareerFieldSettingDTO cfsdto) {
			return dao.deleteCareerField(cfsdto);
		}
		
		// 제출서류 파일
		public int insertRsmFileUploadField(RsmFileUploadFieldSettingDTO rfsdto) {
			int rs= 0;
			String[] rsmFileUploadField_idx= rfsdto.getRsmfileupload_field_idx().split(",");
			List<String> rsmFileUploadField_idxList = new ArrayList<String>();
			for(String s : rsmFileUploadField_idx) {
				rsmFileUploadField_idxList.add(s);
			}
			System.out.println("serviceimpl : "+ rsmFileUploadField_idxList.toString());
			
			
			String[] fileField= rfsdto.getFileField().split(",");
			List<String> fileFieldList = new ArrayList<String>();
			for(String s : fileField) {
				fileFieldList.add(s);
			}
			System.out.println("serviceimpl : "+ fileFieldList.toString());
			
			int repeatRsmFileUploadFieldNum =0;
			
			for(int i =0; i<rsmFileUploadField_idxList.size(); i++) {
				rfsdto.setRsmfileupload_field_idx(rsmFileUploadField_idxList.get(i));
				for(int j =repeatRsmFileUploadFieldNum; j<fileFieldList.size(); j++) {
					if(fileFieldList.get(j).equals("0")&&!fileFieldList.get(j+1).equals(null)) {
						System.out.println("삽입1 : "+ fileFieldList.toString());
						rfsdto.setFileField(fileFieldList.get(j));
						rs += dao.insertRsmFileUploadField(rfsdto);
					}else if(!fileFieldList.get(j).equals("1")&&!fileFieldList.get(j).equals("0")) {
						fileFieldList.remove(j+1);
						System.out.println("삽입2 : "+ fileFieldList.toString());
						rfsdto.setFileField(fileFieldList.get(j));
						rs += dao.insertRsmFileUploadField(rfsdto);
					}else if(fileFieldList.get(j).equals("1")){
						fileFieldList.remove(j);
						System.out.println("제거 : "+fileFieldList.toString());
						repeatRsmFileUploadFieldNum = j;
						break;
					}
				}
			}
			
			rs = (rs==rsmFileUploadField_idxList.size()) ? 1 : 0;
			
			return rs;
		}
		
		public int deleteRsmFileUploadField(RsmFileUploadFieldSettingDTO rfsdto) {
			return dao.deleteRsmFileUploadField(rfsdto);
		}
}
