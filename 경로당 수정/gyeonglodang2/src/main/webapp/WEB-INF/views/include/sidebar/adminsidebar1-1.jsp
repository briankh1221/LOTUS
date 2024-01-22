<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside id="sidebar">
	<div class="menu">
		<ul class="sideMainmenu">
			<li>>>>&nbsp;웹사이트</li>
			<ul class="sideSubmenu">
				<li><a href="../notice/companyAccount">회사정보관리(메인)</a></li>
				<li><a href="../notice/announcement_board">공지사항</a></li>
				<li><a href="../board/ManagerFaqList">FAQ</a></li>
				<li><a href="../board/ManagerQnaList">Q&A</a></li>
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