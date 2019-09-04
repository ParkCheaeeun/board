package kr.or.ddit.board;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.board.dao.BoardDaompl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.MybatisUtil;

public class BoardDaoTest {

	BoardDaompl boardDao;
	SqlSession sqlSession;
	
	@Before
	public void setup() {
		boardDao = BoardDaompl.getInstance();
		sqlSession = MybatisUtil.getSession();
	}
	
	@Test
	public void boardDaoInsertTest() {
		/***Given***/
		BoardVO boardVo = new BoardVO("Q&A 게시판", "Y");
		
		/***When***/
		int cnt = boardDao.insertBoard(sqlSession, boardVo);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(cnt, 1);
	}
	
	@Test
	public void getAllBoardTest() {
		/***Given***/
		List<BoardVO> list = new ArrayList<BoardVO>();

		/***When***/
		list = boardDao.getAllBoardList(sqlSession);

		/***Then***/
		assertEquals(2, list.size());
	}
	
	@Test
	public void updateBoardTest() {
		/***Given***/
		BoardVO vo = new BoardVO();
		vo.setBoard_no(8);
		vo.setUsable("N");

		/***When***/
		int cnt = boardDao.updateBoard(sqlSession, vo);

		/***Then***/
		assertEquals(1, cnt);
	}

}
