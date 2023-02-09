package com.acorn.project.notice.service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.project.notice.dao.NoticeDao;
import com.acorn.project.notice.dto.NoticeDto;


@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public Map<String, Object> getList(int pageNum, String keyword, String condition) {
		final int PAGE_ROW_COUNT=5;
		final int PAGE_DISPLAY_COUNT=5;
		
		int num=1;
		String strPageNum=Integer.toString(pageNum);
		if(strPageNum != null){
			num=Integer.parseInt(strPageNum);
		}
		
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		int endRowNum=pageNum*PAGE_ROW_COUNT;
	
		if(keyword==null){
			keyword="";
			condition=""; 
		}

		String encodedK=URLEncoder.encode(keyword);
			
		NoticeDto dto=new NoticeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		if(!keyword.equals("")){
			if(condition.equals("title_content")){
				
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")){ 
				dto.setTitle(keyword);
			}
		}
		
		List<NoticeDto> list=noticeDao.getList(dto);
		
		int totalRow=noticeDao.getCount(dto);
		
		
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		

		
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; 
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		map.put("startPageNum", startPageNum);
		map.put("endPageNum", endPageNum);
		map.put("condition", condition);
		map.put("keyword", keyword);
		map.put("encodedK", encodedK);
		map.put("totalPageCount", totalPageCount);
		map.put("totalRow", totalRow);
		map.put("list", list);
		
		return map;
		
	}

	@Override
	public Map<String, Object> getDetail(int num, String keyword, String condition) {
				
		noticeDao.addViewCount(num);
		
		if(keyword==null){
			
			keyword="";
			condition=""; 
		}
		
		NoticeDto dto=new NoticeDto();
		
		dto.setNum(num);
		
		if(!keyword.equals("")){
			if(condition.equals("title_content")){
				
				dto.setTitle(keyword);
				dto.setContent(keyword);			
			}else if(condition.equals("title")){ 
				dto.setTitle(keyword);	
			}
		}
		
		NoticeDto resultDto=noticeDao.getData(dto);

		String encodedK=URLEncoder.encode(keyword);
		
		final int PAGE_ROW_COUNT=10;

		int pageNum=1;

		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;

		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		map.put("encodedK", encodedK);
		map.put("dto", resultDto);

		return map;
		
	}

	@Override
	public Map<String, String> saveContent(NoticeDto dto) {
		noticeDao.insert(dto);

		Map<String, String> map = new HashMap<String, String>();
		map.put("isSuccess","success");
				
		return map;
		
	}

	@Override
	public Map<String, String> updateContent(NoticeDto dto) {
		noticeDao.update(dto);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("isSuccess","success");
				
		return map;
	}

	@Override
	public Map<String, String> deleteContent(int num) {
		noticeDao.delete(num);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("isSuccess","success");
				
		return map;
		
	}

	@Override
	public Map<String, Object> getData(int num) {
		NoticeDto dto = noticeDao.getData(num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess","success");
		map.put("dto", dto);
		
		return map;
	}

}
