package com.gyeonglodang.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gyeonglodang.dao.PassFailDAO;
import com.gyeonglodang.dto.CompanyAccount_boardDTO;
import com.gyeonglodang.dto.Companyinfo_boardDTO;
import com.gyeonglodang.dto.JobNotice_boardDTO;
import com.gyeonglodang.dto.PassFailDTO;
import com.gyeonglodang.dto.RecruitDTO;
import com.gyeonglodang.service.IrecruitService;

@Controller
@RequestMapping("recruit_PassFailRead")
public class RecruitController {
	String view;
	
	@Autowired
	@Qualifier("recruitServiceImpl")
	IrecruitService service;
	
	@Autowired
	@Qualifier("passFailDAOimpl")
	PassFailDAO dao;
	
    @GetMapping("/reView")
    public String reView(HttpSession session, Model model, @RequestParam Map<String,Object> map) {
       int companyIdx = (int)session.getAttribute("companyIdx");
       List<Map<String,Object>> gonggo_list = service.getGonggo(companyIdx);
       model.addAttribute("gonggo_list",gonggo_list);
       
       if(map.get("rs")!=null) {
          String srs = (String)map.get("rs");
          int rs = Integer.parseInt(srs);
          model.addAttribute("rs",rs);
       }
       view = "recruit_PassFailRead/reView"; 
       return view;
    }
	
    @PostMapping("/PassFailPage")
    public String PassFailPage(HttpSession session, @RequestParam Map<String, Object> map
          , Model model, PassFailDTO pfdto) {
//       선택한 공고를 idx로 변환 하는 작업이 필요함(공고제목 -> 공고 idx)
       int companyIdx = (int)session.getAttribute("companyIdx");
       String gonggo = (String)map.get("gonggo");
       int gonggoidx = service.getGonggoidx(gonggo);
       String email = (String)map.get("email");
       String password = (String)map.get("password");
       String scode="";
       String part ="";
//       email, password비교(조회)
       boolean check = false;
       Map<String,Object> getscode = new HashMap<String, Object>();
       getscode.put("email", email);
       getscode.put("gonggoidx", gonggoidx);
       List<RecruitDTO> rList = service.getConfirm(getscode);
       for(RecruitDTO dto : rList) {
          if(dto.getPassword().equals(password)){
             check = true;
//             email, password로 수험번호 가져옴
             scode=dto.getScode();
             break;
          }
       }
//       맞는경우 결과 조회 페이지로 이동
       if(check==true) {
//       현재 날짜에 따라 보여지는 전형을 변환
          Date date = new Date();
          SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
          String sNow = format.format(date);
          int now = Integer.parseInt(sNow);
          List<Map<String,Object>> dates = service.getDate(gonggoidx);
          for(Map<String,Object> dMap : dates) {
             String sDate = (String)dMap.get("announcement_date");
             String[] sDates = sDate.split("~");
             String startDate = sDates[0].trim().replace("-","");
             String endDate = sDates[1].trim().replace("-","");
             int iStartDate = Integer.parseInt(startDate);
             int iEndDate = Integer.parseInt(endDate);
             if(now>=iStartDate && now<=iEndDate) {
                part = (String)dMap.get("part_list_title");
                break;
             }
          }
//          해당하는 custom문구 커스터마이징
          Map<String, Object> part_scode = new HashMap<String, Object>();
          part_scode.put("part", part);
          part_scode.put("scode", scode);
          part_scode.put("companyIdx", companyIdx);
          part_scode.put("gonggoidx", gonggoidx);
//          전형과 수험번호에 해당하는 맞춤설정들을 가져옴
          Map<String, Object> commonMap = dao.getCommon(part_scode);
          if(commonMap.get("pass_fail")!=null) {
             model.addAttribute("pass_fail",commonMap.get("pass_fail"));
          }
//          커스텀문구를 전형과 수험번호를 통해 가져옴
          String custom_context = service.getCustomContext(part_scode);
//          맞춤설정을 리플레이스로 재설정
          if(custom_context!=null) {
             custom_context = custom_context.replace("[수험번호]", scode);
             custom_context = custom_context.replace("[성명]", (String)commonMap.get("name"));
             custom_context = custom_context.replace("[지원분야]", (String)commonMap.get("fieldidx"));
             custom_context = custom_context.replace("[개인1]", (String)commonMap.get("column1"));
             custom_context = custom_context.replace("[개인2]", (String)commonMap.get("column2"));
             custom_context = custom_context.replace("[개인3]", (String)commonMap.get("column3"));
             model.addAttribute("custom_context",custom_context);
          }
//          해당하는 분야 문구 커스터마이징
//          공통도 같이 커스터마이징
          if(commonMap.get("pass_fail").equals("합격")) {
             pfdto.setPart(part);
             pfdto.setFieldidx((String)commonMap.get("fieldidx"));
             pfdto.setCompanyIdx((int)commonMap.get("companyIdx"));
             pfdto.setGonggoidx((int)commonMap.get("gonggoidx"));
             String pass_text = dao.getPassText(pfdto);
             if(pass_text!=null) {
                pass_text = pass_text.replace("[수험번호]", scode);
                pass_text = pass_text.replace("[성명]", (String)commonMap.get("name"));
                pass_text = pass_text.replace("[지원분야]", (String)commonMap.get("fieldidx"));
                pass_text = pass_text.replace("[개인1]", (String)commonMap.get("column1"));
                pass_text = pass_text.replace("[개인2]", (String)commonMap.get("column2"));
                pass_text = pass_text.replace("[개인3]", (String)commonMap.get("column3"));
                model.addAttribute("field_context",pass_text);
             }
             pfdto.setFieldidx("공통");
             pass_text = dao.getPassText(pfdto);
             if(pass_text!=null) {
                pass_text = pass_text.replace("[수험번호]", scode);
                pass_text = pass_text.replace("[성명]", (String)commonMap.get("name"));
                pass_text = pass_text.replace("[지원분야]", (String)commonMap.get("fieldidx"));
                pass_text = pass_text.replace("[개인1]", (String)commonMap.get("column1"));
                pass_text = pass_text.replace("[개인2]", (String)commonMap.get("column2"));
                pass_text = pass_text.replace("[개인3]", (String)commonMap.get("column3"));
                model.addAttribute("common_text",pass_text);
             }
          }else {
             pfdto.setPart(part);
             pfdto.setFieldidx((String)commonMap.get("fieldidx"));
             pfdto.setCompanyIdx((int)commonMap.get("companyIdx"));
             pfdto.setGonggoidx((int)commonMap.get("gonggoidx"));
             String fail_text = dao.getFailText(pfdto);
             if(fail_text!=null) {
                fail_text = fail_text.replace("[수험번호]", scode);
                fail_text = fail_text.replace("[성명]", (String)commonMap.get("name"));
                fail_text = fail_text.replace("[지원분야]", (String)commonMap.get("fieldidx"));
                fail_text = fail_text.replace("[개인1]", (String)commonMap.get("column1"));
                fail_text = fail_text.replace("[개인2]", (String)commonMap.get("column2"));
                fail_text = fail_text.replace("[개인3]", (String)commonMap.get("column3"));
                model.addAttribute("field_context",fail_text);
             }
             pfdto.setFieldidx("공통");
             fail_text = dao.getFailText(pfdto);
             if(fail_text!=null) {
                fail_text = fail_text.replace("[수험번호]", scode);
                fail_text = fail_text.replace("[성명]", (String)commonMap.get("name"));
                fail_text = fail_text.replace("[지원분야]", (String)commonMap.get("fieldidx"));
                fail_text = fail_text.replace("[개인1]", (String)commonMap.get("column1"));
                fail_text = fail_text.replace("[개인2]", (String)commonMap.get("column2"));
                fail_text = fail_text.replace("[개인3]", (String)commonMap.get("column3"));
                model.addAttribute("common_text",fail_text);
             }
          }
          view="recruit_PassFailRead/PassFailPage";
       }else {
          model.addAttribute("rs",1);
          view="redirect:reView";
       }
       return view;
    }
	
