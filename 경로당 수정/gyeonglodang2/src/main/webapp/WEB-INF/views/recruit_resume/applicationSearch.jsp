<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recruit_resume - applicationSearch</title>
<link rel="stylesheet" href="../resources/css/recruitresume/applicationSearch.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="../resources/script/applicationSearch.js"></script>
</head>
<body>
	<%@include file="../include/header/recruitheader.jsp" %>
	<div id="wrap">
		<div id="title">
			<h1>지원서 수정/확인</h1>
		</div>
		<div id="content">
			<form action="applicationSubmit" method="post" id="form">
				<table class="applicantSearchTable">
					<tr>
						<td>채용공고</td>
						<td>
							<select name="gonggoidx" id="field" style="height: 25px;">
								<option>-- 선택 -- </option>
								<c:forEach items="${rsList }" var="dto" varStatus="loop">
										<option value="${dto.noticeControl_idx }">${dto.jobNotice_title }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="email" id="email"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="password" id="password"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" value="확인하기" id="submitBtn"></td>
					</tr>
				</table>
			</form>
		</div> 
	</div>
</body>
</html>