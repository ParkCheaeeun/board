package kr.or.ddit.file.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.file.vo.FileVo;

public class FileDaoImpl implements IFileDao {
	
	private static FileDaoImpl dao;
	
	public static FileDaoImpl getInstance() {
		if(dao == null) dao = new FileDaoImpl();
		return dao;
	}

	@Override
	public int insertFile(SqlSession sqlSession, FileVo file) {
		return sqlSession.insert("file.insertFile", file);
	}

	@Override
	public int updateFile(SqlSession sqlSession, FileVo file) {
		return sqlSession.update("file.updateFile", file);
	}

	@Override
	public List<FileVo> getFile(SqlSession sqlSession, int post_no) {
		return sqlSession.selectList("file.getFile", post_no);
	}

	@Override
	public FileVo getOneFile(SqlSession sqlSession, int file_no) {
		return sqlSession.selectOne("file.getOneFile", file_no);
	}
	
	
	
	

}
