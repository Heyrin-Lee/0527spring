package co.micol.prj.notice.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.micol.prj.notice.vo.NoticeVO;

public interface NoticeMapper {
	List<NoticeVO> noticeSelectList(@Param("state") int state, @Param("key") String key); //두개 이상의 파라미터를 사용하려면 @param을 써야한다. *mapperInterface에서만! serviceInterface에서는 안써도 된다!*
	NoticeVO noticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeUpdate(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
	
	int noticeHitUpdate(int id); //조회수 변경
}
