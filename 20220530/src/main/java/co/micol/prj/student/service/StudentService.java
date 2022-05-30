package co.micol.prj.student.service;

import java.util.List;

import co.micol.prj.student.vo.StudentVO;

public interface StudentService { //dao에서 사용하는 인터페이스 
	// dao에서 사용하는 인터페이스의 매소드와 mybatis에서 사용하는 인터페이스의 매소드의 갯수는 같아야한다. 근데 각각 하나씩 만드는 이유는 매개변수를 하나이상 전달하기 위해서는 mybatis인터페이스에서는 매개변수를 받는 방법이 바뀌기 때문에 dao,mybatis 각각 하나씩 인터페이스를 만들어야 한다
	List<StudentVO> studentSelectList(); //전체회원 조회
	StudentVO studentSelect(StudentVO vo); //한명 회원 조회 or  로그인 처리
	int studentInsert(StudentVO vo); //회원 추가
	int studentUpdate(StudentVO vo); //회원정보 수정
	int studentDelete(StudentVO vo); //회원 삭제
	
	boolean idCheck(String id); //아이디 중복체크
}
