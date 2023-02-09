package com.acorn.project.notice.dao;

import java.util.List;

import com.acorn.project.notice.dto.NoticeDto;
import com.acorn.project.notice.dto.NoticeReq;
import com.acorn.project.notice.dto.NoticeRes;

public interface NoticeDao {
	public List<NoticeDto> getList(NoticeDto dto);
	//글의 갯수
	public int getCount(NoticeDto dto);
	//글 추가
	public void insert(NoticeRes dto);
	//글정보 얻어오기
	public NoticeDto getData(int num);
	//키워드를 활용한 글정보 얻어오기 (키워드에 부합하는 글중에서 이전글, 다음글의 글번호도 얻어올수 있도록)
	public NoticeDto getData(NoticeDto dto);
	//조회수 증가 시키기
	public void addViewCount(int num);
	//글 삭제
	public void delete(int num);
	//글 수정
	public void update(NoticeReq dto);
}
