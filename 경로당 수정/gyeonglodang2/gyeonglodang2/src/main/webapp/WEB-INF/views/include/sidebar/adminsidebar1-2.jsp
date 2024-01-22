<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside id="sidebar">
	<div class="menu">
		<ul class="sideMainmenu">
			<li>>>>&nbsp;설정</li>
			<ul class="sideSubmenu">
				<li><a href="../notice/administratorAccounts">운영자설정(메인)</a></li>
			</ul>
		</ul>
	</div>
</aside>
<script>
function hidePart(data) {
	if(data.length===0){
		document.getElementById("part").style.display = "none";		
	}else{
		document.getElementById("part").style.display = "block";
	}
}
</script>