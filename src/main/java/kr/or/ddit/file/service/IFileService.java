package kr.or.ddit.file.service;

import java.util.List;

import kr.or.ddit.file.vo.FileVo;

public interface IFileService {
	
	int insertFile(FileVo file);
	
	int updateFile(FileVo file);
	
	FileVo getOneFile(int file_no);
	
	List<FileVo> getFile(int post_no);

}
