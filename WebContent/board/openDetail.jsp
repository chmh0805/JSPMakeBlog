<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">

	<c:if test="${sessionScope.principal.id == board.userId }">
		<a href="/blog/board?cmd=updateForm&boardId=${board.id }" class="btn btn-warning">수정</a>
		<button class="btn btn-danger" onclick="deleteById(${board.id})">삭제</button>
	</c:if>

	<br />
	<br />
	<h6 class="m-2">
		작성자 : <i>${board.username }</i> 조회수 : <i>${board.readCount }</i>
	</h6>
	<br />
	<h3 class="m-2">
		<b>${board.title }</b>
	</h3>
	<hr />
	<div class="form-group">
		<div class="m-2">${board.content }</div>
	</div>

	<hr />
	
	<!-- 댓글 박스 -->
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2"><b>Comment</b></div>
					<div class="panel-body">
						<textarea id="reply__write__form" class="form-control" placeholder="write a comment..." rows="2"></textarea>
						<br>
						<button onclick="#" class="btn btn-primary pull-right">댓글쓰기</button>
						<div class="clearfix"></div>
						<hr />
						
						<!-- 댓글 리스트 시작-->
						<ul id="reply__list" class="media-list">
						
								<!-- 댓글 아이템 -->
								<li id="reply-1" class="media">		
									<div class="media-body">
										<strong class="text-primary">홍길동</strong>
										<p>
											댓글입니다.
										</p>
									</div>
									<div class="m-2">
		
										<i onclick="#" class="material-icons">delete</i>

									</div>
								</li>
							
						</ul>
						<!-- 댓글 리스트 끝-->
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 댓글 박스 끝 -->
</div>

<script>
	function deleteById(boardId){
		// ajax로 delete 요청 (Mehtod : POST), json형태로 !!
		var data = {
			boardId: boardId
		};
		
		$.ajax({
			type: "POST",
			url: "/blog/board?cmd=delete&boardId="+boardId,
			dataType: "json"
		}).done((result)=>{
			if(result.statusCode == 1){
				alert('삭제되었습니다.');
				location.href="/blog/";
			} else {
				alert('게시글 삭제를 실패하였습니다.');
			}
		});
	}
</script>

</body>
</html>