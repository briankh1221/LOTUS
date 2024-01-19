<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/recruitheader.jsp"%>
<c:set var="list" value="${requestScope.list}" />
<c:set var="loggedInUser" value="${requestScope.recruit_email}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Q&A 게시판 리스트</title>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <style>
#list 
		{
			width: 1200px;
			margin: 0 auto;
			margin-bottom: 100px;
		}
		
		h2
		{
			width: 1200px;
			height: 100px;
			line-height: 100px;
		}
		    	
        table 
        {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
            border: 1px solid black;
        }
        
        tr
        {
        	border: 1px dotted black;
        }

        th, td 
        {
            padding: 8px;
            text-align: center;
        }

        th 
        {
            background-color: black;
            color: white;
        }

        #post-list tr:nth-child(even) 
        {
            background-color: #f9f9f9;
        }

        #post-list tr:hover 
        {
            background-color: #f5f5f5;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div id="lists">
    <form action="#">
        <div class="container">
            <h2 style="text-align: center">Manager Q&A 게시판</h2>
            <table>
                <thead>
                <tr>
                    <th>제목</th>
                    <th>등록자</th>
                    <th>등록일</th>
                    <th>답변여부</th>
                </tr>
                </thead>
                <tbody id="post-list">
                <c:forEach items="${list}" var="dto">
                    <c:if test="${dto.recruit_email eq loggedInUser}">
                        <tr>
                            <td><a href="../board/qnaSearchViewDetailForm?idx=${dto.idx}">
                                <c:out value="${dto.qna_title}" /></a></td>
                            <td>${dto.recruit_name }</td>
                            <td>${dto.qna_registration_date }</td>
                            <td>
                                <c:choose>
                                    <c:when test="${empty dto.qna_answer_board}">
                                        미응답
                                    </c:when>
                                    <c:when test="${not empty dto.qna_answer_board}">
                                        답변 완료
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </form>
</div>
</body>
</html>