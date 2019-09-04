package kr.or.ddit.reply.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.reply.dao.ReplyDaoImpl;
import kr.or.ddit.reply.vo.ReplyVO;
import kr.or.ddit.util.MybatisUtil;

public class ReplyServiceImpl implements IReplyService {
	
	private static ReplyServiceImpl service;
	private ReplyDaoImpl dao;
	
	ReplyServiceImpl(){
		dao = ReplyDaoImpl.getInstance();
	}
	
	public static ReplyServiceImpl getInstance() {
		if(service == null) service = new ReplyServiceImpl();
		return service;
	}
	

	@Override
	public List<ReplyVO> getReplyList(int post_no) {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<ReplyVO> list = dao.getReplyList(sqlSession, post_no);
		sqlSession.close();
		
		return list;
	}

	@Override
	public int insertReply(ReplyVO vo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.insertReply(sqlSession, vo);
		sqlSession.commit();
		sqlSession.close();
		
		return cnt;
	}

	@Override
	public int updateReply(ReplyVO vo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.updateReply(sqlSession, vo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int disabledReply(int reply_no) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.disabledReply(sqlSession, reply_no);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

}
