<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <br><br>
        <div class="container">
        <label for="content">comment</label>
        <form name="commentInsertForm" action="${pageContext.request.contextPath}/replyInsert" method="post">
            <div class="input-group">
               <input type="hidden" name="post_no" value="${post.post_no}"/>
               <c:choose>
               		<c:when test="${login_id == null }">
		               <textarea rows="3" class="form-control" name="content" placeholder="로그인 후 이용해주세요."></textarea>
		               <span class="input-group-btn">
                    		<button disabled style="height:74px;" class="btn btn-success" type="button" name="commentInsertBtn">등록</button>
		               </span>
               		</c:when>
               		<c:otherwise>
		               <textarea maxlength="500" rows="3" id="content" class="form-control" name="content" placeholder="내용을 입력하세요."></textarea>
		               <span class="input-group-btn">
                    		<button style="height:74px;" id="commentBtn" class="btn btn-success" type="submit" name="commentInsertBtn">등록</button>
		               </span>
               		</c:otherwise>
               </c:choose>
              </div>
        </form>
    </div>
    
<c:forEach items="${replyList }" var="reply">
	<c:choose>
		<c:when test="${reply.usable != 'N'}">
			<br>
			<div class="container">
			    <div id="commentList">
			    	<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">
			           <div class="commentInfo">댓글번호 : ${reply.reply_no }  / 작성자 :${reply.user_id } &nbsp; ${reply.write_date_str }
			           <c:if test="${reply.user_id == login_id }">
			            <a onclick="commentUpdate('${reply.content }',${reply.reply_no })"> 수정 </a>
			            <a onclick="commentDelete(${reply.reply_no })"> 삭제 </a>
			           </c:if>
			           </div>
			           <div class="commentContent">
			              <p class="p_content${reply.reply_no }"> 내용 :${reply.content }</p>
			           </div>
			        </div>
			    </div>
			</div>
		</c:when>
		<c:otherwise>
			<br>
			<div class="container">
			    <form id="commentListForm" name="commentListForm" method="post">
			        <div id="commentList">
			        	<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">
			               <div class="commentInfo">
			               	<span style="color : lightgray;">삭제된 댓글 입니다.</span>
			               </div>
			            </div>
			        </div>
			    </form>
			</div>
		</c:otherwise>
	</c:choose>
</c:forEach>

<form id="rpDeleteForm" name="delete" method="get" action="${cp }/replyModify">
	<input type="hidden" id="deletebtn" name="reply_no">
	<input type="hidden" name="post_no" value="${post.post_no}"/>
</form>
<form id="rpUpdateForm" name="updateForm" method="post" action="${cp }/replyModify">
	<input type="hidden" id="updateRn" name="reply_no">
	<input type="hidden" name="post_no" value="${post.post_no}"/>
	<input type="hidden" class="ryCont" name="reply_content" value="${reply.content }">
</form>
<script>
function commentDelete(reply_no){
	$('#deletebtn').val(reply_no);
	$('#rpDeleteForm').submit();
}
function commentUpdate(reply_content, reply_no){
	
	if($('#inputContent').length > 0){
		$('#inputContent').remove();
		$('#updateBtn').remove();
		$('.p_content'+rplyno).append(rplycontent);
	}
	
	var input = $('<input type="text" id="inputContent" name="content"/>');
	var button = $('<button id="updateBtn" class="btn btn-success" type="button" onclick="updateSubmit()">수정</button>')
	
	content(reply_content, reply_no);
	$('.p_content'+reply_no).html('');
	$('.p_content'+reply_no).append(input);
	$('.p_content'+reply_no).append(button);
	$('#inputContent').focus();
	$('#inputContent').val(reply_content);
	$('#updateRn').val(reply_no);
	console.log(reply_no);
	
}

var rplycontent
var rplyno
function content(content, reply_no){
	rplycontent = content;
	rplyno = reply_no
}

function updateSubmit(){
	$('.ryCont').val($('#inputContent').val());
	$('#rpUpdateForm').submit();
}
</script>
