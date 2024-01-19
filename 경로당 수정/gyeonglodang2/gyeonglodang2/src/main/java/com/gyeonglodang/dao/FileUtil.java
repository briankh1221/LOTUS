package com.gyeonglodang.dao;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUtil {
	public List<HashMap<String, String>> parseExcelSpringMultiPart(Map<String, MultipartFile> files, String KeyStr,
			int fileKeyParam, String storePath) {
		List<HashMap<String, String>> list = null;
//		임의로 파일명이랑 경로를 설정했지만 파일내용만 리스트에 담고 파일은 삭제하도록 설계함
		int fileKey = fileKeyParam;
		String storePathString = "";
		if (storePath.equals("") || storePath == null) {
			storePathString = "/resources/upload/";
		} else {
			storePathString = "/resources/upload/" + storePath;
		}
//		파일이 업로드될 폴더가 존재하지않으면 폴더를 생성함
		File saveFolder = new File(filePathBlackList(storePathString));
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
//		혹시 여러개의 파일을 업로드했다면 여기서 반복문을 통해 파일별로 
//		리스트에 담도록 설계함(여러개를 업로드하진 않을예정)
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();
			if (orginFileName.equals("")) {
				continue;
			}
			int index = orginFileName.lastIndexOf(".");

			String fileExt = orginFileName.substring(index + 1);
			String newName = KeyStr + System.currentTimeMillis() + fileKey;
			try {
				if (!orginFileName.equals("")) {
					filePath = storePathString + File.separator + newName + "." + fileExt;
					file.transferTo(new File(filePathBlackList(filePath)));
				}
				list = getListXlsxRead(filePath, fileExt);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			fileKey++;
		}
		File delfile=new File(filePath);
		delfile.delete();
		return list;
	}

//	parseExcelSpringMultiPart에서 사용할 메소드
//	특정 특수문자를 공백으로 치환
//	보안성 증가라는데 잘모르겠다.
	public static String filePathBlackList(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\./", "");
		returnValue = returnValue.replaceAll("\\.\\.\\\\", "");

		return returnValue;
	}

//	parseExcelSpringMultiPart에서 사용할 메소드
	public List<HashMap<String, String>> getListXlsxRead(String excel, String fileExt) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		File file = new File(excel);
		if (!file.exists() || !file.isFile() || !file.canRead()) {
			System.out.println("파일 업로드에 문제발생");
			return list;
		}
		try {
//			여기서 log4j2관련 오류가 나기에 xml과 pom을추가함
//			확장자에 따라 다른 참조변수를 사용해서 메소드도 분기함
			if (fileExt.equals("xls")) {
				HSSFWorkbook wb;
				wb = new HSSFWorkbook(new FileInputStream(file));
				list = getXlsList(wb);
			} else if (fileExt.equals("xlsx")) {
				XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
				list = getXlsxList(wb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
//	여기서 엑셀파일 내용을 꺼내서 리스트에 담음
//	엑셀파일에서 열을 읽음 그 열마다 있는 행을 읽어서 값이있는 각각의 셀을 전부 가져옴
//	그셀에 담긴 값의 타입을 읽고 그 타입을 String으로 변환해서 변수에 초기화함
//	그 String을 맵에 담음 key값은 cell + 숫자조합으로 넣음
//	그 맵들을 리스트로 담아서 리턴해줌
	@SuppressWarnings("incomplete-switch")
	public List<HashMap<String, String>> getXlsList(HSSFWorkbook wb) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int check = 0;
		try {
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				for (Row row : wb.getSheetAt(i)) {
					if (check != 0) {
						HashMap<String, String> hMap = new HashMap<String, String>();
						String valueStr = "";
						int cellLength = (int) row.getLastCellNum();
						for (int j = 0; j < cellLength; j++) {
							Cell cell = row.getCell(j);
							if (cell == null || cell.getCellType() == CellType.BLANK) {
								valueStr = "";
							} else {
								switch (cell.getCellType()) {
								case STRING:
									valueStr = cell.getStringCellValue();
									break;
								case NUMERIC: // 날짜 형식이든 숫자 형식이든 다 CELL_TYPE_NUMERIC으로 인식함.
									if (DateUtil.isCellDateFormatted(cell)) { // 날짜 유형의 데이터일 경우,
										SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
										String formattedStr = dateFormat.format(cell.getDateCellValue());
										valueStr = formattedStr;
										break;
									} else { // 순수하게 숫자 데이터일 경우,
										Double numericCellValue = cell.getNumericCellValue();
										if (Math.floor(numericCellValue) == numericCellValue) { // 소수점 이하를 버린 값이 원래의 값과
																								// 같다면,,
											valueStr = numericCellValue.intValue() + ""; // int형으로 소수점 이하 버리고 String으로
																							// 데이터 담는다.
										} else {
											valueStr = numericCellValue + "";
										}
										break;
									}
								case BOOLEAN:
									valueStr = cell.getBooleanCellValue() + "";
									break;
								}
							}
							hMap.put("cell_" + j, valueStr);
						}
						list.add(hMap);
					}
					check++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("incomplete-switch")
	public List<HashMap<String, String>> getXlsxList(XSSFWorkbook wb) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int check = 0;
		try {
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				for (Row row : wb.getSheetAt(i)) {
					if (check != 0) {
						HashMap<String, String> hMap = new HashMap<String, String>();
						String valueStr = "";
						int cellLength = (int) row.getLastCellNum();
						for (int j = 0; j < cellLength; j++) {
							Cell cell = row.getCell(j);
							if (cell == null || cell.getCellType() == CellType.BLANK) {
								valueStr = "";
							} else {
								switch (cell.getCellType()) {
								case STRING:
									valueStr = cell.getStringCellValue();
									break;
								case NUMERIC: // 날짜 형식이든 숫자 형식이든 다 CELL_TYPE_NUMERIC으로 인식함.
									if (DateUtil.isCellDateFormatted(cell)) { // 날짜 유형의 데이터일 경우,
										SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
										String formattedStr = dateFormat.format(cell.getDateCellValue());
										valueStr = formattedStr;
										break;
									} else { // 순수하게 숫자 데이터일 경우,
										Double numericCellValue = cell.getNumericCellValue();
										if (Math.floor(numericCellValue) == numericCellValue) { // 소수점 이하를 버린 값이 원래의 값과
																								// 같다면,,
											valueStr = numericCellValue.intValue() + ""; // int형으로 소수점 이하 버리고 String으로
																							// 데이터 담는다.
										} else {
											valueStr = numericCellValue + "";
										}
										break;
									}
								case BOOLEAN:
									valueStr = cell.getBooleanCellValue() + "";
									break;
								}
							}
							hMap.put("cell_" + j, valueStr);
						}
						list.add(hMap);
					}
					check++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
