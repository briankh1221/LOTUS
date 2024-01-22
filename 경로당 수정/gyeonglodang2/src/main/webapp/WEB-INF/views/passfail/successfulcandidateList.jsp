<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>합격자 목록</title>
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
   #list th, #list td {
       text-align: center; 
    }
    
    table {
       border-collapse: collapse;
       border-bottom: 2px solid black;
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
     
     textarea {
      width: 1150px;
      height: 200px;      
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
     
     #thList {
        background-color: black;
       color: white;
     }
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
//검색 버튼 클릭시 실행되는 function PassFailAPi로 값을 전달하고 받아옴
function serchFieldPassFail(){
   //hidden 에 jstl로 담아둔 변수값을 head에서 쓰기위해 변수에 초기화
   const fieldidx=$('#field_select').val();
   const pass_fail=$('#pass_fail_select').val();
   const param={fieldidx:fieldidx,pass_fail:pass_fail};
   $.ajax({
        type: 'POST',
        url: '../api/serchFieldPassFail',
        dataType: 'json',
        data: param,
        success: function(data) {
         console.log(data);
         let list = "";
         //받아온 list를 let list에 for문으로 담고 list_div의 html을 list로 변경
           $(data['successful_list']).each(function(index, item) {
              list += "<tr>";
              list += "<td>" + item.scode + "</td>";
              list += "<td>" + item.name + "</td>";
              list += "<td><input type='button' onclick='gofinal("+item.scode+")' value='지원서'></td>";
              list += "<td>" + item.fieldidx + "</td>";
              list += "<td>" + item.pass_fail + "</td>";
              list += "</tr>";
           });
         $('#list_div').html(list);
         $('#gonggoidx').val(data['gonggoidx']);
         
        }, error: function(xhr, status, error) {
           console.log(xhr, status, error);
        }
   });
};


function gofinal(scode){
	  let param={scode:scode}
      $.ajax({
           type: 'POST',
           url: '../api/gofinal',
           dataType: 'json',
           data: param,
           success: function(data) {
            location.href="../recruit_resume/application_final?scode="+data['scode']+"&noticeControl_idx="
                  +data['gonggoidx']+"&recruitCateg_categ="+data['field'];
           }, error: function(xhr, status, error) {
              console.log(xhr, status, error);
           }
      });
   };

$(document).ready(function(){
   //get 방식으로 웹 로딩후 hidden에 담겨있는 page값을 변수에 초기화
   //list_div의 html을 page로 변경
   getSession();
   const page=$('#page').val();
   $('#list_div').html(page);
   $('#serch').on('click',function(){
      serchFieldPassFail();
   });
   $('#upload').on('click',function(){
      location.href="../passfail/upload?check=SC";
   });
   $('#download').on('click',function(){
       window.location = '../api/downloadList';
   });
   $('#download_pattern').on('click',function(){
       window.location = '../api/downloadPattern';
   });
});
</script>
</head>
<body>
<div id="list">
<h2>합격자 목록</h2>
<form name = "serch_field_pass_fail">
   <table>
      <tr>
         <td>지원분야</td>
         <td>
         <!-- part에 연결되어있는 fieldidx들을 불러와서
         각각 option으로 등록 -->
            <select name="field_select" id="field_select">
               <option value="전체">전체</option>
               <c:if test="${not empty field_list }">
               <c:forEach items="${field_list }" var="fieldidx" varStatus="loop">
                  <option value="${fieldidx }">${fieldidx }</option>
               </c:forEach>
               </c:if>
            </select>
         </td>
         <td>합격여부</td>
         <td>
         <!-- 합격여부를 pass_fail에 담아서 전달되는 select의 val값을
         선택 -->
            <select name="pass_fail_select" id="pass_fail_select">
               <option value="전체">전체</option>
               <option value="합격">합격</option>
               <option value="불합격">불합격</option>
            </select>
            &nbsp; <input type="button" name="serch" id="serch" value="검색">
         </td>
      </tr>
   </table>
   <!-- 검색 클릭시 serch함수 실행 list_div의 html에 담길 값을 변경 -->
   
</form>
<form name="successful_candidate_list">
<!-- controller에서 넘어온값을 head에서 사용하기위해 임시저장할 공간 -->
<input type="hidden" name="page" id="page" value="${page }">
<!-- gonggoidx를 받아둔 히든폼이지만 사용은 안할예정 -->    
<input type="hidden" name="gonggoidx" id="gonggoidx" value="${gonggoidx }">
 <table>
        <tr>
            <td><input type="button"  name="upload" id="upload" value="합격자 파일 등록"></td>
            <td><input type="button"  name="download" id="download" value="명단 다운로드"></td>
            <td><input type="button" name="download_pattern" id="download_pattern" value="양식 다운로드"></td>
        </tr>
    </table>
    <table>
        <thead>
            <tr>
                <th style="width:225px">수험번호</th>
                <th style="width:225px">성명</th>
                <th style="width:225px">지원서</th>
                <th style="width:225px">지원분야</th>
                <th style="width:225px">합격여부</th>
            </tr>
        </thead>
        <tbody id="list_div">
            <!-- 데이터 행들이 동적으로 추가될 공간 -->
        </tbody>
    </table>
</form>
</div>
</body>
</html>