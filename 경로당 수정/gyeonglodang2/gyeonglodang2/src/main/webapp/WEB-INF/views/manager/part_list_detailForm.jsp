<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<c:set var="list" value="${requestScope.list}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
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
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	window.onload = function() {
		getSession();
		$('#hidden_form').hide();
		const idx=$('#idx').val();
		console.log(idx);
		getManager();
	}
        //getManager1명 데이터 가져오기
        function getManager() {
        	let idx = location.search.split('=')[1];
        	let param = {idx : idx}
			$.ajax({
				type: 'GET',
				url: '../manager/getManager',
				data: param,
				dataType: 'json',
				success: function(data){
					console.log(data);
					$('#part_list_title').attr('value', data.part_list_title);
					$('#startDate').attr('value', data.announcement_date.split('~')[0].trim());
					$('#endDate').attr('value', data.announcement_date.split('~')[1].trim());
					$("#announcement_setting").val(data.announcement_setting);
					
				},
				error: function(xhr, status, error){
					console.log(xhr, status, error);
				}
			});
		};
        // 저장 버튼의 action 값을 변경하고 폼을 제출합니다. 업데이트 내용
        function save() {
            $('#formTag').attr('action', '../manager/update');
            $('#formTag').submit();
            console.log("detailForm - save")
        }
    	
        // 삭제 버튼의 action 값을 변경하고 폼을 제출합니다.
        function deletes() {
            $('#formTag').attr('action', '../manager/delete');
            $('#formTag').submit();
            console.log("detailForm - deletes")
        }
       
        
    </script>
</head>
<h2>전형 공고 상세보기</h2>

<body>
<div id="list">
	<h2>&#10063;전형 내용 수정</h2>
	<div class="container">
		<form action="../manager/update" method="post" id="formTag">
				<input type="hidden" id="idx" name="idx" value="${idx }"> 
				<input type="hidden" name="announcement_date" id="mergedDate">
			<table>
			<thead>
				<c:forEach items="${list}" var="dto">
					<tr>
						<th id="tdList" style="width:200px">전형명</th>
						<td><input type="text" name="part_list_title" id = "part_list_title" style="width:1150px;"></td>
					<tr>
						<th id="tdList" style="width:200px">발표기간</th>
						<td><input type="date" name = "startDate" id="startDate">
						<!-- 시작 날짜 입력 필드 -->
						~
						<input type="date" name = "endDate" id="endDate"></td>
						<!-- 종료 날짜 입력 필드 -->
						<!-- 병합된 날짜를 보낼 숨겨진 필드 -->
					</tr>
					<tr>
						<th id="tdList" style="width:200px">발표설정</th>
						<td><select name="announcement_setting" id ="announcement_setting">
							<option>-close-</option>
							<option>-open-</option>
						</select></td>
					</tr>
				</c:forEach>
			</thead>
			</table>
			<div id="funcBtn">
				<input	type="button" onclick="history.back()" value="이전">
				<input type="button" id="delete_btn" value="삭제" onclick='deletes();' />
				<input type="button" id="save_btn" value="저장" onclick='save();' />
			</div>
		</form>
		<form id="hidden_form">
			<input type="hidden" name="gonggoidx" id="gonggoidx" value="${gonggoidx }">
		</form>
	</div>
</div>
</body>
</html>