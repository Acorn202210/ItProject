package com.acorn.project.faq.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.acorn.project.faq.dto.FaqDto;
import com.acorn.project.faq.dto.FaqReq;
import com.acorn.project.faq.dto.FaqRes;

public interface FaqService {
	public Map<String, String> saveContent(FaqRes dto);
	public Map<String, String> updateContent(FaqReq dto);
	public Map<String, String> deleteContent(int num);
	public Map<String, Object> getData(int num);
	public Map<String, Object> list(int pageNum, String question, String content);
	
}
