<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp" %>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
// jobNotice_board_new.jsp: 공고 신규 등록 페이지 *231110 권지현
/* 기본 정보를 부르기 위한 메서드 
 * ../notice/getConten -> NcJasonController 로 이동함 *231110 권지현
 */
function getContent(num) {
	return new Promise(function(resolve, reject) {
		let param = {noticeControl_idx:num};
	    
		$.ajax({
	        type: 'GET',
	        url: '../notice/getContent',
	        data: param,
	        dataType: 'json',
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        success: function(data) {
	        	// 채용 공고명 *231110 권지현
	        	$('#jobNotice_title').attr('value', data.jobContent.jobNotice_title);
	        	// 운영여부 *231110 권지현
	        	if(data.jobContent.jobNotice_operation == 'n') {
	        		$('#jobNotice_operationY').attr('checked', '');
	        		$('#jobNotice_operationN').attr('checked', 'checked');
	        	}
	        	// 공고게시기간 *231110 권지현
	        	let jobNotice_postingDate = data.jobContent.jobNotice_postingDate;
	        	let jobNotice_postingDateS = jobNotice_postingDate.split('~')[0];
	        	let jobNotice_postingDateE = jobNotice_postingDate.split('~')[1];
	        	$('#jobNotice_postingDateS').attr('value', jobNotice_postingDateS);
	        	$('#jobNotice_postingDateE').attr('value', jobNotice_postingDateE);
	        	
	        	for(i=0; i<data.recContent.length; i++ ) {
	        		// 채용분야 *231110 권지현
	        		$('#recruitCateg_categ_'+i).attr('value', data.recContent[i].recruitCateg_categ);
	        		// 채용인원수 *231110 권지현
	        		$('#recruitCateg_number_'+i).attr('value', data.recContent[i].recruitCateg_number);
	        		// 수험번호코드 *231110 권지현
	        		$('#recruitCateg_testID_'+i).attr('value', data.recContent[i].recruitCateg_testID);
	        		// 기능 *231110 권지현
	        		$('#recruitCateg_idx_'+i).attr('value', data.recContent[i].recruitCateg_idx);
	        		
	        		if(i == data.recContent.length-1) break;
	        		
	        		addRow('n');
	        	}
	        	$('#companyidx').attr('value', data.jobContent.companyidx);
	        	resolve();
	        },
	        error: function(xhr, status, error) {
	            reject(error);
	        }
	    });
	});
}
// 기본정보 빈칸에 모두 입력했는지 확인하기 위한 메서드 *231110 권지현
function nullCheck() {
	let txtEle = $("#formTag tr.displayRow input[type=text]");
	for(i=0; i<txtEle.length; i++) {
		if($(txtEle[i]).val().length == 0) {
			alert('모든값 입력하세요');
			return false;
		}
	}
	txtEle = $("#formTag input[type=text]");
	for(i=0; i<txtEle.length; i++) {
		if($(txtEle[i]).val().length == 0) {
			alert('모든값 입력하세요');
			return false;
		}
	}
	txtEle = $("#formTag input[type=date]");
	for(i=0; i<txtEle.length; i++) {
		if($(txtEle[i]).val().length == 0) {
			alert('공고 게시 기간을 선택하세요');
			return false;
		}
	}
	return true;
}
// 기본 정보 저장하기 위한 메서드 *231110 권지현
function save() {
	$('#formTag').attr('action', '../notice/jobNotice_board_new_save');
	if(!nullCheck()) return;
	$('#formTag').submit(); 
	
}
// 기본 정보 업데이트하기 위한 메서드 *231110 권지현
function update_new() {
	$('#formTag').attr('action', '../notice/jobNotice_board_new_update');
	if(!nullCheck()) return;
	$('#formTag').submit();
}
// 기본 정보 삭제하기 위한 메서드 *231110 권지현
function deleteNotice() {
	if (confirm("정말로 이 공고를 삭제하시겠습니까?")) {
		let noticeControl_idx = location.search.split('=')[1];
		window.location.href = "../notice/jobNotice_board?noticeControl_idx=" + noticeControl_idx;
    } else {
        alert("삭제가 취소되었습니다.");
    }
}
// 행 추가 버튼 *231110 권지현
function addRow(change) {
	// 채용분야 및 채용인원 행 갯수 *231110 권지현
	let disTrCnt = $('tr.displayRow').length;
	let allTrCnt = $('tr.displayRow').length + $('tr.hiddenRow').length;
	
	if(disTrCnt < 5) {
		str = '';
		// 위로 올리기 버튼 *231111 고훈
		str += '<tr class="displayRow"><td><input type="button" value="&#8679;" onclick="javascript:rowUp(this);"/>';
		// 위로 내리기 버튼 *231111 고훈
		str += '<input type="button" value="&#8681;" onclick="javascript:rowDown(this);"/></td>';			
		// 채용분야명 *231111 고훈
		str += '<td><input type="text" placeholder="채용분야명을 선택하세요" id="recruitCateg_categ_' + allTrCnt + '" name="recDtoList[' + allTrCnt + '].recruitCateg_categ"/></td>';
		// 인원 수 *231111 고훈
		str += '<td><input type="text" placeholder="1" id="recruitCateg_number_' + allTrCnt + '" name="recDtoList[' + allTrCnt + '].recruitCateg_number"/></td>';
		// 수험번호 *231111 고훈
		str += '<td><input type="text" placeholder="수험번호코드" id="recruitCateg_testID_' + allTrCnt + '" name="recDtoList[' + allTrCnt + '].recruitCateg_testID"/></td>';
		// idx *231111 고훈
		str += '<td style="display: none;"><input type="hidden" style="display: none;" id = "recruitCateg_idx_' + allTrCnt + '" name="recDtoList[' + allTrCnt + '].recruitCateg_idx"/></td>'										
		// 삭제 버튼 *231111 고훈
		str += '<td calspan="2"><input type="button" value="삭제" onclick="javascript:deleteRow(this);"></td>';
		// 행 변경 상태 *231111 고훈
		str += '<td style="display: none;"><input type="hidden" style="display: none;" id = "recruitCateg_status_change_' + allTrCnt + '" name="recDtoList[' + allTrCnt + '].recruitCateg_status_change" value="' + change + '"/></td>'		
		// 해당 row 화면 표시 여부 *231111 고훈
		str += '<td style="display: none;"><input type="hidden" style="display: none;" id = "recruitCateg_show_' + allTrCnt + '" name="recDtoList[' + allTrCnt + '].recruitCateg_show" value="y"/></td>'		
		
		$('#recruit_table_body').append(str);
	}
	else {
		alert('더이상 추가 할 수 없습니다.');
	}
}
// 행 삭제 버튼 *231110 권지현
function deleteRow(obj) {
	let trCnt = $('tr.displayRow').length;
	let allTrCnt = $('tr.displayRow').length + $('tr.hiddenRow').length;
	
	if(trCnt > 1) {
	    let tr = obj.parentNode.parentNode;
	    tr.style.display = "none";
	    tr.classList.remove("displayRow");
	    tr.classList.add("hiddenRow");
		tr.querySelectorAll('td')[6].querySelector('input').value = 'delete';
		tr.querySelectorAll('td')[7].querySelector('input').value = 'n';
		
		for(let i =0; i < tr.querySelectorAll('input').length; i++) {
			tr.querySelectorAll('input')[i].type = 'hidden';
		}
	}
	else {
	 alert('더이상 삭제할 수 없습니다.');
	}
}
// 행 수정 버튼 *231110 권지현
function update() {
	let trs = document.querySelectorAll('tr.displayRow');
	
	for(tr of trs) {
		for(i=1; i<4; i++) {
			const inputElement = tr.querySelectorAll('td')[i].querySelector('input'); 
			const chage = tr.querySelectorAll('td')[6].querySelector('input');
			
			inputElement.addEventListener('input', function (inputElement) {
				chage.value='update';
			});
		}
	}
}

