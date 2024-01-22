package com.gyeonglodang.mybatis.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gyeonglodang.dto.AnnouncementCustomDTO;
import com.gyeonglodang.dto.CustomizeDTO;
import com.gyeonglodang.dto.ManagerDTO;
import com.gyeonglodang.dto.PassFailDTO;
import com.gyeonglodang.dto.SuccessfulCandidateListDTO;

@Mapper
public interface PassFailMapper {
	
	public Map<String, Object> getDateSetting(Map<String,Object> company_part);
	
	public int updateDateSetting(Map<String,Object> map);
		
	public int updatePass(PassFailDTO dto);
	
	public int updateFail(PassFailDTO dto);
	
	public List<PassFailDTO> getTextList(Map<String,Object> company_part);
	
	public String getPassText(PassFailDTO dto);
	
	public String getFailText(PassFailDTO dto);
	
	public List<PassFailDTO> getFieldidxs(Map<String,Object> company_part);
	
	public List<AnnouncementCustomDTO> getCustomTitle(Map<String,Object> company_part);
	
	public AnnouncementCustomDTO getCustom(Map<String,Object> company_custom);
	
	public AnnouncementCustomDTO getMainCustom(Map<String,Object> map);
	
	public int insertCustom(Map<String,Object> map);
	
	public int updateCustom(Map<String, Object> map);
	
	public List<AnnouncementCustomDTO> getApplicantList(Map<String,Object> company_title);
	
	public List<SuccessfulCandidateListDTO> getSuccessfulCandidateList(Map<String,Object> company_pass_fail);
	
	public List<SuccessfulCandidateListDTO> getSuccessfulList(Map<String, Object> company_pass_fail);
	
	public int insertSuccessfulList(SuccessfulCandidateListDTO dto);
	
	public int insertAA(Map<String, Object> map);
	
	public int deleteSCList();
	
	public int deleteCustomList(Map<String, Object> map);
	
	public int deleteCustomizeList(Map<String, Object> map);
			
	public List<CustomizeDTO> getCustomizeList(Map<String, Object> map);
	
	public int insertCS(Map<String, Object> map);
	
	public int deleteAC(Map<String, Object> map);
	
	public int insertAC(Map<String, Object> map);
	
	public Map<String, Object> getCommon(Map<String,Object> map);
	
	public List<ManagerDTO> getPartTitle(Map<String, Object> map);
	
	public Map<String,Object> getFinal(String scode);
	
//	public int insertPartField(Map<String,Object> map);
	
//	public int insertPassFailText(PassFailDTO dto);
}
