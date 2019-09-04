package kr.or.ddit.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.dao.BoardDaompl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.MybatisUtil;

public class BoardServiceImpl implements IBoardService {
	
	private static BoardServiceImpl boardService;
	private BoardDaompl boardDao;
	
	BoardServiceImpl(){
		boardDao = BoardDaompl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if(boardService == null) boardService = new BoardServiceImpl();
		return boardService;
	}
	
	@Override
	public List<BoardVO> getAllBoardList() {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<BoardVO> list = boardDao.getAllBoardList(sqlSession);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = boardDao.insertBoard(sqlSession, boardVo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = boardDao.updateBoard(sqlSession, boardVo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public BoardVO getBoard(int board_no) {
		SqlSession sqlSession = MybatisUtil.getSession();
		BoardVO vo = boardDao.getBoard(sqlSession, board_no);
		sqlSession.close();
		return vo;
	}

}
