<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div#list {
		 position: absolute;
		 top: 200px;
		 left: 370px;
		/* transform: translate(-50%, -50%); */
	}
	
	#tdList {
		background-color: black;
		color: white;
	
	}
	
	table, th, td {
		border: none;
		margin: 10px;
		padding: 10px;
	}
	
	#jobNotice_title {
		width: 1150px;
	}
	
	 table {
    	border-collapse: collapse;
    	border-bottom: 2px solid black;
    	
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
  	
  	#tdList {
    	text-align: center; 
    }
    
   	#funcBtn {
		width: 100%;
		margin-left: 1050px;
  	}
  	
  	.funcBtn {
  		width:100px;
  		height: 30px;
  	}
  	
  	table, th, td {
	      border: none;
	      margin: 10px;
	      padding: 10px;
	}
	
	tr {
    	border-bottom: 1px dotted black;
    }
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
function _onSubmit(){
    
    if($("#file").val() == ""){
        alert("파일을 업로드해주세요.");
        $("#file").focus();
        return false;
    }
    
    if(!confirm(gTxt("confirm.save"))){
        return false;
    }
    
    return true;
}
$(document).ready(function(){
	getSession();
// 	gonggoidx를 받아둔 히든폼이지만 사용은 안할예정
	$('#hidden_form').hide();
	let check=$('#check').val();
	console.log(check);
	if(check==="SC"){
		$('#upload_excel').attr("action", "../passfail/SCupload");
	}else if(check==="C"){
		$('#upload_excel').attr("action", "../passfail/customUpload");
	}else if(check==="CM"){
		$('#upload_excel').attr("action", "../passfail/customizeUpload");
	}
});

</script>
</head>
<body>
<div id="list">
<h2>&#10063;지원자 등록</h2><br>
<form name="upload_excel" id="upload_excel" method="post" enctype="multipart/form-data">
	<input type="hidden" id="check" value="${check }">
	<input type="hidden" name="title" id="title" value="${title }">
	<input type="hidden" name="part" id="part" value="${part }">
	<table>
		<tr>
			<th id="tdList" style="width:200px;">파일 업로드</th>
			<td>
				<input type="file" name="file" id="file" accept=".xlsx, .xls"
				onsubmit="return _onSubmit();" style="width:1150px;">
			</td>
		</tr>
	</table><br>
	<div id="funcBtn">
		<input type="submit" value="엑셀파일 업로드">
	</div>
</form>
<form id="hidden_form">
	<input type="hidden" name="gonggoidx" id="gonggoidx" value="${gonggoidx }">
</form>
</div>
</body>
</html>