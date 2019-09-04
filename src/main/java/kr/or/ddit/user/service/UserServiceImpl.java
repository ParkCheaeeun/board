package kr.or.ddit.user.service;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.vo.UserVO;
import kr.or.ddit.util.MybatisUtil;

public class UserServiceImpl implements IUserService {
	
	
	private static UserServiceImpl userService;
	UserDaoImpl userDao;
	
	UserServiceImpl(){
		userDao = UserDaoImpl.getInstance();
	}
	
	public static UserServiceImpl getInstance() {
		if(userService == null) userService = new UserServiceImpl();
		return userService;
	}
	
	@Override
	public UserVO getUser(String user_id) {
		SqlSession sqlSession = MybatisUtil.getSession();
		UserVO userVo = userDao.getUser(sqlSession, user_id);
		sqlSession.close();
		return userVo;
	}

}
