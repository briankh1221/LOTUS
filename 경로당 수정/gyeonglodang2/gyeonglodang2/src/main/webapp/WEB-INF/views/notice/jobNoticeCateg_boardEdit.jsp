<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<head>
<style>  	
textarea {
	width: 1150px;
	height: 200px; 	  
}
div#list {
	position: absolute;
	top: 200px;
	left: 370px;
}
#tdList {
	background-color: black;
	color: white;

}
table, th, td {
	border: none;
	margin: 10px;
	padding: 10px;
}
#jobNotice_title {
	width: 1150px;
}
table {
  	border-collapse: collapse;
  	border-bottom: 2px solid black;
}
table tr td:nth-child(2) a {
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
#tdList {
 	text-align: center; 
}
#funcBtn {
	width: 100%;
	margin-left: 1050px;
}
.funcBtn {
	width:100px;
	height: 30px;
}
table, th, td {
    border: none;
    margin: 10px;
    padding: 10px;
}
tr {
   	border-bottom: 1px dotted black;
}
</style>
<script>
// jobNoticeCateg_boardEdit.jsp: 공고 게시물 수정 페이지 *231104 권지현

/* 공고 게시물을 부르기 위한 메서드 *231104 권지현
 * ../notice/jobNoticeCateg_boardEditList -> NcJasonController2 로 이동함 *231104 권지현
 */
function boardEditList() {   
	
	let noticeControl_idx = location.search.split('=')[1];			
	param = {noticeControl_idx: noticeControl_idx};
	
	$.ajax({
		type: 'GET',
        url: '../notice/jobNoticeCateg_boardEditList',
        dataType: 'json',
        data: param,	
        success: function(data) {  
        	// 공고 게시물 제목 *231104 권지현 
        	$('#jobNotice_title').attr('value', data['jobNotice_title']);
        	// 저장 및 수정 할 때 필요함 *231104 권지현
        	$('#noticeControl_idx').attr('value', noticeControl_idx);   
        },
         error: function(xhr, status, error) {	            
        	 console.log(xhr, status, error);
         }
     });			
}
/* 공고 게시물 게시기간, 게시여부, 내용을 부르기 위한 메서드 *231104 권지현
 * ../notice/jobNoticeCateg_contAndOperAndPostDateView -> NcJasonController2 로 이동함 *231104 권지현
 */	
function contAndOperAndPostDateView() {   
	
	let noticeControl_idx = location.search.split('=')[1];			
	param = {noticeControl_idx: noticeControl_idx};
	
	$.ajax({
		type: 'GET',
        url: '../notice/jobNoticeCateg_contAndOperAndPostDateView',   
        dataType: 'json',
        data: param,	
        success: function(data) {   
        		// 공고 게시물 게시여부 *231104 권지현
        		if(data['jobNoticeCateg_operation'] == 'y') {
	        		$('#jobNoticeCateg_operation').attr('checked', true);    
	        	}       	
        		// 공고 게시물 게시기간 *231104 권지현
        		$('#jobNoticeCateg_postingDate').attr('value', data['jobNoticeCateg_postingDate'].split('~')[0]);    
	        	$('#jobNoticeCateg_postingDate2').attr('value', data['jobNoticeCateg_postingDate'].split('~')[1]);
	        	// 공고 게시물 내용 *231104 권지현
        		$('#jobNoticeCateg_content2').append(data['jobNoticeCateg_content']);   
        		
        		buttons();
        },
         error: function(xhr, status, error) {	             
        	 console.log(xhr, status, error);
             
        	 buttons();
         }
     });			
}
/* 공고 게시물 채용분야를 부르기 위한 메서드 *231104 권지현
 * ../notice/jobNoticeCateg_categoryView -> NcJasonController2 로 이동함 *231104 권지현
 */
function categoryView() {    			
	
	let noticeControl_idx = location.search.split('=')[1];			
    param = {noticeControl_idx: noticeControl_idx};
	
	$.ajax({
		type: 'GET',
        url: '../notice/jobNoticeCateg_categoryView',
        dataType: 'json',
        data: param,	
        success: function(data) {  
        	str ='';
        	// 공고 게시물 채용분야 *231104 권지현
        	for(item of data) {
        		str += '<option>' + item['recruitCateg_categ'] + '</option>';
        	}
        	$('#recruitCateg_categ').html(str);
        },
         error: function(xhr, status, error) {	        	 
             console.log(xhr, status, error);
         }
     });			
}
/* 공고 게시물 첨부파일을 위한 메서드 *231104 권지현
 * ../notice/jobNoticeCateg_fileUpload -> NcJasonController2 로 이동함 *231104 권지현
 */	
