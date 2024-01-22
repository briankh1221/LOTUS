window.onload = function () {
	getSession();
	
  // 인적사항 항목 추가 버튼 *231105 유준호
  const infoPlusBtn = $(".infoPlus > input");

  // 인적사항 항목 추가 클릭시 테이블 증가 메서드 실행 *231108 유준호
  // no. 초기값 *231108 유준호
  let count = 1; 

  infoPlusBtn.click(function () {
    let trCount = document.querySelectorAll(".plusTable > tbody > tr").length + 1;
    
    if (trCount < 6) {
      addPlusTable(count);
      count++;
    } else {
      alert("추가할 수 있는 개수는 최대 5개까지입니다");
      return;
    }
    
  });
};

function test() {
   let minRow = $('#minRow').val();
   let maxRow = $('#minRow').val();
   let maxCol = $('#maxCol').val();
   let loop = $('#loop').val();
   $('#tableMinRow'+loop).val(minRow).prop("selected", true);
   $('#tableMaxRow'+loop).val(maxRow).prop("selected", true);
   $('#tableMaxCol'+loop).val(maxCol).prop("selected", true);
}

// 맞춤형 문항 추가 클릭 시 맞춤형 문항 추가하는 메서드 *231107 유준호
function addCustom(obj) {

  let customCount = obj.parentNode.parentNode.children[1].children[3].children.length+1
  
  if (customCount < 6) {
  
    let block = "";
    block += "<div class='custom'>";
    block += "<div class='customheader'>";
    block += "<div class='inputBlock' style='width: 300px;'>";
    block += "<span>>>&nbsp;</span><input type='text' value='기타문항1' name='customTitle' style='width: 250px;'>";
    block += "</div>";
    block += "<!-- inputBlock *231110 유준호-->";
    block += "<div class='plusBtn'>";
    block += "<div class='field'>";
    block += "<select name='customField'>";
    block += "<option value='0'>공통</option>";
    block += "<option value='1'>채용분야</option>";
    block += "</select>";
    block += "</div>";
    block += "<input type='button' value='+문항(단문형)추가' class='shortPlusBtn' onclick='javascript: addSentenceFunc(this)'>";
    block += "<input type='button' value='+문항(테이블형)추가' class='tablePlusBtn' onclick='javascript: addTableFunc(this)'>";
    block += "<input type='button' value='+맞춤형문항 삭제' class='blockDelBtn' onclick='javascript: decreaseblock(this)'>";
    block += "</div>";
    block += "<!-- plusBtn *231110 유준호-->";
    block += "</div>";
    block += "<!-- customheader *231110 유준호-->";
    block += "<div class='customexplain'>";
    block += "<textarea class='explaintxarea' placeholder='설명을 입력하세요' name='customNotice'></textarea>";
    block += "</div>";
    block += "<!-- explain *231110 유준호-->";
    block += "<hr>";
    block += "<!-- 맞춤형문항(단문형) *231110 유준호-->";
    block += "<table class='setencetbl'>";
    block += "<thead>";
    block += "<tr>";
    block += "<th>no.</th>";
    block += "<th style='width: 400px; height: 40px;'>문항</th>";
    block += "<th style='width: 400px; height: 40px;'>안내글 및 선택값 설정(구분자:$)</th>";
    block += "<th style='width: 130px; height: 40px;'>필수</th>";
    block += "<th style='width: 90px; height: 40px;'><input type='hidden' value ='1' name='sentenceQuestion'><input type='hidden' value ='1' name='tableTitle'></th>";
    block += "</tr>";
    block += "</thead>";
    block += "<tbody>";
    block += "</tbody>";
    block += "</table>";
    block += "<!-- 맞춤형문항(테이블형) *231112 유준호-->";
    block += "<hr>";
    block += "<div class='tableBlock'>";
    block += "<div class='tableType'>";
    block += "<table class='tableSetting'>";
    block += "<tr>";
    block += "<th>질문 또는 가이드 문구&nbsp;<input type='text' placeholder='생략가능' name='tableTitle'><input type='hidden' value='0' name='tableTitle'></th>";
    block += "<th>기본 행 <select name='tableMinRow'>";
    block += "<option value='1'>1개</option>";
    block += "<option value='2'>2개</option>";
    block += "<option value='3'>3개</option>";
    block += "<option value='4'>4개</option>";
    block += "<option value='5'>5개</option>";
    block += "</select>";
    block += "</th>";
    block += "<th>최대 행 <select name='tableMaxRow'>";
    block += "<option value='1'>1개</option>";
    block += "<option value='2'>2개</option>";
    block += "<option value='3'>3개</option>";
    block += "<option value='4'>4개</option>";
    block += "<option value='5'>5개</option>";
    block += "</select>";
    block += "</th>";
    block += "<th>입력항목(열) <select name='tableMaxCol' onchange='selectTb(this)'>";
    block += "<option value='1'>1개</option>";
    block += "<option value='2'>2개</option>";
    block += "<option value='3'>3개</option>";
    block += "<option value='4' selected>4개</option>";
    block += "<option value='5'>5개</option>";
    block += "</select>";
    block += "</th>";
    block += "<th><a onclick='javascript: decreaseTable5(this)'>삭제</a></th>";
    block += "</tr>";
    block += "</table><!-- tableSetting *231112 유준호-->";
    block += "<table class='tableQuestion'>";
    block += "<tr>";
    block += "<td><input type='hidden' value='1' name='tableCategory'><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
    block += "<td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
    block += "<td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
    block += "<td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
    block += "</tr>";
    block += "<tr>";
    block += "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
    block += "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
    block += "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
    block += "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
    block += "</tr>";
    block += "</table>";
    block += "</div>";
    block += "</div>";
    block += "</div>";

    $(".customBlock").append(block);
  } else {
    alert("추가할 수 있는 개수는 최대 5개까지입니다");
    return;
  }
}

