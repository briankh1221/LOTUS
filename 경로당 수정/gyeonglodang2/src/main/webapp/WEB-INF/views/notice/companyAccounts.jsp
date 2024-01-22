<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@include file="../include/header/adminheader2.jsp"%>
<%@include file="../include/sidebar/adminsidebar2.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>companyAccounts</title>
<style>
    .table1 {
      display: flex;
    }

    .div1 {
       overflow-y: scroll;
       margin-right: 150px;
    }

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
<script type="text/javascript"
   src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    function companyList() {
        $.ajax({
            type: 'GET',
            url: '../notice/companyList',
            dataType: 'json',
            success: function(data) {
                str = '';
                for(item of data) {
                    str += '<tr><td style="width:200px;"><a href="#" onclick=companyOneShow(' + item['companyIdx'] + ');>'  + item['company_name'] + '</a></td><td><input type="button" onclick="deleteBtn(' + item['companyIdx'] + ');" value="삭제"></td></tr>';
                }
                $('#companyList').html(str);
            },
            error: function(xhr, status, error) {
                console.log(xhr, status, error);
            }
       });
    }
   
    function companyOneShow(companyIdx) {   // 이미지 업로드 삭제
        $('#companyEditBtn').show();
        $('#companyRegisterBtn').hide();
        param = {companyIdx: companyIdx};
        $.ajax({
            type: 'GET',
            url: '../notice/companyOneShow',
            dataType: 'json',
            data: param,
            success: function(data) {
                $('#company_companyIdx').attr('value', data['companyIdx']);
                $('#company_name').attr('value', data['company_name']);
                $('#company_email').attr('value', data['company_email']);
                $('#company_name').attr('value', data['company_name']);
                if (data['company_operation'] == 'y') {
                   $('#company_operation').attr('checked', true);
                }
                $('#company_note').append(data['company_note']);
            },
            error: function(xhr, status, error) {
                console.log(xhr, status, error);
            }
        });
    }
   
    function deleteBtn(companyIdx) {
        param = {companyIdx: companyIdx};
        $.ajax({
            type: 'GET',
            url: '../notice/companyInfoDelete',
            dataType: 'json',
            data: param,
            success: function(data) {
               companyList();
            },
            error: function(xhr, status, error) {
                console.log(xhr, status, error);
            }
        });         
    }
   
    function companyEdit(companyIdx) {
        param = {companyIdx: companyIdx};
        $.ajax({
            type: 'GET',
            url: '../notice/companyInfoDelete',
            dataType: 'json',
            data: param,
            success: function(data) {
                companyList();
            },
            error: function(xhr, status, error) {
                console.log(xhr, status, error);
            }
        });
    }
   
    function companyRegister() {
        $('#All').attr('action', '../notice/companyRegister');
        $('#All').submit();
        companyList();
    }
   
    function companyEdit() {
        $('#All').attr('action', '../notice/companyEdit');
        $('#All').submit();
        companyList();
    }

    window.onload = function() {
        companyList();
        $('#companyEditBtn').hide();
    }
</script>
</head>
<body>
<div id="list">
   <h2>&#10063;업체정보</h2><br>
   <form id="All" action="../notice/companyRegister" method="post">
         <div class="table1">
            <div class="div1">
               <table>
                  <tbody id="companyList" style="width:200px;"></tbody>
               </table>
            </div>
            <div>
            <div id="funcBtn">
               <input type="hidden" style="display: none;" id ="company_companyIdx" name="companyIdx" value="0"/>
                   <input id="companyRegisterBtn" type="button" value="등록" onclick="companyRegister();"/>
                   <input id="companyEditBtn" type="button" value="수정" onclick="companyEdit();"/>
               </div>
               <table>
                  <tr>
                     <th style="width:200px;"><label for="company_name">기관, 회사명</label></th>
                     <td><input id="company_name" name="company_name" type="text" style="width:750px;"></td>
                  </tr>
                  <tr>
                     <th><label for="company_email" style="width:200px;">이메일</label></th>
                     <td><input id="company_email" name="company_email" type="email" required style="width:750px;"></td>
                  </tr>
                  <tr>
                     <th><label for="company_operation">운영여부</label></th>
                     <td><input id="company_operation" name="company_operation" type="checkbox"></td>
                  </tr>
                  <tr>
                     <th><label for="company_note">비고</label></th>
                     <td><textarea id="company_note" name="company_note" style="width: 750px; height: 250px;"></textarea></td>
                  </tr>
                  </table>
            </div>
         </div>
      </form>
   </div>
</body>
</html>