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
	<script type="text/javascript" src="../js/HuskyEZCreator.js" charset="utf-8"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script src="/js/HuskyEZCreator.js"></script>
    <script type="text/javascript">
    	$(function(){
    		window.location.reload();
    	})
    </script>
  </head>

  <body>

    <%@ include file="../main/header.jsp" %>

    <div class="container-fluid">
      <div class="row">
        
        <%@ include file="../main/left.jsp" %>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          
          <h2 class="sub-header">${board_name }</h2>
          <div class="table-responsive">
            
		<input type="hidden" value='<%=request.getParameter("post_content") %>' id="hiddenInput">
		<div class="container" role="main">
				<div class="mb-3">
					<form id="frm" action="${pageContext.request.contextPath}/replyBoard" method="post" enctype="multipart/form-data">
						<input type="hidden" value="<%= request.getParameter("board_no") %>" name="board_no">
						<input type="hidden" value="<%= request.getParameter("post_no") %>" name="post_no">
						<input type="hidden" value="${post_no }" name="post_no">
						<div class="mb-3">
							<label for="title">제목</label>
							<input type="text" style="width : 770px;" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">
						</div><br>
						<textarea name="ir1" id="ir1" rows="10" cols="100" style="width:766px; height:412px; display:none;"></textarea>
						<div class="filewrap">
							파일 추가: 
							<button type="button" class="glyphicon glyphicon-plus" class="fileBtn" onclick="fileUp()"></button>
  							<input type="file" style="" id="file" name="file">
						</div><br>
						<button type="button" onclick="submitContents(this);" id="savebutton" class="btn btn-sm btn-primary">저장</button>
						<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
					</form>
					<script type="text/javascript">
					var oEditors = [];
					
					// 추가 글꼴 목록
					//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
					
					nhn.husky.EZCreator.createInIFrame({
						oAppRef: oEditors,
						elPlaceHolder: "ir1",
						sSkinURI: "SmartEditor2Skin.html",	
						htParams : {
							bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseVerticalResizer : false,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
							//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
							fOnBeforeUnload : function(){
								//alert("완료!");
							}
						}, //boolean
						
						fOnAppLoad : function(){
							if($('#hiddenInput').val() != 'null'){
								oEditors.getById["ir1"].exec("PASTE_HTML", []);
							}
							
						},
						fCreator: "createSEditor2"
					});
					
					function pasteHTML(filepath) {
						var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
						oEditors.getById["ir1"].exec("PASTE_HTML", [sHTML]);
						var sHTML = '';
					}
					
					function showHTML() {
						var sHTML = oEditors.getById["ir1"].getIR();
						alert(sHTML);
					}
						
					function submitContents(elClickedObj) {
						oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
						
						// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
						
						try {
							elClickedObj.form.submit();
						} catch(e) {}
					}
					
					function setDefaultFont() {
						var sDefaultFont = '궁서';
						var nFontSize = 24;
						oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
					}
					var i = 0;
					function fileUp(){
						
						if($('input[type=file]').length > 4){
							return;
						}
						i++;
						var input = $('<input type="file" class="'+ i +'" name="file"/>');
						var button = $('<button type="button" id="'+ i +'" class="glyphicon glyphicon-minus" onclick="fileRemove('+i+')"></button>')
						
						$('.filewrap').append(input);
						$('.filewrap').append(button);
						
					}
					
					function fileRemove(i){
						if($('input[type=file]').length > 1){
							$('.'+i).filter('input[type=file]').remove();
							$('#'+i).filter('button[type=button]').remove();
						}
					}
					</script>
				</div>
		</div>

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
  </body>
</html>
