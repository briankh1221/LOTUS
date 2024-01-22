package com.gyeonglodang.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gyeonglodang.dao.PassFailDAO;
import com.gyeonglodang.dto.AnnouncementCustomDTO;
import com.gyeonglodang.dto.CustomizeDTO;
import com.gyeonglodang.dto.SuccessfulCandidateListDTO;

@RestController
@RequestMapping("api")
public class PassFailApi {
	String view;
	int gonggoidx;
	int companyIdx;
	@Autowired
	@Qualifier("passFailDAOimpl")
	PassFailDAO dao;

	@PostMapping("changeDateSetting")
	public Map<String, Object> changeDateSetting(@RequestParam Map<String, Object> map
			, HttpSession session) {
		// 맵에 담겨온 변경된값을 변수로 초기화 이후 시작날짜값과 종료날짜값를 합쳐서 합친날짜와 세팅을 db에 업데이트 *231123 정민석
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		String startdate = (String) map.get("startdate");
		String enddate = (String) map.get("enddate");
		String date = startdate + "~" + enddate;
		map.put("setting", "-"+map.get("setting")+"-");
		map.put("date", date);
		map.put("companyIdx", companyIdx);
		map.put("gonggoidx", gonggoidx);
		dao.updateDateSetting(map);

		return map;
	}

