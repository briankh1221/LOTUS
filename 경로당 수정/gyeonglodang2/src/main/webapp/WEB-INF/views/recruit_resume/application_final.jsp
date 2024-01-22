<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>resumePreview.jsp</title>
<link rel="stylesheet" href="../resources/css/application.css">
</head>
<body>
	<%@include file="../include/header/recruitheader.jsp" %>
	<div id="wrap">
		<div id="content">
			<form name="applicant_form" id="applicant_form" enctype="multipart/form-data" method="post">
				<h2>지원서</h2>
				<div class="field">
					<table id="fieldtbl">
						<tr>
							<th>지원분야</th>
							<th>${jobNotice_title }</th>
						</tr>
						<tr>
							<th>수험번호</th>
							<th>${scode }</th>
						</tr>
					</table>
					<!-- fieldtbl -->
				</div>
				<!-- field -->
				<div class="info">
					<h4>인적사항</h4>
					<div class="infonotice">
					<p>${info_dto.infoTitle }</p>
					<p>${info_dto.infoNotice }</p>
					</div>
					<!-- infonotice -->
					<div class="infoinsert">
						<table id="infotbl">
						<c:if test="${not empty info_dto }">
							<c:if test="${info_dto.infoName eq '1' }">
								<tr>
									<th>성명</th>
									<td><input type="text" name="name" value="${infoValueDTO.name }" class="inputForm" readonly="readonly"></td>
								</tr>
							</c:if>
							<c:if test="${info_dto.infoBirth eq '1' }">
								<tr>
									<th>출생월일</th>
									<td>
										<input type="text" name="year" value="${infoValueDTO.birth_year }" class="inputForm" readonly="readonly">년 
										<input type="text" name="month" value="${infoValueDTO.birth_month }" class="inputForm" readonly="readonly">월 
										<input type="text" name="date" value="${infoValueDTO.birth_date }" class="inputForm" readonly="readonly">일
									</td>
								</tr>
							</c:if>
							<c:if test="${info_dto.infoAddress eq '1' }">
								<tr>
									<th>주소</th>
									<td>
										우편번호<input type="text" name="zipcode" value="${infoValueDTO.zipcode }"  class="inputForm" readonly="readonly"> 
										도로명주소<input type="text" name="roadname" value="${infoValueDTO.roadname }" class="inputForm" readonly="readonly"> 
										상세주소<input type="text" name="detailaddress" value="${infoValueDTO.detail_address }" class="inputForm" readonly="readonly">
									</td>
								</tr>
							</c:if>
							<c:if test="${info_dto.infoPhone eq '1' }">
								<tr>
									<th>휴대전화</th>
									<td>
										<input type="text" name="frontnum" value="${infoValueDTO.phone_frontnum }" class="inputForm" readonly="readonly">-
										<input type="text" name="middlenum" value="${infoValueDTO.phone_middlenum }" class="inputForm" readonly="readonly">-
										<input type="text" name="backnum" value="${infoValueDTO.phone_backnum }" class="inputForm" readonly="readonly">
									</td>
								</tr>
								<tr>
									<th>긴급전화</th>
									<td>
										<input type="text" name="em_frontnum" value="${infoValueDTO.emergency_frontnum }" class="inputForm" readonly="readonly">-
										<input type="text" name="em_middlenum" value="${infoValueDTO.emergency_middlenum }" class="inputForm" readonly="readonly">-
										<input type="text" name="em_backnum" value="${infoValueDTO.emergency_backnum }" class="inputForm" readonly="readonly">
									</td>
								</tr>
							</c:if>
							<c:if test="${info_dto.infoVulnerable eq '1' }">
								<tr>
									<th>차상위계층</th>
									<td>
									<c:if test="${not empty infoValueDTO.lowerclass }">
										<c:choose>
										<c:when test="${infoValueDTO.lowerclass eq '1' }">
											<input type="text" name="lowerclass" value="대상" class="inputForm" readonly="readonly">
										</c:when>
										<c:otherwise>
											<input type="text" name="lowerclass" value="비대상" class="inputForm" readonly="readonly">
										</c:otherwise>
										</c:choose>
									</c:if>
									</td>
								</tr>
							</c:if>
							<c:if test="${info_dto.infoDisabled eq '1' }">
								<tr>
									<th>장애구분</th>
									<td>
									<c:if test="${not empty infoValueDTO.disabled }">
										<c:choose>
										<c:when test="${infoValueDTO.disabled eq '1' }">
											<input type="text" name="disabled" value="대상" class="inputForm" readonly="readonly">
										</c:when>
										<c:otherwise>
											<input type="text" name="disabled" value="비대상" class="inputForm" readonly="readonly">
										</c:otherwise>
										</c:choose>
									</c:if>
									</td>
								</tr>
							</c:if>
							<c:if test="${info_dto.infoArmy eq '1' }">
								<tr>
									<th>병역구분</th>
									<td>
									<c:if test="${not empty infoValueDTO.army }">
										<c:choose>
										<c:when test="${infoValueDTO.army eq '1' }">
											<input type="text" name="army" value="비대상" class="inputForm" readonly="readonly">
										</c:when>
										<c:when test="${infoValueDTO.army eq '2' }">
											<input type="text" name="army" value="병역필" class="inputForm" readonly="readonly">
										</c:when>
										<c:when test="${infoValueDTO.army eq '3' }">
											<input type="text" name="army" value="미필" class="inputForm" readonly="readonly">
										</c:when>
										<c:when test="${infoValueDTO.army eq '4' }">
											<input type="text" name="army" value="면제" class="inputForm" readonly="readonly">
										</c:when>
										</c:choose>
									</c:if>
									</td>
								</tr>
							</c:if>
							<c:if test="${info_dto.infoYouth eq '1' }">
								<tr>
									<th>청년여부</th>
									<td>
									<c:if test="${not empty infoValueDTO.youth }">
										<c:choose>
										<c:when test="${infoValueDTO.youth eq '1' }">
											<input type="text" name="youth" value="대상" class="inputForm" readonly="readonly">
										</c:when>
										<c:otherwise>
											<input type="text" name="youth" value="비대상" class="inputForm" readonly="readonly">
										</c:otherwise>
										</c:choose>
									</c:if>
									</td>
								</tr>
							</c:if>
						</c:if>
						<c:if test="${not empty infoCustom_List }">
						<c:set var="countTemp" value="1" ></c:set>
						<c:forEach items="${infoCustom_List }" var="ilist" varStatus="loop">
							<c:forEach items="${infocustom_category_list }" var="category" varStatus="cgloop">
								<c:if test="${loop.count eq cgloop.count }">
									<c:set var="infocustom_category_value" value="${category }"/>
								</c:if>
							</c:forEach>
							<c:forEach items="${infocustom_pilsu_list }" var="pilsu" varStatus="psloop">
								<c:if test="${loop.count eq psloop.count }">
									<c:set var="infocustom_pilsu_value" value="${pilsu }"/>
								</c:if>
							</c:forEach>
							<input type="hidden" name="info_Pilsu${loop.count }" value="${infocustom_pilsu_value }">
								<tr>
								<th>${infocustom_category_value }</th>
									<c:if test="${not empty infocustomValueList }">
										<c:forEach items="${infocustomValueList }" var="icValue" varStatus="icloop">
											<c:if test="${icloop.count eq countTemp }">
												<td><input type="text" name="custom${loop.count }" value="${icValue }" readonly="readonly"></td>
											</c:if>
										</c:forEach>
									<c:set var="countTemp" value="${countTemp+1 }" ></c:set>
									</c:if>
								</tr>
						</c:forEach>
						
						</c:if>
						</table>
					</div>
					<!-- infoinsert -->
				</div>
				<!-- info -->
				<hr>
				<div id="customQuestion">
				<c:set var="countTemp" value="1" ></c:set>
				<c:set var="countTemp_table" value="1" ></c:set>
				<c:if test="${not empty  resumecustom_list}">
				<c:forEach items="${resumecustom_list }" var="dto" varStatus="loop">
					
					<p>${dto.customTitle }</p>
					<h5>${dto.customNotice }</h5>
					<div id="customQuestionnotice">
					</div>
					
					<!-- customQuestionnotice -->
					<c:forEach items="${sentence_type_list_list }" var="sentence_type_list" varStatus="stloop">
						<c:if test="${loop.count eq stloop.count }">
							<c:set var="sentence_type_list" value="${sentence_type_list }"/>
						</c:if>
					</c:forEach>
					<c:forEach items="${sentence_notice_list_list }" var="sentence_notice_list" varStatus="rcloop">
							<c:set var="sentence_notice_list" value="${sentence_notice_list_list[loop.index] }"/>
						<c:if test="${not empty sentence_notice_list}">
						<c:forEach items="${sentence_notice_list }" var="slist" varStatus="mloop">
							<c:if test="${mloop.count eq rcloop.count }">
								<c:forEach items="${sentence_type_list }" var="title" varStatus="titleloop">
									<c:if test="${titleloop eq scloop }">
										<p>${title.sentenceQuestion }</p>
									</c:if>
								</c:forEach>
								<c:forEach items="${sentence_check_list_list }" var="sentence_check_list" varStatus="scloop">
									<c:set var="sentence_check_list" value="${sentence_check_list }"/>
								</c:forEach>
								<div>
									<c:forEach items="${sentenceValueList }" var="svValue" varStatus="svloop">
										<c:if test="${svloop.count eq  countTemp}">
											<input type="text" name="sentenceNotice${loop.count }-${mloop.count }" value="${svValue }" readonly="readonly">
										</c:if>
									</c:forEach>
								<c:set var="countTemp" value="${countTemp+1 }" ></c:set>
								</div>
							</c:if>
						</c:forEach>
						</c:if>
					</c:forEach>
					<c:if test="${not empty tabletype_list_list}">
					<c:forEach items="${tabletype_list_list }" var="tabletype_list" varStatus="tloop">
						<c:set var="tabletype_list" value="${tabletype_list_list[loop.index] }"/>
						<c:forEach items="${tabletype_list}" var="tDto" varStatus="tableloop">
							<c:if test="${tableloop.count eq tloop.count }">
								<div id="tableType">
									<p>${tDto.tabletitle }</p>
									<div id="tableTypeQuestion">
										<table id="table">
											<thead>
												<tr>
													<c:if test="${not empty tableTypeQ_list}">
													<c:forEach items="${tableTypeQ_list }" var="tqDto" varStatus="tqloop">
														<c:if test="${tqDto.tableTypeidx eq tDto.idx }">
															<th>${tqDto.tableCategory }</th>
														</c:if>
													</c:forEach>
													</c:if>
												</tr>
											</thead>
											<tbody>
											<c:forEach var = "i" begin="1" end="${tDto.tableMinRow }" step="1">
												<tr>
													<c:if test="${not empty tableTypeQ_list }">
													<c:forEach items="${tableTypeQ_list }" var="tqDto" varStatus="tqloop">
														<c:if test="${tqDto.tableTypeidx eq tDto.idx }">
															<c:forEach items="${tableValueList }" var="tableValue" varStatus="tableloop">
															<c:if test="${tableloop.count eq countTemp_table }">
																<td><input type="text" name="tableText${loop.count }-${tableloop.count }-${tqloop.count}"
																	 value="${tableValue }" readonly="readonly"></td>
															</c:if>
															</c:forEach>
														<c:set var="countTemp_table" value="${countTemp_table+1 }" ></c:set>
														</c:if>
													</c:forEach>
													</c:if>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
								<!-- tableTypeQuestion -->
								</div>
							</c:if>
						</c:forEach>
					</c:forEach>
					</c:if>
					<!-- tableType -->
				</c:forEach>
				</c:if>
				</div>
				<!-- customQuestion -->
				<div id="personal">
				<c:if test="${not empty personalList }">
				<c:set var="countTemp" value="1" ></c:set>
				<c:forEach items="${personalList }" var="pDto" varStatus="ploop">
					<h4>${pDto.personalTitle }</h4>
					<h5>${pDto.personalNotice }</h5>
					<div id="personalnotice">
					</div>
					<!-- personalnotice -->
					<div id="personalQuesiton">
						<c:forEach items="${personalQList }" var="pqDto" varStatus="pqloop">
						<c:if test="${pqDto.personalidx eq pDto.idx }">
							<table id="personalQuestiontbl">
								<thead>
									<tr>
										<th>${pqDto.personalQuestion }</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:forEach items="${personalValueList }" var="personalValue" varStatus="personalloop">
										<c:if test="${personalloop.count eq countTemp }">
											<td><input type="text" maxlength="${pqDto.personalMaxW }" name="personalValue${ploop.count }${pqloop.count}" value="${personalValue}" readonly="readonly"></td>
										</c:if>
										</c:forEach>
									<c:set var="countTemp" value="${countTemp+1 }" ></c:set>
									</tr>
								</tbody>
							</table>
						</c:if>
						</c:forEach>
					</div>
					<!-- personalQuestion -->
				</c:forEach>
				</c:if>
				</div>
				<!-- personal -->
				<div id="career">
					<c:if test="${not empty careerList }">
					<c:set var="countTemp" value="1" ></c:set>
					<c:forEach items="${careerList }" var="cDto" varStatus="careerloop">
						<h4>${cDto.careerTitle }</h4>
						<h5>${cDto.careerNotice }</h5>
						<div id="careernotice">
						</div>
						<!-- careernotice -->
						<div class="careerQuesiton">
							<c:forEach items="${careerQList }" var="cqDto" varStatus="careerqloop">
							<c:if test="${cqDto.careeridx eq cDto.idx }">
								<table class="careerQuestiontbl">
									<thead>
										<tr>
											<th>${cqDto.careerQuestion }</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<c:forEach items="${careerValueList }" var="careerValue" varStatus="cvloop">
											<c:if test="${cvloop.count eq countTemp }">
												<td><input type="text" maxlength="${cqDto.careerMaxW }" name="careerValue${careerloop.count }${careerqloop.count}" value="${careerValue }" readonly="readonly"></td>
											</c:if>
											</c:forEach>
										<c:set var="countTemp" value="${countTemp+1 }" ></c:set>
										</tr>
									</tbody>
								</table>
							</c:if>
							</c:forEach>
						</div>
					</c:forEach>
					</c:if>
				</div>
				<!-- career -->
				<c:if test="${not empty resumefileDTO }">
				<div id =file>
				<h4>${resumefileDTO.rsmfile_title }</h4>
				<h5>${resumefileDTO.rsmfile_explain }</h5>
					<div id="filenotice">
					</div>
					<!-- filenotice -->
					<c:if test="${not empty resumefile_list }">
						<c:forEach items="${resumefile_list }" var="rfDto" varStatus="rfloop">
							<div class="filename">
								<span>${rfDto.rsmfileupload_fileName }</span>
							</div><!-- filename -->
							<div class="filedownload">
							</div><!-- filedownload -->
							<div class="fileupload">
								<c:forEach items="${fileValueList }" var="fvDto" varStatus="fvloop">
								<c:if test="${fvloop.count eq rfloop.count }">
									<span><a href="../resume/download?filePath=${fvDto.applicant_file_filePath }&OfileName=${fvDto.applicant_file_Original }">${fvDto.applicant_file_Original }</a></span>
								</c:if>
								</c:forEach>
							</div><!-- fileupload -->
						</c:forEach>
					</c:if>
				</div><!-- file -->
				</c:if>
				<div id="buttons">
					<input type="button" value="돌아가기">
				<!--  
					<input type="button" id="submit" value="저장하기">
					<input type="button" id="final_submit" value="제출하기">-->
				</div>
				
				<input type="hidden" value=${recruitCateg_categ } name="recruitCateg_categ">
			</form>
			
		</div>
	</div>
	<!-- wrap -->
</body>
</html>