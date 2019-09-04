package kr.or.ddit.post.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.file.service.FileServiceImpl;
import kr.or.ddit.file.service.IFileService;
import kr.or.ddit.file.vo.FileVo;

@WebServlet("/download")
public class downloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IFileService fileService;
	
    @Override
    public void init() throws ServletException {
    	fileService = FileServiceImpl.getInstance();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int file_no = Integer.parseInt(request.getParameter("file_no"));
		FileVo file = fileService.getOneFile(file_no);
		
		ServletOutputStream sos = response.getOutputStream();
		
		File files = new File(file.getReal_file_route());
		FileInputStream fis = new FileInputStream(files);
		
		byte[] buff = new byte[512];
		int len = 0;
		
		while ((len = fis.read(buff, 0, 512)) != -1) {
			sos.write(buff, 0, len);
		}
		fis.close();
		sos.close();
	}


}
