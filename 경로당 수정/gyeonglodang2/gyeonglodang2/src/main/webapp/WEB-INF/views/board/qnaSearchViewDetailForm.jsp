
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header/recruitheader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

<head>
<meta charset="UTF-8">
<title>Manager Q&A 디테일 게시판</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style>
	* 
		{
			box-sizing: border-box;
		}
		
		h2
		{
			width: 1200px;
			height: 100px;
			line-height: 100px;
			margin: 0 auto;
			text-align: center;
		}
		
		form
		{
			width: 1200px;
			margin: 0 auto;
		} 
		
		table
		{
			width: 1000px;
			margin: 0 auto;
			border: 1px solid black;
			border-collapse: collapse;
		}
		
		tr 
		{
			height: 60px;
		}
		
		td
		{
			padding: 10px;
			border: 1px dotted black;
			
		}
		
		table tr td:first-child
		{
			width: 200px;
			text-align: center;
			background-color: black;
			color: white;
			border-bottom: 1px dotted white;
			font-size: 17px;
			font-weight: bold;
		}
		
		input
		{
			border: none;
			height: 30px;
			font-size: 16px;
		}
		
		input[name=qna_title]
		{
			width: 780px;
		}
		
		input[name=recruit_name]{
			width: 780px;
		}
		
		.read-only-textarea
		{
			width: 780px;
			height: 800px;
			border: none;
			resize: none;
			padding: 10px;
		}
		
		textarea[name=qna_answer_board]
		{
			width: 780px;
			height: 800px;
			border: none;
			resize: none;
			padding: 10px;
		}
		
		input[name=gonggo_list]
		{
			width: 780px;
		}
		
		.button-container
		{
			width: 1000px;
			height: 30px;
			text-align: right;
			margin: 20px auto;
			margin-bottom: 100px;
		}
		
		.button-container > button
		{
			width: 150px;
			height: 30px;
		}
</style>
</head>
<body>
	<h1 style="text-align: center;">문의하신 Q&A 답변 확인용</h1>
	<form action="../board/qnaUpdate" method="post" id="formTag">
		<input type="hidden" name="idx" value="${param.idx}">
		<table>

			<tr>
				<td><label for="job_area">제목</label></td>
				<td><input type="text" name="qna_title"
					value="${qDto.qna_title}" readonly></td>
			</tr>
			<tr>
				<td>공고 및 채용분야</td>
				<td><input type="text" name="gonggo_list"
					value="${qDto.gonggo_list}" readonly> <input type="text"
					name="field_list" value="${qDto.field_list}" readonly></td>
			</tr>
			<tr>
				<td>등록인</td>
				<td><input type="text" name="recruit_name"
					value="${qDto.recruit_name}" readonly></td>
			</tr>
			<tr>
				<td>문의내용</td>
				<td><textarea class="read-only-textarea" rows="4" cols="50"
						name="qna_context" readonly>${qDto.qna_context}</textarea></td>
			</tr>
			<tr>
				<td>답변</td>
				<td><textarea name="qna_answer_board" rows="4" cols="50"
						readonly>${qDto.qna_answer_board }</textarea></td>
			</tr>
		</table>
		<div class="button-container">
			<button class="list-button" type="button" onclick="history.back()">확인</button>
		</div>
	</form>
</body>
</html>