function fileUpload(){   	
	
	let form = $('#jobNoticeCateg_file_name')[0].files[0];
	let length = $('#jobNoticeCateg_file_name')[0].files.length;
	let formData = new FormData();
			
	if (length > 0) {
	    for (let i = 0; i < length; i++) {
	    	formData.append('files',  $('#jobNoticeCateg_file_name')[0].files[i]);
	    }
	}		
	
	let noticeControl_idx = location.search.split('=')[1];
	// formData에 noticeControl_idx 넣음 *231104 권지현
	formData.append('noticeControl_idx', noticeControl_idx);   

	$.ajax({
	       type: "POST",
	       url: "../notice/jobNoticeCateg_fileUpload",
	       data: formData, 
	       processData: false,
	       contentType: false,
	       cache: false,
	       timeout: 600000,
	       success: function (data) {
	    		// 파일 업로드 리스트 *231104 권지현
			   fileUploadList();   	
	    		
	           alert("첨부 파일 업로드 성공하였습니다.");
	       },
	       error: function (e) {
	           alert("첨부 파일 업로드 실패하였습니다.");
	       }		       
	  });			
}
/* 공고 게시물 이미지파일을 위한 메서드 *231104 권지현
 * ../notice/jobNoticeCateg_imageUpload -> NcJasonController2 로 이동함 *231104 권지현
 */	
function imageUpload(){   
	
	let form = $('#jobNoticeCateg_image_name')[0].files[0];
	let length = $('#jobNoticeCateg_image_name')[0].files.length;
	let formData = new FormData();
	
	if (length > 0) {
	    for (let i = 0; i < length; i++) {
	    	formData.append('files',  $('#jobNoticeCateg_image_name')[0].files[i]);
	    }
	}
	
	let noticeControl_idx = location.search.split('=')[1];
	// formData에 noticeControl_idx 넣음 *231104 권지현
	formData.append('noticeControl_idx', noticeControl_idx);   
	
	$.ajax({
	       type: "POST",
	       enctype: 'multipart/form-data',
	       url: "../notice/jobNoticeCateg_imageUpload",
	       data: formData,
	       processData: false,
	       contentType: false,
	       cache: false,
	       timeout: 600000,
	       success: function (data) {
	           // 이미지 업로드 리스트 *231104 권지현
	    	   imageUploadList();   
	    	   
	    	   alert("이미지 파일 업로드 성공하였습니다.");	           
	       },
	       error: function (e) {
	    	   
	           alert("이미지 파일 업로드 실패하였습니다.");
	       }		       
	  });	
}
/* 파일 업로드 리스트	를 부리기 위한 메서드 *231104 권지현
 * ../notice/jobNoticeCateg_fileUploadList -> NcJasonController2 로 이동함 *231104 권지현
 */	
function fileUploadList() {   
	
	let noticeControl_idx = location.search.split('=')[1];	
	param = {noticeControl_idx: noticeControl_idx};
	
	$.ajax({
		type: 'GET',
        url: '../notice/jobNoticeCateg_fileUploadList',
        dataType: 'json',
        data: param,	
        success: function(data) {
        	let str = '';
        	for(item of data) {
        		str += '<tr><td>' + item['fileName']+ '</td><td>' + item['filePath'] + item['fileName']+'</td><td>' + '<input type="button" onclick="fileDeleteBtn('+ item['fileUpload_idx']+');" value="삭제"></td></tr>';       	
        	}
        	$('#fileUploadList').html(str);
        },
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     });			
}
/* 이미지 업로드 리스트	를 부리기 위한 메서드 *231104 권지현
 * ../notice/jobNoticeCateg_imageUploadList -> NcJasonController2 로 이동함 *231104 권지현
 */		
