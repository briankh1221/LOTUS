<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>합격자 발표 문구</title>
<style>
	 textarea {
	 	white-space: pre-wrap;
	 	overflow-y: scroll;
	 }
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
    
    #thList {
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
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>

$(document).ready(function(){
	getSession();
	// gonggoidx를 받아둔 히든폼이지만 사용은 안할예정
	$('#hidden_form').hide();
	// jquery와 jstl을 병행사용하기위해 hidden 타입에 값 저장후 그값을
	// jquery로 변수에 저장
	const pass_fail=$('#pass_fail').val();
	const part=$('#part_val').val();
		console.log(part);
	const fieldidx=$('#fieldidx').val();
	// 전달받은 값을 통해 드롭박스 값을 지정
	$('#fieldidx_select').val(fieldidx).prop("selected", true);
	$('#pass_fail_select').val(pass_fail).prop("selected", true);
	// 드롭박스 변경시 url 에 전달받을 값을 변경후 리다이렉트
	$('#fieldidx_select').on('change',function(){
		const selectedValue = $(this).val();
		let path = '../passfail/passfailWrite?part=' + part;
	    path += '&fieldidx=' + selectedValue + '&pass_fail=' + pass_fail;
		location.href=path;
	});
	$('#pass_fail_select').on('change',function(){
		const selectedValue = $(this).val();
		let path = '../passfail/passfailWrite?part=' + part;
	    path += '&fieldidx=' + fieldidx + '&pass_fail=' + selectedValue;
		location.href=path;
	});
});

</script>


</head>
<body>
<div id="list">
	<form action="../passfail/passfailWrite" method="post">
		<input type="hidden" name="part" id="part_val" value="${dto.part }">
		<input type="hidden" name="fieldidx" id="fieldidx" value="${dto.fieldidx }">
		<input type="hidden" name="pass_fail" id="pass_fail" value="${pass_fail }">
		<h2>&#10063;기본정보</h2><br>
		<select name="pass_fail_select" id="pass_fail_select">
			<option value="pass">합격발표문</option>
			<option value="fail">불합격발표문</option>
		</select><br><br>
		<table>
			<tr>
				<th id="thList" style="width:200px;">채용분야</th>
				<td>
					<select name="fieldidx_select" id="fieldidx_select">
						<option value="공통">공통</option>
						<c:forEach items="${list }" var="var" varStatus="loop">
						<option value="${var.fieldidx }">${var.fieldidx }</option>
						</c:forEach>
					</select>
				</td>
			</tr><br>
			<tr>
				<th id="thList" style="width:200px;">전형</th>
				<td>${dto.part }
				</td>
			</tr>
			<tr>
				<th id="thList" style="width:200px;">내용</th>
				<td>
					<textarea style="width:1150px; height:400px;" name="pass_fail_text">${text }</textarea>
				</td>
			</tr>
		</table>
		<table>
			<tfoot>
			<tr>
				<td>
				<h6>맞춤설정:수험번호-[수험번호], 성명-[성명], 지원분야-[지원분야]
				, 개인1-[개인1], 개인2-[개인2], 개인3-[개인3]</h6>
				</td>
			</tr>
			</tfoot>
		</table>
			<div id="funcBtn">
				<input type="submit" value="등록">
			</div>
	</form>
	<form id="hidden_form">
		<input type="hidden" name="gonggoidx" id="gonggoidx" value="${gonggoidx }">
	</form>
</div>
</body>
</html>