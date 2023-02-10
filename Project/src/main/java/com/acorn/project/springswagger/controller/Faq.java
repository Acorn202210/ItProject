package com.acorn.project.springswagger.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acorn.project.faq.dto.FaqDto;
import com.acorn.project.faq.dto.FaqReq;
import com.acorn.project.faq.dto.FaqRes;
import com.acorn.project.faq.service.FaqService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api("FaqController")
@RequestMapping("/api/faq")
public class Faq {
	@Autowired private FaqService service;
	
	@ApiOperation(value="자주묻는질문 리스트", notes = "자주묻는질문의 리스트를 출력함.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "페이지 번호", required = true),
		@ApiImplicitParam(name = "question", value = "질문"),
		@ApiImplicitParam(name = "content", value = "답변")
	})
	@GetMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "pageNum", required = true)int pageNum,
			@RequestParam(value = "question", required = false)String question,
			@RequestParam(value = "content", required = false)String content){	
		return service.list(pageNum, question, content);
	}
	
	@ApiOperation(value="자주묻는질문 등록", notes = "자주묻는질문을 입력 받아 등록한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "question", value = "질문"),
		@ApiImplicitParam(name = "content", value = "답변")
	})
	@PostMapping("/insert")
	public Map<String, String> insert(@RequestBody FaqRes faqres) {
		return service.saveContent(faqres);
	}
	
	@ApiOperation(value="자주묻는질문 수정", notes = "자주묻는질문을 입력 받아 등록한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "num", value = "질문번호", required = true),
		@ApiImplicitParam(name = "question", value = "질문"),
		@ApiImplicitParam(name = "content", value = "답변")
	})
	@PutMapping("/{num}/update")
	public Map<String, String> update(@PathVariable int num, @RequestBody FaqRes faqRes){
		
		FaqReq dto = new FaqReq();
		dto.setNum(num);
		dto.setQuestion(faqRes.getQuestion());
		dto.setContent(faqRes.getContent());
		
		return service.updateContent(dto);
	}
	
	@ApiOperation(value="자주묻는질문 업데이트 전 상세", notes = "자주묻는질문의 업데이트 전 그 상세 데이터 내용을 가져온다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "num", value = "질문번호", required = true)
	})
	@GetMapping("/{num}/update")
	public Map<String, Object> update(@PathVariable int num){
		
		return service.getData(num);
	}
	
	@ApiOperation(value="자주묻는질문 삭제", notes = "자주묻는질문의 데이터를 삭제한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "num", value = "질문번호", required = true)
	})
	@DeleteMapping("/{num}/delete")
	public Map<String, String> delete(@PathVariable int num){
		
		return service.deleteContent(num);
	}
	
}