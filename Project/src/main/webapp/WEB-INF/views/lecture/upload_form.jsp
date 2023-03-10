<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/lecture/upload_form.jsp</title>
<jsp:include page="/WEB-INF/views/include/bootCss.jsp"></jsp:include>
<link href="${pageContext.request.contextPath }/resources/css/users.css" rel="stylesheet">

</head>
<body>
	<div class="wrapper">
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<div class="container userform">
			<main class="form-signin w-100 m-auto mt-5">
				<h3 class="text-center mb-3 fw-normal">강의 수정 페이지 </h3>
	   			<p class="text-center mb-3 fw-normal">강의 수정 페이지입니다.</p>
				<form action="${pageContext.request.contextPath}/lecture/upload" method="post" enctype="multipart/form-data" >
					<div class="mb-3">
					  <label class="form-label" for="image">썸네일</label>
					  <input class="form-control" type="file" id="formFile" name="image" accept=".jpg, .jpeg, .png, .JPG, .JPEG" >
					</div>
					<div class="mb-3">
						<label class="form-label" for="title">강의 제목</label>
						<input class="form-control" type="text" name="title" id="title"/>
					</div>
					<div class="mb-3">
						<label class="form-label" for="teacher">강사</label>				
						<input class="form-control" type="text" name="teacher" id="teacher"  />				
					</div>
					<div class="mb-3">
						<label class="form-label" for="describe">강의 내용</label>				
						<textarea name="describe" id="describe">${dto.describe }</textarea>			
					</div>
					<div class="mb-3">
				         	<label class="form-label" for="videoPath">강의 영상</label>
				         	<input class="form-control" type="text" name="videoPath" id="videoPath" />
				    </div>
				    <div class="mb-3">
				     	<label class="form-label" for="large_category">대분류</label>
				     	<select class="form-control" name="large_category" id="large_category">
				      			<option value="front">프론트엔드</option>
				      			<option value="backend">백엔드</option>
				      			<option value="mobile">모바일</option>
				      	</select>
				    </div>
				    <div class="mb-3">
				      	<label class="form-label" for="small_category">소분류</label>
				      		<select class="form-control" name="small_category" id="small_category">
				      			<option value="js">javascript</option>
				      			<option value="html_css">html/css</option>
				      			<option value="react">react</option>
				      			<option value="vue">vue.js</option>
				      			<option value="jquery">jQuery</option>
				      			<option value="java">java</option>
				      			<option value="spring">spring</option>
				      			<option value="springboot">spring boot</option>
				      			<option value="kotlin">kotlin</option>
				      		</select>
				      	</div>
					<button class="w-100 button btn btn-lg mt-3 mb-5" type="submit" onclick="submitContents(this)">수정확인</button>
				</form>
			</main>
		</div>	
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath }/resources/SmartEditor/js/HuskyEZCreator.js"></script>
	<script>
		var oEditors = [];
		
		//추가 글꼴 목록
		//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
		
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "describe",
			sSkinURI: "${pageContext.request.contextPath}/resources/SmartEditor/SmartEditor2Skin.html",	
			htParams : {
				bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
				fOnBeforeUnload : function(){
					//alert("완료!");
				}
			}, //boolean
			fOnAppLoad : function(){
				//예제 코드
				//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
			},
			fCreator: "createSEditor2"
		});
		
		function pasteHTML() {
			var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
			oEditors.getById["describe"].exec("PASTE_HTML", [sHTML]);
		}
		
		function showHTML() {
			var sHTML = oEditors.getById["describe"].getIR();
			alert(sHTML);
		}
			
		function submitContents(elClickedObj) {
			//SmartEditor 에 의해 만들어진(작성한글) 내용이 textarea 의 value 가 되도록 한다. 
			oEditors.getById["describe"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
			
			// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.
			
			try {
				//폼 제출하기 
				elClickedObj.form.submit();
			} catch(e) {}
		}
		
		function setDefaultFont() {
			var sDefaultFont = '궁서';
			var nFontSize = 24;
			oEditors.getById["describe"].setDefaultFont(sDefaultFont, nFontSize);
		}
	</script>		
</body>
</html>
