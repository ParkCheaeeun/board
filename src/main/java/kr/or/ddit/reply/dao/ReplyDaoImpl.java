package kr.or.ddit.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.reply.vo.ReplyVO;
import kr.or.ddit.util.MybatisUtil;

public class ReplyDaoImpl implements IReplyDao {
	
	private static ReplyDaoImpl dao;
	
	public static ReplyDaoImpl getInstance() {
		if(dao == null) dao = new ReplyDaoImpl();
		return dao;
	}
	
	@Override
	public List<ReplyVO> getReplyList(SqlSession sqlSession, int post_no) {
		return sqlSession.selectList("reply.getReplyList", post_no);
	}

	@Override
	public int insertReply(SqlSession sqlSession, ReplyVO vo) {
		return sqlSession.insert("reply.insertReply", vo);
	}

	@Override
	public int updateReply(SqlSession sqlSession, ReplyVO vo) {
		return sqlSession.update("reply.updateReply", vo);
	}

	@Override
	public int disabledReply(SqlSession sqlSession, int reply_no) {
		return sqlSession.update("reply.disabledReply", reply_no);
	}

}
