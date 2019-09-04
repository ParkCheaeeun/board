package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.file.vo.FileVo;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.post.vo.PostVO;
import kr.or.ddit.session.UserSession;
import kr.or.ddit.util.FileuploadUtil;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/replyBoard")
public class ReplyBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardServiceImpl boardService;
	private PostServiceImpl postService;
	private UserSession session;
	
	 @Override
	 public void init() throws ServletException {
	 	boardService = BoardServiceImpl.getInstance();
	 	postService = PostServiceImpl.getInstance();
	 	session = UserSession.getInstance();
	 }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		List<BoardVO> boardList = boardService.getAllBoardList();
		String user_id = session.getUser().getUserId();
		
		String title = request.getParameter("title");
		String content = request.getParameter("ir1");
		
		PostVO postVo = new PostVO(board_no, title, content, user_id);
		postVo.setParent_post_no(post_no);
		
		List<FileVo> list = new ArrayList<FileVo>();
		//파일
		String fileName = "";
		Collection<Part> parts = request.getParts();
		for(Part part : parts) {
			if(part.getName().equals("file")) {
				fileName = FileuploadUtil.getFilename(part.getHeader("Content-Disposition"));
				
				String path = "e:\\upload\\"+ UUID.randomUUID().toString() +
						FileuploadUtil.getFileExtension(part.getHeader("Content-Disposition"));
				
				part.write(path);
				
				FileVo file = new FileVo();
				file.setUpload_file_name(fileName);
				file.setReal_file_route(FileuploadUtil.getpath());
				list.add(file);
			}
		}
		
		postService.insertPost(postVo,list);
		
		String board_name = "";
		String pageStr = request.getParameter("page");
		String pagesizeStr = request.getParameter("pagesize");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int pageSize = pagesizeStr == null ? 10 : Integer.parseInt(pagesizeStr);
		
		Page p = new Page(page, pageSize);
		int paginationSize = 1;
		List<PostVO> postList = null;
		BoardVO vo = boardService.getBoard(board_no);
		p.setBoard_no(board_no);
		
		board_name = vo.getBoard_name();
		
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
		
		request.getRequestDispatcher("/main/main.jsp").forward(request, response);
	}

}
