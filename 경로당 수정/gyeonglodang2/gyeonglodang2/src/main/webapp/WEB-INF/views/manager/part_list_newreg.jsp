<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<!-- 전형정보 신규등록 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전형정보 신규등록</title>
<style>
div#list {
    position: absolute;
    top: 200px;
    left: 370px;
   /* transform: translate(-50%, -50%); */
}
	
#tdList {
	background-color: black;
    color: white;

}

table {
	border-collapse: collapse;
	border-bottom: 2px solid black;
}

table, th, td {
    border: none;
    margin: 10px;
    padding: 10px;
}

table tr td:last-child input:hover{
	background-color: black;
	color: white;
	font-weight: bold;
}

#funcBtn {
	width: 100%;
	text-align: right;
	margin: 10px 0;}
  	
#funcBtn > input {
	width:100px;
	height: 30px;
 }
 
 tr {
    	border-bottom: 1px dotted black;
    }
</style>
</head>
<body>
<div id="list">
	<h2>&#10063;전형 정보</h2><br>
	<div class="container">
		<form action="../manager/insert" method="post">
				<input type="hidden" name="announcement_date" id="mergedDate">
			<table>
				<tr>
					<th id="tdList" style="width:200px">전형명</th>
					<td id="firstLine"><input type="text" id="firstLine" name="part_list_title" style="width:1150px;"></td>
				</tr>
				<tr>
					<th id="tdList" width="200px">발표기간</th>
					<td><input type="date" id="startDate">
					<!-- 시작 날짜 입력 필드 -->
					~
					<input type="date" id="endDate"></td>
					<!-- 종료 날짜 입력 필드 -->
					<!-- 병합된 날짜를 보낼 숨겨진 필드 -->
				</tr>
				<tr>
					<th id="tdList" width="200px">발표설정</th>
					<td><select name="announcement_setting">
						<option>-close-</option>
						<option>-open-</option>
					</select></td>
				</tr>
			</table><br>
				<div id="funcBtn">
					<input type="button" onclick="history.back()" value="이전">
					<input type="submit" id="save_btn" value="저장">
				</div>
		</form>
		<form id="hidden_form">
			<input type="hidden" name="gonggoidx" id="gonggoidx" value="${gonggoidx }">
		</form>
	</div>
	<script>
	$('#hidden_form').hide();
document.addEventListener("DOMContentLoaded", function() {
    // 시작 날짜와 종료 날짜 입력 필드를 가져옵니다.
    var startDateInput = document.getElementById("startDate");
    var endDateInput = document.getElementById("endDate");
    
    // 병합된 날짜를 표시할 숨겨진 필드를 가져옵니다.
    var mergedDateInput = document.getElementById("mergedDate");
    
    // 시작 날짜나 종료 날짜가 변경될 때마다 호출되는 함수
    function mergeDates() {
        var startDate = startDateInput.value;
        var endDate = endDateInput.value;
        
        // 시작 날짜와 종료 날짜를 병합하고 숨겨진 필드에 설정합니다.
        mergedDateInput.value = startDate + " ~ " + endDate;
    }
    
    // 시작 날짜와 종료 날짜 필드의 변경 이벤트를 감시합니다.
    startDateInput.addEventListener("change", mergeDates);
    endDateInput.addEventListener("change", mergeDates);
    
    // 초기에 한 번 병합 함수를 호출하여 초기 값을 설정합니다.
    mergeDates();
});
</script>
</div>
</body>
</html>