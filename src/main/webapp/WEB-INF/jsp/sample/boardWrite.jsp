<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
</head>
<body>
	<form id="frm" name="frm" enctype="multipart/form-data">
		<table class="board_view">
			<colgroup>
				<col width="15%">
				<col width="*" />
			</colgroup>
			<caption>게시글 작성</caption>
			<tbody>
				<tr>
					<th scope="row">제목</th>
					<td><input type="text" id="TITLE" name="TITLE" class="wdp_90"></input></td>
				</tr>
				<tr>
					<td colspan="2" class="view_text"><textarea rows="20"
							cols="100" title="내용" id="CONTENTS" name="CONTENTS"></textarea></td>
				</tr>
			</tbody>
		</table>
		<div id="fileDiv"></div>
		<input type="file" name="file"> <br />
		<br /> <a href="#this" class="btn" id="addFile">파일추가</a> <a
			href="#this" class="btn" id="write">작성하기</a> <a href="#this"
			class="btn" id="list">목록으로</a>
	</form>

	<%@ include file="/WEB-INF/include/include-body.jspf"%>
	<script type="text/javascript">
		var gfv_count = 1;
		$(document).ready(function() {
			// 목록으로 버튼 function
			$('#list').on("click", function(e) {
				e.preventDefault();
				fn_openBoardList();
			});
	
			// 작성하기 버튼 function
			$('#write').on("click", function(e) {
				e.preventDefault();
				fn_insertBoard();
			});
	
			// 파일추가 버튼 
			$('#addFile').on("click", function(e) {
				e.preventDefault();
				fn_addFile();
			});
	
			// 삭제 버튼 
			$("a[name='delete']").on("click", function(e) { //삭제 버튼
				e.preventDefault();
				fn_deleteFile($(this));
			});
		});
	
		function fn_openBoardList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/openBoardList.do' />");
			comSubmit.submit();
		}
	
		function fn_insertBoard() {
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/sample/insertBoard.do' />");
			comSubmit.submit();
		}
	
		function fn_addFile() {
			console.log("pressed");
			var str = "<p><input type='file' name='file_" + (gfv_count++) + "'><a href='#this' class='btn' name='delete'>삭제</a></p>";
			$("#fileDiv").append(str);
			$("a[name='delete']").on("click", function(e) { //삭제 버튼
				e.preventDefault();
				fn_deleteFile($(this));
			});
		}
	
		function fn_deleteFile(obj) {
			obj.parent().remove();
		}
	</script>
</body>
</html>