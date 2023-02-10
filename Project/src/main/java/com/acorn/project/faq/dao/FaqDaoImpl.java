package com.acorn.project.faq.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.project.faq.dto.FaqDto;
import com.acorn.project.faq.dto.FaqReq;
import com.acorn.project.faq.dto.FaqRes;

@Repository
public class FaqDaoImpl implements FaqDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public List<FaqDto> getList(FaqDto dto) {

		return session.selectList("faq.getList", dto);
	}
	
	@Override
	public int getCount(FaqDto dto) {

		return session.selectOne("faq.getCount", dto);
	}

	@Override
	public void insert(FaqRes dto) {
		
		session.insert("faq.insert", dto);
	}

	@Override
	public FaqDto getData(int num) {
		
		return session.selectOne("faq.getData", num);
	}

	@Override
	public FaqDto getData(FaqDto dto) {
		
		return session.selectOne("faq.getData", dto);
	}
	
	@Override
	public void delete(int num) {
		session.delete("faq.delete", num);
	}

	@Override
	public void update(FaqReq dto) {
		session.update("faq.update", dto);
	}

}
