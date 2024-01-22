package com.gyeonglodang.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyeonglodang.dto.FaqBoardDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.ManagerDTO;
import com.gyeonglodang.dto.QnaBoardDTO;
import com.gyeonglodang.dto.QnaFileDTO;
import com.gyeonglodang.mybatis.mapper.ManagerMapper;
import com.gyeonglodang.service.ImanagerService;

@Service
public class ManagerServiceImpl implements ImanagerService{
	@Autowired
	ManagerMapper dao;
	
	@Override
	public List<ManagerDTO> getGonggoList(Map<String,Object> map) {
		return dao.getGonggoList(map);
		// 서비스에서 리턴이 들어감
		}
	
	@Override
	public ManagerDTO getManager(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return dao.getManager(map);
	}

	@Override
	public int insert(ManagerDTO mDto) {
		// TODO Auto-generated method stub
		return dao.insert(mDto);
	}

	@Override
	public List<ManagerDTO> part_list_detailForm(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return dao.part_list_detailForm(map);
	}

	@Override
	public int update(ManagerDTO mDto) {
		// TODO Auto-generated method stub
		return dao.update(mDto);
	}

	@Override
	public int delete(ManagerDTO mDto) {
		// TODO Auto-generated method stub
		return dao.delete(mDto);
	}

	@Override
	public List<FaqBoardDTO> getFaqBoList(FaqBoardDTO fDto) {
		// TODO Auto-generated method stub
		return dao.getFaqBoList(fDto);
	}

	@Override
	public int faqInsert(FaqBoardDTO fDto) {
		// TODO Auto-generated method stub
		return dao.faqInsert(fDto);
	}

	@Override
	public FaqBoardDTO getFaq(int faq_idx) {
		// TODO Auto-generated method stub
		return dao.getFaq(faq_idx);
	}

	@Override
	public int faqUpdate(FaqBoardDTO fDto) {
		// TODO Auto-generated method stub
		return dao.faqUpdate(fDto);
	}

	@Override
	public List<FaqBoardDTO> faqDetailForm(int faq_idx) {
		// TODO Auto-generated method stub
		return dao.faqDetailForm(faq_idx);
	}

	@Override
	public int faqDelete(FaqBoardDTO fDto) {
		// TODO Auto-generated method stub
		return dao.faqDelete(fDto);
	}

	@Override
	   public List<QnaBoardDTO> getQnaBoList(int pageNum, int companyIdx) {
	      // TODO Auto-generated method stub
	      Map<String, Integer> map = new HashMap<String, Integer>();
	      map.put("startNum", 10*(pageNum - 1));
	      map.put("endNum", 10*(pageNum));
	      map.put("companyIdx", companyIdx);
	      
	      return dao.getQnaBoList(map);
	   }

	@Override
	public QnaBoardDTO qnaDetailForm(int idx) {
		// TODO Auto-generated method stub
		return dao.qnaDetailForm(idx);
	}

	@Override
	public int qnaInsert(QnaBoardDTO qDto) {
		// TODO Auto-generated method stub
		return dao.qnaInsert(qDto);
	}

	@Override
	public int qnaUpdate(QnaBoardDTO qDto) {
		// TODO Auto-generated method stub
		return dao.qnaUpdate(qDto);
	}

	@Override
	public int qnaDelete(QnaBoardDTO qDto) {
		// TODO Auto-generated method stub
		return dao.qnaDelete(qDto);
	}
	
	@Override
	public int loadqnaidx() {
		// TODO Auto-generated method stub
		return dao.loadqnaidx();
	}

	@Override
	public int insertFile(QnaFileDTO fDto) {
		// TODO Auto-generated method stub
		return dao.insertFile(fDto);
	}

	@Override
	public QnaFileDTO getFileName(QnaBoardDTO qDto) {
		// TODO Auto-generated method stub
		return dao.getFileName(qDto);
	}

	@Override
	public int insertPartField(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.insertPartField(map);
	}

	@Override
	public int insertPassFailText(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.insertPassFailText(map);
	}

	@Override
	public List<String> getCategory(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.getCategory(map);
	}

	@Override
	public int getQnaListCount() {
		// TODO Auto-generated method stub
		return dao.getQnaListCount();
	}

	@Override
	public List<JobNotice_boardDTO> getJobList() {
		// TODO Auto-generated method stub
		return dao.getJobList();
	}

	@Override
	public List<QnaBoardDTO> getSearchList() {
		// TODO Auto-generated method stub
		return dao.getSearchList();
	}
	
	@Override
	public List<QnaBoardDTO> getSearchList2(QnaBoardDTO qnaBoardDTO) {
		// TODO Auto-generated method stub
		return dao.getSearchList2(qnaBoardDTO);
	}


	@Override
	public QnaBoardDTO qnaSearchViewDetailForm(int idx) {
		// TODO Auto-generated method stub
		return dao.qnaSearchViewDetailForm(idx);
	}

	@Override
	public QnaBoardDTO checkLogin(QnaBoardDTO qnaBoardDTO) {
		// TODO Auto-generated method stub
		return dao.checkLogin(qnaBoardDTO);
	}



}
