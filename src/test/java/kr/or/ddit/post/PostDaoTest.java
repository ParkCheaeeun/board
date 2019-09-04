package kr.or.ddit.post;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.post.dao.PostDaoImpl;
import kr.or.ddit.post.vo.PostVO;
import kr.or.ddit.util.MybatisUtil;

public class PostDaoTest {
	
	PostDaoImpl dao;
	SqlSession sqlSession;
	
	@Before
	public void setUp() throws Exception {
		dao = PostDaoImpl.getInstance();
		sqlSession = MybatisUtil.getSession();
	}

	@After
	public void tearDown() throws Exception {
		sqlSession.commit();
		sqlSession.close();
	} 

	@Test
	public void insertPostTest() {
		/***Given***/
		PostVO vo = new PostVO(1, "안녕하세요", "반갑습니다.", "cony");
		
		/***When***/
		int cnt = dao.insertPost(sqlSession, vo);

		/***Then***/
		assertEquals(cnt, 1);
	}
	
	@Test
	public void getPostTest() {
		/***Given***/
		PostVO vo;

		/***When***/
		vo = dao.getPost(sqlSession, 2);
		
		/***Then***/
		assertEquals("brown", vo.getUser_id());
	}
	
	@Test
	public void getPostListTest() {
		/***Given***/
		List<PostVO> list;

		/***When***/
		list = dao.getPostList(sqlSession, 1);

		/***Then***/
		assertEquals(2, list.size());
	}
	
	@Test
	public void updatePostTest() {
		/***Given***/
		PostVO vo = new PostVO(1, "안녕하세요", "반갑습니다.", "cony");
		vo.setPost_no(3);
		
		/***When***/
		int cnt = dao.updatePost(sqlSession, vo);
		
		/***Then***/
		assertEquals(cnt, 1);
	}

}
