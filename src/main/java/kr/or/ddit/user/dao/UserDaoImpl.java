package kr.or.ddit.user.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.vo.UserVO;

public class UserDaoImpl implements IUserDao{
	
	private static UserDaoImpl userDao;
	
	public static UserDaoImpl getInstance() {
		if(userDao == null) userDao = new UserDaoImpl();
		return userDao;
	}

	@Override
	public UserVO getUser(SqlSession sqlSession, String user_id) {
		return sqlSession.selectOne("user.getUser", user_id);
	}
	

}
