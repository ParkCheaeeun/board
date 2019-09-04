package kr.or.ddit.user;

import static org.junit.Assert.assertEquals;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.vo.UserVO;
import kr.or.ddit.util.MybatisUtil;

public class UserServiceTest {
	
	UserDaoImpl userdao;
	SqlSession sqlSession;
	
	@Before
	public void setup() {
		userdao = UserDaoImpl.getInstance();
		sqlSession = MybatisUtil.getSession();
	}
	
	@Test
	public void test() {
		/***Given***/
		String user_id = "brown"; 

		/***When***/
		UserVO user = userdao.getUser(sqlSession, user_id);
		
		/***Then***/
		assertEquals(user.getPass(), "brown123");
	}

}
