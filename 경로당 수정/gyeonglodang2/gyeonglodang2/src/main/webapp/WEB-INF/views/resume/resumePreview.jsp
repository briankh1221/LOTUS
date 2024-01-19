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
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
	function checkMinLength(text,minlength){
	};
</script>
</head>
<%@include file="../include/header/gonggoheader.jsp"%>
<%@include file="../include/sidebar/adminsidebar.jsp"%>
<body>
	<div id="wrap">
		<div id="content">
			<form action="preview" id="form" method="post">
				<select id="fieldSelect" name="recruitCateg_categ">
					<c:forEach items="${categList }" var="cdto">
						
						<option value="${cdto.recruitCateg_categ }" <c:if test="${cdto.recruitCateg_categ eq recruitCateg_categ}" >selected</c:if>>
						${cdto.recruitCateg_categ }
						</option>
						
					</c:forEach>
				</select>
			</form>
				<h2>지원서</h2>
				<div class="field">
					<table id="fieldtbl">
						<tr>
							<th>지원분야</th>
							<th>${recruitCateg_categ }</th>
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
									<td><input type="text" name="name" value="${infoValueDTO.name }" class="inputForm"></td>
								</tr>
							</c:if>
							<c:if test="${info_dto.infoBirth eq '1' }">
								<tr>
									<th>출생월일</th>
									<td>
										<input type="text" name="year" value="${infoValueDTO.birth_year }" class="inputForm">년 
										<input type="text" name="month" value="${infoValueDTO.birth_month }" class="inputForm">월 
										<input type="text" name="date" value="${infoValueDTO.birth_date }" class="inputForm">일
									</td>
								</tr>
							</c:if>
							<c:if test="${info_dto.infoAddress eq '1' }">
								<tr>
									<th>주소</th>
									<td>
										우편번호<input type="text" name="zipcode" value="${infoValueDTO.zipcode }"  class="inputForm"> 
										도로명주소<input type="text" name="roadname" value="${infoValueDTO.roadname }" class="inputForm"> 
										상세주소<input type="text" name="detailaddress" value="${infoValueDTO.detail_address }" class="inputForm">
									</td>
								</tr>
							</c:if>
							<c:if test="${info_dto.infoPhone eq '1' }">
								<tr>
									<th>휴대전화</th>
									<td>
										<input type="text" name="frontnum" value="${infoValueDTO.phone_frontnum }" class="inputForm">-
										<input type="text" name="middlenum" value="${infoValueDTO.phone_middlenum }" class="inputForm">-
										<input type="text" name="backnum" value="${infoValueDTO.phone_backnum }" class="inputForm">
									</td>
								</tr>
								<tr>
									<th>긴급전화</th>
									<td>
										<input type="text" name="em_frontnum" value="${infoValueDTO.emergency_frontnum }" class="inputForm">-
										<input type="text" name="em_middlenum" value="${infoValueDTO.emergency_middlenum }" class="inputForm">-
										<input type="text" name="em_backnum" value="${infoValueDTO.emergency_backnum }" class="inputForm">
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
											<label><input type="radio" name="lowerclass" value="0" >비대상</label>
											<label><input type="radio" name="lowerclass" value="1" checked="checked" >대상</label>
										</c:when>
										<c:otherwise>
											<label><input type="radio" name="lowerclass" value="0" checked="checked" >비대상</label>
											<label><input type="radio" name="lowerclass" value="1" >대상</label>
										</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${empty infoValueDTO.lowerclass }">
										<label><input type="radio" name="lowerclass" value="0" >비대상</label>
										<label><input type="radio" name="lowerclass" value="1" >대상</label>
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
											<label><input type="radio" name="disabled" value="0" >비대상</label>
											<label><input type="radio" name="disabled" value="1" checked="checked"  >대상</label>
										</c:when>
										<c:otherwise>
											<label><input type="radio" name="disabled" value="0" checked="checked" >비대상</label>
											<label><input type="radio" name="disabled" value="1" >대상</label>
										</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${empty infoValueDTO.disabled }">
										<label><input type="radio" name="disabled" value="0" >비대상</label>
										<label><input type="radio" name="disabled" value="1"  >대상</label>
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
											<label><input type="radio" name="army" value="1" checked="checked">비대상</label>
											<label><input type="radio" name="army" value="2">병역필</label> 
											<label><input type="radio" name="army" value="3">미필</label>
											<label><input type="radio" name="army" value="4">면제</label>
										</c:when>
										<c:when test="${infoValueDTO.army eq '2' }">
											<label><input type="radio" name="army" value="1">비대상</label>
											<label><input type="radio" name="army" value="2" checked="checked">병역필</label> 
											<label><input type="radio" name="army" value="3">미필</label>
											<label><input type="radio" name="army" value="4">면제</label>
										</c:when>
										<c:when test="${infoValueDTO.army eq '3' }">
											<label><input type="radio" name="army" value="1">비대상</label>
											<label><input type="radio" name="army" value="2">병역필</label> 
											<label><input type="radio" name="army" value="3" checked="checked">미필</label>
											<label><input type="radio" name="army" value="4">면제</label>
										</c:when>
										<c:when test="${infoValueDTO.army eq '4' }">
											<label><input type="radio" name="army" value="1">비대상</label>
											<label><input type="radio" name="army" value="2">병역필</label> 
											<label><input type="radio" name="army" value="3">미필</label>
											<label><input type="radio" name="army" value="4" checked="checked">면제</label>
										</c:when>
										</c:choose>
									</c:if>
									<c:if test="${empty infoValueDTO.army }">
										<label><input type="radio" name="army" value="1" >비대상</label>
										<label><input type="radio" name="army" value="2">병역필</label> 
										<label><input type="radio" name="army" value="3">미필</label>
										<label><input type="radio" name="army" value="4">면제</label>
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
											<label><input type="radio" name="youth" value="0">비대상</label>
											<label><input type="radio" name="youth" value="1" checked="checked">대상</label>
										</c:when>
										<c:otherwise>
											<label><input type="radio" name="youth" value="0" checked="checked">비대상</label>
											<label><input type="radio" name="youth" value="1">대상</label>
										</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${empty infoValueDTO.youth }">
										<label><input type="radio" name="youth" value="0" >비대상</label>
										<label><input type="radio" name="youth" value="1" >대상</label>
									</c:if>
									</td>
								</tr>
							</c:if>
						</c:if>
						<c:if test="${not empty infoCustom_List }">
						<c:set var="countTemp" value="1" ></c:set>
						<c:forEach items="${infoCustom_List }" var="ilist" varStatus="loop">
							<c:forEach items="${infocustom_check_list }" var="check" varStatus="cloop">
								<c:if test="${loop.count eq cloop.count }">
									<c:set var="infocustom_check_value" value="${check }"/>
								</c:if>
							</c:forEach>
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
									<c:choose>
									<c:when test="${infocustom_check_value eq 'List' }">
										<td>
										<c:forEach items="${ilist }" var = "inotice" varStatus="iloop">
											<c:if test="${not empty infocustomValueList }">
											<c:forEach items="${infocustomValueList }" var="icValue" varStatus="icloop">
												<c:if test="${icloop.count eq countTemp }">
												<c:choose>
													<c:when test="${icValue eq inotice }">
														<label><input type="radio" name="custom${loop.count }" value="${icValue }" checked="checked">${icValue }</label>
													</c:when>
													<c:otherwise>
														<label><input type="radio" name="custom${loop.count }" value="${icValue }">${icValue }</label>
													</c:otherwise>
												</c:choose>
												</c:if>
											</c:forEach>
											</c:if>
											<c:if test="${empty infocustomValueList }">
												<label><input type="radio" name="custom${loop.count }" value="${inotice }">${inotice }</label>
											</c:if>
										</c:forEach>
										</td>
										<c:set var="countTemp" value="${countTemp+1 }" ></c:set>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${not empty infocustomValueList }">
												<c:forEach items="${infocustomValueList }" var="icValue" varStatus="icloop">
													<c:if test="${icloop.count eq countTemp }">
														<td><input type="text" name="custom${loop.count }" value="${icValue }"></td>
													</c:if>
												</c:forEach>
											<c:set var="countTemp" value="${countTemp+1 }" ></c:set>
											</c:when>
											<c:otherwise>
												<td><input type="text" name="custom${loop.count }" placeholder="${ilist }"></td>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
									</c:choose>
								</tr>
						</c:forEach>
						
						</c:if>
						</table>
					</div>
					<!-- infoinsert -->
				</div>
				<!-- info -->
				<div id="customQuestion">
				<c:set var="countTemp" value="1" ></c:set>
				<c:set var="countTemp_table" value="1" ></c:set>
				<c:if test="${not empty  resumecustom_list}">
				<c:forEach items="${resumecustom_list }" var="dto" varStatus="loop">
					
					<p>${dto.customTitle }</p>
					<div id="customQuestionnotice">
					<h5>${dto.customNotice }</h5>
					</div>
					<!-- customQuestionnotice -->
					<c:forEach items="${sentence_type_list_list }" var="sentence_type_list_var" varStatus="stloop">
						<c:if test="${loop.count eq stloop.count }">
							<c:set var="sentence_type_list" value="${sentence_type_list_var }"/>
						</c:if>
					</c:forEach>
					<c:forEach items="${sentence_notice_list_list }" var="sentence_notice_list_var" varStatus="rcloop">
						<c:set var="sentence_notice_list" value="${sentence_notice_list_list[loop.index] }"/>
					</c:forEach>
						<c:forEach items="${sentence_notice_list }" var="slist" varStatus="mloop">
						<c:if test="${not empty  slist}">
								<c:forEach items="${sentence_type_list }" var="title" varStatus="titleloop">
									<c:if test="${titleloop.count eq mloop.count }">
										<p>${title.sentenceQuestion }</p>
									</c:if>
								</c:forEach>
								<c:forEach items="${sentence_check_list_list }" var="sentence_check_list" varStatus="scloop">
									<c:set var="sentence_check_list" value="${sentence_check_list }"/>
									<c:if test="${scloop.count eq loop.count }">
									<c:forEach items="${sentence_check_list }" var="check" varStatus="cloop">
										<c:if test="${cloop.count eq mloop.count }">
											<c:set var="sentence_check_value" value="${check }"/>
										</c:if>
									</c:forEach	>
									</c:if>
								</c:forEach>
								<c:choose>
									<c:when test="${sentence_check_value eq 'List' }">
										<div id="sentenceTypeQuestion">
										<c:forEach items="${slist }" var="snotice" varStatus="kloop">
											<c:if test="${not empty sentenceValueList }">
											<c:forEach items="${sentenceValueList }" var="svValue" varStatus="svloop">
												<c:if test="${svloop.count eq  countTemp}">
												<c:choose>
													<c:when test="${svValue eq  snotice}">
														<label><input type="radio" name="sentenceNotice${loop.count }-${mloop.count }" value="${svValue }" checked="checked">${svValue }</label>
													</c:when>
													<c:otherwise>
														<label><input type="radio" name="sentenceNotice${loop.count }-${mloop.count }" value="${svValue }">${svValue }</label>
													</c:otherwise>
												</c:choose>
												</c:if>
											</c:forEach>
											</c:if>
											<c:if test="${empty sentenceValueList }">
												<label><input type="radio" name="sentenceNotice${loop.count }-${mloop.count }" value="${snotice }">${snotice }</label>
											</c:if>
										</c:forEach>
										<c:set var="countTemp" value="${countTemp+1 }" ></c:set>
										</div>
									</c:when>
									<c:otherwise>
										<div>
										<c:choose>
											<c:when test="${not empty sentenceValueList }">
											<c:forEach items="${sentenceValueList }" var="svValue" varStatus="svloop">
												<c:if test="${svloop.count eq  countTemp}">
													<input type="text" name="sentenceNotice${loop.count }-${mloop.count }" value="${svValue }">
												</c:if>
											</c:forEach>
											</c:when>
											<c:otherwise>
												<input type="text" name="sentenceNotice${loop.count }-${mloop.count }" placeholder="${slist }">
											</c:otherwise>
										</c:choose>
										<c:set var="countTemp" value="${countTemp+1 }" ></c:set>
										</div>
									</c:otherwise>
								</c:choose>
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
											<c:forEach var = "i" begin="1" end="${tDto.tableMaxRow }" step="1">
												<c:choose>
												<c:when test="${i eq 1 }">
													<tr>
														<c:if test="${not empty tableTypeQ_list }">
														<c:forEach items="${tableTypeQ_list }" var="tqDto" varStatus="tqloop">
															<c:if test="${tqDto.tableTypeidx eq tDto.idx }">
																<c:choose>
																<c:when test="${not empty tableValueList }">
																	<c:forEach items="${tableValueList }" var="tableValue" varStatus="tableloop">
																	<c:if test="${tableloop.count eq countTemp_table }">
																		<td><input type="text" name="tableText${loop.count }-${tqloop.count}"
																			 value="${tableValue }"></td>
																	</c:if>
																	</c:forEach>
																<c:set var="countTemp_table" value="${countTemp_table+1 }" ></c:set>
																</c:when>
																<c:otherwise>
																	<td><input type="text" name="tableText${loop.count }-${tqloop.count}"
																		placeholder="${tqDto.tableNotice }"></td>
																</c:otherwise>
																</c:choose>
															</c:if>
														</c:forEach>
														</c:if>
													</tr>
												</c:when>
												<c:otherwise>
													<tr>
														<c:if test="${not empty tableTypeQ_list }">
														<c:forEach items="${tableTypeQ_list}" var="tqDto" varStatus="tqloop">
															<c:if test="${tqDto.tableTypeidx eq tDto.idx }">
																<c:choose>
																<c:when test="${not empty tableValueList }">
																	<c:forEach items="${tableValueList }" var="tableValue" varStatus="tableloop">
																	<c:if test="${tableloop.count eq countTemp_table }">
																		<td><input type="text" name="tableText${loop.count}-${tqloop.count}"
																			value="${tableValue }"></td>
																	</c:if>
																	</c:forEach>
																<c:set var="countTemp_table" value="${countTemp_table+1 }" ></c:set>
																</c:when>
																<c:otherwise>
																	<td><input type="text" name="tableText${loop.count}-${tqloop.count}"
																		placeholder="값입력"></td>
																</c:otherwise>
																</c:choose>
															</c:if>
														</c:forEach>
														</c:if>
													</tr>
												</c:otherwise>
												</c:choose>
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
					<c:forEach items="${personalList }" var="pDto" varStatus="ploop">
						<c:forEach items="${personalQList }" var="pqDto" varStatus="pqloop">
						<c:if test="${pqDto.personalidx eq pDto.idx }">
	             	 		<c:if test="${not empty pqDto.personalQuestion }">
	                  			<c:set var="personalQuestionCheck" value="1"></c:set>
	               			</c:if>
	               		</c:if>
	               		</c:forEach>
               		</c:forEach>
               		
               		<c:if test="${not empty personalQuestionCheck }">
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
											<c:choose>
												<c:when test="${not empty personalValueList }">
													<c:forEach items="${personalValueList }" var="personalValue" varStatus="personalloop">
													<c:if test="${personalloop.count eq countTemp }">
														<td><input type="text" maxlength="${pqDto.personalMaxW }" name="personalValue${ploop.count }${pqloop.count}" onblur="checkMinLength(this, ${pqDto.personalMinW});" value="${personalValue}"></td>
													</c:if>
													</c:forEach>
												<c:set var="countTemp" value="${countTemp+1 }" ></c:set>
												</c:when>
												<c:otherwise>
													<td><input type="text" maxlength="${pqDto.personalMaxW }" name="personalValue${ploop.count }${pqloop.count}" onblur="checkMinLength(this, ${pqDto.personalMinW});" placeholder="최소글자수: ${pqDto.personalMinW }, 최대글자수: ${pqDto.personalMaxW }"></td>
												</c:otherwise>
											</c:choose>
											</tr>
										</tbody>
									</table>
								</c:if>
								</c:forEach>
							</div>
							<!-- personalQuestion -->
						</c:forEach>
					</c:if>
					</c:if>
				</div>
				<!-- personal -->
				<div id="career">
					<c:forEach items="${careerList }" var="cDto" varStatus="careerloop">
						<c:forEach items="${careerQList }" var="cqDto" varStatus="cqloop">
						<c:if test="${cqDto.careeridx eq cDto.idx }">
		                 	<c:if test="${not empty cqDto.careerQuestion }">
		                  		<c:set var="careerQuestionCheck" value="1"></c:set>
		                	</c:if>
		                </c:if>
		                </c:forEach>
	                </c:forEach>
	                <c:if test="${not empty careerQuestionCheck }">
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
											<c:choose>
												<c:when test="${not empty careerValueList }">
													<c:forEach items="${careerValueList }" var="careerValue" varStatus="cvloop">
													<c:if test="${cvloop.count eq countTemp }">
														<td><input type="text" maxlength="${cqDto.careerMaxW }" name="careerValue${careerloop.count }${careerqloop.count}" onblur="checkMinLength(this, ${cqDto.careerMinW});" value="${careerValue }"></td>
													</c:if>
													</c:forEach>
												<c:set var="countTemp" value="${countTemp+1 }" ></c:set>
												</c:when>
												<c:otherwise>
													<td><input type="text" maxlength="${cqDto.careerMaxW }" name="careerValue${careerloop.count }${careerqloop.count}" onblur="checkMinLength(this, ${cqDto.careerMinW});" placeholder="최소글자수: ${cqDto.careerMinW }, 최대글자수: ${cqDto.careerMaxW }"></td>
												</c:otherwise>
											</c:choose>
											</tr>
										</tbody>
									</table>
								</c:if>
								</c:forEach>
							</div>
						</c:forEach>
					</c:if>
					</c:if>
				</div>
				<!-- career -->
				
				<c:if test="${not empty resumefileDTO}">
				<div id =file>
				<h4>${resumefileDTO.rsmfile_title }</h4>
				<h5>${resumefileDTO.rsmfile_explain }</h5>
					<div id="filenotice">
					</div>
					<!-- filenotice -->
					<c:if test="${not empty resumefile_list }">
						<c:forEach items="${resumefile_list }" var="rfDto" varStatus="rfloop">
							<c:choose>
							<c:when test="${empty fileValueList}">
								<div class="filename">
									<span>${rfDto.rsmfileupload_fileName }</span>
								</div><!-- filename -->
								<div class="filedownload">
								<c:if test="${not empty rfDto.rsmfileupload_original }">
									<span>${rfDto.rsmfileupload_original }<a href="../resume/download?filePath=${rfDto.rsmfileupload_filePath }&OfileName=${rfDto.rsmfileupload_original }">파일다운로드</a></span>
								</c:if>
								</div><!-- filedownload -->
								<div class="fileupload">
									<input type="file" name="applicant_file">
								</div><!-- fileupload -->
							</c:when>
							<c:otherwise>
								<div class="filename">
									<span>${rfDto.rsmfileupload_fileName }</span>
								</div><!-- filename -->
								<div class="filedownload">
								<c:if test="${not empty rfDto.rsmfileupload_original }">
									<span>${rfDto.rsmfileupload_original }<a href="../resume/download?filePath=${rfDto.rsmfileupload_filePath }&OfileName=${rfDto.rsmfileupload_original }">파일다운로드</a></span>
								</c:if>
								</div><!-- filedownload -->
								<div class="fileupload">
									<c:forEach items="${fileValueList }" var="fvDto" varStatus="fvloop">
									<c:if test="${fvloop.count eq rfloop.count }">
										<span><a href="../resume/download?filePath=${fvDto.applicant_file_filePath }&OfileName=${rfDto.rsmfileupload_original }">${fvDto.applicant_file_Original }</a></span>
										<input type="file" name="applicant_file">
									</c:if>
									</c:forEach>
								</div><!-- fileupload -->
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</div><!-- file -->
				</c:if>
				<div class="agreement">
				<p>동의 합니깡</p>
				<label><input type="checkbox">동의함</label>
				<p>제출일 : 2023년 10월 25일</p>
				<p>작성자 :</p>
				</div>
				
				<div id="buttons">
					<input type="button" value="뒤로가기" onclick="resumeFieldSetting()">
				</div>
		</div>
	</div>
	<!-- wrap -->
	
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

	function resumeFieldSetting(){
		window.location.href = "resumeFieldSetting";
	}

    const selectElement = document.getElementById('fieldSelect');
    selectElement.addEventListener("change",function(){
    	document.getElementById("form").submit();
    });

	</script>
</body>
</html>