package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	public List<BoardVO> getAllBoardList();
	
	public int insertBoard(BoardVO boardVo);
	
	public int updateBoard(BoardVO boardVo);
	
	public BoardVO getBoard(int board_no);
}
