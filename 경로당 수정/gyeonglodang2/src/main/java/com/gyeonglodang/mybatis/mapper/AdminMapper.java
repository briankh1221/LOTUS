package com.gyeonglodang.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gyeonglodang.dto.Admininfo_boardDTO;
// AdminMapper.java:  ID & Password 확인 하기 위한 Mapper Interface *231104 권지현
@Mapper	
public interface AdminMapper {	
	// getAdminDTO를 가져오기 위한 메서드 *231101 허재영
	public Admininfo_boardDTO getAdminDTO(Admininfo_boardDTO dto);	

}
