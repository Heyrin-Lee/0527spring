package co.micol.prj.student.service;

import java.util.List;

import co.micol.prj.student.vo.StudentVO;

public interface StudentMapper { //mybatis에서 사용하는 인터페이스 (student-map.xml에서 namespace부분에 co.micol.prj.student.service.StudentMapper와 같이 이 인터페이스 이름으로 맞춰줘야한다 
	List<StudentVO> studentSelectList(); //전체회원 조회
	StudentVO studentSelect(StudentVO vo); //한명 회원 조회 or  로그인 처리
	int studentInsert(StudentVO vo); //회원 추가
	int studentUpdate(StudentVO vo); //회원정보 수정
	int studentDelete(StudentVO vo); //회원 삭제
	
	boolean idCheck(String id); //아이디 중복체크
}
