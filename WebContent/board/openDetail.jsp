<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container" style="border: 1px solid black">
	<h1 style="text-align: center; border-bottom: 1px solid darkgray;">타이틀 : ${board.title }</h1>
	<div style="display: flex; justify-content: space-around;">
		<div>글번호 : ${board.id }</div>
		<div>작성자 : ${board.userId }</div>
		<div>조회수 : ${board.readCount }</div>
		<div>작성일자 : ${board.createDate }</div>
	</div>
	<div style="background-color: pink; height: 500px; font-size: 16px; padding:10px;">
		${board.content }
	</div>
</div>
</body>
</html>