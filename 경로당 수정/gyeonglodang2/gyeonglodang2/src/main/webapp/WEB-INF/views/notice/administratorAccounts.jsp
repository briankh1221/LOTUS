<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar1-2.jsp" %>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
// administratorAccounts.jsp: 채용 담당자 목록 페이지. 채용 담당자가 여러 담당자 추가 할 수 있음 *231110 권지현

// 신규등록 버튼. 신규등록 버튼을 누르면 입력 테이블이 나타남 *231111 권지현
function joinAdminAccount() {
	const joinAdminTable = document.getElementById("joinAdminTable");
    
    if (joinAdminTable.style.display === "none") {
        joinAdminTable.style.display = "table";
    } else {
        joinAdminTable.style.display = "none";
    }	
}
/* 운영자 목록을 부리기 위한 메서드 
 * ../notice/administratorAccountsList -> NcJasonController2 로 이동함 *231101 권지현 
 */
function administratorAccountsList() {
	 
	 $.ajax({	         
	    type: 'GET',
        url: '../notice/administratorAccountsList',
        dataType: 'json',
        success: function(data) {  
       	str = ''; 
			for(item of data) {
				str +='<tr><td>' + item['idx'] + '</td><td><a href="#" onclick="adminAccountsEditShowOne('+ item['idx'] + ');">'  + item['name'] + '</a></td><td>' + item['id'] + '</td><td>';
				str += item['pw'] + '</td><td>' + item['email'] + '</td><td>' + item['tel'] + '</td><td>' + item['dep'] + '</td></tr>';			
			}
			$('#adminList').html(str);  
        },
         error: function(xhr, status, error) {	            
        	 console.log(xhr, status, error);
         }
	});	
}
/* 운영자 목록 삭제하기 위한 메서드 
 * ../notice/adminAccountDelete -> NcJasonController2 로 이동함 *231101 권지현 
 */	
function adminAccountDelete(idx) {
	
	param = {idx};	
	
	$.ajax({	         
		 type: 'GET',
         url: '../notice/adminAccountDelete',
         dataType: 'json',
         data: param,
         success: function(data) {  
        	 administratorAccountsList();
	        },
	         error: function(xhr, status, error) {	            
	        	 console.log(xhr, status, error);
	         }
	});	
}	
/* 운영자 목록 편집하기 위한 메서드 
 * ../notice/adminAccountsEditShowOne -> NcJasonController2 로 이동함 *231111 권지현 
 */	
function adminAccountsEditShowOne(idx) {
	
	const joinAdminTable = document.getElementById("joinAdminTable");
	
	if (joinAdminTable.style.display === "none") {
        joinAdminTable.style.display = "table";
    }
	
	$('#adminAccountUpdateBtn').show();
	$('#adminAccountRegisterBtn').hide();
	
	param = {idx};			
	
	$.ajax({	         
		 type: 'GET',
         url: '../notice/adminAccountsEditShowOne',
         dataType: 'json',
         data: param,
         success: function(data) {       
			$('#name').attr('value', data['name']);
        	$('#id').attr('value', data['id']);
        	$('#pw').attr('value', data['pw']);
        	 
        	if(data['auth'] == 'y') {
	        	$('#auth').attr('checked', true); 
	        	$('#auth2').attr('checked', false);
	        } else if (data['auth'] == 'n') {
		    	$('#auth2').attr('checked', true);
				$('#auth').attr('checked', false);
			}
        	 
        	 $('#email').attr('value', data['email']);
        	 $('#tel').attr('value', data['tel']);
        	 $('#dep').attr('value', data['dep']);
        	       	 
        	 $('#idx').attr('value', data['idx']);	        	 
             $('#adminAccountDeleteBtn').attr('onclick', 'adminAccountDelete(' + data['idx'] + ');'); 
	        },
	         error: function(xhr, status, error) {	            
	        	 console.log(xhr, status, error);
	         }
	});	
}
// 운영자 정보 신규 등록 버튼 *231111 권지현 	
function adminAccountRegister() {   	
	$('#All').attr('action', '../notice/adminAccountRegister');
	if (!nullCheck())
		return;
	$('#All').submit();	
}	
// 운영자 정보 수정하기 위한 버튼 *231111 권지현 
function adminAccountUpdate() {	         
		$('#All').attr('action', '../notice/adminAccountUpdate');
		if (!nullCheck())
			return;
		$('#All').submit();	
}

