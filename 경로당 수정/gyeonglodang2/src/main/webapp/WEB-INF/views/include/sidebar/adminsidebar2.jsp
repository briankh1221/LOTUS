<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside id="sidebar">
	<div class="menu">
		<ul class="sideMainmenu">
			<li>>>>&nbsp;채용관리</li>
			<ul class="sideSubmenu">
				<li><a href="../notice/jobNotice_operationStatus">운영현황</a></li>
			</ul>
		</ul>
		<ul class="sideMainmenu">
			<li>>>>&nbsp;업체관리</li>
			<ul class="sideSubmenu">
			<li><a href="../notice/companyAccounts">업체정보</a></li>
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


