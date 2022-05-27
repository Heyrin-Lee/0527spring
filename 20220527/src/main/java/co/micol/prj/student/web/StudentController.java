package co.micol.prj.student.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.micol.prj.student.service.StudentService;

@Controller
public class StudentController { //호출명,
	@Autowired
	StudentService studentDao; //DAO 객체를 자동 주입
	
	@RequestMapping("/studentList.do")
	public String studentList(Model model) { //매소드명, Model 객체에 studentList를 담아서 
		model.addAttribute("students", studentDao.studentSelectList());
		return "student/studentList"; //돌려줄 페이지명을 통일시킨다, 이 페이지로 던진다
	}
}
