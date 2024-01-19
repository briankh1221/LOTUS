package com.gyeonglodang.service;

import com.gyeonglodang.dto.Admininfo_boardDTO;
// AdminService.java:  ID & Password 확인 하기 위한 Interface *231101 권지현
public interface AdminService {
	// AdminDTO를 가져오기 위한 메서드 *231102 허재영
	Admininfo_boardDTO getAdminDTO(Admininfo_boardDTO dto);	
}
