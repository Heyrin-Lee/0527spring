package co.micol.prj.notice.web;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.vo.NoticeVO;

@Controller
public class NoticeController {
	@Autowired private NoticeService noticeDao;
	
	@Autowired private String saveDir; //IoC 컨테이너에서 저장된 것을 가져온다
	
	@RequestMapping("/noticeList.do") //전체목록보기
	public String noticeList(Model model) {
		model.addAttribute("notices", noticeDao.noticeSelectList(1, "전체"));
		
		return "notice/noticeList";
	}
	
	@RequestMapping("/noticeInsertForm.do") //글등록 폼 호출
	public String noticeInsertForm() {
		return "notice/noticeInsertForm";
	}
	
	@PostMapping("/noticeInsert.do")
	public String noticeInsert(NoticeVO vo, MultipartFile file) {
		String fileName = file.getOriginalFilename();
		if(fileName != null) {
			File target = new File(saveDir,fileName); // [c:temp\저장된 파일 이름] 과 같은 모양이 된다 // c:temp는 정하는 경로에 따라 다르다
			vo.setNoticeAttech(fileName); //오리지널 파일네임을 담고
			vo.setNoticeDir(target.toString()); //실제 저장경로를 담고
			try {
				FileCopyUtils.copy(file.getBytes(), target); //실제 파일을 저장한다
				
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		noticeDao.noticeInsert(vo); //파일이 있을경우 파일을 먼저 업로드하고 DB에 저장한다
		return "redirect:noticeList.do";
	}
}
