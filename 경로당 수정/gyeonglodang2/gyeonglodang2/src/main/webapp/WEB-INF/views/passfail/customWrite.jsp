<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>합격자발표문구(커스텀)</title>
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
    	text-align: center;
    	
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
  	
  	textarea {
		width: 1150px;
		height: 200px; 	  
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
  	
  	#thList {
  		background-color: black;
    	color: white;
  	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>

function downloadPattern(){
	$.ajax({
        type: 'POST',
        url: '../api/downloadCustomPattern',
        dataType: 'json',
        success: function(data) {
            console.log(data);
	      
        }, error: function(xhr, status, error) {
        	console.log(xhr, status, error);
        }
	});
};

$(document).ready(function(){
	getSession();
	const part = $('#get_part').val();
	const title=$('#past_title').val();
	if(title===""){
		$('#title_select').val("new").prop("selected", true);
	}else{
		$('#title_select').val(title).prop("selected", true);
	}
	$('#title_select').on('change',function(){
		const selectedValue = $(this).val();
		let path = '../passfail/customWrite?title=' + selectedValue;
		path+="&part="+part;
		location.href=path;
	});	
	let selected_title = $('#title_select').val();
	if(selected_title==='new'){
		$('#applicant').hide();
	};
	
	$('#back').on('click',function(){
		location.href="../passfail/write?part="+part;
	});	
	$('#submit_applicant').on('click',function(){
		location.href="../passfail/upload?check=C&title="+title+"&part="+part;
	});	
	$('#download_pattern').on('click',function(){
		window.location = '../api/downloadCustomPattern';
	});	
	$('#download_applicant').on('click',function(){
		window.location = '../api/downloadCustomList?title='+title;
	});	
});
</script>
</head>
<body>
<div id="list">
	<h2>&#10063;합격자 발표 제목</h2>
	<form name="titleDropbox">
	<!-- gonggoidx를 받아둔 히든폼이지만 사용은 안할예정 -->
	<input type="hidden" name="gonggoidx" id="gonggoidx" value="${gonggoidx }">
		<table>
			<tr>
				<td>
				<select name="title_select" id="title_select">
					<c:if test="${not empty custom_title }">
					<c:forEach items="${custom_title }" var="var" varStatus="loop">
					<option value="${var.announcement_custom_title }">${var.announcement_custom_title }</option>
					</c:forEach>
					</c:if>
					<option value="new">새로 추가</option>
				</select>
				</td>
			</tr>
		</table>
	</form>
	<form name = "applicant" id="applicant">
			<div>
				<input type="button" name="submit_applicant" id="submit_applicant" value="지원자 등록">
				<input type="button" name="download_applicant" id="download_applicant" value="명단 다운로드">
				<input type="button" name="download_pattern" id="download_pattern" value="양식 다운로드">
			</div>
		<table>
			<tr>
				<th style="width:450px">수험번호</th>
				<th style="width:450px">이름</th>
				<th style="width:450px">지원분야</th>
			</tr>
			<c:forEach items="${applicant_list }" var="var" varStatus="loop">
				<tr>
					<td>${var.scode }</td>		
					<td>${var.name }</td>		
					<td>${var.fieldidx }</td>		
				</tr>
			</c:forEach>
		</table>
	</form>
	<form action="../passfail/customWrite" method="post">
		<input type="hidden" name="past_title" id="past_title" value="${title }">
		<input type="hidden" name="part" id="get_part" value="${part }">
		<table>
		<br>
			<tr>
				<th style="width:200px">제목</th>
				<td>
				<input type="text" name="announcement_custom_title" value="${title }" style="width:1150px">
				</td>
			</tr>
			<tr>
				<th style="width:200px">내용</th>
				<td><textarea name="context" rows="30px" cols="30px">${context }</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
				<h6>맞춤설정:수험번호-[수험번호], 성명-[성명], 지원분야-[지원분야]
				, 개인1-[개인1], 개인2-[개인2], 개인3-[개인3]</h6>
				</td>
			</tr>
		</table>
			<div id="funcBtn">
				<input type="submit" value="등록">
				<input type="button" id="back" value="돌아가기">
			</div>
	</form>
</div>
</body>
</html>