<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp" %>
<%@include file="../include/sidebar/adminsidebar1-1.jsp" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>announcement_boardWrite</title>
</head>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<Script>
	function boardWrite() {
		$('#All').attr('action', '../notice/announcement_boardWrite');
		if (!nullCheck())
			return;
		if (!nullCheck2())
			return;
		if (!nullCheck3())
			return;
		$('#All').submit();
	}
	
	function nullCheck() {
		let txtEle = $("#All input[type=text]");
		for (i = 0; i < txtEle.length; i++) {
			if ($(txtEle[i]).val().length == 0) {
				alert('공고전용 및 전체 아니면 제목을 입력하세요');
	
				return false;
			}
		}
		return true;
	}
	
	function nullCheck2() {
		let txtEle = $("#All input[type=date]");
		for (i = 0; i < txtEle.length; i++) {
			if ($(txtEle[i]).val().length == 0) {
				alert('게시기간을 선택하세요');
	
				return false;
			}
		}
		return true;
	}
	
	function nullCheck3() {
		let txtEle = $("#All textarea");
		for (i = 0; i < txtEle.length; i++) {
			if ($(txtEle[i]).val().length == 0) {
				alert('내용을 입력하세요');
	
				return false;
			}
		}
		return true;
	}
	window.onload = function () {
		getSession();
	}
</Script>
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
<body>
<div id="list">
	<form id="All" action="../notice/announcement_boardWrite" method="post">
		<h2>&#10063;공지사항</h2><br>
		<main>
			<div id="funcBtn">
				<input type="button" value="목록" onclick="location.href='../notice/announcement_board'">
				<input type="button" value="등록" onclick="boardWrite();">
			</div>
				<table>
					<tr>
						<th style="width:200px;">공고전용 및 전체</th>
						<td><input type="text" id="announcement_category" name="announcement_category" autofocus style="width:1150px;"></td>
					</tr>
					<tr>
						<th><label for="announcement_title" style="width:200px;">제목</label></th>
						<td><input id="announcement_title" name="announcement_title" type="text" style="width:1150px;"></td>
					</tr>
					<tr>
						<th><label for="announcement_postingDate">게시기간</label></th>
						<td><input id="announcement_postingDate" name="announcement_postingDate1" type="date" /> ~ <input id="announcement_postingDate2" name="announcement_postingDate2" type="date"></td>
					</tr>
					<tr>
						<th><label for="announcement_operation">게시여부</label></th>
						<td><input id="announcement_operation" name="announcement_operation" type="checkbox">공고 올리기</td>
					</tr>
					<tr>
						<th><label for="announcement_content">내용</label></th>
						<td><textarea id="announcement_content" name="announcement_content" placeholder="텍스트를 입력하세요" style="width:1150px; height:350px;"></textarea></td>
					</tr>
				</table>
		</main>
	</form>
</div>	
</body>
</html>