// 필수 제출자료 설정 항목 추가하는 메서드 *231110 유준호
function addFileSettingTbl(obj) {
  let trCount = obj.parentNode.parentNode.parentNode.children.length;
  
  if(trCount < 6){
	let tr = "";
	tr += "<tr>";
	tr += "<td><select name='rsmfileupload_field'>";
	tr += "<option value='0'>공통</option>";
	tr += "<option value='1'>채용분야별</option>";
	tr += "</select><input type='hidden' value='1' name='rsmfileupload_field'></td>";
	tr += "<td><input type='text' placeholder='파일명칭' name='rsmfileupload_fileName'><input type='hidden' value='1' name='rsmfileupload_fileName'></td>";
	tr += "<td><input type='text' placeholder='안내문' name='rsmfileupload_pilsu'><input type='hidden' value='1' name='rsmfileupload_pilsu'></td>";
	tr += "<input type='hidden' value='$' name='filePath'>"
	tr += "<input type='hidden' value='$' name='fileName'>"
	tr += "<td><input type='file' name='file'><input type='hidden' value='1' ><a onclick='javascript: decreaseTable4(this)'>삭제</a></td>";
	tr += "</tr>";
	  
	  $(".fileSettingTbl tbody tr:last-child").before(tr);
  } else {
	alert("추가할 수 있는 개수는 최대 5개까지입니다");
	return;  
  }
}

// 경력기술서 질문 문항 추가하는 메서드 *231112 유준호
function addCareerQuestionTbl(obj) {
  let trCount = obj.parentNode.parentNode.parentNode.children.length;
 
  if(trCount < 6) {
    let tr = "";
    tr += "<tr>";
    tr += "<td><input type='text' placeholder='경력기술 질문 내용 작성' name='careerQuestion'></td>";
    tr += "<td>최소&nbsp;<input type='text' placeholder='글자수' value ='0' name='careerMinW'></td>";
    tr += "<td>최대&nbsp;<input type='text' placeholder='글자수' value ='0' name='careerMaxW'></td>";
    tr += "<td><a onclick='javascript: decreaseTable3(this)'>삭제</a></td>";
    tr += "</tr>";
    $(".careerQuestionTbl tr:last-child").before(tr);
  } else {
    alert("추가할 수 있는 개수는 최대 5개까지입니다");
    return;
  }
}

