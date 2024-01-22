<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/recruitheader.jsp"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var form = document.getElementById('formTag');

        form.addEventListener('submit', function(event) {
            var recruitName = document.getElementById('recruit_name').value;
            var recruitEmail = document.getElementById('recruit_email').value;
            var recruitPassword = document.getElementById('recruit_password').value;
            var recruitPhoneNum = document.getElementById('recruit_phonenum').value;
            var qnaTitle = document.getElementById('qna_title').value;
            var qnaContext = document.getElementById('qna_context').value;
            
            if (recruitName.trim() === '') {
                alert('성명을 입력해 주세요.');
                event.preventDefault();
            }

            if (recruitEmail.trim() === '') {
                alert('이메일을 입력해 주세요.');
                event.preventDefault();
            }

            if (recruitPassword.trim() === '') {
                alert('비밀번호를 입력해 주세요.');
                event.preventDefault();
            } else if (recruitPassword.length > 10) {
                alert('비밀번호는 10자리 이하여야 합니다.');
                event.preventDefault();
            }

            if (recruitPhoneNum.trim() === '') {
                alert('연락처를 입력해 주세요.');
                event.preventDefault();
            }

            if (qnaTitle.trim() === '') {
                alert('제목을 입력해 주세요.');
                event.preventDefault();
            }

            if (qnaContext.trim() === '') {
                alert('문의내용을 입력해 주세요.');
                event.preventDefault();
            }
        });
    });
    
</script>
<head>
<meta charset="UTF-8">
<title>Q&A 작성</title>
<style>
   * {
      list-style: none;
      box-sizing: border-box;
   }

   h1 {
      width: 1200px;
      height: 100px;
      line-height: 100px;
      margin: 30px auto;
      font-size: 32px;
   }

   form {
      width: 1200px;
      margin: 0 auto;
   }
   
   #inputTable{
      width: 1000px;
      margin: 0 auto;
      background-color: #F5F5F5F5;
      border-collapse: collapse;
      border: 1px solid black;
   }
   
   
   
   #inputTable tr td:first-child{
      width: 200px;
      height: 60px;
      text-align: center;
      font-size: 17px;
      font-weight: bold;
      background-color: black;
      color: white;
      border: 1px dotted white;      
   }
   
   #inputTable tr td:last-child{
      padding: 10px 20px;
      border-bottom: 1px dotted black;
   }
   
   #gonggo_list {
      width: 300px;
      height: 30px;
   }
   
   #inputTable tr td:last-child input[type=text]
   {
      width: 300px;
      height: 30px;
      padding: 10px;
   }
   
   #inputTable tr td:last-child input[type=email]
   {
      width: 300px;
      height: 30px;
      padding: 10px;
   }
   
   #inputTable tr td:last-child input[type=password]
   {
      width: 300px;
      height: 30px;
      padding: 10px;
   }
   
   
   #inputTable tr td:last-child span
   {
      display: inline-block;
      width: 250px;
      height: 30px;
      font-size: 12px;
      font-weight: bold;
      padding-left: 20px;
      
   }
   
   #inputTable tr td:last-child textarea
   {
      width: 800px;
      height: 500px;
      padding: 10px;
      resize: none;
   }
   
   .submit-btn{
      width: 1200px;
      height: 30px;
      margin: 20px auto;
      text-align: right;
      margin-bottom: 100px;
   }
   
   .submit-btn input
   {
      width: 150px;
      height: 30px;
      margin-right: 100px;
   }
   
   
</style>
</head>
<body>
   <h1 style="text-align: center;">Q&A</h1>
   <form action="../board/qnaInsert" method="post" id="formTag"
      enctype="multipart/form-data">
       <input type="hidden" name="companyIdx" value="${companyIdx}" />
      <table id="inputTable">
         <tr>
            <td>채용공고</td>
            <td>
                <select id="gonggo_list" name="gonggo_list">
                    <option value="1" selected>-선택-</option>
                    <c:forEach var="jobNotice" items="${jobNotices}">
                        <c:if test="${jobNotice.companyIdx eq companyIdx}">
                            <option value="${jobNotice.jobNotice_title}">
                                ${jobNotice.jobNotice_title}
                            </option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
         <tr>
            <td>성명</td>
            <td><input type="text" id="recruit_name" name="recruit_name"></td>
         </tr>
         <tr>
            <td>이메일</td>
            <td><input type="email" id="recruit_email" name="recruit_email"></td>
         </tr>
         <tr>
            <td>비밀번호</td>
            <td><input type="password" id="recruit_password"
               name="recruit_password" placeholder="최대 10자리 숫자만 가능합니다.">
               <span>*등록된 답변을 조회할 때 필요합니다.</span></td>
         </tr>
         <tr>
            <td>연락처</td>
            <td><input type="text" id="recruit_phonenum"
               name="recruit_phonenum" placeholder="숫자만 입력해 주세요"></td>
         </tr>
         <tr>
            <td>제목</td>
            <td><input type="text" id="qna_title" name="qna_title"
               placeholder="[질문분야]_수험번호(출생월일)_성명"></td>
         </tr>
         <tr>
            <td>문의내용</td>
            <td><textarea id="qna_context" name="qna_context" rows="4"
                  cols="50" placeholder="문의내역을 남겨주세요"></textarea></td>
         </tr>
         <tr>
            <!-- 파일 업로드에서는 enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
            <td><label for="qna_file_name">파일첨부</label></td>
            <td><input type="file" multiple="multiple" id="qna_file"
               name="qna_file">
         </tr>
      </table>
      <div class="submit-btn">
         <input type="submit" value="등록">
      </div>
   </form>
</body>
</html>
