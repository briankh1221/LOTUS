<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/recruitheader.jsp"%>
<c:set var="list" value="${requestScope.list}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 게시판</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
	 .faq-item {
        margin: 10px;
        max-width: 1000px; /* 최대 너비를 지정 */
        margin-left: auto;
        margin-right: auto;
    }

	h2
	{
		width: 1200px;
		height: 100px;
		line-height: 100px;
		margin: 0 auto;
	}
	
	#closeBtn
	{
		width: 1200px;
		height: 30px;
		margin: 0 auto;
		margin-bottom: 20px;
	}	
	
	#closeBtn button
	{
		width: 150px;
		height: 30px;
	}

	#list
	{
		width: 1200px;
		margin: 0 auto;
		margin-bottom: 100px;
	}
	
	.ui-accordion-header-active 
	{
	    background-color: black;
	    color: white;
	    font-weight: bold; 
    }
</style>
</head>
<body>
   <h2 style="text-align: center">FAQ 게시판</h2>


   <c:forEach items="${list}" var="dto">
      <div class="faq-item">
         <h3>
            Q:
            <c:out value="${dto.faq_board_title}" />
         </h3>
         <div>
            A:
            <c:out value="${dto.faq_board_context}" />
         </div>
      </div>
   </c:forEach>
   <script>
        $(document).ready(function() {
            var faqItems = $(".faq-item");
            faqItems.accordion({
                collapsible: true,
                active: false
            });
        });
    </script>
</body>
</html>