function imageUploadList() {   
	
	let noticeControl_idx = location.search.split('=')[1];	
	param = {noticeControl_idx: noticeControl_idx};
	
	$.ajax({
		type: 'GET',
        url: '../notice/jobNoticeCateg_imageUploadList',
        dataType: 'json',
        data: param,	
        success: function(data) {        	
        	let imageURL = '';
        	// 이미지 보여줌 *231104 권지현
        	for(item of data) {        	
        		imageURL +=	'<img src="/web/resources/uploadfile/' + item['fileName'] + '" width="500px"/><br>';		        	        	
        	}	        	
        	$('#jobNoticeCateg_content').html(imageURL);
           	
        	let str = '';
        	// 이미지 업로드 리스트 *231104 권지현
        	for(item of data) {   
        		str += '<tr><td>' + item['fileName']+ '</td><td>' + item['filePath'] + item['fileName']+'</td><td>' + '<input type="button" onclick="imageDeleteBtn('+ item['fileUpload_idx']+');" value="삭제"></td></tr>';       	
        	}
        	$('#imageUploadList').html(str);	
        	
        	if(data.length == 0) {
        		$('#imageUploadList').attr('style', 'display:none;');
        	}	        	
        },
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     });			
}
/* 첨부파일 삭제 버튼 *231104 권지현
 * ../notice/jobNoticeCateg_fileDeleteList -> NcJasonController2 로 이동함 *231104 권지현
 */			
function fileDeleteBtn(fileUpload_idx) {   
	
	param = {fileUpload_idx: fileUpload_idx};
	
	$.ajax({
		type: 'GET',
        url: '../notice/jobNoticeCateg_fileDeleteList',
        dataType: 'json',
        data: param,	
        success: function(data) {       		
        	fileUploadList();    
        },
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     });			
}
/* 이미지파일 삭제 버튼 *231104 권지현
 * ../notice/jobNoticeCateg_fileDeleteList -> NcJasonController2 로 이동함 *231104 권지현
 */	
function imageDeleteBtn(fileUpload_idx) {   
	
	 param = {fileUpload_idx: fileUpload_idx};
	
	 $.ajax({
		type: 'GET',
        url: '../notice/jobNoticeCateg_imageDeleteList',
        dataType: 'json',
        data: param,	
        success: function(data) {   
        	imageUploadList();
        },
         error: function(xhr, status, error) {
             console.log(xhr, status, error);
         }
     });			
}
// form 저장 버튼 *231104 권지현	 	
function savingAll() {   
	$('#All').attr('action', '../notice/saveAll');
	if(!nullCheck()) return;
	if(!nullCheck2()) return;
	if(!nullCheck3()) return;
	$('#All').submit();							
}
//form 수정 버튼 *231104 권지현	
function updateAll() {   
	$('#All').attr('action', '../notice/updateAll');
	if(!nullCheck()) return;
	if(!nullCheck2()) return;
	if(!nullCheck3()) return;
	$('#All').submit();	
}
// form 초안이면 저장 버튼으로 보여줌. 그 외 수정 버튼으로 보여 줌 *231104 권지현	
function buttons () {    
	$('#savingAllBtn').hide();
	$('#updateAllBtn').hide();
	const date = document.getElementById("jobNoticeCateg_postingDate");
	if(date.value == '') {
		$('#savingAllBtn').show();
	}
	else {
		$('#updateAllBtn').show();
	}
}
// 미리보기 버튼 *231104 권지현
function preview() {   
	let noticeControl_idx = location.search.split('=')[1];			
	window.location.href="../notice/jobNoticeCateg_boardEdit_preview?noticeControl_idx="+ noticeControl_idx;	
}
// 공고 게시물 빈칸에 모두 입력했는지 확인하기 위한 메서드 *231110 권지현
function nullCheck() {
      let txtEle = $("#All input[type=text]");
      for(i=0; i<txtEle.length; i++) {
         if($(txtEle[i]).val().length == 0) {
            alert('제목을 입력하세요');
            
            return false;
         }
      }
      return true;
}	

function nullCheck2() {
      let txtEle = $("#All input[type=date]");
      for(i=0; i<txtEle.length; i++) {
         if($(txtEle[i]).val().length == 0) {
            alert('게시기간을 선택하세요');
            
            return false;
         }
      }
      return true;
}

