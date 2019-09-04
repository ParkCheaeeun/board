package kr.or.ddit.user.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.vo.UserVO;

public interface IUserDao {
	
	public UserVO getUser(SqlSession sqlSession, String user_id);

}
