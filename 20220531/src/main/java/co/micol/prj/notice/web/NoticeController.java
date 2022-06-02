package co.micol.prj.notice.web;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		if(fileName != null && !fileName.isEmpty() && fileName.length() != 0 ) {
			//UUID : 범용 고유 식별자
			//UUID 사용하여 파일명 변경
			String uid = UUID.randomUUID().toString();
			String saveFileName = uid + fileName.substring(fileName.indexOf("."));
			//uuid.xml 과 같이 만들기 위해서 UUID로 만든 파일명 넣기
			File target = new File(saveDir,saveFileName); // [c:temp\저장된 파일 이름] 과 같은 모양이 된다 // c:temp는 정하는 경로에 따라 다르다
			
			vo.setNoticeAttech(fileName); //오리지널 파일명
			vo.setNoticeDir(saveFileName); //실제 저장경로를 담고
			try {
				FileCopyUtils.copy(file.getBytes(), target); //실제 파일을 저장한다
				
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		noticeDao.noticeInsert(vo); //파일이 있을경우 파일을 먼저 업로드하고 DB에 저장한다
		return "redirect:noticeList.do"; //view resolver에게 보내지말고 바로 handlㅣer controller에게 보내라
	}
	
	@PostMapping("/getContent.do")
	public String getContent(NoticeVO vo, Model model) {
	//getContent.do?noticeId=2
	//public String getContent(@RequestParam int noticeId) { //@requestParam은 get방식일때 사용가능하다. @requestParam은 getContent.do?에서 ?뒤에 파라미터값을 읽어오는 역할을 한다. 그래서 직접 파라미터값을 지정해주는 방법이 있고, 그냥 한꺼번에 vo 전체를 다 지정해주는 방법. 총 2가지가 있다
		noticeDao.noticeHitUpdate(vo.getNoticeId());
		model.addAttribute("content", noticeDao.noticeSelect(vo));
		return "notice/noticeContent";
	}
	
	@RequestMapping("/downLoad.do")
	public void downLoad(@RequestParam("saveName") String saveName, @RequestParam("fileName")String fileName, HttpServletResponse resp) { //saveName = noticeDir의 값
		try {
		//file을 배열로 전환
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(
				new File(saveDir+ "\\" +saveName)); //"C:\\temp\\" 혹은 saveDir+ "\\" 
		//모든 타입의 데이터를 전송할 대 사용
		resp.setContentType("application/octet-stream");
		//파일 길이만큼 사이즈 설정
		resp.setContentLength(fileByte.length);
		//파일을 다운받기 위해 설정
		resp.setHeader("Content-Disposition", "attachment; fileName=\"" 
		+URLEncoder.encode(fileName,"UTF-8") + "\";"); //try부터 여기까지는 파일을 다운로드 받기 위해서 설정한 부분 //saveName = UUID이름으로 파일 다운로드되고 fileName = 파일원본의 이름으로 다운로드된다
		
		resp.getOutputStream().write(fileByte); //파일 저장
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	} catch(IOException e) {
		e.printStackTrace();
	}
	}
	
	@RequestMapping("/noticeDelete.do")
	public String noticeDelete(NoticeVO vo) {
		noticeDao.noticeDelete(vo);
		return "redirect:noticeList.do";
	}
	
	@RequestMapping("/noticeModifyForm.do")
	public String noticeModifyForm(NoticeVO vo, Model model) {
		model.addAttribute("notice", noticeDao.noticeSelect(vo));
		return "notice/noticeModifyForm";
	}
	
	@PostMapping("/noticeModify.do")
	public String noticeModify(NoticeVO vo, MultipartFile file) {
		String fileName = file.getOriginalFilename();
		
		if(fileName != null && !fileName.isEmpty() && fileName.length() != 0 ) {
			//UUID : 범용 고유 식별자
			//UUID 사용하여 파일명 변경
			String uid = UUID.randomUUID().toString();
			String saveFileName = uid + fileName.substring(fileName.indexOf("."));
			//uuid.xml 과 같이 만들기 위해서 UUID로 만든 파일명 넣기
			File target = new File(saveDir,saveFileName); // [c:temp\저장된 파일 이름] 과 같은 모양이 된다 // c:temp는 정하는 경로에 따라 다르다
			
			vo.setNoticeAttech(fileName); //오리지널 파일네임을 담고
			vo.setNoticeDir(saveFileName); //실제 저장경로를 담고
			try {
				FileCopyUtils.copy(file.getBytes(), target); //실제 파일을 저장한다
				
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		noticeDao.noticeUpdate(vo);
		
		return "redirect:noticeList.do";
	}
	
	@PostMapping("/ajaxSearchList.do")
	@ResponseBody
	public List<NoticeVO> ajaxSearchList(@RequestParam("state") int state, @RequestParam("key") String key) {
		
		return noticeDao.noticeSelectList(state, key);
	}
}
