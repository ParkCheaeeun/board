package kr.or.ddit.post.controller;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.file.service.FileServiceImpl;
import kr.or.ddit.file.vo.FileVo;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.post.vo.PostVO;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.session.UserSession;
import kr.or.ddit.util.FileuploadUtil;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/PostInsert")
public class PostInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(writePostController.class);
	
	private BoardServiceImpl boardService;
    private PostServiceImpl postService;
    private ReplyServiceImpl replyService;
    private UserSession session;
    private FileServiceImpl fileService;
    
    @Override
    public void init() throws ServletException {
    	fileService = FileServiceImpl.getInstance();
    	boardService = BoardServiceImpl.getInstance();
    	postService = PostServiceImpl.getInstance();
    	session = UserSession.getInstance();
    	replyService = ReplyServiceImpl.getInstance();
    }
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoardVO> boardList = boardService.getAllBoardList();
		String file_no_str = request.getParameter("file_no");
		String post_content = request.getParameter("ir1");
		String post_title = request.getParameter("title");
		post_content = new String(post_content.getBytes("ISO-8859-1"), "UTF-8");
		post_title = new String(post_title.getBytes("ISO-8859-1"), "UTF-8");
		String post_no_str = request.getParameter("post_no");
		
		logger.debug("file_no : {} " , file_no_str);
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		String pageStr = request.getParameter("page");
		String pagesizeStr = request.getParameter("pagesize");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int pageSize = pagesizeStr == null ? 10 : Integer.parseInt(pagesizeStr);
		Page p = new Page(page, pageSize);
		
		int paginationSize = 1;
		List<PostVO> postList = null;
		
		request.setAttribute("post_content", post_content);
		request.setAttribute("post_title", post_title);
		request.setAttribute("boardList", boardList);
		
		logger.debug("ir1 : {}",request.getParameter("ir1"));
		
		PostVO postVo = new PostVO(board_no, post_title, post_content, session.getUser().getUserId());
		if(post_no_str != null) {
			int post_no = Integer.parseInt(request.getParameter("post_no"));
			postVo.setParent_post_no(post_no);
		}
		
		BoardVO vo = boardService.getBoard(board_no);
		p.setBoard_no(board_no);
		
		String board_name = vo.getBoard_name();
		
		String applicationPath = request.getServletContext().getRealPath("");
		List<FileVo> list = new ArrayList<FileVo>();
		//파일
		String fileName = "";
		Collection<Part> parts = request.getParts();
		for(Part part : parts) {
			if(part.getName().equals("file")) {
				logger.debug("part.getHeader : {}", part.getHeader("Content-Disposition"));
				fileName = FileuploadUtil.getFilename(part.getHeader("Content-Disposition"));
				
				String path = "e:\\upload\\"+ UUID.randomUUID().toString() +
						FileuploadUtil.getFileExtension(part.getHeader("Content-Disposition"));
				
				part.write(path);
				
				FileVo file = new FileVo();
				file.setUpload_file_name(fileName);
				file.setReal_file_route(path);
				list.add(file);
			}
		}
		
		postService.insertPost(postVo, list);
		
		Map<String, Object> resultMap = postService.getPostPagingList(p);
		postList = (List<PostVO>) resultMap.get("postList");
		paginationSize = (int) resultMap.get("paginationSize");
		int total = postService.getPostList(p.getBoard_no()).size();
		int lastPage = total/pageSize+1;
		
		request.setAttribute("pageVo", p);
		request.setAttribute("postList", postList);
		request.setAttribute("paginationSize", paginationSize);
		
		request.setAttribute("fileList", list);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("boardList", boardList);
		request.setAttribute("board_name", board_name);
		request.setAttribute("board_no", board_no);
		request.setAttribute("user", session.getUser());
		
		request.getRequestDispatcher("/main/main.jsp").forward(request, response);
	}

}
