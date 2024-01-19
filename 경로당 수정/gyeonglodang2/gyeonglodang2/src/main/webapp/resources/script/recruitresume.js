window.onload = function(){
	const submitBtn = document.querySelector('#submitBtn');	//작성하기 버튼 불러오기
	//console.log(submitBtn);
	
	submitBtn.addEventListener('click', function(){	//작성하기 버튼 눌렀을 때 실행되는 메서드
		let pw = $('#pw').val();	
		let pwCheck = $('#pwCheck').val();
		
		if(pw !== pwCheck) {
			alert('비밀번호가 서로 다릅니다. 확인하시고 수정해주세요.');
			$('#pwCheck').focus();
		} else {
			const form = $("#form");
			form.submit();
		}
	});
	
	$(".mainmenu>li").mouseover(function(){
    //console.log("hover");
    $(this).find(".submenu").stop().slideDown(200);
	}).mouseout(function(){
	    $(this).find(".submenu").stop().slideUp(400);
	});
	
	
}

