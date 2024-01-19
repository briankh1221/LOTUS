<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<html>
<head>
<meta charset="UTF-8">
<title>recruitheader</title>
<link rel="stylesheet" href="../resources/css/include/main.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>

jQuery(document).ready(function ($) {
    $(".mainmenu>li").mouseover(function () {
        $(this).find(".submenu").stop().slideDown(200);
    }).mouseout(function () {
        $(this).find(".submenu").stop().slideUp(400);
    });
});

function jobNoticeCateg_boardBtn() {   
    window.location.href="../notice/jobNoticeCateg_board2";   
 }
 
function qnaWriteBtn() {   
    window.location.href="../board/qnaWrite";   
 }
 
function qnaSearchBtn() {   
    window.location.href="../board/qnaSearch";   
 }
 
function HomeBtn() {
	
	$.ajax({
		type: 'GET',
        url: '../notice/homePageMove',
        dataType: 'json',
        success: function(data) {
        	
       	window.location.href="../notice/main?companyIdx=" + data;
        	
        	},
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     }); 
}

function companyPageBtn() {
	
	$.ajax({
		type: 'GET',
        url: '../notice/companyPage',
        dataType: 'json',
        success: function(data) {
        	
       	console.log(data['companyUrl']);
        window.open(data['companyUrl'], '_blank'); 
        	
        },
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     }); 
	  
}

function faqListBtn(){
	   window.location.href="../board/faqList";   
}

function companyLogoView() {   	
	
	$.ajax({
		type: 'GET',
        url: '../notice/companyLogoView',
        dataType: 'json',
        success: function(data) {  
			let companyLogo = '';
        	let imagePath = '../resources/uploadfile/' + data['companyLogoImg'];
        	console.log(imagePath);
            $('#companyLogo').css('background-image', 'url(' + imagePath + ')');
        },
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     });			
}
 
</script>
</head>
<body>
	<header>
        <a href="#" onclick="HomeBtn();"><span class="logo" id="companyLogo"></span></a>
        <div class="menu">
            <ul class="mainmenu">
                <li>
                    <a href="#" onclick="jobNoticeCateg_boardBtn();">채용공고</a>
                </li>
                <li>
                    <a href="#">입사지원관리</a>
                    <ul class="submenu">
                        <li><a href="../recruit_resume/main">지원서작성</a></li>
                        <li><a href="../recruit_resume/applicationSearch">지원서수정/확인</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#">채용문의</a>
                    <ul class="submenu">
                        <li><a href="#" onclick="faqListBtn();">채용FAQ</a></li>
                        <li><a href="#" onclick="qnaWriteBtn();">Q&A등록</a></li>
                        <li><a href="#" onclick="qnaSearchBtn();">Q&A조회</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#">전형결과</a>
                    <ul class="submenu">
                        <li><a href="../recruit_PassFailRead/reView">전형결과조회</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" onclick="companyPageBtn();">회사소개</a>
                </li>
            </ul>
        </div>
    </header>
    <div class="mainIMG" id="mainImage"><img src="../resources/image/헤더이미지.jpg" alt="mainIMG"/></div>
</body>
</html>