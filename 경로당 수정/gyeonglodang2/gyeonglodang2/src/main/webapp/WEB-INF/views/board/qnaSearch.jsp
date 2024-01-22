<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/header/recruitheader.jsp"%>
<c:set var="list" value="${requestScope.list}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Q&A 조회하기</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            // form이 제출될 때 실행되는 함수
            $("#formTag").submit(function(event) {
                // 아이디와 비밀번호를 가져옴
                var recruit_email = $("#recruit_email").val();
                var recruit_password = $("#recruit_password").val();

                // 간단한 유효성 검사
                if (recruit_email.trim() === '' || recruit_password.trim() === '') {
                    alert("이메일과 비밀번호를 입력해주세요.");
                    event.preventDefault(); // form 제출을 막음
                }
            });
        });

        
   function login(){ 
      let rs = ${rs};
      if(rs===0){
         alert("아이디 비밀번호를 확인해주세요");
         location.href="../board/qnaSearch?companyIdx=" + ${companyIdx};
      }
   }       
     window.onload = function() {
      login();
     }
    </script>
    <style>
       * {
          box-sizing: border-box;
       }
    
       #wrap 
       {
          width: 1200px;
          margin: 0 auto;
       }
       
       #title
       {
          width: 1200px;
          height: 100px;
       }
       
       #title h1
       {
          width: 1200px;
          height: 100px;
          line-height: 100px;
          text-align: center;
       
       }
       
       #formTag 
       {
          width: 1200px;
       }
       
       .qnaSearchTable
       {
          width: 800px;
          background-color: #F5F5F5;
          margin: 0 auto;
          border-collapse: collapse;
       }
       
       .qnaSearchTable  tr 
       {
          height: 60px;
       }
       
       .qnaSearchTable  tr td:first-child
       {
          width: 200px;
          text-align: center;
          font-weight: bold;
          background-color: black;
          color: white;
       }
       
       .qnaSearchTable  tr td:last-child
       {
          padding: 20px 10px;
          text-align: center;
       }
       
       .qnaSearchTable  tr td:last-child input 
       {
          width: 500px;
          height: 30px;
          padding-left: 10px;
       }
       
       .qnaSearchTable tr:last-child td  
       {
          background-color: white;
          height: 80px;
       }
       
       .qnaSearchTable tr:last-child td input
       {
          width: 150px;
          height: 30px;
       }
       
    </style>
</head>
<body>
    <div id="wrap">
        <div id="title">
            <h1>Q&A 조회하기</h1>
        </div>
        <div id="content">
            <!-- form 태그 추가 -->
            <form id="formTag" action="../board/qnaSearchView" method="POST">
                <table class="qnaSearchTable">
                    <tr>
                        <td>이메일</td>
                        <td><input type="text" placeholder="이메일을 입력해주세요" name="recruit_email" id="recruit_email"></td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td><input type="password" placeholder="비밀번호를 입력해주세요" id="recruit_password" name="recruit_password"></td>
                    </tr>
                    <!-- submit 버튼을 사용하여 form 전송 -->
                    <tr>
                        <td colspan="2"><input type="submit" value="조회하기" id="submitBtn"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
