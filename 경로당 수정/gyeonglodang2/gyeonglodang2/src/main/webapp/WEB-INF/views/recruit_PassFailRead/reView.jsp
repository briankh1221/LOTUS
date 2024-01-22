<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recruit_PassFailRead.jsp</title>
<link href = "../resources/css/reView.css" rel="stylesheet" />
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
	$('#hidden_form').hide();
	let rs=$('#rs').val();
	if(rs == 1){
		alert("이메일 혹은 비밀번호가 틀렸습니다.");
	}
	$('#inquiry').on('click',function(){
		$('#form').submit();
	});
	let gonggo = $('#gonggo').val();
	if (gonggo === "-선택-") {
	    $('#inquiry').prop("disabled", true);
	}

	$('#gonggo').on('change', function () {
	    gonggo = $('#gonggo').val();
	    if (gonggo !== "-선택-") {
	        $('#inquiry').prop("disabled", false);
	    } else {
	        $('#inquiry').prop("disabled", true);
	    }
	});
});
</script>
</head>
<body>
	<%@include file="../include/header/recruitheader.jsp" %>
	<form id="hidden_form">
		<input type="hidden" id="rs" value="${rs }">
	</form>
	<h2>전형결과 조회</h2>
	<div id="wrap">
		<div class="ViewRecruitBlock">
			<form action="PassFailPage" method="post" id="form">
			<table id = "abc">
				<tr>
					<th>채용공고</th>
					<td>
					<select name="gonggo" id="gonggo" style="width:200px; height:20px">
	                     <option value="-선택-" selected="selected">-선택-</option>
	                     <c:if test="${not empty gonggo_list }">
	                     <c:forEach items="${gonggo_list }" var="dto" varStatus="loop">
	                        <option value="${dto.jobNotice_title }">${dto.jobNotice_title }</option>
	                     </c:forEach>
	                     </c:if>
               		</select>
               		</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" placeholder="이메일을 입력해주세요" >
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="password" placeholder="비밀번호를 입력해주세요" >
				</tr>
			</table>
			</form>
		</div>
		<div class = "submitBlock">
			<input type="button" id="inquiry" value="조회하기">
		</div>
	</div>
</body>
</html>