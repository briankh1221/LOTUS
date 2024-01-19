<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header/recruitheader.jsp"%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>recruit_main</title>
<script>

function announcementBoardList() {       
   let companyIdx = location.search.split('=')[1];         
   param = {companyIdx: companyIdx};
   $.ajax({            
      type: 'GET',
         url: '../notice/announcementBoardList',
         dataType: 'json',
         data: param,
         success: function(data) {
            mainImageView();
            str = '';
              for (item of data) {
                 str += "<li class='announcementList'>"
                 str += '<a href="../notice/announcemnet_boardPreview2?companyIdx=' + item['companyIdx'] + '&&announcement_no=' + item['announcement_no'] + '">' + item['announcement_title'] + '</a>'; 
                 str += '<p>' + '작성일자 | ' + item['announcement_postingDate'].split("~")[0] + '</p>';
                 str += "</li>"
              }
              $('#gonggiList').html(str);
           },
            error: function(xhr, status, error) {               
               console.log(xhr, status, error);
            }
        });         
   }
   
function searchList() {   
   let companyIdx = location.search.split('=')[1];         
   param = {companyIdx: companyIdx};
   $.ajax({            
      type: 'GET',
         url: '../notice/jobNotice_boardList2',
         dataType: 'json',
         data: param,
         success: function(data) {
            str = '';
            for(i=0; i<data.boardList.length; i++) {
              str += "<li class='jobNoticeCategList'>"
               str += '<a href="../notice/jobNoticeCateg_boardEdit_preview2?companyIdx=' + data.boardList[i]['companyIdx'] + '&&noticeControl_idx=' + data.boardList[i]['noticeControl_idx'] + '">' + data.boardList[i]['jobNotice_title'] + '</a>'; 
               str += '<p style="display: inline-block;">' + '접수기간 | ' + data.boardList[i]['jobNotice_postingDate'] + '</p>&nbsp;';
               str += '<span style="display: inline-block;" class="status"><a href="#">';
               let operation = '(접수 준비중)';    
              for(j=0; j<data.operationList.length; j++) {
                 if(data.operationList[j]['noticeControl_idx'] == data.boardList[i]['noticeControl_idx']) {  
                    let oper = data.operationList[j]['jobNoticeCateg_operation'];
                     if (oper == 'y') {
                        operation = '(접수 중)';
                         } else if (oper == 'n') {
                            operation = '(접수 마감)';
                         }                    
                 } 
              } 
              str += operation;
               str += '</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="gonggoStatus" style="display: inline-block;"><a href="#">' + '접수하기' + '</a></span>'                
               str += "</li>";
            }
         $('#gonggoList').html(str);         
         },
         error: function (xhr, status, error) {
             console.log(xhr, status, error);
         }
     });
 }
 
function mainImageView() {      
   
   let companyIdx;
   
    if (location.search.split('=')[1].includes('&&')) {
        let companyIdx2 = location.search.split('=')[1];
        companyIdx = companyIdx2.split('&&')[0];
    } else {
        companyIdx = location.search.split('=')[1];
    }
    
    let param = {companyIdx: companyIdx};   
       
   $.ajax({
      type: 'GET',
        url: '../notice/mainImageView',
        dataType: 'json',
        data: param,   
        success: function(data) {  
           let mainImage = '/web/resources/' + data['companySiteImg'];
            $('#mainImage').css('background-image', 'url(' + mainImage + ')');
        },
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     });         
}
 

window.onload = function () {
   companyLogoView();
   announcementBoardList();
   searchList();
}

</script>

<style>
#wrap {
   margin-bottom: 100px;
}

.gonggi {
    width: 600px;
}

.gonggiTop {
    width: 560px;
    height: 50px;
    display: flex;
    flex-direction: row;
    margin-top: 30px;
    background-color: black;
}

.gonggiTop > h3 {
    width: 500px;
    height: 50px;
    line-height: 50px;
    padding-left: 20px;
    color: white;
}

.gonggiTop > input {
    width: 50px;
    height: 50px;
    margin: 0 auto;
    background-color: black;
    border: none;
    font-size: 35px;
    color: white;
}

.gonggiBottom {
    width: 560px;
    height: 500px;
    padding: 20px;
    border: 1px solid black; 
}

.gonggiList > li:first-child {
    height: 70px;
}

.gonggiList > li:first-child > a {
    height: 50px;
    line-height: 50px;
    font-size: 18px;
    font-weight: bold;
}

.gonggo {
    width: 600px;
}

.gonggoTop {
    width: 560px;
    height: 50px;
    display: flex;
    flex-direction: row;
    margin-top: 30px;
    background-color: black;
}

.gonggoTop > h3 {
    width: 500px;
    height: 50px;
    line-height: 50px;
    padding-left: 20px;
    color: white;
}

.gonggoTop > input {
    width: 50px;
    height: 50px;
    margin: 0 auto;
    background-color: black;
    border: none;
    font-size: 35px;
    color: white;
}

.gonggoBottom {
    width: 560px;
    height: 500px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    border: 1px solid black;
    padding: 20px;
}

.gonggoList {
    width: 500px;
}

.gonggoList > li:first-child {
    height: 70px;
}

.gonggoList > li:first-child > a {
    height: 50px;
    line-height: 50px;
    font-size: 18px;
    font-weight: bold;
}


#jobNoticeCateList {
   height: 50px;
}

#gonggiList {
   width: 490px;
   height: 460px;
}

.announcementList {
   width: 490px;
   height: 100px;
}

.announcementList a {
   width: 490px;
   height: 50px;
   line-height: 50px;
   font-size: 20px;
   font-weight: bold;
}


.announcementList p {
   width: 490px;
   height: 50px;
   font-size: 16px;
}

#gonggoList {
   width: 490px;
   height: 460px;
}

.jobNoticeCategList {
   height: 100px;
}

.jobNoticeCategList > a {
   width: 490px;
   height: 50px;
   line-height: 50px;
   font-size: 20px;
   font-weight: bold;
}

.jobNoticeCategList > p {
   width: 450px;
   height: 50px;
}

.jobNoticeCategList > p {
   width: 270px;
   height: 50px;
}


.status a{
   font-weight: bold;
   color: #3876BF;
}

.jobNoticeCategList > span:last-child {
   width: 90px;
   height: 40px;
   text-align: center;
}

.jobNoticeCategList > span:last-child > a{
   width: 90px;
   height: 40px;
   line-height: 30px;
   font-weight: bold;
   padding: 5px;
   text-align: center;
   background-color: #F0F0F0;
   color: black;
}

.jobNoticeCategList > span:last-child > a:hover{
   background-color: black;
   color: white;
   
}


</style>
</head>
<body>
    <div id="wrap">
        <div class="content">
            <div class="gonggi">
                <div class="gonggiTop">
                    <h3>공지사항</h3>
                    <input type="button" value="+">
                </div>
                <div class="gonggiBottom" style="overflow-y: scroll;">
                    <ul id="gonggiList">
                    </ul>
                </div>
            </div>
            <div class="gonggo">
                <div class="gonggoTop">
                    <h3>채용공고</h3>
                    <input type="button" value="+">
                </div>
                <div class="gonggoBottom" style="overflow-y: scroll;">
                    <ul id="gonggoList">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
