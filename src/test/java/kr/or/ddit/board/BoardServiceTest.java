package kr.or.ddit.board;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.MybatisUtil;

public class BoardServiceTest {
	
	BoardServiceImpl boardService;
	SqlSession sqlSession;
	
	@Before
	public void setup() {
		boardService = BoardServiceImpl.getInstance();
		sqlSession = MybatisUtil.getSession();
	}
	
	@After
	public void tearDown() {
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void boardDaoInsertTest() {
		/***Given***/
		BoardVO boardVo = new BoardVO("Q&A 게시판", "Y");
		
		/***When***/
		int cnt = boardService.insertBoard(boardVo);
		
		/***Then***/
		assertEquals(cnt, 1);
	}
	
	@Test
	public void getAllBoardTest() {
		/***Given***/
		List<BoardVO> list = new ArrayList<BoardVO>();

		/***When***/
		list = boardService.getAllBoardList();

		/***Then***/
		assertEquals(3, list.size());
	}
	
	@Test
	public void updateBoardTest() {
		/***Given***/
		BoardVO vo = new BoardVO();
		vo.setBoard_no(8);
		vo.setUsable("Y");

		/***When***/
		int cnt = boardService.updateBoard(vo);

		/***Then***/
		assertEquals(1, cnt);
	}

}
