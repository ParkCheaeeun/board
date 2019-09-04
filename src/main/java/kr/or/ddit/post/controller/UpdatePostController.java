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

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.post.vo.PostVO;
import kr.or.ddit.session.UserSession;

@WebServlet("/updatePost")
public class UpdatePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UpdatePostController.class);
	private PostServiceImpl postService;
	private BoardServiceImpl boardService;
	private UserSession session;
	
	@Override
	public void init() throws ServletException {
		postService = PostServiceImpl.getInstance();
		boardService = BoardServiceImpl.getInstance();
		session = UserSession.getInstance();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		String pageStr = request.getParameter("page");
		String pagesizeStr = request.getParameter("pagesize");
		
		List<BoardVO> boardList = boardService.getAllBoardList();
		
		logger.debug("post_no : {}", post_no);
		
		int cnt = postService.disabledPost(post_no);
		
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int pageSize = pagesizeStr == null ? 10 : Integer.parseInt(pagesizeStr);
		
		Page p = new Page(page, pageSize);
		
		int paginationSize = 1;
		List<PostVO> postList = null;
		
		BoardVO vo = boardService.getBoard(board_no);
		p.setBoard_no(board_no);
		
		String board_name = vo.getBoard_name();
		
		
		Map<String, Object> resultMap = postService.getPostPagingList(p);
		postList = (List<PostVO>) resultMap.get("postList");
		paginationSize = (int) resultMap.get("paginationSize");
		int total = postService.getPostList(p.getBoard_no()).size();
		int lastPage = total/pageSize+1;
		
		request.setAttribute("pageVo", p);
		request.setAttribute("postList", postList);
		request.setAttribute("paginationSize", paginationSize);
		
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("board_name", board_name);
		request.setAttribute("board_no", board_no);
		request.setAttribute("user", session.getUser());
			
		request.setAttribute("boardList", boardList);
		request.setAttribute("postList", postList);
		request.getRequestDispatcher("/main/main.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		List<BoardVO> boardList = boardService.getAllBoardList();
		List<PostVO> postList = postService.getPostList(board_no);
		
		String post_no = request.getParameter("post_no");
		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("post_conent");
		
		logger.debug("post_no : {}", post_no);
		logger.debug("post_title : {}", post_title);
		logger.debug("post_content : {}", post_content);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("postList", postList);
		request.setAttribute("post_conent", post_content);
		request.setAttribute("post_title", post_title);
		request.setAttribute("post_no", post_no);
		
		request.getRequestDispatcher("/SE2/postwrite.jsp").forward(request, response);
	}

}
