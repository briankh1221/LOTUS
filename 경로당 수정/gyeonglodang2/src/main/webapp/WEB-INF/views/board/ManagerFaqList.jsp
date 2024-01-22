<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp" %>
<%@include file="../include/sidebar/adminsidebar1-1.jsp" %>	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value="${requestScope.list}" />
		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manger 전용 FAQ 게시판</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
window.onload = function() {
	getSession();
}
</script>
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
    
    #list th, #list td {
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
<form action = "../board/faqWrite">
<div id="list">
    <h2>&#10063;ManagerFAQ게시판</h2><br>
    <div id="funcBtn">
    	<input type = "submit" value = "글쓰기">
    </div>
    <table>
        <tr>
            <th style="width:675px;">질문</th>
            <th style="width:675px;">답변</th>
        </tr>
        <c:forEach items="${list}" var="dto">
            <tr>
                <td style="width:675px;">
                   Q:<a href="../board/faqDetailForm?faq_idx=${dto.faq_idx}" style="display:inline-block;"><c:out value="${dto.faq_board_title}" /></a>
                </td>
                <td style="width:675px;">
                    A:<c:out value="${dto.faq_board_context}" />
                </td>
            </tr>
        </c:forEach>
    </table>
</div>    
    </form>

</body>
</html>