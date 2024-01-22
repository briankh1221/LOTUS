<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp" %>
<%@include file="../include/sidebar/adminsidebar1-1.jsp" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>announcementz_board</title>
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
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>  
	
	function announcementBoardList() { 		
		$.ajax({	         
			type: 'GET',
	         url: '../notice/announcementBoardList',
	         dataType: 'json',
	         success: function(data) {  	        	
	        	let companyIdx = data['companyIdx'];
	        	 $('#companyIdx').attr('value', companyIdx);
	        	 
	        	 str = '';
		        	 for (item of data) {
						 str += '<tr><td>' + item['announcement_no'] + '</td><td>' + item['announcement_category'] + '</td><td><a href="../notice/announcement_boardEdit?announcement_no=' + item['announcement_no'] + '">' + item['announcement_title'] + '</a>' + '</td><td>' + item['announcement_postingDate'] + '</td><td>';
						 let oepration = "개시";
						 if(item['announcement_operation'] == 'y') {
							 operation = "개시";
						 } else if (item['announcement_operation'] == 'n') {
							 operation = "닫음";
						 }
						 str += operation + '</td></tr>';
					 }
					 $('#announcementList').html(str);
			 },
			 error: function(xhr, status, error) {
				 console.log(xhr, status, error);
			 }
		});
	}
	
	window.onload = function () {
		announcementBoardList();
		getSession();
	}
</script>
<body>
	<div id="list">
		<h2>&#10063;공지사항</h2><br>
		<div id="funcBtn">
			<input type="button" value="신규등록" onclick="location.href='../notice/announcement_boardRegister'"/>
			<input type="hidden" style="display: none;" id ="companyIdx" name="companyIdx" />
		</div>
		<table>
			<thead>
				<tr>
					<th style="width:270px;">No.</th>
					<th style="width:270px;">분류</th>
					<th style="width:270px;">제목</th>
					<th style="width:270px;">게시기간</th>
					<th style="width:270px;">게시여부</th>
				</tr>
				</thead>
				<tbody id="announcementList"></tbody>
		</table>
	</div>
</body>
</html>