	@PostMapping("serchFieldPassFail")
	public Map<String,Object> serchFieldPassFail(@RequestParam Map<String, Object> map,
			Map<String, Object> company_pass_fail, HttpSession session) {
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		// 선택한 분야,합격여부를 맵에 담아와서 변수에 초기화 *231123 정민석
		String fieldidx = (String) map.get("fieldidx");
		String pass_fail = (String) map.get("pass_fail");
		List<SuccessfulCandidateListDTO> successful_list = null;
		// pass_fail이 전체인경우 like문에 빈값을 넣어서 합격여부에 조건을 뺌 *231123 정민석
		if (pass_fail.equals("전체")) {
			pass_fail = "";
		}
		// fieldidx가 전체인경우 map에 companyidx와 pass_fail만 넣어서 companyidx와 pass_fail만 조건에 넣음 *231123 정민석
		if (fieldidx.equals("전체")) {
			company_pass_fail.put("companyIdx", companyIdx);
			company_pass_fail.put("gonggoidx", gonggoidx);
			company_pass_fail.put("pass_fail", pass_fail);
			System.out.println(company_pass_fail);
			successful_list = dao.getSuccessfulCandidateList(company_pass_fail);
		//	fieldidx가 전체가 아닌 경우 map에 companyidx,pass_fail,fieldidx를 전부 조건에 넣음 *231123 정민석
		} else {
			company_pass_fail.put("companyIdx", companyIdx);
			company_pass_fail.put("gonggoidx", gonggoidx);
			company_pass_fail.put("pass_fail", pass_fail);
			company_pass_fail.put("fieldidx", fieldidx);
			successful_list = dao.getSuccessfulList(company_pass_fail);
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("successful_list", successful_list);
		result.put("gonggoidx", gonggoidx);
		
		return result;
	}
	
	@PostMapping("serchName")
	public Map<String, Object> serchName(@RequestParam Map<String, Object> map, HttpSession session){
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		map.put("companyIdx", companyIdx);
		map.put("gonggoidx", gonggoidx);
		List<CustomizeDTO> customize_list = dao.getCustomizeList(map);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("gonggoidx", gonggoidx);
		result.put("customize_list", customize_list);
		return result;
	}
	
	// 합격자목록에서 등록된 db내 내용을 엑셀파일로 다운로드 *231130 정민석
	@GetMapping("downloadList")
	public void downloadList(HttpSession session, HttpServletResponse response) {
		Map<String, Object> company_pass_fail = new HashMap<String, Object>();
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		company_pass_fail.put("companyIdx", companyIdx);
		company_pass_fail.put("gonggoidx", gonggoidx);
		company_pass_fail.put("pass_fail", "");
		List<SuccessfulCandidateListDTO> scList = null;
		// 엑셀 파일 생성을 위한 참조변수들 생성 *231130 정민석
		Workbook xworkbook = new SXSSFWorkbook();
		Sheet xSheet = xworkbook.createSheet("sheet1");
		FileOutputStream fos = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		File tempFile = null;
		try {
			// 다운로드할 파일의 이름 설정 *231130 정민석
			String filename = "합격자목록";
			tempFile = File.createTempFile(filename, ".xlsx");
			fos = new FileOutputStream(tempFile);
			// 열 생성 *231130 정민석
			Row xRow = xSheet.createRow(0);
			// 행 생성 *231130 정민석
			Cell xCell = xRow.createCell(0);
			// 행에 값 입력 *231130 정민석
			xCell.setCellValue("수험번호");
			xCell = xRow.createCell(1);
			xCell.setCellValue("성명");
			xCell = xRow.createCell(2);
			xCell.setCellValue("지원분야");
			xCell = xRow.createCell(3);
			xCell.setCellValue("합격여부");
			// successfulCandidateList 테이블 에서 모든 값을 가져옴 *231130 정민석
			scList = dao.getSuccessfulCandidateList(company_pass_fail);
			int row = 1;
			// 열과 행을 생성하고 가져온값을 행에 넣음 *231201 정민석
			for (SuccessfulCandidateListDTO dto : scList) {
				xRow = xSheet.createRow(row);
				xCell = xRow.createCell(0);
				xCell.setCellValue(dto.getScode());
				xCell = xRow.createCell(1);
				xCell.setCellValue(dto.getName());
				xCell = xRow.createCell(2);
				xCell.setCellValue(dto.getFieldidx());
				xCell = xRow.createCell(3);
				xCell.setCellValue(dto.getPass_fail());
				row++;
			}
			// 다운받을 엑셀파일에 위에 넣은 값들을 적용 *231201 정민석
			xworkbook.write(fos);
			// 다운받을 파일 설정을 엑셀파일로 설정 *231201 정민석
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			// 위에서 지정한 파일 이름을 헤더를통해 설정 *231201 정민석
			String encoded = URLEncoder.encode(filename + ".xlsx", "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encoded + "\"");
			// 크롬같은 브라우저의 다운로드 기능을 사용 *231201 정민석
			inputStream = new FileInputStream(tempFile);
			outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
				inputStream.close();
				if (fos != null) {
					fos.close();
				}
				if (xworkbook != null) {
					xworkbook.close();
				}
				tempFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 위에 다운로드와 같은 방식이지만 양식이기에 1열에 필요한 값만 넣음 *231201 정민석
	@GetMapping("downloadPattern")
	public void downloadPattern(HttpServletResponse response) {
		Workbook xworkbook = new SXSSFWorkbook();
		Sheet xSheet = xworkbook.createSheet("sheet1");
		FileOutputStream fos = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		File tempFile = null;
		try {
			String filename = "합격자목록(양식)";
			tempFile = File.createTempFile(filename, ".xlsx");
			fos = new FileOutputStream(tempFile);

			Row xRow = xSheet.createRow(0);
			Cell xCell = xRow.createCell(0);
			xCell.setCellValue("수험번호");
			xCell = xRow.createCell(1);
			xCell.setCellValue("성명");
			xCell = xRow.createCell(2);
			xCell.setCellValue("지원분야");
			xCell = xRow.createCell(3);
			xCell.setCellValue("합격여부");
			xworkbook.write(fos);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String encoded = URLEncoder.encode(filename + ".xlsx", "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encoded + "\"");
			inputStream = new FileInputStream(tempFile);
			outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
				inputStream.close();
				if (fos != null) {
					fos.close();
				}
				if (xworkbook != null) {
					xworkbook.close();
				}
				tempFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 커스텀문구를 적용할 지원자들 명단을 다운로드하는곳 *231205 정민석
	@GetMapping("downloadCustomList")
	public void downloadCustomList(HttpSession session, HttpServletResponse response
			, @RequestParam String title) {
		Map<String, Object> company_title = new HashMap<String, Object>();
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		company_title.put("companyIdx", companyIdx);
		company_title.put("gonggoidx", gonggoidx);
		company_title.put("title", title);
		List<AnnouncementCustomDTO> aList = null;
		Workbook xworkbook = new SXSSFWorkbook();
		Sheet xSheet = xworkbook.createSheet("sheet1");
		FileOutputStream fos = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		File tempFile = null;
		try {
			String filename = "지원자목록";
			tempFile = File.createTempFile(filename, ".xlsx");
			fos = new FileOutputStream(tempFile);

			Row xRow = xSheet.createRow(0);
			Cell xCell = xRow.createCell(0);
			xCell.setCellValue("수험번호");
			xCell = xRow.createCell(1);
			xCell.setCellValue("성명");
			xCell = xRow.createCell(2);
			xCell.setCellValue("지원분야");
			
			aList = dao.getApplicantList(company_title);
			int row = 1;
			for (AnnouncementCustomDTO dto : aList) {
				xRow = xSheet.createRow(row);
				xCell = xRow.createCell(0);
				xCell.setCellValue(dto.getScode());
				xCell = xRow.createCell(1);
				xCell.setCellValue(dto.getName());
				xCell = xRow.createCell(2);
				xCell.setCellValue(dto.getFieldidx());
				row++;
			}
			xworkbook.write(fos);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String encoded = URLEncoder.encode(filename + ".xlsx", "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encoded + "\"");
			inputStream = new FileInputStream(tempFile);
			outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
				inputStream.close();
				if (fos != null) {
					fos.close();
				}
				if (xworkbook != null) {
					xworkbook.close();
				}
				tempFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 커스텀문구를 등록할 지원자들 명단 입력 양식 *231205 정민석
	@GetMapping("downloadCustomPattern")
	public void downloadCustomPattern(HttpServletResponse response) {
		Workbook xworkbook = new SXSSFWorkbook();
		Sheet xSheet = xworkbook.createSheet("sheet1");
		FileOutputStream fos = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		File tempFile = null;
		try {
			String filename = "지원자등록(양식)";
			tempFile = File.createTempFile(filename, ".xlsx");
			fos = new FileOutputStream(tempFile);

			Row xRow = xSheet.createRow(0);
			Cell xCell = xRow.createCell(0);
			xCell.setCellValue("수험번호");
			xCell = xRow.createCell(1);
			xCell.setCellValue("성명");
			xCell = xRow.createCell(2);
			xCell.setCellValue("지원분야");
			xworkbook.write(fos);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String encoded = URLEncoder.encode(filename + ".xlsx", "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encoded + "\"");
			inputStream = new FileInputStream(tempFile);
			outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
				inputStream.close();
				if (fos != null) {
					fos.close();
				}
				if (xworkbook != null) {
					xworkbook.close();
				}
				tempFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 맞춤설정 명단 가져오기 *231205 정민석
	@GetMapping("downloadCustomizeList")
	public void downloadCustomizeList(HttpSession session, HttpServletResponse response
			, @RequestParam String part) {
		Map<String, Object> company_part_name = new HashMap<String, Object>();
		companyIdx = (int) session.getAttribute("companyIdx");
		gonggoidx = (int)session.getAttribute("noticeControl_idx_selected");
		company_part_name.put("companyIdx", companyIdx);
		company_part_name.put("gonggoidx", gonggoidx);
		company_part_name.put("part", part);
		company_part_name.put("name", "");
		List<CustomizeDTO> cList = null;
		Workbook xworkbook = new SXSSFWorkbook();
		Sheet xSheet = xworkbook.createSheet("sheet1");
		FileOutputStream fos = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		File tempFile = null;
		try {
			String filename = "맞춤설정명단";
			tempFile = File.createTempFile(filename, ".xlsx");
			fos = new FileOutputStream(tempFile);

			Row xRow = xSheet.createRow(0);
			Cell xCell = xRow.createCell(0);
			xCell.setCellValue("수험번호");
			xCell = xRow.createCell(1);
			xCell.setCellValue("성명");
			xCell = xRow.createCell(2);
			xCell.setCellValue("지원분야");
			xCell = xRow.createCell(3);
			xCell.setCellValue("컬럼1");
			xCell = xRow.createCell(4);
			xCell.setCellValue("컬럼2");
			xCell = xRow.createCell(5);
			xCell.setCellValue("컬럼3 ");
			
			cList = dao.getCustomizeList(company_part_name);
			int row = 1;
			for (CustomizeDTO dto : cList) {
				xRow = xSheet.createRow(row);
				xCell = xRow.createCell(0);
				xCell.setCellValue(dto.getScode());
				xCell = xRow.createCell(1);
				xCell.setCellValue(dto.getName());
				xCell = xRow.createCell(2);
				xCell.setCellValue(dto.getFieldidx());
				xCell = xRow.createCell(3);
				xCell.setCellValue(dto.getColumn1());
				xCell = xRow.createCell(4);
				xCell.setCellValue(dto.getColumn2());
				xCell = xRow.createCell(5);
				xCell.setCellValue(dto.getColumn3());
				row++;
			}
			xworkbook.write(fos);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String encoded = URLEncoder.encode(filename + ".xlsx", "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encoded + "\"");
			inputStream = new FileInputStream(tempFile);
			outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
				inputStream.close();
				if (fos != null) {
					fos.close();
				}
				if (xworkbook != null) {
					xworkbook.close();
				}
				tempFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 맞춤설정 양식 다운로드 *231205 정민석
	@GetMapping("downloadCustomizePattern")
	public void downloadCustomizePattern(HttpServletResponse response) {
		Workbook xworkbook = new SXSSFWorkbook();
		Sheet xSheet = xworkbook.createSheet("sheet1");
		FileOutputStream fos = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		File tempFile = null;
		try {
			String filename = "맞춤설정명단(양식)";
			tempFile = File.createTempFile(filename, ".xlsx");
			fos = new FileOutputStream(tempFile);

			Row xRow = xSheet.createRow(0);
			Cell xCell = xRow.createCell(0);
			xCell.setCellValue("수험번호");
			xCell = xRow.createCell(1);
			xCell.setCellValue("성명");
			xCell = xRow.createCell(2);
			xCell.setCellValue("지원분야");
			xCell = xRow.createCell(3);
			xCell.setCellValue("컬럼1");
			xCell = xRow.createCell(4);
			xCell.setCellValue("컬럼2");
			xCell = xRow.createCell(5);
			xCell.setCellValue("컬럼3 ");
			
			xworkbook.write(fos);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String encoded = URLEncoder.encode(filename + ".xlsx", "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encoded + "\"");
			inputStream = new FileInputStream(tempFile);
			outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
				inputStream.close();
				if (fos != null) {
					fos.close();
				}
				if (xworkbook != null) {
					xworkbook.close();
				}
				tempFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	   @PostMapping("gofinal")
	   public Map<String, Object> gofinal(@RequestParam Map<String, Object> map){
	      String scode=(String)map.get("scode");
	      System.out.println(scode);
	      Map<String,Object> returnmap = dao.getFinal(scode);
	      return returnmap;
	   }
}
