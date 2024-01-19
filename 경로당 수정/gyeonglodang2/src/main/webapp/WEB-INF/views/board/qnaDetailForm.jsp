<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/gonggoheader.jsp" %>
<%@include file="../include/sidebar/adminsidebar1-1.jsp" %>

<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
	//저장 버튼의 action 값을 변경하고 폼을 제출합니다. 업데이트 내용
	function qnaUpdate() {
		$('#formTag').attr('action', '../board/qnaUpdate');
		$('#formTag').submit();
		console.log("qanDetailForm - update")
	}

	function qnaDelete() {
		$('#formTag').attr('action', '../board/qnaDelete');
		$('#formTag').submit();
		console.log("qnaDetailForm - delete")
	}
	
	
</script>

<head>
<meta charset="UTF-8">
<title>Manager Q&A 디테일 게시판</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style>
	div#list {
	    position: absolute;
	    top: 200px;
	    left: 370px;
	   /* transform: translate(-50%, -50%); */
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
    
    tr {
    	border-bottom: 1px dotted black;
    }
    
    table th {
    	background-color: black;
    	color: white;
    }
    
    table tr td:nth-child(2) a{
    	color: black;
    }
    
    
  	table tr td:last-child input{
  		padding: 7px;
  		border: none;
  	}
  	
  	table tr td:last-child input:hover{
  		background-color: black;
  		color: white;
  		font-weight: bold;
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

</style>
</head>
<body>
	<div id="list">
	<h2>&#10063;Manager Q&A 디테일 게시판</h2>
	<form action="../board/qnaUpdate" method="post" id="formTag">
		<input type="hidden" name="idx" value="${param.idx}">
			<div id="funcBtn">
				<input class="list-button" type="button" onclick="history.back()" value="취소">
				<input class="save-button" type="button" onclick="qnaUpdate();" value="저장">
				<input class="delete-button" type="button" onclick="qnaDelete();" value="삭제">
			</div>
		<table style="width: 1350px;" >
				<tr>
					<th name = "a" style="width: 200px;"><label for="job_area">제목</label></th>
					<td name = "b"><input type="text" name="qna_title"
						value="${qDto.qna_title}" readonly style="width: 1150px;"></td>
				</tr>
				<tr>
					<th name = "a" style="width: 200px;">공고 및 채용분야</th>
					<td name = "b"><input type="text" name="gonggo_list"
						value="${qDto.gonggo_list}" readonly> <input type="text"
						name="field_list" value="${qDto.field_list}" readonly style="width: 1150px;"></td>
				</tr>
				<tr>
					<th name = "a" style="width: 200px;">등록인</th>
					<td name = "b">
						<input type="text" name="recruit_name"
						value="${qDto.recruit_name}" readonly style="width: 383px;"> 
						<input type="text"
						name="recruit_email" value="${qDto.recruit_email}" readonly style="width: 383px;">
						<input type="text" name="recruit_phonenum"
						value="${qDto.recruit_phonenum}" readonly style="width: 383px;"></td>
				</tr>
				<tr>
					<th name = "a" style="width: 200px;">비밀번호</th>
					<td name = "b"><input type="text" name="recruit_password"
						value="${qDto.recruit_password}" readonly style="width: 1150px;"></td>
				</tr>
				<tr>
					<th name = "a" style="width: 200px;">문의내용</th>
					<td name = "b"><textarea class="read-only-textarea" rows="4" cols="50"
							name="qna_context" readonly style="width: 1150px; height: 250px;">${qDto.qna_context}</textarea></td>
				</tr>
				<tr>
					<th name = "a">파일</th>
					<td name = "b"><a href="../board/downloadFile?fileName=${fileName}" id="downloadLink" name="downloadLink">${fileName}</a></td>
				</tr>
				<tr>
					<th name = "a">답변</th>
					<td name = "b"><textarea name="qna_answer_board" style="width: 1150px; height: 250px;">${qDto.qna_answer_board }</textarea></td>
				</tr>
		</table>
	</form>
	</div>
</body>
</html>
