package co.micol.lhr.board.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.micol.lhr.board.service.BoardService;
import co.micol.lhr.board.vo.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardDao;
	
	//게시글 전체 목록
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		model.addAttribute("board", boardDao.boardList(1, " 전체"));
		return "board/boardList";
	}
	
	//게시글 등록폼
	@RequestMapping("boardInsertForm.do")
	public String boardInsertForm() {
		return "board/boardInsertForm";
	}
	
	//게시글 등록
	@RequestMapping("boardInsert.do")
	public String boardInsert(BoardVO vo) {
		boardDao.boardInsert(vo);
		return "redirect:boardList.do";
	}
	
	//게시글 상세보기
	@PostMapping("/getContent.do")
	public String getContent(BoardVO vo, Model model) {
		boardDao.boardHitUpdate(vo.getBoardId());
		model.addAttribute("content", boardDao.boardSelect(vo));
		return "board/boardContent";
	}
	
	//게시글 수정폼
	@RequestMapping("/boardUpdateForm.do")
	public String boardUpdateForm(BoardVO vo, Model model) {
		model.addAttribute("board",boardDao.boardSelect(vo));
		return "board/boardUpdateForm";
	}
	
	//게시글 수정
	@RequestMapping("/boardUpdate.do")
	public String boardUpdate(BoardVO vo) {
		boardDao.boardUpdate(vo);
		return "redirect:boardList.do";
	}
	
	//게시글 삭제
	@RequestMapping("/boardDelete.do")
	public String boardDelete(BoardVO vo) {
		boardDao.boardDelete(vo);
		return "redirect:boardList.do";
	}
	
	@RequestMapping("/ajaxSearchList.do")
	@ResponseBody
	public List<BoardVO> ajaxSearchList(@RequestParam("state") int state, @RequestParam("key") String key) {
		return boardDao.boardList(state, key);
	}
}
