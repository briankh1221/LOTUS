<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header/recruitheader.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>announcemnet_boardPreview2</title>
<style>
	#list {
		width: 1200px;
		margin: 30px auto;
	}
	
	h2 {
		margin: 30px 0;
	}
	
	
	table{
		width: 1200px;
		border-collapse: collapse;
		border: 1px solid black;
	}
	
	table tr th {
		background-color: black;
		color: white;
		width: 200px;
		height: 50px;
	}
	
	table tr:first-child {
		height: 50px;
	} 
	
	
	table tr:last-child{
		height: 400px;
	}
	
	table tr td {
		text-align: center;
	}
	
	table tr:first-child td{
		border-bottom: 1px dotted black;
	}
	
	table tr:first-child th{
		border-bottom: 1px dotted white;
	}
	
	table tr td input {
		width: 950px;
		height: 35px;
		text-align: center;		
		padding: 5px;
		border: 0;
	}
	
	table tr:last-child td textarea{
		width: 950px;
		height: 375px;
		border: 0;
		resize: none;
	}
	
	/* input을 클릭시 focus 애니메이션 없애주는 소스 */
	input:focus {
		outline:none;
	}
	
	textarea:focus {
		outline:none;
	}
</style>
</head>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	function announcement_boardEditList() {
		let announcement_no = location.search.split('=')[2];
		param = {announcement_no : announcement_no};

	    $.ajax({
			type : 'GET',
		  	url : '../nc/announcement_boardEditList',
			dataType : 'json',
			data : param,
			success : function(data) {
				$('#announcement_title').attr('value', data['announcement_title']);
				$('#announcement_content').append(data['announcement_content']);
			},
			error : function(xhr, status, error) {
				console.log(xhr, status, error);
			}
		});
	}

	window.onload = function () {
		companyLogoView();
		announcement_boardEditList();
	}
</script>
<body>
<div id="list">
	<h2>공지사항</h2>
    <section>
		<table>
			<tr>
				<th><label for="announcement_title">제목</label></th>
				<td><input type="text" id="announcement_title" name="announcement_title" readonly></td>
			</tr>
			<tr>
				<th><label for="announcement_content">내용</label></th>
				<td><textarea id="announcement_content" name="announcement_content" readonly></textarea></td>
			</tr>
		</table><br>
	</section>
</div>	
</body>
</html>