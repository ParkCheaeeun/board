<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/js.cookie.js"></script>
    
    <script type="text/javascript">
    $(document).ready(function(){
    	
    	var userId = Cookies.get("userId");
    	if(userId != undefined){
    		$("#userId").val(userId);
			$("#rememberMe").prop("checked", true);
			$('#pass').focus();
    	}
    	
    	//signin btn 클릭 이벤트 핸들러
    	$('#signinBtn').on('click', function(){
    		
    	});
    	
    })
    	function getCookie(cookieId){
    		var cookies = document.cookie.split('; ');
    		
    		for(var i=0; i<cookies.length; i++){
    			var cookie = cookies[i];
    			var cookieNmVal = cookie.split("=");
    			
    			if(cookieId == cookieNmVal[0]){
    				return cookieNmVal[1];
    			}
    		}
    		return "";
    	}
    	
    	function setCookie(cookieNm, cookieValue, expires){
    		var dt = new Date();
    		dt.setDate(dt.getDate() + Number(expires));
    		
    		document.cookie = cookieNm + "=" + cookieValue + "; path=/; expires="
    						  + dt.toGMTString();
    	}
    	
    	function deleteCookie(cookieName){
    		setCookie(cookieName, "", -1);
    	}
    	
    </script>
  </head>

  <body>

    <div class="container">
      <form id="frm" class="form-signin" action="<%= request.getContextPath()%>/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="userId" class="sr-only">User Id</label>
        
        <% 
        	String userId = request.getParameter("userId");
        	userId = userId == null ? "" : userId;
        %>
        
        <input type="text" id="userId" name="userId" class="form-control" 
        	   placeholder="id" required autofocus value="sally">
        <label for="pass" class="sr-only">Password</label>
        <input type="password" id="pass" name="pass" value="123"
        	   class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" name="rememberMe" value="remember-me" id="rememberMe"> Remember me
          </label>
        </div>
        <button type="submit" class="btn btn-lg btn-primary btn-block" id="signinBtn">Sign in</button>
      </form>
	
    </div> <!-- /container -->

  </body>
</html>
