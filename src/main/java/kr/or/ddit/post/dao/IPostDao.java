package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.vo.PostVO;

public interface IPostDao {
	List<PostVO> getPostList(SqlSession sqlSession, int board_no);
	
	PostVO getPost(SqlSession sqlSession, int post_no);
	
	int insertPost(SqlSession sqlSession, PostVO postVo);
	
	int updatePost(SqlSession sqlSession, PostVO postVo);
	
	int updateGroupNo(SqlSession sqlSession);
	
	int disabledPost(SqlSession sqlSession, int post_no);
	
	List<PostVO> getPostPagingList(SqlSession sqlSession, Page page);
	
	int getPostTotalCnt(SqlSession sqlSession);
}
