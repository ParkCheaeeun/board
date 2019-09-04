package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
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
import kr.or.ddit.file.service.FileServiceImpl;
import kr.or.ddit.file.vo.FileVo;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.post.vo.PostVO;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.reply.vo.ReplyVO;
import kr.or.ddit.session.UserSession;
import kr.or.ddit.util.FileuploadUtil;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/writePost")
public class writePostController extends HttpServlet {
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
	
	//post update
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		List<BoardVO> boardList = boardService.getAllBoardList();
		String post_content = request.getParameter("ir1");
		String post_title = request.getParameter("title");
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		
		PostVO postVo = new PostVO();
		postVo.setPost_no(post_no);
		postVo.setPost_title(post_title);
		postVo.setPost_content(post_content);
		
		List<FileVo> list = new ArrayList<FileVo>();
		//파일
		String fileName = "";
		Collection<Part> parts = request.getParts();
		for(Part part : parts) {
			if(part.getName().equals("file")) {
				logger.debug("part.getHeader : {}", part.getHeader("Content-Disposition"));
				fileName = FileuploadUtil.getFilename(part.getHeader("Content-Disposition"));
				FileVo file = new FileVo();
				file.setUpload_file_name(fileName);
				file.setReal_file_route(FileuploadUtil.getpath());
				list.add(file);
			}
		}
		
		postService.updatePost(postVo, list);
		
		Map<String, Object> map = postService.getPost(post_no);
		postVo = (PostVO) map.get("postVo");
		List<FileVo> fileList = (List<FileVo>) map.get("fileList");
		
		List<ReplyVO> replyList = replyService.getReplyList(post_no);
		
		request.setAttribute("fileList", fileList);
		request.setAttribute("boardList", boardList);
		request.setAttribute("post", postVo);
		request.setAttribute("replyList", replyList);
		request.setAttribute("login_id", session.getUser().getUserId());
		
		request.getRequestDispatcher("/board/post.jsp").forward(request, response);
	}

}
