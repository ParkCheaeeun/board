package kr.or.ddit.file.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.file.vo.FileVo;

public interface IFileDao {
	
	int insertFile(SqlSession sqlSession, FileVo file);
	
	int updateFile(SqlSession sqlSession, FileVo file);
	
	FileVo getOneFile(SqlSession sqlSession, int file_no);
	
	List<FileVo> getFile(SqlSession sqlSession, int post_no);
}
