<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 조회</title><!-- 합격자 페이지 -->
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
		text-align: center;
		margin: 0 auto;
	}
	
	table
	{
		width: 1200px;
		margin: 0 auto;
		margin-bottom: 100px;
	}
	
	table tr td {
		font-size:25px;
		border-top: 2px solid black;
		padding: 10px;
		background-color: #f5f5f5;
	}
</style>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
   let custom_context = $('#custom_context').val();
   let field_context = $('#field_context').val();
   let common_text = $('#common_text').val();
   let text = "<tr>";
   text += "<td>[문구]</td>";
   text += "</tr>";
   if(custom_context!==""){
      text = text.replace("[문구]",custom_context);
      console.log(text);
   }else{
      if(field_context!==""){
         text = text.replace("[문구]",field_context);
      }else{
         text = text.replace("[문구]",common_text);
      }
   }
   $('#content').html(text);
   $('#show_custom').on('click',function(){
      text = "<tr>";
      text += "<td>[문구]</td>";
      text += "</tr>";
      text = text.replace("[문구]",custom_context);
      $('#content').html(text);
      $('#show_custom').hide();
      $('#show_field').show();
      $('#show_common').show();
   });
   $('#show_field').on('click',function(){
      text = "<tr>";
      text += "<td>[문구]</td>";
      text += "</tr>";
      text = text.replace("[문구]",field_context);
      $('#content').html(text);
      $('#show_custom').show();
      $('#show_field').hide();
      $('#show_common').show();
   });
   $('#show_common').on('click',function(){
      text = "<tr>";
      text += "<td>[문구]</td>";
      text += "</tr>";
      text = text.replace("[문구]",common_text);
      $('#content').html(text);
      $('#show_custom').show();
      $('#show_field').show();
      $('#show_common').hide();
   });
});
      
   
</script>
</head>
<body>
<form>
<input type="hidden" id="custom_context" value="${custom_context }">
<input type="hidden" id="field_context" value="${field_context }">
<input type="hidden" id="common_text" value="${common_text }">
<table border="1">
   <tbody id="text_div">
      
   </tbody>
</table>
</form>
<div id="result">
    <%-- 서버에서 전달된 합격 여부에 따라 다른 문구 표시 --%>
    <c:choose>
        <c:when test="${pass_fail eq '합격'}">
            합격했습니다!
        </c:when>
        <c:otherwise>
            불합격했습니다.
        </c:otherwise>
    </c:choose>
</div>
<div id="content">

</div>
<div id="content1">
   <c:if test="${not empty custom_context }">
      <input type = "button" id="show_custom" value="개인별 문구 보기">
   </c:if>
   <c:if test="${not empty field_context }">
      <input type = "button" id="show_field" value="분야별 문구 보기">
   </c:if>
   <c:if test="${not empty common_text }">
      <input type = "button" id="show_common" value="공통문구 보기">
   </c:if>
</div>
</body>
</html>