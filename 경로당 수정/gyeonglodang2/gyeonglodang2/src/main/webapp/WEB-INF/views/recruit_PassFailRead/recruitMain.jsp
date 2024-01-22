<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value="${requestScope.list}" />
<html>
<head>
<title>사용자Main</title>
<style>
* {
   box-sizing: border-box;
}
a {
   text-decoration: none;
   display: block;
   color: black;
}
h1 {
   width: 1400px;
   height: 200px;
   line-height: 200px;
   margin: 0 auto;      
   text-align: center;
   background-color: black;
   color: white;
   font-size: 40px;
}
#list {
   width: 1400px;
   margin: 0 auto;
   border:1px solid black;
}
#list:after{
   content:"";
   display: block;
   clear: both;
}
.job-box {
   cursor: pointer;
   transition: transform 0.3s;
}
.job-box:hover {
   transform: scale(1.05);
   border-color: black;
} 
.job-box {
   width: 349px;
   height: 355px;
   float: left;
   margin: 57px;
   border: 1px solid lightgray;
   padding: 20px;
}       
.logo {
   display: inline-block;
   width: 300px; 
   height: 120px; 
   background-size: contain; 
   background-position: center; 
   background-repeat: no-repeat; 
}  
.item {
   width: 350px;
   height: 50px;
   margin: 5px 0;
}
.item h2 {
   width: 300px;
   line-height: 50px;
}
p {
   font-size: 18px;
   font-wieght: bold;
}
.gongoTitle{
   white-space: nowrap;
   overflow: hidden;
   text-overflow: ellipsis;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
 <script>
         $(document).ready(function() {
             var currentDate = new Date().getTime();

             $(".job-box").each(function() {
                 var postingDateText = $(this).find(".posting-date").text();

                 // 마감일을 가져오기
                 var closingDate = postingDateText.split("~")[1].trim();
                 
                 // 날짜 형식이 "yyyy-MM-dd"인 경우
                 var closingDateObj = new Date(closingDate);
                 var closingDateTime = closingDateObj.getTime();

                 <!--if (isNaN(closingDateTime)) {
                     console.log("Invalid Closing Date Format:", closingDate);
                     return;  // 날짜 형식이 맞지 않으면 종료
                 }

                 if (closingDateTime < currentDate) {
                     $(this).hide();
                 }-->
             });
         });
    </script>
</head>
<body>
    <h1>채용공고 목록</h1>
    <div id="list">
        <c:forEach var="item" items="${list}" varStatus="loop">
            <div class="job-box">
              <a href="../nc/main?companyIdx=${item.companyIdx}">
                    <c:forEach var="comAccItem" items="${companyAccountList}">
                        <c:if test="${comAccItem.companyIdx eq item.companyIdx}">
                            <div class="logo" style="background-image: url('/web/resources/uploadfile/${comAccItem.companyLogoImg}');"></div>
                        </c:if>
                    </c:forEach>
                   <div class="item">
                       <c:forEach var="comItem" items="${comList}">
                           <c:if test="${comItem.companyIdx eq item.companyIdx}">
                               <h2>${comItem.company_name}</h2>
                           </c:if>
                       </c:forEach>
                   </div>
                   <p class="gongoTitle">${item.jobNotice_title}</p>
                   <p class="posting-date">${item.jobNotice_postingDate}</p>
                   <p class="dday">채용마감&nbsp;&nbsp;&nbsp;D-${Ddaylist[loop.index]}</p>
               </a>
            </div>
        </c:forEach>
    </div>
</body>
</html>
