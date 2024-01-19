<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>합격발표 설정</title>
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
    	text-align: center;
    	
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
  	
  	#thList {
  		background-color: black;
    	color: white;
  	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
//날짜와 세팅 변경버튼 클릭식 호출 api컨트롤러로 값을 보내서 db에 업데이트
//업데이트 후 url을 리다이렉트
function changeDateSetting(){
	const setting = $("input:radio[name='setting']:checked").val();
	const startdate=$('#startDate').val();
	const enddate=$('#endDate').val();
	const part=$('#get_part').val();
	const param={setting:setting,startdate:startdate,enddate:enddate,part:part};
	$.ajax({
        type: 'POST',
        url: '../api/changeDateSetting',
        dataType: 'json',
        data: param,
        success: function(data) {
        	location.href="../passfail/write?part="+part;
        }, error: function(xhr, status, error) {
        	console.log(xhr, status, error);
        }
	});
};



// 웹이 로딩 됐을때 받아온 세팅값 selected설정
// 마찬가지로 날짜 value도 설정
// 전형 변경시 가져오는 전형값 변경후 url리다이렉트
// 변경하기 버튼 클릭시마다 table display값 변경
$(document).ready(function(){
	
	$('#announcementSetting').hide();
	let part = $('#get_part').val();
	if(part===""){
		part="blank";
	}
	
	$('#part_select').val(part).prop("selected", true);
	let part_selected = $('#part_select').val();
	
	if(part_selected!=="blank"){
		$('#announcementSetting').show();
		const Setting='#'+$('#Setting').val()
		$('#changeSetting').hide();
		$(Setting).prop("checked", true);
		$('#dateSetting').on('click',function(){
			if($('#changeSetting').css('display') == 'none'){
				$('#changeSetting').show();
			}else{
				$('#changeSetting').hide();
			}
		});
		$('#changeDateSetting').on('click',function(){
			changeDateSetting();
		});
	
		$('#changecustom').on('click',function(){
			let check=$('#custom_check').val();
			if(check==="true"){
				location.href='../passfail/customWrite?title=new&part='+part;
			}else{
				location.href="../passfail/customWrite?title=change&part="+part;
			}
		});
	
		$('#customizeData').on('click',function(){
			location.href='../passfail/customizeList?part='+part;
		});
	}
	$('#part_select').on('change',function(){
		const selectedValue = $(this).val();
		$('#hiddenpart').val(selectedValue);
		$('#hideform_sendpart').submit();
	});
	
	getSession();
});
</script>
</head>
<body>
	<div id="list">
	<h2>&#10063;합격발표 설정</h2>
	<!-- 전형선택 -->
	<form name="partform">
	<!-- jquery사용을 위한 데이터 저장공간 -->
	<input type="hidden" name="get_part" id="get_part" value="${part }">
	<input type="hidden" id="Setting" value="${Setting }">
	<input type="hidden" id="custom_check" value="${empty custom_title }">
	<!-- 공고idx를 받아놓은건 사용하지않을예정 -->
	<input type="hidden" name="gonggoidx" id="gonggoidx" value="${gonggoidx }">
	<table border=1>
		<tr>
			<td id="thList" width="200px">전형</td>
			<td width="200px">
				<select name="part_select" id="part_select">
					<option value="blank" selected="selected">-선택-</option>
				<c:forEach items="${part_title_list }" var="dto" varStatus="loop">
					<option value="${dto.part_list_title }" id ="part_option">${dto.part_list_title }</option>
				</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	</form>
	<!-- 선택한 전형의 합격발표 설정 -->
	<form name='announcementSetting' id="announcementSetting">
	<h2>${part } - 설정 내역</h2>
	<table>
		<tr>
			<td id="thList">발표기간 및 오픈설정</td>
			<td>
				${dateSetting}
				<input type="button" name="dateSetting" id="dateSetting" value="변경하기">
				<!-- 변경하기 클릭시 나타나고 사라지는 테이블 -->
				<table id="changeSetting">
					<tr>
						<td><input type="date" name="startDate" id="startDate" value="${startdate }"></td>
						<td><input type="date" name="endDate" id="endDate" value="${enddate }"></td>
						<td>
							open<input type="radio" name="setting" id="open" value="open">
							close<input type="radio" name="setting" id="close" value="close">
						</td>
						<td><input type="button" id="changeDateSetting" value="변경"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td id="thList" width="200px">합격자 발표</td>
			<td width="1150px">
				<table>
					<tr>
						<th width="383">분야</th>
						<th width="383">합격 문구</th>
						<th width="383">불합격 문구</th>
					</tr>
					<!-- 전형에 연결된 분야당 문구를 설정하기 위한 공간 -->
					<c:forEach items="${list }" var="dto" varStatus="loop">
						<tr>
							<td>${dto.fieldidx }</td>
							<td>
							<!-- 합격 혹은 불합격 문구중 선택한 a태그에 따라
							드롭박스에 표시되는 값이 달라지도록 값을 전달 -->
							<c:choose>
								<c:when test="${empty dto.pass_text }">
									<a href='../passfail/passfailWrite?part=${dto.part }&fieldidx=${dto.fieldidx}&pass_fail=pass'>미 등록</a>
								</c:when>
								<c:otherwise>
									<a href='../passfail/passfailWrite?part=${dto.part }&fieldidx=${dto.fieldidx}&pass_fail=pass'>수정</a>
								</c:otherwise>		
							</c:choose>
							</td>
							<td>
							<c:choose>
								<c:when test="${empty dto.fail_text }">
									<a href='../passfail/passfailWrite?part=${dto.part }&fieldidx=${dto.fieldidx}&pass_fail=fail'>미 등록</a>
								</c:when>
								<c:otherwise>
									<a href='../passfail/passfailWrite?part=${dto.part }&fieldidx=${dto.fieldidx}&pass_fail=fail'>수정</a>
								</c:otherwise>		
							</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td id="thList">합격자발표(커스텀)</td>
			<td width="1150px">
				<table>
					<tr style="text-align: center;">
					<c:choose>
						<c:when test="${empty custom_title }">
								<td>등록된 설정 내용이 없습니다<input type="button" id="changecustom" value="등록 및 변경"></td>
						</c:when>
						<c:otherwise>
						<tr>
							<th width="1150px" >제목</th>
						</tr>
						<tr>
							<c:forEach items="${custom_title }" var="dto" varStatus="loop">
							<c:choose>
								<c:when test="${loop.index lt 2}">
									<td>${dto.announcement_custom_title } <input type="button" id="changecustom" value="등록 및 변경"></td>
								</c:when>
								<c:when test="${loop.index eq 2 }">
									<td>--생략--</td>
								</c:when>
							</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td id="thList">사용자 맞춤설정</td>
			<td width="1150px">
				<table>
					
					<c:choose>
					<c:when test="${not empty customize_list }">
						<tr>
							<th width="225px">수험번호</th>
							<th width="225px">성명</th>
							<th width="225px">지원분야</th>
							<th width="225px">column1</th>
							<th width="225px">column2</th>
							<th width="225px">column3</th>
						</tr>
						<c:forEach items="${customize_list }" var="dto" varStatus="loop">
						<tr>
							<c:choose>
							<c:when test="${loop.index lt 2}">
								<td>${dto.scode }</td>
								<td>${dto.name }</td>
								<td>${dto.fieldidx }</td>
								<td>${dto.column1 }</td>
								<td>${dto.column2 }</td>
								<td>${dto.column3 }</td>
							</c:when>
							<c:when test="${loop.index eq 2 }">
								<td colspan="6">-----------------------------생략----------------------------</td>
							</c:when>
							</c:choose>
						</tr>
						</c:forEach>
						<tr>
							<td><input type=button name="customizeData" id="customizeData" value="등록 및 변경"></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td>등록된 설정이 없습니다.</td>
							<td><input type=button name="customizeData" id="customizeData" value="등록 및 변경"></td>
						</tr>
					</c:otherwise>
					</c:choose>
				</table>
			</td>
		</tr>
	</table>
	</form>
	
	<form id="hideform_sendpart" action="../passfail/write" method="post">
		<input type="hidden" name="part" id="hiddenpart">
	</form>
	</div>
</body>
</html>