// 자기소개서 질문 문항 추가하는 메서드 *231115 유준호
function addPersonalQuestion(obj) {
  let trCount = obj.parentNode.parentNode.parentNode.children.length;

  if (trCount < 6) {
    let tr = "";
    tr += "<tr>";
    tr += "<td><input type='text' placeholder='자기소개 질문 내용 작성' name='personalQuestion'></td>";
    tr += "<td>최소&nbsp;<input type='text' placeholder='글자수' value ='0' name='personalMinW'></td>";
    tr += "<td>최대&nbsp;<input type='text' placeholder='글자수' value ='0' name='personalMaxW'></td>";
    tr += "<td><a onclick='javascript: decreaseTable3(this)'>삭제</a></td>";
    tr += "</tr>";
    
    $(".personalQuestionTbl tr:last-child").before(tr);
  } else {
    alert("추가할 수 있는 개수는 최대 5개까지입니다");
    return;
  }
}

// 기타문항1 테이블형문항 항목 추가하는 메서드 *231115 유준호
function addTableType(addTable) {
  let table = "";
  table += "<div class='tableType'>";
  table += "<table class='tableSetting'>";
  table += "<tr>";
  table += "<th>질문 또는 가이드 문구&nbsp;<input type='text' placeholder='생략가능' name='tableTitle'><input type='hidden' value='0' name='tableTitle'></th>";
  table += "<th>기본 행 <select name='tableMinRow'>";
  table += "<option value='1'>1개</option>";
  table += "<option value='2'>2개</option>";
  table += "<option value='3'>3개</option>";
  table += "<option value='4'>4개</option>";
  table += "<option value='5'>5개</option>";
  table += "</select>";
  table += "</th>";
  table += "<th>최대 행 <select name='tableMaxRow'>";
  table += "<option value='1'>1개</option>";
  table += "<option value='2'>2개</option>";
  table += "<option value='3'>3개</option>";
  table += "<option value='4'>4개</option>";
  table += "<option value='5'>5개</option>";
  table += "</select>";
  table += "</th>";
  table += "<th>입력항목(열) <select name='tableMaxCol' onchange='selectTb(this)'>";
  table += "<option value='1'>1개</option>";
  table += "<option value='2'>2개</option>";
  table += "<option value='3'>3개</option>";
  table += "<option value='4' selected>4개</option>";
  table += "<option value='5'>5개</option>";
  table += "</select>";
  table += "</th>";
  table += "<th><a onclick='javascript: decreaseTable5(this)'>삭제</a></th>";
  table += "</tr>";
  table += "</table><!-- tableSetting -->";
  table += "<table class='tableQuestion'>";
  table += "<tr>";
  table += "<td><input type='hidden' value='1' name='tableCategory'><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
  table += "<td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
  table += "<td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
  table += "<td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
  table += "</tr>";
  table += "<tr>";
  table += "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
  table += "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
  table += "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
  table += "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
  table += "</tr>";
  table += "</table>";
  table += "</div>";
  
  $(addTable).append(table);
}

// 기타사항1 테이블 추가하는 메서드 *231118 유준호
function addTableFunc(e) {

  let tableCount =
    e.parentNode.parentNode.nextElementSibling.nextElementSibling
      .nextElementSibling.nextElementSibling.nextElementSibling.children
      .length + 1;
  let addTable =
	e.parentNode.parentNode.nextElementSibling.nextElementSibling
      .nextElementSibling.nextElementSibling.nextElementSibling
  if (tableCount < 6) {
    addTableType(addTable);
  } else {
    alert("추가할 수 있는 개수는 최대 5개까지입니다");
    return;
  }
}

