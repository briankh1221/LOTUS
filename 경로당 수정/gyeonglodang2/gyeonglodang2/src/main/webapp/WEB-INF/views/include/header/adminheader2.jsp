<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var = "path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>admin-main</title>
	<link rel="stylesheet" href="../resources/css/include/adminheader.css">
	<link rel="stylesheet" href="../resources/css/include/adminsidebar.css">
	<link rel="stylesheet" href="../resources/css/gonggo/gongomain.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="../resources/script/resumemain.js"></script>
</head>
<header>
	<div class="topHeader" style="display: flex;">
		<div class="logo">
			<a href="jobNotice_operationStatus"><img src="../resources/image/gyeonglodang_logo.png"></a> 
		</div>
		<div style="display:inline-block;  margin: 23px 23px;">
			 <label for="jobnoticeList">공고 선택</label>
		    <select id="jobnoticeList" name="jobnoticeList" onchange="select()"></select>
		</div>
	</div>
	<div class="bottomHeader">
	</div>
</header>
<script>
	
	function getSession() {		// 현재 java 단의 session에 있는 noticeControl_idx_selected 값을 받아옴
		 $.ajax({
		        type: 'GET',
		        url: '../notice/getSession',
		        dataType: 'json',
		        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		        success: function(data) {
		        	sessionStorage.setItem('noticeControl_idx_selected', data);		// script의 sessionStorage에 값 올림
		        	categSelectList(data);
		        },
		        error: function(xhr, status, error) {
		            console.log(xhr, status, error);
		        }
		    });
	}
	
	function setSession(noticeControl_idx_selected) {	// 매개변수로 들어온 값을 script의 sessionStorage와 java의 session에 올리는 매서드
		 let param = {noticeControl_idx_selected};
		 sessionStorage.setItem('noticeControl_idx_selected', noticeControl_idx_selected);	// sessionStorage에 올림
		 $.ajax({		// java의 session에 올림											
		        type: 'GET',
		        url: '../notice/setSession',
		        dataType: 'json',
		        data: param,
		        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		        success: function(data) {
		        	location.reload();
		        },
		        error: function(xhr, status, error) {
		            console.log(xhr, status, error);
		        }
		    });
	}

	function categSelectList(noticeControl_idx_selected) {  // 공고 목록을 select 단에 셋팅 session에 있는 항목에 selected 추가
	    $.ajax({
	        type: 'GET',
	        url: '../notice/jobNotice_boardList',	
	        dataType: 'json',
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        success: function(data) {
	        	let selectStr = '';
	        	if(noticeControl_idx_selected == 0) {
		        	for(let i=0; i<data.length; i++) {
		        		if(i == 0) {selectStr += '<option value="' + data[i].noticeControl_idx + '" selected>' + data[i].jobNotice_title + '</option>';}
		        		else {selectStr += '<option value="' + data[i].noticeControl_idx + '">' + data[i].jobNotice_title + '</option>';}
		        	}
		        	// 가져오는 리스트가 빈리스트일때 분기해야함
		        	if(data.length===0){
		        		setSession(1);
		        	}else{
		        		setSession(data[0].noticeControl_idx);
		        	}
	        	}
	        	else {
	        		for(let i=0; i<data.length; i++) {
		        		if(data[i].noticeControl_idx == noticeControl_idx_selected) {
		        			selectStr += '<option value="' + data[i].noticeControl_idx + '" selected>' + data[i].jobNotice_title + '</option>';
		        		}
		        		else {selectStr += '<option value="' + data[i].noticeControl_idx + '">' + data[i].jobNotice_title + '</option>';}
		        	}
	        	}
	        	$('#jobnoticeList').html(selectStr);
	        	hidePart(data);
	        },
	        error: function(xhr, status, error) {
	            console.log(xhr, status, error);
	        }
	    });
	}
	
	function select () {  // 선택된 항목의 noticeControl_idx 를 session에 올리고 해당 idx 값을 selectedValue 로 리턴
		let selectedValue = document.getElementById("jobnoticeList").value;
		setSession(selectedValue);	//session들 에 올리는 매서드
	
      	let selectOptions = document.getElementById('jobnoticeList');
          for (let i = 0; i < selectOptions.options.length; i++) {	// 모든 option의 selected 제거
          	selectOptions.options[i].removeAttribute('selected');
          }

          for (let i = 0; i < selectOptions.options.length; i++) {	// select에 선택된 값을 셋팅
              if (selectOptions.options[i].value == selectedValue) {
              	selectOptions.options[i].selected = true;
                  break;
              }
          }
          
	}

</script>