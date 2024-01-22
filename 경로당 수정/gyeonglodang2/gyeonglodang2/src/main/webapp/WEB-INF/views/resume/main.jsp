<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
// main.jsp: 지원서 설정 페이지 *231113 권지현
</script>
<body>
	<div id="wrap">
		<div id="content">
			<div id="title">
				<h1>지원서 설정</h1>
			</div>
			<!-- 추후 시작 폼 위치는 여기 *231103 유준호-->
			<!-- <form action="save" method="post" class="form"> *231103 유준호 -->
			<form action="save" method="post" id="form" enctype="multipart/form-data">
				<!-- 인적사항 저장된값 유무 체크 if(empty info_dto) *231103 유준호 -->
				<c:choose>
					<c:when test="${empty info_dto}">
						<div class="infoPlus">
							<div class="inputBlock">
								<span>>>&nbsp;</span><input type="text" value="인적사항" name="infoTitle">
							</div>
							<input type="button" value="+항목추가">
						</div>
						<!-- infoPlus *231103 유준호-->
						<div class="infoResume">
							<div class="explain">
								<textarea class="explaintxarea" placeholder="설명을 입력하세요" name="infoNotice"></textarea>
							</div>
							<!-- explain *231103 유준호-->
							<div class="col">
								<div class="label">
									<label>성명&nbsp;<input type="checkbox" value="1" name="info" checked></label>
								</div>
								<div class="label">
									<label>출생월일&nbsp;<input type="checkbox" value="2" name="info" checked></label>
								</div>
								<div class="label">
									<label>현주소&nbsp;<input type="checkbox" value="3" name="info" checked></label>
								</div>
								<div class="label">
									<label>휴대폰&nbsp;<input type="checkbox" value="4" name="info" checked></label>
								</div>
								<div class="label">
									<label>군필여부&nbsp;<input type="checkbox" value="5" name="info" checked></label>
								</div>
								<div class="label">
									<label>차상위계층&nbsp;<input type="checkbox" value="6" name="info" checked></label>
								</div>
								<div class="label">
									<label>청년여부&nbsp;<input type="checkbox" value="7" name="info" checked></label>
								</div>
								<div class="label">
									<label>장애구분&nbsp;<input type="checkbox" value="8" name="info" checked></label>
								</div>
							</div>
							<!-- col  *231103 유준호-->
							<table class="plusTable">
								<thead>
									<tr>
										<td>no.</td>
										<td style="width: 320px; height: 40px;">항목</td>
										<td style="width: 320px; height: 40px;">안내글 및 선택값</td>
										<td style="width: 258px; height: 40px;">채용분야</td>
										<td style="width: 85px; height: 40px;">필수</td>
										<td style="width: 60px; height: 40px;"></td>
									</tr>
								</thead>
								<tbody>
									<!-- 여기는 저장된 값이 없을시 비어있어야함 *231103 유준호 *231103 유준호-->
								</tbody>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<!-- otherwise *231103 유준호-->
						<!-- 여기 tbody엔 저장된값이 있으니 그 값들을가져와야함 *231103 유준호-->
						<!-- 물려있는 list 존재유무 체크 if(not empty info_edit_list) *231103 유준호-->
						<!-- 인적상항 추가항목 foreach(item=info_edit_list var = info_edit_dto) *231103 유준호-->
						<div class="infoPlus">
							<div class="inputBlock">
								<span>>>&nbsp;</span><input type="text"
									value="${info_dto.infoTitle }" name="infoTitle">
							</div>
							<input type="button" value="+항목추가">
						</div>
						<!-- infoPlus *231103 유준호-->
						<div class="infoResume">
							<div class="explain">
								<textarea class="explaintxarea" name="infoNotice">${info_dto.infoNotice }</textarea>
							</div>
							<!-- explain *231103 유준호-->
							<div class="col">
								<div class="label">
									<c:choose>
										<c:when test="${info_dto.infoName eq '1' }">
											<label>성명&nbsp;<input type="checkbox" value="1" name="info" checked></label>
										</c:when>
										<c:otherwise>
											<label>성명&nbsp;<input type="checkbox" value="1" name="info"></label>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="label">
									<c:choose>
										<c:when test="${info_dto.infoBirth eq '1' }">
											<label>출생월일&nbsp;<input type="checkbox" value="2" name="info" checked></label>
										</c:when>
										<c:otherwise>
											<label>출생월일&nbsp;<input type="checkbox" value="2" name="info"></label>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="label">
									<c:choose>
										<c:when test="${info_dto.infoAddress eq '1' }">
											<label>현주소&nbsp;<input type="checkbox" value="3" name="info" checked></label>
										</c:when>
										<c:otherwise>
											<label>현주소&nbsp;<input type="checkbox" value="3" name="info"></label>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="label">
									<c:choose>
										<c:when test="${info_dto.infoPhone eq '1' }">
											<label>휴대폰&nbsp;<input type="checkbox" value="4" name="info" checked></label>
										</c:when>
										<c:otherwise>
											<label>휴대폰&nbsp;<input type="checkbox" value="4" name="info"></label>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="label">
									<c:choose>
										<c:when test="${info_dto.infoArmy eq '1' }">
											<label>군필여부&nbsp;<input type="checkbox" value="5" name="info" checked></label>
										</c:when>
										<c:otherwise>
											<label>군필여부&nbsp;<input type="checkbox" value="5" name="info"></label>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="label">
									<c:choose>
										<c:when test="${info_dto.infoVulnerable eq '1' }">
											<label>차상위계층&nbsp;<input type="checkbox" value="6" name="info" checked></label>
										</c:when>
										<c:otherwise>
											<label>차상위계층&nbsp;<input type="checkbox" value="6" name="info"></label>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="label">
									<c:choose>
										<c:when test="${info_dto.infoYouth eq '1' }">
											<label>청년여부&nbsp;<input type="checkbox" value="7" name="info" checked></label>
										</c:when>
										<c:otherwise>
											<label>청년여부&nbsp;<input type="checkbox" value="7" name="info"></label>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="label">
									<c:choose>
										<c:when test="${info_dto.infoDisabled eq '1' }">
											<label>장애구분&nbsp;<input type="checkbox" value="8" name="info" checked></label>
										</c:when>
										<c:otherwise>
											<label>장애구분&nbsp;<input type="checkbox" value="8" name="info"></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<!-- col  *231103 유준호-->
							<table class="plusTable">
								<thead>
									<tr>
										<td>no.</td>
										<td style="width: 320px; height: 40px;">항목</td>
										<td style="width: 320px; height: 40px;">안내글 및 선택값</td>
										<td style="width: 258px; height: 40px;">채용분야</td>
										<td style="width: 85px; height: 40px;">필수</td>
										<td style="width: 60px; height: 40px;"></td>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty infoCustom_list }">
										<c:forEach items="${infoCustom_list }" var="dto"
											varStatus="loop">
											<tr>
												<td>${loop.count }</td>
												<td><input type='text' value='${dto.infoCustomCategory }' name='infoCustomCategory'></td>
												<td><input type='text' value="${dto.infoCustomNotice }" name='infoCustomNotice'><input type='hidden' value='0' name='infoCustomNotice'></td>
												<td><select name='infoCustomField' style='width: 150px;'>
														<c:choose>
															<c:when test="${dto.infoCustomField eq '0' }">
																<option value='0' selected="selected">공통</option>
																<option value='1'>채용분야별</option>
															</c:when>
															<c:otherwise>
																<option value='0'>공통</option>
																<option value='1' selected="selected">채용분야별</option>
															</c:otherwise>
														</c:choose>
												</select></td>
												<c:choose>
													<c:when test="${dto.infoCustomPilsu  eq '0' }">
														<td><label>필수&nbsp;<input type='checkbox' value='1' name='infoCustomPilsu' onclick='javascript: pilsu(this);'>
														<input type='hidden' value='0' name='infoCustomPilsu' onclick='javascript: pilsu(this);'></label></td>
													</c:when>
													<c:otherwise>
														<td><label>필수&nbsp;<input type='checkbox' value='1' name='infoCustomPilsu' onclick='javascript: pilsu(this);' checked="checked">
														<input type='hidden' value='0' name='infoCustomPilsu' onclick='javascript: pilsu(this);'></label></td>
													</c:otherwise>
												</c:choose>
												<td id='plusTableDelBtn'><a
													onclick='javascript: decreaseTable(this)'>삭제</a></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
						<!-- infoResume *231103 유준호-->
					</c:otherwise>
				</c:choose>
				<hr>
				<!-- 맞춤형문항 *231103 유준호-->
				<!-- 기타문항 존재유무 if(not empty custom_list) *231103 유준호-->
				<!-- 기타문항 존재할시 존재하는 만큼foreach문 반복 foreach(item=custom_list var=custom_dto) *231103 유준호-->
				<!-- 기타문항에 물려있는 필드값과 추가항목들을 list로가져오면됨 *231103 유준호-->
				<div class="customBlock">
					<c:if test="${not empty resumeCustom_list }">
						<c:forEach items="${resumeCustom_list }" var="dto" varStatus="loop">
							<div class="custom">
								<div class="customheader">
									<div class="inputBlock" style="width: 300px;">
										<span>>>&nbsp;</span><input type="text" value="${dto.customTitle }" name="customTitle" style="width: 250px;">
									</div>
									<!-- inputBlock -->
									<div class="plusBtn">
										<div class="field">
											<select name="customField">
												<c:choose>
													<c:when test="${dto.customField eq '0' }">
														<option value="0" selected="selected">공통</option>
														<option value="1">채용분야별</option>
													</c:when>
													<c:otherwise>
														<option value="0">공통</option>
														<option value="1" selected="selected">채용분야별</option>
													</c:otherwise>
												</c:choose>
											</select>
										</div>
										<input type="button" value="+문항(단문형)추가" class="shortPlusBtn" onclick="addSentenceFunc(this)"> <input type="button" value="+문항(테이블형)추가" class="tablePlusBtn" onclick="javascript: addTableFunc(this)"> 
										<input type="button" value="+맞춤형문항 삭제" class="blockDelBtn" onclick="javascript: decreaseblock(this)">
									</div>
									<!-- plusBtn *231105 유준호-->
								</div>
								<!-- customheader *231105 유준호-->
								<div class="customexplain">
									<textarea class="explaintxarea" name="customNotice">${dto.customNotice }</textarea>
								</div>
								<!-- explain *231105 유준호-->
								<hr>

								<!-- 맞춤형문항(단문형) *231110 유준호-->
								<table class="setencetbl">
									<thead>
										<tr>
											<th>no.</th>
											<th style="width: 400px; height: 40px;">문항</th>
											<th style="width: 400px; height: 40px;">안내글 및 선택값
												설정(구분자:$)</th>
											<th style="width: 130px; height: 40px;">필수</th>
											<th style="width: 90px; height: 40px;">
											<input type='hidden' value='1' name='sentenceQuestion'>
											<input type='hidden' value='1' name='tableTitle'>
											</th>
										</tr>
									</thead>
									<tbody>
										<!-- 기타문항 추가 항목 존재할시 존재하는만큼 foreach문 반복 foreach(item=custom_simple_list var=simple_dto) *231110 유준호-->
										<!-- 어떤 단문형인지 체크하고 그 단문형에 물려있는 list를 가져옴 -->
										<!-- 리스트만큼 foreach문 반복 -->
										<c:if test="${not empty sentenceType_list }">
											<c:forEach items="${sentenceType_list }" var="sDto" varStatus="loop">
												<!-- 물려있는걸  체크 if(custom_dto.idx eq simple_dto.idx) *231110 유준호-->
												<c:if test="${dto.idx eq sDto.resumeCustomIdx }">
													<tr>
														<td>${loop.count }</td>
														<td>
														<input type="text" name="sentenceQuestion" value="${sDto.sentenceQuestion }">
														<input type="hidden" value="0" name="sentenceQuestion">
														</td>
														<td>
														<input type="text" name="sentenceNotice" value="${sDto.sentenceNotice }">
														<input type="hidden" value="0" name="sentenceNotice">
														</td>
														<c:choose>
															<c:when test="${sDto.sentencePilsu eq '0' }">
																<td>필수&nbsp;<input type="checkbox" value="1" name="sentencePilsu"><input type="hidden" value="0" name="sentencePilsu"></td>
															</c:when>
															<c:otherwise>
																<td>필수&nbsp;<input type="checkbox" value="1" name="sentencePilsu" checked="checked">
																<input type="hidden" value="0" name="sentencePilsu"></td>
															</c:otherwise>
														</c:choose>
														<td><a onclick="javascript: decreaseTable2(this)">삭제</a></td>
													</tr>
												</c:if>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<!-- 맞춤형문항(테이블형) *231110 유준호-->
								<hr class="line1">
								<div class="tableBlock">
									<div class="tableType">
								<!-- 어떤 테이블형인지 체크하고 그 테이블형에 물려있는 list를 가져옴 *231110 유준호-->
								<!-- 리스트가 없을시 if(empty tableQuestion_list) *231111 유준호-->
								<!-- 테이블형 문항 존재여부 체크 -->
								<c:if test="${not empty tableType_list }">
									<c:forEach items="${tableType_list }" var="tDto" varStatus="loop">
										<!-- 물려있는걸 체크 -->
										<c:if test="${dto.idx eq tDto.resumeCustomIdx }">
											<input type="hidden" id="minRow" value=${tDto.tableMinRow }>
											<input type="hidden" id="maxRow" value=${tDto.tableMaxRow }>
											<input type="hidden" id="maxCol" value=${tDto.tableMaxCol }>
											<input type="hidden" id="loop" value=${loop.count }>
													<table class="tableSetting">
														<tr><th>질문 또는 가이드 문구&nbsp;<input type="text" name="tableTitle" value="${tDto.tableTitle}">
															<input type='hidden' value='0' name='tableTitle'></th>
							  <c:choose>
                              <c:when test="${tDto.tableMinRow eq '1'}">
                              <th>기본 행 <select name="tableMinRow">
                                    <option value="1" selected="selected">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMinRow eq '2'}">
                              <th>기본 행 <select name="tableMinRow">
                                    <option value="1">1개</option>
                                    <option value="2" selected="selected">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMinRow eq '3'}">
                              <th>기본 행 <select name="tableMinRow">
                                    <option value="1">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3" selected="selected">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMinRow eq '4'}">
                              <th>기본 행 <select name="tableMinRow">
                                    <option value="1">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4" selected="selected">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMinRow eq '5'}">
                              <th>기본 행 <select name="tableMinRow">
                                    <option value="1">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5" selected="selected">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              </c:choose>
                              <c:choose>
                              <c:when test="${tDto.tableMaxRow eq '1'}">
                              <th>기본 행 <select name="tableMaxRow">
                                    <option value="1" selected="selected">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMaxRow eq '2'}">
                              <th>기본 행 <select name="tableMaxRow">
                                    <option value="1">1개</option>
                                    <option value="2" selected="selected">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMaxRow eq '3'}">
                              <th>기본 행 <select name="tableMaxRow">
                                    <option value="1">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3" selected="selected">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMaxRow eq '4'}">
                              <th>기본 행 <select name="tableMaxRow">
                                    <option value="1">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4" selected="selected">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMaxRow eq '5'}">
                              <th>기본 행 <select name="tableMaxRow">
                                    <option value="1">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5" selected="selected">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              </c:choose>
                              <c:choose>
                              <c:when test="${tDto.tableMaxCol eq '1'}">
                              <th>기본 행 <select name="tableMaxCol" onchange="selectTb(this)">
                                    <option value="1" selected="selected">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMaxCol eq '2'}">
                              <th>기본 행 <select name="tableMaxCol" onchange="selectTb(this)">
                                    <option value="1">1개</option>
                                    <option value="2" selected="selected">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMaxCol eq '3'}">
                              <th>기본 행 <select name="tableMaxCol" onchange="selectTb(this)">
                                    <option value="1">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3" selected="selected">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMaxCol eq '4'}">
                              <th>기본 행 <select name="tableMaxCol" onchange="selectTb(this)">
                                    <option value="1">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4" selected="selected">4개</option>
                                    <option value="5">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              <c:when test="${tDto.tableMaxCol eq '5'}">
                              <th>기본 행 <select name="tableMaxCol" onchange="selectTb(this)">
                                    <option value="1">1개</option>
                                    <option value="2">2개</option>
                                    <option value="3">3개</option>
                                    <option value="4">4개</option>
                                    <option value="5" selected="selected">5개</option>
                                 </select>
                              </th>
                              </c:when>
                              </c:choose>
							  <th><a class=".tableDelBtn" onclick="decreaseTable5(this)">삭제</a>
							  <script>test();</script></th></tr>
													</table>
													<!-- tableSetting *231115 유준호-->
													<table class="tableQuestion">
														<!-- 리스트만큼 foreach문 반복 foreach(item=custom_table_list var=table_dto) *231115 유준호-->
														<!-- 물려있는걸 체크 if(custom_dto.idx eq table_dto.idx) *231115 유준호-->
														<tr>
															<!-- tableqlist 존재여부 체크 *231115 유준호-->
															<c:if test="${not empty tableTypeQ_list }">
																<c:forEach items="${tableTypeQ_list }" var="tqDto" varStatus="loop">
																	<!-- tabledto에 물려있는 걸 체크 *231115 유준호-->
																	<c:if test="${tDto.idx eq tqDto.tableTypeIdx }">
																		<td><input type="text" name="tableCategory" value="${tqDto.tableCategory }">
																		<input type="hidden" value="0" name="tableCategory"></td>
																	</c:if>
																</c:forEach>
															</c:if>
														</tr>
														<tr>
															<c:if test="${not empty tableTypeQ_list }">
																<c:forEach items="${tableTypeQ_list }" var="tqDto" varStatus="loop">
																	<c:if test="${tDto.idx eq tqDto.tableTypeIdx }">
																		<td><input type="text" name="tableNotice" value="${tqDto.tableNotice }">
																			<input type="hidden" value="0" name="tableNotice">
																		</td>
																	</c:if>
																</c:forEach>
															</c:if>
														</tr>
													</table>
														</c:if>
									</c:forEach>
								</c:if>
									</div><!-- tableType *231115 유준호-->
								</div><!-- table block *231115 유준호-->
							</div>
							<!-- custom *231117 유준호-->
						</c:forEach>
					</c:if>
				</div>
				<hr class="line">
				<!-- 자기소개서 저장된 값 유무 체크 if(empty personal_dto) *231117 유준호-->
				<c:choose>
					<c:when test="${empty personal_dto }">
						<div class="personalResume">
							<div class="inputHeader">
								<div class="inputBlock">
									<span>>>&nbsp;</span><input type="text" value="자기소개서" name="personalTitle">
								</div>
								<div class="field">
									<select name="personalField">
										<option value="0">공통</option>
										<option value="1">채용분야별</option>
									</select>
								</div>
							</div>
							<div class="explain">
								<textarea class="explaintxarea" placeholder="설명을 입력하세요" name="personalNotice"></textarea>
							</div>
							<!-- explain *231117 유준호-->
						</div>
						<!-- personalResume *231117 유준호-->
						<div class="personalQuestion">
							<table class="personalQuestionTbl">
								<tr>
									<td><input type="text" placeholder="자기소개 질문 내용 작성" name="personalQuestion"></td>
									<td>최소&nbsp;<input type="text" placeholder="글자수" value="0" name="personalMinW"></td>
									<td>최대&nbsp;<input type="text" placeholder="글자수" value="0" name="personalMaxW"></td>
									<td><a onclick="javascript: decreaseTable3(this)">삭제</a></td>
								</tr>
								<tr>
									<td colspan="4"><a onclick="javascript: addPersonalQuestion(this)">+&nbsp;문항 추가</a></td>
								</tr>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<div class="personalResume">
							<div class="inputHeader">
								<div class="inputBlock">
									<span>>>&nbsp;</span><input type="text" value="${personal_dto.personalTitle }" name="personalTitle">
								</div>
								<div class="field">
									<select name="personalField">
										<c:choose>
											<c:when test="${personal_dto.personalField eq '0' }">
												<option value="0" selected="selected">공통</option>
												<option value="1">채용분야별</option>
											</c:when>
											<c:otherwise>
												<option value="0">공통</option>
												<option value="1" selected="selected">채용분야별</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</div>
							<div class="explain">
								<textarea class="explaintxarea" name="personalNotice">${personal_dto.personalNotice }</textarea>
							</div>
							<!-- explain *231118 유준호-->
						</div>
						<!-- personalResume *231118 유준호-->
						<div class="personalQuestion">
							<table class="personalQuestionTbl">
								<c:if test="${not empty personalQ_list }">
									<c:forEach items="${personalQ_list }" var="dto"
										varStatus="loop">
										<tr>
											<td><input type="text" name="personalQuestion" value="${dto.personalQuestion }"></td>
											<td>최소&nbsp;<input type="text" placeholder="글자수" value="${dto.personalMinW }" name="personalMinW"></td>
											<td>최대&nbsp;<input type="text" placeholder="글자수" value="${dto.personalMaxW }" name="personalMaxW"></td>
											<td><a onclick="javascript: decreaseTable3(this)">삭제</a></td>
										</tr>
									</c:forEach>
								</c:if>
								<tr>
									<td colspan="4"><a onclick="javascript: addPersonalQuestion(this)">+&nbsp;문항 추가</a></td>
								</tr>
							</table>
						</div>
					</c:otherwise>
				</c:choose>
				<hr>
				<!-- 경력기술서 저장된 값 유무 체크 if(empty career_dto) *231118 유준호-->
				<c:choose>
					<c:when test="${empty career_dto }">
						<div class="careerResume">
							<div class="inputHeader">
								<div class="inputBlock">
									<span>>>&nbsp;</span><input type="text" value="경력기술서"
										name="careerTitle">
								</div>
								<div class="field">
									<select name="careerField">
										<option value="0">공통</option>
										<option value="1">채용분야별</option>
									</select>
								</div>
							</div>
							<div class="explain">
								<textarea class="explaintxarea" placeholder="설명을 입력하세요" name="careerNotice"></textarea>
							</div>
							<!-- explain *231118 유준호-->
						</div>
						<!-- careerResume *231118 유준호-->
						<div class="careerQuestion">
							<table class="careerQuestionTbl">
								<tr>
									<td><input type="text" placeholder="경력기술 질문 내용 작성" name="careerQuestion"></td>
									<td>최소<input type="text" placeholder="글자수" value="0" name="careerMinW"></td>
									<td>최대<input type="text" placeholder="글자수" value="0" name="careerMaxW"></td>
									<td><a onclick="javascript: decreaseTable3(this)">삭제</a></td>
								</tr>
								<tr>
									<td colspan="4"><a onclick="addCareerQuestionTbl(this)">+&nbsp;문항 추가</a></td>
								</tr>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<div class="careerResume">
							<div class="inputHeader">
								<div class="inputBlock">
									<span>>>&nbsp;</span><input type="text" value="${career_dto.careerTitle }" name="careerTitle">
								</div>
								<div class="field">
									<select name="careerField">
										<c:choose>
											<c:when test="${career_dto.careerField eq '0'}">
												<option value="0" selected="selected">공통</option>
												<option value="1">채용분야별</option>
											</c:when>
											<c:otherwise>
												<option value="0">공통</option>
												<option value="1" selected="selected">채용분야별</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</div>
							<div class="explain">
								<textarea class="explaintxarea" name="careerNotice">${career_dto.careerNotice }</textarea>
							</div>
							<!-- explain *231118 유준호-->
						</div>
						<!-- careerResume *231118 유준호-->
						<div class="careerQuestion">
							<table class="careerQuestionTbl">
								<c:if test="${not empty careerQ_list }">
									<c:forEach items="${careerQ_list }" var="dto" varStatus="loop">
										<tr>
											<td><input type="text" name="careerQuestion" value="${dto.careerQuestion }"></td>
											<td>최소<input type="text" placeholder="글자수" value="${dto.careerMinW }" name="careerMinW"></td>
											<td>최대<input type="text" placeholder="글자수" value="${dto.careerMaxW }" name="careerMaxW"></td>
											<td><a onclick="javascript: decreaseTable3(this)">삭제</a></td>
										</tr>
									</c:forEach>
								</c:if>
								<tr>
									<td colspan="4"><a onclick="addCareerQuestionTbl(this)">+&nbsp;문항 추가</a></td>
								</tr>
							</table>
						</div>
					</c:otherwise>
				</c:choose>
				<hr>
			<c:choose>
	            <c:when test="${empty rsmfile_dto }">
	            <div class="file">
	               <div class="inputBlock">
	                  <span>>>&nbsp;</span><input type="text" value="필수 제출자료 설정" name="rsmfile_title">
	               </div>
	               <div class="explain">
	                  <textarea class="explaintxarea" placeholder="설명을 입력하세요" name="rsmfile_explain"></textarea>
	               </div>
	               <div class="fileSetting">
	                  <table class="fileSettingTbl">
	                     <thead>
	                        <tr>
	                           <th>지원분야</th>
	                           <th>파일명칭</th>
	                           <th>필수안내문구</th>
	                           <th>필수파일</th>
	                        </tr>
	                     </thead>
	                     <tbody>
	                        <tr>
	                           <td><select name="rsmfileupload_field">
	                                 <option value="0">공통</option>
	                                 <option value="1">채용분야별</option>
	                              </select>
	                              <input type="hidden" value="1" name="rsmfileupload_field">
	                              </td>
	                           <td><input type="text" placeholder="파일명칭" name="rsmfileupload_fileName"><input type="hidden" value="1" name="rsmfileupload_fileName">
	                           </td>
	                           <td><input type="text" placeholder="안내문" name="rsmfileupload_pilsu">
	                           <input type='hidden' value='1' name='rsmfileipload_pilsu'>
	                           </td>
	                           <td>
	                           <input type="hidden" value="$" name="filePath">
	                           <input type="hidden" value="$" name="fileName">
	                           <input type="file" name="file">
	                           <a onclick="javascript: decreaseTable4(this)">삭제</a></td>
	                        </tr>
	                        <tr>
	                           <td colspan="4"><a onclick="javascript: addFileSettingTbl(this)">+&nbsp;파일 추가</a></td>
	                        </tr>
	                     </tbody>
	                  </table>
	               </div>
	            </div>
	            </c:when>
            <c:otherwise>
            <div class="file">
               <div class="inputBlock">
                  <span>>>&nbsp;</span><input type="text" value="${rsmfile_dto.rsmfile_title }" name="rsmfile_title">
               </div>
               <div class="explain">
                  <textarea class="explaintxarea" name="rsmfile_explain">${rsmfile_dto.rsmfile_explain }</textarea>
               </div>
               <div class="fileSetting">
                  <table class="fileSettingTbl">
                     <thead>
                        <tr>
                           <th>지원분야</th>
                           <th>파일명칭</th>
                           <th>필수안내문구</th>
                           <th>필수파일</th>
                        </tr>
                     </thead>
                     <tbody>
                     <c:if test="${not empty rsmfileUpload_list }">
                     <c:forEach items="${rsmfileUpload_list }" var="dto" varStatus="loop">
                        <tr>
                           <td>
                           <select name="rsmfileupload_field">
                           <c:choose>
                           <c:when test="${dto.rsmfileupload_field eq '0'}">
                              <option value="0" selected="selected">공통</option>
                              <option value="1">채용분야별</option>
                           </c:when>
                           <c:otherwise>
                              <option value="0">공통</option>
                              <option value="1" selected="selected">채용분야별</option>
                           </c:otherwise>
                           </c:choose>
                           </select>
                           <input type="hidden" value="1" name="rsmfileupload_field">
                           </td>
                           <td>
                           <input type="text" value="${dto.rsmfileupload_fileName }" name="rsmfileupload_fileName">
                           <input type="hidden" value="1" name="rsmfileupload_fileName">
                           </td>
                           <td>
                           <input type="text" value="${dto.rsmfileupload_pilsu }" name="rsmfileupload_pilsu">
                           <input type='hidden' value='1' name='rsmfileupload_pilsu'>
                           </td>
                           <td>
                           <a href="download?filePath=${dto.rsmfileupload_filePath }&OfileName=${dto.rsmfileupload_original }">${dto.rsmfileupload_original }</a>
                           <input type="hidden" value="${dto.rsmfileupload_filePath }" name="filePath"><input type="hidden" value="$" name="filePath">
                           <input type="hidden" value="${dto.rsmfileupload_original }" name="fileName"><input type="hidden" value="$" name="fileName">
                      	   <%--<input type="hidden" value="${dto.rsmfileupload_filePath }" name="filePath${loop.count }">
                           <input type="hidden" value="${dto.rsmfileupload_original }" name="fileName${loop.count }">--%>
                           <input type="file" name="file"><a onclick="javascript: decreaseTable4(this)">삭제</a>
                           </td>
                        </tr>
                     </c:forEach>
                     </c:if>
                        <tr>
                           <td colspan="4"><a onclick="javascript: addFileSettingTbl(this)">+&nbsp;파일 추가</a></td>
                        </tr>
                     </tbody>
                  </table>
               </div>
            </div>
            </c:otherwise>
            </c:choose>
			</form>
			<!-- 추후 최종 폼 위치는 여기 *231118 유준호-->
			<hr id="endLine">
			<div id="button">
				<a href="#">취소</a> <a onclick="javascript: addCustom(this)">맞춤형 문항 추가</a> <a onclick="javascript: resumeSave()">저장></a>
			</div>
		</div>
		<!-- content *231118 유준호-->
	</div>
	<!-- wrap *231118 유준호-->
</body>
</html>