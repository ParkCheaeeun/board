package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
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

@WebServlet(urlPatterns = {"/Main"}, loadOnStartup = 5)
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
    private BoardServiceImpl boardService;
    private PostServiceImpl postService;
    private UserSession session;
    
    @Override
    public void init() throws ServletException {
    	boardService = BoardServiceImpl.getInstance();
    	postService = PostServiceImpl.getInstance();
    	session = UserSession.getInstance();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_no = request.getParameter("board_no");
		String board_name = "";
		String pageStr = request.getParameter("page");
		String pagesizeStr = request.getParameter("pagesize");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int pageSize = pagesizeStr == null ? 10 : Integer.parseInt(pagesizeStr);
		
		Page p = new Page(page, pageSize);
		
		List<BoardVO> boardList = boardService.getAllBoardList();
		int paginationSize = 1;
		List<PostVO> postList = null;
		
		if(board_no == null) {
			board_name = boardList.get(0).getBoard_name();
			board_no = 1+"";
			p.setBoard_no(Integer.parseInt(board_no));
		}else {
			int no = Integer.parseInt(board_no);
			BoardVO vo = boardService.getBoard(no);
			p.setBoard_no(no);
			
			board_name = vo.getBoard_name();
		}
		
		Map<String, Object> resultMap = postService.getPostPagingList(p);
		postList = (List<PostVO>) resultMap.get("postList");
		paginationSize = (int) resultMap.get("paginationSize");
		int total = postService.getPostList(p.getBoard_no()).size();
		int lastPage = total/pageSize+1;
		
		request.setAttribute("pageVo", p);
		request.setAttribute("postList", postList);
		request.setAttribute("paginationSize", paginationSize);
		
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("boardList", boardList);
		request.setAttribute("board_name", board_name);
		request.setAttribute("board_no", board_no);
		request.setAttribute("user", session.getUser());
		
		logger.debug("board_no : {}", board_no);
		logger.debug("board_name : {}", board_name);
		logger.debug("user : {}", session.getUser());
		
		request.getRequestDispatcher("/main/main.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}


}
