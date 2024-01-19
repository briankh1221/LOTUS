<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/gonggoheader.jsp" %>
<%@include file="../include/sidebar/adminsidebar1-1.jsp" %>
<c:set var="list" value="${requestScope.list}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시판 리스트</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
 function paging(pageNum){
    let param = {pageNum};
    $.ajax({
         type: 'GET',
           url: '../board/paging',
           dataType: 'json',
           data: param,   
           success: function(data) {
             let str = '';
         for(i=1; i<=data.totalPage; i++){
            str += '<input type="button" value="' + i + '" onclick="changePage(' + i + ')">';
         }
         $('#pagination').html(str);
           },
            error: function(xhr, status, error) {
                console.log(xhr, status, error);
            }
        });      
    }
 window.onload = function() {
	 getSession();
     paging(1);
 }
 function changePage(pageNum) {
     // 원하는 URL로 이동합니다.
     window.location.href = '../board/ManagerQnaList?pageNum='+pageNum;
   }
 
</script>

<style>

	#pagination {
	   text-align: center;
	   margin-top: 20px;
	}

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
    
    #list th, #list td {
    	text-align: center; 
    }
    
      table th {
    	background-color: black;
    	color: white;
    }
</style>
</head>
<body>
<div id="list">   
<form action="../board/qnaWrite">
      <div class="container">
         <h2>&#10063;Manager Q&A 게시판</h2><br>
         <table>
            <thead>
               <tr>
                  <th style="width:270px;">No.</th>
                  <th style="width:270px;">제목</th>
                  <th style="width:270px;">등록자</th>
                  <th style="width:270px;">등록일</th>
                  <th style="width:270px;">답변여부</th>
               </tr>
            </thead>
            <tbody id="post-list">
            <thead>
               <c:forEach items="${list}" var="dto">
                  <tr>
                     <td>${dto.rownum}</td>
                     <td><a href="../board/qnaDetailForm?idx=${dto.idx}"><c:out
                              value="${dto.qna_title}" /></td>
                     <td>${dto.recruit_name }</td>
                     <td>${dto.qna_registration_date }</td>
                    <td><c:choose>
                           <c:when test="${empty dto.qna_answer_board}">
                                 미응답
                             </c:when>
                           <c:otherwise>
                              <%
                              java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
                              java.util.Date currentDate = new java.util.Date();
                              String currentTime = dateFormat.format(currentDate);
                              out.print(currentTime);
                              %>
                           </c:otherwise>
                        </c:choose></td>
                  </tr>
               </c:forEach>
            </thead>
            </tbody>
         </table>
      </div>
       <div id="pagination" style=" position: ; bottom: 150px; left: : 600px; width: 100%; background-color: white;">
            <!-- 페이지 버튼을 고정된 위치에 배치 -->
        </div>
   </form>
</div>
</body>
</html>