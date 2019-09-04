package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	
	public List<BoardVO> getAllBoardList(SqlSession sqlSession);
	
	public int insertBoard(SqlSession sqlSession, BoardVO boardVo);
	
	public int updateBoard(SqlSession sqlSession, BoardVO boardVo);
	
	public BoardVO getBoard(SqlSession sqlSession, int board_no);
	
}
