<%@page import="com.gyeonglodang.dto.FaqBoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/gonggoheader.jsp" %>
<%@include file="../include/sidebar/adminsidebar1-1.jsp" %>	
<c:set var="list" value="${requestScope.list}" />
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>

//저장 버튼의 action 값을 변경하고 폼을 제출합니다. 업데이트 내용
function faqUpdate() {
	$('#formTag').attr('action', '../board/faqUpdate');
	$('#formTag').submit();
	console.log("faqDetailForm - update")
}

function faqDelete() {
	$('#formTag').attr('action', '../board/faqDelete');
	$('#formTag').submit();
	console.log("faqDetailForm - delete")
}

</script>

<head>
<meta charset="UTF-8">
<title>상세보기</title>
<style>
/* 스타일을 적용하여 질문과 답변 창의 크기를 조정합니다. */
input[name="faq_board_title"] {
	width: 100%; /* 질문 입력란을 화면 너비에 맞게 확장 */
	padding: 10px; /* 텍스트 입력란 내부 여백 조정 */
	margin-bottom: 10px; /* 아래쪽 간격 추가 */
}

textarea[name="faq_board_context"] {
	width: 100%; /* 답변 입력란을 화면 너비에 맞게 확장 */
	height: 200px; /* 답변 입력란의 높이 (고정된 크기) */
	padding: 10px; /* 텍스트 입력란 내부 여백 조정 */
	resize: none; /* 크기 조정 비활성화 */
}
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
	<h2>&#10063;Manager FAQ 수정 / 삭제</h2>
		<div id="funcBtn">
			<input type="button" onclick="history.back()" value="이전"> <input type="button" id="faqDelete_btn" value="삭제" onclick='faqDelete();' />
			<input type="button" id="faqUpdate_btn" value="저장" onclick='faqUpdate();' />
		</div>
	<div class="container">
		<form action="../board/faqUpdate" method="post" id="formTag">
			<input type="hidden" name="faq_idx" value="${param.faq_idx}">
			<table>
				<c:forEach items="${list}" var="dto">
					<tr>
						<th id="thList" style="width:200px;">질문</th>
						<td><input type="text" name="faq_board_title" value="${dto.faq_board_title}" style="width: 1150px;"/></td>
					</tr>
					<tr>
						<th id="thList" style="width:200px;">답변</th>
						<td><textarea name="faq_board_context" style="width: 1150px; height: 250px;">${dto.faq_board_context}</textarea></td>
					</tr>
				</c:forEach>
			</table>
			
		</form>
	</div>
</div>	
</body>
</html>