function buttons () {   
	$('#adminAccountUpdateBtn').hide();
}
// form에 모두 입력했는지 하기 위한 메서드 *231111 권지현
function nullCheck() {
	let txtEle = $("#All input[type=text]");
	for (i = 0; i < txtEle.length; i++) {
		if ($(txtEle[i]).val().length == 0) {
			alert('모두 입력하세요');
			return false;
		}
	}
	return true;
}
	
window.onload = function() {
	getSession();
	administratorAccountsList();
	buttons();
}
</script>
<style>
div#list {
    position: absolute;
    top: 200px;
    left: 370px;
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
#funcBtn {
	width: 100%;
	text-align: right;
	margin: 10px 0;
}
#funcBtn > input{
	width:100px;
	height: 30px;
}
#list th, #list td {
 	text-align: center; 
 }
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id ="list">
    <h2>&#10063;운영자 목록</h2><br>
    <div id="funcBtn">
    	<input type="button" value="+신규등록" onclick="joinAdminAccount();">
    </div> 
    <main>    	
       	<table>	
       		<thead>
               <tr>
                   <th style="width:193px">No.</th>
                   <th style="width:193px">담당자</th>
                   <th style="width:193px">아이디</th>
                   <th style="width:193px">패스워드</th>
                   <th style="width:193px">이메일</th>
                   <th style="width:193px">연락처</th>
                   <th style="width:193px">소속</th>
               </tr>
           </thead>
           <tbody id="adminList"></tbody>
       	</table>
    </main><br><br><br><br><br><br>
    	<form id="All" action="../notice/adminAccountRegister" method="post">  
			<table id="joinAdminTable" style="display: none;">
				<tr>
					<th style="width:200px"><label for="name">이름</label></th>
					<td><input id="name" name="name" type="text" style="width:475px"></td>
				</tr>
				<tr>
					<th style="width:200px"><label for="id">아이디</label></th>
					<td><input id="id" name="id" type="text" required style="width:475px"></td>
				</tr>	
				<tr>
					<th style="width:200px"><label for="pw">패스워드</label></th>
					<td><input id="pw" name="pw" type="password" required style="width:475px"></td>
				</tr>
				<tr>
					<th style="width:200px"><label for="auth">권한</label></th>
					<td>
						<input id="auth" name="auth" type="radio" value="y">뷰어
						<input id="auth2" name="auth" type="radio" value="n">운영
					</td>
				</tr>
				<tr>
					<th style="width:200px"><label for="email">이메일</label></th>
					<td><input id="email" name="email" type="email" required style="width:475px"></td>
				</tr>
				<tr>
					<th style="width:200px"><label for="tel">연락처</label></th>
					<td><input id="tel" name="tel" type="tel" required style="width:475px"></td>
				</tr>
				<tr>
					<th style="width:200px"><label for="dep">소속</label></th>
					<td><input id="dep" name="dep" type="text" style="width:475px"></td>
				</tr>
				<tr>
	                <td colspan="2">
	                <input id="adminAccountRegisterBtn" type="button" value="등록" onclick="adminAccountRegister();"/>
	                <input id="adminAccountUpdateBtn" type="button" value="수정" onclick="adminAccountUpdate();"/>
	                <input id="adminAccountDeleteBtn" type="button" value="삭제" onclick="adminAccountDelete();"/>
	                <input type="hidden" style="display: none;" id="idx" name="idx" />
	                </td>
	            </tr>					
			</table>
		</form>
</div>
</body>
</html>