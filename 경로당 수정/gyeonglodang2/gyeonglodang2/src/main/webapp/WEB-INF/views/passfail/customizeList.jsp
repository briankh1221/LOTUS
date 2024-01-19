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
function serchName(part){
	//hidden 에 jstl로 담아둔 변수값을 head에서 쓰기위해 변수에 초기화
	const name=$('#name').val();
	const param={name:name,part:part};
	$.ajax({
        type: 'POST',
        url: '../api/serchName',
        dataType: 'json',
        data: param,
        success: function(data) {
			console.log(data);
			let list = "";
			//받아온 list를 let list에 for문으로 담고 list_div의 html을 list로 변경
	        $(data['customize_list']).each(function(index, item) {
	        	list += "<tr>";
	        	list += "<td>" + item.scode + "</td>";
	        	list += "<td>" + item.name + "</td>";
	        	list += "<td>" + item.fieldidx + "</td>";
	        	list += "<td>" + item.column1 + "</td>";
	        	list += "<td>" + item.column2 + "</td>";
	        	list += "<td>" + item.column3 + "</td>";
	        	list += "</tr>";
	        });
			$('#list_div').html(list);
			$('#gonggoidx').val(data['gonggoidx']);
        }, error: function(xhr, status, error) {
        	console.log(xhr, status, error);
        }
	});
};
$(document).ready(function(){
	getSession();
	const page=$('#page').val();
	const part=$('#part_val').val();
	$('#list_div').html(page);
	$('#upload').on('click',function(){
		location.href="../passfail/upload?check=CM&part="+part;
	});
	$('#serch').on('click',function(){
		serchName(part);
	});
	$('#download').on('click',function(){
		 window.location = '../api/downloadCustomizeList?part='+part;
	});
	$('#download_pattern').on('click',function(){
		 window.location = '../api/downloadCustomizePattern';
	});
	$('#back').on('click',function(){
		 location.href = '../passfail/write?part='+part;
	});
});
</script>
</head>
<body>
<div id="list">
<h2>&#10063;사용자 맞춤 설정</h2>
	<form name="serch_name">
		<table>
			<tr>
				<th style="width:200px">이름</th>
				<td><input type="text" name="name" id="name" style="width:1150px">
				<td><input type="button" name="serch" id="serch" value="검색"></td>
			</tr>
		</table>
	</form>
	
	<form name="custom_list">
		<input type="hidden" id="part_val" value="${part }">
		<input type="hidden" id="page" value="${page }">
		<!-- gonggoidx를 받아둔 히든폼이지만 사용은 안할예정 -->
		<input type="hidden" name="gonggoidx" id="gonggoidx" value="${gonggoidx }">
			<div>
				<input type="button" name="upload_file" id="upload" value="파일등록">
				<input type="button" name="download_file" id="download" value="다운로드">
				<input type="button" name="download_pattern" id="download_pattern" value="양식 다운로드">
			</div>
		<table>
			<thead>
				<tr>
					<th style="width:225px">수험번호</th>
					<th style="width:225px">성명</th>
					<th style="width:225px">채용분야</th>
					<th style="width:225px">개인1</th>
					<th style="width:225px">개인2</th>
					<th style="width:225px">개인3</th>
				</tr>
			</thead>
			<tbody id="list_div">
				
			</tbody>
			<tfoot>
				<tr>
					<td><input type="button" name="back" id="back" value="돌아가기"></td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>
</body>
</html>