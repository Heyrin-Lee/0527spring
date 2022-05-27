package co.micol.prj.student.service;

import java.util.List;

import co.micol.prj.student.vo.StudentVO;

public interface StudentService {
	List<StudentVO> studentSelectList();
	StudentVO studentSelect(StudentVO vo); //한명 데이터 조회
	int studentInsert(StudentVO vo);
	int studentUpdate(StudentVO vo);
	int studentDelete(StudentVO vo);
	
	boolean stduentIdcheck(String id); //아이디 중복체크
}
