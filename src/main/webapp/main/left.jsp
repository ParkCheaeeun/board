<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-sm-3 col-md-2 sidebar">
  <ul class="nav nav-sidebar">
     <li><a href="${pageContext.request.contextPath}/NewBoard">신규 게시판 생성</a></li>
  </ul>
  <ul class="nav nav-sidebar">
  	<c:forEach items="${boardList }" var="board">
  		<c:choose>
  			<c:when test="${board.usable == 'N' }">
         		<li><a style="color : lightgray;" href="#">비활성화된 게시판입니다.</a></li>
  			</c:when>
  			<c:when test="${board.board_no == board_no }">
         		<li class="active"><a href="${pageContext.request.contextPath}/Main?board_no=${board.board_no}">${board.board_name }</a></li>
  			</c:when>
  			<c:otherwise>
         		<li><a href="${pageContext.request.contextPath}/Main?board_no=${board.board_no}">${board.board_name }</a></li>
  			</c:otherwise>
  		</c:choose>
  	</c:forEach>
  </ul>
  
</div>