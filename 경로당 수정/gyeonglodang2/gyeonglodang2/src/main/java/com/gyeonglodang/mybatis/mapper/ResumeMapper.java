package com.gyeonglodang.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
import com.gyeonglodang.dto.ResumeFileUploadSetDTO;
import com.gyeonglodang.dto.RsmFileUploadFieldSettingDTO;
import com.gyeonglodang.dto.SentenceTypeDTO;
import com.gyeonglodang.dto.TableTypeDTO;
import com.gyeonglodang.dto.TableTypeQDTO;

@Mapper
public interface ResumeMapper {
	
//	----------------------지원서 정보 DB값 입력----------------------	
	// 인적사항 
	public int insertInfo(InfoDTO idto);
	public int deleteInfo(InfoDTO idto);
	
	// 인적사항 커스텀
	public int insertInfoCustom(InfoCustomDTO icdto);
	public int deleteInfoCustom(InfoCustomDTO icdto);
	
	// 자기소개서
	public int insertPersonal(PersonalDTO pdto);
	public int deletePersonal(PersonalDTO pdto);
	public PersonalDTO getPersonalIdx(PersonalDTO pdto);
	
	//자기소개서 문항
	public int insertPersonalQ(PersonalQDTO pqdto);
	public int deletePersonalQ(PersonalDTO pdto);
	
	
	// 경력기술서
	public int insertCareer(CareerDTO cdto);
	public int deleteCareer(CareerDTO cdto);
	public CareerDTO getCareerIdx(CareerDTO cdto);
	
	// 경력기술서 문항
	public int insertCareerQ(CareerQDTO cqdto);
	public int deleteCareerQ(CareerDTO cdto);
	
	// 맞춤형 문항
	public int insertResumeCustom(ResumeCustomDTO rcdto);
	public int deleteResumeCustom(ResumeCustomDTO rcdto);
	public ResumeCustomDTO getResumeCustomIdx(ResumeCustomDTO rcdto);
	
	// 맞춤형 문항_단문형 문항
	public int insertSentenceType(SentenceTypeDTO stdto);
	public int deleteSentenceType(SentenceTypeDTO stdto);
	
	// 맞춤형 문항_테이블형 정보
	public int insertTableType(TableTypeDTO ttdto);
	public int deleteTableType(TableTypeDTO ttdto);
	public TableTypeDTO getTableTypeIdx(TableTypeDTO ttdto);
	
	// 맞춤형 문항_테이블형 정보_문항
	public int insertTableTypeQ(TableTypeQDTO ttqdto);
	public int deleteTableTypeQ(TableTypeQDTO ttqdto);
	
	// 파일 업로드 
	public int insertResumeFileUpload(ResumeFileUploadSetDTO rfusdto);
	public int deleteResumeFileUpload(ResumeFileDTO rfdto);
	public int insertResumeFile(ResumeFileDTO rfdto);
	public int deleteResumeFile(ResumeFileDTO rfdto);
	public int getResumeFileIdx(ResumeFileDTO rfdto);
	
//	----------------------지원서 정보 DB에서 호출----------------------
	
	public InfoDTO getInfo(InfoDTO idto);
	public List<InfoCustomDTO> getInfoCustom(InfoCustomDTO icdto);
	public PersonalDTO getPersonal(PersonalDTO pdto);
	public List<PersonalQDTO> getPersonalQ(PersonalDTO pdto);
	public CareerDTO getCareer(CareerDTO cdto);
	public List<CareerQDTO> getCareerQ(CareerDTO cdto);
	public List<ResumeCustomDTO> getResumeCustom(ResumeCustomDTO rcdto);
	public List<SentenceTypeDTO> getSentenceType(ResumeCustomDTO rcdto);
	public List<TableTypeDTO> getTableType(ResumeCustomDTO rcdto);
	public List<TableTypeQDTO> getTableTypeQ(ResumeCustomDTO rcdto);
	public ResumeFileDTO getResumeFile(ResumeFileDTO rfdto);
	public List<ResumeFileUploadSetDTO> getResumeFileUpload(ResumeFileDTO rfdto);
	
//	============================================================================
	
//	----------------------지원서 채용분야별 정보 DB에서 호출----------------------
	
	public List<InfoCustomDTO> getInfoCustomField(InfoCustomDTO icdto);
	public List<InfoCustomFieldSettingDTO> getInfoCustomFieldSetting(InfoCustomFieldSettingDTO icfsdto);
	public List<ResumeCustomDTO> getResumeCustomField(ResumeCustomDTO rcdto);
	public List<CustomFieldSettingDTO> getResumeCustomFieldSetting(CustomFieldSettingDTO customsdto);
	public PersonalDTO getPersonalField(PersonalDTO pdto);
	public List<PersonalFieldSettingDTO> getPersonalFieldSetting(PersonalFieldSettingDTO pfsdto);
	public CareerDTO getCareerField(CareerDTO cdto);
	public List<CareerFieldSettingDTO> getCareerFieldSetting(CareerFieldSettingDTO cfsdto);
	public List<ResumeFileUploadSetDTO> getResumeFileUploadField(ResumeFileDTO rfdto);
	public List<RsmFileUploadFieldSettingDTO> getResumeFileUploadFieldSetting(RsmFileUploadFieldSettingDTO rfsdto);
	public List<RecruitCategBoardDTO> getRecruitField(RecruitCategBoardDTO rcbdto);
	
//	----------------------지원서 채용분야별 정보 DB로 삽입----------------------
	public int insertInfoCustomField(InfoCustomFieldSettingDTO icfsdto);
	public int deleteInfoCustomField(InfoCustomFieldSettingDTO icfsdto);
	public int insertCustomField(CustomFieldSettingDTO customsdto);
	public int deleteCustomField(CustomFieldSettingDTO customsdto);
	public int insertPersonalField(PersonalFieldSettingDTO pfsdto);
	public int deletePersonalField(PersonalFieldSettingDTO pfsdto);
	public int insertCareerField(CareerFieldSettingDTO cfsdto);
	public int deleteCareerField(CareerFieldSettingDTO cfsdto);
	public int insertRsmFileUploadField(RsmFileUploadFieldSettingDTO rfsdto);
	public int deleteRsmFileUploadField(RsmFileUploadFieldSettingDTO rfsdto);
}
