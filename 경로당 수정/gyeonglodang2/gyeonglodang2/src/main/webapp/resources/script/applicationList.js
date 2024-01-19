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