// 기타사항1 단문문항 테이블 추가하는 메서드 *231118 유준호
function addSentenceFunc(e) {
  let trCount =
    e.parentNode.parentNode.parentNode.children[3].children[1].children.length +
    1;
  console.log(trCount);
  let tableTbody = e.parentNode.parentNode.parentNode.children[3].children[1];
  if (trCount < 6) {
    addSetencetbl(tableTbody, trCount);
  } else {
    alert("추가할 수 있는 개수는 최대 5개까지입니다");
    return;
  }
}

// 기타문항1 단문문항 항목 추가하는 메서드 *231118 유준호
function addSetencetbl(tableTbody, trCount) {
  let tr = "<tr>";
  tr += "<td>" + trCount + "</td>";
  tr += "<td><input type='text' placeholder='$' name='sentenceQuestion'><input type='hidden' value ='0' name='sentenceQuestion'></td>";
  tr += "<td><input type='text' placeholder='$' name='sentenceNotice'><input type='hidden' value ='0' name='sentenceNotice'></td>";
  tr += "<td>필수&nbsp;<input type='checkbox' value ='1' name='sentencePilsu'><input type='hidden' value ='0' name='sentencePilsu'></td>";
  tr += "<td><a onclick='javascript: decreaseTable2(this)'>삭제</a></td>";
  tr += "</tr>";
  
  $(tableTbody).append(tr);
}

// 인적사항 테이블 증가 메서드 *231118 유준호
function addPlusTable(count) {
  let tr = "";
  tr += "<tr>";
  tr += "<td>" + count + "</td>";
  tr += "<td><input type='text' value ='인적사항항목' name='infoCustomCategory'></td>";
  tr += "<td><input type='text' placeholder='구분자 : $' name='infoCustomNotice'><input type='hidden' value ='0' name='infoCustomNotice'></td>";
  tr += "<td><select name='infoCustomField' style='width: 150px;'><option value='0'>공통</option><option value='1'>채용분야별</option></select></td>";
  tr += "<td><label>필수&nbsp;<input type='checkbox' value='1' name='infoCustomPilsu' onclick='javascript: pilsu(this);'><input type='hidden' value='0' name='infoCustomPilsu' onclick='javascript: pilsu(this);'></label></td>";
  tr += "<td id='plusTableDelBtn'><a onclick='javascript: decreaseTable(this)'>삭제</a></td>";
  tr += "</tr>";
  
  $(".plusTable  tbody").append(tr);
}

// 인적사항 테이블 tr 삭제 메서드 *231113 유준호
function decreaseTable(obj) {
  let tr = obj.parentNode.parentNode;
  tr.parentNode.removeChild(tr);
}

// 기타문항1 테이블 tr 삭제 메서드 *231113 유준호
function decreaseTable2(obj) {
  let tr = obj.parentNode.parentNode;
  tr.parentNode.removeChild(tr);
}

// 자기소개서 테이블 tr 삭제 메서드 *231113 유준호
function decreaseTable3(obj) {
  let tr = obj.parentNode.parentNode;
  tr.parentNode.removeChild(tr);
}

// 경력기술서 테이블 tr 삭제 메서드 *231113 유준호
function decreaseTable3(obj) {
  let tr = obj.parentNode.parentNode;
  tr.parentNode.removeChild(tr);
}

// 필수 제출자료 설정 테이블 tr 삭제 메서드 *231113 유준호
function decreaseTable4(obj) {
  let tr = obj.parentNode.parentNode;
  tr.parentNode.removeChild(tr);
}

// 클릭 시 기타문항1 테이블형문항 항목 삭제하는 메서드 *231113 유준호
function decreaseTable5(obj) {
  let table = obj.parentNode.parentNode.parentNode.parentNode.parentNode;
  table.parentNode.removeChild(table);
}

// 클릭 시 기타문항1 테이블형문항 항목 삭제하는 메서드 *231115 유준호
function decreaseblock(obj) {
  let block = obj.parentNode.parentNode.parentNode;
  block.remove();
}

