<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
// jobNotice_board.jsp: 공고 목록을 보여주기 위한 페이지. 채용 담당자는 로그인 후 바로 이 페이지로 들어옴 *231101 권지현

	/* 공고 목록을 보여주기 위한 메서드
	 * ../notice/jobNotice_boardList -> NcJasonController로 이동함 *231101 고훈
	 */
	function searchList() {
		$.ajax({
			type: 'GET',
			url: '../notice/jobNotice_boardList',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success: function(data) {
				str = '<tr><th width="150px">No.</th><th width="350px">공고명</th><th width="350px">서류전형 접수기간</th><th width="250px">운영여부</th><th width="250px">운영화면</th></tr>'
				for(item of data) {
					str += '<tr><td>' + item['noticeControl_idx'] + '</td><td>' +'<a class="" href="../notice/jobNotice_board_new?noticeControl_idx=' + item['noticeControl_idx'] + '">'+ item['jobNotice_title'] + '</a></td><td>' + item['jobNotice_postingDate']
					str += '</td><td>' + item['jobNotice_operation'] + '</td><td>' + '<input type="button" value="바로가기" />' + '</td></tr>'
				}
				$('.jobNotice_board').html(str);
			},
			error: function(xhr, status, error) {
				console.log(xhr, status, error);
			}
		});
	};

	window.onload = function() {
		searchList();
		getSession();
	}

	$(function name() {
		$('#searchBtn').on('click', function() {
		});

	});
</script>
<style>
	div#list {
		position: absolute;
		top: 200px;
		left: 370px;
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

	table tbody tr td{
		height: 60px;
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
<head>
	<title>jobNotice_board</title>
</head>
<body>
	<div id="list">
	<h2>&#10063;공고 목록</h2>
	<div id="funcBtn">
		<input type="button" onclick="location.href= '../notice/jobNotice_board_new'" value="신규 등록"/>
	</div>
	<table style="text-align: center;" >
		<tbody class="jobNotice_board">
		</tbody>
	</table>	
	</div>
</body>
</html>