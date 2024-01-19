package com.gyeonglodang.dao;

import java.util.List;
import java.util.Map;


import com.gyeonglodang.dto.AnnouncementCustomDTO;
import com.gyeonglodang.dto.CustomizeDTO;
import com.gyeonglodang.dto.ManagerDTO;
import com.gyeonglodang.dto.PassFailDTO;
import com.gyeonglodang.dto.SuccessfulCandidateListDTO;

public interface PassFailDAO {
//	합격발표설정에 표시될 발표날짜와 세팅을 part_list table에서 가져오는 메소드
	public Map<String,Object> getDateSetting(Map<String,Object> company_part);
	
//	전형생성시 pass_fail_text 테이블에 미리 part와 field값을 넣어두고 
//	그곳에 합격문구와 불합격문구를 업데이트 해주는 메소드
	public int updatePass(PassFailDTO dto);
	public int updateFail(PassFailDTO dto);
	
//	발표날짜와 세팅을 변경시 사용할 메소드
	public int updateDateSetting(Map<String,Object> map);
	
//	합격발표설정에 part와 엮인 field 값들과 그 두개와 엮인 합격,불합격 문구를 등록 혹은 수정하는
//	a태그를 표시하기위해 pass_fail_text 테이블에서 모든값을 리스트라 담아오는 메소드
	public List<PassFailDTO> getTextList(Map<String,Object> company_part);
	
// 	합격,불합격문구 수정시 담겨있던 문구를 보여주기위해 가져오는 메소드
	public String getPassText(PassFailDTO dto);
	public String getFailText(PassFailDTO dto);
	
//	합격자발표 문구가 커스텀으로 등록되있는 문구 제목들을 리스트로 받아오는 메소드
//	미사용할 여지가있음(수정예정)
	public List<AnnouncementCustomDTO> getCustomTitle(Map<String,Object> company_part);
	
//	part와 묶여있는 field값들을 리스트로 담아오는 메소드
//	passfailWrite의 드롭박스, successfulcandidateList의 임시 분야값 리스트(수정예정)에 사용
	public List<PassFailDTO> getFieldidxs(Map<String,Object> company_part);
	
//	합격자발표문구(커스텀)에서 제목드롭박스 수정시 수정된 제목을 기준으로 
//	announcement_custom 테이블에서 값들을 가져오는 메소드
	public AnnouncementCustomDTO getCustom(Map<String,Object> company_custom);
	
//	합격발표설정에서 합격자발표(커스텀)에 등록 버튼을 누르면 커스텀을 넘어가면서
//	드롭박스 설정과 보이는 값들이 첫번째 제목을 기준으로 설정하기위해
//	announcement_custom 테이블에서 idx값이 1인 값들을 가져오는 메소드
	public AnnouncementCustomDTO getMainCustom(Map<String,Object> map);
	
//	합격자발표문구(커스텀)에 새로 문구를 등록 혹은 수정할때 사용하는 메소드
	public int insertCustom(Map<String,Object> map);	
	public int updateCustom(Map<String,Object> map);
	
//	합격자발표문구(커스텀)에서 제목에 묶여있는 지원자들의 내역을 가져오기위해
//	announcement_applicant테이블에서 제목을 기준으로 값을 가져오는 메소드
	List<AnnouncementCustomDTO> getApplicantList(Map<String,Object> company_title);
	
//	upload에서 등록한 excel파일을 저장하고 그 파일에서 값을 읽어내서 
//	successful_candidate_list 테이블에에 저장할때 쓰는 메소드
	public int insertSuccessfulList(SuccessfulCandidateListDTO dto);
	
//	합격자발표문구(커스텀) 에서 제목과 연결되는 지원자들을 등록할려고
//	excel파일을 업로드해서 값을 읽어내고 그값을  announcement_applicant테이블에 저장하는 메소드
	public int insertAA(Map<String,Object> map);
	
//	엑셀파일을 업로드 할때 이미 들어가있던 테이블의 값을 삭제하는 메소드
//	수정 할때를 위해 만들어둔것
	public int deleteSCList();
	public int deleteCustomList(Map<String,Object> map);
	public int deleteCustomizeList(Map<String,Object> map);
	
//	합격자목록에서 검색 기능을 사용할때 사용하는 메소드
//	필드값이나 합격여부값에 따라 사용하는 메소드가 달라짐
//	위에 메소드는 필드값이 전체일때 사용
	List<SuccessfulCandidateListDTO> getSuccessfulCandidateList(Map<String,Object> company_pass_fail);
	List<SuccessfulCandidateListDTO> getSuccessfulList(Map<String,Object> company_pass_fail);

//	like문을 이용하기에 이름이 빈값으로 올경우 전체리스트를 가져오게됨
	List<CustomizeDTO> getCustomizeList(Map<String,Object> map);

//	customize_setting 테이블에 값을 insert하는 메소드
	public int insertCS(Map<String,Object> map);
	
//	applicant_cutomize는 맞춤설정이 적용된 문자를 분야별 혹은 개인별로
//	수험번호와 연결해놓는 테이블
//	개인에게 발송될 최종메세지를 수험번호를 통해 가져올수있도록 설계  
//	applicant_cutomize테이블을 수정 혹은 삭제할때 사용
	public int deleteAC(Map<String,Object> map);
//	applicant_customize 테이블에 등록할때 사용
	public int insertAC(Map<String,Object> map);
	
//	합격여부가 등록되어있는 SuccessfulCandidateList테이블과
//	맞춤설정관련 정보가 들어있는 customize_setting테이블에서
//	수험번호가 동일한 경우의 값들을 가져오는 메소드
//	지원자가 본인 조회를 할때 설정되어있는 값을 리플레이스
//  현재로선 사용하지않음
	public Map<String, Object> getCommon(Map<String,Object> map);
	
//	전형 목록을 드롭박스에 등록하기위해 전형제목들을 가져옴
	public List<ManagerDTO> getPartTitle(Map<String,Object> map);
	
	
	Map<String,Object> getFinal(String scode);
}
