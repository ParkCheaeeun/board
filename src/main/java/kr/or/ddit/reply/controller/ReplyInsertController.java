package kr.or.ddit.reply.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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

/**
 * Servlet implementation class ReplyInsertController
 */
@WebServlet("/replyInsert")
public class ReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReplyInsertController.class);
    private ReplyServiceImpl replyService;
    private BoardServiceImpl boardService;
    private PostServiceImpl postService;
    private UserSession session;
    
    @Override
    public void init() throws ServletException {
    	boardService = BoardServiceImpl.getInstance();
    	replyService = ReplyServiceImpl.getInstance();
    	postService = PostServiceImpl.getInstance();
    	session = UserSession.getInstance();
    }
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String content = request.getParameter("content");
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		
		List<BoardVO> boardList = boardService.getAllBoardList();
		request.setAttribute("boardList", boardList);
		
		ReplyVO vo = new ReplyVO();
		vo.setContent(content);
		vo.setPost_no(post_no);
		vo.setUser_id(session.getUser().getUserId());
		
		replyService.insertReply(vo);
		
		Map<String, Object> map = postService.getPost(post_no);
		PostVO post = (PostVO) map.get("postVo");
		List<FileVo> fileList = (List<FileVo>) map.get("fileList");
		
		List<ReplyVO> replyList = replyService.getReplyList(post_no);
		
		request.setAttribute("post", post);
		request.setAttribute("replyList", replyList);
		request.setAttribute("fileList", fileList);
		request.setAttribute("login_id", session.getUser().getUserId());
		
		logger.debug("reply post_no : {}", post_no);
		
		request.getRequestDispatcher("/board/post.jsp").forward(request, response);
	}

}
