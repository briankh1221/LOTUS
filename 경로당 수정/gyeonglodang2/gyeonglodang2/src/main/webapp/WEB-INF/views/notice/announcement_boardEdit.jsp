<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp" %>
<%@include file="../include/sidebar/adminsidebar1-1.jsp" %>		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jobNoticeCateg_boardEdit</title>
</head>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<Script>
function announcement_boardEditList() {
	
	let announcement_no = location.search.split('=')[1];
	param = {announcement_no : announcement_no};

	$.ajax({
		type : 'GET',
		url : '../notice/announcement_boardEditList',
		dataType : 'json',
		data : param,
		success : function(data) {
			$('#announcement_no').attr('value', announcement_no);
			$('#announcement_category').attr('value',data['announcement_category']);
			$('#announcement_title').attr('value',data['announcement_title']);
			if (data['announcement_operation'] == 'y') {
				$('#announcement_operation').attr('checked', true);
			}
			$('#announcement_postingDate').attr('value', data['announcement_postingDate'].split('~')[0]);
			$('#announcement_postingDate2').attr('value', data['announcement_postingDate'].split('~')[1]);
			$('#announcement_content').append(data['announcement_content']);
		},
		error : function(xhr, status, error) {
			console.log(xhr, status, error);
		}
	});
  }

function boardEditDelete() {
	
	let announcement_no = location.search.split('=')[1];
	param = {announcement_no : announcement_no};
	$.ajax({
		type : 'GET',
		url : '../notice/announcement_boardEditDelete',
		dataType : 'json',
		data : param,
		success : function(data) {
			
			window.location.href = '../notice/announcement_board';
		},
		error : function(xhr, status, error) {
			console.log(xhr, status, error);
		}
	});
  }

function boardEditPreview() {		
	let announcement_no = location.search.split('=')[1];
	window.location.href="../notice/announcement_boardPreview?announcement_no="+ announcement_no;
   }

function savingAll() {
	$('#All').attr('action', '../notice/savingBoardEdit');
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

window.onload = function() {
	getSession();
	announcement_boardEditList();
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
    <h2>&#10063;공지사항</h2>
    <form id="All" action="../notice/savingBoardEdit" method="post">
    	<div id="funcBtn">
       		<input type="button" value="목록" onclick="location.href='../notice/announcement_board'">
         	<input id="deleteBtn" type="button" value="삭제" onclick="boardEditDelete();">
          	<input id="previewBtn" type="button" value="미리보기" onclick="boardEditPreview();">
          	<input id="saveBtn" type="button" value="저장" onclick="savingAll();">
         	<input type="hidden" style="display: none;" id="announcement_no" name="announcement_no">
    	</div>
        <section>
            <table>
                <tr>
                    <th><label for="announcement_category" style="width:200px;">공고 전용 및 전체</label></th>
                    <td><input type="text" id="announcement_category" name="announcement_category" autofocus style="width:1150px;"></td>
                </tr>
                <tr>
                    <th><label for="announcement_title" style="width:200px;">제목</label></th>
                    <td><input type="text" id="announcement_title" name="announcement_title" style="width:1150px;"></td>
                </tr>
                <tr>
                    <th><label for="announcement_postingDate" style="width:200px;">게시기간</label></th>
                    <td>
                        <input id="announcement_postingDate" name="announcement_postingDate1" type="date" /> ~
                        <input id="announcement_postingDate2" name="announcement_postingDate2" type="date">
                    </td>
                </tr>
                <tr>
                    <th><label for="announcement_operation" style="width:200px;">게시여부</label></th>
                    <td>
                        <input id="announcement_operation" type="checkbox" name="announcement_operation">공고 올리기
                    </td>
                </tr>
                <tr>
                    <th><label for="announcement_content" style="width:200px;">내용</label></th>
                    <td><textarea id="announcement_content" name="announcement_content" placeholder="텍스트를 입력하세요" style="width:1150px; height:350px;"></textarea></td>
                </tr>
            </table>
        </section>
    </form>
</div>
</body>
</html>
