package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.List;

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
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.post.vo.PostVO;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
    private BoardServiceImpl boardService;
    private PostServiceImpl postService;
    
    @Override
    public void init() throws ServletException {
    	boardService = BoardServiceImpl.getInstance();
    	postService = PostServiceImpl.getInstance();
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_no = request.getParameter("board_no");
		String board_name = "";
		
		List<BoardVO> boardList = boardService.getAllBoardList();
		List<PostVO> postList = null;
		
		if(board_no == null) {
			postList = postService.getPostList(boardList.get(0).getBoard_no());
			board_name = boardList.get(0).getBoard_name();
			
		}else {
			int no = Integer.parseInt(board_no);
			postList = postService.getPostList(no);
			BoardVO vo = boardService.getBoard(no);
			
			board_name = vo.getBoard_name();
		}
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("postList", postList);
		request.setAttribute("board_name", board_name);
		request.setAttribute("board_no", 1);
		
		logger.debug("board_name : {}", board_name);
		
		request.getRequestDispatcher("/main/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
