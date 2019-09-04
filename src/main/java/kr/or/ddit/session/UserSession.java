package kr.or.ddit.session;

import kr.or.ddit.user.vo.UserVO;

public class UserSession {
	
	private static UserSession session;
	public UserVO user;
	
	public static UserSession getInstance() {
		if(session == null) session = new UserSession();
		return session;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
	
}
