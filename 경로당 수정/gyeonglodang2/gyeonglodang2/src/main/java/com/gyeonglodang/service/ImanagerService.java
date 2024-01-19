package com.gyeonglodang.service;


import java.util.List;
import java.util.Map;

import com.gyeonglodang.dto.FaqBoardDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.ManagerDTO;
import com.gyeonglodang.dto.QnaBoardDTO;
import com.gyeonglodang.dto.QnaFileDTO;

public interface ImanagerService {
	//채용공고 리스트 죄회
	List<ManagerDTO> getGonggoList(Map<String,Object> map);
	//특정 채용공고 상세 조회
	ManagerDTO getManager(Map<String,Object> map);
	//채용공고 추가
	int insert(ManagerDTO mDto);
	//제목 누르면 매니저 폴더 상세페이지로 가기
	List<ManagerDTO> part_list_detailForm(Map<String,Object> map);
	//리스트 업데이트
	int update(ManagerDTO mDto);
	//리스트 삭제
	int delete(ManagerDTO mDto);
	//faq리스트
	List<FaqBoardDTO> getFaqBoList(FaqBoardDTO fDto);
	//faq리스트 추가
	int faqInsert(FaqBoardDTO fDto);
	//faq상세조회
	FaqBoardDTO getFaq(int faq_idx);
	//faq리스트 업데이트
	int faqUpdate(FaqBoardDTO fDto);
	//제목 누르면 faqlist 상세페이지로 가기
	List<FaqBoardDTO> faqDetailForm(int faq_idx);
	//faq리스트 삭제
	int faqDelete(FaqBoardDTO fDto);
	//qna리스트
	List<QnaBoardDTO> getQnaBoList(int pageNum, int companyIdx);
	//제목 누르면 qnalist 상세페이지로 가기
	QnaBoardDTO qnaDetailForm(int idx);

	QnaBoardDTO qnaSearchViewDetailForm(int idx);
	//qna리스트 추가
	int qnaInsert(QnaBoardDTO fDto);
	//qna리스트 업데이트
	int qnaUpdate(QnaBoardDTO qDto);
	//qna리스트 삭제
	int qnaDelete(QnaBoardDTO qDto); 
	//qna qna_Board에서 idx 가져오는 메소드
	int loadqnaidx(); 
	// qna_fileupload_table에 insert 하는 메소드
	int insertFile(QnaFileDTO fDto);
	// qna idx 가져오는 메소드
	QnaFileDTO getFileName(QnaBoardDTO qDto);
	// qna 리스트 총갯수 가져오기
	int getQnaListCount();
	//part_field테이블 추가	
	int insertPartField(Map<String,Object> map);
	//pass_fail_text테이블 추가	
	int insertPassFailText(Map<String,Object> map);

	List<String> getCategory(Map<String,Object> map);
	
	List<JobNotice_boardDTO> getJobList();
	
	List<QnaBoardDTO> getSearchList();
	
	List<QnaBoardDTO> getSearchList2(QnaBoardDTO qnaBoardDTO);
	
	QnaBoardDTO checkLogin(QnaBoardDTO qnaBoardDTO);
	
}

