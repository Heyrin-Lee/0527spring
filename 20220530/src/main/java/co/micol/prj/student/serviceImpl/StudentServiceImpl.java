package co.micol.prj.student.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.micol.prj.student.service.StudentMapper;
import co.micol.prj.student.service.StudentService;
import co.micol.prj.student.vo.StudentVO;
@Repository("studentDao")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper map; //IoC컨테이너에서 student-map.xml파일을 map이라는 변수로 자동주입한다 (=StudentMapper)
	
	@Override
	public List<StudentVO> studentSelectList() {
		return map.studentSelectList();
	}

	@Override
	public StudentVO studentSelect(StudentVO vo) {
		return map.studentSelect(vo);
	}

	@Override
	public int studentInsert(StudentVO vo) {
		return map.studentInsert(vo);
	}

	@Override
	public int studentUpdate(StudentVO vo) {
		return map.studentUpdate(vo); //이름만 변경할 수 있다
	}

	@Override
	public int studentDelete(StudentVO vo) {
		return map.studentDelete(vo);
	}

	@Override
	public boolean idCheck(String id) {
		return map.idCheck(id);
	}
	

}
