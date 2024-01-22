<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp" %>
<%@include file="../include/sidebar/adminsidebar1-1.jsp" %>	
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>

  <script>
    function faqInsert() {
        // 질문과 답변 필드의 값을 가져오기
        var question = $('input[name="faq_board_title"]').val().trim();
        var answer = $('textarea[name="faq_board_context"]').val().trim();

        // 질문과 답변 필드가 모두 비어 있지 않을 때만 등록
        if (question === '' || answer === '') {
            alert('질문과 답변을 모두 입력하세요.');
        } else {
            // 입력 필드가 비어 있지 않을 때 폼 제출
            $('#formTag').attr('action', '../board/faqInsert');
            $('#formTag').submit();
            console.log('faqWrite - faqinsert');
        }
    }
</script>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FAQ 작성</title>
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
</head>
<body>
<div id="list">
    <h2>&#10063;FAQ 작성 페이지</h2>
    <div class="container">
        <form action="../board/faqInsert" method="post" id="formTag">
        <div id="funcBtn">
        	<input type="button" onclick="faqInsert()" value="등록">
        </div>
            <table>
            	<tr>
                  <th style="width:200px;">질문</th>
 	                <td><input type="text" name="faq_board_title" style="width:1150px;"></td>
                </tr>
                <tr>
                   <th style="width:200px;">답변</th>
                   	<td><textarea name="faq_board_context" style="width: 1150px; height: 250px;"></textarea></td>
                </tr>
            </table>
        </form>
    </div>
 </div>
</body>
</html>