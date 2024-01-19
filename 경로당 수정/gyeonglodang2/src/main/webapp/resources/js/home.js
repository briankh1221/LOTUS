/**
 * 
 */
 
window.onload = function(){
		
	//Login 버튼 
	const submitBtn = $('#submitBtn');
	
	submitBtn.click(function(){	//Login버튼 눌렀을 때의 메서드
		let id = $('#id').val();	//id값
		let pw = $('#pw').val();	//pw값
		
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
	
function login(id,pw){
	$.ajax({
		type: 'POST',
		url: 'api/loginAction',
		data: {
			id: id,
			pw: pw
		},
		dataType: 'json',
		success: function(rsData){
			//console.log(rsData);
			const rs = rsData['rs'];	//map으로 보냈기 때문에 꺼내주기 위한 상수 rs
			//console.log(rs);
			
			if(rs === '0'){
				alert('아이디와 비밀번호를 확인해주세요');
			} else {
				alert('로그인이 완료되었습니다');
				location.href = 'admin/main';
			}
		},
		error: function(xhr, status, error){
			console.log("xhr: " + xhr 
					+ ", status: " + status
					+ ", error: " + error);
		}
	});
}
	
	