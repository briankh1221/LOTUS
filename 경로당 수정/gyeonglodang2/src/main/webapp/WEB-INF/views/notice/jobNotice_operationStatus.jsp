<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header/adminheader2.jsp"%>
<%@include file="../include/sidebar/adminsidebar2.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jobNotice_operationStatus here</title>
</head>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
function operStatusList() {    
	$.ajax({	         
		type: 'GET',
         url: '../notice/operStatusList',
         dataType: 'json',
         success: function(data) {
        	 str = ''; 
        	 for(i=0; i<data.jobNoticeList.length; i++) {
        		str += '<tr><td>' + data.jobNoticeList[i]['noticeControl_idx'] +'</td><td>';
        		for(j=0; j<data.companyinfoList.length; j++) {
					if(data.jobNoticeList[i]['companyIdx'] == data.companyinfoList[j]['companyIdx']) {
	        			str += data.companyinfoList[j]['company_name'];
	        		}      			
        		} 
        		str += '</td><td>' + data.jobNoticeList[i]['jobNotice_title'] + '</td><td>' + data.recruitCategList[i] + '</td><td>';
	       		str += data.jobNoticeList[i]['jobNotice_postingDate'] + '</td><td><a href="../notice/jobNotice_board?companyIdx=' + data.jobNoticeList[i]['companyIdx'] + '"><button>관리</button></a></td></tr>';
        	 }
        	 $('#operStatusList').html(str); 
				  
	        },	
         error: function (xhr, status, error) {
             console.log(xhr, status, error);
         }
     });
 }
	window.onload = function() {
		operStatusList();    		
	 }	
</script>
<style>
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
	
	table tbody tr:hover
	{
		background-color: #f5f5f5;
		
	}
	
	table tbody tr td:last-child a button
	{
		width: 100px;
		height: 30px;	
	}
</style>
<body>
<div id="list">
	<h2>&#10063;채용공고 운영현황 (진행중)</h2><br>
<main>
<table border="1">
<thead>
	<tr>
		<th style="width:200px;">No.</th>
		<th style="width:200px;">업체</th>
		<th style="width:200px;">공고명</th>
		<th style="width:200px;">채용분야수</th>
		<th style="width:200px;">서류전형 접수기간</th>
		<th style="width:200px;">관리</th>
	</tr>
</thead>
<tbody id="operStatusList"></tbody>
</table>
</main>
</div>
</body>
</html>