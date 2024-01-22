<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<!-- 공고관리 전형목록 전형 내역 게시판 -->

<c:set var="list" value="${requestScope.list}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전형 목록</title>
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
    
    table tr td:nth-child(2) a{
    	color: black;
    }
    
    
  	table tr td:last-child input{
  		padding: 7px;
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
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	$(document).ready(function(){
		getSession();
	});
</script>
</head>
<body>
<div id="list">
		<h2>&#10063;전형 목록</h2>
			<form action="part_list_newreg" method="post">
		<div id="funcBtn">
			<input type="submit" value="신규등록">
	    </div>
	<div class="container">
		<div class="row">
			<input type="hidden" name="gonggoidx" id="gonggoidx" value="${gonggoidx }">
				<table class="table table-striped">
					<thead>
						<tr>
							<th width="150px";>No.</th>
							<th width="400px">제목(전형
								유형)</th>
							<th width="400px">발표기간</th>
							<th width="400px">발표설정</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="dto">
							<tr>
								<td><c:out value="${dto.rownum}" /></td>
								<td> <a href="../manager/detailForm?idx=${dto.idx }"> <c:out value="${dto.part_list_title}" /></a></td>
								<td><c:out value="${dto.announcement_date}" /></td>
								<td><c:out value="${dto.announcement_setting}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>