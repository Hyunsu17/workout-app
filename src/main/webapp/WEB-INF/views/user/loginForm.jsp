<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp" %>

<div class="container">
<form action="/auth/loginProc" method="post">
  
  <div class="form-group">
    <label for="username">User name</label>
    <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
  </div>
  
  <div class="form-group">
    <label for="password">Password:</label>
    <input type="password" name ="password" class="form-control" placeholder="Enter password" id="password">
  </div>

  <div class="form-group form-check">
    <label class="form-check-label">
      <input class="form-check-input" name="remember" type="checkbox"> Remember me
    </label>
  </div>
  	
  	<button id="btn-login" class="btn btn-primary">로그인</button>
  	<a href="https://kauth.kakao.com/oauth/authorize?client_id=263627d78c01c77316c66238b918bf02&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code
"><img height="38px" src="/image/kakao_login_button.png"/></a>
  	
</form>


  
</div>
<%@include file="../layout/footer.jsp" %>




