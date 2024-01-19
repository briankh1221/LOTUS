<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recruit_resume-applicationList</title>
<link rel="stylesheet" href="../resources/css/recruitresume/applicationList.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="../resources/script/applicationList.js"></script>
</head>
<body>
	<%@include file="../include/header/recruitheader.jsp" %>
	<div id="wrap">
		<div id="title">
			<h1>채용분야</h1>
		</div>
		<div id="content">
			<form action="application" method="post">
				<table id="listTable">
					<thead>
						<tr>
							<th colspan="2">채용분야선택</th>				
						</tr>
					<thead>
					<tbody>
						<c:forEach var="item" items="${jobnoticelist }">
							<tr>
								<td>${item.recruitCateg_categ }</td>
								<td>
									<a href="application?noticeControl_idx=${item.noticeControl_idx }&recruitCateg_categ=${item.recruitCateg_categ}">선택</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>