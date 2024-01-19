<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp" %>
<%@include file="../include/sidebar/adminsidebar1-1.jsp" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<style></style>
<script>
	let announcement_no = ${announcement_no};
	
	function announcement_boardEditList() {	
		param = {announcement_no : announcement_no};

		$.ajax({
			type : 'GET',
			url : '../notice/announcement_boardEditList',
			dataType : 'json',
			data : param,
			success : function(data) {
				$('#announcement_no').attr('value', announcement_no);
				$('#announcement_category').attr('value',data['announcement_category']);
				$('#announcement_title').attr('value',data['announcement_title']);
				
				if (data['announcement_operation'] == 'y') {
					$('#announcement_operation').attr('checked', true);
				}
				$('#announcement_postingDate').attr('value', data['announcement_postingDate']);
				$('#announcement_content').append(data['announcement_content']);
			},
			error : function(xhr, status, error) {
				console.log(xhr, status, error);
			}
		});
    }
	
	function boardBack() {		
		window.location.href="../notice/announcement_boardEdit?announcement_no="+ announcement_no;
    }

	
	window.onload = function () {
		announcement_boardEditList();	
		getSession();
	}
</script>
</head>
<body>
<div id="list">
	<section>
		<table>
			<tr>
				<th><label for="announcement_category">공고전용 및 전체</label></th>
				<td><input type="text" id="announcement_category" name="announcement_category" readonly></td>
			</tr>
			<tr>
				<th><label for="announcement_title">제목</label></th>
				<td><input type="text" id="announcement_title" name="announcement_title" readonly></td>
			</tr>
			<tr>
				<th><label for="announcement_postingDate">게시기간</label></th>
				<td><input id="announcement_postingDate"name="announcement_postingDate" type="text" readonly/>
			</tr>
			<tr>
				<th><label for="announcement_operation">게시여부</label></th>
				<td><input id="announcement_operation" type="checkbox" name="announcement_operation" readonly>공고 올리기</td>
			</tr>
			<tr>
				<th><label for="announcement_content">내용</label></th>
				<td><textarea id="announcement_content" name="announcement_content" readonly></textarea></td>
			</tr>
		</table><br>
	</section>
	<section>
		<table>
		 <tr>
		 	<td><input type="button" value="목록" onclick="location.href='../notice/announcement_board'"></td>
		 	<td><input id="deleteBtn" type="button" value="뒤로가기" onclick="boardBack();"></td>
		 </tr>
		</table>	 
	</section>
</div>	
</body>
</html>