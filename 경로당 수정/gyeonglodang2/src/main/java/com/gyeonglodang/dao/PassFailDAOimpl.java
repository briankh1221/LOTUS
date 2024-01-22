package com.gyeonglodang.dao;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyeonglodang.dto.AnnouncementCustomDTO;
import com.gyeonglodang.dto.CustomizeDTO;
import com.gyeonglodang.dto.ManagerDTO;
import com.gyeonglodang.dto.PassFailDTO;
import com.gyeonglodang.dto.SuccessfulCandidateListDTO;
import com.gyeonglodang.mybatis.mapper.PassFailMapper;

@Service
public class PassFailDAOimpl implements PassFailDAO {

	@Autowired
	PassFailMapper mapper;

	@Override
	public Map<String, Object> getDateSetting(Map<String, Object> company_part) {
		
		return mapper.getDateSetting(company_part);
	}
	@Override
	public int updateDateSetting(Map<String, Object> map) {
		
		return mapper.updateDateSetting(map);
	}
	@Override
	public int updatePass(PassFailDTO dto) {
		
		return mapper.updatePass(dto);
	}
	@Override
	public int updateFail(PassFailDTO dto) {
		
		return mapper.updateFail(dto);
	}
	@Override
	public List<PassFailDTO> getTextList(Map<String, Object> company_part) {
		
		return mapper.getTextList(company_part);
	}
	@Override
	public List<PassFailDTO> getFieldidxs(Map<String, Object> company_part) {
		
		return mapper.getFieldidxs(company_part);
	}
	@Override
	public String getPassText(PassFailDTO dto) {
		
		return mapper.getPassText(dto);
	}
	@Override
	public String getFailText(PassFailDTO dto) {
		
		return mapper.getFailText(dto);
	}
	@Override
	public List<AnnouncementCustomDTO> getCustomTitle(Map<String, Object> company_part) {
		
		return mapper.getCustomTitle(company_part);
	}
	@Override
	public AnnouncementCustomDTO getCustom(Map<String, Object> company_custom) {
		
		return mapper.getCustom(company_custom);
	}
	@Override
	public AnnouncementCustomDTO getMainCustom(Map<String,Object> map) {
		
		return mapper.getMainCustom(map);
	}
	@Override
	public int insertCustom(Map<String, Object> map) {
		
		return mapper.insertCustom(map);
	}
	@Override
	public int updateCustom(Map<String, Object> map) {
		
		return mapper.updateCustom(map);
	}
	@Override
	public List<AnnouncementCustomDTO> getApplicantList(Map<String, Object> company_title) {
		
		return mapper.getApplicantList(company_title);
	}
	//	fieldidx가 전체인경우 사용
	@Override
	public List<SuccessfulCandidateListDTO> getSuccessfulCandidateList(Map<String, Object> company_pass_fail) {
		
		return mapper.getSuccessfulCandidateList(company_pass_fail);
	}
	// fieldidx가 전체 이외인 경우 사용
	@Override
	public List<SuccessfulCandidateListDTO> getSuccessfulList(Map<String, Object> company_pass_fail) {
		
		return mapper.getSuccessfulList(company_pass_fail);
	}
	@Override
	public int insertSuccessfulList(SuccessfulCandidateListDTO dto) {
		
		return mapper.insertSuccessfulList(dto);
	}
	@Override
	public int deleteSCList() {
		
		return mapper.deleteSCList();
	}
	@Override
	public int deleteCustomList(Map<String,Object> map) {
		
		return mapper.deleteCustomList(map);
	}
	@Override
	public int insertAA(Map<String, Object> map) {
		
		return mapper.insertAA(map);
	}
	@Override
	public List<CustomizeDTO> getCustomizeList(Map<String, Object> map) {
		
		return mapper.getCustomizeList(map);
	}
	@Override
	public int deleteCustomizeList(Map<String, Object> map) {
		
		return mapper.deleteCustomizeList(map);
	}
	@Override
	public int insertCS(Map<String, Object> map) {
		
		return mapper.insertCS(map);
	}
	@Override
	public int deleteAC(Map<String, Object> map) {
		
		return mapper.deleteAC(map);
	}
	@Override
	public int insertAC(Map<String, Object> map) {
		
		return mapper.insertAC(map);
	}
	@Override
	public Map<String, Object> getCommon(Map<String,Object> map) {
		
		return mapper.getCommon(map);
	}
	@Override
	public List<ManagerDTO> getPartTitle(Map<String, Object> map) {
		
		return mapper.getPartTitle(map);
	}
	@Override
	public Map<String,Object> getFinal(String scode) {
		
		return mapper.getFinal(scode);
	}
//	파일 업로드시 사용

//	@Override
//	public int insertPartField(Map<String,Object> map) {
//		return mapper.insertPartField(map);
//	}

//	@Override
//	public int insertPassFailText(PassFailDTO dto) {
//		return mapper.insertPassFailText(dto);
//	}
}
