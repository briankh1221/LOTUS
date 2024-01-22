package com.gyeonglodang.service;

import java.util.List;
import java.util.Map;

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
import com.gyeonglodang.dto.JobNoticeDTO;
import com.gyeonglodang.dto.RecruitCateg_boardDTO;
import com.gyeonglodang.dto.ResumeFileUploadSetDTO;
import com.gyeonglodang.dto.RsmFileDTO;

public interface RecruitResumeService {
	List<JobNoticeDTO> getTitle(int companyIdx);	
	ApplicantDTO getApplicant(String email);
	ApplicantDTO getApplicant2(Map<String,Object> map);
	int insertApplicant(ApplicantDTO dto);
	List<JobNoticeDTO> getJobNoticeList(Map<String,Object> map);
	String getJBTittle(int noticeControl_idx);
	GetPersonalDTO getPersonalList(int personalidx);
	int insertPersonalValue(Map<String, Object> map);
	GetCareerDTO getCareerList(int careeridx);
	GetPersonalCustomDTO getPersonalCustom(int infocustomidx);
	GetResumeCustomDTO getResumeCustom(int resumecustomidx);
	List<GetSentenceTypeDTO> getSentenceType(int resumeCustomIdx);
	List<GetTableTypeDTO> getTableTypeList(int resumeCustomIdx);
	int insert_infocustom(Map<String, Object> insertMap);
	int insert_sentence(Map<String, Object> insertMap);
	int insert_table(Map<String, Object> insertMap);
	int insert_info(Map<String, Object> infoMap);
	List<GetTableTypeQDTO> getTableTypeQListAll();
	List<GetPersonalQDTO> getPersonalQList();
	List<GetCareerQDTO> getCareerQList();
	GetInfoDTO getInfo(int noticeControl_idx);
	List<String> getPersonalValueList(Map<String, Object> applicant_info);
	int insertCareerValue(Map<String, Object> insertMap);
	List<String> getCareerValueList(Map<String, Object> applicant_info);
	List<String> getInfoCustomValueList(Map<String, Object> applicant_info);
	int deletePersonalValue(Map<String, Object> applicant_info);
	int deleteCareerValue(Map<String, Object> applicant_info);
	int deleteSentenceValue(Map<String, Object> applicant_info);
	int deleteInfoCustomValue(Map<String, Object> applicant_info);
	int deleteTableValue(Map<String, Object> applicant_info);
	int deleteInfoValue(Map<String, Object> applicant_info);
	int deleteResumeFile(Map<String, Object> applicant_info);
	List<String> getSentenceValueList(Map<String, Object> applicant_info);
	List<String> gettableValueList(Map<String, Object> applicant_info);
	GetInfoValueDTO getinfoValue(Map<String, Object> applicant_info);
	List<GetFileValueDTO> getFileValueList(Map<String, Object> applicant_info);
	List<Integer> getInfoCustomIdxList(Map<String, Object> application_info);
	List<Integer> getPersonalIdxList(Map<String, Object> application_info);
	List<Integer> getCareerIdxList(Map<String, Object> application_info);
	List<Integer> getResumeCustomIdxList(Map<String, Object> application_info);
	List<Integer> getFileIdxList(Map<String, Object> application_info);
	RsmFileDTO getResumeFile(int noticeControl_idx);
	ResumeFileUploadSetDTO getRsmFileUpload(int fileidx);
	int insertResumeFile(Map<String, Object> fileUploadmap);
	int insertPersonalValue_final(Map<String, Object> insertMap);
	int insertCareerValue_final(Map<String, Object> insertMap);
	int insert_final_infocustom(Map<String, Object> insertMap);
	int insert_final_sentence(Map<String, Object> insertMap);
	
	int insert_final_table(Map<String, Object> insertMap);

	int insert_final_info(Map<String, Object> infoMap);
	int insertResumeFile_final(Map<String, Object> fileUploadmap);
	List<String> getPersonalValueList_final(Map<String, Object> applicant_info);
	List<String> getCareerValueList_final(Map<String, Object> applicant_info);
	List<String> getInfoCustomValueList_final(Map<String, Object> applicant_info);
	List<String> getSentenceValueList_final(Map<String, Object> applicant_info);
	List<String> gettableValueList_final(Map<String, Object> applicant_info);
	GetInfoValueFinalDTO getinfoValue_final(Map<String, Object> applicant_info);
	List<GetFileValueFinalDTO> getFileValueList_final(Map<String, Object> applicant_info);
	List<RecruitCateg_boardDTO> getField(RecruitCateg_boardDTO categ);
	int updateField(Map<String,Object> map);
	int updateScode(Map<String,Object> map);
	String getFieldCode(Map<String,Object> map);
	String getCountScode(int noticeControl_idx);
	Map<String,Object> getCategScode(Map<String,Object> map);
	
	
}