function rowUp(obj) {
	// 행 요소를 가져옴 *231111 고훈
	let tr1 = obj.parentNode.parentNode;
	// 이전 행 요소를 가져옴 *231111 고훈
	let tr2 = tr1.previousElementSibling; 

	// 첫 번째 행을 두 번째 행 뒤로 이동시킴 *231111 고훈
	if (tr2) {
	    $(tr2).insertAfter(tr1); 
	}	
 }

function rowDown(obj) {
	// 행 요소를 가져옴 *231111 고훈
	let tr1 = obj.parentNode.parentNode;
	// 다음 행 요소를 가져옴 *231111 고훈
	let tr2 = tr1.nextElementSibling; 
	// 첫 번째 행을 두 번째 행 뒤로 이동시킴 *231111 고훈
	if (tr2) {
	    $(tr1).insertAfter(tr2); 
	}
 }

function buttons() {
	let num = location.search.split('=')[1];
	$('#updateBtn').hide();
	$('#submitBtn').hide();
	
	if(num > 0) {
		$('#updateBtn').show();
	}
	else {
		$('#submitBtn').show();
	}
 }

function increaseNumber(string) {
    // 정규 표현식을 사용하여 문자열에서 숫자 부분을 찾음 *231111 고훈
    const match = string.match(/\d+/);
 	// 숫자가 발견되면 1을 더하고, 아니면 1을 리턴함 *231111 고훈
    const allTrCnt = $('tr.displayRow').length + $('tr.hiddenRow').length;
    
    return string.replace(/\d+/, (allTrCnt-1).toString());

}

