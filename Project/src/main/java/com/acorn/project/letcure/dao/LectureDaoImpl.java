package com.acorn.project.letcure.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.project.lecture.dto.LectureDto;

@Repository
public class LectureDaoImpl implements LectureDao{

	@Autowired private SqlSession session;
	
	@Override
	public List<LectureDto> LectureList(LectureDto dto) {
		return session.selectList("lecture.lectureList", dto);
	}
	
	@Override
	public int getCount() {
		return session.selectOne("lecture.getCount");
	}

	@Override
	public void insert(LectureDto dto) {
		session.insert("lecture.insert", dto);
		
	}

	@Override
	public LectureDto getData(int num) {
		return session.selectOne("lecture.getData", num);
	}

	@Override
	public void delete(int num) {
		session.delete("lecture.delete", num);
		
	}

	@Override
	public void update(LectureDto dto) {
		session.update("lecture.update", dto);
		
	}

	@Override
	public void addViewCount(int num) {
		session.update("lecture.addViewCount", num);
		
	}


}
