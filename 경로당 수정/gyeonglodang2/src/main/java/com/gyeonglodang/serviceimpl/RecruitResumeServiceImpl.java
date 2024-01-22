package com.gyeonglodang.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.gyeonglodang.mybatis.mapper.RecruitResumeMapper;
import com.gyeonglodang.service.RecruitResumeService;

@Service
public class RecruitResumeServiceImpl implements RecruitResumeService{
	@Autowired
	RecruitResumeMapper dao;
	
	public List<RecruitCateg_boardDTO> getField(RecruitCateg_boardDTO categ){
		return dao.getField(categ);
	}
	
	@Override
	public List<JobNoticeDTO> getTitle(int companyIdx){
		return dao.getTitle(companyIdx);
	}

	@Override
	public ApplicantDTO getApplicant(String email) {
		return dao.getApplicant(email);
	}
	
	@Override
	public ApplicantDTO getApplicant2(Map<String,Object> map) {
		return dao.getApplicant2(map);
	}

	@Override
	public int insertApplicant(ApplicantDTO dto) {
		return dao.insertApplicant(dto);
	}

	@Override
	public List<JobNoticeDTO> getJobNoticeList(Map<String,Object> map) {
		return dao.getJobNoticeList(map);
	}

	@Override
	public String getJBTittle(int noticeControl_idx) {
		return dao.getJBTittle(noticeControl_idx);
	}

	@Override
	public GetPersonalDTO getPersonalList(int personalidx) {
		return dao.getPersonalList(personalidx);
	}
	
	@Override
	public GetCareerDTO getCareerList(int careeridx) {
		return dao.getCareerList(careeridx);
	}
	
	@Override
	public GetPersonalCustomDTO getPersonalCustom(int infocustomidx) {
		return dao.getPersonalCustom(infocustomidx);
	}
	
	@Override
	public int insertPersonalValue(Map<String, Object> map) {
		return dao.insertPersonalValue(map); 
	}


	@Override
	public GetResumeCustomDTO getResumeCustom(int resumecustomidx) {
		return dao.getResumeCustom(resumecustomidx);
	}

	@Override
	public List<GetSentenceTypeDTO> getSentenceType(int resumeCustomIdx) {
		return dao.getSentenceType(resumeCustomIdx);
	}

	@Override
	public List<GetTableTypeDTO> getTableTypeList(int resumeCustomIdx) {
		return dao.getTableTypeList(resumeCustomIdx);
	}


	@Override
	public int insert_infocustom(Map<String, Object> insertMap) {
		return dao.insert_infocustom(insertMap);
	}


	@Override
	public int insert_sentence(Map<String, Object> insertMap) {
		return dao.insert_setence(insertMap);
	}


	@Override
	public int insert_table(Map<String, Object> insertMap) {
		return dao.insert_table(insertMap);
	}

	@Override
	public int insert_info(Map<String, Object> infoMap) {
		return dao.insert_info(infoMap);
	}

	@Override
	public List<GetTableTypeQDTO> getTableTypeQListAll() {
		return dao.getTableTypeQListAll();
	}

	@Override
	public List<GetPersonalQDTO> getPersonalQList() {
		return dao.getPersonalQList();
	}

	@Override
	public List<GetCareerQDTO> getCareerQList() {
		return dao.getCareerQList();
	}

	@Override
	public GetInfoDTO getInfo(int noticeControl_idx) {
		return dao.getInfo(noticeControl_idx);
	}


	@Override
	public List<String> getPersonalValueList(Map<String, Object> applicant_info) {
		return dao.getPersonalValueList(applicant_info);
	}

	@Override
	public int insertCareerValue(Map<String, Object> insertMap) {
		return dao.insertCareerValue(insertMap);
	}

	@Override
	public List<String> getCareerValueList(Map<String, Object> applicant_info) {
		return dao.getCareerValueList(applicant_info);
	}


	@Override
	public List<String> getInfoCustomValueList(Map<String, Object> applicant_info) {
		return dao.getInfoCustomValueList(applicant_info);
	}

	@Override
	public int deletePersonalValue(Map<String, Object> applicant_info) {
		return dao.deletePersonalValue(applicant_info);
	}

	@Override
	public int deleteCareerValue(Map<String, Object> applicant_info) {
		return dao.deleteCareerValue(applicant_info);
	}

	@Override
	public int deleteSentenceValue(Map<String, Object> applicant_info) {
		return dao.deleteSentenceValue(applicant_info);
	}

	@Override
	public int deleteInfoCustomValue(Map<String, Object> applicant_info) {
		return dao.deleteInfoCustomValue(applicant_info);
	}

	@Override
	public int deleteTableValue(Map<String, Object> applicant_info) {
		return dao.deleteTableValue(applicant_info);
	}
	
	@Override
	public int deleteInfoValue(Map<String, Object> applicant_info) {
		return dao.deleteInfoValue(applicant_info);
	}