	@GetMapping("/recruitMain")
	   public String recruitMain(Model model) {
		
	      List<JobNotice_boardDTO > list = service.getJncList();
	      model.addAttribute("list", list);
	      
	      List<Companyinfo_boardDTO> comList = service.getCompanyInfo();
	      model.addAttribute("comList", comList);
	      
	      List<CompanyAccount_boardDTO> companyAccountList = service.getCompanyLogo();
	      model.addAttribute("companyAccountList", companyAccountList);
	      
	      List<JobNotice_boardDTO> dDayCount = service.dDayCount();
	      
	      LocalDate today = LocalDate.now();
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	      ArrayList<Long> Ddaylist = new ArrayList<>();
	      
	      for(int i=0; i<dDayCount.size(); i++) {
	    	  String date1  = dDayCount.get(i).getJobNotice_postingDate().split("~")[1];
	    	  String date2 = today.format(formatter);
	    	  LocalDate localDate1 = LocalDate.parse(date1);
	          LocalDate localDate2 = LocalDate.parse(date2);
	          
	          long daysUntil = ChronoUnit.DAYS.between(LocalDate.now(), localDate1);
	          long daysSince = ChronoUnit.DAYS.between(localDate2, LocalDate.now());
	          Ddaylist.add(Math.abs(daysUntil));
	      }
	      
	      model.addAttribute("Ddaylist", Ddaylist);
	      
	      return "recruit_PassFailRead/recruitMain";
	   }
}
