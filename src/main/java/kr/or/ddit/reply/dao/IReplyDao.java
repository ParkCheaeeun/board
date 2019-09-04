package kr.or.ddit.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.reply.vo.ReplyVO;

public interface IReplyDao {
	
	List<ReplyVO> getReplyList(SqlSession sqlSession, int post_no);
	
	int insertReply(SqlSession sqlSession, ReplyVO vo);
	
	int updateReply(SqlSession sqlSession, ReplyVO vo);
	
	int disabledReply(SqlSession sqlSession, int reply_no);

}
