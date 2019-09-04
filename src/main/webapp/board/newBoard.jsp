<%@page import="kr.or.ddit.session.UserSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <title>Board</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$(".dropdown-menu li a").click(function(){
    			  var selText = $(this).text();
    			  $(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
    			  $(this).parents('.btn-group').find('.use').val(selText);
    		});
    	})
    </script>
  </head>

  <body>

    <%@ include file="../main/header.jsp" %>

    <div class="container-fluid">
      <div class="row">
        
        <%@ include file="../main/left.jsp" %>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <form action="${pageContext.request.contextPath}/NewBoard" method="post">
	            <div class="form-group">
	            <label style="float:left; margin-right:10px; margin-top:6px;">게시판이름 </label>
				   <input type="text" style="width:500px; float:left; margin-right:15px;" name="board_name" class="form-control" placeholder="게시판 이름">
				</div>
				
				<div class="btn-group" role="group" aria-label="...">
				  <div class="btn-group" role="group">
				    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
				       사용여부
				      <span class="caret"></span>
				    </button>
				    <ul class="dropdown-menu" role="menu">
				      <li><a href="#">사용</a></li>
				      <li><a href="#">미사용</a></li>
				    </ul>
				  </div>
				  <input type="hidden" name="board_no" value="${board.board_no }">
				  <input type="hidden" name="usable" class="use">
				  <button type="submit" class="btn btn-default">생성</button>
				</div>
            </form>
            
        <c:forEach items="${boardList }" var="board">
            <br>
            <form action="${pageContext.request.contextPath}/NewBoard" method="post">
	            <div class="form-group">
	            <label style="float:left; margin-right:10px; margin-top:6px;">게시판이름 </label>
				   <input type="text" name="board_name" style="width:500px; float:left; margin-right:15px;"
				    class="form-control" value="${board.board_name }">
				</div>
				
				<div class="btn-group" role="group" aria-label="...">
				  <div class="btn-group" role="group">
				    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
				    <c:choose>
				    	<c:when test="${board.usable == 'N' }">
				 		  미사용
				    	</c:when>
				    	<c:otherwise>
				    	사용
				    	</c:otherwise>
				    </c:choose>
				      <span class="caret"></span>
				    </button>
				    <ul class="dropdown-menu" role="menu">
				      <li><a href="#">사용</a></li>
				      <li><a href="#">미사용</a></li>
				    </ul>
				  </div>
				 	<input type="hidden" name="board_no" value="${board.board_no }">
				 	<input type="hidden" name="usable" class="use">
				  <button type="submit" class="btn btn-default">수정</button>
				</div>
            </form>
        </c:forEach>
        
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  </body>
</html>
