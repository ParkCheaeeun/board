package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.reply.vo.ReplyVO;

public interface IReplyService {
	
	List<ReplyVO> getReplyList(int post_no);
	
	int insertReply(ReplyVO vo);
	
	int updateReply(ReplyVO vo);
	
	int disabledReply(int reply_no);

}
