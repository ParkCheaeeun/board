package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;

public class BoardDaompl implements IBoardDao {
	
	private static BoardDaompl dao;
	
	public static BoardDaompl getInstance() {
		if(dao == null) dao = new BoardDaompl();
		return dao;
	}
	
	@Override
	public List<BoardVO> getAllBoardList(SqlSession sqlSession) {
		return sqlSession.selectList("board.getAllBoardList");
	}

	@Override
	public int insertBoard(SqlSession sqlSession, BoardVO boardVo) {
		return sqlSession.insert("board.insertBoard", boardVo);
	}

	@Override
	public int updateBoard(SqlSession sqlSession, BoardVO boardVo) {
		return sqlSession.update("board.updateBoard", boardVo);
	}

	@Override
	public BoardVO getBoard(SqlSession sqlSession, int board_no) {
		return sqlSession.selectOne("board.getBoard", board_no);
	}

}
