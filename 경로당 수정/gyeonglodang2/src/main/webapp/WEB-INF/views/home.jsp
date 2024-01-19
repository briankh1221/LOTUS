<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var = "path" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>Home</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="${path}/resources/css/home.css">
<script src="${path}/resources/js/home.js"></script>
<script>
//home.jsp: 운영자 및 채용 담당자 로그인 및 지원자 이동 페이지 *231101 허재영

window.onload = function(){

	// Login 버튼 *231101 허재영
	const submitBtn = $('#submitBtn');
	
	submitBtn.click(function(){	
		let id = $('#id').val();
		let pw = $('#pw').val();	
		
		if(id === null || id === ""){
			alert("아이디를 입력해주세요");
			$('#id').focus();
			return false;
		}else if(pw === null || pw === ""){
			$('#pw').focus();
			alert("비밀번호를 입력해주세요");
			return false;
		}
		
		login(id,pw);
	});
}
/* ID & PW 확인하기 위한 메서드 
 * api/loginAction -> AdminAjaxController 이동함 *231101 권지현
 */
function login(id,pw){
	$.ajax({
		type: 'POST',
		url: 'api/loginAction',
		data: {
			id: id,
			pw: pw
		},
		dataType: 'json',
		success: function(data){
			// map으로 보냈기 때문에 꺼내주기 위한 상수 rs *231101 허재영
			const rs = data['rs'];	
			
			if(rs === '0'){
				alert('아이디와 비밀번호를 확인해주세요');
			} else {
				alert('로그인이 완료되었습니다');
				/* 운영자로 로그인 완료 후 공고 목록 페이지로 이동함 
				 * 채용담당자로 로그인 완료 후 채용공고 운영현황으로 이동함 *231101 권지현
				 */
				if(data.role == 999) {location.href = './nc/jobNotice_operationStatus';}
				
				else {location.href = './nc/jobNotice_board';}
			}
		},
		error: function(xhr, status, error){
			console.log("xhr: " + xhr 
					+ ", status: " + status
					+ ", error: " + error);
		}
	});
}
</script>
</head>
<body>
	<div id="wrap">
		<div class="loginBlock">
			<h2>채용담당자 로그인</h2>
			<div class="inputBlock">
				<table id="inputTable">
					<tr>
						<td><input type="text" name="id" style="padding-left: 10px;" placeholder="아이디를 입력해주세요" id="id"></td>
					</tr>
					<tr>
						<td><input type="password" name="pw" style="padding-left: 10px;" placeholder="비밀번호를 입력해주세요" id="pw"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" id="submitBtn" value="Login" style="width: 280px;"></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="loginBlock">
			<h2>지원자 이동</h2>
			<div class="inputBlock">
				<table id="inputTable"><br><br>		
					<tr>
						<td colspan="2"><a href="./recruit_PassFailRead/recruitMain"><input type="button" id="submitBtn" value="지원자 이동" style="width: 280px;"></a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>