window.onload = function() {
	buttons();
	addRow('n');
	let num = location.search.split('=')[1];
	
	if(num > 0) {
		$('#noticeControl_idx').attr('value', num);
		getContent(num).then(function() {
			update();
		}).catch(function(error) {
		      console.error(error);
	    });
	}
	getSession();
}

$(function name() {  
	$('#rowAddBtn').on('click', function() {
		addRow('c');
	});
});

   $(document).ready(function() {
       // 오늘 날짜를 가져와서 문자열로 변환 *231111 고훈
       var today = new Date();
       var dd = String(today.getDate()).padStart(2, '0');
   	   // 0부터 시작하므로 +1 *231111 고훈
       var mm = String(today.getMonth() + 1).padStart(2, '0'); 
       var yyyy = today.getFullYear();
       today = yyyy + '-' + mm + '-' + dd;

       // 공고게시기간 input 요소에 오늘 날짜로 설정 *231111 고훈
       $('#jobNotice_postingDateS').attr('value', today);
       $('#jobNotice_postingDateE').attr('value', today);

       // 공고게시기간 input 요소에 change 이벤트를 추가하여 오늘 이전의 날짜를 선택할 경우 경고 메시지 출력 *231111 고훈
       $('#jobNotice_postingDateS, #jobNotice_postingDateE').on('change', function() {
           var selectedStartDate = new Date($('#jobNotice_postingDateS').val());
           var selectedEndDate = new Date($('#jobNotice_postingDateE').val());
           var currentDate = new Date();

           // 시작일이 종료일보다 이후의 날짜인 경우 경고 메시지를 표시하고 다시 오늘 날짜로 설정 *231111 고훈
           if (selectedStartDate > selectedEndDate) {
               alert('종료일은 시작일 이후거나 동일해야 합니다.');
               $('#jobNotice_postingDateE').val(today);
           }
           else if (selectedStartDate < currentDate) {
               $('#jobNotice_postingDateS').val(today);
           }
       });
   });	
</script>
<style>
div#list {
    position: absolute;
    top: 200px;
    left: 370px;
}	
table, th, td {
      border: none;
}    
#tdList {
 	background-color: black;
 	color: white;
 	text-align: center;
}
#funcBtn {
	width: 100%;
	text-align: right;
	margin: 10px 0;
}	
.funcBtn {
	width:100px;
	height: 30px;
}
table {
 	border-collapse: collapse;
 	border-bottom: 2px solid black;
 	
}    
table tr td:last-child input:hover {
	background-color: black;
	color: white;
	font-weight: bold;
}	
tr {
   	border-bottom: 1px dotted black;
}   
table, th, td {
    border: none;
    margin: 10px;
    padding: 10px;
}	
table tr td:last-child input{
 	padding: 7px;
 	border: none;
}
.th1 {
	width: 200px;
}
</style>
<head>
	<title>jobNotice_board_new</title>
