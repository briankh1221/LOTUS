<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recruitresume-main</title>
<link rel="stylesheet" href="../resources/css/recruitresume/recruitresume_main.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="../resources/script/recruitresume.js"></script>
</head>
<body>
	<%@include file="../include/header/recruitheader.jsp" %>
	<div id="wrap">
		<div id="title">
			<h1>지원서 작성</h1>
		</div>
		<div id="content">
			<form action="applicant" method="post" id="form">
				<table class="applicantTable">
					<tr>
						<td>채용공고</td>
						<td>
							<select name="gonggoidx" style="height: 25px;">
								<option value="선택">--&nbsp;선택&nbsp;--</option>
								<c:forEach items="${rsList }" var="dto" varStatus="loop">
									<option value="${dto.noticeControl_idx }">${dto.jobNotice_title }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" placeholder="이메일을 입력해주세요" name="email"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" placeholder="비밀번호를 입력해주세요" id="pw" name="password"></td>
					</tr>
					<tr>
						<td>비밀번호 확인</td>
						<td><input type="password" placeholder="위의 비밀번호를 입력해주세요" id="pwCheck"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" value="작성하기" id="submitBtn"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>