package com.gyeonglodang.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gyeonglodang.dto.FaqBoardDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.QnaBoardDTO;
import com.gyeonglodang.dto.QnaFileDTO;
import com.gyeonglodang.service.ImanagerService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	String view;
	
	@Autowired
	ImanagerService service;
	// FAQ 게시판 코드 *231123 최재원
	@GetMapping("/faqList")
	public String faqList(Model model, HttpSession session) {
		
		int companyIdx = (int)session.getAttribute("companyIdx");
		
		FaqBoardDTO fDto = new FaqBoardDTO();
		fDto.setCompanyIdx(companyIdx);
		
		List<FaqBoardDTO> list = service.getFaqBoList(fDto);
		model.addAttribute("list", list);

		return"/board/faqList";
	}
	
	@GetMapping("/ManagerFaqList")
	public String MangerfaqList(Model model, HttpSession session) {
		
		int companyIdx = (int)session.getAttribute("companyIdx");
		
		FaqBoardDTO fDto = new FaqBoardDTO();
		fDto.setCompanyIdx(companyIdx);
		
			List<FaqBoardDTO> list = service.getFaqBoList(fDto);
			model.addAttribute("list", list);

		
		return"/board/ManagerFaqList";
	}
	
	@GetMapping("/faqWrite")
	public String faqWrite(Model model, HttpSession session) {

	    int companyIdx = (int) session.getAttribute("companyIdx");

	    // 세션에 companyIdx를 저장하지 않음 *231123 최재원

	    view = "board/faqWrite";
	    return view;
	}
	
	@PostMapping("/faqInsert")
	public String faqInsert(FaqBoardDTO fDto, RedirectAttributes ra, HttpSession session) {

	    int companyIdx = (int) session.getAttribute("companyIdx");

	    fDto.setCompanyIdx(companyIdx);
	    
	    int rs = service.faqInsert(fDto);
	    ra.addFlashAttribute("rs", rs);

	    // 세션에 companyIdx를 다시 저장할 필요가 없음 *231123 최재원

	    view = "redirect:ManagerFaqList";
	    return view;
	}
	// faq 글 상세 페이지 *231123 최재원
	@GetMapping("/faqDetailForm")
	public  String faqDetailForm(FaqBoardDTO fDto, Model model) {
		List<FaqBoardDTO> list = service.faqDetailForm(fDto.getFaq_idx());
		System.out.println(list);
		
		model.addAttribute("list", list);
		model.addAttribute("faq_idx", fDto.getFaq_idx());
		
		return "board/faqDetailForm";
	}
	
	@PostMapping("/faqUpdate")
	public String faqUpdate(FaqBoardDTO fDto, HttpSession session) {
		
		int idx = fDto.getFaq_idx();
		fDto.setFaq_idx(idx);
		service.faqUpdate(fDto);
		
		view = "redirect:ManagerFaqList";
		return view;
	}
	
	@PostMapping("/faqDelete")
	public String faqDelete(FaqBoardDTO fDto,  HttpSession session) {
		
		if(fDto != null) {
			service.faqDelete(fDto);
			view = "redirect:ManagerFaqList";
		}
		return view;
	}
	
	// Q&A 게시판 코드 *231125 최재원
	// 페이징 처리 *231125 최재원
	@GetMapping("/ManagerQnaList")
	   public String ManagerQnaList(Model model, HttpSession session, 
	         @RequestParam(required = false) String pageNum) {
	      
	      int companyIdx = (int) session.getAttribute("companyIdx");
	       
	       
	      if(pageNum==null) {
	         pageNum = "1";
	      }
	      List<QnaBoardDTO> list = service.getQnaBoList(Integer.parseInt(pageNum), companyIdx);
	      model.addAttribute("list", list);
	      
	      return"/board/ManagerQnaList";
	   }

	
	// qna 글 상세 페이지 *231125 최재원
	@GetMapping("/qnaDetailForm")
	public String qnaDetailForm(QnaBoardDTO qDto, Model model, int idx) {
	    
	    qDto = service.qnaDetailForm(idx);

	    // qDto가 null이 아닌지 확인 *231125 최재원
	    if (qDto != null) {
	        QnaFileDTO fDto = service.getFileName(qDto);

	        // fDto가 null이 아니고 파일 경로가 비어있지 않은지 확인 *231125 최재원
	        if (fDto != null && fDto.getQna_file_path() != null && !fDto.getQna_file_path().isEmpty()) {
	            String[] filePath = fDto.getQna_file_path().split("\\\\");
	            String fileName = filePath[filePath.length - 1];

	            // 파일 이름이 "resources"인지 확인 *231125 최재원
	            if (fileName.equals("resources")) {
	                fileName = "";
	            }

	            model.addAttribute("qDto", qDto);
	            model.addAttribute("fDto", fDto);
	            model.addAttribute("fileName", fileName);
	        } else {
	            // 파일이 없는 경우 처리 *231127 최재원
	            model.addAttribute("qDto", qDto);
	            // 원하는 경우 메시지를 추가하거나 필요에 따라 다르게 처리할 수 있습니다 *231127 최재원
	            model.addAttribute("noFileMessage", "이 QnaBoard에는 파일이 연관되어 있지 않습니다");
	        }
	    } else {
	        // qDto가 null인 경우 처리 *231127 최재원
	        // 원하는 경우 메시지를 추가하거나 필요에 따라 다르게 처리할 수 있습니다 *231127 최재원
	        model.addAttribute("errorMessage", "QnaBoard를 찾을 수 없습니다");
	    }

	    return "board/qnaDetailForm";
	}
	
		@GetMapping("/qnaWrite")
		public String qnaWrite(Model model, HttpSession session) {
		    
		    String companyIdx = session.getAttribute("companyIdx").toString();

		    List<JobNotice_boardDTO> list = service.getJobList();
		    // "jobNotices"로 수정 *231127 최재원
		    model.addAttribute("jobNotices", list);  
		    // companyIdx를 모델에 추가 *231127 최재원
		    model.addAttribute("companyIdx", companyIdx);  
		    
		    view = "board/qnaWrite";
		    return view;
		}
		
		//qna 조회 하는 거 *231127 최재원
		@GetMapping("/qnaSearch")
		public String qnaSearch(Model model, HttpSession session) {
			
			int companyIdx = (int)session.getAttribute("companyIdx");
			
			List<QnaBoardDTO> list = service.getSearchList();
			model.addAttribute("list", list);
			// companyIdx를 모델에 추가 *231127 최재원 
			model.addAttribute("companyIdx", companyIdx);  
			
			view = "board/qnaSearch";
		    return view;
		}
		
		//qna 조회 해서 해당 개인 리스트로 들어가기 *231127 최재원
		@RequestMapping(value = "/qnaSearchView", method = {RequestMethod.GET, RequestMethod.POST})
		public String qnaSearchView(Model model,
		                            @RequestParam(name = "companyIdx", required = false) String companyIdx,
		                            @RequestParam(name = "recruit_email", required = false) String recruit_email,
		                            @RequestParam(name = "recruit_password", required = false) String recruit_password) {

		    if (recruit_email != null && recruit_password != null) {
		        // 로그인 확인 로직 추가 *231127 최재원
		        QnaBoardDTO qnaBoardDTO = new QnaBoardDTO();
		        qnaBoardDTO.setRecruit_email(recruit_email);
		        qnaBoardDTO.setRecruit_password(recruit_password);

		        QnaBoardDTO isLoggedIn = service.checkLogin(qnaBoardDTO);
		        if (isLoggedIn != null) {
		        	List<QnaBoardDTO> list = service.getSearchList2(qnaBoardDTO);
		        	model.addAttribute("list", list);
		        	model.addAttribute("companyIdx", companyIdx);
		        	model.addAttribute("recruit_email", recruit_email);
		        	// 로그인 성공 처리 *231127 최재원
		        	view = "board/qnaSearchView";

		        } else {
		        	model.addAttribute("rs", 0);
		        	model.addAttribute("companyIdx", companyIdx);
		        	view = "board/qnaSearch";
		        }
		    }

		    return view;
		}
		
		@GetMapping("/qnaSearchViewDetailForm")
		public String qnaSearchViewDetailForm(QnaBoardDTO qDto, Model model, int idx) {
		    
		    qDto = service.qnaDetailForm(idx);

		    // qDto가 null이 아닌지 확인 *231127 최재원
		    if (qDto != null) {
		        QnaFileDTO fDto = service.getFileName(qDto);

		        // fDto가 null이 아니고 파일 경로가 비어있지 않은지 확인 *231127 최재원
		        if (fDto != null && fDto.getQna_file_path() != null && !fDto.getQna_file_path().isEmpty()) {
		            String[] filePath = fDto.getQna_file_path().split("\\\\");
		            String fileName = filePath[filePath.length - 1];

		            // 파일 이름이 "resources"인지 확인 *231127 최재원
		            if (fileName.equals("resources")) {
		                fileName = "";
		            }

		            model.addAttribute("qDto", qDto);
		            model.addAttribute("fDto", fDto);
		            model.addAttribute("fileName", fileName);
		        } else {
		            // 파일이 없는 경우 처리 *231127 최재원
		            model.addAttribute("qDto", qDto);
		            // 원하는 경우 메시지를 추가하거나 필요에 따라 다르게 처리할 수 있습니다 *231127 최재원
		            model.addAttribute("noFileMessage", "이 QnaBoard에는 파일이 연관되어 있지 않습니다");
		        }
		    } else {
		        // qDto가 null인 경우 처리 *231127 최재원
		        // 원하는 경우 메시지를 추가하거나 필요에 따라 다르게 처리할 수 있습니다 *231127 최재원
		        model.addAttribute("errorMessage", "QnaBoard를 찾을 수 없습니다");
		    }

		    return "board/qnaSearchViewDetailForm";
		}
	
		
		// qna 리스트 추가 *231130 최재원
		@PostMapping("/qnaInsert")
		public String qnaInsert(QnaBoardDTO qDto, QnaFileDTO fDto, RedirectAttributes ra,
				@RequestParam ArrayList<MultipartFile> qna_file,@RequestParam(name = "companyIdx") String companyIdx) {

		    // service가 null인지 확인 *231130 최재원
		    if (service != null) {
		        System.out.println(qDto);
		        System.out.println(qna_file);

		        String qnauploadPath = "/Users/dpqbw/Desktop/k-digital/spring2/springws/gyeonglodang/src/main/webapp/resources/uploadfile";
		        int rs = service.qnaInsert(qDto);

		        // qna_board에서 idx값 받아오기 *231130 최재원
		        int idx = service.loadqnaidx();
		        fDto.setQna_board_idx(idx);

		        // qna_file이 null이 아니고 비어 있지 않은지 확인 *231130 최재원
		        if (qna_file != null && !qna_file.isEmpty()) {
		            // 원본 파일 이름 가져오기
		            String originalFilename = qna_file.get(0).getOriginalFilename();

		            // 파일 이름에 마침표가 있는지 확인 *231130 최재원
		            if (originalFilename != null && originalFilename.contains(".")) {
		                // 나머지 코드는 그대로 유지 *231130 최재원
		            } else {
		                // 파일 이름이 null이거나 마침표가 없는 경우 처리 *231130 최재원 
		                System.out.println("파일 이름에 마침표가 없거나 null입니다.");
		            }
		        } else {
		            // qna_file이 null이거나 비어 있는 경우 처리 *231130 최재원
		            System.out.println("파일이 없거나 비어 있습니다.");
		        }

		        ra.addFlashAttribute("rs", rs);
		    } else {
		        // service가 null인 경우 처리 *231130 최재원
		        System.out.println("Service is null");
		    }

		    view = "redirect:/notice/main?companyIdx=" + companyIdx;
		    return view;
		}
		// qna 업데이트 *231130 최재원	
		@PostMapping("/qnaUpdate")
		public String qanUpdate(QnaBoardDTO qDto, HttpSession session) {
			
			int idx = qDto.getIdx();
			qDto.setIdx(idx);
			service.qnaUpdate(qDto);
			
			view = "redirect:ManagerQnaList";
			return view;
		}
		// qna 리스트 삭제 *231130 최재원
		@PostMapping("/qnaDelete")
		public String qnaDelete(QnaBoardDTO qDto,  HttpSession session) {
			
			if(qDto != null) {
				service.qnaDelete(qDto);
				view = "redirect:ManagerQnaList";
			}
			return view;
		}
		
		// qna디테일폼 파일 다운로드 *231130 최재원
		@GetMapping("/downloadFile")
		@ResponseBody
		public ResponseEntity<byte[]> downloadFile(@RequestParam String fileName) throws IOException {
		    // 실제 파일 경로 *231130 최재원
		    String filePath = "/Users/dpqbw/Desktop/k-digital/spring2/springws/gyeonglodang/src/main/webapp/resources/uploadfile"; 

		    // 파일을 읽어 바이트 배열로 변환 *231130 최재원
		    File file = new File(filePath + fileName);
		    byte[] fileContent = Files.readAllBytes(file.toPath());

		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		    // 파일 이름을 UTF-8로 인코딩하여 Content-Disposition 헤더에 추가 *231130 최재원
		    String encodedFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		    headers.setContentDispositionFormData("attachment", encodedFileName);

		    return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
		}
		    
		 
		
}
