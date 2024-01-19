package com.gyeonglodang.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyeonglodang.dto.Admininfo_boardDTO;
import com.gyeonglodang.mybatis.mapper.AdminMapper;
import com.gyeonglodang.service.AdminService;
// AdminServiceImpl.java: ID & Password 확인 하기 위한 메서드 *231103 권지현
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminMapper dao;
	
	// Mapper의 AdminDTO를 가져오기 위한 메서드 *231102 허재영
	@Override
	public Admininfo_boardDTO getAdminDTO(Admininfo_boardDTO dto) {	
		
		return dao.getAdminDTO(dto);	
	}
}
