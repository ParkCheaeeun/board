package kr.or.ddit.reply.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.file.vo.FileVo;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.post.vo.PostVO;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.reply.vo.ReplyVO;
import kr.or.ddit.session.UserSession;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/replyModify")
public class replyModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(replyModifyController.class);
	private ReplyServiceImpl replyService;
	private BoardServiceImpl boardService;
	private UserSession session;
	private PostServiceImpl postService;
	
	@Override
	public void init() throws ServletException {
		replyService = ReplyServiceImpl.getInstance();
		boardService = BoardServiceImpl.getInstance();
    	postService = PostServiceImpl.getInstance();
    	session = UserSession.getInstance();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reply_noStr = request.getParameter("reply_no");
		int reply_no = Integer.parseInt(reply_noStr);
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		
		Map<String, Object> map = postService.getPost(post_no);
		PostVO post = (PostVO) map.get("postVo");
		List<FileVo> fileList = (List<FileVo>) map.get("fileList");
		
		replyService.disabledReply(reply_no);
		List<BoardVO> boardList = boardService.getAllBoardList();
		List<ReplyVO> replyList = replyService.getReplyList(post_no);
		
		request.setAttribute("login_id", session.getUser().getUserId());
		request.setAttribute("boardList", boardList);
		request.setAttribute("replyList", replyList);
		request.setAttribute("fileList", fileList);
		request.setAttribute("post", post);
		
		request.getRequestDispatcher("/board/post.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reply_noStr = request.getParameter("reply_no");
		String content = request.getParameter("reply_content");
		content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
		
		logger.debug("reply_noStr : {} " , reply_noStr);
		logger.debug("content : {} " , content);
		int reply_no = Integer.parseInt(reply_noStr);
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		
		Map<String, Object> map = postService.getPost(post_no);
		PostVO post = (PostVO) map.get("postVo");
		List<FileVo> fileList = (List<FileVo>) map.get("fileList");
		
		ReplyVO replyVo = new ReplyVO();
		replyVo.setContent(content);
		replyVo.setReply_no(reply_no);
		replyVo.setPost_no(post_no);
		
		replyService.updateReply(replyVo);
		List<BoardVO> boardList = boardService.getAllBoardList();
		List<ReplyVO> replyList = replyService.getReplyList(post_no);
		
		request.setAttribute("login_id", session.getUser().getUserId());
		request.setAttribute("boardList", boardList);
		request.setAttribute("replyList", replyList);
		request.setAttribute("fileList", fileList);
		request.setAttribute("post", post);
		
		request.getRequestDispatcher("/board/post.jsp").forward(request, response);
	}

}
