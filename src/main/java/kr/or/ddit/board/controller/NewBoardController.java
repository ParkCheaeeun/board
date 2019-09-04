package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.post.vo.PostVO;

@WebServlet("/NewBoard")
public class NewBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(NewBoardController.class);
	private BoardServiceImpl boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = BoardServiceImpl.getInstance();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoardVO> boardList = boardService.getAllBoardList();
		request.setAttribute("boardList", boardList);
		
		request.getRequestDispatcher("/board/newBoard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String board_no_str = request.getParameter("board_no");
		String usable = request.getParameter("usable");
		String board_name = request.getParameter("board_name");
		logger.debug("board_no : {}" , board_no_str);
		logger.debug("usable : {}" , usable);
		
		if(board_no_str.equals("")) {
			if(usable.equals("미사용")) usable = "N";
			else usable = "Y";
			
			BoardVO boardVo = new BoardVO(board_name, usable);
			boardService.insertBoard(boardVo);
		}else {
			if(usable.equals("미사용")) usable = "N";
			else usable = "Y";
			int board_no = Integer.parseInt(board_no_str);
			
			BoardVO boardVo = new BoardVO(board_name, usable);
			boardVo.setBoard_no(board_no);
			boardService.updateBoard(boardVo);
		}
		
		List<BoardVO> boardList = boardService.getAllBoardList();
		request.setAttribute("boardList", boardList);
		
		request.getRequestDispatcher("/board/newBoard.jsp").forward(request, response);
	}

}
