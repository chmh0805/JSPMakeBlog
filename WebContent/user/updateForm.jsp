<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/blog/user?cmd=update" method="post" onsubmit="return valid()">
		
		<input type="hidden" name="userId" value="${sessionScope.principal.id }" />
	
		<div class="form-group">
			<input type="text" name="username" id="username" class="form-control" value="${sessionScope.principal.username }"  required readonly/>
		</div>

		<div class="form-group">
			<input type="password" name="password" class="form-control" placeholder="Enter Password" required/>
		</div>

		<div class="form-group">
			<input type="email" name="email" class="form-control" placeholder="Enter Email" value="${sessionScope.principal.email }" required/>
		</div>
		
		<div class="d-flex justify-content-end">
			<button type="button" class="btn btn-info" onClick="goPopup();">주소검색</button>
		</div>
		
		<div class="form-group">
			<input type="text" name="address" id="address" class="form-control" placeholder="Enter Address" value="${sessionScope.principal.address }" required readonly/>
		</div>
		
		<br/>
		<button type="submit" class="btn btn-primary">회원정보수정</button>
	</form>
</div>

<script>
	function goPopup(){
		var pop = window.open("/blog/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	}
	
	function jusoCallBack(roadFullAddr){
		var addressEl = document.querySelector("#address");
		addressEl.value = roadFullAddr;
	}
</script>

</body>
</html>