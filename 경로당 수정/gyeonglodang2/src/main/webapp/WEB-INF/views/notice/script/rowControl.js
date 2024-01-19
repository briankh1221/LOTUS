function addRow() {
		let trCnt = $('#recruit_table tr').length - 1;
		
		if(trCnt < 6) {
			str = '';
			str += '<tr><td><input type="button" value="&#8679;" onclick="javascript:rowUp(this);"/>';
			str += '<input type="button" value="&#8681;" onclick="javascript:rowDown(this);"/></td>';
			str += '<td><input type="text" placeholder="채용분야명을 선택하세요"/></td>';
			str += '<td><input type="text" placeholder="1"/></td>';
			str += '<td><input type="text" placeholder="수험번호코드"/></td>';
			str += '<td><input type="button" value="복제" onclick="javascript:duplicateRow(this);"/>';
			str += '<input type="button" value="삭제" onclick="javascript:deleteRow(this);"></td>';
			$('#recruit_table_body').append(str);
		}
		else {
			alert('더이상 추가 할 수 없습니다.');
		}
		
	}
	function deleteRow(obj) {
		let trCnt = $('#recruit_table tr').length - 1;
		
		if(trCnt > 3) {
		    let tr = obj.parentNode.parentNode;
		    tr.parentNode.removeChild(tr);
		}
		else {
		 alert('더이상 삭제할 수 없습니다.');
		}
	 }
	
	function duplicateRow(obj) {
		let trCnt = $('#recruit_table tr').length - 1;
		console.log(trCnt);
		
		if(trCnt < 6) {
		    let tr = obj.parentNode.parentNode; // 원하는 행 요소를 가져옵니다.
		    let trCopy = $(tr).clone(); // 행을 복제합니다.
		    $(tr).after(trCopy);
		}
		else {
		 alert('더이상 추가할 수 없습니다.');
		}
	 }
	
	function rowUp(obj) {
		
		let tr1 = obj.parentNode.parentNode; // 행 요소를 가져옵니다.
		let tr2 = tr1.previousElementSibling; // 이전 행 요소를 가져옵니다.
		console.log(tr1);
		console.log(tr2);

		if (tr2) {
		    $(tr2).insertAfter(tr1); // 첫 번째 행을 두 번째 행 뒤로 이동시킵니다.
		}
	 }
	
	function rowDown(obj) {
		
		let tr1 = obj.parentNode.parentNode; //  행 요소를 가져옵니다.
		let tr2 = tr1.nextElementSibling; // 다음 행 요소를 가져옵니다.

		if (tr2) {
		    $(tr1).insertAfter(tr2); // 첫 번째 행을 두 번째 행 뒤로 이동시킵니다.
		}
	 }

$(function name() {  // 실행시 자동으로 실행
	$('#rowAddBtn').on('click', function() {
		addRow();
	});

});
