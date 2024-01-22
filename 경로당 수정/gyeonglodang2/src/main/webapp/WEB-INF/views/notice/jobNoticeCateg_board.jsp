<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<html>
<head>
<title>jobNoticeCateg_bored</title>
</head>
<style>
div#list {
    position: absolute;
    top: 200px;
    left: 370px;
}
table, th, td {
    border: none;
    margin: 10px;
    padding: 10px;
}
#list th, #list td {
   	text-align: center; 
}   
table {
	border-collapse: collapse;
	border-bottom: 2px solid black;
}   
table th {
	background-color: black;
	color: white;
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
#funcBtn {
	width: 100%;
	text-align: right;
	margin: 10px 0;
}
#funcBtn > input {
	width:100px;
	height: 30px;
}
tr {
 	border-bottom: 1px dotted black;
}
#jobNoticeCateg_bored {
	height:60px;
}
#jobNoticeCateg_bored tr:hover {
	background-color: #f5f5f5;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
// jobNoticeCateg_board.jsp : 채용공고 게시물 목록 페이지 *231101 권지현

var companyIdx = "${companyIdx}"
/* 채용공고 게시물 목록을 부리기 위한 메서드 
 * ../notice/jobNotice_boardList2 -> NcJasonController2 로 이동함 *231101 권지현 
 */
function searchList() {   
	
	 let param = {companyIdx};
	
	 $.ajax({	         
		 type: 'GET',
         url: '../notice/jobNotice_boardList2',
         dataType: 'json',
         data: param,
         success: function(data) {
        	 str = '';
      			for(i=0; i<data.boardList.length; i++) {
      				 // 채용공고 게시물 목록 No *231101 권지현
      				str += '<tr><td>' + data.boardList[i]['noticeControl_idx'] + '</td><td>';  
      				// 채용공고 게시물 목록 상태 *231101 권지현 
      				let operation = 'Holding';   
      				for(j=0; j<data.operationList.length; j++) {
      					if(data.operationList[j]['noticeControl_idx'] == data.boardList[i]['noticeControl_idx']) {  
      						let oper = data.operationList[j]['jobNoticeCateg_operation'];
          					if (oper == 'y') {
          						operation = 'Open';
                       		} else if (oper == 'n') {
                       			operation = 'Closed';
                       		}  						
      					} 
      				} 
      				str += operation;  
      				// 채용공고 게시물 제목, 게시기간, 등록일 *231101 권지현
      				str += '</td><td><a href="../notice/jobNoticeCateg_boardEdit?noticeControl_idx=' + data.boardList[i]['noticeControl_idx'] + '">' + data.boardList[i]['jobNotice_title'] + '</a>' + '</td><td>' + data.boardList[i]['jobNotice_postingDate'] + '</td><td>' + data.boardList[i]['jobNotice_regidate'] + '</td><td>' + '<input type="button" onclick="deleteBtn('+ data.boardList[i]['noticeControl_idx'] + ', \''+ operation + '\');" value="삭제"></td></tr>';
                }   
                $('#jobNoticeCateg_bored').html(str);         
         },
         error: function (xhr, status, error) {
             console.log(xhr, status, error);
         }
     });
}
//채용공고 게시물 삭제 버튼 *231101 권지현
function deleteBtn(noticeControl_idx, operation) {   	
	
	let params = {noticeControl_idx: noticeControl_idx, operation: operation};
	
	$.ajax({
		type: 'GET',
        url: '../notice/jobNoticeCateg_boardDelete',
        dataType: 'json',
        data: params,	
        success: function(data) { 
        	searchList();
        },
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     });			
}

window.onload = function() {
	getSession();  
    searchList();
}
</script>
<body>
<div id="list">
	<h2>&#10063;채용공고 게시물 목록</h2><br>
	<section>
		<table>
			<tr>
				<th id="jobNoticeCateg_idx" width="150px">No.</th>
				<th id="jobNoticeCateg_operation" width="100px">상태</th>
				<th id="jobNoticeCateg_content" width="350px" >제목</th>
				<th id="jobNoticeCateg_regi" width="300px">게시기간</th>
				<th id="jobNoticeCateg_postingDate" width="350px">등록일</th>
				<th id="jobNoticeCateg_postingDate" width="100px">삭제</th>
			</tr>
			<tbody id="jobNoticeCateg_bored"></tbody>
		</table>
	</section>
</div>
</body>
</html>