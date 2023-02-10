package com.acorn.project.faq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.project.faq.dao.FaqDao;
import com.acorn.project.faq.dto.FaqDto;
import com.acorn.project.faq.dto.FaqReq;
import com.acorn.project.faq.dto.FaqRes;
import com.acorn.project.notice.dto.NoticeDto;

@Service
public class FaqServiceImpl implements FaqService{

	@Autowired
	private FaqDao faqDao;
	
	@Override
	public Map<String, Object> list(int pageNum, String question, String content) {
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=10;
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=10;
		
		//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
		int num=1;
		//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
		String strPageNum=Integer.toString(pageNum);
		//만일 페이지 번호가 파라미터로 넘어 온다면
		if(strPageNum != null){
			//숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
			num=Integer.parseInt(strPageNum);
		}
		
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(num-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=num*PAGE_ROW_COUNT;
		/*
		String question=request.getParameter("question");
		String content=request.getParameter("content");
		*/
		//CafeDto 객체에 startRowNum 과 endRowNum 을 담는다.
		FaqDto dto=new FaqDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//글 목록 얻어오기 
		List<FaqDto> list=faqDao.getList(dto);
		//전체글의 갯수
		int totalRow=faqDao.getCount(dto);
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((num-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		

		//전체 페이지의 갯수
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 전체 페이지 갯수보다 크다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다.
		}
		
		dto.setQuestion(question);
		dto.setContent(content);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageNum", num);
		map.put("startPageNum", startPageNum);
		map.put("endPageNum", endPageNum);
		map.put("totalPageCount", totalPageCount);
		map.put("totalRow", totalRow);
		
		return map;
	}
	
	@Override
	public Map<String, String> saveContent(FaqRes dto) {
		faqDao.insert(dto);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("isSuccess","success");
				
		return map;
	}

	@Override
	public Map<String, String> updateContent(FaqReq dto) {
		faqDao.update(dto);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("isSuccess","success");
				
		return map;
	}

	@Override
	public Map<String, Object> getData(int num) {
		FaqDto dto= faqDao.getData(num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess","success");
		map.put("dto", dto);
		
		return map;
	}
	
	@Override
	public Map<String, String> deleteContent(int num) {
		faqDao.delete(num);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("isSuccess","success");
				
		return map;
	}

}
