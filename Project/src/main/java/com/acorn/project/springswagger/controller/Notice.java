package com.acorn.project.springswagger.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acorn.project.notice.dto.NoticeDto;
import com.acorn.project.notice.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api("NoticeController")
@RequestMapping("/api/notice")
public class Notice {
	@Autowired private NoticeService service;
	
	@ApiOperation(value="공지 사항 리스트", notes = "공지사항을 리스트를 출력함.")
	@GetMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "pageNum", required = true)int pageNum,
			@RequestParam(value = "keyword", required = false)String keyword,
			@RequestParam(value = "condition", required = false)String condition){	
		return service.getList(pageNum, keyword, condition);
	}
	
	
	@ApiOperation(value="공지 사항 상세", notes = "공지사항 상세 (검색에 대한 값도 포함되어 있음)")
	@GetMapping("/{num}")
	public Map<String, Object> detail(@PathVariable int num, @RequestParam(value = "keyword", required = false)String keyword,
			@RequestParam(value = "condition", required = false)String condition) {
		
		return service.getDetail(num, keyword, condition);
	}
	
	@ApiOperation(value="공지 사항 등록", notes = "공지사항을 입력 받아 등록한다.")
	@PostMapping("/insert")
	public Map<String, String> insert(@RequestBody NoticeDto dto) {
		return service.saveContent(dto);
	}
	
	@ApiOperation(value="공지 사항 수정", notes = "공지사항을 입력 받아 등록한다.")
	@PutMapping("/{num}/update")
	public Map<String, String> update(@PathVariable int num, @RequestParam(value = "title", required = false)String title,
			@RequestParam(value = "content", required = false)String content){
		
		NoticeDto dto = new NoticeDto();
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);
		
		return service.updateContent(dto);
	}
	
	@ApiOperation(value="공지 사항 업데이트 전 상세", notes = "공지사항의 업데이트 전 그 상세 데이터 내용을 가져온다.")
	@GetMapping("/{num}/update")
	public Map<String, Object> update(@PathVariable int num){
		
		return service.getData(num);
	}
	
	@ApiOperation(value="공지 사항 삭제", notes = "공지사항의 데이터를 삭제한다.")
	@DeleteMapping("/{num}/delete")
	public Map<String, String> delete(@PathVariable int num){
		
		return service.deleteContent(num);
	}
	
}