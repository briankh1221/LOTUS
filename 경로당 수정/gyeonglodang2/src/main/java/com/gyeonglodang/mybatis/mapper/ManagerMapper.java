package com.gyeonglodang.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gyeonglodang.dto.FaqBoardDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.ManagerDTO;
import com.gyeonglodang.dto.QnaBoardDTO;
import com.gyeonglodang.dto.QnaFileDTO;

@Mapper
public interface ManagerMapper {

	//리스트 불러오기
	public List<ManagerDTO> getGonggoList(Map<String,Object> map);
	//리스트 한명 불러오기
	public ManagerDTO getManager(Map<String,Object> map);
	//리스트 삽입
	public int insert(ManagerDTO mDto);
	//리스트 상세보기	
	List<ManagerDTO> part_list_detailForm(Map<String,Object> map);
	//리스트 업데이트
	public int update(ManagerDTO mDto);
	//리스트 삭제
	public int delete(ManagerDTO mDto);
	//faqboard 리스트 불러오기
	public List<FaqBoardDTO> getFaqBoList(FaqBoardDTO fDto);
	//faqboard 상세보기
	List<FaqBoardDTO> faqDetailForm(int faq_idx);
	//faqboard 리스트 한개 불러오기
	public FaqBoardDTO getFaq(int faq_idx);
	//faqboard 리스트 추가
	public int faqInsert(FaqBoardDTO fDto);
	//faqboard 업데이트
	public int faqUpdate(FaqBoardDTO fDto);
	//faqboard 삭제
	public int faqDelete(FaqBoardDTO fDto);
	//qnaboard 리스트 불러오기
	public List<QnaBoardDTO> getQnaBoList(Map<String, Integer> map);
	//qnaboard 상세보기
	public QnaBoardDTO qnaDetailForm(int idx);
	
	public QnaBoardDTO qnaSearchViewDetailForm(int idx);
	//qnaboard 리스트 추가
	public int qnaInsert(QnaBoardDTO qDto);
	//qnaSearch 필요해서 만듬
	public List<QnaBoardDTO> getSearchList();
	//qna 리스트 업데이트
	public int qnaUpdate(QnaBoardDTO qDto);
	//qna 리스트 삭제
	public int qnaDelete(QnaBoardDTO qDto);
	//qna board idx 가져오기
	public int loadqnaidx(); 
	// qna_fileupload_table에 insert 하는 메소드
	public int insertFile(QnaFileDTO fDto);
	//
	public QnaFileDTO getFileName(QnaBoardDTO qDto);
	// qna리스트 총갯수
	public int getQnaListCount();
	
	public int insertPartField(Map<String, Object> map);

	public int insertPassFailText(Map<String, Object> map);

	public List<String> getCategory(Map<String, Object> map);
	
	public List<JobNotice_boardDTO> getJobList();
	
	public List<QnaBoardDTO> getSearchList2(QnaBoardDTO qnaBoardDTO);
	
	public QnaBoardDTO checkLogin(QnaBoardDTO qnaBoardDTO);
}
