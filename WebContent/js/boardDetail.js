function deleteById(boardId){
	// ajax로 delete 요청 (Mehtod : POST), json형태로 !!
	
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

function replySave(userId, boardId) {
	var data = {
		userId: userId,
		boardId: boardId,
		content: $("#reply__write__form").val()
	}
	
	$.ajax({
		type: "post",
		url: "/blog/reply?cmd=save",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(result){
		if (result.statusCode == 1) {
			addReply(result.data);
		} else {
			alert("댓글 작성 실패");
		}
	})
};

function addReply(data) {
	var replyList = $("#reply__list");
	
	var newLi = document.createElement("li");
	newLi.id = "reply-"+data.replyId;
	newLi.className = "media";
	
	var liDetail = `<div class="media-body">`;
	liDetail += `<strong class="text-primary">${data.username}</strong>`;
	liDetail += `<p>${data.content}</p>`;
	liDetail += `</div>	<div class="m-2">`;
	liDetail += `<i onclick="deleteReply(${data.id})" class="material-icons">delete</i>`;
	liDetail += `</div>`;
	
	newLi.innerHTML = liDetail;
	
	replyList.prepend(newLi);
}

function deleteReply(replyId) {
	// 세션의 유저의 id와 reply의 userId를 비교해서 같을때만 !!
}