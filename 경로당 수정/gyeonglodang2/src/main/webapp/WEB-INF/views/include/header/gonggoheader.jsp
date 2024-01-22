<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var = "path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>admin-main</title>
   <link rel="stylesheet" href="../resources/css/include/adminheader.css">
   <link rel="stylesheet" href="../resources/css/include/adminsidebar.css">
   <link rel="stylesheet" href="../resources/css/gonggo/gongomain.css">
   <script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
   <script src="../resources/script/resumemain.js"></script>
</head>
<header>
   <div class="topHeader" style="display: flex;">
      <div class="logo">
         <a href="../notice/jobNotice_board"><img class="logo" id="companyLogo2" /></a>
      </div>
      <div style="display:inline-block;  margin: 23px 23px;">
          <label for="jobnoticeList">공고 선택</label>
          <select id="jobnoticeList" name="jobnoticeList" onchange="select()"></select>
      </div>
   </div>
   <div class="bottomHeader">
      <div class="menuBlock">
         <ul class="headerMenu">
            <li><a href="../notice/jobNotice_board">공고관리</a></li>
            <li id="companyAccount"><a href="../notice/companyAccount">화면관리</a></li>
            <li id="administratorAccounts"><a href="../notice/administratorAccounts">시스템관리</a></li>
         </ul>
      </div>
   </div>
</header>
<script>
// gonggoheader.jsp: 채용자 담당자 모든 페이지 헤더 부분 *231105 권지현

/* 현재 java 단의 session에 있는 noticeControl_idx_selected 값을 받아옴
*  ../notice/getSession -> NcJasonController 로 이동함 *231103 고훈
*/
function getSession() {      
    
	$.ajax({
           type: 'GET',
           url: '../notice/getSession',
           dataType: 'json',
           contentType: "application/x-www-form-urlencoded; charset=UTF-8",
           success: function(data) {
         	  // script의 sessionStorage에 값 올림 *231103 고훈 
              sessionStorage.setItem('noticeControl_idx_selected', data);      
              categSelectList(data);
              companyLogoView();
           },
           error: function(xhr, status, error) {
               console.log(xhr, status, error);
           }
       });
}
   /* 매개변수로 들어온 값을 script의 sessionStorage와 java의 session에 올리는 매서드 
   *  ../notice/setSession -> NcJasonController 로 이동함 *231103 고훈
   */
   function setSession(noticeControl_idx_selected) {   
       let param = {noticeControl_idx_selected};
       // sessionStorage에 올림 *231103 고훈
       sessionStorage.setItem('noticeControl_idx_selected', noticeControl_idx_selected);   
       // java의 session에 올림 *231103 고훈
       $.ajax({                                       
              type: 'GET',
              url: '../notice/setSession',
              dataType: 'json',
              data: param,
              contentType: "application/x-www-form-urlencoded; charset=UTF-8",
              success: function(data) {
                 location.reload();
              },
              error: function(xhr, status, error) {
                  console.log(xhr, status, error);
              }
          });
   }
   /* 회사 로고 올리기 위한 메서드
   *  ../notice/companyLogoView -> NcJasonController2 로 이동함 *231103 권지현
   */
   function companyLogoView() {      
	   
      $.ajax({
         type: 'GET',
           url: '../notice/companyLogoView',
           dataType: 'json',
           success: function(data) {  
                 let companyLogo = '';
                 if(data.companyLogoImg) {
                    let imagePath = "/web/resources/uploadfile/" + data['companyLogoImg'];
                    $('#companyLogo2').attr('src', imagePath);
                 }
           },
            error: function(xhr, status, error) {
                console.log(xhr, status, error);
            }
        });         
   }
   /* 공고 목록을 select 단에 셋팅 session에 있는 항목에 selected 추가 
   * ../notice/jobNotice_boardLis -> NcJasonController 로 이동함 *231110 고훈
   */
   function categSelectList(noticeControl_idx_selected) {  
       $.ajax({
           type: 'GET',
           url: '../notice/jobNotice_boardList',   
           dataType: 'json',
           contentType: "application/x-www-form-urlencoded; charset=UTF-8",
           success: function(data) {
              let selectStr = '';
              
              if(noticeControl_idx_selected == 0) {
                 for(let i=0; i<data.length; i++) {
                    if(i == 0) {selectStr += '<option value="' + data[i].noticeControl_idx + '" selected>' + data[i].jobNotice_title + '</option>';}
                    else {selectStr += '<option value="' + data[i].noticeControl_idx + '">' + data[i].jobNotice_title + '</option>';}
                 }
                 // 가져오는 리스트가 빈리스트일때 분기해야함 *231110 고훈
                 if(data.length===0){
                    setSession(1);
                 }else{
                    setSession(data[0].noticeControl_idx);
                 }
              }
              else {
                 for(let i=0; i<data.length; i++) {
                    if(data[i].noticeControl_idx == noticeControl_idx_selected) {
                       selectStr += '<option value="' + data[i].noticeControl_idx + '" selected>' + data[i].jobNotice_title + '</option>';
                    }
                    else {selectStr += '<option value="' + data[i].noticeControl_idx + '">' + data[i].jobNotice_title + '</option>';}
                 }
              }
              $('#jobnoticeList').html(selectStr);
           },
           error: function(xhr, status, error) {
               console.log(xhr, status, error);
           }
       });
   }
   // 선택된 항목의 noticeControl_idx 를 session에 올리고 해당 idx 값을 selectedValue 로 리턴함 *231101 고훈
   function select () {  
      let selectedValue = document.getElementById("jobnoticeList").value;
      // session들 에 올리는 매서드 *231105 고훈
      setSession(selectedValue);   
   
         let selectOptions = document.getElementById('jobnoticeList');
          for (let i = 0; i < selectOptions.options.length; i++) {
        	 // 모든 option의 selected 제거 *231105 고훈
             selectOptions.options[i].removeAttribute('selected');
          }
          for (let i = 0; i < selectOptions.options.length; i++) {   
              if (selectOptions.options[i].value == selectedValue) {
            	// select에 선택된 값을 셋팅 *231105 고훈
                 selectOptions.options[i].selected = true;
                  break;
              }
          }
   }
</script>