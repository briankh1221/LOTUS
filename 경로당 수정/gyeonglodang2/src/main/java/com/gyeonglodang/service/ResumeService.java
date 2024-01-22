package com.gyeonglodang.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

public interface ResumeService {
//	List<PersonalDTO> getPersonalList(PersonalDTO dto);
	
	// 입사지원서 정보 insert
	int insertResume(InfoDTO idto, InfoCustomDTO icdto, PersonalDTO pdto, PersonalQDTO pqdto, CareerDTO cdto, CareerQDTO cqdto, ResumeCustomDTO rcdto, SentenceTypeDTO stdto, TableTypeDTO ttdto, TableTypeQDTO ttqdto, ResumeFileDTO rfDTO, ResumeFileUploadDTO rfuDTO, HttpServletRequest request, List<MultipartFile> file, Map<String,Object> map) throws Exception;
	
	// 입사지원서 정보 get
	InfoDTO getInfo(InfoDTO idto);
	List<InfoCustomDTO> getInfoCustom(InfoCustomDTO icdto);
	PersonalDTO getPersonal(PersonalDTO pdto);
	List<PersonalQDTO> getPersonalQ(PersonalDTO pdto);
	CareerDTO getCareer(CareerDTO cdto);
	List<CareerQDTO> getCareerQ(CareerDTO cdto);
	List<ResumeCustomDTO> getResumeCustom(ResumeCustomDTO rcdto);
	List<SentenceTypeDTO> getSentenceType(ResumeCustomDTO rcdto);
	List<TableTypeDTO> getTableType(ResumeCustomDTO rcdto);
	List<TableTypeQDTO> getTableTypeQ(ResumeCustomDTO rcdto);
	ResumeFileDTO getResumeFile(ResumeFileDTO rfdto);
	List<ResumeFileUploadSetDTO> getResumeFileUpload(ResumeFileDTO rfdto);
	
	// 채용별 정보 get
	List<InfoCustomDTO> getInfoCustomField(InfoCustomDTO icdto);
	List<InfoCustomFieldSettingDTO> getInfoCustomFieldSetting(InfoCustomFieldSettingDTO icfsdto);
	List<ResumeCustomDTO> getResumeCustomField(ResumeCustomDTO rcdto);
	List<CustomFieldSettingDTO> getResumeCustomFieldSetting(CustomFieldSettingDTO customsdto);
	PersonalDTO getPersonalField(PersonalDTO pdto);
	List<PersonalFieldSettingDTO> getPersonalFieldSetting(PersonalFieldSettingDTO pfsdto);
	CareerDTO getCareerField(CareerDTO cdto);
	List<CareerFieldSettingDTO> getCareerFieldSetting(CareerFieldSettingDTO cfsdto);
	List<ResumeFileUploadSetDTO> getResumeFileUploadField(ResumeFileDTO rfdto);
	List<RsmFileUploadFieldSettingDTO> getResumeFileUploadFieldSetting(RsmFileUploadFieldSettingDTO rfsdto);
	List<RecruitCategBoardDTO> getRecruitField(RecruitCategBoardDTO rcbdto);
	
	// 채용별 정보 insert
	int insertResumeField(InfoCustomFieldSettingDTO icfsdto, CustomFieldSettingDTO customsdto, PersonalFieldSettingDTO pfsdto, CareerFieldSettingDTO cfsdto, RsmFileUploadFieldSettingDTO rfsdto);
	
}
