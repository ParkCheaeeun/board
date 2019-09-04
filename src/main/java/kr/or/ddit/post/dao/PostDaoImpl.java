package kr.or.ddit.post.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.vo.PostVO;

public class PostDaoImpl implements IPostDao {
	
	private static PostDaoImpl dao;
	
	public static PostDaoImpl getInstance() {
		if(dao == null) dao = new PostDaoImpl();
		return dao;
	}

	@Override
	public List<PostVO> getPostList(SqlSession sqlSession, int board_no) {
		return sqlSession.selectList("post.getPostList", board_no);
	}

	@Override
	public PostVO getPost(SqlSession sqlSession, int post_no) {
		return sqlSession.selectOne("post.getPost", post_no);
	}

	@Override
	public int insertPost(SqlSession sqlSession, PostVO postVo) {
		return sqlSession.insert("post.insertPost", postVo);
	}

	@Override
	public int updatePost(SqlSession sqlSession, PostVO postVo) {
		return sqlSession.update("post.updatePost", postVo);
	}

	@Override
	public int updateGroupNo(SqlSession sqlSession) {
		return sqlSession.update("post.updateGroupNo");
	}

	@Override
	public int disabledPost(SqlSession sqlSession, int post_no) {
		return sqlSession.update("post.disabledPost", post_no);
	}

	@Override
	public List<PostVO> getPostPagingList(SqlSession sqlSession, Page page) {
		List<PostVO> list = sqlSession.selectList("post.getPostPagingList", page);
		return list;
	}

	@Override
	public int getPostTotalCnt(SqlSession sqlSession) {
		return sqlSession.selectOne("post.getPostTotalCnt");
	}

}
