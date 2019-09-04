package kr.or.ddit.file.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.file.dao.FileDaoImpl;
import kr.or.ddit.file.vo.FileVo;
import kr.or.ddit.util.MybatisUtil;

public class FileServiceImpl implements IFileService{
	
	private static FileServiceImpl service;
	private FileDaoImpl dao;
	
	public static FileServiceImpl getInstance() {
		if(service == null) service = new FileServiceImpl();
		return service;
	}
	
	FileServiceImpl(){
		dao = FileDaoImpl.getInstance();
	}
	
	@Override
	public int insertFile(FileVo file) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.insertFile(sqlSession, file);
		sqlSession.commit();
		sqlSession.close();
		
		return cnt;
	}

	@Override
	public int updateFile(FileVo file) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.updateFile(sqlSession, file);
		sqlSession.commit();
		sqlSession.close();
		
		return cnt;
	}

	@Override
	public List<FileVo> getFile(int post_no) {
		SqlSession sqlSession = MybatisUtil.getSession();
		return dao.getFile(sqlSession, post_no);
	}

	@Override
	public FileVo getOneFile(int file_no) {
		SqlSession sqlSession = MybatisUtil.getSession();
		return dao.getOneFile(sqlSession, file_no);
	}

}