	@Override
	public int deleteResumeFile(Map<String, Object> applicant_info) {
		return dao.deleteResumeFile(applicant_info);
	}
	
	@Override
	public List<String> getSentenceValueList(Map<String, Object> applicant_info) {
		return dao.getSentenceValueList(applicant_info);
	}

	@Override
	public List<String> gettableValueList(Map<String, Object> applicant_info) {
		return dao.gettableValueList(applicant_info);
	}

	@Override
	public GetInfoValueDTO getinfoValue(Map<String, Object> applicant_info) {
		return dao.getinfoValue(applicant_info);
	}
	
	@Override
	public List<GetFileValueDTO> getFileValueList(Map<String, Object> applicant_info) {
		return dao.getFileValueList(applicant_info);
	}

	@Override
	public List<Integer> getInfoCustomIdxList(Map<String, Object> application_info) {
		return dao.getInfoCustomIdxList(application_info);
	}

	@Override
	public List<Integer> getPersonalIdxList(Map<String, Object> application_info) {
		return dao.getPersonalIdxList(application_info);
	}

	@Override
	public List<Integer> getCareerIdxList(Map<String, Object> application_info) {
		return dao.getCareerIdxList(application_info);
	}

	@Override
	public List<Integer> getResumeCustomIdxList(Map<String, Object> application_info) {
		return dao.getResumeCustomIdxList(application_info);
	}

	@Override
	public List<Integer> getFileIdxList(Map<String, Object> application_info) {
		return dao.getFileIdxList(application_info);
	}

	@Override
	public RsmFileDTO getResumeFile(int noticeControl_idx) {
		return dao.getResumeFile(noticeControl_idx);
	}

	@Override
	public ResumeFileUploadSetDTO getRsmFileUpload(int fileidx) {
		return dao.getRsmFileUpload(fileidx);
	}

	@Override
	public int insertResumeFile(Map<String, Object> fileUploadmap) {
		return dao.insertResumeFile(fileUploadmap);
	}

	@Override
	public int insertPersonalValue_final(Map<String, Object> insertMap) {
		return dao.insertPersonalValue_final(insertMap);
	}

	@Override
	public int insertCareerValue_final(Map<String, Object> insertMap) {
		return dao.insertCareerValue_final(insertMap);
	}

	@Override
	public int insert_final_infocustom(Map<String, Object> insertMap) {
		return dao.insert_final_infocustom(insertMap);
	}

	@Override
	public int insert_final_sentence(Map<String, Object> insertMap) {
		return dao.insert_final_sentence(insertMap);
	}

	@Override
	public int insert_final_table(Map<String, Object> insertMap) {
		System.out.println("serviceImpl: "+insertMap.get("value"));
		return dao.insert_final_table(insertMap);
	}

	@Override
	public int insert_final_info(Map<String, Object> infoMap) {
		return dao.insert_final_info(infoMap);
	}

	@Override
	public int insertResumeFile_final(Map<String, Object> fileUploadmap) {
		return dao.insertResumeFile_final(fileUploadmap);
	}

	@Override
	public List<String> getPersonalValueList_final(Map<String, Object> applicant_info) {
		return dao.getPersonalValueList_final(applicant_info);
	}

	@Override
	public List<String> getCareerValueList_final(Map<String, Object> applicant_info) {
		return dao.getCareerValueList_final(applicant_info);
	}

	@Override
	public List<String> getInfoCustomValueList_final(Map<String, Object> applicant_info) {
		return dao.getInfoCustomValueList_final(applicant_info);
	}

	@Override
	public List<String> getSentenceValueList_final(Map<String, Object> applicant_info) {
		return dao.getSentenceValueList_final(applicant_info);
	}

	@Override
	public List<String> gettableValueList_final(Map<String, Object> applicant_info) {
		return dao.gettableValueList_final(applicant_info);
	}

	@Override
	public GetInfoValueFinalDTO getinfoValue_final(Map<String, Object> applicant_info) {
		return dao.getinfoValue_final(applicant_info);
	}

	@Override
	public List<GetFileValueFinalDTO> getFileValueList_final(Map<String, Object> applicant_info) {
		return dao.getFileValueList_final(applicant_info);
	}


	@Override
	public int updateField(Map<String, Object> map) {
		return dao.updateField(map);
	}
	
	@Override
	public int updateScode(Map<String, Object> map) {
		return dao.updateScode(map);
	}

	@Override
	public String getFieldCode(Map<String, Object> map) {
		return dao.getFieldCode(map);
	}
	
	@Override
	public String getCountScode(int noticeControl_idx) {
		return dao.getCountScode(noticeControl_idx);
	}
	
	@Override
	public Map<String, Object> getCategScode(Map<String, Object> map) {
		return dao.getCategScode(map);
	}


	

	
}
