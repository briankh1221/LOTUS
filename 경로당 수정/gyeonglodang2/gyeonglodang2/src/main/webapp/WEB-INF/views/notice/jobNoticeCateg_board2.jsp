<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@include file="../include/header/recruitheader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style>
   #list {
      width: 1200px;
      margin: 40px auto;
      
      margin-bottom: 100px;
   }
   
   .mainIMG {
      background-color: #F5F5F5;
   }
   
   h2{
         width: 1200px;
         height: 100px;
         line-height: 100px;
         text-align: center;
      }
   table {
          width:100%; 
          border-collapse: collapse;
      }
   
   th, td {
       padding: 10px; 
       font-size: 16px;
   }
   
   tr {
      height: 60px;
      border-bottom: 1px dotted black;
   }
   
   tr:last-child {
      border-bottom: 3px solid black;
   }
   
   th {
      background-color: black;
      color: white;
   }
   
   td:first-child{
      font-weight: bold;
   }
   
   tr:hover{
      font-size: 17px;
      font-weight: bold;
	  background-color: #F5F5F5;
      
   }   
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>

function searchList() {   // 채용공고 게시물 목록 
   let companyIdx = location.search.split('=')[1];   
   let param = {companyIdx};
   $.ajax({            
      type: 'GET',
         url: '../notice/jobNotice_boardList2',
         dataType: 'json',
         data: param,
         success: function(data) {
            str = '';
               for(i=0; i<data.boardList.length; i++) {
                  str += '<tr><td>' + data.boardList[i]['noticeControl_idx'] + '</td><td>';   // No. 
                  str += '<a href="../notice/jobNoticeCateg_boardEdit_preview2?companyIdx=' + data.boardList[i]['companyIdx']  + '&&noticeControl_idx=' + data.boardList[i]['noticeControl_idx'] + '">' + data.boardList[i]['jobNotice_title'] + '</a>' + '</td><td>' + data.boardList[i]['jobNotice_regidate'] + '</td></tr>';
                }   
                $('#jobNoticeCateg_bored').html(str);         
         },
         error: function (xhr, status, error) {
             console.log(xhr, status, error);
         }
     });
 }
 
window.onload = function () {
   companyLogoView();
   searchList();
}

</script>
<body>
<div id="list">
   <h2>채용공고 게시물 목록</h2>
   <section>
      <table style="text-align: center;">
         <tr>
            <th id="jobNoticeCateg_idx" width="50px">No.</th>
            <th id="jobNoticeCateg_content" width="300px" >제목</th>
            <th id="jobNoticeCateg_postingDate" width="300px">등록일</th>
         </tr>
         <tbody id="jobNoticeCateg_bored"></tbody>
      </table>
   </section>
</div>
</body>
</html>
