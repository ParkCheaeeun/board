package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.post.vo.PostVO;
import kr.or.ddit.session.UserSession;
import kr.or.ddit.user.service.UserServiceImpl;
import kr.or.ddit.user.vo.UserVO;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserServiceImpl userService;
	UserSession session;
	private BoardServiceImpl boardService;
    private PostServiceImpl postService;
	
	@Override
	public void init() throws ServletException {
		userService = UserServiceImpl.getInstance();
		session = UserSession.getInstance();
		boardService = BoardServiceImpl.getInstance();
    	postService = PostServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		UserVO userVo = userService.getUser(user_id);
		String pageStr = request.getParameter("page");
		String pagesizeStr = request.getParameter("pagesize");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int pageSize = pagesizeStr == null ? 10 : Integer.parseInt(pagesizeStr);
		
		Page p = new Page(page, pageSize);
		
		int paginationSize = 1;
		if(userVo != null) {
			if(pass.equals(userVo.getPass())) {
				session.setUser(userVo);
				
				String board_no = request.getParameter("board_no");
				String board_name = "";
				
				List<BoardVO> boardList = boardService.getAllBoardList();
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
				
				request.setAttribute("boardList", boardList);
				request.setAttribute("postList", postList);
				request.setAttribute("board_name", board_name);
				request.setAttribute("board_no", board_no);
				request.setAttribute("user", session.getUser());
				
				request.getRequestDispatcher("/main/main.jsp").forward(request, response);
			}else {
				doGet(request, response);
			}
		}
		
		
	}

}
