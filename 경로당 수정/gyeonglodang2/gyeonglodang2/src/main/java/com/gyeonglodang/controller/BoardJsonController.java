package com.gyeonglodang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gyeonglodang.dto.PagingDTO;
import com.gyeonglodang.service.ImanagerService;

@RestController
@RequestMapping("/board")
public class BoardJsonController {
	
	@Autowired
	ImanagerService service;
	
	@RequestMapping(value = "/paging")
	public PagingDTO paging (@RequestParam int pageNum) {
		// qna 리스트 페이징 *231130 최재원
        int listNum = 10;        
        int totalCount = service.getQnaListCount();
        int blockNum = 5;
        PagingDTO dto = new PagingDTO(totalCount, pageNum, blockNum, listNum);
        dto.setPaging();

        	return dto;
     }
	}


