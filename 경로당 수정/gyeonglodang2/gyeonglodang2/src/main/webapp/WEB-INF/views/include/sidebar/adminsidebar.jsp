<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside id="sidebar">
	<div class="menu">
		<ul class="sideMainmenu">
			<li>>>>&nbsp;공고 현황</li>
			<ul class="sideSubmenu">
				<li><a href="../notice/jobNotice_board">공고 목록</a></li>
				<li><a href="../notice/jobNoticeCateg_board">공고 게시물</a></li>
			</ul>
		</ul>
		<ul class="sideMainmenu">
			<li>>>>&nbsp;공고 관리</li>
			<ul class="sideSubmenu">
				<li><a href="../resume/main">지원서 설정</a></li>
				<li><a href="../resume/resumeFieldSetting">지원서별 채용분야 설정</a></li>
			</ul>
		</ul>
		<ul class="sideMainmenu" id = "part">
			<li>>>>&nbsp;전형 관리</li>
			<ul class="sideSubmenu">
				<li><a href="../manager/part_list">전형 목록</a></li>
				<li><a href="../passfail/write">합격발표 설정</a></li>
				<li><a href="../passfail/successfulcandidateList">합격자 설정</a></li>
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