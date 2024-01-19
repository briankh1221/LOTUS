<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp" %>
<%@include file="../include/sidebar/adminsidebar1-1.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	// companyAccount.jsp: 회사 등록 정보 및 이미지 설정하는 페이지 *231101 고훈

	// 회사 로고 이미지 저장 *231101 고훈
	function companyLogoUpload(){
		let form = $('#"companyLogo"')[0].files[0];
		let length = $('#"companyLogo"')[0].files.length;
		let formData = new FormData();
		
		if (length > 0) {
		    for (let i = 0; i < length; i++) {
		    	formData.append('files',  $('#"companyLogo"')[0].files[i]);
		    }
		}	
		$.ajax({
		       type: "POST",
		       enctype: 'multipart/form-data',
		       url: "../nc/companyAccountCompanyLogoUpload",
		       data: formData,
		       processData: false,
		       contentType: false,
		       cache: false,
		       timeout: 600000,
		       success: function (data) {
		    	   companyLogoShow();
		    	   alert("회사 로그 업로드 성공");   
		       },
		       error: function (e) {
		           alert("회사 로그 업로드 실패");
		       }		       
		  });	
	}

	// 기존 등록 정보 출력 메서드 *231101 고훈
	function getContent() {
		 $.ajax({
		        type: 'GET',
		        url: '../notice/companyAccount_getContent',
		        dataType: 'json',
		        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		        success: function(data) {
		        	$('#name').attr('value', data.name);
		        	$('#contactNumber').attr('value', data.contactNumber);
		        	$('#mail').attr('value', data.mail);
		        	$('#companyUrl').attr('value', data.companyUrl);
		        	$('#companyIdx').attr('value', data.companyIdx);
		        	
		        	if(data.companyLogoImg != "") {
			        	$('#logoImgUpBtn').attr('style', 'display: none;');
						$('#logoImageTag').attr('style', 'padding: 0; width: 300px; height: 100px');
						$('#logoImageTag').attr('src', '/web/resources/uploadfile/'+ data.companyLogoImg);
						$('#companyLogoImg').attr('value', data.companyLogoImg);
		        	}
		        	if(data.companySiteImg != "") {
		        		$('#recruitImgUpBtn').attr('style', 'display: none;');
		    			$('#recruitImageATag').attr('style', 'display: "";');
		    			$('#recruitImageTagImgTag').attr('style', 'padding: 0; width: 300px; height: 100px');
						$('#recruitImageTagImgTag').attr('src', '/web/resources/uploadfile/'+ data.companySiteImg);
						$('#companySiteImg').attr('value', data.companySiteImg);
		        	}
		        	buttons();
		        },
		        error: function(xhr, status, error) {
		            console.log(xhr, status, error);
		            buttons();
				}
		 });
	}

	// 필수 입력 값 null 여부 체크 *231101 고훈
	function nullCheck() {
		let txtEle = $("#formTag input[type=text]");
		for(i=0; i<txtEle.length; i++) {
			if($(txtEle[i]).val().length == 0) {
				alert('모든값 입력하세요');
				
				return false;
			}
		}
		return true;
	}

	// 등록한 정보 저장 *231101 고훈
	function companyAccount_save () {
		$('#formTag').attr('action', '../notice/companyAccount_save')
		if(!nullCheck()) return;
		imgUpload();
	}

	// 변경된 정보 수정 *231101 고훈
	function companyAccount_update () {
		$('#formTag').attr('action', '../notice/companyAccount_update')
		if(!nullCheck()) return;
		imgUpload();
	}

	// 접근 상태에 따른 버튼 설정 *231101 고훈
	function buttons() {
		$('#updateBtn').hide();
		$('#saveBtn').hide();
		if($('#name').val()) {
			$('#updateBtn').show();
		}
		else {
			$('#saveBtn').show();
		}	
	}

	// 현재 선택된 로고 이미지 셋팅 *231101 고훈
	function logoImageView(obj) {
		let fileName = obj.files[0].name;
		if(fileName) {
			$('#logoImgUpBtn').attr('style', 'display: none;');
			$('#logoImageTag').attr('style', 'padding: 0; width: 300px; height: 100px');
			$('#logoImageTag').attr('src', '/web/resources/'+ fileName);
		}
	}

	// 현재 선택된 채용 공고 이미지 셋팅 *231101 고훈
	function recruitImageView(obj) {
		let fileName = obj.files[0].name;
		if(fileName) {
			$('#recruitImgUpBtn').attr('style', 'display: none;');
			$('#recruitImageATag').attr('style', 'display: "";');
			$('#recruitImageTagImgTag').attr('style', 'padding: 0; width: 300px; height: 100px');
			$('#recruitImageTagImgTag').attr('src', '/web/resources/'+ fileName);
		}
	}

	// 선택된 이미지 업로드 *231101 고훈
	function imgUpload(){		
		let logoImg = $('#companyLogo')[0].files[0];
		let recruitImg = $('#register')[0].files[0];
		let formData = new FormData();
				
		if (logoImg !== undefined) {
			formData.append('logoFile', logoImg); 
		}
		if (recruitImg !== undefined) {
			formData.append('regFile', recruitImg); 
		}
		let imageSelect = $("input[name='imageSelect']:checked").val();
		formData.append('imageSelect', imageSelect);
		$.ajax({
			type: "POST",
			enctype: 'multipart/form-data',
		    url: "../notice/companyLogo_imgUpload",
		    data: formData, 
		    processData: false,
		    contentType: false,
		    cache: false,
		    timeout: 600000,
		    success: function (data) {
		    	if(data.companyLogoImg) $('#companyLogoImg').attr('value', data.companyLogoImg);
		    	if(data.companySiteImg) $('#companySiteImg').attr('value', data.companySiteImg);
		    	$('#companyIdx').attr('value', data.companyIdx);
		    	$('#formTag').submit();
		    },
		    error: function (e) {
		    	console.log(e)
		    }		       
		});			
	}
	
	window.onload = function() {
		getContent();
		getSession();
		
	}