</head>
<body>
	<div id="list">
	<h2>&#10063;공고 신규 등록 </h2><br><br>
	<form action="../nc/jobNotice_board_new_update" method="post" id="formTag">
		<h3>기본 정보</h3><hr>
		<table id="formTable">
			<tr>
				<th id="tdList" width="200px">채용 공고명</th>
				<td width="1150px"><input type="text" style="width: 100%;"  id="jobNotice_title" name="jobDto.jobNotice_title"/></td>
			</tr>
			<tr>
				<th id="tdList">운영 여부</th>
				<td>
					<label><input type="radio" name="jobDto.jobNotice_operation" id="jobNotice_operationY" value="y" checked> Y</label>
					<label><input type="radio" name="jobDto.jobNotice_operation" id="jobNotice_operationN" value="n"> N</label>
				</td>
			</tr>
			<tr>
				<th id="tdList">공고 게시 기간</th>
				<td><input type="date" max="2025-12-31" min="2020-01-01" value="2023-10-24" id="jobNotice_postingDateS" name="jobDto.jobNotice_postingDateS" /> ~ 
					<input type="date" max="2025-12-31" min="2020-01-01" value="2023-10-24" id="jobNotice_postingDateE" name="jobDto.jobNotice_postingDateE" />
				</td>
			</tr>
			<tr>
				<th id="tdList">채용분야 및 채용 인원</th>
			<td>
				<table style="text-align: center;" id="recruit_table">
					<tr id="tdList">
						<th class="th1">순서</th><th width="150px">채용분야</th><th width="350px">채용인원수</th><th width="350px">수험번호코드</th><th colspan="4" width="50px">기능</th>
					</tr>
					<tbody id="recruit_table_body">
					<!-- 
						<tr class="displayRow" >
							<td>
								<input type="button" value="&#8679;" onclick='javascript:rowUp(this);'/>
								<input type="button" value="&#8681;" onclick='javascript:rowDown(this);'/>
							</td>
							<td><input type="text" placeholder="채용분야명을 선택하세요" id="recruitCateg_categ_1" name="recDtoList[0].recruitCateg_categ"/></td>
							<td><input type="text" placeholder="1" id="recruitCateg_number_1" name="recDtoList[0].recruitCateg_number"/></td>
							<td><input type="text" placeholder="수험번호코드" id="recruitCateg_testID_1" name="recDtoList[0].recruitCateg_testID"/></td>
							<td><input type="text" name="recruitCateg_status_change" value="n"/></td>
							<td>
								<input type="button" value="복제" onclick='javascript:duplicateRow(this);'/>
								<input type="button" value="삭제" onclick='javascript:deleteRow(this);'/>
							</td>
						</tr>
					 -->
					</tbody>
					<tbody>
						<tr>
							<td colspan="6"><input type="button" value="+ 행 추가" id="rowAddBtn"></td>
						</tr>
					</tbody>
				</table>
			</td>
			</tr>
		</table><br><br><br>
		<h3>기타 정보</h3><hr>
		<table>
			<tr>
				<td>수험번호 설정 예시</td>
				<td>[채용분야코드] + [순번]</td>
	    	</tr>
		</table><br><br>
		<div id="funcBtn">
			<input type="button" class="funcBtn" value="취소" />
			<input type="hidden" id="noticeControl_idx" name="noticeControl_idx"/>
			<input type="button" value="삭제" id="deleteBtn"  class="funcBtn" onclick='javascript:deleteNotice();'/>
			<input type="button" value="저장" id="updateBtn"  class="funcBtn" onclick='javascript:update_new();'/>
			<input type="button" value="저장" id="submitBtn"  class="funcBtn" onclick='javascript:save();'/>
		</div>
	</form>
	</div>
</body>
</html>
