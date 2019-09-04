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

    <%@ include file="header.jsp" %>

    <div class="container-fluid">
      <div class="row">
        
        <%@ include file="left.jsp" %>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          
          <h2 class="sub-header">${board_name }</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>글번호</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>작성일</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${postList }" var="post">
	              <c:choose>
	              	<c:when test="${post.usable == 'N' }">
		                <tr>
		                  <td></td>
		                  <td><a href="#"
		                   style="text-decoration: none; color : lightgray;">삭제된 게시글 입니다.</a></td>
		                  <td></td>
		                  <td></td>
		                </tr>
	              	</c:when>
	              	<c:otherwise>
		                <tr>
		                  <td>${post.post_no }</td>
		                  <td>
			                  <a href="${pageContext.request.contextPath}/post?post_no=${post.post_no }&board_name=${board_name}"
			                   style="text-decoration: none;">
			                  	 ${post.post_title }
			                   </a>
		                   </td>
		                  <td>${post.user_id }</td>
		                  <td>${post.write_date_str }</td>
		                </tr>
	              	</c:otherwise>
	              </c:choose>
              </c:forEach>
              </tbody>
            </table>
            <form id="writeForm" action="${pageContext.request.contextPath}/SE2/postwrite.jsp" method="get">
            	<input type="hidden" name="board_no" value="${board_no }">
            	<c:choose>
            		<c:when test="${user == null }">
	           	 		<button type="submit" class="btn btn-sm btn-primary" disabled>글작성</button>
	           	 		<label style="font-size : 9px">로그인 먼저 해주세요</label>
            		</c:when>
            		<c:otherwise>
	           	 		<button type="submit" class="btn btn-sm btn-primary">글작성</button>
            		</c:otherwise>
            	</c:choose>
            </form>
          </div>
          
          
          <div class="text-center">
			<ul class="pagination">
	   			 <li><a href="${cp }/Main?page=1&board_no=${board_no}">1...</a></li>
			<!-- 이전페이지 가기 -->
			 <c:choose>
				 <c:when test="${pageVo.page == 1}">
					 <li class="disabled">
			         	<span aria-hidden="true">&laquo;</span>
				     </li>
				 </c:when>
				 <c:otherwise>
					  <li>
				      <a href="${cp }/Main?page=${pageVo.page -1}&board_no=${board_no}" aria-label="Previous">
			          <span aria-hidden="true">&laquo;</span>
				      </a>
				      </li>
				 </c:otherwise>
			 </c:choose>
			
			<c:forEach begin="1" end="${lastPage}" var="page">
				<c:choose>
					<c:when test="${page == pageVo.page }">
						<li class="active"><span>${page}</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="${cp }/Main?page=${page}&board_no=${board_no}">${page}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음페이지 가기 -->
			<c:choose>
				<c:when test="${pageVo.page == paginationSize }">
					<li class="disabled">
				        <span aria-hidden="true">&raquo;</span>
				    </li>
				</c:when>
				<c:otherwise>
					<li>
				      <a href="${cp }/Main?page=${pageVo.page +1}&board_no=${board_no}" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				</c:otherwise>
			</c:choose>
   			 <li><a href="${cp }/Main?page=${lastPage}&board_no=${board_no}">...${lastPage }</a></li>
			</ul>
		</div>
          
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
  </body>
</html>
