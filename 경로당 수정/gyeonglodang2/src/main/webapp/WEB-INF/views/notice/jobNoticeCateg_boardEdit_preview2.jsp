<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header/recruitheader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style>
	*
	{
		box-sizing: border-box;	
	}
	
	#list
	{
		width: 1200px;
		margin: 0 auto;
		margin-bottom: 100px;
	}
	
	h2
	{
		width: 1200px;
		height: 100px;
		line-height: 100px;
		text-align: center;
	}
	
	table
	{
		width: 1000px;
		margin: 0 auto;
		border: 1px solid black;
		border-collapse: collapse;
	}
	
	tr:first-child, tr:nth-child(2), tr:nth-child(3)
	{
		height: 60px;
	}
	
	tr:first-child td, tr:nth-child(2) td
	{
		border: 1px dotted black;
	}
	
	
	th
	{
		width: 200px;
		font-size: 17px;
		font-weight: bold;
		background-color: black;
		color: white;
		border-bottom: 1px dotted white;
	}
	
	td 
	{
		width: 780px;
		padding: 10px;
	}
	
	input 
	{
		width: 780px;
		height: 30px;
		border: none;
	}
	
	#fileDownload
	{
		border-radius: 10px;
	}
	
	#fileDownload:hover
	{
		background-color: black;
		color: white;
		font-weight: bold;
	}
	
	tr:nth-child(3) td:first-child
	{
		width: 300px;
	}
	
	
	tr:nth-child(3) td:nth-child(2) 
	{
		width: 150px;
	}
	
	tr:nth-child(3) td:nth-child(2) input
	{
		width:150px;
	}
	
	tr:nth-child(4)
	{
		text-align: center;
	}
	
	
	#fileUploadList
	{
		border-bottom: 1px dotted black;
	}
	
	
	tr:nth-child(5)
	{
		width: 1000px;
	}
	
	tr:nth-child(5) td textarea
	{
		width: 980px;
		height: 300px;
		padding: 10px;
		resize: none;
		border: none;
		overflow: auto;
	}
</style>
<meta charset="UTF-8">
<title>jobNoticeCateg_boardEdit_preview2</title>
</head>
<script>

function titleView() {   
	let noticeControl_idx2 = location.search.split('&&')[1];
	let noticeControl_idx = noticeControl_idx2.split('=')[1];
	param = {noticeControl_idx : noticeControl_idx};
	$.ajax({
		type : 'GET',
		url : '../notice/jobNoticeCateg_titleView',
		dataType : 'json',
		data : param,
		success : function(data) {
			$('#jobNotice_title').attr('value', data['jobNotice_title']);   // 제목	
		},
		error : function(xhr, status, error) {
			console.log(xhr, status, error);
		}
	});
}

function previewList() {   
	let noticeControl_idx2 = location.search.split('&&')[1];
	let noticeControl_idx = noticeControl_idx2.split('=')[1];
	param = {noticeControl_idx : noticeControl_idx};
	$.ajax({
		type : 'GET',
		url : '../notice/jobNoticeCateg_boardEdit_previewList',
		dataType : 'json',
		data : param,
		success : function(data) {
		
			$('#jobNoticeCateg_postingDate').attr('value', data['jobNoticeCateg_postingDate']);   // 접수기간
			$('#jobNoticeCateg_content').val(data['jobNoticeCateg_content']);   // 내용

		},
		error : function(xhr, status, error) {
			console.log(xhr, status, error);
		}
	});
}

function imageView() {   // 이미지 
	let noticeControl_idx2 = location.search.split('&&')[1];
	let noticeControl_idx = noticeControl_idx2.split('=')[1]
	param = {noticeControl_idx: noticeControl_idx};
	$.ajax({
		type: 'GET',
        url: '../notice/jobNoticeCateg_imageUploadList',
        dataType: 'json',
        data: param,	
        success: function(data) {        	
        	let imageURL = '';	        	
        	for(item of data) {        	
        		imageURL +=	'<img src="/web/resources/uploadfile/' + item['fileName'] + '" width="800"/><br>';   // 이미지 리스트		        	        	
        	}	        	
        	$('#fileName').html(imageURL);
        },  	
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     });			
}

function fileUploadList() {   // 첨부 파일 
	let noticeControl_idx2 = location.search.split('&&')[1];
	let noticeControl_idx = noticeControl_idx2.split('=')[1]
	param = {noticeControl_idx: noticeControl_idx};
	$.ajax({
		type: 'GET',
        url: '../notice/jobNoticeCateg_fileUploadList',
        dataType: 'json',
        data: param,	
        success: function(data) {
        	let str = '';
        	for(item of data) {

        		str += '<tr><td>' + item['fileName'] + '</td><td><input id="fileDownload" name="fileDownload" type="button" value="다운로드" onclick="downloadBtn(\'' + item['fileName'] + '\');"></td></tr>';
        	}
        	$('#fileUploadList').html(str);
        },
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     });			
}

function downloadBtn(fileName) {
   
	let param = {fileName: fileName};

    let iframe = document.createElement('iframe');
    iframe.style.display = 'none';
    document.body.appendChild(iframe);

    iframe.src = '../notice/downloadFile?' + $.param(param);
}


window.onload = function() {
	titleView();
	previewList();
	imageView();
	fileUploadList();
	companyLogoView();
}
</script>
<body>
<div id="list">
	<h2 style="text-align: center;">채용공고</h2>
	<br>
	<table>
		<tr>
			<th>공고명</th>
			<td><input id="jobNotice_title" readonly></td>
		</tr>
		<tr>
			<th>접수기간</th>
			<td><input id="jobNoticeCateg_postingDate" readonly>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td id="fileUploadList"></td>
		<tr>
			<td colspan="2"><div id="fileName" style="width: 80%; height: 50%;"></div></td>
		</tr>
		<tr>
    		<td colspan="2">
        	<textarea id="jobNoticeCateg_content" readonly></textarea>
    		</td>
		</tr>
      </table><hr>
	</div>
</body>
</html>