// 클릭 시 변경된 지원서 양식 전체 정보를 저장하는 메서드 *231115 유준호
function resumeSave() {
  document.getElementById("form").submit();
}

// 클릭시 필수 값 체크 여부 *231115 유준호
function pilsu(cs) {
  if (cs.checked) {
    cs.value = 1;
  } else {
    cs.value = 0;
  }
}

// 질문 또는 가이드 문구 tr에서 입력항목 개수 선택시 td의 개수 조정하는 메서드 *231111 유준호
function selectTb(obj) {
  //select의 value == td의 개수 *231111 유준호
  const tdCount = obj.value; 
  let firstBlock = obj.parentNode.parentNode.parentNode.parentNode.parentNode.children[5].children[0].children[0];
  let secondBlock = obj.parentNode.parentNode.parentNode.parentNode.parentNode.children[5].children[0].children[1];
  
  if (tdCount == 1) {
    firstBlock.innerHTML =
      "<td><input type='hidden' value='1' name='tableCategory'><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
    secondBlock.innerHTML =
      "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
  } else if (tdCount == 2) {
    firstBlock.innerHTML =
      "<td><input type='hidden' value='1' name='tableCategory'><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td><td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
    secondBlock.innerHTML =
      "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td><td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
  } else if (tdCount == 3) {
    firstBlock.innerHTML =
      "<td><input type='hidden' value='1' name='tableCategory'><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td><td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td><td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
    secondBlock.innerHTML =
      "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td><td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td><td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
  } else if (tdCount == 4) {
    firstBlock.innerHTML =
      "<td><input type='hidden' value='1' name='tableCategory'><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td><td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td><td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td><td><input type='text' placeholder='항목명' name='tableCategory'><input type='hidden' value='0' name='tableCategory'></td>";
    secondBlock.innerHTML =
      "<td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td><td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td><td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td><td><input type='text' placeholder='안내글 또는 선택값' name='tableNotice'><input type='hidden' value='0' name='tableNotice'></td>";
  } else if (tdCount == 5) {
    firstBlock.innerHTML =
      "<td style='width:210px;'><input type='hidden' value='1' name='tableCategory'><input type='text' placeholder='항목명' name='tableCategory' style='width:210px;'><input type='hidden' value='0' name='tableCategory'></td><td style='width:210px;'><input type='text' placeholder='항목명' name='tableCategory' style='width:210px;'><input type='hidden' value='0' name='tableCategory'></td><td style='width:210px;'><input type='text' placeholder='항목명' name='tableCategory' style='width:210px;'><input type='hidden' value='0' name='tableCategory'></td><td style='width:210px;'><input type='text' placeholder='항목명' name='tableCategory' style='width:210px;'><input type='hidden' value='0' name='tableCategory'></td><td style='width:210px;'><input type='text' placeholder='항목명' name='tableCategory' style='width:210px;'><input type='hidden' value='0' name='tableCategory'></td>";
    secondBlock.innerHTML =
      "<td style='width:210px;'><input type='text' placeholder='안내글 또는 선택값' name='tableNotice' style='width:210px;'><input type='hidden' value='0' name='tableNotice'></td><td style='width:210px;'><input type='text' placeholder='안내글 또는 선택값' name='tableNotice' style='width:210px;'><input type='hidden' value='0' name='tableNotice'></td><td style='width:210px;'><input type='text' placeholder='안내글 또는 선택값' name='tableNotice' style='width:210px;'><input type='hidden' value='0' name='tableNotice'></td><td style='width:210px;'><input type='text' placeholder='안내글 또는 선택값' name='tableNotice' style='width:210px;'><input type='hidden' value='0' name='tableNotice'></td><td style='width:210px;'><input type='text' placeholder='안내글 또는 선택값' name='tableNotice' style='width:210px;'><input type='hidden' value='0' name='tableNotice'></td>";
  }
}
