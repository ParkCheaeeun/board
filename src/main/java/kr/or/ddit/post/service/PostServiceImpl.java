package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.file.dao.FileDaoImpl;
import kr.or.ddit.file.dao.IFileDao;
import kr.or.ddit.file.vo.FileVo;
import kr.or.ddit.post.dao.IPostDao;
import kr.or.ddit.post.dao.PostDaoImpl;
import kr.or.ddit.post.vo.PostVO;
import kr.or.ddit.util.MybatisUtil;

public class PostServiceImpl implements IPostService {
	
	private static PostServiceImpl service;
	
	private IPostDao postDao;
	private IFileDao fileDao;
	
	PostServiceImpl(){
		postDao = PostDaoImpl.getInstance();
		fileDao = FileDaoImpl.getInstance();
	}
	
	public static PostServiceImpl getInstance() {
		if(service == null) service = new PostServiceImpl();
		return service;
	}
	
	
	@Override
	public List<PostVO> getPostList(int board_no) {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<PostVO> list = postDao.getPostList(sqlSession, board_no);
		
		sqlSession.close();
		
		return list;
	}

	@Override
	public Map<String, Object> getPost(int post_no) {
		SqlSession sqlSession = MybatisUtil.getSession();
		PostVO vo = postDao.getPost(sqlSession, post_no);
		List<FileVo> fileList = fileDao.getFile(sqlSession, post_no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileList", fileList);
		map.put("postVo", vo);
		
		return map;
	}

	@Override
	public int insertPost(PostVO postVo, List<FileVo> list) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int insertCnt = postDao.insertPost(sqlSession, postVo);
		
		//첨부파일 추가
		for(FileVo vo : list) {
			fileDao.insertFile(sqlSession, vo);
		}
		
		sqlSession.commit();
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public int updatePost(PostVO postVo, List<FileVo> list) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int updateCnt = postDao.updatePost(sqlSession, postVo);
		int gnUpdateCnt = postDao.updateGroupNo(sqlSession);
		
		for(FileVo vo : list) {
			fileDao.updateFile(sqlSession, vo);
		}
		
		sqlSession.commit();
		sqlSession.close();
		
		if(updateCnt == 1 && gnUpdateCnt == list.size()) {
			return 1;
		}
		return 0;
		
	}

	@Override
	public int disabledPost(int post_no) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = postDao.disabledPost(sqlSession, post_no);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public Map<String, Object> getPostPagingList(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		SqlSession sqlSession = MybatisUtil.getSession();
		List<PostVO> postList = postDao.getPostPagingList(sqlSession, page);
		int totalCnt = postDao.getPostTotalCnt(sqlSession);
		
		
		map.put("postList", postList);
		map.put("paginationSize", (int) Math.ceil((double)totalCnt/page.getPagesize()));
		
		sqlSession.close();
		return map;
	}
	
	

}
