<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<head>
<style>       
textarea {
   width: 1350px;
   height: 200px;      
}
div#list {
    position: absolute;
    top: 200px;
    left: 370px;
}
#tdList {
   background-color: black;
   color: white;
}
table, th, td {
   border: none;
   margin: 10px;
   padding: 10px;
}
table {
   border-collapse: collapse;
   border-bottom: 2px solid black;
}
table tr td:nth-child(2) a {
   color: black;
}
table tr td:last-child input {
   padding: 7px;
   border: none;
}
table tr td:last-child input:hover {
   background-color: black;
   color: white;
   font-weight: bold;
}
#tdList {
   text-align: center; 
}
#funcBtn {
   width: 100%;
   text-align: right;
   margin: 10px 0;
}
#funcBtn > input{
   width:100px;
   height: 30px;
}
table, th, td {
   border: none;
   margin: 10px;
   padding: 10px;
}
tr {
   border-bottom: 1px dotted black;
}
</style>
<script>
// jobNoticeCateg_boardEdit_preview.jsp: 채용 공고 분야 게시물 미리보기 *231107 권지현 
   
let noticeControl_idx = ${noticeControl_idx};
/* 채용공고 게시물 제목 미리보기  
 * ../notice/jobNoticeCateg_titleView -> NcJasonController2.jsp 로 이동함 *231107 권지현
 */
function titleView() {   
   
	param = {noticeControl_idx : noticeControl_idx};
   
	$.ajax({
	   type : 'GET',
	   url : '../notice/jobNoticeCateg_titleView',
	   dataType : 'json',
	   data : param,
	   success : function(data) {
	      $('#jobNotice_title').attr('value', data['jobNotice_title']);      
	
	   },
	   error : function(xhr, status, error) {
	      console.log(xhr, status, error);
	   }
	});
}
/* 채용공고 게시물 접수기간, 내용 미리보기 
 * ../notice/jobNoticeCateg_boardEdit_previewList -> NcJasonController2.jsp 로 이동함 *231107 권지현 
 */
function previewList() {   
   
	param = {noticeControl_idx : noticeControl_idx};
   
	$.ajax({
      type : 'GET',
      url : '../notice/jobNoticeCateg_boardEdit_previewList',
      dataType : 'json',
      data : param,
      success : function(data) {
      
         $('#jobNoticeCateg_postingDate').attr('value', data['jobNoticeCateg_postingDate']);   
         $('#jobNoticeCateg_content').val(data['jobNoticeCateg_content']);   

      },
      error : function(xhr, status, error) {
         console.log(xhr, status, error);
      }
   });
}
/* 채용공고 게시물 이미지 미리보기 
 * ../notice/jobNoticeCateg_imageUploadList -> NcJasonController2.jsp 로 이동함 *231107 권지현 
 */   
function imageView() {   
   param = {noticeControl_idx: noticeControl_idx};
   $.ajax({
      type: 'GET',
        url: '../notice/jobNoticeCateg_imageUploadList',
        dataType: 'json',
        data: param,   
        success: function(data) {           
           let imageURL = '';              
           for(item of data) {           
              imageURL +=   '<img src="/web/resources/uploadfile/' + item['fileName'] + '" style="width:400; text-align: center;" /><br>';                              
           }              
           $('#fileName').html(imageURL);
        },     
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     });         
}
/* 채용공고 게시물 첨부파일 및 첨부파일 리스트 미리보기 *231107 권지현 
 * ../notice/jobNoticeCateg_fileUploadList -> NcJasonController2.jsp 로 이동함 *231107 권지현  
 */  
function fileUploadList() {   
   param = {noticeControl_idx: noticeControl_idx};
   $.ajax({
      type: 'GET',
        url: '../notice/jobNoticeCateg_fileUploadList',
        dataType: 'json',
        data: param,   
        success: function(data) {
           let str = '';
           for(item of data) {
              str += '<tr><td>' + item['fileName']+ '</td></tr>';             
           }
           $('#fileUploadList').html(str);
        },
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     });         
}

window.onload = function() {
   titleView();
   previewList();
   imageView();
   fileUploadList();
   getSession();
}
</script>
<meta charset="UTF-8">
<title>jobNoticeCateg_boardEdit_preview</title>
</head>
<body>
<div id="list">
   <h2 style="text-align: center;">&#10063;채용 공고</h2><br>
   <div id="funcBtn">
      <input type="button" value="목록" onclick="location.href='../notice/jobNoticeCateg_board'"/> 
   </div>
   <table style="width:1350px;">
      <tr style="width:1350px;">
         <th style="width:200px;" id="tdList">공고명</th>
         <td><input id="jobNotice_title" readonly style="width:1150px;"></td>
      </tr>
      <tr>
         <th style="width:200px;" id="tdList">접수기간</th>
         <td><input id="jobNoticeCateg_postingDate" readonly style="width:1150px;">
      </tr>
      <tr>
         <th style="width:200px;" id="tdList">첨부파일</th>
         <td id="fileUploadList"></td>
      <tr>
         <td colspan="2"><div id="fileName" style="width: 80%; height: 50%;"></div></td>
      </tr>
      <tr>
          <td colspan="2">
           <textarea id="jobNoticeCateg_content" readonly style="width:1350px;" style=""></textarea>
          </td>
      </tr>
      </table>
   </div>
</body>
</html>