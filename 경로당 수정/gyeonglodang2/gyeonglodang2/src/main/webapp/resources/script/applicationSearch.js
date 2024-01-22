window.onload = function(){
	const submitBtn = $("#submitBtn");
	submitBtn.click(function(){
		getApplicant();		
	});
	
	$(".mainmenu>li").mouseover(function(){
    //console.log("hover");
    $(this).find(".submenu").stop().slideDown(200);
	}).mouseout(function(){
	    $(this).find(".submenu").stop().slideUp(400);
	});
	
	
}

function getApplicant(){
	let field = $("#field").val();
	let email = $("#email").val();
	let password = $("#password").val();
	$.ajax({
		url: "../applicantapi/applicantAction",
		type: "post",
		data: {
			field: field,
			email: email,
			password: password
		},
		dataType: "json",
		success: function(rsData){
			const rs = rsData['rs'];
			
			if (rs === '0'){
				alert("이메일 주소와 비밀번호를 확인해주세요");
			} else {
				alert("확인 성공! 지원서 확인페이지로 넘어갑니다");
				$('#form').submit();
			}
		},
		error: function(xhr, status, error){
			console.log(
				"xhr: " + xhr 
				+ ", status: " + status
				+ ", error: " + error
			);
		}
		
	});	
	
	
}