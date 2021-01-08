<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
	<form action="/blog/board?cmd=update" method="POST">
		<input type="hidden" name="boardId" value="${board.id }" />
		<div class="form-group">
			<label for="title">Title:</label>
			<input type="text" class="form-control" placeholder="title" id="title" name="title" value="${board.title }">
		</div>
	
		<div class="form-group">
			<label for="content">Content:</label>
			<textarea id="summernote" class="form-control" rows="5" id="content" name="content" >
				${board.content }
			</textarea>
		</div>
	
		<button type="submit" class="btn btn-primary">수정 완료</button>
	</form>
</div>

<script>
	  $('#summernote').summernote({
	        placeholder: '내용을 입력하세요.',
	        tabsize: 2,
	        height: 400
	      });
</script>
</body>
</html>