</script>
<style>
	label input, #recruitImgUpBtn, #recruitImageTag {
    	display: inline-block;
    	background-color: white; /* 배경색 설정 */
    	color: #001122; /* 텍스트 색상 설정 */
    	padding: 42px 145px; /* 패딩을 통해 버튼 크기 조절 */
    	border: none;
    	cursor: pointer;
    }
	#td_plusBtn, #tr_radio {
        padding: 10px 150px; /* 원하는 패딩 크기로 조절 */
        background-color: #D3D3D3;
	}
    
    #tr_radio td {
    	width: 100px;
        height: 80px; 
        text-align: center;
    	padding: 10px 20px;
     
    }

    p {
    	margin: 3px;
    	font-size: 12px;
    }
    
    hr {
    	margin: 0;	
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
	
	#list th, #list td {
    	text-align: center; 
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
 
<title>company Account</title>
</head>
<body>
<div id="list">
	<h2>&#10063;회사정보관리</h2><br><br>
	<h3>회사 로고</h3><hr>
	<table style="width:80%; text-align:center;">
		<tr>
			<td id="td_plusBtn">
				<label for="companyLogo">
					<input type="button" id="logoImgUpBtn" value="+" onclick="companyLogo.click();" />
					<input type="image" id="logoImageTag" src="" style="display: none; padding: 0;" onclick="companyLogo.click();"/>
				</label>
				<input type="file" id="companyLogo" name="companyLogo" style="display: none;" onchange="logoImageView(this);">
			</td>
			<td>
				<p>로고를 등록할 수 있습니다</p>
				<p>권장 사이즈</p>
				<p>파일 확장자</p>
			</td>
		</tr>
	</table><hr><br><br><br>
	
	<h3>채용 사이트 이미지</h3><hr>
	<form method="post" action="../notice/companyAccount_save" id="formTag">
		<table style="width:80%; text-align:center;">
			<tr id="tr_radio">
				<td>
					<p><input type="radio" name="imageSelect" id="imageSelect" value="1" checked>1안</p><br>
					<p><img src="/web/resources/image/1111.jpg" width="300px" height="100px"></p>
				</td>
		        <td>
					<p><input type="radio" name="imageSelect" id="imageSelect" value="2" >2안</p><br>
					<p><img src="/web/resources/image/cat/cat1.jpg" width="300px" height="100px"></p>
				</td>
		        <td>
					<p><input type="radio" name="imageSelect" id="imageSelect" value="3" >3안</p><br>
					<p><img src="/web/resources/image/cat/cat2.jpg" width="300px" height="100px"></p>
				</td>
		        <td>
		        	<p><input type="radio" name="imageSelect" id="imageSelect" value="reg" >직접등록</p><br>
					<p>
						<input type="button" id="recruitImgUpBtn" value="+" onclick="register.click();"/>
						<a onclick="register.click();" id="recruitImageATag" style="display: none; padding: 0;"> 
							<img id="recruitImageTagImgTag" src="">
						</a>
						<input type="file" id="register" style="display: none;" onchange="recruitImageView(this);">
					</p>
		        </td>
			</tr>
		</table><br><br><br>
	
		<h3 style="margin: 4px;">채용 문의</h3><hr style="background-color: blue; height: 2px;">
	
		<table style="text-align:center;">
			<tr>
				<th style="width:200px">담당자명</th>
				<td style="width:475px"><input type="text" name="name" id="name" style="width:475px"/></td>
				<th style="width:200px">연락처</th>
				<td style="width:475px"><input type="text" name="contactNumber" id="contactNumber" style="width:475px"/></td>
			</tr>
			<tr>
				<th style="width:200px">담당자메일</th>
				<td style="width:475px"><input type="text" name="mail" id="mail" style="width:475px"/></td>
				<th style="width:200px">회사개요URL</th>
				<td style="width:475px"><input type="text" name="companyUrl" id="companyUrl" style="width:475px"/></td>
				<td style="display: none;"><input type="hidden" name="companyLogoImg" id="companyLogoImg" /></td>
				<td style="display: none;"><input type="hidden" name="companySiteImg" id="companySiteImg" /></td>
				<td style="display: none;"><input type="hidden" name="companyIdx" id="companyIdx"/></td>
			</tr>
		</table><br>
		<div id="funcBtn">
		<input type="button" value="수정" id="updateBtn" onclick='companyAccount_update();'/>
		<input type="button" value="저장" id="saveBtn" onclick='companyAccount_save();'/>
		</div>
	</form>
	<br>
</div>
</body>
</html>