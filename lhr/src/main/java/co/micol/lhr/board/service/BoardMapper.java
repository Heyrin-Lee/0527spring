package co.micol.lhr.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.micol.lhr.board.vo.BoardVO;

public interface BoardMapper {
	
	//게시글 전체목록
		List<BoardVO> boardList(@Param("state") int state, @Param("key") String key);
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
