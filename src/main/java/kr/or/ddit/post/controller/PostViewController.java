package kr.or.ddit.post.controller;

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

import kr.or.ddit.board.controller.MainController;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.file.service.FileServiceImpl;
import kr.or.ddit.file.vo.FileVo;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.post.vo.PostVO;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.reply.vo.ReplyVO;
import kr.or.ddit.session.UserSession;

@WebServlet("/post")
public class PostViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
    private BoardServiceImpl boardService;
    private PostServiceImpl postService;
    private UserSession session;
    private ReplyServiceImpl replyService;
    private FileServiceImpl fileService;
    
    @Override
    public void init() throws ServletException {
    	fileService = FileServiceImpl.getInstance();
    	boardService = BoardServiceImpl.getInstance();
    	postService = PostServiceImpl.getInstance();
    	session = UserSession.getInstance();
    	replyService = ReplyServiceImpl.getInstance();
    	
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		String board_name = request.getParameter("board_name");
		board_name = new String(board_name.getBytes("ISO-8859-1"), "UTF-8");
		
		List<BoardVO> boardList = boardService.getAllBoardList();
		Map<String, Object> map = postService.getPost(post_no);
		PostVO postVo = (PostVO) map.get("postVo");
		List<FileVo> fileList = (List<FileVo>) map.get("fileList");
		
		List<ReplyVO> replyList = replyService.getReplyList(post_no);
		
		request.setAttribute("fileList", fileList);
		request.setAttribute("replyList", replyList);
		request.setAttribute("boardList", boardList);
		request.setAttribute("board_name", board_name);
		request.setAttribute("post", postVo);
		
		if(session.getUser() != null) {
			String login_id = session.getUser().getUserId();
			request.setAttribute("login_id", login_id);
			logger.debug("login_id : {} " , login_id);
		}
		
		logger.debug("board_name : {} " , board_name);
		
		request.getRequestDispatcher("/board/post.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/SE2/postwrite.jsp").forward(request, response);
	}

}
