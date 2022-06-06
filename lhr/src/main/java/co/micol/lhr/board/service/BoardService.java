package co.micol.lhr.board.service;

import java.util.List;

import co.micol.lhr.board.vo.BoardVO;

public interface BoardService {
	
	//게시글 전체목록
	List<BoardVO> boardList(int state, String key);
	//게시글 검색
	BoardVO boardSelect(BoardVO vo);
	//게시글 등록
	int boardInsert(BoardVO vo);
	//게시글 수정
	int boardUpdate(BoardVO vo);
	//게시글 삭제
	int boardDelete(BoardVO vo);
	//게시글 조회수
	int boardHitUpdate(int boardId);
}