function nullCheck3() {
      let txtEle = $("#All textarea");
      for(i=0; i<txtEle.length; i++) {
         if($(txtEle[i]).val().length == 0) {
            alert('내용을 입력하세요');
            
            return false;
         }
      }
      return true;
}

function companyIdxSend() {
		var companyIdx = "${companyIdx}"
		$('#companyIdx').attr('value', companyIdx);
}

window.onload = function () {
	fileUploadList();
	boardEditList();
	imageUploadList();
	contAndOperAndPostDateView();
	categoryView();
	companyIdxSend();	
	getSession();
}
</script>
<meta charset="UTF-8">
<title>jobNotice_boardEdit</title>
</head>
<body>
<div id="list">
	<h2>&#10063;공고 게시물 - (채용 공고)</h2><br>
	<form id="All" action="../notice/savingAll" method ="post">
		<table>
			<tr>
				<th width="200px" id="tdList"><label for="jobNotice_boardDTO.jobNotice_title">제목</label></th>
				<td width="1150px"><input type="text" id="jobNotice_title" name="jobNotice_boardDTO.jobNotice_title" width="1150px"></td>
			</tr>
			<tr>
				<th id="tdList" width="200px"><label for="jobNoticeCateg_boardDTO.jobNoticeCateg_postingDate">게시기간</label></th>
				<td><input id="jobNoticeCateg_postingDate" type="date" name="jobNoticeCateg_boardDTO.jobNoticeCateg_postingDate" /> ~ <input id="jobNoticeCateg_postingDate2" type="date" name="jobNoticeCateg_boardDTO.jobNoticeCateg_postingDate2"/ ></td>
			</tr>
			<tr>
				<th id="tdList" width="200px"><label for="jobNoticeCateg_boardDTO.jobNoticeCateg_operation">게시여부</label></th>
				<td><input id="jobNoticeCateg_operation" type="checkbox" name="jobNoticeCateg_boardDTO.jobNoticeCateg_operation">공고 올리기</td>
			</tr>
			<tr>
				<th rowspan='2'id="tdList"><label for="jobNoticeCateg_file_name" >첨부파일</label></th>
				<td ><input id="jobNoticeCateg_file_name" name="jobNoticeCateg_file_name" type="file" multiple="multiple">
				<input type="button" id="fileUploadBtn" value="등록" onclick="fileUpload();">	
			</tr>		
			<tr>
			<td id="fileUploadList" width="1150px"></td>
			</tr>		
			<tr>
				<th id="tdList"><label for="category">채용분야</label></th>
				<td><select id="recruitCateg_categ" name="recruitCateg_categ"></select></td>
			</tr>
			<tr>
				<th id="tdList"><label for="jobNoticeCateg_boardDTO.jobNoticeCateg_content">내용</label></th>
				<td><div id="jobNoticeCateg_content" contentEditable="true" style="overflow: scroll; width: 1150px; height: 250px; text-align: center;"></div>
				<textarea id="jobNoticeCateg_content2" name="jobNoticeCateg_boardDTO.jobNoticeCateg_content"></textarea></td>
			</tr>
			<tr>
				<th rowspan='2'id="tdList"><label for="jobNoticeCateg_image_name">이미지 파일</label></th>
				<td><input id="jobNoticeCateg_image_name" name="jobNoticeCateg_image_name" type="file" multiple="multiple" />
				<input id="imageUploadBtn" type="button" value="등록" onclick="imageUpload();" width="1150px">
			</tr>
			<tr>
			<td id="imageUploadList" width="1150px"></td>
			</tr>
		</table><br>
	<div id="funcBtn">
		<input type="hidden" style="display: none;" id ="noticeControl_idx" name="noticeControl_idx" />
		<input type="hidden" style="display: none;" id ="companyIdx" name="companyIdx" />
		<input type="button" value="취소" class="funcBtn" onclick="location.href='../notice/jobNoticeCateg_board'"/> 
		<input type="button" id="previewBtn" class="funcBtn" value="미리보기" onclick="preview();"> 
		<input type="button" id="savingAllBtn" class="funcBtn" value="저장" onclick="savingAll();">
		<input type="button" id="updateAllBtn" class="funcBtn" value="수정" onclick="updateAll();">
	</div>
</form>
</div>
</body>
</html>