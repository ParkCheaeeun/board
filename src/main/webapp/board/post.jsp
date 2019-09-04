<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="kr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Board</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
  <form action="${pageContext.request.contextPath}/SE2/postwrite.jsp" id="updateForm" method="post">
  	<input type="hidden" name="post_no" value="${post.post_no }">
  	<input type="hidden" name="board_no" value="${post.board_no }">
  	<input type="hidden" name="post_title" value="${post.post_title }${post_title }">
  	<input type="hidden" name="post_content" value='${post.post_content }${post_content }'>
  </form>

    <%@ include file="../main/header.jsp" %>

    <div class="container-fluid">
      <div class="row">
        
        <%@ include file="../main/left.jsp" %>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          
          <h2 class="sub-header">${board_name }</h2>
          <div class="table-responsive">
            
            <article>

		<div class="container" role="main">
			<form name="form" id="form" role="form" method="post" action="">
				<div class="mb-3">
					<h3>${post.post_title }${post_title }<span style="float : right; color : gray;">${post.post_no }</span></h3>
					<label style="color : lightgray; font-size:15px;">${post.write_date_str }</label>
					<label style="color : lightgray; font-size:15px; margin-left: 15px;">${post.user_id }</label>
					
					<c:if test="${post.user_id == login_id }">
						<label style="float:right;">
						    <a href="${pageContext.request.contextPath}/updatePost?post_no=${post.post_no}&board_no=${post.board_no}"
						     style="text-decoration: none;">삭제</a>
						</label>
						<label style="float:right;">
							<a onclick="update();" style="text-decoration: none;">수정&nbsp;&n
							-`````````````
							bsp;</a>
						</label>
					</c:if>
				</div>
				<hr>
				<div class="mb-3">
					<label for="content" style="text-align : right;">첨부파일 &nbsp;&nbsp;
						<c:forEach items="${fileList }" var="file">
							<a href="${cp }/download?file_no=${file.file_no}" download="${file.upload_file_name }">${file.upload_file_name }</a>&nbsp;/
						</c:forEach>
					</label>
					<br><br>
					${post.post_content }${post_content }
				</div>
				<br><br>
			</form>
					<form action="${cp }/SE2/replyPost.jsp" method="post">
				<c:if test="${login_id != null }">
						<button class="btn btn-success" type="submit" style="float:right;">답글</button>
						<input type="hidden" name="board_no" value="${post.board_no }">
						<input type="hidden" name="post_no" value="${post.post_no }">
				</c:if>
					</form>
				<div class="mb-3">
					
				<%@ include file="comment.jsp" %>
				</div>
				
		</div>

	</article>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="../../assets/js/vendor/holder.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    <script type="text/javascript">
    function update(){
    	$('#updateForm').submit();
    }
    
    </script>
  </body>
</html>
