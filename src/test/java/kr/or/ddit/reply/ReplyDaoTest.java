package kr.or.ddit.reply;

import static org.junit.Assert.assertEquals;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.reply.dao.ReplyDaoImpl;
import kr.or.ddit.reply.vo.ReplyVO;
import kr.or.ddit.util.MybatisUtil;

public class ReplyDaoTest {
	
	ReplyDaoImpl dao;

	@Before
	public void setUp() throws Exception {
		dao = ReplyDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void InsertReplyTest() {
		/***Given***/
		SqlSession sqlSession = MybatisUtil.getSession();
		ReplyVO replyVo = new ReplyVO();
		replyVo.setPost_no(3);
		replyVo.setUser_id("brown");
		replyVo.setContent("안녕하세요!");

		/***When***/
		int cnt = dao.insertReply(sqlSession, replyVo);
		sqlSession.commit();

		/***Then***/
		assertEquals(cnt, 1);
	}
	
	@Test
	public void updateReplyDao() {
		/***Given***/
		SqlSession sqlSession = MybatisUtil.getSession();
		ReplyVO replyVo = new ReplyVO();
		replyVo.setReply_no(2);
		replyVo.setPost_no(3);
		replyVo.setUser_id("brown");
		replyVo.setContent("반가와요!");
		
		/***When***/
		int cnt = dao.updateReply(sqlSession, replyVo);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void disabledReply() {
		/***Given***/
		SqlSession sqlSession = MybatisUtil.getSession();
		
		/***When***/
		int cnt = dao.disabledReply(sqlSession, 2);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(1, cnt);
	}

}
