<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value="${requestScope.list}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>resumeFieldSetting.jsp</title>
<link rel="stylesheet"
   href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
div#list {
    position: absolute;
    top: 200px;
    left: 370px;
}
* {
 box-sizing: border-box;
}
div:last-child {
 margin-bottom: 60px;
}	
h3 {
 width: 1200px;
 height: 100px;
 line-height: 90px;	
 margin: 0 auto;
 padding: 10px;
 background-color: #f5f5f5;
}	
#closeBtn {
 width: 1200px;
 height: 30px;
 margin: 0 auto;
 text-align: right;
}	
#collapseAll {
 width: 150px;
 height: 30px;
}
#content {
 margin-bottom: 30px;
}	
.faq-item {
 width: 1200px;
 margin: 20px auto;
}	
form {
 position: relative;
 top: 100px;
}
#button {
     width: 150px;
      height: 30px;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(40%, 2000%);
}
</style>
</head>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp"%>
<body>
<div id ="list">
   <h2 style="text-align: center">채용분야별 문항 설정</h2>
   <div id="closeBtn">
      <button id="collapseAll">전체 닫기</button>
   </div>
<form id="form" action="resumeFieldInsert" method="POST">
	<div id="content">
   <c:forEach items="${infoCustom_list}" var="icdto">
      <div class="faq-item">
         <h3>
            인적사항 커스텀 문항 : 
            <c:out value="${icdto.infoCustomCategory}" />
            <input type="hidden" value="${icdto.idx }" name="infoCustomField_idx">
         </h3>
         <div>
			<c:forEach items="${fieldList}" var="fdto">
			    <c:set var="isChecked" value="false" />
			    <c:forEach items="${infoCustomField_list}" var="icfsdto">
			        <c:if test="${icdto.idx eq icfsdto.infoCustomField_idx and fdto.recruitCateg_categ eq icfsdto.infoCustomField}">
			            <c:set var="isChecked" value="true" />
			        </c:if>
			    </c:forEach>
			    <label>
			        <input type='checkbox' value='${fdto.recruitCateg_categ}' name='infoCustomField' 
			               <c:if test="${isChecked}">checked</c:if>>
			        <input type="hidden" value="0" name="infoCustomField">
			        <c:out value="${fdto.recruitCateg_categ}" />
			    </label>
			    <br>
			</c:forEach>
			<input type="hidden" value="1" name="infoCustomField">
         </div>
      </div>
  	 </c:forEach>

      <c:forEach items="${resumeCustom_list}" var="rcdto">
      <div class="faq-item">
         <h3>
            맞춤형 문항 :
            <c:out value="${rcdto.customTitle}" />
            <input type="hidden" value="${rcdto.idx }" name="customField_idx">
         </h3>
         <div>
         <c:forEach items="${fieldList}" var="fdto">
         			    <c:set var="isChecked" value="false" />
			    <c:forEach items="${resumeCustomField_list}" var="customsdto">
			        <c:if test="${rcdto.idx eq customsdto.customField_idx and fdto.recruitCateg_categ eq customsdto.customField}">
			            <c:set var="isChecked" value="true" />
			        </c:if>
			    </c:forEach>
			<label>
			        <input type='checkbox' value='${fdto.recruitCateg_categ}' name='customField' 
			               <c:if test="${isChecked}">checked</c:if>>
			        <input type="hidden" value="0" name="customField">
			<c:out value="${fdto.recruitCateg_categ}" />
			</label>
			<br>
         </c:forEach>
			<input type="hidden" value="1" name="customField">
         </div>
      </div>
 	  </c:forEach>
 	  	  
 	  <c:if test="${not empty personal_dto }">
      <div class="faq-item">
         <h3>
            자기소개서 문항 : 
            <c:out value="${personal_dto.personalTitle}" />
            <input type="hidden" value="${personal_dto.idx }" name="personalField_idx">
         </h3>
         <div>
         <c:forEach items="${fieldList}" var="fdto">
         			    <c:set var="isChecked" value="false" />
			    <c:forEach items="${personalField_list}" var="pfsdto">
			        <c:if test="${personal_dto.idx eq pfsdto.personalField_idx and fdto.recruitCateg_categ eq pfsdto.personalField}">
			            <c:set var="isChecked" value="true" />
			        </c:if>
			    </c:forEach>
			<label>
			        <input type='checkbox' value='${fdto.recruitCateg_categ}' name='personalField' 
			               <c:if test="${isChecked}">checked</c:if>>
			        <input type="hidden" value="0" name="personalField">
			<c:out value="${fdto.recruitCateg_categ}" />
			</label>
			<br>
         </c:forEach>
			<input type="hidden" value="1" name="personalField">
         </div>
      </div>
 	  </c:if>
 	    
 	  <c:if test="${not empty career_dto }">
      <div class="faq-item">
         <h3>
            경력기술서 문항 : 
            <c:out value="${career_dto.careerTitle}" />
            <input type="hidden" value="${career_dto.idx }" name="careerField_idx">
         </h3>
         <div>
         <c:forEach items="${fieldList}" var="fdto">
         			    <c:set var="isChecked" value="false" />
			    <c:forEach items="${careerField_list}" var="cfsdto">
			        <c:if test="${career_dto.idx eq cfsdto.careerField_idx and fdto.recruitCateg_categ eq cfsdto.careerField}">
			            <c:set var="isChecked" value="true" />
			        </c:if>
			    </c:forEach>
			<label>
			<input type='checkbox' value='${fdto.recruitCateg_categ}' name='careerField'
					<c:if test="${isChecked}">checked</c:if>>
			<input type="hidden" value ="0" name="careerField">
			<c:out value="${fdto.recruitCateg_categ}" />
			</label>
			<br>
         </c:forEach>
			<input type="hidden" value="1" name="careerField">
         </div>
      </div>
 	  </c:if>
 	  	  
 	  <c:forEach items="${rsmfileUpload_list}" var="rfudto">
      <div class="faq-item">
         <h3>
            필수제출 자료 :
            <c:out value="${rfudto.rsmfileupload_fileName}" />
            <input type="hidden" value="${rfudto.rsmfileupload_idx }" name="rsmfileupload_field_idx">
         </h3>
         <div>
         <c:forEach items="${fieldList}" var="fdto">
         			    <c:set var="isChecked" value="false" />
			    <c:forEach items="${rsmfileUploadField_list}" var="rfsdto">
			        <c:if test="${rfudto.rsmfileupload_idx eq rfsdto.rsmfileupload_field_idx and fdto.recruitCateg_categ eq rfsdto.fileField}">
			            <c:set var="isChecked" value="true" />
			        </c:if>
			    </c:forEach>
			<label>
			<input type='checkbox' value='${fdto.recruitCateg_categ}' name='fileField'
					<c:if test="${isChecked}">checked</c:if>>
			<input type="hidden" value ="0" name="fileField">
			<c:out value="${fdto.recruitCateg_categ}" />
			</label>
			<br>
         </c:forEach>
			<input type="hidden" value="1" name="fileField">
         </div>
      </div>
 	  </c:forEach>
 	  </div>
   </form>
   <br><br><br><br>
     <div id="button" class="closingBtn">
	  <a onclick="javascript: resumeFieldInsert()">저장</a>
	</div>
   <script>
        $(document).ready(function() {
            var faqItems = $(".faq-item");
            faqItems.accordion({
                collapsible: true,
                active: false
            });
            $("#collapseAll").click(function() {
                faqItems.accordion("option", "active", false);
            });
        });
     	// 클릭 시 변경된 지원서 양식 전체 정보를 저장하는 메서드
        function resumeFieldInsert() {
          document.getElementById("form").submit();
        }
    </script>
</div>
</body>
</html>